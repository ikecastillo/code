package  com.dt.jirasdfieldmapper.event;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.context.IssueContextImpl;

import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;

import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.jira.issue.index.IssueIndexingService;


//import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
//import java.net.URI;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStream;


import com.dt.jirasdfieldmapper.service.FieldMapperService;
import com.dt.jirasdfieldmapper.rest.FieldBean;
import com.dt.jirasdfieldmapper.rest.AdminConfigResource.Config;
/**
 * StandardBAUChangeEventListener.java event listener class 
 * Update the risk Questionnaire tab details from Service Management when the template is auto-populate while the
 * creating change ticket.
 */

public class StandardBAUChangeEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final OptionsManager optionsManager;
    private final JiraAuthenticationContext authenticationContext;  
    private final FieldMapperService fieldMapperService;
	private final Logger logger = Logger
			.getLogger(StandardBAUChangeEventListener.class);
	 private static final String PLUGIN_STORAGE_KEY = Config.class.getName();
	  private final FieldManager fieldManager;
    private final IssueIndexingService issueIndexingService;
   
	
	
	
	/**
    *constructor
    */
	public StandardBAUChangeEventListener(EventPublisher eventPublisher,
                                          PluginSettingsFactory pluginSettingsFactory, OptionsManager optionsManager,
                                          JiraAuthenticationContext authenticationContext, FieldMapperService fieldMapperService, FieldManager fieldManager, IssueIndexingService issueIndexingService) {
		this.eventPublisher = eventPublisher;
		this.pluginSettingsFactory = pluginSettingsFactory;
		this.optionsManager=optionsManager;
        this.authenticationContext=authenticationContext; 
        this.fieldMapperService = fieldMapperService;
        this.fieldManager = fieldManager;
        this.issueIndexingService = issueIndexingService;
    }
	
	
	
    /**
     * This event occurs when ticket creation. 
     * @param issueEvent
     */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
				
		try {
				Long eventTypeId = issueEvent.getEventTypeId();
				Issue issueParent = issueEvent.getIssue();
				Map<String,Object> issueEventMap=issueEvent.getParams();
				Project project = issueParent.getProjectObject();
				String statusName= issueParent.getStatusObject().getName();
				IssueType  issuetype = issueParent.getIssueTypeObject();
				ApplicationUser user = issueParent.getReporterUser();
				ApplicationUser appUser = ComponentAccessor.getUserManager().getUserByName(user.getName());
				logger.debug("eventTypeId : "+ eventTypeId);
				// 1 - Issue Create Event
				if(eventTypeId == 1 && "CHG".equals(project.getKey()) && "Change".equals(issuetype.getName())  ){
						String changeType= getCustomFeildValue("Change Type",issueParent);
						if("Standard".equals(changeType)){
						logger.debug("changeType : "+ changeType);
							getServiceManagementIssueDtails(appUser,issueParent);					
						}
				}
			} catch (Exception e) {
					e.printStackTrace();
				}
			}
    /**
    *Get Service Management issue details and update same on the current change Issue
    */
    private String[] getServiceManagementIssueDtails(ApplicationUser user,Issue issue){         
        InputStream   inputStream = null;
		try{
           
        String summary=issue.getSummary(); 
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        String serviceDeskURL = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskURL");
        String userid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskUserId");
        String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskPwd");
        String servDeskIssueKey = "";
        String impacted=getCustomFeildValue("Impacted - Function",issue);
		if(impacted!=null && !impacted.equals("")){
			logger.debug("impacted: "+ impacted);
			String[] impactedValue = impacted.split(",");
			logger.debug(impactedValue.length);
			if(impactedValue.length == 2){
				servDeskIssueKey = impactedValue[1].substring(0,impactedValue[1].indexOf(":"));
				logger.debug(servDeskIssueKey);
			} 
		}
         
	    HttpClient defaultHttpClient =null;
       
        defaultHttpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(serviceDeskURL+"/rest/api/2/issue/"+servDeskIssueKey+"?fields="+getAllFieldIds());
		
		get.addHeader("Content-Type", "application/json");
		get.addHeader("Authorization", "Basic " + getTokenId(userid, password));
       
			HttpResponse response = defaultHttpClient.execute(get);
           
			inputStream = response.getEntity().getContent();
			byte[] responseBody =  IOUtils.toByteArray(inputStream);
			if(responseBody!=null && responseBody.length > 0){
               ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(responseBody);
				JsonNode fieldNode = rootNode.get("fields");
				
				List<FieldBean> allFields = fieldMapperService.getAllMappingsFromDB();
				for(FieldBean fieldBean: allFields){
					Object  value= getJsonFieldValue(fieldNode,fieldBean.getJiraSdFieldId(),fieldBean.getJiraFieldName(),issue);
					updateCustomFieldValue(fieldBean.getJiraFieldName(),value,issue);	// Update custom field values					
				}
				
				return null;                
            }
             
			 } catch (Exception e) {
			e.printStackTrace();
            inputStream = null;
		}
        finally {
		inputStream = null;               
        }
        
        return null;
    }
	/**
	* Get All mapping fields
	*/
	private String getAllFieldIds(){
		List<FieldBean> mappingFields = fieldMapperService.getAllMappingsFromDB();
		String s1 = null;
		
		StringBuffer sb = new StringBuffer();
		if(mappingFields!=null){
			for(FieldBean field: mappingFields){
				sb.append(field.getJiraSdFieldId()).append(",");
			}
			s1= sb.substring(0, sb.length()-1);
		}
		
		return s1;	
	}
	/**
	* Get field value from json response
	*/
	private Object getJsonFieldValue(JsonNode fieldNode,String customFeildId,String jiraFieldName,Issue issue){
		
		CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
    	CustomField field = cfm.getCustomFieldObjectByName(jiraFieldName);
    	
	
		 if (fieldManager.isCustomField(field)) {
	            CustomField customField = (CustomField) field;
	            CustomFieldType cfType = customField.getCustomFieldType();
	            logger.debug(" customField.getCustomFieldType().getKey() " + customField.getCustomFieldType().getKey());
	            //logger.debug("customField.getCustomFieldType().getName() " + customField.getCustomFieldType().getName());
	            if("com.atlassian.jira.plugin.system.customfieldtypes:textarea".equals(customField.getCustomFieldType().getKey())){
	            	return (Object)fieldNode.get(customFeildId).asText();
	            } else if("com.atlassian.jira.plugin.system.customfieldtypes:select".equals(customField.getCustomFieldType().getKey())){
	            	JsonNode selectNode = fieldNode.get(customFeildId);
	            	if(selectNode!=null && selectNode.has("value")){
	            		String value = selectNode.get("value").asText();
	            		logger.debug(" jiraFieldName "+ jiraFieldName + " - "+value);
	            		List<Option> options = ComponentAccessor.getOptionsManager().findByOptionValue(value);
	            		if(options!=null &&  options.size()>0)
	            			return (Object)options.get(0);
	            	}
	            } else if("com.atlassian.jira.plugin.system.customfieldtypes:multiselect".equals(customField.getCustomFieldType().getKey())){
	            	JsonNode multiSelNode = fieldNode.get(customFeildId);
	            	Iterator<JsonNode> itNode = multiSelNode.getElements();
	            	List<Option> updateOptions = new ArrayList<Option>();
					List<Option> options123 = (List<Option>)issue.getCustomFieldValue(customField);
					Project project1 = issue.getProjectObject();
					IssueType issueType = issue.getIssueTypeObject();
					FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project1, issueType));	
					Options multiSelect = optionsManager.getOptions(fieldConfigSolution);	
					
	            	while(itNode.hasNext()){
	            		JsonNode valueNode = itNode.next();
	            		String val= valueNode.get("value").asText();
	            		logger.debug(" jiraFieldName "+ jiraFieldName + " - "+val);	
		            				for(Option opt : multiSelect){ 		            	
		   							 if(opt.getValue().equals(val.trim())){
		   								logger.debug("Option value: "+ opt.getValue() + " option id: "+ opt.getOptionId());
		   								updateOptions.add(opt);
		   								break;
		   							  }
		   						   }
	            	}
					/*IssueManager issueManager = ComponentAccessor.getIssueManager();
					//CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
					//CustomField  customField = customFieldManager.getCustomFieldObjectByName(customFieldName); //customFieldName		
					DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
					String key = issue.getKey();
					MutableIssue mutableIssue = issueManager.getIssueByCurrentKey(key);	
					Map<String, ModifiedValue> modifiedFields = null;
					FieldLayoutItem fieldLayoutItem = null;
					ModifiedValue modifiedValue = null;
					
					if(updateOptions!=null ){
						mutableIssue.setCustomFieldValue(customField, updateOptions);
						modifiedFields = mutableIssue.getModifiedFields();
						fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
						modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
						customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
						setReindex(mutableIssue);
					}
					logger.debug("Successfully updated custom field value : " + updateOptions);
					logger.debug("Successfully updated custom field value : " + updateOptions);*/
	            	return (Object)updateOptions;
	            }else if("com.atlassian.jira.plugin.system.customfieldtypes:multiuserpicker".equals(customField.getCustomFieldType().getKey())){
	            	JsonNode multiSelNode = fieldNode.get(customFeildId);
	            	Iterator<JsonNode> itNode = multiSelNode.getElements();
	            	List<ApplicationUser> appUser = new ArrayList<ApplicationUser>();
	            	while(itNode.hasNext()){
	            		JsonNode valueNode = itNode.next();
	            		String val= valueNode.get("key").asText();
	            		logger.debug(" jiraFieldName "+ jiraFieldName + " - "+val);
		            		ApplicationUser user = ComponentAccessor.getUserManager().getUserByName(val);
		            		if(user!=null){
		            			appUser.add(user);
		            		}
		            		
		            	}
	            	return (Object)appUser;
	            
	            } else if("com.atlassian.jira.plugin.system.customfieldtypes:userpicker".equals(customField.getCustomFieldType().getKey())){
	            	JsonNode selectNode = fieldNode.get(customFeildId);
	            	if(selectNode!=null && selectNode.has("key")){
	            		String value = selectNode.get("key").asText();
	            		logger.debug(" jiraFieldName "+ jiraFieldName + " - "+value);
	            		ApplicationUser user = ComponentAccessor.getUserManager().getUserByName(value);
	            		return (Object)user;
	            	}
	            }
		 }
		/*if(fieldNode.has(customFeildId)){
			return fieldNode.get(customFeildId).asText();
		}*/
		return null;	
	}
	

	
 /**
 *creating token for basic authentication
 */
public String getTokenId(String userid, String password) {	 
		// encoding byte array into base 64
		byte[] encoded = Base64.encodeBase64((userid+":"+password).getBytes());
		String sid = new String(encoded);
		return sid;
	}  
            
     
/* * Helper method to update the custom field.   
 * @param updateValue
 * @param currentIssue
 */
private void updateCustomFieldValue(String customFieldName, Object updateValue,Issue currentIssue){
	IssueManager issueManager = ComponentAccessor.getIssueManager();
	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
	CustomField  customField = customFieldManager.getCustomFieldObjectByName(customFieldName); //customFieldName		
	DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
	//String key = currentIssue.getKey();
	MutableIssue mutableIssue = (MutableIssue) currentIssue; //issueManager.getIssueByCurrentKey(key);	
	Map<String, ModifiedValue> modifiedFields = null;
	FieldLayoutItem fieldLayoutItem = null;
	ModifiedValue modifiedValue = null;
	
	if(updateValue!=null ){
		mutableIssue.setCustomFieldValue(customField, updateValue);
		modifiedFields = mutableIssue.getModifiedFields();
		fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
		modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
		customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,issueChangeHolder);
		setReindex(mutableIssue);
	}
	logger.debug("Successfully updated custom field value : " + updateValue);
}
/**
* Issue re-index
*/
private void setReindex(MutableIssue mutableIssue){
	try {
		//ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
        issueIndexingService.reIndex(mutableIssue);
	} catch (IndexException ie) {
		logger.error("index issue" + ie.getMessage());
	}
}  
/**
* Get custom field value 
* @name - custom field 
* @issue- Issue Object
*/     
 private String getCustomFeildValue(String name,Issue issue){
    String value="";
    CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
	CustomField customFeildObj = cfManager.getCustomFieldObjectByName(name);
    String feildType=customFeildObj.getCustomFieldType().getKey();
    if(feildType.equals("com.atlassian.jira.plugin.system.customfieldtypes:cascadingselect")){
   logger.debug("=======custom feild values solution group product : ---"+customFeildObj.getValue(issue));
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
        logger.debug("value : "+value);
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
