package com.dt.jira.plugin.changeRisk.calculator.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;

import com.atlassian.jira.issue.fields.screen.FieldScreen;
import com.atlassian.jira.issue.fields.screen.FieldScreenTab;
import com.atlassian.jira.issue.fields.screen.FieldScreenLayoutItem;
import com.atlassian.jira.issue.context.GlobalIssueContext;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import org.apache.log4j.Logger;
//import com.atlassian.jira.issue.managers.DefaultCustomFieldManager;
import org.slf4j.LoggerFactory;


/**
 * RiskCalculationServlet loads questions and answers.
 * @author srinadh.garlapati
 *
 */
public class RiskCalculationServlet extends HttpServlet{

	private final TemplateRenderer renderer;
	private final ApplicationProperties applicationProperties;	
	private final JiraBaseUrls jiraBaseUrls;
	private final Logger logger = Logger.getLogger(RiskCalculationServlet.class);
	private final String RISK_SCREEN="JIRA IT Service Management Ref Model Create Screen V1.0";
	private final String CUSTOM_COMPONENT_RISK="Risk";
	private final CustomFieldManager customFieldManager;
	public RiskCalculationServlet(TemplateRenderer renderer,
								  ApplicationProperties applicationProperties,
								  JiraBaseUrls jiraBaseUrls, CustomFieldManager customFieldManager) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.jiraBaseUrls = jiraBaseUrls;
		this.customFieldManager = ComponentAccessor.getCustomFieldManager();
	}
	
	
	/**
	 * doGetmethod
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			logger.debug("Within Risk Calculation Servlet");
			Map<String, Object> ctx = new HashMap<String, Object>();
			Collection<FieldScreen> fieldScreenList=ComponentAccessor.getFieldScreenManager().getFieldScreens();
			//finding feild screen from list by name
			FieldScreen requiredscreen=null;
			for(FieldScreen screen: fieldScreenList){
				if(screen.getName().equals(RISK_SCREEN)){
					requiredscreen=screen;
					break;
				}
			}
			FieldScreenTab screentab=requiredscreen.getTab(5) ;
			List<FieldScreenLayoutItem> layoutItems=screentab.getFieldScreenLayoutItems();
			/*CustomFieldManager cfManager = ComponentManager.getInstance()
					.getCustomFieldManager();*/
			CustomFieldManager cfManager = this.customFieldManager;
			List<String> questionsList=new ArrayList<String>();
			Map<String,List<Option>> optionsMap=new HashMap<String,List<Option>>();
			for(FieldScreenLayoutItem layout:layoutItems){
				CustomField cf=cfManager.getCustomFieldObject(layout.getFieldId());
				questionsList.add(cf.getFieldName());
				Options opts=cf.getOptions(layout.getFieldId(),GlobalIssueContext.getInstance());
				optionsMap.put(cf.getFieldName(),opts.getRootOptions());
			
			}
			
			CustomField riskCustomFeild = cfManager
					.getCustomFieldObjectByName(CUSTOM_COMPONENT_RISK);
			Options riskOptions = ComponentAccessor.getOptionsManager().getOptions(riskCustomFeild.getConfigurationSchemes().listIterator().next().getOneAndOnlyConfig());
			
			logger.info("-------options---------"+riskOptions);
			
			
			ctx.put("QustionsList", questionsList);	
			ctx.put("OptionsMap", optionsMap);
			ctx.put("riskOptions", riskOptions.getRootOptions());
	        ctx.put("baseURL", applicationProperties.getBaseUrl());			
			response.setContentType("text/html;charset=utf-8");
			renderer.render("templates/riskCalculationConfig.vm", ctx, response.getWriter());
	}
}
