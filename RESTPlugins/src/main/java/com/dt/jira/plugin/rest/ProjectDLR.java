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

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * Restful web service which is implemented to calculated for Project Defect Leakage.
 * Defect Leakage is a ratio of number of defects which made their way into production (leaked) to the total number of defects identified.
 */
@Path("/projectdlr")
public class ProjectDLR {
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
 * It builds the one jql query and get valid production defects associated project and another jql query to get total QA defects
 * 
 * @param projectkey - <String> project key 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id 
 * @param groupbyField - <String> - grouped by Defect Cause/Severity.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version,@QueryParam("sprintId") String sprintId,@QueryParam("groupbyField") String groupbyField) throws Exception{
	
	 String ISSUE_TYPES = "Defect";    
	 String appendQuery = " Status not in ('Cancelled', 'Deferred',Cancel) ";
	 double totValidDef = 0;
	 StringBuffer jql = CommonService.buildJQLQueryForDLR(ISSUE_TYPES, projectkey, version, sprintId, appendQuery);
	 SearchResults results1 = CommonService.getSerarchResults(jql.toString());
	 if(results1!=null)
			totValidDef = results1.getIssues()!=null? results1.getIssues().size(): 0;
        
     CustomField  customField= CommonService.getCustomFieldManager().getCustomFieldObjectByName(groupbyField);
  
	   log.info("**************Query: "+jql.toString());
	   System.out.println("DLR**************Query: "+jql.toString());
	   System.out.println("DLR**************total : "+totValidDef);
	    this.drilldownTableUrl = "/issues/?jql=" + jql ;
	    this.drilldownPieUrl = "/issues/?jql=" + jql ; 
	    
	   
	    ArrayList<ProjectDLRModel> listOfModels= new ArrayList<ProjectDLRModel>();
		 Map map = new HashMap();	
		
		 if (results1!=null &&  results1.getIssues()!=null && results1.getIssues().size()>0)
	      {
	         List<Issue> issues = results1.getIssues();
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
	   		 String urlForTable = "";
   			 String urlForQA = "";
   			 String urlForProd ="";
   		  	 String drillDownFF ="";
   		  	 String drillDownNotFF = "";
   		  	 
   		     Set set = map.keySet();
   			 Iterator it = set.iterator();   			
   			 while(it.hasNext()){
   				 String key = (String)it.next();
   				 ArrayList<Issue> value = (ArrayList<Issue>) map.get(key);
   				 int totalSev = value.size();
   				 //log.info("key: " + key + " totalSev: " + totalSev);
   				 
   				 int countQA= 0;
   				 int countProd=0;
   				 for(Issue issue1: value)	 {
  					
					String fieldValue=CommonService.getCustomFieldValue(issue1, "Test Phase");
				    if(!fieldValue.equalsIgnoreCase("Production")){
				    	countQA++;
				    	drillDownFF=  drillDownFF + issue1.getKey() + ",";
				    }

			    	if(fieldValue.equalsIgnoreCase("Production")){
			    		countProd++;
			    		drillDownNotFF=  drillDownNotFF + issue1.getKey() + ",";
			    	}	
   				 }
   				String grpBy = " AND \"" +  groupbyField + "\"";
				if (key.equalsIgnoreCase("other")) grpBy += " IS EMPTY";
				else grpBy += " = \"" + key + "\"";
				
   				urlForTable = this.drilldownTableUrl + grpBy;
   				urlForQA = this.drilldownPieUrl + " and 'Test Phase' not in (Production)";
   				urlForProd = this.drilldownPieUrl + " and 'Test Phase' = Production";
   				listOfModels.add(new ProjectDLRModel(key, String.valueOf(countQA), String.valueOf(countProd), String.valueOf(totalSev), urlForTable , urlForQA, urlForProd));
   			 } 
          	  calculateTotAndPercentage(listOfModels);
	      } 
		 return  Response.ok(listOfModels).build();	   
	}
/**
 * Calculate the total and percentage for all the objects in the list.
 * @param list - ArrayList<ProjectDLRModel> 
 */
public void calculateTotAndPercentage(ArrayList<ProjectDLRModel> list){
	double totQA = 0;
	double totProd=0;
	double total = 0;
	
	String urlForQA = "";
	String urlForProd = "";
	if(list!=null && list.size()>0){
	for(int i=0;i<list.size(); i++){
		ProjectDLRModel projectDLRModel =  list.get(i);
		totQA = totQA  + Long.valueOf(projectDLRModel.getQadefects());
		totProd = totProd  + Long.valueOf(projectDLRModel.getProddefects());
		total = total + Long.valueOf(projectDLRModel.getTotal());
		urlForQA = projectDLRModel.getUrlForQA();
		urlForProd =  projectDLRModel.getUrlForProd();
	}
	//String urlForFF  = this.drilldownPieUrl + urlForQA ;
	//String 	urlForRework = this.drilldownPieUrl + urlForProd;
	list.add(new ProjectDLRModel("Total", String.valueOf((long)totQA), String.valueOf((long)totProd), String.valueOf((long)total),"",urlForQA,urlForProd));
    double perFF = (totQA * 100)/total;  
    perFF = Math.round(perFF*100.00)/100.00;
    
    double perRework = (totProd * 100)/total;  
    perRework = Math.round(perRework*100.00)/100.00;
    
    list.add(new ProjectDLRModel("DLR", String.valueOf(perFF), String.valueOf(perRework),"100%","",urlForQA,urlForProd ));
	}
}


	
}
