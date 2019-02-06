AJS.toInit(function() {
  //alert('service desck js ');
  var baseUrl =AJS.$("#baseURL").val();
  
   AJS.$('#request-types-table tbody tr').live('mouseover', function() { 
	  //alert('mouseon');
	  AJS.$(this).css('background-color', '#E8E8E8');
  }); 
  AJS.$('#request-types-table tbody tr').live('mouseout', function() { 
	  //alert('mouseout');
	  AJS.$(this).css('background-color', 'white');
  });
  
  
  AJS.$('#request-types-table tbody tr  .tdcls').live('mouseover', function() { 
	  //alert('mouseon');
	  if(AJS.$(this).children('.icons').length=== 0){
	  AJS.$(this).append('<div class="icons"></div>');
	   }
	  AJS.$(this).children('.icons').append('<a style="float:right" class="aui-icon-small aui-iconfont-edit"/>');
	  AJS.$(this).addClass('tdhover'); 
  }); 
  AJS.$('#request-types-table tbody tr .tdcls').live('mouseout', function() { 
	  //alert('mouseout');
	  AJS.$(this).children('.icons').remove();
	  AJS.$(this).removeClass('tdhover');
  });
  
  AJS.$('.request-type-icon').live('click', function() { 
	    AJS.dialog2("#icons-popup").show();
  });
  
  AJS.$('.request-type-icon-add').live('click', function(e) { 
  //alert('add exceute');
       AJS.dialog2("#icons-add-popup").show();
	});
	
	
	
  
  
  
  // Hides the dialog
  AJS.$("#dialog-close-button").click(function(e) {
    e.preventDefault();
    AJS.dialog2("#group-popup").hide();
  });

   AJS.$("#icons-add-close-button").click(function(e) { 
     e.preventDefault();
     AJS.dialog2("#icons-add-popup").hide();
	});
	
	AJS.$("#icons-close-button").click(function(e) { 
     e.preventDefault();
     AJS.dialog2("#icons-popup").hide();
	});
 
  AJS.$('.vp-icons-chooser > .vp-rq-icon').live('click', function() { 
	  //pop up all images.
	  AJS.$('#selIconHidden').val(AJS.$(this).attr('class'));
	  AJS.$(this).addClass('selected');
	});
	
	AJS.$('.vp-icons-chooser-add > .vp-rq-icon').live('click', function() { 
	  //pop up all images.
	  AJS.$('#selAddHidden').val(AJS.$(this).attr('class'));
	  AJS.$(this).addClass('selected');
	});
  
  AJS.$('#addGroup').live('click', function(e) { 
	  //pop up all images.
	  //alert('add new group '+AJS.$('#addNewGroup').val());
	  var newGrpVal=AJS.$('#addNewGroup').val();
	  //alert('newGrpVal'+newGrpVal);
	  if(newGrpVal!==null && validateString(newGrpVal)){
	  AJS.$("#groups").append('<option value="'+newGrpVal+'"   title="'+newGrpVal+'">' + newGrpVal+ '</option>');
	  AJS.$('#groups').val(newGrpVal);
	  AJS.$('#groups').auiSelect2();
	  //alert('executed');
	  }else{
	  //alert('enter value to add new group ');
	  }
	  e.preventDefault();
    AJS.dialog2("#group-popup").hide();
  });
  
  AJS.$('.selectIcon').live('click', function(e) { 
   //alert(' hidden value edit ');
	var v=AJS.$('#selIconHidden').val();
	AJS.$('.aui-restfultable-focused td:first-child').find('div').removeClass();
	AJS.$('.aui-restfultable-focused td:first-child').find('div').addClass(v);
	AJS.$('.aui-restfultable-focused td:first-child').find('div').removeClass('selected');
	AJS.$('.aui-restfultable-focused td:first-child').find('input:hidden').val(v);
	e.preventDefault();
    AJS.dialog2("#icons-popup").hide();
   });
   
   
   AJS.$('.selectIconadd').live('click', function(e) { 
    //alert('hidden value add ');
	var a=AJS.$('#selAddHidden').val();
	//alert(' hidden value add v'+a);
	AJS.$('.request-type-icon-add').find('div').removeClass();
	AJS.$('.request-type-icon-add').find('div').addClass(a);
	AJS.$('.request-type-icon-add').find('div').removeClass('selected');
	AJS.$('.request-type-icon-add').find('input:hidden').val(a);
	e.preventDefault();
    AJS.dialog2("#icons-add-popup").hide();
   });
  
  
   
  
   AJS.$('.addNewGrpBtn').live('click', function() { 
   //alert("add group button clicked");
	 AJS.dialog2("#group-popup").show();
	  
  });
  
  
  
  
   AJS.$.ajax({
			url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/getServiceDeskFields.json',
		    type : 'GET',
		    contentType : 'application/json',
		    data : { 
		    	 	
			    	},		    
			success: function(response) {
				//alert('response  '+response);
				var uniques = [];
				AJS.$("#request-types-table tbody tr").remove();
				AJS.$.each(response, function(i, items) {
					//alert('groups :  '+items.icon);
					AJS.$('<tr id="'+items.serviceID+'" class="aui-restfultable-editrow aui-restfultable-row "></tr>').html('<td class="tdcls request-type-icon"><span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+items.icon +'"><div aria-hidden="true" class="'+items.icon +'"></div></span></td><td class="tdcls"><div class="tdwrapcls" title="'+items.item +'">'+items.item +'</div><div class="icons"></div></td><td class="tdcls"><div class="tdwrapcls" title="'+items.groups +'">'+items.groups +'</div></td><td class="tdcls"><div class="tdurlWrapcls" title="'+items.url +'">'+items.url +'</td><td class="tdcls">'+items.servicedesks +'</div></td><td colspan="2"><input type="hidden" value="'+items.serviceID+'"/><a  href="javascript:void(0);" class="delBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Delete</a></td>').appendTo('#request-types-table');
					var itemgrp=items.groups;
					//alert(itemgrp.indexOf(",")+'itemgrp'+itemgrp);
					if(itemgrp.indexOf(",") >= 0){
					//alert('if');
					var itemGrpList=itemgrp.split(",");
					//alert('itemGrpList '+itemGrpList);
					AJS.$.each(itemGrpList, function(index, itm) {
					//alert('itm '+itm);
					if(AJS.$.inArray(itm, uniques) === -1 ) uniques.push(itm);
					});	
					}else{
					if(AJS.$.inArray(items.groups, uniques) === -1 ) uniques.push(itemgrp);
					}
				});	
				//alert('uniques   : '+uniques);
				AJS.$.each(uniques, function(g, group) {
				if(group.indexOf(",") === -1){
					AJS.$('#groups').append('<option value="'+group+'"  title="'+group+'">'+group+'</option>  ');
					}
				}); 
			}
		});	
	 
	 AJS.$("#fileUpload").on('change', function () {
		   //alert('imgPath '+imgPath);
		    var imgPath = AJS.$(this)[0].value;
		    //alert('imgPath '+imgPath);
		    var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
		    //alert('extn '+extn);
		    if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
		        if (typeof (FileReader) != "undefined") {

		            var image_holder = AJS.$("#image-holder");
		            image_holder.empty();

		            var reader = new FileReader();
		            reader.onload = function (e) {
		            	//alert('result: '+e.target.result);
		            	AJS.$('<img></img>', {
		                        "src": e.target.result,
		                        "class": "thumb-image",
		                        "style": "width:85px;height:80px",
		                       
		                }).appendTo(image_holder);
		           //AJS.$("#imgbtn").css({'background-image-url',e.target.result},{'style', 'width:85px;height:80px'});
		            }
		            
		            
		            image_holder.show();
		            reader.readAsDataURL(AJS.$(this)[0].files[0]);
		        } else {
		            //alert("This browser does not support FileReader.");
		        }
		    } else {
		        //alert("Pls select only images");
		    }
		});
	 
	 AJS.$("#request-types-table tbody tr  .tdcls").live('click', function() {
		  var rowToDelete = AJS.$(this).closest('tr');
		  
		  var selected = rowToDelete.hasClass('aui-restfultable-focused');
			    AJS.$("#request-types-table tbody tr").removeClass('aui-restfultable-focused');
			    if(!selected){
			    	rowToDelete.addClass('aui-restfultable-focused');
			    }
		  
		  
		  var tableData = [];
		 AJS.$(this).closest('tr').find("td").each(function() {
        	 var tdData='';
        	 AJS.$(this).find("select").each(function() {
        		 tdData=AJS.$(this).val();	 
        	 });
        	 
        	 if(tdData===''){
        	 AJS.$(this).find("input").each(function() {
        		 if(AJS.$(this).is(":text")){
        			 //alert('text '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        		 if(AJS.$(this).is(":hidden")){
        		 //alert('hidden '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        	   });
        	 }
        	 if(tdData===''){
        		 tdData=AJS.$(this).text();	 
        	 }
			 //alert('tdData'+tdData);
        	 tableData.push(tdData);
        });
        //alert('tableData'+tableData);
        var groups = [];
        var resp=AJS.$.parseJSON( AJS.$.ajax({
			url: baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/getServiceDeskGroups.json?servicedeskName=",
		    type : 'GET',
		    contentType : 'application/json',
		    async: false
		}).responseText);	
			
           AJS.$.each(resp, function(i, items) {
        	   groups.push(items.groups);
				});	
        
        
        
        var serviceDeskList =[];
		var response=AJS.$.parseJSON( AJS.$.ajax({
			url: baseUrl + "/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/getServiceDeskMainFields.json",
		    type : 'GET',
		    contentType : 'application/json',
		    async: false
		}).responseText);	
			
           AJS.$.each(response, function(i, items) {
					serviceDeskList.push(items.name);
					
				});	 
					
		AJS.$(this).parent().html('<td class="tdhout">	<span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+tableData[0]+'"><div aria-hidden="true" class="'+tableData[0]+'"></div></span>	</td><td class="tdhout"><input value="'+tableData[1]+'" class="text" /></td><td class="tdhout" id="editGroup'+tableData[5]+'"></td><td class="tdhout"><input value="'+tableData[3]+'" class="text" /></td><td class="tdhout" id="editServiceDesks'+tableData[5]+'"></td><td colspan="2"><input type="hidden" value="'+tableData[5]+'" /><input type="button" class="aui-button updateBtn" value="Update" style="margin-right:10px"/><a  href="javascript:void(0);" class="cancelBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Cancel</a></td><td class="aui-restfultable-status"><span class="aui-restfultable-throbber"></span></td><td></td>');
		var selArray=[];
		var selectedName=tableData[2];
		//alert(selectedName.indexOf(",")+ 'selectedName '+selectedName);
		if(selectedName.indexOf(",") >= 0){
		selArray=selectedName.split(",")
		}else{
		selArray.push(selectedName);
		}
		//alert('selArray '+selArray);
		getGroupDropDownList("editGroup","editGroup"+tableData[5],groups,selArray);
		getServiceDeskDropDownList("editSerivedesk","editServiceDesks"+tableData[5],serviceDeskList,tableData[4]);
		AJS.$("select[id^='editGroup']").auiSelect2();
	});
	 
	 
	 
	 AJS.$(".newGroup").live('click', function() {
		 //alert("new Group link clicked");
	 
	 });
	 
	 AJS.$("#request-types-table tbody tr td .updateBtn").live('click', function() {
		 var rowToDelete = AJS.$(this).closest('tr');
		   var tableData = [];
         
         AJS.$(this).closest('tr').find("td").each(function() {
        	 var tdData='';
        	 AJS.$(this).find("select").each(function() {
        		 tdData=AJS.$(this).val();	 
        	 });
        	 
        	 if(tdData===''){
        	 AJS.$(this).find("input").each(function() {
        		 if(AJS.$(this).is(":text")){
        			 //alert('text '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        		 if(AJS.$(this).is(":hidden")){
        		 //alert('hidden '+AJS.$(this).val());
        		 tdData=AJS.$(this).val();
        		 }
        	   });
        	 }
        	 if(tdData===''){
        		 tdData=AJS.$(this).text();	 
        	 }
        	 tableData.push(tdData);
        });
        //alert('tableData '+tableData);
        if(tableData[1]!==null){
		if(validateString(tableData[1]) && validateString(tableData[3])){
		   if(tableData[2]===null){ alert('Please select group');}else{
		    if(tableData[4]==='None'){ alert('Please select Service desk');}else{
			   AJS.$.ajax({
					url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/updateServiceDeskField.json?item='+tableData[1]+'&icon='+tableData[0]+'&groups='+tableData[2]+'&url='+tableData[3]+'&servicedesks='+tableData[4]+'&serviceID='+tableData[5],
				    type : 'PUT',
				    contentType : 'application/json',
				    data : { 
						
				    },		    
					success: function(response) {
						//alert('response2   '+response);
						AJS.$("#request-types-table tbody tr").remove();
						AJS.$.each(response, function(i, items) {
							AJS.$('<tr class="aui-restfultable-readonly aui-restfultable-row"></tr>').html('<td class="tdcls request-type-icon"> 	<span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+items.icon +'"><div aria-hidden="true" class="'+items.icon +'"></div></span>	</td><td class="tdcls"><div class="tdwrapcls" title="'+items.item +'">'+items.item +'</div></td><td class="tdcls"><div class="tdwrapcls" title="'+items.groups +'">'+items.groups +'</div></td><td class="tdcls"><div class="tdurlWrapcls"  title="'+items.url +'">'+items.url +'</div></td><td class="tdcls">'+items.servicedesks +'</td><td colspan="2"><input type="hidden" value="'+items.serviceID +'" /><a  href="javascript:void(0);" class="delBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Delete</a></td>').appendTo('#request-types-table');
						});
					}
				});
               }
			 }
			}				
		   }
        
        
       });
	 
	 
	 AJS.$("#request-types-table tbody tr td .cancelBtn").live('click', function() { 
			AJS.$.ajax({
			url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/getServiceDeskFields.json',
		    type : 'GET',
		    contentType : 'application/json',
		    data : { 
		    	 	
			    	},		    
			success: function(response) {
				//alert('response  '+response);
				AJS.$("#request-types-table tbody tr").remove();
				AJS.$.each(response, function(i, items) {
					
					AJS.$('<tr id="'+items.serviceID+'" class="aui-restfultable-editrow aui-restfultable-row "></tr>').html('<td class="tdcls request-type-icon">	<span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+items.icon +'"><div aria-hidden="true" class="'+items.icon +'"></div></span>	</td><td class="tdcls"><div class="tdwrapcls" title="'+items.item +'">'+items.item +'</div><div class="icons"></div></td><td class="tdcls"><div class="tdwrapcls"  title="'+items.groups +'">'+items.groups +'</div></td><td class="tdcls"><div class="tdurlWrapcls" title="'+items.url +'">'+items.url +'</td><td class="tdcls">'+items.servicedesks +'</div></td><td colspan="2"><input type="hidden" value="'+items.serviceID+'"/><a  href="javascript:void(0);" class="delBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Delete</a></td>').appendTo('#request-types-table');
				});	
			}
		});
	 });
	 
	 
	 AJS.$("#request-types-table thead tr td .addBtn").live('click', function() {
		 //alert('adding records');
		var name= AJS.$("#nameID").val();
		var group= AJS.$("#groups").val();
		//alert('group slectged'+group);
		if(group ==='undefined'){
		group='';
		}
		var url= AJS.$("#urlID").val();
		var serviceDesk= AJS.$("#serviceDesk").val();
		//alert('name '+name  +' group '+group+' url '+url+' serviceDesk '+serviceDesk);
		var def_icon=AJS.$('.request-type-icon-add').find('input:hidden').val();
		//alert('def_icon '+def_icon);
		if(def_icon===''){
		def_icon="trigger-icon-edit vp-rq-icon vp-rq-icon-32";
		}
		   if(name!==null){
		   if(validateString(name) && validateString(url)){
		   if(group===null){ alert('Please select group');}else{
		    if(serviceDesk==='None'){ alert('Please select Service desk');}else{
			//alert('else');
			   AJS.$.ajax({
					url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/addServiceDeskField.json?item='+name+'&icon='+def_icon+'&groups='+group+'&url='+url+'&servicedesks='+serviceDesk,
				    type : 'PUT',
				    contentType : 'application/json',
				    data : { 
						
				    },		    
					success: function(response) {
						//alert('response2   '+response);
						AJS.$("#request-types-table tbody tr").remove();
						AJS.$.each(response, function(i, items) {
							AJS.$('<tr class="aui-restfultable-readonly aui-restfultable-row"></tr>').html('<td class="tdcls request-type-icon">	<span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+items.icon +'"><div aria-hidden="true" class="'+items.icon +'"></div></span>	 </td><td ><div class="tdwrapcls"  title="'+items.item +'">'+items.item +'</div></td><td ><div class="tdwrapcls"   title="'+items.groups +'">'+items.groups +'</div></td><td ><div class="tdurlWrapcls"  title="'+items.url +'">'+items.url +'</div></td><td >'+items.servicedesks +'</td><td colspan="2"><input type="hidden" value="'+items.serviceID +'" /><a  href="javascript:void(0);" class="delBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Delete</a></td>').appendTo('#request-types-table');
						});
					}
				});
				}
				}
             } 				
		   }
		 });
	 
	 
	 AJS.$("#request-types-table tbody tr td .delBtn").live('click', function() {
		 var rowToDelete = AJS.$(this).closest('tr');
		   var tableData = [];
     
     AJS.$(this).closest('tr').find("td").each(function() {
    	 var tdData='';
    	AJS.$(this).find("select").each(function() {
 		 tdData=AJS.$(this).val();	 
    	});
    	
    	if(tdData===''){
    	 AJS.$(this).find("input").each(function() {
    		 if(AJS.$(this).is(":text")){
    			 //alert('text '+AJS.$(this).val());
    		 tdData=AJS.$(this).val();
    		 }
    		 if(AJS.$(this).is(":hidden")){
    		 //alert('hidden '+AJS.$(this).val());
    		 tdData=AJS.$(this).val();
    		 }
    	});
 	 }
    	 if(tdData===''){
    		 tdData=AJS.$(this).text();	 
    	 }
    	 tableData.push(tdData);
    });
    //alert('tableData '+tableData);
			
			   if(name!==null){
				   AJS.$.ajax({
						url : baseUrl + '/rest/dealertracks/servicedeskrest/1.0/ServiceDeskField/deleteServiceDeskField.json?item='+tableData[1]+'&icon="" &groups='+tableData[2]+'&url='+tableData[3]+'&servicedesks='+tableData[4],
					    type : 'PUT',
					    contentType : 'application/json',
					    data : { 
							
					    },		    
						success: function(response) {
							//alert('response2   '+response);
							AJS.$("#request-types-table tbody tr").remove();
							AJS.$.each(response, function(i, items) {
								AJS.$('<tr class="aui-restfultable-readonly aui-restfultable-row"></tr>').html('<td class="tdcls request-type-icon"><span class="aui-restfultable-hovercell" data-field-name="icon"><input type="hidden" name="requestTypeIcon" id="requestTypeIcon" value="'+items.icon +'"><div aria-hidden="true" class="'+items.icon +'"></div></span></td><td class="tdcls"><div class="tdwrapcls" title="'+items.item +'">'+items.item +'</div></td><td class="tdcls"><div class="tdwrapcls" title="'+items.groups +'">'+items.groups +'</div></td><td class="tdcls"><div class="tdurlWrapcls" title="'+items.url +'">'+items.url +'</div></td><td class="tdcls">'+items.servicedesks +'</td><td colspan="2"><input type="hidden" value="'+items.serviceID +'" /><a  href="javascript:void(0);" class="delBtn aui-button aui-button-link aui-restfultable-delete" original-title="">Delete</a></td>').appendTo('#request-types-table');
							});
						}
					});   
			   }
		  });
		  
		  
	 function validateString(testString) {
	 if(testString===null) {
	  alert('Please enter required details');
	 return false;
	 }
	 if (testString.match(/[`%&~,<>;'"\[\]\|{}()=_+]/)){ 
        alert('Special characters are not allowed');
         return false;		
	   }else{
	   //alert('testString  : '+testString);
	   return true;
	   }
	 }
	 
	 //------------------start ladp--------------------
	 // var baseUrl = AJS.$("meta[name='application-base-url']").attr("content");
	  //alert('verify ldap JS ');
	  function populateForm() {
		//alert('populateForm ');
	    AJS.$.ajax({
	      url:  baseUrl +"/rest/dealertracks/servicedeskrest/1.0/ServiceDeskMgmtResource/ldapServiceDesk",
	      type: "GET",
	      contentType: "application/json",
	      success: function(ldapSrvc) {
	    	//alert('ldapSrvc.ldapUid '+ldapSrvc.ldapUid);
	    	AJS.$("#ldapinitctx").attr("value", ldapSrvc.ldapInitCtx);
	    	AJS.$("#ldapsrvrname").attr("value", ldapSrvc.ldapSrvrName);
	        AJS.$("#ldapbasedn").attr("value", ldapSrvc.ldapBaseDn);
			AJS.$("#ldapuid").attr("value", ldapSrvc.ldapUid);
	        AJS.$("#ldappwd").attr("value", ldapSrvc.ldapPwd);
	        AJS.$("#dtincldappwd").attr("value", ldapSrvc.dtincLdapPwd);

	      }
	    });
	  }
	  function updateConfig() {

		  AJS.$.ajax({
		      url:  baseUrl +"/rest/dealertracks/servicedeskrest/1.0/ServiceDeskMgmtResource/ldapsave",
		      type: "PUT",
		      contentType: "application/json",
		      data: '{ "ldapInitCtx": "' + AJS.$("#ldapinitctx").attr("value") + '","ldapSrvrName": "' + AJS.$("#ldapsrvrname").attr("value") + '", "ldapBaseDn": "' + AJS.$("#ldapbasedn").attr("value") + '", "ldapUid": "' + AJS.$("#ldapuid").attr("value") + '", "ldapPwd": "' +  AJS.$("#ldappwd").attr("value") + '", "dtincLdapPwd": "' +  AJS.$("#dtincldappwd").attr("value") + '" }',
		      processData: false,
					success: function() {
						JIRA.Messages.showSuccessMsg('ldap Plugin Configuration Saved Successfully!');
					},
					error: function(request, status, error) {
						JIRA.Messages.showErrorMsg('LDAP Plugin Configuration could not be saved!<br><b>Server returned the following Error:</b><br> ' + request.responseText);
					}
		    });    
	  }  
	  populateForm();
	  
	  AJS.$("#ldapSrvc").submit(function(e) {
	    updateConfig();
	  });

	// -------------------ldap end------------------ 
   
    });

function getServiceDeskDropDownList(name, id, optionList,selectedName) {
	 var combo = AJS.$("<select class='select'></select>").attr("id", name);
	 combo.append("<option value='None'>---select---</option>");
	 if(optionList!==null){
	 var uniquesList = [];
    AJS.$.each(optionList, function (i, el) {
	if(AJS.$.inArray(el, uniquesList) === -1) {
	    uniquesList.push(el);
    	if(selectedName===el){
        combo.append("<option value='"+el+"' title='"+el+"'  selected='selected'>" + el + "</option>");
    	}else{
    		combo.append("<option value='"+el+"'  title='"+el+"'>" + el + "</option>");	
    	}
		}
    });
	 }
   
    AJS.$("#"+id).append(combo);
}


function getGroupDropDownList(name, id, optionList,selectedArray) {
	 var combo = AJS.$("<select multiple='multiple' class='select'></select>").attr("id", name);
	 if(optionList!==null){
	 var uniquesList = [];
    AJS.$.each(optionList, function (i, el) {
	if(AJS.$.inArray(el, uniquesList) === -1) {
	    //alert('el'+el);
		if(el.indexOf(",") === -1){
		uniquesList.push(el);
    	if(AJS.$.inArray(el, selectedArray) !== -1) {
        combo.append("<option value='"+el+"' title='"+el+"' selected='selected'>" + el + "</option>");
    	}else{
    		combo.append("<option value='"+el+"'  title='"+el+"'>" + el + "</option>");	
    	}
		}else{
		var splitArray=el.split(",");
		//alert('splitArray  '+splitArray);
		AJS.$.each(splitArray, function (index, eleItem) {
		//alert('eleItem  '+eleItem);
		if(AJS.$.inArray(eleItem, uniquesList) === -1) {
		uniquesList.push(eleItem);
		//alert('uniquesList  '+uniquesList);
		if(AJS.$.inArray(eleItem, selectedArray) !== -1) {
        combo.append("<option value='"+eleItem+"'  title='"+eleItem+"'  selected='selected'>" + eleItem + "</option>");
    	}else{
    		combo.append("<option value='"+eleItem+"' title='"+eleItem+"'>" + eleItem + "</option>");	
    	}
		}
		});
		}
		}
    });
	 }
   
    AJS.$("#"+id).append(combo);
}



function getDropDownList(name, id, optionList,selectedName) {
	 var combo = AJS.$("<select style='height:80px' class='select'></select>").attr("id", name);
	 combo.append("<option value='None'>---select---</option>");
	 if(optionList!==null){
    AJS.$.each(optionList, function (i, el) {
    	if(selectedName===el.Name){
        combo.append("<option value='"+el.Name+"' title='"+el.Name+"'  selected='selected'>" + el.Name + "</option>");
    	}else{
    		combo.append("<option  value='"+el.Name+"'  title='"+el.Name+"'>" + el.Name + "</option>");	
    	}
      });
	 }
    AJS.$("#"+id).append(combo);
  }

AJS.$(function() { 
	//AJS.$('#editGroup').auiSelect2();
	//AJS.$('#editSerivedesk').auiSelect2(); 
	AJS.$('#serviceDesk').auiSelect2(); 
	AJS.$('#groups').auiSelect2();
	AJS.$("select[id^='editGroup']").auiSelect2();
	AJS.$('.cv-breadcrumb-item-link').add
	
});












