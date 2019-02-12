package  com.dt.jira.plugin.servicedesk.standardbauchange.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import com.atlassian.jira.issue.index.IssueIndexingService;

import com.atlassian.crowd.embedded.api.Group;
import com.atlassian.crowd.exception.GroupNotFoundException;
import com.atlassian.crowd.exception.OperationFailedException;
import com.atlassian.crowd.exception.OperationNotPermittedException;
import com.atlassian.crowd.exception.UserNotFoundException;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.plugin.servicedesk.standardbauchange.rest.ITSMCHGConfig;
import com.dt.jira.plugin.servicedesk.standardbauchange.service.ServiceDeskLDAPIntegration;
import com.dt.jira.plugin.servicedesk.standardbauchange.service.LDAPDetails;
import com.atlassian.jira.event.type.EventDispatchOption;
/**
 * StandardBAUEventListener.java event listener class to request new template from Service Desk portal to ITSM change management
 *
 */

public class StandardBAUEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
    private final JiraAuthenticationContext authenticationContext;            
	private final String FIELD_IMPACTED_FUNCTION="Impacted - Function"; 
    private final String FIREWALL_VALUES_YES="Yes"; 
	private final String STANDARD_CHANGE = "Standard Change - "; 
	
	private final Logger logger = Logger
			.getLogger(StandardBAUEventListener.class);
    private final IssueIndexingService issueIndexingService;  
   
    private static final String PLUGIN_STORAGE_KEY = ITSMCHGConfig.class.getName();
	private final String STATUS_PENDING_MANAGERS_APPROVAL = "PENDING APPROVAL - MANAGER";
	private final String STATUS_PENDING_OWNERS_APPROVAL = "PENDING APPROVAL - OWNER";
	private final String STATUS_PENDING_SME_APPROVAL = "PENDING APPROVAL - SME";
	private final String PROJECT_KEY_NAME = "SM";
	//private final String FIELD_REPORTERS_MANAGER = "Reporter's Manager";
	private final String FIELD_REPORTERS_DEPORTMENT = "Reporter's Title";
	private final String GROUP_SERVICE_DESK_AGENTS = "servicedesk_manager_CSAR";
	private final String GROUP_SME = "SM-STD-CHG-SME";
	
	private final ServiceDeskLDAPIntegration  serviceDeskLDAPIntegration;
	/**
    *constructor
    */
	public StandardBAUEventListener(EventPublisher eventPublisher,
			PluginSettingsFactory pluginSettingsFactory,OptionsManager optionsManager,IssueIndexingService issueIndexingService,
                                 JiraAuthenticationContext authenticationContext,UserManager userManager,TransactionTemplate transactionTemplate) {
		this.eventPublisher = eventPublisher;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
        this.authenticationContext=authenticationContext;
        this.issueIndexingService=issueIndexingService;
        this.serviceDeskLDAPIntegration = new ServiceDeskLDAPIntegration(userManager, pluginSettingsFactory, transactionTemplate);
		}
	
	
	
    /**
     * This event occurs when status of Service Management ticket moved Pending MANAGER Approval       *  
     * @param issueEvent
     */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		 logger.info("*********start***********StandardBAUEventListener********");	
		 Map<String,Object> issueEventMap=issueEvent.getParams();
		Issue issueParent = issueEvent.getIssue();
		 Project project = issueParent.getProjectObject();
		 String statusName= issueParent.getStatusObject().getName();
		 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		String chgUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".chgUrl");
		String templatelinkURL  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".templatelinkURL");
		 String username  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".itsmUserName");
		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".itsmPassword");
		
		String ldapUid  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
		String ldapPwd  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
		
		
		List accDetails = new ArrayList();
			LDAPDetails myRestResourceModel=new LDAPDetails();
		Long eventTypeId = issueEvent.getEventTypeId();
		String impactedFunctionValue = getCustomFeildValue(FIELD_IMPACTED_FUNCTION,issueParent);
		ApplicationUser user = issueParent.getReporterUser();
		
		if (eventTypeId.equals(EventType.ISSUE_CREATED_ID) && "Service Management".equals(project.getName())) {
			logger.info("StandardBAUEventListener  Executed issue created");
			
		     try {
				// get reporter LDAP details
				accDetails = serviceDeskLDAPIntegration.getUserAccountDetailsByMailId(user.getEmailAddress());
				int acc = Integer.parseInt((String) accDetails.get(0));
				if (acc == 0) {
					myRestResourceModel=(LDAPDetails) accDetails.get(2);
					logger.info("LDAP UserProfile User Name : "+myRestResourceModel.getUserName());
					logger.info("LDAP UserProfile User MailID : "+myRestResourceModel.getUserMailId());
					logger.info("LDAP UserProfile User Phone Number : "+myRestResourceModel.getPhoneNo());
					logger.info("LDAP UserProfile User Department : "+myRestResourceModel.getDeptName());
					logger.info("LDAP UserProfile User Manager's Name : "+myRestResourceModel.getMngrName());
					logger.info("LDAP UserProfile User Manager's Account Name : "+
                            myRestResourceModel.getMngrAccountName());
					logger.info("LDAP UserProfile User Manager's MailID : "+myRestResourceModel.getMngrMailId());
					logger.info("CriticalAccessApprovalEventListener LDAP UserProfile User Manager's account Name : "
                            + myRestResourceModel.getMngrAccountName());
					logger.info("CriticalAccessApprovalEventListener LDAP UserProfile Job Title Name : "
                            + myRestResourceModel.getJobTitle());

                   
                    ApplicationUser reportMgrUser = ComponentAccessor.getUserManager().getUserByName(myRestResourceModel.getMngrAccountName());
                    logger.debug("Managers name: " + reportMgrUser.getName());
                   

                    //Update the Team member's job title
					updateCustomFieldValue(FIELD_REPORTERS_DEPORTMENT, myRestResourceModel.getJobTitle(),issueParent);
				}
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		}
		logger.debug("Status Name : " + statusName);
		logger.debug("Project Name : " + project.getName());
		logger.debug("Event Type Id : " + eventTypeId);
		if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID) || eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
            logger.info("StandardBAUEventListener update event "+issueEventMap.get("eventsource"));
			if("Service Management".equals(project.getName())){
			
			 try {
				
				user = issueParent.getReporterUser();
				accDetails = serviceDeskLDAPIntegration.getUserAccountDetailsByMailId(user.getEmailAddress());
				int acc = Integer.parseInt((String) accDetails.get(0));
				if (acc == 0) {
					myRestResourceModel=(LDAPDetails) accDetails.get(2);
					logger.info("LDAP UserProfile User Name : "+myRestResourceModel.getUserName());
					logger.info("LDAP UserProfile User MailID : "+myRestResourceModel.getUserMailId());
					logger.info("LDAP UserProfile User Phone Number : "+myRestResourceModel.getPhoneNo());
					logger.info("LDAP UserProfile User Department : "+myRestResourceModel.getDeptName());
					logger.info("LDAP UserProfile User Manager's Name : "+myRestResourceModel.getMngrName());
					logger.info("LDAP UserProfile User Manager's Account Name : "+
                            myRestResourceModel.getMngrAccountName());
					logger.info("LDAP UserProfile User Manager's MailID : "+myRestResourceModel.getMngrMailId());
					logger.info(" StandardBAUEventListener LDAP UserProfile User Manager's account Name : "
                            + myRestResourceModel.getMngrAccountName());
					logger.info("StandardBAUEventListener LDAP UserProfile Job Title Name : "
                            + myRestResourceModel.getJobTitle());
                   	}
			 } catch (Exception e) {
				e.printStackTrace();
			 }


				
				/* User is not a Director or above Director. He should be go through the Manager approval */
				if( "Service Management".equals(project.getName()) && "PENDING APPROVAL - MANAGER".equals(statusName)   ){
				System.out.println("Pending approval manager");
				 ApplicationUser reportMgrUser = ComponentAccessor.getUserManager().getUserByName(myRestResourceModel.getMngrAccountName());
				updateAssignee(reportMgrUser,issueParent);
				/* User is a Director or above Director. He should skip the Manager approval */	
				} else if("Service Management".equals(project.getName()) && "PENDING APPROVAL - SUPPORT".equals(statusName)){
				
				// Not applicable
				
				} else if("Service Management".equals(project.getName()) && "Resolved".equals(statusName) ){
						HttpPost post = new HttpPost(chgUrl);;	
						ArrayList<NameValuePair> postParameters;		
						HttpClient defaultHttpClient =null;	
					try {
						logger.info("************StandardBAUEventListener********"+ project.getName() + " Status Name: "+ statusName);		

						String summary = issueParent.getKey()+":"+STANDARD_CHANGE +issueParent.getSummary();		// As per the requirement append the Standard Change	
						post = new HttpPost(chgUrl);
						// set headers
						post.setHeader("Content-Type", "application/json");
						post.setHeader("Authorization", "Basic " + getTokenId(username,password));
					 
						postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("Summary", summary));
						postParameters.add(new BasicNameValuePair("Impacted", impactedFunctionValue));
						post.setEntity(new UrlEncodedFormEntity(postParameters));
						defaultHttpClient = HttpClientBuilder.create().build();
						HttpResponse response = defaultHttpClient.execute(post);
						InputStream inputStream = response.getEntity().getContent();
						String respString  = getStringFromInputStream(inputStream);
						if(respString!=null && respString.equals("Added new cascade value")){
							//Create the new template on ITSM change management
							createNewTemplate(issueParent,impactedFunctionValue,templatelinkURL,username,password);
						}						
						} catch (Exception e) {
							e.printStackTrace();
						}
						 finally {
							post.releaseConnection();               
						}						
				
					
				}
			 }
			
		}

		if("Service Management".equals(project.getName()) && STATUS_PENDING_SME_APPROVAL.equals(statusName)
				&& eventTypeId.equals(EventType.ISSUE_ASSIGNED_ID)) {
			//Make sure that the SME is in the group SM-STD-CHG-SME
			ApplicationUser smeAssignee = issueParent.getAssignee();
			updateSMEsGroups(smeAssignee);
		}
		
	}

	/**
	 * Adds the SME to the groups service-desk-agents and SM-STD-CHG-SME if he is not already added.
	 *
	 * @param smeAssignee
     */
	private void updateSMEsGroups(ApplicationUser smeAssignee) {
		if(smeAssignee!=null) {
			/*
			   Check if SME is in the jira group service-desk-agents and SM-STD-CHG-SME and if not, add SME to
			   those groups - LDAP verification not required.
			 */
			logger.debug("Checking if the SME is in the necessary group SM-STD-CHG-SME");
			GroupManager groupManager = ComponentAccessor.getGroupManager();
			/*if (!isSMEInServiceDeskAgents(smeAssignee)) {
				logger.info("SME not currently in Service Desk Agents Group going ahead to add the SME to that group ");

				Group group = ComponentAccessor.getCrowdService().getGroup("service-desk-agents");
				try {
					groupManager.addUserToGroup(smeAssignee, group);
				} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
						OperationFailedException addUserToGroupException) {
					logger.error("Could not add SME to Service Desk Agents Group ", addUserToGroupException);
				}
			}*/
			if (!isSMEInCorrectGroup(smeAssignee)) {
				logger.info("SME not currently in required group, going ahead to add the SME to that group ");
				Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SME);
				try {
					groupManager.addUserToGroup(smeAssignee, group);

				} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
						OperationFailedException addUserToGroupException) {
					logger.error("Could not add SME to group SM-STD-CHG-SME ", addUserToGroupException);
				}
			}
		}
	}

	/**
	* Update assignee on current issue
	*/
	private void updateAssignee(ApplicationUser updateValue,Issue currentIssue){
        logger.info("StandardBAUEventListener UPDATE VALUE IS " + updateValue);
		IssueManager issueManager = ComponentAccessor.getIssueManager();
		CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
		
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		//String key = currentIssue.getKey();
		MutableIssue mutableIssue = (MutableIssue) currentIssue;	
		
		if(updateValue!=null){
			//mutableIssue.setCustomFieldValue(reportMgrCustomField, updateValue);

			/*
			   Check if manager is in the jira group service-desk-agents and if not, add manager to that group and
			   then set to assignee - LDAP verification not required.
			 */
			logger.info("Checking if the manager is in the necessary group service-desk-agents");
			if (!isManagerInCorrectGroup(updateValue)) {
				//Add manager to the correct group first - as of now the group in question is service-desk-agents
				logger.info("Manager not currently in required group, going ahead to add the manager to that group ");
				GroupManager groupManager = ComponentAccessor.getGroupManager();
				Group group = ComponentAccessor.getCrowdService().getGroup(GROUP_SERVICE_DESK_AGENTS);
				try {
					groupManager.addUserToGroup(updateValue, group);
					
				} catch (GroupNotFoundException | UserNotFoundException | OperationNotPermittedException |
						OperationFailedException addUserToGroupException) {
					logger.info("Could not add manager to group service-desk-agents ", addUserToGroupException);
				}
			}
			
			logger.info("Confirmed that the manager is in the correct group, going ahead to set to assignee");
			if(updateValue.isActive()){	
				mutableIssue.setAssignee(updateValue);			
				Issue newIssue = issueManager.updateIssue(authenticationContext.getLoggedInUser(),mutableIssue,EventDispatchOption.ISSUE_UPDATED,true);
				logger.info("Successfully updated the Manager as assignee : "+updateValue);
			}else{
				logger.info("================User is Not Available ");
			}

            mutableIssue.store();
			setReindex(mutableIssue);
		 
		}
	
		
	}
	private boolean isManagerInCorrectGroup(ApplicationUser managerUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(managerUser, GROUP_SERVICE_DESK_AGENTS);
	}

	private boolean isSMEInCorrectGroup(ApplicationUser smeUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(smeUser, GROUP_SME);
	}

	private boolean isSMEInServiceDeskAgents(ApplicationUser smeUser) {
		GroupManager groupManager = ComponentAccessor.getGroupManager();
		return groupManager.isUserInGroup(smeUser, "service-desk-agents");
	}

	
	/* * Helper method to update the custom field.   
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
		logger.info("Successfully updated custom field value : " + updateValue);
	}

	private void addUserToServiceDeskAgents(ApplicationUser user){
		logger.info("Checking if the manager is in the necessary group service-desk-agents");
		if (!isManagerInCorrectGroup(user)) {
			//Add manager to the correct group first - as of now the group in question is service-desk-agents
			logger.info("Manager not currently in required group, going ahead to add the manager to that group ");
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
	/**
	*Create new template under the change management and added new cascade value
	*/
     private void createNewTemplate(Issue issueParent,String impactedFunctionValue,String  templatelinkURL,String username,String password){
	 HttpPost post = null;
	 ArrayList<NameValuePair> postParameters;		
	 HttpClient defaultHttpClient =null;	
	 try {
						// add new template
						String desc = issueParent.getDescription();
						String template_name = impactedFunctionValue +" - "+ issueParent.getKey()+":"+ STANDARD_CHANGE + issueParent.getSummary(); // template's name
						String template_description = impactedFunctionValue +" - "+STANDARD_CHANGE + issueParent.getSummary(); // template's description
						String template_projects = "All"; // list of projects separated by commas
						String template_code = StringEscapeUtils.unescapeHtml3(desc); // HTML code of the template
						System.out.println("template_code :"+ template_code);
						post = new HttpPost(templatelinkURL);
						post.setHeader("Content-Type", "application/x-www-form-urlencoded");
						post.setHeader("Authorization", "Basic " + getTokenId(username,password));
						
						postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("template_label", template_name));		
						postParameters.add(new BasicNameValuePair("template_description", template_description));
						postParameters.add(new BasicNameValuePair("template_value", template_code));
						postParameters.add(new BasicNameValuePair("template_projects", template_projects));		
						
						post.setEntity(new UrlEncodedFormEntity(postParameters,"UTF-8"));
						defaultHttpClient = HttpClientBuilder.create().build();
						   
						HttpResponse response1 = defaultHttpClient.execute(post);
										
					} catch (Exception e) {
						e.printStackTrace();
					}
					finally {
					post.releaseConnection();               
					}
	 
	}	 
	// convert InputStream to String
	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
  /**
    *is firewall field selected as Yes
    */
    private Boolean  hasIssueLinks(Issue issue){
      
        RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
        RemoteIssueLinkService.RemoteIssueLinkListResult result=remoteIssueLinkService.getRemoteIssueLinksForIssue(authenticationContext.getUser(), issue);
        if(result.isValid()){
            List<RemoteIssueLink>	remoteLinkList=result.getRemoteIssueLinks(); 
            if(remoteLinkList!=null){
            for(RemoteIssueLink remoteLink:remoteLinkList){
             if(remoteLink.getTitle().contains("CHG")){
			 return false;
			 }
            }
            }
        }
		return true;
		
    }
            
     private String getCustomFeildValue(String name,Issue issue){
        String value="";
        CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(name);
        String feildType=customFeildObj.getCustomFieldType().getKey();
        if(feildType.equals("com.atlassian.jira.plugin.system.customfieldtypes:cascadingselect")){
       logger.info("=======custom feild values solution group product : ---"+customFeildObj.getValue(issue));
       HashMap<String, Option> hashMapEntries = (HashMap<String, Option>)customFeildObj.getValue(issue);
            if (hashMapEntries != null) {
                Option parent = hashMapEntries.get(null);
                    Option child = hashMapEntries.get("1");
                if(parent!=null && child==null){
                 value=parent.getValue();   
                }
                else if(parent!=null && child!=null){
               value=parent.getValue()+","+child.getValue(); 
                }
              }
            logger.info("value : "+value);
            }else if(feildType.equals("com.atlassian.jira.plugin.system.customfieldtypes:select")){
                value=""+customFeildObj.getValue(issue); 
            }else if(feildType.equals("com.atlassian.jira.plugin.system.customfieldtypes:multiselect")){
             List<Option>  envlist=(List<Option>)customFeildObj.getValue(issue);
            if(envlist!=null){
            for(Option opt:envlist){
             value= value+","+opt.getValue();  
            }
            }
          }
        return value;
    }        
            
   
 /**
 *creating token for basic authentication
 */
public String getTokenId(String username,String password) {		
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64((username+":"+password).getBytes());		
		String sid = new String(encoded);
		return sid;
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
