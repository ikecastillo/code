package com.dt.jira.plugin.autopopulatemanager.fnideveops.ui;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
 /**
 * LDAP configuration UI
 */
 @Named("LDAPConfigServlet")
public class LdapServlet extends HttpServlet
{
	@ComponentImport
	private final TemplateRenderer renderer;
	@ComponentImport
	private final ApplicationProperties applicationProperties;

	@Inject
	public LdapServlet(TemplateRenderer renderer,ApplicationProperties applicationProperties) {
		this.renderer = renderer;
		this.applicationProperties=applicationProperties;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("baseURL", applicationProperties.getBaseUrl());
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/LdapService.vm", ctx, response.getWriter());
		//renderer.render("templates/LdapService.vm", response.getWriter());
	}
}
