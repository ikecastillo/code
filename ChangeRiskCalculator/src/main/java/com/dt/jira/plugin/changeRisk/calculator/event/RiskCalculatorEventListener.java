package com.dt.jira.plugin.changeRisk.calculator.event;

import java.util.Collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.issue.fields.screen.FieldScreen;
import com.atlassian.jira.issue.fields.screen.FieldScreenTab;
import com.atlassian.jira.issue.fields.screen.FieldScreenLayoutItem;
import com.dt.jira.plugin.changeRisk.calculator.ao.RiskCalculation;
import com.dt.jira.plugin.changeRisk.calculator.service.RiskCalculationService;
import com.atlassian.jira.issue.index.IssueIndexingService;
//import com.atlassian.jira.issue.managers.DefaultCustomFieldManager;

/**
 * RiskCalculator EventListener  
 * This class estimates risk based on 
 * Questions and answers given in Risk Questionnaire Tab.
 * 
 * @author srinadh.garlapati
 */
public class RiskCalculatorEventListener implements InitializingBean,
		DisposableBean {

	private final EventPublisher eventPublisher;
	
	private final RiskCalculationService  riskCalculationService;
	
	private final OptionsManager optionsManager;
	
	private final Logger logger = Logger.getLogger(RiskCalculatorEventListener.class);
	
	private final String RISK_QUESTIONNAIRE_SCREEN= "JIRA IT Service Management Ref Model Create Screen V1.0";

	private final String PROJECT_KEY="CHG";
	
	private final String PROJECT_NAME="Change";
	
	private final String DEFAULT_RESULT="Low";
	
	private final String CUSTOM_COMPONENT_NAME="Risk";
	
	private final String CONDITION_AND="And";
	
	private final String CONDITION_OR="Or";
	private final IssueIndexingService issueIndexingService;
	private final CustomFieldManager customFieldManager;
	
	public RiskCalculatorEventListener(EventPublisher eventPublisher, RiskCalculationService riskCalculationService, OptionsManager optionsManager, IssueIndexingService issueIndexingService,
									   CustomFieldManager customFieldManager){
		this.eventPublisher = eventPublisher;
		this.riskCalculationService=riskCalculationService;
		this.optionsManager=optionsManager;
		this.issueIndexingService = issueIndexingService;
		this.customFieldManager = ComponentAccessor.getCustomFieldManager();
	}
	
	/**
	 * Get All questions based on screen name.
	 * 
	 * @return List<String>
	 */
	private List<String> getQuestionsList(){
		Collection<FieldScreen> fieldScreenList=ComponentAccessor.getFieldScreenManager().getFieldScreens();
		//finding feild screen from list by name
		FieldScreen requiredscreen=null;
		//get Fieldscreen objects  on screen name 
		for(FieldScreen screen: fieldScreenList){
			if(screen.getName().equals(RISK_QUESTIONNAIRE_SCREEN)){
				requiredscreen=screen;
				break;
			}
		}
		//getTab(5) as risk questionnaire tab on position 5.
		FieldScreenTab screentab=requiredscreen.getTab(5) ;
		List<FieldScreenLayoutItem> layoutItems=screentab.getFieldScreenLayoutItems();
		/*CustomFieldManager cfManager = ComponentManager.getInstance()
				.getCustomFieldManager();*/
		CustomFieldManager cfManager = this.customFieldManager;
		List<String> questionsList=new ArrayList<String>();
		//get question from each FieldScreenLayoutItem.
		for(FieldScreenLayoutItem layout:layoutItems){
			CustomField cf=cfManager.getCustomFieldObject(layout.getFieldId());
			questionsList.add(cf.getFieldName());
			}
		return questionsList;
	}

	/**
	 * Event Listener calculates risk based on answers.  
	 * @param issueEvent
	 */
	@EventListener
	public void onIssueEvent(IssueEvent issueEvent) {
		logger.info("===========RiskCalculatorEventListener========onIssueEvent======called===========");
		Long eventTypeId = issueEvent.getEventTypeId();
		Issue issueParent = issueEvent.getIssue();
		String name = issueParent.getIssueTypeObject().getName();
		MutableIssue issue = (MutableIssue) issueParent;
		/*CustomFieldManager cfManager = ComponentManager.getInstance()
				.getCustomFieldManager();*/
		CustomFieldManager cfManager = this.customFieldManager;
		Project project = issueParent.getProjectObject();
		//this event is applicable for Project 'CHG' and Name 'Change'.
		if (project.getKey().equals(PROJECT_KEY) && name.equals(PROJECT_NAME)) {
			if ( eventTypeId.equals(EventType.ISSUE_UPDATED_ID) || eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID) ) {
				try {
					List<String> answersList=new ArrayList<String>();
					for(String question:getQuestionsList()){
						String answer=getValuesFromCustomComponent(question,issueParent);
						if(answer!=null){
						answersList.add(answer);
						}else{
						answersList.add("None");	
						}

					}
				String result=	calculateRisk(answersList);
				logger.info("result :"+result);
				if(emptyCheck(result)){
					//update issue with estimated Risk field.
					updateingIssue(issue,result);
				  }else{
					//update issue with risk field value as LOW.
					updateingIssue(issue,DEFAULT_RESULT);  
				  }
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		
			}
		}
		
		logger.info("===========RiskCalculatorEventListener========onIssueEvent======end===========");
	}
	
	/**
	 * Update Risk field with new estimated risk value.
	 * @param issue
	 * @param newRiskvalue
	 */
	private void updateingIssue(MutableIssue issue,String newRiskvalue){
		IssueManager imanager=ComponentAccessor.getIssueManager();
		JiraAuthenticationContext  authenticationContext  = ComponentAccessor.getJiraAuthenticationContext();
		/*CustomFieldManager cfManager = ComponentManager.getInstance()
				.getCustomFieldManager();*/
		CustomFieldManager cfManager = this.customFieldManager;
		CustomField riskCustomFeild = cfManager
				.getCustomFieldObjectByName(CUSTOM_COMPONENT_NAME);
		logger.info(riskCustomFeild.getValue(issue)+"------newRiskvalue------------------"+newRiskvalue);
		List<Option> optList=optionsManager.findByOptionValue(newRiskvalue);
		logger.info(optList+"-----------------------");
		if(optList!=null && (!optList.isEmpty())){
			logger.info("------------------"+optList.get(0));
			riskCustomFeild.updateValue(null, issue, new ModifiedValue(
					riskCustomFeild.getValue(issue),optList.get(0)), new DefaultIssueChangeHolder());
			issue.store();
			logger.info("-----new Option-------------"+optList.get(0).getValue());
		}
		
	}
	
	/**
	 * Calculate Risk based on answers list
	 * @param answersList
	 * @return
	 */
	private String calculateRisk(List<String> answersList){
	//getting all conditions configured in 'Risk Assessment Calculation Configuration >>> Addons' UI screen. 	
	List<RiskCalculation> riskConditions=riskCalculationService.findAll();
		for(RiskCalculation riskCondition: riskConditions){
			String condition= riskCondition.getCondition();
			logger.info(condition+"-------------------------------"+riskCondition.getID());
			 List<Boolean> booleanList=new ArrayList<Boolean>();
			if(answersList!=null) {
			if((answersList.size()>0) && emptyCheck(answersList.get(0))){
				booleanList.add(riskCondition.getQuestion1().equals(answersList.get(0)));	
			}
			if((answersList.size()>1) && emptyCheck(answersList.get(1))){
				booleanList.add(riskCondition.getQuestion2().equals(answersList.get(1)));	
				}
			if((answersList.size()>2) && emptyCheck(answersList.get(2))){
				booleanList.add(riskCondition.getQuestion3().equals(answersList.get(2)));	
				}
			/*if((answersList.size()>3) && emptyCheck(answersList.get(3))){ // As per the requirement, this is not consider for the Risk calculation
				booleanList.add(riskCondition.getQuestion4().equals(answersList.get(3)));	
				}*/
			}
			logger.info("Boolean List : "+booleanList);
			if(condition.equals(CONDITION_AND)){
			
				if(booleanList.size()>0 ){
					if(!booleanList.contains(false)){
						return 	riskCondition.getResult();
					}
					
				}
				
			}else if(condition.equals(CONDITION_OR)){
				if(booleanList.size()>0 && booleanList.contains(true)){
					return 	riskCondition.getResult();	
				}	
			}
		 }
		 
	return null;	
		
	}
	
	/**
	 * This method verify the given text is empty or null.
	 * @param text
	 * @return
	 */
	private boolean emptyCheck(String text){
		if(text==null || text.isEmpty()){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * get values from Custom Component.
	 * @param componentKey
	 * @param issueParent
	 * @return
	 */
	private String getValuesFromCustomComponent(String componentKey,Issue issueParent){
		logger.info(componentKey+"-------componentKey----------------issueParent----------------------"+issueParent);
		/*CustomFieldManager cfManager = ComponentManager.getInstance()
				.getCustomFieldManager();*/
		CustomFieldManager cfManager = this.customFieldManager;
				logger.info("-----------------------cfManager----------------------"+cfManager);
		CustomField hiddenText = cfManager
				.getCustomFieldObjectByName(componentKey);
		logger.info("---------------hiddenText------------------------------"+hiddenText);
		Option textFieldValue = (Option) hiddenText.getValue(issueParent);	
		logger.info("-----------------------textFieldValue----------------------"+textFieldValue);
		if(textFieldValue!=null){
		return ""+textFieldValue.getValue();
		}else{
			return null;
		}
	}
	
	
	private void setReindex(MutableIssue mutableIssue) {
		try {
			// ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
			issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
			logger.debug("index issue" + ie.getMessage());
		}
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
