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
 * REST service that provides the statistics  (# of Valid Defects / total Development Story Points delivered ) in %
 * at a project/version/sprint/type level
 * The output is formatted for a jqplot pie-chart. Pie chart is depicted based on the configuration type is Valid Defects/Normalized tickets
 * 
 */
@Path("/defectdensitypie")
public class DefectDensityPie {
	/* Drill down url for Bar chart view*/
	private String drilldownBarUrl;
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version and sprintId.
 * It builds the one jql query and get valid defects and total development story points delivered.
 * 
 * # of Valid Defects: = Query looks like IssueType is Defect AND Status is not 'Cancelled', 'Deferred' AND Defect Cause is not Rejected, 'Not
 * Applicable', 'Existing Issue' and Test Phase is "QA" or "PRODUCTION"
 * 
 * total Development Story Points = Get all the Issue Types which are linked to the above Defects (it could be Stories, Technical Stories) and then
 * get the total of all the story points for these issue types
 * 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @param type - <String> - type.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("type") String type,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
	Set set = null;
	Iterator it = null;
	String VALIDDEFECT_ISSUE_TYPES = "Defect"; 
	
    String appendQuery1 = " Status not in ('Cancelled', 'Deferred')  AND 'Defect Cause'  not in ('Rejected',  'Existing Issue','Not Applicable') ";
   
    String	STORY_ISSUE_TYPES = "Story, 'Technical Story', Requirements, 'Performance Testing'"; 	
   
    CustomField  customField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Severity");
	ArrayList<DefectDensityPieModel> listOfModels= new ArrayList<DefectDensityPieModel>(); 
    if(type.equals("Valid Defects")){
    
    	 Map map = new HashMap();
    	
    	StringBuffer jql1 = CommonService.buildJQLQuery(VALIDDEFECT_ISSUE_TYPES, projectkey, version, sprintId, appendQuery1);
		log.info("jql1: "+jql1.toString());	
    	this.drilldownBarUrl = "/issues/?jql=" + jql1 ;
    	SearchResults results1 = CommonService.getSerarchResults(jql1.toString());
		if(results1!=null){
			List<Issue> issues = results1.getIssues()!=null? results1.getIssues() : null;
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
			    set = map.keySet();
				it = set.iterator();
				 while(it.hasNext()){
					 String urlForBar = "";
					 String key = (String)it.next();
					 ArrayList<Issue> value = (ArrayList<Issue>) map.get(key);
					 int totalSev = value.size();
					 //log.info("key: " + key + " totalSev: " + totalSev);
					 urlForBar = this.drilldownBarUrl + " and Severity in ("+ key + ")";				
					 
					 listOfModels.add(new DefectDensityPieModel(key,String.valueOf(totalSev),urlForBar));
				 } // end while 
		}//end if
    } else if(type.equals("Normalized tickets")){
    
		StringBuffer jql2 = CommonService.buildJQLQuery(STORY_ISSUE_TYPES, projectkey, version, sprintId, null);
		SearchResults results2 = CommonService.getSerarchResults(jql2.toString());
		log.info("jql2: "+jql2.toString());	
		this.drilldownBarUrl = "/issues/?jql=" + jql2 ;
		CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
	 
		 Map storymap = new HashMap();
		
		 long totalStPt = 0;
		 if(results2!=null){
			List<Issue> issues = results2.getIssues()!=null? results2.getIssues() : null;
			ArrayList<Issue> tempIssues  = null;
	   		  for(Issue issue: issues){        		  
					Option selectedVal = (Option) issue.getCustomFieldValue(customField);
					String key= "";
					key = (selectedVal!=null)?selectedVal.getValue():"Other";        		 
	       		  if(storymap.containsKey(key)){
	       			  ArrayList<Issue> list1 = (ArrayList<Issue>)storymap.get(key);
	       			  list1.add(issue);
	       			storymap.put(key,list1);       		
	       		  } else {
	       			  tempIssues = new ArrayList<Issue>(); 
	       			  tempIssues.add(issue);
	       			storymap.put(key,tempIssues);        	    		
	       		  }
	       	  }// end for
   		     set = storymap.keySet();
   			 it = set.iterator();
   			 while(it.hasNext()){
   				 String key = (String)it.next();
   				 ArrayList<Issue> value = (ArrayList<Issue>) storymap.get(key);  				
   				 //log.info("key: " + key + " totalSev: " + totalSev);   				 
   				 long  totStorypnts= 0;
   				 String urlForBar = "";
   				 for(Issue issue1: value)
   				 {	 	
   					 Double selectedVal = (Double) issue1.getCustomFieldValue(storyPointField);
   					 long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
   					 totStorypnts = totStorypnts + storyPointValue;	 
   				 } // end for
   				//log.info(" Issue key: "+ key +" Issue story: "+totStorypnts);
   				urlForBar = this.drilldownBarUrl + " and Severity in ("+ key + ")";   			
   				listOfModels.add(new DefectDensityPieModel(key,String.valueOf(totStorypnts),urlForBar));
   			 } // end while          	  
	      }
    }// end if
		 return  Response.ok(listOfModels).build();	   
	}	
}
