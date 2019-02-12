package com.dt.jira.ldap.incident.integration.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
/**
 * The Controller Servlet for Incident Management LDAP Configuration
 *
 * @author Firoz Khan
 */
public class IncidentLdapServlet extends HttpServlet
{
	private final TemplateRenderer renderer;	
	private final ApplicationProperties applicationProperties;
	
	/**
     * Constructor
     * @param renderer the TemplateRenderer to be injected
     * @param applicationProperties the ApplicationProperties to be injected     
     */
	public IncidentLdapServlet(TemplateRenderer renderer,ApplicationProperties applicationProperties) {
		this.renderer = renderer;
		this.applicationProperties=applicationProperties;
	}
	
	/**
     * Service for LDAP Configuration view    
     */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("baseURL", applicationProperties.getBaseUrl());
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/addIncidentLdapConfig.vm", ctx, response.getWriter());
	}
}
