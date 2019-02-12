AJS.toInit(function() {
	var baseUrl = AJS.$("#baseURL").val();
    
  function populateForm() {
	 
    AJS.$.ajax({
      url: "/rest/ldapconfigresource/1.0/message/ldapservice",
      type: "GET",
      contentType: "application/json",
      success: function(ldapConfigResourceModel) {
    	AJS.$("#ldapinitctx").attr("value", ldapConfigResourceModel.ldapInitCtx);
    	AJS.$("#ldapservername").attr("value", ldapConfigResourceModel.ldapServerName);
        AJS.$("#ldapbasedn").attr("value", ldapConfigResourceModel.ldapBaseDn);
		AJS.$("#ldapuid").attr("value", ldapConfigResourceModel.ldapUid);
        AJS.$("#ldappwd").attr("value", ldapConfigResourceModel.ldapPwd);
      }
    });
  }
  function updateConfig() {

	  AJS.$.ajax({
	      url: "/rest/ldapconfigresource/1.0/message/ldapsave",
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
  
  AJS.$("#ldapConfigResource").submit(function(e) {
    e.preventDefault();
     updateConfig();
  });
  
  
  AJS.$("#menu_btn_save").click(function(e) {
	    e.preventDefault();
	   
	    AJS.$.ajax({
		      url : baseUrl + '/rest/ldapconfigresource/1.0/menu-mapping',
		      type : 'PUT',
		      contentType : 'application/json',
		      processData: false,
		      data: {
		    	  
		    	    linkName: AJS.$('#menuAppName').val(),
		    	    linkUrl: AJS.$('#menuUrl').val(),
		    	    linkIcon: AJS.$('#menuIcon').val(),
		    	    linkDesc: AJS.$('#menuDesc').val(),
		    	    linkActiveFlag: AJS.$('#menuActiveFlag').val(),
		    	    linkOrder: AJS.$('#menuOrder').val(),
		    	    linkParentOrder: AJS.$('#menuParentOrder').val()
				},
		      success: function(response) { 
		    	    	  
		    	  JIRA.Messages.showSuccessMsg('Menu Mapping Saved Successfully!');   	 
					
				},
		      error: function(request, status, error) {
					JIRA.Messages.showErrorMsg('Menu Mapping could not be saved!'+request.responseText);					
				}
		    });
			
  });
  
  AJS.$("#submenu_btn_save").click(function(e) {
	    e.preventDefault();
	    console.log("call sub menu");
});
  
//  AJS.$('.cv-req-group a').click(function(e) {
//	    e.preventDefault();	
//	  console.log("call sub menu");
//	 });
  
  function readUrlsFromDB(){    	
		console.log("----Calling ReadUrlsFromDB-----"+AJS.$('#cv-req-group-id a').data('identity'));
	}
 
  AJS.$( ".leftMenu a" ).click(function( event ) {
	  event.preventDefault();
	  AJS.$( "<div>" )
	    .append( "default " + event.type  )
	    .appendTo( "#log" );
	});
  
 AJS.$("#btn_portal a").click(function(e) {
	    e.preventDefault();	    
	    console.log("-----calling save----");
	    
//	    AJS.$.ajax({
//		      url : baseUrl + '/rest/ldapconfigresource/1.0/menu-mapping',
//		      type : 'GET',
//		      contentType : 'application/json',
//		      success: function(response) { 		    	    	  
//		    	  	JIRA.Messages.showSuccessMsg('Menu Mapping Saved Successfully!'); 
//				},
//		      error: function(request, status, error) {
//					JIRA.Messages.showErrorMsg('Menu Mapping could not be saved!'+request.responseText);					
//				}
//		    });
});
 
 
});
