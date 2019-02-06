package com.dt.jira.servicedesk.customize.rest;



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
import java.util.Map;
import java.util.HashMap;



/**
 * Get Issue Types of a given Project
 */
@Path("/deskautomationprojects")
public class ProjectIssueService {

	@GET
	@AnonymousAllowed
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deskissueTypes")
	public Response getMessage(@QueryParam("projectkey") String projectName)
			throws Exception {
		Map<String, List<GetIssueTypesModel>> subTaskIssueTypeMap = new HashMap<String, List<GetIssueTypesModel>>();
		try {
			Project project = ComponentAccessor.getProjectManager()
					.getProjectObjByName(projectName);
			Collection<IssueType> issueTypesCollection = project
					.getIssueTypes();

			for (IssueType issueType : issueTypesCollection) {
				if (!issueType.isSubTask()) {
					String name = issueType.getName();
					// listOfModels.add(new GetIssueTypesModel(name, name));
					if (subTaskIssueTypeMap.containsKey("issueType")) {
						List<GetIssueTypesModel> issueTypeList = subTaskIssueTypeMap
								.get("issueType");
						issueTypeList.add(new GetIssueTypesModel(name, name));
						subTaskIssueTypeMap.put("issueType", issueTypeList);
					} else {
						List<GetIssueTypesModel> issueTypeList = new ArrayList<GetIssueTypesModel>();
						issueTypeList.add(new GetIssueTypesModel(name, name));
						subTaskIssueTypeMap.put("issueType", issueTypeList);
					}
				} else {
					String name = issueType.getName();
					// listOfModels.add(new GetIssueTypesModel(name, name));
					if (subTaskIssueTypeMap.containsKey("subTask")) {
						List<GetIssueTypesModel> issueTypeList = subTaskIssueTypeMap
								.get("subTask");
						issueTypeList.add(new GetIssueTypesModel(name, name));
						subTaskIssueTypeMap.put("subTask", issueTypeList);
					} else {
						List<GetIssueTypesModel> issueTypeList = new ArrayList<GetIssueTypesModel>();
						issueTypeList.add(new GetIssueTypesModel(name, name));
						subTaskIssueTypeMap.put("subTask", issueTypeList);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("---------subTaskIssueTypeMap-----------"
				+ subTaskIssueTypeMap);
		return Response.ok(subTaskIssueTypeMap).build();

	}


@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
@Path("/deskstatus")
public Response getStatus(@QueryParam("projectkey") String projectName,@QueryParam("issueType") String issueType) throws Exception{
	ArrayList<GetStatusTypesModel> listOfModels= new ArrayList<GetStatusTypesModel>();
	try{
	
	if(issueType!=null && projectName!=null){
		Project project = ComponentAccessor.getProjectManager().getProjectObjByName(projectName);
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
