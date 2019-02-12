/**
 * Created by yagnesh.bhat on 5/13/2016.
 */
AJS.toInit(function () {

    JIRASDFMMapper.getAllMappings();

    function updateJIRASDFMConfig() {
        AJS.$.ajax({
            url: "/rest/jirasdfm/1.0/jirasdfmadminconfig",
            type: "PUT",
            contentType: "application/json",
            data: '{"serviceDeskUserId": "' + AJS.$("#jirasdfmuid").attr("value") +
            '", "serviceDeskPwd": "' + AJS.$("#jirasdfmpwd").attr("value") +
            '", "serviceDeskURL": "' + AJS.$("#jirasdfmSDURL").attr("value") +
            '"}',
            processData: false
        });
    }

    AJS.$("#jirasdfmadmin2").submit(function(e) {
        e.preventDefault();
        updateJIRASDFMConfig();
        JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
    });

    // Shows the dialog when the "Show dialog" button is clicked
    AJS.$("#jirasdfm-mapping-button").click(function() {
        AJS.log("Mapping dialog pops up");
        AJS.dialog2("#jirasdfm-mapping-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages

        //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
        // Check this function mappingDialogFieldsNotEmpty().
        AJS.$("#jiracustField > option:eq(0)").prop('selected',true);
        AJS.$("#sdcustField > option:eq(0)").prop('selected',true);

        var projectKey = "CHG";
        var issueType = "Change";
        AJS.log("Project Key used : " + projectKey);
        AJS.log("Issue Type used : " + issueType);
        populateJiraCustomFields(projectKey,issueType);
        populateServiceDeskCustomFields();
    });

    // Hides the dialog
    AJS.$("#jirasdfmmapping-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#jirasdfm-mapping-dialog").hide();
        AJS.log("Mapping dialog hides");
    });

    AJS.$("#jirasdfmmapping-dialog-add-button").click(function() {
        if (jiraandSDMappingDialogFieldsNotEmpty()) {
            JIRASDFMMapper.addMapping();
            AJS.dialog2("#jirasdfm-mapping-dialog").hide();
        }
    });

    AJS.$(".jirasdfmdeleteMapping").live('click',function() {
        var rowToDelete = AJS.$(this).closest('tr');
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        AJS.log("Field to delete for JIRA " + tableData[0]);
        AJS.log("Field to delete for Service Desk " + tableData[1]);

        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#jirasdfm-delete-mapping-dialog").show();
        AJS.$("#jirafieldconf").text(tableData[0]);
        AJS.$("#jirasdfmfieldconf").text(tableData[1]);
        AJS.$("#jirasdfmfieldidconf").text(tableData[2]);

    });

    AJS.$("#jirasdfmmapping-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#jirasdfm-delete-mapping-dialog").hide();
        AJS.log("Delete Mapping dialog hides");
    });

    AJS.$("#jirasdfmmapping-dialog-delete-button").click(function() {
        AJS.log("Making an AJAX call to delete " + AJS.$("#jirafieldconf").text() + " -> " + AJS.$("#jirasdfmfieldconf").text() );
        JIRASDFMMapper.removeMapping(AJS.$("#jirafieldconf").text(), AJS.$("#jirasdfmfieldconf").text(),AJS.$("#jirasdfmfieldidconf").text());
        AJS.dialog2("#jirasdfm-delete-mapping-dialog").hide();
    });

});

// Pre populate all the custom fields from the given project and issue type
function populateJiraCustomFields(projectKey,issueType) {
    AJS.$.ajax({
        url: "/rest/jirasdfm/1.0/jirafields/"+projectKey+"/"+issueType+".json",
        dataType: "json",
        success: function(data) {
            AJS.log("custom fields->"+data.csvFields); // fields are with comma separated
            var fieldsArray = data.csvFields.split(',');

            var  jiraSelectfield = AJS.$("#jiracustField");

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

function populateServiceDeskCustomFields() {
    AJS.log("Getting initial Set up fields to populate for the second dropdown");
    AJS.$.ajax({
        url: "/rest/jirasdfm/1.0/getAllServiceDeskFields",
        dataType: "json",
        success: function(data) {
            var  jiraSDSelectfield = AJS.$("#sdcustField");

            //Remove everything but the --select-- option
            AJS.$('option', jiraSDSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var value = data[i].fieldName;
                var custFieldId = data[i].fieldId;
                var jiraSDFieldOption = AJS.$("<option/>").attr({"value" :value,"id" : custFieldId}).text(value);
                jiraSDSelectfield.append(jiraSDFieldOption);
            }
        }
    });

}

function jiraandSDMappingDialogFieldsNotEmpty() {
    var jiraField = AJS.$("#jiracustField option:selected").text();
    var jiraSdfield = AJS.$("#sdcustField option:selected").text();
    var validationPassed = true;
    
    if (jiraField == "Select") {
        AJS.$("#jiracustFielderror").html("Please select a JIRA ITSM field");
        validationPassed = false;
    }

    if (jiraSdfield == "Select") {
        AJS.$("#sdcustFielderror").html("Please select a JIRA Service Desk field");
        validationPassed = false;
    }
    
    return validationPassed;

}
