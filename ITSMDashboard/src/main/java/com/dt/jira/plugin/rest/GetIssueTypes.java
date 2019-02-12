package com.dt.jira.plugin.rest;



import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


/**
 * Get Issue Types of a given Project
 */
@Path("/getIssueTypes")
public class GetIssueTypes {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
	ArrayList<GetIssueTypesModel> listOfModels= new ArrayList<GetIssueTypesModel>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
		
		for(IssueType issueType:  issueTypesCollection){
			String name = issueType.getName();
			listOfModels.add(new GetIssueTypesModel(name, name));
		}
		
	}catch(Exception e){
			e.printStackTrace();
	}
  		return Response.ok(listOfModels).build();		
	}
}
