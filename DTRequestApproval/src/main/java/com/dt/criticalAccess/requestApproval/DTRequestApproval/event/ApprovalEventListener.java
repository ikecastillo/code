package  com.dt.criticalAccess.requestApproval.DTRequestApproval.event;

import java.util.Collection;
import java.util.ArrayList;
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

import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;

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
import java.util.Properties;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.UpdateIssueRequest;
import com.atlassian.jira.event.type.EventDispatchOption;

import com.atlassian.jira.issue.status.Status;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkManager;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;

    import org.apache.http.HttpEntity;
      import org.apache.http.NameValuePair;

//import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
//import java.net.URI;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import java.io.InputStream;

import com.atlassian.jira.issue.index.IssueIndexingService;
import com.dt.criticalAccess.requestApproval.DTRequestApproval.rest.ApproverConfig;
import javax.servlet.http.HttpServletRequest;



import com.atlassian.jira.issue.link.IssueLink;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService.RemoteIssueLinkListResult;

/**
 * ApprovalEventListener.java event listener class 
 * Creates remote link when status moved Pending Owner Approval and 
 *feild :'Will this access also require a Firewall change?'  selected  as YES
 */

public class ApprovalEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
    private final JiraAuthenticationContext authenticationContext;            
	private final String FIREWALL_COMPONENT_NAME="Will this access also require a Firewall change?"; 
    private final String FIREWALL_VALUES_YES="Yes"; 
	private final Logger logger = Logger
			.getLogger(ApprovalEventListener.class);
    private final IssueIndexingService issueIndexingService;  
    private final String MANAGER_NAME="Prasad.Narni";
    private static final String PLUGIN_STORAGE_KEY = ApproverConfig.class.getName();
	
	
	
	/**
    *constructor
    */
	public ApprovalEventListener(EventPublisher eventPublisher,
			PluginSettingsFactory pluginSettingsFactory,OptionsManager optionsManager,IssueIndexingService issueIndexingService,
                                 JiraAuthenticationContext authenticationContext) {
		this.eventPublisher = eventPublisher;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
        this.authenticationContext=authenticationContext;
        this.issueIndexingService=issueIndexingService;
		}
	
	
	
    /**
     * This event occurs when status of CSAR ticket moved Pending Owner Approval  
     * and firwall selected as Yes. 
     * @param issueEvent
     */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		 //logger.info("*********start***********approval event listener********");			
		try {
			Long eventTypeId = issueEvent.getEventTypeId();
			Issue issueParent = issueEvent.getIssue();
			Map<String,Object> issueEventMap=issueEvent.getParams();
		    Project project = issueParent.getProjectObject();
            String statusName= issueParent.getStatusObject().getName();
    if("CSAR".equals(project.getKey())){
		if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID) || eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID )) {
             logger.info("-----------event source1 : "+issueEventMap.get("eventsource"));
			 logger.info("------------StatusName : "+statusName);
         if((issueEventMap!=null && ("workflow").equals(issueEventMap.get("eventsource")))){
          if((("PENDING APPROVAL - OWNER").equals(statusName)) && (isFirewallSelected(issueParent)) && hasIssueLinks(issueParent)){
                logger.info("--------------event source :"+issueEventMap.get("eventsource"));
				logger.info("--------------Executed on Status Change");
                String[] resps=createChangeTicket(issueParent);
                for(String issuedetails:resps){
                String[] resp=issuedetails.split("=");
                if(resp!=null){
                   linkCHGonCSAR(resp[0],resp[1],issueParent.getId()); 
                    }
                  }
                }
            }
         }
       }
    } catch (Exception e) {
			//e.printStackTrace();
			logger.info("--------------Error in ApprovalEventListener.onIssueEvent method"+e.getMessage());
		}
	}
            
    /**
    *is firewall field selected as Yes
    */
    private Boolean  isFirewallSelected(Issue issue){
        CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName(FIREWALL_COMPONENT_NAME);
        logger.info("----------------firewall component selected value :  "+customFeildObj.getValue(issue));
        return (FIREWALL_VALUES_YES.equals(""+customFeildObj.getValue(issue)))?true:false;
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
            logger.info("----------------value : "+value);
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
    *creates change ticket
    */
    private String[] createChangeTicket(Issue csarIssue){         
         try{
           
        String summary=csarIssue.getSummary(); 
        String solGrp=getCustomFeildValue("Solution Group - Product",csarIssue);
        String env=getCustomFeildValue("Type of Environment",csarIssue);
         
          
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String chgUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".chgUrl");
		ArrayList<NameValuePair> postParameters;
             
        HttpPost post = new HttpPost(chgUrl+"?projectKey=CHG&issueType=Change&managerName="+MANAGER_NAME);
		// set headers
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "Basic " + getTokenId());
             
             postParameters = new ArrayList<NameValuePair>();
             postParameters.add(new BasicNameValuePair("SolutionGroupProduct", solGrp));
             postParameters.add(new BasicNameValuePair("TypeofEnvironment", env));
             postParameters.add(new BasicNameValuePair("Summary", csarIssue.getSummary()));
             postParameters.add(new BasicNameValuePair("Reporter", csarIssue.getReporter().getName()));
             post.setEntity(new UrlEncodedFormEntity(postParameters));

             
             
        HttpClient defaultHttpClient =null;
       
         defaultHttpClient = HttpClientBuilder.create().build();
           
           HttpResponse response = defaultHttpClient.execute(post);
             logger.info(" ----------------response:  " +response);
           InputStream   inputStream = response.getEntity().getContent();
		   
			byte[] responseBody =  IOUtils.toByteArray(inputStream);
			logger.info("----------Content:"+ new String(responseBody).substring(0,1500));
			if(responseBody!=null && responseBody.length > 0){
                String[] array=(new String(responseBody)).split(",");
            return array;
                
            }
             
       
             } catch (Exception e) {
			  logger.info("----------Error in ApproveEventListener.createChangeTicket()  :"+e.getMessage());
			//e.printStackTrace();
            //response.close();
		}
        finally {
        //response.close();    
            
        }
        
        return null;
    }
 /**
 *creating token for basic authentication
 */
public String getTokenId() {
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64(("DTjira.admin"+":"+"DTpass11").getBytes());
		String sid = new String(encoded);
		return sid;
	}  
            
     
            
              
   private void linkCHGonCSAR(String chgId,String chgKey,Long csarId){
       
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String remoteLinkUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".remoteLinkUrl");
		String applinkId = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".applinkId");
       
	   if(emptyCheck(chgKey) && (csarId!=null)){
       RemoteIssueLinkBuilder remoteIssueLinkBuilder =new RemoteIssueLinkBuilder();
	       remoteIssueLinkBuilder.url(remoteLinkUrl+chgKey);
           remoteIssueLinkBuilder.globalId("appLinkId="+applinkId+"&issueId="+chgId);
           remoteIssueLinkBuilder.title(chgKey);
           remoteIssueLinkBuilder.summary("firewall change request for Critical access");
           remoteIssueLinkBuilder.issueId(csarId);
           remoteIssueLinkBuilder.relationship("relates to");
           remoteIssueLinkBuilder.build();
           
        RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
        logger.info(remoteIssueLink+"--------------remoteIssueLinkBuilder---------"+remoteIssueLinkBuilder); 
        RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
        RemoteIssueLinkService.CreateValidationResult createResult=remoteIssueLinkService.validateCreate(authenticationContext.getUser(),  remoteIssueLink);
        logger.info("----------------createResult  "+createResult.getErrorCollection().getReasons());
		RemoteIssueLinkService.RemoteIssueLinkResult result= remoteIssueLinkService.create(authenticationContext.getUser(), createResult);
        logger.info("-------------result  "+result);
        
            }
      }              
	

	private void setReindex(MutableIssue mutableIssue) {
		try {
			issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.info("------------------index issue" + ie.getMessage());
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
