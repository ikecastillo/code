package com.dt.jira.incident.problem.action;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.issue.IssueService.IssueResult;
import com.atlassian.jira.bc.issue.link.IssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.xsrf.RequiresXsrfCheck;
import com.atlassian.jira.web.action.issue.DeleteLink;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.incident.problem.constants.CreateProblemConstants;

/**
 * DeleteProblemIssueLinkAction.java class for delete the Incident details from Problem Ticket within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */
public class DeleteProblemIssueLinkAction extends DeleteLink{
	
	private final WebResourceManager webResourceManager;
    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final ApplicationProperties applicationProperties;
	 private static final Logger log = LoggerFactory.getLogger(DeleteProblemIssueLinkAction.class);
	/**
     * Constructor
     * @param issueLinkService the IssueLinkService to be injected
     * @param applicationProperties the ApplicationProperties to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param webResourceManager the WebResourceManager to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
     */
	public DeleteProblemIssueLinkAction(IssueLinkService issueLinkService,ApplicationProperties applicationProperties,IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
    		WebResourceManager webResourceManager,
			IssueLinkManager issueLinkManager) {
		super(issueLinkService);
		this.applicationProperties = applicationProperties;
        this.issueService = issueService;
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
		this.issueLinkManager= issueLinkManager;
	}
	
	
	@Override
	@RequiresXsrfCheck
	protected String doExecute() throws Exception {
		
		Issue issueParent = getIssueObject();
		log.info("DeleteProblemIssueLinkAction linked issue Parent Id********"+issueParent.getId());
		MutableIssue linkedissue= ComponentAccessor.getIssueManager().getIssueObject(getSourceId());
		CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
		CustomField incidentKeyCustomField 	= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY);
		if(linkedissue!=null){
		    if(linkedissue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_PROBLEM) 
					&& linkedissue.getCustomFieldValue(incidentKeyCustomField)!=null 
					&& !linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("") 
					&& linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase(issueParent.getKey())){
				log.info("**********linked issue:" + linkedissue.getKey() +" **********having same incident key:" +linkedissue.getCustomFieldValue(incidentKeyCustomField).toString() +" **********as its parent key:" +issueParent.getKey());
				
				 // Check to see if we can create the problem ticket
		         @SuppressWarnings("unchecked")
		        final IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
		        CustomField incidentSeverityCustomField 	= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_SEVERITY);
			    CustomField incidentPriorityCustomField 	= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_PRIORITY);
				CustomField incidentSummaryCustomField 		= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_SUMMARY);
				CustomField incidentDescriptionCustomField 	= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_DESCRIPTION);
				
				issueInputParameters.addCustomFieldValue(incidentKeyCustomField.getId(), "None");
				issueInputParameters.addCustomFieldValue(incidentSeverityCustomField.getId(), "None");
				issueInputParameters.addCustomFieldValue(incidentPriorityCustomField.getId(), "None");
		    	issueInputParameters.addCustomFieldValue(incidentSummaryCustomField.getId(), "None");
			    issueInputParameters.addCustomFieldValue(incidentDescriptionCustomField.getId(), "None");
			    
				IssueService.UpdateValidationResult updateValidationResult = issueService.validateUpdate(authenticationContext.getLoggedInUser(), linkedissue.getId(), issueInputParameters);
		         if (!updateValidationResult.isValid())
		         {
		        	updateValidationResult.getErrorCollection();
		         } 		         
		        // This should validate whether the user is able to create the issue
		        IssueResult createResult = issueService.update(authenticationContext.getLoggedInUser(), updateValidationResult);		
				
		         if (!createResult.isValid())
		         {
		        	createResult.getErrorCollection();
		         }
	
			}
		}
		return super.doExecute();
	}
	
	/**
     * Gets Linked Issue Id
     * @return the Long
     */
	public Long getSourceId(){
		return super.getSourceId();
	}
	 /**
     * Includes Jira Web Resources
     * @return void
     */
    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
    }

    /**
     * Gets Issue Object
     * @return the MutableIssue
     */
    public MutableIssue getIssueObject()
    {
    	IssueService.IssueResult issueResult =null;
    	if(id!=null){
	        issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), id);	        
    	}
    	
    	if (!issueResult.isValid())
        {
            this.addErrorCollection(issueResult.getErrorCollection());
            return null;
        }
    	
        return  issueResult.getIssue();
    }

    // Getter adn Setters for passing the form params

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}