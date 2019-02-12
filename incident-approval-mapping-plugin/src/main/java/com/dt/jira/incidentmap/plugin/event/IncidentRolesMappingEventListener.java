package com.dt.jira.incidentmap.plugin.event;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.atlassian.jira.user.ApplicationUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUsers;
import com.dt.jira.incidentmap.plugin.ao.IncidentMap;
import com.dt.jira.incidentmap.plugin.ao.IncidentMapService;
import com.dt.jira.incidentmap.plugin.constants.IRSubtaskAssigneeMapConstants;
import com.atlassian.jira.issue.index.IssueIndexingService;
/**
 * Incident Roles Mapping Event based on Incident Report Subtask Transition Status change within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IncidentRolesMappingEventListener implements InitializingBean, DisposableBean {
	private final Logger logger = Logger.getLogger(IncidentRolesMappingEventListener.class);
	private final EventPublisher eventPublisher;
	private final IncidentMapService incidentMapService;
	private final JiraAuthenticationContext authenticationContext;
	private final SubTaskManager subTaskManager;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	Map<String,Long> statusMap = new HashMap<String, Long>();
	private final IssueIndexingService issueIndexingService;
	
	/* *
     * Constructor
     * @param eventPublisher the EventPublisher to be injected
     * @param incMapService the IncidentMapService to be injected
     * @param statusMap the HashMap<String, Long> to be injected
     */
	public IncidentRolesMappingEventListener(EventPublisher eventPublisher,
											 IncidentMapService incMapService,
											 JiraAuthenticationContext authenticationContext,
											 SubTaskManager subTaskManager,
											 IssueManager issueManager,
											 IssueFactory issueFactory, IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;	
		this.incidentMapService = incMapService;
		this.authenticationContext = authenticationContext;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.issueIndexingService = issueIndexingService;
		statusMap.put("Assigned", IRSubtaskAssigneeMapConstants.ROLE_INCIDENT_REPORT_ASSIGNEE);
		statusMap.put("Pending Approval", IRSubtaskAssigneeMapConstants.ROLE_SOLUTIONTEAM_INCIDENT_MANAGER);
		statusMap.put("Pending Board Review", IRSubtaskAssigneeMapConstants.ROLE_INCIDENT_MANAGEMENT_BOARD_REVIEW_MEMEBER);
	}

	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	try{	
		
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issueParent = issueEvent.getIssue();		
		if(eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)){
			
			Status status = issueParent.getStatusObject();	
			Project project = issueParent.getProjectObject();
			String pjKey  = project.getKey();	
			
			IssueType issueType=issueParent.getIssueTypeObject();
				
			if(issueType.getName().equalsIgnoreCase(IRSubtaskAssigneeMapConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK) && issueType.isSubTask()){	
				
				CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();
				
				CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_TYPE ); 
				CustomField solGrpField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
				CustomField impactedField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
				
				String typevalue = "";
			    String childvalue = "";
			    String thirdVal = "";
			    String fourthLevelValue = "None";
			    //Reading cascade select value based on the type value either internal or external    
				if (customFieldType.getCustomFieldType() instanceof CascadingSelectCFType) {
	                HashMap<String, Option> hashMapEntriesType = (HashMap<String, Option>) issueParent.getCustomFieldValue(customFieldType);
	                if (hashMapEntriesType != null) {                	
	                    Option parentType =  hashMapEntriesType.get(CascadingSelectCFType.PARENT_KEY);
	                    Option childType =  hashMapEntriesType.get(CascadingSelectCFType.CHILD_KEY);
	                    logger.info("Selected Type:"+parentType.getValue().toString());
	                    typevalue = parentType.getValue(); 
	                    if(typevalue.equalsIgnoreCase("Internal")){
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
	                    	
	                    }else if(typevalue.equalsIgnoreCase("External")){
						   logger.info("solGrpField.getCustomFieldType() :" + solGrpField.getCustomFieldType());	               
	                            //HashMap<String, Option> hashMapEntriesSoln = (HashMap<String, Option>) issueParent.getCustomFieldValue(solGrpField);
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
				
				//Reading and compare field history based on the transition status change  
				List<ChangeItemBean> statusChHistory = chm.getChangeItemsForField(issueParent, "status");
				Collections.sort(statusChHistory, new Comparator<ChangeItemBean>() {
						public int compare(ChangeItemBean o1, ChangeItemBean o2) {
							return o1.getFrom().compareTo(o2.getTo());
				    }
				});
								
				List changeItems = chm.getChangeItemsForField(issueParent, "assignee");
				
				logger.info("Selected Approved Status:"+status.getName().toString());
				
				//Sets the assignee and update the comment based on the Pending Approval status changed
				if(status.getName().equalsIgnoreCase(IRSubtaskAssigneeMapConstants.STATUS_PENDING_APPROVAL)){					
					
					 List<IncidentMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
					 logger.info("listAssigneeMap:"+listAssigneeMap.toString());
					 String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					 logger.info("managerName:"+managerName);
					 
					 String commentManagerName="";
					 
					 if(managerName!=null && !managerName.equals("")){				
					     updateManager(issueEvent,status.getName(),managerName);	
					    
						 commentManagerName=managerName;
						 addComment(issueParent,status.getName(),commentManagerName);
					  }
					 else{
							if(changeItems !=null && !changeItems.isEmpty()){
								ChangeItemBean ci = (ChangeItemBean) changeItems.get(0);
								managerName = ci.getTo();
								commentManagerName = ci.getToString();
								updateManager(issueEvent,status.getName(),managerName);
							}
					  }
					
					  if(statusChHistory!=null && statusChHistory.size()==0) {
					     ChangeItemBean statusChItemBean = statusChHistory.get(statusChHistory.size() - 1);				     
					    if(!statusChItemBean.getFromString().equalsIgnoreCase(statusChItemBean.getToString())){					       
							addComment(issueParent,status.getName(),commentManagerName);
					     }
					   }
					  
				}
				//Sets the assignee and update the comment based on the Pending Board Review status changed
				else if(status.getName().equalsIgnoreCase(IRSubtaskAssigneeMapConstants.STATUS_PENDING_BOARD_REVIEW)){
					 List<IncidentMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.info("managerName:"+managerName);					
					 
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issueEvent,status.getName(),managerName);
						addComment(issueParent,status.getName(),managerName);
					}
					
				}		   
			  }
			}else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {	
				final MutableIssue currentIssue = (MutableIssue) issueEvent.getIssue();
				Boolean subIssueExistsCondition = currentIssue.isSubTask();
				String issueTypeName = currentIssue.getIssueTypeObject().getName();

				
				Project project = currentIssue.getProjectObject();
				String pjKey  = project.getKey();	
				
				String typevalue = "";
			    String childvalue = "";
			    String impactvalue = "";
			    String thirdVal = "";
			    String fourthLevelValue = "";
			    Status status = issueParent.getStatusObject();	
			    CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();

				try{        	
		 			if(subIssueExistsCondition && issueTypeName.equalsIgnoreCase(IRSubtaskAssigneeMapConstants.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)){
		 		
		 					try{	 						
		 						CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_TYPE ); 
		 						CustomField solGrpField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
		 						CustomField impactedField = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
		 						//Reading cascade select value based on the type value either internal or external    
		 					
		 			                HashMap<String, Option> hashMapEntriesType = (HashMap<String, Option>) issueParent.getCustomFieldValue(customFieldType);
									  logger.info("hashMapEntriesType :  "+hashMapEntriesType.toString());
		 			                if (hashMapEntriesType != null) {                	
										// Read the value for Type
		 			                    Option parentType =  hashMapEntriesType.get(CascadingSelectCFType.PARENT_KEY);
		 			                    Option childType =  hashMapEntriesType.get(CascadingSelectCFType.CHILD_KEY);
		 			                    typevalue = parentType.getValue();
		 			                    if(typevalue.equalsIgnoreCase("Internal")){
		 			                    	logger.info("Selected Location Id:"+childType.getOptionId().toString());
		 			                    	logger.info("Selected Location Name:"+childType.getValue().toString());
		 			                    	childvalue = String.valueOf(childType.getOptionId());
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
		 			                 
		 			                    	logger.info("Selected Impacted Function Name:"+thirdVal);
											// For internal fourth level field not not applicable
		 			                    	fourthLevelValue = "None";
		 			                    }else if(typevalue.equalsIgnoreCase("External")){	
		 			                            List<Option> hashMapEntriesSoln = (List<Option>) issueParent.getCustomFieldValue(solGrpField);
		 			                            if (hashMapEntriesSoln != null) {                            	   
													childvalue = getOptionValue(hashMapEntriesSoln.get(0)); // 0 - return solution group value
													if(hashMapEntriesSoln.size() > 1 ){
														thirdVal =  getOptionValue(hashMapEntriesSoln.get(1));// 1 - return Product value		 			                               
													} else {
														thirdVal = "None";
													}
													if(hashMapEntriesSoln.size() > 2 ){ // if products has childs sub - products
														fourthLevelValue =    getOptionValue(hashMapEntriesSoln.get(2));
													} else {
														fourthLevelValue = "None";
													}
		 			                            }		 			                           
		 			                          
		 			                 	    // }
		 			                    }
		 			                    
		 			                }
		 			     	     //}
								
		 						
		 						//Reading and compare field history based on the transition status change  
		 						List<ChangeItemBean> statusChHistory = chm.getChangeItemsForField(issueParent, "status");
		 						Collections.sort(statusChHistory, new Comparator<ChangeItemBean>() {
		 								public int compare(ChangeItemBean o1, ChangeItemBean o2) {
		 									return o1.getFrom().compareTo(o2.getTo());
		 						    }
		 						});
		 										
		 						List changeItems = chm.getChangeItemsForField(issueParent, "assignee");
		 						
		 						//Sets the assignee and update the comment based on the Assigned status changed
		 						if(status.getName().equalsIgnoreCase(IRSubtaskAssigneeMapConstants.STATUS_ASSIGNED)){		
		 							 List<IncidentMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
		 							 String managerName = getManagerFromDB(listAssigneeMap,status.getName());
									  logger.info("managerName:"+managerName);
									  
		 							 String commentManagerName="";
		 							 
		 							 if(managerName!=null && !managerName.equals("")){
									    //we need get first user of communications Lead update as assignee
										String communicationLead=getCommunicationsLeadUser(issueParent);
										if(communicationLead!=null && (!communicationLead.isEmpty())){
										updateManager(issueEvent,status.getName(),communicationLead);
										commentManagerName=communicationLead;												
										}else{
										updateManager(issueEvent,status.getName(),managerName);
										commentManagerName=managerName;										
										}
										// updateManager(issueEvent,status.getName(),managerName);	
		 								 commentManagerName=managerName;
		 								 addComment(issueParent,status.getName(),commentManagerName);
		 							  }
		 							 else{
		 									if(changeItems !=null && !changeItems.isEmpty()){
		 										ChangeItemBean ci = (ChangeItemBean) changeItems.get(0);
		 										managerName = ci.getTo();
												commentManagerName = ci.getToString();
												//we need get first user of communications Lead update as assignee
												String communicationLead=getCommunicationsLeadUser(issueParent);
												if(communicationLead!=null && (!communicationLead.isEmpty())){
												updateManager(issueEvent,status.getName(),communicationLead);	
												}else{
												updateManager(issueEvent,status.getName(),managerName);	
												}
										
												}
		 							  }
		 							 
		 							  if(statusChHistory==null && statusChHistory.size()==0) {		 								 
		 							     ChangeItemBean statusChItemBean = statusChHistory.get(statusChHistory.size());
										 if(!statusChItemBean.getFromString().equalsIgnoreCase(statusChItemBean.getToString())){					       
		 									addComment(issueParent,status.getName(),commentManagerName);
		 							     }
		 							   }
		 							  
		 						}

		 					}catch(Exception e){
		 						e.printStackTrace();
		 					}
		        			
		 				//}
			 		  }
					}catch(Exception e){
						e.printStackTrace();
					}
			    
				
				
			    
			}		
			}catch(Exception e){
			     e.printStackTrace();
			}
	}

	/**
	 * Update the comment based on the status changed.
	 */
	private void addComment(Issue issue,String status,String managerName) {		
		User user = ComponentAccessor.getJiraAuthenticationContext().getUser().getDirectoryUser();
		String assignedManager=ComponentAccessor.getUserManager().getUserByName(managerName).getDisplayName();
        CommentManager commentManager = ComponentAccessor.getCommentManager();	
	        try {
	        	 if(issue != null) {
	        	  String comment ="This issue was automatically assigned to " + assignedManager + " through the Incident Report Assignee Mapping";
				  commentManager.create(issue, ApplicationUsers.from(user), comment, true);
	        	 }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
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
	
	
	
	public String getCommunicationsLeadUser(Issue issueParent) {
	try{
		  Issue issue=issueParent;
	      if(issueParent.isSubTask()){
		  issue=issueParent.getParentObject();
		  }
			 CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
			 CustomField communicationLead = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.COMPONENT_COMMUNICATIONS_LEAD);
			 List<ApplicationUser> userlist = (List<ApplicationUser>) issue.getCustomFieldValue(communicationLead);
			 	 if(userlist!=null && (!userlist.isEmpty())){
						return userlist.get(0).getName();
			         }	
			}catch(Exception e){
			     e.printStackTrace();
			}
			
			return null;
	}
	
	
	
	/**
	 * Update MappedAssigneeIncident custom field and sets field value to actual assignee of issue with the help of post function.
	 */
	private void updateManager(IssueEvent event,String status,String manager){	
    	try{
		final MutableIssue issueParent = (MutableIssue) event.getIssue();
		if  (issueParent!=null) {
				if(manager!=null){
					ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(manager.trim());
				    if(user!=null && user.isActive()){			
						IssueManager issueManager = ComponentAccessor.getIssueManager();
						issueParent.setAssigneeId(user.getKey());
						Issue newIssue = issueManager.updateIssue(authenticationContext.getLoggedInUser(),issueParent,EventDispatchOption.ISSUE_UPDATED,true);
					}else{
					    logger.info("================User is Not Available ");
					}
				}
			}				
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Get incident report mapping details from the DB
	 */
	private List<IncidentMap> getIncidentDetailsById(String pjKey,String typevalue,String childvalue,String thirdVal,String fourthLevelVal){
		logger.info("pjkey: "+ pjKey + " typevalue: "+ typevalue + " childvalue: "+ childvalue + " thirdVal: "+ thirdVal + "fourthLevelVal: "+ fourthLevelVal );
		List<IncidentMap> listAssigneeMap = null;
		listAssigneeMap = incidentMapService.getIncidentMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,fourthLevelVal);
		if(listAssigneeMap != null && listAssigneeMap.size()>0 ){
			 return listAssigneeMap;
		} else {
			listAssigneeMap = incidentMapService.getIncidentMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,"None");
		}
		 return listAssigneeMap;
	}
	
	private String getOptionValue(Option o){
		String optVal = "None";
		if(o!=null){
			optVal = String.valueOf(o.getOptionId());		
		}	
	return optVal;
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

