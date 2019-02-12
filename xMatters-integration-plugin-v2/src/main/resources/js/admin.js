AJS.toInit(function() {
  //var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");

   //Get all mappings and add them to the concerned table in the concerned admin page.
   Mapper.getAllMappings();

    //Get all configured events and add them to the concerned table in the concerned admin page
    EventManager.getAllEventConfigs();

    //Get all configured hipchat rooms and add them to the concerned table in the concerned admin page
    HipChatRoomConfigurator.getAllHipChatConfigs();

    //Check from the AO , if the option to configure hipchat rooms is selected or not and check the corresponding radio box
    //HipChatRoomConfigurator.getHipChatInfoSendConfig();

  function populateForm() {
    AJS.$.ajax({
      url: "/rest/xmatter-integration/1.0/xconfig",
      dataType: "json",
      success: function(config) {
		AJS.$("#xmattersuid").attr("value", config.xmattersUid);
        AJS.$("#xmatterspwd").attr("value", config.xmattersPwd);

		AJS.$("#xmattersinterval").attr("value", config.xmattersInterval);
		AJS.$("#xmattersjiraemailid").attr("value", config.jiraAdminEmail);
        AJS.$("#xmattershipchattoken").attr("value", config.xmattersHipchattoken);

      }
    });
  }
  function updateConfig() {
  //alert(" xmattersurl: "+AJS.$("#xmattersurl").attr("value") );
    AJS.$.ajax({
      url: "/rest/xmatter-integration/1.0/xconfig",
      type: "PUT",
      contentType: "application/json",
      data: '{"jiraAdminEmail": "' +
            AJS.$("#xmattersjiraemailid").attr("value") + '","xmattersInterval": "' +
            AJS.$("#xmattersinterval").attr("value") + '", "xmattersUid": "' +
            AJS.$("#xmattersuid").attr("value") + '","xmattersPwd": "' +  AJS.$("#xmatterspwd").attr("value") +
                '","xmattersHipchattoken": "' + AJS.$("#xmattershipchattoken").attr("value") +'"}',
      processData: false
    });
  }
  populateForm();

  AJS.$("#admin2").submit(function(e) {
    e.preventDefault();
    updateConfig();
		JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
  });

  // Shows the dialog when the "Show dialog" button is clicked
  AJS.$("#mapping-button").click(function() {
      AJS.log("Mapping dialog pops up");
      AJS.dialog2("#mapping-dialog").show();
      AJS.$(".error").html(''); //Clear all error messages

      //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
      // Check this function mappingDialogFieldsNotEmpty().
      AJS.$("#jiraField > option:eq(0)").prop('selected',true);

      AJS.$("#xMattersField").val('') //Blank out the input field so new in
      var projectKey = AJS.I18n.getText("xMatters.admin.mappingform.defaultProjectKey");
      var issueType = AJS.I18n.getText("xMatters.admin.mappingform.defaultIssueType");
      AJS.log("Project Key used : " + projectKey);
      AJS.log("Issue Type used : " + issueType);
	  populateJiraCustomFields(projectKey,issueType);
  });

  // Hides the dialog
  AJS.$("#mapping-dialog-cancel-link").click(function(e) {
      e.preventDefault();
      AJS.dialog2("#mapping-dialog").hide();
      AJS.log("Mapping dialog hides");
  });

 AJS.$("#mapping-dialog-add-button").click(function() {
     if (mappingDialogFieldsNotEmpty()) {
         Mapper.addMapping();
         AJS.dialog2("#mapping-dialog").hide();
     }
 });

 AJS.$(".deleteMapping").live('click',function() {
     var rowToDelete = AJS.$(this).closest('tr');
     var tableData = [];
     AJS.$(this).closest('tr').find("td").each(function() {
         tableData.push(AJS.$(this).text());
     });

     //TODO Confirm here that you are getting the right parameters to delete
     AJS.log("Field to delete for JIRA " + tableData[0]);
     AJS.log("Field to delete for xMatters " + tableData[1]);

     AJS.log("Delete Mapping dialog pops up");
     AJS.dialog2("#delete-mapping-dialog").show();
     AJS.$("#jirafieldconf").text(tableData[0]);
     AJS.$("#xmattersfieldconf").text(tableData[1]);

 });

AJS.$("#mapping-dialog-delete-cancel-link").click(function(e) {
    e.preventDefault();
    AJS.dialog2("#delete-mapping-dialog").hide();
    AJS.log("Delete Mapping dialog hides");
});

AJS.$("#mapping-dialog-delete-button").click(function() {
    AJS.log("Making an AJAX call to delete " + AJS.$("#jirafieldconf").text() + " -> " + AJS.$("#xmattersfieldconf").text() );
    Mapper.removeMapping(AJS.$("#jirafieldconf").text(), AJS.$("#xmattersfieldconf").text());
    AJS.dialog2("#delete-mapping-dialog").hide();
});

/*************** For the event config screens ********************************************************************/

// Shows the dialog when the "Show dialog" button is clicked
AJS.$("#event-config-button").click(function() {
    AJS.log("Event dialog pops up");
    AJS.dialog2("#event-dialog").show();
    AJS.$(".error").html(''); //Clear all error messages
    //Clear the form
    AJS.$(".mandatoryevtField").val('');
    AJS.$(".optionalFieldEvt").val('');

});

// Hides the dialog
AJS.$("#event-dialog-cancel-link").click(function(e) {
    e.preventDefault();
    AJS.dialog2("#event-dialog").hide();
    AJS.log("Event dialog hides");
});

AJS.$("#event-dialog-add-button").click(function() {
    if (eventDialogFieldsNotEmpty()) {
        EventManager.addEventConfig();
        AJS.dialog2("#event-dialog").hide();
    }
});


AJS.$(".deleteEvent").live('click',function() {
    var rowToDelete = AJS.$(this).closest('tr');
    var tableData = [];
    AJS.$(this).closest('tr').find("td").each(function() {
        tableData.push(AJS.$(this).text());
    });

    AJS.log("Delete Mapping dialog pops up");
    AJS.dialog2("#delete-event-dialog").show();
    AJS.$("#formapiidconf").text(tableData[0]);
    AJS.$("#xmattersFormconf").text(tableData[1]);
    AJS.$("#xmattersformwsconf").text(tableData[2]);
    AJS.$("#responsecodeavconf").text(tableData[3]);
    AJS.$("#responsecodenotavconf").text(tableData[4]);
});

AJS.$("#event-dialog-delete-cancel-link").click(function(e) {
    e.preventDefault();
    AJS.dialog2("#delete-event-dialog").hide();
    AJS.log("Delete Event dialog hides");
});

AJS.$("#event-dialog-delete-button").click(function() {
    AJS.log("Making an AJAX call to delete xmatters event config with form API ID " + AJS.$("#formapiidconf").text() );
    //Mapper.removeMapping(AJS.$("#jirafieldconf").text(), AJS.$("#xmattersfieldconf").text());
    EventManager.removeEventConfig(AJS.$("#formapiidconf").text(),AJS.$("#xmattersFormconf").text(),AJS.$("#xmattersformwsconf").text(),
        AJS.$("#responsecodeavconf").text(), AJS.$("#responsecodenotavconf").text());

    AJS.dialog2("#delete-event-dialog").hide();
});


/*****************************************************************************************************************/
/********************** For Hipchat Management room configuration screens ***************************************/
// Shows the dialog when the "Show dialog" button is clicked
    AJS.$("#hipchat-config-button").click(function() {
        AJS.log("Hipchat config dialog pops up");
        AJS.dialog2("#hipchat-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages
        //Clear the form
        AJS.$(".mandatoryhcField").val('');
        var projectKey = AJS.I18n.getText("xMatters.admin.mappingform.defaultProjectKey");
        populateSolutionGroups(projectKey);
    });

    // Hides the dialog
    AJS.$("#hipchatconfig-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#hipchat-dialog").hide();
        AJS.log("Hipchat dialog hides");
    });

    //Adds a new entry
    AJS.$("#hipchatconfig-dialog-add-button").click(function() {
        if (hipChatDialogFieldsNotEmpty()) {
            HipChatRoomConfigurator.addHipChatConfig();
            AJS.dialog2("#hipchat-dialog").hide();
        }
    });

    AJS.$(".deleteHCConfig").live('click',function() {
        var rowToDelete = AJS.$(this).closest('tr');
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        AJS.log("Delete Hipchat dialog pops up");
        AJS.dialog2("#delete-hipchat-dialog").show();
        AJS.$("#sollinemgmtconf").text(tableData[0]);
        AJS.$("#hipchatroomurlconf").text(tableData[1]);
    });

    AJS.$("#hipchat-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#delete-hipchat-dialog").hide();
        AJS.log("Delete Hipchat dialog hides");
    });

    AJS.$("#hipchat-dialog-delete-button").click(function() {
        AJS.log("Making an AJAX call to delete hipchat config for severity " + AJS.$("#sollinemgmtconf").text() );
        HipChatRoomConfigurator.removeHipChatConfig(AJS.$("#sollinemgmtconf").text(), AJS.$("#hipchatroomurlconf").text() )
        AJS.dialog2("#delete-hipchat-dialog").hide();
    });


    /*Listener for the "Save Selection" Button */
    /* Commenting out this for now after my discussion with Vikas - we will work on this later on as necessary */
   /* AJS.$("#hipchat-persist-button").click(function() {
        AJS.log("Going ahead to save the hipchat room persistance settings");
        HipChatRoomConfigurator.setHipChatInfoSendConfig();
    });*/

/****************************************************************************************************************/


 // Pre populate all the custom fields from the given project and issue type
function populateJiraCustomFields(projectKey,issueType) {
	AJS.$.ajax({
            url: "/rest/xmatter-integration/1.0/jirafields/"+projectKey+"/"+issueType+".json",
            dataType: "json",
            // data: {
            // projectKey : projectKey,
            // issueType: issueType
            // },
            success: function(data) {
                AJS.log("custom fields->"+data.csvFields); // fields are with comma separated
                var fieldsArray = data.csvFields.split(',');

                var  jiraSelectfield = AJS.$("#jiraField");

                //Remove everything but the --select-- option
                AJS.$('option', jiraSelectfield).not(':eq(0)').remove();
                for(var i=0;i<fieldsArray.length;i++){
                    var value = fieldsArray[i];
                    var jirafiledOption = AJS.$("<option/>").attr("value", value).text(value);
                    jiraSelectfield.append(jirafiledOption);
                }
            }
	});
}

//validates if the mapping dialog fields are not empty
function mappingDialogFieldsNotEmpty() {
    var errorMessagexMatters, errorMessageJIRA;
    AJS.$('.error').html('') //Clear all error messages
    var xMattersField = AJS.$("#xMattersField").val().trim();

    if (AJS.$("#jiraField option:selected").text() == "Select") {
        errorMessageJIRA = AJS.I18n.getText("xmatters.admin.mappingform.dialog.errorjiraField");
    } else if (!xMattersField) {
        errorMessagexMatters = AJS.I18n.getText("xmatters.admin.mappingform.dialog.errorxMattersField");
    }

    if (errorMessagexMatters) {
        AJS.$("#xMattersField").focus().siblings(".error").html(errorMessagexMatters);
        return false;
    } else if (errorMessageJIRA) {
        AJS.$("#jiraField").focus().siblings(".error").html(errorMessageJIRA);
        return false;
    }

    return true;
}
//validates if the event dialog fields are not empty
function eventDialogFieldsNotEmpty() {
    var isFieldFilled = true;
    AJS.$('.error').html('');//Clear all error messages
    AJS.$(".mandatoryevtField").each(function() {
        if (!AJS.$(this).val()) {
            AJS.log("Found a blank field, cant save");
            AJS.$(this).focus().siblings(".error").html("This field cannot be left blank");
            isFieldFilled = false;
        }
    });
    return isFieldFilled;
}

    //validate if the hipchat dialog fields are not empty
    function hipChatDialogFieldsNotEmpty() {
        var isFieldFilled = true;
        AJS.$('.error').html('');//Clear all error messages
        AJS.$(".mandatoryhcField").each(function() {
            if (!AJS.$(this).val()) {
                AJS.log("Found a blank field, cant save");
                AJS.$(this).focus().siblings(".error").html("This field cannot be left blank");
                isFieldFilled = false;
            }
        });
        return isFieldFilled;
    }

    function populateSolutionGroups(projectKey) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/xmatter-integration/1.0/getSolutionGroups",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                projectkey : projectKey
            },
            async:false,
            success: function(data) {
                var  solnlineSelectfield = AJS.$("#sollinemgmt");
                for(var i=0;i<data.length;i++){
                    var value = data[i].value;
                    var solnLineOption = AJS.$("<option/>").attr("value", value).text(value);
                    solnlineSelectfield.append(solnLineOption);
                }
            }
        });
    }

});
