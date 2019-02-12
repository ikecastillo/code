AJS.toInit(function() {
    
  function populateForm() {
	 
    AJS.$.ajax({
      url: "/rest/incidentmgmtdt/1.0/incident-ldap/read",
      type: "GET",
      contentType: "application/json",
      success: function(incidentLdapConfig) {
    	AJS.$("#ldapinitctx").attr("value", incidentLdapConfig.ldapInitCtx);
    	AJS.$("#ldapservername").attr("value", incidentLdapConfig.ldapServerName);
        AJS.$("#ldapbasedn").attr("value", incidentLdapConfig.ldapBaseDn);
		AJS.$("#ldapuid").attr("value", incidentLdapConfig.ldapUid);
        AJS.$("#ldappwd").attr("value", incidentLdapConfig.ldapPwd);
      }
    });
  }
  function updateConfig() {

	  AJS.$.ajax({
	      url: "/rest/incidentmgmtdt/1.0/incident-ldap/save",
	      type: "PUT",
	      contentType: "application/json",
	      data: '{ "ldapInitCtx": "' + AJS.$("#ldapinitctx").attr("value") + '","ldapServerName": "' + AJS.$("#ldapservername").attr("value") + '", "ldapBaseDn": "' + AJS.$("#ldapbasedn").attr("value") + '", "ldapUid": "' + AJS.$("#ldapuid").attr("value") + '", "ldapPwd": "' +  AJS.$("#ldappwd").attr("value") + '" }',
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
  
  AJS.$("#ldapSrvc").submit(function(e) {
    e.preventDefault();
     updateConfig();
  });
});
