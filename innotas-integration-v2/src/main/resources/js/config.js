AJS.toInit(function() {
  var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
    
  function populateForm() {
    AJS.$.ajax({
      url: baseUrl + "/rest/dealertrack/innotas/1.0/innotasconfig",
      dataType: "json",
      success: function(config) {
        AJS.$("#innotasurl").attr("value", config.innotasUrl);
				AJS.$("#innotasuid").attr("value", config.innotasUid);
        AJS.$("#innotaspwd").attr("value", config.innotasPwd);
      }
    });
  }
  function updateConfig() {
    AJS.$.ajax({
      url: baseUrl + "/rest/dealertrack/innotas/1.0/innotasconfig",
      type: "PUT",
      contentType: "application/json",
      data: '{ "innotasUrl": "' + AJS.$("#innotasurl").attr("value") + '", "innotasUid": "' + AJS.$("#innotasuid").attr("value") + '", "innotasPwd": "' +  AJS.$("#innotaspwd").attr("value") + '" }',
      processData: false,
			success: function() {
				JIRA.Messages.showSuccessMsg('Innotas Plugin Configuration Saved Successfully!');
			},
			error: function(request, status, error) {
				JIRA.Messages.showErrorMsg('Innotas Plugin Configuration could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
			}
    });
  }  
  populateForm();

  AJS.$("#config").submit(function(e) {
    e.preventDefault();
    updateConfig();
  });
});
