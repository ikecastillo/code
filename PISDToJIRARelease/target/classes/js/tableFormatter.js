/**
 * This file is for formatting the rows of the mappings table in view mode.
 * Also contains client side validations before a row is sent to update.
 * 
 * Created by yagnesh.bhat on 4/19/2016.
 */
var PISDToRelTableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deleteProjMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#pisdtoreltktmappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isTheSdProjectAlreadyMapped: function(sdProjectName) {
        var isItMapped = false;
        var cellIndexMapping = { 0: true };
        AJS.$("#pisdtoreltktmappingstable tbody tr").each( function(){
            var sdProjectNameInTable = "";
            AJS.$(this).find("td").each(function(cellIndex) {
                console.log("Cell index " + cellIndex); 
                if (cellIndexMapping[cellIndex]) {
                    console.log("Cell index to consider " + cellIndex);
                    if (cellIndex === 0) {
                        sdProjectNameInTable = AJS.$(this).text();
                        console.log("Service Desk Project Name under check " + sdProjectNameInTable);
                    }
                }
            });
            if (sdProjectNameInTable === sdProjectName ) {
                console.log("Found a service desk project already configured, returning true")
                isItMapped = true;
                return isItMapped;
            }
        });
        return isItMapped;
    }
};