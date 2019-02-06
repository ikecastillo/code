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
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * Get Issue Types of a given Project
 */
@Path("/addLinkedIssues")
public class AddLinkedIssues {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("versions") String versions) throws Exception{
	ArrayList<AddLinkedIssuesModel> listOfModels= new ArrayList<AddLinkedIssuesModel>();
	String ISSUE_TYPES = "Story, 'Technical Story', Defect, Requirement,'Performance Testing'";
	List<Issue> listOfIssues = null;
	 double totValidDef = 0;
	try{
		
		 StringBuffer jql = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, versions, null, " status=Closed ");
		 SearchResults results = CommonService.getSerarchResults(jql.toString());
		 System.out.println("FixVersion**************Query1: "+jql.toString());
		 if(results!=null){
				totValidDef = results.getIssues()!=null? results.getIssues().size(): 0;
				listOfIssues = results.getIssues(); 
				//System.out.println("**************Query1: "+jql.toString());
				System.out.println("FixVersion**************total issues: "+totValidDef);
		 }
		  for(Issue issue: listOfIssues){
			  String id = issue.getId().toString();
			  String key = issue.getKey();	
			  listOfModels.add(new AddLinkedIssuesModel(id, key));
		  }
	}catch(Exception e){
			e.printStackTrace();
	}
  		return Response.ok(listOfModels).build();		
	}
}
