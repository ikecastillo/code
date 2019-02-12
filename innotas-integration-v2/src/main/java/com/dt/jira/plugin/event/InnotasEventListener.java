package com.dt.jira.plugin.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.project.VersionDeleteEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigScheme;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.workflow.AssignableWorkflowScheme;
import com.dt.jira.plugin.innotas.ao.InnotasCache;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;

public class InnotasEventListener implements InitializingBean, DisposableBean {

	private final EventPublisher eventPublisher;
	private final InnotasCacheService cacheService;
	CustomField cf_inbu = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Business Unit");
	CustomField cf_inProgram = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Program");
	CustomField cf_inProjId = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Project ID");
	CustomField cf_inProjName = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Project Name");
	CustomField cf_insubbu = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Sub Business Unit");
	private final Logger logger = Logger.getLogger(InnotasEventListener.class);
    
	public InnotasEventListener(EventPublisher eventPublisher,
			InnotasCacheService cacheService) {
		this.eventPublisher = eventPublisher;
		this.cacheService = cacheService;
	}

	private String getReleaseId(Issue issue) {
		Collection<Version> versions = issue.getFixVersions();
		String releseIds = "";
		int intCounter = 1;
		StringBuffer sb = new StringBuffer();
		if (versions!=null && versions.size() > 0){
			for (Version v : versions) {
				// strVersion[intCounter] = v.getId().toString();
				intCounter = intCounter + 1;
				sb.append(v.getId());
				sb.append(",");
			}
		}		
		
		if (sb != null && sb.length() > 0) {
			releseIds = sb.substring(0, sb.length() - 1);
		}

		return releseIds;
	}
	
	private Option getOption(CustomField customField, String value,Issue issue){
	 Option o = null;
		OptionsManager optionManager = ComponentAccessor
				.getOptionsManager();
	 FieldConfig fieldConfig = customField.getRelevantConfig(issue);
		Options opts = optionManager.getOptions(fieldConfig);
		Iterator<Option> it = opts.iterator();
		while (it.hasNext()) {
			Option option = it.next();
			if (option.getValue().equals(value)) {
				 o = optionManager
						.findByOptionId(option.getOptionId());				
				break;
			}			
		}
		return o;
 }
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		Long eventTypeId = issueEvent.getEventTypeId();
		//System.out.println("on Issue Event");
		if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {

			Issue issue = issueEvent.getIssue();
			MutableIssue mutableIssue = ComponentAccessor.getIssueManager()
					.getIssueObject(issue.getId());
			String releseIds = getReleaseId(issue);
			Project p = issue.getProjectObject();
			System.out.println("p.key: "+p.getKey());
			System.out.println("cacheService: "+cacheService);
			Map<String, Object> imapList = cacheService
					.getInnotasProjectsByIDs(p.getKey(), releseIds);
			if(imapList!=null)
				logger.debug("create project list size:  " + imapList.size());
			InnotasCache ic = null;
			List<Option> inproopt = new ArrayList<Option>();
			List<Option> inbuopt = new ArrayList<Option>();
			List<Option> inProjIdopt = new ArrayList<Option>();
			List<Option> inProjNameopt = new ArrayList<Option>();
			List<Option> insubbuopt = new ArrayList<Option>();
			
			if(imapList!=null){
				for (Object icache : imapList.values()) {
					ic = (InnotasCache) icache;
	
					logger.debug("ISSUE CREATE EVENT-  Business Unit ID:  "
							+ ic.getBuId());
					logger.debug("ISSUE CREATE -  Business Unit Name:  "
							+ ic.getBuName());
					System.out
							.println("ISSUE CREATE -  Innotas Project Projectid:  "
									+ ic.getProjectId());
					logger.debug("ISSUE CREATE -  Innotas Project Name:  "
							+ ic.getProjectName());
					logger.debug("ISSUE CREATE -  Innotas Prog Name:  "
							+ ic.getProgramName());
					logger.debug("ISSUE CREATE -  Innotas Sub BU Name:  "
							+ ic.getSbuName());
	
					String innbuName = ic.getBuName();
					String innProjId = ic.getProjectId();
					String innProjectName = ic.getProjectName();
					String innProgName = ic.getProgramName();
					String innsubBuName = ic.getSbuName();
	
					
					// build  options for innotas program name				
					Option opt_innProgName = getOption(cf_inProgram, innProgName,issue);
					if(opt_innProgName != null)
						inproopt.add(opt_innProgName);
	
					//  build  options for innotas business unit
					Option opt_innbuName = getOption(cf_inbu, innbuName,issue);
					if(opt_innbuName != null)
						inbuopt.add(opt_innbuName);
	
					//  build  options for innotas project id
					Option opt_innProjId = getOption(cf_inProjId, innProjId,issue);
					if(opt_innProjId != null)
						inProjIdopt.add(opt_innProjId);
					
					// build  options for innotas project name
					Option opt_innProjectName = getOption(cf_inProjName, innProjectName,issue);
					if(opt_innProjectName!=null)
						inProjNameopt.add(opt_innProjectName);
					
					// build  options for innotas sub bu name
					Option opt_innsubBuName = getOption(cf_insubbu, innsubBuName,issue);
					if(opt_innsubBuName!=null)
						insubbuopt.add(opt_innsubBuName);
				}// for end
			//}
			cf_inProgram.createValue(mutableIssue, inproopt);
			cf_inbu.createValue(mutableIssue, inbuopt);
			cf_inProjId.createValue(mutableIssue, inProjIdopt);
			cf_inProjName.createValue(mutableIssue, inProjNameopt);
			cf_insubbu.createValue(mutableIssue, insubbuopt);

			logger.debug("insubbuopt : " + insubbuopt);
			logger.debug("inuopt : " + inbuopt);
			logger.debug("inproopt : " + inproopt);
			logger.debug("inProjNameopt : " + inProjNameopt);
			logger.debug("inProjIdopt : " + inProjIdopt);

			setReindex(mutableIssue);			
			} // No mapping found. It means no need to execute the code. issue fix for AIOI-1676
		} else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
			
			Issue issue = issueEvent.getIssue();
			String releseIds = getReleaseId(issue);
			Project p = issue.getProjectObject();
			System.out.println("p.key: "+p.getKey());
			System.out.println("cacheService: "+cacheService);
			Map<String, Object> imapList = cacheService.getInnotasProjectsByIDs(p.getKey(), releseIds);
			if(imapList!=null)
				logger.debug("update project list size:  " + imapList.size());
			MutableIssue mutableIssue = ComponentAccessor.getIssueManager()
					.getIssueObject(issue.getId());
			
			List<Option> inproopt = new ArrayList<Option>();
			List<Option> inbuopt = new ArrayList<Option>();
			List<Option> inProjIdopt = new ArrayList<Option>();
			List<Option> inProjNameopt = new ArrayList<Option>();
			List<Option> insubbuopt = new ArrayList<Option>();			
			InnotasCache icp = null;
			
			if(imapList!=null){
				for (Object icache : imapList.values()) {
					icp = (InnotasCache) icache;
	
					logger.debug("Issue Update Event - buid:  "
							+ icp.getBuId());
					logger.debug("Issue Update Event- buname:  "
							+ icp.getBuName());
					logger.debug("Issue Update Event- innotasprojectProjectid:  "
									+ icp.getProjectId());
					logger.debug("Issue Update Event - innotasprojectnmae:  "
									+ icp.getProjectName());
					logger.debug("Issue Update Event- innotas progrmname:  "
									+ icp.getProgramName());
					logger.debug("Issue Update Event - innotas sub bu name:  "
									+ icp.getSbuName());
	
					String innbuName = icp.getBuName();
					String innProjId = icp.getProjectId();
					String innProjectName = icp.getProjectName();
					String innProgName = icp.getProgramName();
					String innsubBuName = icp.getSbuName();
					
					// build  options for innotas program name	
						
					Option opt_innProgName = getOption(cf_inProgram, innProgName,issue);
					if(opt_innProgName != null)
						inproopt.add(opt_innProgName);
	
					//  build  options for innotas business unit
					Option opt_innbuName = getOption(cf_inbu, innbuName,issue);
					if(opt_innbuName != null)
						inbuopt.add(opt_innbuName);
	
					//  build  options for innotas project id
					Option opt_innProjId = getOption(cf_inProjId, innProjId,issue);					
					if(opt_innProjId != null)
						inProjIdopt.add(opt_innProjId);
					
					// build  options for innotas project name
					Option opt_innProjectName = getOption(cf_inProjName, innProjectName,issue);
					if(opt_innProjectName != null)
						inProjNameopt.add(opt_innProjectName);
					
					// build  options for innotas sub bu name
					Option opt_innsubBuName = getOption(cf_insubbu, innsubBuName,issue);
					if(opt_innsubBuName != null)
						insubbuopt.add(opt_innsubBuName);
				}
			}

			
			//update custom field value for innotas program			
			updateCustomFieldValue(mutableIssue,cf_inProgram,inproopt);			
		
			//update custom field value for innotas business unit
			updateCustomFieldValue(mutableIssue,cf_inbu,inbuopt);
			
			//update custom field value for innotas project id 
			updateCustomFieldValue(mutableIssue,cf_inProjId,inProjIdopt);

			//update custom field value for innotas project name 
			updateCustomFieldValue(mutableIssue,cf_inProjName,inProjNameopt);
	
			//update custom field value for innotas sub business unit 
			updateCustomFieldValue(mutableIssue,cf_insubbu,insubbuopt);
		
			setReindex(mutableIssue);

			
			
		}
	}
	private void updateCustomFieldValue(MutableIssue mutableIssue,CustomField customField, List<Option> options){
	 	DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
		Map<String, ModifiedValue> modifiedFields = null;
		FieldLayoutItem fieldLayoutItem = null;
		ModifiedValue modifiedValue = null;
	
		if(options!=null && options.size()>0){
			mutableIssue.setCustomFieldValue(customField, options);
			modifiedFields = mutableIssue.getModifiedFields();
			fieldLayoutItem = ComponentAccessor.getFieldLayoutManager()
					.getFieldLayout(mutableIssue).getFieldLayoutItem(customField);
			modifiedValue = (ModifiedValue) modifiedFields.get(customField
					.getId());
			customField.updateValue(fieldLayoutItem, mutableIssue, modifiedValue,
					issueChangeHolder);
		}
	}
	private void setReindex(MutableIssue mutableIssue){
	 try {
			ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.debug("index issue" + ie.getMessage());
		}
	}
	@EventListener
	public void onReleaseEvent(VersionDeleteEvent versionEvent) {
		Long versionId = versionEvent.getVersionId();
		VersionManager versionManager = ComponentAccessor.getVersionManager();
		Version v = versionManager.getVersion(versionId);
		logger.debug("version name: " + v.getName());
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
}
