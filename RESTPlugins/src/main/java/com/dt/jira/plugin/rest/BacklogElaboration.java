package com.dt.jira.plugin.rest;



import java.util.ArrayList;
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
 * A resource of message.
 */
@Path("/backlogelaboration")
public class BacklogElaboration {

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("today") String today) throws Exception{
	
	String ISSUE_TYPES = "Story, 'Technical Story', Defect, Epic, Theme,Activity";
	int range = 0; String url=""; long r_story = 0; long r_technicalStory = 0; long r_defect = 0; long r_epic = 0; long  r_theme = 0; 	long  r_activity = 0; 	
	int range1 = 0; String url1=""; long r1_story = 0; long r1_technicalStory = 0; long r1_defect = 0; long r1_epic = 0; long  r1_theme = 0;	long  r1_activity= 0;
	int range2 = 0; String url2="";  long r2_story = 0; long r2_technicalStory = 0; long r2_defect = 0; long r2_epic = 0; long  r2_theme = 0;   long  r2_activity = 0;
	int range3 = 0; String url3="";  long r3_story = 0; long r3_technicalStory = 0; long r3_defect = 0; long r3_epic = 0; long  r3_theme = 0;	long  r3_activity = 0;
	int range4 = 0; String url4="";  long r4_story = 0; long r4_technicalStory = 0; long r4_defect = 0; long r4_epic = 0; long  r4_theme = 0;	long  r4_activity = 0;
	int range5 = 0; String url5="";  long r5_story = 0; long r5_technicalStory = 0; long r5_defect = 0; long r5_epic = 0; long  r5_theme = 0;	long  r5_activity = 0;
	
	
	 
    CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
    
    //jql query issuetype = Defect  and createdDate >= '2013-10-30' and createdDate <= '2013-12-30'
     
	 //String appendQuery = " Sprint is EMPTY and status in (InDefinition, 'Ready for Development') ";
    String appendQuery1 = " Sprint is EMPTY and status in (InDefinition, 'Ready for Development','Open') ";
    //String appendQuery2 = " Sprint in closedSprints() ";
	 double totValidDef = 0;
	 StringBuffer jql = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, version, null, "");
	 StringBuffer jql1 = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, version, null, appendQuery1);
	 //StringBuffer jql2 = CommonService.buildJQLQuery(ISSUE_TYPES, projectkey, version, null, appendQuery2);
	 SearchResults results1 = CommonService.getSerarchResults(jql1.toString());
	 //SearchResults results2 = CommonService.getSerarchResults(jql2.toString());
	 
	 List<Issue> listA = null;
	 //List<Issue> listB = null;
	 
	 if(results1!=null){
			totValidDef = results1.getIssues()!=null? results1.getIssues().size(): 0;
			listA = results1.getIssues(); 
    System.out.println("BacklogElaboration**************Query1: "+jql1.toString());
    System.out.println("BacklogElaboration**************total issues: "+totValidDef);
	 }
    /*if(results2!=null){
		totValidDef = results2.getIssues()!=null? results2.getIssues().size(): 0;
		listB = results2.getIssues(); 
		System.out.println("**************Query2: "+jql2.toString());
		System.out.println("**************total issues: "+totValidDef);
    }*/
    List<Issue> finalList = new ArrayList<Issue>();
    finalList.addAll(listA);
    //finalList.addAll(listB);
    
    if (finalList!=null)  {  
    	 
    	  for(Issue issue: finalList){
    		  String issueTypeName = issue.getIssueTypeObject().getName();
    		  
    		  Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
    		  double storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
    		  if(storyPointValue>=1 && storyPointValue <=5){
    			  range1++;
    			  url1 = url1 + issue.getKey() + ",";
    			  if("Story".equalsIgnoreCase(issueTypeName))
    				  r1_story++;
    			  if("Technical Story".equalsIgnoreCase(issueTypeName))
    				  r1_technicalStory++;
    			  if("Defect".equalsIgnoreCase(issueTypeName))
    				  r1_defect++;
    			  if("Epic".equalsIgnoreCase(issueTypeName))
    				  r1_epic++;
    			  if("Theme".equalsIgnoreCase(issueTypeName))
    				  r1_theme++;
    			  if("Activity".equalsIgnoreCase(issueTypeName))
    				  r1_activity++;
    		  } else if(storyPointValue>=6 && storyPointValue <=10) {
    			  range2++;
    			  url2 = url2 + issue.getKey() + ",";
    			  if("Story".equalsIgnoreCase(issueTypeName))
    				  r2_story++;
    			  if("Technical Story".equalsIgnoreCase(issueTypeName))
    				  r2_technicalStory++;
    			  if("Defect".equalsIgnoreCase(issueTypeName))
    				  r2_defect++;
    			  if("Epic".equalsIgnoreCase(issueTypeName))
    				  r2_epic++;
    			  if("Theme".equalsIgnoreCase(issueTypeName))
    				  r2_theme++;
    			  if("Activity".equalsIgnoreCase(issueTypeName))
    				  r2_activity++;
    		  } else if(storyPointValue>=11 && storyPointValue <=15) {
    			  range3++;
    			  url3 = url3 + issue.getKey() + ",";
    			  if("Story".equalsIgnoreCase(issueTypeName))
    				  r3_story++;
    			  if("Technical Story".equalsIgnoreCase(issueTypeName))
    				  r3_technicalStory++;
    			  if("Defect".equalsIgnoreCase(issueTypeName))
    				  r3_defect++;
    			  if("Epic".equalsIgnoreCase(issueTypeName))
    				  r3_epic++;
    			  if("Theme".equalsIgnoreCase(issueTypeName))
    				  r3_theme++;
    			  if("Activity".equalsIgnoreCase(issueTypeName))
    				  r3_activity++;
    		  } else if(storyPointValue>=16 && storyPointValue <=20) {
    			  range4++;
    			  url4 = url4 + issue.getKey() + ",";
    			  if("Story".equalsIgnoreCase(issueTypeName))
    				  r4_story++;
    			  if("Technical Story".equalsIgnoreCase(issueTypeName))
    				  r4_technicalStory++;
    			  if("Defect".equalsIgnoreCase(issueTypeName))
    				  r4_defect++;
    			  if("Epic".equalsIgnoreCase(issueTypeName))
    				  r4_epic++;
    			  if("Theme".equalsIgnoreCase(issueTypeName))
    				  r4_theme++;
    			  if("Activity".equalsIgnoreCase(issueTypeName))
    				  r4_activity++;
    		  } else if(storyPointValue>=21 && storyPointValue <=50) {
	    		  range5++;
	    		  url5 = url5 + issue.getKey() + ",";
	    		  if("Story".equalsIgnoreCase(issueTypeName))
	    			  r5_story++;
	    		  if("Technical Story".equalsIgnoreCase(issueTypeName))
		   			  r5_technicalStory++;
	    		  if("Defect".equalsIgnoreCase(issueTypeName))
		   			  r5_defect++;
		   		  if("Epic".equalsIgnoreCase(issueTypeName))
		   			  r5_epic++;
		   		  if("Theme".equalsIgnoreCase(issueTypeName))
		   			  r5_theme++;
		   		  if("Activity".equalsIgnoreCase(issueTypeName))
		   			  r5_activity++;
    		  } else if(storyPointValue<=0){
				  range++;
	    		  url = url + issue.getKey() + ",";
	    		  if("Story".equalsIgnoreCase(issueTypeName))
	    			  r_story++;
	    		  if("Technical Story".equalsIgnoreCase(issueTypeName))
		   			  r_technicalStory++;
	    		  if("Defect".equalsIgnoreCase(issueTypeName))
		   			  r_defect++;
		   		  if("Epic".equalsIgnoreCase(issueTypeName))
		   			  r_epic++;
		   		  if("Theme".equalsIgnoreCase(issueTypeName))
		   			  r_theme++;
		   		  if("Activity".equalsIgnoreCase(issueTypeName))
		   			  r_activity++;
			  }
    	  }
    }
    
    ArrayList<BacklogElaborationModel> listOfModels= new ArrayList<BacklogElaborationModel>();
      listOfModels.add(new BacklogElaborationModel("<=0", String.valueOf(r_story), String.valueOf(r_technicalStory), String.valueOf(r_defect),  String.valueOf(r_epic), String.valueOf(r_theme), String.valueOf(r_activity),buildURL(jql.toString(),url), ""));
    listOfModels.add(new BacklogElaborationModel("1-5", String.valueOf(r1_story), String.valueOf(r1_technicalStory), String.valueOf(r1_defect),  String.valueOf(r1_epic), String.valueOf(r1_theme),String.valueOf(r1_activity), buildURL(jql.toString(),url1), ""));
    listOfModels.add(new BacklogElaborationModel("6-10", String.valueOf(r2_story), String.valueOf(r2_technicalStory), String.valueOf(r2_defect),  String.valueOf(r2_epic), String.valueOf(r2_theme),String.valueOf(r2_activity), buildURL(jql.toString(),url2), ""));
    listOfModels.add(new BacklogElaborationModel("11-15", String.valueOf(r3_story), String.valueOf(r3_technicalStory), String.valueOf(r3_defect),  String.valueOf(r3_epic), String.valueOf(r3_theme), String.valueOf(r3_activity),buildURL(jql.toString(),url3), ""));
    listOfModels.add(new BacklogElaborationModel("16-20", String.valueOf(r4_story), String.valueOf(r4_technicalStory), String.valueOf(r4_defect),  String.valueOf(r4_epic), String.valueOf(r4_theme), String.valueOf(r4_activity), buildURL(jql.toString(),url4), ""));
    listOfModels.add(new BacklogElaborationModel("21-50", String.valueOf(r5_story), String.valueOf(r5_technicalStory), String.valueOf(r5_defect),  String.valueOf(r5_epic), String.valueOf(r5_theme), String.valueOf(r5_activity),buildURL(jql.toString(),url5), ""));    
    
	return Response.ok(listOfModels).build();		
	}

	
 private String buildURL(String query, String url){
     if(url!=null && url.equals(""))
	 return ""; 
	 url = url.substring(0,url.length()-1);
	 String drilDownUrl =  "/issues/?jql="+query+" and key in ("+url+")";
	 return drilDownUrl;
 }
	
}
