/**
 * This file is for formatting the rows of the mappings table and events table in view mode.
 * Also contains client side validations before a row is sent to update.
 *
 * Created by yagnesh.bhat on 6/25/2015.
 */
var JIRASDFMTableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="jirasdfmdeleteMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#jirasdfmmappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isRowAlreadyPresent:function (jiraField,jiraSDField,jiraSDFieldId) {
        var isItMapped = false;
        var cellIndexMapping = { 0: true, 1: true, 2: true };
        AJS.$("#jirasdfmmappingstable tbody tr").each( function(){
            var jiraFieldInRow = "";
            var jiraSDFieldInRow = "";
            var jiraSDFieldIdInRow = "";
            AJS.$(this).find("td").each(function(cellIndex) {
                console.log("Cell index " + cellIndex);
                if (cellIndexMapping[cellIndex]) {
                    console.log("Cell index to consider " + cellIndex);
                    if (cellIndex === 0) {
                        jiraFieldInRow = AJS.$(this).text();
                        console.log("Jira Field under check " + jiraFieldInRow);
                    }
                    if (cellIndex === 1) {
                        jiraSDFieldInRow = AJS.$(this).text();
                        console.log("JIRA SD field Name under check " + jiraSDFieldInRow);
                    }
                    if (cellIndex === 2) {
                        jiraSDFieldIdInRow = AJS.$(this).text();
                        console.log("JIRA SD field Id under check " + jiraSDFieldIdInRow);
                    }
                }
            });
            if (jiraFieldInRow === jiraField && jiraSDFieldInRow === jiraSDField && jiraSDFieldIdInRow === jiraSDFieldId ) {
                console.log("Found a row already in the table, returning true")
                isItMapped = true;
                return isItMapped;
            }
        });
        return isItMapped;
    }
};
