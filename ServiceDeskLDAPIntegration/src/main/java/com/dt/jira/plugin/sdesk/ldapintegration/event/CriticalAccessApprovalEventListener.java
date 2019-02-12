package com.dt.jira.plugin.sdesk.ldapintegration.event;

import com.atlassian.crowd.embedded.api.Group;
import com.atlassian.crowd.exception.GroupNotFoundException;
import com.atlassian.crowd.exception.OperationFailedException;
import com.atlassian.crowd.exception.OperationNotPermittedException;
import com.atlassian.crowd.exception.UserNotFoundException;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.projectroles.ProjectRoleService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.security.roles.DefaultRoleActors;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleActor;
import com.atlassian.jira.security.roles.ProjectRoleActors;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.security.roles.RoleActor;
import com.atlassian.jira.security.roles.RoleActorDoesNotExistException;
import com.atlassian.jira.security.roles.RoleActorFactory;
import com.atlassian.jira.security.roles.actor.UserRoleActorFactory;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.SimpleErrorCollection;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.dt.autoassign.rest.ConfigBean;
import com.dt.autoassign.service.ConfigService;
import com.dt.jira.plugin.sdesk.ldapintegration.rest.ServiceDeskLDAPIntegrationResource;
import com.dt.jira.plugin.sdesk.ldapintegration.rest.ServiceDeskLDAPIntegrationResourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

import com.atlassian.jira.database.DbConnectionManager;

/**
 * Event listener class  update the custom fields values and verify user's manager is belongs to service-desk-agents *   group or not. If not, then add the user's manager to the service-desk-agents group and update the assignee as *  manager.
 * 
 * 
 */

public class CriticalAccessApprovalEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
	private final IssueIndexingService issueIndexingService;
	private final ServiceDeskLDAPIntegrationResource  serviceDeskLDAPIntegrationResource;
	private final ConfigService configService;
	private final String STATUS_PENDING_MANAGERS_APPROVAL = "PENDING APPROVAL - MANAGER";
	private final String STATUS_PENDING_OWNERS_APPROVAL = "PENDING APPROVAL - OWNER";
	private final String STATUS_AWAITING_IMPLEMENTATION = "AWAITING IMPLEMENTATION";
	private final String STATUS_INTIATED = "Initiated";
	private final String PROJECT_KEY_NAME = "CSAR";
	private final String FIELD_REPORTERS_MANAGER = "Reporter's Manager";
	private final String FIELD_TEAM_MEMBERS_JOB_TITLE = "Team Members Job Title";
	private final String GROUP_SERVICE_DESK_AGENTS = "service-desk-agents";
	private final String GROUP_SERVICE_CSARMANAGER_AGENTS = "servicedesk_manager_CSAR";
	private final String GROUP_SERVICE_CSAROWNER_AGENTS= "servicedesk_owner_CSAR";
	private final DbConnectionManager dbConnectionManager=null;
	
	private final Logger logger = LoggerFactory
			.getLogger(CriticalAccessApprovalEventListener.class);

	public CriticalAccessApprovalEventListener(EventPublisher eventPublisher,
			PluginSettingsFactory pluginSettingsFactory,OptionsManager optionsManager,
            IssueIndexingService issueIndexingService,UserManager userManager,TransactionTemplate transactionTemplate,
											   ConfigService configService) {
		this.eventPublisher = eventPublisher;	
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
		this.issueIndexingService =issueIndexingService;
		this.configService = configService;
		this.serviceDeskLDAPIntegrationResource = new ServiceDeskLDAPIntegrationResource(userManager,
                                                                        pluginSettingsFactory, transactionTemplate);
																		
    }
	
    /**
     * This event occurs for Project CSAR  
     * @param issueEvent
     */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
			Long eventTypeId = issueEvent.getEventTypeId();		
			Issue currentIssue = issueEvent.getIssue();
			ApplicationUser user = currentIssue.getReporter();
			logger.debug(currentIssue.getStatusObject().getId()+"-------status -------------"+currentIssue.getStatusObject().getName());
			logger.debug("CriticalAccessApprovalEventListener  Executed issue created"+ currentIssue.getStatusObject().getName());
			Project project = currentIssue.getProjectObject();
			if (eventTypeId.equals(EventType.ISSUE_CREATED_ID) &&  PROJECT_KEY_NAME.equals(project.getKey()) &&
					STATUS_INTIATED.equals(currentIssue.getStatusObject().getName())) {
				logger.debug("CriticalAccessApprovalEventListener  Executed issue created");
				logger.debug("CriticalAccessApprovalEventListener  Executed issue created");
				List accDetails = new ArrayList();
				ServiceDeskLDAPIntegrationResourceModel myRestResourceModel=new ServiceDeskLDAPIntegrationResourceModel();
			     try {
				
					accDetails = serviceDeskLDAPIntegrationResource.getUserAccountDetailsByMailId(user.getEmailAddress());
					int acc = Integer.parseInt((String) accDetails.get(0));
					if (acc == 0) {
						myRestResourceModel=(ServiceDeskLDAPIntegrationResourceModel) accDetails.get(2);
						logger.debug("LDAP UserProfile User Name : "+myRestResourceModel.getUserName());
						logger.debug("LDAP UserProfile User MailID : "+myRestResourceModel.getUserMailId());
						logger.debug("LDAP UserProfile User Phone Number : "+myRestResourceModel.getPhoneNo());
						logger.debug("LDAP UserProfile User Department : "+myRestResourceModel.getDeptName());
						logger.debug("LDAP UserProfile User Manager's Name : "+myRestResourceModel.getMngrName());
						logger.debug("LDAP UserProfile User Manager's Account Name : "+
                                myRestResourceModel.getMngrAccountName());
						logger.debug("LDAP UserProfile User Manager's MailID : "+myRestResourceModel.getMngrMailId());
						logger.debug("CriticalAccessApprovalEventListener LDAP UserProfile User Manager's account Name : "
                                + myRestResourceModel.getMngrAccountName());
						logger.debug("CriticalAccessApprovalEventListener LDAP UserProfile Job Title Name : "
                                + myRestResourceModel.getJobTitle());

                        //Update the reporter's manager and make the manager as the assignee
                        ApplicationUser reportMgrUser = ComponentAccessor.getUserManager().getUserByName(myRestResourceModel.getMngrAccountName());
                        logger.debug("Going ahead to update the manager to " + reportMgrUser.getName());
                        updateReportsManagerCustomFieldValue(FIELD_REPORTERS_MANAGER, reportMgrUser,currentIssue);

                        //Update the Team member's job title
						updateCustomFieldValue(FIELD_TEAM_MEMBERS_JOB_TITLE, myRestResourceModel.getJobTitle(),currentIssue);
					}
				 } catch (Exception e) {
					e.printStackTrace();
				 }
			} else {
				logger.debug("-------eventTypeId -------------"+eventTypeId);
				
				if(eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID) && PROJECT_KEY_NAME.equals(project.getKey()) &&
						(STATUS_PENDING_OWNERS_APPROVAL.equals(currentIssue.getStatusObject().getName()) ||
								STATUS_AWAITING_IMPLEMENTATION.equalsIgnoreCase(currentIssue.getStatusObject().getName()))){

					logger.debug("Getting into the case where status is either pending owner approval or " +
							"pending implementation");
					CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
					CustomField solutionGroupCF = cfm.getCustomFieldObjectByName("Solution Group - Product");
					Map<LazyLoadedOption, LazyLoadedOption> solGrpMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
					currentIssue.getCustomFieldValue(solutionGroupCF);
					String sgvalue = "";
					if (solGrpMap != null && !solGrpMap.isEmpty()) {
						for (Map.Entry<LazyLoadedOption, LazyLoadedOption> solGrpOpt : solGrpMap.entrySet()) {
							if (solGrpOpt.getKey() == null) {
								logger.debug("solution grp key: " + solGrpOpt.getKey());
								LazyLoadedOption sgllo = solGrpOpt.getValue();
								logger.debug("solution grp id: " + sgllo.getOptionId());
								sgvalue = String.valueOf(sgllo.getValue());
								break;
							}
						}
					} else {
						sgvalue = "Default"; //There can be a mapping for no solution group but issue type also
					}
					logger.debug("Solution Group of the issue is : " + sgvalue);
					IssueType issueType = currentIssue.getIssueType();
					String issueTypeValue = issueType.getName();
					logger.debug("Issue Type Value :" + issueTypeValue);

					//Now wrap the solution group and issue type in config bean and search for the user
					ConfigBean configBean = new ConfigBean();
					configBean.setSolutionGroup(sgvalue);
					configBean.setIssueType(issueTypeValue);

					configBean = configService.getConfig(configBean);
					if (configBean!= null) {
						setOwnerOrImplementorAssignee(issueEvent, currentIssue, configBean);

					} else {
						//if there is no config for the solution group and issue type, but there is a config for
						//default solution group and issue type, then try to retrieve that instead
						configBean = new ConfigBean();
						configBean.setSolutionGroup("Default");
						configBean.setIssueType(issueTypeValue);

						configBean = configService.getConfig(configBean);
						if (configBean != null) {
							setOwnerOrImplementorAssignee(issueEvent, currentIssue, configBean);
						}
					}
				}
			}
	}

	/**
	 * Helper method that goes ahead and sets the owner/implementor as needed
	 *
	 * @param issueEvent
	 * @param currentIssue
	 * @param configBean
     */
	private void setOwnerOrImplementorAssignee(IssueEvent issueEvent, Issue currentIssue, ConfigBean configBean) {
		String userNameToAssign = "";
		if (STATUS_PENDING_OWNERS_APPROVAL.equalsIgnoreCase(currentIssue.getStatusObject().getName())) {
            userNameToAssign = configBean.getOwner();
        } else if (STATUS_AWAITING_IMPLEMENTATION.equalsIgnoreCase(currentIssue.getStatusObject().getName())){
            userNameToAssign = configBean.getImplementor();
        }
		logger.debug("User name to assign is " + userNameToAssign);
		ApplicationUser assignee = ComponentAccessor.getUserManager().getUserByKey(userNameToAssign);
		if (assignee == null) {
			assignee = ComponentAccessor.getUserManager().getUser(userNameToAssign);
		}
		logger.debug("Assignee object found is " + assignee);
		addUserToServiceDeskAgents(assignee);
		//updateProjectRoleActor(appUser,project);
		addOwnerToCSARoleGroup(assignee);
		setNewAssigneeOfIssue(assignee, currentIssue, issueEvent);
	}

	private void setNewAssigneeOfIssue(ApplicationUser assignee, Issue currentIssue, IssueEvent issueEvent) {
		logger.debug("Changing the assignee in the old fashioned way");
		Issue issue = issueEvent.getIssue();
		MutableIssue mutableIssue = (MutableIssue)issue;
		ApplicationUser adminUser = ComponentAccessor.getUserManager().getUserByName("dtjira.admin");
		mutableIssue.setAssignee(assignee);
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		issueManager.updateIssue(adminUser, mutableIssue, EventDispatchOption.ISSUE_ASSIGNED, true);
		mutableIssue.store();
		setReindex(mutableIssue);
	}
	/*
	private void updateProjectRoleActor(ApplicationUser user,Project project){
		
		ProjectRoleManager projectRoleManager = (ProjectRoleManager) ComponentAccessor.getComponentOfType(ProjectRoleManager.class); 
		DbConnectionManager dbConnectionManager = (DbConnectionManager) ComponentAccessor.getComponentOfType(DbConnectionManager.class); 
		ProjectRoleService projectRoleService = (ProjectRoleService) ComponentAccessor.getComponentOfType(ProjectRoleService.class); 
		SimpleErrorCollection simpleErrorCollection = new SimpleErrorCollection();
		
		ApplicationUser admin = ComponentAccessor.getUserManager().getUserByName("kiran.muthoju");
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(user.getName()));
		
		RoleActorFactory roleActorFactory = new UserRoleActorFactory(ComponentAccessor.getUserManager(),dbConnectionManager);
		/*for(ProjectRole pRoles: projectRoleManager.getProjectRoles()){
			System.out.println("Role Name: "+pRoles.getName() + " ID : "+ pRoles.getId());
		}
		
	        ProjectRole projectRole = projectRoleManager.getProjectRole("Service Desk Team");
			System.out.println("updateProjectRoleActor "+projectRole);
			if(projectRole!=null){
				
		        
		        try {
				System.out.println("updateProjectRoleActor");
				projectRoleService.addActorsToProjectRole(admin,list, projectRole, project, ProjectRoleActor.USER_ROLE_ACTOR_TYPE, simpleErrorCollection);
		           // userActor = roleActorFactory.createRoleActor(null, projectRole.getId(), project.getId(), ProjectRoleActor.USER_ROLE_ACTOR_TYPE, user.getUsername());
		         
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		        Collection<String> errorMsg = simpleErrorCollection.getErrorMessages();
		        Iterator<String> itErrorMsg = errorMsg.iterator();
		        while(itErrorMsg.hasNext()){
		        	String errorStr = itErrorMsg.next();
		        	System.out.println("User: "+ user.getName() + " Error messages on role "+ errorStr);
		        }
		        
			}

	}
	*/
/**
     * Helper method to update the custom field.   
     * @param updateValue
     * @param currentIssue
     */

	private void updateCustomFieldValue(String customFieldName, String updateValue,Issue currentIssue){
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		CustomField  customField = customFieldManager.getCustomFieldObjectByName(customFieldName); //customFieldName		
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		String key = currentIssue.getKey();
		MutableIssue mutableIssue = issueManager.getIssueByCurrentKey(key);	
		Map<String, ModifiedValue> modifiedFields = null;
		FieldLayoutItem fieldLayoutItem = null;
		ModifiedValue modifiedValue = null;
		
		if(updateValue!=null && updateValue.length()>0){
			mutableIssue.setCustomFieldValue(customField, updateValue);
			modifiedFields = mutableIssue.getModifiedFields();
			fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
			modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
			customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
			setReindex(mutableIssue);
		}
		logger.debug("Successfully updated custom field value : " + updateValue);
	}

    /**
     * Helper method to update the reporter's manager custom field and make manager as the assignee.
     * @param reportMgrCustomFieldName
     * @param updateValue
     * @param currentIssue
     */
	private void updateReportsManagerCustomFieldValue(String reportMgrCustomFieldName, ApplicationUser updateValue,Issue currentIssue){
        logger.debug("UPDATE VALUE IS " + updateValue);
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		CustomField  reportMgrCustomField = customFieldManager.getCustomFieldObjectByName(reportMgrCustomFieldName); //customFieldName
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		String key = currentIssue.getKey();
		MutableIssue mutableIssue = issueManager.getIssueByCurrentKey(key);	
		Map<String, ModifiedValue> modifiedFields = null;
		FieldLayoutItem fieldLayoutItem = null;
		ModifiedValue modifiedValue = null;
		
		if(updateValue!=null){
			mutableIssue.setCustomFieldValue(reportMgrCustomField, updateValue);

			/*
			   Check if manager is in the jira group service-desk-agents and if not, add manager to that group and
			   then set to assignee - LDAP verification not required.
			 */
			logger.debug("Checking if the manager is in the necessary group service-desk-agents");
			if (!isManagerInCorrectGroup(updateValue)) {
				//Add manager to the correct group first - as of now the group in question is service-desk-agents
				logger.debug("Manager not currently in required group, going ahead to add the manager to that group ");
				GroupManager groupManager = ComponentAccessor.getGroupManager();
				Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SERVICE_DESK_AGENTS);
				try {
					groupManager.addUserToGroup(updateValue, group);
					
				} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
						OperationFailedException addUserToGroupException) {
					logger.error("Could not add manager to group service-desk-agents ", addUserToGroupException);
				}
			}
			
			logger.debug("add the manager in the necessary service desk group (Service Desk Team)");
			//updateProjectRoleActor(updateValue,currentIssue.getProjectObject());
			addManagerToCSARoleGroup(updateValue);

			logger.debug("Confirmed that the manager is in the correct group, going ahead to set to assignee");
            mutableIssue.setAssignee(updateValue);
			modifiedFields = mutableIssue.getModifiedFields();
			fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(reportMgrCustomField);
			modifiedValue = (ModifiedValue) modifiedFields.get(reportMgrCustomField.getId());
			reportMgrCustomField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);

            mutableIssue.store();
			setReindex(mutableIssue);
		}
		logger.debug("Successfully updated the reporters Manager : "+updateValue);
	}

	private boolean isManagerInCorrectGroup(ApplicationUser managerUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(managerUser, GROUP_SERVICE_DESK_AGENTS);
	}

	private boolean isManagerInCorrectGroupCSAR(ApplicationUser managerUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(managerUser, GROUP_SERVICE_CSARMANAGER_AGENTS);
	}

	private boolean isOwnerInCorrectGroupCSAR(ApplicationUser managerUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(managerUser, GROUP_SERVICE_CSAROWNER_AGENTS);
	}

	private void addUserToServiceDeskAgents(ApplicationUser user){
		logger.debug("Checking if the manager is in the necessary group service-desk-agents");
		if (!isManagerInCorrectGroup(user)) {
			//Add manager to the correct group first - as of now the group in question is service-desk-agents
			logger.debug("Manager not currently in required group, going ahead to add the manager to that group ");
			GroupManager groupManager = ComponentAccessor.getGroupManager();
			Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SERVICE_DESK_AGENTS);
			try {
				groupManager.addUserToGroup(user, group);
			} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
					OperationFailedException addUserToGroupException) {
				logger.error("Could not add manager to group service-desk-agents ", addUserToGroupException);
			}
		}
	}

	private void addManagerToCSARoleGroup(ApplicationUser user){
		logger.debug("Checking if the manager is in the necessary group service-desk-agents");
		if (!isManagerInCorrectGroupCSAR(user)) {
			//Add manager to the correct group first - as of now the group in question is service-desk-agents
			logger.debug("Manager not currently in required group, going ahead to add the manager to that group ");
			GroupManager groupManager = ComponentAccessor.getGroupManager();
			Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SERVICE_CSARMANAGER_AGENTS);
			try {
				groupManager.addUserToGroup(user, group);
			} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
					OperationFailedException addUserToGroupException) {
				logger.error("Could not add manager to group service-desk-agents ", addUserToGroupException);
			}
		}
	}



	private void addOwnerToCSARoleGroup(ApplicationUser user){
		logger.debug("Checking if the manager is in the necessary group service-desk-agents");
		if (!isOwnerInCorrectGroupCSAR(user)) {
			//Add manager to the correct group first - as of now the group in question is service-desk-agents
			logger.debug("Manager not currently in required group, going ahead to add the manager to that group ");
			GroupManager groupManager = ComponentAccessor.getGroupManager();
			Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SERVICE_CSAROWNER_AGENTS);
			try {
				groupManager.addUserToGroup(user, group);
			} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
					OperationFailedException addUserToGroupException) {
				logger.error("Could not add manager to group service-desk-agents ", addUserToGroupException);
			}
		}
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
