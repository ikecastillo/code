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
        id: "xMatters-dialog",
        trigger: "a.issueaction-xMatters-issue", 
        ajaxOptions: JIRA.Dialogs.getDefaultAjaxOptions,
        onSuccessfulSubmit : function(){ 
        	        	//alert("is checked: "+ AJS.$("#radioButtonOne").is(":checked"));
						//alert("is checked: "+ AJS.$("#radioButtonTwo").is(":checked"));
			//alert("is checked: "+ AJS.$("#radioButtonTwo").is(":checked"));			
            AJS.$.ajax({
				url: AJS.params.baseURL+"/rest/xmatter-integration/1.0/xMatters",
				type : 'GET',
				contentType: "application/json",
				data : { 
					issueKey:JIRA.Issue.getIssueKey(),
					confBridge: AJS.$("#radioButtonOne").is(":checked"),
					nonConfBridge : AJS.$("#radioButtonTwo").is(":checked"),
					internalConfBridge : AJS.$("#radioButtonThree").is(":checked"),
					internalNonConfBridge : AJS.$("#radioButtonFour").is(":checked")
		    	},
				async: false,
			success: function(response) {
				//alert("issueKey: "+ response.issueKey);
				//alert("issueKey: "+ response.status);
				if(response.status==="Validation Success"){
				JIRA.Messages.showSuccessMsg(AJS.I18n.getText("xMatters.success.issue", JIRA.Issue.getIssueKey()));
				} else {
				JIRA.Messages.showErrorMsg(response.message);
				}
			}				
			});
        },
        onDialogFinished : function(){
        	
        },
        autoClose : true

    });
	// Hides the dialog

});