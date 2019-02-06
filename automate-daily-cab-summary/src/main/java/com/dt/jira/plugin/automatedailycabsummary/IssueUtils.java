package com.dt.jira.plugin.automatedailycabsummary;


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
import com.atlassian.jira.issue.issuetype.IssueType;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

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
import static org.apache.commons.lang3.StringUtils.isBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringEscapeUtils;

public class IssueUtils 
{
   
    
    
    private final Logger logger = LoggerFactory.getLogger(IssueUtils.class);
    String[] fieldBeans = {"Solution Group - Product","Impacted - Function","Type"};
    
    
    
    /**
	 * Helper method that creates a HashMap of the JIRA field name -> its values
	 * @param issue
	 *
	 * @return HashMap of the JIRA field name -> its values
	 */
	public Map<String,List<String>> getJiraFieldValues(Issue issue,String[] fieldNames){
		
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
		
		for(String fieldName: fieldNames){
			CustomField customField = getCustomField(fieldName);

			//If its not a custom field, then it will be a system field
			if (customField == null) {
				extractSystemFieldValuesInHash(issue, jiraFieldValues, fieldName);
			} else {
				String fieldType = customField.getCustomFieldType().getName();
				logger.info("Field Type is " + fieldType);

				if(fieldType.equalsIgnoreCase("Select List (cascading)")){
					values = getCascadeSelectValue(issue,customField);
					jiraFieldValues.put(fieldName, values);

				} else if(fieldType.equalsIgnoreCase("Select List (single choice)")) {
					values = getSingleSelectValue(issue, customField);
					jiraFieldValues.put(fieldName, values);
				} else if(fieldType.equalsIgnoreCase("Select List (multiple choices)")) {
					values = getMultiLevelSelectValue(issue, customField);
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
						   case IssueFieldConstants.ISSUE_KEY:
													String key = issue.getKey();
													values.add(key);
													jiraFieldValues.put(fieldName, values);
													break;
									case IssueFieldConstants.PROJECT:
													String projectValue = issue.getProjectObject().getName();
													values.add(projectValue);
													jiraFieldValues.put(fieldName, values);
													break;
									case IssueFieldConstants.ASSIGNEE :
													String assigneeValue = (issue.getAssignee()!=null)?issue.getAssignee().getDisplayName():"";
													assigneeValue = getRefinedFieldValue(assigneeValue);
													values.add(assigneeValue);
													jiraFieldValues.put(fieldName,values);
													break;
									case IssueFieldConstants.DESCRIPTION :
													String descriptionValue = issue.getDescription();
													//descriptionValue = getRefinedFieldValue(descriptionValue);
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
	 * Helper method to deal with multilevel cascade field. Note, as of now, only the Solution Group Product - Category
	 * is a multilevel cascade field
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for multiselect cascade field , out of which we need to pick only first two
	 */
	private List getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

		//Add at the max three values as all of them are not supported in xMatters as of now
		if (valuesList != null) {
			for (Object value : valuesList) {
				values.add(value.toString());
			}

			//For now if by any chance the element is present at fourth level of cascade, then remove it.
			if (values.size() > 2) {
				for (int i = 3 ; i < values.size() ; i++) {
					values.remove(i);
				}
			}
		}


		return values;
	}
	/**
	 * Helper method to deal with Select List (multiple choices) field type. Note, the Impacted Business Unit
	 * is a multilevel field
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for Select List (multiple choices)
	 */
	private List getMultiLevelSelectValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);
		if (valuesList != null) {
			int i=0;
			for (Object value : valuesList) {

				if(i != valuesList.size()-1){
					values.add(value.toString()+",");
				} else {
					values.add(value.toString());
				}
				i++;
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
    
    
}