package com.dt.jira.plugin.scheduler;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.scheduling.PluginScheduler;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.LoggerWrapper;
import com.dt.jira.plugin.rest.ConfigResource.Config;


import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
/**
 * Class defined to sync the projects/versions from jira instance to salesforce
 * @author kiran.muthoju
 *
 */
public class JobImpl implements  JobMonitor,LifecycleAware {

	/* A unique job key  */ 
	static final String KEY = "SF"+JobImpl.class.getName() + ":instance";
	/* A unique job name  */
	private static final String JOB_NAME = "SF"+JobImpl.class.getName() + ":job";
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());
	/*  default  600000L = 10mins now. for schedule job in days = (days * 1000 * 60 * 60 * 24)  */
	private static long JOB_SCHEDULE_INTERVAL = 600000L; 
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private static final String GRANT_TYPE = "password";
	private static final String SF_OAUTH2_URL = "/services/oauth2/token";
	private static final String SF_SELECT_QUERY_URL = "/services/data/v29.0/query/?q=select+Project_ID__c%2C+Name%2C+Jira_Project__c%2C+Active__c%2C+Version__c+from+Jira_Project__c+Where+(NOT+Project_ID__c++LIKE+%27%25%5C_%25%27)";
	private static final String  SF_JIRA_PROJECT_URL = "/services/data/v29.0/sobjects/Jira_Project__c/";
	private static final String SF_SELECT_QUERY_URL_FOR_VERSIONS = "/services/data/v29.0/query/?q=select+Project_ID__c%2C+Name%2C+Jira_Project__c%2C+Active__c%2C+Version__c+from+Jira_Project__c+Where+(Jira_Project__r.Project_ID__c%3D+%27";
	private static final String SF_SELECT_QUERY_URL_FOR_PARENTKEYNULL_VERSIONS = "/services/data/v29.0/query/?q=select+Project_ID__c%2C+Name%2C+Jira_Project__r.Project_ID__c%2C+Active__c%2C+Version__c+from+Jira_Project__c+Where+(Jira_Project__r.Project_ID__c%3Dnull+and+Project_ID__c+LIKE+%27%25%5C_%25%27)";
	private String userName = "";
	private String passWord = "";
	private String instanceUrl = "";
	private String oauth2Url = "";	
	private String clientId = "" ;
	private String code = "test";
	private String clientSecretKey = ""; 
	private Date lastRun = null;
	private long interval =  600000L;
	private static PluginSettingsFactory pluginSettingsFactory;
	private static ObjectMapper mapper = new ObjectMapper();
	private final PluginScheduler pluginScheduler;// provided by SAL
	private String query = "Atlassian test"; // default search


	public JobImpl(PluginScheduler pluginScheduler,PluginSettingsFactory psf) {
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
		pluginScheduler.scheduleJob(
				JOB_NAME,// unique name of the job
				ScheduleJob.class,	// class of the job
				new HashMap<String,Object>() {{
					put(KEY, JobImpl.this);
				}},							 // data that needs to be passed to the job
				new Date(),					// the time the job is to start
				interval);// interval between repeats, in milliseconds	(days * 1000 * 60 * 60 * 24)
		logger.info(String.format("The SF integration job scheduled to run every %dms", interval));
	
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
  * Set the plugin configuration
  */
 private void setConfiguration(){
	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfintegrationUid");
		String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfintegrationPwd");
		String oauthurl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfintegrationUrl");
		String clientid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfClientId");
		String secretKey = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfClientSecretKey");
		String interval = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".sfInterval");
		
		//logger.info("username: "+username);
		//logger.info("password: "+password);
		logger.info("oauthurl: "+ oauthurl);
		logger.info("clientid: "+clientid);
		logger.info("secretKey: "+secretKey);
		logger.info("interval: "+interval);
		
		logger.info("Set plugin configuration");
		setUserName(username);
		setPassWord(password);
		setOauth2Url(oauthurl);
		setClientId(clientid);
		setClientSecretKey(secretKey);
		try{
			long l = Long.parseLong(interval.trim());
			setInterval(l);
		}catch(Exception e){
			//setInterval(600000L);
			logger.info("Exception in schedule job interval. Interval value should be in milliseconds(long) ");
		}
		
		
 }
	 public String authenticate() {
		    HttpClient httpclient = new HttpClient();
		    String accessToken = null;
		    String instanceUrlStr = null;
		    setConfiguration();
		    PostMethod post = new PostMethod(getOauth2Url()+"/services/oauth2/token");
		    post.addParameter("grant_type",GRANT_TYPE);
		       /** For session ID instead of OAuth 2.0, use "grant_type", "password" **/
		    post.addParameter("client_id",getClientId());
		    post.addParameter("client_secret",getClientSecretKey());
		    //post.addParameter("redirect_uri",redirectUri);
		    post.addParameter("username",getUserName());
		    post.addParameter("password",getPassWord());
		    try {
			    int sc = httpclient.executeMethod(post);	      // Read the response body.
			    showErrorMessage(post,sc);
			    // String responseBody1 = post.getResponseBodyAsString();
			    byte[] responseBody = post.getResponseBody();
			    
			    JsonNode rootNode = mapper.readTree(responseBody);
			    JsonNode stringNode = rootNode.path("access_token");
			    JsonNode instanceUrl = rootNode.path("instance_url");
			    accessToken = stringNode.asText();
			    instanceUrlStr =  instanceUrl.asText();
			    setInstanceUrl(instanceUrlStr);
			    } catch (HttpException e) {
			    	logger.error("Fatal protocol violation: " + e.getMessage());
			    } catch (JsonMappingException e) {
			    	logger.error("Fatal protocol violation: " + e.getMessage());
			    }catch (IOException e) {
			    	logger.error("Fatal protocol violation: " + e.getMessage());
			} finally {
		       	post.releaseConnection();
		    } 
		    return accessToken;
		  }
	 /**
	  * Get the data from sales force which contains projects and versions.
	  * @param accessToken - <String> - token
	  * @return <Map> - returns the Map which key and value as projectkey and customobject
	  */
	 public Map  getAllSalesForceProjects(String accessToken){
		 
		 String  queryurl =  getInstanceUrl() + SF_SELECT_QUERY_URL;
		  GetMethod m = new GetMethod(queryurl);
		  Map  jiraProjMap = new HashMap<String, JiraProjects>();
		  try{
				m.setRequestHeader("Authorization", "Bearer " + accessToken);
				HttpClient c = new HttpClient();
				int sc = c.executeMethod(m);
				 if (sc > 299) {
					 List<ApiError> errors = mapper.readValue(m.getResponseBodyAsStream(), new TypeReference<List<ApiError>>() {} );
					 for (ApiError e : errors)
					 		logger.error(e.errorCode + " " + e.message);
				 }				
				byte[] responseBody =  IOUtils.toByteArray(m.getResponseBodyAsStream());; 
				
				    ObjectMapper mapper = new ObjectMapper();
				    JsonNode rootNode = mapper.readTree(responseBody);
				    JsonNode recordNode = rootNode.path("records");
				    Iterator<JsonNode> iterator = recordNode.getElements();
				  
				    while(iterator.hasNext()){
						JiraProjects jiraProjects = null;
				    	JsonNode jsonNode = iterator.next();
				    	JsonNode attriubtesNode = jsonNode.get("attributes");
				    	JsonNode urlNode = attriubtesNode.get("url");
				    	//System.out.println("URL : "+ urlNode.asText());
				    	JsonNode projectIdNode = jsonNode.get("Project_ID__c");
				    	JsonNode nameNode = jsonNode.get("Name");
				    	JsonNode parentObj = jsonNode.get("Jira_Project__c");
						JsonNode versionObj = jsonNode.get("Version__c");
						JsonNode activateObj = jsonNode.get("Active__c");

						if(activateObj!=null && activateObj.asText().equals("true") ){ // only active objects are considered
					
							if(parentObj!=null  && !parentObj.asText().equals("null")){								
							
							} else {								
								jiraProjects  = new JiraProjects(projectIdNode.asText(), nameNode.asText(), true, urlNode.asText());
								jiraProjMap.put(projectIdNode.asText(), jiraProjects);
							}
						}
				    }
					logger.info("Project count: "+ jiraProjMap.size());
		   }catch(Exception e){
			   logger.error("Exception in getAllSalesForceProjects() " + e.getMessage());
		  }
		  finally {
		      	m.releaseConnection();
		    }
		  return jiraProjMap;
	  }
	  /**
	  * Sync projects and versions from Jira to Salesforce
	  *
	  */
	 public String syncJiraProjectsAndReleases(Map map) {
		 	logger.info("sync jira projects and releases start--->");
			ProjectManager projectManager = ComponentAccessor.getProjectManager();
			VersionManager versionManager  = ComponentAccessor.getVersionManager(); 
			
			List<Project> projects = projectManager.getProjectObjects();
			Map releaseMap = new HashMap();
			String accessToken = authenticate();
			String jiracreateurl =  getInstanceUrl() + SF_JIRA_PROJECT_URL;
			for(Project p: projects){
				String projectSOid = null;
				String projectName = p.getName();
				String projectKey = p.getKey();
				
				List<Version> versionsJira = versionManager.getVersions(p.getId());
			
				if(map.containsKey(projectKey) ){ 
					// SteP 2. if project key is exist then update the new versions  
					//logger.info("the project is already in sync " +projectKey);
					JiraProjects project = (JiraProjects)map.get(projectKey);
					if(project!=null){
						projectSOid = getProjectSOId(project.getUrl());
					}
					// get the versions from sales force query url
					Map versionsSF = getSFVersions(projectKey, accessToken);
						for(Version v: versionsJira){
						
							Long vId = v.getId();
							String vNAME = v.getName().trim(); 
							String vKey = projectKey+"_"+vId;
							//Compare the version/release(in Jira) to release(SF), if version is not exist in SF then create the new version under the SF
							if(!versionsSF.containsKey(vKey)){
								JiraProjects newRelease = new JiraProjects(vKey, vNAME, true, "");
								newRelease.setParentKey(projectSOid);
								newRelease.setVersion(vNAME);
								String relObjId = createProjectOrVersion(jiracreateurl,accessToken,newRelease); 
								logger.info("Step-2: Successfully created new  version under existing projectName on SF: " + projectName +" new version: "+vNAME);
								setSleepInterval(1000);
							
							} else {
									JiraProjects existingRelease = (JiraProjects) versionsSF.get(vKey);
									if(existingRelease!=null){
										String releaseName = existingRelease.getProjectName();
										// Verify the release name have been changed or not
										if(!releaseName.equalsIgnoreCase(vNAME)){
											//Update the query on version name changes
											existingRelease.setProjectName(vNAME);
											existingRelease.setVersion(vNAME);
											
											if(existingRelease.getParentKey()==null){
												logger.info("jira version"+vNAME+" on SF the parent is null: ");
											}
											// De activate the Release on SF if version is archived
											if(v.isArchived()) {
												existingRelease.setActive(false);
												logger.info(" Project/Version name is de-activated : "+ existingRelease.getProjectName() );
											}
											updateObjectOnSF(existingRelease,accessToken);
											logger.info("Step-3: Successfully  updated jira version"+vNAME+" on SF: "+releaseName);
											
										}
										//logger.info("SF existing projectName: " + projectName +" existing jira version: "+vNAME + " SF version "+  releaseName);
									} //endif								
							} //end else
						}// end for

				} else {
					//Step 1. if project key does not exist on the sales force then create the projects and versions
					JiraProjects newProject = new JiraProjects(projectKey, projectName, true, "");
						String objId = createProjectOrVersion(jiracreateurl,accessToken,newProject); // create the project
						setSleepInterval(1000);
						for(Version v: versionsJira){
							Long vId = v.getId();
							String vName = v.getName().trim(); 						
							String vKey = projectKey+"_"+vId;
								JiraProjects newVersion = new JiraProjects(vKey, vName, true, "");
								newVersion.setParentKey(objId);
								newVersion.setVersion(vName);
								createProjectOrVersion(jiracreateurl,accessToken,newVersion); // create the version 
								logger.info("Step-1: $successfully created new projectName: " + projectName +" new version: "+vName);
								setSleepInterval(1000);
						}
				}
				
			}
			return "";
		}
	 /**
	  * Get the versions of the given project from sales force
	  * @return
	  */
	private Map getSFVersions(String projectKey,String accessToken){
		 String  queryurl =  getInstanceUrl() + SF_SELECT_QUERY_URL_FOR_VERSIONS+projectKey+"%27)";
		  GetMethod m = new GetMethod(queryurl);
		  Map  versionMap = new HashMap<String, JiraProjects>();
		  try{
				m.setRequestHeader("Authorization", "Bearer " + accessToken);
				HttpClient c = new HttpClient();
				int sc = c.executeMethod(m);
				 if (sc > 299) {
					 List<ApiError> errors = mapper.readValue(m.getResponseBodyAsStream(), new TypeReference<List<ApiError>>() {} );
					 for (ApiError e : errors)
					 		logger.error(e.errorCode + " " + e.message);
				 }				
				byte[] responseBody =  IOUtils.toByteArray(m.getResponseBodyAsStream());; 
				
				    ObjectMapper mapper = new ObjectMapper();
				    JsonNode rootNode = mapper.readTree(responseBody);
				    JsonNode recordNode = rootNode.path("records");
				    Iterator<JsonNode> iterator = recordNode.getElements();
				  
				    while(iterator.hasNext()){
						JiraProjects jiraProjects = null;
				    	JsonNode jsonNode = iterator.next();
				    	JsonNode attriubtesNode = jsonNode.get("attributes");
				    	JsonNode urlNode = attriubtesNode.get("url");
				    	//System.out.println("URL : "+ urlNode.asText());
				    	JsonNode projectIdNode = jsonNode.get("Project_ID__c");
				    	JsonNode nameNode = jsonNode.get("Name");
				    	JsonNode parentObj = jsonNode.get("Jira_Project__c");
						JsonNode versionObj = jsonNode.get("Version__c");
						JsonNode activateObj = jsonNode.get("Active__c");

						if(activateObj!=null && activateObj.asText().equals("true") ){ // only active objects are considered								
								jiraProjects  = new JiraProjects(projectIdNode.asText(), nameNode.asText(), true, urlNode.asText());
								jiraProjects.setParentKey(parentObj.asText());
								jiraProjects.setVersion(nameNode.asText());
								versionMap.put(projectIdNode.asText(), jiraProjects);
							
						}
				    }
				    /*if(versionMap!=null)
				    	logger.info(" Project Key: "+ projectKey +"  Versions : "+ versionMap.size());
				    else 
				    	logger.info(" Project Key: "+ projectKey +"  Versions null ");*/
				  
		   }catch(Exception e){
			   logger.error("Exception in getSFVersions() " + e.getMessage());
		  }
		  finally {
		      	m.releaseConnection();
		    }
		  return versionMap;
		
	}
	 /**
	  * Get the versions which parent key is null from sales force
	  * @param accessToken - token of SF
	  * @return Map - list of versions 
	  */
	private Map getSFVersionsHasParentKeyNull(String accessToken){
		 String  queryurl =  getInstanceUrl() + SF_SELECT_QUERY_URL_FOR_PARENTKEYNULL_VERSIONS;
		  GetMethod m = new GetMethod(queryurl);
		  Map  versionMap = new HashMap<String, JiraProjects>();
		  try{
				m.setRequestHeader("Authorization", "Bearer " + accessToken);
				HttpClient c = new HttpClient();
				int sc = c.executeMethod(m);
				 if (sc > 299) {
					 List<ApiError> errors = mapper.readValue(m.getResponseBodyAsStream(), new TypeReference<List<ApiError>>() {} );
					 for (ApiError e : errors)
					 		logger.error(e.errorCode + " " + e.message);
				 }				
				byte[] responseBody =  IOUtils.toByteArray(m.getResponseBodyAsStream());; 
				
				    ObjectMapper mapper = new ObjectMapper();
				    JsonNode rootNode = mapper.readTree(responseBody);
				    JsonNode recordNode = rootNode.path("records");
				    Iterator<JsonNode> iterator = recordNode.getElements();
				  
				    while(iterator.hasNext()){
						JiraProjects jiraProjects = null;
				    	JsonNode jsonNode = iterator.next();
				    	JsonNode attriubtesNode = jsonNode.get("attributes");
				    	JsonNode urlNode = attriubtesNode.get("url");
				    	//System.out.println("URL : "+ urlNode.asText());
				    	JsonNode projectIdNode = jsonNode.get("Project_ID__c");
				    	JsonNode nameNode = jsonNode.get("Name");
				    	JsonNode parentObj = jsonNode.get("Jira_Project__c");
						JsonNode versionObj = jsonNode.get("Version__c");
						JsonNode activateObj = jsonNode.get("Active__c");						
						if(activateObj!=null && activateObj.asText().equals("true") ){ // only active objects are considered								
								jiraProjects  = new JiraProjects(projectIdNode.asText(), nameNode.asText(), true, urlNode.asText());								
								jiraProjects.setVersion(nameNode.asText());
								versionMap.put(projectIdNode.asText(), jiraProjects);
							
						}
				    }
				    if(versionMap!=null)
				    	logger.info(" Versions whiich has parent key null size : "+ versionMap.size());
				  
				  
		   }catch(Exception e){
			   logger.error("Exception in getSFVersionsHasParentKeyNull() " + e.getMessage());
			   e.printStackTrace();
		  }
		  finally {
		      	m.releaseConnection();
		    }
		  return versionMap;
		
	}
	 /**
	  * Update or De-active the project/version on Sales Force.
	  * @param map - Map<projectkey,JiraProject> - Existing projects from SF
	  * @param accessToken - <String> token 
	  * @return
	  */
	 public String updateVersionsHasParentKeyNull(Map projectMap,String accessToken){
		 logger.info("updateVersionsHasParentKeyNull start--->");
		 ProjectManager projectManager = ComponentAccessor.getProjectManager();
		
		 logger.info("SF Projects size "+ projectMap.size());
		 
		 Set keySet = projectMap.keySet();
		 Map versions = getSFVersionsHasParentKeyNull(accessToken);
		 updateORdeleteVersions(projectMap,versions,accessToken);
		 setSleepInterval(500);
		 return "Successfully done";
	 }
	 /**
	  * Update or De-active the project/version on Sales Force.
	  * @param map - Map<projectkey,JiraProject> - Existing projects from SF
	  * @param accessToken - <String> token 
	  * @return
	  */
	 public String updateOrdeleteProjectsOnSF(Map projectMap,String accessToken){
		 logger.info("updateOrdeleteProjectsOnSF start--->");
		 ProjectManager projectManager = ComponentAccessor.getProjectManager();
		
		 logger.info("SF Projects size "+ projectMap.size());
		 
		 Set keySet = projectMap.keySet();
		 Iterator<String> iterator = keySet.iterator();
		 while(iterator.hasNext()){
			 String projectKey = iterator.next();
			 Project project = projectManager.getProjectByCurrentKey(projectKey);
			 JiraProjects jiraProjects = (JiraProjects)projectMap.get(projectKey);
			 
			 if(project!=null){
				 //System.out.println("Project key exist on both: "+ project.getKey());
				 //check both the jira porject name and SF project name are equal
				 if((project.getName().trim()).equals(jiraProjects.getProjectName())){
					  // modified to get the versions from the sales force system
					  //  compare the the versions from jira to sales force
					 Map versions = getSFVersions(projectKey, accessToken);
					 updateORdeleteVersions(projectMap,versions,accessToken);
					 setSleepInterval(500);
				 } else {
					 logger.info(" Step-3: Jira project " + project.getName()+" name is updated on SF ");
					 // update project using the query
					 updateObjectOnSF(jiraProjects,accessToken);
					 setSleepInterval(500);
				 }
			 } else {
				logger.info(" Step-5: Project name is de-activated : "+ jiraProjects.getProjectName() );
				  // de-active the project  the query 
				jiraProjects.setActive(false);
				updateObjectOnSF(jiraProjects,accessToken);
				setSleepInterval(500);
			 }
		 }
				 
		

		 return "Successfully done";
	 }
	 /**
	  * 
	  * @param versionsMap
	  * @param accessToken
	  */
	 private void updateORdeleteVersions(Map projectMap,Map versionsMap,String accessToken){
		 Set sfVerSet = versionsMap.keySet();
		 Iterator<String> itSf = sfVerSet.iterator();
		 while(itSf.hasNext()){
			 String sfVersionId = itSf.next();
			 JiraProjects sfVersion = null;
			 
			 
			 	 if(!isVersionExist(sfVersionId)  ){
					sfVersion = (JiraProjects)versionsMap.get(sfVersionId);						
					// De-active Salesforce version Object
					logger.info("Step-5:   Version name is de-activated : "+ sfVersion.getProjectName() );
					sfVersion.setActive(false);
					updateObjectOnSF(sfVersion,accessToken);
					setSleepInterval(500);
				 } else {
					//Update the parent key value of version if parent is missing.
					sfVersion = (JiraProjects)versionsMap.get(sfVersionId);
					//logger.info(" version name : "+ sfVersion.getProjectName() + " Parent key : " + sfVersion.getParentKey());
					if(sfVersion.getParentKey()==null){
					logger.info(" version name : "+ sfVersion.getProjectName() + " Parent key null: " + sfVersion.getParentKey());
						String v_projectKey= getProjectKey(sfVersionId);
						JiraProjects jiraProjects = (JiraProjects)projectMap.get(v_projectKey);
						logger.info(" v_projectKey  : "+ v_projectKey + " Parent Obj: " + jiraProjects);
						if(jiraProjects!=null){
							String projectSOid = getProjectSOId(jiraProjects.getUrl());
							sfVersion.setParentKey(projectSOid);
							updateObjectOnSF(sfVersion,accessToken);
							logger.info("Version is updated with parent:  Version Name: "+sfVersion.getProjectName() + " and Parent  "+ projectSOid );
						}
						
					 
					}
				 }
			
		 }
	 }
	 /**
	  * Get Project key of the version 
	  * @param versionId - <String> version id
	  * @return <String> - returns Project key
	  */
	 private String getProjectKey(String versionId){
			
		 	String verKey = versionId;
			verKey = verKey.substring(0,verKey.indexOf('_'));
		 	return verKey;
	 }
	 /**
	  * Is version exist on the jira instance
	  * @param versionId - <String> version id
	  * @return <boolean>- returns true/false.
	  */
	 private boolean isVersionExist(String versionId){
		 	VersionManager versionManager  = ComponentAccessor.getVersionManager();
		 	String verKey = versionId;
			verKey = verKey.substring(verKey.indexOf('_')+1,verKey.length());
			Long vId = -1L;
			try{
				vId = Long.valueOf(verKey);
			}catch(NumberFormatException ne){
				//System.out.println("Please check the version key value. It should be ProjectKey_versionID "+ne.getMessage());
				logger.info("Please check the version key value. It should be ProjectKey_versionID "+ne.getMessage());
			}
		 	Version v = versionManager.getVersion(vId);
		 	if( v!=null  && !v.isArchived()){
		 		return true;		 	
			} 
		 return false;
	 }
	 /**
	  * Update project/version on Sales Force.
	  * @param jiraProjects - Object which contains the information about project/version
	  * @param accessToken - token
	  */
	 private  void updateObjectOnSF(JiraProjects jiraProjects, String accessToken)  {
		    String url = getInstanceUrl() + jiraProjects.getUrl();
			PostMethod m = new PostMethod(url) {
				@Override public String getName() { return "PATCH"; }
			};
			//PostMethod m = new PostMethod(url);
		  try{
		  	
			m.setRequestHeader("Authorization", "Bearer " + accessToken);
			Map<String, Object> accUpdate = new HashMap<String, Object>();
			accUpdate.put("Name", jiraProjects.getProjectName());
			accUpdate.put("Project_ID__c", jiraProjects.getProjectId());
			accUpdate.put("Version__c", jiraProjects.getVersion());
			accUpdate.put("Active__c", jiraProjects.isActive());	
			if(jiraProjects.getParentKey()!=null){
				accUpdate.put("Jira_Project__c", jiraProjects.getParentKey());
			}
			
			ObjectMapper mapper = new ObjectMapper();

			m.setRequestEntity(new StringRequestEntity(mapper.writeValueAsString(accUpdate), "application/json", "UTF-8"));
			
			HttpClient c = new HttpClient();
			int sc = c.executeMethod(m);
			
			showErrorMessage(m,sc);
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally {
		      // Release the connection.
		    	m.releaseConnection();
		    }  
			
		}
	 /**
	  * Delete project/version on Sales Force.
	  * @param url - Sales force url
	  * @param accessToken - token
	  */
	 private void deleteSFVersionObj(String url,String accessToken){
		 //System.out.println("Urls : "+ url);
		 DeleteMethod m = new DeleteMethod(url);
		  try{
		  	
			m.setRequestHeader("Authorization", "Bearer " + accessToken);
			HttpClient c = new HttpClient();
			int sc = c.executeMethod(m);
			logger.info("deleteSFVersionObj(): Post call returned a status code of " + sc);
			showDeleteErrorMessage(m,sc);
			
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally {
		      // Release the connection.
		    	m.releaseConnection();
		    }  
	 }
	 /**
	  * Set the sleep interval
	  * @param milliseconds - <long>
	  */
	 public void setSleepInterval(long milliseconds){
		 try{
				Thread.sleep(milliseconds);
				}catch(InterruptedException ie){
					ie.getStackTrace();
			}
	 }
	 /**
	  * Get the sales force object Id from the url
	  * @param url - <String> sales force url
	  * @return - <String>
	  */
	 private String getProjectSOId(String url){
		 String soID =url.substring(url.lastIndexOf("/")+1,url.length());
		 return soID;
	 }
	 /**
	  * Create the new Project or version on sales force
	  * @param url - <String> URL
	  * @param sid - <String>- token 
	  * @param jiraProjects - Custom Object 
	  * @return- <String> - returns sales force Object id.
	  */
	 private String createProjectOrVersion(String url, String sid, JiraProjects jiraProjects)  {
		 	String ojbId = "";
		 	PostMethod m = new PostMethod(url);
		  try{
		  	
			m.setRequestHeader("Authorization", "Bearer " + sid);
			Map<String, Object> accUpdate = new HashMap<String, Object>();
			accUpdate.put("Name", jiraProjects.getProjectName());
			accUpdate.put("Project_ID__c", jiraProjects.getProjectId());
			accUpdate.put("Version__c", jiraProjects.getVersion());
			accUpdate.put("Active__c", jiraProjects.isActive());
			accUpdate.put("Jira_Project__c", jiraProjects.getParentKey());

			m.setRequestEntity(new StringRequestEntity(mapper.writeValueAsString(accUpdate), "application/json", "UTF-8"));
			
			HttpClient c = new HttpClient();
			int sc = c.executeMethod(m);
			showErrorMessage(m,sc);
			if(sc<=299){
				byte[] responseBody =  IOUtils.toByteArray(m.getResponseBodyAsStream());; 
				
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(responseBody);
				JsonNode recordNode = rootNode.path("id");
				ojbId = recordNode.asText();
				logger.info("Create Project/version Response string : "+m.getResponseBodyAsString());
			}
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  finally {
		      // Release the connection.
		    	m.releaseConnection();
		    }  
		  return ojbId;
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
	 /**
	 * Send Mail Notification	
	 * @param errorMsg - <String> error message	 	 
	 */ 
	 private void sendMailNotification(String errorMsg){
		try{
			logger.info("Send Mail Notification: "+errorMsg);
			MailServerManager mailServerManager = ComponentAccessor
					.getMailServerManager();
			SMTPMailServer mailServer = mailServerManager
					.getDefaultSMTPMailServer();

			Properties p=new Properties();

			
			String emailProperty = "Kiran.muthoju@dealertrack.com";  //getJiraAdminEmailids();
			Email email = new Email(
					emailProperty);
			email.setFrom(mailServer.getDefaultFrom());
			email.setSubject("Salesforce  integration failed");
			email.setMimeType("text/html");
			email.setBody(errorMsg);
			SingleMailQueueItem item = new SingleMailQueueItem(email);
			ComponentAccessor.getMailQueue().addItem(item);
		}
		catch(Exception ex){
			ex.printStackTrace();
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
	
	
}