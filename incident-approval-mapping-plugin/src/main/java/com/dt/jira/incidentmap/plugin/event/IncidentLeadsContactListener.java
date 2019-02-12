package com.dt.jira.incidentmap.plugin.event;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.changehistory.ChangeHistoryItem;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.dt.jira.incidentmap.plugin.ao.IncidentMap;
import com.dt.jira.incidentmap.plugin.ao.IncidentMapService;
import com.dt.jira.incidentmap.plugin.constants.IRSubtaskAssigneeMapConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

/**
 * Incident Roles Mapping Event based on Incident Report Subtask Transition Status change within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IncidentLeadsContactListener implements InitializingBean, DisposableBean {
	private final Logger logger = Logger.getLogger(IncidentLeadsContactListener.class);
	private final EventPublisher eventPublisher;
	private final IncidentMapService incidentMapService;
	private final JiraAuthenticationContext authenticationContext;
	private final SubTaskManager subTaskManager;
	private final IssueManager issueManager;
	private final UserManager userManager;
	private final IssueFactory issueFactory;
	private final String projectKey="ITIM";
	Map<String,Long> statusMap = new HashMap<String, Long>();
	private final IssueIndexingService issueIndexingService;
	
	/* *
     * Constructor
	 * @param statusMap the HashMap<String, Long> to be injected
	 * @param eventPublisher the EventPublisher to be injected
	 * @param incMapService the IncidentMapService to be injected
	 * @param issueIndexingService
	 */
	public IncidentLeadsContactListener(EventPublisher eventPublisher,
										IncidentMapService incMapService,
										JiraAuthenticationContext authenticationContext,
										SubTaskManager subTaskManager,
										IssueManager issueManager,
										UserManager userManager,
										IssueFactory issueFactory, IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;	
		this.incidentMapService = incMapService;
		this.authenticationContext = authenticationContext;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.userManager = userManager;
		this.issueFactory = issueFactory;
		this.issueIndexingService = issueIndexingService;
	}

	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	try{	
		
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issueParent = issueEvent.getIssue();		
		Status status = issueParent.getStatusObject();	
		Project project = issueParent.getProjectObject();
		String pjKey  = project.getKey();	
		IssueType issueType=issueParent.getIssueTypeObject();
		logger.info(projectKey+"========IncidentLeadsContactListener==== start======"+project.getKey());	
			 if (projectKey.equals(project.getKey()) &&
					 (eventTypeId.equals(EventType.ISSUE_CREATED_ID) || eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) &&
					 (!issueType.isSubTask())) {
                 List<String> fieldsChanged = getFieldsUpdated(issueParent);
				logger.info(issueType.isSubTask()+"----------if block-----------"+eventTypeId);
				 //get solution group products
				 CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				 CustomField solGrpField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);	
				 CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_TYPE ); 
				 CustomField impactedField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
					
				 
				 String type="";
				 String childvalue="";
				 String thirdVal="";
				 String fourthLevelValue="";
				 if (customFieldType.getCustomFieldType() instanceof CascadingSelectCFType) {
		                HashMap<String, Option> hashMapEntriesType = (HashMap<String, Option>) issueParent.getCustomFieldValue(customFieldType);
		                if (hashMapEntriesType != null) {                	
		                    Option parentType =  hashMapEntriesType.get(CascadingSelectCFType.PARENT_KEY);
		                    Option childType =  hashMapEntriesType.get(CascadingSelectCFType.CHILD_KEY);
		                    logger.info("Selected Type:"+parentType.getValue().toString());
		                    type = parentType.getValue(); 
		                    if(type.equalsIgnoreCase("Internal")){
		                    	logger.info("Selected Location Id:"+childType.getOptionId().toString());
		                    	logger.info("Selected Location Name:"+childType.getValue().toString());
								childvalue = String.valueOf(childType.getOptionId());
		                    	if (impactedField.getCustomFieldType() instanceof CascadingSelectCFType) {
		                            HashMap<String, Option> hashMapEntriesImpact = (HashMap<String, Option>) issueParent.getCustomFieldValue(impactedField);
		                            if (hashMapEntriesImpact != null) {                            	
		                                Option parentSoln =  hashMapEntriesImpact.get(CascadingSelectCFType.PARENT_KEY);	                                
		                                  logger.info("Selected Impact value is empty:"+parentSoln.getValue().isEmpty());
			                              logger.info("Selected Impact value:"+parentSoln.getValue());
			                               if(parentSoln!=null && !parentSoln.getValue().isEmpty()){
				                                logger.info("Selected Impact Id:"+parentSoln.getOptionId().toString());
				                                logger.info("Selected Impact Name:"+parentSoln.getValue().toString());		 			                                
				                                //thirdVal = String.valueOf(parentSoln.getValue());
												thirdVal = String.valueOf(parentSoln.getOptionId());
			                               }else{
			                            	   thirdVal = "None";  
			                               }
		                            }else{
		                            	   thirdVal = "None";  
		                               }
		                 	     }
		                    	
		                    	 logger.info("Selected Impacted Function Name:"+thirdVal);
		                    	
		                    }else if(type.equalsIgnoreCase("External")){
		                    	/*HashMap<String, Option> hashMapEntriesType = (HashMap<String, Option>) issueParent.getCustomFieldValue(customFieldType);
		    	                if (hashMapEntriesType != null) {                	
		    	                    Option parentType =  hashMapEntriesType.get(CascadingSelectCFType.PARENT_KEY);
		    	                    logger.info("parentType :" + parentType.getValue().toString());
		    	                    type=parentType.getValue().toString();
		    	                
		    	                }
		    				 */
		    	                //logger.info("solGrpField.getCustomFieldType() :" + solGrpField.getCustomFieldType());	               
		                          List<Option> hashMapEntriesSoln = (List<Option>) issueParent.getCustomFieldValue(solGrpField);
		                          logger.info("solution group map :" + hashMapEntriesSoln.toString());
		                         
		                          if (hashMapEntriesSoln != null) {                            	   
		    							childvalue = getOptionValue(hashMapEntriesSoln.get(0)); // 0 - return solution group value
		    							if(hashMapEntriesSoln.size() > 1 ){
		    								thirdVal =  getOptionValue(hashMapEntriesSoln.get(1));// 1 - return Product value		 			                               
		    							} else {
		    								thirdVal = "None";
		    							}
		    							if(hashMapEntriesSoln.size() > 2 ){ // if products has childs sub - products
		    								fourthLevelValue = getOptionValue(hashMapEntriesSoln.get(2));
		    							} else {
		    								fourthLevelValue = "None";
		    							}
		    						}
		                    }
				 
		                }
				 }
                      logger.info("project.getKey(): "+project.getKey()+" type: "+type+" childvalue: "+childvalue+" thirdVal: "+thirdVal+" fourthLevelValue: "+fourthLevelValue);
                      List<IncidentMap> listAssigneeMap= getIncidentDetailsById(project.getKey(),type,childvalue,thirdVal,fourthLevelValue);
					  if(listAssigneeMap==null || listAssigneeMap.isEmpty()){
						  //Allow to edit the lead contact deatils when mapping is empty
						  if((eventTypeId.equals(EventType.ISSUE_UPDATED_ID))&&(
								  fieldsChanged.contains(IRSubtaskAssigneeMapConstants.COMPONENT_COMMUNICATIONS_LEAD)||
										  (fieldsChanged.contains(IRSubtaskAssigneeMapConstants.COMPONENT_TECH_LEAD)) ||
										  (fieldsChanged.contains(IRSubtaskAssigneeMapConstants.COMPONENT_CUST_IMPACT_LEAD)) ||
										  (fieldsChanged.contains(IRSubtaskAssigneeMapConstants.COMPONENT_CUST_SERVICE_CONTACT))

						  			)){
							  return;

						  }else{
							  //clearing lead contact feilds as no mapping found
							  updateIssueTextField((MutableIssue) issueParent, "", IRSubtaskAssigneeMapConstants.COMPONENT_COMMUNICATIONS_LEAD);
							  updateIssueTextField((MutableIssue) issueParent, "", IRSubtaskAssigneeMapConstants.COMPONENT_TECH_LEAD);
							  updateIssueTextField((MutableIssue) issueParent, "", IRSubtaskAssigneeMapConstants.COMPONENT_CUST_IMPACT_LEAD);
							  updateIssueTextField((MutableIssue) issueParent, "", IRSubtaskAssigneeMapConstants.COMPONENT_CUST_SERVICE_CONTACT);
						  }
              	    	
					  }else{
					  String commLead="";
					  String teachLead="";
					  String cusContLead="";
					  String cusServLead="";
					  //Take the lead values from AO only if solution groups - products or clients impacted or impacted function has changed.
                           //Also take it from AO if the incident is created for the first time
                            if (eventTypeId.equals(EventType.ISSUE_CREATED_ID) ||
                                    fieldsChanged.contains(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT) ||
                                    fieldsChanged.contains(IRSubtaskAssigneeMapConstants.FIELD_TYPE) ||
                                    fieldsChanged.contains(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION)
                                ) {
					   for(IncidentMap incidentMap:listAssigneeMap){
                            String role= incidentMap.getRoles();

                           
								if(IRSubtaskAssigneeMapConstants.ROLE_COMMUNICATIONS_LEAD.equals(role)){
									commLead=incidentMap.getUsers();
                                   
                                }
                                else if(IRSubtaskAssigneeMapConstants.ROLE_TECH_LEAD.equals(role)){
									teachLead=incidentMap.getUsers();
                                }
                                else if(IRSubtaskAssigneeMapConstants.ROLE_CUST_IMPACT_LEAD.equals(role)){
                                    cusContLead=incidentMap.getUsers();
								}
                                else if(IRSubtaskAssigneeMapConstants.ROLE_CUST_SERVICE_CONTACT.equals(role)){
									cusServLead=incidentMap.getUsers();
                                }
                            }
							
							 updateIssueTextField((MutableIssue)issueParent,commLead,IRSubtaskAssigneeMapConstants.COMPONENT_COMMUNICATIONS_LEAD);
							 updateIssueTextField((MutableIssue)issueParent,teachLead,IRSubtaskAssigneeMapConstants.COMPONENT_TECH_LEAD);
                             updateIssueTextField((MutableIssue)issueParent,cusContLead,IRSubtaskAssigneeMapConstants.COMPONENT_CUST_IMPACT_LEAD);
                             updateIssueTextField((MutableIssue)issueParent,cusServLead,IRSubtaskAssigneeMapConstants.COMPONENT_CUST_SERVICE_CONTACT);
                                      
                        }
					   }
                      
                      
                      
                      
                      logger.info("========IncidentLeadsContactListener==== end======");	
			 }	
			 
			 
			}catch(Exception e){
			     e.printStackTrace();
			}
	}

    /**
     * Helper method to get the list of fields updated during create or update
     *
     * @param issueParent
     * @return
     */
    private List<String> getFieldsUpdated(Issue issueParent) {
        ChangeHistoryManager changeHistoryManager = ComponentAccessor.getChangeHistoryManager();
        List<ChangeHistoryItem> changeHistoryItems = changeHistoryManager.getAllChangeItems(issueParent);
        List<String> fieldsChanged = new ArrayList<String>();
        if (!changeHistoryItems.isEmpty()) {
            ChangeHistoryItem latestChangeHistory = changeHistoryItems.get(changeHistoryItems.size() - 1); //latest change history item
            long latestChangeGroupId = latestChangeHistory.getChangeGroupId();
            logger.info("LATEST CHANGE GROUP ID " + latestChangeGroupId);

            for (ChangeHistoryItem changeHistoryItem : changeHistoryItems) {
                if (changeHistoryItem.getChangeGroupId() == latestChangeGroupId) {
                    logger.info("Field changed for the change group id " + changeHistoryItem.getChangeGroupId() +
                            " is " +  changeHistoryItem.getField());
                    fieldsChanged.add(changeHistoryItem.getField());
                }
            }
        }
        return fieldsChanged;
    }


    /**
	 * Update LDAP Integration status with new  value Success/Failed. 
	 * @param issue
	 * @param newvalue
	 */
	/*private void updateingIssue(MutableIssue issue,String newvalue ,String customComponentName){
		IssueManager imanager=ComponentAccessor.getIssueManager();
		JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
		CustomFieldManager cfManager = ComponentManager.getInstance().getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(customComponentName);
		logger.info(customFeildObj.getValue(issue)+"------newValue------"+newvalue);
		List<Option> optList=optionsManager.findByOptionValue(newvalue);
		logger.info(optList+"-----------------------");
		if(optList!=null && (!optList.isEmpty())){
			logger.info("------------------"+optList.get(0));
			customFeildObj.updateValue(null, issue, new ModifiedValue(
					customFeildObj.getValue(issue),optList.get(0)), new DefaultIssueChangeHolder());
			issue.store();
			logger.info("-----new Option-------------"+optList.get(0).getValue());
		}
		
	}*/
	
	/**
	 * update Issue TextField
	 * @param issue
	 * @param newvalue
	 */
	private void updateIssueTextField(MutableIssue issue,String newvalue,String customComponentName){
		logger.info(issue+"------issue------"+newvalue+"------newvalue------"+customComponentName+"------customComponentName------");
		try{
		JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(customComponentName);
		logger.info(customFeildObj.getValue(issue)+"-------------custom feild value----------");
		customFeildObj.updateValue(null, issue, new ModifiedValue(customFeildObj.getValue(issue),getUsersList(newvalue)), new DefaultIssueChangeHolder());
		issue.store();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private Collection<ApplicationUser> getUsersList(String userNames){
		logger.info("--------userNames-----------"+userNames);
		List<ApplicationUser> applicationUserList=null;
		if(userNames!=null && (!userNames.isEmpty())){
		String[] users=userNames.split(",");
		logger.info("--------users-----------"+users);
		applicationUserList=new ArrayList<ApplicationUser>();
		for(String userName:users){
			logger.info("-------user-------"+userName);
			String user=userName.trim();
			if(user!=null && (!user.isEmpty())){
				ApplicationUser applicationUser=userManager.getUserByName(user.trim());	
			logger.info("---------applicationUser-------"+applicationUser);
			if(applicationUser!=null){
			applicationUserList.add(applicationUser);
			  }
			}
		}
	}
	logger.info("-------applicationUserList----------"+applicationUserList);
	if(applicationUserList!=null && applicationUserList.isEmpty()){
		return null;
	}
	return applicationUserList;
	}
	
	
	private String getOptionValue(Option o){
		String optVal = "None";
		if(o!=null){
			optVal = String.valueOf(o.getOptionId());		
		}	
	return optVal;
	}

	
	/**
	 * Reads/Sets Assignee from db and compare with role map based on the status changed.
	 */
	private String getManagerFromDB(List<IncidentMap> list,String roles){	
		
		String manager = "";
		for(IncidentMap incMap: list){
			 long rolesId = (Long) statusMap.get(roles.trim());
			 logger.info(" rolesId "+rolesId + " incMap.getRoles()  ****" + incMap.getRoles());
			if(String.valueOf(rolesId).equalsIgnoreCase(incMap.getRoles())){
				manager = incMap.getUsers();
				break;
			}
		}
		return manager;
	}
	

	/**
	 * Get incident report mapping details from the DB
	 */
	private List<IncidentMap> getIncidentDetailsById(String pjKey,String typevalue,String childvalue,String thirdVal,String fourthLevelVal){
		logger.info("pjkey: "+ pjKey + " typevalue: "+ typevalue + " childvalue: "+ childvalue + " thirdVal: "+ thirdVal + "fourthLevelVal: "+ fourthLevelVal );
		if("-1".equals(thirdVal)){
			thirdVal="None"	;
		}
		if("-1".equals(fourthLevelVal)){
			fourthLevelVal="None";
		}
		List<IncidentMap> listAssigneeMap = null;
		listAssigneeMap = incidentMapService.getIncidentMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,fourthLevelVal);
		logger.info("--------listAssigneeMap----------"+listAssigneeMap);
		if(listAssigneeMap != null && listAssigneeMap.size()>0 ){
			 return listAssigneeMap;
		} else {
			listAssigneeMap = incidentMapService.getIncidentMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,"None");
		}
		 return listAssigneeMap;
	}
	
	
	/**
	 * Re-indexing issue after successfully updation.
	 */
	private void setReindex(MutableIssue mutableIssue){
	 try {
			// ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
         issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.debug("index issue" + ie.getMessage());
		}
	}

	
	
	/**
	 * Called when the plugin has been enabled.
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		eventPublisher.register(this);
	}

	/**
	 * Called when the plugin is being disabled or removed.
	 * 
	 * @throws Exception
	 */
	public void destroy() throws Exception {
		eventPublisher.unregister(this);
	}
}

