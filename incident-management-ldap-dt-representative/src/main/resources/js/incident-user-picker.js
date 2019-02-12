(function ($) {
		
		JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
			setTimeout(function() {
				populateDTRepresentativeDetails();
			}, 50);
		});

	function populateDTRepresentativeDetails() {
		var createIssueDialog = AJS.$('#create-issue-dialog h2');
		var createIssueDialogAdmin = AJS.$('.aui-page-header-main h1');
		var createJiraIssueDialog = AJS.$('.jira-dialog-heading h2');
		var createIssueAddOnDialog = AJS.$('#content>header h1');
		/*var issuetypeoption = AJS.$(context).find("#issuetype option:selected").text();
		var issuetypespan = AJS.$('#issue-create-issue-type').text();*/

		/*var issuetype= issuetypeoption == '' ? issuetypespan : issuetypeoption;*/
		var issuetype = AJS.$('#issuetype-field').val();
		if (!issuetype) {
			issuetype =  AJS.$('#issue-create-issue-type').text();
		}
		issuetype = AJS.$.trim(issuetype);

		AJS.log("Issue Type found after timeout is " + issuetype);
		var dt_rpt_name;
		console.log("-------- createIssueDialog ---------"+AJS.$(createIssueDialog).text());
		console.log("-------- createIssueDialogAdmin ---------"+AJS.$(createIssueDialogAdmin).text());
		console.log("-------- createJiraIssueDialog ---------"+AJS.$(createJiraIssueDialog).text());
		console.log("-------- createIssueAddOnDialog ---------"+AJS.$(createIssueAddOnDialog).text());

		if(AJS.$(createIssueDialog).text()=='Create Issue' || AJS.$(createJiraIssueDialog).text()=='Create Issue'){
			AJS.log("Going ahead to find issue type");
			if(issuetype=='Incident'){
				AJS.log("Detected that the issue type is incident, trying to get dt rep name");
				AJS.$('#tab-1').find('label').each(function () {
					if(AJS.$(this).text()=="DT Representative Name"){
						dt_rpt_name=AJS.$(this).attr('for');
					}
				});

			}
		}
		if(AJS.$(createIssueDialogAdmin).text()=='Create Issue' || AJS.$(createIssueAddOnDialog).text()=='Create Issue'){
			if(issuetype=='Incident'){
				AJS.$('#tab2').find('label').each(function () {
					if(AJS.$(this).text()=="DT Representative Name"){
						dt_rpt_name=AJS.$(this).attr('for');
					}
				});
			}
		}
		console.log("-------- dt_rpt_name ---------"+dt_rpt_name);

		AJS.$('#'+dt_rpt_name).on("change", function () {
			var dt_rpt_name_val = AJS.$('input[type=text]#'+dt_rpt_name).val();
			AJS.$.ajax({
				url : '/rest/incidentmgmtdt/1.0/incident-ldap/readUserDetails',
				type : 'GET',
				contentType : 'application/json',
				data : {userName: dt_rpt_name_val},
				success: function(response) {
					AJS.$.each(response,function(index,value){
						AJS.$('#' + index).val(value);
					});
				}
			});
		});
	}

	
})(AJS.$);