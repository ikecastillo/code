package com.dt.jira.plugin.event;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.util.JiraUtils;
import com.atlassian.jira.workflow.WorkflowManager;
import com.atlassian.jira.workflow.WorkflowTransitionUtil;
import com.atlassian.jira.workflow.WorkflowTransitionUtilImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AddIssueToSprintEvent.java event fires when ticket is moved from backlog to any sprint on agile board and status will change from indifination status to Ready for development.
 */ 

public class AddIssueToSprintEvent implements InitializingBean, DisposableBean {

	private final EventPublisher eventPublisher;
	private final IssueIndexingService issueIndexingService;
	Map<String,Long> statusMap = new HashMap<String, Long>();
	
	private final Logger logger = Logger.getLogger(AddIssueToSprintEvent.class);
    
	public AddIssueToSprintEvent(EventPublisher eventPublisher, IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;
		this.issueIndexingService = issueIndexingService;
	}

	
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
	try{		
			Long eventTypeId = issueEvent.getEventTypeId();
			Issue issueParent = issueEvent.getIssue();		
			if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
				//logger.info("Issue create event ");
			} else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
				logger.info("Issue update event on automation to readyfordevelopment ");
				IssueManager issueManager = ComponentAccessor.getIssueManager();
				Project project = issueParent.getProjectObject();
				String status = issueParent.getStatusObject().getName();
				logger.info("Status "+ status);
				if(status.equalsIgnoreCase("InDefinition")){
					logger.info("Status "+ status);
					logger.info("Issue Key "+ issueParent.getKey());
					CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
					CustomField customField =customFieldManager.getCustomFieldObjectByName("Sprint");
					DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
					User user = ComponentAccessor.getJiraAuthenticationContext().getUser().getDirectoryUser();
					WorkflowManager workflowMan = ComponentAccessor.getWorkflowManager();
					
					List<ChangeItemBean>  sprintLog= ComponentAccessor.getChangeHistoryManager().getChangeItemsForField(issueParent,"Sprint");
					
					if(sprintLog!=null){
						boolean b = isIssueMovedFromBacklog(sprintLog);
						if(b){
							String key = issueParent.getKey();
							IssueType issueType = issueParent.getIssueTypeObject();
							MutableIssue mutableIssue = issueManager.getIssueByCurrentKey(issueParent.getKey());
							WorkflowTransitionUtil transitionUtil = JiraUtils.loadComponent( WorkflowTransitionUtilImpl.class);
							transitionUtil.setIssue(mutableIssue);
							transitionUtil.setUsername(user.getName());
							transitionUtil.setAction(11);
							transitionUtil.validate();
							transitionUtil.progress();
							logger.info("Issue update event updated status to readyfordevelopment ");
						}
					}
					
				}
					
			}
		}catch(Exception e){
		     e.printStackTrace();
		}
	}
	
	
	private boolean  isIssueMovedFromBacklog(List<ChangeItemBean>  beans){		
		if(beans!=null && beans.size()>0){			
				ChangeItemBean item = beans.get(beans.size()-1);
				logger.info("From String "+ item.getFromString());
				logger.info("To String "+ item.getToString());
				if(item.getFromString()==null && item.getToString() != null ){
					return true;
				}
			}
	    return false;
	}

	private void setReindex(MutableIssue mutableIssue){
	 try {

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

