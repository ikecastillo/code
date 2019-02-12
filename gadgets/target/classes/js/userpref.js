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
            DTGADGET.header(gadget),
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
DTGADGET.DLRdescriptor = function (gadget, args, baseUrl) {
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
            DTGADGET.header(gadget),
			DTGADGET.DLR_projectPicker(gadget, "projectId", args.projectOptions),
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
