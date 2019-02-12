
AJS.toInit(function () {
    console.log("javascrit loaded");
    var projectKey;
    var projectIssueId;
    var list =[];
    var listObject = {};

    AJS.$(document).bind('dialogContentReady', function(event, dialog) {
      projectIssueId = AJS.$("#issueID").val();
     console.log("dialog loaded");
     console.log("issueID :"+projectIssueId);

        projectKey = $('#projectList').val();
        AJS.$("#projectList").auiSelect2({
          placeholder:"Select Project"
        });

        console.log("got projectKey"+projectKey);
        AJS.$('#projectList').change(function(){
            projectKey = $(this).val();
            AJS.$("#info").hide();
            console.log("got projectKey inside change"+projectKey);
        });

         console.log("got projectKey final"+projectKey);

            $( "#aui-submit" ).click(function( e ) {
               if(AJS.$("#projectList").val() != ""){
               AJS.$("#info").hide();
               AJS.$.ajax({
                            url: "/rest/createjiraticket/1.0/jirarelease/create?&issueId="+projectIssueId+"&projectKey="+projectKey,
                            type: "PUT",
                            dataType: "json",
                            success: function(data){
                              console.log('data'+JSON.stringify(data));
                              dialog.hide();
                               if(data.value == 'Success'){
                                window.location.reload();
                                JIRA.Messages.showSuccessMsg(data.issueKey + ' Ticket has been Created Successfully!');
                               }
                               else if(data.value == 'Warning'){
                                 JIRA.Messages.showWarningMsg("Jira Engg. Release Ticket already created");

                               }
                               else {
                                JIRA.Messages.showErrorMsg("Error: While creating ticket" + data.value );
                               }
                            }
               });
               }
               else{
               AJS.$("#info").show();
               }
            });
            /*$( "#aui-close" ).click(function( e ) {
                    dialog.close();
            });*/
    });

});
