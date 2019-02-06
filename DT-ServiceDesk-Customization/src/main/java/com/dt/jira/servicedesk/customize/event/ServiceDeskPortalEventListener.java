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
import java.util.Iterator;
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

public class ServiceDeskPortalEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PortalService portalService;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
	private final IssueIndexingService issueIndexingService;
	
	private final Logger logger = Logger
			.getLogger(ServiceDeskPortalEventListener.class);
	
	
	private final String STATUS_PROCESSING="Processing";
	private final String STATUS_PENDING_APPROVAL="Pending Approval";
	private final String CUSTOM_FIELD_INTEGRATION_STATUS="User In Group";
	private final String OPTION_SUCCESS="Success";
	private final String OPTION_FAILED="Failed";
	private final String CUSTOM_FIELD_LDAP_EXCEPTION_MESSAGE="LDAP Exception Message";
	private final String LDAP_ENTRY_ERROR_MESSAGE="problem 6005 (ENTRY_EXISTS)";
	
	
	public ServiceDeskPortalEventListener(EventPublisher eventPublisher,
			PortalService portalService,
			PluginSettingsFactory pluginSettingsFactory,OptionsManager optionsManager,IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;
		this.portalService = portalService;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
		this.issueIndexingService =issueIndexingService;
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
		try {
			Long eventTypeId = issueEvent.getEventTypeId();
			String usrName="";
			Issue issueParent = issueEvent.getIssue();
			logger.info(issueParent.getStatusObject().getId()+"-------status -------------"+issueParent.getStatusObject().getName());
			if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
				logger.info("ServiceDeskPortalEventListener  Executed issue created");
				createSubtasksByConfiguration(issueEvent);
			}
			
			else {
			logger.info("EventType.ISSUE_GENERICEVENT_ID");
					
					Project project = issueParent.getProjectObject();
					String issueTypeName = issueParent.getIssueTypeObject().getName();
					List<AutomationPortal> automationPortalList=portalService.findByProjectIssueType(project.getName(), issueTypeName);
					logger.info("Executed issue update");
					if ((!issueParent.isSubTask()) && automationPortalList!=null && automationPortalList.size()>0) {
					if (issueParent.getStatusObject().getName()
							.equals(STATUS_PROCESSING)  && getIssueHistories(issueParent)) {
					
						try{

						String userDn = getDistinguishedNamebyUserName(issueParent
								.getReporterId());
						logger.info(project.getName()+"----projectName------portals-----------"+issueParent.getIssueTypeObject()						
								.getName());
						
						
						String groupDn = null;
						
						if (automationPortalList != null) {
							for (AutomationPortal portal : automationPortalList) {
								groupDn = (portal.getGroup());
								usrName=portal.getUserdn();
							}
						}
						if(emptyCheck(userDn) && emptyCheck(groupDn)){
						logger.info(userDn+"-------userDn-----------groupDn---"+groupDn);

						//Groups can be more than one, need to add for evey group
						String[] groups = groupDn.split(",");

						StringBuffer ldapExceptionMessageBuffer = new StringBuffer();

						MutableIssue issueParent1 = (MutableIssue) issueEvent.getIssue();
						for (String group : groups) {
							try {
								addEntry(userDn, getDistinguishedNamebyUserName(group), issueParent);
								ldapExceptionMessageBuffer.append(group + "=SUCCESS");
								updateingIssue(issueParent1,OPTION_SUCCESS);
							} catch (NamingException ne) {
								//Naming exception/ lDAP exception
								logger.debug("NAMING EXCEPTION IN LOOP");
								ne.printStackTrace();
								// update custom field LDAP Integration with failed status
								if(ne.getMessage().contains(LDAP_ENTRY_ERROR_MESSAGE)){
									updateingIssue(issueParent1,OPTION_SUCCESS);
									ldapExceptionMessageBuffer.append(group + "=" +ne.getMessage() + "\n");
									//updateIssueTextField(issueParent1,group + "=" +ne.getMessage());
								}else{
									updateingIssue(issueParent1,OPTION_FAILED);
									ldapExceptionMessageBuffer.append(group + "=" +ne.getMessage() + "\n");
									//updateIssueTextField(issueParent1,group + "=" +ne.getMessage());
								}
								logger.info("======END=NAMING EXCEPTION IN LOOP");
							}
						}
						updateIssueTextField(issueParent1,ldapExceptionMessageBuffer.toString());

						}
						/*// Update parent issue with Success/Fail on custom field
						 updateingIssue(((MutableIssue)issueParent),OPTION_SUCCESS);
						 // Update parent issue with ldap error message on custom text field
						 updateIssueTextField(((MutableIssue)issueParent),"");*/
						 
						} catch (NamingException ne) {
							//Naming exception/ lDAP exception							
							logger.info("===========NamingException===============");
							ne.printStackTrace();
							MutableIssue issueParent1 = (MutableIssue) issueEvent.getIssue();
							 // update custom field LDAP Integration with failed status
							 if(ne.getMessage().contains(LDAP_ENTRY_ERROR_MESSAGE)){
								  updateingIssue(issueParent1,OPTION_SUCCESS);
								  updateIssueTextField(issueParent1,ne.getMessage());
							  }else{
								  updateingIssue(issueParent1,OPTION_FAILED);
								  updateIssueTextField(issueParent1,ne.getMessage());								  
							  }							
							 logger.info("======END=====NamingException===============");
						}
						
						
					}
				}

				
				
			}
		logger.info("*********end***********update Service Desk  issue event********");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
		
	private void reindexIssueandSubtasks(MutableIssue issueParent){	
		setReindex(issueParent);
	}
	/* Get change history to find the status from "Pending Approval" to "Processing" */
	private Boolean getIssueHistories(Issue issue){
		Boolean flag=Boolean.FALSE;
		ChangeHistoryManager changeHistoryManager = ComponentAccessor.getChangeHistoryManager();
        List<ChangeHistoryItem> changeHistoryItemList=changeHistoryManager.getAllChangeItems(issue);
        
        if(changeHistoryItemList!=null){
        	ChangeHistoryItem changeHistoryItem=changeHistoryItemList.get(changeHistoryItemList.size()-1);
        	if(changeHistoryItem!=null){        
        	Map<String,String> fromMap= changeHistoryItem.getFroms();
        	Map<String,String> toMap= changeHistoryItem.getTos();
        	String fromValue="";
        	String toValue="";
        	 if(fromMap!=null && toMap!=null){
        		 if(toMap.keySet().size()==1){
        			Iterator it= fromMap.entrySet().iterator();
        	    while (it.hasNext()) {
        	        Map.Entry pair = (Map.Entry)it.next();        	        
        	        fromValue= ""+pair.getValue(); 
        	       }
        		 }
        		
        		if(toMap.keySet().size()==1){
        			 Iterator itr= toMap.entrySet().iterator();
             	    while (itr.hasNext()) {
             	        Map.Entry pair = (Map.Entry)itr.next();             	        
             	       toValue= ""+pair.getValue(); 
             	       }
             		 }	
        		
        	 }
        	 logger.info(fromValue+"------fromValue------------toValue-----"+toValue);
        	 if(fromValue.equals("Pending Approval") && toValue.equals("Processing")){
        		 flag=Boolean.TRUE; 
        	 }
        	 
        	}
      
        }        
        return flag;
	}
	//{10200=Pending Approval}-----from-----------------to-----{10202=Processing}

	/**
	 * Update LDAP Integration status with new  value Success/Failed. 
	 * @param issue
	 * @param newvalue
	 */
	private void updateingIssue(MutableIssue issue,String newvalue){
		IssueManager imanager=ComponentAccessor.getIssueManager();
		JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
		//CustomFieldManager cfManager = ComponentManager.getInstance().getCustomFieldManager();
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(CUSTOM_FIELD_INTEGRATION_STATUS);
		logger.info(customFeildObj.getValue(issue)+"------newValue------"+newvalue);
		List<Option> optList=optionsManager.findByOptionValue(newvalue);
		logger.info(optList+"-----------------------");
		if(optList!=null && (!optList.isEmpty())){
			logger.info("------------------"+optList.get(0));
			customFeildObj.updateValue(null, issue, new ModifiedValue(
					customFeildObj.getValue(issue),optList.get(0)), new DefaultIssueChangeHolder());
			issue.store();
			setReindex(issue);
			logger.info("-----new Option-------------"+optList.get(0).getValue());
		}
		
	}
	
	/**
	 * update Issue TextField
	 * @param issue
	 * @param newvalue
	 */
	private void updateIssueTextField(MutableIssue issue,String newvalue){
		try{
		JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(CUSTOM_FIELD_LDAP_EXCEPTION_MESSAGE);
			customFeildObj.updateValue(null, issue, new ModifiedValue(
					customFeildObj.getValue(issue) ,newvalue), new DefaultIssueChangeHolder());
			issue.store();
			setReindex(issue);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Move issue from processing to Pending approval.
	 * action 901 configured.
	 * @param actionId
	 * @param issue
	 * @param userName
	 */
	private void doReverseTrasition(int actionId,MutableIssue issue,String userName){
		logger.info(actionId+"=====actionId===userName===="+userName);
		WorkflowTransitionUtil workflowTransitionUtil = (WorkflowTransitionUtil)JiraUtils.loadComponent(WorkflowTransitionUtilImpl.class);
	    workflowTransitionUtil.setIssue(issue);
	    workflowTransitionUtil.setAction(actionId); //my transition
	    workflowTransitionUtil.setUsername(userName);
	    ActionDescriptor actionDescriptor = workflowTransitionUtil.getActionDescriptor();
	    actionDescriptor.setAutoExecute(false);
	    workflowTransitionUtil.validate();
	    workflowTransitionUtil.progress();
		try {
		issueIndexingService.reIndex(issue);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
	
	
	/**
	 * getStatusIdByStatusName
	 * @param statusName
	 * @param statuses
	 * @return
	 *//*
	private String getStatusIdByStatusName(String statusName,List<Status> statuses){
		if(statuses!=null){
			for(Status statusObj:statuses){
				if(statusName.equals(statusObj.getName())){
				return 	statusObj.getId();
				}
			}
		}
		
	return null;	
	}*/
	
	
	/**
	 *  --- Not in Used ----
	 * In any failures code reverts the transition 
	 * from resolved to pending approval
	 * @param issueParent
	 */
	private void reverseTransitionasLDAPfailures(MutableIssue issueParent){
		//send status Id 
		logger.info("-------issueParent------------"+issueParent);
		JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
		IssueManager imanager=ComponentAccessor.getIssueManager();
		WorkflowManager workflowManager = ComponentAccessor.getWorkflowManager();
		List<Status> statuses = workflowManager.getWorkflow(issueParent).getLinkedStatusObjects();
		//get available action on issue
		//search action name 'Pending approval'
		//then move issue back to pending approval using action id 901(DEV).
		Integer transitionId = null;
		IssueWorkflowManager issueWorkflowManager = ComponentAccessor.getComponentOfType(IssueWorkflowManager.class);
		List<ActionDescriptor> actions = issueWorkflowManager.getSortedAvailableActions((Issue)issueParent,authenticationContext.getUser());
			for (ActionDescriptor actionDescriptor : actions) {
				if(STATUS_PENDING_APPROVAL.equals(actionDescriptor.getName())){
			     transitionId = actionDescriptor.getId();
			     }
				}
			if(transitionId!=null){
		doReverseTrasition(transitionId,issueParent,authenticationContext
	    		.getLoggedInUser().getName());
			}
	}
	
	
	/**
	 * 
	 * Send Mail Notification	
	 * @param errorMsg - <String> error message	 	 
	 */
	
	// this method not been used as email configuration by admin
	 private void sendMailNotification(String errorMsg,String emailCC,String emailBcc){
		 //System.out.println(emailCC+"----emailCC-------------emailBcc-"+emailBcc);
		try{
			logger.info("Send Mail Notification: "+errorMsg);
			MailServerManager mailServerManager = ComponentAccessor
					.getMailServerManager();
			SMTPMailServer mailServer = mailServerManager
					.getDefaultSMTPMailServer();
			
			Properties p=new Properties();

			//we need to get al jira admin mails
			String emailProperty = "srinadh.garlapati@dealertrack.com";  //getJiraAdminEmailids();
			Email email = new Email(
					emailProperty,emailCC,emailBcc);
			email.setFrom(mailServer.getDefaultFrom());
			email.setSubject("Adding user AD group failed");
			email.setMimeType("text/html");
			email.setBody(errorMsg);
			SingleMailQueueItem item = new SingleMailQueueItem(email);
			ComponentAccessor.getMailQueue().addItem(item);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * gets distinguishedName by user name from LDAP BaseDN.
	 * 
	 * @param username
	 * @return
	 */
	public String getDistinguishedNamebyUserName(String username) throws NamingException,Exception{

		DirContext initialdircontext = null;
		try {
			initialdircontext = getLDAPContext();
			PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
			String ldapBaseDn = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn");
			logger.info("-----------ldapBaseDn--------------"+ldapBaseDn);
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { "distinguishedName", "sn", "givenname","mail", "telephonenumber" };
			constraints.setReturningAttributes(attrIDs);
			//Search Distingushed name on lDAP Server
			// First input parameter is search base dn, it can be
			// "CN=Users,DC=YourDomain,DC=com"
			// Second Attribute can be uid=username
			logger.info("username "+ username);
			NamingEnumeration answer = initialdircontext.search(ldapBaseDn,"sAMAccountName=" + username, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next())
						.getAttributes();
				// displayAttributes(attributes);
				logger.info("distinguishedName "
						+ attrs.get("distinguishedName").get(0));

				return "" + attrs.get("distinguishedName").get(0);
			} else {
				throw new Exception("Invalid User");
			}

		} catch (NamingException ex) {
			//ex.printStackTrace();
			throw ex;
		}

		//return null;

	}
	
	/**
	 * get the LDAP context
	 * 
	 * @return
	 */
	private DirContext getLDAPContext() throws NamingException {
		DirContext context = null;
		PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		ServiceDeskLdap ldapSrvc = new ServiceDeskLdap();
		 // Get LDAP Initial Context Class Name
		String ldapInitCtx = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapInitCtx");
	     // Get LDAP URL 	
		String ldapSrvrName = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapSrvrName");
		// Get LDAP BaseDN  Name
		String ldapBaseDn = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn");
		// Get LDAP user login Name
		String ldapUid = (String) settings.get(ServiceDeskLdap.class.getName()+ ".ldapUid");
		String ldapPwd = (String) settings.get(ServiceDeskLdap.class.getName()+ ".ldapPwd");
		Properties properties = new Properties();
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,ldapInitCtx);
		properties.put(javax.naming.Context.PROVIDER_URL, "ldap://"+ldapSrvrName);
		properties.put(javax.naming.Context.SECURITY_PRINCIPAL, ldapUid);
		properties.put(javax.naming.Context.SECURITY_CREDENTIALS, ldapPwd);

		try {

			context = new InitialDirContext(properties);

		} catch (NamingException e) {

			//e.printStackTrace();
			throw e;

		}

		return context;
	}
	
	
	

	private void setReindex(MutableIssue mutableIssue) {
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

	/**
	 * Creating subtask under the current issue
	 * 
	 * @param event - the IssueEvent object
	 */
	private void createSubtasksByConfiguration(IssueEvent event) {
		final MutableIssue issueParent = (MutableIssue) event.getIssue();
		Project project = issueParent.getProjectObject();
		String issueTypeName = issueParent.getIssueTypeObject().getName();
		List<AutomationPortal> automationPortalList=portalService.findByProjectIssueType(project.getName(), issueTypeName);
		if ((!issueParent.isSubTask()) &&  automationPortalList!=null && automationPortalList.size()>0) {
			Boolean subIssueExistsCondition = issueParent.isSubTask();
			IssueFactory issueFactory = ComponentAccessor.getIssueFactory();
			IssueManager issueManager = ComponentAccessor.getIssueManager();
			CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
			JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
			SubTaskManager subTaskManager = ComponentAccessor.getSubTaskManager();

			try {
				Status status = issueParent.getStatusObject();
				if (status.getName() != null) {
					// define subtask here need to manager approval yes or no
					String[] assigneeList = null;
					String subTaskName = null;
					if (automationPortalList != null) {
						for (AutomationPortal portal : automationPortalList) {
							assigneeList = (portal.getAssignee() == null) ? null: portal.getAssignee().split(",");
							subTaskName = portal.getSubTask();
							}
					}
					
					// Create subtask on JIRA 5 and higher
					if (assigneeList != null && subTaskName!=null) {
						for (String assignee : assigneeList) {
							if ((assignee != null) && (!assignee.isEmpty())) {
								MutableIssue issueObject = getIssueObjectOnParentIssueObject(issueParent, project, issueFactory,subTaskName);
								ApplicationUser user = ComponentAccessor
										.getUserManager().getUserByName(
												assignee.trim());
								if (user != null) {
									issueObject.setAssigneeId(user.getKey());
									Issue subTask = issueManager
											.createIssueObject(
													authenticationContext
															.getLoggedInUser(),
													issueObject);
									//Creating subtask and link to current issue
									subTaskManager.createSubTaskIssueLink(
											issueParent, subTask,
											authenticationContext
													.getLoggedInUser());
								}
							}
						}
					}
					//adding watchers to issue
					addingWatcherToIssue(assigneeList,issueParent);
					
				} else {
					System.out.println("You cannot create an as parent status is null. "
									+ "Please update the status and try again!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			logger.info("Subtask is already created for "
					+ issueParent.getKey());
		}

	}
	
	/**
	 * Adds users to watchers List of an issue.
	 * @param watchersList
	 * @param issueParentObj
	 */
	private void addingWatcherToIssue(String[] watchersList,MutableIssue issueParentObj){
		for (String watcher : watchersList) {
		if(watcher!=null && (!watcher.isEmpty())){
		ApplicationUser user = ComponentAccessor.getUserManager().getUserByName(watcher.trim());
		if(user!=null){
		WatcherManager wm = ComponentAccessor.getWatcherManager();
		wm.startWatching(user, issueParentObj);
		    }
		  }
		}	
	}

	/**
	 * getLDAPcontext based user name configured if user name is not configure
	 * then it will consider user name in configure button in plug in
	 * 
	 * @param issueParent
	 * @return
	 */
	private DirContext getLDAPContext(Issue issueParent) throws NamingException,Exception{

		DirContext context = null;
		PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		ServiceDeskLdap ldapSrvc = new ServiceDeskLdap();
        // Get LDAP Initial Context Class Name
		String ldapInitCtx = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapInitCtx");
		// Get LDAP Context URL
		String ldapSrvrName = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapSrvrName");
		// Get LDAP BaseDN  Name
		String ldapBaseDn = (String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn");
		// Get LDAP user login Name
		String ldapUid = (String) settings.get(ServiceDeskLdap.class.getName()+ ".ldapUid");
		String ldapPwd = (String) settings.get(ServiceDeskLdap.class.getName()+ ".ldapPwd");
		// getting user DN and password from configuration
		String issueTypeName = issueParent.getIssueTypeObject().getName();
		Project project = issueParent.getProjectObject();
		List<AutomationPortal> portals = portalService.findByProjectIssueType(
				project.getName(), issueTypeName);
		if (portals != null && (!portals.isEmpty())) {
			AutomationPortal autoPortal = (AutomationPortal) portals.get(0);
			if (autoPortal != null) {
				ldapUid = getDistinguishedNamebyUserName(autoPortal.getUserdn());
				ldapPwd = EncryptUtils.decodeLDAPDetails(autoPortal
						.getPassword());
			}
		}
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, ldapInitCtx);
        // LDAP URL using domain name:"ldap://nhpads002.dt.inc"
		properties.put(Context.PROVIDER_URL, "ldap://" + ldapSrvrName);
		properties.put(Context.SECURITY_PRINCIPAL, ldapUid);
		properties.put(Context.SECURITY_CREDENTIALS, ldapPwd);

		try {

		context = new InitialDirContext(properties);

		} catch (NamingException e) {

			//e.printStackTrace();
			throw e;

		}

		return context;
	}

	/**
	 * adding user to LDAP group
	 * 
	 * @param userDn
	 * @param groupDn
	 * @param issueParent
	 */
	public void addEntry(String userDn, String groupDn, Issue issueParent) throws NamingException,Exception {

			logger.info(userDn+" is being tried to be added to group :"+groupDn);
			DirContext context = getLDAPContext(issueParent);
			ModificationItem[] mods = new ModificationItem[1];
			Attribute mod = new BasicAttribute("member", userDn);
			mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, mod);
			context.modifyAttributes(groupDn, mods);
		

		return;
	}

	

	/**
	 * populate subtask On ParentIssueObject
	 * 
	 * @param issueParent
	 * @param project
	 * @param issueFactory
	 * @return
	 */
	private MutableIssue getIssueObjectOnParentIssueObject(MutableIssue issueParent, Project project, IssueFactory issueFactory,String subTask) {
		MutableIssue issueObject = issueFactory.getIssue();
		issueObject.setProjectId(issueParent.getProjectObject().getId());
		String subtaskId = getIncidentReportSubtaskTypeId(project,subTask);
		issueObject.setIssueTypeId(subtaskId);
		// normal subtask
		issueObject.setParentId(issueParent.getId());
		// set subtask attributes
		issueObject.setSummary(subTask);
		issueObject.setDescription(issueParent.getDescription());
		String issueType = issueParent.getSummary();
		issueObject.setReporter(issueParent.getReporter());
		return issueObject;
	}

	/**
	 * Gets the Incident Report Subtask Id
	 * 
	 * @param project
	 *            the Project
	 * @return the String
	 */
	private String getIncidentReportSubtaskTypeId(Project project,String subTaskName) {
		Collection<IssueType> issuesTypes = project.getIssueTypes();
		String issueTypeId = "";
		for (IssueType childIssue : issuesTypes) {
			if (childIssue.isSubTask() && childIssue.getName().equals(subTaskName)) {
				issueTypeId = childIssue.getId();
				break;
			}

		}
		return issueTypeId;
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
