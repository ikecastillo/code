package com.dt.jira.problem.rcasubtask.action;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.problem.rcasubtask.ao.RCASubtaskFieldMap;
import com.dt.jira.problem.rcasubtask.constants.CreateRCASubtaskConstants;
import com.dt.jira.problem.rcasubtask.service.FieldMappingService;
import com.dt.jira.problem.rcasubtask.utils.IssueFieldUtils;

public class CreateAndLinkRCASubtaskAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(CreateAndLinkRCASubtaskAction.class);
    
    private Long id;
    private String key;
    private final WebResourceManager webResourceManager;
    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final SubTaskManager subTaskManager;
	private final ApplicationProperties applicationProperties;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	private final FieldMappingService fieldMapService;
	private final IssueFieldUtils issueFieldUtils;
	private final IssueLinkManager issueLinkManager;
	/**
     * Constructor
     * @param applicationProperties the ApplicationProperties to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param webResourceManager the WebResourceManager to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
     */
    public CreateAndLinkRCASubtaskAction(ApplicationProperties applicationProperties,
    		IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
    		WebResourceManager webResourceManager,IssueManager issueManager, IssueFactory issueFactory,
    		SubTaskManager subTaskManager,
    		FieldMappingService fieldMapService,
    		IssueFieldUtils issueFieldUtils,
    		IssueLinkManager issueLinkManager)
    {
    	this.applicationProperties = applicationProperties;
        this.issueService = issueService;
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.issueLinkManager = issueLinkManager;
		this.fieldMapService = fieldMapService;
		this.issueFieldUtils = issueFieldUtils;
    }
    
    protected void doValidation()
    {
        includeResources();      

    }
 
    @Override
    public String execute() throws Exception {
        Issue issueParent = getIssueObject();
        log.info("-------------------issueParent issue type id---------"+issueParent.getIssueTypeId());
        log.info("-------------------issueParent id -------------------"+issueParent.getId());
        includeResources();
        
		IssueType issueType		=	issueParent.getIssueTypeObject();		
		CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
		CustomField incidentKeyCustomField 		= 	cfm.getCustomFieldObjectByName(CreateRCASubtaskConstants.FIELD_INCIDENT_KEY);
		CustomField incidentTimelineCustomField = cfm.getCustomFieldObjectByName(CreateRCASubtaskConstants.FIELD_TIMELINE_OF_EVENTS);
		
			
		if(issueType.getName().equalsIgnoreCase(CreateRCASubtaskConstants.ISSUE_TYPE_PROBLEM) && getRCASubtaskLinkedIssueCount(issueParent)==0){
			 try{
				 Status status = issueParent.getStatusObject();
				 if(status.getName().equalsIgnoreCase(CreateRCASubtaskConstants.STATUS_IN_PROGRESS)
						 ||status.getName().equalsIgnoreCase(CreateRCASubtaskConstants.STATUS_RESOLVED_PENDING_REPORT_REVIEW)
						 || status.getName().equalsIgnoreCase(CreateRCASubtaskConstants.STATUS_RESOLVED_CONFIRMED)
						 ||status.getName().equalsIgnoreCase(CreateRCASubtaskConstants.STATUS_CLOSED) ){
				 // define subtask
				 MutableIssue issueObject = issueFactory.getIssue();
				 // set the project
				 issueObject.setProjectId(issueParent.getProjectObject().getId());				 
				 Project project = issueParent.getProjectObject();
				 String rcasubtaskId = getRCAIssueTypeId(project);
				 // Set the issue type
				 issueObject.setIssueTypeId(rcasubtaskId);//RCA-RS 10604 for 49 & RCA-RS 10301 for 26
				  //set parent id 
				 issueObject.setParentId(issueParent.getId());				
				 // set the timeline of events
	         	if(issueParent.getCustomFieldValue(incidentKeyCustomField)!=null && !issueParent.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("")){
		         	MutableIssue pbrParentIssue= issueManager.getIssueObject((String)issueParent.getCustomFieldValue(incidentKeyCustomField));
			        log.info("-------------------incident Issue Key-------"+pbrParentIssue.getKey());
		         	if(pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField)!=null){
		         		log.info("-------------------Incident Timeline CustomField-------"+pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField));
		         		issueObject.setCustomFieldValue(incidentTimelineCustomField, pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField));
		         	}
	         	}  	
				
	         	 // set subtask attributes
				 List<RCASubtaskFieldMap> dbFields = fieldMapService.findAllByParentAndChildIssue(issueType.getName(),CreateRCASubtaskConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK);
			     ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
				 if(dbFields!=null) {
			            for(RCASubtaskFieldMap fieldMap:dbFields) {			            	
			            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
			            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
			            }
			        }
				 /* Create RCA Report Subtask and link with Problem */
				 Issue subTask = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
				 subTaskManager.createSubTaskIssueLink(issueParent, subTask, authenticationContext.getLoggedInUser());
			 
				 
				 }else{
					 messages= "You cannot create an RCA Report Subtask unless the Problem is marked as In Progress, Resolved - Pending Report Review, Resolved Confirmed or Closed. " +
	    				"Please update the status and try again!";
					 addErrorMessage(messages);		       
				        return ERROR;
				 }
	           }catch(Exception e){
	        	   e.printStackTrace();
	           }
			        
			        return SUCCESS;
	          
			
	        }else{
	 		        messages= "RCA Report Subtask is already created for "+issueParent.getKey();
					addErrorMessage(messages);		       
			        return ERROR;		       
	        }
        
		
    }
  /**
     * Gets the RCA Report Subtask Id
     *
     * @param project the Project 
     * @return the String
     */
  private String getRCAIssueTypeId(Project project){
       Collection<IssueType> issuesTypes=project.getIssueTypes();
       String issueTypeId="";
       for(IssueType childIssue : issuesTypes){
              if(childIssue.getName().equalsIgnoreCase(CreateRCASubtaskConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK)){
                     issueTypeId=childIssue.getId();
                     break;
              }
       }
       return issueTypeId;
    }


    /**
     * Gets number of RCASubtask is linked with problem
     *
     * @param parent the Issue
     * @param incidentKeyCustomField the CustomField
     * @return the int
     */
    //public int getProblemTicketLinkedIssueCount(Issue parent,CustomField incidentKeyCustomField){
    public int getRCASubtaskLinkedIssueCount(Issue parent){
	    int count = 0;   
	    log.info("**********RCA Report Subtask Already Linked To Parent Ticket Count parent issue:" + parent.getKey());
	    SubTaskManager subTaskManager= ComponentAccessor.getSubTaskManager();
		Collection<Issue> linkedSubtask= subTaskManager.getSubTaskObjects(parent);		
		if(linkedSubtask.size()==0){
			log.info("**********No RCA Report Subtask Ticket linked with parent issue");
			return count;
		}else{			
			for(Issue linkedSubtaskIssue : linkedSubtask){				
				if(linkedSubtaskIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateRCASubtaskConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK) )
				{
					    count++;
				}
			}
		  return count;
		}
    }
    
    private String messages = "";
    public String getMessages(){return this.messages;}
    
    /**
     * Includes Jira Web Resources
     * @return void
     */
    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
    }

    /**
     * Gets Issue Object
     * @return the Issue
     */
    public Issue getIssueObject()
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
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
