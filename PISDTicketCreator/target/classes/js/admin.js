/**
 * Created by yagnesh.bhat on 4/18/2016.
 */
AJS.toInit(function() {

    //Get all mappings and add them to the concerned table in the concerned admin page.
    PISDMapper.getAllMappings();

    function updatePISDConfig() {
        AJS.$.ajax({
            url: "/rest/pisd-ticket-creator-api/1.0/pisdadminconfig",
            type: "PUT",
            contentType: "application/json",
            data: '{"pisdTicketUid": "' + AJS.$("#pisdticketuid").attr("value") +
            '", "pisdTicketPwd": "' + AJS.$("#pisdticketpwd").attr("value") +
            '","pisdticketSDApplicationName": "' +  AJS.$( "#pisdticketSDApplicationName option:selected" ).text() +
            '"}',
            processData: false
        });
    }

    AJS.$("#pistticketadmin2").submit(function(e) {
        e.preventDefault();
        updatePISDConfig();
        JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
    });
    
    // Shows the dialog when the "Show dialog" button is clicked
    AJS.$("#pisd-mapping-button").click(function() {
        AJS.log("Mapping dialog pops up");
        AJS.dialog2("#pisdtktmapping-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages

        //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
        // Check this function mappingDialogFieldsNotEmpty().
        AJS.$("#solngroupITIM > option:eq(0)").prop('selected',true);
        AJS.$("#productITIM > option:eq(0)").prop('selected',true);
        AJS.$("#subproductITIM > option:eq(0)").prop('selected',true);

        AJS.$("#serviceDeskId").val('');//Blank out the input field so new in
        AJS.$("#requestTypeId").val(''); //Blank out the input field so new in
        AJS.$("#custfieldWithId").val(''); //Blank out the input field so new in
        /*AJS.$(".mandatoryevtField").val('');*/

        populateSolutionGroupsForIncidentPISD("ITIM");
        
        AJS.$("#solngroupITIM").change(function() {
            var selectedSG = AJS.$("#solngroupITIM").val();
            if (selectedSG != "Select") {
                populateProductsForSolutionGroupPISD("ITIM", selectedSG);
            } else {
                //blank out products and sub products
                blankOutProductsAndSubproducts();
            }
        });

        AJS.$("#productITIM").change(function() {
            var selectedSG = AJS.$("#solngroupITIM").val();
            var selectedProd = AJS.$("#productITIM").val();
            if (selectedProd != "Select") {
                populateSubProductsForSolutionGroupAndProductPISD("ITIM", selectedSG, selectedProd);
            } else {
                //blank out sub products
                blankOutSubProducts();
            }
        });
        
        AJS.log("Mapping dialog ready to accept form inputs");
    });

    // Hides the dialog
    AJS.$("#pisdmapping-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pisdtktmapping-dialog").hide();
        AJS.log("Mapping dialog hides");
    });

    AJS.$("#pisdmapping-dialog-add-button").click(function() {
        if (pisdMappingDialogFieldsNotEmpty()) {
            PISDMapper.addMapping();
            AJS.dialog2("#pisdtktmapping-dialog").hide();
        }
    });

    AJS.$(".deletePisdMapping").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });
        
        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#pisdtktdelete-mapping-dialog").show();
        AJS.$("#serviceDeskIdconf").text(tableData[0]);
        AJS.$("#serviceDeskNameconf").text(tableData[1]);
        AJS.$("#requestTypeIdIdconf").text(tableData[2]);
        AJS.$("#requestTypeNameconf").text(tableData[3]);
        AJS.$("#customfieldswithIdsconf").text(tableData[4]);

    });

    AJS.$("#pisdmapping-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pisdtktdelete-mapping-dialog").hide();
        AJS.log("Delete Mapping dialog hides");
    });

    AJS.$("#pisdmapping-dialog-delete-button").click(function() {
        PISDMapper.removeMapping(AJS.$("#serviceDeskIdconf").text(),
            AJS.$("#serviceDeskNameconf").text(),AJS.$("#requestTypeIdIdconf").text()
        ,AJS.$("#requestTypeNameconf").text(),AJS.$("#customfieldswithIdsconf").text());
        AJS.dialog2("#pisdtktdelete-mapping-dialog").hide();
    });
    
    function populateSolutionGroupsForIncidentPISD(projectKey) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/xmatter-integration/1.0/getSolutionGroups",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                projectkey : projectKey
            },
            async:false,
            success: function(data) {
                var  solnlineSelectfield = AJS.$("#solngroupITIM");

                //Remove everything but the --select-- option
                AJS.$('option', solnlineSelectfield).not(':eq(0)').remove();
                for(var i=0;i<data.length;i++){
                    var value = data[i].value;
                    var solnLineOption = AJS.$("<option/>").attr("value", value).text(value);
                    solnlineSelectfield.append(solnLineOption);
                }
            }
        });
    }
    
    function populateProductsForSolutionGroupPISD(projectkey, selectedSG) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/incident/1.0/getTypes/getProductValues",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                solutionGroup: selectedSG,
                projectkey : projectkey
            },
            async:false,
            success: function(data) {
                var  productSelectfield = AJS.$("#productITIM");

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

    function populateSubProductsForSolutionGroupAndProductPISD(projectkey, selectedSG, selectedProd) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/pisd-ticket-creator-api/1.0/subProducts/getSubProductValues",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                solutionGroup: selectedSG,
                projectkey : projectkey,
                product: selectedProd
            },
            async:false,
            success: function(data) {
                var  subproductSelectfield = AJS.$("#subproductITIM");

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
    
    function blankOutProductsAndSubproducts() {
        var  productSelectfield = AJS.$("#productITIM");
        AJS.$('option', productSelectfield).not(':eq(0)').remove();
        blankOutSubProducts();
    }
    
    function blankOutSubProducts() {
        var subProductSelectField = AJS.$("#subproductITIM");
        AJS.$('option', subProductSelectField).not(':eq(0)').remove();
    }
    
    function pisdMappingDialogFieldsNotEmpty() {
        var validationPassed = true;
        var product = AJS.$("#productITIM option:selected").text();
        var subProduct = AJS.$("#subproductITIM option:selected").text();
        var serviceDeskID = AJS.$("#serviceDeskId").val().trim();
        var requestTypeID = AJS.$("#requestTypeId").val().trim();
        var customfieldWithIds = AJS.$("#custfieldWithId").val().trim();
        
        if (product == "Select") {
            AJS.$("#productITIM").focus().siblings(".error").html("Please select a Product");
            validationPassed = false;
        }
        
        if (subProduct == "Select") {
            AJS.$("#subproductITIM").focus().siblings(".error").html("Please select a Sub Product");
            validationPassed = false;
        }
        
        if (!serviceDeskID) {
            AJS.$("#serviceDeskId").focus().siblings(".error").html("Please enter the Service Desk ID");
            validationPassed = false;
        }
        
        if (!requestTypeID) {
            AJS.$("#requestTypeId").focus().siblings(".error").html("Please enter the Request Type ID");
            validationPassed = false;
        }
        
        return validationPassed;
        
    }
});