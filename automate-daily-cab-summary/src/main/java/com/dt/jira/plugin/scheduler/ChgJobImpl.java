package com.dt.jira.plugin.scheduler;

import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.scheduling.PluginScheduler;

import org.apache.commons.httpclient.methods.PostMethod;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.LoggerWrapper;
import com.atlassian.sal.api.ApplicationProperties;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dt.jira.plugin.service.TemplateService;
import com.atlassian.jira.mail.MailService;
import org.apache.commons.codec.binary.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;


import com.dt.jira.plugin.rest.EmailConfig;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;


/**
 * Class defined to sync the projects/versions from jira instance to salesforce
 * @author Srinadh.Garlapati
 *
 */
public class ChgJobImpl implements  ChgJobMonitor,LifecycleAware {

	/* A unique job key  */ 
	static final String KEY = ChgJobImpl.class.getName() + ":instance";
	/* A unique job name  */
	private static final String JOB_NAME = ChgJobImpl.class.getName() + ":job";
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());
	/*  default  600000L = 10mins now. for schedule job in days = (days * 1000 * 60 * 60 * 24)  */
	private static long JOB_SCHEDULE_INTERVAL = 1000 * 60 * 60 * 24;// 180000l
	//private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private static final String PLUGIN_STORAGE_KEY = EmailConfig.class.getName();
	
	
	private Date lastRun = null;
	private long interval =  1000 * 60 * 60 * 24; //180000l
	private static PluginSettingsFactory pluginSettingsFactory;
	private static ObjectMapper mapper = new ObjectMapper();
	private final PluginScheduler pluginScheduler;// provided by SAL
	private String query = "Atlassian test"; // default search
    private final TemplateService templateService;
    private final MailService mailService;
    private final ApplicationProperties applicationProperties;
    
   
    
	public ChgJobImpl(PluginScheduler pluginScheduler,PluginSettingsFactory psf,TemplateService templateService,MailService mailService,ApplicationProperties applicationProperties) {
		super();
		logger.setInfoLogLevel();
		this.pluginScheduler = pluginScheduler;
		this.pluginSettingsFactory = psf;
        this.templateService=templateService;
        this.mailService=mailService;
        this.applicationProperties=applicationProperties;
         	
	}

	@Override
	public void onStart() {		
		reschedule(query, getInterval());
        
	}
    
    
    /**
     *
     */
   /* private Date getScheduleDate(){
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        String feilds = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".feilds");
         System.out.println("templateService   "+templateService);
        String time=templateService.findSceduleTimeByTemplateName(feilds);
         System.out.println("time   "+time);
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());
        System.out.println("calEnd   "+calEnd);
        if(time!=null && (!time.isEmpty())){
            String[] timeArray= time.split(":");
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        if(timeArray.length>=1){    
        calEnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeArray[0]));
        }
       if(timeArray.length==2){
        calEnd.set(Calendar.MINUTE, Integer.valueOf(timeArray[1]));
       }
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        }
        Date midnightTonight = calEnd.getTime();
         
        return midnightTonight;
   }*/
    
    
    
    /**
     *
     */
    private Date getScheduleDate(){
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        String time = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".feilds");
        Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());
       if(time!=null && (!time.isEmpty())){
        String[] timeArray= time.split(":");
        calEnd.set(Calendar.DAY_OF_YEAR, calEnd.get(Calendar.DAY_OF_YEAR));
        if(timeArray.length>=1){
        calEnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeArray[0]));
        }
       if(timeArray.length==2){
       calEnd.set(Calendar.MINUTE, Integer.valueOf(timeArray[1]));
       }
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);
        }
        Date midnightTonight = calEnd.getTime();
        return midnightTonight;
   }
    
    
   
	/**
	 * Schedule job using jira plugin scheduler module
	 */
	@Override
	public void reschedule(String query, long interval) {
        
             System.out.println(" need scedule AUTO CAB SUMMARY in weekdays"); 
       this.query = query;
		pluginScheduler.scheduleJob(
				JOB_NAME,// unique name of the job
				ScheduleJob.class,	// class of the job
				new HashMap<String,Object>() {{
					put(KEY, ChgJobImpl.this);
				}},							 // data that needs to be passed to the job
				getScheduleDate(),					// the time the job is to start
				interval);// interval between repeats, in milliseconds	(days * 1000 * 60 * 60 * 24)
       logger.info(String.format("The SF integration job scheduled to run every %dms", interval));
       
	
	}
    
    public void sendAutomatecabEmail(){
       
        
        try{
       HttpGet httpGet = new HttpGet(applicationProperties.getBaseUrl()+"/rest/cabsummary/1.0/automatedailycab");
       httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", "Basic " + getTokenId());
       HttpClient defaultHttpClient = HttpClientBuilder.create().build();
     HttpResponse response = defaultHttpClient.execute(httpGet);
          System.out.println(" response:  " +response); 
        
          } catch (Exception e) {
			e.printStackTrace();
            //response.close();
		}
        finally {
        //response.close();    
            
        }
        
    }
    
    /**
 *creating token for basic authentication
 */
public String getTokenId() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String userName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".userName");
		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".password");
		String feilds = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".feilds");
		// encoding byte array into base 64
   
		byte[] encoded = Base64.encodeBase64((userName+":"+password).getBytes());
		String sid = new String(encoded);
		return sid;
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
	
	
	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
    
    /*
    public  void getAutomateCabMessage()
    { 
        
        
       try{
		MailQueue mailQueue = ComponentAccessor.getMailQueue();		
    	ApplicationUser applicationUser = ComponentAccessor.getUserManager().getUserByName("Kiran.Muthoju");
        System.out.println("---applicationUser---"+applicationUser);
    	String subjectTemplatePath = "/templates/subjecttemplate.vm";
    	String bodyTemplatePath = "/templates/bodytemplate.vm";
    	Map<String,Object> context = new HashMap<String,Object>();
        Map<TemplateModel,List<SectionModel>> templateDetails=getTemplateDetails("Auto CAB Summary Template");
        context.put("templateDetails",templateDetails);
        List<ApplicationUser> usrList=getUserRecipients(templateDetails);
        if(usrList!=null){
            for(ApplicationUser user:usrList){
        //NotificationRecipient recipient = new NotificationRecipient(user);
       // mailService.sendRenderedMail(applicationUser.getDirectoryUser(), recipient, subjectTemplatePath, bodyTemplatePath, context);
            }
        }
		System.out.println("getAutomateCabMessage end");
        }catch(Exception e){
         e.printStackTrace();   
        }
		
    }
    
    
    // automate cab email code
    
    private  Map<TemplateModel,List<SectionModel>> getTemplateDetails(String templateName){
        System.out.println(templateService+"--------templateName------"+templateName);
        
        Map<TemplateModel,List<SectionModel>> tempalteDetails= templateService.findTemplateByName(templateName);
        try{
        System.out.println("------tempalteDetails------"+tempalteDetails);
        for (Map.Entry<TemplateModel,List<SectionModel>> entry : tempalteDetails.entrySet()){
            System.out.println(entry.getKey() + "/" + entry.getValue());
            TemplateModel template=(TemplateModel)entry.getKey();
            System.out.println("------tempalteDetails------"+template.getMail_to());
            List<SectionModel> sectionModelList=(List<SectionModel>)entry.getValue();
            if(sectionModelList!=null){
                for(SectionModel section:sectionModelList){
                 System.out.println(section.getJql_query()+"--JQL----section.getColumns------"+section.getColumns()); 
                 System.out.println("------section_name------"+section.getSection_name());
                   List<Issue> issueList= getIssuesOnJQL(section.getJql_query());
                    
                     
                    
                    List<Issue> issueList1=getIssuesOnJQL("assignee = 'Srinadh.Garlapati' AND resolution = Unresolved ORDER BY updatedDate DESC");
                    System.out.println("-just  issueList for test --"+issueList1);
                 
                    List<Map<String,List<String>>> entireList=new ArrayList<Map<String,List<String>>>();
                    for(Issue issue:issueList){
                 Map<String,List<String>> issueMap= new IssueUtils().getJiraFieldValues(issue,section.getColumns().split(","));
                        System.out.println("issueMap: "+issueMap);
                        entireList.add(issueMap);
                    }
                    
                    System.out.println("entireList: "+entireList);
                    section.setJql_result(entireList);
                  }
                
                
                
                
            }
            
        }
        }catch(Exception e){
         e.printStackTrace();   
            
        }
        return tempalteDetails;
        
    }
    
    
    
    
    
    
    
    private List<ApplicationUser> getUserRecipients(Map<TemplateModel,List<SectionModel>> templateDetails){
        
         List<ApplicationUser> applicationUserList=new ArrayList<ApplicationUser>();
        try{
       for (Map.Entry<TemplateModel,List<SectionModel>> entry : templateDetails.entrySet()){
         TemplateModel template=(TemplateModel)entry.getKey(); 
         String users=  template.getMail_to();
           if(users!=null){
            String[] userArray= users.split(",");
               if(userArray!=null){
                 for(String user:userArray){
                     String appUser=user.trim();
                     if(appUser!=null && (!appUser.isEmpty())){
                   ApplicationUser applicationUser= ComponentAccessor.getUserManager().getUserByName(appUser); 
                         if(applicationUser!=null){
                           applicationUserList.add(applicationUser);  
                         }
                        }
                 }  
                   
               }
           }
           
       }
        }catch(Exception e){
         e.printStackTrace();   
            
        }
        return applicationUserList;
        
    }
    
    
    
    
    
   private List<Issue> getIssuesOnJQL(String jql) throws SearchException{
	System.out.println("*******Query: "+jql.toString());
    List<Issue> issues =null;
    SearchService searchService=ComponentAccessor.getComponent(SearchService.class);
       System.out.println("user name "+ComponentAccessor.getUserManager().getUser("Kiran.Muthoju"));
	final SearchService.ParseResult pResult= searchService.parseQuery(ComponentAccessor.getUserManager().getUser("Kiran.Muthoju"),(jql));
       System.out.println("pResult "+pResult);
       System.out.println("pResult is valid "+pResult.isValid());
	if (pResult.isValid())  {  
		SearchResults issueList = searchService.search( ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
        System.out.println("issueList: " + issueList);
	    issues = issueList.getIssues();
	    System.out.println("total issues: " + issues.size());
	}
 return issues;

}*/

    
  
	
	
}