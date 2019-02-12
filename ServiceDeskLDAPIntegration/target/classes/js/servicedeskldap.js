JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
var createIssueDialog = AJS.$('#create-issue-dialog h2');
var createIssueDialogAdmin = AJS.$('.aui-page-header-main h1');
var createJiraIssueDialog = AJS.$('.jira-dialog-heading h2');
var createIssueAddOnDialog = AJS.$('#content>header h1');
//var issuetypeoption = AJS.$(context).find("#issuetype option:selected").text();
//var issuetypespan = AJS.$('#issue-create-issue-type').text();
//var issuetype= issuetypeoption == '' ? issuetypespan : issuetypeoption;
//issuetype = AJS.$.trim(issuetype);
 var tablist = AJS.$("#horizontal").find("ul li a");
 console.log("tablist: " + tablist.length);	
 AJS.$(tablist).each(function(index,value){
 console.log("tablist: "+AJS.$(this).text());
	var tabName = AJS.$(this).text();
	if(tabName==="Manager/Reporter Details"){
		if(AJS.$(createIssueDialog).text()=='Create Issue'  ||  
		AJS.$(createIssueDialogAdmin).text()=='Create Issue' ||  
		AJS.$(createJiraIssueDialog).text()=='Create Issue' ||  
		AJS.$(createIssueAddOnDialog).text()=='Create Issue'){		
			var versions = AJS.$.ajax({
				key: "servicedeskldapintegration",
				url: AJS.params.baseURL+"/rest/servicedeskldapintegration/1.0/sdldapintegration.json",
				contentType: "application/json",
				success: function (response){						
					AJS.$.each(response,function(index,value){ 
						 console.log('#' + index + " value = " + value);					 
						 AJS.$('#' + index).val(value);
					 });
					
				}			
			});	
		}
		return;
	}	
 });

});