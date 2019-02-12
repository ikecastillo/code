package com.dt.userfromemail.rest;

import com.atlassian.core.util.StringUtils;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.userfromemail.utils.EmailToUserID;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * Created by Yagnesh.Bhat on 4/5/2016.
 */

@Path("/createincidentoroutage")
public class CreateIncidentOrOutageREST {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(CreateIncidentOrOutageREST.class);

    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(final IssueBean issueBean) throws Exception{

        String projectkey = issueBean.getProjectkey();
        String issuetype = issueBean.getIssuetype();
        String summary = issueBean.getSummary();
        String description = issueBean.getDescription();
        String solutiongroup = issueBean.getSolutiongroup();
        String product = issueBean.getProduct();
        String severity = issueBean.getSeverity();
        String reporter = issueBean.getReporteremail();
        String customerimpact = issueBean.getCustomerimpact();
        String clientsimpacted = issueBean.getClientsimpacted();
        String impactedField = issueBean.getImpacted();
        String customertalkingpoints = issueBean.getCustomertalkingpoints();
        String incidentstart = issueBean.getIncidentstart();
        String incidentsource = issueBean.getIncidentsource();

        IssueService issueService = ComponentAccessor.getIssueService();

        //Set the reporter first
        String reporterId = EmailToUserID.getUserIDFromEmail(reporter).getUserid();
        log.debug("Reporter ID to be used for issue creation " + reporterId);

        //Getting the project ID
        ProjectManager projectManager = ComponentAccessor.getProjectManager();
        Project project = projectManager.getProjectObjByKey(projectkey);

        //Getting issue type ID
        String issueTypeID = "-1";
        IssueType issueType = null;
        Collection<IssueType> issueTypes = ComponentAccessor.getConstantsManager().getAllIssueTypeObjects();
        for (IssueType issueType1 : issueTypes) {
            if (issueType1.getName().equalsIgnoreCase(issuetype)) {
                issueTypeID = issueType1.getId();
                issueType = issueType1;
                break;
            }
        }

        //Getting all the remaining custom fields
        CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
        OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);

        CustomField severityCF = customFieldManager.getCustomFieldObjectByName("Severity");
        FieldConfig fieldConfigSolution = severityCF.getRelevantConfig(new IssueContextImpl(project, issueType));
        List<Option> sevOpts = optionsManager.getOptions(fieldConfigSolution);
        String sevOptionId = "";
        if (sevOpts!=null) {
            for(Option o: sevOpts){
                if(o.getValue().equals(severity)){
                    sevOptionId += o.getOptionId();
                    break;
                }
            }
        }

        log.debug("Severity Option ID is " + sevOptionId);

        CustomField solutionGroupsProductsCF = customFieldManager.getCustomFieldObjectByName("Solution Groups - Products");
        fieldConfigSolution = solutionGroupsProductsCF.getRelevantConfig(new IssueContextImpl(project, issueType));
        List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);
        String solngroupOptionID = "";
        String productOptionID = "";
        if(solOpts!=null){
            for(Option o: solOpts){
                if(o.getValue().equals(solutiongroup)){
                    solngroupOptionID += o.getOptionId();
                    if (isNotBlank(product)) {
                        for(Option productOption : o.getChildOptions()){
                            if(productOption.getValue().equalsIgnoreCase(product)) {
                                productOptionID += productOption.getOptionId();
                            }
                        }
                    }
                    break;
                }
            }
        }
        log.debug("Solution Group Option ID is " + solngroupOptionID);
        log.debug("Product Option ID is " + productOptionID);

        CustomField clientsImpactedCF = customFieldManager.getCustomFieldObjectByName("Clients Impacted");
        fieldConfigSolution = clientsImpactedCF.getRelevantConfig(new IssueContextImpl(project, issueType));
        List<Option> impacted = optionsManager.getOptions(fieldConfigSolution);
        String clientsImpactedOptionID = "";
        String clientsImpactedOptionIDLocation = "";
        for(Option o: impacted){
            if(!o.getDisabled()) {
                if (o.getValue().equalsIgnoreCase(clientsimpacted)) {
                    clientsImpactedOptionID += o.getOptionId();
                    if (clientsimpacted.equals("Internal")) {
                        for (Option locationOption : o.getChildOptions()) {
                            if (locationOption.getValue().equalsIgnoreCase("Burlington")) {
                                clientsImpactedOptionIDLocation += locationOption.getOptionId();
                            }
                        }
                    }
                }
            }
        }

        log.debug("Clients Impacted Option ID is " + clientsImpactedOptionID);
        log.debug("Clients Impacted Option ID for internal location (if internal is selected, else its blank) is " +
                clientsImpactedOptionIDLocation);


        CustomField customerImpactCF = customFieldManager.getCustomFieldObjectByName("Customer Impact");



        CustomField incidentSourceCF = customFieldManager.getCustomFieldObjectByName("Incident Source");
        fieldConfigSolution = incidentSourceCF.getRelevantConfig(new IssueContextImpl(project, issueType));
        List<Option> incidentSourceOpts = optionsManager.getOptions(fieldConfigSolution);
        String incidentSourceOptionId = "";
        if (incidentSourceOpts!=null) {
            for(Option o: incidentSourceOpts){
                if(o.getValue().equalsIgnoreCase(incidentsource)){
                    incidentSourceOptionId += o.getOptionId();
                    break;
                }
            }
        }

        log.debug("Incident source Option ID for HipChat is " + incidentSourceOptionId);

        //Getting IssueInput parameters ready
        IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
        issueInputParameters.setProjectId(project.getId())
                .setIssueTypeId(issueTypeID)
                .setSummary(summary)
                .setDescription(description)
                .setReporterId(reporterId)
                .addCustomFieldValue(severityCF.getId(),sevOptionId)
                .addCustomFieldValue(customerImpactCF.getId(),customerimpact)
                .addCustomFieldValue(incidentSourceCF.getId(),incidentSourceOptionId);

        //DDC is default solution group anyways for both internal and external in the hipchat addon,
        // but product is not mandatory if clients impacted = internal.
        //So check if there is any product option id retrieved or not, and set parameters accordingly.
        if (isNotBlank(productOptionID)) {
            issueInputParameters.addCustomFieldValue(solutionGroupsProductsCF.getId(),solngroupOptionID,productOptionID);
        } else {
            issueInputParameters.addCustomFieldValue(solutionGroupsProductsCF.getId(),solngroupOptionID);
        }

        if (clientsimpacted.equalsIgnoreCase("External")) {
            issueInputParameters.addCustomFieldValue(clientsImpactedCF.getId(),clientsImpactedOptionID);
        } else if (clientsimpacted.equalsIgnoreCase("Internal")) {
            issueInputParameters.addCustomFieldValue(clientsImpactedCF.getId(),clientsImpactedOptionID);
            issueInputParameters.addCustomFieldValue(clientsImpactedCF.getId()+ ":1",clientsImpactedOptionIDLocation);
        }

        if (isNotBlank(impactedField)) {
            CustomField impactedCF = customFieldManager.getCustomFieldObjectByName("Impacted - Function");
            fieldConfigSolution = impactedCF.getRelevantConfig(new IssueContextImpl(project, issueType));
            impacted = optionsManager.getOptions(fieldConfigSolution);
            String impactedOptionID = "";
            for(Option o: impacted){
                if(!o.getDisabled()) {
                    if (o.getValue().equalsIgnoreCase(impactedField)) {
                        impactedOptionID += o.getOptionId();
                    }
                }
            }

            log.debug("Impacted Function Option ID (if selected, else blank)" +
                    impactedOptionID);
            issueInputParameters.addCustomFieldValue(impactedCF.getId(),impactedOptionID);
        }

        if (isNotBlank(customertalkingpoints)) {
            CustomField customerTalkingPtsCF = customFieldManager.getCustomFieldObjectByName("Customer Talking Points");
            issueInputParameters.addCustomFieldValue(customerTalkingPtsCF.getId(),customertalkingpoints);
        }

        //finally for now add the incident start date
        CustomField incidentStartDateCF = customFieldManager.getCustomFieldObjectByName("Incident Start");
        issueInputParameters.addCustomFieldValue(incidentStartDateCF.getId(), incidentstart);


        IncidentOutageModel incidentOutageModel = createIncidentOrOutage(issueService, reporterId, issueInputParameters);

        return Response.ok(incidentOutageModel).build();
    }

    /**
     * Helper method that actually creates the ticket once all validations passed
     * @param issueService
     * @param reporterId
     * @param issueInputParameters
     * @return
     */
    private IncidentOutageModel createIncidentOrOutage(IssueService issueService, String reporterId, IssueInputParameters issueInputParameters) {
        ApplicationUser user = ComponentAccessor.getUserManager().getUser(reporterId);
        IssueService.CreateValidationResult createValidationResult = issueService.validateCreate(user, issueInputParameters);
        log.debug("Any validation errors? :" + createValidationResult.getErrorCollection());
        String issueKey = "";
        if (createValidationResult.isValid())
        {
            IssueService.IssueResult createResult = issueService.create(user, createValidationResult);
            log.debug("Create Result errors? : " + createResult.getErrorCollection());
            if (!createResult.isValid())
            {
                log.debug("Cannot create an incident or outage");
            }
            issueKey = createResult.getIssue().getKey();
        } else {
            log.debug("Create ERROR :" + createValidationResult.getErrorCollection());
        }

        return new IncidentOutageModel(issueKey);
    }
}
