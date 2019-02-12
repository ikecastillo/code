AJS.toInit(function() {
	
	RoleBasedMapper.getAllRoleBasedMappings();
   	  
	AJS.$("#dialog-show-button").click(function() {
	    AJS.dialog2("#demo-dialog").show();
	});

	AJS.$("#dialog-close-button").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#demo-dialog").hide();
	});
	
	AJS.$("#rolebasedmapping-dialog-save-button").click(function() {
		if (mappingDialogFieldsNotEmpty()) {
			RoleBasedMapper.addRoleBasedMapping();
         AJS.dialog2("#demo-dialog").hide();
     }             
	});
	
	AJS.$(".deleteRoleBasedMapping").live('click',function() {
		var rowToDelete = AJS.$(this).closest('tr');
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			tableData.push(AJS.$(this).text());
		});

		AJS.log("Field to delete for Project Key " + tableData[0]);
		AJS.log("Field to delete for Issue Type " + tableData[1]);
		AJS.log("Field to delete for Solution Group " + tableData[2]);
		AJS.log("Field to delete for Project Role " + tableData[3]);

		AJS.log("Delete Mapping dialog pops up");
		AJS.dialog2("#delete-dialog").show();
		AJS.$("#projectKeyConf").text(tableData[0]);
		AJS.$("#issueTypeConf").text(tableData[1]);
		AJS.$("#solutionGroupConf").text(tableData[2]);
		AJS.$("#projectRoleConf").text(tableData[3]);

	});
	
	AJS.$("#rolebasedmapping-dialog-delete-cancel-link").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#delete-dialog").hide();
		
	});
	

	AJS.$("#rolebasedmapping-dialog-delete-button").click(function() {
		RoleBasedMapper.removeRoleBasedMapping(AJS.$("#projectKeyConf").text(), 
				AJS.$("#issueTypeConf").text(),
				AJS.$("#solutionGroupConf").text(),
				AJS.$("#projectRoleConf").text());
		AJS.dialog2("#delete-dialog").hide();
	});
	
	
	function mappingDialogFieldsNotEmpty() {
    var errorMessageProjects, errorMessageIssueTypes,errorMessageGroups,errorMessageProjectRoles;
    AJS.$('.error').html('') //Clear all error messages
    var selectedProjects  = AJS.$("#selectedProjects").val();
	var selectedIssueTypes   = AJS.$("#selectedIssueTypes").val();
	var selectedSolutionGroups    = AJS.$("#selectedSolutionGroups").val();
	var selectedProjectRoles = AJS.$("#selectedProjectRoles").val();

    if (!selectedProjects) {
        errorMessageProjects = AJS.I18n.getText("com.dt.jira.project.rolebased.mapping.dialog.errorprojectkey");
    }
	if (!selectedIssueTypes) {
        errorMessageIssueTypes = AJS.I18n.getText("com.dt.jira.project.rolebased.mapping.dialog.errorissuetype");
    }

	if (!selectedSolutionGroups) {
        errorMessageGroups = AJS.I18n.getText("com.dt.jira.project.rolebased.mapping.dialog.errorsolutiongroup");
    }
	
	if (!selectedProjectRoles) {
        errorMessageProjectRoles = AJS.I18n.getText("com.dt.jira.project.rolebased.mapping.dialog.errorprojectrole");
    }
		
	if (errorMessageProjects) {
        AJS.$("#selectedProjects").focus().siblings(".error").html(errorMessageProjects);
        return false;
    }
	 if (errorMessageIssueTypes) {
        AJS.$("#selectedIssueTypes").focus().siblings(".error").html(errorMessageIssueTypes);
        return false;
    }

	if (errorMessageGroups) {
        AJS.$("#selectedSolutionGroups").focus().siblings(".error").html(errorMessageGroups);
        return false;
    }
	 if (errorMessageProjectRoles) {
        AJS.$("#selectedProjectRoles").focus().siblings(".error").html(errorMessageProjectRoles);
        return false;
    } 

    return true;
}


	var baseUrl = AJS.$("#baseURL").val();

	populateIssueTypes();

	function populateIssueTypes() {
		var prKey = AJS.$('#selectedProjects').find('option:selected').val();
		AJS.$.ajax({
		url : "/rest/rolebasedmap/1.0/ProjectRoles/getIssueTypes",
		type : 'GET',
		contentType : 'application/json',
		data : { 
					projectKey: prKey
				},		    
		success: function(response) {
				var list = response == null ? [] : (response instanceof Array ? response : [response]);
				AJS.$("#selectedIssueTypes").empty();
				var issueTypeOption = AJS.$("<option/>").attr("value", "").text("--Select Issue Type--");							
				AJS.$.each(list, function(index, fieldModel) {
					issueTypeOption = AJS.$("<option/>").attr("value", fieldModel.value).text(fieldModel.label);
					AJS.$("#selectedIssueTypes").append(issueTypeOption);								
				});	
				populateSolutionGroups();			
			}
		});		
	}
	
	AJS.$("#selectedProjects").change(function(e) {
			e.preventDefault();
			 populateIssueTypes();
			 populateSolutionGroups();
	});
	
	AJS.$("#selectedIssueTypes").change(function(e) {
			e.preventDefault();
			populateSolutionGroups();
	});

		function populateSolutionGroups() {	
			var prKey = AJS.$('#selectedProjects').find('option:selected').val();
			var issueType = AJS.$('#selectedIssueTypes').find('option:selected').text();
		
			AJS.$.ajax({
				url : "/rest/rolebasedmap/1.0/ProjectRoles/getAllSolGrpMap",
				type : 'GET',
				contentType : 'application/json',
				data : { 
							projectKey: prKey,
							issueType: issueType
						},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);	
					AJS.$("#selectedSolutionGroups").empty();
					var solGrpOption = AJS.$("<option/>").attr("value", "").text("--Select Solution Group--");						
					AJS.$.each(list, function(index, solGrp) {
						solGrpOption = AJS.$("<option/>").attr("value", solGrp.value).text(solGrp.label);
						AJS.$("#selectedSolutionGroups").append(solGrpOption);				
					});												
				}
			});
    }
  
});
