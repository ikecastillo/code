package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.mail.Email;
import com.atlassian.jira.project.Project;
import com.atlassian.mail.queue.SingleMailQueueItem;
import com.atlassian.mail.server.MailServerManager;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.LoggerWrapper;
import com.dt.jira.xmatters.intgt.plugin.rest.ConfigResource.Config;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
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
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The resource is used to create a xMatters event on xMatters application.
 */
@Path("/xMatters")
public class xMatterRestAPI {

	public String userpwd; 
	private static String OUTAGE_TYPE_INTERNAL_SYSTEM = "Internal";
	private static String OUTAGE_TYPE_EXTERNAL_SYSTEM = "External";
	private static PluginSettingsFactory pluginSettingsFactory;
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
	public xMatterRestAPI(PluginSettingsFactory psf){
		logger.setInfoLogLevel();
		this.pluginSettingsFactory = psf;
		setConfiguration();
	}
	
	private void setConfiguration(){
		 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		 /* external outage url */
			String outageurl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersOutUrl");
			String nonOutageurl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersNonOutUrl");
			
			String externalResp1 = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersExternalResponse1");
			String externalResp2 = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersExternalResponse2");
			
			String internalOutUrl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersinternalOutUrl");
			String internalNonOutUrl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersinternalNonOutUrl");
			
			String internalResp1 = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersInternalResponse1");
			String internalResp2 = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersInternalResponse2");
			
			String username = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersUid");
			String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".xmattersPwd");			
			
			String jiraAdmins = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".jiraAdminEmail");	
			
			setUserName(username);
			setPassword(password);
			setOutageUrl(outageurl);
			setNonoutageUrl(nonOutageurl);
			setXmattersinternalNonOutUrl(internalNonOutUrl);
			setXmattersinternalOutUrl(internalOutUrl);
			setXmattersExternalResponse1(externalResp1);
			setXmattersExternalResponse2(externalResp2);
			setXmattersInternalResponse1(internalResp1);
			setXmattersInternalResponse2(internalResp2);
			setJiraAdminEmailids(jiraAdmins);
		
			userpwd =  username+":"+password;
			//logger.info("Plugin config username and pwd: "+ userpwd);	
	 }
	@GET
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	public Response getXMatters(@QueryParam("issueKey") String issueKey,
			@QueryParam("confBridge") String confBridge,
			@QueryParam("nonConfBridge") String nonConfBridge,
			@QueryParam("internalConfBridge") String internalConfBridge,
			@QueryParam("internalNonConfBridge") String internalNonConfBridge) {

		
		logger.info("xMatter Integration ");
		logger.info("issueKey Integration Field>>>" + issueKey);
		logger.info("confBridge Integration Field>>>" + confBridge);
		logger.info("nonConfBridge Integration Field>>>>"
				+ nonConfBridge);
		String sgvalue = "";
		String eventStatus = "";
		String response = "";
		String statusErr = "Validation Error";
		String authtoken = getTokenId();
		

		Issue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(
				issueKey);

		Status status = issue.getStatusObject();
		
		
		logger.info("--------------------status: "+ status.getName());
		Project p = issue.getProjectObject();
		OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		IssueType issueType = issue.getIssueTypeObject();
		CustomField sgpCF = cfm.getCustomFieldObjectByName("Solution Group - Product");		
		logger.info("SG map: "+ issue.getCustomFieldValue(sgpCF));
		CustomField typeCF = cfm.getCustomFieldObjectByName("Type");		
		logger.info("typeCF map: "+ issue.getCustomFieldValue(typeCF));
		CustomField impactedCF = cfm.getCustomFieldObjectByName("Impacted - Function");
		logger.info("impactedCF map: "+ issue.getCustomFieldValue(typeCF));
		CustomField severityCF = cfm.getCustomFieldObjectByName("Severity");
		logger.info("severityCF map: "+ issue.getCustomFieldValue(severityCF));
		CustomField incidentDurationCF = cfm.getCustomFieldObjectByName("Incident Duration");
		logger.info("incidentDuration map: "+ issue.getCustomFieldValue(incidentDurationCF));
		
		String solutionGrValue = "";
		String productValue = "";
		
		
		String outageType = getOutageType(confBridge,nonConfBridge,internalConfBridge,internalNonConfBridge);
		String isConference= isConferenceBridge(confBridge,nonConfBridge,internalConfBridge,internalNonConfBridge);
		
		if(outageType.equalsIgnoreCase("External")){
			Map<LazyLoadedOption, LazyLoadedOption> solutionGroupMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(sgpCF);
			int sizeOfSolutionGpMap = solutionGroupMap.size();			
			logger.info("Size of the sizeOfSolutionGpMap"+sizeOfSolutionGpMap);
			if(sizeOfSolutionGpMap==1){
				productValue = ".";
				statusErr = "Validation Error";
				return 	Response.ok(new xMatterRestAPIModel(issueKey,statusErr, "Product is Mandatory")).build();
			}
			// Cascade field Solution Group - Product
			
			for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : solutionGroupMap.entrySet()) {
			LazyLoadedOption llo = null;
				if(opt.getKey() ==  null ){ // for Solution Group				
					llo = (LazyLoadedOption) opt.getValue();				
					String groupOptId = String.valueOf(llo.getOptionId());
					solutionGrValue = String.valueOf(llo.getValue());				
				}
				if(opt.getKey() !=  null ){
					
					llo = (LazyLoadedOption) opt.getValue();
					
					String productId = String.valueOf(llo.getOptionId());
					productValue = String.valueOf(llo.getValue());
					
				}
			}
			
		} else {		
			// Cascade field Type 
			Map<LazyLoadedOption, LazyLoadedOption> typeMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(typeCF);
			int sizeOftypeMap = typeMap.size();
			logger.info("Size of the sizeOfSolutionGpMap"+sizeOftypeMap);
			for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : typeMap.entrySet()) {
			LazyLoadedOption llo = null;
				if(opt.getKey() ==  null ){ // for Solution Group					
					llo = (LazyLoadedOption) opt.getValue();
					String typeId = String.valueOf(llo.getOptionId());
					String typeVal = String.valueOf(llo.getValue());
				}
				if(opt.getKey() !=  null ){
					llo = (LazyLoadedOption) opt.getValue();
					String locationId = String.valueOf(llo.getOptionId());
					String locationValue = String.valueOf(llo.getValue());
					solutionGrValue = locationValue;
				}
			}			
			
			// Cascade field Impacted - Function 
			Map<LazyLoadedOption, LazyLoadedOption> timpactedCFMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(impactedCF);
			if(timpactedCFMap!=null){
				int sizeOftimpactedCFMap = timpactedCFMap.size();				
				logger.info("Size of the sizeOftimpactedCFMap"+sizeOftimpactedCFMap);
				for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : timpactedCFMap.entrySet()) {
				LazyLoadedOption llo = null;
					if(opt.getKey() ==  null ){ // for Impacted				
						llo = (LazyLoadedOption) opt.getValue();						
						String impactedId = String.valueOf(llo.getOptionId());
						String impactedVal = String.valueOf(llo.getValue());
						
						productValue = impactedVal;
					}						
				}
			}
		} // else if
		String priorityName = "";
		LazyLoadedOption severityVal = (LazyLoadedOption)issue.getCustomFieldValue(severityCF);
		if(severityVal!=null && severityVal.getOptionId()!=null ){
		Option  severityOpt= optionsManager.findByOptionId(severityVal.getOptionId());
			if(severityOpt!=null && severityOpt.getValue()!=null){
				priorityName = severityOpt.getValue();				
				logger.info("Severity : "+ severityOpt.getValue());
			}
		}
		
		String xMattersDesc = (String)issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("xMatters Description"));
		//logger.info("xMatters Description: "+issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("xMatters Description")));
		LazyLoadedOption xMattersEventStatus = (LazyLoadedOption)issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("xMatters Event Status"));
		if(xMattersEventStatus!=null && xMattersEventStatus.getOptionId()!=null ){
			Option  xMEventStatus= optionsManager.findByOptionId(xMattersEventStatus.getOptionId());
			if(xMEventStatus!=null && xMEventStatus.getValue()!=null){
				eventStatus = xMEventStatus.getValue();
				
				logger.info("xMattersEventStatus: "+ xMEventStatus.getValue());
			} else {
				statusErr = "Validation Error";
				return 	Response.ok(new xMatterRestAPIModel(issueKey,statusErr, "xMatters Event Status is Mandatory")).build();
			}
		} else {
				statusErr = "Validation Error";
				return 	Response.ok(new xMatterRestAPIModel(issueKey,statusErr, "xMatters Event Status is Mandatory")).build();
		}
		
		//logger.info("xMatters Log: "+issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("XMatters Log")));
		String xMattersLog =(String) issue.getCustomFieldValue(cfm.getCustomFieldObjectByName("XMatters Log"));
		if( (xMattersDesc==null) || (xMattersDesc!=null && xMattersDesc.trim().equals("null"))){
			statusErr = "Validation Error";
			response = "xMatters Description is Mandatory";
			return Response.ok(new xMatterRestAPIModel(issueKey, statusErr,response)).build();
		}
		
		String incidentDuration =(String) issue.getCustomFieldValue(incidentDurationCF);
		
		
		xMatterRestAPIModel restApiModel = null;
		try{
			restApiModel = createOutageIncient(authtoken, priorityName,solutionGrValue, productValue, isConference,issueKey,xMattersDesc,xMattersLog,outageType,eventStatus,incidentDuration);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(restApiModel).build();
		
	}
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
    		 logger.info("getOutageUrl() "+ getOutageUrl());
    	 } else if(isConf.equals("true") && outageType.equals("Internal")){
    		 post = new HttpPost(getXmattersinternalOutUrl());
    		 logger.info("getXmattersinternalOutUrl() "+ getXmattersinternalOutUrl());
    	 } else if(isConf.equals("false") && outageType.equals("External")){
    		 post = new HttpPost(getNonoutageUrl());
    		 logger.info("getNonoutageUrl() "+ getNonoutageUrl());
    	 } else {
    		 post = new HttpPost(getXmattersinternalNonOutUrl());
    		 logger.info("getXmattersinternalNonOutUrl() "+ getXmattersinternalNonOutUrl());
    	 }
    	return post;
    }
    private void setReindex(MutableIssue mutableIssue){
   	 try {
   			ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
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
		        
		    
		        xMatterValidation validation = new xMatterValidation();
		        validation.setDate(date);
		        validation.setDesc(desc);
		        validation.setEventId(eventId);
		        validation.setEventStatus(eventstatus);
		        validation.setPriority(priority);
		        tableList.add(validation);
		    }
		    return tableList;
    }
	public xMatterRestAPIModel createOutageIncient(String sid,String priority,String solutionGroup,String product,String isConf,String issueKey,String desc,String existingLog,String outageType,String eventstatus,String incidentDuration)
			throws UnsupportedEncodingException {
		xMatterRestAPIModel apiModel = new xMatterRestAPIModel();
		apiModel.setIssueKey(issueKey);
		
		   // Create an instance of HttpClient.
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		    HttpPost post = getPostObject(isConf, outageType);
		    // set headers   
		    post.setHeader("Content-Type", "application/json");	
		    post.setHeader("Authorization", "Basic " + sid);
		    Iterator<JsonNode> it = null;
		    try{
		    	if(existingLog!=null && !existingLog.equals("")){
				    
				    //logger.info("xMatters existingLog: $$$$$$"+ eventstatus);
				    if(!existingLog.equals("<html></html>")){
				    List dataList = parseHTMLTableData(existingLog);
				    Iterator<xMatterValidation> iterator = dataList.iterator();
				    boolean isEventStatusNew = false;
				    while(iterator.hasNext()){
				    	xMatterValidation validation = iterator.next();
				    	String validateEventStatus = validation.getEventStatus();
				    	if(validateEventStatus.equals("New") || validateEventStatus.equals("New/Resolved")){				    		
				    		if(eventstatus.equals("New") || eventstatus.equals("New/Resolved")){
				    			apiModel.setStatus("Validation Error");
								apiModel.setMessage("Duplicate Event Status");
								return apiModel;
				    		}
				    	}
				    }
				    }
					//byte[] byteArray =  IOUtils.toByteArray(existingLog);
					//ObjectMapper existingmapper = new ObjectMapper();
					//JsonNode existingrootNode = existingmapper.readTree(byteArray);
					//JsonNode dataNode = existingrootNode.get("data");
					// it = dataNode.getElements();
		    	}
		    }catch(Exception e){
				sendMailNotification(e.getMessage() + " " + issueKey);
				e.printStackTrace();
			}
			//String baseUrl = ComponentAccessor.getApplicationProperties().getString("jira.baseurl");
			//baseUrl = "<a href='"+ baseUrl +"/browse/" + issueKey +"'>"+issueKey +"</a>";
			//System.out.println("BaseURL: " + baseUrl);
		    
		    StringBuilder service = new StringBuilder();
		   		    
		    if(outageType.equalsIgnoreCase("Internal")){
		    	service.append("\"Internal Service\": [")
			     .append("\"" + solutionGroup +"\",")
			      .append("\"" + product +"\"")
			       .append( "], \n");	    
		    	
		    } else {
		    	service.append("\"External Service\": [")
			     .append("\"" + solutionGroup +"\",")
			      .append("\"" + product +"\"")
			      .append( "], \n");	  	
		    	
		    }
		
		   String[] lines = desc.split("\r\n|\n");	
		   StringBuilder description = new StringBuilder();
		   //Date curDate = new Date();
		   //String  DateToStr = DateFormat.getTimeInstance().format(curDate);
		   //description.append(DateToStr+"::");
		   
		    for(int i = 0; i< lines.length; i++){
		    	description.append(lines[i]);		    
		    	if(i != (lines.length -1)){
		    		description.append("\\n");	    		
		    	}
		    	
		    }
		    desc = description.toString();
		    lines = desc.split("\r\t|\t");
		    description = new StringBuilder();
			   
		    for(int i = 0; i< lines.length; i++){
		    	description.append(lines[i]);
		    	if(i != (lines.length -1)){
		    		description.append("\\t");	    		
		    	}
		    	
		    }
		   
		    StringBuilder PAYLOAD = new StringBuilder()
		   
		    .append( "{ \n" )

		     .append("\"properties\": {")
		     
		    .append(service)
		    .append("\"Incident Type\": \""+outageType+"\", \n")
		    .append("\"Status\": \""+eventstatus+"\", \n")     
		    .append("\"Severity\": \""+ priority +"\", \n")
			.append("\"Incident ID\": \""+issueKey+"\", \n")
		    //.append("\"Impact\": \"Low (3) - Single/Isolated Users\", \n")
		    .append("\"Description\": \""+description+ "\", \n")
		    .append("\"Incident Duration\": \""+incidentDuration+ "\", \n")
		    
			.append("\"xMatters Communication Log\": \""+getXmattersCommunicationLog(existingLog)+ "\" \n")
		    .append( "}," );
		    if(isConf.equals("true")){ // Validating conference bridge or no conference bridge
		    	   StringBuilder responses = new StringBuilder();				    
				    if(outageType.equalsIgnoreCase("Internal")){	// Validating Internal conf bridge or external  conference bridge			    	
				    	responses.append( " \"responses\":   [")				
						.append("\""+ getXmattersInternalResponse1()+"\",")
					    .append("\""+ getXmattersInternalResponse2()+"\" ")
					    .append( "],");				    	
				    } else {				    			    	
				    	responses.append( " \"responses\":   [")
					   .append("\""+ getXmattersExternalResponse1()+"\",")
					    .append("\""+ getXmattersExternalResponse2()+"\" ")
					    .append( "],");
				    }
			    StringBuilder outageBu = new StringBuilder()
			    .append( " \"conferences\":   [")
			    .append("{ \n")
			    .append("\"name\": null \n")
			    .append( "}" )
			    .append( "],")
			    .append(responses);
			   
		    PAYLOAD.append(outageBu); 
		    }
		    PAYLOAD.append( " \"recipients\":   [")
		    .append("{ \n")
			.append("\"targetName\": \""+getUserName()+"\" \n")
		    .append( "}" )
		    .append( "]")
		    
		    .append( "}" );

		    logger.info(PAYLOAD.toString());
		    StringEntity data = new StringEntity( PAYLOAD.toString() );

		    data.setContentType("application/json");

		    post.setEntity( data );        
		    
		        
		    String line = "";
		   InputStream inputStream = null;
		   // custom filed update
					CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
					DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
					MutableIssue mutableIssue =ComponentAccessor.getIssueManager().getIssueByCurrentKey(issueKey);
		    try {
		    	HttpResponse response = defaultHttpClient.execute(post);	      // Read the response body.
		    	/*	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	       		    String line11 = "";
	       		    while ((line11 = rd.readLine()) != null) {
	       		      System.out.println( line11 );
	       		    }
					{"type":"DATA_VALIDATION_ERROR","message":"There are data validation errors in the form.","errorDetails":[{"jsonPath":"properties/Outage Type","details":"Specified value not found in the list of configured values"}]}
	    		*/
	       		inputStream = response.getEntity().getContent();
		    	byte[] responseBody =  IOUtils.toByteArray(inputStream);
		    	if(responseBody!=null && responseBody.length > 0){		    		
		    			
		    		logger.info("xMatters response body: ");
		    		String eventId = "";
			    	ObjectMapper mapper = new ObjectMapper();
				    JsonNode rootNode = mapper.readTree(responseBody);
					 JsonNode typeNode = rootNode.get("type");
					if(typeNode==null){
							JsonNode idNode = rootNode.get("id");							
							eventId = idNode.asText();
							logger.info("eventId: "+eventId);
							line = eventId;
							apiModel.setEventId(eventId);
							apiModel.setStatus("Validation Success");
							apiModel.setMessage("xMatters Event successfully created!");
							CustomField customField =  cfm.getCustomFieldObjectByName("XMatters Log");
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
							.append("<td>").append(strDate).append("</td>")
							.append("<td>").append(desc).append("</td>")
							.append("<td>").append(eventId).append("</td>")							
							.append("<td>").append(eventstatus).append("</td>")							
							.append("<td>").append(priority).append("</td>")
							.append("</tr>")
							.append("</tbody>")
							.append("</table>");
						
							String xMattersLog = xMattersResponse.toString();
							if(xMattersLog!=null && xMattersLog.length()>0){
								mutableIssue.setCustomFieldValue(cfm.getCustomFieldObjectByName("XMatters Log"), xMattersLog);
								modifiedFields = mutableIssue.getModifiedFields();
								fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
								modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
								customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
								setReindex(mutableIssue);
							}
					} else {
						JsonNode messageNode = null;
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
						 sendMailNotification(errorMsg.toString() + "\n issuekey: " + issueKey +"\n Json String: "+PAYLOAD.toString());
						 return apiModel;
					}
				 	
		    	}
			inputStream.close();
		    } catch (Exception e) {		    
		      apiModel.setStatus("Validation Error");
			  apiModel.setMessage( e.getMessage() );
			  sendMailNotification(e.getMessage() + "\n issuekey: " + issueKey +"\n Json String: "+PAYLOAD.toString());
			  e.printStackTrace();
			  return apiModel;
		    } /*catch (JSONException e) {
		    		e.printStackTrace();
			}*/ finally {
		      // Release the connection.
		    	post.releaseConnection();
				
		    }
		    return apiModel;  
	}
	
	private void sendMailNotification(String errorMsg){
		try{
		logger.info("Send Mail Notification: "+errorMsg);
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
			ex.printStackTrace();
		}
	}
	private String appendHeaders(){
		StringBuffer headers = new StringBuffer();
		 String columns = "Date Time,Description,Event Id,Event Status,Severity";
		 headers.append("<table border=1>")
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
		if(htmlStr.indexOf("<thead>")>0){
			htmlStr = htmlStr.substring(htmlStr.indexOf("<tbody>"),htmlStr.indexOf("</tbody>"));
		} else if( htmlStr.equals("") || htmlStr.indexOf("data")>0 || htmlStr.indexOf("</html>")>0){
				htmlStr="";
		}
		return htmlStr;
	}
	
	
	public String getXmattersCommunicationLog(String existingLog){
		StringBuffer logBuffer = new StringBuffer();
		 if(existingLog!=null && !existingLog.equals("")){			
			    if(!existingLog.equals("<html></html>")){
				    List dataList = parseHTMLTableData(existingLog);
				    Iterator<xMatterValidation> iterator = dataList.iterator();

					//Will be used to filter out some messages as seen toward the end of this method
					List<String> rowLogs = new ArrayList<String>();

				    while(iterator.hasNext()){
				    	xMatterValidation validation = iterator.next();
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

				}
			    return logBuffer.toString().trim();
		 }
		 return "";
	}
	
	private String getTime(String dateStr){
		String timeString = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa");
		Date date = null;
		try {
			 date= (Date)dateFormat.parse(dateStr);
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
	
	
	
}
