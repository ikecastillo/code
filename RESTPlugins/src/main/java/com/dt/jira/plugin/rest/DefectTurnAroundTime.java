package com.dt.jira.plugin.rest;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * REST service that provides the statistics of Defect Turn Around.
 * A no of days - between the issue is closed and time the defect is detected  at a project/version/sprint level
 * The output is formatted for a jqplot chart .
 */
@Path("/defectturnaround")
public class DefectTurnAroundTime {
	/* Drill down url for bar chart view*/
	private String drilldownBarUrl;
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version, sprintId and groupbyField.
 * It builds the one jql query and find the  no of days - between the issue is closed and time the defect is detected  at a project/version/sprint level
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id 
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
	
	String ISSUE_TYPES = "Defect";    
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

        
    CustomField  customField= fieldManager.getCustomFieldObjectByName("Severity");
    
    
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") and status in (Closed) ");
	  //jql.append("status not in (Cancel,Cancelled,Rejected) and 'Emergency Type' = yes "); 
	  
	  if(projectkey!=null){
		  jql.append(" and project = "+projectkey);		 
	  } 
	  if(projectkey!=null  && version!=null && !version.equals("All")){
		  jql.append(" and fixVersion in ("+ version +")");
	  } 
	  if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
		   jql.append(" and Sprint in ("+ sprintId +")");
	  }
	   log.info("**************Query: "+jql.toString());
	    this.drilldownBarUrl = "/issues/?jql=" + jql ;
	   
	    final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	    ArrayList<DefectTurnAroundTimeModel> listOfModels= new ArrayList<DefectTurnAroundTimeModel>();
		 Map map = new HashMap();
		 Map sprintMap = new HashMap();
		 long totalStPt = 0;
		
		 if (pResult.isValid()) {	         
           SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
           List<Issue> issues = issueList.getIssues();
           ArrayList<Issue> tempIssues  = null;
	   		  for(Issue issue: issues){        		  
					Option selectedVal = (Option) issue.getCustomFieldValue(customField);
					String key= "";
					key = (selectedVal!=null)?selectedVal.getValue():"Other";        		 
	       		  if(map.containsKey(key)){
	       			  ArrayList<Issue> list1 = (ArrayList<Issue>)map.get(key);
	       			  list1.add(issue);
	         			   map.put(key,list1);       		
	       		  } else {
	       			  tempIssues = new ArrayList<Issue>(); 
	       			  tempIssues.add(issue);
	       			  map.put(key,tempIssues);        	    		
	       		  }
	       	  }// end for
   		     Set set = map.keySet();
   			 Iterator it = set.iterator();
   			 while(it.hasNext()){
   				 String key = (String)it.next();
   				 ArrayList<Issue> value = (ArrayList<Issue>) map.get(key);
   				 int totalSev = value.size();
   				 //log.info("key: " + key + " totalSev: " + totalSev);
   				 
   				 long  totNumberOfDays= 0;
   				 String urlForBar = "";
   				 for(Issue issue1: value)
   				 {		
   					 // 1. find the issue is created date 
   					 // 2. find the issue is closed date from the history
   					 // 3. get the difference in days between close date and created date
   					
   					 Timestamp created = issue1.getCreated();
   					 Timestamp closed = getIssueClosedDate(issue1);
   					 
   					 long diff = closed.getTime() - created.getTime() ;
   					
   					 //log.info(" Severity: "+ key + "issue key: " + issue1.getKey() + "  days: "+ diff);
   					 totNumberOfDays = totNumberOfDays + diff;
						
   				 } // end for
   				urlForBar = this.drilldownBarUrl + " and Severity in ("+ key + ")";
   				String days =CommonService.convertToDays(totNumberOfDays);
   				listOfModels.add(new DefectTurnAroundTimeModel(key,days,urlForBar));
   			 } // end while          	  
	      } // end if
		 return  Response.ok(listOfModels).build();	   
	}
/**
 * Get the issue date from the log history. 
 * @param issue <Issue>
 * @return <Timestamp> - issue closed date.
 */
 	private Timestamp getIssueClosedDate(Issue issue){
 		List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
 		Timestamp issueChaged = null;
 		if(statusItem!=null && statusItem.size()>0){
			ChangeItemBean item = statusItem.get(statusItem.size()-1);
			
			if(item.getToString().equalsIgnoreCase("Closed")){
				 issueChaged = item.getCreated();			
			}
		}
 		return issueChaged;
 	}

}
