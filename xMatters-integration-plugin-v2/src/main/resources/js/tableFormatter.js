/**
 * This file is for formatting the rows of the mappings table and events table in view mode.
 * Also contains client side validations before a row is sent to update.
 *
 * Created by yagnesh.bhat on 6/25/2015.
 */
var TableRowAppender = {
    showTableRows:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deleteMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#mappingstable tbody');
        });
        AJS.log("All mappings loaded");
    },
    isXMattersFieldAlreadyMapped:function (xMattersField) {
        var isItMapped = false;
        AJS.$("#mappingstable tbody tr td:nth-child(2)").each( function(){
            //If the xMatters field (second column data) is already displayed in the table the set the flag to true.
            if (AJS.$(this).text() === xMattersField) {
                isItMapped = true;
            }
        });
        return isItMapped;
    },
    showAllXMattersEvents:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deleteEvent" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#eventstable tbody');
        });
        AJS.log("All xMatters events loaded");
    },

    isXMattersEventConfigAlreadyPresent:function (formapiid) {
        var isFormIDPresent = false;
        AJS.$("#eventstable tbody tr td:nth-child(1)").each( function(){
            //If the form API ID is already displayed in the table the set the flag to true.
            if (AJS.$(this).text() === formapiid) {
                isFormIDPresent = true;
            }
        });
        return isFormIDPresent;
    },

    isHipChatConfigAlreadyPresent:function(severity) {
        var isSeverityPresent = false;
        AJS.$("#hipchatconfigtable tbody tr td:nth-child(1)").each( function(){
            //If the severity is already displayed in the table the set the flag to true.
            if (AJS.$(this).text() === severity) {
                isSeverityPresent = true;
            }
        });
        return isSeverityPresent;
    },

    showAllHipChatConfigs:function(ajaxResponse) {
        AJS.log("Parsing the response and converting them to table rows");
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
            //append the delete column as well
            AJS.$('<td><a href="#" class="deleteHCConfig" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#hipchatconfigtable tbody');
        });
        AJS.log("All Hipchat configs loaded");
    }

};
