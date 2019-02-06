(function ($) {
var objidlist= new Array();
var subjectArr = new Array();
var ownerArr = new Array();
var callFlag;
	// This method excutes on page load of the issue.
	function callCIOnPageLoad(){
		var ci = $.ajax({
				key: "cisubtask",
				url: AJS.params.baseURL+"/rest/createci/1.0/subtask",
				type : 'PUT',
				contentType: "application/json",
				processData: false,
				data : getData(),
				success: function (response){		
					//$('#subtasks-show-all').click();
					var isSubtaskvis = $('#view-subtasks').css("visibility");
					//alert(' objidlist length '+ objidlist.length);
					
					if($.trim(isSubtaskvis)=='' && objidlist.length>0 ){
					JIRA.Messages.showSuccessMsg("Sub-tasks created successfully for the selected CI's. Please refresh the page once.");
					objidlist= new Array();
					subjectArr = new Array();
					ownerArr = new Array();					
					} 
				}								
		
			}); 
	}
	// This method excutes on update of the issue. and also select ci screeen
	function callCIAPI(){
		var ci = $.ajax({
				key: "cisubtask",
				url: AJS.params.baseURL+"/rest/createci/1.0/subtask",
				type : 'PUT',
				contentType: "application/json",
				processData: false,
				data : getData(),
				success: function (response){
					var isSubtaskvis = $('#view-subtasks').css("visibility");					
					if($.trim(isSubtaskvis)==''){
						JIRA.Messages.showSuccessMsg("Sub-tasks created successfully for the selected CI's. Please refresh the page once.");						
					} else {				
					JIRA.Messages.showSuccessMsg("Sub-tasks created successfully for the selected CI's.");
					$('#subtasks-show-all').click();
					objidlist= new Array();
					subjectArr = new Array();
					ownerArr = new Array();					
					}
				}								
		
			}); 
	}
	// This method excutes on update of the issue. and also select ci secreeen
	function createCISubtask(ctx) { 
		 console.log(' createCISubtask');
		var customfieldId = $(".rlabs-customfield .rlabs-customfield-openicon").find("span").attr("data-fieldname-key");	
		console.log("customfield "+customfieldId);
		$("#"+customfieldId+" option:selected", ctx).each(function () {		
			var newdata = $(this).attr('value');			
			newdata = AJS.$.trim(newdata);
				console.log("newdata "+newdata);
			if(newdata!==""){
				objidlist.push(newdata);
				console.log('newdata '+newdata);
				var subject;
				var owner;
				var schema = $.ajax({
				key: "dtschema",
				url: AJS.params.baseURL+"/rest/insight/1.0/object/"+newdata,
				contentType: "application/json",
				//data : {issuekey: $("#key-val").text()},
				//cache: true,
				success:  				
					function (response){
						console.log('response '+JSON.stringify(response));
						$.each(response.attributes,function(){ 						
							 if(this.objectTypeAttribute){								
								console.log("name "+ this.objectTypeAttribute.name);
								 if(this.objectTypeAttribute.name == 'Name'){							 
									 attribValues = this.objectAttributeValues;
									if(attribValues && attribValues.length>0)
									subject = attribValues[0].value;
								}								 
									
								if(this.objectTypeAttribute.name == 'Owner'){			
								 attribValues = this.objectAttributeValues;	
								   if(attribValues && attribValues.length>0){								
										if(attribValues[0].user){
											owner = attribValues[0].user.name;	
										}
									}
								}							
							 }	
							
													 
						 });	
						subjectArr.push(subject);
						ownerArr.push(owner);	
					},
				async:   false				
				});
				}});	
			callCIAPI(); // create-subtask rest api call
	}
	function getData(){
			var mapdata = new Array();	
			$.each(objidlist, function( idx, value ) {						
				//console.log("obj: "+objidlist[idx]+"sub: "+subjectArr[idx]+"owner: "+ownerArr[idx]);
				var map = {
							insightObj: objidlist[idx],
							subject: subjectArr[idx],
							owner: ownerArr[idx],
							issueKey: $("#key-val").text()						
						};
					
				mapdata.push(map);
			});
			//console.log("mapdata :"+mapdata);
			console.log('getData '+JSON.stringify(mapdata));
			return JSON.stringify(mapdata);				
	}
	// method executes on create issue screen 
	function createScreenSubtask(ctx) { 
	       $(".rlabs-customfield-viewdialog-object", ctx).each(function () {
				var newdata = $(this).attr('data-key');	
				console.log("new data updated"+newdata);
			});
		$(".rlabs-customfield-viewdialog-object", ctx).each(function () {	
			var newdata = $(this).attr('data-key');		
			newdata = AJS.$.trim(newdata);
			
			if(newdata!==""){
				objidlist.push(newdata);
				var subject;
				var owner;
				var schema = $.ajax({
				key: "dtschema",
				url: AJS.params.baseURL+"/rest/insight/1.0/object/"+newdata,
				contentType: "application/json",
				//data : {issuekey: $("#key-val").text()},
				//cache: true,
				success:  				
					function (response){				
						$.each(response.attributes,function(){ 						
							 if(this.objectTypeAttribute){
								// alert("name "+ this.objectTypeAttribute.name);
								console.log("name "+ this.objectTypeAttribute.name);
								 if(this.objectTypeAttribute.name == 'Name'){ // Change item name
									 attribValues = this.objectAttributeValues;
									if(attribValues && attribValues.length>0)
									subject = attribValues[0].value;
								}								 
									
								if(this.objectTypeAttribute.name == 'Owner'){	//CI owner		
								 attribValues = this.objectAttributeValues;	
								   if(attribValues && attribValues.length>0){								
										if(attribValues[0].user){
											owner = attribValues[0].user.name;	
										}
									}
								}							
							 }	
							
													 
						 });	
				
						subjectArr.push(subject);
						ownerArr.push(owner);
					},
				async:   false				
				});
				
			}//if 
						
		});	
		callCIOnPageLoad();		
    }
	// Validate whether select ci field is update or not if updated return 0 otherwise 1
	function vUpdate(ctx,datakeyArr){
	console.log("issue sub task created on page load: ");
		var flag  = 0;
		//var datakeyArr = datakeys.split(',');
		//alert(' datakeyArr '+datakeyArr);
		//alert(' datakeyArr length '+datakeyArr.length);
		datakeyArr.sort();
	
		var newdataArr = new Array();
		var customfieldId = $(".rlabs-customfield .rlabs-customfield-openicon").find("span").attr("data-fieldname-key");	
		
		$("#"+customfieldId+" option:selected", ctx).each(function () {
			var newdata = $(this).attr('value');			
			newdata = AJS.$.trim(newdata);			
			newdataArr.push(newdata);
		});
		if(newdataArr.length == 0){
			return 1;
		}
		//alert(' newdataArr '+newdataArr);
		//alert(' newdataArr length '+newdataArr.length);
		newdataArr.sort();
		console.log('datakeyArr '+datakeyArr);
		console.log('newdataArr '+newdataArr);
		if(newdataArr.length > datakeyArr.length){
			return flag;
		} else {
			$.each(newdataArr, function( idx, value ) {
				if(newdataArr[idx] === datakeyArr[idx]){
					flag = 1;
				}
			});
		}
		
	return flag;		
    }
    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) {
		var createUser = AJS.$("#create-issue-submit").val();
		var createAdmin = AJS.$("#issue-create-submit").val();
		var edit = AJS.$("#edit-issue-submit").val();
		var selectCi = AJS.$(context).text();
		console.log("selectCi: "+selectCi);
		console.log("context: "+context);
		console.log("edit value: "+edit);
		console.log("createAdmin value: "+createAdmin);
		console.log("createUser value: "+createUser);
		$("#stqc_show").css("visibility","hidden");
		//$("#create-subtask").css("visibility","hidden");
		var issueType= AJS.$('#type-val').text();		
		issueType = AJS.$.trim(issueType);	
		var datakeyArr = new Array();
		if(issueType == 'Change'){
		
			//$("#create-subtask").hide();
 
			var issuekey = $("#key-val").text();
			var statusval = $("#issuedetails").find("#status-val span").text();
		
			console.log('issuekey: '+issuekey); 	
			console.log('statusval: '+statusval); 
			
			//Create Issue screen
			if(reason == JIRA.CONTENT_ADDED_REASON.pageLoad){				
				if(issuekey!=='' && statusval!==''){
					 if(statusval=='Initiated'){
					    //var localDataKeys = [];
						//var customfieldId = $(".rlabs-customfield .rlabs-customfield-openicon").find("span").attr("data-fieldname-key");	
						$('#customfield-panel-3 .rlabs-customfield-viewdialog-object').each(function (){
							console.log($(this).attr('data-key'));
							datakeyArr.push($(this).attr('data-key'));
						});
						 objidlist= new Array();
						  subjectArr = new Array();
						  ownerArr = new Array();
						  callFlag = true;
						  //createScreenSubtask(context);			
						  createScreenSubtask(context);
					 }
				}
			}		
			// Update issue screen			
			/*AJS.$("#edit-issue-submit").click(function() { // this is conflicted with time line of event code.
				alert('update ci');					
				//createCISubtask(context);
			});*/
			
			
			// Select CI screen
			//if ( (reason!= JIRA.CONTENT_ADDED_REASON.panelRefreshed)) {		
			
			/*if( selectPage == 'Select CI'){	 
				//alert('select ci');
				//var customfldId = $(context).find(".rlabs-objectpicker-openicon span").attr('data-fieldname');
				//alert('select ci'+customfldId);
				
				//var datakeys = $(context).find("#"+customfldId).attr('data-keys');
				//alert('datakeys '+datakeys);
				$(context).find("#issue-workflow-transition-submit").click(function() {
					alert('select ci');
					//var isUpdated = vUpdate(context,datakeys);
					//if( parseInt(isUpdated) == 0)
					//	createCISubtask(context);
				});
			 } else {*/
			// Update issue screen and Select CI Screen
			
			
			
			if(edit == 'Update' || selectCi.contains("Select CI")) {
				$(context).find("form").submit(function( event ) {
					//alert('update ci');
					var isUpdated = vUpdate(context,datakeyArr);						
					if( parseInt(isUpdated) == 0){
						console.log("issue sub task created on page load: " +isUpdated);
						callFlag = false;
						createCISubtask(context);
						}
				 });
			}
				
			//}
		}
		//}
		
    });

})(AJS.$);





