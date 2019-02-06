(function ($) {

    		JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
			setTimeout(function() {
			populateReporterDetails();
  		}, 100);
  	});


function populateReporterDetails(){

var createIssueDialog = AJS.$('#create-issue-dialog h2');
var createIssueDialogAdmin = AJS.$('.aui-page-header-main h1');
var createJiraIssueDialog = AJS.$('.jira-dialog-heading h2');
var createIssueAddOnDialog = AJS.$('#content>header h1');
/*var issuetypeoption = AJS.$(context).find("#issuetype option:selected").text();
var issuetypespan = AJS.$('#issue-create-issue-type').text();
 var issuetype= issuetypeoption == '' ? issuetypespan : issuetypeoption; */

var issuetype = AJS.$('#issuetype-field').val();
 	if (!issuetype) {
 		issuetype =  AJS.$('#issue-create-issue-type').text();
 	}
console.log("issue type is: "+issuetype);
issuetype = AJS.$.trim(issuetype);
	if(AJS.$(createIssueDialog).text()=='Create Issue'  ||
	   AJS.$(createIssueDialogAdmin).text()=='Create Issue' ||
	   AJS.$(createJiraIssueDialog).text()=='Create Issue' ||
	   AJS.$(createIssueAddOnDialog).text()=='Create Issue'){
		if(issuetype=='Change'){
			var versions = AJS.$.ajax({
				key: "changemanagement",
				url: AJS.params.baseURL+"/rest/myrestresource/1.0/message.json",
				contentType: "application/json",
				success: function (response){
					AJS.$.each(response,function(index,value){
						 console.log('#' + index + " value = " + value);
						 AJS.$('#' + index).val(value);
					 });

				}
			});
		}
	}

}

})(AJS.$);

