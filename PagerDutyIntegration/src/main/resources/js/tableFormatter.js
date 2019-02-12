/**
 * This file is for formatting the rows of the mappings table and events table in view mode.
 * Also contains client side validations before a row is sent to update.
 *
 * Created by yagnesh.bhat on 6/25/2015.
 */
var PDTableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deletePDMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#pagerdutymappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isJIRAFieldAlreadyMapped:function (jiraField) {
        var isItMapped = false;
        AJS.$("#pagerdutymappingstable tbody tr td:nth-child(1)").each( function(){
            //If the JIRA field (first column data) is already displayed in the table the set the flag to true.
            if (AJS.$(this).text() === jiraField) {
                isItMapped = true;
            }
        });
        return isItMapped;
    },
    showAllRowsWithEditAndDelete : function (ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows with edit and delete options");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete and edit in a column as well
            AJS.$('<td><a href="#" class="pdEditService" title="Edit this row">Edit</a> | ' +
                '<a href="#" class="pdDeleteService" title="Remove this row">Delete</a>' +
                '</td>').appendTo(tr);
            tr.appendTo('#pdservicestable tbody');
        });
        AJS.log("All pagerDuty Services configs loaded");
    },

    isPagerDutyServiceConfigAlreadyPresent: function(serviceKey, serviceName,clientsImpacted, impacted,
                                                     severities, ddcProduct, ddcSubProduct, ddcSubSubProduct) {
        var isItMapped = false;
        var cellIndexMapping = { 0: true, 1: true, 2: true, 3: true, 4: true, 5: true, 6: true, 7:true };
        AJS.$("#pdservicestable tbody tr").each( function(){
            var serviceKeyInRow = "";
            var serviceNameInRow = "";
            var clientsImpactedInRow = "";
            var impactedInRow = "";
            var severitiesInRow = "";
            var ddcProductInRow = "";
            var ddcSubProductInRow = "";
            var ddcSubSubProductInRow = "";
            AJS.$(this).find("td").each(function(cellIndex) {
                console.log("Cell index " + cellIndex);
                if (cellIndexMapping[cellIndex]) {
                    console.log("Cell index to consider " + cellIndex);
                    if (cellIndex === 0) {
                        serviceKeyInRow = AJS.$(this).text();
                        console.log("serviceKeyInRow under check " + serviceKeyInRow);
                    }
                    if (cellIndex === 1) {
                        serviceNameInRow = AJS.$(this).text();
                        console.log("serviceName under check " + serviceNameInRow);
                    }
                    if (cellIndex === 2) {
                        clientsImpactedInRow = AJS.$(this).text();
                        console.log("clientsImpactedInRow under check " + clientsImpactedInRow);
                    }
                    if (cellIndex === 3) {
                        impactedInRow = AJS.$(this).text();
                        console.log("impactedInRow under check " + impactedInRow);
                    }
                    if (cellIndex === 4) {
                        severitiesInRow = AJS.$(this).text();
                        console.log("severities under check " + severitiesInRow);
                    }
                    if (cellIndex === 5) {
                        ddcProductInRow = AJS.$(this).text();
                        console.log("ddcProduct under check " + ddcProductInRow);
                    }
                    if (cellIndex === 6) {
                        ddcSubProductInRow = AJS.$(this).text();
                        console.log("ddcSubProduct under check " + ddcSubProductInRow);
                    }
                    if (cellIndex === 7) {
                        ddcSubSubProductInRow = AJS.$(this).text();
                        console.log("ddcSubSubProduct under check " + ddcSubSubProductInRow);
                    }
                }
            });
            if (serviceKeyInRow == serviceKey && serviceNameInRow == serviceName && clientsImpactedInRow == clientsImpacted
                && impactedInRow == impacted && severitiesInRow == severities && ddcProductInRow == ddcProduct &&
                ddcSubProductInRow == ddcSubProduct && ddcSubSubProductInRow == ddcSubProduct) {
                console.log("Found a row already in the table, returning true")
                isItMapped = true;
                return isItMapped;
            }
        });
        return isItMapped;
    }

};
