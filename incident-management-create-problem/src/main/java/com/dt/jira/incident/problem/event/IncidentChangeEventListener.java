package com.dt.jira.incident.problem.event;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.ofbiz.core.entity.GenericEntityException;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.core.util.map.EasyMap;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.issue.IssueService.IssueResult;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.exception.CreateException;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
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
import com.dt.jira.incident.problem.ao.ProblemFieldMap;
import com.dt.jira.incident.problem.constants.CreateProblemConstants;
import com.dt.jira.incident.problem.service.FieldMappingService;
import com.dt.jira.incident.problem.utils.IssueFieldUtils;
/**
 * IncidentChangeEventListener.java event listener class for creating, updating and linking a Problem Ticket to Incident within Incident Management Project.
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */


public class IncidentChangeEventListener implements InitializingBean, DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(IncidentChangeEventListener.class);
	private final EventPublisher publisher;
	private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final IssueFactory issueFactory;
	private final IssueManager issueManager;
	private final IssueFieldUtils issueFieldUtils;
	private final FieldMappingService fieldMapService;
	
	/**
     * Constructor
     * @param publisher the EventPublisher to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
     */
	public IncidentChangeEventListener(EventPublisher publisher,
			IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
			IssueLinkManager issueLinkManager,
			FieldMappingService fieldMapService,
			IssueFactory issueFactory,
			IssueManager issueManager,
			IssueFieldUtils issueFieldUtils) {
		this.publisher = publisher;
		this.issueService = issueService;
        this.authenticationContext = authenticationContext;
		this.issueLinkManager= issueLinkManager;
		this.fieldMapService = fieldMapService;
		this.issueFactory = issueFactory;
		this.issueManager = issueManager;
		this.issueFieldUtils = issueFieldUtils;
	}
	
	@EventListener
	public void onIssueEvent(IssueEvent event) {		
		Long eventTypeId = event.getEventTypeId();
		if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
			/* Creating and linking a Problem Ticket to Incident */
			createProblemTicketByIncident(event);
			
			/* Create Problem from parent issue (incident) and associate with Incident Report subtask If the "Problem Investigation Required" is checked and status is Pending Approval on Incident Report subtask*/
			createAndLinkProblemFromIncidentReportSubtask(event);	
			
		}else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
			/* Creating and linking a RCA Report Subtask to Problem */
			createRCASubtask(event);
		}else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
			/* Update the problem incident details from incident */
			updateProblemTicketIncidentDetails(event);
		}
	}
	/**
	* Return true/false If "Problem Investigation Required" is enable/disable on submit approval screen for incident report subtask
	*/
	private boolean isProblemInvestigationChecked(Issue currentIssue){
	CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
	CustomField subtaskInvstRequiredCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_INVESTIGATION_REQUIRED);
	 if(currentIssue.getCustomFieldValue(subtaskInvstRequiredCustomField)!=null){
		return true;
	 } 
		return false;
	}
	/**
	* If "Problem Investigation Required" is unchecked in Incident but checked in Incident Report subtask while Submitting for Approval, 
	* the value of "Problem Investigation Required" should be copied to Incident and a Problem ticket should be created 
	* which should have link to both Incident and Incident Report subtask.
	*/
	private void createAndLinkProblemFromIncidentReportSubtask(IssueEvent event){	
			final MutableIssue currentIssue = (MutableIssue) event.getIssue();
			Boolean subIssueExistsCondition = currentIssue.isSubTask();
			String issueTypeName = currentIssue.getIssueTypeObject().getName();
			try{  
				if(subIssueExistsCondition && issueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK) && isProblemInvestigationChecked(currentIssue) ){
						final MutableIssue parentIssue = (MutableIssue) currentIssue.getParentObject(); 
						if(parentIssue!=null && ( parentIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) || parentIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE)) ){
								try{	
										Status currentIssueStatus = currentIssue.getStatusObject();
										if(currentIssueStatus.getName().equalsIgnoreCase(CreateProblemConstants.STATUS_PENDING_APPROVAL)){
											/* Copying the Problem Investigation Required from Incident Report subtask to Incident */
											CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
											CustomField subtaskInvstRequiredCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_INVESTIGATION_REQUIRED);
											CustomField incidentInvstRequiredCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_INVESTIGATION_REQUIRED);
											
											@SuppressWarnings("unchecked")
											final IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
											OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
											log.info("Inc-RS Project "+currentIssue.getProjectObject());
											log.info("Inc-RS  Issue Type "+currentIssue.getIssueTypeObject());
											FieldConfig fieldConfigSeverity = subtaskInvstRequiredCustomField.getRelevantConfig(new IssueContextImpl(currentIssue.getProjectObject(),currentIssue.getIssueTypeObject()));	
											Options pbrInvOption = optionsManager.getOptions(fieldConfigSeverity);
											for(Option pbrInvOpt : pbrInvOption){
												log.info("pbrInvOpt "+pbrInvOpt.getValue());
												 if(!pbrInvOpt.getDisabled() && pbrInvOpt.getValue().equals("Yes")){
													issueInputParameters.addCustomFieldValue(incidentInvstRequiredCustomField.getId(), String.valueOf(pbrInvOpt.getOptionId()));
												  }
											  }
											/* Update Incident Report subtask Problem Investigation Required Custom Field value */  
											IssueService.UpdateValidationResult updateValidationResult = issueService.validateUpdate(authenticationContext.getLoggedInUser(),parentIssue.getId(), issueInputParameters);
											IssueResult updateResult = issueService.update(authenticationContext.getLoggedInUser(), updateValidationResult);
											
											if (updateResult.isValid()){
												/* Create problem ticket on incident */
												createProblemTicketFromIncident(parentIssue);
												/* Gets the list of Issue Link associated with Incident */
												List<IssueLink> inwardLinks = ComponentAccessor.getIssueLinkManager().getInwardLinks(parentIssue.getId());
												for(IssueLink inLinkedissue : inwardLinks){
													IssueLinkTypeManager issueLinkTypeManager = (IssueLinkTypeManager) ComponentAccessor.getComponentOfType(IssueLinkTypeManager.class);
													IssueLinkType issueLinkType=issueLinkTypeManager.getIssueLinkType(10000L);
													// If the Problem ticket is getting created on incident then linked with Incident Report subtask 
													issueLinkManager.createIssueLink(inLinkedissue.getSourceObject().getId(), currentIssue.getId(), issueLinkType.getId(), null, authenticationContext.getLoggedInUser());
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
	// Create Problem from incident report sub-task
	private void createProblemTicketFromIncident(MutableIssue issueParent) {
    	Status status = issueParent.getStatusObject();	
		IssueType issueType=issueParent.getIssueTypeObject();		

		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		
		
		CustomField problemInvstRequiredCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_INVESTIGATION_REQUIRED);
		CustomField incidentKeyCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY);
		try{        	
			/* Create the Problem if the Problem investigation Required is unchecked on Incident. But checked on Incident Report Sub-task*/
 			if( (issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) || issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE) )
				&& (status.getName().equalsIgnoreCase(CreateProblemConstants.STATUS_RESOLVED_PENDING_REPORT_REVIEW)
				|| status.getName().equalsIgnoreCase(CreateProblemConstants.STATUS_RESOLVED_CONFIRMED))
				&& issueParent.getCustomFieldValue(problemInvstRequiredCustomField)!=null 
				&& getProblemTicketLinkedIssueCount(issueParent,incidentKeyCustomField)==0
				&& (String) issueParent.getCustomFieldValue(incidentKeyCustomField)==null){
 				log.info("Usecasee2: Create the Problem if the Problem investigation Required is unchecked on Incident. But checkedin on Incident Report Sub-task");
 				createProblemFromIncidentReport(issueParent);
 			}
			
		}catch(Exception e){
       	     e.printStackTrace();
       }
    }
	/* Create Problem from incident report sub-task when problemInvstRequiredCustomField is enabled */
	private void  createProblemFromIncidentReport(MutableIssue issueParent) throws CreateException{
		 	MutableIssue issueObject = issueFactory.getIssue();
			Project prbProject = ComponentAccessor.getProjectManager().getProjectObjByKey("PRB");
			String pbrIssuTypeId = getProblemIssueTypeId(prbProject);
			CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
			CustomField incidentKeyCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY);
			IssueType issueType=issueParent.getIssueTypeObject();
			 issueObject.setProjectId(prbProject.getId());
			 issueObject.setIssueTypeId(pbrIssuTypeId);
			 issueObject.setParentId(issueParent.getId());
			 issueObject.setStatusId("1");
			/* Default Fields Mapping */
			//CustomField severiryCustomField 			= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_SEVERITY); 	         	
			//CustomField incidentSeverityCustomField 	= cfm.getCustomFieldObjectByName("Incident Severity");				
			CustomField startTimeCustomField 			= cfm.getCustomFieldObjectByName("Problem Start");	
		
			// Set current date to Problem Start field
			Date currentDate = new Date();
		    long currentTime = currentDate.getTime();
			issueObject.setCustomFieldValue(startTimeCustomField, (Object)new Timestamp(currentTime));
						
			/* Associate Severity and Incident Severity Custom Field Value from Incident to Problem Ticket     
			if(issueParent.getCustomFieldValue(severiryCustomField)!=null){		         	
				Option selectedOption = (Option) issueParent.getCustomFieldValue(severiryCustomField);
				String severiryCustomFieldValue=selectedOption.getOptionId().toString();
				String incidentSeverityCustomFieldValue = selectedOption.getValue().toString(); 		
				
				issueObject.setCustomFieldValue(severiryCustomField, severiryCustomFieldValue);
				issueObject.setCustomFieldValue(incidentSeverityCustomField, incidentSeverityCustomFieldValue);
			}else{
				OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
				FieldConfig fieldConfigSeverity = severiryCustomField.getRelevantConfig(new IssueContextImpl(issueParent.getProjectObject(),issueType));	
				Options severityOption = optionsManager.getOptions(fieldConfigSeverity);
				for(Option sevirityOpt : severityOption){ 			   			
					 if(!sevirityOpt.getDisabled() && sevirityOpt.getValue().equals("Low")){
						 issueObject.setCustomFieldValue(severiryCustomField, String.valueOf(sevirityOpt.getOptionId()));
						 issueObject.setCustomFieldValue(incidentSeverityCustomField, sevirityOpt.getValue());
					  }
				  }
			}	*/
			 /* Read the fields from DB */ 
			 List<ProblemFieldMap> dbFields = fieldMapService.findAllByParentAndChildIssue(issueType.getName(),CreateProblemConstants.ISSUE_TYPE_PROBLEM);
		     ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
			 if(dbFields!=null) {
		            for(ProblemFieldMap fieldMap:dbFields) {
		            	
		            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
		            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
		            }
		        }
			 issueObject.setCustomFieldValue(incidentKeyCustomField, issueParent.getKey());
			 /* Special use case for new cascade field Solution Groups - Products: Due to that incident and problem having two different contexts. Options can not  be copied from incident to problem*/
			 CustomField solutionProductsCustomField = cfm.getCustomFieldObjectByName("Solution Groups - Products");
			 Object value=issueObject.getCustomFieldValue(solutionProductsCustomField);
			 List<Option> castObj = (List<Option>) value;
			 List<Option> replaceOptions=replaceCascadeSelectOption(castObj);
			 issueObject.setCustomFieldValue(solutionProductsCustomField,replaceOptions);
			 // End 
					 
			 /* Create Problem  */
			 Issue createResult = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
	    
			IssueLinkTypeManager issueLinkTypeManager = (IssueLinkTypeManager) ComponentAccessor.getComponentOfType(IssueLinkTypeManager.class);
			IssueLinkType issueLinkType=issueLinkTypeManager.getIssueLinkType(10000L); // Problem is link to 10000L id i.e "is blocked by"   
			/* Link the problem ticket with incident */
			issueLinkManager.createIssueLink(createResult.getId(), issueParent.getId(), issueLinkType.getId(), null, authenticationContext.getLoggedInUser());
		
			
			
	}
	/**
     * Creating and linking a Problem Ticket to Incident if the Problem Investigation Required is checked and the status is Resolved - Confirmed or Resolved - Pending Report Review.
     * @param event the IssueEvent
     */
    private void createProblemTicketByIncident(IssueEvent event) {
    	Issue issueParent = event.getIssue();
    	Status status = issueParent.getStatusObject();
		
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();

		IssueType issueType=issueParent.getIssueTypeObject();
		
		
		CustomField problemInvstRequiredCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_INVESTIGATION_REQUIRED);
		CustomField incidentKeyCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY);
		/* Create the problem ticket if the Parent Issue is Incident and the transition status either Resolved - Confirmed or Resolve - Pending Report Review and also the Incident Problem Investigation Required is checked */
		
		try{ 			
 			if( (issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) || issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE))
				&& (status.getName().equalsIgnoreCase(CreateProblemConstants.STATUS_RESOLVED_PENDING_REPORT_REVIEW)
				|| status.getName().equalsIgnoreCase(CreateProblemConstants.STATUS_RESOLVED_CONFIRMED))
				&& issueParent.getCustomFieldValue(problemInvstRequiredCustomField)!=null 
				&& getProblemTicketLinkedIssueCount(issueParent,incidentKeyCustomField)==0 //Allows to create only one Problem Ticket
				&& (String) issueParent.getCustomFieldValue(incidentKeyCustomField)==null){
 			
					/* Create the problem ticket */
					 @SuppressWarnings("unchecked")
					//final IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
					Project prbProject = ComponentAccessor.getProjectManager().getProjectObjByKey("PRB");
					String pbrIssuTypeId = getProblemIssueTypeId(prbProject);
					 MutableIssue issueObject = issueFactory.getIssue();
					 issueObject.setProjectId(prbProject.getId());
					 issueObject.setIssueTypeId(pbrIssuTypeId);
					 issueObject.setParentId(issueParent.getId());
					 issueObject.setStatusId("1");
	 	         	
					/* Default Fields Mapping */
					//CustomField severiryCustomField 			= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_SEVERITY); 	         	
					//CustomField incidentSeverityCustomField 	= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_SEVERITY);
					CustomField startTimeCustomField 			= cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_PROBLEM_START);	

					// Set current date to Problem Start field
					Date currentDate = new Date();
				    long currentTime = currentDate.getTime();
					issueObject.setCustomFieldValue(startTimeCustomField, (Object)new Timestamp(currentTime));
					
					log.info("Usecase 1: Create the problem ticket if the Incident Problem Investigation Required is checked on Incident and the transition status either Resolved - Confirmed or Resolve - Pending Report Review");
					
					/* Associate Severity and Incident Severity Custom Field Value from Incident to Problem Ticket     
					if(issueParent.getCustomFieldValue(severiryCustomField)!=null){		         	
						Option selectedOption = (Option) issueParent.getCustomFieldValue(severiryCustomField);
						String severiryCustomFieldValue=selectedOption.getOptionId().toString();
						String incidentSeverityCustomFieldValue = selectedOption.getValue().toString(); 		
						
						issueObject.setCustomFieldValue(severiryCustomField, severiryCustomFieldValue);
						issueObject.setCustomFieldValue(incidentSeverityCustomField, incidentSeverityCustomFieldValue);
					}else{
						OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
						FieldConfig fieldConfigSeverity = severiryCustomField.getRelevantConfig(new IssueContextImpl(issueParent.getProjectObject(),issueType));	
						Options severityOption = optionsManager.getOptions(fieldConfigSeverity);
						for(Option sevirityOpt : severityOption){ 			   			
							 if(!sevirityOpt.getDisabled() && sevirityOpt.getValue().equals("Low")){
								 issueObject.setCustomFieldValue(severiryCustomField, String.valueOf(sevirityOpt.getOptionId()));
								 issueObject.setCustomFieldValue(incidentSeverityCustomField, sevirityOpt.getValue());
							  }
						  }
					}	
					*/
					// Read the fields from Problem Field Mapping table 
					 List<ProblemFieldMap> dbFields = fieldMapService.findAllByParentAndChildIssue(issueType.getName(),CreateProblemConstants.ISSUE_TYPE_PROBLEM);
				     ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
					 if(dbFields!=null) {
				            for(ProblemFieldMap fieldMap:dbFields) {				            	
				            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
				            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
				            }
				        }
					 issueObject.setCustomFieldValue(incidentKeyCustomField, issueParent.getKey());
					 
					 /* Special use case for new cascade field Solution Groups - Products: Due to that incident and problem having two different contexts. Options can not  be copied from incident to problem*/
					 CustomField solutionProductsCustomField = cfm.getCustomFieldObjectByName("Solution Groups - Products");
					 Object value=issueObject.getCustomFieldValue(solutionProductsCustomField);
					 List<Option> castObj = (List<Option>) value;
					 List<Option> replaceOptions=replaceCascadeSelectOption(castObj);
					 issueObject.setCustomFieldValue(solutionProductsCustomField,replaceOptions);
					 // End 
					 Issue createResult = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
			    
					IssueLinkTypeManager issueLinkTypeManager = (IssueLinkTypeManager) ComponentAccessor.getComponentOfType(IssueLinkTypeManager.class);
					IssueLinkType issueLinkType=issueLinkTypeManager.getIssueLinkType(10000L); // Problem is link to 10000L id i.e "is blocked by"  
					/* Link the problem ticket with incident */
					issueLinkManager.createIssueLink(createResult.getId(), issueParent.getId(), issueLinkType.getId(), null, authenticationContext.getLoggedInUser());
 			}
			
		}catch(Exception e){
       	     e.printStackTrace();
       }
    }

	 /**
     * Gets the Problem Ticket Issue Type Id
     *
     * @param project the Project
     * @return the String
     */
    public String getProblemIssueTypeId(Project project){
    	Collection<IssueType> issuesTypes=project.getIssueTypes();
    	String issueTypeId="";
    	for(IssueType childIssue : issuesTypes){
    		if(childIssue.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_PROBLEM)){
    			issueTypeId=childIssue.getId();
    			break;
    		}
    	}
    	return issueTypeId;
    }

    /**
     * Creating and linking RCA Subtask to Problem and copying the Timeline of Events custom field value from Incident to RCA Subtask
     * @param event the IssueEvent
     */
    private void createRCASubtask(IssueEvent event) {    	
    	final MutableIssue currentIssue = (MutableIssue) event.getIssue();
		Boolean subIssueExistsCondition = currentIssue.isSubTask();
		String issueTypeName = currentIssue.getIssueTypeObject().getName();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		try{ 
			/* Whether issue type is RCA Report Subtask or not */       	
 			if(subIssueExistsCondition && issueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK)){ 				
 				final MutableIssue parentIssue = (MutableIssue) currentIssue.getParentObject(); 				
 				CustomField incidentKeyCustomField = customFieldManager.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY); 				
 				String incidentKeyCustomFieldValue = (String) parentIssue.getCustomFieldValue(incidentKeyCustomField);
 				/* Gets parent issue of problem issue */
 				Issue popissue = ComponentAccessor.getIssueManager().getIssueObject(incidentKeyCustomFieldValue);
 				/* Checks parent of problem issue */
 				if(popissue!=null && (popissue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) || popissue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE)) ){
 					try{	 						
						/* Timeline of event Custom field of incident */
 						CustomField incidentTimelineCustomField = customFieldManager.getCustomFieldObjectByName(CreateProblemConstants.FIELD_TIMELINE_OF_EVENTS);
						/* Timeline of event Custom field of rca report subtask */
 						CustomField subtaskTimelineCustomField = customFieldManager.getCustomFieldObjectByName(CreateProblemConstants.FIELD_TIMELINE_OF_EVENTS);
						/* Copy the timeline of event Custom field value from incident to RCA report subtask */
 						copyTimelineOfEventsFromIncident(currentIssue, (String)popissue.getCustomFieldValue(incidentTimelineCustomField), subtaskTimelineCustomField);
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
     * Updates the Problem Ticket incident details from Incident.
     * @param event the IssueEvent
     */
    private void updateProblemTicketIncidentDetails(IssueEvent event) {
    	
    	Issue issueParent = event.getIssue();
    	IssueType issueType=event.getIssue().getIssueTypeObject();
    	/* Whether issue type is Incident or not */ 
		if(issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) || issueType.getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE)){
            List<GenericValue> changeItems = null;
			ApplicationUser user = event.getUser();
            try {
                GenericValue changeLog = event.getChangeLog();
                changeItems = changeLog.internalDelegator.findByAnd("ChangeItem", EasyMap.build("group",changeLog.get("id")));
                for (Iterator<GenericValue> iterator = changeItems.iterator(); iterator.hasNext();){
	                GenericValue changetemp = (GenericValue) iterator.next();
					/* Update the problem incident details from incident */
	                updateProblemTicketIncidentDetails(changetemp, issueParent, user);
	            }
            } catch (GenericEntityException e){
                e.getMessage();
            }
         }	 
      }
    
       
    /**
     * Updates the Problem Ticket incident details from Incident.
     * @param changetemp the GenericValue
     * @param issue the Issue
     * @param user the User
     */
     private void updateProblemTicketIncidentDetails(GenericValue changetemp, Issue issue, ApplicationUser user) {
    	
    	String field = changetemp.getString("field");
        String oldValue = changetemp.getString("oldstring");
        String newValue = changetemp.getString("newstring");
        StringBuilder fullstring = new StringBuilder();
        fullstring.append("Issue ");
        fullstring.append(issue.getKey());
        fullstring.append(" field ");
        fullstring.append(field);
        fullstring.append(" has been updated from ");
        fullstring.append(oldValue);
        fullstring.append(" to ");
        fullstring.append(newValue);
        
        log.info("******************updateProblemTicketIncidentDetails() Updated field values******************"+fullstring);
        
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        IssueService issueService = ComponentAccessor.getIssueService();
   	 
        IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
        CustomField incidentKeyCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_KEY);
       
        if(field.equalsIgnoreCase("summary")){
        	CustomField incidentSummaryCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_SUMMARY);
        	log.info("******************updateProblemTicketIncidentDetails() Update Incident Summary******************"+fullstring);        	
        		issueInputParameters.addCustomFieldValue(incidentSummaryCustomField.getId(),newValue);
        }else if(field.equalsIgnoreCase("description")){
        	CustomField incidentDescriptionCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_DESCRIPTION);
        	log.info("******************updateProblemTicketIncidentDetails() Update Incident Description***************"+fullstring);
        	if (newValue == null || newValue.equals("")) {  
        		issueInputParameters.addCustomFieldValue(incidentDescriptionCustomField.getId(),"None");
        	}else{
        		issueInputParameters.addCustomFieldValue(incidentDescriptionCustomField.getId(),newValue);
        	}   
        }else if(field.equalsIgnoreCase("severity")){
        	CustomField incidentSeverityCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_INCIDENT_SEVERITY);
        	log.info("******************updateProblemTicketIncidentDetails() Update Incident Severity******************"+fullstring);
        	 if (newValue == null || newValue.equals("")) { 
        		 issueInputParameters.addCustomFieldValue(incidentSeverityCustomField.getId(), "Low");
        	}else{
        		 issueInputParameters.addCustomFieldValue(incidentSeverityCustomField.getId(), newValue);
        	}
        }else if (field.equalsIgnoreCase("xmatters log")) {
			CustomField xMattersLogCustomField = cfm.getCustomFieldObjectByName(CreateProblemConstants.FIELD_XMATTERS_LOG);
			log.info("******************updateProblemTicketIncidentDetails() Update XMatters Log******************"+fullstring);
			issueInputParameters.addCustomFieldValue(xMattersLogCustomField.getId(), newValue);
		}
		        
        LinkCollection linkCollection = issueLinkManager.getLinkCollection(issue, authenticationContext.getLoggedInUser());
    	Collection<Issue> allLinkedIssues= linkCollection.getAllIssues();
    	for(Issue linkedIssue : allLinkedIssues){
    		
    		if(linkedIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_PROBLEM) 
    				&& linkedIssue.getCustomFieldValue(incidentKeyCustomField)!=null 
    				&& !linkedIssue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("") 
    				&& linkedIssue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase(issue.getKey())){
    			    IssueService.UpdateValidationResult updateValidationResult = issueService.validateUpdate(user,linkedIssue.getId(), issueInputParameters);
	                
	                if (updateValidationResult.isValid())
	                {
	                    IssueResult updateResult = issueService.update(user, updateValidationResult);
	                    if (!updateResult.isValid())
	                    {
	                    	log.info("******************Problem Ticket has been upfated sucessfully!******************");
	                    }
	                }
			}
    	}
	    
    }
  
	/**      
   	  * Copying the Time Line Events details from Incident to RCA Subtask.
      * @param issue the MutableIssue
      * @param valueToSave the String text
      * @param customField the CustomField
      */
    public void copyTimelineOfEventsFromIncident(MutableIssue issue, String valueToSave, CustomField customField) throws FieldLayoutStorageException {

    	log.info("copyTimelineOfEventsFromIncident");
	    issue.setCustomFieldValue(customField, valueToSave);
	    Map<String, ModifiedValue> modifiedFields = issue.getModifiedFields();
	    FieldLayoutItem fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(issue).getFieldLayoutItem(customField);
	    DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
	    final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
	    customField.updateValue(fieldLayoutItem, issue, modifiedValue, issueChangeHolder);
	}
    
    /**      
  	 * Gets number of Problem Ticket is linked with incident which have the same incident key as its parent key.
     * @param parent the Issue
     * @param incidentKeyCustomField the CustomField
     * @return count if the Problem Ticket is linked with incident which have the same incident key as its parent key.
     */
    public int getProblemTicketLinkedIssueCount(Issue parent,CustomField incidentKeyCustomField){
        int count = 0; 
        LinkCollection linkCollection = issueLinkManager.getLinkCollection(parent, authenticationContext.getLoggedInUser());
    	Collection<Issue> allLinkedIssues= linkCollection.getAllIssues();
    	log.info("**********getProblemTicketLinkedIssueCount all Linked Issues size:" +allLinkedIssues.size());
    	if(allLinkedIssues.size()==0){
    		log.info("**********No problem Ticket linked with parent issue**********");
    		return count;
    	}else{
    		for(Issue linkedissue : allLinkedIssues){
    			if(linkedissue.getIssueTypeObject().getName().equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_PROBLEM) 
    					&& linkedissue.getCustomFieldValue(incidentKeyCustomField)!=null 
    					&& !linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("") 
    					&& linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase(parent.getKey())){    				   
    				    count++;
    			}
    		}
    	  return count;
    	}
      }
	  
	   private Option getSolutionGroupOfProblem(String solGroup,List<Option> solutionGroup){
    	if(solutionGroup != null && (!solutionGroup.isEmpty())){
			for(Option solopt : solutionGroup){ 					
				if(solopt.getValue().equals(solGroup)){
				return 	solopt;
				}
				
			}
		}
		return 	null;
    }
	   /**
	   * Get all options for new cascade field Solution Groups - Products of problem context
	   */
	  private Options getSolutionGroupOptionsOfProblem(){    	
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("PRB");
    	
    	CustomField customFieldSolution = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Solution Groups - Products" );
		//reverted back the context related change to previous state
		//FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
    	FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project.getId() , getProblemIssueTypeId(project)));
		
		OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 		
		Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
		return 	solutionGroup;
    }
	  /**
	   * Special use case for new cascade field Solution Groups - Products: Due to that incident and problem having two different * contexts. Options can not  be copied from incident to problem
	   */
    
    private List<Option> replaceCascadeSelectOption(List<Option> castObj){
    	List<Option> replaceOptionList=null;
    	if(castObj!=null){
    		Options solGroupProductsOptions=getSolutionGroupOptionsOfProblem();
    		int i=0;
    		Option replaceOption=null;
    		replaceOptionList=new ArrayList<Option>();
    		for(Option opt:castObj){
    			if(i==0){
    				replaceOption=getSolutionGroupOfProblem(opt.getValue(),solGroupProductsOptions.getRootOptions());
    				if(replaceOption!=null){
    					replaceOptionList.add(i,replaceOption);    					
    				}else{
    					replaceOptionList.add(i,opt);    					
    				}
    			}else{
    				if(replaceOption!=null){
    					replaceOption=getSolutionGroupOfProblem(opt.getValue(),replaceOption.getChildOptions());
    					if(replaceOption!=null){
        					replaceOptionList.add(i,replaceOption);        					
        				}else{
        					replaceOptionList.add(i,opt);        					
        				}
    			}
    				
    			}
    			i++;    			
    		}
    		
    	}
		return replaceOptionList;
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
