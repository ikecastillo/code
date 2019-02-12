/**
 * Created by yagnesh.bhat on 5/5/2016.
 */
AJS.toInit(function() {
    CSARAssigneeMapper.getAllMappings();
    populateSolutionGroupsForCSAR("CSAR","csarsolutiongroupoutside");

    AJS.$("#csarsolutiongroupoutside").change(function() {
        var selectedSG = AJS.$("#csarsolutiongroupoutside").val();
        if (selectedSG === "All") {
            CSARAssigneeMapper.getAllMappings();
        } else {
            /*
             First clear all the rows in the table.
             Then, make an AJAX call to get all the available mappings from AO in JSON format.
             Then parse it and display it in the form table rows to the already existing table in the vm
             */
            AJS.$("#csarmappingstable tbody tr").remove();
            var postData = JSON.stringify({
                "solutionGroup" : selectedSG
            });
            var url = "/rest/servicedeskldapintegration/1.0/config/getAllConfigsBasedOnSolGrp";
            AJS.$.ajax({
                url: url,
                dataType: "json",
                type: "POST",
                data: postData,
                contentType: 'application/json',
                success: function (data) {
                    AJS.log("Parsing the response and converting them to table rows");
                    CSAROwnerImplementorTableRowAppender.showTableRows(data);
                }
            });
        }
    });
    
    AJS.$("#csar-mapping-button").click(function() {
        AJS.log("Mapping dialog pops up");
        AJS.dialog2("#csar-mapping-dialog").show();
        AJS.$(".error").html(''); //Clear all error messages

        //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
        // Check this function mappingDialogFieldsNotEmpty().
        AJS.$("#csarsolutiongroup > option:eq(0)").prop('selected',true);
        AJS.$("#csarrequesttype > option:eq(0)").prop('selected',true);
        AJS.$("#csarowners").val('');//Blank out the input field so new in
        AJS.$("#csarimplementors").val(''); //Blank out the input field so new in

        populateSolutionGroupsForCSAR("CSAR","csarsolutiongroup");
        populateIssueTypesForCSAR("CSAR");
    });

    AJS.$("#csar-mapping-dialog-add-button").click(function() {
        AJS.$(".error").html('');
        if (csarMappingDialogFieldsNotEmpty()) {
            CSARAssigneeMapper.addMapping();
            AJS.dialog2("#csar-mapping-dialog").hide();
        }
    });

    // Hides the dialog
    AJS.$("#csar-mapping-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#csar-mapping-dialog").hide();
        AJS.log("Mapping dialog hides");
    });


    AJS.$(".deletecsarMapping").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#csardelete-mapping-dialog").show();
        AJS.$("#csarsolngrpconf").text(tableData[0]);
        AJS.$("#csarrequesttypeconf").text(tableData[1]);
        AJS.$("#csarownersconf").text(tableData[2]);
        AJS.$("#csarimplementorsconf").text(tableData[3]);
    });

    AJS.$("#ownerimplementor-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#csardelete-mapping-dialog").hide();
        AJS.log("Delete Mapping dialog hides");
    });

    AJS.$("#ownerimplementor-dialog-delete-button").click(function() {
        CSARAssigneeMapper.removeMapping(AJS.$("#csarsolngrpconf").text(),
            AJS.$("#csarrequesttypeconf").text(),AJS.$("#csarownersconf").text(),AJS.$("#csarimplementorsconf").text());
        AJS.dialog2("#csardelete-mapping-dialog").hide();
    });

    function populateSolutionGroupsForCSAR(projectKey, selectorId) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/servicedeskldapintegration/1.0/getSelectCascadeSolGrp",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                projectkey : projectKey
            },
            async:false,
            success: function(data) {
                var selectElement = "#"+selectorId;
                var  solnlineSelectfield = AJS.$(selectElement);

                //Remove everything but the --select-- option
                AJS.$('option', solnlineSelectfield).not(':eq(0)').remove();
                
                //Add a default option first before adding other options
                var defaultSolnLineOption = AJS.$("<option/>").attr("value", "Default").text("Default");
                solnlineSelectfield.append(defaultSolnLineOption);
                for(var i=0;i<data.length;i++){
                    var value = data[i].value;
                    var solnLineOption = AJS.$("<option/>").attr("value", value).text(value);
                    solnlineSelectfield.append(solnLineOption);
                }
            }
        });
    }

    function populateIssueTypesForCSAR(projectKey) {
        AJS.$.ajax({
            url: AJS.params.baseURL + "/rest/servicedeskldapintegration/1.0/getIssueTypes",
            type: 'GET',
            contentType: "application/json" ,
            data: {
                projectkey : projectKey
            },
            async:false,
            success: function(data) {
                var  issueTypeSelectfield = AJS.$("#csarrequesttype");

                //Remove everything but the --select-- option
                AJS.$('option', issueTypeSelectfield).not(':eq(0)').remove();
                for(var i=0;i<data.length;i++){
                    var value = data[i].label;
                    var issueTypeOption = AJS.$("<option/>").attr("value", value).text(value);
                    issueTypeSelectfield.append(issueTypeOption);
                }
            }
        });
    }

    function csarMappingDialogFieldsNotEmpty() {
        var validationPassed = true;
        var solutionGroup = AJS.$("#csarsolutiongroup option:selected").text();
        var requestType = AJS.$("#csarrequesttype option:selected").text();
        var owner = AJS.$("#csarowners").val().trim();
        var implementor = AJS.$("#csarimplementors").val().trim();

        if (solutionGroup == "Select") {
            AJS.log("Solution group is Select, giving error message");
            /*AJS.$("#csarsolutiongroup").focus().siblings(".error").html("Please select a Solution Group");*/
            AJS.$("#errorcsarsolutiongroup").html("Please select a Solution Group");
            validationPassed = false;
        }

        if (requestType == "Select") {
            AJS.log("Request Type is Select, giving error message");
            /*AJS.$("#csarrequesttype").focus().siblings(".error").html("Please select a Request Type");*/
            AJS.$("#errorcsarrequesttype").html("Please select a Request Type");
            validationPassed = false;
        }

        if (!owner) {
            AJS.log("No owner selected , giving error message");
            /*AJS.$("#csarowners").focus().siblings(".error").html("Please select an owner");*/
            AJS.$("#errorcsarowners").html("Please select an owner");
            validationPassed = false;
        }

        if (!implementor) {
            AJS.log("No implementor selected , giving error message");
            /*AJS.$("#csarimplementors").focus().siblings(".error").html("Please select an implementor");*/
            AJS.$("#errorcsarimplementors").html("Please select an implementor");
            validationPassed = false;
        }

        return validationPassed;

    }
});