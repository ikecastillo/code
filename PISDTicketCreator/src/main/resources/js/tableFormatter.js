/**
 * This file is for formatting the rows of the mappings table in view mode.
 * Also contains client side validations before a row is sent to update.
 * 
 * Created by yagnesh.bhat on 4/19/2016.
 */
var PISDTableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deletePisdMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#pisdtktmappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isTheProdAndSubProdAlreadyMapped:function (product,subProduct) {
        var isItMapped = false;
        var cellIndexMapping = { 1: true, 3: true };
        AJS.$("#pisdtktmappingstable tbody tr").each( function(){
            var serviceDeskName = "";
            var requestTypeName = "";
            AJS.$(this).find("td").each(function(cellIndex) {
                console.log("Cell index " + cellIndex); 
                if (cellIndexMapping[cellIndex]) {
                    console.log("Cell index to consider " + cellIndex);
                    if (cellIndex === 1) {
                        serviceDeskName = AJS.$(this).text();
                        console.log("Service Desk Name under check " + serviceDeskName);
                    } 
                    if (cellIndex === 3) {
                        requestTypeName = AJS.$(this).text();
                        console.log("Request Type Name under check " + requestTypeName);
                    }
                }
            });
            if (serviceDeskName === product && requestTypeName === subProduct ) {
                console.log("Found a service desk config, returning true")
                isItMapped = true;
                return isItMapped;
            }
        });
        return isItMapped;
    }
};