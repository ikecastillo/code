package com.dt.jira.plugin.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.workflow.JiraWorkflow;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;



/**
 * Get Issue Types of a given Project
 */
@Path("/jiraAutoAssignee")
public class ProjectIssueService {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
@Path("/issueTypes")
public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
	ArrayList<GetIssueTypesModel> listOfModels= new ArrayList<GetIssueTypesModel>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
		
		for(IssueType issueType:  issueTypesCollection){
			//allowing sub task also.
			//if(!issueType.isSubTask()){
			String name = issueType.getName();
			listOfModels.add(new GetIssueTypesModel(name, name));
			//}
		}
		
	}catch(Exception e){
			e.printStackTrace();
	}
  		return Response.ok(listOfModels).build();		
			
	}

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
@Path("/status")
public Response getStatus(@QueryParam("projectkey") String projectkey,@QueryParam("issueType") String issueType) throws Exception{
	ArrayList<GetStatusTypesModel> listOfModels= new ArrayList<GetStatusTypesModel>();
	try{
	
	if(issueType!=null && projectkey!=null){
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
		for(IssueType issueTypeValue:  issueTypesCollection){
			String name = issueTypeValue.getName();
			if(issueType.equals(name)){
				 JiraWorkflow workFlow = ComponentAccessor.getWorkflowManager().getWorkflow(project.getId(), issueTypeValue.getId());
				 List<Status> statusList = workFlow.getLinkedStatusObjects();
				 for(Status status :statusList){
					 String statusName = status.getName();
					 listOfModels.add(new GetStatusTypesModel(statusName, statusName));
				 }
			   }
		     }
	     }
       }
	catch(Exception e){
	e.printStackTrace();
	}
  return Response.ok(listOfModels).build();
  }

	
 }
