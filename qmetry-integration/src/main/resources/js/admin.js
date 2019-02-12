AJS.toInit(function() {
  //var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
    
  function populateForm() {
    AJS.$.ajax({
      url: baseUrl + "/rest/dealertrackqmetry/1.0/qmetryconfig",
      dataType: "json",
      success: function(config) {
        AJS.$("#qmetryurl").attr("value", config.qmetryUrl);
				AJS.$("#qmetryuid").attr("value", config.qmetryUid);
        AJS.$("#qmetrypwd").attr("value", config.qmetryPwd);
      }
    });
  }
  function updateConfig() {
    AJS.$.ajax({
      url: baseUrl + "/rest/dealertrackqmetry/1.0/qmetryconfig",
      type: "PUT",
      contentType: "application/json",
      data: '{ "qmetryUrl": "' + AJS.$("#qmetryurl").attr("value") + '", "qmetryUid": "' + AJS.$("#qmetryuid").attr("value") + '", "qmetryPwd": "' +  AJS.$("#qmetrypwd").attr("value") + '" }',
      processData: false
    });
  }  
  populateForm();

  AJS.$("#admin").submit(function(e) {
    e.preventDefault();
    updateConfig();
		JIRA.Messages.showSuccessMsg('Qmetry Plugin Configuration Saved Successfully!');
  });
});
