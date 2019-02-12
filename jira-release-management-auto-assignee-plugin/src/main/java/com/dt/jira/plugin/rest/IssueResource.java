package com.dt.jira.plugin.rest;

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
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.plugin.ao.AutoAssigneePortal;
import com.dt.jira.plugin.service.AutoAssigneeService;
import net.java.ao.Entity;

/**
 * The REST Service for AutoAssignee Portal 
 *
 * @author Srinadh.Garlapati
 */
@Path("/autoAssigneeportal")
public class IssueResource
{
		private final Logger logger = Logger.getLogger(IssueResource.class);
		private final AutoAssigneeService autoAssigneeService;
		private final UserManager userManager;
		
	/**
	 * Constructor
	 * @param AutoAssigneeService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public IssueResource(AutoAssigneeService autoAssigneeService, UserManager userManager)
		 {
		   this.autoAssigneeService = autoAssigneeService;
		   this.userManager = userManager;
		 }
		

		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addPortal")
		public Response put(@QueryParam("project") String project ,@QueryParam("issueType") String issueType ,@QueryParam("status") String status ,@QueryParam("approval") String approval ,@QueryParam("assignee") String assignee,@QueryParam("group") String group,  @Context HttpServletRequest request)
		{
			logger.info(autoAssigneeService+"------group---------"+group);
			logger.info("--------IssueResource---project--------------- "+project);
			if(assignee!=null){
				logger.info("-----------assignee----------"+assignee);
				ApplicationUser user=ComponentAccessor.getUserManager().getUserByName(assignee.trim());
				logger.info("-user-:"+user);
				if(user==null || (!user.isActive())){
					logger.info("-----------USER_INACTIVE_OR_NOT_EXISTS---------------");
					return Response.ok(new ArrayList<AutoAssigneePortalModel>()).build();	
				}
				AutoAssigneePortal automation=autoAssigneeService.create(project, issueType, status, assignee);
				if(automation!=null){
					List<AutoAssigneePortalModel> automationPortalModelList=getAllPortalsByIssueTypeStatus(project,issueType,status);
					return Response.ok(automationPortalModelList).build();
				}
			}
			
			return Response.noContent().build();
		}
		
		@PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deletePortal")
		public Response delete(@QueryParam("project") String project ,@QueryParam("issueType") String issueType ,@QueryParam("status")String status ,@QueryParam("s_issueType") String s_issueType ,@QueryParam("s_status")String s_status )
		{
			logger.info(s_issueType+"--------IssueResource---delete--------------- "+s_status);
			autoAssigneeService.delete(project, s_issueType, s_status);
			List<AutoAssigneePortalModel> automationPortalModelList=getAllPortalsByIssueTypeStatus(project,issueType,status);
				return Response.ok(automationPortalModelList).build();
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portals")
		public Response getPortals(@QueryParam("project") String project)
		{
			
			List<AutoAssigneePortalModel> automationPortalModelList=getAllAutoPortals(project);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portalIssues")
		public Response getPortalsOnIssueTypes(@QueryParam("project") String project,@QueryParam("issueType") String issueType)
		{
			
			List<AutoAssigneePortalModel> automationPortalModelList=getAllPortalsByIssueType(project,issueType);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		@GET
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/portalIssuesStatus")
		public Response getPortalsOnIssueTypesStatus(@QueryParam("project") String project,@QueryParam("issueType") String issueType ,@QueryParam("status") String status)
		{
			
			List<AutoAssigneePortalModel> automationPortalModelList=getAllPortalsByIssueTypeStatus(project,issueType,status);
			return Response.ok(automationPortalModelList).build();
			
			
		}
		
		private List<AutoAssigneePortalModel> getAllAutoPortals(String project){
			
			List<AutoAssigneePortalModel> automationPortalModelList=new ArrayList<AutoAssigneePortalModel>();
			List<AutoAssigneePortal> aList=autoAssigneeService.find(project);
			for(AutoAssigneePortal automation: aList){
				automationPortalModelList.add(new AutoAssigneePortalModel(automation.getProject(),automation.getIssueType(),automation.getStatus(),automation.getAssignee()));
			}
			return automationPortalModelList;
			
		}
		
        private List<AutoAssigneePortalModel> getAllPortalsByIssueType(String project,String issueType){
			
			List<AutoAssigneePortalModel> automationPortalModelList=new ArrayList<AutoAssigneePortalModel>();
			List<AutoAssigneePortal> aList=autoAssigneeService.findByProjectIssueType(project, issueType);
			for(AutoAssigneePortal automation: aList){
				automationPortalModelList.add(new AutoAssigneePortalModel(automation.getProject(),automation.getIssueType(),automation.getStatus(),automation.getAssignee()));
			}
			return automationPortalModelList;
		}
        
       private List<AutoAssigneePortalModel> getAllPortalsByIssueTypeStatus(String project,String issueType,String status){
			List<AutoAssigneePortalModel> automationPortalModelList=null;
			List<AutoAssigneePortal> aList=autoAssigneeService.findByProjectIssueTypeStatus(project, issueType,status);
			if(aList!=null && (!aList.isEmpty())){
			automationPortalModelList=new ArrayList<AutoAssigneePortalModel>();
			for(AutoAssigneePortal automation: aList){
				automationPortalModelList.add(new AutoAssigneePortalModel(automation.getProject(),automation.getIssueType(),automation.getStatus(),automation.getAssignee()));
			 }
			}
			return automationPortalModelList;
		}

	


}