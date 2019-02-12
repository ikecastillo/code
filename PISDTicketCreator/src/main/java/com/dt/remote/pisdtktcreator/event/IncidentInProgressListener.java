package com.dt.remote.pisdtktcreator.event;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.remote.pisdtktcreator.rest.ConfigBean;
import com.dt.remote.pisdtktcreator.service.ConfigService;
import com.dt.remote.pisdtktcreator.utils.ApplicationIDFinder;
import com.dt.remote.pisdtktcreator.utils.ServiceDeskJSONCreator;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dt.remote.pisdtktcreator.rest.AdminConfigResource.Config;

/**
 * Listener that will check if the incident or outage has gone to progress.
 * If the solution group contains F&I and the product is Partner Integration Desk AND
 * If the incident or outage's status is in progress , then
 *  1) Create a PISD ticket in Service Desk portal
 *  2) Change the reporter of that ticket to the incident's reporter
 *
 * Created by yagnesh.bhat on 4/14/2016.
 */
public class IncidentInProgressListener implements InitializingBean,
        DisposableBean {

    private final EventPublisher eventPublisher;
    private ConfigService configService;
    private final JiraAuthenticationContext authenticationContext;
    private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
    private final Logger log = LoggerFactory.getLogger(IncidentInProgressListener.class);

    private static PluginSettingsFactory pluginSettingsFactory;

    private String baseURL = "";
    private String applicationLinkID = "";
    private String userpwd = "";

    private String itsmApplicationLinkID = "";

    private String pisdticketSDApplicationName = "";
    private String pisdticketITSMApplicationName = "";

    private ServiceDeskJSONCreator serviceDeskJSONCreator;

    public IncidentInProgressListener(EventPublisher eventPublisher, JiraAuthenticationContext authenticationContext,
                                      ConfigService configService,PluginSettingsFactory psf) {
        this.eventPublisher = eventPublisher;
        this.authenticationContext=authenticationContext;
        this.configService = configService;
        this.pluginSettingsFactory = psf;
        serviceDeskJSONCreator = new ServiceDeskJSONCreator();
        setConfiguration();
    }

    private void setConfiguration() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();

        String userid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdTicketUid");
        String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdTicketPwd");
        userpwd =  userid+":"+password;

        pisdticketSDApplicationName = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdticketSDApplicationName");
    }

    /**
     * This event occurs when an ITIM Ticket is moved to In Progress and
     * Solution Group contains F&I and product is Partner Integration Service Desk
     *
     * @param issueEvent
     */
    @EventListener
    public void onIssueEvent(IssueEvent issueEvent) {
        Long eventTypeId = issueEvent.getEventTypeId();
        log.debug("PISC Ticket Creator event type ID " + eventTypeId);
        Issue issue = issueEvent.getIssue();
        Project project = issue.getProjectObject();

        log.debug("Issue Type Object check for PISD :" + issue.getIssueTypeObject().getName());
        if ("ITIM".equals(project.getKey()) &&
            (issue.getIssueTypeObject().getName().equals("Incident") ||
                    issue.getIssueTypeObject().getName().equals("Outage"))) {
            log.debug("Detected that this is an Incident or outage - checking if PISD ticket needs created");
            Map<String, Object> issueEventMap = issueEvent.getParams();
            log.debug("PISD event source when not in progress : "+issueEventMap.get("eventsource"));
            String statusName = issue.getStatusObject().getName();
            if (eventTypeId.equals(EventType.ISSUE_WORKSTARTED_ID)) {
                log.debug("Is this incident or outage updated recently?");
                log.debug("PISD : Issue status is " + statusName);
                if ((issueEventMap != null && ("workflow").equals(issueEventMap.get("eventsource")))) {
                    log.debug("PISD event source when issue updated : "+issueEventMap.get("eventsource"));
                    log.debug("Checking if this incident is in progress for PISD");
                    if (("In Progress").equalsIgnoreCase(statusName)) {
                        log.debug("PISD event source when issue in progress : "+issueEventMap.get("eventsource"));
                        log.debug("This incident is in progress, checking if its a candidate for PISD ticket creation");
                        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
                        CustomField solutionGroupProdCF = cfm.getCustomFieldObjectByName("Solution Groups - Products");
                        List<String> solutionGroupProdValues = getMultiLevelCascadingSelectValue(issue, solutionGroupProdCF);

                        //Need to make sure the third level dropdown is selected
                        if (solutionGroupProdValues.size() > 2 && !hasIssueLinks(issue))  {
                            createPISDTicket(issue, solutionGroupProdValues);
                        }
                    }
                }
            }
        }
    }

    private void createPISDTicket(Issue issue, List<String> solutionGroupProdValues) {
        String solutionGroup = solutionGroupProdValues.get(0);
        if (solutionGroup.contains("F&I")) {
            String product = solutionGroupProdValues.get(1);
            if (product.equalsIgnoreCase("Partner Integration Service Desk")) {
                log.debug("Going ahead to create a PISD Service desk ticket for the Incident/Outage : " + issue.getKey());
                String subProduct = solutionGroupProdValues.get(2);
                //Create a config bean that will be used as a lookup in AO
                ConfigBean configBean = new ConfigBean();
                configBean.setServiceDeskName(product);
                configBean.setRequestTypeName(subProduct);
                ConfigBean beanInAO = configService.getConfig(configBean);
                log.debug("Trying to find a config in AO");
                if (beanInAO != null) {
                    log.debug("Found an AO configuration for PISD service desk, proceeding to create PISD ticket");
                    int serviceDeskID = Integer.parseInt(beanInAO.getServiceDeskID());
                    int requestTypeID = Integer.parseInt(beanInAO.getRequestTypeID());
                    String customFieldsWithIDs = beanInAO.getCustFieldsWithIDs();
                    log.debug("Custom fields with IDS are " + customFieldsWithIDs);
                    String summary = issue.getSummary();
                    log.debug("Summary is " + summary);
                    String description = issue.getDescription();
                    log.debug("Description is " + description);
                    Map<String, String> custFieldIDWithValue = serviceDeskJSONCreator.lookUpCustomFieldsInIssue(customFieldsWithIDs, issue);
                    log.debug("Got the hashmap of custom fields");
                    pisdticketITSMApplicationName = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_TITLE);
                    log.debug("Current APPLICATION NAME IS " + pisdticketITSMApplicationName);
                    ApplicationIDFinder applicationIDFinder = new ApplicationIDFinder(pisdticketSDApplicationName, pisdticketITSMApplicationName,
                            ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL), getTokenId());
                    Map<String, String> applicationIDMap = applicationIDFinder.createApplicationIDMap();
                    baseURL = applicationIDMap.get(pisdticketSDApplicationName).split("\\|")[0];
                    applicationLinkID = applicationIDMap.get(pisdticketSDApplicationName).split("\\|")[1];
                    itsmApplicationLinkID = applicationIDMap.get(pisdticketITSMApplicationName).split("\\|")[1];
                    log.debug("Set baseURL = " + baseURL);
                    log.debug("Set Application Link ID of SD as " + applicationLinkID);
                    log.debug("Set Application Link ID of ITSM as " + itsmApplicationLinkID);
                    Map<String, Object> pisdIssueMap = createPISDTicket(serviceDeskID, requestTypeID, summary, description, custFieldIDWithValue);
                    if (pisdIssueMap != null) {
                        long pisdIssueID = (Integer)pisdIssueMap.get("issueId");
                        log.debug("Issue ID Passed on is " + pisdIssueID);
                        String pisdIssueKey  = (String) pisdIssueMap.get("issueKey");
                        String incidentIssueID = issue.getId().toString();
                        String incidentIssueKey = issue.getKey();
                        createRemoteIssueLink(incidentIssueID,incidentIssueKey,pisdIssueID,pisdIssueKey);
                        updateRepoterAndIncidentIDInServiceDeskTicket(issue.getReporter().getName(), pisdIssueKey, issue.getKey());
                        createRemoteIssueLinkWithinPISDTicket(pisdIssueID+"",pisdIssueKey,incidentIssueID,incidentIssueKey,
                                ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL));

                    } else {
                        log.info("Cannot create PISD ticket");
                    }


                } else {
                    log.info("Couldnt find any settings configured for this sub product " +
                            "for partner integration service desk");
                }
            }
        }
    }

    private void createRemoteIssueLinkWithinPISDTicket(String pisdIssueID, String pisdIssueKey,String incidentIssueID,
                                                       String incidentIssueKey,String jiraITSMBaseURL) {

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
                log.debug("Exception in creating URL");
                e.printStackTrace();
            }

            log.debug("REMOTE PISD URL IS " + url);
            HttpPut httpPut = new HttpPut(url);
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setHeader("Authorization", "Basic " + getTokenId());
            HttpClient defaultHttpClient =null;

            defaultHttpClient = HttpClientBuilder.create().build();

            HttpResponse response = null;
            try {
                response = defaultHttpClient.execute(httpPut);
            } catch (IOException e) {
                log.error("Exception in setting remote issue link within PISD ticket");
                e.printStackTrace();
            }

            if (response != null && response.getEntity().getContentLength() != 0) {
                log.debug("Response from setting the reporter " + response.getEntity());
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
            log.error("Exception in setting the reporter and incident Id in the PISD ticket");
            e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.debug("Response from setting the reporter " + response.getEntity());
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

        String remoteLinkUrl  = baseURL+"/browse/";
        if(StringUtils.isNotBlank(issueKey) && (remoteIssueId!=null)){
            RemoteIssueLinkBuilder remoteIssueLinkBuilder =new RemoteIssueLinkBuilder();
            remoteIssueLinkBuilder.url(remoteLinkUrl+remoteIssueKey);
            remoteIssueLinkBuilder.globalId("appId="+applicationLinkID+"&issueId="+remoteIssueId);
            remoteIssueLinkBuilder.title(remoteIssueKey);
            //remoteIssueLinkBuilder.statusName("Waiting for support");
            remoteIssueLinkBuilder.applicationType("com.atlassian.jira"); //This is constant, no need to be configured
            /*remoteIssueLinkBuilder.applicationName("JIRA Service Desk Clone");*/
            remoteIssueLinkBuilder.applicationName(pisdticketSDApplicationName);
            remoteIssueLinkBuilder.issueId(Long.valueOf(issueId));
            remoteIssueLinkBuilder.relationship("relates to");
            //remoteIssueLinkBuilder.build();
            RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
            log.debug(remoteIssueLink+"remoteIssueLinkBuilder---------"+remoteIssueLinkBuilder);
            RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
            RemoteIssueLinkService.CreateValidationResult createResult=remoteIssueLinkService.validateCreate(authenticationContext.getUser(),  remoteIssueLink);
            log.debug("createResult of issue link  "+createResult.getErrorCollection().getReasons());
            RemoteIssueLinkService.RemoteIssueLinkResult result= remoteIssueLinkService.create(authenticationContext.getUser(), createResult);
            log.debug("result of issue link  "+result);
        }
    }

    private Map<String, Object> createPISDTicket(int serviceDeskID, int requestTypeID, String summary, String description,
                                    Map<String, String> custFieldIDWithValue) {
        log.debug("Invoking method to construct JSON Payload to create PISD ticket");
        String payload = serviceDeskJSONCreator.constructJSONPayLoadForPISD(serviceDeskID, requestTypeID, summary,
                description, custFieldIDWithValue );
        log.debug("Payload retrieved to create PISD ticket is " + payload);
        StringEntity entity = null;
        entity = new StringEntity(payload, "UTF-8");
        String url = baseURL+"/rest/servicedeskapi/request";

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
        } catch (IOException e) {
            log.error("Exception in executing the REST call to create PISD ticket",e);
            //e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {

            //Confirm if the issue key and ID are returned properly, and return the ticket ID for further processing
            Map<String, Object> pisdIssueMap = serviceDeskJSONCreator.parseResponseAndGetTicketAttribute(response);
            log.info("PISD Ticket created with the key :  " + pisdIssueMap.get("issueKey"));
            /*String ticketID = parseResponseAndGetTicketAttribute(response, "issueId");
            log.info("And Issue ID of the PISD ticket created is :  " +ticketID);*/
            return pisdIssueMap;
        }


        return null;
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

    public String getTokenId() {
        // encoding byte array into base 64
        byte[] encoded = Base64.encodeBase64((userpwd).getBytes());
        String sid = new String(encoded);
        return sid;
    }

    @Override
    public void destroy() throws Exception {
        // unregister ourselves with the EventPublisher
        eventPublisher.unregister(this);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // register ourselves with the EventPublisher
        eventPublisher.register(this);
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
}
