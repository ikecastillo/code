package com.dt.jira.servicedesk.customize.ui;

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
import com.atlassian.jira.project.Project;
import com.atlassian.templaterenderer.TemplateRenderer;

/**
 * ProjectsServlet populates projects and groups. 
 * @author srinadh.garlapati
 *
 */
public class ProjectsServlet extends HttpServlet{

	private final TemplateRenderer renderer;
	private final ApplicationProperties applicationProperties;	
	private final JiraBaseUrls jiraBaseUrls;
	
	public ProjectsServlet(TemplateRenderer renderer,
			ApplicationProperties applicationProperties,
			JiraBaseUrls jiraBaseUrls) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.jiraBaseUrls = jiraBaseUrls;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
			Map<String, Object> ctx = new HashMap<String, Object>();
			//get all JIRA projects
			List<Project> listOfProjects = ComponentAccessor.getProjectManager().getProjectObjects();
			 if(listOfProjects !=null && listOfProjects.size()>0 ){												
				ctx.put("projectList", listOfProjects);				
	        }
	         //get all groups from JIRA
	         Collection<com.atlassian.crowd.embedded.api.Group> groupsList= ComponentAccessor.getGroupManager().getAllGroups() ;
	        System.out.println("-----------groupsList.size()----------"+groupsList.size());
	        List<String> groupList=new ArrayList<String>();
	        if(groupsList !=null && groupsList.size()>0 ){	
	        	 for(com.atlassian.crowd.embedded.api.Group group:groupsList){
	        		 groupList.add(group.getName()); 
	        	 }
	         ctx.put("groupList", groupList);	
	         }
	         
	         
	        ctx.put("baseURL", applicationProperties.getBaseUrl());			
	       
	        response.setContentType("text/html;charset=utf-8");
			renderer.render("templates/serviceDeskAutomation.vm", ctx, response.getWriter());
	}
}
