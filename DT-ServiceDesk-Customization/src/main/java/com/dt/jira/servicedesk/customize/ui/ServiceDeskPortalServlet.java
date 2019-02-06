package com.dt.jira.servicedesk.customize.ui;

import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.dt.jira.servicedesk.customize.ao.ServiceDesk;
import com.dt.jira.servicedesk.customize.service.ServiceDeskService;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskFields;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * ServiceDesk PortalServlet
 * @author srinadh.garlapati
 *
 */
public class ServiceDeskPortalServlet extends HttpServlet{

	private final TemplateRenderer renderer;
	private final ApplicationProperties applicationProperties;	
	private final JiraBaseUrls jiraBaseUrls;
	private final ServiceDeskService serviceDeskService;
	
	public ServiceDeskPortalServlet(TemplateRenderer renderer,
			ApplicationProperties applicationProperties,
			JiraBaseUrls jiraBaseUrls, ServiceDeskService serviceDeskService) {
		this.applicationProperties=applicationProperties;
		this.renderer = renderer;
		this.jiraBaseUrls = jiraBaseUrls;
		this.serviceDeskService=serviceDeskService;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {					 
			
			final String servicedeskName = (String) request.getParameter("name");
              final String servicedeskDescription = (String) request.getParameter("description");
              
				 Map<String, Object> ctx = new HashMap<String, Object>();
				 //find  groups by service desk name.
				 List<ServiceDesk> serviceDeskGroupsList = serviceDeskService.findGroupsByServiceDesk(servicedeskName);
				
				if(serviceDeskGroupsList !=null && serviceDeskGroupsList.size()>0 ){
					Set<String> uniqueGroupSet = new HashSet<String>();
					for (ServiceDesk temp : serviceDeskGroupsList){
					String groups=temp.getGroups();
					if(groups.contains(",")){
					String[] grpArray=groups.split(",");
					for(String obj:grpArray){
					uniqueGroupSet.add(obj);
					}
					}else{
					uniqueGroupSet.add(groups);
					}
				
					ctx.put("serviceDeskGroups", uniqueGroupSet);				
				}
			}
					
				
			   final String groups=request.getParameter("groups");
			   if(groups!=null){
				   try {
				   //find service desk item by group.
					    List<ServiceDesk> serviceDeskList =  serviceDeskService.findItemsByServiceDesk(servicedeskName);
						List<ServiceDesk> serviceDeskItemsList =  new ArrayList<ServiceDesk>();
						if(serviceDeskList!=null){
						for(ServiceDesk serviceDeskObj:serviceDeskList){
						
						if(serviceDeskObj.getGroups().contains(",")){
						String[] grpList=serviceDeskObj.getGroups().split(",");
						for(String obj:grpList){
						if(obj.equals(groups)){
						serviceDeskItemsList.add(serviceDeskObj);
						      }
						    }
						}else{
						if(serviceDeskObj.getGroups().equals(groups)){
						serviceDeskItemsList.add(serviceDeskObj);
						         }
						       }
						    }
						}
					   
					   	if(serviceDeskItemsList !=null && serviceDeskItemsList.size()>0 ){												
								ctx.put("serviceDeskItems", serviceDeskItemsList);					
							}
							
					} catch (Exception e) {
						e.printStackTrace();
					}
			   }  
			   
				  ctx.put("servicedeskName", servicedeskName);
				  ctx.put("servicedeskDescription", servicedeskDescription);
				  
				   response.setContentType("text/html;charset=utf-8");
				renderer.render("templates/servicedeskchild.vm",ctx,response.getWriter());
						
	}
}
