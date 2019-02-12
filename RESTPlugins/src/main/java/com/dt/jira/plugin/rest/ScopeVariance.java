package com.dt.jira.plugin.rest;



import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.changehistory.ChangeHistoryManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Restful web service which is implemented to calculate the total no of story points committed when sprint start 
 * and total no of story points completed at end of sprint"
 */
@Path("/velocity")
public class ScopeVariance {
	
public final LoggerWrapper log = LoggerWrapper.with(getClass());

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes three input parameters project key, version.
 * It builds the jql query and get results for to calculate the total no of story points committed when sprint start 
 * and total no of story points completed at end of sprint
 * @param projectkey - Project key is input value and mandatory value
 * @param version - Version - default value is All
 * @return Response -  Returns the Object which contains Model
 * @throws JiraException
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version) throws Exception{
	
	String ISSUE_TYPES = "Story, 'Technical Story', Defect, Activity, Task";    
	String URL =   "/issues/?jql=";
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

   ChangeHistoryManager changeHistoryManager = ComponentAccessor.getChangeHistoryManager();
    
    CustomField  sprintField= fieldManager.getCustomFieldObjectByName("Sprint");
    CustomField  storyPointField= fieldManager.getCustomFieldObjectByName("Story Points");
	StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	  
	  if(projectkey!=null){
		  jql.append(" and project = "+projectkey);		 
	  } 
	  if(projectkey!=null  && version!=null && !version.equals("All")){
		  jql.append(" and fixVersion in ("+ version +")");
	  } 
	 
	  log.info("ScopeVariance**Query: "+jql.toString(),projectkey,version);
	    final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	    ArrayList<ScopeVarianceModel> listOfModels= new ArrayList<ScopeVarianceModel>();
		 Map map = new HashMap();
		 int totalDefects = 0;
		 if (pResult.isValid())
	      {         
             SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
             List<Issue> issues = issueList.getIssues();
             totalDefects = issues!=null ? issues.size() : 0;
             ArrayList<Issue> tempIssues  = null;
		   		  for(Issue issue: issues){        		  
						ArrayList arrList = (ArrayList) issue.getCustomFieldValue(sprintField);
						if(arrList!=null && arrList.size()>0){
							
							for(int i=0;i<arrList.size();i++){
								Map mapSprint = getSprintMap(arrList.get(i).toString());
								String sprintStatus = (String)mapSprint.get("state");
								log.info("Sprint status value: "+sprintStatus);
								if(sprintStatus.equalsIgnoreCase("Closed")){								
									String keyStr = (String)mapSprint.get("id");
									Integer key = Integer.valueOf(keyStr);
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
							}
						}
				 	}
   		   Map sortMap = new TreeMap(map);
   		     Set set = sortMap.keySet();
   			 Iterator it = set.iterator();
   			
   			String committedurl = "";
   			String completedUrl = "";
   		    //long totStpntsRelease = 0;
   			 while(it.hasNext()){
   				 Integer key = (Integer)it.next();
   				 ArrayList<Issue> value = (ArrayList<Issue>) sortMap.get(key);
				 log.info("size of the sprint: "+value.size());
   				 long commited = 0;
      			 long completed = 0;
      			 String name = "";
      			 String sprintStartTime = "";
      			 String sprintEndTime="";
      			 String tempurl = "";
      			 //String completedUrl = "";
      			 double totStpnts = 0;
      			 
   				 for(Issue issue1: value)
   				 {
   					ArrayList arrList1 = (ArrayList) issue1.getCustomFieldValue(sprintField);
						if(arrList1!=null && arrList1.size()>0){							
							for(int i=0;i<arrList1.size();i++){
								Map mapSprint = getSprintMap(arrList1.get(i).toString());
								String id = (String)mapSprint.get("id");
								if(id.equalsIgnoreCase(key.toString())){
									name =  (String)mapSprint.get("name");
									sprintStartTime = (String)mapSprint.get("startDate");
									sprintEndTime =  (String)mapSprint.get("completeDate");
									log.info("sprint name: "+name +"  startDate:     "+ sprintStartTime +" completeDate:  "+sprintEndTime);
									break;
								}
							}
						}
   					String status = issue1.getStatusObject().getNameTranslation(authenticationContext.getI18nHelper());
   					//log.info("Velocity chart : issue status value1: "+status);
   					
   					Double selectedVal = (Double) issue1.getCustomFieldValue(storyPointField);
					long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
					totStpnts = totStpnts + storyPointValue;
   					List<ChangeItemBean>  beans = changeHistoryManager.getChangeItemsForField(issue1,"Sprint");
   					List<ChangeItemBean>  statusItem= changeHistoryManager.getChangeItemsForField(issue1,"status");
   					//log.info("Velocity chart : issue status beans: "+statusItem);
   				
   					boolean isIssueAddedAfterSprint=true;
   					for(int j=0;j<beans.size();j++){
   						ChangeItemBean item= beans.get(j);
   					
   						boolean b= isSprintNameSameASItemname(key.toString(), item, sprintStartTime);
   						if(b){
   							log.info("Log Sprint name is same as sprint id "+ key +" issue name: "+issue1.getKey());
							isIssueAddedAfterSprint = adddedAfterSprint(item,sprintStartTime);
						log.info("issue name: "+issue1.getKey() +"  change Item bean :"+item+ " isIssueAddedAfterSprint " + isIssueAddedAfterSprint);
   							break;
   						}
	   					
   					}
					if(issueLastModifiedDateOnResolved(statusItem, sprintEndTime) && (status.equalsIgnoreCase("Done") || status.equalsIgnoreCase("Closed") ||  status.equalsIgnoreCase("Resolved"))){
					     log.info("issue name: "+issue1.getKey() +" is completed ");
   						 completed = completed + storyPointValue;
   					 } 
					 if(!isIssueAddedAfterSprint){
					   log.info("issue name: "+issue1.getKey() +" is committed ");
					   //log.info("issue name: "+issue1.getKey());
						tempurl =  tempurl + issue1.getKey() +",";
						commited = commited + storyPointValue;
					}
						  	         		
   				 }
   				 
   				 if(tempurl.equals("")){
   					committedurl = URL + jql.toString()+ " and sprint="+ key.toString() ; 
   				 } else {
   					committedurl = URL + jql.toString()+ " and sprint="+ key.toString() +" and key in ("+ tempurl.substring(0, tempurl.length()-1) +")"; 
   				 }
   				completedUrl =  URL + jql.toString()+ " and sprint="+ key.toString() +" and status in (Done, Resolved,Closed) "; 
   				
   				// % of Story Points Delivered  in each sprint
   				
   				double percentageOfsprint = (completed/totStpnts)*100;
   				percentageOfsprint = Math.round(percentageOfsprint*100.00)/100.00;
   				log.info("totStpnts: " + totStpnts);
   				log.info("key: " + name + " commited : " + commited + " completed: " + completed + "start time: "+sprintStartTime+ "Committed URL "+ committedurl);
				
   				ScopeVarianceModel scopeVarianceModel = new ScopeVarianceModel(name, String.valueOf(commited), String.valueOf(completed),key.toString(),committedurl,completedUrl);
   				scopeVarianceModel.setMessage7(CommonService.convertDateFormat(sprintStartTime));
   				scopeVarianceModel.setMessage8(CommonService.convertDateFormat(sprintEndTime));
				 scopeVarianceModel.setMessage9(String.valueOf(percentageOfsprint));
				 //scopeVarianceModel.setMessage9(String.valueOf(percentageOfsprint)+ '%');
   				
   				//Calculate vel/wk  =([@Completed]/NETWORKDAYS([@[Start Date]],[@[End Date]],Holidays!A:A))*5
   				double vel =calculateVelocity(completed,CommonService.convertDateFormat(sprintStartTime),CommonService.convertDateFormat(sprintEndTime));
   				scopeVarianceModel.setMessage10(String.valueOf(vel));
   				if(commited>0 || completed>0)
					listOfModels.add(scopeVarianceModel); 
   			}
   			 //Collections.sort(listOfModels, new IssueSorter());
   			 calculateTotAndPercentage(listOfModels);
	      } 
 log.info("End ScopeVariance ");
			return Response.ok(listOfModels).build();		
	}


/**
 * The method get the sprint details 
 * @param str - It is string which has sprint details
 * @return - Returns Map which has sprint details
 * 
 * ex: Map<id,sprintId>
 * 		Map<name,sprintName>
 * 		Map<startDate,sprintStartDate>
 * 		Map<endDate,sprintEndDate>
 */
	public Map getSprintMap(String str) {
		
		   //log.info(str.substring(0,str.length()));
		   Map map = new HashMap<String, String>();
		   
		   String str1 = str.substring(0,str.length());
		   //log.info(str1.indexOf('['));
		   //log.info(str1.indexOf(']'));
		   int begin = str1.indexOf('[');
		   int end = str1.indexOf(']');
		  if(begin>-1 && end >-1){
		  log.debug("Sprint Details : "+str1.substring(begin+1,end));
		   String str3[] = str1.substring(begin+1,end).split(",");
		   //Map map = new HashMap<String, String>();
		   for(int i=0;i<str3.length;i++){
			   /*log.info(str3[i]);
			   String value[] = str3[i].split("=");
			   map.put(value[0],value[1]);*/

			   /*
			   * Commented the above logic and replaced with the below code on behalf of WCDICC-1145.
			   * Since comma(,) is used as delimiter, if any of teh sprint field values contain comma, code is breaking.
			   * So to avoid the scenario code has been changed.
			   * */

			   log.debug(str3[i]);
			   if(str3[i].contains("=")) {
				   String value[] = str3[i].split("=");
				   if(value.length >1) {
					   map.put(value[0], value[1]);
				   }
				   else{
					   map.put(value[0], " ");
				   }
			   }
			   else{
				   String value[] = str3[i-1].split("=");
				   map.put(value[0], value[1]+str3[i]);
			   }

		   }
		  }
		   log.debug(map.toString());
	 return map;	
	}
	/**
	 * The method verify the sprint id is same as the sprint id which get from the change log history.
	 * @param id - sprint id
	 * @param item - ChangeItemBean object which has change log history
	 * @param sprintStartDate - sprint start date
	 * @return - Returns true/false
	 */
	private boolean isSprintNameSameASItemname(String id, ChangeItemBean item, String sprintStartDate){		
		 boolean flag=false;
		 String itemSprint = item.getTo()!=null && !(item.getTo()).equals("")? item.getTo(): "";
		// log.info("item sprint "+ itemSprint);
		 //log.info("sprint id "+ id);
		 if(itemSprint.indexOf(',')>0){
			 String sprintIds[] = itemSprint.split(",");
			 for(int i=0;i<sprintIds.length;i++){
				log.info("isSprintNameSameASItemname " + id + " "+ sprintIds[i] );
				 if(id.equalsIgnoreCase(sprintIds[i].trim())){
					 flag = true;
					 break;
				 }
			 }
		 } else {
			 if(id.equalsIgnoreCase(itemSprint)){
				 flag= true;
			 }
		 }
		 // log.info("flag value  "+ flag);
	 return flag;
	}
	/**
	 * The method verify the sprint end date is same as the sprint end date which get from the change log history.
	 * @param  beans- List<ChangeItemBean> object which has change log history
	 * @param sprintEdDate - sprint end date
	 * @return - Returns true/false
	 */
	private boolean issueLastModifiedDateOnResolved(List<ChangeItemBean>  beans, String sprintEdDate){		
		if(beans!=null && beans.size()>0){
			ChangeItemBean item = beans.get(beans.size()-1);
			String sprintEndTime = "";
			if(item.getToString().equalsIgnoreCase("Done") || item.getToString().equalsIgnoreCase("Resolved") || item.getToString().equalsIgnoreCase("Closed")){
				Timestamp issueChaged = item.getCreated();
				sprintEndTime = sprintEdDate.replace('T', ' ');
				log.info("issueChaged : "+issueChaged);
				
				sprintEndTime = sprintEndTime.substring(0,sprintEndTime.lastIndexOf('-'));
				Timestamp sprintStartD =Timestamp.valueOf(sprintEndTime);
				log.info("Sprint completed date : "+sprintStartD);
				if(issueChaged.before(sprintStartD) || issueChaged.equals(sprintStartD)){
				       return true;
				}
			}
		}
	 return false;
	}
	/**
	 * The method verify that is the sprint added after the sprint started. it returns true or false.
	 * @param item - ChangeItemBean object which has change log history
	 * @param sprintStartDate - sprint start date
	 * @return - Returns true/false
	 */
	private boolean adddedAfterSprint(ChangeItemBean item, String sprintStartDate){
		String sprintStartTime = "";
		Timestamp issueChaged = item.getCreated();
		sprintStartTime = sprintStartDate.replace('T', ' ');
		log.info("sprint start Date1: "+sprintStartTime);
		sprintStartTime = sprintStartTime.substring(0,sprintStartTime.lastIndexOf('-'));
		log.info("sprint start Date: "+sprintStartTime + " Issue Added @: " + issueChaged);
		Timestamp sprintStartD =Timestamp.valueOf(sprintStartTime);
		if(issueChaged.after(sprintStartD)){
		       log.info("Issue changed time is greater than sprint start time");
		       return true;
		}
		return false;
	}
	
	/**
	 * Calculate the total and percentage for all the objects in the list.
	 * @param list - ArrayList<ScopeVarianceModel> 
	 */
	public void calculateTotAndPercentage(ArrayList<ScopeVarianceModel> list){
		double totComitted = 0;
		double totCompleted=0;
		double totPercentSprint=0;
		double total = 0;
		
		String urlForQA = "";
		String urlForProd = "";
		if(list!=null && list.size()>0){
		for(int i=0;i<list.size(); i++){
			ScopeVarianceModel scopeVarianceModel =  list.get(i);
			totComitted = totComitted  + Long.valueOf(scopeVarianceModel.getMessage2());
			totCompleted = totCompleted  + Long.valueOf(scopeVarianceModel.getMessage3());
			totPercentSprint = totPercentSprint + Double.valueOf(scopeVarianceModel.getMessage9());
			scopeVarianceModel.setMessage9(String.valueOf(scopeVarianceModel.getMessage9())+'%');
		}
		//String urlForFF  = this.drilldownPieUrl + urlForQA ;
		//String 	urlForRework = this.drilldownPieUrl + urlForProd;
		list.add(new ScopeVarianceModel("Total", String.valueOf((long)totComitted), String.valueOf((long)totCompleted),"","",""));
	    double avgComitted = totComitted/(list.size()-1);
	    avgComitted = Math.round(avgComitted*100.00)/100.00;
	    
	    double avgCompleted = totCompleted/(list.size()-1);
	    avgCompleted = Math.round(avgCompleted*100.00)/100.00;

		double avgPercentSprint = totPercentSprint/(list.size()-1);
		avgPercentSprint = Math.round(avgPercentSprint*100.00)/100.00;

		ScopeVarianceModel scModel = new ScopeVarianceModel("Average", String.valueOf(avgComitted), String.valueOf(avgCompleted),"","","");
		scModel.setMessage9(String.valueOf(avgPercentSprint));

			list.add(scModel);
		}
	}
	public double calculateVelocity(long completed,String startDate,String endDate){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date startd = null;
		Date endd = null;
		try {
			startd = formatter.parse(startDate);
			endd = formatter.parse(endDate);
		} catch (ParseException e) {
					e.printStackTrace();
		}
		
		
		long networkDays = CommonService.getDateDiff(startd, endd, TimeUnit.DAYS);
		long excludeDays = CommonService.getHolidays(startDate, endDate);
		//log.info("completed: "+ completed);
		//log.info("networkDays: "+ networkDays);
		//log.info("excludeDays: "+ excludeDays);
		//double vel= ( completed / (networkDays-excludeDays)) * 5;
		double vel = 0.0;
		if(completed == 0 || ((networkDays-excludeDays)== 0 ))
			vel = 0.0;
		  else 
			vel=  (completed / (networkDays-excludeDays));
		return vel;
	}
}
