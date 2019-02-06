AJS.toInit(function() {
 // alert('auto change js ');
  var baseUrl =AJS.$("#baseURL").val(); 
  
	  function populateForm() {
		//alert('populateForm ');
	    AJS.$.ajax({
	      url:  baseUrl +"/rest/dealertrack/change/1.0/LDAPConfigResource/ldapDetails",
	      type: "GET",
	      contentType: "application/json",
	      success: function(ldapSrvc) {
	    	//alert('ldapSrvc.ldapUid '+ldapSrvc.ldapUid);
	    	AJS.$("#ldapinitctx").attr("value", ldapSrvc.ldapInitCtx);
	    	AJS.$("#ldapsrvrname").attr("value", ldapSrvc.ldapSrvrName);
	        AJS.$("#ldapbasedn").attr("value", ldapSrvc.ldapBaseDn);
			AJS.$("#ldapuid").attr("value", ldapSrvc.ldapUid);
	        AJS.$("#ldappwd").attr("value", ldapSrvc.ldapPwd);
	      }
	    });
	  }
	  function updateConfig() {
     // alert('update config method');
		  AJS.$.ajax({
		      url:  baseUrl +"/rest/dealertrack/change/1.0/LDAPConfigResource/ldapDetailsave",
		      type: "PUT",
		      contentType: "application/json",
		      data: '{ "ldapInitCtx": "' + AJS.$("#ldapinitctx").attr("value") + '","ldapSrvrName": "' + AJS.$("#ldapsrvrname").attr("value") + '", "ldapBaseDn": "' + AJS.$("#ldapbasedn").attr("value") + '", "ldapUid": "' + AJS.$("#ldapuid").attr("value") + '", "ldapPwd": "' +  AJS.$("#ldappwd").attr("value") + '" }',
		      processData: false,
					success: function() {
						JIRA.Messages.showSuccessMsg('ldap Plugin Configuration Saved Successfully!');
					},
					error: function(request, status, error) {
						JIRA.Messages.showErrorMsg('LDAP Plugin Configuration could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
					}
		    });    
	  }  
	  populateForm();
	  
	 AJS.$("#ldapConfigSave").live("click",function(e) {
	    updateConfig();
	  });

	// -------------------ldap end------------------ 
   
    });