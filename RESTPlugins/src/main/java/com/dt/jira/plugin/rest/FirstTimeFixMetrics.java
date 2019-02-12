package com.dt.jira.plugin.rest;



import java.sql.Timestamp;
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
 * REST service that provides the statistics of First Time Fix.
 * A defect is fixed by a developer in first attempt without any further rework, it is First Time Fix at a project/version/sprint level
 * The output is formatted for a jqplot chart .
 *
 * Updated code for Reference Model workflow and AIOI-3071 Infrastructure JIRA workflow
 */
@Path("/firsttimefix")
public class FirstTimeFixMetrics {
	/* Drill down url for Table view*/
	private String drilldownTableUrl;
	/* Drill down url for Pie chart view*/
	private String drilldownPieUrl;
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
	
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version, sprintId and groupbyField.
 * It builds the one jql query and get the list of issues, iterate the issues whether the issue is fixed firt time or not.
 * 
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
	log.info("sprintId "+sprintId);
        
    CustomField  customField= fieldManager.getCustomFieldObjectByName("Severity");
    //CustomField  statusField= fieldManager.getCustomFieldObjectByName("status");
    
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") and  status in (Closed) "); // removed as request on 13/2013
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
	   
	  log.info("firsttimefix**************Query: "+jql.toString());
	    this.drilldownTableUrl = "/issues/?jql=" + jql ;
	    this.drilldownPieUrl = "/issues/?jql=" + jql ;
	    final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	    ArrayList<FirstTimeFixMetricsModel> listOfModels= new ArrayList<FirstTimeFixMetricsModel>();
		 Map map = new HashMap();
		 Map sprintMap = new HashMap();
		 long totalStPt = 0;
		
		 if (pResult.isValid())
	      {
	         
             SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
             List<Issue> issues = issueList.getIssues();
             //totalDefects = issues!=null ? issues.size() : 0;
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
       	  }
   		  
   		     Set set = map.keySet();
   			 Iterator it = set.iterator();
   			 String urlForTable = "";
   			 String urlForFF = "";
   			 String urlForRework ="";
   			 while(it.hasNext()){
   				 String key = (String)it.next();
   				 ArrayList<Issue> value = (ArrayList<Issue>) map.get(key);
   				 int totalSev = value.size();
   				 //log.info("key: " + key + " totalSev: " + totalSev);
   				 
   				 int countFF= 0;
   				 int countRework=0;
				 String drillDownFF ="";
				 String drillDownNotFF = "";
   				 for(Issue issue1: value)
   				 {
					
   					 // 1. find the issue is FF 
   					 // 2. find the issue is re-opend : and once it is re-open it should go to the dev (Status = Resolved) and QA (status = Re-opened/Closed)
   					
						boolean isFirFix= isIssueFixedFirstTime(issue1);
					    if(isFirFix){
					    	countFF++;
					    	drillDownFF=  drillDownFF + issue1.getKey() + ",";
					    }					  
					    if(!isFirFix){
					    	boolean isNotFirFix = isIssueNotFixedFirstTime(issue1);
					    	if(isNotFirFix){
					    		countRework++;
					    		drillDownNotFF=  drillDownNotFF + issue1.getKey() + ",";
					    	}
					    }
						
   				 }
   				urlForTable = this.drilldownTableUrl + " and Severity =";
   				//urlForRework =  drillDownNotFF + ;
   				//urlForFF = this.drilldownPieUrl + " and key in ("+ drillDownFF + ")";
   				listOfModels.add(new FirstTimeFixMetricsModel(key, String.valueOf(countFF), String.valueOf(countRework), String.valueOf(totalSev), urlForTable , drillDownFF, drillDownNotFF));
   			 } 
          	  calculateTotAndPercentage(listOfModels);
	      } 
		 return  Response.ok(listOfModels).build();	   
	}
/**
 * Calculate the total and percentage for all the objects in the list.
 * @param list - ArrayList<FirstTimeFixMetricsModel> 
 */
public void calculateTotAndPercentage(ArrayList<FirstTimeFixMetricsModel> list){
	long totFF = 0;
	long totRework=0;
	long total = 0;
	
	String urlForR = "";
	String urlForF = "";
	
	String urlForFF = "";
	String 	urlForRework = "";
	if(list!=null && list.size()>0){
	for(int i=0;i<list.size(); i++){
		FirstTimeFixMetricsModel firstTimeFixMetricsModel =  list.get(i);
		totFF = totFF  + Long.valueOf(firstTimeFixMetricsModel.getFirstTimeFix());
		totRework = totRework  + Long.valueOf(firstTimeFixMetricsModel.getRework());
		total = total + Long.valueOf(firstTimeFixMetricsModel.getTotal());
		urlForF = urlForF + firstTimeFixMetricsModel.getUrlForFF();
		urlForR = urlForR + firstTimeFixMetricsModel.getUrlForRework();
	}
	
	if(!urlForF.equals("")){
		urlForF = urlForF.substring(0, (urlForF.length()-1));
	}
	urlForFF  = this.drilldownPieUrl + " and key in ("+ urlForF + ")";
	if(!urlForR.equals(""))	{
		urlForR =  urlForR.substring(0, (urlForR.length()-1) ); 
	}
	urlForRework = this.drilldownPieUrl + " and key in ("+ urlForR + ")";
	list.add(new FirstTimeFixMetricsModel("Total", String.valueOf(totFF), String.valueOf(totRework), String.valueOf(total),"",urlForFF,urlForRework));
    double perFF = (totFF * 100)/total;  
    perFF = Math.round(perFF*100.00)/100.00;
    
    double perRework = (totRework * 100)/total;  
    perRework = Math.round(perRework*100.00)/100.00;
    
    list.add(new FirstTimeFixMetricsModel("FTF", String.valueOf(perFF), String.valueOf(perRework),"100%","",urlForFF,urlForRework ));
	}
}
/**
 * Is issue is fixed first time - when a defect is fixed by a developer in first attempt without any further rework, it is First Time Fix
 * @param issue<Issue> 
 * @return - Returns true/false
 */
public boolean isIssueFixedFirstTime(Issue issue){
	 List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
	 int flag=0;
	 Timestamp qaTestDate =null;
	 if(statusItem!=null && statusItem.size()>0){
		 
		 for(int i=0;i<statusItem.size(); i++){
				ChangeItemBean item = statusItem.get(i);
				/* Updated status Open, In Progress and Resolved for AIOI-3071 Infrastructure JIRA workflow */
				if( item.getToString().equalsIgnoreCase("QA Test")  || item.getToString().equalsIgnoreCase("Closed") || item.getToString().equalsIgnoreCase("In Process") || item.getToString().equalsIgnoreCase("Resolved")){
					flag = 1;
					qaTestDate =  item.getCreated();
				}
				if(flag == 1){
					//log.info("QA Test statu date : "+ qaTestDate.toString() + " item.getCreated() " +item.getCreated());
					if(qaTestDate.before(item.getCreated()) &&  item.getToString().equalsIgnoreCase("Reopened")){
						flag = 0;
						break;
					}
				}
				
		 }
		 if(flag ==  1)
			 return true;
	 }
	 
	return false;	
}
/**
 * Is issue is not fixed first time.
 * @param issue<Issue> 
 * @return - Returns true/false
 */
public boolean isIssueNotFixedFirstTime(Issue issue){
	 List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
	 int flag = 0;
	 int reworkcount = 0;
	 Timestamp reworkDate =null;
	 if(statusItem!=null && statusItem.size()>0){
		 for(int i=0;i<statusItem.size(); i++){
				ChangeItemBean item = statusItem.get(i);
				// 1. check the status for QA test 
				// 2. check the status reopened after QA test 
				// 3. Yes it is re-opened then check dev integration test more than one
				// 4. 	Updated status Closed  and Resolved for AIOI-3071 Infrastructure JIRA workflow 
				if( item.getToString().equalsIgnoreCase("QA Test") || item.getToString().equalsIgnoreCase("Resolved") || item.getToString().equalsIgnoreCase("Closed")){
					flag = 1;
					reworkDate =  item.getCreated();
				}				
				if(flag == 1){
					//log.info("reopend date : "+ reworkDate.toString() + " item.getCreated() " +item.getCreated());
					// 	Updated status Re opened for AIOI-3071 Infrastructure JIRA workflow 
					if((reworkDate.before(item.getCreated()) &&  item.getToString().equalsIgnoreCase("Dev Integration Test")) || (reworkDate.before(item.getCreated()) &&  item.getToString().equalsIgnoreCase("Reopened") ))
						reworkcount ++;					
				}
		 }
		 if(reworkcount > 0)
			 return true;
	 }
	 
	return false;	
}

}
