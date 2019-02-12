AJS.toInit(function() {
  var baseUrl = AJS.$("#baseURL").val();
  
  AJS.$("#typesGroup").change(function(event){
	    
	  AJS.$( "#typesGroup option:selected").each(function(){	
	  
		  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
				var idx = this.id.substring(this.id.indexOf('_'));	      					
				AJS.$("#pndappr" + idx + "-field").val('');
			});
			
		AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
		
          if(AJS.$(this).text()=="Internal"){
        	  AJS.$(".box").hide(); 
        	  AJS.$(".InternalImpacted").hide();
        	  AJS.$(".Internal").show();
        	  AJS.$('#s2id_impactedGroup').find('a span.select2-chosen').text('');
        	  AJS.$('#s2id_impactedGroup').find('a span.select2-chosen').text('---select---');
        	  
        	  if((AJS.$('#s2id_locationGroup').find('a span.select2-chosen').text()!=='---select---') 
      				&& (AJS.$("#locationGroup").val()!=='None' 
      					|| AJS.$('#typesGroup').find('option:selected').val()!=='None') 
      					&& (AJS.$('#typesGroup').find('option:selected').text()=='Internal') 
      					&& (AJS.$('#s2id_impactedGroup').find('a span.select2-chosen').text()=='---select---')){
      	    	      			
      	    	AJS.$.ajax({
      				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
      			    type : 'GET',
      			    contentType : 'application/json',
      			    data : { 
      					    	projectKey: AJS.$('#projectKey').val(),  
      					    	type : AJS.$('#typesGroup').find('option:selected').text(),
      					    	grpOptionName :   AJS.$('#locationGroup').find('option:selected').val(),
      					    	cldGrpOptionName :   'None'
      				    	},		    
      				success: function(response) {
      					var list = response == null ? [] : (response instanceof Array ? response : [response]);
      					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
      						var idx = this.id.substring(this.id.indexOf('_'));					
      						AJS.$("#pndappr" + idx + "-field").val('');
      					});
      					
      					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
      						var idx1 = this.id.substring(this.id.indexOf('_'));
      						AJS.$("#appr" + idx1).text('');
      					});
      					
      					AJS.$.each(list, function(index, rcasubtask) {
      							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
      		            	});												
      					}
      			});
      	    }
        	  
        	  if(AJS.$('#s2id_locationGroup').find('a span.select2-chosen').text()!=='---select---'){
        		  AJS.$(".InternalImpacted").show();
        		  
        		  AJS.$.ajax({
  	      			url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/cascade-mapping',
  	      		    type : 'GET',
  	      		    contentType : 'application/json',
  	      		    data : { 
  	      				    	projectKey: AJS.$('#projectKey').val(),  
  	      				    	type : AJS.$('#typesGroup').find('option:selected').text(),
  	      				    	grpOptionName :   AJS.$('#s2id_locationGroup').find('a span.select2-chosen').text()
  	      			    	},		    
  	      			success: function(response) {
  	      				
	  	      			if(response!=null){
			      				AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			      					var idx = this.id.substring(this.id.indexOf('_'));	      					
			      					AJS.$("#pndappr" + idx + "-field").val('');
			      				});
			      				
			  	      		    AJS.$("#impactedGroup").find('option').remove();
								var impactedGroupOption = AJS.$("<option></option>").attr("value", "None").text("---select---").attr("selected", "selected");
								 AJS.$("#impactedGroup").append(impactedGroupOption);
								AJS.$.each(response,function(index,value){ 
									 //console.log("key = " + index + " value = " + value);
									 impactedGroupOption = AJS.$("<option></option>").attr("value", index).text(value);	
									 AJS.$("#impactedGroup").append(impactedGroupOption);
								 });	
								
	  	      				}
	  	      			else{
		  	      			AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
		      					var idx = this.id.substring(this.id.indexOf('_'));	      					
		      					AJS.$("#pndappr" + idx + "-field").val('');
		      				});
	  	      			}
  	      			}
  	      		});
            	  
            	  }else{
            		 
            		  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
	      					var idx = this.id.substring(this.id.indexOf('_'));	      					
	      					AJS.$("#pndappr" + idx + "-field").val('');
	      				});
            		  
            		  AJS.$(".InternalImpacted").hide();
            	  }
          }else if(AJS.$(this).text()=="External"){
        	  AJS.$(".box").hide(); 
        	  AJS.$(".ExternalProduct").hide();
        	  AJS.$(".External").show();
        	  AJS.$('#s2id_productGroup').find('a span.select2-chosen').text('');        	 
        	  AJS.$('#s2id_productGroup').find('a span.select2-chosen').text('---select---');
        	  AJS.$(".ExternalFourthLevel").hide();
			  
        	  if((AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()!=='---select---') 
      				&& (AJS.$("#solutionGroup").val()!=='None' 
      					|| AJS.$('#typesGroup').find('option:selected').val()!=='None') 
      					&& (AJS.$('#typesGroup').find('option:selected').text()=='External') 
      					&& (AJS.$('#s2id_productGroup').find('a span.select2-chosen').text()=='---select---')){
      	    	
      			console.log("calling for user selection");
      			 
      	    	AJS.$.ajax({
      				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
      			    type : 'GET',
      			    contentType : 'application/json',
      			    data : { 
      					    	projectKey: AJS.$('#projectKey').val(),  
      					    	type : AJS.$('#typesGroup').find('option:selected').text(),
      					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
      					    	cldGrpOptionName :   'None'
      				    	},		    
      				success: function(response) {
      					var list = response == null ? [] : (response instanceof Array ? response : [response]);
      					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
      						var idx = this.id.substring(this.id.indexOf('_'));					
      						AJS.$("#pndappr" + idx + "-field").val('');
      					});
      					
      					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
      						var idx1 = this.id.substring(this.id.indexOf('_'));
      						AJS.$("#appr" + idx1).text('');
      					});
      					
      					AJS.$.each(list, function(index, rcasubtask) {
      							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
      		            	});												
      					}
      			});
      	    }
        	  
        	  if(AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()!=='---select---'){
        		  AJS.$(".ExternalProduct").show();
	        	  AJS.$.ajax({
	      			url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/cascade-mapping',
	      		    type : 'GET',
	      		    contentType : 'application/json',
	      		    data : { 
	      				    	projectKey: AJS.$('#projectKey').val(),  
	      				    	type : AJS.$('#typesGroup').find('option:selected').text(),
	      				    	grpOptionName :   AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()
	      			    	},		    
	      			success: function(response) {
	      				if(response!=null){
	      				AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
	      					var idx = this.id.substring(this.id.indexOf('_'));	      					
	      					AJS.$("#pndappr" + idx + "-field").val('');
	      				});
	      				
	      				AJS.$("#productGroup").empty();
						var productGroupOption = AJS.$("<option></option>").attr("value", "None").text("---select---").attr("selected", "selected");	
						 AJS.$("#productGroup").append(productGroupOption);
						AJS.$.each(response,function(index,value){ 
							 productGroupOption = AJS.$("<option></option>").attr("value", index).text(value);	
							 AJS.$("#productGroup").append(productGroupOption);
						 });
	      					
	      				}else{
	      					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
		      					var idx = this.id.substring(this.id.indexOf('_'));	      					
		      					AJS.$("#pndappr" + idx + "-field").val('');
		      				});
	      				}
	      			}
	      		});
        	  }else{
        		  
        		  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
    					var idx = this.id.substring(this.id.indexOf('_'));	      					
    					AJS.$("#pndappr" + idx + "-field").val('');
    				});
        		 
        		  AJS.$(".ExternalProduct").hide();
				  AJS.$(".ExternalFourthLevel").hide();
        	  }
          }else{
        	  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
					var idx = this.id.substring(this.id.indexOf('_'));	      					
					AJS.$("#pndappr" + idx + "-field").val('');
				});
        	  AJS.$(".box").hide();
        	  AJS.$(".Internal").hide();
        	  AJS.$(".External").hide();
        	  AJS.$(".ExternalProduct").hide();
        	  AJS.$(".InternalImpacted").hide();
			  AJS.$(".ExternalFourthLevel").hide();
          }
         
          
      });
  }).change();
  
  

  AJS.$('#solutionGroup').change(function(event) {		
	  
	  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));	      					
			AJS.$("#pndappr" + idx + "-field").val('');
		});
		
		AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
	  
	  AJS.$('#s2id_productGroup').find('a span.select2-chosen').text('---select---');
	  
		if((AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()!=='---select---') 
				&& (AJS.$("#solutionGroup").val()!=='None' 
				|| AJS.$('#typesGroup').find('option:selected').val()!=='None') 
				&& (AJS.$('#typesGroup').find('option:selected').text()=='External')){
			console.log("calling for option selection");
			
			AJS.$(".ExternalProduct").show();
			 AJS.$(".ExternalFourthLevel").hide();			
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/cascade-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').text()
				    	},		    
				success: function(response) {
					AJS.$("#productGroup").empty();
					var productGroupOption = AJS.$("<option></option>").attr("value", "None").text("---select---").attr("selected", "selected");
					AJS.$("#productGroup").append(productGroupOption);
					AJS.$.each(response,function(index,value){ 
						 //console.log("key = " + index + " value = " + value);
						 productGroupOption = AJS.$("<option></option>").attr("value", index).text(value);	
						 AJS.$("#productGroup").append(productGroupOption);
					 });
					}
			});
	    }else{
	    	 AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
					var idx = this.id.substring(this.id.indexOf('_'));	      					
					AJS.$("#pndappr" + idx + "-field").val('');
					
				});
	    	
     	  AJS.$(".ExternalProduct").hide();
	    }
		
		if((AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()!=='---select---') 
				&& (AJS.$("#solutionGroup").val()!=='None' 
					|| AJS.$('#typesGroup').find('option:selected').val()!=='None') 
					&& (AJS.$('#typesGroup').find('option:selected').text()=='External') 
					&& (AJS.$('#s2id_productGroup').find('a span.select2-chosen').text()=='---select---')){
	    		    	
	    	AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
					    	cldGrpOptionName :   'None'
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
						
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
	    }
		
		
	});
  
  AJS.$('#productGroup').change(function(event) {		
	  
	  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));	      					
			AJS.$("#pndappr" + idx + "-field").val('');
			
		});
	  AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
		// populate child values to next level drop down
	  AJS.$('#s2id_fourthLevel').find('a span.select2-chosen').text('---select---');
	  if((AJS.$("#solutionGroup").val()!=='None' || AJS.$('#typesGroup').find('option:selected').val()!=='None') 
		&& (AJS.$('#typesGroup').find('option:selected').text()=='External')
		&& (AJS.$('#s2id_solutionGroup').find('a span.select2-chosen').text()!=='---select---') 
		&& (AJS.$('#productGroup').find('option:selected').text()!=='---select---')){
		
		 AJS.$(".ExternalFourthLevel").show();
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/cascade-mapping-thirdlevel',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').text(),
							cldGrpOptionName :   AJS.$('#productGroup').find('option:selected').text()
				    	},		    
				success: function(response) {
				    var length = 0;
					AJS.$("#fourthLevel").empty();
						var fourthLevelOption = AJS.$("<option></option>").attr("value", "None").text("---select---").attr("selected", "selected");	
						 AJS.$("#fourthLevel").append(fourthLevelOption);
						AJS.$.each(response,function(index,value){ 
							 fourthLevelOption = AJS.$("<option></option>").attr("value", index).text(value);	
							 AJS.$("#fourthLevel").append(fourthLevelOption);
							 length = length + 1;
						 });
						 if(length==0){
						  // no child values for the product
						  AJS.$(".ExternalFourthLevel").hide();
						 } else {
						   AJS.$(".ExternalFourthLevel").show();
						 }
						
					}
			});
		} else{
	    	 AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
					var idx = this.id.substring(this.id.indexOf('_'));	      					
					AJS.$("#pndappr" + idx + "-field").val('');
					
				});
	    	
     	  AJS.$(".ExternalFourthLevel").hide();
	    } // end 
		
		//populate rules engine mapping values
		if((AJS.$("#solutionGroup").val()!=='None' || AJS.$('#typesGroup').find('option:selected').val()!=='None') 
		&& (AJS.$('#typesGroup').find('option:selected').text()=='External')
		&& (AJS.$('#productGroup').find('option:selected').text()!=='---select---')){
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/rcasubtask-mapping-thirdlevel',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
					    	cldGrpOptionName :   AJS.$('#productGroup').find('option:selected').val(),
							fourthLevelOptionName :   'None'
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
		}else{
			 AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
					    	cldGrpOptionName :   'None'
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
		}
  });
   AJS.$('#fourthLevel').change(function(event) {		
	  
	  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));	      					
			AJS.$("#pndappr" + idx + "-field").val('');
			
		});
	  AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
	  console.log('fourthLevel change');
	  
		if((AJS.$("#solutionGroup").val()!=='None' || AJS.$('#typesGroup').find('option:selected').val()!=='None') 
		&& (AJS.$('#typesGroup').find('option:selected').text()=='External')
		&& (AJS.$('#productGroup').find('option:selected').text()!=='---select---')
		&& (AJS.$('#fourthLevel').find('option:selected').text()!=='---select---')){
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/rcasubtask-mapping-thirdlevel',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
					    	cldGrpOptionName :   AJS.$('#productGroup').find('option:selected').val(),
							fourthLevelOptionName :   AJS.$('#fourthLevel').find('option:selected').val()
				    	},		    
				success: function(response) {
				if(response == null ){
					console.log('response is null');
				}
				console.log('text: '+AJS.$('#fourthLevel').find('option:selected').text());	  
				console.log('val: '+AJS.$('#fourthLevel').find('option:selected').val());
	  
					
					var list = response == null ? [] : (response instanceof Array ? response : [response]);				
						 
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
		}else{
			 AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/rcasubtask-mapping-thirdlevel',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#solutionGroup').find('option:selected').val(),
					    	cldGrpOptionName :   AJS.$('#productGroup').find('option:selected').val(),
							fourthLevelOptionName :   'None'					    	
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
		}
  });
  AJS.$('#locationGroup').change(function(event) {	
	  
	  AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));	      					
			AJS.$("#pndappr" + idx + "-field").val('');
			
		}); 
		
		AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
	  
	  AJS.$('#s2id_impactedGroup').find('a span.select2-chosen').text('---select---');
	  
		if((AJS.$('#s2id_locationGroup').find('a span.select2-chosen').text()!=='---select---') 
		&& (AJS.$("#locationGroup").val()!=='None' || AJS.$('#typesGroup').find('option:selected').val()!=='None') 
		&& (AJS.$('#typesGroup').find('option:selected').text()=='Internal')){
						
			AJS.$(".InternalImpacted").show();
			
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/cascade-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#locationGroup').find('option:selected').text()
				    	},		    
				success: function(response) {
					
					AJS.$("#impactedGroup").find('option').remove();
					var impactedGroupOption = AJS.$("<option></option>").attr("value", 'None').text('---select---').attr("selected", 'selected');	
					AJS.$("#impactedGroup").append(impactedGroupOption);
					AJS.$.each(response,function(index,value){ 
						 //console.log("key = " + index + " value = " + value);						
						 impactedGroupOption = AJS.$("<option></option>").attr("value", index).text(value);	
						 AJS.$("#impactedGroup").append(impactedGroupOption);
					 });
					
				}
			});			
	  }else{
			 AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
					var idx = this.id.substring(this.id.indexOf('_'));	      					
					AJS.$("#pndappr" + idx + "-field").val('');
					
				});
			 AJS.$(".InternalImpacted").hide();
		}
		
		if((AJS.$('#s2id_locationGroup').find('a span.select2-chosen').text()!=='---select---') 
				&& (AJS.$("#locationGroup").val()!=='None' 
					|| AJS.$('#typesGroup').find('option:selected').val()!=='None') 
					&& (AJS.$('#typesGroup').find('option:selected').text()=='Internal') 
					&& (AJS.$('#s2id_impactedGroup').find('a span.select2-chosen').text()=='---select---')){
	    	
	    	AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#locationGroup').find('option:selected').val(),
					    	cldGrpOptionName :   'None'
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
						
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
	    }
	});
  
  AJS.$('#impactedGroup').change(function(event) {	
	  
	    AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));	      					
			AJS.$("#pndappr" + idx + "-field").val('');
			
		});
		
		AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
			var idx1 = this.id.substring(this.id.indexOf('_'));
			AJS.$("#appr" + idx1).text('');
		});
	  
		if((AJS.$("#locationGroup").val()!=='None' || AJS.$('#typesGroup').find('option:selected').val()!=='None') 
		&& (AJS.$('#typesGroup').find('option:selected').text()=='Internal')
		&& (AJS.$('#impactedGroup').find('option:selected').text()!=='---select---')){
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#locationGroup').find('option:selected').val(),
					    	cldGrpOptionName :   AJS.$('#impactedGroup').find('option:selected').val()
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
						
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);					
		            	});												
					}
			});
		}else{
			AJS.$.ajax({
				url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
			    type : 'GET',
			    contentType : 'application/json',
			    data : { 
					    	projectKey: AJS.$('#projectKey').val(),  
					    	type : AJS.$('#typesGroup').find('option:selected').text(),
					    	grpOptionName :   AJS.$('#locationGroup').find('option:selected').val(),
					    	cldGrpOptionName :   'None'
				    	},		    
				success: function(response) {
					var list = response == null ? [] : (response instanceof Array ? response : [response]);
					AJS.$('[id^=pndappr_rcasubtask_]').each( function(){
						var idx = this.id.substring(this.id.indexOf('_'));					
						AJS.$("#pndappr" + idx + "-field").val('');
						
					});
					
					AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
						var idx1 = this.id.substring(this.id.indexOf('_'));
						AJS.$("#appr" + idx1).text('');
					});
					
					AJS.$.each(list, function(index, rcasubtask) {
							AJS.$("#pndappr_rcasubtask_" + rcasubtask.roles + "-field").val(rcasubtask.users);													
		            	});												
					}
			});
		}
  });
 
  
  AJS.$("#btn_Save").click(function(e) {
	    e.preventDefault();   
	   
	    if(AJS.$('#typesGroup').find('option:selected').val()!='None'){
	    	
	    	if(AJS.$('#typesGroup').find('option:selected').text()=='Internal'){	
				
				if(AJS.$('#locationGroup').find('option:selected').val()!='None'){
					var typeLocationId=AJS.$('#locationGroup').find('option:selected').val();
					var typeLocationVal=AJS.$('#locationGroup').find('option:selected').text();	
					
						if( AJS.$('#impactedGroup').find('option:selected').val()!='None'){
							var typeImpactId=AJS.$('#impactedGroup').find('option:selected').val();
							var typeImpactVal=AJS.$('#impactedGroup').find('option:selected').text();
							saveIncidentConfig(typeLocationId,typeLocationVal,typeImpactId,typeImpactVal,'None','None'); 
						}else{
							
							var typeImpactId=AJS.$('#impactedGroup').find('option:selected').val();
							var typeImpactVal=AJS.$('#impactedGroup').find('option:selected').val();
							saveIncidentConfig(typeLocationId,typeLocationVal,typeImpactId,typeImpactVal,'None','None'); 
						}
						return true;
				}else{
					alert('Please select the Location');
					return false;
				}				 
				
			}else if(AJS.$('#typesGroup').find('option:selected').text()=='External'){
				
				if(AJS.$('#solutionGroup').find('option:selected').val()!='None'){
					var typeSolutionId=AJS.$('#solutionGroup').find('option:selected').val();
					var typeSolutionVal=AJS.$('#solutionGroup').find('option:selected').text();
					var fourthLevelId = "None";
					var fourthLevelVal = "None";
					  if( AJS.$('#productGroup').find('option:selected').text()!=='---select---'){
						  var typeProductId=AJS.$('#productGroup').find('option:selected').val();
						  var typeProductVal=AJS.$('#productGroup').find('option:selected').text();
						   if(AJS.$('#fourthLevel').find('option:selected').text()!=='---select---'){
								fourthLevelId=AJS.$('#fourthLevel').find('option:selected').val();
								fourthLevelVal=AJS.$('#fourthLevel').find('option:selected').text();
						  } 
						  
						  saveIncidentConfig(typeSolutionId,typeSolutionVal,typeProductId,typeProductVal,fourthLevelId,fourthLevelVal);
					  }else{
						  var typeProductId=AJS.$('#productGroup').find('option:selected').val();
						  var typeProductVal=AJS.$('#productGroup').find('option:selected').val();
						  saveIncidentConfig(typeSolutionId,typeSolutionVal,typeProductId,typeProductVal,fourthLevelId,fourthLevelVal);
					  }
					 
					return true;
				}else{
					alert('Please select the Solution Group');
					return false;
				}
				 
			}
		}else{
				alert('Please select the Type value');
				return false;
		}
	    
	  });
  
  
  

function validateTypesGroup(){
	if(AJS.$('#typesGroup').find('option:selected').val()!='None'){
		 	return true;
	}else{
			alert('Please select the Type value');
			return false;
	}
}
  function saveIncidentConfig(optId,optName,cldOptId,cldOptName,fourthLevelId,fourthLevelVal) {
	  		  
	 	  AJS.$.ajax({
		      url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping',
		      type : 'PUT',
		      contentType : 'application/json',
		      processData: false,
		      data: getMapData(optId,optName,cldOptId,cldOptName,fourthLevelId, fourthLevelVal),
		      success: function(response) { 
		    	    	  
		    	  if(response == null){		    		  
		    		  AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
							var idx1 = this.id.substring(this.id.indexOf('_'));
							AJS.$("#appr" + idx1).text('');
						});		    		  
		    		  JIRA.Messages.showSuccessMsg('RCA Report Subtask Assignee Mappings Saved Successfully!');    		  
		    		 	
		    	  }else{
		    		  
		    		  var list = response == null ? [] : (response instanceof Array ? response : [response]);
					  
					  AJS.$('[id^=appr_rcasubtask_error_]').each( function(){
							var idx1 = this.id.substring(this.id.indexOf('_'));
							AJS.$("#appr" + idx1).text('');
						});
						
			    	  AJS.$.each(list, function(index, rcasubtask) {
			    		  if(rcasubtask.users==undefined || rcasubtask.users==''|| rcasubtask.users==null){
			    			  AJS.$("#appr_rcasubtask_error_"+rcasubtask.roles).text("User name should not be empty");
			    		  }else{
			    			  AJS.$("#appr_rcasubtask_error_"+rcasubtask.roles).text("User "+rcasubtask.users+" Does Not Exist");
			    		  }										
		          	  });
		    	  }    	 					
				},
		      error: function(request, status, error) {
					JIRA.Messages.showErrorMsg('RCA Report Subtask Assignee Mappings could not be saved!'+request.responseText);					
				}
		    });
  }
  
  
  function getMapData(optId,optName,cldOptId,cldOptName,fourthLevelId, fourthLevelVal){
		
		var mapdata = new Array();	
		var inc_roles=[];
				
		if(AJS.$('#pndappr_rcasubtask_10800').find('option:selected').text() == AJS.$('#pndappr_rcasubtask_10800-field').val() ){
			pndappr_rcasubtask_10800 = AJS.$('#pndappr_rcasubtask_10800').find('option:selected').val();
		}else if(AJS.$('#pndappr_rcasubtask_10800').find('option:selected').val() == AJS.$('#pndappr_rcasubtask_10800-field').val()){
			pndappr_rcasubtask_10800 = AJS.$('#pndappr_rcasubtask_10800-field').val();
		}else{
			pndappr_rcasubtask_10800 = AJS.$('#pndappr_rcasubtask_10800-field').val();
		}
		
		if(AJS.$('#pndappr_rcasubtask_10801').find('option:selected').text() == AJS.$('#pndappr_rcasubtask_10801-field').val() ){
			pndappr_rcasubtask_10801 = AJS.$('#pndappr_rcasubtask_10801').find('option:selected').val();
		}else if(AJS.$('#pndappr_rcasubtask_10801').find('option:selected').val() == AJS.$('#pndappr_rcasubtask_10801-field').val()){
			pndappr_rcasubtask_10801 = AJS.$('#pndappr_rcasubtask_10801-field').val();
		}else{
			pndappr_rcasubtask_10801 = AJS.$('#pndappr_rcasubtask_10801-field').val();
		}
		
		if(AJS.$('#pndappr_rcasubtask_10802').find('option:selected').text() == AJS.$('#pndappr_rcasubtask_10802-field').val() ){
			pndappr_rcasubtask_10802 = AJS.$('#pndappr_rcasubtask_10802').find('option:selected').val();
		}else if(AJS.$('#pndappr_rcasubtask_10800').find('option:selected').val() == AJS.$('#pndappr_rcasubtask_10802-field').val()){
			pndappr_rcasubtask_10802 = AJS.$('#pndappr_rcasubtask_10802-field').val();
		}else{
			pndappr_rcasubtask_10802 = AJS.$('#pndappr_rcasubtask_10802-field').val();
		}
		
				inc_roles.push({roles:AJS.$('#pndappr_rcasubtask_rpts').val(),  users:pndappr_rcasubtask_10800});
				inc_roles.push({roles:AJS.$('#pndappr_rcasubtask_stim').val(),  users:pndappr_rcasubtask_10801});
				inc_roles.push({roles:AJS.$('#pndappr_rcasubtask_ibam').val(),  users:pndappr_rcasubtask_10802});
				for(j=0;j<inc_roles.length;j++){
					
				var map = {
						projectKey: AJS.$('#projectKey').val(),
						type: AJS.$('#typesGroup').find('option:selected').text(),
						grpOptionId: optId,
						grpOptionName: optName,
						cldGrpOptionId: cldOptId,
						cldGrpOptionName: cldOptName,
						fourthLevelOptionId: fourthLevelId,
						fourthLevelOptionName:	fourthLevelVal,
						roles: inc_roles[j].roles,
						users: inc_roles[j].users
					};
					
					mapdata.push(map);
				}
		
				console.log(JSON.stringify(mapdata));
				return JSON.stringify(mapdata);
			   
	} 
  
  AJS.$("#btn_inccsv").click(function(e) {		         
	    e.preventDefault();		    
	    	 AJS.$.ajax({
			      url : baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/exportrcasubtaskassigneecsv',
			      type : 'GET',
			      data : { 
				    	projectKey: AJS.$('#projectKey').val()
			    	},
			      success: function(response) { 				    	  
			    	  
			    	  window.location.href = baseUrl + '/rest/rcasubtask-approval-mapping/1.0/rcasubtask-mapping/exportrcasubtaskassigneecsv?projectKey='+AJS.$('#projectKey').val();

			        },
			      error: function(request, status, error) {
						JIRA.Messages.showErrorMsg('Jira/Assignee Mapping could not be Export!'+request.responseText);					
				    }
			    });
	    return false;
});
});

AJS.$(function() {
	AJS.$('#typesGroup').auiSelect2();
    AJS.$('#solutionGroup').auiSelect2();
    AJS.$('#locationGroup').auiSelect2();  
    AJS.$('#impactedGroup').auiSelect2();
    AJS.$('#productGroup').auiSelect2();
	AJS.$('#fourthLevel').auiSelect2();
});




