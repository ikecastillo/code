AJS.toInit(function() {
  var baseUrl = AJS.$("#baseURL").val();
    
  AJS.$("#btn_Save").click(function(e) {
	    e.preventDefault();
	    if(validateSolutionGroup() && validateImpactedGroup()){ 
	    	var pndappr_10804 = AJS.$('#pndappr_10804').find('option:selected').val() == undefined ? AJS.$('#pndappr_10804-field').val() : AJS.$('#pndappr_10804').find('option:selected').val();
			var pndappr_10805 = AJS.$('#pndappr_10805').find('option:selected').val() == undefined ? AJS.$('#pndappr_10805-field').val() : AJS.$('#pndappr_10805').find('option:selected').val();
			        
	    	if((pndappr_10804==pndappr_10805) && (pndappr_10804!='' && pndappr_10804!=null)  && (pndappr_10805!='' && pndappr_10804!=null)){
			AJS.$('[id^=pndapprerror_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#pndapprerror" + idx1).text('');
					});
	    		JIRA.Messages.showErrorMsg('User should not be the same for ' +AJS.$('#lbl_pndappr_ecab1').text()+' and '+AJS.$('#lbl_pndappr_ecab2').text());		 
		    }else{		    	
		    	saveConfig();    	
		    }
		    			
	    } 
	  });
  
  function validateSolutionGroup(){
		if(AJS.$('#solutionGroup').find('option:selected').val()!='None'){
				return true;
		}else{
				alert('Please select the Solution Group');
				return false;
		}
	}

	function validateImpactedGroup(){
		if(AJS.$('#impactedGroup').find('option:selected').val()!='None'){
			 	return true;
		}else{
				alert('Please select the Impacted Group');
				return false;
		}
	}
	
	 AJS.$("#btn_csv").click(function(e) {		         
		    e.preventDefault();		    
		    	 AJS.$.ajax({
				      url : baseUrl + '/rest/dealertrack/1.0/assignee-mapping/exportassigneecsv',
				      type : 'GET',
				      data : { 
					    	projectKey: AJS.$('#projectKey').val()
				    	},
				      success: function(response) { 				    	  
				    	  //window.open(baseUrl + '/rest/dealertrack/1.0/assignee-mapping/exportassigneecsv?projectKey='+AJS.$('#projectKey').val());
				    	  window.location.href = baseUrl + '/rest/dealertrack/1.0/assignee-mapping/exportassigneecsv?projectKey='+AJS.$('#projectKey').val();
	
				        },
				      error: function(request, status, error) {
							JIRA.Messages.showErrorMsg('Jira/Assignee Mapping could not be Export!'+request.responseText);					
					    }
				    });
		    return false;
	 });

	function saveConfig() {	
		
	    AJS.$.ajax({
	      url : baseUrl + '/rest/dealertrack/1.0/assignee-mapping',
	      type : 'PUT',
	      contentType : 'application/json',
	      processData: false,
	      data: getMapData(),
	      success: function(response) { 
	    	    	  
	    	  if(response == null){		    		  
	    		  AJS.$('[id^=pndapprerror_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#pndapprerror" + idx1).text('');
					});
	    		  
	    		  JIRA.Messages.showSuccessMsg('Jira/Assignee Mapping Saved Successfully!');	    		  
	    		 	
	    	  }else{
	    		  
	    		  var list = response == null ? [] : (response instanceof Array ? response : [response]);
				  
				  AJS.$('[id^=pndapprerror_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#pndapprerror" + idx1).text('');
					});
					
		    	  AJS.$.each(list, function(index, assignee) {
		    		  if(assignee.users==undefined || assignee.users==''|| assignee.users==null){
		    			  AJS.$("#pndapprerror_"+assignee.status).text("User name should not be empty");
		    		  }else{
		    			  AJS.$("#pndapprerror_"+assignee.status).text("User "+assignee.users+" Does Not Exist");
		    		  }										
	          	  });
	    	  }    	 
				
			},
	      error: function(request, status, error) {
				JIRA.Messages.showErrorMsg('Jira/Assignee Mapping could not be saved!'+request.responseText);					
			}
	    });
	  } 

	function getMapData(){
		
		var mapdata = new Array();	
		var assignee_status=[];
		
		var pndappr_10800 = AJS.$('#pndappr_10800').find('option:selected').val() == undefined ? AJS.$('#pndappr_10800-field').val() : AJS.$('#pndappr_10800').find('option:selected').val();
		var pndappr_10801 = AJS.$('#pndappr_10801').find('option:selected').val() == undefined ? AJS.$('#pndappr_10801-field').val() : AJS.$('#pndappr_10801').find('option:selected').val();
		var pndappr_10802 = AJS.$('#pndappr_10802').find('option:selected').val() == undefined ? AJS.$('#pndappr_10802-field').val() : AJS.$('#pndappr_10802').find('option:selected').val();
		var pndappr_10803 = AJS.$('#pndappr_10803').find('option:selected').val() == undefined ? AJS.$('#pndappr_10803-field').val() : AJS.$('#pndappr_10803').find('option:selected').val();
		var pndappr_10804 = AJS.$('#pndappr_10804').find('option:selected').val() == undefined ? AJS.$('#pndappr_10804-field').val() : AJS.$('#pndappr_10804').find('option:selected').val();
		var pndappr_10805 = AJS.$('#pndappr_10805').find('option:selected').val() == undefined ? AJS.$('#pndappr_10805-field').val() : AJS.$('#pndappr_10805').find('option:selected').val();
		
		
		assignee_status.push({status:AJS.$('#pndappr_sme').val(),  users:pndappr_10800});
		assignee_status.push({status:AJS.$('#pndappr_sec').val(),  users:pndappr_10801});
		assignee_status.push({status:AJS.$('#pndappr_cab').val(),  users:pndappr_10802});
		assignee_status.push({status:AJS.$('#pndappr_sch').val(),  users:pndappr_10803});
		assignee_status.push({status:AJS.$('#pndappr_ecab1').val(),users:pndappr_10804});
		assignee_status.push({status:AJS.$('#pndappr_ecab2').val(),users:pndappr_10805});
		
		for(j=0;j<assignee_status.length;j++){
			
		var map = {
				projectKey: AJS.$('#projectKey').val(),
				solutionGroup: AJS.$('#solutionGroup').find('option:selected').val(),
				impact: AJS.$('#impactedGroup').find('option:selected').val(),
				status: assignee_status[j].status,
				users: assignee_status[j].users
			};
			
			mapdata.push(map);
		}
		console.log(JSON.stringify(mapdata));
		return JSON.stringify(mapdata);
			   
	}

AJS.$('#solutionGroup').change(function(event) {		
  
		if(AJS.$("#solutionGroup").val()!=='None' || AJS.$("#impactGroup").val()!=='None'){
			
		AJS.$.ajax({
			url : baseUrl + '/rest/dealertrack/1.0/assignee-mapping',
		    type : 'GET',
		    contentType : 'application/json',
		    data : { 
				    	projectKey: AJS.$('#projectKey').val(), 
				    	solutionGroup :   AJS.$('#solutionGroup').find('option:selected').val(), 
				    	impact : AJS.$('#impactedGroup').find('option:selected').val() 
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
	  }
	});

AJS.$('#impactedGroup').change(function(event) {		
	  
			if(AJS.$("#solutionGroup").val()!=='None' || AJS.$("#impactGroup").val()!=='None'){
			
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/1.0/assignee-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(), 
					    	solutionGroup :   AJS.$('#solutionGroup').find('option:selected').val(), 
					    	impact : AJS.$('#impactedGroup').find('option:selected').val() 
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
		  }
		});

});

AJS.$(function() {
    AJS.$('#solutionGroup').auiSelect2();
    AJS.$('#impactedGroup').auiSelect2();
});




