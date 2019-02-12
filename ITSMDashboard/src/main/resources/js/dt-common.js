AJS.$.namespace("DT.gadget.fields");
/**
 * GreenHopper project picker
 * 
 * @param projectUserPref the id under which the project is stored
 * @param projectOptions the result of the ajax call that provides the available projects
 */
DTGADGET.projectPicker = function(gadget, projectUserPref, projectOptions)
{
	return {
        userpref: projectUserPref,
        label: gadget.getMsg("gadget.common.project.label"),
        id: "project_picker_" + projectUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		// add a project options box and description. Note that the label has already been added 
            parentDiv.append(
                AJS.$("<select/>").attr({
                    id: projectUserPref,
                    name: projectUserPref
                }).addClass('select')
            );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.project.description")));
            			            
            // now fill the project list with values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            $projectSelectList.empty();

            //append the options to the projectPicker, selecting the currently selected project
            var projectUserPrefValue = gadget.getPref(projectUserPref);
            AJS.$(projectOptions).each(function()
            {
                var projectOption = AJS.$("<option/>").attr("value", this.key).text(this.name);
                $projectSelectList.append(projectOption);
                if (this.key == projectUserPrefValue)
                {
                    projectOption.attr({selected: "selected"});
                    selectedProjectId = this.key;
                }
            });
        }
    };
};

/**
 * Updates the version selection options.
 */
DTGADGET.updateVersionSelectionOptions = function(gadget, selectedProjectId, versionUserPref,includeAllOption )
{
	// fetch and empty the multiselection list
    var versionMultiSelectList = AJS.$("#" + versionUserPref);
    versionMultiSelectList.empty();
    // if (includeAllOption) {
		// var versionOption = AJS.$("<option/>").attr("value", "All").text("All Versions");
		// versionMultiSelectList.append(versionOption);		
	// }
    // fetch the versions for the selected project
    var versions = AJS.$.ajax({

        key: "versions",
        url: "/rest/api/2/project/" + selectedProjectId + "/versions",
		contentType: "application/json",
        // data: {
            // jql : selectedProjectId,
            // returnIds: true
        // },
        success: function (response)
        {
            var selectedVersions = gadget.getPref(versionUserPref).split("|");
			console.log('Selected Versions: ' + selectedVersions.toString());
			versions = response;
			if (includeAllOption) {
				versions.unshift({id:"All", name:"All Versions"});
			}
            AJS.$(versions).each(function()
            {
                var versionOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
                versionMultiSelectList.append(versionOption);
                for(var i = 0, l = selectedVersions.length; i < l; i++) {
                    if(selectedVersions[i] == this.id){
                        versionOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	console.log (versionMultiSelectList);
	  //versionMultiSelectList.chosen();
};


DTGADGET.versionPicker = function(gadget, projectUserPref, versionUserPref, multipleVersions, includeAllOption)
{
	return {
        userpref: versionUserPref,
        label: gadget.getMsg("Version"),
        id: "version_picker_" + versionUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		var selectAttrs = {
                id: versionUserPref,
                name: versionUserPref
            };
        	var className = "select";
        	if (multipleVersions) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.version.description")));
            
            // update the values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.updateVersionSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.updateVersionSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            });
        }
    };
};
/**
 * Updates the sprint selection options.
 */
DTGADGET.updateSprintSelectionOptions = function(gadget, selectedProjectId, sprintUserPref, includeAllOption)
{
	selectedProjectId = 'project=' + selectedProjectId;	
	// fetch and empty the multiselection list
    var sprintMultiSelectList = AJS.$("#" + sprintUserPref);
    sprintMultiSelectList.empty();
    // if (includeAllOption) {
		// var sprintOption = AJS.$("<option/>").attr("value", "All").text("All Sprints");
		// sprintMultiSelectList.append(sprintOption);		
	// }
    
    // fetch the sprints for the selected project
    var sprints = AJS.$.ajax({

        key: "sprints",
        url: "/rest/greenhopper/1.0/integration/teamcalendars/sprint/list",
		contentType: "application/json",
        data: {
            jql : selectedProjectId,
            returnIds: true
        },
        success: function (response)
        {
            var selectedSprints = gadget.getPref(sprintUserPref).split("|");
            sprints = response.sprints;
 			if (includeAllOption) {
				sprints.unshift({id:"All", name:"All Sprints"});
			}
           AJS.$(sprints).each(function()
            {
                var sprintOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
                sprintMultiSelectList.append(sprintOption);
                for(var i = 0, l = selectedSprints.length; i < l; i++) {
                    if(selectedSprints[i] == this.id){
                        sprintOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	  //sprintMultiSelectList.chosen();
};


DTGADGET.sprintPicker = function(gadget, projectUserPref, sprintUserPref, multipleSprints, includeAllOption)
{
	return {
        userpref: sprintUserPref,
        label: gadget.getMsg("Sprint"),
        id: "sprint_picker_" + sprintUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		var selectAttrs = {
                id: sprintUserPref,
                name: sprintUserPref
            };
        	var className = "select";
        	if (multipleSprints) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.sprint.description")));
            
            // update the values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.updateSprintSelectionOptions(gadget, $projectSelectList.val(), sprintUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.updateSprintSelectionOptions(gadget, $projectSelectList.val(), sprintUserPref, includeAllOption);
            });
        }
    };
};

/**
 * Updates the release selection options.(Qmetry gadget)
 */
DTGADGET.updateReleaseSelectionOptions = function(gadget, selectedProjectId, versionUserPref,includeAllOption )
{
	// fetch and empty the multiselection list
    var versionMultiSelectList = AJS.$("#" + versionUserPref);
    versionMultiSelectList.empty();
    // if (includeAllOption) {
		// var versionOption = AJS.$("<option/>").attr("value", "All").text("All Versions");
		// versionMultiSelectList.append(versionOption);		
	// }
    // fetch the versions for the selected project
    var versions = AJS.$.ajax({

        key: "versions",
        url: "/rest/dealertrackqmetry/1.0/testexecutionstatus/getVersions.json?project="+selectedProjectId,
		contentType: "application/json",
        // data: {
            // jql : selectedProjectId,
            // returnIds: true
        // },
        success: function (response)
        {
            var selectedVersions = gadget.getPref(versionUserPref).split("|");
			console.log('Selected Versions: ' + selectedVersions.toString());
			versions = response;
			if (includeAllOption) {
				versions.unshift({id:"All", name:"All Versions"});
			}
            AJS.$(versions).each(function()
            {
                var versionOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
                versionMultiSelectList.append(versionOption);
                for(var i = 0, l = selectedVersions.length; i < l; i++) {
                    if(selectedVersions[i] == this.id){
                        versionOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	console.log (versionMultiSelectList);
	  //versionMultiSelectList.chosen();
};
/**
 * Create the release picker. (Qmetry gadget)
 */
DTGADGET.releasePicker = function(gadget, projectUserPref, versionUserPref, multipleVersions, includeAllOption)
{
	return {
        userpref: versionUserPref,
        label: "Release",
        id: "version_picker_" + versionUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		var selectAttrs = {
                id: versionUserPref,
                name: versionUserPref
            };
        	var className = "select";
        	if (multipleVersions) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.version.description")));
            
            // update the values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.updateReleaseSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.updateReleaseSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            });
        }
    };
};
/**
 * Updates the release selection options.(Qmetry gadget)
 */
DTGADGET.updateIssueTypeSelectionOptions = function(gadget, selectedProjectId, issueTypeUserPref,includeAllOption )
{
	// fetch and empty the multiselection list
    var versionMultiSelectList = AJS.$("#" + issueTypeUserPref);
    versionMultiSelectList.empty();
    // if (includeAllOption) {
		// var versionOption = AJS.$("<option/>").attr("value", "All").text("All Versions");
		// versionMultiSelectList.append(versionOption);		
	// }
    // fetch the versions for the selected project
    var issuetypes = AJS.$.ajax({

        key: "issueTypes",
        url: "/rest/dealertrack/1.0/getIssueTypes.json?projectkey="+selectedProjectId,
		contentType: "application/json",
        // data: {
            // jql : selectedProjectId,
            // returnIds: true
        // },
        success: function (response)
        {
            var selectedVersions = gadget.getPref(issueTypeUserPref).split("|");
			console.log('Selected getIssueTypes: ' + selectedVersions.toString());
			issuetypes = response;
			if (includeAllOption) {
				issuetypes.unshift({label:"All", value:"All Versions"});
			}
            AJS.$(issuetypes).each(function()
            {
                var versionOption = AJS.$("<option/>").attr("value", this.label).text(this.value);
                versionMultiSelectList.append(versionOption);
                for(var i = 0, l = selectedVersions.length; i < l; i++) {
                    if(selectedVersions[i] == this.label){
                        versionOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	console.log (versionMultiSelectList);
	  //versionMultiSelectList.chosen();
};
/**
 * Create the release picker. (Qmetry gadget)
 */
DTGADGET.issueTypesPicker = function(gadget, projectUserPref, issueTypeUserPref, multipleVersions, includeAllOption)
{
	return {
        userpref: issueTypeUserPref,
        label: 'Issue Types:',
        id: "issuetype_picker_" + issueTypeUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		// add a project options box and description. Note that the label has already been added 
			var selectAttrs = {
                id: issueTypeUserPref,
                name: issueTypeUserPref
            };
            var className = "select";
        	if (multipleVersions) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.project.description")));
            			            
            // now fill the project list with values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.updateIssueTypeSelectionOptions(gadget, $projectSelectList.val(), issueTypeUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.updateIssueTypeSelectionOptions(gadget, $projectSelectList.val(), issueTypeUserPref, includeAllOption);
            });
        }
    };
};
/**
 * Create the release picker. (Qmetry gadget)
 */
DTGADGET.populateOuthDetail = function(gadget, uidUserPref,pwdUserPref,outhOptions)
{
	return {
        userpref: uidUserPref,
        label: uidUserPref,
        id: "project_picker_" + uidUserPref,
        type: "hidden",
        value: outhOptions.qmetryUid
    };
	
	
};
/**
* -------------------------------------------------------------------------------
*
* Create Project and Version picker specific to DLR gadgets
*
*--------------------------------------------------------------------------------
*/
/**
 * Modified for ITSM Dashboard gadget Solution Group picker
 * 
 * @param sgUserPref the id under which the solution group is stored
 * @param sgOptions the result of the ajax call that provides the available solution groups
 */
DTGADGET.ITSM_sgPicker = function(gadget, sgUserPref, sgOptions)
{
	return {
        userpref: sgUserPref,
        label: 'Solution Groups',
        id: "sg_picker_" + sgUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		// add a project options box and description. Note that the label has already been added 
          var selectAttrs = {
                id: sgUserPref,
                name: sgUserPref
            };
        	var className = "select";
        	if (true) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.project.description")));
            			            
            // now fill the project list with values
            var $projectSelectList = AJS.$("#" + sgUserPref);
			//alert("html:"+$projectSelectList.html());
            $projectSelectList.empty();

            //append the options to the projectPicker, selecting the currently selected project
            var projectUserPrefValue = gadget.getPref(sgUserPref);
			
			var p = projectUserPrefValue.split("|");
			//alert('p.length '+p.length);
			//for(var j = 0, k = p.length; j < k; j++) {
					AJS.$(sgOptions).each(function()
						{
							var projectOption = AJS.$("<option/>").attr("value", this.value).text(this.value);
							$projectSelectList.append(projectOption);
							//if (this.key == p[j])
							//alert(AJS.$.inArray(this.key,p));
							if(AJS.$.inArray(this.value,p)>-1)
							{
								projectOption.attr({selected: "selected"});
								selectedProjectId = this.value;
							}
						});
			//}
			
            
        }
    };
};
/**
 * Updates the version selection options for DLR.
 */
DTGADGET.DLR_updateVersionSelectionOptions = function(gadget, selectedProjectId, versionUserPref,includeAllOption )
{
	// fetch and empty the multiselection list
    var versionMultiSelectList = AJS.$("#" + versionUserPref);
    versionMultiSelectList.empty();
	var versions;
    // if (includeAllOption) {
		// var versionOption = AJS.$("<option/>").attr("value", "All").text("All Versions");
		// versionMultiSelectList.append(versionOption);		
	// }
    // fetch the versions for the selected project
	//alert('selectedProjectId '+selectedProjectId);
	if(selectedProjectId === null){
		// alert('its null');
		return;
	} else {		
		 if(selectedProjectId.length>1){
		 // alert('its not null');
		 //alert('selectedProjectId '+selectedProjectId.toString());
		 var flag = 0;
			   for(var j = 0, k = selectedProjectId.length; j < k; j++) {
					//alert('proj name: ' + selectedProjectId[j]);
					versions = AJS.$.ajax({

					key: "versions",
					url: "/rest/api/2/project/" + selectedProjectId[j] + "/versions",
					contentType: "application/json",
					// data: {
						// jql : selectedProjectId,
						// returnIds: true
					// },
					success: function (response)
					{
						var selectedVersions = gadget.getPref(versionUserPref).split("|");
						console.log('Selected Versions: ' + selectedVersions.toString());
						versions = response;
						if (flag == 0) {
							versions.unshift({id:"All", name:"All Versions"});
							flag = 1;
						}
						AJS.$(versions).each(function()
						{
							var versionOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
							versionMultiSelectList.append(versionOption);
							for(var i = 0, l = selectedVersions.length; i < l; i++) {
								if(selectedVersions[i] == this.id){
									versionOption.attr("selected", "selected");
								}
							}

						});

					}
					});	
			   }
		} else {
			versions = AJS.$.ajax({

			key: "versions",
			url: "/rest/api/2/project/" + selectedProjectId + "/versions",
			contentType: "application/json",
			// data: {
				// jql : selectedProjectId,
				// returnIds: true
			// },
			success: function (response)
			{
				var selectedVersions = gadget.getPref(versionUserPref).split("|");
				console.log('Selected Versions: ' + selectedVersions.toString());
				versions = response;
				if (includeAllOption) {
					versions.unshift({id:"All", name:"All Versions"});
				}
				AJS.$(versions).each(function()
				{
					var versionOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
					versionMultiSelectList.append(versionOption);
					for(var i = 0, l = selectedVersions.length; i < l; i++) {
						if(selectedVersions[i] == this.id){
							versionOption.attr("selected", "selected");
						}
					}

				});

			}
			});		
		} // else if selectedProjectId.length
	}  // else if selectedProjectId === null
    
	console.log (versionMultiSelectList);
	  //versionMultiSelectList.chosen();
};


DTGADGET.DLR_versionPicker = function(gadget, projectUserPref, versionUserPref, multipleVersions, includeAllOption)
{
	return {
        userpref: versionUserPref,
        label: gadget.getMsg("Version"),
        id: "version_picker_" + versionUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		var selectAttrs = {
                id: versionUserPref,
                name: versionUserPref
            };
        	var className = "select";
        	if (multipleVersions) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.version.description")));
            
            // update the values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.DLR_updateVersionSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.DLR_updateVersionSelectionOptions(gadget, $projectSelectList.val(), versionUserPref, includeAllOption);
            });
        }
    };
};


DTGADGET.DLR_sprintPicker = function(gadget, projectUserPref, sprintUserPref, multipleSprints, includeAllOption)
{
	return {
        userpref: sprintUserPref,
        label: gadget.getMsg("Sprint"),
        id: "sprint_picker_" + sprintUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		var selectAttrs = {
                id: sprintUserPref,
                name: sprintUserPref
            };
        	var className = "select";
        	if (multipleSprints) {
                selectAttrs.multiple = "multiple";
                selectAttrs.size = "4";
                className = "multi-select";
        	}
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.sprint.description")));
            
            // update the values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.DLR_updateSprintSelectionOptions(gadget, $projectSelectList.val(), sprintUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	DTGADGET.DLR_updateSprintSelectionOptions(gadget, $projectSelectList.val(), sprintUserPref, includeAllOption);
            });
        }
    };
};
/**
 * Updates the sprint selection options.
 */
DTGADGET.DLR_updateSprintSelectionOptions = function(gadget, selectedProjectId, sprintUserPref, includeAllOption)
{
	//selectedProjectId = 'project=' + selectedProjectId;
	selectedProjectId = "project in(" + selectedProjectId + ")";
	// fetch and empty the multiselection list
    var sprintMultiSelectList = AJS.$("#" + sprintUserPref);
    sprintMultiSelectList.empty();
    // if (includeAllOption) {
		// var sprintOption = AJS.$("<option/>").attr("value", "All").text("All Sprints");
		// sprintMultiSelectList.append(sprintOption);		
	// }
    
    // fetch the sprints for the selected project
    var sprints = AJS.$.ajax({

        key: "sprints",
        url: "/rest/greenhopper/1.0/integration/teamcalendars/sprint/list",
		contentType: "application/json",
        data: {
            jql : selectedProjectId,
            returnIds: true
        },
        success: function (response)
        {
            var selectedSprints = gadget.getPref(sprintUserPref).split("|");
            sprints = response.sprints;
 			if (includeAllOption) {
				sprints.unshift({id:"All", name:"All Sprints"});
			}
           AJS.$(sprints).each(function()
            {
                var sprintOption = AJS.$("<option/>").attr("value", this.id).text(this.name);
                sprintMultiSelectList.append(sprintOption);
                for(var i = 0, l = selectedSprints.length; i < l; i++) {
                    if(selectedSprints[i] == this.id){
                        sprintOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	  //sprintMultiSelectList.chosen();
};

/**
 * Updates the release single selection options.(Qmetry gadget)
 */
DTGADGET.updateITSMIssueTypeSelectionOptions = function(gadget, selectedProjectId, issueTypeUserPref,includeAllOption )
{
	// fetch and empty the multiselection list
    var versionMultiSelectList = AJS.$("#" + issueTypeUserPref);
  //  alert("versionMultiSelectList"+versionMultiSelectList);
    versionMultiSelectList.empty();
     var issuetypes = AJS.$.ajax({

        key: "issueTypes",
        url: "/rest/incident/1.0/getIssueTypes.json?projectkey="+selectedProjectId,
		contentType: "application/json",
      
        success: function (response)
        {
            var selectedVersions = gadget.getPref(issueTypeUserPref).split("|");
		//	alert('Selected getIssueTypes: ' + selectedVersions.toString());
			issuetypes = response;
			//alert("issuetypes"+issuetypes);
			if (includeAllOption) {
				issuetypes.unshift({label:"All", value:"All Versions"});
			}
            AJS.$(issuetypes).each(function()
            {
                var versionOption = AJS.$("<option/>").attr("value", this.label).text(this.value);
                versionMultiSelectList.append(versionOption);
                for(var i = 0, l = selectedVersions.length; i < l; i++) {
                    if(selectedVersions[i] == this.label){
                        versionOption.attr("selected", "selected");
                    }
                }

            });

        }
    });
	console.log (versionMultiSelectList);
	  //versionMultiSelectList.chosen();
};
/**
 * Updates the release  selection options.(Qmetry gadget)
 */
DTGADGET.updateITSMStatusSelectionOptions = function(gadget, selectProjectId, selectIssueTypeId, statusTypeUserPref,includeAllOption )
{
	var versionMultiSelectList = AJS.$("#" + statusTypeUserPref);
	 versionMultiSelectList.empty();
	 var status = AJS.$.ajax({
	 	key: "status",
        url: "/rest/incident/1.0/getStatusTypes.json?projectkey="+selectProjectId+"&issueType="+selectIssueTypeId,
		contentType: "application/json",
		success: function (response)
        {
        	 var selectedStatus = gadget.getPref(statusTypeUserPref).split("|");
        	 status = response;
        	 AJS.$(status).each(function(index)
            {
			   
            	var versionOption = AJS.$("<option/>").attr("value", this.label).text(this.value);
                versionMultiSelectList.append(versionOption);
				for(var i = 0, l = selectedStatus.length; i < l; i++) {
                    if(selectedStatus[i] == this.label){
                        versionOption.attr("selected", "selected");
                        selectedProjectId = this.value;
                    }
                }				
            });
			
        }
	 });
};
/**
 * Create status picker
 */
DTGADGET.ITSMstatusPicker = function(gadget, projectUserPref, issueTypeUserPref, statusTypeUserPref)
{
	return {
        userpref: statusTypeUserPref,
        label: 'Status:',
        id: "status_picker_" + statusTypeUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
           
        	var selectAttrs = {
                id: statusTypeUserPref,          
                name: statusTypeUserPref
            };
            var className = "multi-select";
        	selectAttrs.multiple = "multiple";
            selectAttrs.size = "4";
            
        	parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
             var $issueSelectList = AJS.$("#" + issueTypeUserPref);
              var $projectSelectList = AJS.$("#" + projectUserPref);
              
              var project = gadget.getPref("projectId");
              var issueTypes = gadget.getPref("issueTypes");
              if(project == "All" || issueTypes == "All"){
            	  DTGADGET.updateITSMStatusSelectionOptions(gadget, $projectSelectList.val(), $issueSelectList.val(), statusTypeUserPref);
              }
              else{
            	  DTGADGET.updateITSMStatusSelectionOptions(gadget, project, issueTypes, statusTypeUserPref);
              }
            $projectSelectList.change(function(event)
            {
            	gadget.savePref("projectId", $projectSelectList.val());
            	DTGADGET.updateITSMStatusSelectionOptions(gadget, $projectSelectList.val(),$issueSelectList.val(), statusTypeUserPref);
            	
            });
            $issueSelectList.change(function(event)
            {
            	 gadget.savePref("issueTypes", $issueSelectList.val());
            	DTGADGET.updateITSMStatusSelectionOptions(gadget, $projectSelectList.val(),$issueSelectList.val(), statusTypeUserPref);
            	
            });
          
          
            AJS.$('#'+statusTypeUserPref).change(function() {
                gadget.savePref("status", AJS.$(this).val());
             }); 
        }
        };
};
/**
 * Create single select issuetype picker. (Qmetry gadget)
 */
DTGADGET.ITSMissueTypesPicker = function(gadget, projectUserPref, issueTypeUserPref, multipleVersions, includeAllOption)
{
	return {
        userpref: issueTypeUserPref,
        label: 'Issue Types:',
        id: "issuetype_picker_" + issueTypeUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
    		// add a project options box and description. Note that the label has already been added 
			var selectAttrs = {
                id: issueTypeUserPref,
                name: issueTypeUserPref
            };
            var className = "select";
        	
            parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
//            parentDiv.append(AJS.$("<div/>").addClass("description").text(gadget.getMsg("gh.gadget.project.description")));
            			            
            // now fill the project list with values
            var $projectSelectList = AJS.$("#" + projectUserPref);
            DTGADGET.updateITSMIssueTypeSelectionOptions(gadget, $projectSelectList.val(), issueTypeUserPref, includeAllOption);
            
            // get informed of changes
            $projectSelectList.change(function(event)
            {
            	gadget.savePref("projectId", $projectSelectList.val());
            	DTGADGET.updateITSMIssueTypeSelectionOptions(gadget, $projectSelectList.val(), issueTypeUserPref, includeAllOption);
            });
        }
    };
};
/**
 * Updates the release  selection options.(Qmetry gadget)
 */
DTGADGET.updateITSMstatusPicker_incident = function(gadget, selectProjectId, selectIssueTypeId, statusTypeUserPref,includeAllOption )
{
	var versionMultiSelectList = AJS.$("#" + statusTypeUserPref);
	 versionMultiSelectList.empty();
	 var status = AJS.$.ajax({
	 	key: "status",
        url: "/rest/incident/1.0/getStatusTypes.json?projectkey="+selectProjectId+"&issueType="+selectIssueTypeId,
		contentType: "application/json",
		success: function (response)
        {
        	 var selectedStatus = gadget.getPref(statusTypeUserPref).split("|");
        	 status = response;
        	 AJS.$(status).each(function(index)
            {
			   
            	var versionOption = AJS.$("<option/>").attr("value", this.label).text(this.value);
                versionMultiSelectList.append(versionOption);
				for(var i = 0, l = selectedStatus.length; i < l; i++) {
                    if(selectedStatus[i] == this.label){
                        versionOption.attr("selected", "selected");
                        selectedProjectId = this.value;
                    }
                }				
            });
			
        }
	 });
};
/**
 * Create status picker for Incident Management related gadgets (Note Project key is hard coded)
 */
DTGADGET.ITSMstatusPicker_incident = function(gadget, project, issueType, statusTypeUserPref)
{
	return {
        userpref: statusTypeUserPref,
        label: 'Status:',
        id: "status_picker_" + statusTypeUserPref,
        type: "callbackBuilder",
        callback: function (parentDiv)
        {
           
        	var selectAttrs = {
                id: statusTypeUserPref,          
                name: statusTypeUserPref
            };
            var className = "multi-select";
        	selectAttrs.multiple = "multiple";
            selectAttrs.size = "4";
            
        	parentDiv.append(
            	AJS.$("<select>").attr(selectAttrs).addClass(className)
             );
           
             
            
              DTGADGET.updateITSMstatusPicker_incident(gadget, project, issueType, statusTypeUserPref);
          
			
        }
        };
};
