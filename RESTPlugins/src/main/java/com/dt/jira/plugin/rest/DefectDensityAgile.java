package com.dt.jira.plugin.rest;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;
import com.atlassian.jira.issue.link.IssueLink;
/**
 * REST service that provides the statistics  (# of Valid Defects / total Development Story Points delivered ) in %
 * at a project/version/sprint level
 * The output is formatted for a jqplot table chart 
 * 
 */
@Path("/defectdensityagile")
public class DefectDensityAgile {
	/* Logger */
	public final LoggerWrapper log = LoggerWrapper.with(getClass());
	
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters project key, version and sprintId.
 * It builds the one jql query and get valid defects and total development story points delivered.
 * # of Valid Defects: = Query looks like IssueType is Defect AND Status is not 'Cancelled', 'Deferred' AND Defect Cause is not Rejected, Not
 * Applicable, Existing Issue and Test Phase is "QA" or "PRODUCTION"
 * 
 * total Development Story Points = Get all the Issue Types which are linked to the above Defects (it could be Stories, Technical Stories) and then
 * get the total of all the story points for these issue types
 * 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectKey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId) throws Exception{
	Response response=null;
	 // 1. find the total no of issues for valid defects
    String VALIDDEFECT_ISSUE_TYPES = "Defect";    
	
	double totValidDef = 0;
    String appendQuery = " Status not in ('Cancelled', 'Deferred')  AND 'Defect Cause'  not in ('Rejected',  'Existing Issue','Not Applicable') and 'Test Phase' in (QA,Production)";
   
    
	
    StringBuffer jql = CommonService.buildJQLQuery(VALIDDEFECT_ISSUE_TYPES, projectkey, version, sprintId, appendQuery);
    log.info("jql "+jql.toString());
    SearchResults results1 = CommonService.getSerarchResults(jql.toString());
	if(results1!=null)
		totValidDef = results1.getIssues()!=null? results1.getIssues().size(): 0;

	 // 2. build the query for Development story points
	 // 3. Calculate the total of Development story points
	 // 4. Calculated Defect Density 
    
   
    CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
	
	
	 double totStorypnts = 0; 
	 List<Issue> parentissues = results1.getIssues();
     
     if(parentissues!= null && parentissues.size() > 0)
     {    
    	List<Long> listId = new ArrayList<Long>();
   	  for(Issue parentissue: parentissues){   		  
   		List<Issue> list = getLinkIssue(parentissue);
	   	 for (Iterator<Issue> outIterator = list.iterator(); outIterator.hasNext();) { 
	   		 Issue issue = outIterator.next();  
	   		 if(listId.contains(issue.getId())){
	   			 //log.info("Already Issue considered:"+issue.getKey());  
	   		 } else {
	   			 listId.add(issue.getId());
	   		 
	   		
		   		 Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
				 double storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
				 totStorypnts = totStorypnts + storyPointValue;
				 //log.info("Parent Issue key: "+parentissue.getKey()+" Issue key: "+issue.getKey() +" Issue story: "+storyPointValue);
	   		 }
	   	  } // inside for
   	  } //outside for
     }// end if
    
	
     String  d = calculateDefectDensity(totValidDef,totStorypnts);     
	 DefectDensityAgileModel model = new DefectDensityAgileModel(d);
	 response= Response.ok(model).build();	
	
	 return response;
  }
/**
 * Get the list of issues which are linked to the ticket.
 * @param parent <Issue> - Issue input parameter
 * @return - List<Issue> - list of issues
 */
	private List<Issue> getLinkIssue(Issue parent){
		List<Issue> list=new ArrayList<Issue>();
		 List<IssueLink> allOutIssueLink = ComponentAccessor.getIssueLinkManager().getOutwardLinks(parent.getId()); 
		    for (Iterator<IssueLink> outIterator = allOutIssueLink.iterator(); outIterator.hasNext();) { 
		        IssueLink issueLink = (IssueLink) outIterator.next();
                       Issue key = issueLink.getDestinationObject(); 
                       //log.info("Parent Issue key: "+parent.getKey()+" Issue key: "+key.getKey());
                       list.add(key);
                       
		    } 
		return list;
	}
	/**
	 * Calculate the defect density in %
	 * @param validDef <double> - Number of valid defects
	 * @param totStorypnts <double> - Total story points
	 * @return Returns <String>
	 */
	private String calculateDefectDensity(double validDef, double totStorypnts){
	  if(validDef>0 && totStorypnts > 0){
			double  d = validDef/(totStorypnts * 100);
			NumberFormat nf = new DecimalFormat("#.##");
			//log.info(nf.format(d));
			return nf.format(d);
		}
		return "0.00";
	}
}

