package com.dt.jira.create.incident.report.subtask.event;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.issue.IssueService.IssueResult;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutStorageException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLink;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.create.incident.report.subtask.ao.IRSubtaskFieldMap;
import com.dt.jira.create.incident.report.subtask.constants.CreateIRSubtaskConstants;
import com.dt.jira.create.incident.report.subtask.service.FieldMappingService;
import com.dt.jira.create.incident.report.subtask.utils.IssueFieldUtils;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.FunctionDescriptor;
/**
 * IncidentReportSubtaskEventListener.java event listener class for creating, updating and linking a Incident Report Subtask to Incident within Incident Management Project.
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */


public class IncidentReportSubtaskEventListener implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(IncidentReportSubtaskEventListener.class);
	private final EventPublisher publisher;
	private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final SubTaskManager subTaskManager;
	private final ApplicationProperties applicationProperties;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	private final FieldMappingService fieldmappingservice;
	private final IssueFieldUtils issueFieldUtils;
	private final FieldManager fieldManager;
		
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
	public IncidentReportSubtaskEventListener(EventPublisher publisher,
			IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
			IssueLinkManager issueLinkManager,
			ApplicationProperties applicationProperties,
			IssueManager issueManager, 
			IssueFactory issueFactory,
    		SubTaskManager subTaskManager,
    		FieldMappingService fieldmappingservice,
    		IssueFieldUtils issueFieldUtils,
    		FieldManager fieldManager) {
		this.publisher = publisher;
		this.issueService = issueService;
        this.authenticationContext = authenticationContext;
		this.issueLinkManager= issueLinkManager;
		this.applicationProperties = applicationProperties;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.fieldmappingservice = fieldmappingservice;
		this.issueFieldUtils = issueFieldUtils;
		this.fieldManager = fieldManager;
	}
	
	@EventListener
	public void onIssueEvent(IssueEvent event) {		
		Long eventTypeId = event.getEventTypeId();
		if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
			/* Create Incident Report subtask from parent issue (incident)*/
			createIncidentReportSubtaskByIncident(event);	
			
		}else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
			/* Copy Timeline of event and xMatter log from parent issue (incident) to Incident Report subtask */		
			copyTimeLineAndXMattersValueInIncidentReportSubtask(event);	
		}
	}
	

 /**
     * Creating and linking Incident Report Subtask automatically to Incident if transition status is Resolve - Pending Report Review
     * @param event the IssueEvent
     */
    private void createIncidentReportSubtaskByIncident(IssueEvent event) {
	final MutableIssue issueParent = (MutableIssue) event.getIssue();
		Boolean subIssueExistsCondition = issueParent.isSubTask();
		String issueTypeName = issueParent.getIssueTypeObject().getName();		
		/* Check Parent Issue Type */	
		if(issueTypeName.equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT) || issueTypeName.equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_OUTAGE) ){
			/* Check Incident Report Subtask Linked Issue Count from Incident */	
			if(getIncidentReportSubtaskLinkedIssueCount(issueParent)==0 ){
			/* Custom field for creating Incident Report Subtask on Incident */
			CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
			
			CustomField severiryCustomField = cfm.getCustomFieldObjectByName(CreateIRSubtaskConstants.FIELD_SEVERITY);
			
			Option selectedOption = (Option) issueParent.getCustomFieldValue(severiryCustomField);
			String severiryCustomFieldOptValue = selectedOption.getValue().toString();
				 try{			   
					 Status status = issueParent.getStatusObject();
					 /* Check Parent Issue (Incident) workflow transition status */	
					 if(status.getName().equalsIgnoreCase(CreateIRSubtaskConstants.STATUS_RESOLVED_PENDING_REPORT_REVIEW)){
					     /* Check Severity value from Incident */	
						 if(severiryCustomFieldOptValue.equalsIgnoreCase("High") 
							|| severiryCustomFieldOptValue.equalsIgnoreCase("Critical")){
							 /* Creates Incident Report Subtask object */
							 MutableIssue issueObject = issueFactory.getIssue();
							 issueObject.setProjectId(issueParent.getProjectObject().getId());
							 log.info("-------------------Issue Parent Project Object-------------"+issueParent.getProjectObject().getId());
							 Project project = issueParent.getProjectObject();
							 String subtaskId = getIncidentReportSubtaskTypeId(project);
							 issueObject.setIssueTypeId(subtaskId);
							 issueObject.setParentId(issueParent.getId());
							// set subtask attributes
							 List<IRSubtaskFieldMap> dbFields = fieldmappingservice.findAllByParentAndChildIssue(issueTypeName,CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK);
							 ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
							 if(dbFields!=null) {
						            for(IRSubtaskFieldMap fieldMap:dbFields) {						            
						            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
						            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
						            }
						        }	
							 if(!isSolutionGrpFIPatner(issueParent)){ // returns true if Solution Group Name :F&I Solutions (F&I)  Product Value:   Partner --> This case incident report subtask is not create.
								 // Create subtask
								 Issue subTask = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
								 /* Linked Incident Report Subtask with Incident */
								 subTaskManager.createSubTaskIssueLink(issueParent, subTask, authenticationContext.getLoggedInUser());
							 } else {
								 log.info("You cannot create Incident Report Subtask  Solution Group :F&I Solutions (F&I)  Product :   Partner ");
							 }
					 }else{
						 log.info("You cannot create an Incident Report Subtask unless the Incident Severity is High or Critical. " +
							"Please update the status and try again!");
					 }
				}else{
					 log.info("You cannot create an Incident Report Subtask unless the Incident is marked as Resolved Pending Report Review. " +
	    				"Please update the status and try again!");
				 }
	           }catch(Exception e){
	        	   e.printStackTrace();
	           }
			
	        }else{
	 		       log.info("Incident Report Subtask is already created for "+issueParent.getKey());   
	        }
        }
	}
	
	 /**
     * Gets the Incident Report Subtask Id
     * @param project the Project 
     * @return the String
     */
  private String getIncidentReportSubtaskTypeId(Project project){
       Collection<IssueType> issuesTypes=project.getIssueTypes();
       String issueTypeId="";
       for(IssueType childIssue : issuesTypes){
			/* Gets Sub Issue Type Id */  
              if(childIssue.getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)){
                     issueTypeId=childIssue.getId();
                     break;
              }
       }
       return issueTypeId;
    }

    /**
     * Gets number of Problem Ticket is linked with incident which have same incident key as parent key
     * @param parent the Issue
     * @param incidentKeyCustomField the CustomField
     * @return the int
     */
    public int getIncidentReportSubtaskLinkedIssueCount(Issue parent){
	    int count = 0;   
	    SubTaskManager subTaskManager= ComponentAccessor.getSubTaskManager();
		/* Gets all Linked Sub Issue Type from Parent Issue */  
		Collection<Issue> linkedSubtask= subTaskManager.getSubTaskObjects(parent);		
		if(linkedSubtask.size()==0){
			log.info("**********No Incident Report Subtask Ticket linked with parent issue");
			return count;
		}else{			
			for(Issue linkedSubtaskIssue : linkedSubtask){
				/* Gets Linked Sub Issue Type Name */  
				if(linkedSubtaskIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK) )
				{
					    count++;
				}
			}
		  return count;
		}
    }
    /**
     * Copying the Timeline of Events and XMatters Log custom field value from Incident to Incident Report Subtask
     * @param event the IssueEvent
     */
    private void copyTimeLineAndXMattersValueInIncidentReportSubtask(IssueEvent event) {    	
    	final MutableIssue currentIssue = (MutableIssue) event.getIssue();
		Boolean subIssueExistsCondition = currentIssue.isSubTask();
		/* Gets Current Issue Type */    
		String issueTypeName = currentIssue.getIssueTypeObject().getName();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		try{ 
			/* Is Sub Issue Type is Incident Report Subtask */       	
 			if(subIssueExistsCondition && issueTypeName.equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)){ 				
 				final MutableIssue parentIssue = (MutableIssue) currentIssue.getParentObject(); 
				/*Is Parent Issue Type is Incident */ 				
 				if(parentIssue!=null && parentIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT)){
 					try{	 						
 						 List<IRSubtaskFieldMap> dbFields = fieldmappingservice.findAll();					 
					     ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
					    
						 if(dbFields!=null) {
					            for(IRSubtaskFieldMap fieldMap:dbFields) {
					            	if((fieldMap.getJiraField().equalsIgnoreCase(CreateIRSubtaskConstants.FIELD_TIMELINE_OF_EVENTS) 
					            			&& fieldMap.getMappingField().equalsIgnoreCase(CreateIRSubtaskConstants.FIELD_TIMELINE_OF_EVENTS))
					            			|| (fieldMap.getJiraField().equalsIgnoreCase(CreateIRSubtaskConstants.FIELD_XMATTERS_LOG) 
					            			&& fieldMap.getMappingField().equalsIgnoreCase(CreateIRSubtaskConstants.FIELD_XMATTERS_LOG))){					            		
					            		
					            		CustomField parentCustomField = customFieldManager.getCustomFieldObjectByName(fieldMap.getJiraField());
						            	CustomField childCustomField = customFieldManager.getCustomFieldObjectByName(fieldMap.getMappingField());
						            	copyCustomFieldsFromIncident(currentIssue, (String)parentIssue.getCustomFieldValue(parentCustomField), childCustomField);
					            	}
					            }
					        }
 						
 				    
 					}catch(Exception e){
 						e.printStackTrace();
 					}        			
 				}
	 		}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	   
  
	/**      
   	  * Copying the CustomFields from Incident to Incident Report Subtask.
      * @param issue the MutableIssue
      * @param valueToSave the String text
      * @param customField the CustomField
      */
    public void copyCustomFieldsFromIncident(MutableIssue issue, String valueToSave, CustomField customField) throws FieldLayoutStorageException {
	    issue.setCustomFieldValue(customField, valueToSave);
	    Map<String, ModifiedValue> modifiedFields = issue.getModifiedFields();
	    FieldLayoutItem fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(issue).getFieldLayoutItem(customField);
	    DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
	    final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
	    customField.updateValue(fieldLayoutItem, issue, modifiedValue, issueChangeHolder);
	}
	
	
    /**      
  	  * Method is used  not generate an Incident Report for any F&I – “Partner” Incidents.
     * @param issue the MutableIssue   
     */
   public boolean isSolutionGrpFIPatner(MutableIssue issue)  {
	   
	    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
	   JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
	   CustomField  sgProductCF = fieldManager.getCustomFieldObjectByName("Solution Groups - Products");

	   List solutionGroupList = (ArrayList) issue.getCustomFieldValue(sgProductCF);
	   if (solutionGroupList != null) {
			Option solGrp = (Option)solutionGroupList.get(0);
			Option solGrpCascadeValue = (Option)solutionGroupList.get(1);	
			if( ( solGrp!=null && solGrpCascadeValue!=null ) && ( solGrp.getValue().equalsIgnoreCase("F&I Solutions (F&I)") && solGrpCascadeValue.getValue().equalsIgnoreCase("Partner") )){
				log.info("Solution Group Name :"+solGrp.getValue() + "  Product Value:   "+solGrpCascadeValue.getValue());
				return true;
			}
	   }
	   return false;	    
	    
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
