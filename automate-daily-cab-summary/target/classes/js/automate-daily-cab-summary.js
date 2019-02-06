AJS.toInit(function() {
 // alert('auto change js ');
  var baseUrl =AJS.$("#baseURL").val(); 
  
	  function populateForm() {
		//alert('populateForm ');
	    AJS.$.ajax({
	      url:  baseUrl +"/rest/cabsummary/1.0/emailConfigResource/emailConfig",
	      type: "GET",
	      contentType: "application/json",
	      success: function(emailConfig) {
	    	AJS.$("#userName").attr("value", emailConfig.userName);
	    	AJS.$("#password").attr("value", emailConfig.password);
	        AJS.$("#feilds").attr("value", emailConfig.feilds);
			
	      }
	    });
	  }
	  function updateConfig() {
     // alert('update config method');
		  AJS.$.ajax({
		      url:  baseUrl +"/rest/cabsummary/1.0/emailConfigResource/emailConfigSave",
		      type: "PUT",
		      contentType: "application/json",
		      data: '{ "userName": "' + AJS.$("#userName").attr("value") + '","password": "' + AJS.$("#password").attr("value") + '", "feilds": "' + AJS.$("#feilds").attr("value") +'" }',
		      processData: false,
					success: function() {
						JIRA.Messages.showSuccessMsg('ldap Plugin Configuration Saved Successfully!');
					},
					error: function(request, status, error) {
						JIRA.Messages.showErrorMsg(' Configuration Details could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
					}
		    });    
	  }  
	  populateForm();
	  
	 AJS.$("#emailConfigSave").live("click",function(e) {
	    updateConfig();
	  });

	// -------------------ldap end------------------ 
   
    });