(function(AJS) { 
    var templateValueImpactedFunc = "", templateValueImpactedFuncDecoded = "",
        custFieldImpactedFuncParent = "", //To hold the jQuery Selector for Impacted Function First Dropdown
        custFieldImpactedFunc = "", //To hold the jQuery Selector for Impacted Function Second Dropdown
        customfieldImpactedFunc_id = 'customfield_10111:1', //ID we need for Impacted function template selection
        customfieldImpactedFunc_id2 = 'customfield_10111',  // ID has been changed for production
        impactedFuncParentValue = "",
        impactedFuncValue = "",
        derivedTemplateName = ""; //This will be "Impacted Function Parent - Child Value"
    
    /* These function will be called on dialog load AND on change of the concerned dropdowns (see above for the dropdown names) */
    
    function setTemplateInDescriptionForImpactedFunction() {
        var templateFound = false;
        if (typeof JEDITOR_CONFIGURATION_JIRA === 'undefined') {
            return; 
        }

        custFieldImpactedFuncParent = AJS.$('select[id="' + customfieldImpactedFunc_id2 + '"]');
        custFieldImpactedFunc = AJS.$('select[id="' + customfieldImpactedFunc_id + '"]');
        impactedFuncValue = custFieldImpactedFunc.find('option:selected').text();
        impactedFuncParentValue = custFieldImpactedFuncParent.find('option:selected').text();
        derivedTemplateName = impactedFuncParentValue + " - " + impactedFuncValue;
        AJS.log("Template name to look for " + derivedTemplateName);
        
        
        //Get the concerned templates and keep them decoded and ready
        for (obj in JEDITOR_CONFIGURATION_JIRA) {
            if (JEDITOR_CONFIGURATION_JIRA[obj].type === 'template' && decodeURIComponent(JEDITOR_CONFIGURATION_JIRA[obj].label) ===  derivedTemplateName) {
               templateValueImpactedFunc = JEDITOR_CONFIGURATION_JIRA[obj].value;
               templateValueImpactedFuncDecoded = decodeURIComponent(templateValueImpactedFunc);
               templateValueImpactedFuncDecoded = templateValueImpactedFuncDecoded.replace(/<\/\p>/g,"").replace(/<p>/g,"").replace(/<br \/\>/g,"");// Removed extra spaces before populating the template 
                JEDITOR_PA.setData('description', templateValueImpactedFuncDecoded);
                return;
            }        
        }
        
        if (!templateFound) {
            JEDITOR_PA.setData('description', '');
        }
               
    }


    JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function (e, context, reason) { 
        if (reason === 'dialogReady' && typeof JEDITOR !== 'undefined' && AJS.$('[id="create-issue-dialog"]:visible').length) {
            //If something is already preset in create screen in future
           setTemplateInDescriptionForImpactedFunction();
            
            //Switch off the default event handlers if set for these fields, so we can attach our new event handlers to deal with templates
            AJS.$('select[id="' + customfieldImpactedFunc_id2 + '"], select[id="' + customfieldImpactedFunc_id + '"]').off('change.JEDITOR');

            //Listen to impacted function dropdown change at second level
            AJS.$('select[id="' + customfieldImpactedFunc_id + '"],select[id="' + customfieldImpactedFunc_id2 + '"]').on('change.JEDITOR',function() {
                AJS.log("In change event of impacted function");
                setTemplateInDescriptionForImpactedFunction();
            });
        } 
    });
})(AJS);