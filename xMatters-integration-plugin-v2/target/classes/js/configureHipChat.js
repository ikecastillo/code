/**
 * Created by yagnesh.bhat on 11/5/2015.
 */
var HipChatRoomConfigurator = {

    addHipChatConfig: function () {

        //Get the selected fields from the add hipchat config dialog
        var sollinemgmt = AJS.$("#sollinemgmt option:selected").text();
        var hipchatroomurl = AJS.$("#hipchatroomurl").val().trim();

        if (this.validateHipChatConfig(sollinemgmt)) {
            //Make the AJAX Call here to update the AO,then call getAllXMattersEventConfigs()
            var url = "/rest/xmatter-integration/1.0/mgtHipChatConfig/addMgtHipChatConfig";
            var postData = JSON.stringify({
                "solutionLine" : sollinemgmt,
                "hipChatUrl" : hipchatroomurl
            });
            AJS.$.ajax({
                url: url,
                type: "POST",
                data: postData,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    AJS.$("#hipchatconfigtable tbody tr").remove();
                    AJS.log(" New hipchat config created for solution Line " + sollinemgmt);
                    TableRowAppender.showAllHipChatConfigs(data);
                }
            });
            //TODO not sure why am I not able to i18n it. If I do, its outputting the key for some reason. Anyways ....
            JIRA.Messages.showSuccessMsg("New HipChat room configuration added!");
        }

    },

    removeHipChatConfig: function(sollinemgmt, hipchatroomurl) {

        var url = "/rest/xmatter-integration/1.0/mgtHipChatConfig/deleteMgtHipChatConfig";
        var postData = JSON.stringify({
            "solutionLine" : sollinemgmt,
            "hipChatUrl" : hipchatroomurl
        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$("#hipchatconfigtable tbody tr").remove();
                AJS.log(" Deleted hipchat config with solution line " + sollinemgmt);
                TableRowAppender.showAllHipChatConfigs(data);
            }
        });

        JIRA.Messages.showSuccessMsg("HipChat room configuration deleted!");
    },

    getAllHipChatConfigs:function() {
        AJS.$("#hipchatconfigtable tbody tr").remove();
        var url = "/rest/xmatter-integration/1.0/mgtHipChatConfig/getAllMgtHipChatConfigs";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data) {
                AJS.log("Parsing the response and converting them to table rows");
                TableRowAppender.showAllHipChatConfigs(data);
            }
        });
    },

    validateHipChatConfig:function(sollinemgmt) {

        if (TableRowAppender.isHipChatConfigAlreadyPresent(sollinemgmt)) {
            JIRA.Messages.showErrorMsg("Duplicate Configuration! Hipchat room for solution line " + sollinemgmt + " is already configured ");
            return false;
        } else {
            return true;
        }

    },

    getHipChatInfoSendConfig: function() {
        var url = "/rest/xmatter-integration/1.0/issendhipchatroom/getHipChatChoice";
        AJS.$.ajax({
            url: url,
            dataType: "json",
            type: "POST",
            success: function (data){
                AJS.log("Checking the saved hipchat send selection from AO");
                if (data[0].sendEnabled) {
                    AJS.$("#sendhcr").prop('checked', true);
                } else {
                    AJS.$("#sendnohcr").prop('checked', true);
                }
            }
        });
    },

    setHipChatInfoSendConfig: function() {
        var isSendChecked = AJS.$('#sendhcr').is(':checked');
        var url =  "/rest/xmatter-integration/1.0/issendhipchatroom/setHipChatChoice";
        var postData = JSON.stringify({
            "sendEnabled":isSendChecked
        });
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.log("Setting the saved hipchat send selection to AO");
                if (data[0].sendEnabled) {
                    AJS.$("#sendhcr").prop('checked', true);
                } else {
                    AJS.$("#sendnohcr").prop('checked', true);
                }
            }
        });

        JIRA.Messages.showSuccessMsg("Selection saved!");

    }

};
