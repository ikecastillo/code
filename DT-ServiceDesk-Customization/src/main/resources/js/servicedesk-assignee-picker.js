(function ($) { 
 

     function createSingleUserPickers(ctx) { 
 

         var restPath = "/rest/api/1.0/users/picker"; 
 

         AJS.$(".customuserpickerfield", ctx).each(function () { 
        	 //alert('check1 ');
             var $this = $(this);
            // alert('check '+$this);
            if ($this.data("aui-ss")) return; 
             var data = {showAvatar: false}, 
             textarea = $this.data('inputValue'); 
 
             new AJS. MultiSelect({ 
                 element: $this, 
                 submitInputVal: true, 
                 showDropdownButton: !!$this.data('show-dropdown-button'), 
                 errorMessage: AJS.I18n.getText("user.picker.invalid.user", "'{0}'"), 
                ajaxOptions: { 
                     url: AJS.contextPath() + restPath, 
                     query: true, // keep going back to the sever for each keystroke 
                     data: data, 
                     formatResponse: JIRA.UserPickerUtil.formatResponse 
                 }, 
                 textarea: textarea 
             }); 
         }); 
     } 
 
     JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) { 
         if (reason !== JIRA.CONTENT_ADDED_REASON.panelRefreshed) { 
        	 //alert("HI>>>>");
             createSingleUserPickers(context);			 
         } 
     }); 
 
     
     
     

 })(AJS.$); 

