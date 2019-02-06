package com.dt.jira.plugin.event;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

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
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.comments.CommentManager;

import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;

import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMap;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMapService;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;

/**
 * ComponentChangeEventListener.java event listener class for updating the manager approval cycle based on the status getting changed within Change Management Project
 */ 

public class ComponentChangeEventListener implements InitializingBean, DisposableBean {

	private final EventPublisher eventPublisher;
	private final AssigneeMapService assigneeMapService;
	private final IssueIndexingService issueIndexingService;
	Map<String,Long> statusMap = new HashMap<String, Long>();
	
	private final Logger logger = Logger.getLogger(ComponentChangeEventListener.class);
    
	public ComponentChangeEventListener(EventPublisher eventPublisher, AssigneeMapService  assigneeMapService ,IssueIndexingService issueIndexingService ) {
		this.eventPublisher = eventPublisher;	
		this.assigneeMapService = assigneeMapService;	
		this.issueIndexingService =issueIndexingService;		
		statusMap.put("Pending Approval - SME", 10800L);		
		statusMap.put("Pending Approval - Security", 10801L);
		statusMap.put("Pending Approval - CAB",10802L);
		statusMap.put("Pending Approval ENT-CAB", 10799L);
		statusMap.put("Scheduled",10803L);
		statusMap.put("Pending Approval - E-CAB1",10804L);
		statusMap.put("Pending Approval - E-CAB2",10805L);
	}

	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	try{
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		User user = ComponentAccessor.getJiraAuthenticationContext().getUser().getDirectoryUser();
		String userName  = ComponentAccessor.getJiraAuthenticationContext().getUser().getUsername();
		SubTaskManager subTaskManager = ComponentAccessor.getSubTaskManager();
		
		ChangeHistoryManager chm = ComponentAccessor.getChangeHistoryManager();
		
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issueParent = issueEvent.getIssue();
		if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {					
							
				Issue issue = issueEvent.getIssue();
				logger.debug("Issue Key>>>>>>>>>>>>>>>>>>>>"+issue.getKey()); 
				
				Status status = issue.getStatusObject();
				logger.debug("-------------------status: "+status.getName());
				Project p = issue.getProjectObject();
				String pjKey  = p.getKey();				
				logger.debug("Project Key>>>>>>>>>>>>>>>>>>>>"+p.getKey());
				
				CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
				//LazyLoadedOption sgoption =(LazyLoadedOption) issue.getCustomFieldValue(customFieldManager.getCustomFieldObjectByName("Solution Groups"));
				
				String 	sgvalue = "";
				String imvalue = "10000";
				IssueType issueType=issue.getIssueTypeObject();
				logger.debug("Issue Type Name>>>>>>>>>>>>>>>>>>>>"+issueType.getName());
								
			if(issueType.getName().equalsIgnoreCase("Change")){
					
				CustomField solGrpField = customFieldManager.getCustomFieldObjectByName("Solution Group - Product");
				logger.debug("map: "+ issue.getCustomFieldValue(solGrpField));
				Map<LazyLoadedOption, LazyLoadedOption> solGrpMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(solGrpField);
				
				for(Map.Entry<LazyLoadedOption, LazyLoadedOption> solGrpOpt : solGrpMap.entrySet()) {
					if(solGrpOpt.getKey() ==  null ){
						logger.debug("solution grp key: "+ solGrpOpt.getKey());
						LazyLoadedOption sgllo = (LazyLoadedOption) solGrpOpt.getValue();
						logger.debug("solution grp id: "+ sgllo.getOptionId());
						sgvalue = String.valueOf(sgllo.getOptionId());
						break;
					}
				}
				
				CustomField impactedField = customFieldManager.getCustomFieldObjectByName("Impacted - Function");
				logger.debug("map: "+ issue.getCustomFieldValue(impactedField));
				
				Map<LazyLoadedOption, LazyLoadedOption> impactMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(impactedField);
				
				for(Map.Entry<LazyLoadedOption, LazyLoadedOption> imopt : impactMap.entrySet()) {
					if(imopt.getKey() ==  null ){
						logger.debug("impact key: "+ imopt.getKey());
						LazyLoadedOption llo = (LazyLoadedOption) imopt.getValue();
						logger.debug("impact id: "+ llo.getOptionId());
						imvalue = String.valueOf(llo.getOptionId());
						break;
					}
				}
				
				List<ChangeItemBean> statusChHistory = chm.getChangeItemsForField(issue, "status");
				Collections.sort(statusChHistory, new Comparator<ChangeItemBean>() {
						public int compare(ChangeItemBean o1, ChangeItemBean o2) {
							return o1.getFrom().compareTo(o2.getTo());
				    }
				});
								
				List changeItems = chm.getChangeItemsForField(issue, "assignee");
				
				logger.debug("--------------------Status: "+status.getName());				
				logger.debug("--------------------Project Key: "+ pjKey);
				logger.debug("--------------------Solution Group: "+ sgvalue);
				logger.debug("--------------------Impacted Group: "+ imvalue);
				
				if(status.getName().equalsIgnoreCase("Pending Approval - SME")){	
					// Step 1.Update assignee 
					 List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					 String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					 String commentManagerName="";
					 
					 if(managerName!=null && !managerName.equals("")){
					     updateManager(issue,status.getName(),managerName);
						 commentManagerName=managerName;						 
					  } else {
						if(changeItems !=null && changeItems.size()!=0){
							ChangeItemBean ci = (ChangeItemBean) changeItems.get(0);
							managerName = ci.getTo();
							logger.debug("previousManager>>>>>>>>>>>>>>>"+managerName);
							commentManagerName = ci.getToString();
							logger.debug("commentManagerName>>>>>>>>>>>>>>>"+commentManagerName);
							updateManager(issue,status.getName(),managerName);
					   }
						logger.debug("if manager null then previous--------------------manager Name: "+ managerName);
					}
					 					 
					  logger.debug("--------------------commentManagerName: "+ commentManagerName);						 
					  if(!statusChHistory.isEmpty()) {
						     ChangeItemBean statusChItemBean = statusChHistory.get(statusChHistory.size() - 1);
						     logger.debug("statusChItemBean.getFromString-----------------"+statusChItemBean.getFromString());
						     logger.debug("statusChItemBean.getToString-------------------"+statusChItemBean.getToString());						     
						    if(!statusChItemBean.getFromString().equalsIgnoreCase(statusChItemBean.getToString())){					       
								addComment(issue,"SME Approval",commentManagerName);
						     }
					 }
					 
				}
				
				else if(status.getName().equalsIgnoreCase("Pending Approval - Security")){					
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"Security Approval",managerName);
					}
				} else if(status.getName().equalsIgnoreCase("Pending Approval - CAB")){
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"CAB Approval",managerName);
					}						
				}else if(status.getName().equalsIgnoreCase("Pending Approval ENT-CAB")){					
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"E-CAB Approval",managerName);
					}
				}else if(status.getName().equalsIgnoreCase("Scheduled")){
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"Scheduling",managerName);
					}
				} else if(status.getName().equalsIgnoreCase("Pending Approval - E-CAB1")){
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"Emergency Approval",managerName);
					}
				}  else if(status.getName().equalsIgnoreCase("Pending Approval - E-CAB2")){
					List<AssigneeMap> listAssigneeMap = assigneeMapService.getAssigneeMappingDetails(pjKey,sgvalue,imvalue);
					String managerName = getManagerFromDB(listAssigneeMap,status.getName());
					logger.debug("--------------------manager Name: "+ managerName);
					if(managerName!=null && !managerName.equals("")){						
						updateManager(issue,status.getName(),managerName);
						addComment(issue,"Emergency Approval",managerName);
						
					}
				} 			
		    }
		}
		}catch(Exception e){
		     e.printStackTrace();
		}
	}


	private void addComment(Issue issue,String status,String managerName) {		
		ApplicationUser user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
        CommentManager commentManager = ComponentAccessor.getCommentManager();		
	        try {
	        	 if(issue != null) {
		          String comment ="This issue was automatically assigned to " + managerName + " through the Status/Assignee Rules Engine Mapping for "+status;
				  commentManager.create(issue,user, comment, true);
	        	 }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	private String getManagerFromDB(List<AssigneeMap> list,String status){
		
		
		String manager = "";
		for(AssigneeMap assigneeMap: list){	 
			 Long statusIdFromMap = new Long(statusMap.get(status.trim()));
			 logger.debug("--------------------statusIdFromMap: "+ statusIdFromMap);
			 Long statusIdFromAssigneeMap = new Long(assigneeMap.getStatus());
			 logger.debug("--------------------statusIdFromAssigneeMap: "+ statusIdFromAssigneeMap);
			 if(statusIdFromMap.equals(statusIdFromAssigneeMap)){
				manager = assigneeMap.getUsers();
				break;
			}
		}
		return manager;
		
	}
	private void updateManager(Issue issueParent,String status,String manager){
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		CustomField customField =customFieldManager.getCustomFieldObjectByName("Mapped Assignee");
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		String key = issueParent.getKey();
		MutableIssue mutableIssue = issueManager.getIssueByCurrentKey(key);	
		Map<String, ModifiedValue> modifiedFields = null;
		FieldLayoutItem fieldLayoutItem = null;
		ModifiedValue modifiedValue = null;
		String test = manager;
		if(test!=null && test.length()>0){
			mutableIssue.setCustomFieldValue(customField, test);
			modifiedFields = mutableIssue.getModifiedFields();
			fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
			modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
			customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
			setReindex(mutableIssue);
		}
		logger.debug("Successfully updated: "+status);
	}
	
	private void setReindex(MutableIssue mutableIssue){
		try {
		issueIndexingService.reIndex(mutableIssue);
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("index issue" + e.getMessage());
		}
	}
	
	/**
	 * Called when the plugin has been enabled.
	 * 
	 * @throws Exception
	 */
	public void afterPropertiesSet() throws Exception {
		// register ourselves with the EventPublisher
		eventPublisher.register(this);
	}

	/**
	 * Called when the plugin is being disabled or removed.
	 * 
	 * @throws Exception
	 */
	public void destroy() throws Exception {
		// unregister ourselves with the EventPublisher
		eventPublisher.unregister(this);
	}
}

