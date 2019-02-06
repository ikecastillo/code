JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e,context) {
var createIssueDialog = AJS.$('#create-issue-dialog h2');
var createIssueDialogAdmin = AJS.$('.aui-page-header-main h1');

if(AJS.$(createIssueDialog).text()=='Create Issue'  ||  AJS.$(createIssueDialogAdmin).text()=='Create Issue' ){
	//alert(AJS.$(createIssueDialogAdmin).text());
	
	var projectAdmin = AJS.$('#issue-create-project-name').text();
	var issueTypeAdmin = AJS.$('#issue-create-issue-type').text();
	issueTypeAdmin = AJS.$.trim(issueTypeAdmin);	
	
	var project=AJS.$('#project').find('option:selected').text();	
	project = AJS.$.trim(project);
	if(project==''){
		project = AJS.$.trim(projectAdmin);
	}
	var issueType=AJS.$('#issuetype').find('option:selected').text();
	issueType = AJS.$.trim(issueType);

	if(issueType=='Release' ||  issueTypeAdmin == 'Release' ){				
		AJS.$("#fixVersions-textarea").focusin(function() {
			
		}).focusout(function() {
		//alert('focusout');
			    var tempversions = new Array();	
				var versions_val="";
				var optgroupitem = AJS.$("#fixVersions optgroup");		 
				AJS.$(optgroupitem).find('option').each(function() {
					var optValue = AJS.$(this).text();
					var optId = AJS.$(this).attr('value');
					var optSelected = AJS.$(this).attr('selected');
					if(optSelected=='selected'){
						versions_val = versions_val+","+optId;
					}
				});
				if(versions_val==""){
				} else {
				   versions_val = versions_val.substring(1,versions_val.length);
				}		
						
				var versions = AJS.$.ajax({
					key: "linkedissues",
					url: AJS.params.baseURL+"/rest/fixversionenhancement/1.0/addLinkedIssues.json?projectkey="+project+"&versions="+versions_val,
					contentType: "application/json",
					// data: {  
						// jql : selectedProjectId,
						// returnIds: true
					// },
					success: function (response)
					{					   
						versions = response;									
						AJS.$(versions).each(function()
						{
						//alert(tempversions.length);
						  if(tempversions.length > 0 ){
								var index = tempversions.indexOf(this.name);
								//alert('index ' + index);
								if(index>-1){
									// do not add
								} else {
									tempversions.push(this.name);									
								}
						  } else {
								tempversions.push(this.name);								
						  }	
						});
						removeLinkedIssues();
						AJS.$.each(tempversions,function(index,value){	
							var  addNewItem = new AJS.MultiSelect.Lozenge({
								label: value,
								title: value,
							container: AJS.$("#issuelinks-issues-multi-select ul")
							}); 
							
						});
						setHeight();				
						setDeploymentInstructions(tempversions);
					} // end sucess				
				});// end ajax	
		}); //focus out	

		
		AJS.$("#create-issue-submit").click(function() {
			//alert('create-issue-submit');
			linkIssuesToRelease();
		});
		AJS.$("#issue-create-submit").click(function() {
			//alert('issue-create-submit');
			linkIssuesToRelease();
		});
	}// if issueType is release
}//if screen is create Issue      
}); // end jira event

function setDeploymentInstructions(issues){
	var tickets='';
	AJS.$.each(issues,function(index,value){	
				tickets = tickets + value + ',';				
	});
	//alert(" tickets"+tickets);
			var dissues = AJS.$.ajax({
					key: "deploymentissues",
					url: AJS.params.baseURL+"/rest/fixversionenhancement/1.0/addDeloplymentInstructions.json?tickets="+tickets,
					contentType: "application/json",
					// data: {  
						// jql : selectedProjectId,
						// returnIds: true
					// },
					success: function (response)
					{					   
						dissues = response;									
						AJS.$(dissues).each(function()
						{
								var  field = '#'+this.cfid;							
								AJS.$(field).val(this.cfvalue);
						});
					} // end sucess				
				});// end ajax	
}

function removeLinkedIssues(){
	var parentDiv = AJS.$("#issuelinks-issues-multi-select");
	var parentUI = AJS.$(parentDiv).find('ul');
	parentUI.empty();
	AJS.$('.aui-field-issuepicker hidden multi-select-select').empty();		
}
function setHeight(){
	var parentDiv = AJS.$("#issuelinks-issues-multi-select span span");
	var spanLength  = 0;
	var totPixels = 0;
	AJS.$(parentDiv).each(function()		{
		var spanText = AJS.$(this).text().length;
		spanLength =  spanLength + spanText;
		totPixels = totPixels + (spanText * 8.375) + 11 + 16
	});
	//alert(" spanLength: "+spanLength);
	//alert(" totPixels: "+totPixels);
	if(totPixels>0){
		//var h = parseInt(spanLength/39) * 23 + 30;
		var noOfLines = parseInt(totPixels/344) + 1;
		//alert(" height "+h);
		//alert(" noOfLines "+noOfLines);
		var height = (noOfLines * 22) + 30;
		var top = (noOfLines * 22) + 4;
		
		var heightVal = 'padding-top: '+top+'px; padding-left: 5px; height: '+height+'px;'
		AJS.$("#issuelinks-issues-textarea").attr('style',heightVal);
	}	
}

function linkIssuesToRelease(){
	
	
			/*var selectObj = AJS.$("#issuelinks-issues");
			var issuelinksOption = AJS.$("<option/>").attr({
								value: spanText,
								title: spanText,
								selected: 'selected'
								}).text(spanText).css("background-image", AJS.params.baseURL+"/images/icons/issuetypes/bug.png");
			selectObj.append(issuelinksOption);
			*/
	 /*var selectFieldRef = new AJS.MultiSelect({
            element: AJS.$("#issuelinks-issues"),
            itemAttrDisplayed: "label",
            errorMessage: AJS.I18n.getText("jira.ajax.autocomplete.multiselect.error"),
            maxInlineResultsDisplayed: 15,
            submitInputVal: true,
            expandAllResults: true
        });
		
		var descriptor = new AJS.ItemDescriptor({value: text, label: text});
		selectFieldRef.addItem(descriptor,false);*/
		
	
	var selectObj = AJS.$("#issuelinks-issues");	
	var parentDiv = AJS.$("#issuelinks-issues-multi-select span span");	
	AJS.$(parentDiv).each(function()		{
		var spanText = AJS.$(this).text();
			  //alert(spanText);
			if(spanText==='More'){		
			} else {	
			var issuelinksOption = AJS.$("<option/>").attr({
								value: spanText,
								title: spanText,
								selected: 'selected'
								}).text(spanText).css("background-image", AJS.params.baseURL+"/images/icons/issuetypes/bug.png");
			selectObj.append(issuelinksOption);
			}
	});
}
