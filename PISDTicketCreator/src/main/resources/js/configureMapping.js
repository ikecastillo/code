/**
 * The Mapper Object contains Javascript functions to add/remove mappings to the table.
 * Created by yagnesh.bhat on 4/19/2016.
 */
var PISDMapper = {
    
    addMapping: function() {

        //Get the selected fields from the mapping dialog
        var product = AJS.$("#productITIM option:selected").text();
        var subProduct = AJS.$("#subproductITIM option:selected").text();
        var serviceDeskID = AJS.$("#serviceDeskId").val().trim();
        var requestTypeID = AJS.$("#requestTypeId").val().trim();
        var customfieldWithIds = AJS.$("#custfieldWithId").val().trim();

        if (this.validateMapping(product,subProduct)) {
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            var url = "/rest/pisd-ticket-creator-api/1.0/config/addConfig";
            var postData = JSON.stringify({
                "serviceDeskID": serviceDeskID,
                "serviceDeskName": product,
                "requestTypeID": requestTypeID,
                "requestTypeName": subProduct,
                "custFieldsWithIDs": customfieldWithIds
            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#pisdtktmappingstable tbody tr").remove();
                    AJS.log(" New mapping created for Service Desk " + product + "and Request Type " + subProduct);
                    PISDTableRowAppender.showTableRows(data);
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("New Service Desk Config Added!");
        }
    },

    removeMapping:function(serviceDeskID, serviceDeskName, requestTypeID, requestTypeName, customFieldsWithIDs) {
        var url = "/rest/pisd-ticket-creator-api/1.0/config/deleteConfig";
        var postData = JSON.stringify({
            "serviceDeskID": serviceDeskID,
            "serviceDeskName": serviceDeskName,
            "requestTypeID": requestTypeID,
            "requestTypeName": requestTypeName,
            "custFieldsWithIDs": customFieldsWithIDs});
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#pisdtktmappingstable tbody tr").remove();
                AJS.log("Mapping removed for Service Desk " + serviceDeskName + "and Request Type " + requestTypeName);
                PISDTableRowAppender.showTableRows(data);
            }
        });

        JIRA.Messages.showSuccessMsg("Service Desk Config Deleted!");
    },

    getAllMappings:function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available mappings from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#pisdtktmappingstable tbody tr").remove();
        var url = "/rest/pisd-ticket-creator-api/1.0/config/getAllConfigs";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                PISDTableRowAppender.showTableRows(data);
            }
        });
    },

    validateMapping:function(product,subProduct) {
        /**
         * Checks if the mapping row of product and subproduct already exists
         */
        if (PISDTableRowAppender.isTheProdAndSubProdAlreadyMapped(product,subProduct)) {
            JIRA.Messages.showErrorMsg("There is already is configuration for Service Desk " + product + 
                " and request type " + subProduct );
            return false;
        } else {
            return true;
        }
    }

};



