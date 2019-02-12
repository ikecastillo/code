package com.dt.jira.plugin.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.bc.project.component.ProjectComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.search.SearchProvider;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.query.Query;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

/**
 * A resource of getting list of issues of the given project.
 */
@Path("/githubintegration")
public class GithubIntegrationRestResource {
		
		private final Logger logger = Logger.getLogger(GithubIntegrationRestResource.class);
	
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") final String  projectkey)
    {
    	List<GithubIntegrationRestResourceModel> listModel = new ArrayList<GithubIntegrationRestResourceModel>();
    	try {
    		Query query = null;    	
    		SearchResults searchResults = null;
    		SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    		SearchProvider searchProvider = ComponentAccessor.getComponent(SearchProvider.class); 
    		PagerFilter filter = PagerFilter.getUnlimitedFilter(); 
    		JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
    		
    		
    		StringBuffer jql = new StringBuffer();    	  
    		if(projectkey!=null && projectkey != ""){
    			jql.append(" project = "+projectkey);
    		}
    		logger.info("GithubIntegrationRestResource: "+jql.toString());
    		
    		final SearchService.ParseResult totalDefResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
    		if( totalDefResult	!=null){	
    			query = totalDefResult.getQuery();
    			searchResults= searchProvider.search(query, authenticationContext.getLoggedInUser(), filter);    			
    		}
    		List<Issue> issues = searchResults.getIssues();
    		for(Issue issue: issues){
    			String key = issue.getKey();
    			String summary = issue.getSummary();
    			GithubIntegrationRestResourceModel model = new GithubIntegrationRestResourceModel(summary, key);
    			listModel.add(model);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return Response.ok(listModel).build();
    }
    
   
}