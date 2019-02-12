package com.dt.jira.problem.rcasubtask.event;

import java.util.Collection;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.problem.rcasubtask.ao.RCASubtaskFieldMap;
import com.dt.jira.problem.rcasubtask.constants.CreateRCASubtaskConstants;
import com.dt.jira.problem.rcasubtask.service.FieldMappingService;
import com.dt.jira.problem.rcasubtask.utils.IssueFieldUtils;


public class RCAReportSubtaskEventListener implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(RCAReportSubtaskEventListener.class);
	private final EventPublisher publisher;
	private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final SubTaskManager subTaskManager;
	private final ApplicationProperties applicationProperties;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	private final FieldMappingService fieldMapService;
	private final IssueFieldUtils issueFieldUtils;
	
		
	/**
     * Constructor
     * @param publisher the EventPublisher to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
	 * @param applicationProperties the ApplicationProperties to be injected
	 * @param subTaskManager the SubTaskManager to be injected
	 * @param issueManager the IssueManager to be injected
	 * @param issueFactory the IssueFactory to be injected
     */
	public RCAReportSubtaskEventListener(EventPublisher publisher,
			IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
			IssueLinkManager issueLinkManager,
			ApplicationProperties applicationProperties,
			IssueManager issueManager, 
			IssueFactory issueFactory,
    		SubTaskManager subTaskManager,FieldMappingService fieldMapService,
    		IssueFieldUtils issueFieldUtils
    		) {
		this.publisher = publisher;
		this.issueService = issueService;
        this.authenticationContext = authenticationContext;
		this.issueLinkManager= issueLinkManager;
		this.applicationProperties = applicationProperties;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.fieldMapService = fieldMapService;
		this.issueFieldUtils = issueFieldUtils;
	}
	
	@EventListener
	public void onIssueEvent(IssueEvent event) {		
		Long eventTypeId = event.getEventTypeId();
		if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
			createRCAReportSubtaskByProblem(event);	
		}
	}
	
 /**
     * Creating and linking RCA Report Subtask automatically to Problem if transition status will be RESOLVED - PENDING REPORT REVIEW
     * @param event the IssueEvent
     */
    private void createRCAReportSubtaskByProblem(IssueEvent event) {
	final MutableIssue issueParent = (MutableIssue) event.getIssue();
		String issueTypeName = issueParent.getIssueTypeObject().getName();
		
		if(issueTypeName.equalsIgnoreCase(CreateRCASubtaskConstants.ISSUE_TYPE_PROBLEM)){
		 if(getRCAReportSubtaskLinkedIssueCount(issueParent)==0){
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField severiryCustomField = cfm.getCustomFieldObjectByName(CreateRCASubtaskConstants.FIELD_SEVERITY);		
		CustomField incidentKeyCustomField 		= 	cfm.getCustomFieldObjectByName(CreateRCASubtaskConstants.FIELD_INCIDENT_KEY);
		CustomField incidentTimelineCustomField = cfm.getCustomFieldObjectByName(CreateRCASubtaskConstants.FIELD_TIMELINE_OF_EVENTS);
		
		
		Option selectedOption = (Option) issueParent.getCustomFieldValue(severiryCustomField);
		String severiryCustomFieldOptValue = selectedOption.getValue().toString(); 
		log.info("-------------------issueTypeName---------------"+issueTypeName);
		
			 try{			   
				 Status status = issueParent.getStatusObject();
				 log.info("-------------------issueParent Status----------------------"+status.getName());
				 log.info("-------------------Issue Parent Severity-------------------"+severiryCustomFieldOptValue);	
				if(status.getName().equalsIgnoreCase(CreateRCASubtaskConstants.STATUS_IN_PROGRESS)){
				if((issueParent.getCustomFieldValue(severiryCustomField)!=null) && (severiryCustomFieldOptValue.equalsIgnoreCase("High") || severiryCustomFieldOptValue.equalsIgnoreCase("Critical"))){
				 // define subtask
				 MutableIssue issueObject = issueFactory.getIssue();
				 issueObject.setProjectId(issueParent.getProjectObject().getId());
				 log.info("-------------------Issue Parent Project Object-------------"+issueParent.getProjectObject().getId());
				 Project project = issueParent.getProjectObject();
				 String subtaskId = getRCAReportSubtaskTypeId(project);
				 issueObject.setIssueTypeId(subtaskId);//IRS 10901 for 49 & IRS 10500 for 26
				  //normal subtask
				 issueObject.setParentId(issueParent.getId());
				
				 // set time line of events from incident 
				if(issueParent.getCustomFieldValue(incidentKeyCustomField)!=null && !issueParent.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("")){
		         	MutableIssue pbrParentIssue= issueManager.getIssueObject((String)issueParent.getCustomFieldValue(incidentKeyCustomField));
			        log.info("-------------------incident Issue Key-------"+pbrParentIssue.getKey());
		         	if(pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField)!=null){
		         		log.info("-------------------Incident Timeline CustomField-------"+pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField));
		         		issueObject.setCustomFieldValue(incidentTimelineCustomField, pbrParentIssue.getCustomFieldValue(incidentTimelineCustomField));
		         	}
					
	         	}  	

				
	         	 // get fields from mapping table 
				 List<RCASubtaskFieldMap> dbFields = fieldMapService.findAllByParentAndChildIssue(issueTypeName,CreateRCASubtaskConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK);
			     ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
				 if(dbFields!=null) {
			            for(RCASubtaskFieldMap fieldMap:dbFields) {			            	
			            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
			            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
			            }
			        }
				 //Create rca subtask from problem 
				 Issue subTask = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
				 subTaskManager.createSubTaskIssueLink(issueParent, subTask, authenticationContext.getLoggedInUser());
				 
				 }else{
					 log.info("You cannot create an RCA Report Subtask unless the Problem Severity is High or Critical. " +
			    				"Please update the status and try again!");
				 }
				}else{
					 log.info("You cannot create an RCA Report Subtask unless the problem is in progress or marked as Resolved. " +
	    				"Please update the status and try again!");
				 }
	           }catch(Exception e){
	        	   e.printStackTrace();
	           }
			
	        }else{
	 		        log.info("RCA Report Subtask is already created for "+issueParent.getKey());   
	        }
        }
	}
	
	 /**
     * Gets the Incident Report Subtask Id
     *
     * @param project the Project 
     * @return the String
     */
  private String getRCAReportSubtaskTypeId(Project project){
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
     * Gets number of RCAReportSubtask is linked with Problem
     *
     * @param parent the Issue
     * @param incidentKeyCustomField the CustomField
     * @return the int
     */
    public int getRCAReportSubtaskLinkedIssueCount(Issue parent){
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
    
	@Override
	public void destroy() throws Exception {
		this.publisher.unregister(this);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.publisher.register(this);
		
	}
}
