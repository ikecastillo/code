/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 6/22/2015.
 */
var JIRASDFMMapper = {
    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var jiraField = AJS.$("#jiracustField option:selected").text();
        var jiraSDField = AJS.$("#sdcustField option:selected").text();
        var jiraSDFieldId = AJS.$("#sdcustField option:selected").attr("id");

        if (this.validateMapping(jiraField,jiraSDField,jiraSDFieldId)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/jirasdfm/1.0/fieldMapper/addMapping";
            var postData = JSON.stringify({jiraFieldName : jiraField, jiraSdFieldName : jiraSDField, jiraSdFieldId : jiraSDFieldId});
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#jirasdfmmappingstable tbody tr").remove();
                    AJS.log(" Mapping deleted is " + jiraField + "->" + jiraSDField);
                    JIRASDFMTableRowAppender.showTableRows(data);
                    JIRA.Messages.showSuccessMsg("Config Added!");
                }
            });
            
        }
    },

    removeMapping:function(jiraField, jiraSDField, jiraSDFieldId) {
        var url = "/rest/jirasdfm/1.0/fieldMapper/deleteMapping";
        var postData = JSON.stringify({jiraFieldName : jiraField, jiraSdFieldName : jiraSDField, jiraSdFieldId : jiraSDFieldId});
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#jirasdfmmappingstable tbody tr").remove();
                AJS.log(" Mapping deleted is " + jiraField + "->" + jiraSDField);
                JIRASDFMTableRowAppender.showTableRows(data);
                JIRA.Messages.showSuccessMsg("Config Deleted!");
            }
        });

        
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#jirasdfmmappingstable tbody tr").remove();
        var url = "/rest/jirasdfm/1.0/fieldMapper/getAllMappings";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                JIRASDFMTableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(jiraField,jiraSDField,jiraSDFieldId) {
        
       /*
         We check if the jira field -> jira sd field -> jira sd field id combination is already there.
         If yes, then we dont allow to add another row as duplicate
        */
      
        if (JIRASDFMTableRowAppender.isRowAlreadyPresent(jiraField,jiraSDField,jiraSDFieldId)) {
            JIRA.Messages.showErrorMsg("The config you want to add is already present, please check your config again" );
            return false;
        } else {
            return true;
        }
        return true;
    }

};


