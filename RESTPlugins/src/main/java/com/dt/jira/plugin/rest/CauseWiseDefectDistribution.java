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
 * The output is formatted for a ta pie-chart 
 */
@Path("/causewisedistribution")
public class CauseWiseDefectDistribution {
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId,@QueryParam("groupbyField") String groupbyField) throws Exception{
	String ISSUE_TYPES = "Defect";
	Response response=null;
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
   
    CustomField  groupbyCustomField= fieldManager.getCustomFieldObjectByName(groupbyField);
    CustomField  severityfield= fieldManager.getCustomFieldObjectByName("Severity");
    //System.out.println("Custom: "+groupbyCustomField.getFieldName());
	  UserManager userManager = ComponentAccessor.getUserManager(); 
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") AND ");
	  if(projectkey!=null){
		  jql.append("project = "+projectkey);
	  } 
	  if(projectkey!=null  && version!=null && !version.equals("All")){
		  jql.append(" and fixVersion in ("+ version +")");
	  } 
	  if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
		   jql.append(" and Sprint in ("+ sprintId +")");
	  }
	 System.out.println("Query: "+jql.toString());
	  
	 final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 ArrayList<CauseWiseDefectDistributionModel> listOfModels= new ArrayList<CauseWiseDefectDistributionModel>();
	 Map map = new HashMap();
	 int totalDefects = 0;
	 if (pResult.isValid())
      {         
              SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
              List<Issue> issues = issueList.getIssues();
              totalDefects = issues!=null ? issues.size() : 0;
              ArrayList<Issue> tempIssues  = null;
    		  for(Issue issue: issues){     
				Option selectedVal = null;
				String key= "";
					if(groupbyCustomField!=null ){
						System.out.println("Custom: "+groupbyCustomField.getFieldName());
						selectedVal = (Option) issue.getCustomFieldValue(groupbyCustomField);
						key = (selectedVal!=null)?selectedVal.getValue():"Other";  
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
        			  ArrayList<Issue> list1 = (ArrayList<Issue>)map.get(key);
        			  list1.add(issue);
          			   map.put(key,list1);
        		
        		  } else {
        			  tempIssues = new ArrayList<Issue>(); 
        			  tempIssues.add(issue);
        			  map.put(key,tempIssues);        	    		
        		  }
        	  }
    		  
    		     Set set = map.keySet();
    			 Iterator it = set.iterator();
    			
    			 while(it.hasNext()){
    				 String key = (String)it.next();
    				 ArrayList<Issue> value = (ArrayList<Issue>) map.get(key);
    				 int totalSev = value.size();
    				 double perDefects = (totalSev * 100)/totalDefects;
    				 Map innerMap = new HashMap();
					 perDefects = Math.round(perDefects*100.00)/100.00;
    				 System.out.println("key: " + key + " totalSev: " + totalSev + " perDefects: " + perDefects);
    				 for(Issue issue1: value)
    				 {
						//System.out.println("Kiran: " + issue1.getId());
    					Option selectedVal = (Option) issue1.getCustomFieldValue(severityfield);
						String key1= "";
						key1 = (selectedVal!=null)?selectedVal.getValue(): "None";
						
						System.out.println("Kiran custom field value: "+ key1);
    	         		  if(innerMap.containsKey(key1)){
    	         			 int count = Integer.parseInt(innerMap.get(key1).toString());
    	         			 count++;
    	         			 innerMap.remove(key1);
    	         			 innerMap.put(key1,count);
    	         		  } else {
    	         			 innerMap.put(key1,1);
    	         		  }
    				 }
    				 
    				Set set1 = innerMap.keySet();

    				 Iterator it1 = set1.iterator();
    				 String low="0";
    				 String high = "0";
    				 String medium= "0";
    				 String critical="0" ,none = "0";
    				 int totDefects = 0;
    				 while(it1.hasNext()){
    					 String key2 = (String)it1.next();
    					 Integer value2 = (Integer) innerMap.get(key2);
    					 totDefects = totDefects + value2.intValue();
    					 if(key2.equalsIgnoreCase("Low")){
    						 low = value2.toString();
    					 }
    					 if(key2.equalsIgnoreCase("Medium")){
    						 medium = value2.toString();
    					 }
    					 if(key2.equalsIgnoreCase("High")){
    						 high = value2.toString();
    					 }
    					 if(key2.equalsIgnoreCase("Critical")){
    						 critical = value2.toString();
    					 }
    					 if(key2.equalsIgnoreCase("None")){
    						 none = value2.toString();
    					 }    					
    				 }
    				 listOfModels.add(new CauseWiseDefectDistributionModel(key, String.valueOf(perDefects), String.valueOf(totDefects), low, medium, high, critical,none)); 
    			}
    		 
           	 response= Response.ok(listOfModels).build();	
      }
	 return response;
  }
}