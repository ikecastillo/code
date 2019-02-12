DTGADGET = {};
// DT = {};
// DT.gadget = {};
// DT.gadget.fields={};

/**
 * Returns the ajax requests that should be executed prior to calling DTGADGET.descriptor.
 * The descriptor will get the results in the args parameter, with each request response added to the "key" field
 */
DTGADGET.descriptorArgs = function (baseUrl) {
	AJS.$.ajaxSettings.contentType = 'application/json';
	return [
        {
            key: "projectOptions",
            // ajaxOptions:  baseUrl + "/rest/greenhopper/1.0/project-list"
			ajaxOptions:  baseUrl + "/rest/api/2/project", 
			dataType: "json"
			
        },
		{
            key: "solutionGroups",
            // ajaxOptions:  baseUrl + "/rest/greenhopper/1.0/project-list"
			ajaxOptions:  baseUrl + "/rest/incident/1.0/getSolutiionGroups.json?projectkey=ITSM", 
			dataType: "json"
			
        }
    ];
};

DTGADGET.descriptorArgsSummary = function (baseUrl) {
	AJS.$.ajaxSettings.contentType = 'application/json';
	
	return [
        {
        	key: "columnChoices",
        	ajaxOptions:  "/rest/gadget/1.0/availableColumns"
         },      
        {
            key: "projectOptions",
            // ajaxOptions:  baseUrl + "/rest/greenhopper/1.0/project-list"
			ajaxOptions:  baseUrl + "/rest/api/2/project", 
			dataType: "json"
			
        },
        {
        	key: "types",          
			ajaxOptions: function ()
			{

				return {
					url: "/rest/incident/1.0/getTypes.json?projectkey=ITIM",
					contentType: "application/json" 
				}
			}
            },
            {
            	key: "typeValues",          
    			ajaxOptions: function ()
    			{

    				return {
    					url: "/rest/incident/1.0/getTypes/getLocationValues?type=Internal",
    					contentType: "application/json" 
    				}
    			}
                },
                {
                    key: "solutionGroups",
                    // ajaxOptions:  baseUrl + "/rest/greenhopper/1.0/project-list"
        			ajaxOptions: function ()
        			{

        				return { 
        					url:  "/rest/incident/1.0/getSolutiionGroups.json?projectkey=ITIM", 
        					contentType: "application/json" 
        				}
                }
                },
        {
            key: "causes",
            ajaxOptions: function ()
            {
                return {
                    url:  "/rest/incident/1.0/getCauses.json?projectkey=ITIM",
                    contentType: "application/json"
                }
            }
        }
    ];
};
/**
 * Configuration screen descriptor for the gadget
 */ 
DTGADGET.descriptor = function (gadget, args, baseUrl) {
	AJS.$("body").removeClass('config-unavailable');
	return {
        //action: baseUrl + "/rest/greenhopper/1.0/chartdata/validate-hourburndown.json",
        theme : function()
        {
            if (gadgets.window.getViewportDimensions().width < 450)
            {
                return "gdt top-label";
            }
            else
            {
                return "gdt";
            }
        }(),
        fields:[
			DTGADGET.projectPicker(gadget, "projectId", args.projectOptions),
			//AJS.gadget.fields.projectPicker(gadget, "projectId", args.projectOptions),
			DTGADGET.versionPicker(gadget, "projectId", "version", true, true),
			DTGADGET.sprintPicker(gadget, "projectId", "sprint", true, true),
            //GH.gadget.fields.projectPicker(gadget, "projectKey", args.projectOptions.options),
			//AJS.gadget.fields.projectPicker
			//this.sprintpicker (projectKey),
			AJS.gadget.fields.nowConfigured()
        ]
    };
};
/**
 * DLR specific configuration screen descriptor for the gadgets
 */ 
DTGADGET.ITSMdescriptor = function (gadget, args, baseUrl) {
	AJS.$("body").removeClass('config-unavailable');
	return {
        //action: baseUrl + "/rest/greenhopper/1.0/chartdata/validate-hourburndown.json",
        theme : function()
        {
            if (gadgets.window.getViewportDimensions().width < 450)
            {
                return "gdt top-label";
            }
            else
            {
                return "gdt";
            }
        }(),
        fields:[			
			DTGADGET.ITSM_sgPicker(gadget, "solutionGroup", args.solutionGroups),
			//AJS.gadget.fields.projectPicker(gadget, "projectId", args.projectOptions),
			//DTGADGET.DLR_versionPicker(gadget, "projectId", "version", true, true),
			//DTGADGET.DLR_sprintPicker(gadget, "projectId", "sprint", true, true),
            //GH.gadget.fields.projectPicker(gadget, "projectKey", args.projectOptions.options),
			//AJS.gadget.fields.projectPicker
			//this.sprintpicker (projectKey),
			AJS.gadget.fields.nowConfigured()
        ]
    };
};

DTGADGET.ITSMdescriptorSumary = function (gadget, args, baseUrl) {
	AJS.$("body").removeClass('config-unavailable');

	return {
		
        //action: baseUrl + "/rest/greenhopper/1.0/chartdata/validate-hourburndown.json",
        theme : function()
        {
            if (gadgets.window.getViewportDimensions().width < 450)
            {
                return "gdt top-label";
            }
            else
            {
                return "gdt";
            }
        }(),
        fields:[              
            DTGADGET.projectPicker(gadget, "projectId", args.projectOptions),
			//DTGADGET.ITSM_sgPicker(gadget, "solutionGroup", args.solutionGroups),
			//AJS.gadget.fields.projectPicker(gadget, "projectId", args.projectOptions),
			//DTGADGET.DLR_versionPicker(gadget, "projectId", "version", true, true),
			//DTGADGET.DLR_sprintPicker(gadget, "projectId", "sprint", true, true),
            //GH.gadget.fields.projectPicker(gadget, "projectKey", args.projectOptions.options),
			//AJS.gadget.fields.projectPicker
			//this.sprintpicker (projectKey),
			AJS.gadget.fields.numberToShow(gadget, "num"),
			AJS.gadget.fields.nowConfigured()
        ]
    };
};

/**
 * DLR specific configuration screen descriptor for the gadgets
 */ 
DTGADGET.ITSMUptimeReportdescriptorSumary = function (gadget, args, baseUrl) {
	AJS.$("body").removeClass('config-unavailable');

	return {
		
        //action: baseUrl + "/rest/greenhopper/1.0/chartdata/validate-hourburndown.json",
        theme : function()
        {
            if (gadgets.window.getViewportDimensions().width < 450)
            {
                return "gdt top-label";
            }
            else
            {
                return "gdt";
            }
        }(),
        fields:[              
          //  DTGADGET.projectPicker(gadget, "projectId", args.projectOptions),
			//DTGADGET.ITSM_sgPicker(gadget, "solutionGroup", args.solutionGroups),
			//AJS.gadget.fields.projectPicker(gadget, "projectId", args.projectOptions),
			//DTGADGET.DLR_versionPicker(gadget, "projectId", "version", true, true),
			//DTGADGET.DLR_sprintPicker(gadget, "projectId", "sprint", true, true),
            //GH.gadget.fields.projectPicker(gadget, "projectKey", args.projectOptions.options),
			//AJS.gadget.fields.projectPicker
			//this.sprintpicker (projectKey),
      
			AJS.gadget.fields.nowConfigured()
        ]
    };
};
