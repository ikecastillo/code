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
	
	AJS.$(".deleteIssueMapping").live('click',function() {
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			tableData.push(AJS.$(this).text());
		});

		//TODO Confirm here that you are getting the right parameters to delete
		AJS.log("Field to delete for 1 " + tableData[0]);
		AJS.log("Field to delete for 2 " + tableData[1]);
		AJS.log("Field to delete for 3 " + tableData[2]);
		AJS.log("Field to delete for 4 " + tableData[3]);

		AJS.log("Delete Mapping dialog pops up");
		AJS.dialog2("#delete-dialog").show();
		AJS.$("#srcIssueFieldConf").text(tableData[0]);
		AJS.$("#destIssueFieldConf").text(tableData[1]);
		AJS.$("#srcIssueTypeConf").text(tableData[2]);
		AJS.$("#destIssueTypeConf").text(tableData[3]);
		

	});

	
	AJS.$("#mapping-dialog-delete-cancel-link").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#delete-dialog").hide();
		
	});

	AJS.$("#mapping-dialog-delete-button").click(function() {
		Mapper.removeMapping( AJS.$("#srcIssueFieldConf").text(),AJS.$("#destIssueFieldConf").text(),AJS.$("#srcIssueTypeConf").text(),AJS.$("#destIssueTypeConf").text());
		AJS.dialog2("#delete-dialog").hide();
	});
	
	
	function mappingDialogFieldsNotEmpty() {
    var errorMessagemapping, errorMessageJIRA;
    AJS.$('.error').html('') //Clear all error messages
    
    var jiraField = AJS.$("#selectedDefaultIssueFields").val();
	var mappingField = AJS.$("#mappingfield").val();

    if (!jiraField) {
        errorMessageJIRA = AJS.I18n.getText("create-and-link-issue.admin.mappingform.dialog.errorjiraField");
    }
	if (!mappingField) {
        errorMessagemapping = AJS.I18n.getText("create-and-link-issue.admin.mappingform.dialog.errormappingField");
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
	
	
  
});