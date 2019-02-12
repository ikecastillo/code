package com.dt.jira.plugin.innotas.ui;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.Project;
 
public class MapServlet extends HttpServlet
{
	private final TemplateRenderer renderer;
	private final ProjectManager projectManager;
	public MapServlet(TemplateRenderer renderer, ProjectManager pm) {
		this.renderer = renderer;
		this.projectManager = pm;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		Map<String, Object> ctx = new HashMap<String, Object>();
		Project project = projectManager.getProjectObjByKey(request.getParameter("pkey"));
		ctx.put("project", project);
		request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);
		response.setContentType("text/html;charset=utf-8");
		renderer.render("templates/map.vm", ctx, response.getWriter());
	}
}
