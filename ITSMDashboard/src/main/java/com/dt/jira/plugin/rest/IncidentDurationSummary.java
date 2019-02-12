package com.dt.jira.plugin.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;


/**
 * REST service that provides the statistics of valid emergency production defects.
 * The output is formatted for a jqplot chart 
 */
@Path("/incidentdurationsummary")
public class IncidentDurationSummary {
/* Logger */
public final LoggerWrapper log = LoggerWrapper.with(getClass());
@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method and takes input parameters solution group, severity, start date and end date.
 * It builds the one jql query and get valid incidents.
 
 * @param projectkey - <String> project key is mandatory 
 * @param version - <String> version
 * @param sprintId - <String> - sprint id.
 * @return Returns the Object which contains Model
 * @throws Exception
 */
 public Response getMessage(@QueryParam("solutionGroup") String solutionGroup,@QueryParam("severity") String severity,@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws Exception{
	String ISSUE_TYPES = "Incident";    
	String URL =   "/issues/?jql=";
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
    CustomField  sgProductCF = fieldManager.getCustomFieldObjectByName("Solution Group - Product");
    log.info("solutionGroup "+ solutionGroup + "severity "+ severity + " startDate : " + startDate  + " endDate: "+endDate );
    
	severity = severity.replace('|', ',');
    solutionGroup = solutionGroup.replace('|', ':');
	String arr[] = solutionGroup.split(":");
	List<String> listSG = Arrays.asList(arr);
    String sDate = startDate;
	String eDate = endDate;
	
      String projectkey = "IT Incident Management"; 
	  StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") AND ");
	  jql.append("status not in (Cancelled,Rejected)"); 
	  
	  if(projectkey!=null){
		  jql.append(" and project = '"+projectkey+"'");		 
	  } 
	  if(severity!=null){
		  jql.append(" and Severity  in ("+severity +")");		 
	  }
	  String drillDownForMonth = URL + jql.toString();
	  if( (startDate!=null && !startDate.equals("")) && (endDate!=null && !endDate.equals("")) ){
		  jql.append(" and createdDate  >= '"+startDate + "' and createdDate <= '"+endDate + "'");	 
	  }
	
	 log.info("Query: "+jql.toString());
	 log.info("Query: "+jql.toString());
	  
	 final SearchService.ParseResult sevtMajor= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 ArrayList<IncidentExecSummaryModel> listOfModels= new ArrayList<IncidentExecSummaryModel>();
	 Map map = new HashMap();
	 IncidentExecSummaryModel header = new IncidentExecSummaryModel();
	 header.setSolutionGroup("Major Incidents");
	 String dateRange = getListOfMonths(sDate,eDate);
	 StringTokenizer  stringTokenizer = new StringTokenizer(dateRange,",");
	 ArrayList<IncidentExecMonthlyModel> headerModels= new ArrayList<IncidentExecMonthlyModel>();
	 while(stringTokenizer.hasMoreTokens()){
		 String token = stringTokenizer.nextToken();		 
		 headerModels.add(new IncidentExecMonthlyModel(token, "0"));
	 }
	 header.setMonthly(headerModels);
	 listOfModels.add(header); 
	    
	 if (sevtMajor.isValid())
      {         
              SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
              List<Issue> issues = results1.getIssues();
          	  log.info("issues size: "+issues.size());
          	  
              if(issues!= null && issues.size() > 0)
              {  
            	  ArrayList<Issue> newArry  = null;
            	  Map<String,List<Issue>> sgMap = new HashMap<String,List<Issue>>();
            	
            	  for(Issue is: issues){
            		  	if(is.getIssueTypeObject().getName().equals("Incident")){
            			Map<String,Option> solutionGroupMap = (HashMap<String,Option>)is.getCustomFieldValue(sgProductCF);
	            		    if(solutionGroupMap!=null){
		            			int sizeOfSolutionGpMap = solutionGroupMap.size();		            		        			
		            			String solutionGrValue = "";
				        			for(Map.Entry<String, Option> opt : solutionGroupMap.entrySet()) {
				            			Option llo = null;
				        				if(opt.getKey() ==  null ){
				        					llo = (Option) opt.getValue();
				        					String groupOptId = String.valueOf(llo.getOptionId());
				        					solutionGrValue = String.valueOf(llo.getValue());
				        					if( (!solutionGrValue.equalsIgnoreCase("RTS")) &&  (listSG.contains(solutionGrValue)) ){
					        					if(sgMap.containsKey(solutionGrValue)){
													  ArrayList<Issue> existArry = (ArrayList<Issue>)sgMap.get(solutionGrValue);
													  existArry.add(is);
													  sgMap.put(solutionGrValue,existArry);								
												 } else {
													 newArry = new ArrayList<Issue>(); 
													 newArry.add(is);
													 sgMap.put(solutionGrValue,newArry);			
													  
												 }				        					
				        					}  
				        				}
				        			} // end solution group for loop  		            			
		            		}
	            		  }
            				
            				
            			  
            	  }// end issues for loop 
            	  
            	  for(Map.Entry<String, List<Issue>>  mothmap: sgMap.entrySet()) {
            		IncidentExecSummaryModel durationSummaryModel = new IncidentExecSummaryModel();
            		Map  sgJson =new HashMap();
          			String key = mothmap.getKey();
					 List monthcountList = null;
					 Map<String,List<Issue>> finalJson = new HashMap<String,List<Issue>>(); 
					 List<Issue>  sgLists = (List<Issue>) sgMap.get(key);
						for(Issue issue: sgLists){
							 String _createdDate = issue.getCreated().toString();
							 String month = getMonthYear(_createdDate);
							 if(finalJson.containsKey(month)){
								List sg_issue = (ArrayList)finalJson.get(month);
								sg_issue.add(issue);
								finalJson.put(month,sg_issue);
							 } else {
								monthcountList = new ArrayList();
								monthcountList.add(issue);
								finalJson.put(month,monthcountList);
							 }
						}
					durationSummaryModel.setSolutionGroup(key);
					String drillDownUrl = setDrillDown(URL+jql.toString(),key);
					durationSummaryModel.setDrillDown(drillDownUrl);
					 
					 ArrayList<IncidentExecMonthlyModel> monthlyList= new ArrayList<IncidentExecMonthlyModel>();
					 
					 String headers = getListOfMonths(startDate, endDate);
					 log.info("headers: "+ headers);
					 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
					 int totalMonths = headerTokenizer.countTokens();
					 int indexMonth = 0;
					 
					 while(headerTokenizer.hasMoreTokens()){
						 String monthName = headerTokenizer.nextToken();
						 IncidentExecMonthlyModel monthlyModel = null;
						 int flag = 0;
							for(Map.Entry<String, List<Issue>>  finaljsonVar: finalJson.entrySet()){
								
								String _key = finaljsonVar.getKey();
								List<Issue>  monthLists = (List<Issue>) finalJson.get(_key);
								
								CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
								CustomField incidentDurationCustomField = cfm.getCustomFieldObjectByName("Incident Duration");								
								
								if(monthName.equalsIgnoreCase(_key)){
									long incidentDuration =0L;
									for(int i=0;i<monthLists.size();i++){					
										if(monthLists.get(i).getCustomFieldValue(incidentDurationCustomField)!=null){
											incidentDuration+= Long.valueOf(monthLists.get(i).getCustomFieldValue(incidentDurationCustomField).toString().replaceAll(" Minutes", "").toString());
										}												
									}
									monthlyModel = new IncidentExecMonthlyModel(_key, incidentDuration+"");
									String monthlyUrl = setMonthlyDrillDown(key,_key,drillDownForMonth,indexMonth,totalMonths,startDate,endDate);
									monthlyModel.setDrillDown(monthlyUrl);
									monthlyList.add(monthlyModel);
									flag = 1;
									break;
								}
							}
							if(flag==0){
							monthlyModel = new IncidentExecMonthlyModel(monthName, "0");
							
							monthlyList.add(monthlyModel);
							}
							indexMonth++;
					 }
					durationSummaryModel.setMonthly(monthlyList);
					
					log.info("durationSummaryModel :" + durationSummaryModel.toString());
					listOfModels.add(durationSummaryModel);
      			} // 
            	  
            }
       }
       else
          {
          	  listOfModels.add(new IncidentExecSummaryModel());
          }
        
	 calculateTotAndPercentage(listOfModels,startDate, endDate);
	 calculateAvarage(listOfModels,startDate, endDate);
	 
     return Response.ok(listOfModels).build();
	}

private String setMonthlyDrillDown(String solutionGrp,String month,String url,int indexMonth,int totalMonths, String startDate, String endDate){
	StringBuffer drillUrl = new StringBuffer(url);
	drillUrl.append(" and  \"Solution Group - Product\" in  cascadeOption(\""+ solutionGrp+"\")");
	if(indexMonth==0){
		drillUrl.append(" and createdDate  >= '"+startDate + "' and createdDate <= '"+getLastDate(month) + "'");
	} 
	if(indexMonth==(totalMonths-1)){
		drillUrl.append(" and createdDate  >= '"+ getStartDate(month) + "' and createdDate <= '"+ endDate + "'");
	}
	if(indexMonth>0 && indexMonth<(totalMonths-1)){
		drillUrl.append(" and createdDate  >= '"+getStartDate(month)  + "' and createdDate <= '"+getLastDate(month)  + "'");
	}
	
	return drillUrl.toString();
}

private String setDrillDown(String url, String key){
	String drillUrl = url + " and  \"Solution Group - Product\" in  cascadeOption(\""+ key+"\")";
	
	return drillUrl;
}

/**
 * Calculate the total and percentage for all the objects in the list.
 * @param list - ArrayList<ScopeVarianceModel> 
 */
public void calculateTotAndPercentage(ArrayList<IncidentExecSummaryModel> list,String startDate,String endDate){
	
	 String headers = getListOfMonths(startDate, endDate);
	 log.info("headers: "+ headers);
	 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
	 IncidentExecSummaryModel durationSummaryModel  = new IncidentExecSummaryModel();
	 List<IncidentExecMonthlyModel> monthlyTotalModel = new ArrayList<IncidentExecMonthlyModel>();
	 while(headerTokenizer.hasMoreTokens()){
		 String token = headerTokenizer.nextToken();
			long total = 0;
		 if(list!=null && list.size()>0){				
				for(int i=0;i<list.size(); i++){
					IncidentExecSummaryModel execModel =  list.get(i);
					List<IncidentExecMonthlyModel> execMonthlyModels = execModel.getMonthly();
					for(IncidentExecMonthlyModel execMonthlyModel : execMonthlyModels){
						String month = execMonthlyModel.getMonth();
						if(month.equals(token)){
							total =  total + Long.valueOf(execMonthlyModel.getIncidentcount());
							break;
						}
					}
				}
				monthlyTotalModel.add(new IncidentExecMonthlyModel(token, String.valueOf(total)));
		}
	 }
	 durationSummaryModel.setSolutionGroup("Total");
	 durationSummaryModel.setMonthly(monthlyTotalModel);	
	 list.add(durationSummaryModel);
	
}

public void calculateAvarage(ArrayList<IncidentExecSummaryModel> list,String startDate,String endDate){
	
	 String headers = getListOfMonths(startDate, endDate);
	 log.info("headers: "+ headers);
	 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
	 IncidentExecSummaryModel durationSummaryModel  = new IncidentExecSummaryModel();
	 List<IncidentExecMonthlyModel> monthlyTotalModel = new ArrayList<IncidentExecMonthlyModel>();
	 int count = 1;
	 while(headerTokenizer.hasMoreTokens()){
		 String token = headerTokenizer.nextToken();
			
		 double avg = 0;
		 if(count>=3 && count<=(list.size()-2)){
					IncidentExecSummaryModel execModel =  list.get(list.size()-1);
					if(execModel.getSolutionGroup().equals("Total")){
						List<IncidentExecMonthlyModel> execMonthlyModels = execModel.getMonthly();
						log.info("tostring "+execMonthlyModels.toString());
						int countJ = count-3;
						double total = 0;
						for(int i=countJ;i<count; i++){
							IncidentExecMonthlyModel model = execMonthlyModels.get(i);
							log.info("model.getMonth() " +model.getMonth() + "model.getIncidentcount() "+model.getIncidentcount());
							total = total +  Double.valueOf(model.getIncidentcount());
							
						}
						if(total>0){
							avg = total/3;		
							avg = Math.round(avg*100.00)/100.00;					
							log.info("avg "+avg);
						}
								
					}
		 }
				monthlyTotalModel.add(new IncidentExecMonthlyModel(token, String.valueOf(avg)));
		 count++;
	 }
	 durationSummaryModel.setSolutionGroup("3 Month Average");
	 durationSummaryModel.setMonthly(monthlyTotalModel);	
	 list.add(durationSummaryModel);
	
	 
	
}
	private String getMonthYear(String date){

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date d=null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String month = new SimpleDateFormat("MMM").format(d.getTime());
		String year = new SimpleDateFormat("yyyy").format(d.getTime());
		return month+"-"+year;
	}
	
	private String getListOfMonths(String startdate,String enddate){
		StringBuffer months = new StringBuffer();
		String startDate = startdate;
		String endDate = enddate;
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");			
		Date convertedStartDate=null;
		Date convertedEndDate=null;
		try {
			convertedStartDate = startdateFormat.parse(startDate);
			convertedEndDate = startdateFormat.parse(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		while(convertedStartDate.compareTo(convertedEndDate)<=0){
			String res = getNextMonth(startDate);
			months.append(new SimpleDateFormat("MMM-yyyy").format(convertedStartDate.getTime()));
			months.append(",");
			try {
				convertedStartDate = startdateFormat.parse(res);
				startDate = startdateFormat.format(convertedStartDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		String bufferStr  = "";
		if(months!=null && months.length()>0)
			bufferStr = months.toString().substring(0, months.length()-1);
		return bufferStr;
	}
	
	private String getStartDate(String MMM_YYYY){
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		SimpleDateFormat currentFormat = new SimpleDateFormat("MMM-yyyy");
		String currntMonth = MMM_YYYY;
		Date d=null;
		String startd ="";
		try {
			d=currentFormat.parse(currntMonth);
			startd = startdateFormat.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.info("startd "+ startd);
		return startd;		
	}
	
	private String getLastDate(String MMM_YYYY){
		SimpleDateFormat lastdateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		SimpleDateFormat currentFormat = new SimpleDateFormat("MMM-yyyy");
		String currntMonth = MMM_YYYY;
		Date d=null;
		try {
			d=currentFormat.parse(currntMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String endd = "";
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date date = c.getTime();
		log.info(date.toString());	
		endd = lastdateFormat.format(date);
		log.info("endd "+ endd);
		return endd;
	}
	
	private  String getNextMonth(String dateString){
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate=null;
		try {
			convertedDate = startdateFormat.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		c.setTime(convertedDate);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date date = c.getTime();
		
		Date d = addDays(date, 1);
		String returnDate=null;
		returnDate = startdateFormat.format(d);
		return returnDate;
		
	}
	
	private  Date addDays(Date d, int days)
    {
		Date date = d;
		date.setTime(d.getTime() + (days * 1000 * 60 * 60 * 24));	
        return date;
    }
}