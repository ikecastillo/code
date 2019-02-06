var TableRowAppender = {
    showServiceDeskTableRows:function(ajaxResponse) {
        
        AJS.$.each(ajaxResponse, function(key, val) {
		
            var tr=AJS.$('<tr></tr>');
			 var index=0;
			 var service_id='';
			    AJS.$.each(val, function(k, v){
					index++;
					if(index===3){
					service_id=v;
					
					}else{
					AJS.$('<td class="tdadd" style="max-width:200px">'+v+'</td>').appendTo(tr);
					}
            });
             AJS.$('<td><input type="hidden" value="'+service_id+'" /> <a href="#" class="deleteconfiguration" title="Remove this row"> Delete </a></td>').appendTo(tr);
            tr.appendTo('#request-types-table-main-field .ui-sortable');
        });
        
    },
    
};
