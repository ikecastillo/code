AJS.toInit(function() {
     var baseUrl =AJS.$("#baseURL").val();
     var key=AJS.$("#projectKey").val();
     //loading issues on project and loading table 
     if(key !== null){
    	 AJS.$.ajax({
 			url : baseUrl + '/rest/dealertrack/autoassignee/1.0/jiraAutoAssignee/issueTypes.json?projectkey='+key,
 		    type : 'GET',
 		    contentType : 'application/json',
 		    data : { 
 				    	
 			    	},		    
 			success: function(response) {
 				var items = response == null ? [] : (response instanceof Array ? response : [response]);
 				AJS.$("#issue").children().remove();
 				AJS.$("#issue").append(new Option("---select---","None"));
 				AJS.$("#issue").select2("val", "None");
 				AJS.$.each(items, function(index, item) {
 					 AJS.$("#issue").append(new Option(item.value, item.value));
 				
 				});
 				AJS.$("#status").children().remove();
 				AJS.$("#status").append(new Option("---select---","None"));
 				AJS.$("#status").select2("val", "None");
 				AJS.$("#deatils_table tbody tr").remove();
 				AJS.$.ajax({
 					url : baseUrl + '/rest/dealertrack/autoassignee/1.0/autoAssigneeportal/portalIssuesStatus.json?project='+key+'&issueType='+AJS.$('#issue').val()+'&status='+AJS.$('#status').val(),
 				    type : 'GET',
 				    contentType : 'application/json',
 				    data : { 
 						    	
 					    	},		    
 					success: function(response) {
 						AJS.$("#deatils_table tbody tr").remove();
 						if(response !==null){
 						AJS.$.each(response, function(i, item) {
 						AJS.$('<tr id='+i+' style="border:1px solid black">').html("<td>"+response[i].issueType +"</td><td>" + response[i].status +"</td><td>" + response[i].assignee+ "</td><td style='width:70px'>  <input type='button' style='width:65px'  class='del' type='submit'  class='button spaced' value=' Delete '  title=' Delete a row'/></td></tr>").appendTo('#deatils_table');
 								});
 						}else{
 							AJS.$('<tr>').html("<td colspan='6' style='text-align:center'>  No records to display  </td></tr>").appendTo('#deatils_table');
 		                }
 					}
 				});
 	            }
 		});	 
    	 
    	 
     }
    
    //event to loads statuses on issuetype 
    AJS.$('#issue').change(function(event) {		
		 if(key!== null || AJS.$("#issue").val()!=='None'){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/autoassignee/1.0/jiraAutoAssignee/status.json?projectkey='+key+'&issueType='+AJS.$('#issue').val(),
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	
				    	},		    
				success: function(response) {
					var items = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$("#status").children().remove();
					AJS.$("#status").append(new Option("---select---","None"));
					AJS.$.each(items, function(index, item) {
						 AJS.$("#status").append(new Option(item.value, item.value));
					
					});
					AJS.$("#status").select2("val", "None");
					AJS.$("#deatils_table tbody tr").remove();
					AJS.$.ajax({
						url : baseUrl + '/rest/dealertrack/autoassignee/1.0/autoAssigneeportal/portalIssuesStatus.json?project='+key+'&issueType='+AJS.$('#issue').val()+'&status='+AJS.$('#status').val(),
					    type : 'GET',
					    contentType : 'application/json',
					    data : { 
							    	
						    	},		    
						success: function(response) {
							AJS.$("#deatils_table tbody tr").remove();
							if(response !==null){
							AJS.$.each(response, function(i, item) {
							AJS.$('<tr id='+i+' style="border:1px solid black">').html("<td>"+response[i].issueType +"</td><td>" + response[i].status +"</td><td>" + response[i].assignee+ "</td><td style='width:70px'>  <input type='button'  style='width:65px'  class='del' type='submit'  class='button spaced' value=' Delete '  title=' Delete a row'/></td></tr>").appendTo('#deatils_table');
									});
							}else{
								AJS.$('<tr>').html("<td colspan='6' style='text-align:center'>  No records to display  </td></tr>").appendTo('#deatils_table');
			                }
						}
					});
		          }
			  });
		    }
		});
  
  //event to loads table on status change .
  AJS.$('#status').change(function(event) {		
		 if(key!==null || AJS.$("#issue").val()!=='None' || AJS.$("#status").val()!=='None' ){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/autoassignee/1.0/autoAssigneeportal/portalIssuesStatus.json?project='+key+'&issueType='+AJS.$('#issue').val()+'&status='+AJS.$('#status').val(),
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	
				    	},		    
				success: function(response) {
					AJS.$("#deatils_table tbody tr").remove();
					if(response !==null){
					AJS.$.each(response, function(i, item) {
					AJS.$('<tr id='+i+' style="border:1px solid black">').html("<td>"+response[i].issueType +"</td><td>" + response[i].status +"</td><td>" + response[i].assignee+ "</td><td style='width:70px'>  <input type='button'  style='width:65px'  class='del' type='submit'  class='button spaced' value=' Delete '  title=' Delete a row'/></td></tr>").appendTo('#deatils_table');
							});
					}else{
						AJS.$('<tr>').html("<td colspan='6' style='text-align:center'>  No records to display  </td></tr>").appendTo('#deatils_table');
	                }
				}
			});
		  }
		});
  
  AJS.$('#btnp_Save').click(function(event) {
	 if(key!==null && AJS.$("#issue").val()!=='None' && AJS.$("#status").val()!=='None' && AJS.$("#approval").val()!=='None' && AJS.$('#user_picker').find('option:selected').val()!==''){
		  AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/autoassignee/1.0/autoAssigneeportal/addPortal.json?project='+key+'&issueType='+AJS.$('#issue').val()+'&status='+AJS.$('#status').val()+'&assignee='+AJS.$('#user_picker').find('option:selected').val(),
			    type : 'PUT',
			    contentType : 'application/json',
			    data : { 
					    
			    },		    
				success: function(response) {
					if(response !==null){
						if(response.length<=0){
							alert("Assignee is Inactive or not Exists");	
						}else{
					AJS.$("#deatils_table tbody tr").remove();
					AJS.$.each(response, function(i, item) {
					AJS.$('<tr id='+i+' style="border:1px solid black">' ).html("<td>"+response[i].issueType +"</td><td>" + response[i].status +"</td><td>" + response[i].assignee+ "</td><td style='width:70px'>  <input type='button' style='width:65px'  class='del' type='submit'  class='button spaced' value=' Delete '  title=' Delete a row'/></td></tr>").appendTo('#deatils_table');
							});
						}
					}else{
						alert("Issue Type already exists   ");
					}
				}
			});
		  }else{
			  alert('Please select required values');  
		  }
		});
  
   AJS.$("#deatils_table tbody tr td .del").live('click', function() {
	 var  htmcol= AJS.$(this).parent().parent().html();
	 var res = htmcol.split("</td><td>");
	 var selctedissueType=res[0].replace('<td>','');
	   if(key!==null){
		   AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/autoassignee/1.0/autoAssigneeportal/deletePortal.json?project='+key+'&issueType='+AJS.$('#issue').val()+'&status='+AJS.$("#status").val()+'&s_issueType='+selctedissueType+'&s_status='+res[1],
			    type : 'PUT',
			    contentType : 'application/json',
			    data : { 
					s_status: res[1],
					s_issueType: selctedissueType
			    },		    
				success: function(response) {
					AJS.$("#deatils_table tbody tr").remove();
					if(response !==null){
					AJS.$.each(response, function(i, item) {
					AJS.$('<tr id='+i+' style="border:1px solid black">').html("<td>"+response[i].issueType +"</td><td>" + response[i].status +"</td><td>" + response[i].assignee+ "</td><td style='width:70px'>  <input type='button' style='width:65px' class='del' type='submit'  class='button spaced' value=' Delete '  title=' Delete a row'/></td></tr>").appendTo('#deatils_table');
					});
					}else{
						AJS.$('<tr>').html("<td colspan='6' style='text-align:center'> No records to display  </td></tr>").appendTo('#deatils_table');
				    }
				}
			});   
	   }
   });
 });

	AJS.$(function() { 
		AJS.$('#proj').auiSelect2(); 
		AJS.$('#issue').auiSelect2(); 
		AJS.$('#status').auiSelect2(); 
		
	});











