package com.dt.jira.xmatters.intgt.plugin.scheduler;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.project.Project;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.scheduling.PluginScheduler;
import com.dt.jira.plugin.rest.LoggerWrapper;
import com.dt.jira.xmatters.intgt.plugin.rest.ConfigResource.Config;

/**
 * Class defined to synch the External Service and Internal Service from jira
 * instance to xMatters
 * 
 * @author kiran.muthoju
 * 
 */
public class xMattersJobImpl implements xMattersJob, LifecycleAware {

	/* A unique job key */
	static final String KEY = xMattersJobImpl.class.getName() + ":instance";
	/* A unique job name */
	private static final String JOB_NAME = xMattersJobImpl.class.getName()
			+ ":job";
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());
	/*
	 * default 600000L = 10mins now. for schedule job in days = (days * 1000 *
	 * 60 * 60 * 24)
	 */
	private static long JOB_SCHEDULE_INTERVAL = 600000L;
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private static final String GRANT_TYPE = "password";

	private String userName = "";
	private String passWord = "";
	private String internalInstanceUrl = "";
	private String externalInstanceUrl = "";
	private Date lastRun = null;
	private long interval = 60000L;
	private static PluginSettingsFactory pluginSettingsFactory;
	private static ObjectMapper mapper = new ObjectMapper();
	private final PluginScheduler pluginScheduler;// provided by SAL
	private String query = "Atlassian test"; // default search
	private String jiraAdminEmail = "";

	public xMattersJobImpl(PluginScheduler pluginScheduler,
			PluginSettingsFactory psf) {
		super();
		logger.setInfoLogLevel();
		this.pluginScheduler = pluginScheduler;
		this.pluginSettingsFactory = psf;
		setConfiguration();
	}

	@Override
	public void onStart() {
		reschedule(query, getInterval());
	}

	/**
	 * Schedule job using jira plugin scheduler module
	 */
	@Override
	public void reschedule(String query, long interval) {

		this.query = query;
		pluginScheduler.scheduleJob(JOB_NAME,// unique name of the job
				xMattersScheduleJob.class, // class of the job
				new HashMap<String, Object>() {
					{
						put(KEY, xMattersJobImpl.this);
					}
				}, // data that needs to be passed to the job
				new Date(), // the time the job is to start
				interval);// interval between repeats, in milliseconds (days *
							// 1000 * 60 * 60 * 24)
		logger.info(String.format(
				"The xMatters integration job scheduled to run every %dms",
				interval));

	}

	public String getQuery() {
		return query;
	}

	public Date getLastRun() {
		return lastRun;
	}

	public void setLastRun(Date lastRun) {
		this.lastRun = lastRun;
	}

	/**
	 * Show error message after post method response.
	 * 
	 * @param m
	 *            - PostMethod
	 * @param errorcode
	 *            - input integer
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void showErrorMessage(PostMethod m, int errorcode)
			throws JsonMappingException, IOException {
		if (errorcode > 299) {
			List<ApiError> errors = mapper.readValue(
					m.getResponseBodyAsStream(),
					new TypeReference<List<ApiError>>() {
					});
			for (ApiError e : errors)
				if (logger.isInfoEnabled())
					logger.info(e.errorCode + " " + e.message);
		}
	}

	private static class ApiError {
		public String errorCode;
		public String message;
		public String[] fields;
	}

	/**
	 * Set the plugin configuration
	 */
	private void setConfiguration() {
		PluginSettings pluginSettings = this.pluginSettingsFactory
				.createGlobalSettings();
		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY
				+ ".xmattersUid");
		String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY
				+ ".xmattersPwd");
		String internalServiceURL = (String) pluginSettings
				.get(PLUGIN_STORAGE_KEY + ".xmattersInternalServiceUrl");
		String externalServiceURL = (String) pluginSettings
				.get(PLUGIN_STORAGE_KEY + ".xmattersExternalServiceUrl");
		String interval = (String) pluginSettings.get(PLUGIN_STORAGE_KEY
				+ ".xmattersInterval");

		String jiraAdminEmail = (String) pluginSettings.get(PLUGIN_STORAGE_KEY
				+ ".jiraAdminEmail");
		// logger.info("username: "+username);
		// logger.info("password: "+password);
		logger.info("internalServiceURL: " + internalServiceURL);
		logger.info("externalServiceURL: " + externalServiceURL);
		logger.info("jiraAdminEmail: " + jiraAdminEmail);
		logger.info("Set plugin configuration");
		setUserName(username);
		setPassWord(password);
		setExternalInstanceUrl(externalServiceURL);
		setInternalInstanceUrl(internalServiceURL);
		setJiraAdminEmail(jiraAdminEmail);
		
		try {
			long l = Long.parseLong(interval.trim());
			setInterval(l);
		} catch (Exception e) {
			// setInterval(600000L);
			logger.info("Exception in schedule job interval. Interval value should be in milliseconds(long) ");
		}

	}

	public String getTokenId() {
		// encoding byte array into base 64
		String userpwd = getUserName() + ":" + getPassWord();
		byte[] encoded = Base64.encodeBase64(userpwd.getBytes());
		String sid = new String(encoded);
		return sid;
	}

	public String syncSGandProducts() {
		HttpClient httpclient = new HttpClient();
		String accessToken = "Successfully done";

		setConfiguration();
		int response = 0;
		PutMethod method = null;
		try {
			method = new PutMethod(getExternalInstanceUrl());
			method.addRequestHeader("Content-Type", "application/json");
			method.addRequestHeader("Authorization", "Basic " + getTokenId());
			method.getParams().setParameter("ability.act.EditProperty", true);
		} catch (Exception e1) {
			accessToken = "Authentication Failed";
			return accessToken;
		}
		StringBuilder externalService = new StringBuilder();
		try {

			Project project = ComponentAccessor.getProjectManager()
					.getProjectObjByKey("ITIL");
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager()
					.getDefaultIssueType(project);
			CustomFieldManager cfManager = ComponentAccessor
					.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Solution Group - Product");
			FieldConfig fieldConfigSolution = customField
					.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor
					.getComponentOfType(OptionsManager.class);
			List<Option> solutionGroups = optionsManager
					.getOptions(fieldConfigSolution);
			
			StringBuilder parentService = null;
			externalService.append("[");

			for (Option opt : solutionGroups) {
				// if(opt.getValue().equals("CMS") ||
				// opt.getValue().equals("DDS")){
				parentService = new StringBuilder();
				
				if (!opt.getDisabled()) {
					parentService.append("{\"name\" :  \"" + opt.getValue()
							+ "\",\n ");
					List<Option> childs = opt.getChildOptions();
					StringBuilder childService = null;
					if (childs != null && childs.size() > 0) {
						
						childService = new StringBuilder();
						childService.append("\"childNodes\": [");
						int hasChilds = 0;
						for (Option child : childs) {
							if (!child.getDisabled()) {
								childService.append(" {\"name\" :  \""
										+ child.getValue() + "\"},");
							hasChilds = 1;
							}
						}
						String extService = childService.toString();
						if(hasChilds!=0){
							extService = extService.substring(0,
									extService.length() - 1);
							// childService.append(extService);
							childService.deleteCharAt(extService.length());
						}
						childService.append("] \n");
						parentService.append(childService);

					} else {
						childService = new StringBuilder();
						childService.append("\"childNodes\": []\n");
						parentService.append(childService);
					}
					parentService.append("},\n");
					externalService.append(parentService);
					// }
				}
			}
			if (externalService != null && !externalService.equals("")) {
				String _pextService = externalService.toString();
				if (_pextService != null && !_pextService.equals("")) {
					_pextService = _pextService.substring(0,
							_pextService.length() - 1);
					externalService.deleteCharAt(_pextService.length() - 1);
				}
			}
			externalService.append("]");

			logger.info(externalService.toString());
			StringEntity data = new StringEntity(externalService.toString());
			data.setContentType("application/json");

			method.setRequestBody(data.getContent());
			response = httpclient.executeMethod(method);
			logger.info("syncSGandProducts Response:"+response);

		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} catch (IOException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		if (response == 200 || response == 203 || response == 204) {
			sendMailNotification(externalService.toString());
			return accessToken;
		}
		accessToken = "Integration Failed";
		return accessToken;
	}

	public String syncLocationsandImpacted() {
		HttpClient httpclient = new HttpClient();
		String accessToken = "Successfully done";

		setConfiguration();
		int response = 0;
		PutMethod method = null;
		StringBuilder externalService = new StringBuilder();
		try {
			method = new PutMethod(getInternalInstanceUrl());
			method.addRequestHeader("Content-Type", "application/json");
			method.addRequestHeader("Authorization", "Basic " + getTokenId());
			method.getParams().setParameter("ability.act.EditProperty", true);
		} catch (Exception e1) {
			accessToken = "Authentication Failed";
			return accessToken;
		}

		try {

			Project project = ComponentAccessor.getProjectManager()
					.getProjectObjByKey("ITIM");

			CustomFieldManager customFieldManager = ComponentAccessor
					.getCustomFieldManager();
			IssueType incidentIssueType = null;
			Collection<IssueType> issueTypesProj = ComponentAccessor
					.getIssueTypeSchemeManager().getIssueTypesForProject(
							project);
			for (IssueType issueT : issueTypesProj) {
				if (!issueT.isSubTask()) { // exclude sub-task
					incidentIssueType = issueT;
				}
			}

			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager()
					.getDefaultIssueType(project);
			CustomFieldManager cfManager = ComponentAccessor
					.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Type");
			FieldConfig fieldConfigLoc = customField
					.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor
					.getComponentOfType(OptionsManager.class);
			List<Option> locations = optionsManager.getOptions(fieldConfigLoc);
			/*
			 * for(Option opt: locations){ System.out.println("locations: "+
			 * opt.getValue()); }
			 */

			customField = cfManager
					.getCustomFieldObjectByName("Impacted - Function");
			fieldConfigLoc = customField
					.getRelevantConfig(new IssueContextImpl(project,
							incidentIssueType));
			optionsManager = ComponentAccessor
					.getComponentOfType(OptionsManager.class);
			List<Option> impacted = optionsManager.getOptions(fieldConfigLoc);
			/*
			 * for(Option opt: impacted){ System.out.println("impacted: "+
			 * opt.getValue()); }
			 */
			
			StringBuilder parentService = null;
			externalService.append("[");
			for (Option opt : locations) {
				parentService = new StringBuilder();

				if (opt.getValue().equals("Internal")) {
					for (Option childOption : opt.getChildOptions()) {
						if (!childOption.getDisabled()) {
							// System.out.println("Locations: "+
							// childOption.getValue());
							parentService.append("{\"name\" :  \""
									+ childOption.getValue() + "\",\n ");

							StringBuilder childService = null;
							if (impacted != null && impacted.size() > 0) {
								// System.out.println("impacted length: "+
								// impacted.size());
								childService = new StringBuilder();
								childService.append("\"childNodes\": [");
								int hasChilds = 0;
								for (Option child : impacted) {
									if(!child.getDisabled()){
									childService.append(" {\"name\" :  \""
											+ child.getValue() + "\"},");
									hasChilds = 1;
									}
								}
								String extService = childService.toString();
								if(hasChilds!=0){
									extService = extService.substring(0,
											extService.length() - 1);
									// childService.append(extService);
									childService.deleteCharAt(extService.length());
								}
								childService.append("] \n");
								parentService.append(childService);
							} else {
								childService = new StringBuilder();
								childService.append("\"childNodes\": []\n");
								parentService.append(childService);
							}
							parentService.append("},\n");

						}
					}
					externalService.append(parentService);
				}

			}
			if (externalService != null && !externalService.equals("")) {
				String _pextService = externalService.toString();
				if (_pextService != null && !_pextService.equals("")) {
					_pextService = _pextService.substring(0,
							_pextService.length() - 1);
					externalService.deleteCharAt(_pextService.length() - 1);
				}
			}
			externalService.append("]");

			logger.info("************** externalService "
					+ externalService.toString());
			StringEntity data = new StringEntity(externalService.toString());
			data.setContentType("application/json");

			method.setRequestBody(data.getContent());
			response = httpclient.executeMethod(method);
			logger.info("syncLocationsandImpacted Response:"+response);
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} catch (IOException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		if (response == 200 || response == 203 || response == 204) {
			sendMailNotification(externalService.toString());
			return accessToken;
		}
		accessToken = "Integration Failed";
		return accessToken;
	}

	/**
	 * Get the data from sales force which contains projects and versions.
	 * 
	 * @param accessToken
	 *            - <String> - token
	 * @return <Map> - returns the Map which key and value as projectkey and
	 *         customobject
	 */
	public Map getAllSalesForceProjects(String accessToken) {
		try {

		} catch (Exception e) {
			logger.error("Exception in getAllSalesForceProjects() "
					+ e.getMessage());
		} finally {
			// m.releaseConnection();
		}
		return null;
	}

	/**
	 * Set the sleep interval
	 * 
	 * @param milliseconds
	 *            - <long>
	 */
	public void setSleepInterval(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ie) {
			ie.getStackTrace();
		}
	}
 private void sendMailNotification(String errorMsg){
			try{
			MailServerManager mailServerManager = ComponentAccessor
	                .getMailServerManager();
	        SMTPMailServer mailServer = mailServerManager
	                .getDefaultSMTPMailServer();
	       
	        Properties p=new Properties();
	        
	        
	        String emailProperty = getJiraAdminEmail();
	        Email email = new Email(emailProperty);
	        email.setFrom(mailServer.getDefaultFrom());
	        email.setSubject("xMatters integration successfull");
	        email.setMimeType("text/html");
	        email.setBody(errorMsg);
	        SingleMailQueueItem item = new SingleMailQueueItem(email);
	        ComponentAccessor.getMailQueue().addItem(item);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public String getInternalInstanceUrl() {
		return internalInstanceUrl;
	}

	public void setInternalInstanceUrl(String internalInstanceUrl) {
		this.internalInstanceUrl = internalInstanceUrl;
	}

	public String getExternalInstanceUrl() {
		return externalInstanceUrl;
	}

	public void setExternalInstanceUrl(String externalInstanceUrl) {
		this.externalInstanceUrl = externalInstanceUrl;
	}

	public String getJiraAdminEmail() {
		return jiraAdminEmail;
	}

	public void setJiraAdminEmail(String jiraAdminEmail) {
		this.jiraAdminEmail = jiraAdminEmail;
	}

	
}