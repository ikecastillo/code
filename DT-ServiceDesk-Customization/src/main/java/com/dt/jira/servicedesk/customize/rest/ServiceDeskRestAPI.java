package com.dt.jira.servicedesk.customize.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.servicedesk.customize.ao.ServiceDesk;
import com.dt.jira.servicedesk.customize.ao.ServiceDeskMain;
import com.dt.jira.servicedesk.customize.service.ServiceDeskService;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskFields;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskMainFields;


/**
 * The REST Service for Automation Portal 
 *
 * @author Srinadh.Garlapati
 */
@Path("/ServiceDeskField")
public class ServiceDeskRestAPI
{
		private final Logger logger = Logger.getLogger(ServiceDeskRestAPI.class);
		private final ServiceDeskService servicedeskservice;
		private final UserManager userManager;
	/**
	 * Constructor
	 * @param portalService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public ServiceDeskRestAPI(ServiceDeskService servicedeskservice, UserManager userManager)
		 {
		   this.servicedeskservice = servicedeskservice;
		   this.userManager = userManager;
		 }
		

		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addServiceDeskField")
		public Response put(@QueryParam("icon") String icon ,@QueryParam("item") String item,@QueryParam("groups") String groups ,@QueryParam("url") String url,@QueryParam("servicedesks") String servicedesks, @Context HttpServletRequest request)
		{
			logger.info("------ServiceDeskRestAPI---------"+icon+">>>"+item+">>>"+groups+">>>"+url+">>>"+servicedesks);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			ServiceDesk servicedesk=servicedeskservice.create(icon, item,groups,url,servicedesks);
			logger.info("------ServiceDesk created---------");
			if(servicedesk!=null){
				logger.info("------ServiceDesk is not null---------");
				List<ServiceDeskFields> ServiceDeskFieldsList=getAllServiceDeskFields(icon, item,groups,url,servicedesks);
				logger.info(ServiceDeskFieldsList);
				logger.info("------ServiceDesk added to response---------");
				return Response.ok(ServiceDeskFieldsList).build();
			}
			return Response.noContent().build();
		}
		
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/updateServiceDeskField")
		public Response update(@QueryParam("icon") String icon ,@QueryParam("item") String item,@QueryParam("groups") String groups ,@QueryParam("url") String url,@QueryParam("servicedesks") String servicedesks,@QueryParam("serviceID") String serviceID, @Context HttpServletRequest request)
		{
			logger.info("------ServiceDeskRestAPI---------"+icon+">>>"+item+">>>"+groups+">>>"+url+">>>"+servicedesks);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			ServiceDesk servicedesk=servicedeskservice.update(icon, item,groups,url,servicedesks,Integer.parseInt(serviceID));
			logger.info("------ServiceDesk created---------");
			if(servicedesk!=null){
				logger.info("------ServiceDesk is not null---------");
				List<ServiceDeskFields> ServiceDeskFieldsList=getAllServiceDeskFields(icon, item,groups,url,servicedesks);
				logger.info(ServiceDeskFieldsList);
				logger.info("------ServiceDesk added to response---------");
				return Response.ok(ServiceDeskFieldsList).build();
			}
			return Response.noContent().build();
		}
		
		
		
		
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addServiceDeskMainField")
		public Response put(@QueryParam("name") String name,@QueryParam("description") String description, @Context HttpServletRequest request)
		{
			 logger.info("------ServiceDeskRestAPI---------"+name+">>>"+description);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			ServiceDeskMain servicedeskmain=servicedeskservice.create(name,description);
			logger.info("------ServiceDeskMain created---------");
			if(servicedeskmain!=null){
				logger.info("------ServiceDeskMain is not null---------");
				List<ServiceDeskMainFields> ServiceDeskMainFieldsList=getAllServiceDeskMainFields(name,description);
				logger.info(ServiceDeskMainFieldsList);
				logger.info("------ServiceDesk added to response---------");
				return Response.ok(ServiceDeskMainFieldsList).build();
			}
			return Response.noContent().build();
		}
		
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/updateServiceDeskMainField")
		public Response put(@QueryParam("name") String name,@QueryParam("description") String description, @QueryParam("service_id") String service_id, @Context HttpServletRequest request)
		{
			 logger.info("------ServiceDeskRestAPI---------"+name+">>>"+description);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			ServiceDeskMain servicedeskmain=servicedeskservice.update(name,description,Integer.parseInt(service_id));
			logger.info("------ServiceDeskMain created---------");
			if(servicedeskmain!=null){
				logger.info("------ServiceDeskMain is not null---------");
				List<ServiceDeskMainFields> ServiceDeskMainFieldsList=getAllServiceDeskMainFields(name,description);
				logger.info(ServiceDeskMainFieldsList);
				logger.info("------ServiceDesk added to response---------");
				return Response.ok(ServiceDeskMainFieldsList).build();
			}
			return Response.noContent().build();
		}
		
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deleteServiceDeskField")
		public Response delete(@QueryParam("icon") String icon ,@QueryParam("item") String item ,@QueryParam("groups") String groups ,@QueryParam("url") String url,@QueryParam("servicedesks") String servicedesks, @Context HttpServletRequest request)
		{
			logger.info("------ServiceDeskRestAPIdelete---------");
			/* logger.info("--------IssueResource---project--------------- "+project); */
			servicedeskservice.delete(icon, item,groups,url,servicedesks);
			logger.info("------ServiceDesk Deleted---------");
			List<ServiceDeskFields> ServiceDeskFieldsList=getAllServiceDeskFields(icon, item,groups,url,servicedesks);
			logger.info(ServiceDeskFieldsList);
				return Response.ok(ServiceDeskFieldsList).build();
			
		}
		
		
		
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deleteServiceDeskMainField")
		public Response delete(@QueryParam("name") String name,@QueryParam("description") String description, @Context HttpServletRequest request)
		{
			logger.info("------ServiceDeskRestAPIdelete---------");
			/* logger.info("--------IssueResource---project--------------- "+project); */
			servicedeskservice.delete(name,description);
			logger.info("------ServiceDeskMain Deleted---------");
			List<ServiceDeskMainFields> ServiceDeskMainFieldsList=getAllServiceDeskMainFields(name,description);
			logger.info(ServiceDeskMainFieldsList);
				return Response.ok(ServiceDeskMainFieldsList).build();
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getServiceDeskFields")
		public Response getAllServiceDeskFields() {
			logger.info("------ServiceDeskRestAPIFetchingFields---------");
        return Response.ok(getAllServiceDesksFromDB()).build();
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getServiceDeskGroups")
		public Response getAllServiceDeskGroups(@QueryParam("servicedeskName") String servicedeskName) {
		 return Response.ok(getAllServiceDesksFromDB()).build();
		}
		
		
		
		
		/**
		 * getAllServiceDesks From DB
		 * @return
		 */
		private List<ServiceDeskFields> getAllServiceDesksFromDB() {
			List<ServiceDeskFields> ServiceDeskFieldsList=new ArrayList<ServiceDeskFields>();
			logger.info("------Pre Finding Fields---------");
			List<ServiceDesk> xList=servicedeskservice.findAll();
			logger.info(xList);
			logger.info("------Pre Getting Fields---------");
			for(ServiceDesk servicedesk: xList){
				ServiceDeskFields serviceDeskFields=new ServiceDeskFields(servicedesk.getIcon(),servicedesk.getItem(),servicedesk.getGroups(),servicedesk.getUrl(),servicedesk.getServiceDesks());
				serviceDeskFields.setServiceID(servicedesk.getID());
				ServiceDeskFieldsList.add(serviceDeskFields);
			} 
			logger.info(ServiceDeskFieldsList);
			return ServiceDeskFieldsList;
		}
		
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getServiceDeskMainFields")
		public Response getAllServiceDeskMainFields() {
			logger.info("------ServiceDeskRestAPIFetchingFields---------");
        return Response.ok(getAllServiceDesksMainFromDB()).build();
		}
		
		private List<ServiceDeskMainFields> getAllServiceDesksMainFromDB() {
			List<ServiceDeskMainFields> ServiceDeskMainFieldsList=new ArrayList<ServiceDeskMainFields>();
			logger.info("------Pre Finding Fields---------");
			List<ServiceDeskMain> xList=servicedeskservice.findAllMainFields();
			logger.info(xList);
			logger.info("------Pre Getting Fields---------"+xList.size());
			for(ServiceDeskMain servicedeskmain: xList){
				ServiceDeskMainFields serviceDeskMainFields=new ServiceDeskMainFields(servicedeskmain.getName(),servicedeskmain.getDescription());
				serviceDeskMainFields.setService_id(servicedeskmain.getID());
				ServiceDeskMainFieldsList.add(serviceDeskMainFields);
			}
			logger.info(ServiceDeskMainFieldsList);
			return ServiceDeskMainFieldsList;
		}
		
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/cancelServiceDeskMainField")
		public Response getAllServiceDeskMainFieldsList() {
			logger.info("------ServiceDeskRestAPIFetchingFieldsLIst---------");
        return Response.ok(getAllServiceDesksMainFromDB()).build();
		}
		
        private List<ServiceDeskFields> getAllServiceDeskFields(String icon,String item,String groups,String url,String servicedesks){
			
			List<ServiceDeskFields> ServiceDeskFieldsList=new ArrayList<ServiceDeskFields>();
			logger.info("------Finding Fields---------");
			List<ServiceDesk> xList=servicedeskservice.findAll();
			logger.info(xList);
			logger.info("------Getting Fields---------");
			for(ServiceDesk servicedesk: xList){
				ServiceDeskFields serviceDeskFields=new ServiceDeskFields(servicedesk.getIcon(),servicedesk.getItem(),servicedesk.getGroups(),servicedesk.getUrl(),servicedesk.getServiceDesks());
				serviceDeskFields.setServiceID(servicedesk.getID());
				ServiceDeskFieldsList.add(serviceDeskFields);	
			}
			logger.info(ServiceDeskFieldsList);
			return ServiceDeskFieldsList;
			
		}
		
		
		/**
		 * getAllServiceDesk MainFields
		 * @param name
		 * @param description
		 * @return
		 */
		private List<ServiceDeskMainFields> getAllServiceDeskMainFields(String name,String description){
			
			List<ServiceDeskMainFields> ServiceDeskMainFieldsList=new ArrayList<ServiceDeskMainFields>();
			logger.info("------Finding Fields---------");
			List<ServiceDeskMain> xList=servicedeskservice.findAllMainFields();
			logger.info(xList);
			logger.info("------Getting Fields---------");
			for(ServiceDeskMain servicedeskmain: xList){
				ServiceDeskMainFields serviceDeskMainFields=new ServiceDeskMainFields(servicedeskmain.getName(),servicedeskmain.getDescription());
				serviceDeskMainFields.setService_id(servicedeskmain.getID());
				ServiceDeskMainFieldsList.add(serviceDeskMainFields);
			}
			logger.info(ServiceDeskMainFieldsList);
			return ServiceDeskMainFieldsList;
			
		}

	


}