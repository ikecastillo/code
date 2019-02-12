/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 6/22/2015.
 */
var Mapper = {
    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var jiraField = AJS.$("#jiraField option:selected").text();
        var xMattersField = AJS.$("#xMattersField").val().trim();

        if (this.validateMapping(xMattersField)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/xmatter-integration/1.0/fieldMapper/addMapping";
            var postData = JSON.stringify({"jiraFieldName" : jiraField, "xMattersFieldName" : xMattersField});
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#mappingstable tbody tr").remove();
                    AJS.log(" New mapping created is " + jiraField + "->" + xMattersField);
                    TableRowAppender.showTableRows(data);
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("XMatters Mapping Added!");
        }
    },

    removeMapping:function(jiraField, xMattersField) {
        var url = "/rest/xmatter-integration/1.0/fieldMapper/deleteMapping";
        var postData = JSON.stringify({"jiraFieldName" : jiraField, "xMattersFieldName" : xMattersField});
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#mappingstable tbody tr").remove();
                AJS.log(" Mapping deleted is " + jiraField + "->" + xMattersField);
                TableRowAppender.showTableRows(data);
            }
        });

        JIRA.Messages.showSuccessMsg("XMatters Mapping Deleted!");
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#mappingstable tbody tr").remove();
        var url = "/rest/xmatter-integration/1.0/fieldMapper/getAllMappings";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                TableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(xMattersField) {
        /**
         * Two validation checks:
         * 1) One JIRA field can be mapped to many xmatters fields but not the other way round. So its one-to-many
         *    relationship
         * 2) Before we add a new mapping, we check if its already present in the existing rows.
         * Technically, the first check automatically verifies the second one too :)
         */
        if (TableRowAppender.isXMattersFieldAlreadyMapped(xMattersField)) {
            JIRA.Messages.showErrorMsg("The xMatters Field " + xMattersField + " is already mapped, "+
                "please choose a different field" );
            return false;
        } else {
            return true;
        }
    }

};


