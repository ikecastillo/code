package com.dt.jira.plugin.pisdtojirarelease.rest;

import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.issue.AttachmentManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.attachment.Attachment;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.pisdtojirarelease.Constants.PISDToJiraConstants;
import com.dt.jira.plugin.pisdtojirarelease.handler.PluginSettingFactoryHandler;
import com.dt.jira.plugin.pisdtojirarelease.service.ProjMapService;
import com.dt.jira.plugin.pisdtojirarelease.utils.ApplicationIDFinder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.wsdl.Input;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This Restful Service contains methods to create Ticket in Jira Resource Management, Create link and Reciprocal links in Service desk and
 * Jira resource Management respectively.
 *
 */
@Path("/jirarelease")
public class CreateJiraRelTicket {

    private final Logger logger = LoggerFactory.getLogger(com.dt.jira.plugin.pisdtojirarelease.rest.CreateJiraRelTicket.class);
    private static PluginSettingFactoryHandler pluginSettingFactoryHandler;

    String baseURL = "";
    String applicationLinkID = "";
    String pisdApplicationLinkID = "";
    String pisdticketApplicationName  = "";
    private String jiraEnggApplicationName="";
    CreateJiraTicketModel createJiraTicketModel = new CreateJiraTicketModel();

    private  final JiraAuthenticationContext authenticationContext;
    private ProjMapService projMapService;


    public CreateJiraRelTicket(PluginSettingFactoryHandler pluginSettingFactoryHandler, JiraAuthenticationContext authenticationContext,
                               ProjMapService projMapService){
        this.pluginSettingFactoryHandler = pluginSettingFactoryHandler;
        this.authenticationContext = authenticationContext;
        this.projMapService = projMapService;
    }


    /*
    * This method accepts IsseuKey and Project Id as inputs to creaet ticket in jira Resource Management.
    * */
    @PUT
    @Path("/create")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("issueId") String issueId,
                               @QueryParam("projectKey") String projectKey)
    {
        setConfiguration();
        String resMsg = ""; //populated response message

        logger.info("*********start***********Call Rest  Service Testing********");
        try {

            MutableIssue issueParent = ComponentAccessor.getIssueManager().getIssueObject(Long.valueOf(issueId));
            logger.info("***Issue Key : "+issueParent.getKey()+"****");
            Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
            String statusName= issueParent.getStatus().getName();
            logger.info("***statusName : "+statusName+"****");

            if(  hasIssueLinks(issueParent)){
                logger.info("Executed on Status Change issue Parent  :"+ issueParent.toString());
                Map<String, String> jiraIssueMap=createJiraRMTicket(issueParent, projectKey);

                if (jiraIssueMap != null && jiraIssueMap.size()>0) {
                    long jiraIssueID = Integer.parseInt(jiraIssueMap.get("id").toString());
                    logger.info("****Issue ID Passed on is " + jiraIssueMap);
                    String jiraIssueKey  = (String) jiraIssueMap.get("key");
                    String pisdIssueID = issueParent.getId().toString();
                    String pisdIssueKey = issueParent.getKey();
                    String pisdLink = pisdIssueKey.concat(issueParent.getSummary());

                    logger.info("*****remote issue Id :"+jiraIssueID+" key : "+ jiraIssueKey );
                    logger.info("*****PISD issue id :"+pisdIssueID+" pisd issue key : "+ pisdIssueKey );

                    createRemoteIssueLinkWithinPISDTicket(pisdIssueID,pisdIssueKey,jiraIssueID,jiraIssueKey);
                    createRemoteIssueLinkInJira(jiraIssueID,jiraIssueKey, pisdIssueID,pisdIssueKey);
                    /* copy the attachments from service desk to Jira Realese System.*/
                    boolean isAttached  = addAttachmentToIssue(issueParent,jiraIssueKey);
                    if(isAttached){
                        logger.info("***Attachments are added successfully to the remote issue : "+ jiraIssueKey);
                    }
                    resMsg = "Success";
                    createJiraTicketModel.setIssueKey(String.valueOf(jiraIssueKey));


                } else {
                    logger.info("Error in PISD ticket creation for this Issue");
                    resMsg = "Error";
                }
            }  else {
                resMsg = "Warning";
            }

        } catch (Exception e) {
		logger.info("***Error occurs in getMessage of CreateJiraREL ticket due to : "+ e.getMessage());
            //e.printStackTrace();
        }
        createJiraTicketModel.setMessage(resMsg);

        return Response.ok(createJiraTicketModel).build();
    }


    /*
    * This method fetches data(UserId, Password, Application Name) from Plugin Settings Factory and configures
    * them with local variables. This method also fetches Application Id using ApplicationIDFinder class.
    *
    * */
    private void setConfiguration() {

        jiraEnggApplicationName = pluginSettingFactoryHandler.getJiraEnggAppName();
        pisdticketApplicationName = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_TITLE);
        logger.info("Current APPLICATION NAME IS " + pisdticketApplicationName);
        ApplicationIDFinder applicationIDFinder = new ApplicationIDFinder(jiraEnggApplicationName, pisdticketApplicationName,
                ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL), pluginSettingFactoryHandler.getTokenId());
        Map<String, String> applicationIDMap = applicationIDFinder.createApplicationIDMap();
        baseURL =    pluginSettingFactoryHandler.getJiraEnggURL(); //JIRA_ENGG_BASEURL
        applicationLinkID = pluginSettingFactoryHandler.getJiraEnggAppLinkID(); //JIRA_ENGG_APPLICATIONLINK_ID
        pisdApplicationLinkID = pluginSettingFactoryHandler.getServiceDeskAppLinkID(); //SERVICEDESK_PISD_APPLICATIONLINK_ID
        logger.info("Set createRemoteIssueLinkInJira baseURL = " + baseURL);
        logger.info("Set Application Link ID of Jira Engg as " + applicationLinkID);
        logger.info("Set Application Link ID of ITSM as " + pisdApplicationLinkID);
    }


    /**
     *  This method checks if the issue is having any Jira Engineering Issue Links. Returns false if such links exist.     *
     */
    private Boolean  hasIssueLinks(Issue issue){
        logger.info("***************** has Links called******************");

        RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
        logger.info("***********remoteIssueLinkService  :" + remoteIssueLinkService);
        RemoteIssueLinkService.RemoteIssueLinkListResult result=remoteIssueLinkService.getRemoteIssueLinksForIssue(authenticationContext.getLoggedInUser(), issue);

        if(result.isValid()){
            List<RemoteIssueLink> remoteLinkList=result.getRemoteIssueLinks();
            logger.info("*****************remoteLinkList size() : "+ remoteLinkList.size());
            if(remoteLinkList!=null){
                for(RemoteIssueLink remoteLink:remoteLinkList){
                    String applicationName = remoteLink.getApplicationName();

                    if(applicationName.equalsIgnoreCase(jiraEnggApplicationName)){

                        return false;
                    }
                }
            }
        } else {
            logger.info("*****************result : "+ result.getErrorCollection().toString());

        }
        return true;

    }

/*
*  This method replaces " in any string with \" . So that JSON will not break.s
* */

    private String getRefinedFieldValue(String fieldValue) {
        if (fieldValue != null) {
            fieldValue = fieldValue.replace("\"", "\\\"");
            //Take care of new line characters not impacting JSON
            fieldValue = formatnewLinesToFitInJSON(fieldValue);
        } else {
            fieldValue = "";
        }
        return fieldValue;
    }

    /**
     * Helper method that deals with the issue of new line characters in value while constructing JSON
     * @param value
     * @return refined value that can fit into JSON
     */
    private String formatnewLinesToFitInJSON(String value) {
        String[] lines = value.split("\r\n|\n");
        StringBuilder newValueBuilder = new StringBuilder();

        for(int i = 0; i< lines.length; i++){
            newValueBuilder.append(lines[i]);
            if(i != (lines.length -1)){
                newValueBuilder.append("\\n");
            }

        }
        value = newValueBuilder.toString();
        lines = value.split("\r\t|\t");
        newValueBuilder = new StringBuilder();

        for(int i = 0; i< lines.length; i++){
            newValueBuilder.append(lines[i]);
            if(i != (lines.length -1)){
                newValueBuilder.append("\\t");
            }
        }
        value = newValueBuilder.toString();
        return value;
    }

/*
* This method creates Jira Resource Management Ticket  Link in PISD  ticket.
* */

    private void createRemoteIssueLinkInJira(Long jiraIssueID, String jiraIssueKey,String pisdIssueID,
                                             String pisdIssueKey) throws Exception{
        //String url = "";
        // replacing spaces with %20 for build url
        String pisdApplicationName = URLEncoder.encode(pisdticketApplicationName, "UTF-8").replace("+","%20");
        String pisdBaseUrl = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL);
        pisdBaseUrl = URLEncoder.encode(pisdBaseUrl,"UTF-8");
        String jiraEnggBaseURL = baseURL;
        String url = jiraEnggBaseURL+PISDToJiraConstants.JIRA_ENGG_REMOTE_LINK_URL_TRAILER+
                "issueId="+jiraIssueID
                +"&issueKey="+jiraIssueKey
                +"&remoteIssueId="+pisdIssueID
                +"&remoteIssueKey="+pisdIssueKey
                +"&PISDApplicationName="+pisdApplicationName
                +"&PISDApplicationLinkID="+pisdApplicationLinkID
                +"&PISDBaseURL="+pisdBaseUrl;

        logger.info("REMOTE PISD URL IS " + url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
        HttpClient defaultHttpClient = null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpPost);
        } catch (IOException e) {
            logger.info("Exception in setting remote issue link within PISD ticket:"+e.getMessage());
           // e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            logger.info("Response from setting the reporter " + response.getEntity());
        }
    }


/*
* This method calls Rest API to cerate Jira Resource Management ticket.
* */


    private Map<String, String> createJiraRMTicket(Issue sdIssue, String projectKey){
        HttpResponse response = null;
        BufferedReader br = null;
        InputStream inputStream = null;
        String reporterName = pluginSettingFactoryHandler.getUserName(); // default reporter for external users
        try{

            String summary=getRefinedFieldValue(sdIssue.getSummary());
            String description =  getRefinedFieldValue(sdIssue.getDescription());
            Project issueProject = sdIssue.getProjectObject();
            String sdProjectName = issueProject.getName();
            String sdProjectKey = issueProject.getKey();
            logger.info("SD Project Name passed while creating JIRA Ticket is " + sdProjectName);
            logger.info("SD Project Key passed while creating JIRA Ticket is " + sdProjectKey);
            ProjMapBean projMapBean = new ProjMapBean();
            projMapBean.setSdProjName(sdProjectName);
            projMapBean.setSdProjKey(sdProjectKey);
            projMapBean = projMapService.getMappingBasedOnSDProj(projMapBean);
            String issueType = projMapBean.getJiraProjIssueType();
            //Default to Story issue type if no issue type found
            if (StringUtils.isBlank(issueType)) {
                issueType = PISDToJiraConstants.ISSUE_TYPE_STORY;
            }

            logger.info("JIRA Release PROJ ISSUE TYPE IS " + issueType);

            String key = projectKey;
            String priority = sdIssue.getPriority().getName();
            String jiraEnggUrl = baseURL;
            ApplicationUser reporter = sdIssue.getReporter();
            logger.info("*************projectKey : "+ projectKey);

            //find whether the user is valid in system
            if(isReporterValid(reporter)){
                reporterName = reporter.getUsername().toString();
            }
            logger.info("Reporter to be used for the Engg ticket for the issue "+ sdIssue.getKey() +" would be " + reporterName);
            String payload = constructJSONPayloadForPISD(key, summary,description,issueType,reporterName,priority);

            StringEntity entity = null;
            entity = new StringEntity(payload, "UTF-8");
            String url = jiraEnggUrl+ PISDToJiraConstants.JIRA_ENGG_CREATE_ISSUE_URL_TRAILER;

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
            httpPost.setHeader("X-ExperimentalApi", "true");
           
            HttpClient defaultHttpClient =null;
            defaultHttpClient = HttpClientBuilder.create().build();
            try {
                response = defaultHttpClient.execute(httpPost);
            }
            catch (IOException e) {
                logger.info("Exception in executing the REST call to create Jira Engg. ticket"+e.getMessage());
               // e.printStackTrace();
                createJiraTicketModel.setMessage(e.toString());
            }

            if (response != null && response.getEntity().getContentLength() != 0) {

                inputStream = response.getEntity().getContent();
                br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder content = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    content.append(line);
                }
                logger.info("**********Response *****"+ content.toString());
                final JSONObject obj = new JSONObject(content.toString());
                String jiraKey = obj.getString("key");
                String jiraId = obj.getString("id");
                logger.info("response key :  " + jiraKey);
                logger.info("response Id :  " + jiraId);
                Map<String, String> jiraIssueMap = new HashMap<String, String>();
                jiraIssueMap.put("key",jiraKey);
                jiraIssueMap.put("id",jiraId);
                logger.info("PISD Ticket created with the key :  issueKey" + jiraIssueMap);
                String adminUser = pluginSettingFactoryHandler.getUserName();
                // Add the comment for non cox users
                if(reporterName.equals(adminUser)) {
                    addComment(sdIssue,jiraKey);
                }

                return jiraIssueMap;
            }



        } catch (IOException ie) {
		 logger.info("----------------Error Occurs inside CreateJiraRelTicket.1 -------------"+ie.getMessage());
           // ie.printStackTrace();
        } catch (Exception e){
		logger.info("----------------Error Occurs inside CreateJiraRelTicket.2 -------------"+e.getMessage());
           // e.printStackTrace();;
        }
        finally {
            try {
                inputStream.close();
                br.close();
            } catch (IOException e) {
                //e.printStackTrace();
				logger.info("----------------Error Occurs inside CreateJiraRelTicket.3 -------------"+e.getMessage());
            }


        }

        return null;
    }

    /*
    * This method creates reciprocal PISD ticket link in Jira Resource Management ticket.
    * */
    private void createRemoteIssueLinkWithinPISDTicket(String issueId,String issueKey,Long remoteIssueId,String remoteIssueKey){

        String remoteLinkUrl  = baseURL+PISDToJiraConstants.BASE_URL_TRAILER;
        logger.info("**********MethodInvoked******");
        ApplicationUser user = authenticationContext.getLoggedInUser();
        if(StringUtils.isNotBlank(issueKey) && (remoteIssueId!=null)){
            RemoteIssueLinkBuilder remoteIssueLinkBuilder =new RemoteIssueLinkBuilder();
            remoteIssueLinkBuilder.url(remoteLinkUrl+remoteIssueKey);
            remoteIssueLinkBuilder.globalId("appId="+applicationLinkID+"&issueId="+remoteIssueId);
            remoteIssueLinkBuilder.title(remoteIssueKey);
            remoteIssueLinkBuilder.applicationType("com.atlassian.jira");
            remoteIssueLinkBuilder.applicationName(jiraEnggApplicationName);
            remoteIssueLinkBuilder.issueId(Long.valueOf(issueId));
            remoteIssueLinkBuilder.relationship("relates to");

            RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
            logger.info(remoteIssueLink+"remoteIssueLinkBuilder---------"+remoteIssueLinkBuilder);
            RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
            RemoteIssueLinkService.CreateValidationResult createResult=remoteIssueLinkService.validateCreate(user,  remoteIssueLink);
            logger.info("createResult of issue link  "+createResult.getErrorCollection().getReasons());
            RemoteIssueLinkService.RemoteIssueLinkResult result= remoteIssueLinkService.create(user, createResult);
            logger.info("result of issue link  "+result);
        }
    }

    /*
    * This method constructs JSON message with the values passed to it to send input to rest call.
    * */

    private String constructJSONPayloadForPISD(String key, String summary, String description,String issueType, String reporter, String priority) {
        StringBuffer json = new StringBuffer("{\"fields\": {\"project\":{\"key\": \"");
        json.append(key + "\"},\"summary\": \""+summary)
                .append("\",\"description\": \""+ description)
                .append( "\","+"\"issuetype\": {" + "          \"name\": \"" + issueType + "\"")
                .append("}," +" \"reporter\":{" + "  \"name\":\""+reporter+"\"")
                .append("}," +" \"priority\":{" + "  \"name\":\""+priority+"\"")
                .append("}" +"   }" +"}");
        logger.info("***********Json  : "+ json.toString());

        return json.toString();

    }

    private MultipartEntityBuilder getMultipartEntityBuilder(Issue issue) {
        AttachmentManager attachManager = ComponentAccessor.getAttachmentManager();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        List<Attachment> attachmentList = attachManager.getAttachments(issue);
        String pisdIssueKey = issue.getKey();
        String projectKey = issue.getProjectObject().getKey();
        if (attachmentList.size() > 0) {
            for (Attachment attachment : attachmentList) {
                logger.info("***Attachment ID : " + attachment.getId() + "***Attachment file Name : " + attachment.getFilename() + "**** attachment.getMimetype() " + attachment.getMimetype());
                attachment.getMimetype();


                String absolutepath = PISDToJiraConstants.ATTACHMENTS_FOLDER_PATH+projectKey+"//"+getNextSequenceFolder(pisdIssueKey,PISDToJiraConstants.ATTACHMENTS_FOLDER_NAME_TEN_THOUSAND)+"//" + pisdIssueKey + PISDToJiraConstants.ATTACHMENTS_DOUBLE_SLASH + attachment.getId();
                File fileToUpload = new File(absolutepath);
                FileBody fileBody = new FileBody(fileToUpload, attachment.getFilename(), attachment.getMimetype(), "UTF-8");
                multipartEntityBuilder.addPart("file", fileBody);
            }
        }
        return  multipartEntityBuilder;
    }

    /**
     * Get next sequence id for the attachment folder
     *
     * @param issuekey - issue key
     * @param attachmentfolder - default folder id 10000
     * @return - next sequence id for attachment ex( 10000, 20000, 30000, etc..)
     */
    private long getNextSequenceFolder(String issuekey, long attachmentfolder){
        String arr[] = issuekey.split("-");
        long nextattachmentfolder = 0;
        if( arr!=null && arr.length > 0){
            long keyid = Long.parseLong(arr[1]);
            nextattachmentfolder  = attachmentfolder * (1 + keyid/attachmentfolder) ;
        }
        return  nextattachmentfolder;
    }
    /**
     * Add attachment to the remote issue
     * @param jiraIssueKey - remote issue Key
     * @return
     * @throws IOException
     */
    public boolean addAttachmentToIssue(Issue issue,String jiraIssueKey) throws IOException{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = pluginSettingFactoryHandler.getJiraEnggURL()+PISDToJiraConstants.JIRA_ENGG_CREATE_ISSUE_URL_TRAILER+jiraIssueKey+PISDToJiraConstants.ATTACHMENTS_URL_TRAILER;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
        httpPost.setHeader("X-Atlassian-Token", "nocheck");
        MultipartEntityBuilder multipartEntityBuilder = getMultipartEntityBuilder(issue);
        httpPost.setEntity(multipartEntityBuilder.build());
        String requestLine = "executing request " + httpPost.getRequestLine();
        logger.info("********* " + requestLine);

        CloseableHttpResponse response;
        try {
            response = httpclient.execute(httpPost);
        } finally {
            httpclient.close();
        }
        /*
           Tested the post url using curl as below--->
           curl -D- -u username:pwd -X POST -H "X-Atlassian-Token: no-check" -F "file=@Products_xMatters.txt"
           http://10.134.8.51:8080/rest/api/2/issue/CBE-810/attachments --noproxy '*'
        */

        logger.info("**********Attachment response.getStatusLine().getStatusCode() *****"+ response.getStatusLine().getStatusCode());
        if(response.getStatusLine().getStatusCode() == 200)
            return true;
        else
            return false;

    }

    /**
     * Is reporter  is valid cox email id. if yes update reporter as cox user otherwise dtjira admin as reporter.
     * @param reporter - reporter email-id
     * @return  true - if reporter is valid
     */
    private  boolean isReporterValid(ApplicationUser reporter) throws  IOException{
        boolean isReporter = false;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = pluginSettingFactoryHandler.getJiraEnggURL()+PISDToJiraConstants.REST_API_GET_USER+"?username="+reporter.getUsername();
        HttpGet httpPost = new HttpGet(url);
        httpPost.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
        httpPost.setHeader("X-Atlassian-Token", "nocheck");

        CloseableHttpResponse response;
        try {
            response = httpclient.execute(httpPost);
        } finally {
            httpclient.close();
        }
        if (response != null && (response.getStatusLine().getStatusCode() == 200)) {
            isReporter = true;
        }

        return  isReporter;
    }


    /**
     * Add comment to the Jira Engin.. Ticket
     * @param issue
     * @throws IOException
     */
    private  void addComment(Issue issue,String remoteIssueKey) throws  IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = pluginSettingFactoryHandler.getJiraEnggURL()+PISDToJiraConstants.REST_API_ADD_COMMENT+"/"+remoteIssueKey+"/comment";
        logger.info("build url for comment: "+ url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());


        String comment = "JIRA Engg Ticket created from the Service Desk Ticket : "+ issue.getKey() +" whose original reporter is "+issue.getReporter().getDisplayName();
        StringBuffer json = new StringBuffer("{\"body\": \""+comment).append("\"}");
        StringEntity entity = null;
        entity = new StringEntity(json.toString(), "UTF-8");
        httpPost.setEntity(entity);
        logger.info("Insert comment json object: "+ json.toString());
        CloseableHttpResponse response;
        InputStream inputStream = null;
        BufferedReader br=null;
        try {
            response = httpclient.execute(httpPost);
            inputStream = response.getEntity().getContent();
            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                content.append(line);
            }
            logger.info("**********addComment response *****"+ content.toString());

        } finally {
            inputStream.close();
            br.close();
            httpclient.close();
        }
        if (response != null && (response.getStatusLine().getStatusCode() == 200)) {
            logger.info("Reporter of the service desk ticket "+ remoteIssueKey +"is  not a JIRA Engg user, hence adding a comment about the same in the Engg ticket "+ issue.getKey());
        }
    }
}