AJS.toInit(function() {
  //var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
    
  function populateForm() {
    AJS.$.ajax({
      url: "/rest/xmatter-integration/1.0/xconfig",
      dataType: "json",
      success: function(config) {
        AJS.$("#xmattersouturl").attr("value", config.xmattersOutUrl);
		AJS.$("#xmattersnonouturl").attr("value", config.xmattersNonOutUrl);
		
		AJS.$("#xmattersexternalresponse1").attr("value", config.xmattersExternalResponse1);
		AJS.$("#xmattersexternalresponse2").attr("value", config.xmattersExternalResponse2);

        AJS.$("#xmattersinternalouturl").attr("value", config.xmattersinternalOutUrl);
		AJS.$("#xmattersinternalnonouturl").attr("value", config.xmattersinternalNonOutUrl);

		AJS.$("#xmattersinternalresponse1").attr("value", config.xmattersInternalResponse1);
		AJS.$("#xmattersinternalresponse2").attr("value", config.xmattersInternalResponse2);
		
		AJS.$("#xmattersuid").attr("value", config.xmattersUid);
        AJS.$("#xmatterspwd").attr("value", config.xmattersPwd);
				
		AJS.$("#xmattersinternalserviceurl").attr("value", config.xmattersInternalServiceUrl);
        AJS.$("#xmattersexternalserviceurl").attr("value", config.xmattersExternalServiceUrl);
		
		AJS.$("#xmattersinterval").attr("value", config.xmattersInterval);
		AJS.$("#xmattersjiraemailid").attr("value", config.jiraAdminEmail);
	
      }
    });
  }
  function updateConfig() {
  //alert(" xmattersurl: "+AJS.$("#xmattersurl").attr("value") );
    AJS.$.ajax({
      url: "/rest/xmatter-integration/1.0/xconfig",
      type: "PUT",
      contentType: "application/json",
      data: '{"xmattersOutUrl": "' + AJS.$("#xmattersouturl").attr("value") + '",	  "xmattersNonOutUrl": "' + AJS.$("#xmattersnonouturl").attr("value") + '","jiraAdminEmail": "' + AJS.$("#xmattersjiraemailid").attr("value") + '",	  "xmattersExternalResponse1": "' + AJS.$("#xmattersexternalresponse1").attr("value") + '", 	  "xmattersExternalResponse2": "' + AJS.$("#xmattersexternalresponse2").attr("value") + '",	  "xmattersinternalOutUrl": "' + AJS.$("#xmattersinternalouturl").attr("value") + '","xmattersinternalNonOutUrl": "' + AJS.$("#xmattersinternalnonouturl").attr("value") + '",	  "xmattersInternalResponse1": "' + AJS.$("#xmattersinternalresponse1").attr("value") + '",	  "xmattersInternalResponse2": "' + AJS.$("#xmattersinternalresponse2").attr("value") + '",	  "xmattersInternalServiceUrl": "' + AJS.$("#xmattersinternalserviceurl").attr("value") + '", "xmattersExternalServiceUrl": "' + AJS.$("#xmattersexternalserviceurl").attr("value") + '","xmattersInterval": "' + AJS.$("#xmattersinterval").attr("value") + '", "xmattersUid": "' + AJS.$("#xmattersuid").attr("value") + '","xmattersPwd": "' +  AJS.$("#xmatterspwd").attr("value") + '"}',
      processData: false
    });
  }  
  populateForm();

  AJS.$("#admin").submit(function(e) {
    e.preventDefault();
    updateConfig();
		JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
  });
});
