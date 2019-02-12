package com.dt.jira.rcamap.plugin.event;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.atlassian.jira.user.ApplicationUser;
import org.apache.log4j.Logger;
import com.atlassian.jira.event.type.EventDispatchOption;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.atlassian.crowd.embedded.api.User;
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
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUsers;
import com.dt.jira.rcamap.plugin.ao.RCASubtaskMap;
import com.dt.jira.rcamap.plugin.ao.RCASubtaskMapService;
import com.dt.jira.rcamap.plugin.constants.RCASubtaskAssigneeMapConstants;
import com.atlassian.jira.issue.index.IssueIndexingService;

/**
 * RCA Report Subtask Roles Mapping Event based on RCA Report Subtask Transition Status change within Problem Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class RCASubtaskRolesMappingEventListener implements InitializingBean, DisposableBean {
	private final Logger logger = Logger.getLogger(RCASubtaskRolesMappingEventListener.class);
	private final EventPublisher eventPublisher;
	private final RCASubtaskMapService rcaMapService;
	private final JiraAuthenticationContext authenticationContext;
	private final SubTaskManager subTaskManager;
	private final IssueManager issueManager;
	private final IssueFactory issueFactory;
	
	
	private final IssueService issueService;
	private final IssueLinkManager issueLinkManager;

	private final IssueIndexingService issueIndexingService;

	/**
	 *
	 * @param eventPublisher
	 * @param rcaMapService
	 * @param authenticationContext
	 * @param subTaskManager
	 * @param issueManager
	 * @param issueFactory
	 * @param issueService
	 * @param issueLinkManager
     * @param issueIndexingService
     */
	public RCASubtaskRolesMappingEventListener(EventPublisher eventPublisher,
											   RCASubtaskMapService rcaMapService,
											   JiraAuthenticationContext authenticationContext,
											   SubTaskManager subTaskManager,
											   IssueManager issueManager,
											   IssueFactory issueFactory,
											   IssueService issueService,
											   IssueLinkManager issueLinkManager, IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;	
		this.rcaMapService = rcaMapService;
		this.authenticationContext = authenticationContext;
		this.subTaskManager= subTaskManager;
		this.issueManager = issueManager;
		this.issueFactory = issueFactory;
		this.issueService = issueService;
		this.issueLinkManager= issueLinkManager;

		this.issueIndexingService = issueIndexingService;
	}

	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	try{	
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issueParent = issueEvent.getIssue();
		logger.info("IssueParent Key:"+issueParent.getKey());
		
		if(eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)){
			
			Status status = issueParent.getStatusObject();	
			logger.info("Status:"+status.getName());	
			Project project = issueParent.getProjectObject();
			String pjKey  = project.getKey();	
			logger.info("Project Key:"+pjKey);	
			IssueType issueType=issueParent.getIssueTypeObject();
			logger.info("Current Issue Type Name:"+issueType.getName());	
			//creates RCA Report Subtask from problem and assigning approver from RCA Report Subtask Mapping as per the workflow transition
			if(issueType.getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK) && issueType.isSubTask()){
				Map<String,Long> statusMap = new HashMap<String, Long>();
				statusMap.put("Assigned", RCASubtaskAssigneeMapConstants.ROLE_RCA_REPORT_ASSIGNEE);//Mapping from UI - RCA Report Assignee. Id is used to store in DB. It wont be changed from Dev to Production
				statusMap.put("Pending Approval", RCASubtaskAssigneeMapConstants.ROLE_RCA_APPROVER);//Mapping from UI - RCA Approver. Id is used to store in DB. It wont be changed from Dev to Production
				statusMap.put("Pending Board Review", RCASubtaskAssigneeMapConstants.ROLE_ENTERPRISE_BOARD_REVIEW_MEMEBER);//Mapping from UI - Enterprise Board Review Member. Id is used to store in DB. It wont be changed from Dev to Production.
				
				CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();
				
				CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_TYPE); 
				CustomField solGrpField = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
				CustomField impactedField = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
				
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

	                    	//childvalue = String.valueOf(childType.getValue());
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
	                    	//if (solGrpField.getCustomFieldType() instanceof CascadingSelectCFType) {
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
								
	                 	     //}                    		                    	
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
				if(status.getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.STATUS_PENDING_APPROVAL)){					
					
					 List<RCASubtaskMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
					 String managerName = getManagerFromDB(listAssigneeMap,status.getName(),statusMap);
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
				else if(status.getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.STATUS_PENDING_BOARD_REVIEW)){
					List<RCASubtaskMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName(),statusMap);
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
				String pjKey = project.getKey();
				
				String typevalue = "";
			    String childvalue = "";
			    String thirdVal = "";
			    String fourthLevelValue = "";
				
			    Status status = issueParent.getStatusObject();	
			    CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();
                
				logger.info("=======================issueTypeName======================="+issueTypeName);
				//creates problem from incident and assigning second level of approver from RCA Report Subtask Mapping - RCA Approver
				createProblemFromIncident(issueEvent);				
				
				try{  
				//creates RCA Report Subtask from problem and assigning first level of approver from RCA Report Subtask Mapping - RCA Report Assignee 
		 			if(subIssueExistsCondition && issueTypeName.equalsIgnoreCase(RCASubtaskAssigneeMapConstants.ISSUE_TYPE_RCA_REPORT_SUBTASK)){
		 				
		 				final MutableIssue parentIssue = (MutableIssue) currentIssue.getParentObject(); 				
		 				if(parentIssue!=null && parentIssue.getIssueTypeObject().getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.ISSUE_TYPE_PROBLEM)){
		 					try{	 						
		 						CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_TYPE); 
		 						CustomField solGrpField = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
		 						CustomField impactedField = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
		 						Map<String,Long> statusMap = new HashMap<String, Long>();
		 						statusMap.put("Assigned", RCASubtaskAssigneeMapConstants.ROLE_RCA_REPORT_ASSIGNEE); //Mapping from UI - RCA Report Assignee. Id is used to store in DB. It wont be changed from Dev to Production 
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

		 			                    	//childvalue = String.valueOf(childType.getValue());
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
												fourthLevelValue = "None";
		 			                 	     }
		 			                    	logger.info("Selected Impacted Function Name:"+thirdVal);
		 			                    	
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
		 			                 	     //}
		 			                    }		 			                    
		 			                }
		 			     	     }
		 						System.out.println("typevalue: "+ typevalue + " childvalue "+ childvalue + " thirdVal: "+ thirdVal + " fourthLevelValue "+ fourthLevelValue );
		 						//Reading and compare field history based on the transition status change  
		 						List<ChangeItemBean> statusChHistory = chm.getChangeItemsForField(issueParent, "status");
		 						Collections.sort(statusChHistory, new Comparator<ChangeItemBean>() {
		 								public int compare(ChangeItemBean o1, ChangeItemBean o2) {
		 									return o1.getFrom().compareTo(o2.getTo());
		 						    }
		 						});
		 										
		 						List changeItems = chm.getChangeItemsForField(issueParent, "assignee");
		 						
		 						//Sets the assignee and update the comment based on the Assigned status changed
		 						if(status.getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.STATUS_ASSIGNED)){		
		 							 List<RCASubtaskMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
		 							 String managerName = getManagerFromDB(listAssigneeMap,status.getName(),statusMap);
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
		        			
		 				}
			 		  }
					}catch(Exception e){
						e.printStackTrace();
					}
			    
				
			    
				}		
			}catch(Exception e){
			     e.printStackTrace();
			}
	}

	//creates problem from incident and assigning second level of approver from RCA Report Subtask Mapping - RCA Approver
	private void createProblemFromIncident(IssueEvent event) {
    	Issue issueParent = event.getIssue();
		String typevalue = "";
	    String childvalue = "";
	    String thirdVal = "";
	    String fourthLevelValue = "";
	    Status status = issueParent.getStatusObject();	
		Project project = issueParent.getProjectObject();
		String pjKey = project.getKey();
				
	    CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();

		IssueType issueType=issueParent.getIssueTypeObject();
		
		logger.info("=======================Issue type in create event mapping======================="+issueType.getName());
		if(issueType.getName().equalsIgnoreCase("Problem")) {
		
		CustomField solutionGroupsCustomField 		= cfm.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
		CustomField impactedFunctionCustomField 	= cfm.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
		CustomField typeCustomField 				= cfm.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_TYPE);
		
		Map<String,Long> statusMap = new HashMap<String, Long>();
		statusMap.put("Assigned", RCASubtaskAssigneeMapConstants.ROLE_RCA_APPROVER); // Problem ticket will be assigned to second level approver from the RCA Subtask assigne mapping 
		HashMap<String, Option> hashMapEntriesTypes = (HashMap<String, Option>) issueParent.getCustomFieldValue(typeCustomField);
        if (hashMapEntriesTypes != null) {                	
            Option parentType =  hashMapEntriesTypes.get(CascadingSelectCFType.PARENT_KEY);
            Option childType =  hashMapEntriesTypes.get(CascadingSelectCFType.CHILD_KEY);
            logger.info("Selected Type:"+parentType.getValue().toString());
            typevalue = parentType.getValue();
            if(typevalue.equalsIgnoreCase("Internal")){
            	logger.info("Selected Location Id:"+childType.getOptionId().toString());
            	logger.info("Selected Location Name:"+childType.getValue().toString());

            	//childvalue = String.valueOf(childType.getValue());
				childvalue = String.valueOf(childType.getOptionId());
            	
            	if (impactedFunctionCustomField.getCustomFieldType() instanceof CascadingSelectCFType) {
                    HashMap<String, Option> hashMapEntriesImpact = (HashMap<String, Option>) issueParent.getCustomFieldValue(impactedFunctionCustomField);
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
					fourthLevelValue = "None";
         	     }
            	logger.info("Selected Impacted Function Name:"+thirdVal);
            	
            }else if(typevalue.equalsIgnoreCase("External")){
            	
            	//if (solutionGroupsCustomField.getCustomFieldType() instanceof CascadingSelectCFType) {
                    List<Option> hashMapEntriesSoln = (List<Option>) issueParent.getCustomFieldValue(solutionGroupsCustomField);
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
         	     //}
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
	
	//Sets the assignee and update the comment based on the Assigned status changed
	if(status.getName().equalsIgnoreCase(RCASubtaskAssigneeMapConstants.STATUS_ASSIGNED)){		
		 List<RCASubtaskMap> listAssigneeMap = getIncidentDetailsById(pjKey, typevalue, childvalue, thirdVal, fourthLevelValue);
		 String managerName = getManagerFromDB(listAssigneeMap,status.getName(),statusMap);
		 String commentManagerName="";
		 
		 if(managerName!=null && !managerName.equals("")){
			 updateManager(event,status.getName(),managerName);	
			 commentManagerName=managerName;
			 addComment(issueParent,status.getName(),commentManagerName);
		  }
		 else{
				if(changeItems !=null && !changeItems.isEmpty()){
					ChangeItemBean ci = (ChangeItemBean) changeItems.get(0);
					managerName = ci.getTo();
					commentManagerName = ci.getToString();
					updateManager(event,status.getName(),managerName);
					}
		  }
		 
		  if(statusChHistory==null && statusChHistory.size()==0) {		 								 
		     ChangeItemBean statusChItemBean = statusChHistory.get(statusChHistory.size());
			 if(!statusChItemBean.getFromString().equalsIgnoreCase(statusChItemBean.getToString())){					       
				addComment(issueParent,status.getName(),commentManagerName);
		     }
		   }		  
		 }
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
	        	  String comment ="This issue was automatically assigned to " + assignedManager + " through the RCA Report Subtask Assignee Mapping";
				  commentManager.create(issue, ApplicationUsers.from(user), comment, true);
	        	 }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	
	/**
	 * Reads/Sets Assignee from db and compare with role map based on the status changed.
	 */
	private String getManagerFromDB(List<RCASubtaskMap> list,String roles, Map statusMap){	
		
		String manager = "";
		for(RCASubtaskMap incMap: list){
			 long rolesId = (Long) statusMap.get(roles.trim());
			if(String.valueOf(rolesId).equalsIgnoreCase(incMap.getRoles())){
				manager = incMap.getUsers();
				break;
			}
		}
		return manager;
		
	}
	
	/**
	 * Update MappedAssigneeIncident custom field and sets field value to actual assignee of issue with the help of post function.
	 */
	private void updateManager(IssueEvent event, String status, String manager){		
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
	 * Re-indexing issue after successfully updation.
	 */
	private void setReindex(MutableIssue mutableIssue){
	 try {
			//ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
		 issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.debug("index issue" + ie.getMessage());
		}
	}
/**
	 * Get incident report mapping details from the DB
	 */
	private List<RCASubtaskMap> getIncidentDetailsById(String pjKey,String typevalue,String childvalue,String thirdVal,String fourthLevelVal){
		logger.info("pjkey: "+ pjKey + " typevalue: "+ typevalue + " childvalue: "+ childvalue + " thirdVal: "+ thirdVal + "fourthLevelVal: "+ fourthLevelVal );
		List<RCASubtaskMap> listAssigneeMap = null;
		listAssigneeMap = rcaMapService.getRcaReportSubtaskMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,fourthLevelVal);
		if(listAssigneeMap != null && listAssigneeMap.size()>0 ){
			 return listAssigneeMap;
		} else {
			listAssigneeMap = rcaMapService.getRcaReportSubtaskMappingParentChildDetailsById(pjKey,typevalue,childvalue,thirdVal,"None");
		}
		if(listAssigneeMap != null && listAssigneeMap.size()>0 )
			logger.info(" listAssigneeMap: "+ listAssigneeMap.size() );
		 return listAssigneeMap;
	}
	/**
	 * Get option values for solution group, product and sub - product the DB
	 */
	private String getOptionValue(Option o){
		String optVal = "None";
		if(o!=null){
			optVal = String.valueOf(o.getOptionId());		
		}	
	return optVal;
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

