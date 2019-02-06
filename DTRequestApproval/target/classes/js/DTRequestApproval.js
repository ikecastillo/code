AJS.toInit(function() {
  //alert('service desck js ');
  var baseUrl =AJS.$("#baseURL").val(); 

   
   function populateForm() {
		AJS.$.ajax({
	      url:  baseUrl +"/rest/dealertrack/approvalrest/1.0/requestApproverResource/approval",
	      type: "GET",
	      contentType: "application/json",
	      success: function(reqApproval) {
	    	AJS.$("#chgUrl").attr("value", reqApproval.chgUrl);
	    	AJS.$("#remoteLinkUrl").attr("value", reqApproval.remoteLinkUrl);
	        AJS.$("#applinkId").attr("value", reqApproval.applinkId);
		  }
	    });
	  }
	  function updateConfig() {
          AJS.$.ajax({
		      url:  baseUrl +"/rest/dealertrack/approvalrest/1.0/requestApproverResource/approverSave",
		      type: "PUT",
		      contentType: "application/json",
		      data: '{ "chgUrl": "' + AJS.$("#chgUrl").attr("value") + '","remoteLinkUrl": "' + AJS.$("#remoteLinkUrl").attr("value") + '", "applinkId": "' + AJS.$("#applinkId").attr("value") + '"}',
		      processData: false,
					success: function() {
						JIRA.Messages.showSuccessMsg('Request Approver Configuration Saved Successfully!');
					},
					error: function(request, status, error) {
						JIRA.Messages.showErrorMsg('Request Approver Configuration could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
					}
		    });    
	  }  
	  populateForm();
	  AJS.$("#approverSave").live("click",function(e) {
	    updateConfig();
	  });
    
     });