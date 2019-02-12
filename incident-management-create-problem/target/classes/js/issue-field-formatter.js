var TableRowAppender = {
    showTableRows:function(ajaxResponse) {
        
        AJS.$.each(ajaxResponse, function(key, val) {
            var tr=AJS.$('<tr></tr>');
            AJS.$.each(val, function(k, v){
                AJS.$('<td>'+v+'</td>').appendTo(tr);
            });
             AJS.$('<td><a href="#" class="deleteIssueMapping" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#issuemappingstable tbody');
        });
        
    },
    
};
