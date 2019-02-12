/**
 * Created by yagnesh.bhat on 5/6/2016.
 */
/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 4/19/2016.
 */
var CSARAssigneeMapper = {

    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var solutionGroup = AJS.$("#csarsolutiongroup option:selected").text();
        var requestType = AJS.$("#csarrequesttype option:selected").text();
        var owner = AJS.$("#csarowners").val().trim();
        var implementor = AJS.$("#csarimplementors").val().trim();

        if (this.validateMapping(solutionGroup,requestType)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/servicedeskldapintegration/1.0/config/addConfig";
            var postData = JSON.stringify({
                "solutionGroup" : solutionGroup,
                "issueType" : requestType,
                "owner" : owner,
                "implementor" : implementor
            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#csarmappingstable tbody tr").remove();
                    AJS.log(" New mapping created for CSAR for " + solutionGroup + "and Request Type " + requestType);
                    CSAROwnerImplementorTableRowAppender.showTableRows(data);
                }
            });
            JIRA.Messages.showSuccessMsg("New auto assignee config added!");

            //Just set the outer search form to its default dropdown value, whenever you show a message
            AJS.$("#csarsolutiongroupoutside > option:eq(0)").prop('selected',true);
        }
    },

    removeMapping:function(solutionGroup, requestType, owner, implementor) {
        var url = "/rest/servicedeskldapintegration/1.0/config/deleteConfig";
        var postData = JSON.stringify({
            "solutionGroup" : solutionGroup,
            "issueType" : requestType,
            "owner" : owner,
            "implementor" : implementor
        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#csarmappingstable tbody tr").remove();
                AJS.log("Mapping removed for Service Desk " + solutionGroup + "and Request Type " + requestType);
                CSAROwnerImplementorTableRowAppender.showTableRows(data);
            }
        });

        JIRA.Messages.showSuccessMsg("Auto Assignee Config Deleted!");
        
        //Just set the outer search form to its default dropdown value, whenever you show a message
        AJS.$("#csarsolutiongroupoutside > option:eq(0)").prop('selected',true);
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#csarmappingstable tbody tr").remove();
        var url = "/rest/servicedeskldapintegration/1.0/config/getAllConfigs";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                CSAROwnerImplementorTableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(solutionGroup,requestType) {
        /**
         * Checks if the mapping row of product and subproduct already exists
         */
        if (CSAROwnerImplementorTableRowAppender.isSolGroupAndReqTypeMapped(solutionGroup,requestType)) {
            JIRA.Messages.showErrorMsg("There is already a configuration for Solution Group " + solutionGroup +
                " and request type " + requestType );
            return false;
        } else {
            return true;
        }
    }

};




