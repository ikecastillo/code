package com.dt.jira.create.incident.report.subtask.action;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ofbiz.core.entity.GenericEntityException;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.IssueRelationConstants;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.attachment.Attachment;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.impl.SelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.fields.NavigableField;
import com.atlassian.jira.issue.fields.OrderableField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.label.Label;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.worklog.WorkRatio;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectConstant;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.ObjectUtils;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.create.incident.report.subtask.ao.IRSubtaskFieldMap;
import com.dt.jira.create.incident.report.subtask.service.FieldMappingService;
import com.dt.jira.create.incident.report.subtask.utils.IssueFieldUtils;

import com.dt.jira.create.incident.report.subtask.constants.CreateIRSubtaskConstants;
/**
 * CreateAndLinkProblemAction.java  class for creating and linking a Problem Ticket to Incident within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */
public class CreateAndLinkIncidentReportSubtaskAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(CreateAndLinkIncidentReportSubtaskAction.class); 
    private Long id;
    private String key;
    private final WebResourceManager webResourceManager;
    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final SubTaskManager subTaskManager;
	private final ApplicationProperties applicationProperties;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	private final FieldMappingService fieldmappingservice;
	private final IssueFieldUtils issueFieldUtils;
	private final FieldManager fieldManager;
	/**
     * Constructor
     * @param applicationProperties the ApplicationProperties to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param webResourceManager the WebResourceManager to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
	 * @param subTaskManager the SubTaskManager to be injected
	 * @param issueManager the IssueManager to be injected
	 * @param issueFactory the IssueFactory to be injected
     */
    public CreateAndLinkIncidentReportSubtaskAction(ApplicationProperties applicationProperties,
    		IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
    		WebResourceManager webResourceManager,IssueManager issueManager, IssueFactory issueFactory,
    		SubTaskManager subTaskManager,FieldMappingService fieldmappingservice,IssueFieldUtils issueFieldUtils,FieldManager fieldManager)
    {
    	this.applicationProperties = applicationProperties;
        this.issueService = issueService;
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.fieldmappingservice = fieldmappingservice;
		this.issueFieldUtils = issueFieldUtils;
		this.fieldManager = fieldManager;
    }
    
    protected void doValidation()
    {
        includeResources();      

    }
 
	/**
     * Creating and linking Incident Report Subtask manually to Incident if transition status will be RESOLVED - PENDING REPORT REVIEW or RESOLVED - CONFIRMED or CLOSED
     */
    @Override
    public String execute() throws Exception {
        Issue issueParent = getIssueObject();
		if(issueParent!=null){
        includeResources();
        
		IssueType issueType		=	issueParent.getIssueTypeObject();
		/* Creates the Incident Report Subtask ticket if the Parent Issue is Incident */
		if( ( issueType.getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT) || issueType.getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_OUTAGE)) 
			&& getIncidentReportSubtaskLinkedIssueCount(issueParent)==0){
		
			CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();

			 try{			   
				 Status status = issueParent.getStatusObject();
				 log.info("-------------------issueParent Status---------------"+status.getName());
				 /* If the Issue transition status is either Resolved - Confirmed or Resolve - Pending Report Review or Closed */
				 if(status.getName().equalsIgnoreCase("RESOLVED - PENDING REPORT REVIEW") 
					|| status.getName().equalsIgnoreCase("RESOLVED - CONFIRMED")
					|| status.getName().equalsIgnoreCase("CLOSED")){
				 
					 // define subtask
					 MutableIssue issueObject = issueFactory.getIssue();
					 issueObject.setProjectId(issueParent.getProjectObject().getId());
					 Project project = issueParent.getProjectObject();
					 String subtaskId = getIncidentReportSubtaskTypeId(project);
					 issueObject.setIssueTypeId(subtaskId);
					 issueObject.setParentId(issueParent.getId());
					 // set subtask attributes
					 List<IRSubtaskFieldMap> dbFields = fieldmappingservice.findAllByParentAndChildIssue(issueType.getName(),"Incident Report Subtask");
				     ApplicationUser user=getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
					 if(dbFields!=null) {
				            for(IRSubtaskFieldMap fieldMap:dbFields) {				          
				            	Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
				            	issueFieldUtils.setCustomFieldValue(user,issueObject,fieldMap.getMappingField(), parentCustomFieldValue);
				            }
				        }
					 if(!isSolutionGrpFIPatner(issueParent)){// returns true if Solution Group Name :F&I Solutions (F&I)  Product Value:   Partner --> This case incident report subtask is not create.
						 /* Create Incident Report Subtask and link with Incident */
						 Issue subTask = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
						 subTaskManager.createSubTaskIssueLink(issueParent, subTask, authenticationContext.getLoggedInUser());
					 } else {
						 messages= "You cannot create Incident Report Subtask  Solution Group :F&I Solutions (F&I)  Product :   Partner ";
							addErrorMessage(messages);		       
					        return ERROR;		      
					 }
				 
				}else{
					 messages= "You cannot create an Incident Report Subtask unless the Incident is marked as Resolved - Pending Report Review, Resolved Confirmed or Closed. " +
	    				"Please update the status and try again!";
					 addErrorMessage(messages);		       
				        return ERROR;
				 }
	           }catch(Exception e){
	        	   e.printStackTrace();
	           }
			        
			        return SUCCESS;
	          
			
	        }else{
	 		        messages= "Incident Report Subtask is already created for "+issueParent.getKey();
					addErrorMessage(messages);		       
			        return ERROR;		       
	        }
        }else{
		        messages = "Parent issue is null"  ; 
				addErrorMessage(messages);		       
			        return ERROR;
		}
		
    }
 
    /**
     * Get field values from issue 
     * @param issue   - Issue
     * @param field   - Field
     * @param asOption - true/false
     * @return - Object
     */
    public Object getFieldValueFromIssue(Issue issue, Field field, boolean asOption) {
        Object retVal = null;

        try {
            if (fieldManager.isCustomField(field)) { // for custom fields
                // Return the CustomField value. It could be any object.
                CustomField customField = (CustomField) field;
                Object value = issue.getCustomFieldValue(customField);

                if (customField.getCustomFieldType() instanceof CascadingSelectCFType) {
                    HashMap<String, Option> hashMapEntries = (HashMap<String, Option>) value;

                    if (hashMapEntries != null) {
                        Option parent =  hashMapEntries.get(CascadingSelectCFType.PARENT_KEY);
                        Option child =  hashMapEntries.get(CascadingSelectCFType.CHILD_KEY);

                        if (parent != null) {
                            if (ObjectUtils.isValueSelected(child)) {
                                retVal = asOption?child:child.toString();
                            } else {
                                final List<Option> childOptions = parent.getChildOptions();

                                if ((childOptions == null) || (childOptions.isEmpty())) {
                                    retVal = asOption?parent:parent.toString();
                                }
                            }
                        }
                    }
                } else {
                    retVal = value; 
                }

                if (log.isDebugEnabled()) {
                    log.debug(
                            String.format(
                                    "Got field value [object=%s;class=%s]",
                                    retVal, ((retVal != null) ? retVal.getClass() : "")
                            )
                    );
                }
            } else { // for system fields
                String fieldId = field.getId();
                Collection<?> retCollection;

                // Special treatment of fields.
                if (fieldId.equals(IssueFieldConstants.ATTACHMENT)) {
                    // return a collection with the attachments associated to given issue.
                    retCollection = issue.getAttachments();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.AFFECTED_VERSIONS)) {
                    retCollection = issue.getAffectedVersions();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.COMMENT)) {
                    // return a list with the comments of a given issue.
                    try {
                        retCollection = issueManager.getEntitiesByIssueObject(
                                IssueRelationConstants.COMMENTS, issue
                        );

                        if (retCollection != null && !retCollection.isEmpty()) {
                            retVal = retCollection;
                        }
                    } catch (GenericEntityException e) {
                        retVal = null;
                    }
                } else if (fieldId.equals(IssueFieldConstants.COMPONENTS)) {
                    retCollection = issue.getComponentObjects();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.FIX_FOR_VERSIONS)) {
                    retCollection = issue.getFixVersions();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.THUMBNAIL)) {
                    // Not implemented, yet.
                } else if (fieldId.equals(IssueFieldConstants.ISSUE_TYPE)) {
                    retVal = issue.getIssueTypeObject();
                } else if (fieldId.equals(IssueFieldConstants.TIMETRACKING)) {
                    // Not implemented, yet.
                }else if (fieldId.equals(IssueFieldConstants.WORKRATIO)) {
                    retVal = String.valueOf(WorkRatio.getWorkRatio(issue));
                } else if (fieldId.equals(IssueFieldConstants.ISSUE_KEY)) {
                    retVal = issue.getKey();
                } else if (fieldId.equals(IssueFieldConstants.SUBTASKS)) {
                    retCollection = issue.getSubTaskObjects();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.PRIORITY)) {
                    retVal = issue.getPriorityObject();
                } else if (fieldId.equals(IssueFieldConstants.RESOLUTION)) {
                    retVal = issue.getResolutionObject();
                } else if (fieldId.equals(IssueFieldConstants.STATUS)) {
                    retVal = issue.getStatusObject();
                } else if (fieldId.equals(IssueFieldConstants.PROJECT)) {
                    retVal = issue.getProjectObject();
                } else if (fieldId.equals(IssueFieldConstants.SECURITY)) {
                    retVal = issue.getSecurityLevel();
                } else if (fieldId.equals(IssueFieldConstants.TIME_ESTIMATE)) {
                    retVal = issue.getEstimate();
                } else if (fieldId.equals(IssueFieldConstants.TIME_SPENT)) {
                    retVal = issue.getTimeSpent();
                } else if (fieldId.equals(IssueFieldConstants.AGGREGATE_TIME_SPENT)) {
                    retVal = issue.getTimeSpent();
                } else if (fieldId.equals(IssueFieldConstants.ASSIGNEE)) {
                    retVal = issue.getAssigneeUser();
                } else if (fieldId.equals(IssueFieldConstants.REPORTER)) {
                    retVal = issue.getReporterUser();
                } else if (fieldId.equals(IssueFieldConstants.DESCRIPTION)) {
                    retVal = issue.getDescription();
                } else if (fieldId.equals(IssueFieldConstants.ENVIRONMENT)) {
                    retVal = issue.getEnvironment();
                } else if (fieldId.equals(IssueFieldConstants.SUMMARY)) {
                    retVal = issue.getSummary();
                } else if (fieldId.equals(IssueFieldConstants.DUE_DATE)) {
                    retVal = issue.getDueDate();
                } else if (fieldId.equals(IssueFieldConstants.UPDATED)) {
                    retVal = issue.getUpdated();
                } else if (fieldId.equals(IssueFieldConstants.CREATED)) {
                    retVal = issue.getCreated();
                } else if (fieldId.equals(IssueFieldConstants.RESOLUTION_DATE)) {
                    retVal = issue.getResolutionDate();
                } else if (fieldId.equals(IssueFieldConstants.LABELS)) {
                    retVal = issue.getLabels();
                }else {
                    log.warn("Issue field \"" + fieldId + "\" is not supported.");

                    GenericValue gvIssue = issue.getGenericValue();

                    if (gvIssue != null) {
                        retVal = gvIssue.get(fieldId);
                    }
                }
            }
        } catch (NullPointerException e) {
            retVal = null;

            log.error("Unable to get field \"" + field.getId() + "\" value", e);
        }
        
        return retVal;
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
     * @return the Integer
     */
    public int getIncidentReportSubtaskLinkedIssueCount(Issue parent){
	    int count = 0;
	    SubTaskManager subTaskManager= ComponentAccessor.getSubTaskManager();
		Collection<Issue> linkedSubtask= subTaskManager.getSubTaskObjects(parent);		
		if(linkedSubtask!=null && linkedSubtask.size()==0){
			log.info("**********No Incident Report Subtask Ticket linked with parent issue");
			return count;
		} else {			
			for(Issue linkedSubtaskIssue : linkedSubtask){
				if(linkedSubtaskIssue!=null && linkedSubtaskIssue.getIssueTypeObject().getName().equalsIgnoreCase(CreateIRSubtaskConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)  )
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
    	if(issueResult!=null){
    	if (!issueResult.isValid())
        {
            this.addErrorCollection(issueResult.getErrorCollection());
            return null;
        }else{
		     return  issueResult.getIssue();
		}
		}
		return null;
    	
    }
    /**      
 	  * Method is used  not generate an Incident Report for any F&I – “Partner” Incidents.
    * @param issue the Issue  
    */
  public boolean isSolutionGrpFIPatner(Issue issue)  {
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

    // Getter and Setters for passing the form params

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
