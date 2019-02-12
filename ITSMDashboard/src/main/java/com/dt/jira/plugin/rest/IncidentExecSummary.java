package com.dt.jira.plugin.rest;


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
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


/**
 * REST service that provides the data for incident count, incident duration, problem count and duration gadgets.
 * 
 */
@Path("/incidentexecsummary")
public class IncidentExecSummary {
	/* Logger */
	private final Logger logger = Logger.getLogger(IncidentExecSummary.class);
	
	public static String ISSUE_TYPES = "";
	public static String INCIDENT_OUTAGE_TYPES = "";
	public static String OUTAGE_TYPE = "Outage";
	
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * REST service that provides the data for incident count, incident duration, problem count and duration gadgets.
 * 
 * @param solutionGroup - Solution Group field
 * @param severity - Severity field
 * @param startDate - Start Date which is used to filter the issues
 * @param endDate   - End Date which is used to filter
 * @param status    - Status of issue
 * @param reporttype - this is source of the gadget which Incident related or Problem related gadgets
 * @param location  -  It is Type field  cascade value 
 * @param typs      - Type
 * @param cause     - Cause
 * @return
 * @throws Exception
 */
 public Response getMessage(@QueryParam("solutionGroup") String solutionGroup,
		 @QueryParam("severity") String severity,
		 @QueryParam("startDate") String startDate, 
		 @QueryParam("endDate") String endDate, 
		 @QueryParam("status") String status, 
		 @QueryParam("reporttype") String reporttype, /* is gadget incident related or problem related gadgets */
		 @QueryParam("location") String location, 
		 @QueryParam("typs") String typs, //type field
		 @QueryParam("cause") String cause) throws Exception{
	
	String type = typs;
	StringBuffer drilldownjql = new StringBuffer();/* construct the jql query for drill down*/
	// the issue type is incident for incident related gadgets
	if( reporttype!=null && ( reporttype.equalsIgnoreCase("IncidentDuration") ||  reporttype.equalsIgnoreCase("IncidentCount") ) ){
		logger.info("reporttype : "+ reporttype);
		INCIDENT_OUTAGE_TYPES = "Incident,Outage"; 
		ISSUE_TYPES = 	"Incident";
	}
	// the issue type is Problem for Problem related gadgets
	if(reporttype!=null && ( reporttype.equalsIgnoreCase("ProblemDuration") ||  reporttype.equalsIgnoreCase("ProblemCount") )){
		logger.info("reporttype : "+ reporttype);
	    INCIDENT_OUTAGE_TYPES = "Problem";  
		ISSUE_TYPES = "Problem";
	}
		
	String URL =   "/issues/?jql=";
	
    SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
    CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
    JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
    CustomField  sgProductCF = fieldManager.getCustomFieldObjectByName("Solution Groups - Products");
    
    CustomField  impactedFunCF = fieldManager.getCustomFieldObjectByName("Clients Impacted");
    logger.debug("solutionGroup "+ solutionGroup + " severity "+ severity + " startDate : " + startDate  + " endDate: "
			+endDate + " location :"+location + " type :" + type );
	/* Severity is multiselect on the gadget. The values are getting with | symbol*/
	severity = severity.replace('|', ',');
    solutionGroup = solutionGroup.replace('|', ':');
	String arr[] = solutionGroup.split(":");
	List<String> listSG = Arrays.asList(arr);
	/* location is multiselect on the gadget. The values are getting with | symbol*/
	location = location.replace('|', ':');
	String arr1[] = location.split(":");
	List<String> listIF = Arrays.asList(arr1);
	
    String sDate = startDate;
	String eDate = endDate;
	
    String projectkey = "IT "+ISSUE_TYPES+" Management"; 
	// start construct the jql query to fetch the issues
    StringBuffer jql = new StringBuffer("issueType in (" + INCIDENT_OUTAGE_TYPES + ") ");
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
	  String drillDownForMonth = URL + jql.toString();// Drill down jql query for each month
	  
	  String dateJQL ="";
	  /* check reporttype (incident/problem) for getting count/duration report
	  which exists between (incident/problem) start and end date */
	  /* Modified by Kiran: Issue with Incident Start <= <endDate> does not considered last date. To resolve the issue changed the jql query to < getNextDate(endDate of Month/endDate) */
	  if(reporttype.equalsIgnoreCase("ProblemDuration") ||  reporttype.equalsIgnoreCase("ProblemCount")){
		  dateJQL = " and 'Problem Start'  >= '" + startDate + "' and 'Problem Start' < '" + getNextDate(endDate) + "'";
	  } else{
    	  dateJQL = " and 'Incident Start'  >= '"+startDate + "' and 'Incident Start' < '"+getNextDate(endDate) + "'";
      }
	  
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
	/*if( reporttype!=null && ( reporttype.equalsIgnoreCase("IncidentDuration") ||  reporttype.equalsIgnoreCase("IncidentCount"))){
		 jql.append(" and 'Trigger Incident?'='No'");
	}*/
	/* Get the custom filed type value and id */
	CustomField typeField = fieldManager.getCustomFieldObjectByName("Clients Impacted");
	long typeId = typeField.getIdAsLong();
	if(typeField!=null){
		jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "')");
	}
	/* cause is multiselect on the gadget. The values are getting with | symbol*/
	if(!cause.equalsIgnoreCase("None")){
              cause=cause.replace("|", "','");             
               if(cause.contains(",'None")){
                   String causeNone=cause.replace("','None", "");
                   jql.append(" and (cause in ('"+ causeNone + "') or cause is EMPTY)");      
			  }else{
              jql.append(" and cause in ('"+ cause + "')");
              }
       }else{
              jql.append(" and cause is EMPTY");
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
	}else{
		jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "')");
	}	

	/* end the jql query construction with all the input field values */	
	logger.debug("ITSM Dashboard  JQL Statement: " + jql.toString());
	
	 /* search the issues based on jql query */
	 final SearchService.ParseResult sevtMajor= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
	 /* Build the list for each solution group */
	 ArrayList<IncidentExecSummaryModel> listOfModels= new ArrayList<IncidentExecSummaryModel>();
	 Map map = new HashMap();
	 /* Start build the list for the header */
	 IncidentExecSummaryModel header = new IncidentExecSummaryModel();
	 header.setSolutionGroup("Major "+ISSUE_TYPES+"s");
	 String dateRange = getListOfMonths(sDate,eDate);
	 StringTokenizer  stringTokenizer = new StringTokenizer(dateRange,",");
	 ArrayList<IncidentExecMonthlyModel> headerModels= new ArrayList<IncidentExecMonthlyModel>();
	 while(stringTokenizer.hasMoreTokens()){
		 String token = stringTokenizer.nextToken();		 
		 headerModels.add(new IncidentExecMonthlyModel(token, "0"));
	 }
	 header.setMonthly(headerModels);
	 listOfModels.add(header); 
	 /* end the list for the header */
	 if (sevtMajor.isValid()) {
              SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(),
					  PagerFilter.getUnlimitedFilter());
              List<Issue> issues = results1.getIssues();
              logger.info("total issues size: "+issues.size() );
              if(issues!= null && issues.size() > 0)  {
            	  ArrayList<Issue> newArry  = null;

				  /* Create the Map for if type is extternal or internal. If it is external then solution group as key
				  and list of issues as value other wise location as key and list of issues as value */
				  Map<String,List<Issue>> sgMap = new HashMap<String,List<Issue>>();
            	  for(Issue is: issues){// Iterated all the issues create the Map for each solution group.
            		  	if(is.getIssueTypeObject().getName().equals(ISSUE_TYPES) || is.getIssueTypeObject().getName().equals(OUTAGE_TYPE) ){ 
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

	            		    } else {
            		  			Map<String,Option> impactedMap = (HashMap<String,Option>)is.getCustomFieldValue(impactedFunCF);
            		  			
            		  			Option parentOption = (Option) impactedMap.get(null); // null is mapped to get parent value
            					Option childOption = (Option) impactedMap.get("1");  // 1 is mapped to get chilid value
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

            	  // Iterated the above created sgMap and re build the exexute summary model  for each solution group
            	  // and get added the map by calculating number issues for each month(Key is Month and list of issues as value).
            	  for(Map.Entry<String, List<Issue>>  mothmap: sgMap.entrySet()) {
            		  IncidentExecSummaryModel execSummaryModel = new IncidentExecSummaryModel();
					  String solutionGrOptId = "";
					  String key = mothmap.getKey();
					  List monthcountList = null;
					  Map<String,List<Issue>> finalJson = new HashMap<String,List<Issue>>();
					  List<Issue>  sgLists = (List<Issue>) sgMap.get(key);
					  for(Issue issue: sgLists){
							CustomField  incidentStart = fieldManager.getCustomFieldObjectByName(ISSUE_TYPES+" Start");						
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
					
					//System.out.println("SG: "+ key);
					execSummaryModel.setSolutionGroup(key);
					/* Sets Drill Down for issue search */
					  logger.debug("SG :" + key);
					String drillDownUrl = setDrillDown(URL+drilldownjql.toString(),solutionGrOptId,key, type, typeId);
					execSummaryModel.setDrillDown(drillDownUrl);          		
					 
					 ArrayList<IncidentExecMonthlyModel> monthlyList= new ArrayList<IncidentExecMonthlyModel>();
					 
					 String headers = getListOfMonths(startDate, endDate);					
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
								if(monthName.equalsIgnoreCase(_key)){
									if(reporttype!=null && (reporttype.equalsIgnoreCase("IncidentDuration") ||
											reporttype.equalsIgnoreCase("ProblemDuration"))){
										long incidentDuration = calculateIncidentDuration(monthLists);
										
										monthlyModel = new IncidentExecMonthlyModel(_key,getNumberFormat(incidentDuration));
									} else {
										monthlyModel = new IncidentExecMonthlyModel(_key, getNumberFormat(monthLists.size()));
									}
									/* Sets Monthly Drill Down for issue search */
									String monthlyUrl = setMonthlyDrillDown(reporttype, status,solutionGrOptId ,key,
											_key,drillDownForMonth,indexMonth,totalMonths,startDate,endDate,type,
											typeId,cause);
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
					execSummaryModel.setMonthly(monthlyList); // added monthly model list to execute summary model
					listOfModels.add(execSummaryModel);// added execute summary model to list.
      			} // 
            	  
            }
       } else {
          	  listOfModels.add(new IncidentExecSummaryModel());
	   }
	 Collections.sort(listOfModels); 
	 calculateTotAndPercentage(listOfModels,startDate, endDate);
	 calculateAvarage(listOfModels, startDate, endDate);
	
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
 * monthly drill down url format for searching issues 
 *
 */
private String setMonthlyDrillDown(String reporttype, String status, String solutionGrpOptionId,String solutionGrStringValue,String month,String url,int indexMonth,int totalMonths, String startDate, String endDate,String type, long typeId,String cause){
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
		if(reporttype.equalsIgnoreCase("ProblemDuration") ||  reporttype.equalsIgnoreCase("ProblemCount")){
			
		drillUrl.append(" and 'Problem Start'  >= '" + startDate + "' and 'Problem Start' < '" + getLastDate(month) + "'");
		
		}
		else{
			
		drillUrl.append(" and 'Incident Start'  >= '"+startDate + "' and 'Incident Start' < '"+getLastDate(month) + "'");
		
		}
	} 
	if(indexMonth==(totalMonths-1)){
		if(reporttype.equalsIgnoreCase("ProblemDuration") ||  reporttype.equalsIgnoreCase("ProblemCount")){
			
			drillUrl.append(" and 'Problem Start'  >= '" + getStartDate(month) + "' and 'Problem Start' < '" + getNextDate(endDate) + "'");
		}
		else{
			
			drillUrl.append(" and 'Incident Start'  >= '"+ getStartDate(month) + "' and 'Incident Start' < '"+ getNextDate(endDate) + "'");
			
		}
	}
	if(indexMonth>0 && indexMonth<(totalMonths-1)){
		if(reporttype.equalsIgnoreCase("ProblemDuration") ||  reporttype.equalsIgnoreCase("ProblemCount")){
			
			drillUrl.append(" and 'Problem Start'  >= '" + getStartDate(month) + "' and 'Problem Start' < '" + getLastDate(month) + "'");
		}
		else{
			
			drillUrl.append(" and 'Incident Start'  >= '"+getStartDate(month)  + "' and 'Incident Start' < '"+getLastDate(month)  + "'");
		}
	}	
	
    status = status.replace('|', ',');
	String arrstatus[] = status.split(",");
	String statusAppend = "";
	for(int i=0;i<arrstatus.length;i++){
		statusAppend = statusAppend +"'"+arrstatus[i]+"',";
	}
	statusAppend = statusAppend.substring(0,statusAppend.length()-1);
	drillUrl.append(" and ( Status  in ("+statusAppend +") )");
	if( reporttype!=null && ( reporttype.equalsIgnoreCase("IncidentDuration") ||  reporttype.equalsIgnoreCase("IncidentCount") ) ){
		//drillUrl.append(" and 'Trigger Incident?'='No'"); Commenting this out since we are adding Cause = 'Trigger'
		logger.debug("CAUSE VARIABLE IS " + cause);
		if(!cause.equalsIgnoreCase("None")){
              cause=cause.replace("|", "','");             
              if(cause.contains(",'None'")){
                     String causeNone=cause.replace(",'None'", "");
                     drillUrl.append(" and (cause in ('"+ causeNone + "') or cause is EMPTY)");      
              }else{
				  logger.debug("CAUSE DOES NOT CONTAIN NONE");
              drillUrl.append(" and cause in ('"+ cause + "')");
              }
       }else{
              drillUrl.append(" and cause is EMPTY");
       }
	}
	logger.debug("JQL Query check for Clause " + drillUrl.toString());
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
	 * Calculate the total and percentage for all the objects in the list which exist between start and end date.
	 * @param list - ArrayList<IncidentExecSummaryModel> 
	 */
	public void calculateTotAndPercentage(ArrayList<IncidentExecSummaryModel> list,String startDate,String endDate){
		
		 String headers = getListOfMonths(startDate, endDate);
		 //System.out.println("headers: "+ headers);
		 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
		 IncidentExecSummaryModel execSummaryModel  = new IncidentExecSummaryModel();
		 List<IncidentExecMonthlyModel> monthlyTotalModel = new ArrayList<IncidentExecMonthlyModel>();
		 while(headerTokenizer.hasMoreTokens()){
			 String token = headerTokenizer.nextToken();
				long total = 0;
			 if(list!=null && list.size()>0){				
					for(int i=0;i<list.size(); i++){
						IncidentExecSummaryModel execModel =  list.get(i);
						List<IncidentExecMonthlyModel> execMonthlyModels = execModel.getMonthly();
						if(execMonthlyModels !=null ){
							for(IncidentExecMonthlyModel execMonthlyModel : execMonthlyModels){
								String month = execMonthlyModel.getMonth();
								if(month.equals(token)){
									long incidentcount = parseNumberFormat(execMonthlyModel.getIncidentcount());
									total =  total + incidentcount;
									break;
								}
							}
						}
					}
					monthlyTotalModel.add(new IncidentExecMonthlyModel(token, getNumberFormat(total)));
			}
		 }
		 execSummaryModel.setSolutionGroup("Total");
		 execSummaryModel.setMonthly(monthlyTotalModel);	
		 list.add(execSummaryModel);		
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
/**
 *  calculate average issue count which exist between start and end date
 * @throws ParseException 
 */
	public void calculateAvarage(ArrayList<IncidentExecSummaryModel> list,String startDate,String endDate) throws ParseException{
		
		 String headers = getListOfMonths(startDate, endDate);
		// System.out.println("headers: "+ headers);
		 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
		 IncidentExecSummaryModel execSummaryModel  = new IncidentExecSummaryModel();
		 List<IncidentExecMonthlyModel> monthlyTotalModel = new ArrayList<IncidentExecMonthlyModel>();
		 int countMonth = 1;
		 while(headerTokenizer.hasMoreTokens()){
			 String token = headerTokenizer.nextToken();
			 double avg = 0;
			 //Calculate the average from 3 months.
			 if(countMonth>=3 ){
					IncidentExecSummaryModel execModel =  list.get(list.size()-1);
					if(execModel.getSolutionGroup().equals("Total")){
						List<IncidentExecMonthlyModel> execMonthlyModels = execModel.getMonthly();
						int countJ = countMonth-3;
						double total = 0;
						NumberFormat defaultFormat = NumberFormat.getInstance();
						for(int i=countJ;i<countMonth; i++){
							IncidentExecMonthlyModel model = execMonthlyModels.get(i);
							Number num = defaultFormat.parse(model.getIncidentcount());					
							total = total +  Double.valueOf(num.doubleValue());							
						}
						if(total>0){
							avg = total/3;
							avg = Math.round(avg*100.00)/100.00;					
						
						}								
					}
			 }
			 
			 monthlyTotalModel.add(new IncidentExecMonthlyModel(token, getDecimalFormat(avg)));
			 countMonth++;
		 }
		 execSummaryModel.setSolutionGroup("3 Month Average");
		 execSummaryModel.setMonthly(monthlyTotalModel);	
		 list.add(execSummaryModel);
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
		 d = addDays(date, 1);
		
		endd = lastdateFormat.format(d);
		return endd;
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
	

	/**
	 * Get next day of the current end date.
	 */
	private String getNextDate(String enddate){
		StringBuffer months = new StringBuffer();
		String dateString = enddate;
		
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");			
		Date convertedEndDate=null;
		try {
			convertedEndDate = startdateFormat.parse(dateString);			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Date d = addDays(convertedEndDate, 1);
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