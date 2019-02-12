package com.dt.jira.plugin.innotas.config;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.atlassian.templaterenderer.TemplateRenderer;
 
public class ConfigServlet extends HttpServlet
{
	private final TemplateRenderer renderer;	 
	public ConfigServlet(TemplateRenderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/config.vm", response.getWriter());
	}
}
