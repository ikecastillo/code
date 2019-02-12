package com.dt.jira.plugin.rest;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;

/**
 * REST service that provides the statistics  ((# QA defects detected) / (# normalized tickets delivered)) * 100.
 * at a project/version/sprint level
 * The output is formatted for a jqplot pie-chart 
 * 
 */
@Path("/defectdensity")
public class DefectDensity {
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version and sprintId.
 * It builds the one jql query and get valid defects and # normalized tickets delivered.
 * # of Valid Defects: = Query looks like IssueType is Defect AND Status is not 'Cancelled', 'Deferred' AND Defect Cause is not Rejected, 'Not
 * Applicable', 'Existing Issue'.
 * 
 * # of Normalized tickets = # Story + # Technical Story + # Requirements + Performance Testing
 * 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectKey") String projectkey, @QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
	Response response=null;
	 // 1. find the total no of issues for valid defects
    String VALIDDEFECT_ISSUE_TYPES = "Defect"; 
	double totValidDef = 0;
    String appendQuery1 = " Status not in ('Cancelled', 'Deferred')  AND 'Defect Cause'  not in ('Rejected',  'Existing Issue','Not Applicable') ";
    double totStorypnts = 0; 
    String	STORY_ISSUE_TYPES = "Story, 'Technical Story', Requirements, 'Performance Testing'"; 	
    String appendQuery2 = "";
   
    
    StringBuffer jql1 = CommonService.buildJQLQuery(VALIDDEFECT_ISSUE_TYPES, projectkey, version, sprintId, appendQuery1);
   
    SearchResults results1 = CommonService.getSerarchResults(jql1.toString());
	if(results1!=null)
		totValidDef = results1.getIssues()!=null? results1.getIssues().size(): 0;
		 log.info("jql1 "+jql1.toString()+" total QA: "+ totValidDef);
	 // 2. build the query for Development story points
	 // 3. Calculate the total of Development story points
	 // 4. Calculated Defect Density 
    
	StringBuffer jql2 = CommonService.buildJQLQuery(STORY_ISSUE_TYPES, projectkey, version, sprintId, null);
	SearchResults results2 = CommonService.getSerarchResults(jql2.toString());
	  log.info("jql2: "+jql2.toString());	
   
	CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");

    List<Issue> issues = null;
    if(results2!=null)
    		issues = results2.getIssues();
 
     if(issues!= null && issues.size() > 0)
     {    
    	//List<Long> listId = new ArrayList<Long>();
	   	  for(Issue issue: issues){   		  
	   		 Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
			 double storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
			 totStorypnts = totStorypnts + storyPointValue;
			 //log.info(" Issue key: "+issue.getKey() +" Issue story: "+storyPointValue);
	   	  } //end for
     }// end if
    
     log.info("total Storys: "+totStorypnts);	
     String  d = calculateDefectDensity(totValidDef,totStorypnts);     
	 DefectDensityModel model = new DefectDensityModel(d,String.valueOf(totValidDef),String.valueOf(totStorypnts));
	 response= Response.ok(model).build();	
	
	 return response;
  }
/**
 * Calculate the defect density in %
 * @param validDef <double> - Number of valid defects
 * @param totStorypnts <double> - Total story points
 * @return Returns <String>
 */
	private String calculateDefectDensity(double validDef, double totStorypnts){
	  if(validDef>0 && totStorypnts > 0){
			double  d = (validDef/totStorypnts) * 100;
		    d = Math.round(d*100.00)/100.00;
			return String.valueOf(d);
		}
		return "0.00";
	}
}

