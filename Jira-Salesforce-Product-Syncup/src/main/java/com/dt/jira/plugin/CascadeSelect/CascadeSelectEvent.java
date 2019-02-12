package com.dt.jira.plugin.CascadeSelect;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To make the product dropdown as cascading select synchronized in
 * Jira and also how to automatically synchronize the master values 
 * as available\defined in Salesforce into Jira
 */
public class CascadeSelectEvent implements InitializingBean, DisposableBean {
	private EventPublisher publisher;
	private final IssueService issueService;
	private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final IssueIndexingService issueIndexingService;
	
	private static final Logger log = LoggerFactory.getLogger(CascadeSelectEvent.class);
	/* temporary Sync up fields */ 
	private static  final String SYNC_UP_SALESFORCE_PRODUCT="Salesforce Product";
	private static  final String SYNC_UP_SALESFORCE_CATEGORY="Salesforce Category";
	private static  final String SYNC_UP_SALESFORCE_SUB_CATEGORY="Salesforce Sub-Category";
	/* multilevel cascade custom field  */ 
	private static  final String FEILD_SOLUTION_GROUPS_PRODUCTS="Solution Groups - Products";
	
	

	public CascadeSelectEvent(EventPublisher publisher,
							  IssueService issueService,
							  JiraAuthenticationContext authenticationContext,
							  IssueLinkManager issueLinkManager, IssueIndexingService issueIndexingService) {
		this.publisher = publisher;
		this.issueService = issueService;
		this.authenticationContext = authenticationContext;
		this.issueLinkManager = issueLinkManager;

		this.issueIndexingService = issueIndexingService;
	}

	@EventListener
	public void onIssueEvent(IssueEvent event) {
		Long eventTypeId = event.getEventTypeId();
		Issue issueParent = event.getIssue();
		String name = issueParent.getIssueType().getName();
		MutableIssue issue = (MutableIssue) issueParent;
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		if (name.equals("Incident")) {
			if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
				try {

					ChangeHistoryManager changeHistoryManager = ComponentAccessor
							.getChangeHistoryManager();

					log.info("*********************cascade**** select******* update  ************** ");

					List<ChangeItemBean> cascadeSelectList = changeHistoryManager
							.getChangeItemsForField(issue,
									FEILD_SOLUTION_GROUPS_PRODUCTS);
					List<ChangeItemBean> hiddenTxtList = changeHistoryManager
							.getChangeItemsForField(issue, SYNC_UP_SALESFORCE_PRODUCT);
					int cascadeLength = cascadeSelectList.size();
					int cascadeIndex; 
					long tsTime1; /* Solution Groups - Products field*/
					ChangeItemBean cascadeitem;

					CustomField hiddenText = cfManager
							.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_PRODUCT);
					String textFieldValue = (String) hiddenText
							.getValue(issueParent);

					// Get Solution Groups - Products field modified time
					if (cascadeLength != 0) {
						cascadeIndex = (cascadeLength - 1);
						cascadeitem = cascadeSelectList.get(cascadeIndex);
						Timestamp casTimeStamp = cascadeitem.getCreated();
						tsTime1 = casTimeStamp.getTime();
					} else {
						tsTime1 = 0;
					}

					int hiddenLength = hiddenTxtList.size();

					long tsTime2; /* Salesforce Product*/
					ChangeItemBean textitem;

					// Get Salesforce Product field modified time
					if (hiddenLength != 0) {
						int hiddenIndex = (hiddenLength - 1);
						textitem = hiddenTxtList.get(hiddenIndex);
						Timestamp hiddenTimeStamp = textitem.getCreated();
						tsTime2 = hiddenTimeStamp.getTime();
					} else {
						tsTime2 = 0;
					}

					log.info("%%%%%%%%%%%%%%%%%%%%%%%% two times %%%%%%%%%%%%%%"
									+ tsTime1 + "%%%%%%%%%%%%%%" + tsTime2 + "%%%%%%%%%%%%%%" + textFieldValue);
					
					
					
					log.info("tsTime2 > tsTime1  : "+(tsTime2 > tsTime1)+"  tsTime2 == tsTime1 : "+(tsTime2 == tsTime1)+"  textFieldValue != null  : "+(textFieldValue != null));

					/*
					 * If tsTime2(Salesforce Product field) > tsTime1(Solution Groups
					 * - Products field) i.e update is happening from salesforce to
					 * jira
					 */

					if (tsTime2 > tsTime1 || tsTime2 == tsTime1
							|| (textFieldValue != null)) {
						setUpdateOptionValue(event, false);

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
				try {
					log.info("*****************cascade**** select******* create **************");
					setUpdateOptionValue(event, false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	
	

	/** Update the child option if Salesforce Product field is updated by CRM plugin */
	public void setUpdateOptionValue(IssueEvent event, boolean sameValue) {		
		IssueType issueType = event.getIssue().getIssueType();
		Issue issueParent = event.getIssue();
		MutableIssue issue = (MutableIssue) issueParent;
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField hiddenText = cfManager
				.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_PRODUCT);		
		CustomField category_hiddenText = cfManager
				.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_CATEGORY);		
		CustomField sub_category_hiddenText = cfManager
				.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_SUB_CATEGORY);
				
		CustomField cascadeSelect = cfManager
				.getCustomFieldObjectByName(FEILD_SOLUTION_GROUPS_PRODUCTS);
		OptionsManager optionsManager = ComponentAccessor
				.getComponentOfType(OptionsManager.class);
		List<Option> updatedCustomList = new ArrayList<Option>();
		//currently getting list<Option> instead map for cascade select
		List<Option> customList = (List<Option>) issue.getCustomFieldValue(cascadeSelect);		
		if (customList != null ) {
			//values from cascade select field Solution Groups - Product 
			Option solutionOption =(customList.size()>0)? (Option) customList.get(0):null; // Solution Group
			Option productOption =(customList.size()>1)? (Option) customList.get(1):null;  
			Option categoryOption = (customList.size()>2)?(Option) customList.get(2):null;
			Option subCategoryOption =(customList.size()>3)? (Option) customList.get(3):null;
		
			CustomField hiddenCategoryComponent = cfManager
					.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_CATEGORY);
			CustomField hiddenSubCategoryComponent = cfManager
					.getCustomFieldObjectByName(SYNC_UP_SALESFORCE_SUB_CATEGORY);
			
			//vales from hidden fields product
			String textFieldValue = (String) hiddenText.getValue(issueParent);			
			//vales from hidden fields category
			String category_textFieldValue = (String) category_hiddenText.getValue(issueParent);			
			//vales from hidden fields sub_category
			String sub_category_textFieldValue = (String) sub_category_hiddenText.getValue(issueParent);			
			if (textFieldValue != null && (!textFieldValue.isEmpty())) {				
			
			if (textFieldValue != null) {
				updatedCustomList.add(0,solutionOption);
				Option option=getOptionBasedonHiddenFeildText(solutionOption.getChildOptions(),textFieldValue,solutionOption.getOptionId(),issueParent);
				updatedCustomList.add(1,option);
			}
			
			
			if (category_textFieldValue != null) {
				Option parentOption=updatedCustomList.get(1);
				Option option=getOptionBasedonHiddenFeildText(parentOption.getChildOptions(),category_textFieldValue,parentOption.getOptionId(),issueParent);
				updatedCustomList.add(2,option);
			}
			
			
			if (sub_category_textFieldValue != null) {
				Option parentOption=updatedCustomList.get(2);
				Option option=getOptionBasedonHiddenFeildText(parentOption.getChildOptions(),sub_category_textFieldValue,parentOption.getOptionId(),issueParent);
				updatedCustomList.add(3,option);
			}
			
		issue.setCustomFieldValue(cascadeSelect, updatedCustomList);
		
		//reindex
		
		try {
			// ComponentAccessor.getIssueIndexManager().reIndex(issue);
			issueIndexingService.reIndex(issue);

		} catch (Exception ie) {
			ie.printStackTrace();
		}
		
		
		
		Object selectValue = cascadeSelect.getValue(issue);
		cascadeSelect.updateValue(null, issue, new ModifiedValue(
				selectValue, updatedCustomList), new DefaultIssueChangeHolder());
		issueParent.store();
		
		
		log.info("---------updated cascade value----------"+ cascadeSelect.getValue(issue));
		
		clearHiddenFeilds(sub_category_hiddenText,issue);		
		clearHiddenFeilds(category_hiddenText,issue);
		clearHiddenFeilds(hiddenText,issue);
		
		try {
			// ComponentAccessor.getIssueIndexManager().reIndex(issue);
			issueIndexingService.reIndex(issue);

		} catch (Exception ie) {
			ie.printStackTrace();
		}
		
		  }
		
		}
		
	}
	
	/***
	 * Method checks option exists for hidden field or ,if it does not exists in JIRA it will create in JIRA.
	 * if option does not exists in sales force it ignores.
	 * @param childOpt
	 * @param textFieldValue
	 * @param parentOptionId
	 * @param issueParent
	 * @return
	 */
	private Option getOptionBasedonHiddenFeildText(List<Option> childOpt,String textFieldValue,Long parentOptionId,Issue issueParent){
		String compare = null;
		compare = textFieldValue;
		boolean isOptionExist = false;
		//  Find existing child option
			for (Option childOptionValue : childOpt) {
				String childStr = childOptionValue.toString();
				if (childStr.equalsIgnoreCase(compare)) {
					isOptionExist = true;
					log.info("existing child option found: "+compare);
					return childOptionValue;					
				}
			}
		

		// add child option in JIRA
		if (!isOptionExist) {
			// add child option
			CustomField customFieldSolution = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(FEILD_SOLUTION_GROUPS_PRODUCTS);
			FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(issueParent.getProjectObject().getId() , issueParent.getIssueTypeId()));
			 OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 
			Option newOption= optionsManager.createOption(fieldConfigSolution,parentOptionId, new Long(childOpt.size()), textFieldValue) ;			
			try {
				// ComponentAccessor.getIssueIndexManager().reIndex(issueParent);
				issueIndexingService.reIndex(issueParent);
			} catch (Exception ie) {
				ie.printStackTrace();
			}
			log.info("  added new Option in "+ newOption);
			return 	newOption;
			
		}
		
		return 	null;
	}
	
	/**
	 * Clear Hidden Fields after updating to main fields.
	 * @param hiddenComponentText
	 * @param issue
	 */
	private void clearHiddenFeilds(CustomField hiddenComponentText,MutableIssue issue){

		try {

			long testId = hiddenComponentText.getIdAsLong();
			IssueService issueService = ComponentAccessor
					.getIssueService();

			issue.setCustomFieldValue(hiddenComponentText, new String(""));

			Map<String, ModifiedValue> modifiedFields = issue
					.getModifiedFields();

			FieldLayoutItem fieldLayoutItem = ComponentAccessor
					.getFieldLayoutManager()
					.getFieldLayout(issue)
					.getFieldLayoutItem(hiddenComponentText);

			DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();

			final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields
					.get(hiddenComponentText.getId());

			hiddenComponentText.updateValue(fieldLayoutItem, issue,
					modifiedValue, issueChangeHolder);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	
	}
	
	@Override
	public void destroy() throws Exception {
		this.publisher.unregister(this);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.publisher.register(this);

	}

}
