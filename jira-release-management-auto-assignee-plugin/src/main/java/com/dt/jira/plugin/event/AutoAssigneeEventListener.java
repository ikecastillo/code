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

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
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
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.jira.plugin.service.AutoAssigneeService;
import com.dt.jira.plugin.service.AutoAssigneeServiceImpl;
import com.dt.jira.plugin.ao.AutoAssigneePortal;

/**
 * AutoAssigneeEventListener.java event listener class for updating the
 * Assignee on the status update.
 */

public class AutoAssigneeEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final AutoAssigneeService autoAssigneeService;
	

	private final Logger logger = Logger.getLogger(AutoAssigneeEventListener.class); 

	
	public AutoAssigneeEventListener(EventPublisher eventPublisher,AutoAssigneeService autoAssigneeService){
		this.eventPublisher = eventPublisher;
		this.autoAssigneeService=autoAssigneeService;
	}

	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		try {
            Long eventTypeId = issueEvent.getEventTypeId();
			Issue issueParent = issueEvent.getIssue();
			Map<String,Object> issueEventMap=issueEvent.getParams();
			if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
				//need to be made to one step but.
				logger.info("Executed issue created");
				updateIssueByAssignee(issueEvent);
			}else{
			if((issueEventMap!=null && ("workflow").equals(issueEventMap.get("eventsource")))){
				    //update assignee from mapping
				   logger.info("Executed on Status Change");
				   updateIssueByAssignee(issueEvent);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setReindex(MutableIssue mutableIssue) {
		try {
			ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
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

	/**
	 * updating Asignee on status change.
	 * 
	 * @param event
	 *            the IssueEvent
	 */
	private void updateIssueByAssignee(IssueEvent event) {
		final MutableIssue issueParent = (MutableIssue) event.getIssue();
		Project project = issueParent.getProjectObject();
		if  (issueParent!=null) {
			String issueTypeName = issueParent.getIssueTypeObject().getName();
			IssueFactory issueFactory = ComponentAccessor.getIssueFactory();
			IssueManager issueManager  = ComponentAccessor.getIssueManager();
			CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
			JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
			SubTaskManager subTaskManager = ComponentAccessor.getSubTaskManager();
			 
			 try {
				Status status = issueParent.getStatusObject();
				if (status!=null && status.getName() != null) {
					// getting Assinee from AutoAssigneePortal
					List<AutoAssigneePortal> portals=autoAssigneeService.findByProjectIssueTypeStatus(project.getKey(), issueTypeName,status.getName());
					String [] assigneeList=null;
					if(portals!=null){
					for(AutoAssigneePortal portal:portals){
					assigneeList= (portal.getAssignee()==null) ?null :portal.getAssignee().split(",");
					 }
					}
					logger.info("portals:"+portals +"   ,   assigneeList:"+assigneeList);
					if(assigneeList!=null && assigneeList.length>0){
					String assignee=assigneeList[0];
					if((assignee!=null) && (!assignee.isEmpty())){
					ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(assignee.trim());
					if(user!=null){
					if(user.isActive()){
					issueParent.setAssigneeId(user.getKey());
					Issue editIssue = issueManager.updateIssue(authenticationContext.getLoggedInUser(),issueParent,EventDispatchOption.ISSUE_UPDATED,true);
						}else{
						   //validation message need to
						   logger.info("Warning : User in Inactive ");
						  }
						}
					  }
					}
				} else {
					logger.info("You cannot create an as parent status is null. "
									+ "Please update the status and try again!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
}
