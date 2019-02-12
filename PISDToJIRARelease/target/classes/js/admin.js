/**
 * Created by yagnesh.bhat on 7/27/2016.
 */
AJS.$(function() {
    PISDtoRelMapper.getAllMappings();

    function updatePISDToRelConfig() {
        AJS.$.ajax({
            url: "/rest/createjiraticket/1.0/pisdtoreladminconfig",
            type: "PUT",
            contentType: "application/json",
            data: '{"pisdtorelticketuid": "' + AJS.$("#pisdtorelticketuid").attr("value") +
            '", "pisdtorelticketpwd": "' + AJS.$("#pisdtorelticketpwd").attr("value") +
            '","pisdToRelticketApplicationName": "' +  AJS.$( "#pisdToRelticketApplicationName option:selected" ).text() +
            '","pisdToRelticketApplicationUrl": "' +  AJS.$( "#pisdToRelticketApplicationName option:selected" ).attr("displayUrl") +
            '"}',
            processData: false
        });
    }

    AJS.$("#pisdtorelticketadmin2").submit(function(e) {
        e.preventDefault();
        updatePISDToRelConfig();
        JIRA.Messages.showSuccessMsg('Plugin Configuration Saved Successfully!');
    });

    // Shows the dialog when the "Show dialog" button is clicked
    AJS.$("#pisdtorel-mapping-button").click(function() {
        AJS.log("Mapping dialog pops up");
        AJS.dialog2("#pisdtoreltktmapping-dialog").show();

        populateProjectsFromTargetJIRAInstance();
        AJS.$(".error").html(''); //Clear all error messages

        //Keep the option "Select" by default. Note, but "Select" wont be allowed to map.
        // Check this function mappingDialogFieldsNotEmpty().
        AJS.$("#serviceDeskProject > option:eq(0)").prop('selected',true);
        AJS.$("#otherJIRAInstProject > option:eq(0)").prop('selected',true);
        AJS.$("#otherJIRAInstProjectIssueType > option:eq(0)").prop('selected',true);
        /*AJS.$(".mandatoryevtField").val('');*/
        AJS.log("Mapping dialog ready to accept form inputs");
    });

    AJS.$("#pisdtorelmapping-dialog-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pisdtoreltktmapping-dialog").hide();
        AJS.log("Mapping dialog hides");
    });

    AJS.$("#pisdtorelmapping-dialog-add-button").click(function() {
        if (serviceDeskProjectNotEmpty()) {
            PISDtoRelMapper.addMapping();
            AJS.dialog2("#pisdtoreltktmapping-dialog").hide();
        }
    });

    AJS.$(".deleteProjMapping").live('click',function() {
        var tableData = [];
        AJS.$(this).closest('tr').find("td").each(function() {
            tableData.push(AJS.$(this).text());
        });

        AJS.log("Delete Mapping dialog pops up");
        AJS.dialog2("#pisdtoreltktdelete-mapping-dialog").show();
        AJS.$("#serviceDeskProjectNameconf").text(tableData[0]);
        AJS.$("#serviceDeskProjectKeyconf").text(tableData[1]);
        AJS.$("#otherJIRAInstProjectNameconf").text(tableData[2]);
        AJS.$("#otherJIRAInstProjectKeyconf").text(tableData[3]);
        AJS.$("#otherJIRAInstProjectKeyIssueTypeconf").text(tableData[4]);

    });

    AJS.$("#pisdtorelmapping-dialog-delete-cancel-link").click(function(e) {
        e.preventDefault();
        AJS.dialog2("#pisdtoreltktdelete-mapping-dialog").hide();
        AJS.log("Delete Mapping dialog hides");
    });

    AJS.$("#pisdtorelmapping-dialog-delete-button").click(function() {
        PISDtoRelMapper.removeMapping(AJS.$("#serviceDeskProjectNameconf").text(),
            AJS.$("#serviceDeskProjectKeyconf").text(),AJS.$("#otherJIRAInstProjectNameconf").text()
            ,AJS.$("#otherJIRAInstProjectKeyconf").text(), AJS.$("#otherJIRAInstProjectKeyIssueTypeconf").text());
        AJS.dialog2("#pisdtoreltktdelete-mapping-dialog").hide();
    });

    AJS.$("#otherJIRAInstProject").change(function() {
        var currentProjKey = AJS.$("#otherJIRAInstProject option:selected").val();
        populateIssueTypeBasedOnJIRAReleaseProject(currentProjKey);
    });

});

function populateProjectsFromTargetJIRAInstance(){
    AJS.$.ajax({
        url: AJS.params.baseURL + "/rest/createjiraticket/1.0/getJIRAProjectsFromTarget",
        type: 'GET',
        contentType: "application/json" ,
        async:false,
        success: function(data) {
            var  targetJIRAProjectSelectfield = AJS.$("#otherJIRAInstProject");

            //Remove everything but the --select-- option
            AJS.$('option', targetJIRAProjectSelectfield).not(':eq(0)').remove();
            for(var i=0;i<data.length;i++){
                var label = data[i].projectName;
                var value = data[i].projectKey;
                var product = AJS.$("<option/>").attr("value", value).text(label);
                targetJIRAProjectSelectfield.append(product);
            }
        }
    });

}

function serviceDeskProjectNotEmpty() {
    var validationPassed = true;
    var sdProjectName = AJS.$("#serviceDeskProject option:selected").text();
    var jiraRelProjectName = AJS.$("#otherJIRAInstProject option:selected").text();
    var jiraRelProjectIssueType = AJS.$("#otherJIRAInstProjectIssueType option:selected").text();

    console.log("Validating Service Desk Project selected : " + sdProjectName);

    if (sdProjectName === "Select") {
        AJS.$("#sdProjError").html("Please select a Service Desk Project");
        validationPassed = false;
    }

    if (jiraRelProjectName !== "Select" && jiraRelProjectIssueType === "Select") {
        AJS.$("#sdProjIssueTypeError").html("Please select the JIRA Release Project Issue Type");
        validationPassed = false;
    }

    return validationPassed;

}

function populateIssueTypeBasedOnJIRAReleaseProject(currentProjKey) {
    jQuery.ajax({
        url: "/rest/createjiraticket/1.0/getIssueTypes",
        type: 'GET',
        contentType: "application/json",
        data: {
            projectkey: currentProjKey
        },
        success:function(data) {
            var issueTypeSelect = AJS.$("#otherJIRAInstProjectIssueType");
            issueTypeSelect.empty();
            var blankIssueTypeOption = AJS.$("<option/>").attr("value","").text("Select");
            issueTypeSelect.append(blankIssueTypeOption);
            for(var i=0 ; i<data.length; i++) {
                var label = data[i].label;
                var value = data[i].value; //This is the ghissuetype Id;
                var issueTypeOption = jQuery("<option/>").attr("value",value).text(label);
                issueTypeSelect.append(issueTypeOption);
            }
        }

    });
}