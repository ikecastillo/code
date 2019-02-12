package com.dt.jira.plugin.scheduler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.scheduling.PluginScheduler;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;

/**
 * Class defined to synch the projects/versions from jira instance to salesforce
 * @author kiran.muthoju
 *
 */
public class SyncInnotasSchedulerImpl implements  SyncInnotasScheduler,LifecycleAware {

	/* A unique job key  */ 
	static final String KEY = SyncInnotasSchedulerImpl.class.getName() + ":instance";
	/* A unique job name  */
	private static final String JOB_NAME = SyncInnotasSchedulerImpl.class.getName() + ":job";
	/* Logger */
	public final Logger logger = Logger.getLogger(SyncInnotasSchedulerImpl.class);
	
	private String userName = "";
	private String passWord = "";
	private String instanceUrl = "";
	private String oauth2Url = "";	
	private String clientId = "" ;
	
	private String clientSecretKey = ""; 
	private Date lastRun = null;
	private long interval =  86400000L;
	private static PluginSettingsFactory pluginSettingsFactory;
	private static ObjectMapper mapper = new ObjectMapper();
	private final PluginScheduler pluginScheduler;// provided by SAL
	private String query = "Atlassian test"; // default search
	private final InnotasCacheService innotasCacheService;

	public SyncInnotasSchedulerImpl(PluginScheduler pluginScheduler,PluginSettingsFactory psf,InnotasCacheService ics) {
		super();
		this.pluginScheduler = pluginScheduler;
		this.pluginSettingsFactory = psf;	
		this.innotasCacheService = ics;		
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
		pluginScheduler.scheduleJob(
				JOB_NAME,// unique name of the job
				ScheduleJob.class,	// class of the job
				new HashMap<String,Object>() {{
					put(KEY, SyncInnotasSchedulerImpl.this);
				}},							 // data that needs to be passed to the job
				new Date(),					// the time the job is to start
				interval);// interval between repeats, in milliseconds	(days * 1000 * 60 * 60 * 24)
		logger.info(String.format("The  innotas integration job scheduled to run every %dms", interval));
	
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
	 * @param m - PostMethod
	 * @param errorcode - input integer
	 * @throws JsonMappingException
	 * @throws IOException
	 */
  public  void showErrorMessage(PostMethod m, int errorcode) throws JsonMappingException, IOException{
	  if (errorcode > 299) {
			List<ApiError> errors = mapper.readValue(m.getResponseBodyAsStream(), new TypeReference<List<ApiError>>() {} );
			for (ApiError e : errors)
				if(logger.isInfoEnabled())
					logger.info(e.errorCode + " " + e.message);
		}
  }
 private static class ApiError {
		public String errorCode;
		public String message;
		public String [] fields;
 }

	
	/**
	 * Show error message while deleting the SF object
	 * @param m - <DeleteMethod> 
	 * @param errorcode - <int> error codes
	 * @throws JsonMappingException 
	 * @throws IOException
	 */
	 private void showDeleteErrorMessage(DeleteMethod m, int errorcode) throws JsonMappingException, IOException{
		  if (errorcode > 299) {
				List<ApiError> errors = mapper.readValue(m.getResponseBodyAsStream(), new TypeReference<List<ApiError>>() {} );
				for (ApiError e : errors)
					logger.info(e.errorCode + " " + e.message);
			}
	  }
	public String getOauth2Url() {
		return oauth2Url;
	}

	public void setOauth2Url(String oauth2Url) {
		this.oauth2Url = oauth2Url;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecretKey() {
		return clientSecretKey;
	}

	public void setClientSecretKey(String clientSecretKey) {
		this.clientSecretKey = clientSecretKey;
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

	public String getInstanceUrl() {
		return instanceUrl;
	}

	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
	
	public boolean syncInnotasData(){
		boolean b = false;
		b = innotasCacheService.syncCache();
		return b;
	}
	
}