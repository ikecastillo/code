var Mapper = {
    addMapping: function() {

        //Get the selected fields from the mapping dialog
    	console.log("selectedProjectIssueTypes:"+AJS.$("#selectedProjectIssueTypes").val());
    	console.log("selectedIssueTypes:"+AJS.$("#selectedIssueTypes").val());
    	console.log("selectedDefaultIssueFields:"+AJS.$("#selectedDefaultIssueFields").val());
    	console.log("mappingfield:"+AJS.$("#mappingfield").val());
    	
        if(AJS.$("#selectedDefaultIssueFields").val()!=='' &&  AJS.$("#mappingfield").val()!==''){
            
            AJS.$.ajax({
                url :"/rest/dealertracks/fieldmappingrest/1.0/FieldMappingField/addFieldMappingField.json?jiraField="+AJS.$("#selectedDefaultIssueFields").val()+"&mappingField="+AJS.$("#mappingfield").val()+"&fromIssueType="+AJS.$("#selectedProjectIssueTypes").val()+"&toIssueType="+AJS.$("#selectedIssueTypes").val(),
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
					console.log("add mapping:"+response.length);
                    AJS.$("#mappingstable tbody tr").remove();
                    TableRowAppender.showTableRows(response);
                    console.log("mapping added");
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("FieldMapping Mapping Added!");
        }
    },
	
	
	removeMapping:function(jiraField, mappingField,fromIssueType,toIssueType) {
       AJS.$.ajax({
                url :"/rest/dealertracks/fieldmappingrest/1.0/FieldMappingField/deleteFieldMappingField.json?jiraField="+jiraField+"&mappingField="+mappingField+"&fromIssueType="+fromIssueType+"&toIssueType="+toIssueType,
                type: "PUT",
                contentType: 'application/json',
                success: function (response) {
                	console.log("delete mapping:"+response.length);
                    AJS.$("#mappingstable tbody tr").remove();
                    TableRowAppender.showTableRows(response);
                    console.log("mapping deleted");
                }
            });

        JIRA.Messages.showSuccessMsg("FieldMapping Mapping Deleted!");
    },
	
	
	getAllFieldMappings:function() {
       
        AJS.$("#mappingstable tbody tr").remove();
        
        AJS.$.ajax({
            url: "/rest/dealertracks/fieldmappingrest/1.0/FieldMappingField/getAllFieldMappings",
            dataType: "json",
            type: "PUT",
            success: function (response) {
            	console.log("Pre Fetching mapping:"+response.length);
                AJS.log("Parsing the response and converting them to table rows");
                TableRowAppender.showTableRows(response);
                console.log("pre mapping fetched");
            }
        });
    },
}


















































