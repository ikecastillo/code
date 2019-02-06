package com.dt.jira.assigneemap.plugins.ui;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.jira.user.ApplicationUser;
import org.apache.log4j.Logger;

import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.JiraServiceContext;
import com.atlassian.jira.bc.JiraServiceContextImpl;
import com.atlassian.jira.bc.user.search.UserPickerSearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.util.I18nHelper;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMap;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMapService;
import com.dt.jira.plugin.event.ComponentChangeEventListener;

/**
 * The Controller Servlet for Assignee Map within Change Management Project
 *
 * @author Firoz.Khan
 */ 
public class AssigneeMapServlet extends HttpServlet
{
	private final Logger logger = Logger.getLogger(AssigneeMapServlet.class);
	private final TemplateRenderer renderer;
	private final ProjectManager projectManager;
	private final ApplicationProperties applicationProperties;	
	private final AssigneeMapService assigneeMapService;
	
	public AssigneeMapServlet(TemplateRenderer renderer, ProjectManager projectManager,ApplicationProperties applicationProperties,AssigneeMapService assigneeMapService) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.projectManager = projectManager;
		this.assigneeMapService = assigneeMapService;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
			Map<String, Object> ctx = new HashMap<String, Object>();
			Project project = projectManager.getProjectObjByKey(request.getParameter("pkey"));			
			logger.info("****************** Assignee Map Project Name ******************"+project.getName());
			 
	        String projectKey = project.getKey();
	        logger.info("******************Assignee Map Project Key******************"+projectKey);

	        CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();     
			
	        //Project project1 = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
			
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);			
			//reverted back the context related change to previous state
			/*IssueType changeIssueType= null;         
			Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
			for(IssueType issueT: issueTypesProj){
				 if(!issueT.isSubTask()){ // exclude sub-task
						changeIssueType = issueT;
				 }
			}*/
			logger.info("****************** Assignee Map Issue Type for Project ******************"+issueType);  
			CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName("Solution Group - Product" );
			//reverted back the context related change to previous state
			//FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
			
			FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
			List<Option> solutionGroupEnable = new ArrayList<Option>();
			if(solutionGroup != null){
				for(Option solopt : solutionGroup){ 	
					if(!solopt.getDisabled()){
						solutionGroupEnable.add(solopt);
					}
				}
			}
			 logger.info("****************** Assignee Map Enable Solution ******************"+solutionGroupEnable);
						
	        CustomField customFieldImpcated = customFieldManager.getCustomFieldObjectByName("Impacted - Function" );	
			//reverted back the context related change to previous state
			//FieldConfig fieldConfigImpacted = customFieldImpcated.getRelevantConfig(new IssueContextImpl(project, changeIssueType));	
			
			FieldConfig fieldConfigImpacted = customFieldImpcated.getRelevantConfig(new IssueContextImpl(project, issueType));			
			Options impacted = optionsManager.getOptions(fieldConfigImpacted);
			List<Option> impactedEnable = new ArrayList<Option>();
			if(impacted != null) {				
				for(Option impactopt : impacted){ 	
					if(!impactopt.getDisabled()){
						impactedEnable.add(impactopt);
					}
				}
			}
			 logger.info("****************** Assignee Map Enable Impacted ******************"+impactedEnable);
			
			Map<String, String> statusMap = new HashMap<String,String>();
		    I18nHelper i18n = ComponentAccessor.getJiraAuthenticationContext().getI18nHelper();
		    Collection<Status> allStatuses = ComponentAccessor.getConstantsManager().getStatusObjects();
		    logger.info("******************Statuses******************"+allStatuses);
		    for (Status status : allStatuses) {
		       	logger.info("********Status Id******"+status.getId().toString()+"********Status Name******"+status.getNameTranslation(i18n));
		        statusMap.put(status.getId().toString(), status.getNameTranslation(i18n));
		    }	      
		   		    
	        Set<ApplicationUser> users = ComponentAccessor.getUserManager().getAllUsers();
	        Map<String, String> userMap = new HashMap<String, String>();
	        for (ApplicationUser user: users) {
	        	userMap.put(user.getName(), user.getDisplayName());
	        }	        
	       
	        List<AssigneeMap> assigneeMapList = assigneeMapService.getAssigneeMappingByProjectKey(projectKey);
	        logger.info("**********Default Selected Value*************");
	        if(assigneeMapList !=null && assigneeMapList.size()>0 ){
				AssigneeMap im= assigneeMapList.get(0);
				logger.info("*****Project Key****"+im.getProjectKey() +"****SolutionGroup****"+im.getSolutionGroup()+"****Impacted****"+im.getImpact()+"****Status****"+im.getStatus()+"****User****"+im.getUsers());								
				ctx.put("assigneeMapList", assigneeMapList);				
	        }	        
	       	        
	        ctx.put("baseURL", applicationProperties.getBaseUrl());
	        ctx.put("projectKey", projectKey);
	        ctx.put("solutionGroup", solutionGroupEnable);
	        ctx.put("impacted", impactedEnable);
	        ctx.put("statusMap", statusMap);
	        ctx.put("userMap", userMap);		
			ctx.put("project", project);			
			
			ctx.put("selectedImpact",null);
			ctx.put("selectedSolution",null);
						
			request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);
			response.setContentType("text/html;charset=utf-8");
			renderer.render("templates/tabpanels/assignee-map-tab-panel.vm", ctx, response.getWriter());
	}
	
}
