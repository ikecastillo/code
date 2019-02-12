/**
 * The PDServiceManager object deals with setting up of PagerDuty Services
 */
var PDServiceManager = {
    addServiceConfig: function () {

        //Get the selected fields from the add event dialog
        var serviceKey = AJS.$("#pdService option:selected").attr("service_key");
        var serviceName = AJS.$("#pdService option:selected").val();
        var clientsImpacted = AJS.$("#pdClientsImpacted option:selected").val();
        var impacted = AJS.$("#pdImpacted option:selected").val();
        var severity =  AJS.$("#pdSeverity option:selected").val();
        var ddcProduct = AJS.$("#sdDDCProd option:selected").val();
        var ddcSubProduct = AJS.$("#sdDDCSubProd0 option:selected").val();
        var ddcSubSubProduct = AJS.$("#sdDDCSubProd1 option:selected").val();
        
        var canAddService = this.validateServiceConfig(serviceKey, serviceName,clientsImpacted, impacted, 
            severity, ddcProduct, ddcSubProduct, ddcSubSubProduct);
        
        if (canAddService) {
            //Make the AJAX Call here to update the AO,then call getAllXMattersEventConfigs()
            var url = "/rest/pagerduty-integration/1.0/pdServiceConfig/addPDServiceConfig";
            var postData = JSON.stringify({
                serviceKey : serviceKey,
                serviceName : serviceName,
                clientsImpacted : clientsImpacted,
                impacted : impacted,
                severities : severity,
                ddcProduct : ddcProduct,
                ddcSubProduct0 : ddcSubProduct,
                ddcSubProduct1 : ddcSubSubProduct
            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#pdservicestable tbody tr").remove();
                    AJS.log(" New service config created with the service name" + serviceName);
                    PDTableRowAppender.showAllRowsWithEditAndDelete(data);
                    JIRA.Messages.showSuccessMsg("New PagerDuty Service configuration added!");
                    //Just set the outer search form to its default dropdown value, whenever you show a message
                    AJS.$("#pdsearchServiceName > option:eq(0)").prop('selected',true);
                    AJS.$("#pdSearchDDCProduct > option:eq(0)").prop('selected',true);
                }
            });

        }

    },
    removeServiceConfig: function(serviceKey, serviceName,clientsImpacted, impacted,
                                  severities, ddcProduct, ddcSubProduct, ddcSubSubProduct) {

        var url = "/rest/pagerduty-integration/1.0/pdServiceConfig/deletePDServiceConfig";
        var postData = JSON.stringify({
            serviceKey : serviceKey,
            serviceName : serviceName,
            clientsImpacted : clientsImpacted,
            impacted : impacted,
            severities : severities,
            ddcProduct : ddcProduct,
            ddcSubProduct0 : ddcSubProduct,
            ddcSubProduct1 : ddcSubSubProduct

        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#pdservicestable tbody tr").remove();
                AJS.log(" Deleted a service with the name " + serviceName);
                PDTableRowAppender.showAllRowsWithEditAndDelete(data);
                JIRA.Messages.showSuccessMsg("PagerDuty Service configuration deleted!");
                //Just set the outer search form to its default dropdown value, whenever you show a message
                AJS.$("#pdsearchServiceName > option:eq(0)").prop('selected',true);
                AJS.$("#pdSearchDDCProduct > option:eq(0)").prop('selected',true);
            }
        });

    },
    updateServiceConfig:function (pagerDutyServiceEditObject) {
        //Get the selected fields from the add event dialog
        var serviceKey = AJS.$("#pdServiceEdit option:selected").attr("service_key");
        var serviceName = AJS.$("#pdServiceEdit option:selected").val();
        var clientsImpacted = AJS.$("#pdClientsImpactedEdit option:selected").val();
        var impacted = AJS.$("#pdImpactedEdit option:selected").val();
        /*var selSeverityMulti = AJS.$.map(AJS.$("#pdSeverityEdit option:selected"), function (el) {
            return AJS.$(el).text();
        });*/
        var severity = AJS.$("#pdSeverityEdit option:selected").val();
        var ddcProduct = AJS.$("#sdDDCProdEdit option:selected").val();
        var ddcSubProduct = AJS.$("#sdDDCSubProd0Edit option:selected").val();
        var ddcSubSubProduct = AJS.$("#sdDDCSubProd1Edit option:selected").val();

        var canEditService = this.validateServiceConfig(serviceKey, serviceName,clientsImpacted, impacted,
            severity, ddcProduct, ddcSubProduct, ddcSubSubProduct);

        if (canEditService) {
            //Make the AJAX Call here to update the AO,then call getAllXMattersEventConfigs()
            var url = "/rest/pagerduty-integration/1.0/pdServiceConfig/updatePDServiceConfig";
            var postData = JSON.stringify([pagerDutyServiceEditObject,{
                serviceKey : serviceKey,
                serviceName : serviceName,
                clientsImpacted : clientsImpacted,
                impacted : impacted,
                severities : severity,
                ddcProduct : ddcProduct,
                ddcSubProduct0 : ddcSubProduct,
                ddcSubProduct1 : ddcSubSubProduct
            }]);
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#pdservicestable tbody tr").remove();
                    AJS.log(" service config edited with the service name" + serviceName);
                    PDTableRowAppender.showAllRowsWithEditAndDelete(data);
                    JIRA.Messages.showSuccessMsg("Edit Service configuration successful!");
                    //Just set the outer search form to its default dropdown value, whenever you show a message
                    AJS.$("#pdsearchServiceName > option:eq(0)").prop('selected',true);
                    AJS.$("#pdSearchDDCProduct > option:eq(0)").prop('selected',true);
                }
            });
        }
    },
    getAllServiceConfigs: function() {
        /*
         First clear all the rows in the table.
         Then, make an AJAX call to get all the available events from AO in JSON format.
         Then parse it and display it in the form table rows to the already existing table in the vm
         */
        AJS.$("#pdservicestable tbody tr").remove();
        var url = "/rest/pagerduty-integration/1.0/pdServiceConfig/getAllPDServiceConfigs";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                PDTableRowAppender.showAllRowsWithEditAndDelete(data);
            }
        });

    },
    validateServiceConfig: function(serviceKey, serviceName,clientsImpacted, impacted,
                                    severities, ddcProduct, ddcSubProduct, ddcSubSubProduct) {

        if (PDTableRowAppender.isPagerDutyServiceConfigAlreadyPresent(serviceKey, serviceName,clientsImpacted, impacted,
                severities, ddcProduct, ddcSubProduct, ddcSubSubProduct)) {
            JIRA.Messages.showErrorMsg("Duplicate Service configuration! Please check your settings again");
            return false;
        } else {
            return true;
        }
    }

};

/**
 * This object is used for storing the existing service setting when we are trying to edit a PagerDuty Service Config.
 * The moment the updation is done OR when the cancel Edit is done it is reset back to the empty object like below
 */
var pagerDutyServiceEditObject = {};