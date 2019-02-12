AJS.toInit(function() {
  //alert('service desck js ');
  var baseUrl =AJS.$("#baseURL").val(); 

   
   function populateForm() {
		AJS.$.ajax({
	      url:  baseUrl +"/rest/dealertrack/standardbauchange/1.0/requestApproverResource/approval",
	      type: "GET",
	      contentType: "application/json",
	      success: function(reqApproval) {
	    	AJS.$("#chgUrl").attr("value", reqApproval.chgUrl);
	    	AJS.$("#remoteLinkUrl").attr("value", reqApproval.remoteLinkUrl);
	        AJS.$("#applinkId").attr("value", reqApproval.applinkId);
			AJS.$("#templatelinkURL").attr("value", reqApproval.templatelinkURL);
				AJS.$("#itsmUserName").attr("value", reqApproval.itsmUserName);
					AJS.$("#itsmPassword").attr("value", reqApproval.itsmPassword);
			AJS.$("#ldapSrvrname").attr("value", reqApproval.ldapSrvrname);
			AJS.$("#ldapBasedn").attr("value", reqApproval.ldapBasedn);
			AJS.$("#ldapUid").attr("value", reqApproval.ldapUid);
			AJS.$("#ldapPwd").attr("value", reqApproval.ldapPwd);
			
			
		  }
	    });
	  }
	  function updateConfig() {
          AJS.$.ajax({
		      url:  baseUrl +"/rest/dealertrack/standardbauchange/1.0/requestApproverResource/approverSave",
		      type: "PUT",
		      contentType: "application/json",
		      data: '{ "chgUrl": "' + AJS.$("#chgUrl").attr("value") + '","remoteLinkUrl": "' + AJS.$("#remoteLinkUrl").attr("value") + '", "applinkId": "' + AJS.$("#applinkId").attr("value") + '", "ldapSrvrname": "' + AJS.$("#ldapSrvrname").attr("value") + '", "ldapBasedn": "' + AJS.$("#ldapBasedn").attr("value") + '",  "ldapUid": "' + AJS.$("#ldapUid").attr("value") + '","ldapPwd": "' + AJS.$("#ldapPwd").attr("value") + '","itsmUserName": "' + AJS.$("#itsmUserName").attr("value") + '","itsmPassword": "' + AJS.$("#itsmPassword").attr("value") + '","templatelinkURL": "' + AJS.$("#templatelinkURL").attr("value") + '"}',
		      processData: false,
					success: function() {
						JIRA.Messages.showSuccessMsg(' Configuration Saved Successfully!');
					},
					error: function(request, status, error) {
						JIRA.Messages.showErrorMsg(' Configuration could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
					}
		    });    
	  }  
	  populateForm();
	  AJS.$("#approverSave").live("click",function(e) {
	    updateConfig();
	  });
    
     });
 
