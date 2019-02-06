package com.dt.jira.servicedesk.customize.ui;
 
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
 * LdapServlet
 * @author srinadh.garlapati
 *
 */
public class LdapServlet extends HttpServlet
{
	private final TemplateRenderer renderer;	
	private final ApplicationProperties applicationProperties;
	public LdapServlet(TemplateRenderer renderer,ApplicationProperties applicationProperties) {
		this.renderer = renderer;
		this.applicationProperties=applicationProperties;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("baseURL", applicationProperties.getBaseUrl());
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/SLdapService.vm", ctx, response.getWriter());
	}
}
