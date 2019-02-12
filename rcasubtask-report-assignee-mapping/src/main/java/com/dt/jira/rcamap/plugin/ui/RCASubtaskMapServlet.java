package com.dt.jira.rcamap.plugin.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.context.JiraContextNode;
import com.atlassian.jira.issue.context.ProjectContext;
import com.atlassian.jira.issue.context.manager.JiraContextTreeManager;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.customfields.view.CustomFieldParams;
import com.atlassian.jira.issue.customfields.view.CustomFieldParamsImpl;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutStorageException;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.dt.jira.rcamap.plugin.constants.RCASubtaskAssigneeMapConstants;

/**
 * The RCA Report Subtask Map Servlet for RCA Report Subtask Assignee Map on Problem Management project
 *
 * @author Firoz Khan
 */
public class RCASubtaskMapServlet extends HttpServlet
{
	private final Logger logger = Logger.getLogger(RCASubtaskMapServlet.class);
	private final TemplateRenderer renderer;
	private final ProjectManager projectManager;
	private final ApplicationProperties applicationProperties;
	private final OptionsManager optionsManager;
	private final JiraBaseUrls jiraBaseUrls;
    
	public RCASubtaskMapServlet(TemplateRenderer renderer, ProjectManager pm,
			ApplicationProperties applicationProperties,
			OptionsManager optionsManager,
			JiraBaseUrls jiraBaseUrls) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.projectManager = pm;
		this.optionsManager = optionsManager;
		this.jiraBaseUrls = jiraBaseUrls;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
			Map<String, Object> ctx = new HashMap<String, Object>();
			Project project = projectManager.getProjectObjByKey(request.getParameter("pkey"));
			if(project==null){
			project = projectManager.getProjectObjByKey(request.getParameter("projectKey"));
			}
			logger.info("****************** Project Key ******************"+project.getKey());
	        CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();  
	        
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);	
			
			IssueType changeIssueType= null;         
            Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
            for(IssueType issueTypes: issueTypesProj){
                   if(!issueTypes.isSubTask()){ // exclude sub-task
                          changeIssueType = issueTypes;
                   }
            }			
            logger.info("****************** Issue Type ******************"+changeIssueType);

	        CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_TYPE);
	        FieldConfig fieldConfigType = customFieldType.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
			Options types = optionsManager.getOptions(fieldConfigType);	
			
			List<Option> typesEnable = new ArrayList<Option>();
			List<Option> locationEnable = new ArrayList<Option>();
		    for(Option typeopt : types){ 	
		    	if(!typeopt.getDisabled()){		    		
		    		typesEnable.add(typeopt);
		    		
		    	}
		    }
		    
            ProjectManager projectManager = ComponentAccessor.getProjectManager();
            ConstantsManager constantsManager = ComponentAccessor.getConstantsManager();
            CustomFieldType aCustomFieldType = customFieldType.getCustomFieldType(); 
            if (aCustomFieldType instanceof CascadingSelectCFType) 
            { 
                    CascadingSelectCFType cscft = (CascadingSelectCFType) aCustomFieldType; 
                    JiraContextNode jcn = new ProjectContext(project,new JiraContextTreeManager(projectManager, constantsManager)); 
                    FieldConfig fldConf = customFieldType.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
                    Options options = cscft.getOptions(fldConf, jcn); 
                    Long aChildId = null;
                    Option parentOption = null;
                    Option childOption = null;
                    for (Iterator iterator2 = options.iterator(); iterator2.hasNext();) 
                    { 
                            Option aParentOption = (Option) iterator2.next(); 
                            Long aParentId = aParentOption.getOptionId(); 
                            List childOpts = aParentOption.getChildOptions(); 
                            
							if (aParentOption.getValue().equalsIgnoreCase(aParentOption.getValue())) 
                            { 
                                    parentOption = aParentOption; 
                            } 
                            if (parentOption != null) 
                            {                             
								for (Iterator childIt = childOpts.iterator(); childIt.hasNext();) 
	                            { 
                                    Option aChildOption = (Option) childIt.next(); 
                                    aChildId = aChildOption.getOptionId(); 
                                    locationEnable.add(aChildOption);
	                                    if (aChildOption.getValue().equalsIgnoreCase(aChildOption.getValue())) 
	                                    { 
	                                            childOption = aChildOption; 
	                                    } 
	                            } 
                            } 
                    } 
            } 
			CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
			FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
			
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
			List<Option> solutionGroupEnable = new ArrayList<Option>();
		    for(Option solopt : solutionGroup){ 	
		    	if(!solopt.getDisabled()){
		    		
		    		solutionGroupEnable.add(solopt);
		    	}
		    }		    
		    CustomField customFieldImpact = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
			FieldConfig fieldConfigImpact = customFieldImpact.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
		   
			Options impactGroup = optionsManager.getOptions(fieldConfigImpact);	
			List<Option> impactGroupEnable = new ArrayList<Option>();
		    for(Option impopt : impactGroup){ 	
		    	if(!impopt.getDisabled()){
		    		impactGroupEnable.add(impopt);
		    	}
		    }
		    logger.info("****************** Incident Approval Map Enable Solutions ******************"+solutionGroupEnable);
		    	         
	        ctx.put("baseURL", applicationProperties.getBaseUrl());
	        ctx.put("projectKey",  project.getKey());
	        ctx.put("typesGroup", typesEnable);
	        ctx.put("solutionGroup", solutionGroupEnable);
	        ctx.put("locationGroup", locationEnable);	        
			request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);
			response.setContentType("text/html;charset=utf-8");
			renderer.render("templates/rcasubtask-report-assignee-mapping.vm", ctx, response.getWriter());
	}

}
