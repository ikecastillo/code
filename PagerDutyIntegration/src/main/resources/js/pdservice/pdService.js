/**
 * Created by Yagnesh.Bhat on 5/23/2016.
 */
AJS.toInit(function () {
    AJS.log("All Services Loaded!");
    
    PDServiceManager.getAllServiceConfigs();
    populateListOfPDServices("pdsearchServiceName");
    populateProductsForSolutionGroupDDC("ITIM", "DDC","pdSearchDDCProduct");
    
    AJS.$("#pdsearchServiceName").change(function() {
        var serviceNameToSearch = AJS.$("#pdsearchServiceName").val();
        var ddcProductToSearch = AJS.$("#pdSearchDDCProduct").val();
        if (serviceNameToSearch === "All" && ddcProductToSearch === "All") {
            PDServiceManager.getAllServiceConfigs();
        } else {
            filterResults(serviceNameToSearch,ddcProductToSearch);
        }

    });

    AJS.$("#pdSearchDDCProduct").change(function(){
        var serviceNameToSearch = AJS.$("#pdsearchServiceName").val();
        var ddcProductToSearch = AJS.$("#pdSearchDDCProduct").val();
        if (serviceNameToSearch === "All" && ddcProductToSearch === "All") {
            PDServiceManager.getAllServiceConfigs();
        } else {
            filterResults(serviceNameToSearch,ddcProductToSearch);
        }
    });
    
    AJS.$("#pd-service-add-button").click(function() {
        AJS.log("PD Service dialog pops up");
        AJS.dialog2("#pd-service-add-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages

        AJS.$("#pdService > option:eq(0)").prop('selected',true);
        AJS.$("#pdClientsImpacted > option:eq(0)").prop('selected',true);
        AJS.$("#pdImpacted > option:eq(0)").prop('selected',true);
        AJS.$("#sdDDCProd > option:eq(0)").prop('selected',true);
        AJS.$("#sdDDCSubProd0 > option:eq(0)").prop('selected',true);
        AJS.$("#sdDDCSubProd1 > option:eq(0)").prop('selected',true);
        AJS.$("#pdSeverity > option:eq(0)").prop("selected", true);
        
        populateListOfPDServices("pdService");
        populateImpactedDropdown("ITIM","pdImpacted");
        populateProductsForSolutionGroupDDC("ITIM", "DDC","sdDDCProd");

        AJS.$("#sdDDCProd").change(function() {
            var selectedProduct = AJS.$("#sdDDCProd").val();
            if (selectedProduct != "Select") {
                blankOutSubProductsAndSubSubproducts("sdDDCSubProd0","sdDDCSubProd1");
                populateSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC",selectedProduct,"sdDDCSubProd0");
            } else {
                //blank out sub products and sub sub products
                blankOutSubProductsAndSubSubproducts("sdDDCSubProd0","sdDDCSubProd1");
            }
        });

        AJS.$("#sdDDCSubProd0").change(function() {
            var selectedProduct = AJS.$("#sdDDCProd").val();
            var selectedSubProduct = AJS.$("#sdDDCSubProd0").val();
            if (selectedSubProduct != "Select") {
                populateSubSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC", selectedProduct,selectedSubProduct,"sdDDCSubProd1");
            } else {
                //blank out sub products and sub sub products
                blankOutSubSubProducts("sdDDCSubProd1");
            }
        });
        
    });

    function blankOutSubProductsAndSubSubproducts(subProductSelectId, subSubProductSelectId) {
        var  subproductSelectfield = AJS.$("#"+subProductSelectId);
        AJS.$('option', subproductSelectfield).not(':eq(0)').remove();
        blankOutSubSubProducts(subSubProductSelectId);
    }

    function blankOutSubSubProducts(subSubProductSelectId) {
        var subsubProductSelectField = AJS.$("#"+subSubProductSelectId);
        AJS.$('option', subsubProductSelectField).not(':eq(0)').remove();
    }


    AJS.$("#pd-service-add-dialog-add-button").click(function() {
        if (validateServiceFormFields("pdService", "pdClientsImpacted", "pdImpacted", "pdSeverity",
                "sdDDCProd")) {
            PDServiceManager.addServiceConfig();
            AJS.dialog2("#pd-service-add-dialog").hide();
        }
    });
    // Hides the dialog
    AJS.$("#pd-service-add-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pd-service-add-dialog").hide();
        AJS.log("Add service dialog hides");
    });
    
    AJS.$("#pd-service-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pd-service-delete-dialog").hide();
        AJS.log("Delete Service Confirmation dialog hides");
    });


    AJS.$(".pdDeleteService").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#pd-service-delete-dialog").show();
        AJS.$("#pdServiceKeyconf").text(tableData[0]);
        AJS.$("#pdServiceNameconf").text(tableData[1]);
        AJS.$("#pdClientsImpactedconf").text(tableData[2]);
        AJS.$("#pdImpactedconf").text(tableData[3]);
        AJS.$("#pdSeverityconf").text(tableData[4]);
        AJS.$("#pdDDCProductconf").text(tableData[5]);
        AJS.$("#pdDDCSubProductconf").text(tableData[6]);
        AJS.$("#pdDDCSubSubProductconf").text(tableData[7]);
    });
    
    AJS.$("#pd-service-dialog-delete-button").click(function() {
        PDServiceManager.removeServiceConfig(AJS.$("#pdServiceKeyconf").text(),
            AJS.$("#pdServiceNameconf").text(),AJS.$("#pdClientsImpactedconf").text(),
            AJS.$("#pdImpactedconf").text(), AJS.$("#pdSeverityconf").text(),
            AJS.$("#pdDDCProductconf").text(), AJS.$("#pdDDCSubProductconf").text(),
            AJS.$("#pdDDCSubSubProductconf").text());

        AJS.dialog2("#pd-service-delete-dialog").hide();
    });

    /*****************************RELATED TO THE EDIT DIALOG**********************************************************/
    AJS.$(".pdEditService").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        var serviceKeyToEdit = tableData[0];
        var serviceNametoEdit = tableData[1];
        var clientsImpactedEdit = tableData[2];
        var impactedEdit = tableData[3];
        var severityEdit = tableData[4];
        var ddcProductEdit = tableData[5];
        var ddcSubProductEdit = tableData[6];
        var ddcSubSubProductEdit = tableData[7];

        pagerDutyServiceEditObject = {
            serviceKey : serviceKeyToEdit,
            serviceName : serviceNametoEdit,
            clientsImpacted : clientsImpactedEdit,
            impacted : impactedEdit,
            severities : severityEdit,
            ddcProduct : ddcProductEdit,
            ddcSubProduct0 : ddcSubProductEdit,
            ddcSubProduct1 : ddcSubSubProductEdit
        };

        AJS.log("Edit service dialog pops up");
        AJS.dialog2("#pd-service-edit-dialog").show();

        populateListOfPDServices("pdServiceEdit");
        AJS.$("#pdServiceEdit").val(serviceNametoEdit);

        AJS.$("#pdClientsImpactedEdit").val(clientsImpactedEdit);

        populateImpactedDropdown("ITIM","pdImpactedEdit");
        if (impactedEdit) {
            AJS.$("#pdImpactedEdit").val(impactedEdit);
        }
        
        populateProductsForSolutionGroupDDC("ITIM", "DDC","sdDDCProdEdit");
        if (ddcProductEdit) {
            AJS.$("#sdDDCProdEdit").val(ddcProductEdit);
        }

        populateSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC",ddcProductEdit,"sdDDCSubProd0Edit");
        if (ddcSubProductEdit) {
            AJS.$("#sdDDCSubProd0Edit").val(ddcSubProductEdit);
        }

        populateSubSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC", ddcProductEdit,ddcSubProductEdit,"sdDDCSubProd1Edit");
        if (ddcSubSubProductEdit) {
            AJS.$("#sdDDCSubProd1Edit").val(ddcSubProductEdit);
        }
        

        //AJS.$("#pdSeverityEdit option:selected").prop("selected", false);
        AJS.$("#pdSeverityEdit").val(severityEdit);
        
        
        AJS.$("#sdDDCProdEdit").change(function() {
            var selectedProduct = AJS.$("#sdDDCProdEdit").val();
            if (selectedProduct != "Select") {
                blankOutSubProductsAndSubSubproducts("sdDDCSubProd0Edit","sdDDCSubProd1Edit");
                populateSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC",selectedProduct,"sdDDCSubProd0Edit");
            } else {
                //blank out sub products and sub sub products
                blankOutSubProductsAndSubSubproducts("sdDDCSubProd0Edit","sdDDCSubProd1Edit");
            }
        });

        AJS.$("#sdDDCSubProd0Edit").change(function() {
            var selectedProduct = AJS.$("#sdDDCProdEdit").val();
            var selectedSubProduct = AJS.$("#sdDDCSubProd0Edit").val();
            if (selectedSubProduct != "Select") {
                populateSubSubProductsForSolutionGroupDDCAndProduct("ITIM", "DDC", selectedProduct,selectedSubProduct,"sdDDCSubProd1Edit");
            } else {
                //blank out sub products and sub sub products
                blankOutSubSubProducts("sdDDCSubProd1Edit");
            }
        });

        AJS.$(".error").html(''); //Clear all error messages
        
    });
    
    AJS.$("#pd-service-add-dialog-cancel-link-Edit").click(function(e) {
        e.preventDefault();
        pagerDutyServiceEditObject = {}; //RESET it back to empty object
        AJS.dialog2("#pd-service-edit-dialog").hide();
        AJS.log("Edit service dialog hides");
    });

    
    AJS.$("#pd-service-add-dialog-add-button-Edit").click(function() {
        if (validateServiceFormFields("pdServiceEdit", "pdClientsImpactedEdit", "pdImpactedEdit", "pdSeverityEdit",
                "sdDDCProdEdit")) {
            PDServiceManager.updateServiceConfig(pagerDutyServiceEditObject);
            pagerDutyServiceEditObject = {}; //RESET it back to empty object
            AJS.dialog2("#pd-service-edit-dialog").hide();
        }
    });
    
    
    
    /*****************************************************************************************************************/
});

function populateListOfPDServices(servicesSelectFieldId) {
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/pagerduty-integration/1.0/getAllPDServices",
        type: 'GET',
        contentType: "application/json",
        async:false,
        success: function(data) {
            var  servicesSelectfield = AJS.$("#"+servicesSelectFieldId);

            //Remove everything but the --select-- option
            AJS.$('option', servicesSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var serviceName = data[i].serviceName;
                var serviceKey = data[i].serviceKey;
                var serviceOption = AJS.$("<option/>").attr({"value" : serviceName , "service_key" : serviceKey}).text(serviceName);
                servicesSelectfield.append(serviceOption);
            }
        }
    });
}

function populateImpactedDropdown(projectKey, impactedSelectId) {
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/pagerduty-integration/1.0/getImpacted/getImpactedValues",
        type: 'GET',
        contentType: "application/json" ,
        data: {
            projectkey : projectKey
        },
        async:false,
        success: function(data) {
            var  impactedSelectfield = AJS.$("#"+impactedSelectId);

            //Remove everything but the --select-- option
            AJS.$('option', impactedSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var value = data[i].value;
                var impactedOption = AJS.$("<option/>").attr("value", value).text(value);
                impactedSelectfield.append(impactedOption);
            }
        }
    });
}

function populateProductsForSolutionGroupDDC(projectkey, selectedSG, productSelectfieldId) {
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/pagerduty-integration/1.0/getSolutionGroups/getProductValues",
        type: 'GET',
        contentType: "application/json" ,
        data: {
            solutionGroup: selectedSG,
            projectkey : projectkey
        },
        async:false,
        success: function(data) {
            var  productSelectfield = AJS.$("#"+productSelectfieldId);

            //Remove everything but the --select-- option
            AJS.$('option', productSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var value = data[i].value;
                var product = AJS.$("<option/>").attr("value", value).text(value);
                productSelectfield.append(product);
            }
        }
    });
}

function populateSubProductsForSolutionGroupDDCAndProduct(projectkey, selectedSG, selectedProd, subproductSelectfieldId) {
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/pagerduty-integration/1.0/getSolutionGroups/getSubProductValues",
        type: 'GET',
        contentType: "application/json" ,
        data: {
            solutionGroup: selectedSG,
            projectkey : projectkey,
            product: selectedProd
        },
        async:false,
        success: function(data) {
            var  subproductSelectfield = AJS.$("#"+subproductSelectfieldId);

            //Remove everything but the --select-- option
            AJS.$('option', subproductSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var value = data[i].value;
                var product = AJS.$("<option/>").attr("value", value).text(value);
                subproductSelectfield.append(product);
            }
        }
    });
}

function populateSubSubProductsForSolutionGroupDDCAndProduct(projectkey, selectedSG, selectedProd, selectSubProd, subSubProductSelectId) {
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/pagerduty-integration/1.0/getSolutionGroups/getSubSubProductValues",
        type: 'GET',
        contentType: "application/json" ,
        data: {
            solutionGroup: selectedSG,
            projectkey : projectkey,
            product: selectedProd,
            subproduct: selectSubProd
        },
        async:false,
        success: function(data) {
            var  subproductSelectfield = AJS.$("#"+subSubProductSelectId);

            //Remove everything but the --select-- option
            AJS.$('option', subproductSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var value = data[i].value;
                var product = AJS.$("<option/>").attr("value", value).text(value);
                subproductSelectfield.append(product);
            }
        }
    });
}

function validateServiceFormFields(serviceNameSelectId, clientsImpactedSelectId, impactedSelectId, severitySelectId,
ddcProductSelectId) {
    AJS.$(".error").html(''); //Clear all error messages and then validate
    var serviceName = AJS.$("#" + serviceNameSelectId + " option:selected").val();
    var clientsImpacted = AJS.$("#" + clientsImpactedSelectId +" option:selected").val();
    var impacted = AJS.$("#" + impactedSelectId +" option:selected").val();
    var severity = AJS.$("#" + severitySelectId +" option:selected").val();
    var ddcProduct = AJS.$("#"+ ddcProductSelectId + " option:selected").val();
    
    var validationPassed = true;

    if (!serviceName) {
        AJS.$("#" +serviceNameSelectId).focus().siblings(".error").html("Please select a PagerDuty Service");
        validationPassed = false;
    }

    if (!clientsImpacted) {
        AJS.$("#"+clientsImpactedSelectId).focus().siblings(".error").html("Please select Clients Impacted ");
        validationPassed = false;
    } else if (clientsImpacted === "External" && !ddcProduct) {
        AJS.$("#"+ddcProductSelectId).focus().siblings(".error").html("Product is required for Clients Impacted External.");
        validationPassed = false;
    } else if (clientsImpacted === "Internal" && !impacted) {
        AJS.$("#"+impactedSelectId).focus().siblings(".error").html("Please select Impacted since Clients Impacted is Internal");
        validationPassed = false;
    }

    if (!severity) {
        AJS.$("#"+severitySelectId).focus().siblings(".error").html("Severity is required.");
        validationPassed = false;
    }

    return validationPassed;
}

function filterResults(serviceNameToSearch,ddcProductToSearch){
    AJS.$("#pdservicestable tbody tr").remove();
    var postData = JSON.stringify({
        serviceName : serviceNameToSearch,
        ddcProduct : ddcProductToSearch
    });

    /*
     First clear all the rows in the table.
     Then, make an AJAX call to get all the available mappings from AO in JSON format.
     Then parse it and display it in the form table rows to the already existing table in the vm
     */
    AJS.$("#csarmappingstable tbody tr").remove();
    var url = "/rest/pagerduty-integration/1.0/pdServiceConfig/searchPDServiceConfig";
    AJS.$.ajax({
        url: url,
        dataType: "json",
        type: "POST",
        data: postData,
        contentType: 'application/json',
        success: function (data) {
            AJS.log("Parsing the response and converting them to table rows");
            PDTableRowAppender.showAllRowsWithEditAndDelete(data);
        }
    });

}

