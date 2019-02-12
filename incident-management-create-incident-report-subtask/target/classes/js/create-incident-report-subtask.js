AJS.toInit(function() {
    
	Mapper.getAllFieldMappings();
    
	  
	AJS.$("#dialog-show-button").click(function() {
	    AJS.dialog2("#demo-dialog").show();
	});

	AJS.$("#dialog-close-button").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#demo-dialog").hide();
	});
	
	AJS.$("#mapping-dialog-save-button").click(function() {
		if (mappingDialogFieldsNotEmpty()) {
         Mapper.addMapping();
         AJS.dialog2("#demo-dialog").hide();
     }             
	});
	
	AJS.$(".deleteMapping").live('click',function() {
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			tableData.push(AJS.$(this).text());
		});

		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to delete for JIRA " + tableData[0]);
		AJS.log("Field to delete for mapping " + tableData[1]);

		AJS.log("Delete Mapping dialog pops up");
		AJS.dialog2("#delete-dialog").show();
		AJS.$("#jirafieldconf").text(tableData[0]);
		AJS.$("#mappingfieldconf").text(tableData[1]);
		AJS.$("#fromissueconf").text(tableData[2]);
		AJS.$("#toissueconf").text(tableData[3]);

	});

	
	AJS.$("#mapping-dialog-delete-cancel-link").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#delete-dialog").hide();
		
	});

	AJS.$("#mapping-dialog-delete-button").click(function() {
		Mapper.removeMapping(AJS.$("#jirafieldconf").text(), AJS.$("#mappingfieldconf").text(),AJS.$("#fromissueconf").text(),AJS.$("#toissueconf").text());
		AJS.dialog2("#delete-dialog").hide();
	});
	
	
	function mappingDialogFieldsNotEmpty() {
    var errorMessagemapping, errorMessageJIRA;
    AJS.$('.error').html('') //Clear all error messages
    
    var jiraField = AJS.$("#selectedDefaultIssueFields").val();
	var mappingField = AJS.$("#mappingfield").val();

    if (!jiraField) {
        errorMessageJIRA = AJS.I18n.getText("genxmatters.admin.mappingform.dialog.errorjiraField");
    }
	if (!mappingField) {
        errorMessagemapping = AJS.I18n.getText("genxmatters.admin.mappingform.dialog.errormappingField");
    }

	if (errorMessageJIRA) {
        AJS.$("#selectedDefaultIssueFields").focus().siblings(".error").html(errorMessageJIRA);
        return false;
    }
	 if (errorMessagemapping) {
        AJS.$("#mappingfield").focus().siblings(".error").html(errorMessagemapping);
        return false;
    } 

    return true;
}
	
	
  AJS.$('#selectedProjects').change(function(event) {		
	  
	  console.log("selectedProjects "+AJS.$('#selectedProjects').find('option:selected').val());
		/*AJS.$.ajax({
			url : baseUrl + '/rest/dealertrack/1.0/assignee-mapping',
		    type : 'GET',
		    contentType : 'application/json',
		    data : { 
				    	projectKey: AJS.$('#selectedProjects').find('option:selected').val()
			    	},		    
			success: function(response) {
				var list = response == null ? [] : (response instanceof Array ? response : [response]);
				AJS.$('[id^=pndappr_]').each( function(){
					var idx = this.id.substring(this.id.indexOf('_'));
					AJS.$("#pndappr" + idx + "-field").val('');
				});
				
				AJS.$('[id^=pndapprerror_]').each( function(){
					var idx1 = this.id.substring(this.id.indexOf('_'));
					AJS.$("#pndapprerror" + idx1).text('');
				});
				
				AJS.$.each(list, function(index, assignee) {
						AJS.$("#pndappr_" + assignee.status + "-field").val(assignee.users);					
	            	});												
				}
		});
	  */
	});
  
});