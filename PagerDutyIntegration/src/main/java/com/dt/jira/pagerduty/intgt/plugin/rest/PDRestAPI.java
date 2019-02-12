package com.dt.jira.pagerduty.intgt.plugin.rest;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.project.component.ProjectComponent;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.user.DelegatingApplicationUser;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.pagerduty.intgt.plugin.service.FieldMapperService;
import com.dt.jira.pagerduty.intgt.plugin.service.PDServicesConfigService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dt.jira.pagerduty.intgt.plugin.utils.IncidentDurationConverter.convertToDaysHrsMins;

/**
 * This resource is used to create a PagerDuty Event on a PagerDuty Application.
 */
@Path("/pagerDuty")
public class PDRestAPI {

	public String userpwd;

	private static PluginSettingsFactory pluginSettingsFactory;
	private final FieldMapperService fieldMapperService;
	private final PDServicesConfigService pdServicesConfigService;
	private static final String PLUGIN_STORAGE_KEY = ConfigResource.Config.class.getName();

	private String userName = "";
	private String password = "";
	private String interval = "";
	private String pdhipchattoken = "";
	private String serviceKey = "";
	private String pddUrl = "";
	private String pdrestBaseUrl = "";
	private String jiraAdminEmailids = "";
	private String hipChatRoomURL = "";

	public final Logger logger = LoggerFactory.getLogger(PDRestAPI.class);
	public PDRestAPI(PluginSettingsFactory psf, FieldMapperService fieldMapperService,
					 PDServicesConfigService pdServicesConfigService){
		/*logger.setInfoLogLevel();*/
		this.pluginSettingsFactory = psf;
		this.fieldMapperService = fieldMapperService;
		this.pdServicesConfigService = pdServicesConfigService;
		setConfiguration();

	}

	private void setConfiguration(){
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();

		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pduid");
		String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdpwd");

		String interval = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdinterval");
		String pdhipchattoken = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdhipchattoken");

		String pdrestapibaseurl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdrestapibaseurl");
		String pddturl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pddturl");
		String jiraAdmins = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdjiraemailid");

		setUserName(username);
		setPassword(password);
		setPddUrl(pddturl);
		setPdrestBaseUrl(pdrestapibaseurl);
		setPdhipchattoken(pdhipchattoken);
		setJiraAdminEmailids(jiraAdmins);

		userpwd =  username+":"+password;		
	}

/*
 *
 * Sample pager duty request body
 *
 * {
      "service_key": "eb1ea8b7896b49648f238e889bb7baca",
      "event_type": "trigger",
      "description": "Testkk PagerDuty Incident",
      "client": "JIRA ITSM DEV",
      "client_url": "hhttps://jiraitsmdev.dealertrack.com",
      "details": {
        "Severity": "Low",
        "Clients Impacted": "External"
      },
      "contexts":[
        {
          "type": "link",
          "href": "http://dealer.pagerduty.com"
        },{
          "type": "link",
          "href": "http://dealer.pagerduty.com",
          "text": "View the incident on PagerDuty"
        },{
          "type": "image",
          "src": "https://chart.googleapis.com/chart?chs=600x400&chd=t:6,2,9,5,2,5,7,4,8,2,1&cht=lc&chds=a&chxt=y&chm=D,0033FF,0,0,5,1"
        },{
          "type": "image",
          "src": "https://chart.googleapis.com/chart?chs=600x400&chd=t:6,2,9,5,2,5,7,4,8,2,1&cht=lc&chds=a&chxt=y&chm=D,0033FF,0,0,5,1",
          "href": "https://google.com"
        }
      ]
    }

 *
 */


	@GET
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPagerDutyIncident(@QueryParam("issueKey") String issueKey,
											@QueryParam("technicalhipchatroomno") int technicalhipchatroomno) {
		logger.info("Pager duty Integration ");
		logger.info("issueKey Integration Field>>>" + issueKey);
		Issue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(
				issueKey);

		Map<String , List<String>> fieldMap = getJiraFieldValues(issue);
		
		Status status = issue.getStatusObject();
		logger.debug("--------------------status: "+ status.getName());
		logger.debug(" pager duty : "+ issue.getKey());

		List<PDServiceBean> pdServiceBeans = getDDCPagerDutyService(issue);
		logger.debug("Service configurations found " + pdServiceBeans.size());
		hipChatRoomURL = setHipChatLinkForTechnicalBridge(technicalhipchatroomno);


		// Create the pagerDuty event after finding the concerned service
		List<PDRestAPIModel> restApiModels = new ArrayList<>();
		if(pdServiceBeans!=null && pdServiceBeans.size()>0){
			for(PDServiceBean pdServiceBean: pdServiceBeans){
				logger.debug("Service Name: " + pdServiceBean.getServiceName());
				logger.debug("Service Key: " + pdServiceBean.getServiceKey());
				logger.debug("Client Impacted Name: " + pdServiceBean.getClientsImpacted());
				try{
					PDRestAPIModel restApiModel = createIncident(fieldMap, issue,pdServiceBean);
					restApiModels.add(restApiModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// Update Pager duty log
		if (!restApiModels.isEmpty()) {
			updatePagerDutyLogTable(restApiModels, issue);
		}

		return Response.ok(restApiModels).build();

	}

	public String setHipChatLinkForTechnicalBridge(int technicalHipChatRoomNumber) {

		String hipChatServerURL = "https://cm.hipchat.com";
		StringBuffer hipChatTechnicalRoomURL = new StringBuffer();

		if (technicalHipChatRoomNumber == 0) {
			hipChatTechnicalRoomURL.append("No HipChat Room Created");
		} else {
			hipChatTechnicalRoomURL.append("<a href='")
					.append(hipChatServerURL)
					.append("/chat/room/")
					.append(technicalHipChatRoomNumber)
					.append("'>Join HipChat Room </a><br/>");
		}

		return hipChatTechnicalRoomURL.toString();
	}


  private PDRestAPIModel createIncident(Map<String , List<String>> fieldMap, Issue issue,PDServiceBean pdServiceBean){

		PDRestAPIModel apiModel = new PDRestAPIModel();
		String issueKey = issue.getKey();
		// Create an instance of HttpClient.
		HttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpPost post = post = new HttpPost(getPdrestBaseUrl());
		// set headers
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "No Auth");


		InputStream inputStream = null;

		String PAYLOAD =  constructPagerDutyJson(fieldMap, issueKey,pdServiceBean, issue);
		logger.debug("SAMPLE PAYLOAD IS \n" + PAYLOAD);

        /* create event for pager duty */
		try {
			StringEntity data = new StringEntity( PAYLOAD , "UTF-8" );
			data.setContentType("application/json");
			//set the request body
			post.setEntity( data );
			// Post the request for create the xMatters event
			HttpResponse response = defaultHttpClient.execute(post);
			apiModel.setIssueKey(issueKey);
			logger.debug(" response.getEntity().toString() : "+response.getEntity().toString());

				inputStream = response.getEntity().getContent();
				byte[] responseBody =  IOUtils.toByteArray(inputStream);
				if(responseBody!=null && responseBody.length > 0){

					logger.info("Pager duty response body: ");
					ObjectMapper mapper = new ObjectMapper();
					JsonNode rootNode = mapper.readTree(responseBody);
					JsonNode eventId = rootNode.get("incident_key");
					if(eventId!=null){
						logger.info("eventId: "+eventId.asText());
						apiModel.setEventId(eventId.asText());
						apiModel.setStatus("Success");
						apiModel.setMessage("PagerDuty Event successfully created!");
					}
				}
		}   catch(Exception e)  {
			logger.error("Exception due to validation error :",e);
			apiModel.setStatus("Validation Error");
			apiModel.setMessage( e.getMessage() );
			sendMailNotification(e.getMessage() + "\n issuekey: " + issueKey +"\n Json String: "+PAYLOAD);
			e.printStackTrace();
			return apiModel;
		} finally{
			post.releaseConnection();
		}
		return apiModel;

	}

	 private List<PDServiceBean> getDDCPagerDutyService(Issue issue){


		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField severityCF = cfm.getCustomFieldObjectByName("Severity");
		CustomField solutionGroupCF = cfm.getCustomFieldObjectByName("Solution Groups - Products");
		CustomField clientImpactedCF = cfm.getCustomFieldObjectByName("Clients Impacted");
		 CustomField impactedCF = cfm.getCustomFieldObjectByName("Impacted - Function");

		List<String> severityVal = getSingleSelectValue(issue,severityCF);
		String severity = severityVal.get(0).toString();
        List solutionGroupValues = getMultiLevelCascadingSelectValue(issue, solutionGroupCF);
        String solGroup = "";
        String product = "";
        String sub_product = "";
	    String sub_sub_product = "";

	    List<String> clientsImpactedVal = getCascadeSelectValue(issue,clientImpactedCF);
	    String clientsImpacted = clientsImpactedVal.get(0);
	    String location = "";
		 if (clientsImpacted.equals("Internal")) {
			 location = clientsImpactedVal.get(1);
		 }

	    List<String> impactedVal = getCascadeSelectValue(issue,impactedCF);
		 String impacted = "";
		 if (impactedVal != null) {
			 impacted = impactedVal.get(0);
		 }


        if(solutionGroupValues!=null && solutionGroupValues.size()>0){
        	solGroup = (String)solutionGroupValues.get(0);
        }
        if(solutionGroupValues!=null && solutionGroupValues.size()>1 && solGroup.equals("DDC")){
        	product = (String)solutionGroupValues.get(1);
        }
        if(solutionGroupValues!=null && solutionGroupValues.size()>2 && solGroup.equals("DDC")){
        	sub_product = (String)solutionGroupValues.get(2);
        }
		if(solutionGroupValues!=null && solutionGroupValues.size()>3 && solGroup.equals("DDC")){
		 	sub_sub_product = (String)solutionGroupValues.get(3);
		}


    	logger.debug("solGroup: (this will be DDC if clients impacted is external) " + solGroup +
				"  product: "+product + " sub_product: "+ sub_product + " sub_sub_product: " + sub_sub_product);
    	logger.debug("severityVal: " + severity);
	    logger.debug("Clients Impacted " + clientsImpacted);
	    logger.debug("Location (If Clients Impacted is Internal): " + location);
	    logger.debug("Impacted : " + impacted);

		 List<PDServiceBean> liPdServiceBeans = findExactServicesMatchingIssue(severity, product, sub_product,
				 sub_sub_product, clientsImpacted, impacted);

		//If the exact bean is not found we try getting the closest service as possible , based on the
		 //products and its subs
		 if(liPdServiceBeans.isEmpty()) {
			liPdServiceBeans = tryGettingPdServiceBeans(severity, product, sub_product, clientsImpacted,
					impacted, liPdServiceBeans);
		}

		 //If the list of services is still empty, and if the clients impacted is external , we see if
		 //setting impacted to an empty string helps us find service(s)
		 if(liPdServiceBeans.isEmpty() && clientsImpacted.equals("External")) {
			 liPdServiceBeans = tryGettingPdServiceBeans(severity, product, sub_product, clientsImpacted,
					 "", liPdServiceBeans);
		 }



		return liPdServiceBeans;
   }

	private List<PDServiceBean> tryGettingPdServiceBeans(String severity, String product,
				 String sub_product, String clientsImpacted, String impacted, List<PDServiceBean> liPdServiceBeans) {
		if (liPdServiceBeans.isEmpty()) {
            //Check if there is setting for sub product - i.e. one level above sub sub product
            logger.debug("Cant find service, Going one level to sub product");
            liPdServiceBeans = findExactServicesMatchingIssue(severity, product, sub_product,
                    "", clientsImpacted, impacted);
        }

		if (liPdServiceBeans.isEmpty()) {
            //Check if there is setting for sub product - i.e. one level above sub product
            logger.debug("Still Cant find service, Going one level to product");
            liPdServiceBeans = findExactServicesMatchingIssue(severity, product, "",
                    "", clientsImpacted, impacted);
        }


		//This is if clients impacted is internal and no product is selected
		if (liPdServiceBeans.isEmpty()) {
            //Check if there is setting for no product, maybe for clients impacted internal and no prod selected
            logger.debug("Still Cant find service, Going one level to no product");
            liPdServiceBeans = findExactServicesMatchingIssue(severity, "", "",
                    "", clientsImpacted, impacted);
        }
		return liPdServiceBeans;
	}

	private List<PDServiceBean> findExactServicesMatchingIssue(String severity, String product,
							   String sub_product, String sub_sub_product, String clientsImpacted, String impacted) {
		List<PDServiceBean> liPdServiceBeans = new ArrayList<>();
		// Get all the pager duty services which is configured for the incident project
		List<PDServiceBean> pdServiceBeans = pdServicesConfigService.getAllServicesFromDB();

		if(pdServiceBeans!=null && pdServiceBeans.size()>0){
			for(PDServiceBean pdServiceBean: pdServiceBeans){
				/*if (clientsImpacted.equals("External")) {

				} else if (clientsImpacted.equals("Internal")) {

				}*/
				/*if(solGroup.equalsIgnoreCase("DDC")){
		    		if(pdServiceBean.getSeverities().equals(severity) &&
							( !product.equals("") && product.equalsIgnoreCase(pdServiceBean.getDdcProduct()))) {
		    			liPdServiceBeans.add(pdServiceBean);
		    		} // end if
		    	} // end if DDC solution group*/
				if (pdServiceBean.getSeverities().equals(severity) &&
					pdServiceBean.getClientsImpacted().equals(clientsImpacted) &&
					pdServiceBean.getImpacted().equals(impacted) &&
					pdServiceBean.getDdcProduct().equals(product) &&
					pdServiceBean.getDdcSubProduct0().equals(sub_product) &&
					pdServiceBean.getDdcSubProduct1().equals(sub_sub_product)) {
					logger.debug("Found a service , adding it!");

					liPdServiceBeans.add(pdServiceBean);
				}
			} // end for loop
		}
		return liPdServiceBeans;
	}


		/**
		 *  Update the PagerDuty Log table
		 */
		private void updatePagerDutyLogTable(List<PDRestAPIModel> PDRestAPIModels,Issue issue){
				try{
				MutableIssue mutableIssue  = (MutableIssue)issue;
				List priorityListVal = getSingleSelectValue(issue, ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Severity"));
				String priority = priorityListVal.get(0).toString();

				CustomField pdLogCF =  ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("PagerDuty Log");
				String existingLog = (String)issue.getCustomFieldValue(pdLogCF);

				Map<String, ModifiedValue> modifiedFields = null;
				FieldLayoutItem fieldLayoutItem = null;
				ModifiedValue modifiedValue = null;
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa");
				String strDate = dateFormat.format(currentDate);
				StringBuilder pdresponse = new StringBuilder(appendHeaders());
				String _existingLog = existingLog!=null ? appendExistingLog(existingLog) : "" ;
				pdresponse.append(_existingLog);
				pdresponse.append("<tbody>");
				if(PDRestAPIModels!=null && PDRestAPIModels.size()>0){
					for(PDRestAPIModel pdRestAPIModel : PDRestAPIModels){
						String eventId = pdRestAPIModel.getEventId();
						String status  = pdRestAPIModel.getStatus();
						String message = pdRestAPIModel.getMessage();
						pdresponse.append("<tr>")
						.append("<td style='min-width:100px'>").append(strDate).append("</td>")
						.append("<td>").append(issue.getDescription()).append("</td>")
						.append("<td>").append(eventId).append("</td>")
						.append("<td>").append(status).append("</td>")
						.append("<td>").append(priority).append("</td>")
						.append("</tr>");
					}
					pdresponse.append("</tbody>")
					.append("</table>");
				}
				DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
				String pdLog = pdresponse.toString();
				if(pdLog!=null && pdLog.length()>0){
					mutableIssue.setCustomFieldValue(pdLogCF, pdLog);
					modifiedFields = mutableIssue.getModifiedFields();
					fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(pdLogCF);
					modifiedValue = modifiedFields.get(pdLogCF.getId());
					pdLogCF.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
					setReindex(mutableIssue);
				}


				} catch (Exception e) {
					e.printStackTrace();
				}

		}

	private String constructPagerDutyJson(Map<String , List<String>> fieldMap, String issueKey,PDServiceBean pdServiceBean, Issue issue){

		String itsmApplicationName = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_TITLE);
		String itsmBaseURL = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL);

		//Issue is very necessary to determine which incident actually triggered the PD event, so adding it at the
		//very top of the details element in the JSON
		String issueKeyJSONEntry = "\"ITSM Incident ID\" :" + "\""+issueKey+"\",";
		String hipChatRoomURLJSONEntry =  "\"HipChat Room URL\" :" + "\""+hipChatRoomURL+"\",";

		List<FieldBean> mappingsFromAO = fieldMapperService.getAllMappingsFromDB();
		StringBuilder payload = new StringBuilder("\n{");
		payload.append("\n\"service_key\" :" + "\"" +pdServiceBean.getServiceKey() + "\"");
		payload.append(",\n\"event_type\" :" + "\"trigger\"");
		//payload.append(",\n\"subject\" :" + "\""+issue.getSummary()+"\""); <-- Not sure why this is not working
		payload.append(",\n\"description\" :" + "\""+issue.getSummary()+"\"");
		payload.append(",\n\"client\" :" + "\""+itsmApplicationName+"\"");
		payload.append(",\n\"client_url\" :" + "\""+itsmBaseURL+"\"");
		payload.append(",\n" + getContextArrayJson());
		payload.append(",\n\"details\": {");
		payload.append(issueKeyJSONEntry);
		payload.append(hipChatRoomURLJSONEntry);
		String outerComma = ",";
		for (FieldBean fieldBean : mappingsFromAO) {
			String fieldName = fieldBean.getJiraFieldName();
			//Get the list of field values for a particular JIRA field in the mapping
			List<String> jiraFieldValues = fieldMap.get(fieldBean.getJiraFieldName());
			//Then see what is the actual value of the mapped JIRA field. It can either be a single value or cascade
			//In case of single value, the arraylist in the map will be of size 1, else for cascade value,
			//the list will be of size > 1.
			if (jiraFieldValues !=null && jiraFieldValues.size() == 1) {
				//Then ,first of all, map the field name in as a sub-key within properties
				payload.append("\""+fieldName+"\":");

				//One special case of xmatters communication log - value needs to be massaged , before sending
				if (fieldName.equals("PagerDuty Log")) {
					//xMatters Communication Log : getPagerDutyLog(jirafield)
					payload.append("\"" +getPagerDutyLog(jiraFieldValues.get(0))+ "\"");
					payload.append(outerComma);
					continue;
				}

				if (fieldName.equals("Incident Duration")) {
					logger.debug(jiraFieldValues.get(0) + " is converted to " + convertToDaysHrsMins(jiraFieldValues.get(0)));
					payload.append("\"" + convertToDaysHrsMins(jiraFieldValues.get(0))+ "\"");
					payload.append(outerComma);
					continue;
				}
				//Easiest case, just get the list element and add it as the value in JSON payload!
				payload.append("\"" + jiraFieldValues.get(0)+ "\"");
				payload.append(outerComma);
				//outerComma = ",";
			} else if(jiraFieldValues !=null && jiraFieldValues.size() > 1){
				payload.append("\""+fieldName+"\":");
				payload.append("\"" + jiraFieldValues.toString()+ "\"");
				payload.append(outerComma);
			}
		}
		if(payload.lastIndexOf(",") > -1 ) {
			payload.deleteCharAt(payload.length() - 1); // remove extra comma from payload
		}
		payload.append("\n}\n");
		payload.append("\n}\n");
		return payload.toString();
	}
	private String getContextArrayJson(){
		StringBuilder outageBu = new StringBuilder()
		.append( "\n\"contexts\":   [")
		.append("{ \n")
		.append("\"type\": \"link\", \n")
		.append("\"href\": \"http://dealer.pagerduty.com\" \n")
		.append( "}," )
		.append("{ \n")
		.append("\"type\": \"link\", \n")
		.append("\"href\": \"http://dealer.pagerduty.com\", \n")
		.append("\"text\": \"View the incident on PagerDuty\" \n")
		.append( "}" )
		.append("]");
		return outageBu.toString();
	}
	/*private String  getPagerDutyServiceKey(){
		List<PDServiceBean> pdServiceBeans =pdServicesConfigService.getAllServicesFromDB();
		if(pdServiceBeans!=null && pdServiceBeans.size()>0){
		PDServiceBean pdServiceBean = pdServiceBeans.get(0);
		return pdServiceBean.getServiceKey();
		}
	return null;
	}
	public String getTokenId() {
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64(userpwd.getBytes());
		String sid = new String(encoded);
		return sid;
	}*/

	private void setReindex(MutableIssue mutableIssue){
		try {
			ComponentManager.getComponent(IssueIndexManager.class).reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.info("index issue" + ie.getMessage());
		}
	}
	public List parseHTMLTableData(String table){
		List tableList = new ArrayList();
		Document doc = Jsoup.parse(table);
		Element table1 = doc.select("table").get(0); //select the first table.
		Elements rows = table1.select("tr");
		Iterator<Element> rowIterator = rows.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Element row = rowIterator.next();
			Elements cols = row.select("td");

			String date = cols.get(0).text();
			String desc = cols.get(1).text();
			String eventId = cols.get(2).text();
			String eventstatus = cols.get(3).text();
			String priority = cols.get(4).text();


			PDValidation validation = new PDValidation();
			validation.setDate(date);
			validation.setDesc(desc);
			validation.setEventId(eventId);
			validation.setEventStatus(eventstatus);
			validation.setPriority(priority);
			tableList.add(validation);
		}
		return tableList;
	}

	private void sendMailNotification(String errorMsg){
		try{
			logger.info("Send Mail Notification: "+errorMsg);
			MailServerManager mailServerManager = ComponentAccessor
					.getMailServerManager();
			SMTPMailServer mailServer = mailServerManager
					.getDefaultSMTPMailServer();

			Properties p=new Properties();

			InputStream is = getClass().getClassLoader().getResourceAsStream("PagerDuty-integration-plugin.properties");

			if (is!=null)
			{
				p.load(is);
			}
			//String emailProperty = p.getProperty("jira.admin.mailid");
			String emailProperty = getJiraAdminEmailids();
			Email email = new Email(
					emailProperty);
			email.setFrom(mailServer.getDefaultFrom());
			email.setSubject("integration failed");
			email.setMimeType("text/html");
			email.setBody(errorMsg);
			SingleMailQueueItem item = new SingleMailQueueItem(email);
			ComponentAccessor.getMailQueue().addItem(item);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	private String appendHeaders(){
		StringBuffer headers = new StringBuffer();
		String columns = "Date Time,Description,Event Id,Event Status,Severity";
		headers.append("<table border=1 width=100% >")
				.append("<thead><tr>");
		StringTokenizer st = new StringTokenizer(columns,",");
		while(st.hasMoreTokens()){
			String headerName = st.nextToken();
			// System.out.println("header Name: "+ headerName);
			headers.append("<th bgcolor=#D0CECE style='border:1px solid Black'>").append(headerName).append("</th>");
		}
		headers.append("</tr></thead>");
		return headers.toString();
	}
	private String appendExistingLog(String existingLog){
		String htmlStr = existingLog;
		if(htmlStr != null && !htmlStr.equals("") && htmlStr.indexOf("<thead>")>0){
			htmlStr = htmlStr.substring(htmlStr.indexOf("<tbody>"),htmlStr.indexOf("</tbody>"));
		} else if( htmlStr != null || htmlStr.indexOf("data")>0){
			htmlStr="";
		} else if (htmlStr == null) {
			htmlStr = "";
		}
		return htmlStr;
	}


	public String getPagerDutyLog(String existingLog){
		StringBuffer logBuffer = new StringBuffer();
		if(existingLog!=null && !existingLog.equals("")){
			/*if(!existingLog.equals("<html></html>")){*/
				List dataList = parseHTMLTableData(existingLog);
				Iterator<PDValidation> iterator = dataList.iterator();

				//Will be used to filter out some messages as seen toward the end of this method
				List<String> rowLogs = new ArrayList<>();

				while(iterator.hasNext()){
					PDValidation validation = iterator.next();
					TimeZone timeZone = Calendar.getInstance().getTimeZone();
					String rowLog = "@"+getTime(validation.getDate()) + " " + timeZone.getDisplayName(false, TimeZone.SHORT) + "> "+validation.getDesc();
					rowLogs.add(rowLog);
				}

				/*If the message length is > 1999, then we send as much as possible rows from
				bottom up till the length becomes 1999. The moment the length becomes 1999,
				we wont send the row at that iteration.*/
				ListIterator<String> li = rowLogs.listIterator(rowLogs.size());
				// Iterate in reverse.
				while(li.hasPrevious()) {
					String previous = li.previous();
					if (logBuffer.length() + previous.length() <= 1999) {
						logBuffer.append(previous+"\\n\\n");
					}
				}

			//}
			return logBuffer.toString().trim();
		}
		return "";
	}

	private String getTime(String dateStr){
		String timeString = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa");
		Date date = null;
		try {
			date= dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		timeString = DateFormat.getTimeInstance().format(date);
		return timeString;
	}



	/**
	 * Helper method that creates a HashMap of the JIRA field name -> its values
	 * @param issue
	 *
	 * @return HashMap of the JIRA field name -> its values
	 */
	private Map<String,List<String>> getJiraFieldValues(Issue issue){

		/*
		Custom field name: Impacted - Function
		Custom field type name: Select List (cascading)
		Custom field name: Severity
		Custom field type name: Select List (single choice)
		Custom field name: Solution Group - Product
		Custom field type name: Select List (cascading)
		Custom field name: Type
		Custom field type name: Select List (cascading)
		Custom field name: XMatters Log
		Custom field type name: Textfield for xMatters Log
		Custom field name: xMatters Description
		Custom field type name: Text Field (multi-line)
		Custom field name: xMatters Event Status
		Custom field type name: Select List (single choice)

		*/
		Map <String,List<String>> jiraFieldValues = new HashMap<>();
		List values;
		List<FieldBean> fieldBeans = fieldMapperService.getAllMappingsFromDB();
		for(FieldBean fb: fieldBeans){
			String fieldName = fb.getJiraFieldName();
			CustomField customField = getCustomField(fieldName);

			//If its not a custom field, then it will be a system field
			if (customField == null) {
				extractSystemFieldValuesInHash(issue, jiraFieldValues, fieldName);
			} else {
				String fieldType = customField.getCustomFieldType().getName();
				//logger.info("Field Name is " + fieldName);
				//logger.info("Field Type is " + fieldType);
				logger.debug("Field Name is "+ fieldName + " field Type :"+ fieldType  +" field Key "+ customField.getCustomFieldType().getKey());
				

				if(fieldType.equalsIgnoreCase("Select List (cascading)")){
					values = getCascadeSelectValue(issue,customField);
					jiraFieldValues.put(fieldName, values);

				} else if(fieldType.equalsIgnoreCase("Select List (single choice)")) {
					values = getSingleSelectValue(issue, customField);
					jiraFieldValues.put(fieldName, values);
				} else if (fieldType.contains("multi select")) {
					String notSupportedMessage = "Cant map " + fieldName + " as multiselect types are not supported";
					values = new ArrayList<>();
					values.add(notSupportedMessage);
					jiraFieldValues.put(fieldName, values);
				} else if (fieldType.contains("Multi-Level Cascading Select")) {
					values = getMultiLevelCascadingSelectValue(issue, customField);
					jiraFieldValues.put(fieldName, values);
				} else if (fieldType.contains("User Picker (multiple users)")) {
					values = getMultiUserPickerValue(issue, customField);
					if(values!=null && (values.size() > 0) ){
						jiraFieldValues.put(fieldName, values);
					}

				} else if (fieldType.contains("Date Time Picker")) {
					values = getDateTimePickerValue(issue, customField);
					jiraFieldValues.put(fieldName, values);

				} else {
					values = new ArrayList<>();
					String value = (String)issue.getCustomFieldValue(customField);
					//Replace " with \"
					value = getRefinedFieldValue(value);
					values.add(value);
					jiraFieldValues.put(fieldName, values);
				}
			}

		}
		return jiraFieldValues;
	}


	/**
	 * Helper method that extracts system field values from the issue object itself
	 * @param issue
	 * @param jiraFieldValues
	 * @param fieldName
	 */
	private void extractSystemFieldValuesInHash(Issue issue, Map<String, List<String>> jiraFieldValues, String fieldName) {
		List values = new ArrayList<>();
		switch (fieldName.toLowerCase()) {
			case IssueFieldConstants.PROJECT:
				String projectValue = issue.getProjectObject().getName();
				values.add(projectValue);
				jiraFieldValues.put(fieldName, values);
				break;
			case IssueFieldConstants.ASSIGNEE :
				String assigneeValue = issue.getAssignee().getDisplayName();
				assigneeValue = getRefinedFieldValue(assigneeValue);
				values.add(assigneeValue);
				jiraFieldValues.put(fieldName,values);
				break;
			case IssueFieldConstants.DESCRIPTION :
				String descriptionValue = issue.getDescription();
				descriptionValue = getRefinedFieldValue(descriptionValue);
				values.add(descriptionValue);
				jiraFieldValues.put(fieldName, values);
				break;
			case "issue type" :
				values.add(issue.getIssueTypeObject().getName());
				jiraFieldValues.put(fieldName, values);
				break;
			case IssueFieldConstants.PRIORITY :
				if (issue.getPriorityObject() != null) {
					values.add(issue.getPriorityObject().getName());
				} else {
					values.add("PRIORITY FIELD NOT CONFIGURED");
				}

				jiraFieldValues.put(fieldName, values);
				break;
			case IssueFieldConstants.REPORTER:
				String reporterValue = issue.getReporter().getDisplayName();
				values.add(reporterValue);
				jiraFieldValues.put(fieldName, values);
				break;
			case IssueFieldConstants.STATUS:
				values.add(issue.getStatusObject().getName());
				jiraFieldValues.put(fieldName, values);
				break;
			case IssueFieldConstants.SUMMARY:
				String summaryValue = issue.getSummary();
				summaryValue = getRefinedFieldValue(summaryValue);
				values.add(summaryValue);
				jiraFieldValues.put(fieldName,values);
				break;
			case "due date":
				String dueDateValue = "";
				if (issue.getDueDate() != null) {
					dueDateValue = issue.getDueDate().toString();
				} else {
					dueDateValue = "DUE DATE NOT CONFIGURED";
				}
				values.add(dueDateValue);
				jiraFieldValues.put(fieldName,values);
				break;
			case "affects version/s":
				Collection<Version> versions = issue.getAffectedVersions();
				List<String> affVersionsAsString = getVersionsAsStrings(versions);
				//values.add(affVersionsAsString);
				jiraFieldValues.put(fieldName,affVersionsAsString);
				break;
			case "fix version/s":
				Collection<Version> fixVersions  = issue.getFixVersions();
				List<String> fixVersionsAsString =  getVersionsAsStrings(fixVersions);
				//values.add(fixVersionsAsString);
				jiraFieldValues.put(fieldName,fixVersionsAsString);
				break;
			case IssueFieldConstants.RESOLUTION:
				String resolution  = issue.getResolutionObject().getName();
				values.add(resolution);
				jiraFieldValues.put(fieldName,values);
				break;
			case IssueFieldConstants.CREATED:
				Timestamp created  = issue.getCreated();
				values.add(created.toString());
				jiraFieldValues.put(fieldName,values);
				break;
			case IssueFieldConstants.UPDATED:
				Timestamp updated  = issue.getUpdated();
				values.add(updated.toString());
				jiraFieldValues.put(fieldName,values);
				break;
			case "component/s":
				List<String> componentValues = new ArrayList<>();
				StringBuilder compString = new StringBuilder(" ");
				Collection<ProjectComponent> components = issue.getComponentObjects();
				if (components != null && components.size() > 0) {
					for (ProjectComponent projectComponent : components) {
						compString.append(projectComponent.getName() + " ");
					}
					componentValues.add(compString.toString() + " ");
				} else {
					componentValues.add("FIELD NOT SET OR COMPONENTS NOT SET");
				}
				jiraFieldValues.put(fieldName, componentValues);
				break;

			default:
				logger.info("Value not found for " + fieldName + " - setting defaults");
				values.add("JIRA FIELD VALUE NOT FOUND");
				jiraFieldValues.put(fieldName, values);
				break;
		}
	}

	/**
	 * Get a String of all versions separated by spaces and within a list
	 *
	 * @param versions
	 * @return String of all versions separated by spaces and within a list
	 */
	private List<String> getVersionsAsStrings(Collection<Version> versions) {
		List<String> versionsAsStringList = new ArrayList<>();
		StringBuilder versionsString = new StringBuilder(" ");
		if (versions != null && versions.size() > 0) {
			for (Version version : versions) {
				versionsString.append(version.getName() + " ");
			}
			versionsAsStringList.add(versionsString.toString());
		} else {
			versionsAsStringList.add("FIELD NOT SET OR NO VERSIONS SET");
		}
		return versionsAsStringList;
	}

	/**
	 * Helper method to initiate refining of the string values to fit the JSON
	 * @param fieldValue
	 * @return refined Field Value that fits in JSON well
	 */
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

	/**
	 * Get the custom filed obj
	 * @param cfname - name of the custom field
	 * @return customfield object
	 */
	private CustomField getCustomField(String cfname){
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField customField = cfm.getCustomFieldObjectByName(cfname);
		return customField;
	}

	/**
	 * Get the value for custom field type is single select
	 * @param issue
	 * @param customField
	 * @return list of value(s) for single select - but its size would always be one
	 */
	private List<String> getSingleSelectValue(Issue issue, CustomField customField){
		OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
		List<String> values = new ArrayList<>();

		LazyLoadedOption severityVal = (LazyLoadedOption)issue.getCustomFieldValue(customField);

		if(severityVal!=null && severityVal.getOptionId()!=null ){
			Option  severityOpt= optionsManager.findByOptionId(severityVal.getOptionId());
			if(severityOpt!=null && severityOpt.getValue()!=null){
				String priorityName = severityOpt.getValue();
				logger.info("Severity : "+ priorityName);
				values.add(priorityName);
			}
		}
		return values;
	}
	/**
	 * Get the values for custom field type is cascade select
	 * @param issue
	 * @param customField
	 * @return list of value(s) for cascade field - its size would always be > 1
	 */
	private List<String> getCascadeSelectValue(Issue issue, CustomField customField){
		String solutionGrValue = "";
		String productValue = "";
		List<String> values = new ArrayList<>();
		Map<LazyLoadedOption, LazyLoadedOption> solutionGroupMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
				issue.getCustomFieldValue(customField);
		if(solutionGroupMap!=null){
			for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : solutionGroupMap.entrySet()) {
				LazyLoadedOption llo = null;
				if(opt.getKey() ==  null ){
					llo = opt.getValue();
					solutionGrValue = String.valueOf(llo.getValue());
				}
				if(opt.getKey() !=  null ){
					llo = opt.getValue();
					productValue = String.valueOf(llo.getValue());
				}
			}
		}
		values.add(solutionGrValue);
		values.add(productValue);
		return values;
	}

	/**
	 * Helper method to deal with multilevel cascade field.
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for multiselect cascade field.
	 */
	private List getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

		if (valuesList != null) {
			for (Object value : valuesList) {
				values.add(value.toString());
			}

		}
		return values;
	}
/**
	 * Helper method to deal with multi user picker field. Note, as of now
	 * is a multi user picker field
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for multi-user picker field , out of which we need to pick the values
	 */
	private List getMultiUserPickerValue(Issue issue, CustomField customField) {
		List<String> values = null;
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);
		//logger.info("Multi User picker Field Type is " + valuesList);
		//Add only first two values are they are not supported in xMatters as of now
		if (valuesList != null) {
		      values  = new ArrayList<>();
			for (Object value : valuesList) {
				DelegatingApplicationUser user = (DelegatingApplicationUser) value;
				values.add(user.getDisplayName());
			}
		}


		return values;
	}


	/**
	 * Helper method to deal with Date Time Picker Field
	 * @param issue
	 * @param customField
     * @return list of values for Date time picker field, out of which we need to pick first value
     */
	private List<String> getDateTimePickerValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		if (customField != null) {
			Date custFieldDate = (Date) issue.getCustomFieldValue(customField);
			if (custFieldDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy h:mm a");
				values.add(dateFormat.format(custFieldDate));
			} else {
				values.add("");
			}
		} else {
			values.add("");
		}
		return values;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getJiraAdminEmailids() {
		return jiraAdminEmailids;
	}

	public void setJiraAdminEmailids(String jiraAdminEmailids) {
		this.jiraAdminEmailids = jiraAdminEmailids;
	}

	public String getPdhipchattoken() {
		return pdhipchattoken;
	}

	public void setPdhipchattoken(String pdhipchattoken) {
		this.pdhipchattoken = pdhipchattoken;
	}

	public String getPddUrl() {
		return pddUrl;
	}

	public void setPddUrl(String pddUrl) {
		this.pddUrl = pddUrl;
	}

	public String getPdrestBaseUrl() {
		return pdrestBaseUrl;
	}

	public void setPdrestBaseUrl(String pdrestBaseUrl) {
		this.pdrestBaseUrl = pdrestBaseUrl;
	}

}
