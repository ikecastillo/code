/**
 * This file is for formatting the rows of the mappings table in view mode.
 * Also contains client side validations before a row is sent to update.
 *
 * Created by yagnesh.bhat on 5/6/2016.
 */
var CSAROwnerImplementorTableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deletecsarMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#csarmappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isSolGroupAndReqTypeMapped:function (solGroup, reqType) {
        var isItMapped = false;
        var cellIndexMapping = { 0: true, 1: true };
        AJS.$("#csarmappingstable tbody tr").each( function(){
            var solnGroupName = "";
            var requestTypeName = "";
            AJS.$(this).find("td").each(function(cellIndex) {
                console.log("Cell index " + cellIndex);
                if (cellIndexMapping[cellIndex]) {
                    console.log("Cell index to consider " + cellIndex);
                    if (cellIndex === 0) {
                        solnGroupName = AJS.$(this).text();
                        console.log("Soln Grp  Name under check " + solnGroupName);
                    }
                    if (cellIndex === 1) {
                        requestTypeName = AJS.$(this).text();
                        console.log("Request Type Name under check " + requestTypeName);
                    }
                }
            });
            if (solnGroupName === solGroup && requestTypeName === reqType ) {
                console.log("Found a service desk config, returning true")
                isItMapped = true;
                return isItMapped;
            }
        });
        return isItMapped;
    }
};
