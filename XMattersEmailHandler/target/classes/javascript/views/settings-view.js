define('jira/mail/views/settings-view', [
    'jira/mail/entities/projects',
    'jira/mail/entities/issue-types',
    'jira/mail/entities/bulk-options',
    'jira/mail/views/checkbox-view',
    'jira/mail/views/text-view',
    'jira/mail/views/default-reporter-view',
    'jira/mail/views/projects-view',
    'jira/mail/views/issue-types-view',
    'jira/mail/views/bulk-options-view',
    'jira/ajs/ajax/smart-ajax',
    'jira/flag',
    'jquery',
    'backbone',
    'underscore'
], function(
    Projects,
    IssueTypes,
    BulkOptions,
    CheckboxView,
    TextView,
    DefaultReporterView,
    ProjectsView,
    IssueTypesView,
    BulkOptionsView,
    SmartAjax,
    JiraFlag,
    jQufery,
    Backbone,
    _
) {
    return Backbone.View.extend({
        events: {
            "click #testButton": "testHandler",
            "submit": "saveHandler"
        },
        initialize: function() {
            _.bindAll(this, "testHandler", "saveHandler", "testResultsInlineContent", "testCompleteHandler");
            this.model.bind("change", this.modelChanged, this);
        },
        render: function() {
            if (jQuery("#project").length > 0) {
                this.projectsView = new ProjectsView({
                    el: jQuery("#project"),
                    model: this.model,
                    collection: new Projects(this.options.availableProjects)
                }).render();
                this.issueTypesView = new IssueTypesView({
                    el: jQuery("#issuetype"),
                    model: this.model,
                    collection: new IssueTypes()
                }).render();
                this.projectsView.issueTypesView = this.issueTypesView;
            }

            this.createusersView = new CheckboxView({el: jQuery("#createusers"), model: this.model}).render();
            this.notifyusersView = new CheckboxView({el: jQuery("#notifyusers"), model: this.model}).render();
            this.reporterView = new DefaultReporterView({el: jQuery("#reporterusername"), model: this.model}).render();
            this.catchemailView = new TextView({el: jQuery("#catchemail"), model: this.model}).render();
            this.forwardEmailView = new TextView({el: jQuery("#forwardEmail"), model: this.model}).render();

            if (jQuery("#stripquotes").length > 0) {
                this.stripquotes = new CheckboxView({el: jQuery("#stripquotes"), model: this.model}).render();
            }

            if (jQuery("#splitregex").length > 0) {
                this.splitregexView = new TextView({el: jQuery("#splitregex"), model: this.model}).render();
            }

            if (jQuery("#ccwatcher").length > 0) {
                this.ccwatcherView = new CheckboxView({el: jQuery("#ccwatcher"), model: this.model}).render();
                this.ccassigneeView = new CheckboxView({el: jQuery("#ccassignee"), model: this.model}).render();
            }

            this.bulkOptionsView = new BulkOptionsView({
                el: jQuery("#bulk"),
                model: this.model,
                collection: new BulkOptions(this.options.availableBulkOptions)
            }).render();

            this.modelChanged();
            return this;
        },
        getModelAsString: function() {
            var modelJson = this.model.toJSON();
            if (this.model.get("createusers")) {
                // we don't want to send it to the server - as it will cause the validation to fail
                // let's keep in locally in the model and only when the customer actually saves this
                // then we will lose the username potentially provided in UI.
                // let's respect customer input :)
                delete modelJson["reporterusername"];
            }
            return JSON.stringify(modelJson);
        },
        testResultsInlineContent: function(contents, trigger, show) {
            var data = this.testResult || {};
            contents.html(JIRA.Templates.Mail.testDialog({ "data": data}));
            show();
        },
        testCompleteHandler: function(xhr, textStatus, smartAjaxResult) {
            jQuery(".buttons .throbber:last").removeClass("loading");

            if (smartAjaxResult.successful) {
                this.testResult = smartAjaxResult.data;
            } else {
                this.testResult = { succeeded: false, errors: [ SmartAjax.buildSimpleErrorContent(smartAjaxResult) ] };
            }
            if (this.testResult.succeeded) {
                if (this.testResult.stats.messages == 0) {
                    jQuery("#mailHandlerForm .buttons .test-placeholder").html(JIRA.Templates.Mail.InlineMessage.info({
                        message: AJS.I18n.getText('jmp.editHandlerDetails.testDialog.nomessages')
                    }));
                } else {
                    jQuery("#mailHandlerForm .buttons .test-placeholder").html(JIRA.Templates.Mail.InlineMessage.success({
                        message: AJS.I18n.getText('jmp.editHandlerDetails.testDialog.success')
                    }));
                }

            } else {
                jQuery("#mailHandlerForm .buttons .test-placeholder").html(JIRA.Templates.Mail.InlineMessage.error({
                    message: AJS.I18n.getText('jmp.editHandlerDetails.testDialog.error')
                }));
            }
            jQuery("#mailHandlerForm .buttons .test-placeholder a").click({dialog: this}, function(event) {
                event.data.dialog.testResultsInlineDialog.show();
                event.preventDefault();
            });
            JIRA.trace("jira.mail.edit.handler.test.finished");
        },
        testHandler: function(e) {
            e.preventDefault();

            var jsonData = { "detailsJson": this.getModelAsString() };

            jQuery("#mailHandlerForm .buttons .test-placeholder").html("");
            jQuery(".buttons .throbber:last").addClass("loading");

            if (this.testResultsInlineDialog == undefined) {
                this.testResultsInlineDialog = new AJS.InlineDialog(
                    jQuery("#mailHandlerForm div.buttons .test-placeholder"), "test-results-popup", this.testResultsInlineContent, {
                        width: 550,
                        onHover: false,
                        onTop: true,
                        noBind: true,
                        cacheContent: false
                    });
            } else {
                this.testResultsInlineDialog.hide();
            }

            SmartAjax.makeRequest({
                url: contextPath + "/rest/jira-mail-plugin/1.0/message-handlers/test?atl_token=" + atl_token(),
                type: "POST",
                dataType: "json",
                data: jsonData,
                complete: this.testCompleteHandler
            });
        },
        saveHandler: function() {
            jQuery("#details").val(this.getModelAsString());
        },
        modelChanged: function() {
            jQuery(".icon-required",
                jQuery("div.field-group.forwardEmail")).toggle(this.model.get("bulk") == "forward");
            jQuery(".hints-section").toggle(this.model.get("createusers") != true
                && (this.model.get("reporterusername") == undefined
                || this.model.get("reporterusername") == ""));

            jQuery("#testButton").attr("disabled", "disabled");

            var jsonData = { "detailsJson": this.getModelAsString() };

            SmartAjax.makeRequest({
                url: contextPath + "/rest/jira-mail-plugin/1.0/message-handlers/validate?atl_token=" + atl_token(),
                type: "POST",
                dataType: "json",
                data: jsonData,
                complete: function(xhr, textStatus, smartAjaxResult) {
                    if (smartAjaxResult.successful) {
                        var disableTest = false;
                        jQuery(".field-group div.error").remove();
                        jQuery("div.aui-message.error").remove();

                        if (smartAjaxResult.data.globalErrors != undefined
                            && smartAjaxResult.data.globalErrors.length > 0) {
                            disableTest = true;

                            jQuery.each(smartAjaxResult.data.globalErrors, function(idx, value) {
                                JiraFlag.showErrorMsg(jQuery(".global-errors-location"), value, {
                                    shadowed: false,
                                    closeable: false
                                });
                            });
                        }
                        if (jQuery.isEmptyObject(smartAjaxResult.data.fieldErrors) == false) {
                            disableTest = true;

                            jQuery.each(smartAjaxResult.data.fieldErrors, function(key, value) {
                                jQuery(".field-group." + key + " span.element-wrapper")
                                    .append("<div class='error'>" + value + "</div>");
                            });
                        }
                        jQuery("#testButton").prop("disabled", disableTest);
                    } else {
                        jQuery("#testButton").prop("disabled", true);
                    }
                }
            });
        }
    });
});
