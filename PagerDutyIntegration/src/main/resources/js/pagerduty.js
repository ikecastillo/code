AJS.$(function () {
    var getIssueKey = function(){
        if (JIRA.IssueNavigator.isNavigator()){
            return JIRA.IssueNavigator.getSelectedIssueKey();
        } else {
            return AJS.$.trim(AJS.$("#key-val").text());
        }
    };

    var getProjectKey = function(){
        var issueKey = getIssueKey();
        if (issueKey){
            return issueKey.match("[A-Z]*")[0];
        }
    };
	
    JIRA.Dialogs.scheduleIssue = new JIRA.FormDialog({
        id: "pagerduty-dialog",
        trigger: "a.issueaction-pagerduty-issue",
        ajaxOptions: JIRA.Dialogs.getDefaultAjaxOptions,
        onSuccessfulSubmit : function(){

            var hipChatServerURL =  "https://cm.hipchat.com";
            var roomno = 0;
            AJS.$.ajax({
                url: "/rest/pagerduty-integration/1.0/getroomdetails.json",
                type: 'GET',
                contentType: "application/json",
                data: {
                    issuekey: JIRA.Issue.getIssueKey()
                },
                async: false,
                success: function (response) {
                    var te = response;
                    var releaseid =  AJS.$.parseJSON(te.release).id; //Get the room number
                    AJS.log("JSON RESPONSE RELEASE ID " +  releaseid);
                    if (releaseid) {
                        roomno = releaseid;
                    }
                    AJS.log("ROOM NO IS " + roomno);
                },
                error: function() {
                    AJS.log("ERRORED OUT , ROOM NO IS SET TO 0");
                    roomno = 0;
                }
            });


            //Verifying once if I have the right room id and hipchat server URL
            console.log("HIP CHAT ROOM NUMBER RETRIEVED IS " + roomno);
            console.log("HIPCHAT SERVER URL IS " + hipChatServerURL);
            console.log("Issue Key on PD Successful Submit is " + JIRA.Issue.getIssueKey());
            AJS.$.ajax({
                url: "/rest/pagerduty-integration/1.0/pagerDuty",
                type: 'GET',
                contentType: "application/json",
                data: {
                    issueKey: JIRA.Issue.getIssueKey(),
                    technicalhipchatroomno: roomno
                },
                async: false,
                success: function (response) {
                    var pdResponse = response;
					 AJS.log("response : "+ pdResponse.length);
					 if(pdResponse.length > 0){
					    JIRA.Messages.showSuccessMsg(AJS.I18n.getText("pagerduty.success.issue", JIRA.Issue.getIssueKey()));
					 }else {
						 AJS.log("Showing Error message");
                         JIRA.Messages.showErrorMsg(AJS.I18n.getText("pagerduty.noservice.mapping", JIRA.Issue.getIssueKey()));
					 } 
                },
                error: function() {
                    AJS.log("ERRORED OUT , Integration fail on pager duty rest call");                    
                }
            });
        },
        onDialogFinished : function(){

        },
        autoClose : true

    });
    // Hides the dialog

});



