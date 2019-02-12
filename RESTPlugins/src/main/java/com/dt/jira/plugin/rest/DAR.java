package com.dt.jira.plugin.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.query.Query;

/**
 * Restful web service which is implemented to calculate defect aspect ratio of project/version/sprint
 */
@Path("/daratio")
public class DAR {
	@GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	/**
	 * This method is rest service method and takes three input parameters project key, version, sprint id.
	 * It builds the jql query and get results for total no. of defects and total valid defects.
	 * @param projectkey - project key
	 * @param version - version
	 * @param sprintId  - sprint id
	 * @return Response - Object which contains Model
	 * @throws JiraException
	 */
    public Response getMessage(@QueryParam("projectKey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws JiraException{
    	Response response=null;
		Query query = null;
		String totValidDefects = "";
		String totDefects = "";
		SearchResults searchResults = null;
		SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
		//SearchProvider searchProvider = ComponentAccessor.getComponent(SearchProvider.class);
		PagerFilter filter = PagerFilter.getUnlimitedFilter(); 
		//UserManager userManager = ComponentAccessor.getUserManager(); 
		JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
		LoggerWrapper log = LoggerWrapper.with(getClass());
		log.setInfoLogLevel();
		StringBuffer jql = new StringBuffer();
		jql.append("issueType in (Defect)");	  
		if(projectkey!=null && projectkey != ""){
			jql.append(" and project = "+projectkey);
		}		
		if(projectkey!=null  && version!=null && (!version.equals("All"))){			
			jql.append(" and fixVersion in ("+ version +")");
		} 
		if(projectkey!=null  && sprintId != null && !sprintId.equals("")){
			jql.append(" and Sprint in ("+ sprintId +")");
		}

		log.info(jql.toString(), "","");

		final SearchService.ParseResult totalDefResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
		if( totalDefResult	!=null){	
			query = totalDefResult.getQuery();
			searchResults= searchServ.search( authenticationContext.getLoggedInUser(),query, filter);
			totDefects=searchResults.getIssues()!= null ?  Integer.toString(searchResults.getIssues().size()) : "0";
		}
		jql = jql.append(" AND status != Cancelled AND 'Defect Cause' != Rejected AND 'Test Phase' != Production ");		
		final SearchService.ParseResult validDefResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
		if(validDefResult!=null){	
			query = validDefResult.getQuery()  ; 		   
			SearchResults validDefects= searchServ.search(authenticationContext.getLoggedInUser(),query, filter);
			if(validDefects!=null)
				totValidDefects=validDefects.getIssues()!= null ?  Integer.toString(validDefects.getIssues().size()) : "0";
		}

		response= Response.ok(new DARModel(totValidDefects,totDefects)).build();
		return response;
    }

}