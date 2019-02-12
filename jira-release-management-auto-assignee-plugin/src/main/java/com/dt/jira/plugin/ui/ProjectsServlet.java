package com.dt.jira.plugin.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.jira.project.Project;
import com.atlassian.templaterenderer.TemplateRenderer;



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
			ctx.put("projectKey", request.getParameter("projectKey"));	
			ctx.put("baseURL", applicationProperties.getBaseUrl());			
			response.setContentType("text/html;charset=utf-8");
			renderer.render("templates/serviceDeskAutomation.vm", ctx, response.getWriter());
	}
}
