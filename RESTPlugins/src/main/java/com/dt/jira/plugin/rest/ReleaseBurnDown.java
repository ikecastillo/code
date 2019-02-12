package com.dt.jira.plugin.rest;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

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
import com.dt.jira.plugin.utils.Sprint;


/**
 * Restful web service implemented to show the burndown of Story points through associated sprints for a given release
 * and shows the trend lines for planned, optimistic and pessimistic velocity.
 * 
 * Planned velocity is the velocity assumed to plan the release. This could be based on an average of velocity achieved in past sprints or an
 * assumed velocity to start with. The average could be calculated in different variations 
 * Optimistic velocity – is a planned velocity but considers the best case situation
 * Pessimistic Velocity – is a planned velocity but considers the worst case situation
 */
@Path("/releaseversionburndown")
public class ReleaseBurnDown {

	public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes three input parameters project key, version.
 * It builds the jql query and get results  of Story points through associated sprints for a given release
 * @param projectkey - Project key is input value and mandatory value
 * @param version - Version - default value is All
 * @return Response -  Returns the Object Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("version") String version) throws Exception{
	
	String ISSUE_TYPES = "Story, 'Technical Story', Defect, Activity, Task";    
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

        
    CustomField  sprintField= fieldManager.getCustomFieldObjectByName("Sprint");
    
	  //String jql="project = SSP and fixVersion in ('Version 2.0') and Sprint in (2) ORDER BY Rank ASC";
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	  //jql.append("status not in (Cancel,Cancelled,Rejected) and 'Emergency Type' = yes "); 
	  
	  if(projectkey!=null){
		  jql.append(" and project = "+projectkey);		 
	  } 
	  if(projectkey!=null  && version!=null && !version.equals("All")){
		  jql.append(" and fixVersion in ("+ version +")");
	  } 
	 
	    log.info("Release Burn Down**************Query: "+jql.toString());
	    final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	    ArrayList<ReleaseBurnDownModel> listOfModels= new ArrayList<ReleaseBurnDownModel>();
		 Map map = new HashMap();
		 Map sprintMap = new HashMap();
		 long totalStPt = 0;
		
		 if (pResult.isValid())
	      {         
             SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
             List<Issue> issues = issueList.getIssues();
             ArrayList<Issue> tempIssues  = null;
		   		  for(Issue issue: issues){        		  
						ArrayList arrList = (ArrayList) issue.getCustomFieldValue(sprintField);
						if(arrList!=null && arrList.size()>0){
							
							for(int i=0;i<arrList.size();i++){
								Map mapSprint = getSprintMap(arrList.get(i).toString());
								String sprintStatus = (String)mapSprint.get("state");
								//log.info("Sprint status value: "+sprintStatus);
								if(sprintStatus.equalsIgnoreCase("Closed")){
									
									String keyStr = (String)mapSprint.get("id");
									Integer key = Integer.valueOf(keyStr);
									if(map.containsKey(key)){
										  ArrayList<Issue> list1 = (ArrayList<Issue>)map.get(key);
										  list1.add(issue);
											   map.put(key,list1);
									
									 } else {	
											String sprintId = (String)mapSprint.get("id");
											String sprintName =  (String)mapSprint.get("name");
											String sprintStartTime = (String)mapSprint.get("startDate");
											String sprintEndTime =  (String)mapSprint.get("endDate");
											String completeDate = (String)mapSprint.get("completeDate");
											Sprint sprint = new Sprint(sprintId,sprintName,sprintStartTime,sprintEndTime,sprintStatus,completeDate);
											
										  tempIssues = new ArrayList<Issue>(); 
										  tempIssues.add(issue);
										  map.put(key,tempIssues); 
										  sprintMap.put(key,sprint);
									 }
								 }
							}
						}
				 	}
		   	 Map sortMap = new TreeMap(sprintMap);/*1. Populate the map which contains the sprint details */
		   	 
		   	// log.info("sprint map:"+sortMap);
		   	 /* 2. build json object by iterating from map
		   	  * 1. get the backlog story points before start the sprint i.e using jql query- get all issues created before sprint start date
		   	  * 2. get the bakclog story points for each sprint i.e using jql query - get all the issues created before sprint end date 
		   	  * 3. find the complated stopry opints of each sprint i.e suing jql query - get all the issues created before sprint end date and sprint=<sprintId>*/
		   	 Set set = sortMap.keySet();
   			 Iterator it = set.iterator();
   			long completed = 0;
   			String opt = "";
   			String pst = "";
   			String planned = "";
   			int count=1;
   			 while(it.hasNext()){
   				 Integer key = (Integer)it.next();
   				 Sprint sprint = (Sprint) sortMap.get(key);
				 // log.info("size of the sprint: "+value.size());
   				 
      			 String name = sprint.getName();
      			 String sprintStartTime = sprint.getStartDate();
      			 String sprintEndTime=sprint.getEndDate();
      			 String completeDate = sprint.getCompleteDate();
      			 //String completedUrl = "";
   				
   				 if(count==1){
   					 long toTbklogStPonts = getBaklogsStPntBeforeSprint(jql.toString(),convertDateFormart(sprintStartTime));
   					 listOfModels.add(new ReleaseBurnDownModel("Backlog",String.valueOf(toTbklogStPonts),"0","-1001","","","","","")); 
   					log.info("Total no of sprint story points before sprint start: "+toTbklogStPonts);
   					totalStPt = toTbklogStPonts;
   				 }
   				
   				 count++;
   				long bklogStPonts = getBacklogStoryPointsOfSprint(jql.toString(),sprintStartTime, completeDate);
   				completed = getCompleteStoryPointsOfSprint(jql.toString(),key.toString(), completeDate);
   				log.info("Total no of sprint story points: "+bklogStPonts);
   				totalStPt = bklogStPonts;/*totalStPt - completed + bklogStPonts + commited;*/
   				// log.info("key: " + name + " backlog : " + totalStPt +" completed: " + completed  + " bklogStPonts: " + bklogStPonts + " committed: " + commited +" sprint key: "+key+ " " + 
   				//" opt: "+opt+ " "+" pst: "+pst+ " "+" planned:  "+planned+ " "+ "start date: "+convertDateFormart(sprintStartTime)+ " " + "end date: "+convertDateFormart(sprintEndTime));
   				log.info("key: " + name + " backlog : " + totalStPt+" completed :"+ completed);
   				 listOfModels.add(new ReleaseBurnDownModel(name, String.valueOf(totalStPt), String.valueOf(completed),key.toString(),opt,pst,planned,CommonService.convertDateFormat(sprintStartTime),CommonService.convertDateFormat(completeDate))); 
   			}
   			
   			
   			 if(listOfModels.size()>0){
   				 //Collections.sort(listOfModels, new ReleaseSorter());
   				 getStPntsFromLastSprint(listOfModels,jql.toString());
   				 setOptimisticPesimistic(listOfModels);
   			 } else {
				 long toTbklogStPonts = getBaklogsStPntBeforeSprint(jql.toString(),"");
				 // listOfModels.add(new ReleaseBurnDownModel("Backlog","0","0","-1001","","","","",""));
				 listOfModels.add(new ReleaseBurnDownModel("Backlog",String.valueOf(toTbklogStPonts),"0","-1001","","","","",""));				 
			 }
   			 
	      } 

			return Response.ok(listOfModels).build();		
	}
	/**
	 * Get the total story points of current date
	 * @param list - ArrayList<ReleaseBurnDownModel> Object
	 * @param query -  It is JQL query string
	 * @return - Returns long 
	 * @throws JiraException
	 */
	public  long getStPntsFromLastSprint(ArrayList<ReleaseBurnDownModel> list,String query) throws JiraException{
		   String startDate = list.get(list.size()-1).getStartDate();
		   Date date = new Date();
			String format= "yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			String dat = formatter.format(date);
		 long toTbklogStPonts = getBaklogsStPntOfLastSprint(query,dat);
		 list.add(new ReleaseBurnDownModel("Today",String.valueOf(toTbklogStPonts),"0",(new Integer(90000)).toString(),"","","",dat,dat)); 
			//log.info("Total no of sprint story points last sprint : "+toTbklogStPonts);
			return toTbklogStPonts;
		
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
		  //log.info(str1.substring(begin+1,end));
		   String str3[] = str1.substring(begin+1,end).split(",");
		   //Map map = new HashMap<String, String>();
		   for(int i=0;i<str3.length;i++){
			   //log.info(str3[i]);
			   String value[] = str3[i].split("=");			   
				try{
			   		map.put(value[0],value[1]);		   
					}catch(ArrayIndexOutOfBoundsException aie){
					log.info("Missing sprint for the given project ");
				}
		   }
		  }
		   //log.info(map.toString());
	 return map;	
	}
	 
	/**
	 * Get the total story points of before sprint start.
	 * @param query - <String> jql query
	 * @param startDate - <String> sprint start Date
	 * @return - Return <long> total story points
	 * @throws JiraException
	 */
	private  long  getBaklogsStPntBeforeSprint(String query, String startDate) throws JiraException {
		 SearchResults issueList = null;
		 String jql = "";
		 if(!startDate.equals("")) //status not in (Cancelled)
			jql= query + " and   created<= '"+startDate +"'";
		 else 
			jql= query + " and  status not in (Closed,Cancelled) and  sprint is empty";
			
		 long totStPt = 0;
		 CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
		 SearchService searchServ = CommonService.getSearchService();
		  JiraAuthenticationContext authenticationContext = CommonService.getJiraAuthenticationContext();
		  log.info("Release Burn down Backlog Query : "+ jql);
		  final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql);
		  if (pResult.isValid())
	      {         
           issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	      }
		  if(issueList!=null){
			  List<Issue> issues = issueList.getIssues();
		 	  int size= issues.size();
			  for(int i=0;i<size;i++){   
				  Issue issue = issues.get(i);
				  Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
				  long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
				  totStPt = totStPt + storyPointValue;
			 	}
		  }
		 return totStPt;
	}
	private  long  getBaklogsStPntOfLastSprint(String query, String endDate) throws JiraException {
		 SearchResults issueList = null;
		 String jql= query + " and   created<= now() and status not in (Cancelled)";
		 long totStPt = 0;
		 CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
		 SearchService searchServ = CommonService.getSearchService();
		  JiraAuthenticationContext authenticationContext = CommonService.getJiraAuthenticationContext();
		  //log.info(" jql11 story points : "+ jql);
		  final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql);
		  if (pResult.isValid())
	      {         
            issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	      }
		  if(issueList!=null){
			  List<Issue> issues = issueList.getIssues();
		 	  int size= issues.size();
			  for(int i=0;i<size;i++){ 
				  boolean b = false;
				  Issue issue = issues.get(i);
				  List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
				  //log.info("statusItem: "+statusItem + "issukey : "+issue.getKey() );
				  if(statusItem!=null)
					  b = getIssueLastModifiedStatus(statusItem,endDate);
				  if(!b){
				  Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
				  long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
				  totStPt = totStPt + storyPointValue;
				  }
			 	}
		  }
		 return totStPt;
	}
	/**
	 * Get the total story points of between sprint start and end date.
	 * @param query - <String> jql query
	 * @param startDate - <String> sprint start Date
	 * @param endDate - <String> sprint end Date
	 * @return - Return <long> total story points
	 * @throws JiraException
	 */
	private  long  getBacklogStoryPointsOfSprint(String query, String startDate,String endate) throws JiraException {
		SearchResults issueList = null;
		String format= "yyyy-mm-dd hh:mm";
		String jql= "";
		startDate = startDate.replace('T', ' ');
		startDate = startDate.substring(0,format.length());
		String endDate = endate;
		endDate = endDate.replace('T', ' ');
		endDate = endDate.substring(0, format.length());
		
		 jql= query + " and  created<='"+ endDate+"'";
		 long totStPt = 0;
		 CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
		 SearchService searchServ = CommonService.getSearchService();
		  JiraAuthenticationContext authenticationContext = CommonService.getJiraAuthenticationContext();
		  //log.info(" jql----> story points : "+ jql);
		  final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql);
		  if (pResult.isValid())
	      {         
            issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	      }
		  if(issueList!=null){
			  List<Issue> issues = issueList.getIssues();
		 	  int size= issues.size();
			  for(int i=0;i<size;i++){ 
				  boolean b = false;
				  Issue issue = issues.get(i);
				  List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
				  //log.info("statusItem: "+statusItem + "issukey : "+issue.getKey() );
				  if(statusItem!=null)
					  b = getIssueLastModifiedStatus(statusItem,endate);
				  if(!b){
				  Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
				  long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
				  totStPt = totStPt + storyPointValue;
				  }
			 	}
		  }
		 return totStPt; 
	}
	/**
	 * Is the issue last modified status is Resolved/Done/Closed.
	 * 
	 * @param beans - List<ChangeItemBean> collection change log history
	 * @param sprintEdDate <String> sprint end date
	 * @return Returns true/false
	 */
	private boolean getIssueLastModifiedStatus(List<ChangeItemBean>  beans, String sprintEdDate){		
		if(beans!=null && beans.size()>0){
			String format= "yyyy-mm-dd hh:mm:ss";
				ChangeItemBean item = beans.get(beans.size()-1);
				String sprintEndTime = "";
				if(item.getToString().equalsIgnoreCase("Done") || item.getToString().equalsIgnoreCase("Resolved") || item.getToString().equalsIgnoreCase("Closed")){
					Timestamp issueChaged = item.getCreated();
					if(sprintEdDate.indexOf('T')>0)
						sprintEndTime = sprintEdDate.replace('T', ' ');
					else 
						sprintEndTime = sprintEdDate;
					//log.info("sprint start Date1: "+sprintStartTime);
					sprintEndTime = sprintEndTime.substring(0,format.length());
					
					Timestamp sprintStartD =Timestamp.valueOf(sprintEndTime);
					if(issueChaged.before(sprintStartD)){
					       return true;
					}
				}
			}
	 return false;
	}
	/**
	 * Get the total story points which the issue is resolved/done/closed. 
	 * @param query - <String> jql query string
	 * @param id - <String> sprint id
	 * @param endDate <String> sprint end date
	 * @return - Return <long> - total story points
	 * @throws JiraException
	 */
		private  long  getCompleteStoryPointsOfSprint(String query, String id,String endDate) throws JiraException {
			SearchResults issueList = null;
			String format= "yyyy-mm-dd hh:mm";
			String jql= "";
			String tempDate = endDate;
			//startDate = startDate.replace('T', ' ');
			//startDate = startDate.substring(0,format.length());
			
			endDate = endDate.replace('T', ' ');
			endDate = endDate.substring(0, format.length());
			
			 jql= query + " and sprint="+id+" and  created<='"+ endDate+"'";
			 long totStPt = 0;
			 CustomField  storyPointField= CommonService.getCustomFieldManager().getCustomFieldObjectByName("Story Points");
			 SearchService searchServ = CommonService.getSearchService();
			  JiraAuthenticationContext authenticationContext = CommonService.getJiraAuthenticationContext();
			  log.info(" completed story points of the each sprint: "+ jql);
			  final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jql);
			  if (pResult.isValid())
		      {         
	            issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
		      }
			  if(issueList!=null){
				  List<Issue> issues = issueList.getIssues();
			 	  int size= issues.size();
				  for(int i=0;i<size;i++){   
					  Issue issue = issues.get(i);
					  List<ChangeItemBean>  statusItem= CommonService.getChangeHistoryManager().getChangeItemsForField(issue,"status");
					  boolean b = getIssueLastModifiedStatus(statusItem,tempDate);
					  if(b){
					  Double selectedVal = (Double) issue.getCustomFieldValue(storyPointField);
					  long storyPointValue = selectedVal!=null ? selectedVal.longValue() : 0;
					  totStPt = totStPt + storyPointValue;
					  }
				 	}
			  }
			 return totStPt; 
		}
	/**
	 * Set optimistic, pessimistic and planned velocity
	 * 
	 * Planned velocity is the velocity assumed to plan the release. This could be based on an average of 
	 * velocity achieved in past sprints or an assumed velocity to start with. The average could be calculated 
	 * in different variations – see formula Optimistic velocity – is a planned velocity but considers the best case situation
	 * Pessimistic Velocity – is a planned velocity but considers the worst case situation
	 */
	private void setOptimisticPesimistic(ArrayList<ReleaseBurnDownModel> list){
		long avgStPnts = 0;
		long totStPnts = 0;
		String endDate = "";
		String format= "yyyy-mm-dd";
		long lastSprintStPnts = 0;
		int noofCompleted = 0;
		for(int i=0;i<list.size();i++){
			ReleaseBurnDownModel sModel = list.get(i);
			long completed = Long.valueOf(sModel.getCompletedStPoints());
			if(completed>0){
			double vel =calculateVelocity(completed,sModel.getStartDate(),sModel.getEndDate());
			totStPnts = totStPnts + (long)vel;
			log.info("ReleaseBurnDown vel: " + avgStPnts + " sprint name : "+ sModel.getSprintName());
			}
			if(completed==0)
				noofCompleted++;	
			
			endDate = sModel.getEndDate();
			lastSprintStPnts = Long.valueOf(sModel.getBacklogStPoints());
			if(i==(list.size()-1)){
				sModel.setOptimisticValue(String.valueOf(lastSprintStPnts));
				sModel.setPesimisticValue(String.valueOf(lastSprintStPnts));
				sModel.setPlannedValue(String.valueOf(lastSprintStPnts));				
			}
		}
		//endDate = endDate.replace('T', ' ');
		endDate = endDate.substring(0,format.length());
	
		if(totStPnts>0 && ((list.size()-2) - noofCompleted) >0)
			avgStPnts = totStPnts/((list.size()-2) - noofCompleted); // list.size - no.of sprints - sprints which has total story points is zero
		log.info("ReleaseBurnDown avgStPnts " + avgStPnts);
		//log.info("avarage story points "+ avgStPnts);
		ArrayList<ReleaseBurnDownModel> listOfModels = list;
		//log.info("end date at findopt "+ endDate);
	
		
		/* if(count==sortMap.size()){
				 long toTbklogStPonts = getBaklogsStPntOfLastSprint(jql.toString(),convertDateFormart(completeDate));
				 listOfModels.add(new ReleaseBurnDownModel(convertDateFormart(completeDate),String.valueOf(toTbklogStPonts),"0",new Integer(90000).toString(),"","","","","")); 
				log.info("Total no of sprint story points after last sprint end: "+toTbklogStPonts);
				totalStPt = toTbklogStPonts;
		 }*/
		double optimisticpercent=1.3, pessimisticpercent=0.6;
		long optimisticBurndown = lastSprintStPnts, pessimisticBurndown = lastSprintStPnts, plannedBurndown=lastSprintStPnts;
		long optimisticburndownrate = (long)(avgStPnts*optimisticpercent);
		long pessimisticburndownrate =  (long)(avgStPnts*pessimisticpercent);
		long plannedburndownrate = avgStPnts;
		Date dt = stringToDate(endDate);
		int key = 90001;
		//listOfModels.r
		//listOfModels.add(new ReleaseBurnDownModel(addDays(dt, 14).toString(),"","",String.valueOf(key++),String.valueOf(optimisticBurndown),String.valueOf(pessimisticBurndown),String.valueOf(plannedBurndown),"",""));
		int noofsprints = 0;
		while(true) {		
			optimisticBurndown = (long)(optimisticBurndown -  optimisticburndownrate);
			pessimisticBurndown =(long) (pessimisticBurndown -  pessimisticburndownrate);
			plannedBurndown = (long)(plannedBurndown -  plannedburndownrate);
			//dt.setDate(dt.getDay()+14);
			//ticks.push(dateFormat(dt,"mm/dd/yy"));
			
			noofsprints++;
			listOfModels.add(new ReleaseBurnDownModel(addDays(dt, 14).toString(),"","",String.valueOf(key++),String.valueOf(optimisticBurndown),String.valueOf(pessimisticBurndown),String.valueOf(plannedBurndown),"","")); 
			if (optimisticBurndown < 0 && pessimisticBurndown < 0 && plannedBurndown < 0) break;
			if(noofsprints==20){
				break;
			}
		}
		//log.info("optmistic and pesmistic: "+listOfModels);
	}
	/**
	 * This method converts from given string(yyyy-mm-dd hh:mm) to Date.
	 * 
	 * @param date - <String> date in string format
	 * @return - Returns <Date> 
	 */
	private String convertDateFormart(String date){
		String format= "yyyy-mm-dd hh:mm";
		
		date = date.replace('T', ' ');
		date = date.substring(0,format.length());
		return date;
	}
	/**
	 * This method converts from given string(yyyy-MM-dd) to Date.
	 * 
	 * @param dateString - <String> date in string format
	 * @return - Returns <Date> 
	 */
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
	/**
	 * This method is to add the number of days to the given input date.
	 * @param d - Date <Date> 
	 * @param days - number of days <int>
	 * @return - Return <String> date in string format
	 */
    public  String addDays(Date d, int days)
    {
		
		d.setTime(d.getTime() + (14 * 1000 * 60 * 60 * 24));
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
		String date2 = formatter1.format(d);
		//log.info(date2);
        return date2;
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
		  else {
			vel =  (completed / (networkDays-excludeDays)) * 5 ;
			vel = Math.round(vel*100.00)/100.00;
		}
		return vel;
	}
}
