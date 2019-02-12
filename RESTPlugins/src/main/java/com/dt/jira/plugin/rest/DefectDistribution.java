package com.dt.jira.plugin.rest;
import java.util.ArrayList;

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

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;


import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


/**
 * REST service that provides the statistics of defects in a project/version/sprint
 * Defect Distribution based on the following groupings:
 * Defect Cause, Severity, Stage, Resource and Cycle
 * The output is formatted for a jqlplot pie chart 
 */
@Path("/defectdistribution")
public class DefectDistribution {
	/* Drill down url for  chart view*/
	private String drilldownUrl;
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version and sprintId.
 * It builds the one jql query and get issues which the issue type is Defect
 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId,@QueryParam("groupbyField") String groupbyField) throws Exception{
	Response response=null;
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
   
      CustomField  customfield= fieldManager.getCustomFieldObjectByName(groupbyField);
      //log.info("Custom: "+customfield.getFieldName());
	  UserManager userManager = ComponentAccessor.getUserManager(); 
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	   StringBuffer jql = new StringBuffer();
	   
	  
	  if(projectkey!=null){
		  jql.append("project = "+projectkey);
		  jql.append(" and issueType in (Defect) ");
	  } 
		if(projectkey!=null  && version!=null && (!version.equals("All"))){
			//log.info("Inside version check---"+ version+ "---");
			jql.append(" and fixVersion in ("+ version +")");
		} 
		if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
			jql.append(" and Sprint in ("+ sprintId +")");
		}	 
	  log.info("Query: "+jql.toString());
	  this.drilldownUrl = "/issues/?jql=" + jql ;
	  //project=" + projectkey + " AND version in "(All) AND sprintId in (All)  AND Assignee= "rebecca.taylor"
	 final SearchService.ParseResult sevtMajor= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 ArrayList<DefectDistributionModel> listOfModels= new ArrayList<DefectDistributionModel>();
	 Map map = new HashMap();
	
	 if (sevtMajor.isValid())
      {         
              SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
              List<Issue> issues1 = results1.getIssues();
              
              if(issues1!= null && issues1.size() > 0)
              {
            	  
            	  for(Issue issue: issues1){  
            		  Option selectedVal = null;
					   String key= "";
            		  if(customfield!=null && groupbyField.equalsIgnoreCase("Sprint")){
            			  ArrayList x = (ArrayList) issue.getCustomFieldValue(customfield);
						  // Sriram Rajaraman
						  // Note: Sprint customfield gets returned as an array list 
						  // The logic below scavenges for the sprint name in this array list
						  // IMHO this is an ugly way of getting the latest sprint
						  // we should explore an elegant way to retrieve the sprint details using GH API.
						  if (x!=null && !x.isEmpty()) {
							// get the latest sprint data
							key = (x.get(x.size() -1)).toString();
							// And scavenge for the name in the string :-/
							String skey = "name=";
							int start = key.lastIndexOf(skey);
							int end = key.indexOf(",", start);
							key = key.substring(start+skey.length(),end);
						  }
						  else {key = "Other";}
            		  } 
					  if(customfield!=null && !groupbyField.equalsIgnoreCase("Sprint")){
            			  selectedVal = (Option) issue.getCustomFieldValue(customfield);
					      key = selectedVal!=null ? selectedVal.getValue() : "Other"; 
            		  } 
            		   if(!groupbyField.equals("") && groupbyField.equalsIgnoreCase("assignee") ){
            			   ApplicationUser user = issue.getAssigneeUser();
            			   key = user!=null ? user.getName() : "Other" ;
            		   }
            		   if(!groupbyField.equals("") && groupbyField.equalsIgnoreCase("status") ){
            			   com.atlassian.jira.issue.status.Status status = issue.getStatusObject();
            			   key = status!=null ? status.getName() : "Other" ;
            		   }
					    
			
            		  if(map.containsKey(key)){
            			  int count = Integer.parseInt(map.get(key).toString());
            			  count++;
            			  map.remove(key);
            			  map.put(key,count);
            		  } else {
            			  map.put(key,1);
            		  }
            	  }
                 
              }
              else
              {
            	  listOfModels.add(new DefectDistributionModel("", 0));
              }
        }
	 Set set = map.keySet();

	Iterator it = set.iterator();
	while(it.hasNext()){
		String key = (String)it.next();
		Integer value = (Integer) map.get(key);
		String grpBy = " AND \"" +  groupbyField + "\"";
		if (key.equalsIgnoreCase("other")) grpBy += " IS EMPTY";
		else grpBy += " = \"" + key + "\"";
		String url = this.drilldownUrl + grpBy;
		listOfModels.add(new DefectDistributionModel(key, value, url));
	}
	 
	response= Response.ok(listOfModels).build();	

	return response;
  }
}