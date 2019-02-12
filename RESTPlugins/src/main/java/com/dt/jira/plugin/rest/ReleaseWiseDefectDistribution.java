package com.dt.jira.plugin.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


/**
 * REST service that provides the statistics of valid emergency production defects.
 * The output is formatted for a jqplot chart 
 */
@Path("/defectrelease")
public class ReleaseWiseDefectDistribution {
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version and sprintId.
 * It builds the one jql query and get valid valid emergency production defects..
 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
	String ISSUE_TYPES = "Defect";    
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
   
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") AND ");
	  jql.append("status not in (Cancel,Cancelled,Rejected) and 'Emergency Type' = yes AND 'Test Phase' = Production "); 
	  
	  if(projectkey!=null){
		  jql.append(" and project = "+projectkey);		 
	  } 
	  if(projectkey!=null  && version!=null && !version.equals("All")){
		  jql.append(" and fixVersion in ("+ version +")");
	  } 
	  if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
		   jql.append(" and Sprint in ("+ sprintId +")");
	  }
	 log.info("Query: "+jql.toString());
	  
	 final SearchService.ParseResult sevtMajor= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 ArrayList<ReleaseWiseDefectDistributionModel> listOfModels= new ArrayList<ReleaseWiseDefectDistributionModel>();
	 Map map = new HashMap();
	
	 if (sevtMajor.isValid())
      {         
              SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
              List<Issue> issues1 = results1.getIssues();
              
              if(issues1!= null && issues1.size() > 0)
              {  
            	  for(Issue issue: issues1){
            		   String key= "";
            		  Collection<Version> fixVersions =issue.getFixVersions();
            		  if(fixVersions!=null){
            			  //log.info("issue name/id : "+ issue.getKey() + "fixed versions size"+ fixVersions.size());
            		  }
            		  Iterator<Version> iterator= fixVersions.iterator();
            		  while(iterator.hasNext()){
            			  Version ver = iterator.next();
            			  key = ver!=null ? ver.getName() : "Other";  
                		  if(map.containsKey(key)){
                			  int count = Integer.parseInt(map.get(key).toString());
                			  count++;
                			  map.remove(key);
                			  map.put(key,count);
                		  } else {
                			  map.put(key,1);
                		  }
            		  } // End while
            		}// End for
              }
              else
              {
            	  listOfModels.add(new ReleaseWiseDefectDistributionModel("", "0"));
              }
        }
	 Set set = map.keySet();

	 Iterator it = set.iterator();
	 while(it.hasNext()){
		 String key = (String)it.next();
		 Integer value = (Integer) map.get(key);
		
		 listOfModels.add(new ReleaseWiseDefectDistributionModel(key, value.toString()));
	 }
     return Response.ok(listOfModels).build();
 	}
}