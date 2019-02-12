package com.dt.autoassign.rest;


import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Get Issue Types of a given Project
 */
@Path("/getIssueTypes")
public class GetIssueTypes {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON})
public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
	List<GetIssueTypesModel> listOfModels= new ArrayList<GetIssueTypesModel>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
		
		for(IssueType issueType:  issueTypesCollection){
			String name = issueType.getName();
			String issueTypeId = issueType.getId();
			listOfModels.add(new GetIssueTypesModel(name, issueTypeId));
		}
		
	}catch(Exception e){
			e.printStackTrace();
	}
	//GenericEntity<List<GetIssueTypesModel>> entity = new GenericEntity<List<GetIssueTypesModel>>(listOfModels) {};
	return Response.ok(listOfModels).build();
	//return Response.ok(entity).build();
}
}
