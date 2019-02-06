AJS.toInit(function() {
	var baseUrl =AJS.$("#baseURL").val();


	//get all automation portals 
	populateTableData(null,null);

	//Project change event loads issue types dropdown   and filter automation portals 
	//when None selected then it resets clears issue type dropdown and display all automation portals
	AJS.$('#proj').change(function(event) {
		if(AJS.$("#proj").val()!=='None'){


			populatedSubTaskandIssueTypeOnProject(AJS.$("#proj").val(),'issue','subTaskId',true);


		}else{
			AJS.$("#issue").children().remove();
			AJS.$("#issue").append(new Option("---select---","None"));
			AJS.$("#errMsgId").children().remove();
			populateTableData(null,null);
		}
	});



	//based on project key it will load issue types  and subtasks (using Html ids issueId,subTaskId)
	//if loadtable flag  is true then it populates service desk automation table.
	function  populatedSubTaskandIssueTypeOnProject(projectVal,issueId,subTaskId,loadTable){
		AJS.$.ajax({
			url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/deskautomationprojects/deskissueTypes.json?projectkey='+projectVal,
			type : 'GET',
			contentType : 'application/json',
			data : {

			},
			success: function(response) {
				var items = response == null ? [] : (response['issueType'] instanceof Array ? response['issueType'] : [response['issueType']]);
				AJS.$("#"+issueId).children().remove();
				AJS.$("#"+issueId).append(new Option("---select---","None"));
				AJS.$("#"+issueId).select2("val", "None");
				AJS.$.each(items, function(index, item) {
					AJS.$("#"+issueId).append(new Option(item.value, item.value));

				});


				var subtasks = response == null ? [] : (response['subTask'] instanceof Array ? response['subTask'] : [response['subTask']]);
				AJS.$("#"+subTaskId).children().remove();
				AJS.$("#"+subTaskId).append(new Option("---select---","None"));
				AJS.$("#"+subTaskId).select2("val", "None");
				AJS.$.each(subtasks, function(index, subTask) {
					AJS.$("#"+subTaskId).append(new Option(subTask.value, subTask.value));

				});
				if(loadTable){
					populateTableData(AJS.$('#proj').val(),null);
				}
			}
		});


	}


	function buildTableOnResponse(response){
		if(response !==null   &&  response.length >=1){
			AJS.$.each(response, function(i, item) {
				AJS.$('<tr id='+i+'>').html("<td>"+response[i].Project +"</td><td>"+response[i].issueType +"</td><td>"+response[i].subTask  +"</td><td>" + response[i].userdn + "</td><td>"  + response[i].assignee+"</td><td>" + response[i].group+
					"</td><td>  <input type='button' class='del button spaced' type='submit'  class='button spaced' value='Delete'/><input type='button' class='edit button spaced' type='submit'  value='Edit'/></td></tr>").appendTo('#records_table');
			});
		}else{
			AJS.$('<tr>').html("<td colspan='7' style='text-align:center'>  No records to display  </td></tr>").appendTo('#records_table');
		}
	}


	//populating table data
	function populateTableData(project,issueType){
		var urlToloadTable="";
		if(project!==null && issueType!==null){
			urlToloadTable="/rest/dealertracks/servicedeskrest/1.0/automationportal/portalIssues.json?project="+project+"&issueType="+issueType;
		}
		else if(project!==null && issueType===null){
			urlToloadTable="/rest/dealertracks/servicedeskrest/1.0/automationportal/portals.json?project="+project;
		}
		else if(project===null && issueType===null){
			urlToloadTable="/rest/dealertracks/servicedeskrest/1.0/automationportal/getAllPortals.json";
		}
		if(urlToloadTable!==''){
			//send url and loads response as the table
			AJS.$.ajax({
				url : baseUrl + urlToloadTable,
				type : 'GET',
				contentType : 'application/json',
				data : {

				},
				success: function(response) {
					AJS.$("#records_table tbody tr").remove();
					buildTableOnResponse(response);
				}
			});

		}
	}

	//issue type change event
	AJS.$('#issue').change(function(event) {
		if(AJS.$("#proj").val()!=='None' || AJS.$("#issue").val()!=='None'){
			populateTableData(AJS.$("#proj").val(),AJS.$("#issue").val());
		}
	});

//sub task change event
	AJS.$('#subTaskId').change(function(event) {
		if(AJS.$("#proj").val()!=='None' || AJS.$("#issue").val()!=='None' || AJS.$("#subTaskId").val()!=='None'){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/automationportal/portalIssuesSubTask.json?project='+AJS.$('#proj').val()+'&issueType='+AJS.$('#issue').val()+'&subTask='+AJS.$('#subTaskId').val(),
				type : 'GET',
				contentType : 'application/json',
				data : {

				},
				success: function(response) {
					AJS.$("#records_table tbody tr").remove();
					buildTableOnResponse(response);
				}
			});
		}
	});


	//save button event click
	AJS.$('#btnp_Save').click(function(event) {
		if(AJS.$("#proj").val()!=='None' && AJS.$("#issue").val()!=='None' && AJS.$("#subTaskId").val()!=='None' && AJS.$("#userDn").val()!=='' && AJS.$("#password").val()!=='' && AJS.$("textarea#multiUserPicker").val()!=='' &&  AJS.$("#multiGroups").val()!=='None'){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/automationportal/addPortal.json?project='+AJS.$('#proj').val()+'&issueType='+AJS.$('#issue').val()+'&subTask='+AJS.$('#subTaskId').val()+'&userdn='+AJS.$('#userDn').val()+'&password='+AJS.$('#password').val()+'&assignee='+AJS.$('textarea#multiUserPicker').val()+'&group='+AJS.$('#multiGroups').val(),
				type : 'PUT',
				contentType : 'application/json',
				data : {

				},
				success: function(response) {

					if(response !==null){
						AJS.$("#records_table tbody tr").remove();
						buildTableOnResponse(response);
					}else{
						alert("Issue Type already exists   ");
					}
				}
			});
		}else{
			alert('Please select required values');
		}
	});

	//delete button click
	AJS.$("#records_table tbody tr td .del").live('click', function() {
		if(confirm("Do you want to delete Recored? ")){
			var  htmcol= AJS.$(this).parent().parent().html();
			var res = htmcol.split("</td><td>");
			var proj=res[0].replace("<td>","");
			var selectedProject="";
			if(AJS.$("#proj").val()!=='None'){
				selectedProject=AJS.$("#proj").val();
			}
			var selectedIssue="";
			if(AJS.$("#issue").val()!=='None'){
				selectedIssue=AJS.$("#issue").val();
			}

			if( proj!=='' &&  res[1]!==''){
				AJS.$.ajax({
					url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/automationportal/deletePortal.json?project='+ proj+'&issueType='+ res[1]+'&group='+res[2]+'&selectedProject='+ selectedProject+'&selectedIssue='+selectedIssue,
					type : 'PUT',
					contentType : 'application/json',
					data : {

					},
					success: function(response) {
						AJS.$("#records_table tbody tr").remove();
						buildTableOnResponse(response);
					}
				});
			}
		}
	});

	//test connection button event
	AJS.$("#authenticate").live('click', function() {
		//alert("authenticate click");
		AJS.$("#errMsgId").children().remove();
		if(AJS.$("#userDn").val()!=='' && AJS.$("#password").val()!==''){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ldapAuthentication/validate.json?user='+AJS.$('#userDn').val()+'&password='+AJS.$('#password').val(),
				type : 'GET',
				contentType : 'application/json',
				data : {

				},
				success: function(result) {
					if(result !==null){
						AJS.$("#errMsgId").append("<label style='color:#FF9933;font-weight:bold'>"+result+"</label>")
					}
				}

			});
		}else{
			alert('Please select required values');
		}
	});



	//ldap popups code
	AJS.$('#testUser').live('click', function() {
		AJS.dialog2("#testuser-popup").show();
		AJS.$("#errPopMsgId").children().remove();

	});

	AJS.$("#popup-close-button").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#testuser-popup").hide();
	});



	//popup test user button click event.
	// it needs to display all the error messages while adding user to that group.
	AJS.$('#testuser-btn').live('click', function() {
		//web service call verifys the user has permissions or not.
		//alert("authenticate click");
		AJS.$("#errPopMsgId").children().remove();
		if(AJS.$("#userDn").val()!=='' && AJS.$("#password").val()!=='' && AJS.$("#username").val()!==''){
			AJS.$.ajax({
				url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ldapAuthentication/testPermission.json?user='+AJS.$('#userDn').val()+'&password='+AJS.$('#password').val()+'&username='+AJS.$("#username").val()+'&group='+AJS.$('#multiGroups').val(),
				type : 'GET',
				contentType : 'application/json',
				data : {

				},
				success: function(result) {
					if(result !==null){
						for (var i=0 ; i <result.length ; i++) {
							AJS.$("#errPopMsgId").append("<label style='color:#FF9933;font-weight:bold'>"+result[i]+"</label><br/>")
						}
						
					}
				}

			});
		}else{
			alert('Please select required values');
		}
	});


	/*******************Related to Edit Settings dialog *********************************************************/

	//Close Dialog
	AJS.$("#editsettings-close-button").click(function(e) {
		e.preventDefault();
		AJS.dialog2("#editsettings").hide();
	});

	//Open Edit Settings dialog on click of "Edit" button
	AJS.$("#records_table tbody tr td .edit").live('click', function() {
		AJS.dialog2("#editsettings").show();
		AJS.$('.error').html('');//Clear all error messages
		//populate fields with existing values
		var tableData = [];
		AJS.$(this).closest('tr').find("td").each(function() {
			tableData.push(AJS.$(this).text());
		});
		AJS.log("PROJECT TO EDIT " + tableData[0]);
		AJS.log("ISSUE TYPE TO EDIT " +  tableData[1]);
		AJS.log("SUBTASK TO EDIT " + tableData[2]);
		AJS.log("USER DN TO EDIT " + tableData[3]);
		AJS.log("ASSIGNEE TO EDIT " + tableData[4]);
		AJS.log("GROUP TO EDIT " + tableData[5]);

		//populatedSubTaskandIssueTypeOnProject(tableData[0],"issueEdit","subTaskEdit",false);

		var ldapPassword = "";
		AJS.$.ajax({
			url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/automationportal/portalIssuesSubTask.json?project='
			+tableData[0]+'&issueType='+tableData[1]+'&subTask='+tableData[2],
			type : 'GET',
			contentType : 'application/json',
			data : {

			},
			success: function(response) {
				ldapPassword=response[0].password;
			},
			error:function(response) {
				alert("ERROR RETREIVING PASSWORD, DIALOG WILL CLOSE");
				AJS.dialog2("#editsettings").hide();
			}
		});


		//Pre-populate the fields in the dialog
		AJS.$("#projinEdit").val(tableData[0]);
		AJS.$("#issueEdit").val(tableData[1]);
		AJS.$("#subTaskEdit").val(tableData[2]);
		AJS.$("#userDnEdit").val(tableData[3]);
		AJS.$("#multiUserPickerEdit").val(tableData[4]);
		AJS.$("#multiGroupsEdit").val(tableData[5]);

		//We need to retrieve the password from the AO, this can overlap with the time the dialog loads.
		//So need to give some timeout.
		setTimeout(function() {
			AJS.$("#passwordEdit").val(ldapPassword);
		},200);


	});

	/*AJS.$('#projinEdit').change(function(event) {
		if(AJS.$("#projinEdit").val()!=='None'){
			populatedSubTaskandIssueTypeOnProject(AJS.$("#projinEdit").val(),"issueEdit","subTaskEdit",false);
		}
	});*/

	//Save the respective settings on "Save"
	AJS.$("#editsettings-save-button").click(function(e){
		if (editSettingDialogNotEmpty()) {
			var project = AJS.$("#projinEdit").val();
			var issueType = AJS.$("#issueEdit").val();
			var subtask = AJS.$("#subTaskEdit").val();
			var userDn = AJS.$("#userDnEdit").val();
			var password = AJS.$("#passwordEdit").val();
			var multiuser = AJS.$("#multiUserPickerEdit").val();
			var multigroup = AJS.$("#multiGroupsEdit").val();

			AJS.$.ajax({
				url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/automationportal/updatePortal.json?project='+
				project+'&issueType='+issueType+'&subTask='+subtask+
				'&userdn='+userDn+'&password='+password+'&assignee='+
				multiuser+'&group='+multigroup,
				type : 'PUT',
				contentType : 'application/json',
				data : {

				},
				success: function(response) {

					if(response !==null){
						AJS.$("#records_table tbody tr").remove();
						buildTableOnResponse(response);
					}else{
						alert("Issue Type already exists   ");
					}
				}
			});

			AJS.dialog2("#editsettings").hide();
		}
	});

	/*************************************************************************************************************/

});
AJS.$(function() {
	AJS.$('#proj').auiSelect2();
	AJS.$('#issue').auiSelect2();
	AJS.$('#subTaskId').auiSelect2();
	AJS.$('#approval').auiSelect2();
	//AJS.$('#multiGroups').auiSelect2();
});

function editSettingDialogNotEmpty() {
	var isFieldFilled = true;
	AJS.$('.error').html('');//Clear all error messages
	AJS.$(".mandatorysdf").each(function() {
		if (!AJS.$(this).val()) {
			AJS.log("Found a blank field, cant save");
			AJS.$(this).focus().siblings(".error").html("This field cannot be left blank");
			isFieldFilled = false;
		}
	});

	//Check if group is selected
	var groups = AJS.$("#multiGroupsEdit").val();
	if (groups === "None") {
		AJS.log("Please select some group");
		AJS.$("#multiGroupsEdit").focus().siblings(".error").html("Please select a group");
		isFieldFilled = false;
	}
	return isFieldFilled;

}










