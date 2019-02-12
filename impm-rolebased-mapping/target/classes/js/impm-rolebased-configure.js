var RoleBasedMapper = {
	
    addRoleBasedMapping: function() {

        //Get the selected fields from the mapping dialog    	
        if(AJS.$("#selectedIssueTypes").val()!=='' &&  AJS.$("#selectedProjectRoles").val()!==''){
        	 	
        	var solutionGroupVal = AJS.$("#selectedSolutionGroups").val();
			var projectRolesVal = AJS.$("#selectedProjectRoles").val();
        	if(solutionGroupVal.indexOf("&")>0){
        		solutionGroupVal = solutionGroupVal.replace(/&/g, "%26");
        	}  
			if(projectRolesVal.indexOf("&")>0){
				projectRolesVal = projectRolesVal.replace(/&/g, "%26");
			}			
        	console.log("solutionGroupVal:"+solutionGroupVal);
            AJS.$.ajax({
                url :"/rest/rolebasedmap/1.0/ProjectRoles/addRoleBasedMap.json?projectKey="+AJS.$("#selectedProjects").val()+"&issueType="+AJS.$("#selectedIssueTypes").val()+"&solutionGroup="+solutionGroupVal+"&projectRole="+projectRolesVal,
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {                	
                	
						console.log("add mapping:"+response.length);
	                    AJS.$("#rolebasedmaptable tbody tr").remove();
	                    RoleBasedTableRowAppender.showTableRows(response);
	                    console.log("mapping added");
                    	JIRA.Messages.showSuccessMsg("FieldMapping Mapping Added!");
    
                },
                error: function (response) {
                	JIRA.Messages.showErrorMsg('Duplicate Entry Exist for ' +AJS.$("#selectedSolutionGroups").val());
                }
            });            
        }
    },
	
	
	removeRoleBasedMapping:function(projectKey, issueType,solutionGroup,projectRole) {
		
		if(solutionGroup.indexOf("&")>0){
			solutionGroup = solutionGroup.replace(/&/g, "%26");
    	} 

		if(projectRole.indexOf("&")>0){
			projectRole = projectRole.replace(/&/g, "%26");
    	}		
		
       AJS.$.ajax({
                url :"/rest/rolebasedmap/1.0/ProjectRoles/deleteRoleBasedMap.json?projectKey="+projectKey+"&issueType="+issueType+"&solutionGroup="+solutionGroup+"&projectRole="+projectRole,
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
                	console.log("delete mapping:"+response.length);
                    AJS.$("#rolebasedmaptable tbody tr").remove();
                    RoleBasedTableRowAppender.showTableRows(response);
                    console.log("mapping deleted");
                }
            });

        JIRA.Messages.showSuccessMsg("FieldMapping Mapping Deleted!");
    },
	
	
	getAllRoleBasedMappings:function() {
       
        AJS.$("#rolebasedmaptable tbody tr").remove();        
        AJS.$.ajax({
            url: "/rest/rolebasedmap/1.0/ProjectRoles/getAllRoleBasedMap",
            dataType: "json",
            type: "PUT",
            success: function (response) {
            	console.log("Pre Fetching mapping:"+response.length);
                AJS.log("Parsing the response and converting them to table rows");
                RoleBasedTableRowAppender.showTableRows(response);
                console.log("pre mapping fetched");
            }
        });
    }
}
















































