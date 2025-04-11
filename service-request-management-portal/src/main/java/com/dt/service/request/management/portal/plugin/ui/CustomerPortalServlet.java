package com.dt.service.request.management.portal.plugin.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;

/**
 * This servlet is used for portal controller service implementation.
 * 
 * @author Firoz.Khan
 */
public class CustomerPortalServlet extends HttpServlet{

	private final TemplateRenderer renderer;
	private final ApplicationProperties applicationProperties;	
	private final CustomerPortalService customerPortalService;
	private final JiraBaseUrls jiraBaseUrls;
	
	public CustomerPortalServlet(TemplateRenderer renderer,
			ApplicationProperties applicationProperties,
			CustomerPortalService customerPortalService,
			JiraBaseUrls jiraBaseUrls) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.customerPortalService = customerPortalService;
		this.jiraBaseUrls = jiraBaseUrls;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
			Map<String, Object> ctx = new HashMap<String, Object>();
	        List<CustomerPortal> customerPortalList = customerPortalService.findAllParents();
	        if(customerPortalList !=null && customerPortalList.size()>0 ){												
				ctx.put("customerPortals", customerPortalList);				
	        }
	        
	       final String parentId = (String) request.getParameter("parentId");
	       System.out.println("------------------parentId"+parentId);
	       if(parentId!=null){
			   try {
				   List<CustomerPortal> CustomerPortalModelList =  customerPortalService.findAllChild(Integer.valueOf(parentId));
						if(CustomerPortalModelList !=null && CustomerPortalModelList.size()>0 ){												
							ctx.put("CustomerPortalModelList", CustomerPortalModelList);
							ctx.put("parentId", parentId);		
				        }
						
				} catch (Exception e) {
					e.printStackTrace();
				}
	       }       
	        ctx.put("baseURL", applicationProperties.getBaseUrl());			
						
			response.setContentType("text/html;charset=utf-8");
			// Use the Auzi-branded template
			renderer.render("templates/auzi-customer-portal.vm", ctx, response.getWriter());
	}
	
}
