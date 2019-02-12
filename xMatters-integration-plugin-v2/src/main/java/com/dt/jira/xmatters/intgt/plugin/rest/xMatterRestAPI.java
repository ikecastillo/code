package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.bc.project.component.ProjectComponent;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayout;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutManager;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.index.IssueIndexManager;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.resolution.Resolution;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.version.Version;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.xmatters.intgt.plugin.constants.Constants;
import com.dt.jira.xmatters.intgt.plugin.rest.ConfigResource.Config;
import com.dt.jira.xmatters.intgt.plugin.service.EventConfigService;
import com.dt.jira.xmatters.intgt.plugin.service.FieldMapperService;
//import com.dt.jira.xmatters.intgt.plugin.service.MgtHipChatService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
import com.atlassian.jira.user.DelegatingApplicationUser;

import static com.dt.jira.xmatters.intgt.plugin.constants.Constants.*;
import static com.dt.jira.xmatters.intgt.plugin.utils.IncidentDurationConverter.convertToDaysHrsMins;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * The resource is used to create a xMatters event on xMatters application.
 */
@Path("/xMatters")
public class xMatterRestAPI {

	public String userpwd;
	private static String OUTAGE_TYPE_INTERNAL_SYSTEM = "Internal";
	private static String OUTAGE_TYPE_EXTERNAL_SYSTEM = "External";
	private static PluginSettingsFactory pluginSettingsFactory;
	private final FieldMapperService fieldMapperService;
	private final EventConfigService eventConfigService;
	//private final MgtHipChatService mgtHipChatService;
	private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	private String userName = "";
	private String password = "";
	private String outageUrl = "";
	private String nonoutageUrl = "";

	private String xmattersExternalResponse1 = "";
	private String xmattersExternalResponse2 = "";

	private String  xmattersinternalOutUrl = "";
	private String  xmattersinternalNonOutUrl = "";

	private String xmattersInternalResponse1 = "";
	private String xmattersInternalResponse2 = "";

	private String jiraAdminEmailids = "";

	public final LoggerWrapper logger = LoggerWrapper.with(getClass());
	public xMatterRestAPI(PluginSettingsFactory psf, FieldMapperService fieldMapperService,
						  EventConfigService eventConfigService){
		this.pluginSettingsFactory = psf;
		this.fieldMapperService = fieldMapperService;
		this.eventConfigService = eventConfigService;
		//this.mgtHipChatService = mgtHipChatService;
		setConfiguration();

	}

	private void setConfiguration(){
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();

		String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersUid");
		String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersPwd");

		String jiraAdmins = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".jiraAdminEmail");

		setUserName(username);
		setPassword(password);

		setJiraAdminEmailids(jiraAdmins);

		userpwd =  username+":"+password;
	}


	private void setEventConfiguration(String extConfBrdge, String extNonConfBrdge,
									   String internalConf, String internalNonConf,String confBridgeType,
									   Issue issue){
		EventBean eventConfigBean = new EventBean();
		String formAPIID = "";
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField solutionGroupCF = cfm.getCustomFieldObjectByName("Solution Groups - Products");

		List solutionGroupValues = getMultiLevelCascadingSelectValue(issue, solutionGroupCF);
		String solutionGroupValue = "";
		if (solutionGroupValues.size() >=1 ) {
			solutionGroupValue = (String)getMultiLevelCascadingSelectValue(issue, solutionGroupCF).get(0);
		}

		logger.info("--------------------------------------Solution Group value got to decide the FORM API ID is " + solutionGroupValue);

		CustomField clientsImpactedCF = cfm.getCustomFieldObjectByName("Clients Impacted");
		List<String> clientsImpactedValues = getCascadeSelectValue(issue,clientsImpactedCF);
		String internalLocationValue = "";
		if (clientsImpactedValues.size() > 1) {
			internalLocationValue = clientsImpactedValues.get(1);
		}

		logger.info("------------------------------------------Location value got to decide the form API ID is (will be blank or none if external) " +
				internalLocationValue);

		if (extNonConfBrdge.equals("true")){
			if (confBridgeType.equals("Management")) {
				if (solutionGroupValue.equals("DDC") || solutionGroupValue.equals("DDC-xMatters-Test")) {
					formAPIID = "DDCEXTWITHMGTNOCONF";
				} else {
					formAPIID = "EXTWITHMGTNOCONF";
				}
			}else if (confBridgeType.equals("Technical")) {
				//Note DDC-xMatters-Test is ONLY for testing purposes and would be disabled once testing is done
				if (solutionGroupValue.equals("DDC") || solutionGroupValue.equals("DDC-xMatters-Test")) {
					formAPIID = "DDCEXTWITHNONCONF";
				} else {
					formAPIID = "EXTWITHNONCONF";
				}
			}
		}

		if (internalNonConf.equals("true")) {
			if (internalLocationValue.equals("Burlington")) {
				formAPIID = "DDCINTWITHNONCONF";
			} else {
				formAPIID = "INTWITHNONCONF";
			}
		}

		if (extConfBrdge.equals("true")) {
			if (confBridgeType.equals("Management")) {
				if (solutionGroupValue.equals("DDC") || solutionGroupValue.equals("DDC-xMatters-Test")) {
					formAPIID = "DDCEXTWITHMGTCONF";
				} else {
					formAPIID = "EXTWITHMGTCONF";
				}
			} else if (confBridgeType.equals("Technical")) {

				//Note DDC-xMatters-Test is ONLY for testing purposes and would be disabled once testing is done
				if (solutionGroupValue.equals("DDC") || solutionGroupValue.equals("DDC-xMatters-Test")) {
					formAPIID = "DDCEXTWITHCONF";
				} else {
					formAPIID = "EXTWITHCONF";
				}

			}
		}

		if (internalConf.equals("true")) {
			if (internalLocationValue.equals("Burlington")) {
				formAPIID = "DDCINTWITHCONF";
			} else {
				formAPIID = "INTWITHCONF";
			}

		}

		logger.info("--------------------FORM API ID finally decided is " + formAPIID);
		eventConfigBean.setFormAPIID(formAPIID);
		eventConfigBean = eventConfigService.getEvent(eventConfigBean);

		switch(formAPIID) {
			case "EXTWITHCONF" : setExternalConferenceBridgeParams(eventConfigBean); break;
			case "EXTWITHMGTCONF" : setExternalConferenceBridgeParams(eventConfigBean); break;
			case "EXTWITHNONCONF" : setExternalNonConferenceBridgeParams(eventConfigBean); break;
			case "EXTWITHMGTNOCONF" :setExternalNonConferenceBridgeParams(eventConfigBean); break;
			case "INTWITHCONF" : setInternalConferenceBridgeParams(eventConfigBean); break;
			case "INTWITHNONCONF" : setInternalNonConferenceBridgeParams(eventConfigBean); break;
			case "DDCEXTWITHCONF" : setExternalConferenceBridgeParams(eventConfigBean); break;
			case "DDCEXTWITHNONCONF" : setExternalNonConferenceBridgeParams(eventConfigBean); break;
			case "DDCINTWITHCONF" : setInternalConferenceBridgeParams(eventConfigBean); break;
			case "DDCINTWITHNONCONF" : setInternalNonConferenceBridgeParams(eventConfigBean); break;
			case "DDCEXTWITHMGTCONF" : setExternalConferenceBridgeParams(eventConfigBean); break;
			case "DDCEXTWITHMGTNOCONF" : setExternalNonConferenceBridgeParams(eventConfigBean); break;
			default: logger.info("------------------------UNABLE TO FIND THIS FORM API ID, NOT SURE WHAT XMATTERS EVENT TO CREATE! : " + formAPIID); break;
		}
	}

	private void setExternalConferenceBridgeParams(EventBean eventConfigBean) {
		setOutageUrl(eventConfigBean.getxMattersFormWSURL());
		setXmattersExternalResponse1(eventConfigBean.getxMattersFormResponseCodeAvailableUUID());
		setXmattersExternalResponse2(eventConfigBean.getxMattersFormResponseCodeNotAvailableUUID());
	}

	private void setExternalNonConferenceBridgeParams(EventBean eventConfigBean) {
		setNonoutageUrl(eventConfigBean.getxMattersFormWSURL());
	}

	private void setInternalConferenceBridgeParams(EventBean eventConfigBean) {
		setXmattersinternalOutUrl(eventConfigBean.getxMattersFormWSURL());
		setXmattersInternalResponse1(eventConfigBean.getxMattersFormResponseCodeAvailableUUID());
		setXmattersInternalResponse2(eventConfigBean.getxMattersFormResponseCodeNotAvailableUUID());
	}

	private void setInternalNonConferenceBridgeParams(EventBean eventConfigBean) {
		setXmattersinternalNonOutUrl(eventConfigBean.getxMattersFormWSURL());
	}


	@GET
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	public Response getXMatters(@QueryParam("issueKey") String issueKey,
								@QueryParam("confBridge") String confBridge,
								@QueryParam("nonConfBridge") String nonConfBridge,
								@QueryParam("internalConfBridge") String internalConfBridge,
								@QueryParam("internalNonConfBridge") String internalNonConfBridge,
								@QueryParam("restCallCounter") int restCallCounter,
								@QueryParam("totalRestCalls") int totalRestCalls,
								@QueryParam("confBridgeType") String confBridgeType,
								@QueryParam("firstRowAdded") String firstRowAdded) {
		logger.info("-------xMatter Integration ");
		logger.info("---------------issueKey Integration Field>>>" + issueKey);
		logger.info("-------------confBridge Integration Field>>>" + confBridge);
		logger.info("-----------nonConfBridge Integration Field>>>>" + nonConfBridge);
		//logger.info("--------------Hipchat room number got for technical from client side is " + technicalhipchatroomno);
		//logger.info("----------------HipChat server URL got from the client side " + hipChatServerURL);

		String sgvalue = "";
		String eventStatus = "";
		String response = "";
		String statusErr = "Validation Error";
		String authtoken = getTokenId();

		Issue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(
				issueKey);

		setEventConfiguration(confBridge, nonConfBridge, internalConfBridge, internalNonConfBridge, confBridgeType, issue);

		if (xMattersEventStatusContainsResolvedAndMandatoryFieldsNotFilled(issue)) {
			return setErrorMessage(issue,
					"Cannot create xMatters event. Please make sure that the incident is Resolved and the following " +
					"fields are updated with appropriate values: Resolution, Incident End, Corrective Actions Taken & Cause!");
		}

		Map<String , List<String>> fieldMap = getJiraFieldValues(issue);

		Status status = issue.getStatusObject();
		logger.info("--------------------status: "+ status.getName());
		Project p = issue.getProjectObject();
		OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		IssueType issueType = issue.getIssueTypeObject();
		CustomField solutionGroupCF = cfm.getCustomFieldObjectByName("Solution Groups - Products");

        List solutionGroupValues = getMultiLevelCascadingSelectValue(issue, solutionGroupCF);
		String solutionGroupValue = "";
		if (solutionGroupValues.size() >=1 ) {
			solutionGroupValue = (String)getMultiLevelCascadingSelectValue(issue, solutionGroupCF).get(0);
		}

		logger.debug("Solution Group Checking for mgmt bridge " + solutionGroupValue);
		String hipChatRoomURL = "";

		//Management room details only if management bridge is selected
		/*if (confBridgeType.equals("Management")) {
			hipChatRoomURL = getManagementHipChatDetails(solutionGroupValue);
		} else { //If technical or default technical (in case of internal client type)
			hipChatRoomURL = setHipChatLinkForTechnicalBridge(technicalhipchatroomno, hipChatServerURL);
		}*/

		//logger.info("----------------HipChat Room URLs sent to xMatters are " + hipChatRoomURL);

		String outageType = getOutageType(confBridge,nonConfBridge,internalConfBridge,internalNonConfBridge);
		String isConference= isConferenceBridge(confBridge,nonConfBridge,internalConfBridge,internalNonConfBridge);

		// 1. Validation on Mandatory field Product Category, formerly Product
		if(outageType.equalsIgnoreCase("External")){
			List<String> solutionGroupMapList = fieldMap.get("Solution Groups - Products");
			int sizeOfSolutionGpMap = solutionGroupMapList.size();
			//if(sizeOfSolutionGpMap==2 && solutionGroupMapList.get(1).toString().equals("")){
			if (sizeOfSolutionGpMap == 0 ) {
				return setErrorMessage(issue, "Solution Group is mandatory to create xMatters event!");
			}
			if (sizeOfSolutionGpMap == 1) {
				return setErrorMessage(issue, "Product is mandatory to create xMatters event!");
			}
		}
		// 2. Validation on Mandatory field xMatters Event Status 
		LazyLoadedOption xMattersEventStatus = (LazyLoadedOption)issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("xMatters Event Status"));
		if(xMattersEventStatus!=null && xMattersEventStatus.getOptionId()!=null ){
			Option  xMEventStatus= optionsManager.findByOptionId(xMattersEventStatus.getOptionId());
			if(xMEventStatus!=null && xMEventStatus.getValue()!=null){
				eventStatus = xMEventStatus.getValue();
				logger.info("xMattersEventStatus: "+ xMEventStatus.getValue());
			} else {
				return setErrorMessage(issue, "xMatters Event Status is mandatory to create xMatters event!");
			}
		} else {
			return setErrorMessage(issue, "xMatters Event Status is mandatory to create xMatters event!");
		}
		// 2. Validation on Mandatory field xMatters Description 
		String xMattersDesc = (String)issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("xMatters Description"));
		if( (xMattersDesc==null) || (xMattersDesc!=null && xMattersDesc.trim().equals("null"))){
			return setErrorMessage(issue, "xMatters Description is mandatory to create xMatters event!");
		}
		// Create 
		xMatterRestAPIModel restApiModel = null;
		try{
			restApiModel = createOutageIncident(authtoken, fieldMap, outageType, isConference, issue, restCallCounter,
					totalRestCalls,confBridgeType, firstRowAdded, hipChatRoomURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(restApiModel).build();

	}

	private boolean xMattersEventStatusContainsResolvedAndMandatoryFieldsNotFilled(Issue issue) {
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		CustomField xMattersEventStatusCF = cfm.getCustomFieldObjectByName("xMatters Event Status");
		String xMattersEventStatus = getSingleSelectValue(issue, xMattersEventStatusCF).size() == 0 ? "" :
				getSingleSelectValue(issue, xMattersEventStatusCF).get(0) ;
		if (isNotBlank(xMattersEventStatus) &&
			(xMattersEventStatus.equals("Resolved") || xMattersEventStatus.equals("New/Resolved"))) {
			Resolution resolution = issue.getResolutionObject();
			CustomField incidentEndCF = cfm.getCustomFieldObjectByName("Incident End");
			CustomField correctiveActionsTakenCF = cfm.getCustomFieldObjectByName("Corrective Actions Taken");
			CustomField causeCF = cfm.getCustomFieldObjectByName("Cause");

			String resolutionValue = resolution!=null ? resolution.getName() : "";
			logger.info("-------------Resolution value is " + resolutionValue);
			String incidentEnd = getDateTimePickerValue(issue, incidentEndCF).get(0);
			logger.info("---------------Incident End Value is " + incidentEnd);
			String correctiveActionsTaken = (String)issue.getCustomFieldValue(correctiveActionsTakenCF);
			logger.info("-----------Corrective Actions Taken Value " + correctiveActionsTaken);
			String cause = getSingleSelectValue(issue, causeCF).size() == 0 ? "" : getSingleSelectValue(issue, causeCF).get(0);
			logger.info("----------------Cause value is " + cause);

			if (isBlank(resolutionValue) || isBlank(incidentEnd) || isBlank(correctiveActionsTaken) ||
					isBlank(correctiveActionsTaken)) {
				logger.info("-----------------Cannot create xmatters resolved or new/resolved event as the mandatory fields are not filled");
				return true;
			}
		}
		return false;
	}


	public String setHipChatLinkForTechnicalBridge(int technicalHipChatRoomNumber, String hipChatServerURL) {

		StringBuffer hipChatTechnicalRoomURL = new StringBuffer();

		if (technicalHipChatRoomNumber == 0) {
			hipChatTechnicalRoomURL.append("No Technical HipChat Room Created");
		} else {
			hipChatTechnicalRoomURL.append("<a href='")
					.append(hipChatServerURL)
					.append("/chat/room/")
					.append(technicalHipChatRoomNumber)
					.append("'>Join Technical HipChat Room </a><br/>");
		}

		return hipChatTechnicalRoomURL.toString();
	}


	/*private String getManagementHipChatDetails(String solutionGroupValue) {
		String hipChatRoomURLMgt ="";
		if (isBlank(solutionGroupValue)) {
			hipChatRoomURLMgt = "No Management HipChat Room Created , since solution line is not selected in the incident";
		} else {
			MgtHipChatBean mgtHipChatBean = new MgtHipChatBean();
			mgtHipChatBean.setSolutionLine(solutionGroupValue);
			if (mgtHipChatService.getMgtHipChatSetting(mgtHipChatBean)!=null) {
				hipChatRoomURLMgt = mgtHipChatService.getMgtHipChatSetting(mgtHipChatBean).getHipChatUrl();
			}
			if (isBlank(hipChatRoomURLMgt)) {
				hipChatRoomURLMgt = "No Management HipChat Room Created";
			} else {
				hipChatRoomURLMgt = "<a href='" + mgtHipChatService.getMgtHipChatSetting(mgtHipChatBean).getHipChatUrl()
						+ "'>Join Management HipChat Room </a>";
			}
		}
		return hipChatRoomURLMgt;
	}*/

	private String getOutageType(String externalConf,String externalNonConf,String internalConf, String internalNonConf){

		if(externalConf.equalsIgnoreCase("true") || externalNonConf.equalsIgnoreCase("true"))
			return OUTAGE_TYPE_EXTERNAL_SYSTEM;
		if(internalConf.equalsIgnoreCase("true") || internalNonConf.equalsIgnoreCase("true"))
			return OUTAGE_TYPE_INTERNAL_SYSTEM;
		return OUTAGE_TYPE_EXTERNAL_SYSTEM;
	}

	private String isConferenceBridge(String externalConf,String externalNonConf,String internalConf, String internalNonConf){
		String flag = "false";
		if(externalConf.equalsIgnoreCase("true") || internalConf.equalsIgnoreCase("true")){
			flag = "true";
		}
		return flag;
	}
	public String getTokenId() {
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64(userpwd.getBytes());
		String sid = new String(encoded);
		return sid;
	}
	private HttpPost getPostObject(String isConf, String outageType){
		HttpPost post = null;
		if(isConf.equals("true") && outageType.equals("External")){
			post = new HttpPost(getOutageUrl());
			logger.info("-------------getOutageUrl() "+ getOutageUrl());
		} else if(isConf.equals("true") && outageType.equals("Internal")){
			post = new HttpPost(getXmattersinternalOutUrl());
			logger.info("--------------getXmattersinternalOutUrl() "+ getXmattersinternalOutUrl());
		} else if(isConf.equals("false") && outageType.equals("External")){
			post = new HttpPost(getNonoutageUrl());
			logger.info("-------------getNonoutageUrl() "+ getNonoutageUrl());
		} else {
			post = new HttpPost(getXmattersinternalNonOutUrl());
			logger.info("----------------getXmattersinternalNonOutUrl() "+ getXmattersinternalNonOutUrl());
		}
		return post;
	}
	private void setReindex(MutableIssue mutableIssue){
		try {
			//ComponentAccessor.getIssueIndexManager.reIndex(mutableIssue);
			ComponentManager.getComponent(IssueIndexManager.class).reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.info("----------------------index issue" + ie.getMessage());
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
			String bridgeType = "";
			Element row = rowIterator.next();
			Elements cols = row.select("td");

			String date = cols.get(0).text();
			String desc = cols.get(1).text();
			String eventId = cols.get(2).text();
			String eventstatus = cols.get(3).text();
			String priority = cols.get(4).text();
			if(cols.size()>5) {
				bridgeType = cols.get(5) != null ? cols.get(5).text() : "";
			}

			xMatterValidation validation = new xMatterValidation();
			validation.setDate(date);
			validation.setDesc(desc);
			validation.setEventId(eventId);
			validation.setEventStatus(eventstatus);
			validation.setPriority(priority);
			validation.setBridgeType(bridgeType);
			tableList.add(validation);
		}
		return tableList;
	}

	private void sendMailNotification(String errorMsg){
		try{
			logger.info("----------------Send Mail Notification: "+errorMsg);
			MailServerManager mailServerManager = ComponentAccessor
					.getMailServerManager();
			SMTPMailServer mailServer = mailServerManager
					.getDefaultSMTPMailServer();

			Properties p=new Properties();

			InputStream is = getClass().getClassLoader().getResourceAsStream("xMatters-integration-plugin.properties");

			if (is!=null)
			{
				p.load(is);
			}
			//String emailProperty = p.getProperty("jira.admin.mailid");
			String emailProperty = getJiraAdminEmailids();
			Email email = new Email(
					emailProperty);
			email.setFrom(mailServer.getDefaultFrom());
			email.setSubject("xMatters integration failed");
			email.setMimeType("text/html");
			email.setBody(errorMsg);
			SingleMailQueueItem item = new SingleMailQueueItem(email);
			ComponentAccessor.getMailQueue().addItem(item);
		}
		catch(Exception ex){
		logger.info("---------------- error occurs in Send Mail Notification: "+ex.getMessage());
			//ex.printStackTrace();
		}
	}
	private String appendHeaders(){
		StringBuffer headers = new StringBuffer();
		String columns = "Date Time,Description,Event Id,Event Status,Severity,Bridge Type";
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
		if(htmlStr!=null && !htmlStr.equals("") && htmlStr.indexOf("<thead>")>0){
			htmlStr = htmlStr.substring(htmlStr.indexOf("<tbody>"),htmlStr.indexOf("</tbody>"));
		} else if( htmlStr!=null &&  htmlStr.indexOf("data") >0 ) {
			htmlStr="";
		} else if(htmlStr == null){
		    htmlStr="";
		}
		return htmlStr;
	}


	public String getXmattersCommunicationLog(String existingLog){
		StringBuffer logBuffer = new StringBuffer();
		if(existingLog!=null && !existingLog.equals("")){
			//if(!existingLog.equals("<html></html>")){
				List dataList = parseHTMLTableData(existingLog);
				Iterator<xMatterValidation> iterator = dataList.iterator();

				//Will be used to filter out some messages as seen toward the end of this method
				List<String> rowLogs = new ArrayList<>();

				while(iterator.hasNext()){
					xMatterValidation validation = iterator.next();
					TimeZone timeZone = Calendar.getInstance().getTimeZone();
					String rowLog = "<b>@"+getTime(validation.getDate()) + " " + timeZone.getDisplayName(false, TimeZone.SHORT) + "</b>> "+validation.getDesc();
					rowLogs.add(rowLog);
				}

				/*If the message length is > 1999, then we send as much as possible rows from
				bottom up till the length becomes 1999. The moment the length becomes 1999,
				we wont send the row at that iteration.*/
				ListIterator<String> li = rowLogs.listIterator(rowLogs.size());
				// Iterate in reverse.
				while(li.hasPrevious()) {
					String previous = li.previous();
					logger.info("-----------PREVIOUS BUFFER " + previous);
					if (logBuffer.length() + previous.length() <= 1999) {
						logBuffer.append(previous+"<br/>");
					}
					logger.info("--------------LOGGER BUFER IS " + logBuffer);
				}

			//}
			String logString = logBuffer.toString().trim();
			if (logString.endsWith("<br/>")) {
				logString = removeEnd(logString, "<br/>");
			}
			logger.info("--------------------LOGGER BUFFER IN THE END IS " + logString);
			return logString;
		}
		return "";
	}

	public String removeEnd(String str, String remove) {
		if (isEmpty(str) || isEmpty(remove)) {
			return str;
		}
		if (str.endsWith(remove)) {
			return str.substring(0, str.length() - remove.length());
		}
		return str;
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

	public String getOutageUrl() {
		return outageUrl;
	}

	public void setOutageUrl(String outageUrl) {
		this.outageUrl = outageUrl;
	}

	public String getNonoutageUrl() {
		return nonoutageUrl;
	}

	public void setNonoutageUrl(String nonoutageUrl) {
		this.nonoutageUrl = nonoutageUrl;
	}

	public String getXmattersinternalOutUrl() {
		return xmattersinternalOutUrl;
	}

	public void setXmattersinternalOutUrl(String xmattersinternalOutUrl) {
		this.xmattersinternalOutUrl = xmattersinternalOutUrl;
	}

	public String getXmattersinternalNonOutUrl() {
		return xmattersinternalNonOutUrl;
	}

	public void setXmattersinternalNonOutUrl(String xmattersinternalNonOutUrl) {
		this.xmattersinternalNonOutUrl = xmattersinternalNonOutUrl;
	}

	public String getXmattersExternalResponse1() {
		return xmattersExternalResponse1;
	}

	public void setXmattersExternalResponse1(String xmattersExternalResponse1) {
		this.xmattersExternalResponse1 = xmattersExternalResponse1;
	}

	public String getXmattersExternalResponse2() {
		return xmattersExternalResponse2;
	}

	public void setXmattersExternalResponse2(String xmattersExternalResponse2) {
		this.xmattersExternalResponse2 = xmattersExternalResponse2;
	}

	public String getXmattersInternalResponse1() {
		return xmattersInternalResponse1;
	}

	public void setXmattersInternalResponse1(String xmattersInternalResponse1) {
		this.xmattersInternalResponse1 = xmattersInternalResponse1;
	}

	public String getXmattersInternalResponse2() {
		return xmattersInternalResponse2;
	}

	public void setXmattersInternalResponse2(String xmattersInternalResponse2) {
		this.xmattersInternalResponse2 = xmattersInternalResponse2;
	}

	public String getJiraAdminEmailids() {
		return jiraAdminEmailids;
	}

	public void setJiraAdminEmailids(String jiraAdminEmailids) {
		this.jiraAdminEmailids = jiraAdminEmailids;
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
				logger.info("----------------Field Type is " + fieldType);

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

				} else if (fieldType.contains("Number")) {
					values = getNumberFieldValue(issue, customField);
					jiraFieldValues.put(fieldName, values);

				}else {
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
				logger.info("--------------------------Value not found for " + fieldName + " - setting defaults");
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
				logger.info("-----------------Severity : "+ priorityName);
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
				if(opt.getKey() ==  null ){ // for Solution Group				
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
	 * Helper method to deal with multilevel cascade field. Note, as of now, only the Solution Groups - Products
	 * is a multilevel cascade field
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for multiselect cascade field
	 */
	private List getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

		if (valuesList != null) {
			for (Object value : valuesList) {
				values.add(value.toString());
			}

			/*//For now if by any chance the elements are present at third and fourth level of cascade, then remove them.
			if (values.size() > 2) {
				for (int i = 2 ; i < values.size() ; i++) {
					values.remove(i);
				}
			}*/
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

	/**
	 * Helper method to deal with the number field
	 * @param issue
	 * @param customField
     * @return list of values for Number field, out of which we need to pick first value
     */
	private List getNumberFieldValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		Double numberValue = null;
		if (issue.getCustomFieldValue(customField) == null) {
			numberValue = 0.0;
		} else {
			numberValue = (Double)issue.getCustomFieldValue(customField);
		}

		if (customField.getName().equals(NUMBER_OF_CUSTOMER_CALLS_CUSTOM_FIELD_NAME)) {
			Integer numberValueInt = numberValue.intValue();
			String value = numberValueInt.toString();
			logger.info("----------------------Number field value for Number of Customer Calls is  " + value);
			values.add(value);
		} else {
			String value = numberValue.toString();
			logger.info("------------------------Number field value for the field " + customField.getName()+ " is " + value);
			values.add(value);
		}

		return values;
	}



	/**
	 * Constructs the JSON String for Non Conference, something like the following example
	 *
	 *	{
	 "properties": {
	 "External Service": ["Canada SFI","Partner"],
	 "Incident Type": "External",
	 "Status": "Update",
	 "Severity": "Medium",
	 "Incident Duration": "84 Minutes",
	 "Incident ID": "ITIM-3825",
	 "Description": "test 100",
	 "xMatters Communication Log": "@2:18:00 PM EST> test 100\n\n"
	 },
	 "recipients":   [{
	 "targetName": getUserName()
	 }]
	 }
	 *
	 * @param fieldMap
	 * @param outageType
	 *
	 * @return the JSON payload as a string
	 */
	private String returnJSONPayLoadForNonConference(Map<String, List<String>> fieldMap,
													 String outageType, String issueKey, String hipChatRoomURL) {

		List<FieldBean> mappingsFromAO = fieldMapperService.getAllMappingsFromDB();

		StringBuilder payload = new StringBuilder("{ \n\"properties\": {");

		//Get all the xmatters fields names and their corresponding JIRA mappings
		addXMattersMappingsInJSON(fieldMap, mappingsFromAO, payload, outageType, issueKey);

		// Add information in the JSON for the hipchat room
		payload.append(",\"HipChat Room URL\" :" + "\"" + hipChatRoomURL + "\"");

		payload.append("\n},"); //closing brace for properties' values

		//Now for another key to be added to the payload i.e. the recipients key and its values
		payload.append("\n\"recipients\": [{\n\"targetName\":" + "\"" + getUserName() + "\"\n}]");

		//Finally the last closing brace for the whole payload - the JSON is now ready to be sent!
		payload.append("}");
		logger.info("------------------------------Payload JSON for Non Conference is " + payload.toString());

		return payload.toString();
	}

	/**
	 * Helper method that Constructs a JSON String for Conference, something like the following example
	 *
	 * {
	 "properties": {
	 "External Service": ["",""],
	 "Incident Type": "External",
	 "Status": "Update",
	 "Severity": "Medium",
	 "Incident ID": "ITIM-3825",
	 "Description": "test 100",
	 "xMatters Communication Log": "@2:18:00 PM EST> test 100\n\n",
	 "Conference Bridge Type": "Management"
	 },
	 "conferences":   [{
	 "name": null
	 }],
	 "responses":   ["892dddff-fcfe-4c00-8796-8703de5fc758","f3a706b2-b4eb-4a40-9826-270f75806393" ],
	 "recipients":   [{
	 "targetName": "JIRATest"
	 }]
	 }
	 *
	 * @param fieldMap
	 * @param outageType
	 * @return the JSON payload as a string
	 */
	private String returnJSONPayLoadForConference(Map<String, List<String>> fieldMap,
												  String outageType, String issueKey, String hipChatRoomURL) {

		List<FieldBean> mappingsFromAO = fieldMapperService.getAllMappingsFromDB();

		StringBuilder payload = new StringBuilder("{ \n\"properties\": {");

		//Get all the xmatters fields names and their corresponding JIRA mappings
		addXMattersMappingsInJSON(fieldMap, mappingsFromAO, payload, outageType, issueKey);

		// Add information in the JSON for the hipchat room
		payload.append(",\"HipChat Room URL\" :" + "\"" + hipChatRoomURL + "\"");

		payload.append("\n},"); //closing brace for properties' values

		//Add conferences mappings to JSON
		StringBuilder outageBu = new StringBuilder()
				.append( "\n\"conferences\":   [")
				.append("{ \n")
				.append("\"name\": null \n")
				.append( "}" )
				.append("],");
		payload.append(outageBu);

		//Add responses mapping to JSON
		addResponsesMappingToJSON(payload, outageType);

		//Now for another key to be added to the payload i.e. the recipients key and its values
		payload.append("\n\"recipients\": [{\n\"targetName\":" + "\"" + getUserName() + "\"\n}]");

		//Finally the last closing brace for the whole payload - the JSON is now ready to be sent!
		payload.append("}");

		logger.info("----------------------------Payload JSON for Conference is " + payload.toString());

		return payload.toString();
	}

	/**
	 * Helper method used in JSON payload for conference to add Responses mapping.T
	 * The "responses" key's values are decided based on the outage type.
	 *
	 * @param payload
	 */
	private void addResponsesMappingToJSON(StringBuilder payload, String outageType) {
		//NOTE outage type == incident type
		//Get the incident type to determine the responses' values
		StringBuilder responses = new StringBuilder();

		// Validating Internal conf bridge or external  conference bridge
		if(outageType.equalsIgnoreCase("Internal")){
			responses.append( " \"responses\":   [")
					.append("\""+ getXmattersInternalResponse1()+"\",")
					.append("\""+ getXmattersInternalResponse2()+"\" ")
					.append( "],");
		} else {
			responses.append( " \"responses\":   [")
					.append("\""+ getXmattersExternalResponse1()+"\",")
					.append("\""+ getXmattersExternalResponse2()+"\" ")
					.append("],");
		}
		payload.append(responses);
	}


	/**
	 * Helper method to add the xMatterFieldName -> JIRA field value mappings to the JSON Payload
	 * This is used by both conference and non conference events.
	 *  @param fieldMap
	 * @param mappingsFromAO
	 * @param payload
	 * @param outageType
	 */
	private void addXMattersMappingsInJSON(Map<String, List<String>> fieldMap, List<FieldBean> mappingsFromAO,
										   StringBuilder payload, String outageType, String issueKey) {

		//First of all, we need the Mandatory xMatters Field called Incident ID to be mapped to JIRA Issue Key.
		//We do this ourselves, because we cant guarantee if the admin has set this mapping or not.
		payload.append("\"Incident ID\": " + "\""+issueKey+"\"");

		if (!mappingsFromAO.isEmpty()) payload.append(",");//This means more mappings to come from AO ...

		//From hereon, till the end of this method, we add other mapping fields
		String outerComma = ",";
		for (FieldBean fieldBean : mappingsFromAO) {
			String xmatterFieldName = fieldBean.getxMattersFieldName();

			//Get the list of field values for a particular JIRA field in the mapping
			List<String> jiraFieldValues = fieldMap.get(fieldBean.getJiraFieldName());

			//Then see what is the actual value of the mapped JIRA field. It can either be a single value or cascade
			//In case of single value, the arraylist in the map will be of size 1, else for cascade value,
			//the list will be of size > 1.
			if (jiraFieldValues !=null && jiraFieldValues.size() == 1) {
				//Already configured in first line, and we dont want duplicate keys - so skip the iteration for this key
				if (xmatterFieldName.equals("Incident ID")) continue;

				//Then ,first of all, map the xmatters field name in as a sub-key within properties
				payload.append("\""+xmatterFieldName+"\":");

				//One special case of xmatters communication log - value needs to be massaged , before sending
				if (xmatterFieldName.equals("xMatters Communication Log")) {
					//xMatters Communication Log : getXmattersCommunicationLog(jirafield)
					payload.append("\"" + getXmattersCommunicationLog(jiraFieldValues.get(0))+ "\"");
					payload.append(outerComma);
					continue;
				}

				if (xmatterFieldName.equals("Incident Duration")) {
					logger.info(jiraFieldValues.get(0) + " ----------------------is converted to " + convertToDaysHrsMins(jiraFieldValues.get(0)));
					payload.append("\"" + convertToDaysHrsMins(jiraFieldValues.get(0))+ "\"");
					payload.append(outerComma);
					continue;
				}


				//Easiest case, just get the list element and add it as the value in JSON payload!
				payload.append("\"" + jiraFieldValues.get(0)+ "\"");
				payload.append(outerComma);
				//outerComma = ",";

			} else {
				//exclude json for internal service  if  incident type is external  
				if (outageType.equalsIgnoreCase("External") && xmatterFieldName.equalsIgnoreCase("Internal Service")) {
					continue;
				}
				//exclude json for external service  if  incident type is Internal  
				if (outageType.equalsIgnoreCase("Internal") && xmatterFieldName.equalsIgnoreCase("External Service")) {
					continue;
				}
				//Special Case for constructing JSON for XMatters Internal Service field, if present
				if (xmatterFieldName.equalsIgnoreCase("Internal Service")) {
					//Then ,first of all, map the xmatters field name in as a sub-key within properties
					payload.append("\""+xmatterFieldName+"\":");
					//"Internal Service" : Type[1], Impacted - Function[0]
					payload.append("[");

					List<String> jiraTypeFieldList = fieldMap.get("Clients Impacted");
					String secondFieldOfJIRAType = (jiraTypeFieldList==null || jiraTypeFieldList.isEmpty()) ? "" :
							jiraTypeFieldList.get(1);
                    logger.info("1368--------------The value of second field of jira type clients impacted is: "+secondFieldOfJIRAType+"---------------");
					List<String> impactedFunctionsFieldList = fieldMap.get("Impacted - Function");
					String firstFieldOfImpactedFunction = (impactedFunctionsFieldList==null ||
							impactedFunctionsFieldList.isEmpty()) ? "" :
							impactedFunctionsFieldList.get(0);

					payload.append("\""+secondFieldOfJIRAType+"\",\""+firstFieldOfImpactedFunction+"\"]");
					payload.append(outerComma);
logger.info("----------------------------------------------------Internal Service--------------------------------------------");
logger.info(payload);
logger.info("-----------------------------------------------------------------------Internal Service ends----------------");
					continue;
				}

				//Special Case for constructing JSON for XMatters Incident Type Field, if present
				if (xmatterFieldName.equals("Incident Type")) {
					//Then ,first of all, map the xmatters field name in as a sub-key within properties
					payload.append("\""+xmatterFieldName+"\":");
					//"Incident Type" : Type[0] OR outagetype
					payload.append("\""+outageType+"\"");
					payload.append(outerComma);
					//outerComma = ",";
					continue;
				}

				//Then ,first of all, map the xmatters field name in as a sub-key within properties
				payload.append("\""+xmatterFieldName+"\":");
				// if none of the special cases above, then iterate over every value in cascade field
				// and add it to JSON array
				putAllCascadeFieldsInJSONArray(payload, jiraFieldValues);
				payload.append(outerComma);				
				//outerComma = ",";
			}
		}
		if(payload.lastIndexOf(",") > -1 ) {
			payload.deleteCharAt(payload.length() - 1); // remove extra comma from payload
		}
	}

	/**
	 * Method that puts all cascade JIRA fields in a JSON Array and returns the modified payload
	 *
	 * @param payload
	 * @param jiraFieldValues
	 */
	private void putAllCascadeFieldsInJSONArray(StringBuilder payload, List<String> jiraFieldValues) {
		if(jiraFieldValues!=null){
			payload.append("[");
			String comma = "";
				for (String jiraFieldValue : jiraFieldValues) {
					payload.append(comma);
					comma = ",";
					payload.append("\"");
					payload.append(jiraFieldValue);
					payload.append("\"");
				}
			payload.append("]");
			} else {
			    payload.append("\"");
				payload.append("");
				payload.append("\"");
			}			
	}

	private Response setErrorMessage(Issue issue, String errorMsg){
		String statusErr =  "Validation Error";
		return 	Response.ok(new xMatterRestAPIModel(issue.getKey(),statusErr,errorMsg)).build();
	}

	private boolean duplicateXMattersEventStatusForExtConference(Map<String, List<String>>  fieldMap,Issue issue,
																 String firstRowAdded){

		String existingLog =(String) issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager()
				.getCustomFieldObjectByName("XMatters Log"));
		String eventstatus  = "";
		int newCounter = 0;
		List eventstatusValList = (List)fieldMap.get("xMatters Event Status");
		if(eventstatusValList != null )
			eventstatus = eventstatusValList.get(0).toString();
		try{
			if(existingLog!=null && !existingLog.equals("")){
				//if(!existingLog.equals("<html></html>")){
					List dataList = parseHTMLTableData(existingLog);
					Iterator<xMatterValidation> iterator = dataList.iterator();
					boolean isEventStatusNew = false;
					while(iterator.hasNext()){
						xMatterValidation validation = iterator.next();
						String validateEventStatus = validation.getEventStatus();
						if(validateEventStatus.equals("New") || validateEventStatus.equals("New/Resolved") ){
							newCounter++;
						}
					}
				//}
				if(((eventstatus.equals("New")) || eventstatus.equals("New/Resolved")) && newCounter >= 1){
					if (firstRowAdded.equals("true")) {
						logger.info("First row is added, so I am going ahead and adding second row");
						return false;
					}
					return true;
				}
			}
		} catch(Exception e){
			logger.info("Exception due to duplicate xMatters Event Status");
			sendMailNotification(e.getMessage() + " " + issue.getKey());
			//e.printStackTrace();
			logger.info("Exception due to duplicate xMatters Event Status" + e.getMessage());
		}
		return false;
	}


	private boolean duplicateXMattersEventStatus(Map<String, List<String>>  fieldMap,Issue issue, String confBridgeType){
		String existingLog =(String) issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager()
				.getCustomFieldObjectByName("XMatters Log"));
		logger.info("****** Conference Bridge "+ confBridgeType);

		if(confBridgeType!=null && confBridgeType.equals("")){
			confBridgeType = BRIDGE_TYPE_DEFAULT_VALUE_CLIENT_IMPACTED_INTERANL;
		}
		String eventstatus  = "";
		List eventstatusValList = (List)fieldMap.get("xMatters Event Status");
		if(eventstatusValList != null )
			eventstatus = eventstatusValList.get(0).toString();
		try{
			if(existingLog!=null && !existingLog.equals("")){
				//if(!existingLog.equals("<html></html>")){
					List dataList = parseHTMLTableData(existingLog);
					Iterator<xMatterValidation> iterator = dataList.iterator();
					boolean isEventStatusNew = false;
					while(iterator.hasNext()) {
						xMatterValidation validation = iterator.next();
						String validateEventStatus = validation.getEventStatus();
						String bridgeType = validation.getBridgeType();
						if (validateEventStatus.equals("New") || validateEventStatus.equals("New/Resolved")) {
							if (eventstatus.equals("New") || eventstatus.equals("New/Resolved")) {
							/* Bridge Type column is added and validation on based on BridgeType
							* Include a new column as highlighted below in the xMatters log table which
							* will be used to identify whether the xmatters event was created for management or technical bridge. To do this
							* the best way to identify is to record the value in one more additional column called "Bridge Type".
							*/
							if ((bridgeType.equalsIgnoreCase(confBridgeType) && eventstatus.equals("New")) || (bridgeType.equalsIgnoreCase(confBridgeType) && eventstatus.equals("New/Resolved"))) {
								return true;
							}
							}
						}
					}
			}
		} catch(Exception e){
			logger.info("---------------------------Exception due to duplicate xMatters Event Status");
			sendMailNotification(e.getMessage() + " " + issue.getKey());
			logger.info("------------------Exception due to duplicate xMatters Event Status" + e.getMessage());
			//e.printStackTrace();
		}
		return false;
	}
	// Update the xMatters Log table 
	private xMatterRestAPIModel updatexMattersLogTable(HttpResponse response,Issue issue,Map<String, List<String>>  fieldMap,
													   String payload, int restCallCounter, int totalRestCalls,String confBridgeType ){
		xMatterRestAPIModel apiModel = new xMatterRestAPIModel();
		MutableIssue mutableIssue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(issue.getKey());
		InputStream inputStream = null;
		String eventId = "";
		String line = "";
		List descList = fieldMap.get("xMatters Description");
		String desc = descList.get(0).toString();
		//desc = desc.replace("\"", "\\\"");
		logger.info("--------------------xMatters Description: "+desc);
		List priorityListVal = fieldMap.get("Severity");
		String priority = priorityListVal.get(0).toString();
		List xMattersEventStatusList = fieldMap.get("xMatters Event Status");
		String eventstatus = xMattersEventStatusList.get(0).toString();
		CustomField xMattersLogCF =  ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("XMatters Log");
		String existingLog = (String)issue.getCustomFieldValue(xMattersLogCF);
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();

		try{
			inputStream = response.getEntity().getContent();
			byte[] responseBody =  IOUtils.toByteArray(inputStream);
			if(responseBody!=null && responseBody.length > 0){

				logger.info("xMatters response body: ");

				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(responseBody);
				JsonNode typeNode = rootNode.get("type");
				if(typeNode==null){
					JsonNode idNode = rootNode.get("id");
					eventId = idNode.asText();
					logger.info("---------------eventId: "+eventId);
					line = eventId;
					apiModel.setEventId(eventId);
					apiModel.setStatus("Validation Success");
					apiModel.setMessage("xMatters Event successfully created!");

					Map<String, ModifiedValue> modifiedFields = null;
					FieldLayoutItem fieldLayoutItem = null;
					ModifiedValue modifiedValue = null;
					Date currentDate = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa");
					String strDate = dateFormat.format(currentDate);
					StringBuilder xMattersResponse = new StringBuilder(appendHeaders());
					String _existingLog = appendExistingLog(existingLog);
					xMattersResponse.append(_existingLog);
					xMattersResponse.append("<tbody><tr>")
							.append("<td style='min-width:100px'>").append(strDate).append("</td>")
							.append("<td>").append(StringEscapeUtils.escapeHtml3(desc)).append("</td>")
							.append("<td>").append(eventId).append("</td>")
							.append("<td>").append(eventstatus).append("</td>")
							.append("<td>").append(priority).append("</td>")
							.append("<td>").append(confBridgeType).append("</td>")
							.append("</tr>")
							.append("</tbody>")
							.append("</table>");

					String xMattersLog = xMattersResponse.toString();
					if(xMattersLog!=null && xMattersLog.length()>0){
						mutableIssue.setCustomFieldValue(xMattersLogCF, xMattersLog);
						modifiedFields = mutableIssue.getModifiedFields();
						fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(xMattersLogCF);
						modifiedValue = modifiedFields.get(xMattersLogCF.getId());
						xMattersLogCF.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
						setReindex(mutableIssue);
					}
					// Copy the xMatterLog info from incident to problem whenever any changes on xMattersLog file on Incident 
					updateXMattersLogProblem(issue,xMattersLog,xMattersLogCF);
					
					if (restCallCounter == (totalRestCalls - 1) ) {
						logger.info("--------------------Clearing xMatters description from the issue");
						//Clear the xMatters Description field in the JIRA ticket
						clearXMattersDescriptionFieldFromIncident(issue);
					}

				} else {
					logger.info("-----------------Error in response\n");
					JsonNode messageNode;
					StringBuffer errorMsg = new StringBuffer();
					if(rootNode.has("message")){
						messageNode = rootNode.get("message");
						errorMsg.append(" message: " + messageNode.toString());
					}
					if(rootNode.has("errorDetails")){
						messageNode = rootNode.get("errorDetails");
						errorMsg.append(" error: "+messageNode.toString());
					}
					apiModel.setStatus("Validation Error");
					apiModel.setMessage(errorMsg.toString());

					sendMailNotification(errorMsg.toString() + "\n issuekey: " + issue.getKey() +"\n Json String: "+payload);
					return apiModel;
				}

			}
			inputStream.close();
		} catch (Exception e) {
			logger.info("--------------Exception during update");
			apiModel.setStatus("Validation Error");
			apiModel.setMessage( e.getMessage() );
			sendMailNotification(e.getMessage() + "\n issuekey: " + issue.getKey() +"\n Json String: "+payload);
			//e.printStackTrace();
			return apiModel;
		}
		return apiModel;
	}
	/**
	 * Update xMatters Log information from incident to problem when ever changes.
	 * @param issue - Current Issue Object
	 * @param xMattersLog - Html content in String type
	 * @param xMattersLogCF - Customfield Object
	 */
	public void updateXMattersLogProblem(Issue issue,String xMattersLog,CustomField xMattersLogCF){
		DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		Issue childIssue = getProblemTicketLinkedIssue(issue);
		if(childIssue!=null){
			Map<String, ModifiedValue> modifiedFields = null;
			FieldLayoutItem fieldLayoutItem = null;
			ModifiedValue modifiedValue = null;
			MutableIssue mutableIssue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(childIssue.getKey());
			if(xMattersLog!=null && xMattersLog.length()>0){
				mutableIssue.setCustomFieldValue(xMattersLogCF, xMattersLog);
				modifiedFields = mutableIssue.getModifiedFields();
				fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(xMattersLogCF);
				modifiedValue = modifiedFields.get(xMattersLogCF.getId());
				xMattersLogCF.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
				setReindex(mutableIssue);
			}
		}
}
	/**
     * Gets number of Problem Ticket is linked with incident which have same incident key as parent key
     *
     * @param parent the Issue
     * @return the Isssue - linked Issue
     */
    public Issue getProblemTicketLinkedIssue(Issue parent){
    	CustomField incidentKeyCustomField 			= 	ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Incident Key");
	    int count = 0; 
	    Issue childIssue = null;
	    logger.info("**********problem Already Linked To Parent Ticket Count parent issue:" + parent.getKey());
	    LinkCollection linkCollection = ComponentAccessor.getIssueLinkManager().getLinkCollection(parent, ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser());
		Collection<Issue> allLinkedIssues= linkCollection.getAllIssues();
		logger.info("**********all Linked Issues size:" +allLinkedIssues.size());
		if(allLinkedIssues.size()==0){
			logger.info("**********No problem Ticket linked with parent issue");
			count = 0;
			return childIssue;
		} else {
			for(Issue linkedissue : allLinkedIssues){
				logger.info("**********problem Already Linked To Parent Ticket Count linked issue:" + linkedissue.getKey());
				logger.info("**********problem Already Linked To Parent Ticket Count incidentKeyCustomField:" + linkedissue.getCustomFieldValue(incidentKeyCustomField));
				if(linkedissue.getIssueTypeObject().getName().equalsIgnoreCase("Problem") 
						&& linkedissue.getCustomFieldValue(incidentKeyCustomField)!=null 
						&& !linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("") 
						&& linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase(parent.getKey())){
					logger.info("**********linked issue:" + linkedissue.getKey() +" **********having same incident key:" +linkedissue.getCustomFieldValue(incidentKeyCustomField).toString() +" **********as its parent key:" +parent.getKey());
					childIssue = linkedissue;
					    count++;
				}
			}
			
		}
		if(count>0) {
				return childIssue;
			}
		return childIssue;
    }
	/**
	 * Helper method to create an XMatters Event
	 *
	 * @param authtoken
	 * @param fieldMap
	 * @param outageType
	 * @param isConf
	 * @param issue
	 * @return xMatterRestAPIModel with incident details
	 */
	private xMatterRestAPIModel createOutageIncident(String authtoken, Map<String, List<String>> fieldMap, String outageType,
													 String isConf, Issue issue, int restCallCounter, int totalRestCalls,
													 String confBridgeType, String firstRowAdded, String hipChatRoomURL){
		xMatterRestAPIModel apiModel = new xMatterRestAPIModel();
		String issueKey = issue.getKey();
		apiModel.setIssueKey(issueKey);

		// Create an instance of HttpClient.
		HttpClient defaultHttpClient = HttpClientBuilder.create().build();
		HttpPost post = getPostObject(isConf, outageType);
		// set headers
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "Basic " + authtoken);

		// Event can be created only once with New/New or Resolved.

		/*boolean isDuplicate  = duplicateXMattersEventStatus(fieldMap, issue);
		if(isDuplicate){
			String statusErr =  "Validation Error";
			apiModel.setIssueKey(issueKey);
			apiModel.setStatus(statusErr);
			apiModel.setMessage("Duplicate Event Status");	
			return apiModel;
		}*/


		//New/Resolved is just not a valid status for external conference or even external non conference
		if ((isConf.equals("true") && outageType.equals("External"))) {
			List eventstatusValList = (List)fieldMap.get("xMatters Event Status");
			if(eventstatusValList != null ) {
				String eventstatus = eventstatusValList.get(0).toString();
				if (eventstatus.equals("New/Resolved")) {
					String statusErr =  "Validation Error";
					apiModel.setIssueKey(issueKey);
					apiModel.setStatus(statusErr);
					apiModel.setMessage("New/Resolved is not a valid event status for Clients Impacted External");
					return apiModel;
				}
			}
		}

		//Special case for external conference and external non conference
		if((isConf.equals("true") && outageType.equals("External") && totalRestCalls== 2) ||
			(isConf.equals("false") && outageType.equals("External") && totalRestCalls== 2)	){
			String externalExistingLog =(String) issue.getCustomFieldValue(ComponentAccessor.getCustomFieldManager()
					.getCustomFieldObjectByName("XMatters Log"));
			if(externalExistingLog==null){
			} else {
				boolean isDuplicate  = duplicateXMattersEventStatusForExtConference(fieldMap, issue, firstRowAdded);
				if(isDuplicate){
					String statusErr =  "Validation Error";
					apiModel.setIssueKey(issueKey);
					apiModel.setStatus(statusErr);
					apiModel.setMessage("Duplicate Event Status");

					return apiModel;
				}
			}
		} else {
// Event can be created only once with New/New or Resolved.
			boolean isDuplicate  = duplicateXMattersEventStatus(fieldMap, issue, confBridgeType);
			if(isDuplicate){
				String statusErr =  "Validation Error";
				apiModel.setIssueKey(issueKey);
				apiModel.setStatus(statusErr);
				apiModel.setMessage("Duplicate Event Status");

				return apiModel;
			}
		}


		String PAYLOAD =  constructJsonStringForXMattersEvent(fieldMap, outageType, isConf, issueKey, confBridgeType, hipChatRoomURL);
		logger.info("SAMPLE PAYLOAD IS \n" + PAYLOAD);

		//Set Bridge Type as Technical for Client impacted = Interanl
		confBridgeType = setBridgeType(outageType,confBridgeType);
        /* create event for xMatters */
		try {
			StringEntity data = new StringEntity( PAYLOAD , "UTF-8" );
			data.setContentType("application/json");
			//set the request body
			post.setEntity( data );
			// Post the request for create the xMatters event
			HttpResponse response = defaultHttpClient.execute(post);
			// Create and update the xMatters Log table
			apiModel = updatexMattersLogTable(response,issue,fieldMap,PAYLOAD, restCallCounter, totalRestCalls,confBridgeType);

		}   catch(Exception e)  {
			logger.info("------------------Exception due to validation error :" + e);
			apiModel.setStatus("Validation Error");
			apiModel.setMessage( e.getMessage() );
			sendMailNotification(e.getMessage() + "\n issuekey: " + issueKey +"\n Json String: "+PAYLOAD);
			//e.printStackTrace();
			return apiModel;
		} finally{
			post.releaseConnection();
		}
		return apiModel;
	}

	/**
	 * Helper method that clears the xmatters description field in the issue, after xMatters event is created
	 * and xmatters log entry is created in the issue
	 *
	 * @param currentissue
	 */
	private void clearXMattersDescriptionFieldFromIncident(Issue currentissue) {
		CustomField xMattersDescriptionField = ComponentAccessor.getCustomFieldManager()
				.getCustomFieldObjectByName("xMatters Description");
		MutableIssue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(currentissue.getKey());

		issue.setCustomFieldValue(xMattersDescriptionField,"");

		try {
			//ComponentAccessor.getIssueIndexManager().reIndex(issue);
			ComponentManager.getComponent(IssueIndexManager.class).reIndex(issue);
		} catch (Exception ie) {
			logger.info("THIS MUTABLE ISSUE COULD NOT BE UPDATED - PLEASE CHECK " + issue.getKey());
			logger.info("Error occurs due to:" + ie.getMessage());
			//ie.printStackTrace();
		}

		String xMattersDescValue = (String) xMattersDescriptionField.getValue(issue);

		FieldLayoutManager fieldLayoutManager = ComponentAccessor.getFieldLayoutManager();
		FieldLayout layout = fieldLayoutManager.getFieldLayout(issue.getProjectObject(), issue.getIssueTypeObject().getId());

		if (layout.getId() == null) {
			layout = fieldLayoutManager.getEditableDefaultFieldLayout();
		}
		FieldLayoutItem fieldLayoutItem =layout.getFieldLayoutItem(xMattersDescriptionField.getId());

		xMattersDescriptionField.updateValue(fieldLayoutItem, issue, new ModifiedValue(xMattersDescValue, ""),
				new DefaultIssueChangeHolder());
		issue.store();
		try {
			//ComponentAccessor.getIssueIndexManager().reIndex(currentissue);
			ComponentManager.getComponent(IssueIndexManager.class).reIndex(currentissue);
		} catch (Exception ie) {
			logger.info("----------THIS ISSUE COULD NOT BE UPDATED - PLEASE CHECK ----------" + currentissue.getKey());
			logger.info("----------ERRROR OCCURS DUE TO ----------" + ie.getMessage());
			//ie.printStackTrace();
		}

	}

	/**
	 * Helper method to construct the JSON depending on whether its conference or not
	 *
	 * @param fieldMap
	 * @param outageType
	 * @param isConf
	 * @return JSON payload as string
	 */
	public String constructJsonStringForXMattersEvent(Map<String, List<String>> fieldMap, String outageType,
													  String isConf, String issueKey, String confBridgeType,
													  String hipChatRoomURL){
		String payload;
		if(isConf.equals("true")){
			payload  = returnJSONPayLoadForConference(fieldMap,outageType, issueKey, hipChatRoomURL);
		} else {
			payload  = returnJSONPayLoadForNonConference(fieldMap,outageType, issueKey, hipChatRoomURL);
		}
		return payload;
	}

	/**
	 *  Set Bridge Type as Technical for Client Impacted Value as Internal
	 * @param outageType  - Client Impacted for Internal
	 * @param confBridgeType -- Bridge Type either Management or Technical
     * @return
     */
	private String setBridgeType(String outageType, String confBridgeType){
		if(outageType.equals("Internal")){
			confBridgeType = BRIDGE_TYPE_DEFAULT_VALUE_CLIENT_IMPACTED_INTERANL;
		}
		return confBridgeType;
	}

}
