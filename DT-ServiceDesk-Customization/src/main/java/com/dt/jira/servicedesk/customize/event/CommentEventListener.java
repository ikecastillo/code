package com.dt.jira.servicedesk.customize.event;

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
import com.atlassian.jira.event.AbstractWorkflowEvent;
import com.atlassian.jira.event.type.EventType;
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
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
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
import com.dt.jira.servicedesk.customize.rest.EncryptUtils;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskLdap;
import com.dt.jira.servicedesk.customize.service.PortalService;
import com.dt.jira.servicedesk.customize.ao.AutomationPortal;
import java.util.Properties;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.Context;
import com.atlassian.jira.entity.property.JsonEntityPropertyManagerImpl;
import com.atlassian.jira.entity.property.JsonEntityPropertyManager;
import com.atlassian.jira.entity.EntityEngineImpl;
import com.atlassian.jira.entity.EntityEngine;
import com.atlassian.jira.entity.property.EntityProperty;
import com.atlassian.jira.util.json.JSONObject;


import javax.naming.NamingEnumeration;

import javax.naming.NamingException;

import javax.naming.directory.Attribute;

import javax.naming.directory.Attributes;

import javax.naming.directory.BasicAttribute;

import javax.naming.directory.DirContext;

import javax.naming.directory.InitialDirContext;

import javax.naming.directory.ModificationItem;
import com.atlassian.jira.issue.UpdateIssueRequest;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.workflow.WorkflowManager;
import com.atlassian.jira.workflow.WorkflowTransitionUtil;
import com.atlassian.jira.workflow.WorkflowTransitionUtilImpl;
import com.atlassian.jira.util.JiraUtils;
import com.opensymphony.workflow.loader.ActionDescriptor;
import com.atlassian.jira.issue.index.IssueIndexManager;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.security.DefaultPermissionManager;
import com.atlassian.jira.workflow.IssueWorkflowManager;
import com.atlassian.jira.workflow.IssueWorkflowManagerImpl;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import com.atlassian.jira.issue.watchers.WatcherManager;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.changehistory.ChangeHistoryItem;
import com.atlassian.jira.issue.index.IssueIndexingService;

/**
 * ServiceDeskPortalEventListener.java event listener class 
 * In create event it creates multiple sub tasks based on configuration. 
 * When subtask get approved then adds user to ldap group.
 * 
 */

public class CommentEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PortalService portalService;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
	private final IssueIndexingService issueIndexingService;
	
	
	private final Logger logger = Logger
			.getLogger(CommentEventListener.class);
	
	
	private final String STATUS_PROCESSING="Processing";
	private final String STATUS_PENDING_APPROVAL="Pending Approval";
	private final String CUSTOM_FIELD_INTEGRATION_STATUS="User In Group";
	private final String OPTION_SUCCESS="Success";
	private final String OPTION_FAILED="Failed";
	private final String CUSTOM_FIELD_LDAP_EXCEPTION_MESSAGE="LDAP Exception Message";
	private final String LDAP_ENTRY_ERROR_MESSAGE="problem 6005 (ENTRY_EXISTS)";
	
	
	public CommentEventListener(EventPublisher eventPublisher,
			PortalService portalService,
			PluginSettingsFactory pluginSettingsFactory,OptionsManager optionsManager,IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;
		this.portalService = portalService;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
		this.issueIndexingService=issueIndexingService;
		}
	
	
	/*@EventListener
	public void onWorkflowEvent(AbstractWorkflowEvent workflowevent) {
		System.out.println("Workflow event");
	}*/
	
    /**
     * This event occurs for Project Jira Requests 
     * @param issueEvent
     */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		logger.info("*********start***********comment event listener********");			
		try {
			Long eventTypeId = issueEvent.getEventTypeId();
			Issue issueParent = issueEvent.getIssue();
			logger.info(issueParent.getStatusObject().getId()+"-------status -------------"+issueParent.getStatusObject().getName());
			
		if (eventTypeId.equals(EventType.ISSUE_COMMENTED_ID)) {
		logger.info("    comment     EventType.ISSUE_COMMENTED_ID   ");
				if(issueParent.isSubTask()) {
					updateComments(issueEvent);
				}	
			}

		logger.info("*********end***********comment event listener********");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
		
	
	

	private void setReindex(MutableIssue mutableIssue) {
		try {
			//ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
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

	
	/**
	 * Update the comments from sub-task to parent 
	 * @param IssueEvent - issueEvent
	 *
	 */
	private void updateComments(IssueEvent issueEvent){	
				Issue issue = issueEvent.getIssue();				
				if ( issue.isSubTask() ){
				Issue issueParentObj = issue.getParentObject();				
				Project project = issueParentObj.getProjectObject();
				String issueTypeName = issueParentObj.getIssueTypeObject().getName();
				List<AutomationPortal> automationPortalList=portalService.findByProjectIssueType(project.getName(), issueTypeName);
				String message = "";
				try{
						// on sub-task and project and issue type match with configuration  
						if ( automationPortalList!=null && automationPortalList.size()>0) {
							EntityEngine eEng = new EntityEngineImpl(ComponentAccessor.getOfBizDelegator());
					
							JsonEntityPropertyManager entityPropertyManager = new  JsonEntityPropertyManagerImpl(eEng,this.eventPublisher,null);
						
							CommentManager commentManager = ComponentAccessor.getCommentManager();
							Comment comment = issueEvent.getComment();
							//Long roleId = comment.getRoleLevelId();	
							//String groupLevel = comment.getGroupLevel();					
							ApplicationUser author = ComponentAccessor.getJiraAuthenticationContext().getUser();
						System.out.println("------------comment----------"+comment);
							if(comment!=null){
								EntityProperty  entityProp = entityPropertyManager.get("sd.comment.property",comment.getId(),"sd.public.comment");
								logger.info(" entityProp " +entityProp.getValue());
						
								JSONObject jsonObj = new JSONObject(entityProp.getValue());
								boolean b = jsonObj.getBoolean("internal"); // Get the comment is internal or external
								System.out.println("---------------------------jsonObj  :  "+jsonObj);
								
								String subtskcomments = comment.getBody();	
								Comment parentComment = commentManager.create(issueParentObj, author, subtskcomments,
								true);
								if(parentComment!=null){
									String json = "{\"internal\":false}"; // build json for  external
									if(b){
										json = "{\"internal\":true}";//build json for internal 
									}
									entityPropertyManager.put("sd.comment.property",parentComment.getId(),"sd.public.comment",json);	// apply/modify  the value for internal or external							
									commentManager.update(parentComment,true);
									//update(Comment comment, Map<String,JSONObject> commentProperties, boolean dispatchEvent)
									//Map<String,JSONObject> commentProperties=new HashMap<String,JSONObject>();
									//commentProperties.put("value",jsonObj);
									
									//commentProperties.put("sd.public.comment",jsonObj);
									//commentProperties.put("sd.comment.property",jsonObj);
									//commentManager.update(parentComment,commentProperties,true);
									//System.out.println("--------------commentProperties---------------"+commentProperties);
								}					
							}
						}
					}catch(Exception e){
								message = "SQL exception or Data exception on  the comments on the "+ issueParentObj.getKey();						
								
					}
					message = "Successfully created the comments for the "+ issueParentObj.getKey();
							logger.info(message);
							issue.store();	

										
							setReindex((MutableIssue)issue);
							setReindex((MutableIssue)issueParentObj);
							
				}
	}
	/**
	 * Verify the text is null or empty.
	 * @param text
	 * @return
	 */
	private boolean emptyCheck(String text){
		if(text==null || text.isEmpty()){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
