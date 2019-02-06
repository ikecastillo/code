AJS.toInit(function() {
  
  var baseUrl =AJS.$("#baseURL").val();
 
    
  var Mapper = {
    addConfiguration: function() {

        //Get the selected fields from the mapping dialog
        
        if(AJS.$("#name").val()!=='' &&  AJS.$("#description").val()!==''){
            //Make the AJAX Call here to update the AO,then call getAllMappings()
            AJS.$.ajax({
                url :baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/addServiceDeskMainField.json?name="+AJS.$("#name").val()+"&description="+AJS.$("#description").val(),
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
					
                    AJS.$("#request-types-table-main-field .ui-sortable tr").remove();
                    TableRowAppender.showServiceDeskTableRows(response);
					
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("Configuration Added!");
        }
    },
	
	
	removeConfiguration:function(name, description) {
		
       AJS.$.ajax({
                url :baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/deleteServiceDeskMainField.json?name="+name+"&description="+description,
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
					
                    AJS.$("#request-types-table-main-field .ui-sortable tr").remove();
                    TableRowAppender.showServiceDeskTableRows(response);
					
                }
            });

        JIRA.Messages.showSuccessMsg("Configuration Deleted!");
    },
	
	
	updateConfiguration:function(name, description,service_id) {
		
       AJS.$.ajax({
                url :baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/updateServiceDeskMainField.json?name="+name+"&description="+description+"&service_id="+service_id,
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
                    AJS.$("#request-types-table-main-field .ui-sortable tr").remove();
                    TableRowAppender.showServiceDeskTableRows(response);
					
                }
            });

        JIRA.Messages.showSuccessMsg("Configuration Updated!");
    },
	
	cancelConfiguration:function() {
		
       AJS.$.ajax({
                url :baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/cancelServiceDeskMainField",
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
                     AJS.$("#request-types-table-main-field .ui-sortable tr").remove();
                    TableRowAppender.showServiceDeskTableRows(response);
					
                }
            });
    },
	
	
	getAllConfigurations:function() {
        
         //First clear all the rows in the table.
         //Then, make an AJAX call to get all the available mappings from AO in JSON format.
         //Then parse it and display it in the form table rows to the already existing table in the vm
         
		
        AJS.$("#request-types-table-main-field .ui-sortable tr").remove();
        
        AJS.$.ajax({
            url: baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/getServiceDeskMainFields.json",
            dataType: "json",
            type: "GET",
            success: function (response) {
				
                AJS.log("Parsing the response and converting them to table rows");
                TableRowAppender.showServiceDeskTableRows(response);
				
            }
        });
    },
}
  
   Mapper.getAllConfigurations();
   
   
		AJS.$('#request-types-table-main-field tbody tr').live('mouseover', function() { 
		  AJS.$(this).css('background-color', '#E8E8E8');
	  });
	  
	  
	  AJS.$('#request-types-table-main-field tbody tr').live('mouseout', function() { 
		  AJS.$(this).css('background-color', 'white');
	  });
	  
	  AJS.$('#request-types-table-main-field tbody tr .tdadd').live('mouseover', function() { 
		  if(AJS.$(this).children('.icons').length=== 0){
		  AJS.$(this).append('<div class="icons"></div>');
		   }
		  AJS.$(this).children('.icons').append('<a style="float:right" class="aui-icon-small aui-iconfont-edit"/>');
		  AJS.$(this).addClass('tdhover'); 
	  }); 
	  AJS.$('#request-types-table-main-field tbody tr .tdadd').live('mouseout', function() { 
		  AJS.$(this).children('.icons').remove();
		  AJS.$(this).removeClass('tdhover');
	  });
	  
	  
	  AJS.$("#request-types-table-main-field tbody tr .tdadd").live('click',function() {
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		
		
		AJS.$(this).closest('tr').find("td").each(function() {
			var tdData='';
			
			if(tdData===''){
        	 AJS.$(this).find("input").each(function() {
        		 if(AJS.$(this).is(":text")){
        			 //alert('text '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
				 if(AJS.$(this).is(":hidden")){
        		 //alert('hidden '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        	   });
        	 }
			 
			 if(tdData===''){
        	 AJS.$(this).find("textarea").each(function() {
				 tdData=AJS.$(this).val(); 
				  });
        	 }
			
			if(tdData===''){
        		 tdData=AJS.$(this).text();	 
        	 }
        	 tableData.push(tdData);
			
			//tableData.push(AJS.$(this).text());
		});
        
		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to delete for Name " + tableData[0]);
		AJS.log("Field to delete for Description " + tableData[1]);
			var name=tableData[0];
			var description=tableData[1];
			var sev_id=tableData[2];
		AJS.$(this).parent().html('<td class="tdhout"><input value="'+name+'" class="text" /></td><td><textarea rows="3" cols="70" class="textarea-cell" id="description" name="description" >'+description+'</textarea></td><td colspan="2"><input type="hidden" value="'+sev_id+'"  /> <input type="button" class="updateconfiguration" value="update" style="margin-right:10px"/><a  href="#" class="cancelconfiguration" original-title="">cancel</a></td>');
		
	});
	
	
	
	AJS.$(".cancelconfiguration").live('click',function() {
		 /* var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			var tdData='';
			
			if(tdData===''){
        	 AJS.$(this).find("input").each(function() {
        		 if(AJS.$(this).is(":text")){
        			 //alert('text '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
				 if(AJS.$(this).is(":hidden")){
        		 //alert('hidden '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        	   });
        	 }
			 
			 if(tdData===''){
        	 AJS.$(this).find("textarea").each(function() {
				 tdData=AJS.$(this).val(); 
				  });
        	 }
			
			if(tdData===''){
        		 tdData=AJS.$(this).text();	 
        	 }
        	 tableData.push(tdData);
		});

		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to cancel for Name " + tableData[0]);
		AJS.log("Field to cancel for Description " + tableData[1]);
			var name=tableData[0];
			var description=tableData[1];
			var sev_id=tableData[2]; 
		 AJS.$(this).parent().parent().html('<td class="tdadd">'+name+'</td><td class="tdadd">'+description+'</td><td><input type="hidden" value="'+sev_id+'"  /><a href="#" class="deleteconfiguration" title="Remove this row"> Delete </a></td>'); */ 
		 
		  Mapper.cancelConfiguration();
		
	});
	
	
	AJS.$(".updateconfiguration").live('click',function() {
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			var tdData='';
			
			if(tdData===''){
        	 AJS.$(this).find("input").each(function() {
        		 if(AJS.$(this).is(":text")){
        			 //alert('text '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
				 if(AJS.$(this).is(":hidden")){
        		 //alert('hidden '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        	   });
        	 }
			 
			 if(tdData===''){
        	 AJS.$(this).find("textarea").each(function() {
				 tdData=AJS.$(this).val();
				  });
        	 }
			
			if(tdData===''){
        		 tdData=AJS.$(this).text();	 
        	 }
        	 tableData.push(tdData);
		});

		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to update for Name " + tableData[0]);
		AJS.log("Field to update for Description " + tableData[1]);
			var name=tableData[0];
			var description=tableData[1];
           var service_id=tableData[2];
		Mapper.updateConfiguration(name,description,service_id);
		
	});
 
	//change of this clas is required because every button will have class .aui button
	AJS.$(".add-main-config").click(function() {
		
		Mapper.addConfiguration();
		           
	});
	
	
	AJS.$(".deleteconfiguration").live('click',function() {
		
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			tableData.push(AJS.$(this).text());
		});

		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to delete for Name " + tableData[0]);
		
		AJS.log("Field to delete for Description " + tableData[1]);
			var name=tableData[0];
			var description=tableData[1];
		Mapper.removeConfiguration(name,description);
		
		
	});
	
	
	function mappingDialogFieldsNotEmpty() {
    var errorMessagemapping, errorMessageJIRA;
    AJS.$('.error').html('') //Clear all error messages
    var jiraField = AJS.$("#name").val();
	var mappingField = AJS.$("#description").val();

    if (!name) {
        errorMessageJIRA = AJS.I18n.getText("servicedesk.admin.portal.errorname");
    }
	if (!description) {
        errorMessagemapping = AJS.I18n.getText("servicedesk.admin.portal.errordescription");
    }

	if (errorMessageJIRA) {
        AJS.$("#name").focus().siblings(".error").html(errorMessagename);
        return false;
    }
	 if (errorMessagemapping) {
        AJS.$("#description").focus().siblings(".error").html(errorMessagedescription);
        return false;
    } 

    return true;
}

	
	
});

