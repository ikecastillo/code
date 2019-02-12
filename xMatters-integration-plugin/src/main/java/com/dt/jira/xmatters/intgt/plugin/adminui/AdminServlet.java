package com.dt.jira.xmatters.intgt.plugin.adminui;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.atlassian.templaterenderer.TemplateRenderer;
 
public class AdminServlet extends HttpServlet
{
	private final TemplateRenderer renderer;	 
	public AdminServlet(TemplateRenderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/admin/admin.vm", response.getWriter());
	}
}
