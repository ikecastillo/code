AJS.toInit(function() {
  //var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
    
  function populateForm() {
    AJS.$.ajax({
      url: baseUrl + "/rest/salesforceintegration/1.0/sfconfig",
      dataType: "json",
      success: function(config) {
        AJS.$("#sfintegrationurl").attr("value", config.sfintegrationUrl);
				AJS.$("#sfintegrationuid").attr("value", config.sfintegrationUid);
        AJS.$("#sfintegrationpwd").attr("value", config.sfintegrationPwd);
		AJS.$("#sfclientid").attr("value", config.sfClientId);
		AJS.$("#sfclientsecretkey").attr("value", config.sfClientSecretKey);
		AJS.$("#sfinterval").attr("value", config.sfInterval);
      }
    });
  }
  function updateConfig() {
    AJS.$.ajax({
      url: baseUrl + "/rest/salesforceintegration/1.0/sfconfig",
      type: "PUT",
      contentType: "application/json",
      data: '{ "sfintegrationUrl": "' + AJS.$("#sfintegrationurl").attr("value") + '", "sfintegrationUid": "' + AJS.$("#sfintegrationuid").attr("value") + '", "sfintegrationPwd": "' +  AJS.$("#sfintegrationpwd").attr("value") + '", "sfClientId": "' +  AJS.$("#sfclientid").attr("value") + '","sfClientSecretKey": "' +  AJS.$("#sfclientsecretkey").attr("value") + '","sfInterval": "' +  AJS.$("#sfinterval").attr("value") + '"}',
      processData: false
    });
  }  
  populateForm();

  AJS.$("#admin").submit(function(e) {
    e.preventDefault();
    updateConfig();
		JIRA.Messages.showSuccessMsg('Sales Force Plugin Configuration Saved Successfully!');
  });
});
