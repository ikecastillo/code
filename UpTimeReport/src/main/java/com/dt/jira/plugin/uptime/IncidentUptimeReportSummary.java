package com.dt.jira.plugin.uptime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

@Path("/incidentuptimesummary")
public class IncidentUptimeReportSummary {
	
	private Logger log=LoggerFactory.getLogger(IncidentUptimeReportSummary.class);
	public static String CSV_PATH = "//opt//app//UpTimeCalculation.xls";
	public static String Output_PATH = "//opt//app//UpTimeCalculation_Out.xls";
		
	List<Issue> issues = new ArrayList<Issue>();
	
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	@GET
	@AnonymousAllowed
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getMessage(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("primeHours") String primeHours,
			@QueryParam("solutionGroup") String solutionGroup,
			@QueryParam("product") String product,
			@QueryParam("type") String type,
			@QueryParam("impacted") String impacted,
			@QueryParam("location") String location) throws Exception {
		String URL = "/issues/?jql=";
			
		SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
		CustomFieldManager fieldManager = ComponentAccessor.getCustomFieldManager();
		JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
		
		String projectkey = "IT Incident Management";
		String ISSUE_TYPES = "Incident";

		StringBuffer jql = new StringBuffer("issueType in (" + ISSUE_TYPES + ")");

		jql.append(" and project = '" + projectkey + "'");

		 
		CustomField typeField = fieldManager.getCustomFieldObjectByName("Clients Impacted");
		long typeId = typeField.getIdAsLong();
		if(type.equals("External")){
			jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "')");
			Map<String, String> optionIdMap = getOptionIdAsHashMap(solutionGroup, product);
		if (!product.equals("All")) {			
			//jql.append(" and 'Solution Group - Product' in  cascadeOption('"+ solutionGroup + "','" + product + "')");
			jql.append(" and 'Solution Groups - Products' in  MultiLevelCascadeOption('"+ optionIdMap.get("Solution Group") + "','" +  optionIdMap.get("Product") + "')");
		} else {			
			//jql.append(" and 'Solution Group - Product' in  cascadeOption('"+ solutionGroup + "')");
			jql.append(" and 'Solution Groups - Products' in  MultiLevelCascadeOption('"+ optionIdMap.get("Solution Group") + "')");
		  }
		}
		else{
				jql.append(" and cf["+typeId+"] in  cascadeOption('"+ type + "','" + location + "')");			
			if(!impacted.equals("All")){
				jql.append(" and 'Impacted - Function' in  cascadeOption('"+ impacted + "')");
			}		
		}
				
		if ((startDate != null && !startDate.equals("")) && (endDate != null && !endDate.equals(""))) {
			StringBuffer totalMonthlyJql = new StringBuffer();
			totalMonthlyJql.append(jql);
			totalMonthlyJql.append(" and 'Incident Start'  >= '" + startDate+ "' and 'Incident Start' <= '" + endDate + "'");
			
			//System.out.println("Monthly JQL Script: "+totalMonthlyJql.toString());
			/* Search the List of Issue based on startDate and endDate */
			final SearchService.ParseResult sevtMajor = searchServ.parseQuery(authenticationContext.getLoggedInUser(), (totalMonthlyJql.toString()));
			SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
			issues = results1.getIssues();
		}
		
		ArrayList<IncidentUptimeReportModel> listOfModels = new ArrayList<IncidentUptimeReportModel>();
		
		/* Reading date Range  */
		String dateRange = getListOfMonths(startDate, endDate);	
		
		IncidentMonthSummaryModel summaryModel = null;
		StringTokenizer stringTokenizer = new StringTokenizer(dateRange, ",");
		
		/* Monthly header */
		ArrayList<IncidentMonthSummaryModel> headerModels = new ArrayList<IncidentMonthSummaryModel>();
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken();
			headerModels.add(new IncidentMonthSummaryModel(token));
		}
		
		/* Set monthly header in Incident Uptime Report Model  */
		IncidentUptimeReportModel header = new IncidentUptimeReportModel(headerModels, "header", null);
		listOfModels.add(header);
		
		ArrayList<IncidentMonthSummaryModel> monthlyList = new ArrayList<IncidentMonthSummaryModel>();

		IncidentUptimeReportModel reportModel = null;
		ArrayList<IncidentDowntimeSummaryModel> downtimeList = new ArrayList<IncidentDowntimeSummaryModel>();
		
		/* Reading Incident Duration */		
		StringTokenizer stringTok = new StringTokenizer(dateRange, ",");
		
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		/* Custom Fields for incident duration calculation */
		CustomField incidentDurationCustomField = cfm.getCustomFieldObjectByName("Incident Duration");
    	
		/* Custom Fields for second level (product/impacted) minutes and uptime calculation */
    	//CustomField sgProductCF 	= fieldManager.getCustomFieldObjectByName("Solution Group - Product");
    	CustomField sgsProductsCF   = fieldManager.getCustomFieldObjectByName("Solution Groups - Products");
    	CustomField locImpactedCF 	= fieldManager.getCustomFieldObjectByName("Impacted - Function");
    	
    	/* To get the list of product/impacted (i.e. second level) minutes and uptime calculation */
    	if((type.equals("Internal") && impacted.equals("All")) || (type.equals("External") && product.equals("All"))){
    		
    	IncidentUptimeReportModel productHeaderModel = new IncidentUptimeReportModel();
    	productHeaderModel.setHeaders("Products/Impacted");
		listOfModels.add(productHeaderModel);
    	
    	  if(issues!= null && issues.size() > 0)
           {  
    		 ArrayList<Issue> newIssueArray  = null;
    		 Map<String,List<Issue>> productImpactedGroupMap = new HashMap<String,List<Issue>>();
    		 
	       	  for(Issue issue: issues){
	       	
					if(issue.getIssueType().getName().equals("Incident")){
						
							 IncidentUptimeReportModel productSummaryModel = new IncidentUptimeReportModel();
							  if(type.equals("External")){	
								  productSummaryModel.setTableHeader("ProductGroup");
									if(issue.getCustomFieldValue(sgsProductsCF)!=null){
									
									List<Option> productGroupList = (ArrayList<Option>) issue.getCustomFieldValue(sgsProductsCF);
									if (productGroupList != null && productGroupList.size()>1) {
										Option productGroupOpt =  productGroupList.get(1);											
										String productCFValue = String.valueOf(productGroupOpt.getValue());
										if(productImpactedGroupMap.containsKey(productCFValue)){
											ArrayList<Issue> existIssueArray = (ArrayList<Issue>)productImpactedGroupMap.get(productCFValue);
											existIssueArray.add(issue);
											productImpactedGroupMap.put(productCFValue,existIssueArray);								
										 } else {
											 newIssueArray = new ArrayList<Issue>(); 
											 newIssueArray.add(issue);
											 productImpactedGroupMap.put(productCFValue,newIssueArray);	
										 }
									}
								}
				      	     }else{
				      	    	productSummaryModel.setTableHeader("Impacted");
				      	    	if(issue.getCustomFieldValue(locImpactedCF)!=null){	
									if (locImpactedCF.getCustomFieldType() instanceof CascadingSelectCFType) {
										HashMap<String, Option> impactedGroupMap = (HashMap<String, Option>) issue.getCustomFieldValue(locImpactedCF);
										if (impactedGroupMap != null && impactedGroupMap.size()>0) {							
											Option parentSoln =  impactedGroupMap.get(CascadingSelectCFType.PARENT_KEY);
											String productCFValue = String.valueOf(parentSoln.getValue());
											if(productImpactedGroupMap.containsKey(productCFValue)){
											  ArrayList<Issue> existIssueArray = (ArrayList<Issue>)productImpactedGroupMap.get(productCFValue);
											  existIssueArray.add(issue);
											  productImpactedGroupMap.put(productCFValue,existIssueArray);								
											 } else {
												 newIssueArray = new ArrayList<Issue>(); 
												 newIssueArray.add(issue);
												 productImpactedGroupMap.put(productCFValue,newIssueArray);	
											 }
										  
										}
									 }
								} 
				      	     }
						}	
					
					
	       	     }
	       	  
				 for(Map.Entry<String, List<Issue>>  productImpactedGroupMonthMap: productImpactedGroupMap.entrySet()) {
			 	
			 	 IncidentUptimeReportModel productSummaryModel = new IncidentUptimeReportModel();
	   		     
	 			 String productImpactedGroup = productImpactedGroupMonthMap.getKey();
	 			 			 			 
				 List productImpactedMonthCountList = null;
				 
				 Map<String,List<Issue>> finalProductImpactedGroupMap = new HashMap<String,List<Issue>>(); 
				 
					List<Issue>  productImpactedLists = (List<Issue>) productImpactedGroupMap.get(productImpactedGroup);
					
					for(Issue productImpactedIssue: productImpactedLists){
						
						CustomField  incidentStartCF = fieldManager.getCustomFieldObjectByName(ISSUE_TYPES+" Start");
						String createdDate = (String)productImpactedIssue.getCustomFieldValue(incidentStartCF).toString();
						/* Gets product/Impacted Month*/
						 String productImpactedGroupMonth = getMonthYear(createdDate);
						 
						 /* Checks product/Impacted Month contains in the Map*/
						 if(finalProductImpactedGroupMap.containsKey(productImpactedGroupMonth)){
							 
							List productImpactedGroupIssueList = (ArrayList)finalProductImpactedGroupMap.get(productImpactedGroupMonth);
							
							productImpactedGroupIssueList.add(productImpactedIssue);
							
							finalProductImpactedGroupMap.put(productImpactedGroupMonth,productImpactedGroupIssueList);
							
						 } else {
							 productImpactedMonthCountList = new ArrayList();
							
							 productImpactedMonthCountList.add(productImpactedIssue);
							
							finalProductImpactedGroupMap.put(productImpactedGroupMonth,productImpactedMonthCountList);
						 }
					}
					
					/* Set product/Impacted Name*/
					productSummaryModel.setProducts(productImpactedGroup);
					
					ArrayList<IncidentProductMonthlyModel> productMonthlyList= new ArrayList<IncidentProductMonthlyModel>();
					
					/* Reading List of month based on start and end date*/
					 String headers = getListOfMonths(startDate, endDate);
					 
					 StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
					 int indexMonth = 0;
					 while(headerTokenizer.hasMoreTokens()){
						 String monthName = headerTokenizer.nextToken();
						 IncidentProductMonthlyModel monthlyModel = null;
						 int flag = 0;
							for(Map.Entry<String, List<Issue>>  finalProductImpactedGroupMonthKey: finalProductImpactedGroupMap.entrySet()){
								
								String productImpactedGroupMonthKey = finalProductImpactedGroupMonthKey.getKey();
								List<Issue>  productImpactedGroupMonthIssueList = (List<Issue>) finalProductImpactedGroupMap.get(productImpactedGroupMonthKey);
								
								if(monthName.equalsIgnoreCase(productImpactedGroupMonthKey)){
									
									long indPrdImpactIncidentDuration =0L;
									for(int i=0;i<productImpactedGroupMonthIssueList.size();i++){					
										if(productImpactedGroupMonthIssueList.get(i).getCustomFieldValue(incidentDurationCustomField)!=null){
											indPrdImpactIncidentDuration+= Long.valueOf(productImpactedGroupMonthIssueList.get(i).getCustomFieldValue(incidentDurationCustomField).toString().replaceAll(" Minutes", "").toString());
										}												
									}
									
									int indPrdImpactNumberOfDays = getNumberOfDays(monthName);

									int   indPrdImpactPrimeHours = Integer.parseInt(primeHours);
									int   indPrdImpactHours 	 = (indPrdImpactNumberOfDays * indPrdImpactPrimeHours);
									long  indPrdImpactMinutes 	 = indPrdImpactHours * 60;
									float indPrdImpactUptime 	 = getUptime(indPrdImpactIncidentDuration, indPrdImpactMinutes);	
									
									monthlyModel = new IncidentProductMonthlyModel(productImpactedGroupMonthKey, indPrdImpactIncidentDuration+"",indPrdImpactUptime+"");
									
									productMonthlyList.add(monthlyModel);
									
									flag = 1;
									break;
								}
							}
							if(flag==0){
								monthlyModel = new IncidentProductMonthlyModel(monthName, "0","100");										
								productMonthlyList.add(monthlyModel);
							}
							indexMonth++;
					 }
					 productSummaryModel.setMonths(productMonthlyList);
					 listOfModels.add(productSummaryModel);
			   }
				 
       	  }
	}
  	    
  	    
		while (stringTok.hasMoreTokens()) {
			String token = stringTok.nextToken();
			String month = formMonthJQL(token, startDate, endDate);
			String[] monthArr = month.split(",");
			StringBuffer monthJql = new StringBuffer();
			monthJql.append(jql);
			monthJql.append(" and 'Incident Start'  >= '" + monthArr[0]+ "' and 'Incident Start' <= '" + monthArr[1] + "'");

			/* Search the List of Issue based on Month */
			final SearchService.ParseResult sevtMajor = searchServ.parseQuery(authenticationContext.getLoggedInUser(), (monthJql.toString()));
			SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(),sevtMajor.getQuery(), PagerFilter.getUnlimitedFilter());
			List<Issue> issueList = results1.getIssues();
	  	    log.debug("Uptime report monthly JQL:"+ monthJql.toString());
			 long incidentDuration = 0L;
				for (Issue issue : issueList) {	
					
					Object  durationField = issue.getCustomFieldValue(incidentDurationCustomField);
					if ( durationField != null) {							
						long duration = Long.valueOf(durationField.toString().replaceAll(" Minutes", "").toString());							
						incidentDuration += duration;							
					} 					
				}
			
				int numberOfDays = getNumberOfDays(token);

				int prHours = Integer.parseInt(primeHours);
				int hours = (numberOfDays * prHours);
				long minutes = hours * 60;
				float uptime = getUptime(incidentDuration, minutes);
				summaryModel = new IncidentMonthSummaryModel(numberOfDays + "",hours + "", minutes + "", incidentDuration + "", uptime+ "");
				monthlyList.add(summaryModel);				
				
		}
	
		reportModel = new IncidentUptimeReportModel(monthlyList,"tableData", downtimeList);
		
		listOfModels.add(reportModel);
	
		return Response.ok(listOfModels).build();
	}
	
	/**
	 * Helper method that takes the solution Group and product and returns their
	 * option ids in a hashmap.
	 *
	 * @param solutionGroup
	 * @param product
	 * @return hashmap with key Solution Group and Product and their respective values
	 */
	private Map<String, String> getOptionIdAsHashMap(String solutionGroup, String product) {
		Map<String, String> optionIdMap = new HashMap<String, String>();

		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("ITIM");
		CustomField sgsProductsCF = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Solution Groups - Products");

		IssueType changeIssueType= null;
		Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
		for(IssueType issueT: issueTypesProj){
			if(!issueT.isSubTask()){ // exclude sub-task
				//issueT = changeIssueType;
				changeIssueType = issueT;
			}
		}

		FieldConfig fieldConfigSGPCat = sgsProductsCF.getRelevantConfig(new IssueContextImpl(project, changeIssueType));

		Options sgp = ComponentAccessor.getOptionsManager().getOptions(fieldConfigSGPCat);

		for (Option option : sgp) {
			if (option.getValue().equals(solutionGroup)) {
				optionIdMap.put("Solution Group", option.getOptionId().toString());
				List<Option> childOptions = option.getChildOptions();
				for (Option childOption : childOptions) {
					if (childOption.getValue().equals(product)) {
						optionIdMap.put("Product", childOption.getOptionId().toString());
						break;
					}
				}
				break;
			}
		}

		//log.debug("Solution Group option ID " + optionIdMap.get("Solution Group"));
		//log.debug("Product Option ID " + optionIdMap.get("Product"));

		return optionIdMap;
	}

	
	
	@GET
	@Path("/exportdowntimesummaryxsl")
	@Produces("application/vnd.ms-excel")
	public Response getDownTimeSummaryXSL(
			@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("primeHours") String primeHours,
			@QueryParam("solutionGroup") String solutionGroup,
			@QueryParam("product") String product,
			@QueryParam("type") String type,
			@QueryParam("impacted") String impacted,
			@QueryParam("location") String location) {
		
		
		//String ISSUE_TYPES = "Incident";
		
		CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
		/* Custom Fields for incident duration calculation */
		CustomField incidentDurationCustomField = cfm.getCustomFieldObjectByName("Incident Duration");
//    	
//		/* Custom Fields for second level (product/impacted) minutes and uptime calculation */
//    	CustomField sgProductCF = fieldManager.getCustomFieldObjectByName("Solution Group - Product");
//    	CustomField locImpactedCF = fieldManager.getCustomFieldObjectByName("Impacted - Function");
    	
    	/* To get the list of product/impacted (i.e. second level) minutes and uptime calculation */
    	
		ArrayList<IncidentDowntimeSummaryModel> downtimeList = new ArrayList<IncidentDowntimeSummaryModel>();
		
		String headers = getListOfMonths(startDate, endDate);
		
		StringTokenizer headerTokenizer = new StringTokenizer(headers, ",");
		
		while (headerTokenizer.hasMoreTokens()) {
			String token = headerTokenizer.nextToken();
			//ArrayList<Issue> newIssueArray  = null;
			//Map<String,List<Issue>> productImpactedGroupMap = new HashMap<String,List<Issue>>();
			for (Issue issue : issues) {			
		       	
						/*if(issue.getIssueTypeObject().getName().equals("Incident")){
							
								 IncidentUptimeReportModel productSummaryModel = new IncidentUptimeReportModel();
								  if(type.equals("External") && product.equals("All")){	
									  productSummaryModel.setTableHeader("ProductGroup");
									if(issue.getCustomFieldValue(sgProductCF)!=null){	
										if (sgProductCF.getCustomFieldType() instanceof CascadingSelectCFType) {
											HashMap<String, Option> productGroupMap = (HashMap<String, Option>) issue.getCustomFieldValue(sgProductCF);										
											if (productGroupMap != null && productGroupMap.size()>1) {
													Option childSoln =  productGroupMap.get(CascadingSelectCFType.CHILD_KEY);											
													String productCFValue = String.valueOf(childSoln.getValue());
													if(productImpactedGroupMap.containsKey(productCFValue)){
													  ArrayList<Issue> existIssueArray = (ArrayList<Issue>)productImpactedGroupMap.get(productCFValue);
													  existIssueArray.add(issue);
													  productImpactedGroupMap.put(productCFValue,existIssueArray);								
													 } else {
														 newIssueArray = new ArrayList<Issue>(); 
														 newIssueArray.add(issue);
														 productImpactedGroupMap.put(productCFValue,newIssueArray);	
													 }
											    
											}
										}
									} 
					      	     }else if(type.equals("Internal") && impacted.equals("All")){
					      	    	productSummaryModel.setTableHeader("Impacted");
					      	    	if(issue.getCustomFieldValue(locImpactedCF)!=null){	
										if (locImpactedCF.getCustomFieldType() instanceof CascadingSelectCFType) {
											HashMap<String, Option> impactedGroupMap = (HashMap<String, Option>) issue.getCustomFieldValue(locImpactedCF);
											if (impactedGroupMap != null && impactedGroupMap.size()>0) {							
												Option parentSoln =  impactedGroupMap.get(CascadingSelectCFType.PARENT_KEY);
												String productCFValue = String.valueOf(parentSoln.getValue());
												if(productImpactedGroupMap.containsKey(productCFValue)){
												  ArrayList<Issue> existIssueArray = (ArrayList<Issue>)productImpactedGroupMap.get(productCFValue);
												  existIssueArray.add(issue);
												  productImpactedGroupMap.put(productCFValue,existIssueArray);								
												 } else {
													 newIssueArray = new ArrayList<Issue>(); 
													 newIssueArray.add(issue);
													 productImpactedGroupMap.put(productCFValue,newIssueArray);	
												 }
											  
											}
										 }
									} 
					      	     }else{*/
					      	    	String _createdDate = issue.getCreated().toString();
									String month = getMonthYear(_createdDate);
									if (token.equals(month)) {
										
										if (issue.getCustomFieldValue(incidentDurationCustomField) != null) {
											
											long duration = Long.valueOf(issue.getCustomFieldValue(incidentDurationCustomField).toString().replaceAll(" Minutes", "").toString());
											if (duration != 0) {

												Timestamp issueTimeStamp = issue.getCreated();

												String createdTime = convertDateFormat(issueTimeStamp.toString());
												String reason = issue.getSummary();
												IncidentDowntimeSummaryModel downtimeSummary = new IncidentDowntimeSummaryModel(createdTime, duration + "", reason);
												downtimeList.add(downtimeSummary);
											}
										}
									}
					      	     }
							}	
			     //}
			
		/*if((type.equals("External") && product.equals("All")) || (type.equals("Internal") && impacted.equals("All"))){	
			 for(Map.Entry<String, List<Issue>>  productImpactedGroupMonthMap: productImpactedGroupMap.entrySet()) {
		 	
		 	 IncidentUptimeReportModel productSummaryModel = new IncidentUptimeReportModel();
  		     
			 String productImpactedGroup = productImpactedGroupMonthMap.getKey();
			 			 			 
			 List productImpactedMonthCountList = null;
			 
			 Map<String,List<Issue>> finalProductImpactedGroupMap = new HashMap<String,List<Issue>>(); 
			 
				List<Issue>  productImpactedLists = (List<Issue>) productImpactedGroupMap.get(productImpactedGroup);
				//System.out.println("productImpactedLists: "+productImpactedLists.size());
				for(Issue productImpactedIssue: productImpactedLists){
					
					CustomField  incidentStartCF = fieldManager.getCustomFieldObjectByName(ISSUE_TYPES+" Start");
					String createdDate = (String)productImpactedIssue.getCustomFieldValue(incidentStartCF).toString();
					// Gets product/Impacted Month
					 String productImpactedGroupMonth = getMonthYear(createdDate);
					 
					 // Checks product/Impacted Month contains in the Map
					 if(finalProductImpactedGroupMap.containsKey(productImpactedGroupMonth)){
						 
						List productImpactedGroupIssueList = (ArrayList)finalProductImpactedGroupMap.get(productImpactedGroupMonth);
						
						productImpactedGroupIssueList.add(productImpactedIssue);
						
						finalProductImpactedGroupMap.put(productImpactedGroupMonth,productImpactedGroupIssueList);
						
					 } else {
						 productImpactedMonthCountList = new ArrayList();
						
						 productImpactedMonthCountList.add(productImpactedIssue);
						
						finalProductImpactedGroupMap.put(productImpactedGroupMonth,productImpactedMonthCountList);
					 }
				}*/
				
				/*
				for(Map.Entry<String, List<Issue>>  finalProductImpactedGroupMonthKey: finalProductImpactedGroupMap.entrySet()){
					
					String productImpactedGroupMonthKey = finalProductImpactedGroupMonthKey.getKey();
					List<Issue>  productImpactedGroupMonthIssueList = (List<Issue>) finalProductImpactedGroupMap.get(productImpactedGroupMonthKey);
					
					if(token.equalsIgnoreCase(productImpactedGroupMonthKey)){
						
						for(int i=0;i<productImpactedGroupMonthIssueList.size();i++){					
							if(productImpactedGroupMonthIssueList.get(i).getCustomFieldValue(incidentDurationCustomField)!=null){
								
								long indPrdImpactIncidentDuration=Long.valueOf(productImpactedGroupMonthIssueList.get(i).getCustomFieldValue(incidentDurationCustomField).toString().replaceAll(" Minutes", "").toString());
								if (indPrdImpactIncidentDuration != 0) {

									Timestamp issueTimeStamp = productImpactedGroupMonthIssueList.get(i).getCreated();

									String createdTime = convertDateFormat(issueTimeStamp.toString());
									String reason = productImpactedGroupMonthIssueList.get(i).getSummary();
									System.out.println("Month: "+ token  +" 2nd level value: "+ productImpactedGroup+" IncidentDuration: "+indPrdImpactIncidentDuration+" Created Time: "+createdTime+" Summary: "+reason);
									IncidentDowntimeSummaryModel downtimeSummary = new IncidentDowntimeSummaryModel(productImpactedGroup,createdTime, indPrdImpactIncidentDuration + "", reason);
									downtimeList.add(downtimeSummary);
								}
							}												
						}						
					}
				}		
		     }  	  
		  }
		}*/
		
		System.out.println("getDownTimeSummaryListModel   "+ downtimeList.size());
		File f = new File(CSV_PATH);
		File fout = new File(Output_PATH);
		setCreateOptionValue(downtimeList, f, primeHours,solutionGroup, product,type,impacted,location);
		ResponseBuilder response = Response.ok((Object) fout);
		response.header("Content-Disposition","attachment; filename=\"" + fout.getName() + "\"");
		response.header("Content-Type", "application/vnd.ms-excel");
		return response.build();
	}

	public static Date convertDateFormatForExport(String sprintdate) {
		String endate = sprintdate;
		Date date = null;
		String format = "yyyy/MM/dd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dat = null;
		try {
			date = formatter.parse(endate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat exportdate = new SimpleDateFormat("dd-MMM-yyyy");
		dat = exportdate.format(date.getTime());
		Date dateValue = new Date();
		try {
			dateValue = exportdate.parse(dat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateValue;
	}

	private void setCreateOptionValue(ArrayList<IncidentDowntimeSummaryModel> downtimeList, File f,String primeHours, String solutionGroup, String product, String type, String impacted, String location) {
		try {
			FileInputStream fsIP = new FileInputStream(f); // Read the spreadsheet that needs to be updated
			HSSFWorkbook wb = new HSSFWorkbook(fsIP); // Access the workbook
			HSSFSheet worksheet = wb.getSheetAt(0); // Access the worksheet, so that we can update / modify it.
			Cell cell = null; // declare a Cell object
			cell = worksheet.getRow(2).getCell(2); // Access the second cell in second row to update the value
			if(type.equals("External")){
				String solutionValue = solutionGroup + "-" + product;	
				//System.out.println("Xls Sheet solutionGroup-product: "+solutionValue);
				cell.setCellValue(solutionValue); // Get current cell value value and overwrite the value
			}else{				
				String impactedValue = location + "-" + impacted;	
				//System.out.println("Xls Sheet Location-Impacted: "+impactedValue);
				cell.setCellValue(impactedValue);				
				cell = worksheet.getRow(2).getCell(1);
				cell.setCellValue("Location - Impacted");
			}

			cell = worksheet.getRow(3).getCell(2); // Access the second cell in second row to update the value
			//System.out.println("Xls Sheet Prime Hours" + primeHours);									
			cell.setCellValue(primeHours); // Get current cell value value and overwrite the value

			int count = 0;
			// INSERT THE CODE TO FILL THE TABLE
			// loop should always start with 15
			if (downtimeList != null && downtimeList.size() > 0) {
				for (int i = 0; i < downtimeList.size(); i++) {
					count = 15 + i;
					HSSFRow row = worksheet.createRow(count); // Create the row in the spreadsheet
					// 1. Create the date cell style
					HSSFCreationHelper createHelper = wb.getCreationHelper();
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MMM-yyyy"));
					IncidentDowntimeSummaryModel downtimeSummaryModel = (IncidentDowntimeSummaryModel) downtimeList.get(i);
					String products = downtimeSummaryModel.getProducts();	
					//System.out.println("Xls Sheet products" + products);
					String downtimeDate = downtimeSummaryModel.getDateOfOcuurence();
					Long duration = Long.parseLong(downtimeSummaryModel.getDowntime());
					
					//System.out.println("Xls Sheet downtimeDate" + downtimeDate);
					//System.out.println("Xls Sheet duration" + duration);
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					String dateInString = "7-Jun-2013"; // convertDateFormatForExport(downtimeDate); "7-Jun-2013"; Replace this value from the actual Jira Incident Start date using the loop
					
					cell = row.createCell(1);
					Date dateValue = convertDateFormatForExport(downtimeDate);
					
					cell.setCellValue(dateValue);
					cell.setCellStyle(cellStyle);

					//System.out.println("Xls Sheet downtime" + downtimeSummaryModel.getDowntime());
					
					// Incident Duration
					int d = Integer.parseInt(downtimeSummaryModel.getDowntime());
					cell = row.createCell(2);
					cell.setCellValue(d);
					
					//System.out.println("Xls Sheet Summary" + downtimeSummaryModel.getReason());
					// Reason nothing but summary
					cell = row.createCell(3);
					cell.setCellValue(downtimeSummaryModel.getReason());
//				  if((type.equals("External") && product.equals("All")) || (type.equals("Internal") && impacted.equals("All"))){	
//					// Reason nothing but Products/Impacted
//					cell = row.createCell(4);
//					cell.setCellValue(downtimeSummaryModel.getProducts());
//				  }
			   }
			} else {
				HSSFRow row = worksheet.createRow(16);
				cell = row.createCell(1);
				cell.setCellValue("no data");
				cell = row.createCell(2);
				cell.setCellValue("no data");
				cell = row.createCell(3);
				cell.setCellValue("no data");
			}
			// Refresh the excel workbook for the formulas to generate the values after cell values are updated.
			// HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);

			fsIP.close(); // Close the InputStream
			FileOutputStream output_file = new FileOutputStream(new File(Output_PATH)); // Open FileOutputStream to write updates
			wb.write(output_file); // write changes
			output_file.close(); // close the stream

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String formMonthJQL(String month, String startDate, String endDate){
		String startDt = "";
		String endDt = "";
		try{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(startDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		String startMonthDate = new SimpleDateFormat("dd").format(calendar.getTime());
		String startMonthName = new SimpleDateFormat("MMM").format(calendar.getTime());
		String startMonthYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
		
		Date endDateFormat = formatter.parse(endDate);
		calendar = Calendar.getInstance();
		calendar.setTime(endDateFormat);
		String endMonthDate = new SimpleDateFormat("dd").format(calendar.getTime());
		String endMonthName = new SimpleDateFormat("MMM").format(calendar.getTime());
		String endMonthYear = new SimpleDateFormat("yyyy").format(calendar.getTime());
		
		String[] monthSplit = month.split("-");
		
		if(monthSplit[0].equals(startMonthName) && monthSplit[1].equals(startMonthYear)){
			startDt = startDate;							
		}
		if(monthSplit[0].equals(endMonthName) && monthSplit[1].equals(endMonthYear)){
			endDt = endDate;
		}
		
	 if(startDt.equals("") || endDt.equals("")){
			Calendar cal = Calendar.getInstance();
			cal.setTime(new SimpleDateFormat("MMM").parse(monthSplit[0]));
			int minDate = cal.getActualMinimum(Calendar.DATE);
			int maxDate = cal.getActualMaximum(Calendar.DATE);
			int monthNumb = (cal.get(Calendar.MONTH) + 1);
			cal.set(Calendar.MONTH, monthNumb);
			if(startDt.equals("")){	
				 startDt = monthSplit[1] +"-"+monthNumb+"-"+minDate;
			}
			if(endDt.equals("")){
				 endDt = monthSplit[1] +"-"+monthNumb+"-"+maxDate;
			}
		}
		//System.out.println(startDt +" - "+endDt);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return startDt +","+endDt;
	}
	public static String convertDateFormat(String sprintdate) {
		String endate = sprintdate;
		endate = endate.replace('T', ' ');
		Date date = null;
		String format = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dat = null;
		try {
			
			date = formatter.parse(endate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		dat = calendar.get(Calendar.YEAR) + "/"+ (calendar.get(Calendar.MONTH) + 1) + "/"+ calendar.get(Calendar.DATE);
		// System.out.println(date2);
		return dat;
	}

	private float getUptime(long incidentDuration, long minutes) {
		float diffMinutes = (minutes - incidentDuration);
		float uptime = (diffMinutes) / (minutes);

		return (uptime * 100);
	}

	private int getNumberOfDays(String monthYear) {
		String[] splitString = monthYear.split("-");
		Date date = null;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(splitString[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month);

		calendar.set(Calendar.YEAR, Integer.parseInt(splitString[1]));
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		return numDays;
	}

	private String getListOfMonths(String startdate, String enddate) {
		StringBuffer months = new StringBuffer();
		String startDate = startdate;
		String endDate = enddate;
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedStartDate = null;
		Date convertedEndDate = null;
		try {
			convertedStartDate = startdateFormat.parse(startDate);
			convertedEndDate = startdateFormat.parse(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		while (convertedStartDate.compareTo(convertedEndDate) <= 0) {
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
		String bufferStr = "";
		if (months != null && months.length() > 0) {
			bufferStr = months.toString().substring(0, months.length() - 1);
		}

		return bufferStr;
	}

	private String getNextMonth(String dateString) {
		SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate = null;
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
		String returnDate = null;
		returnDate = startdateFormat.format(d);
		return returnDate;

	}

	private Date addDays(Date d, int days) {
		Date date = d;
		date.setTime(d.getTime() + (days * 1000 * 60 * 60 * 24));
		return date;
	}

	private String getMonthYear(String date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String month = new SimpleDateFormat("MMM").format(d.getTime());
		String year = new SimpleDateFormat("yyyy").format(d.getTime());
		return month + "-" + year;
	}

	
	

}
