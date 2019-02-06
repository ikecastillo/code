package com.dt.jira.servicedesk.customize.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.servicedesk.customize.ao.AutomationPortal;
import com.dt.jira.servicedesk.customize.service.PortalService;
import net.java.ao.Entity;

/**
 * The REST Service for Automation Portal 
 *
 * @author Srinadh.Garlapati
 */
@Path("/automationportal")
public class IssueResource
{
		private final Logger logger = Logger.getLogger(IssueResource.class);
		private final PortalService portalService;
		private final UserManager userManager;
	/**
	 * Constructor
	 * @param portalService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public IssueResource(PortalService portalService, UserManager userManager)
		 {
		   this.portalService = portalService;
		   this.userManager = userManager;
		 }
		

		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addPortal")
		public Response put(@QueryParam("project") String project ,@QueryParam("issueType") String issueType ,@QueryParam("subTask") String subTask,@QueryParam("userdn") String userdn ,@QueryParam("password") String password ,@QueryParam("assignee") String assignee,@QueryParam("group") String group,  @Context HttpServletRequest request)
		{
			//System.out.println(portalService+"------group---------"+EncryptUtils.encodeLDAPDetails(password));
			logger.info("--------IssueResource---project--------------- "+project);
			AutomationPortal automation=portalService.create(project, issueType,subTask, userdn, assignee, EncryptUtils.encodeLDAPDetails(password),group);
			if(automation!=null){
				List<AutomationPortalModel> automationPortalModelList=getAllPortalsByIssueTypeSubTask(project,issueType,subTask);
				return Response.ok(automationPortalModelList).build();
			}
			return Response.noContent().build();
		}
				
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deletePortal")
		public Response delete(@QueryParam("project") String project ,@QueryParam("issueType") String issueType ,@QueryParam("group") String group , @QueryParam("selectedProject") String selectedProject ,@QueryParam("selectedIssue") String selectedIssue,@Context HttpServletRequest request)
		{
			logger.info("--------IssueResource---project--------------- "+project);
			portalService.delete(project, issueType, group);
			List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
			if(selectedProject!=null && (!selectedProject.isEmpty())){
				if(selectedIssue!=null && (!selectedIssue.isEmpty())){
					automationPortalModelList=getAllPortalsByIssueType(project,issueType);
			}else{
				automationPortalModelList=getAllAutoPortals(project);
			}
			}else{
				automationPortalModelList=getAllPortals();
			}
				return Response.ok(automationPortalModelList).build();
			
		}

	@PUT
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updatePortal")
	public Response update(@QueryParam("project") String project ,@QueryParam("issueType") String issueType ,@QueryParam("subTask") String subTask,@QueryParam("userdn") String userdn ,@QueryParam("password") String password ,@QueryParam("assignee") String assignee,@QueryParam("group") String group,  @Context HttpServletRequest request)
	{
		//System.out.println(portalService+"------group---------"+EncryptUtils.encodeLDAPDetails(password));
		logger.info("--------IssueResource---project---------------update--------- "+project);
		portalService.delete(project, issueType, subTask);
		AutomationPortal automation=portalService.create(project, issueType,subTask, userdn, assignee, EncryptUtils.encodeLDAPDetails(password),group);
		if(automation!=null){
			List<AutomationPortalModel> automationPortalModelList=getAllPortalsByIssueTypeSubTask(project,issueType,subTask);
			return Response.ok(automationPortalModelList).build();
		}
		return Response.noContent().build();
	/*	List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
		automationPortalModelList=getAllPortalsByIssueTypeSubTask(project,issueType,subTask);*/

		//return Response.ok(automationPortalModelList).build();
	}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portals")
		public Response getPortals(@QueryParam("project") String project)
		{
			
			List<AutomationPortalModel> automationPortalModelList=getAllAutoPortals(project);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/getAllPortals")
		public Response allPortals()
		{
			
			List<AutomationPortalModel> automationPortalModelList=getAllPortals();
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portalIssues")
		public Response getPortalsOnIssueTypes(@QueryParam("project") String project,@QueryParam("issueType") String issueType)
		{
			
			List<AutomationPortalModel> automationPortalModelList=getAllPortalsByIssueType(project,issueType);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portalIssuesSubTask")
		public Response getPortalsOnIssueTypesSubTask(@QueryParam("project") String project,@QueryParam("issueType") String issueType,@QueryParam("subTask") String subTask)
		{
			
			List<AutomationPortalModel> automationPortalModelList=getAllPortalsByIssueTypeSubTask(project, issueType, subTask);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		/**
		 * getAll Automation Portals by project
		 * @param project
		 * @return
		 */
		private List<AutomationPortalModel> getAllAutoPortals(String project){
			
			List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
			List<AutomationPortal> aList=portalService.find(project);
			for(AutomationPortal automation: aList){
				automationPortalModelList.add(new AutomationPortalModel(automation.getProject(),automation.getIssueType(),automation.getSubTask(),automation.getUserdn(),EncryptUtils.decodeLDAPDetails(automation.getPassword()),automation.getAssignee(),automation.getGroup()));
			}
			return automationPortalModelList;
			
		}
		
		/**
		 * getAll Automation Portals
		 * @return
		 */
       private List<AutomationPortalModel> getAllPortals(){
			
			List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
			List<AutomationPortal> aList=portalService.findAll();
			for(AutomationPortal automation: aList){
				automationPortalModelList.add(new AutomationPortalModel(automation.getProject(),automation.getIssueType(),automation.getSubTask(),automation.getUserdn(),EncryptUtils.decodeLDAPDetails(automation.getPassword()),automation.getAssignee(),automation.getGroup()));
			}
			return automationPortalModelList;
			
		}
		
       /**
        * getAll Automation Portals by project and issue type
        * @param project
        * @param issueType
        * @return
        */
        private List<AutomationPortalModel> getAllPortalsByIssueType(String project,String issueType){
			
			List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
			List<AutomationPortal> aList=portalService.findByProjectIssueType(project, issueType);
			for(AutomationPortal automation: aList){
				//System.out.println("-------------automation.getGroup------------------"+automation.getGroup());
				automationPortalModelList.add(new AutomationPortalModel(automation.getProject(),automation.getIssueType(),automation.getSubTask(),automation.getUserdn(),EncryptUtils.decodeLDAPDetails(automation.getPassword()),automation.getAssignee(),automation.getGroup()));
			}
			return automationPortalModelList;
			
		}
        
        
        
        
        /**
         * getAll Automation Portals by project and issue type
         * @param project
         * @param issueType
         * @return
         */
         private List<AutomationPortalModel> getAllPortalsByIssueTypeSubTask(String project,String issueType,String subTask){
 			
 			List<AutomationPortalModel> automationPortalModelList=new ArrayList<AutomationPortalModel>();
 			List<AutomationPortal> aList=portalService.findByProjectIssueTypeSubTask(project, issueType,subTask);
 			for(AutomationPortal automation: aList){
 				//System.out.println("-------------automation.getGroup------------------"+automation.getGroup());
 				automationPortalModelList.add(new AutomationPortalModel(automation.getProject(),automation.getIssueType(),automation.getSubTask(),automation.getUserdn(),EncryptUtils.decodeLDAPDetails(automation.getPassword()),automation.getAssignee(),automation.getGroup()));
 			}
 			return automationPortalModelList;
 			
 		}

	


}