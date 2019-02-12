package com.dt.remote.pisdtktcreator.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import com.atlassian.jira.config.properties.APKeys;
import com.dt.remote.pisdtktcreator.utils.ApplicationIDFinder;
import com.dt.remote.pisdtktcreator.utils.ServiceDeskJSONCreator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.dt.remote.pisdtktcreator.rest.ConfigBean;
import com.dt.remote.pisdtktcreator.rest.AdminConfigResource.Config;
import com.dt.remote.pisdtktcreator.service.ConfigService;

import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import org.apache.http.client.methods.HttpPut;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;
/**
* Create the problem from incident manually from More dropdown 
*
*/
public class CreateAndLinkPISDAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(CreateAndLinkPISDAction.class);
    
    private Long id;
    private String key;
    private final WebResourceManager webResourceManager;
    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final ApplicationProperties applicationProperties;
	
	private final IssueFactory issueFactory;
	private final IssueManager issueManager;
	private ConfigService configService;
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private String baseURL = "";
    private String applicationLinkID = "";
    private String userpwd = "";

    private String itsmApplicationLinkID = "";
    private String pisdticketSDApplicationName = "";
    private String pisdticketITSMApplicationName = "";

    private ServiceDeskJSONCreator serviceDeskJSONCreator;

    private static PluginSettingsFactory pluginSettingsFactory;

	/**
     * Constructor
     * @param applicationProperties the ApplicationProperties to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param webResourceManager the WebResourceManager to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
     */
    public CreateAndLinkPISDAction(ApplicationProperties applicationProperties,
    		IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
    		WebResourceManager webResourceManager,
			IssueLinkManager issueLinkManager,
			IssueFactory issueFactory,
			IssueManager issueManager,ConfigService configService,PluginSettingsFactory psf)
    {
    	this.applicationProperties = applicationProperties;
        this.issueService = issueService;
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
		this.issueLinkManager= issueLinkManager;		
		this.issueFactory = issueFactory;
		this.issueManager = issueManager;	
		this.configService = configService;
		this.pluginSettingsFactory = psf;
        serviceDeskJSONCreator = new ServiceDeskJSONCreator();
		setConfiguration();
    }
    
    protected void doValidation()
    {
        includeResources();      

    }
    private void setConfiguration() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();

        String userid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdTicketUid");
        String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdTicketPwd");
        userpwd =  userid+":"+password;

        pisdticketSDApplicationName = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdticketSDApplicationName");

    }
    private List getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
        List<String> values = new ArrayList<>();
        List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

        //Put all the values from that custom field to the list
        if (valuesList != null) {
            for (Object value : valuesList) {
                values.add(value.toString());
            }
        }
        return values;
    }

 
    @Override
    public String execute() throws Exception {
        Issue issue = getIssueObject();
        if(issue!=null){
        includeResources();
        
		IssueType issueType		=	issue.getIssueTypeObject();
		Project project = issue.getProjectObject();
		CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
		log.info("Issue Type Object check for PISD :" + issue.getIssueTypeObject().getName());
        if ("ITIM".equals(project.getKey()) &&
            (issue.getIssueTypeObject().getName().equals("Incident") ||
                    issue.getIssueTypeObject().getName().equals("Outage"))) {
            log.info("Detected that this is an Incident or outage - checking if PISD ticket needs created");
            String statusName = issue.getStatusObject().getName();
            log.info("Is this incident or outage updated recently?");
            log.info("PISD : Issue status is " + statusName);
         
			CustomField solutionGroupProdCF = cfm.getCustomFieldObjectByName("Solution Groups - Products");
			List<String> solutionGroupProdValues = getMultiLevelCascadingSelectValue(issue, solutionGroupProdCF);
			String solutionGroup = solutionGroupProdValues.get(0);
			if (solutionGroup.contains("F&I")) {
				String product ="";
				if(solutionGroupProdValues!=null && solutionGroupProdValues.size()>1){
					product = solutionGroupProdValues.get(1);
				} else {
					messages= "Product is mandatory. Please select the Product value. ";
					addErrorMessage(messages);
					return ERROR;
				}
				if (product.equalsIgnoreCase("Partner Integration Service Desk")) {
					log.info("Going ahead to create a PISD Service desk ticket for the Incident/Outage : " + issue.getKey());
					String subProduct = "";
						if(solutionGroupProdValues!=null && solutionGroupProdValues.size()>2)
							subProduct = solutionGroupProdValues.get(2);
						else {
							messages= "Please select Solution Groups - Products as F&I Solutions (F&I) - Partner Integration Service Desk, and then select a sub product under it";
							addErrorMessage(messages);
							return ERROR;
						}
					//Create a config bean that will be used as a lookup in AO
					ConfigBean configBean = new ConfigBean();
					configBean.setServiceDeskName(product);
					configBean.setRequestTypeName(subProduct);
					ConfigBean beanInAO = configService.getConfig(configBean);
					log.info("Trying to find a config in AO");
					if (beanInAO != null) {
						log.info("Found an AO configuration for PISD service desk, proceeding to create PISD ticket");
						int serviceDeskID = Integer.parseInt(beanInAO.getServiceDeskID());
						int requestTypeID = Integer.parseInt(beanInAO.getRequestTypeID());
						String customFieldsWithIDs = beanInAO.getCustFieldsWithIDs();
						log.info("Custom fields with IDS are " + customFieldsWithIDs);
						String summary = issue.getSummary();
						String description = issue.getDescription();
						Map<String, String> custFieldIDWithValue = serviceDeskJSONCreator.lookUpCustomFieldsInIssue(customFieldsWithIDs, issue);
						if(!hasIssueLinks(issue)){
                            log.info("Got the hashmap of custom fields");
                            pisdticketITSMApplicationName = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_TITLE);
                            log.info("Current APPLICATION NAME IS " + pisdticketITSMApplicationName);
                            ApplicationIDFinder applicationIDFinder = new ApplicationIDFinder(pisdticketSDApplicationName, pisdticketITSMApplicationName,
                                    ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL), getTokenId());
                            Map<String, String> applicationIDMap = applicationIDFinder.createApplicationIDMap();
                            baseURL = applicationIDMap.get(pisdticketSDApplicationName).split("\\|")[0];
                            applicationLinkID = applicationIDMap.get(pisdticketSDApplicationName).split("\\|")[1];
                            itsmApplicationLinkID = applicationIDMap.get(pisdticketITSMApplicationName).split("\\|")[1];
                            log.info("Set baseURL = " + baseURL);
                            log.info("Set Application Link ID of SD as " + applicationLinkID);
                            log.info("Set Application Link ID of ITSM as " + itsmApplicationLinkID);
							Map<String, Object>  pisdIssueMap= createPISDTicket(serviceDeskID, requestTypeID, summary, description, custFieldIDWithValue);									 
							log.info("PISD TICKET Created : " + pisdIssueMap);
							//System.out.println("PISD TICKET Created :"+ pisdIssueMap);								  
							   if (pisdIssueMap != null && pisdIssueMap.size()>0) {
									long pisdIssueID = (Integer)pisdIssueMap.get("issueId");
									log.info("Issue ID Passed on is " + pisdIssueID);
									String pisdIssueKey  = (String) pisdIssueMap.get("issueKey");
									String incidentIssueID = issue.getId().toString();
									String incidentIssueKey = issue.getKey();
									createRemoteIssueLink(incidentIssueID,incidentIssueKey,pisdIssueID,pisdIssueKey);
									updateRepoterAndIncidentIDInServiceDeskTicket(issue.getReporter().getName(), pisdIssueKey, issue.getKey());
                                   createRemoteIssueLinkWithinPISDTicket(pisdIssueID+"",pisdIssueKey,incidentIssueID,incidentIssueKey,
                                           ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL));
								} else {
									log.info("PISD ticket already created for this Issue");
									messages= "PISD ticket already created for this Issue";
									addErrorMessage(messages);
								}
						 } else {
							log.info("The PISD ticket has already been created and linked for this issue.");
							messages= "The PISD ticket has already been created and linked for this issue.";
							addErrorMessage(messages);
							return ERROR;							
						 }
						
					   return SUCCESS;
					} else {
						log.info("Could not find any settings configured for this sub product " +
								"for partner integration service desk");
						messages= "Could not find any settings configured for this sub product " +
                                "for partner integration service desk";
						addErrorMessage(messages);
						return ERROR;									
					}
				} else {
					messages= "Please select the Solution Groups - Products value as F&I Solutions (F&I) - Partner Integration Service Desk, and then select a sub product under it";
					addErrorMessage(messages);
				}
				
			} else {
				messages= "Please select the Solution Groups - Products value as F&I Solutions (F&I) - Partner Integration Service Desk, and then select a sub product under it";
				addErrorMessage(messages);
			}
		
        }
		}else{
		messages= "issue can not be null";
				addErrorMessage(messages);
		 return ERROR;	
		 }
    }
     /**
     * Creates remote issue link between the PISD and the incident or outage tickets
     * @param issueId
     * @param issueKey
     * @param remoteIssueId
     * @param remoteIssueKey
     */
    private void createRemoteIssueLink(String issueId,String issueKey,Long remoteIssueId,String remoteIssueKey){
		log.info("----------------------------------createRemoteIssueLink starts----------------------------------------");
		log.info("issueId"+issueId);
		log.info("issueKey"+issueKey);
		log.info("remoteIssueId"+remoteIssueId);
		log.info("remoteIssueKey"+remoteIssueKey);
		log.info("----------------------------------createRemoteIssueLink ends-------------------------");
        String remoteLinkUrl  = baseURL+"/browse/";
        if(StringUtils.isNotBlank(issueKey) && (remoteIssueId!=null)){
            RemoteIssueLinkBuilder remoteIssueLinkBuilder =new RemoteIssueLinkBuilder();
            remoteIssueLinkBuilder.url(remoteLinkUrl+remoteIssueKey);
            remoteIssueLinkBuilder.globalId("appId="+applicationLinkID+"&issueId="+remoteIssueId);
            remoteIssueLinkBuilder.title(remoteIssueKey);          
            remoteIssueLinkBuilder.applicationType("com.atlassian.jira");
			/*remoteIssueLinkBuilder.applicationName("JIRA Service Desk Clone");*/
            remoteIssueLinkBuilder.applicationName(pisdticketSDApplicationName);
            remoteIssueLinkBuilder.issueId(Long.valueOf(issueId));
            remoteIssueLinkBuilder.relationship("relates to");
            //remoteIssueLinkBuilder.build();
            RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
            log.info(remoteIssueLink+"remoteIssueLinkBuilder---------"+remoteIssueLinkBuilder);
            RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
            RemoteIssueLinkService.CreateValidationResult createResult=remoteIssueLinkService.validateCreate(authenticationContext.getUser(),  remoteIssueLink);
            log.info("createResult of issue link  "+createResult.getErrorCollection().getReasons());
            RemoteIssueLinkService.RemoteIssueLinkResult result= remoteIssueLinkService.create(authenticationContext.getUser(), createResult);
            log.info("result of issue link  "+result);
        }
    }

    private void createRemoteIssueLinkWithinPISDTicket(String pisdIssueID, String pisdIssueKey,String incidentIssueID,
                                                       String incidentIssueKey,String jiraITSMBaseURL) {
     	log.info("----------------------------------createRemoteIssueLinkWithinPISDTicket starts----------------------------------------");
		log.info("pisdIssueID"+pisdIssueID);
		log.info("pisdIssueKey"+pisdIssueKey);
		log.info("incidentIssueID"+incidentIssueID);
		log.info("incidentIssueKey"+incidentIssueKey);
		log.info("jiraITSMBaseURL"+jiraITSMBaseURL);
		log.info("----------------------------------createRemoteIssueLinkWithinPISDTicket ends-------------------------");
        String url ="";
        try {
            String itsmApplicationName = URLEncoder.encode(pisdticketITSMApplicationName, "UTF-8").replace("+","%20");
            jiraITSMBaseURL = URLEncoder.encode(jiraITSMBaseURL,"UTF-8");
            url = baseURL+"/rest/dealertrack/1.0/createremotelinktoincident/linktoitsmticket?"+
                    "pisdTicketID="+pisdIssueID
                    +"&pisdTicketKey="+pisdIssueKey
                    +"&incidentTicketId="+incidentIssueID
                    +"&incidentTicketKey="+incidentIssueKey
                    +"&itsmApplicationName="+itsmApplicationName
                    +"&ITSMApplicationLinkID="+itsmApplicationLinkID
                    +"&JIRAITSMBaseURL="+jiraITSMBaseURL;
        } catch (UnsupportedEncodingException e) {
            log.info("Exception in creating URL"+e.getMessage());
           // e.printStackTrace();
        }

        log.info("REMOTE PISD URL IS " + url);
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-Type", "application/json");
        httpPut.setHeader("Authorization", "Basic " + getTokenId());
        HttpClient defaultHttpClient =null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpPut);
        } catch (IOException e) {
            log.info("Exception in setting remote issue link within PISD ticket"+e.getMessage());
           // e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.info("Response from setting the reporter " + response.getEntity());
        }
    }


    private void updateRepoterAndIncidentIDInServiceDeskTicket(String reporter, String issueKey, String incidentId) {
        String url = baseURL +"/rest/dealertrack/1.0/updatepsid/reporter?reporter="+reporter+"&issueKey="+
                issueKey+"&incidentId="+incidentId;

        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-Type", "application/json");
        httpPut.setHeader("Authorization", "Basic " + getTokenId());
        HttpClient defaultHttpClient =null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpPut);
        } catch (IOException e) {
            log.info("Exception in setting the reporter and incident Id in the PISD ticket"+e.getMessage());
            //e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.info("Response from setting the reporter " + response.getEntity());
        }
    }

    private Map<String, Object> createPISDTicket(int serviceDeskID, int requestTypeID, String summary, String description,
            Map<String, String> custFieldIDWithValue) {
		String payload = serviceDeskJSONCreator.constructJSONPayLoadForPISD(serviceDeskID, requestTypeID, summary, description,
		custFieldIDWithValue );
		log.info("Payload sent is :-----" + payload);
		StringEntity entity = null;
		entity = new StringEntity(payload, "UTF-8");
		String url = baseURL+"/rest/servicedeskapi/request";
		log.info("URL is :---- " + url);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Authorization", "Basic " + getTokenId());
		httpPost.setHeader("X-ExperimentalApi", "true");
		
		HttpClient defaultHttpClient =null;
		
		defaultHttpClient = HttpClientBuilder.create().build();
		
		HttpResponse response = null;
		try {
		response = defaultHttpClient.execute(httpPost);
		log.info("Error occurs inside CreateAndLinkPISDAction.createPISDTicket due to:-------------"+response. getStatusLine().getStatusCode());
		/*String result = EntityUtils.toString(response.getEntity());
		log.info("the result is:----------"+ result);*/
		log.info("Error occurs inside CreateAndLinkPISDAction.createPISDTicket due to:-------------"+response. getStatusLine().getReasonPhrase());
		} catch (IOException e) {
		log.info("-------------------------------------------------------------");
		log.info("Exception in executing the REST call to create PISD ticket"+e.getMessage());
		log.info("-------------------------------------------------------------");
		//e.printStackTrace();
		}
		
		if (response != null && response.getEntity().getContentLength() != 0) {
		
		//Confirm if the issue key and ID are returned properly, and return the ticket ID for further processing
		Map<String, Object> pisdIssueMap = serviceDeskJSONCreator.parseResponseAndGetTicketAttribute(response);
		//log.info("PISD Ticket created with the key :  " + pisdIssueMap.get("issueId"));
		/*String ticketID = parseResponseAndGetTicketAttribute(response, "issueId");
		log.info("And Issue ID of the PISD ticket created is :  " +ticketID);*/
		return pisdIssueMap;
    }

log.info("----------------------Response comes as null------------------------------");
return null;
}
    public String getTokenId() {
        // encoding byte array into base 64
        byte[] encoded = Base64.encodeBase64((userpwd).getBytes());
        String sid = new String(encoded);
        return sid;
    }

    private String messages = "";
    public String getMessages(){return this.messages;}
    
    /**
     * Includes Jira Web Resources
     * @return void
     */
    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
    }

	 /**
    *  Has issue links returns true or false
	*
    */
    private Boolean  hasIssueLinks(Issue issue){
      
        RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
        RemoteIssueLinkService.RemoteIssueLinkListResult result=remoteIssueLinkService.getRemoteIssueLinksForIssue(authenticationContext.getUser(), issue);
        if(result.isValid()){
            List<RemoteIssueLink>	remoteLinkList=result.getRemoteIssueLinks(); 
            if(remoteLinkList!=null){
            for(RemoteIssueLink remoteLink:remoteLinkList){
             if(remoteLink.getTitle().contains("PISD")){
				return true;
			 }
            }
            }
        }
		return false;
		
    }
    /**
     * Gets Issue Object
     * @return the Issue
     */
    public Issue getIssueObject()
    {
    	IssueService.IssueResult issueResult =null;
    	if(id!=null){
	        issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), id);	        
    	}
    	else{
    		issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), key);            
    	}
    	
    	if (!issueResult.isValid())
        {
            this.addErrorCollection(issueResult.getErrorCollection());
            return null;
        }
    	
        return  issueResult.getIssue();
    }

    // Getter adn Setters for passing the form params

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
	
}
