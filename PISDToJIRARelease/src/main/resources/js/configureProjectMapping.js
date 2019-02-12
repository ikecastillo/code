/**
 * Created by yagnesh.bhat on 7/28/2016.
 */
/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 4/19/2016.
 */
var PISDtoRelMapper = {

    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var sdProjectName = AJS.$("#serviceDeskProject option:selected").text();
        var sdProjectKey = AJS.$("#serviceDeskProject option:selected").val();
        var jiraProjectName = AJS.$("#otherJIRAInstProject option:selected").text();
        var jiraProjectKey = AJS.$("#otherJIRAInstProject option:selected").val();
        var jiraProjectIssueType = AJS.$("#otherJIRAInstProjectIssueType option:selected").text();

        if (jiraProjectName === "Select" && jiraProjectKey === "Select" && jiraProjectIssueType === "Select" ) {
            jiraProjectName = "";
            jiraProjectKey = "";
            jiraProjectIssueType = "";
        }

        if (this.validateMapping(sdProjectName)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/createjiraticket/1.0/projmapconfig/addProjectMapping";
            var postData = JSON.stringify({
                "sdProjName" : sdProjectName,
                "sdProjKey" : sdProjectKey,
                "jiraProjName" : jiraProjectName,
                "jiraProjKey" : jiraProjectKey,
                "jiraProjIssueType" : jiraProjectIssueType
            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#pisdtoreltktmappingstable tbody tr").remove();
                    AJS.log(" New mapping created for Service Desk Project" + sdProjectKey + "and JIRA Project " +
                        jiraProjectKey);
                    PISDToRelTableRowAppender.showTableRows(data);
                }
            });
            JIRA.Messages.showSuccessMsg("New Project Mapping Added for Service Desk Project " + sdProjectName);
        }
    },

    removeMapping:function(sdProjName, sdProjKey, jiraProjName, jiraProjKey, jiraProjIssueType) {
        var url = "/rest/createjiraticket/1.0/projmapconfig/deleteProjectMapping";
        var postData = JSON.stringify(
        {
            "sdProjName": sdProjName,
            "sdProjKey": sdProjKey,
            "jiraProjName": jiraProjName,
            "jiraProjKey": jiraProjKey,
            "jiraProjIssueType" : jiraProjIssueType
        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#pisdtoreltktmappingstable tbody tr").remove();
                AJS.log("Project Mapping removed for Service Desk Project " + sdProjName);
                PISDToRelTableRowAppender.showTableRows(data);
            }
        });

        JIRA.Messages.showSuccessMsg("Project Mapping removed for Service Desk Project " + sdProjName);
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#pisdtoreltktmappingstable tbody tr").remove();
        var url = "/rest/createjiraticket/1.0/projmapconfig/getAllProjectMappings";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                PISDToRelTableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(sdProjectName) {
        /**
         * Checks if the mapping row of Sd Project already exists
         */
        if (PISDToRelTableRowAppender.isTheSdProjectAlreadyMapped(sdProjectName)) {
            JIRA.Messages.showErrorMsg("The Service Project " + sdProjectName + " is already configured, " +
                "please choose another service desk project " );
            return false;
        } else {
            return true;
        }
    }

};



