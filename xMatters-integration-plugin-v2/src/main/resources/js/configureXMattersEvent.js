/**
 * The EventManager object deals with setting up of xmatters event configs
 */
var EventManager = {
    addEventConfig: function () {

        //Get the selected fields from the add event dialog
        var formapiid = AJS.$("#formAPIId").val().trim();
        var xmattersform = AJS.$("#formTemplateName").val().trim();
        var xmattersformws = AJS.$("#formWebServiceURL").val().trim();
        var responsecodeavconf = AJS.$("#responseCodeAvailableUUID").val().trim();
        var responsecodenotavconf = AJS.$("#responseCodeNotAvailableUUID").val().trim();

        if (this.validateEventConfig(formapiid)) {
            //Make the AJAX Call here to update the AO,then call getAllXMattersEventConfigs()
            var url = "/rest/xmatter-integration/1.0/eventConfig/addXMattersEventConfig";
            var postData = JSON.stringify({
                "formAPIID" : formapiid,
                "xMattersTemplateName" : xmattersform,
                "xMattersFormWSURL" : xmattersformws,
                "xMattersFormResponseCodeAvailableUUID" : responsecodeavconf,
                "xMattersFormResponseCodeNotAvailableUUID" : responsecodenotavconf

            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#eventstable tbody tr").remove();
                    AJS.log(" New event config created with the form api id " + formapiid);
                    TableRowAppender.showAllXMattersEvents(data);
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("New xMatters Event configuration added!");
        }

    },
    removeEventConfig: function(formapiid, xmattersform, xmattersformws, responsecodeavconf, responsecodenotavconf ) {

        var url = "/rest/xmatter-integration/1.0/eventConfig/deleteXMattersEventConfig";
        var postData = JSON.stringify({
                                        "formAPIID" : formapiid,
                                        "xMattersTemplateName" : xmattersform,
                                        "xMattersFormWSURL" : xmattersformws,
                                        "xMattersFormResponseCodeAvailableUUID" : responsecodeavconf,
                                        "xMattersFormResponseCodeNotAvailableUUID" : responsecodenotavconf

        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#eventstable tbody tr").remove();
                AJS.log(" Deleted event with the formapiid " + formapiid);
                TableRowAppender.showAllXMattersEvents(data);
            }
        });

        JIRA.Messages.showSuccessMsg("XMatters Event Configuration Deleted!");

    },
    getAllEventConfigs: function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available events from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#eventstable tbody tr").remove();
        var url = "/rest/xmatter-integration/1.0/eventConfig/getAllXMattersEventConfigs";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                TableRowAppender.showAllXMattersEvents(data);
            }
        });

    },
    validateEventConfig: function(formapiid) {

        if (TableRowAppender.isXMattersEventConfigAlreadyPresent(formapiid)) {
            JIRA.Messages.showErrorMsg("Duplicate Event! The xMatters Event with Form API ID  " + formapiid + " is already configured");
            return false;
        } else {
            return true;
        }

    }

};