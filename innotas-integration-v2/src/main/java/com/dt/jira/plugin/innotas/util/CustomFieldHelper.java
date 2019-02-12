/**
 * 
 */
package com.dt.jira.plugin.innotas.util;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.issue.util.IssueChangeHolder;
import com.atlassian.jira.project.Project;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @author sriram.rajaraman
 *
 */
public class CustomFieldHelper {
	private String fieldName;
	private CustomField cf;
	public CustomFieldHelper(String fieldName){
		this.fieldName = fieldName;
		init();
	}
	private void init() {
		CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
		this.cf = fieldManager.getCustomFieldObjectByName(this.fieldName);
	}

	public void updateFieldOption(Issue issue, String value) {
		IssueChangeHolder changeHolder = new DefaultIssueChangeHolder();
		CustomFieldType cftype = cf.getCustomFieldType();
		System.out.println("Custom Field " + this.fieldName + "; Type: " + cftype.getName());
		FieldConfig fcfg = cf.getRelevantConfig(issue);
        OptionsManager optionsManager = ComponentManager.getComponentInstanceOfType(OptionsManager.class);
		Option option = optionsManager.getOptions(fcfg).getOptionForValue(value, null);
				//.find {it.value == componentsDescription.split(":")[1]} ;
		cf.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(cf), option),changeHolder);
		
	}
	public void updateFieldValue(Issue issue, Double value) {
		//TODO: have this method identify the customfieldtype and use the right casting and method calls. 
		IssueChangeHolder changeHolder = new DefaultIssueChangeHolder();
		CustomFieldType cftype = cf.getCustomFieldType();
		System.out.println("Custom Field " + this.fieldName + "; Type: " + cftype.getName());
		cf.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(cf), value),changeHolder);
	}
	public void updateFieldValue(Issue issue, String value) {
		//TODO: have this method identify the customfieldtype and use the right casting and method calls. 
		IssueChangeHolder changeHolder = new DefaultIssueChangeHolder();
		CustomFieldType cftype = cf.getCustomFieldType();
		System.out.println("Custom Field " + this.fieldName + "; Type: " + cftype.getName());
		cf.updateValue(null, issue, new ModifiedValue(issue.getCustomFieldValue(cf), value),changeHolder);
	}
	
	/*public void setFieldOptions(String pKey,List<String> optionValues) {
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(pKey);
		IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
		FieldConfig fcfg = cf.getRelevantConfig(new IssueContextImpl(project, issueType));
        OptionsManager optionsManager = ComponentManager.getComponentInstanceOfType(OptionsManager.class);   
		List<Option> options = new ArrayList();
		optionsManager.removeCustomFieldOptions(cf);
		for(String optionValue : optionValues) {
			
			options.add(optionsManager.createOption(fcfg, null, null, optionValue));
		}        
		optionsManager.updateOptions(options);
		
	}*/	
	public void setFieldOptions(String pKey,List<String> optionValues) {
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(pKey);
		IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
		FieldConfig fcfg = cf.getRelevantConfig(new IssueContextImpl(project, issueType));
        OptionsManager optionsManager = ComponentManager.getComponentInstanceOfType(OptionsManager.class);   
		List<Option> options = new ArrayList();		
		long sequence = 0; 
		List<Option> existingOptions = getFieldOptions(pKey);
		if(existingOptions!=null && existingOptions.size()>0){
			sequence = existingOptions.size();
			System.out.println("cf.getFieldName() : "+cf.getFieldName());
			System.out.println("sequence : "+sequence);
			
		}
		for(String optionValue : optionValues) {
			options.add(optionsManager.createOption(fcfg, null, Long.valueOf(sequence), optionValue));
			sequence++;
		}        
		optionsManager.updateOptions(options);
		
	}
	public List<Option> getFieldOptions(String pKey) {
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(pKey);
		IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
		FieldConfig fcfg = cf.getRelevantConfig(new IssueContextImpl(project, issueType));
        OptionsManager optionsManager = ComponentManager.getComponentInstanceOfType(OptionsManager.class);   
		return optionsManager.getOptions(fcfg);	
	}	
	
}
