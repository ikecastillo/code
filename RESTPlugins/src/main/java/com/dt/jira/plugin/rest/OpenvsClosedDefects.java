package com.dt.jira.plugin.rest;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * A resource of message.
 */
@Path("/openvscloseddefects")
public class OpenvsClosedDefects {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("startdate") String startdate,@QueryParam("enddate") String enddate,@QueryParam("interval") String interval_wk) throws Exception{
	//public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version) throws Exception{
	
	String ISSUE_TYPES = "Defect";    
	

    //jql query issuetype = Defect  and createdDate >= '2013-10-30' and createdDate <= '2013-12-30'
        
    
  /*  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
    if(projectkey!=null){
	  jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && startdate!=null && enddate!=null ){
	  jql.append(" and createdDate >= "+startdate +" and createdDate <="+ enddate );		 
	} 
    System.out.println("**************Query: "+jql.toString());*/
	//System.out.println("**************Startdate: "+startdate);
	//System.out.println("**************enddate: "+enddate);
	
	ArrayList<OpenvsClosedDefectsModel> listOfModels = new ArrayList<OpenvsClosedDefectsModel>();
   // final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	try{
		
		Date inputStDate = getJiraFormatter().parse(startdate);
		Date inputEnDate = getJiraFormatter().parse(enddate);
	 	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
 
	 	String start_date=formatter.format(inputStDate); //change the start date format to dd-MMM-yyyy 			   
	  
	    Date startd = formatter.parse(start_date);
		
	    String end_date = formatter.format(inputEnDate);  //change the end date format to dd-MMM-yyyy 
	    Date endd = formatter.parse(end_date);
	    int interval = Integer.valueOf(interval_wk)*7; //weeks
  
	    Date d = startd;
		 String stdat= startdate;
		 CommonService.addDays(d, interval);
		    while (d.before(endd)) {
		    	List<Issue> issues =null;
				String drillDownOpen ="";
				String drillDownClose ="";
				Date displayStartD = getJiraFormatter().parse(stdat);
				
				issues = getIssuesForOpen(projectkey,stdat,getJiraFormatter().format(d));
				int opened = 0;	
		    	//Find the status for the issue on interval 
		    	for(Issue issue: issues){	
	    		   if(isIssueClosed(d,issue)){
	    			  
	    		   } else {
	    			   opened++;		    			 
	    			   drillDownOpen = drillDownOpen + issue.getKey() + ",";
	    		   }		    		 
		    	}		    	   
		    	int closed = 0;
		    	issues = getIssuesForClosed(projectkey,stdat,getJiraFormatter().format(d));
			    //Find the status for the issue on interval
		    	if(issues!=null && issues.size() > 0)
		    		closed = issues.size();
		    	   
	    	   String openUrl =  "/issues/?jql=" + buildDrillDownQuery(projectkey,stdat,getJiraFormatter().format(d),drillDownOpen);
	    	   String closedUrl = "/issues/?jql=" + buildDrillDownQueryForClose(projectkey,stdat,getJiraFormatter().format(d),null);
	    	   
			    OpenvsClosedDefectsModel   openvsClosedDefectsModel= new OpenvsClosedDefectsModel( formatter.format(displayStartD)+" to "+getDefaultFormatter().format(d),String.valueOf(opened),String.valueOf(closed));
	    		listOfModels.add(openvsClosedDefectsModel);
	    		openvsClosedDefectsModel.setOpenDrillDownUrl(openUrl);
	    		openvsClosedDefectsModel.setCloseDrillDownUrl(closedUrl);
		    	stdat =   getJiraFormatter().format(d); 
		    	CommonService.addDays(d, interval);
				
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
 	return Response.ok(listOfModels).build();		
	}
private String buildDrillDownQueryForClose(String projectkey, String stdate,String enddate,String drillDownUrl){
	
	String ISSUE_TYPES = "Defect";   
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") and status = Closed ");
	if(projectkey!=null){
		jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && stdate!=null && enddate!=null ){
		jql.append(" and updated >= "+stdate +" and updated <="+ enddate); 		 
	}
	if(drillDownUrl!=null && !drillDownUrl.equals("")){
		drillDownUrl = drillDownUrl.substring(0,drillDownUrl.length()-1);
		jql.append(" and key in ("+drillDownUrl +")" );
	}
	 //System.out.println("*******Query: "+jql.toString());
	 return jql.toString();
}
private String buildDrillDownQuery(String projectkey, String stdate,String enddate,String drillDownUrl){
	
	String ISSUE_TYPES = "Defect";   
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	if(projectkey!=null){
		jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && stdate!=null && enddate!=null ){
		jql.append(" and createdDate >= "+stdate +" and createdDate <="+ enddate); 		 
	}
	if(drillDownUrl!=null && !drillDownUrl.equals("")){
		drillDownUrl = drillDownUrl.substring(0,drillDownUrl.length()-1);
		jql.append(" and key in ("+drillDownUrl +")" );
	}
	 //System.out.println("*******Query: "+jql.toString());
	 return jql.toString();
}
private List<Issue> getIssuesOnFirstInterval(String projectkey,String startdate, String enddate) throws SearchException{
	List<Issue> issues = null;
	String ISSUE_TYPES = "Defect";   
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	if(projectkey!=null){
		jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && startdate!=null && enddate!=null ){
		jql.append(" and createdDate >= "+startdate +" and createdDate <="+ enddate );		 
	} 
	 //System.out.println("*******Query: "+jql.toString());
	final SearchService.ParseResult pResult= CommonService.getSearchService().parseQuery(CommonService.getJiraAuthenticationContext().getLoggedInUser(), (jql.toString()));
	if (pResult.isValid())  {  
		SearchResults issueList = CommonService.getSearchService().search(CommonService.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	    issues = issueList.getIssues();
	    //System.out.println("total issues: " + issues.size());
	}
 return issues;

}
private List<Issue> getIssuesForOpen(String projectkey,String startdate, String enddate) throws SearchException{
	List<Issue> issues = null;
	String ISSUE_TYPES = "Defect";   
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	if(projectkey!=null){
		jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && startdate!=null && enddate!=null ){
		jql.append(" and createdDate >= "+startdate +" and createdDate <="+ enddate );		 
	} 
	 //System.out.println("*******Query: "+jql.toString());
	final SearchService.ParseResult pResult= CommonService.getSearchService().parseQuery(CommonService.getJiraAuthenticationContext().getLoggedInUser(), (jql.toString()));
	if (pResult.isValid())  {  
		SearchResults issueList = CommonService.getSearchService().search(CommonService.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	    issues = issueList.getIssues();
	   // System.out.println("total issues: " + issues.size());
	}
 return issues;

}

private List<Issue> getIssuesForClosed(String projectkey,String startdate, String enddate) throws SearchException{
	List<Issue> issues = null;
	String ISSUE_TYPES = "Defect";   
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") and status = Closed ");
	if(projectkey!=null){
		jql.append(" and project = "+projectkey +"");		 
	} 
	if(projectkey!=null && startdate!=null && enddate!=null ){
		jql.append(" and updated >= "+startdate +" and updated <="+ enddate );		 
	} 
	// System.out.println("*******Query: "+jql.toString());
	final SearchService.ParseResult pResult= CommonService.getSearchService().parseQuery(CommonService.getJiraAuthenticationContext().getLoggedInUser(), (jql.toString()));
	if (pResult.isValid())  {  
		SearchResults issueList = CommonService.getSearchService().search(CommonService.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	    issues = issueList.getIssues();
	    //System.out.println("total issues: " + issues.size());
	}
 return issues;

}
private List<Issue> getIssues(String projectkey,String startdate, String enddate) throws SearchException{
		List<Issue> issues = null;
		String ISSUE_TYPES = "Defect";   
		StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
		if(projectkey!=null){
			jql.append(" and project = "+projectkey +"");		 
		} 
		if(projectkey!=null && startdate!=null && enddate!=null ){
			jql.append(" and createdDate > "+startdate +" and createdDate <="+ enddate );		 
		} 
		 //System.out.println("*******Query: "+jql.toString());
		final SearchService.ParseResult pResult= CommonService.getSearchService().parseQuery(CommonService.getJiraAuthenticationContext().getLoggedInUser(), (jql.toString()));
		if (pResult.isValid())  {  
			SearchResults issueList = CommonService.getSearchService().search(CommonService.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
		    issues = issueList.getIssues();
		    //System.out.println("total issues: " + issues.size());
		}
	 return issues;
	
}
private SimpleDateFormat getDefaultFormatter(){
	 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	 return formatter;
}
private SimpleDateFormat getJiraFormatter(){
	SimpleDateFormat jira_formatter = new SimpleDateFormat("yyyy-MM-dd");
	 return jira_formatter;
}
public boolean isIssueClosed(Date d,Issue issue) throws ParseException{
				 
	 boolean isClose = false;
		
	        	  List<ChangeItemBean>  beans= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
	        	  if(beans!=null && beans.size()>0){
	        		  ChangeItemBean bean = beans.get(beans.size()-1);
	        		  String statusdateStr= CommonService.convertDateFormat(bean.getCreated().toString());
	        		  String intervalDate = getDefaultFormatter().format(d);
	        		  Date statusdate = getDefaultFormatter().parse(statusdateStr);
	        		  if(statusdate.before(d) || intervalDate.equals(statusdateStr)){
	        		  if(bean.getToString().equalsIgnoreCase("Closed") || bean.getToString().equalsIgnoreCase("Cancel") || bean.getToString().equalsIgnoreCase("Differred")){
	      					//System.out.println("Closed "+bean.getCreated() + " Key: " +issue.getKey());
	      					isClose= true;							
	     				 }
	        		  }
	      		
	        	  }
		
		   return isClose;
}
	

	
}
