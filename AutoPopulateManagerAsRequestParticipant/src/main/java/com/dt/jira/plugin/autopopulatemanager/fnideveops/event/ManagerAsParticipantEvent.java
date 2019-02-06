package com.dt.jira.plugin.autopopulatemanager.fnideveops.event;


import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.plugin.autopopulatemanager.fnideveops.rest.LdapSrvc;
import com.dt.jira.plugin.autopopulatemanager.fnideveops.rest.ServiceDeskLDAPIntegrationResourceModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.*;

/**
 * ManagerAsParticipantEvent.java event listener class on Issue Create
 * Update reporter's manager name as a participant.
 *
 */
@Named("managerAsParticipantEvent")
public class ManagerAsParticipantEvent implements InitializingBean,
		DisposableBean {

	@ComponentImport
	private final EventPublisher eventPublisher;
	@ComponentImport
	private final IssueIndexingService issueIndexingService;
	@ComponentImport
	private final UserManager userManager;

	@ComponentImport
	private final PluginSettingsFactory pluginSettingsFactory;

	@ComponentImport
	private final TransactionTemplate transactionTemplate;

	private final String FIELD_IMPACTED_FUNCTION = "Impacted - Function";
	private final String FIREWALL_VALUES_YES = "Yes";
	private final String STANDARD_CHANGE = "Standard Change - ";
	private final String STATUS_INTIATED = "Initiated";
	private final String PROJECT_KEY_NAME = "FNIDEVOPS";
	private final String FIELD_REPORTERS_MANAGER = "Reporter's Manager";
	private final Logger logger = Logger
			.getLogger(ManagerAsParticipantEvent.class);

	private static final String PLUGIN_STORAGE_KEY = LdapSrvc.class.getName();

	/**
	 * constructor
	 */
	@Inject
	public ManagerAsParticipantEvent(EventPublisher eventPublisher, IssueIndexingService issueIndexingService,UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate){
		this.eventPublisher = eventPublisher;
		this.issueIndexingService =issueIndexingService;
		this.userManager = userManager;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.transactionTemplate = transactionTemplate;

	}


	/**
	 * This event occurs only on create the Issue and update reporter's manager name as a participant.
	 *
	 * @param issueEvent
	 */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent){

		logger.debug("Issue ManagerAsParticipantEvent");
		PluginSettings pluginSettings =  this.pluginSettingsFactory.createGlobalSettings();;
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue currentIssue = issueEvent.getIssue();
		List accDetails = new ArrayList();
		// Get the Reporter from current issue
		ApplicationUser user = currentIssue.getReporter();
		String mgrName = "";
		logger.debug("ManagerAsParticipantEvent  Executed issue created"+ currentIssue.getStatus().getName());
		Project project = currentIssue.getProjectObject();
		if (eventTypeId.equals(EventType.ISSUE_CREATED_ID) &&  PROJECT_KEY_NAME.equals(project.getKey()) ) {
			logger.debug("ManagerAsParticipantEvent  Executed issue created");

			// Get LDAP details from UI
			String reportsName = user.getEmailAddress();
			ServiceDeskLDAPIntegrationResourceModel myRestResourceModel=new ServiceDeskLDAPIntegrationResourceModel();
			logger.debug("Reporter's Name : " + reportsName);
			accDetails = getUserAccountDetailsByMailId(reportsName);
			logger.debug("Results info: "+accDetails);
			int acc = Integer.parseInt((String) accDetails.get(0));
			if (acc == 0) {
				myRestResourceModel = (ServiceDeskLDAPIntegrationResourceModel) accDetails.get(2);
				/*logger.debug("LDAP UserProfile User Name : "+myRestResourceModel.getUserName());
				logger.debug("LDAP UserProfile User MailID : "+myRestResourceModel.getUserMailId());
				logger.debug("LDAP UserProfile User Phone Number : "+myRestResourceModel.getPhoneNo());
				logger.debug("LDAP UserProfile User Department : "+myRestResourceModel.getDeptName());
				logger.debug("LDAP UserProfile User Manager's Name : "+myRestResourceModel.getMngrName());
				logger.debug("LDAP UserProfile User Manager's Account Name : "+myRestResourceModel.getMngrAccountName());
				logger.debug("LDAP UserProfile User Manager's MailID : "+myRestResourceModel.getMngrMailId());*/
				logger.debug("LDAP UserProfile User Manager's Name : " + myRestResourceModel.getMngrAccountName());
				mgrName = myRestResourceModel.getMngrAccountName();
				//Update the reporter's manager and make the manager as the assignee
				if(mgrName!=null && !mgrName.trim().equals("")) {
					ApplicationUser reportMgrUser = ComponentAccessor.getUserManager().getUserByName(mgrName);
					logger.info("Going ahead For the issue "+ currentIssue.getKey() + " to update the manager to " + reportMgrUser.getName());
					updateReportsManagerCustomFieldValue(FIELD_REPORTERS_MANAGER, reportMgrUser, currentIssue);
					logger.info("For Issue " + currentIssue.getKey() + " Reporter Manager is updated to " + reportMgrUser + " Successfully" );
				}
			}

		}


     }

	/**
	 * Get the User Account details from LDAP
	 * @param userMailId - user's email-id
     * @return - List of values of ldap attributes
     */
	public List getUserAccountDetailsByMailId(String userMailId) {
		logger.debug("About to initialize getUserAccountDetailsByMailId");
		List result = new ArrayList();
		DirContext ctx = null;
		String line;
		final int lhs = 0;
		final int rhs = 1;
		NamingEnumeration namingenumeration = null;
		ctx = getDirectoryContext();

		if (ctx == null) {
			result.add("1");
			result.add("LDAP instance down");

			return result;
		}

		try {
			PluginSettings pluginSettings =  this.pluginSettingsFactory.createGlobalSettings();;
			String basedn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBaseDn");
			String s2 = "(mail=" + userMailId + ")";
			String as[] = {"fn","sn", "givenname", "mobile","telephoneNumber", "mail", "cn", "manager", "department", "title" };

			SearchControls searchcontrols = new SearchControls();
			searchcontrols.setSearchScope(2);
			namingenumeration = ctx.search(basedn, s2, searchcontrols);
			logger.debug("Inside getUserAccountDetailsByMailId after searchcontrols");
			if (namingenumeration != null && namingenumeration.hasMoreElements()) {

				ServiceDeskLDAPIntegrationResourceModel myRestResourceModel=new ServiceDeskLDAPIntegrationResourceModel();
				SearchResult searchresult = (SearchResult) namingenumeration.nextElement();
				String dn = searchresult.getName() + "," + basedn;
				myRestResourceModel.setFirstName((String) getAttribsForKey(searchresult,as[0]).get(0));
				myRestResourceModel.setLastName((String) getAttribsForKey(searchresult,as[1]).get(0));
				myRestResourceModel.setGivenName((String) getAttribsForKey(searchresult,as[2]).get(0));
				myRestResourceModel.setMobileNo((String) getAttribsForKey(searchresult,as[3]).get(0));
				myRestResourceModel.setPhoneNo((String) getAttribsForKey(searchresult, as[4]).get(0));
				myRestResourceModel.setUserMailId((String) getAttribsForKey(searchresult,as[5]).get(0));
				myRestResourceModel.setUserName((String) getAttribsForKey(searchresult, as[6]).get(0));
				myRestResourceModel.setMngrDetails((String) getAttribsForKey(searchresult, as[7]).get(0));
				myRestResourceModel.setDeptName((String) getAttribsForKey(searchresult, as[8]).get(0));
				myRestResourceModel.setJobTitle((String) getAttribsForKey(searchresult, as[9]).get(0));
				if(myRestResourceModel.getMngrDetails()!=null){
					String[] strings = myRestResourceModel.getMngrDetails().split(",");

					TreeMap<String, String> map = new TreeMap<String, String>();
					for (String string : strings) {
						String[] pair = string.trim().split("=");
						map.put(pair[lhs].trim(), pair[rhs].trim());
					}
					myRestResourceModel.setMngrName(map.get("CN"));

					List result1 = getUserManagerMailIdByName(myRestResourceModel.getMngrName());
					myRestResourceModel.setMngrMailId((String) result1.get(1));
					myRestResourceModel.setMngrAccountName((String) result1.get(2));
				}

				result.add("0");
				result.add("successful");
				result.add(myRestResourceModel);

			} else {
				result.add("5");
				result.add("User Mail ID " + userMailId + " doesnot exist");
				return result;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			result.add("1");
			result.add("LDAP operation failed while retrieving User Profile for user Mail Id "
					+ userMailId);
			logger.debug("LDAP operation failed while retrieving User Profile for user Mail Id "
					+ userMailId);
			return result;
		} finally {
			try {
				if (namingenumeration != null)
					namingenumeration.close();
				if (ctx != null)
					ctx.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	private List getAttribsForKey(SearchResult searchresult, String s)
			throws Exception {
		String as[] = new String[1];
		as[0] = s;
		Attributes attributes = searchresult.getAttributes();

		ArrayList arraylist = new ArrayList();
		if (attributes == null)
			throw new Exception("Entry " + searchresult.getName()+ " has none of the specified attributes\n");
		Attribute attribute = attributes.get(s);

		if (attribute != null) {
			for (int i = 0; i < attribute.size(); i++) {
				String name = (String) attribute.get(i);
				arraylist.add(name);
			}

		} else {
			arraylist.add(attribute);
		}

		return arraylist;
	}

	/**
	 * Returns the LDAP Directory object
	 * @return
     */
	private DirContext getDirectoryContext()
	{
		PluginSettings pluginSettings =  this.pluginSettingsFactory.createGlobalSettings();;
		Properties properties = new Properties();
		InitialDirContext initialdircontext = null;
		String initCtx  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapInitCtx");
		String srvrName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapSrvrName");
		String baseDn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBaseDn");
		String username  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
		try
		{
			properties.put("java.naming.factory.initial",initCtx);
			properties.put("java.naming.provider.url", srvrName);
			properties.put("java.naming.security.principal",username);
			properties.put("java.naming.security.credentials",password);
			initialdircontext = new InitialDirContext(properties);
			logger.debug("LDAP connection object from  LDAP Server[DirectoryContextFactory][getDirectoryContext]::"+initialdircontext);


			return initialdircontext;
		}catch (Exception e)
		{
			e.printStackTrace();
			return null;
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
	 * Helper method to update the reporter's manager custom field and make manager as the Request Participant.
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
			modifiedFields = mutableIssue.getModifiedFields();
			fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(reportMgrCustomField);
			modifiedValue = (ModifiedValue) modifiedFields.get(reportMgrCustomField.getId());
			reportMgrCustomField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);

			mutableIssue.store();
			setReindex(mutableIssue);
		}
		logger.debug("Successfully updated the reporters Manager : "+updateValue);
	}
	private void setReindex(MutableIssue mutableIssue) {
		try {
			issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.debug("index issue" + ie.getMessage());
		}
	}
	/**
	 * Get the User Account details from LDAP
	 * @param userMngrName - username
	 * @return - List of values of ldap attributes
	 */
	private   ArrayList getUserManagerMailIdByName(String userMngrName) {
		ArrayList arraylist = new ArrayList();
		DirContext dircontext = null;
		NamingEnumeration namingenumeration = null;
		PluginSettings pluginSettings =  this.pluginSettingsFactory.createGlobalSettings();;
		String basedn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBaseDn");
		String managermailid = null;
		String manageraccountname = null;
		dircontext = getDirectoryContext();

		try {

			SearchControls searchcontrols = new SearchControls();
			String[] attributes = { "mail","sAMAccountName" };
			searchcontrols.setReturningAttributes(attributes);
			searchcontrols.setSearchScope(SearchControls.SUBTREE_SCOPE);
			namingenumeration = dircontext.search(basedn, "(&(cn=" + userMngrName + ")(mail=*))", searchcontrols);

			if (namingenumeration != null && namingenumeration.hasMoreElements()) {
				SearchResult searchresult = (SearchResult) namingenumeration.nextElement();
				managermailid = (String) getAttribsForKey(searchresult,attributes[0]).get(0);
				manageraccountname = (String) getAttribsForKey(searchresult,attributes[1]).get(0);
				if (getAttribsForKey(searchresult, attributes[0]).get(0).equals(null)||
						getAttribsForKey(searchresult, attributes[0]).get(0).equals("")) {
					arraylist.add("5");
					arraylist.add("empty");
				} else {
					arraylist.add("0");
					arraylist.add(managermailid);
					arraylist.add(manageraccountname);
				}
			} else {
				arraylist.add("3");
				arraylist.add("Manager MailId " + userMngrName + " does not exist");
			}
		} catch (NullPointerException nullpointerexception) {
			nullpointerexception.toString();
		} catch (Exception exception) {
			exception.printStackTrace();
			arraylist.add("4");
			arraylist.add("LDAP operation failed while retrieving mailId info for Manager "+ userMngrName);
			return arraylist;
		} finally {
			try {
				if (namingenumeration != null) {
					namingenumeration.close();
				}
				if (dircontext != null) {
					dircontext.close();
				}
			} catch (Exception exception2) {
				exception2.printStackTrace();
			}
		}
		return arraylist;
	}
}
