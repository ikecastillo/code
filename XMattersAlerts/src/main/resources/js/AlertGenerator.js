/**
 * JS that makes respective AJAX calls and checks for alerts.
 *
 * Created by yagnesh.bhat on 7/8/2015.
 */

/**
 * The object that decides which incident needs to be alerted. Once it finds them, it will send those
 * incidents to the AlertDialog object as a single alert message string
 *
 * @type {{checkForAlert: Function}}
 */
var AlertGenerator = {
    checkForAlert : function() {
        //Make an AJAX call only if create issue JIRA dialog is not open - to avoid the refresh icon seen on that dialog
        AJS.log("Will check for alerts only if create issue dialog is closed to avoid seeing the refresh icon there");
        if (AJS.$("#create-issue-dialog").length == 0) {
            AJS.log("Checking for xmatters alerts");
            var url = AJS.params.baseURL+"/rest/api/2/search";
            AJS.$.ajax({
                url: url,
                type : 'GET',
                contentType: "application/json",
                data : {
                    jql: 'assignee=currentUser() AND project = "IT Incident Management" AND issuetype = "Incident" and ' +
                    'status not in (Resolved,"Resolved - Confirmed","Resolved - Pending Report Review") and ' +
                    'Severity in (Critical,High)'
                },
                success: function(response) {
                    AJS.log("Found open incidents to be resolved");
                    AJS.log("There are " + response.issues.length + " incidents that are not resolved");
                    var incidents = [];
                    for (var i=0 ; i < response.issues.length ; i++) {
                        AJS.log(response.issues[i].key);
                        incidents.push(response.issues[i].key);
                    }
                    if (incidents.length > 0) {
                        var url = AJS.params.baseURL + "/rest/incidentalerts/1.0/getIncidentsToAlert";
                        AJS.$.ajax({
                            url: url,
                            type : 'GET',
                            contentType: "application/json",
                            data: {
                                unresolvedIncidents: incidents.join(",")
                            },
                            success:function(response) {
                                var alertMessage = "";
                                AJS.log("Found unresolved incidents!");
                                for (var i=0 ; i < response.length ; i++) {
                                    AJS.log("INCIDENT ID " + response[i].jiraIssueKey);
                                    if (response[i].jiraIssueSummary) {
                                        AJS.log("Summary " + response[i].jiraIssueSummary);
                                        var issueKeyLink = "<a href=" + AJS.params.baseURL+"/browse/"+
                                            response[i].jiraIssueKey + ">" + response[i].jiraIssueKey + "</a>";
                                        alertMessage +=  issueKeyLink + " - " + response[i].jiraIssueSummary +
                                            "<br/>";
                                    }
                                }
                                if (alertMessage != "") {
                                    AlertDialog.showAlert(alertMessage);
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}

/**
 * The object thats actually responsible for showing the alert dialog
 * @type {{showAlert: Function}}
 */
var AlertDialog = {
    //isAlertShown : false, // Keeping it for now in case the client changes mind -
    // to make sure that the alert is seen one screen only one time without refresh

    showAlert: function (alertMessage) {
        AJS.log("Showing alert message " + alertMessage);

        AJS.log("Alerts dialog length = " + jQuery("#alertsdialog").length);
        AJS.log("Is the create issue JIRA dialog closed? : ");
        AJS.log(AJS.$("#create-issue-dialog").length == 0);

        //AJS.log("Is alert shown already on this screen without refresh? : " + this.isAlertShown);
        //if (!this.isAlertShown) { //Keeping it for now in case the client changes mind

        //If the dialog is not there and if create issue JIRA dialog is not open, show the alert, else update the alert
        if (jQuery("#alertsdialog").length == 0 && AJS.$("#create-issue-dialog").length == 0) {
            var dialogId = "alertsdialog";
            var dialogDiv = AJS.$(document.createElement('div')).attr({
                title: 'The following incidents need to be addressed soon',
                id: dialogId
            }).width(600).height(350).html(alertMessage);
            AJS.$('body').append(dialogDiv);

            var width = 600;
            var height = 350;

            var dialogParams = {
                height: height,
                width: width,
                resizable: false
            };

            dialogDiv.dialog(dialogParams);

            var minBtn = AJS.$('<button/>', {
                id: 'min' + dialogId,
                text: '-'
            }).button().css({
                position: 'absolute',
                top: '8px',
                right: '50px',
                'z-index': 0
            });

            var maxBtn = AJS.$('<button/>', {
                id: 'max' + dialogId,
                text: '+'
            }).button().css({
                    position: 'absolute',
                    top: '8px',
                    right: '25px',
                    'z-index': 1
            });

            var closeBtn = AJS.$('<button/>', {
                id: 'closexmalert',
                text: 'x'
            }).button().css ({
                position: 'absolute',
                top: '8px',
                right: '1px',
                'z-index': 2
            })

            dialogDiv.prev('.ui-dialog-titlebar').append(minBtn).append(maxBtn).append(closeBtn);
            minBtn.on('click', function (e) {
                AJS.$("#"+dialogId).parents('.ui-dialog').animate({
                    height: '40px',
                    top: AJS.$(window).height() - 50,
                    right: 0
                }, 350);
                AJS.$(this).css({
                    'z-index': 0
                });
                maxBtn.css({
                    'z-index': 1
                });
            });
            maxBtn.on('click', function (e) {

                AJS.$("#"+dialogId).parents('.ui-dialog').animate({
                    height: 350,
                    top: (AJS.$(window).height() - height) / 2,
                    right: (AJS.$(window).width() - width) / 2
                }, 350);

                AJS.$(this).css({
                    'z-index': 0
                });
                minBtn.css({
                    'z-index': 1
                });
            });

            closeBtn.on('click', function() {
                AJS.$(this).dialog('close');
                dialogDiv.remove();
            });

            //this.isAlertShown = true;
        } else {
            jQuery('#alertsdialog').html(alertMessage);
        }

    }
}
