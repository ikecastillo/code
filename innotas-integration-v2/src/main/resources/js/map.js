//Author: Sriram Rajaraman
// This Script is used by map.vm - the main Innotas mapping UI
AJS.toInit(function() {

	AJS.$('[id^=in-project_]').select2({
    placeholder: "Select a Project",
    allowClear: true
	});

	AJS.$('#businessunit').on("change", function () {
		console.log ("BU On Change triggered");
		var bu = AJS.$('#businessunit').find('option:selected').val();
		AJS.$.getJSON('/rest/dealertrack/innotas/1.0/innotas-project?buid='+bu, function(data) {
				AJS.$('[id^=in-project_]').select2("destroy");
				AJS.$('[id^=in-project_] option').remove();
				AJS.$('[id^=in-project_]').append('<option value="" data-sbu="" data-prg="" data-agile=""></option>');
				AJS.$.each(data, function(){
					AJS.$('[id^=in-project_]').append('<option value="'+ this.id +'" data-sbu="' + this.sbuName +'" data-prg="'+ this.program + '"  data-agile="'+ this.agileProject + '" >'+ this.name +'</option>');					
				});
				AJS.$('[id^=in-project_]').select2({
					placeholder: "Select a Project",
					allowClear: true
				}).change();
		});
	})
	
	AJS.$('[id^=in-project_]').on("change", function () {
		var idx = this.id.substring(this.id.indexOf('_'));
		var s = AJS.$('#in-project' + idx).find('option:selected');
		console.log(s.val());
		AJS.$('#in-pId' + idx).text(s.val()); 
		AJS.$('#in-sbu' + idx).text(s.data('sbu')); 
		AJS.$('#in-prg' + idx).text(s.data('prg'));
		AJS.$('#in-agile' + idx).text(s.data('agile'));
		AJS.$.ajax({
			url:"/rest/dealertrack/innotas/1.0/innotas-release",
			dataType: "json",
			async: false,
			data:{innotasProjectId: s.val()},
			success: function(data) {
				AJS.$('#in-release' + idx + ' option').remove();
				AJS.$('#in-release' + idx).append('<option value=""></option>');
				AJS.$.each(data, function(){
					AJS.$('#in-release' + idx).append('<option value="'+ this.releaseId + '" >' + this.releaseName +'</option>');
				});
			}
		});
		
/* 		AJS.$.getJSON('/rest/dealertrack/innotas/1.0/innotas-release?innotasProjectId='+s.val(), function(data) {
			AJS.$('#in-release' + idx + ' option').remove();
			AJS.$('#in-release' + idx).append('<option value="">Select...</option>');
			AJS.$.each(data, function(){
				AJS.$('#in-release' + idx).append('<option value="'+ this.releaseId + '" >' + this.releaseName +'</option>');
			});
		}); */
	});
	// Validate both jira release name and innotas release name are same or not
	function validateInnotasRelease(){
		var flag = 'true';
		var size = AJS.$('[id^=relId_]').size();
		AJS.$('[id^=relId_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));
		
			var agileProj = AJS.$('#in-agile'+idx).text();			
			var jiraRelName = AJS.$('#relName'+idx).val();
			var innRelName= AJS.$('#in-release'+idx).find('option:selected').text();
			
			if((agileProj == 'Yes') || (  agileProj== 'No' && innRelName.length > 0) ){
				if(jiraRelName===innRelName){
				
				} else {
					flag = 'false';
					return false;
				}
			}
			
		});
		return flag;
	};
	
	
	
	function getMapData(){
		var mapdata = new Array();
		AJS.$('[id^=relId_]').each( function(){
			var idx = this.id.substring(this.id.indexOf('_'));
			var map = {
				jiraProjectKey: AJS.$('#projectkey').text(),
				jiraReleaseId: this.value,
				innotasProjectId: AJS.$('#in-project'+idx).val(),
				innotasReleaseId: AJS.$('#in-release'+idx).find('option:selected').val()
			};
			mapdata.push(map);
		});
		console.log(JSON.stringify(mapdata));
		return JSON.stringify(mapdata);
	};
	
	function saveMapping() {
		AJS.$.ajax({
			url: '/rest/dealertrack/innotas/1.0/innotas-mapping',
			type: 'PUT',
			contentType: "application/json",
			processData: false,
			data: getMapData(),
			success: function() {
				JIRA.Messages.showSuccessMsg('Jira/Innotas Mapping Saved Successfully!');
			},
			error: function(request, status, error) {
				JIRA.Messages.showErrorMsg('Jira/Innotas Mapping could not be saved!');
				AJS.$('#error').text(request.responseText);
			}
		});
	};
	
	AJS.$("#mapping").submit(function(e) {
    e.preventDefault();
	//saveMapping();
	if(validateInnotasRelease()==='true')
		saveMapping();
	else {		
		JIRA.Messages.showErrorMsg('Jira Release Name & Innotas Release name must match. Please update the names and try again !');
	}
	//JIRA.Messages.showErrorMsg('Jira Release Name & Innotas Release name must match. Please update the names and try again !');
	    
  });
	
	function populateForm(pkey) {
	AJS.$('#logingImg').css("display","none");
	AJS.$('#logingImg').css("height","16px");
	AJS.$('#logingImg').css("width","16px");
	
		AJS.$.ajax({
			url:"/rest/dealertrack/innotas/1.0/innotas-mapping",
			dataType: "json",
			data:{projectkey: pkey},
			success: function(map) {
				for (var i=0; i<map.length; i++) { 
					console.log(map[i].innotasProjectId);
					AJS.$('[id^=relId_]').each( function(){
						var relId = this.value; 
						console.log(relId,map[i].jiraReleaseId);
						if (relId == map[i].jiraReleaseId){
							var idx = this.id.substring(this.id.indexOf('_'));					
							AJS.$('#in-project' + idx).select2("val",map[i].innotasProjectId).change(); 
							//var sel = '\'#in-release' + idx + ' option[value="' + map[i].innotasReleaseId +'"]\''; 
							//console.log(sel);
							AJS.$('#in-release' + idx + ' option[value="' + map[i].innotasReleaseId +'"]').prop('selected', true);
						}
					});
				}
			}
		});
	};
	
	populateForm(AJS.$('#projectkey').text());
	
	AJS.$('#btn_sync,#btn_sync_top').on("click", function () {
	AJS.$('#btn_sync,#btn_sync_top').prop('disabled', true);	
	var retVal = confirm("Are you sure you want to update the Innotas data?");
		if( retVal == true ){
			AJS.$('#logingImg').css("display","block");
			AJS.$.ajax({
				url:"/rest/dealertrack/innotas/1.0/custom-field-sync",
				dataType: "json",
				data:{projectkey: AJS.$('#projectkey').text()},
				success: function() {
					JIRA.Messages.showSuccessMsg('Innotas Cache updated Successfully!');
					AJS.$('#btn_sync,#btn_sync_top').prop('disabled', false);
					AJS.$('#logingImg').css("display","none");
				},
				error: function(request, status, error) {
					JIRA.Messages.showErrorMsg('Innotas Cache update failed! <br><b>Server returned the following Error:</b> '+ error);					
					AJS.$('#btn_sync,#btn_sync_top').prop('disabled', false);
					AJS.$('#logingImg').css("display","none");
					if(status=='error'){
					 if(request.responseText=='Sync innotas data in progress'){
							JIRA.Messages.showErrorMsg('Innotas Cache update currently is In-Progress. Please wait!');
					 } else {
						JIRA.Messages.showErrorMsg('Innotas Cache update failed! '+error);
						}
					} else if(status=='abort'){
						JIRA.Messages.showErrorMsg('URL not found');
					}

				}			
			});
		} else {
			AJS.$('#btn_sync,#btn_sync_top').prop('disabled', false);
		}
	});
	
	AJS.$('#btn_syncall').on("click", function () {
	
	var retVal = confirm("Are you sure you want to sync all issues with Innotas data?");
		if( retVal == true ){
			AJS.$('#btn_syncall').prop('disabled', true);
			
			AJS.$.ajax({
				url:"/rest/dealertrack/innotas/1.0/syncallissues",
				dataType: "json",
				data:{projectkey: AJS.$('#projectkey').text()},
				success: function() {
					JIRA.Messages.showSuccessMsg('All issues has been sync with innotas data Successfully!');
					AJS.$('#btn_syncall').prop('disabled', false);
				},
				error: function(request, status, error) {
					JIRA.Messages.showErrorMsg('Sync all the issues failed! <br><b>Server returned the following Error:</b><br> ' + request.responseText);
					AJS.$('#btn_syncall').prop('disabled', false);
				}			
			});
		}
	});		
});
function callAjax(jiraRelId,innRelName){
if(innRelName==''){
	JIRA.Messages.showErrorMsg('Innotas Release is empty. It can not be updated on Jira ');
	return;
}		
	AJS.$.ajax({
			url:"/rest/dealertrack/innotas/1.0/sync-jirarelease",
			dataType: "json",
			data:{projectkey: AJS.$('#projectkey').text(),
			jiraReleaseId: jiraRelId,				
			innotasReleaseName: innRelName},
			success: function() {
				JIRA.Messages.showSuccessMsg('Jira Release updated Successfully!');
				location.reload();
			},
			error: function(request, status, error) {
				JIRA.Messages.showErrorMsg('Jira Release not updated Successfully! ' + request.responseText+ '  ');
			}			
		});		
}