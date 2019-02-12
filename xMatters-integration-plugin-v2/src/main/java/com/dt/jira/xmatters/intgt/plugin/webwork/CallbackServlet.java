package com.dt.jira.xmatters.intgt.plugin.webwork;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import com.atlassian.templaterenderer.TemplateRenderer;

public class CallbackServlet extends HttpServlet
{
	//private final TemplateRenderer renderer;	 
	public CallbackServlet(/*TemplateRenderer renderer*/) {
		//this.renderer = renderer;
		//System.out.println("Callback servlet");
	}
	@Override
	public void init(){					 
		//System.out.println("Callback servlet");
		
		//renderer.render("templates/config.vm", response.getWriter());
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		response.setContentType("text/html;charset=utf-8");
		//System.out.println("Callback servlet");
		//renderer.render("templates/config.vm", response.getWriter());
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
		response.setContentType("text/html;charset=utf-8");
		//System.out.println("Callback servlet");
		//renderer.render("templates/config.vm", response.getWriter());
	}
}
