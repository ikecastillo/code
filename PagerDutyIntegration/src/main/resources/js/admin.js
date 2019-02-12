AJS.toInit(function() {
    //var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");

    //Get all mappings and add them to the concerned table in the concerned admin page.
    PDMapper.getAllMappings();
    
    function populatePDForm() {
        AJS.$.ajax({
            url: "/rest/pagerduty-integration/1.0/pdconfig",
            dataType: "json",
            success: function(config) {
                AJS.$("#pduid").attr("value", config.pduid);
                AJS.$("#pdpwd").attr("value", config.pdpwd);
                AJS.$("#pdinterval").attr("value", config.pdinterval);
                AJS.$("#pdjiraemailid").attr("value", config.pdjiraemailid);
                AJS.$("#pdhipchattoken").attr("value", config.pdhipchattoken);
                AJS.$("#pdrestapibaseurl").attr("value", config.pdrestapibaseurl);
                AJS.$("#pddturl").attr("value", config.pddturl);
                AJS.$("#pdserviceslimit").attr("value", config.pdserviceslimit);
                AJS.$("#pdapitoken").attr("value", config.pdapitoken);
            }
        });
    }
    function updatePDConfig() {
        AJS.$.ajax({
            url: "/rest/pagerduty-integration/1.0/pdconfig",
            type: "PUT",
            contentType: "application/json",
            data: '{"' +
            'pdjiraemailid": "' + AJS.$("#pdjiraemailid").attr("value") +
            '","pdinterval": "' + AJS.$("#pdinterval").attr("value") +
            '","pduid": "' + AJS.$("#pduid").attr("value") +
            '","pdpwd": "' +  AJS.$("#pdpwd").attr("value") +
            '","pdhipchattoken": "' + AJS.$("#pdhipchattoken").attr("value") +
            '","pdrestapibaseurl": "' +  AJS.$("#pdrestapibaseurl").attr("value") +
            '","pddturl": "' +  AJS.$("#pddturl").attr("value") +
            '","pdserviceslimit": "' +  AJS.$("#pdserviceslimit").attr("value") +
            '","pdapitoken": "' +  AJS.$("#pdapitoken").attr("value") +
            '"}',
            processData: false
        });
    }
    populatePDForm();

    AJS.$("#pdinitsetadmin").submit(function(e) {
        e.preventDefault();
        updatePDConfig();
        JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
    });

    /*************** RELATED TO ADDING JIRA FIELD *************************************************************/
    // Shows the dialog when the "Show dialog" button is clicked
    AJS.$("#jirafieldaddbutton").click(function() {
        AJS.log("Mapping dialog pops up");
        AJS.dialog2("#jirafieldadd-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages

        //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
        // Check this function jiraFieldNotEmpty().
        AJS.$("#jiraField > option:eq(0)").prop('selected',true);

        populateJiraCustomFields();
    });

    // Hides the dialog
    AJS.$("#jiraField-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#jirafieldadd-dialog").hide();
        AJS.log("Mapping dialog hides");
    });

    AJS.$("#jiraField-dialog-add-button").click(function() {
        if (jiraFieldNotEmpty()) {
            PDMapper.addMapping();
            AJS.dialog2("#jirafieldadd-dialog").hide();
        }
    });

    AJS.$(".deletePDMapping").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        //Confirm here that you are getting the right parameters to delete
        AJS.log("Field to delete for JIRA " + tableData[0]);

        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#delete-jirafield-dialog").show();
        AJS.$("#jirafielddelconf").text(tableData[0]);

    });

    AJS.$("#dialog-delete-jirafield-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#delete-jirafield-dialog").hide();
        AJS.log("Delete Mapping dialog hides");
    });

    AJS.$("#dialog-delete-jirafield-button").click(function() {
        AJS.log("Making an AJAX call to delete " + AJS.$("#jirafieldconf").text());
        PDMapper.removeMapping(AJS.$("#jirafielddelconf").text());
        AJS.dialog2("#delete-jirafield-dialog").hide();
    });
    
    
    // Pre populate all the custom fields from the given project and issue type
    function populateJiraCustomFields() {
        AJS.$.ajax({
            url: "/rest/pagerduty-integration/1.0/jirafields.json",
            dataType: "json",
            // data: {
            // projectKey : projectKey,
            // issueType: issueType
            // },
            success: function(data) {
                AJS.log("custom fields->"+data.csvFields); // fields are with comma separated
                var fieldsArray = data.csvFields.split(',');

                var  jiraSelectfield = AJS.$("#jiraField");

                //Remove everything but the --select-- option
                AJS.$('option', jiraSelectfield).not(':eq(0)').remove();
                for(var i=0;i<fieldsArray.length;i++){
                    var value = fieldsArray[i];
                    var jirafiledOption = AJS.$("<option/>").attr("value", value).text(value);
                    jiraSelectfield.append(jirafiledOption);
                }
            }
        });
    }

//validates if the mapping dialog fields are not empty
    function jiraFieldNotEmpty() {
        var errorMessageJIRA;
        AJS.$('.error').html('') //Clear all error messages

        if (AJS.$("#jiraField option:selected").text() === "Select") {
            errorMessageJIRA = AJS.I18n.getText("pagerduty.admin.mappingform.dialog.errorjiraField");
            AJS.$("#jiraField").focus().siblings(".error").html(errorMessageJIRA);
            return false;
        }

        return true;
    }
//validates if the event dialog fields are not empty
    function eventDialogFieldsNotEmpty() {
        var isFieldFilled = true;
        AJS.$('.error').html('');//Clear all error messages
        AJS.$(".mandatoryevtField").each(function() {
            if (!AJS.$(this).val()) {
                AJS.log("Found a blank field, cant save");
                AJS.$(this).focus().siblings(".error").html("This field cannot be left blank");
                isFieldFilled = false;
            }
        });
        return isFieldFilled;
    }

    //validate if the hipchat dialog fields are not empty
    function hipChatDialogFieldsNotEmpty() {
        var isFieldFilled = true;
        AJS.$('.error').html('');//Clear all error messages
        AJS.$(".mandatoryhcField").each(function() {
            if (!AJS.$(this).val()) {
                AJS.log("Found a blank field, cant save");
                AJS.$(this).focus().siblings(".error").html("This field cannot be left blank");
                isFieldFilled = false;
            }
        });
        return isFieldFilled;
    }

    function populateSolutionGroups(projectKey) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/xmatter-integration/1.0/getSolutionGroups",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                projectkey : projectKey
            },
            async:false,
            success: function(data) {
                var  solnlineSelectfield = AJS.$("#sollinemgmt");
                for(var i=0;i<data.length;i++){
                    var value = data[i].value;
                    var solnLineOption = AJS.$("<option/>").attr("value", value).text(value);
                    solnlineSelectfield.append(solnLineOption);
                }
            }
        });
    }

});
