package com.dt.jira.plugin.rest;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * A Cumulative Chart that provides the break up of the Backlogs in terms of status mentioned below over a time
 *  period. 
 * We also given filter for Start Date, End Date & Interval along wtih Project & Release criteria
 * Following Issues Types should be considred for this Chart - Story, Technical Story, Performance Testing,Requirement and Defect
 */
@Path("/BacklogCumulativeFlow")
public class BacklogCumulativeFlowDiagram {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version, @QueryParam("startdate") String startdate,@QueryParam("enddate") String enddate,@QueryParam("issuetypes") String issuetypes,@QueryParam("interval") String interval) throws Exception{
	//public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version) throws Exception{
	 System.out.println("BacklogCumulativeFlowDiagram issuetypes: "+ issuetypes);
	 String ISSUE_TYPES = "";
	 String queryIssueTypes = "";
	 if(issuetypes.equals("All")){
		  ISSUE_TYPES = "Story, 'Technical Story', 'Performance Testing', Requirement, Defect";
	 } else {
		StringTokenizer st = new StringTokenizer(issuetypes,"|");		
		while (st.hasMoreTokens()) {
		String issueTypeName = st.nextToken();
            //System.out.println(issueTypeName);
			queryIssueTypes  = queryIssueTypes + "'"+issueTypeName+ "',";
        }
	 }
	 if(queryIssueTypes.length()>0){
		 queryIssueTypes = queryIssueTypes.substring(0, queryIssueTypes.length()-1);
		 //System.out.println("queryIssueTypes: "+ queryIssueTypes);
		 ISSUE_TYPES = queryIssueTypes;
	 }
	
	
	ArrayList<BacklogCumulativeFlowDiagramModel> listOfModels= new ArrayList<BacklogCumulativeFlowDiagramModel>();
   
try{
		
		Date inputStDate = CommonService.getJiraFormatter().parse(startdate);
		Date inputEnDate = CommonService.getJiraFormatter().parse(enddate);
	 	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
 
	 	String start_date=formatter.format(inputStDate); //change the start date format to dd-MMM-yyyy 			   
	  
	    Date startd = formatter.parse(start_date);
		
	    String end_date = formatter.format(inputEnDate);  //change the end date format to dd-MMM-yyyy 
	    Date endd = formatter.parse(end_date);
	    int interval_wk = Integer.valueOf(interval) * 7; //weeks
	    Date d = startd;
	    int count = 0;
		 String stdat= start_date;
		 String ticks = "";
		 List<Issue> finalList = getListOfIssues(ISSUE_TYPES, projectkey, version,CommonService.getJiraFormatter().format(inputStDate),CommonService.getJiraFormatter().format(inputEnDate));
		 //System.out.println("finalList: "+ finalList.size());
		 
		    while (d.before(endd)) {
			long inDefination = 0;
			 long readyForDev = 0;
			 long inProgress = 0;
			 long devComplete = 0;
			 long qaTesting = 0;
			 long qaComplete = 0;
			 long closed = 0;
			 String issueKey = "";
			 
			 count++;
		    	if(finalList!=null && finalList.size()>0){
		    	 for(Issue issue: finalList){
		    		 List<ChangeItemBean>  beans= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
		         	  if(beans!=null && beans.size()>0){
		       			 for(ChangeItemBean bean: beans){
		       				 	boolean b = isIssueLogBtnRange(bean, stdat,d);
								
		       				 	if(b){
								issueKey  = issueKey + issue.getKey() + "|";
		       				 		//System.out.println("isIssueLogBtnRange: "+b +" key "+issue.getKey());
			        				//System.out.println("Status : " +bean.getToString());
			         				 if(bean.getToString().equalsIgnoreCase("InDefinition")){
			         					 inDefination++;
			         					//System.out.println("InDefinition "+bean.getCreated());
			         				 }
			         				 if(bean.getToString().equalsIgnoreCase("Ready For Development")){
			         					 readyForDev++;
			         					//System.out.println("Ready For Development "+bean.getCreated());
			        				 }
			         				if(bean.getToString().equalsIgnoreCase("In Progress")){
			         					inProgress++;
			         					//System.out.println("In Progress "+bean.getCreated());
			        				 }
			         				if(bean.getToString().equalsIgnoreCase("Dev Integration Test")){
			         					devComplete++;
			         					//System.out.println("Dev Complete "+bean.getCreated());
			        				 }
			         				if(bean.getToString().equalsIgnoreCase("QA Test")){
			         					qaTesting++;
			         					//System.out.println("QA Testing "+bean.getCreated());
			        				 }
			         				if(bean.getToString().equalsIgnoreCase("QA Test Complete")){
			         					qaComplete++;
			         					//System.out.println("QA Complete "+bean.getCreated());
			        				 }
			         				if(bean.getToString().equalsIgnoreCase("Closed")){
			         					closed++;
			         					//System.out.println("Closed "+bean.getCreated());
			        				 }
		       				 	} // boolean end
		         			 } //beans for end
		         		} else { // beans if
		         			if(isIssueStatusIndefination(issue, stdat,d)){
		         				inDefination++;
		         			}
		         		}
		    	 } // finalList for end
		    	 
		    	}//finalList if
		    	//set the model		    	
		    	BacklogCumulativeFlowDiagramModel flowDiagramModel = new BacklogCumulativeFlowDiagramModel(dispalyDateformat(d.toString()), inDefination, readyForDev, inProgress, devComplete, qaTesting, qaComplete, closed);
		    	listOfModels.add(flowDiagramModel);
				//System.out.println(" stdat "+d.toString()+" inDefination " + inDefination+" readyForDev " +readyForDev+" inProgress " +inProgress+" devComplete " +devComplete+" qaTesting " +qaTesting+" qaComplete " +qaComplete+" closed " +closed);
				//System.out.println("issueKey: "+issueKey);
				 stdat =  dispalyDateformat(d.toString()) ; 
			    CommonService.addDays(d, interval_wk);
		    } // while end
		    
		}catch(Exception e){
			e.printStackTrace();
		}
   		 
   		return Response.ok(listOfModels).build();		
	}

private boolean isIssueStatusIndefination(Issue issue, String startDate, Date endDate) throws ParseException{
	boolean b = false;
	 Timestamp createdDate = issue.getCreated();		
	 String statusdateStr= CommonService.convertDateFormat(createdDate.toString());
	 Date created = CommonService.getDefaultFormatter().parse(statusdateStr);
	 Date intervalStartDate = CommonService.getDefaultFormatter().parse(startDate);
	//System.out.println(" startDate: "+startDate +" endDate : "+endDate);	
	if((created.compareTo(intervalStartDate) >= 0 )  && (created.compareTo(endDate) <= 0 ) && issue.getStatusObject().getName().equals("InDefinition") ){	
	       return true;
	}
	
	return b;
}
	private boolean isIssueLogBtnRange(ChangeItemBean item, String startDate, Date endDate) throws ParseException{
		boolean b = false;
		//Timestamp issueChaged = item.getCreated();		
		 String statusdateStr= CommonService.convertDateFormat(item.getCreated().toString());
		 Date statusdate = CommonService.getDefaultFormatter().parse(statusdateStr);
		 Date intervalStartDate = CommonService.getDefaultFormatter().parse(startDate);
		 
		//Timestamp intervalStartD =Timestamp.valueOf(startDate);
		//Timestamp intervalEndD =Timestamp.valueOf(endDate);
		//System.out.println(" startDate: "+startDate +" endDate : "+endDate);	
		if((statusdate.compareTo(intervalStartDate) >= 0 )  && (statusdate.compareTo(endDate) <= 0 ) ){	
		       return true;
		}
		
		return b;
	}
	// total story points from backlog of sprint
	private List<Issue> getListOfIssues(String ISSUE_TYPES,String projectkey, String version,String startDate,String endDate) throws JiraException{
		 SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
		    //CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
		    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
			StringBuffer jql = new StringBuffer();
			//Map map = new HashMap();
			//Map sprintMap = new HashMap();
			long totValidDef =0;
		    //jql query issuetype = Defect  and createdDate >= '2013-10-30' and createdDate <= '2013-12-30'
			if(projectkey!=null && startDate!=null && endDate!=null ){
				  jql.append(" and createdDate >= "+startDate +" and createdDate <="+ endDate );		 
				}    
		    String appendQuery1 = " Sprint is EMPTY "/* + jql.toString()*/;
		    //String appendQuery2 = " Sprint in closedSprints() " + jql.toString();
		    StringBuffer jql1 = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, version, null, appendQuery1);
			//StringBuffer jql2 = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, version, null, appendQuery2);
		   // System.out.println("**************Query: "+jql1.toString());
		    final SearchService.ParseResult pResult1= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql1.toString());
		    // final SearchService.ParseResult pResult2= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql2.toString());
			
				List<Issue> results = null;
				SearchResults issueList = null;
				List<Issue> listA = null;
				List<Issue> listB = null;
				List<Issue> finalList = new ArrayList<Issue>();
				
			if (pResult1.isValid())  {         
				issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult1.getQuery(), PagerFilter.getUnlimitedFilter());
		        results = issueList.getIssues();
			}
			if(results!=null){
				totValidDef = results !=null? results.size(): 0;
				listA = results; 
				//System.out.println("**************Query1: "+jql1.toString());
				//System.out.println("**************total issues: "+totValidDef);
				finalList.addAll(listA);
			}
			/*if (pResult2.isValid())  {         
				issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult2.getQuery(), PagerFilter.getUnlimitedFilter());
		        results = issueList.getIssues();
			}
			if(results!=null){
				totValidDef = results !=null? results.size(): 0;
				listB = results; 
				//System.out.println("**************Query2: "+jql2.toString());
				//System.out.println("**************total issues: "+totValidDef);
				finalList.addAll(listB);
		    }*/
			
			return finalList;
	}
	
	private String dispalyDateformat(String date){	
		String start_date = "";
		try{
		SimpleDateFormat dtf 
        = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date inputStDate = dtf.parse(date);
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		  start_date=formatter.format(inputStDate);
		}catch(Exception e){
			System.out.println("dateformat error: "+e.getMessage());
		}
		return start_date;
	}
	
	private Date stringToDate(String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}
    public  String addDays(Date d, int days)
    {
		
		d.setTime(d.getTime() + (14 * 1000 * 60 * 60 * 24));
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
		String date2 = formatter1.format(d);
		//System.out.println(date2);
        return date2;
    }

	
}
