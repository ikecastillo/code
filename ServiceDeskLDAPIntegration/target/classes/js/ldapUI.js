AJS.toInit(function() {
 // var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
    
  function populateForm() {	 
    AJS.$.ajax({
      url: "/rest/servicedeskldapintegration/1.0/sdldapintegration/ldapsrvc",
      type: "GET",
      contentType: "application/json",
      success: function(ldapSrvc) {
    	AJS.$("#ldapinitctx").attr("value", ldapSrvc.ldapInitCtx);
    	AJS.$("#ldapsrvrname").attr("value", ldapSrvc.ldapSrvrName);
        AJS.$("#ldapbasedn").attr("value", ldapSrvc.ldapBaseDn);
		AJS.$("#ldapuid").attr("value", ldapSrvc.ldapUid);
        AJS.$("#ldappwd").attr("value", ldapSrvc.ldapPwd);
      }
    });
  }
  function updateConfig() {

	  AJS.$.ajax({
	      url: "/rest/servicedeskldapintegration/1.0/sdldapintegration/ldapsave",
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
  
  AJS.$("#ldapSrvc").submit(function(e) {
    e.preventDefault();
     updateConfig();
  });
});
