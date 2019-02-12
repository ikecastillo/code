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

    /*var hipChatRoomURL = "";*/

    /**
     * BACKUP PLAN TO GET HIPCHAT ROOM URL
     * */
        /*setTimeout(function() {
            var roomno = AJS.$(".hipchat-dedicated-room a").attr("data-room");
            if (roomno) {
                console.log("There is a hipchat room numbered " + roomno);
                hipChatRoomURL = AJS.$(".hipchat-dedicated-room a").attr("data-api-url")+"/chat/room/" +
                    AJS.$(".hipchat-dedicated-room a").attr("data-room");
                hipChatRoomURL = "<a href="+"\'"+ hipChatRoomURL+"\'"+">Join Technical HipChat Room</a><br/>";
            } else {
                hipChatRoomURL = "No Hipchat room configured for this incident<br/>";
            }

            console.log("HIP CHAT ROOM URL FROM DOM IS " + hipChatRoomURL);
        },1000);*/


   /*  ORIGINAL PLAN TO GET THE HIPCHAT ROOM URL THRU THE REST CALL
   setTimeout(function() {
        var issueKey = AJS.Meta.get("issue-key");
        AJS.log("ISSUE KEY SENT to find the room URL is " + issueKey)
        hipChatRoomURL = getHipChatRoomURL(issueKey);
        console.log("Hipchat room URL is " + hipChatRoomURL);
    },1000);*/


    JIRA.Dialogs.scheduleIssue = new JIRA.FormDialog({
        id: "xMatters-dialog",
        trigger: "a.issueaction-xMatters-issue",
        ajaxOptions: JIRA.Dialogs.getDefaultAjaxOptions,
        onSuccessfulSubmit : function(){
            /*var roomno = AJS.$(".hipchat-dedicated-room a").attr("data-room");*/
            var hipChatServerURL =  "https://cm.hipchat.com";
            var roomno = 0;
            AJS.$.ajax({
                url: "/rest/xmatter-integration/1.0/getroomdetails.json",
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
            console.log("HIP CHAT ROOM NUMBER FROM DOM IS " + roomno);
            console.log("HIPCHAT SERVER URL FROM DOM IS " + hipChatServerURL);

            var ajaxCallCounter = 1;
            var checkedValues = ["",""]; //This will change only for conference bridges as seen below
           if (AJS.$("#radioButtonOne").is(":checked") || AJS.$("#radioButtonTwo").is(":checked")) {
                ajaxCallCounter = AJS.$('.subconfbridge:checked').size();
                checkedValues = AJS.$('.subconfbridge:checked').map(function() {
                    return this.value;
                }).get();
                AJS.log("Checked Values 1" + checkedValues[0]);
                AJS.log("Checked Values 2" + checkedValues[1]); //can be undefined if only 1 conf bridge type is selected
            //}

           //KM:  if (AJS.$("#radioButtonOne").is(":checked") || AJS.$("#radioButtonTwo").is(":checked")) {
                var subconfCounter =  AJS.$('.subconfbridge:checked').size();
                AJS.log("Sub conference bridges selected for type external are " + subconfCounter);
                if (subconfCounter == 0) {
                    JIRA.Messages.showErrorMsg("Please select atleast one of the following options 1) Management " +
                        "or 2)Technical and try again!");
                    ajaxCallCounter = subconfCounter; // Now ajax call counter would be 0 so, no question of making  any REST calls below!
                }
            }

            AJS.log("AJAX CALLS TO BE MADE : " + ajaxCallCounter);
            var firstRowAdded = false;
            if(ajaxCallCounter>=1){
                AJS.log("ajaxCallCounter " + ajaxCallCounter);
                AJS.$.ajax({
                    url: AJS.params.baseURL + "/rest/xmatter-integration/1.0/xMatters",
                    type: 'GET',
                    contentType: "application/json",
                    data: {
                        issueKey: JIRA.Issue.getIssueKey(),
                        confBridge: AJS.$("#radioButtonOne").is(":checked"),
                        nonConfBridge: AJS.$("#radioButtonTwo").is(":checked"),
                        internalConfBridge: AJS.$("#radioButtonThree").is(":checked"),
                        internalNonConfBridge: AJS.$("#radioButtonFour").is(":checked"),
                        restCallCounter: 0,
                        totalRestCalls: ajaxCallCounter,
                        confBridgeType: checkedValues[0],
                        firstRowAdded: false,
                        technicalhipchatroomno: roomno,
                        hipChatServerURL: hipChatServerURL
                    },
                    async: false,
                    success: function (response) {
                        AJS.log("response 1 :" + response);
                        //alert("issueKey: "+ response.issueKey);
                        //alert("issueKey: "+ response.status);
                        if (response.status === "Validation Success") {
                            JIRA.Messages.showSuccessMsg(AJS.I18n.getText("xMatters.success.issue", JIRA.Issue.getIssueKey()));
                            firstRowAdded = true;
                        } else {
                            AJS.log("Showing Error message");
                            JIRA.Messages.showErrorMsg(response.message);
                            AJS.log("Error message shown");
                        }

                    }
                });
            }

            if(ajaxCallCounter===2 && firstRowAdded){
                AJS.log("ajaxCallCounter 2 " + ajaxCallCounter);
                AJS.$.ajax({
                    url: AJS.params.baseURL + "/rest/xmatter-integration/1.0/xMatters",
                    type: 'GET',
                    contentType: "application/json",
                    data: {
                        issueKey: JIRA.Issue.getIssueKey(),
                        confBridge: AJS.$("#radioButtonOne").is(":checked"),
                        nonConfBridge: AJS.$("#radioButtonTwo").is(":checked"),
                        internalConfBridge: AJS.$("#radioButtonThree").is(":checked"),
                        internalNonConfBridge: AJS.$("#radioButtonFour").is(":checked"),
                        restCallCounter: 1,
                        totalRestCalls: ajaxCallCounter,
                        confBridgeType: checkedValues[1],
                        firstRowAdded: true
                    },
                    async: false,
                    success: function (response) {
                        //alert("issueKey: "+ response.issueKey);
                        //alert("issueKey: "+ response.status);
                        AJS.log("response 2 :" + response);
                        if (response.status === "Validation Success") {
                            JIRA.Messages.showSuccessMsg(AJS.I18n.getText("xMatters.success.issue", JIRA.Issue.getIssueKey()));
                        } else {
                            AJS.log("Showing Error message");
                            JIRA.Messages.showErrorMsg(response.message);
                            AJS.log("Error message shown");
                        }
                    }
                });
            }

        },
        onDialogFinished : function(){

        },
        autoClose : true

    });
    // Hides the dialog

});




