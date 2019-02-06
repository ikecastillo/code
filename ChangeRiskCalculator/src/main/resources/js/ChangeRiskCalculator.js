AJS.toInit(function() {
	//on load populates risk calculation conditions.
	var baseUrl =AJS.$("#baseURL").val();
	  AJS.$.ajax({
			url : baseUrl + '/rest/dealertrack/riskCalculationrest/1.0/riskCalculationResource/riskConditions.json',
		    type : 'GET',
		    contentType : 'application/json',
		    data : { 
				    
			    	},		    
			success: function(response) {
				AJS.$("#risk-conditions-table tbody tr").remove();
				if(response !==null  &&  response.length >=1 ){
				AJS.$.each(response, function(i, item) {
				AJS.$('<tr id='+i+'>').html("<td>"+response[i].question1 +"</td><td>" +response[i].question2 +"</td><td>" + response[i].question3 + "</td><td>"+ response[i].question4 + "</td><td>"  + response[i].condition+"</td><td>" + response[i].result +"</td><td><input type='button'  id='"+ response[i].conditionID+ "'   class='delCondition'  value='Delete'  /></td></tr>").appendTo('#risk-conditions-table');
							});
				}else{
					AJS.$('<tr>').html("<td colspan='5' style='text-align:center'>  No records to display  </td></tr>").appendTo('#risk-conditions-table');
	            }
			}
		});

    //Configuring new Condition  (Add Condition) 
	  AJS.$("#add_risk").live('click', function () {
		if((AJS.$("#conditionID").val()!=="") && (AJS.$("#resultID").val()!=="")){ 
		AJS.$.ajax({
			url : baseUrl + '/rest/dealertrack/riskCalculationrest/1.0/riskCalculationResource/addRiskCondition.json?question1='+ AJS.$("#1").val()+'&question2='+AJS.$("#2").val()+'&question3='+AJS.$("#3").val()+'&question4='+AJS.$("#4").val()+'&condition='+AJS.$("#conditionID").val()+'&result='+AJS.$("#resultID").val(),
		    type : 'PUT',
		    contentType : 'application/json',
		    data : { 
				    
			    	},		    
			success: function(response) {
				AJS.$("#risk-conditions-table tbody tr").remove();
				if(response !==null  &&  response.length >=1 ){
				AJS.$.each(response, function(i, item) {
				AJS.$('<tr id='+i+'>').html("<td>"+response[i].question1 +"</td><td>" +response[i].question2 +"</td><td>" + response[i].question3 + "</td><td>" + response[i].question4 + "</td><td>" + response[i].condition+"</td><td>" + response[i].result+"</td><td><input type='button' id='"+ response[i].conditionID+ "'  class='delCondition' value='Delete'  /></td></tr>").appendTo('#risk-conditions-table');
							});
				}else{
					AJS.$('<tr>').html("<td colspan='5' style='text-align:center'>  No records to display  </td></tr>").appendTo('#risk-conditions-table');
	            }
				
			}
		});
		}else{
			
			alert("Please select required feilds");
			
		}
	
	  });
	  
	//Removing a Condition (delete Condition) 
	  AJS.$(".delCondition").live('click', function (e) {
		  AJS.$.ajax({
				url : baseUrl + '/rest/dealertrack/riskCalculationrest/1.0/riskCalculationResource/deleteRiskCondition.json?conditionId='+ e.target.id,
			    type : 'PUT',
			    contentType : 'application/json',
			    data : { 
					    
				    	},		    
				success: function(response) {
					AJS.$("#risk-conditions-table tbody tr").remove();
					if(response !==null  &&  response.length >=1 ){
					AJS.$.each(response, function(i, item) {
					AJS.$('<tr id='+i+'>').html("<td>"+response[i].question1 +"</td><td>" +response[i].question2 +"</td><td>" + response[i].question3 + "</td><td>" + response[i].question4 + "</td><td>" + response[i].condition+"</td><td>" + response[i].result+"</td><td><input type='button' id='"+ response[i].conditionID+ "'  class='delCondition' value='Delete'  /></td></tr>").appendTo('#risk-conditions-table');
								});
					}else{
						AJS.$('<tr>').html("<td colspan='5' style='text-align:center'>  No records to display  </td></tr>").appendTo('#risk-conditions-table');
		            }
				}
			});
		
		
		  });
	
});


AJS.$(function() { 
	
	AJS.$("select").auiSelect2();
	
	
});