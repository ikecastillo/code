/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 6/22/2015.
 */
var PDMapper = {
    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var jiraField = AJS.$("#jiraField option:selected").text();

        if (this.validateMapping(jiraField)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/pagerduty-integration/1.0/pdFieldMapper/addMapping";
            var postData = JSON.stringify({jiraFieldName : jiraField});
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#pagerdutymappingstable tbody tr").remove();
                    AJS.log(" New jira field added is " + jiraField);
                    PDTableRowAppender.showTableRows(data);
                    JIRA.Messages.showSuccessMsg("JIRA Field Added!");
                }
            });
        }
    },

    removeMapping:function(jiraField) {
        var url = "/rest/pagerduty-integration/1.0/pdFieldMapper/deleteMapping";
        var postData = JSON.stringify({jiraFieldName : jiraField});
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#pagerdutymappingstable tbody tr").remove();
                AJS.log(" Mapping deleted is " + jiraField);
                PDTableRowAppender.showTableRows(data);
            }
        });
        JIRA.Messages.showSuccessMsg("JIRA Field Deleted!");
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#pagerdutymappingstable tbody tr").remove();
        var url = "/rest/pagerduty-integration/1.0/pdFieldMapper/getAllMappings";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                PDTableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(jiraField) {
        /**
         * Two validation checks:
         * 1) One JIRA field can be mapped to many xmatters fields but not the other way round. So its one-to-many
         *    relationship
         * 2) Before we add a new mapping, we check if its already present in the existing rows.
         * Technically, the first check automatically verifies the second one too :)
         */
        if (PDTableRowAppender.isJIRAFieldAlreadyMapped(jiraField)) {
            JIRA.Messages.showErrorMsg("The JIRA Field " + jiraField + " is already mapped, "+
                "please choose a different field" );
            return false;
        } else {
            return true;
        }
    }

};


