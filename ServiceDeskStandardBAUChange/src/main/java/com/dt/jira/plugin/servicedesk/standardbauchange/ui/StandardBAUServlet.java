package com.dt.jira.plugin.servicedesk.standardbauchange.ui;
 
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
 * StandardBAUServlet
 * @author srinadh.garlapati
 *
 */
public class StandardBAUServlet extends HttpServlet
{
	private final TemplateRenderer renderer;	
	private final ApplicationProperties applicationProperties;
	public StandardBAUServlet(TemplateRenderer renderer,ApplicationProperties applicationProperties) {
		this.renderer = renderer;
		this.applicationProperties=applicationProperties;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("baseURL", applicationProperties.getBaseUrl());
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/standardbauchange.vm", ctx, response.getWriter());
	}
}
