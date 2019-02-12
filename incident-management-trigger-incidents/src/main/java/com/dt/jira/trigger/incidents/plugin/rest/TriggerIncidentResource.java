package com.dt.jira.trigger.incidents.plugin.rest;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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

import org.apache.log4j.Logger;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;


@Path("/triggerincidentcount")
public class TriggerIncidentResource {
	
/* Logger */
private final Logger logger = Logger.getLogger(TriggerIncidentResource.class);
public static String ISSUE_TYPES = "";

/**
 * This method is rest service method and takes input parameters solution group, severity, start date and end date.
 * It builds the one jql query and get valid incidents.
 *
 * @param solutionGroup
 * @param severity
 * @param startDate
 * @param endDate
 * @param status
 * @param reporttype
 * @param location
 * @param typs
 * @return Returns the Object which contains Model
 * @throws Exception
 */
@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

 public Response getMessage(@QueryParam("solutionGroup") String solutionGroup,
		 @QueryParam("severity") String severity,
		 @QueryParam("startDate") String startDate, 
		 @QueryParam("endDate") String endDate, 
		 @QueryParam("status") String status, 
		 @QueryParam("reporttype") String reporttype, 
		 @QueryParam("location") String location, 
		 @QueryParam("typs") String typs) throws Exception{
		
	String type = typs;
	
	//ISSUE_TYPES = reporttype; 
	if( reporttype!=null && ( reporttype.equalsIgnoreCase("IncidentDuration") ||  reporttype.equalsIgnoreCase("IncidentCount") ) ){
		logger.info("reporttype : "+ reporttype);
		ISSUE_TYPES = "Incident";    
	}
	
	StringBuffer drilldownjql = new StringBuffer();
		
	String URL =   "/issues/?jql=";
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
    CustomField  sgProductCF = fieldManager.getCustomFieldObjectByName("Solution Groups - Products");
    
    CustomField  impactedFunCF = fieldManager.getCustomFieldObjectByName("Type");
    logger.info("solutionGroup "+ solutionGroup + " severity "+ severity + " startDate : " + startDate  + " endDate: "+endDate + " location :"+location + " type :" + type );
	
	severity = severity.replace('|', ',');
    solutionGroup = solutionGroup.replace('|', ':');
	String arr[] = solutionGroup.split(":");
	List<String> listSG = Arrays.asList(arr);
	
	location = location.replace('|', ':');
	String arr1[] = location.split(":");
	List<String> listIF = Arrays.asList(arr1);
	
    String sDate = startDate;
	String eDate = endDate;
	
      String projectkey = "IT "+ISSUE_TYPES+" Management"; 
      StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ") ");
	 // jql.append("status not in (Cancelled,Rejected)");

	String solutionGrStringValue = "";
	String solutionGrOptionId = "";
	  
	  if(projectkey!=null){
		  jql.append(" and project = '"+projectkey+"'");		 
	  } 
	  
	  /* if severity is not null */
	  if(severity!=null && !severity.equals("")) {
		  /* check if severity is having None */
			if(severity.indexOf("None") > 0) {
				severity = severity.substring(0, severity.indexOf("None")-1);
				/* remove None from severity and replace with EMPTY */
				jql.append(" and ( Severity  in ("+severity +") OR Severity is EMPTY )");	
				/* after remove None from severity the jql query become like: and ( Severity  in (High,Critical,Medium,Low) OR Severity is EMPTY ) */
			} else {
				/* if None is not in severity the jql query become like: and Severity  in (High,Critical,Medium,Low) */
				jql.append(" and Severity  in ("+severity +")");
			}
		}
	  String drillDownForMonth = URL + jql.toString();
	  
	  String dateJQL ="";
	  /* check reporttype (incident/problem) for getting count/duration report which exists between (incident/problem) start and end date */
	  dateJQL = " and 'Incident Start'  >= '"+startDate + "' and 'Incident Start' <= '"+endDate + "'";
	  
      if( (startDate!=null && !startDate.equals("")) && (endDate!=null && !endDate.equals("")) ){		  
		  jql.append(dateJQL);
      }
      /* check status (incident/problem) for getting count/duration report */
	 if(status!=null && !status.equals("")) {
		status = status.replace('|', ',');
		String arrstatus[] = status.split(",");
		String statusAppend = "";
		for(int i=0;i<arrstatus.length;i++){
			statusAppend = statusAppend +"'"+arrstatus[i]+"',";
		}
		statusAppend = statusAppend.substring(0,statusAppend.length()-1);
		jql.append(" and ( Status  in ("+statusAppend +") )");
	 }
	 /* reads the (incident/problem) type custom field value by name whether it is Internal or External and append 
	  * i.e. cf[type id (Internal or External] in  cascadeOption(type value (Internal or External),location name) 
	  */
	 if( reporttype!=null && ( reporttype.equalsIgnoreCase("IncidentDuration") ||  reporttype.equalsIgnoreCase("IncidentCount") ) ){		
		 jql.append(" and 'Trigger Incident?'='Yes'");
		}
	 CustomField typeField = fieldManager.getCustomFieldObjectByName("Type");
	 long typeId = typeField.getIdAsLong();
	 if(typeField!=null){		
		 jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "')");
	 }
	 drilldownjql.append(jql); 
	 
		if(type.equals("Internal")){
			jql.append(" and ( ");
			for(int i = 0; i < listIF.size(); i++){				
				jql.append("cf["+typeId+"] in  cascadeOption('"+ type + "','" + listIF.get(i) + "')");
				if(i != (listIF.size() -1)){
					jql.append(" or ");
				}				
			}
			jql.append(" ) ");			
		}
		else{
			jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "')");
		}	
		
	 //System.out.println("Final JQL Statement: "+jql.toString());
	 logger.info("Final JQL Statement: "+jql.toString());
	 /* search the issues based on jql query */
	 final SearchService.ParseResult sevtMajor= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 
	 ArrayList<TriggerIncidentModel> listOfModels= new ArrayList<TriggerIncidentModel>();
	 Map map = new HashMap();
	 TriggerIncidentModel header = new TriggerIncidentModel();
	 header.setSolutionGroup("Major "+ISSUE_TYPES+"s");
	 String dateRange = getListOfMonths(sDate,eDate);
	 StringTokenizer  stringTokenizer = new StringTokenizer(dateRange,",");
	 ArrayList<TriggerIncidentMonthlyModel> headerModels= new ArrayList<TriggerIncidentMonthlyModel>();
	 while(stringTokenizer.hasMoreTokens()){
		 String token = stringTokenizer.nextToken();		 
		 headerModels.add(new TriggerIncidentMonthlyModel(token, "0"));
	 }
	 header.setMonthly(headerModels);
	 listOfModels.add(header); 
	    
	 if (sevtMajor.isValid())
      {         
              SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
              List<Issue> issues = results1.getIssues();
              logger.info("issues size: "+issues.size() );
              if(issues!= null && issues.size() > 0)
              {  
            	  ArrayList<Issue> newArry  = null;
            	  Map<String,List<Issue>> sgMap = new HashMap<String,List<Issue>>();
            	
            	  for(Issue is: issues){
            		  	if(is.getIssueTypeObject().getName().equals(ISSUE_TYPES)){ 
            		  		if(type.equals("External")){
								List solutionGroupList = (ArrayList)is.getCustomFieldValue(sgProductCF);
								if (solutionGroupList != null) {
									Option selectedSolGrp = (Option)solutionGroupList.get(0);
									solutionGrStringValue = solutionGroupList.get(0).toString();
									solutionGrOptionId = selectedSolGrp.getOptionId().toString();
									//logger.debug("Solution group option id is " + solutionGrOptionId);
									if(  listSG.contains(solutionGrStringValue) ) {
										if(sgMap.containsKey(solutionGrStringValue)){
											ArrayList<Issue> existArry = (ArrayList<Issue>)sgMap.get(solutionGrStringValue);
											existArry.add(is);
											sgMap.put(solutionGrStringValue,existArry);
										} else {
											newArry = new ArrayList<Issue>();
											newArry.add(is);
											sgMap.put(solutionGrStringValue,newArry);
										}
									}
								}
	            		    }
            		  		else{
            		  			Map<String,Option> impactedMap = (HashMap<String,Option>)is.getCustomFieldValue(impactedFunCF);
            		  			
            		  			Option parentOption = (Option) impactedMap.get(null);
            					Option childOption = (Option) impactedMap.get("1");
            					String impactedFnValue = String.valueOf(childOption.getValue());
            					if(sgMap.containsKey(impactedFnValue)){
									  ArrayList<Issue> existArry = (ArrayList<Issue>)sgMap.get(impactedFnValue);
									  existArry.add(is);
									  sgMap.put(impactedFnValue,existArry);								
								 } else {
									 newArry = new ArrayList<Issue>(); 
									 newArry.add(is);
									 sgMap.put(impactedFnValue,newArry);			
									  
								 }		
    	            		    
            		  		}
	            		  }
            				
            				
            			  
            	  }// end issues for loop 
            	  
            	  for(Map.Entry<String, List<Issue>>  mothmap: sgMap.entrySet()) {
            		  TriggerIncidentModel execSummaryModel = new TriggerIncidentModel();
					  String solutionGrOptId = "";
					  String key = mothmap.getKey();
					  List monthcountList = null;
					  Map<String,List<Issue>> finalJson = new HashMap<String,List<Issue>>();
					//if(!key.equals("RTS")){
					  List<Issue>  sgLists = (List<Issue>) sgMap.get(key);
					  for(Issue issue: sgLists){
							CustomField  incidentStart = fieldManager.getCustomFieldObjectByName(ISSUE_TYPES+" Start");
							//String _createdDate = "";
							String _createdDate = (String)issue.getCustomFieldValue(incidentStart).toString();
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
						     List solutionGroupList = (ArrayList)issue.getCustomFieldValue(sgProductCF);

						     //For all issues in this list, the solution group is gonna be the same, so just find it once
						     // and then get the option id - since we are using a multiselect cascade field!
						     if (solutionGroupList != null && solutionGrOptId.equals("")) {
								  Option selectedSolGrp = (Option) solutionGroupList.get(0);
								  solutionGrOptId = selectedSolGrp.getOptionId().toString();
							 }

					  }//for
					//}// if 
					//System.out.println("SG: "+ key);
					execSummaryModel.setSolutionGroup(key);
					/* Sets Drill Down for issue search */
					String drillDownUrl = setDrillDown(URL+drilldownjql.toString(),solutionGrOptId,key, type, typeId);
					execSummaryModel.setDrillDown(drillDownUrl);
          			//System.out.println("months: "+ finalJson.toString());
					 
					 ArrayList<TriggerIncidentMonthlyModel> monthlyList= new ArrayList<TriggerIncidentMonthlyModel>();
					 
					 String headers = getListOfMonths(startDate, endDate);
					 //System.out.println("headers: "+ headers);
					 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
					 int totalMonths = headerTokenizer.countTokens();
					 int indexMonth = 0;
					 while(headerTokenizer.hasMoreTokens()){
						 String monthName = headerTokenizer.nextToken();
						 TriggerIncidentMonthlyModel monthlyModel = null;
						 int flag = 0;
							for(Map.Entry<String, List<Issue>>  finaljsonVar: finalJson.entrySet()){
								
								String _key = finaljsonVar.getKey();
								List<Issue>  monthLists = (List<Issue>) finalJson.get(_key);
								//System.out.println("Moth: "+ monthName +" _key: "+ _key + " -Size: "+ monthLists.size());
								if(monthName.equalsIgnoreCase(_key)){
									
									if(reporttype!=null && (reporttype.equalsIgnoreCase("IncidentDuration"))){
										long incidentDuration = calculateIncidentDuration(monthLists);
										monthlyModel = new TriggerIncidentMonthlyModel(_key, getNumberFormat(incidentDuration));
									} else {
										monthlyModel = new TriggerIncidentMonthlyModel(_key, getNumberFormat(monthLists.size()));
									}
									
									/* Sets Monthly Drill Down for issue search */
									String monthlyUrl = setMonthlyDrillDown(reporttype, status, solutionGrOptId, key,_key,drillDownForMonth,indexMonth,totalMonths,startDate,endDate,type, typeId);
									monthlyModel.setDrillDown(monthlyUrl);
									monthlyList.add(monthlyModel);
									flag = 1;
									break;
								}
							}
							if(flag==0){
							monthlyModel = new TriggerIncidentMonthlyModel(monthName, "0");
							
							monthlyList.add(monthlyModel);
							}
							indexMonth++;
					 }
					execSummaryModel.setMonthly(monthlyList);
					
					//System.out.println("execSummaryModel :" + execSummaryModel.toString());
					listOfModels.add(execSummaryModel);
      			} // 
            	  
            }
       } else {
          	  listOfModels.add(new TriggerIncidentModel());
	   }
	 Collections.sort(listOfModels); // Sorting in ascending   
	 calculateTotAndPercentage(listOfModels,startDate, endDate);
	 
	 //COMMENTED THIS FUNCTION AS WE DO NOT NEED TO DISPLAY TOTAL MINUTES OF *****WE ONLY NEED TOTAL COUNT
	 //calculateAvarage(listOfModels,startDate, endDate);
	 
     return Response.ok(listOfModels).build();
	}

/**
 * calculate incident duration 
 *
 */
private long calculateIncidentDuration(List<Issue>  monthLists){
	CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
	CustomField incidentDurationCustomField = cfm.getCustomFieldObjectByName(ISSUE_TYPES+" Duration");								
	
	long incidentDuration =0L;
	for(int i=0;i<monthLists.size();i++){					
		if(monthLists.get(i).getCustomFieldValue(incidentDurationCustomField)!=null){
			incidentDuration+= Long.valueOf(monthLists.get(i).getCustomFieldValue(incidentDurationCustomField).toString().replaceAll(" Minutes", "").toString());
		}												
	}
	return incidentDuration;
}

/**
 * Calculate list of months exist between start and end date
 */
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

/**
 * reads next month
 */
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
/**
 * reads start date
 */
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
	return startd;
	
}

/**
 * reads end date
 */
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
	endd = lastdateFormat.format(date);
	return endd;
}

/**
 * reads month and year
 */
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


/**
 * Calculate the total and percentage for all the objects in the list which exist between start and end date.
 * @param list - ArrayList<ScopeVarianceModel> 
 */
public void calculateTotAndPercentage(ArrayList<TriggerIncidentModel> list,String startDate,String endDate){
	
	 String headers = getListOfMonths(startDate, endDate);
	 //System.out.println("headers: "+ headers);
	 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
	 TriggerIncidentModel execSummaryModel  = new TriggerIncidentModel();
	 List<TriggerIncidentMonthlyModel> monthlyTotalModel = new ArrayList<TriggerIncidentMonthlyModel>();
	 while(headerTokenizer.hasMoreTokens()){
		 String token = headerTokenizer.nextToken();
			long total = 0;
		 if(list!=null && list.size()>0){				
				for(int i=0;i<list.size(); i++){
					TriggerIncidentModel execModel =  list.get(i);
					List<TriggerIncidentMonthlyModel> execMonthlyModels = execModel.getMonthly();
					for(TriggerIncidentMonthlyModel execMonthlyModel : execMonthlyModels){
						String month = execMonthlyModel.getMonth();
						if(month.equals(token)){
							long incidentcount = parseNumberFormat(execMonthlyModel.getIncidentcount());
							total =  total + incidentcount;
							break;
						}
					}
				}
				monthlyTotalModel.add(new TriggerIncidentMonthlyModel(token, getNumberFormat(total)));
		}
	 }
	 execSummaryModel.setSolutionGroup("Total Count");
	 execSummaryModel.setMonthly(monthlyTotalModel);	
	 list.add(execSummaryModel);		
}

/**
*  calculate average issue count which exist between start and end date
*/
public void calculateAvarage(ArrayList<TriggerIncidentModel> list,String startDate,String endDate){
	
	 String headers = getListOfMonths(startDate, endDate);
	// System.out.println("headers: "+ headers);
	 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
	 TriggerIncidentModel execSummaryModel  = new TriggerIncidentModel();
	 List<TriggerIncidentMonthlyModel> monthlyTotalModel = new ArrayList<TriggerIncidentMonthlyModel>();
	 int count = 1;
	 while(headerTokenizer.hasMoreTokens()){
		 String token = headerTokenizer.nextToken();
		 double avg = 0;
		 
		 if(count>=3 && count<=(list.size()-2)){
			 TriggerIncidentModel execModel =  list.get(list.size()-1);
				if(execModel.getSolutionGroup().equals("Total Count")){
					List<TriggerIncidentMonthlyModel> execMonthlyModels = execModel.getMonthly();
					int countJ = count-3;
					double total = 0;
					for(int i=countJ;i<count; i++){
						TriggerIncidentMonthlyModel model = execMonthlyModels.get(i);
						//System.out.println("model.getMonth() " +model.getMonth() + "model.getIncidentcount() "+model.getIncidentcount());
						total = total +  Double.valueOf(model.getIncidentcount());							
					}
					if(total>0){
						avg = total/3;		
						avg = Math.round(avg*100.00)/100.00;					
						//System.out.println("avg "+avg);
					}								
				}
		 }
		 
		 monthlyTotalModel.add(new TriggerIncidentMonthlyModel(token, String.valueOf(avg)));
		 count++;
	 }
	 execSummaryModel.setSolutionGroup("Total Minutes of client impact");
	 execSummaryModel.setMonthly(monthlyTotalModel);	
	 list.add(execSummaryModel);
}

/**
 * monthly drill down url format for searching issues 
 *
 */
private String setMonthlyDrillDown(String reporttype, String status, String solutionGrpOptionId,String solutionGrStringValue,String month,String url,int indexMonth,int totalMonths, String startDate, String endDate,String type, long typeId){
	//System.out.println("solutionGrp: "+ solutionGrp + " month: "+ month + " url "+ url + " indexMonth : "+ indexMonth + " totalMonths: "+ totalMonths );
	StringBuffer drillUrl = new StringBuffer(url);
	String keyString = "";
	// add below condition for ampersand since it is query string which used for drill down
	if(solutionGrStringValue!=null && solutionGrStringValue.length()>0 && solutionGrStringValue.indexOf('&')>0){
		keyString = solutionGrStringValue.replaceAll("&", "%26");
		solutionGrStringValue = keyString;
	}

	if(type.equals("External")){
		drillUrl.append(" and  \"Solution Groups - Products\" in  MultiLevelCascadeOption(\""+  solutionGrpOptionId +"\") and  cf["+typeId+"] in  cascadeOption('"+ type + "')");
	}
	else{
		drillUrl.append(" and  cf["+typeId+"] in  cascadeOption('"+ type + "','" + solutionGrStringValue + "')");
	}

	if(indexMonth==0){
		drillUrl.append(" and 'Incident Start'  >= '"+startDate + "' and 'Incident Start' <= '"+getLastDate(month) + "'");
	} 
	if(indexMonth==(totalMonths-1)){
		drillUrl.append(" and 'Incident Start'  >= '"+ getStartDate(month) + "' and 'Incident Start' <= '"+ endDate + "'");
	}
	if(indexMonth>0 && indexMonth<(totalMonths-1)){
		drillUrl.append(" and 'Incident Start'  >= '"+getStartDate(month)  + "' and 'Incident Start' <= '"+getLastDate(month)  + "'");
	}	
	
    status = status.replace('|', ',');
	String arrstatus[] = status.split(",");
	String statusAppend = "";
	for(int i=0;i<arrstatus.length;i++){
		statusAppend = statusAppend +"'"+arrstatus[i]+"',";
	}
	statusAppend = statusAppend.substring(0,statusAppend.length()-1);
	drillUrl.append(" and ( Status  in ("+statusAppend +") )");
	drillUrl.append(" and 'Trigger Incident?'='Yes'");
	return drillUrl.toString();
}

	/**
	 * drill down url format for searching issues 
	 *
	 */
	private String setDrillDown(String url, String solutionGroupOptionId, String solutionGrStringValue, String type, long typeId){
		String drillUrl = "";
		String keyString ="";
		// add below condition for ampersand since it is query string which used for drill down
		if(solutionGrStringValue!=null && solutionGrStringValue.length()>0 && solutionGrStringValue.indexOf('&')>0){
			keyString = solutionGrStringValue.replaceAll("&", "%26");
			solutionGrStringValue = keyString;
		}
		if(type.equals("External")){
			drillUrl = url + " and  \"Solution Groups - Products\" in  MultiLevelCascadeOption(\""+ solutionGroupOptionId+"\") and  cf["+typeId+"] in  cascadeOption('"+ type + "')";
		}
		else{
			drillUrl = url + " and  cf["+typeId+"] in  cascadeOption('"+ type + "','" + solutionGrStringValue + "')";
		}
		return drillUrl;
	}
	
	/**
	 * Apply the default number format
	 * @param total total value 
	 * @return
	 */
	public String getNumberFormat(long total){
		String totalStr = "0";
		NumberFormat defaultFormat = NumberFormat.getInstance();
		totalStr = defaultFormat.format(total);
		return totalStr;
	}
	
	/**
	 * Apply the default  decimal format
	 * @param total - total value 
	 * @return
	 */
	public String getDecimalFormat(double total){
		String number = "0.0";
		DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");
		number = decimalFormat.format(total);
		return number;
	}
	/**
	 * Convert the Decimal format to long
	 * @param incidentcount - incidentcount value 
	 * @return
	 */
	public long parseNumberFormat(String incidentcount){
		
		NumberFormat defaultFormat = NumberFormat.getInstance();
		long l = 0;
		try {
			Number num = defaultFormat.parse(incidentcount);
			l = num.longValue();
		} catch (ParseException e) {		
			e.printStackTrace();
		}
		return l;
	}
}
