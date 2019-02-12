package com.dt.jira.plugin.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.atlassian.jira.workflow.WorkflowManager;
import com.atlassian.jira.workflow.JiraWorkflow;
import com.atlassian.jira.issue.status.Status;
@Path("/getStatusTypes")
public class GetStatusTypes {

	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMessage(@QueryParam("projectkey") String projectkey, @QueryParam("issueType") String issueType) throws Exception{
		ArrayList<GetStatusTypesModel> listOfModels= new ArrayList<GetStatusTypesModel>();
		try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
		if(issueType.equals("null")){
			for(IssueType issueTypeValue:  issueTypesCollection){
			
				String firstIssueTypeId = issueTypeValue.getId();				
				JiraWorkflow workFlow = ComponentAccessor.getWorkflowManager().getWorkflow(project.getId(), firstIssueTypeId);
				 List<Status> statusList = workFlow.getLinkedStatusObjects();
				 for(Status status :statusList){
					 String statusName = status.getName();
					 listOfModels.add(new GetStatusTypesModel(statusName, statusName));
				 }
				 break;
			}
			
		}
		for(IssueType issueTypeValue:  issueTypesCollection){
			String name = issueTypeValue.getName();
			if(name.equals(issueType)){
				
				 JiraWorkflow workFlow = ComponentAccessor.getWorkflowManager().getWorkflow(project.getId(), issueTypeValue.getId());
				 List<Status> statusList = workFlow.getLinkedStatusObjects();
				 for(Status status :statusList){
					 String statusName = status.getName();
					 listOfModels.add(new GetStatusTypesModel(statusName, statusName));
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
