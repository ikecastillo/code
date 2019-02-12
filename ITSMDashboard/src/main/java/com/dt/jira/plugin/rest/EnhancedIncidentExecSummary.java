package com.dt.jira.plugin.rest;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.priority.Priority;
import com.atlassian.jira.issue.resolution.Resolution;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.jql.builder.JqlClauseBuilder;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.query.Query;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dt.jira.plugin.utils.DateOperations.getListOfMonths;
import static com.dt.jira.plugin.utils.DateOperations.getMonthYear;
import static com.dt.jira.plugin.utils.DrillDown.setDrillDown;
import static com.dt.jira.plugin.utils.DrillDown.setMonthlyDrillDown;
import static com.dt.jira.plugin.utils.NumberFormatter.getNumberFormat;
import static com.dt.jira.plugin.utils.NumberFormatter.parseNumberFormat;


/**
 * REST service that provides the data for incident count, incident duration, problem count and duration gadgets.
 *
 */
@Path("/enhancedincidentexecsummary")
public class EnhancedIncidentExecSummary {
    /* Logger */
    private final Logger logger = Logger.getLogger(EnhancedIncidentExecSummary.class);

    //public static String ISSUE_TYPES = "";
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    /**
     * REST service that provides the data for incident count, incident duration, problem count and duration gadgets.
     *
     * @param searchFilter - Solution Group field
     * @return
     * @throws Exception
     */
    public Response getMessage(@QueryParam("searchFilter") String searchFilter,
                               @QueryParam("groupBy") String groupBy,
                               @QueryParam("legenddepth") String depth,
                               @QueryParam("sigmavalues") String sigmavalues,
                               @QueryParam("xaxis") String xAxisOnBarChart,
                               @QueryParam("yaxis") String yAxisOnBarChart) throws Exception{

        /* construct the jql query for drill down*/
        StringBuffer drilldownjql = new StringBuffer();
        int depthOfCascade = 0;
        String projectKey = "";
        String issueType = "";


        if (!depth.equals("Does Not Apply")) {
            depthOfCascade = Integer.parseInt(depth);
        }

        //ISSUE_TYPES = "Incident";
        String URL = "/issues/?jql=";

        SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
        CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
        JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
        Query jql = getQuery(searchFilter);
        String jqlString = jql.toString();
        //Remove the first and last { , } from the jql Query string
        jqlString = jqlString.substring(1, jqlString.length() - 1);
        /* end the jql query construction with all the input field values */
        jqlString = jqlString.replace("in (\"filter-","=\"").replace("\")","\"");
        logger.debug("ITSM Dashboard  JQL Statement: " + jqlString);

        CustomField  legendCF = fieldManager.getCustomFieldObjectByName(groupBy);

        CustomField  xAxisOnBarChartCF = fieldManager.getCustomFieldObjectByName(xAxisOnBarChart);
        String xAxisFieldType = null;

        //Checking the field type for x Axis
        if (xAxisOnBarChartCF != null) {
            xAxisFieldType = xAxisOnBarChartCF.getCustomFieldType().getName();
        } else {
           //If its not a custom field, then it must be a system field.
           xAxisFieldType = "System Field";
        }

        logger.debug("FIELD TYPE OF XAXIS IS " + xAxisFieldType);

        String drillDownForMonth = URL + jqlString;// Drill down jql query for each month
        logger.debug("Drill Down URL is " + drillDownForMonth);
	
	    /* search the issues based on jql query */
        final SearchService.ParseResult sevtMajor = searchServ.parseQuery(authenticationContext.getLoggedInUser(),
                jqlString);

	    /* Build the list for each solution group */
        ArrayList<GenericSummaryModel> listOfModels= new ArrayList<>();
        List<String> xCoordinatesList = null; //to store a sorted list of dates in order to pick up start date and end date
        //String status = null;

        String xAxisCoordinateValues = "";
        logger.debug("CHECKING IF PARSE RESULTS ARE VALID ... ");
        if (sevtMajor.isValid()) {
            logger.debug("SEVTMAJOR IS VALID");
            SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(), sevtMajor.getQuery(),
                    PagerFilter.getUnlimitedFilter());

            List<Issue> issues = results1.getIssues();
            //Get the Proejct Key and issue type
            if(issues!=null && issues.size()>0) {
			    
				  projectKey = issues.get(0).getProjectObject().getKey();
				  issueType = issues.get(0).getIssueType().getName();
			
                }
            //status = getStatusAsPipeSeparatedString(issues);

            if (xAxisFieldType.contains("Date Time Picker") ||
                xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED)) {
                xCoordinatesList = populateStartDatesList(issues, xAxisOnBarChart);
				if(xCoordinatesList!=null && xCoordinatesList.size()>0){
				
					xAxisCoordinateValues = getListOfMonths(xCoordinatesList.get(0),
                                                        xCoordinatesList.get(xCoordinatesList.size() - 1));
				}
            } else {
                xAxisCoordinateValues = getListofValuesForCFAsCSV(xAxisOnBarChartCF, issues);
                String[] tempArray = xAxisCoordinateValues.split(",");
                xCoordinatesList = Arrays.asList(tempArray);
            }

            logger.debug("LIST OF XAXIS COORDINATE VALUES " + xAxisCoordinateValues);
            logger.debug("TOTAL ISSUES SIZE : " + issues.size());

            /* Start build the list for the header */
            GenericSummaryModel header = new GenericSummaryModel();
            //header.setLegend("Major " + ISSUE_TYPES + "s");
            header.setLegend("Major Issues");
            StringTokenizer  stringTokenizer = new StringTokenizer(xAxisCoordinateValues,",");
            ArrayList<GenericModel> headerModels= new ArrayList<>();
            while(stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                headerModels.add(new GenericModel(token, "0"));
            }
            header.setGraphCoordinates(headerModels);
            listOfModels.add(header);

            if(issues!= null && issues.size() > 0)  {
                /* Create the Map for if type is external or internal. If it is external, then solution group as key
                and list of issues as value other wise location as key and list of issues as value */
                Map<String,List<Issue>> legendHashMap = new HashMap<>();

                // Iterated all the issues create the Map for each solution group.
                updateHashMapBasedOnLegend(groupBy, legendCF, issues, legendHashMap, depthOfCascade);

                // Iterated the above created sgMap and re build the execute summary model  for each solution group
                // and get added the map by calculating number issues for each month(Key is Month and
                // list of issues as value).
                for(Map.Entry<String, List<Issue>>  mothmap: legendHashMap.entrySet()) {
                    GenericSummaryModel execSummaryModel = new GenericSummaryModel();
                    String solutionGrOptId = "";
                    String key = mothmap.getKey();
                    Map<String,List<Issue>> finalJson = new HashMap<String,List<Issue>>();
                    List<Issue>  sgLists = legendHashMap.get(key);
                    String type ="";
                    long typeId = 0;

                    for(Issue issue: sgLists){
                        logger.debug("Getting xaxis custom field value for the issue " + issue.getKey());

                        String xAxisCustomFieldValuePerIssue = null;
                        if (xAxisOnBarChartCF != null) {
                            xAxisCustomFieldValuePerIssue = issue.getCustomFieldValue(xAxisOnBarChartCF)!= null ?
                                    issue.getCustomFieldValue(xAxisOnBarChartCF).toString() : null ;
                        } else if (xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED)) {
                            xAxisCustomFieldValuePerIssue = issue.getCreated().toString();
                        }


                        if ((xAxisFieldType.contains("Date Time Picker") && xAxisCustomFieldValuePerIssue != null) ||
                                xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED) ) {
                            xAxisCustomFieldValuePerIssue = getMonthYear(xAxisCustomFieldValuePerIssue);
                            populateFinalJSONForXAxis(finalJson, issue, xAxisCustomFieldValuePerIssue);
                        } else if (xAxisCustomFieldValuePerIssue != null){
                           /* TODO Have to figure out other xaxis field types if required, once all the excel sheet
                              conditions are taken care of.
                            */

                            populateFinalJSONForXAxis(finalJson, issue, xAxisCustomFieldValuePerIssue);

                        }


                        if (legendCF != null ) {
                            if (legendCF.getCustomFieldType().getName()
                                        .equalsIgnoreCase("Multi-Level Cascading Select")) {
                                List<Option> customFieldValueList = (ArrayList<Option>)issue.getCustomFieldValue(legendCF);
                                StringBuilder solutionGroupIDs = new StringBuilder();

                                int count = 0; //To ensure option IDs are got based on depth of cascade
                                if (customFieldValueList != null) {
                                    for (Option customFieldOption : customFieldValueList) {
                                        if (count <=depthOfCascade) {
                                            solutionGroupIDs.append("\"")
                                                    .append(customFieldOption.getOptionId().toString())
                                                    .append("\",");
                                            count++;
                                        }

                                    }
                                    //Delete the last extra comma
                                    solutionGroupIDs.deleteCharAt(solutionGroupIDs.length() - 1);
                                }

                                solutionGrOptId = solutionGroupIDs.toString();
                                logger.debug("SOLUTION GROUP OPTION IDS ARE " + solutionGrOptId);

                            } else if (legendCF.getCustomFieldType().getName()
                                                .equalsIgnoreCase("Select List (cascading)")) {
                                Map<LazyLoadedOption, LazyLoadedOption> selectListCascMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
                                        issue.getCustomFieldValue(legendCF);

                                if (selectListCascMap != null) {
                                    for (Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : selectListCascMap.entrySet()) {
                                        LazyLoadedOption llo;
                                        if (opt.getKey() == null && depthOfCascade == 0) {
                                            llo = opt.getValue();
                                            solutionGrOptId = "\""+String.valueOf(llo.getValue())+"\"";
                                        }
                                        if (opt.getKey() != null && depthOfCascade == 1) {
                                            llo = opt.getValue();
                                            solutionGrOptId = "\""+String.valueOf(llo.getValue())+"\"";
                                        }
                                    }
                                }
                            }
                        }


                    }//for

                    //System.out.println("SG: "+ key);
                    execSummaryModel.setLegend(key);
					/* Sets Drill Down for issue search */
                    logger.debug("SG :" + key);
                    String drillDownUrl = setDrillDown(URL + drilldownjql.toString(), solutionGrOptId, key, type, typeId,
                            groupBy);
                    logger.debug("DRILL DOWN URL IS " + drillDownUrl);
                    execSummaryModel.setDrillDown(drillDownUrl);

                    ArrayList<GenericModel> monthlyList= new ArrayList<GenericModel>();

                    String headers = null;
                    if (xAxisFieldType.contains("Date Time Picker") && (xCoordinatesList!=null && xCoordinatesList.size()>0) ) {
                        headers = getListOfMonths(xCoordinatesList.get(0),
                                xCoordinatesList.get(xCoordinatesList.size() - 1));
                    } else {
                        headers = xAxisCoordinateValues;
                    }
                    //String headers = getListOfMonths(xCoordinatesList.get(0), xCoordinatesList.get(xCoordinatesList.size() - 1));
                    StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
                    int totalMonths = headerTokenizer.countTokens();
                    int indexMonth = 0;
                    while(headerTokenizer.hasMoreTokens()){
                        String monthName = headerTokenizer.nextToken();
                        GenericModel monthlyModel;
                        int flag = 0;
                        for(Map.Entry<String, List<Issue>>  finaljsonVar: finalJson.entrySet()){
                            String _key = finaljsonVar.getKey();
                            List<Issue>  xAxisValueLists = finalJson.get(_key);
                            if(monthName.equalsIgnoreCase(_key)){
                                /*if(reporttype!=null && (reporttype.equalsIgnoreCase("IncidentDuration") ||
                                        reporttype.equalsIgnoreCase("ProblemDuration")))*/
                                if (sigmavalues.equals("Sum") && yAxisOnBarChart.contains("Duration")){
                                    long incidentDuration = calculateDuration(xAxisValueLists, yAxisOnBarChart);
                                    monthlyModel = new GenericModel(_key,getNumberFormat(incidentDuration));
                                } else if (sigmavalues.equals("Count") && xAxisFieldType.contains("Date Time Picker")){
                                    monthlyModel = new GenericModel(_key, getNumberFormat(xAxisValueLists.size()));
                                } else {
                                    monthlyModel = new GenericModel(_key, getNumberFormat(xAxisValueLists.size()));
                                }

                                /* Sets Monthly Drill Down for issue search */
                                String monthlyUrl = setMonthlyDrillDown(solutionGrOptId, key,
                                        _key, drillDownForMonth, indexMonth, totalMonths, xCoordinatesList.get(0),
                                        xCoordinatesList.get(xCoordinatesList.size() - 1),
                                        groupBy, xAxisOnBarChart, depthOfCascade,projectKey,issueType);
                                monthlyModel.setDrillDown(monthlyUrl);
                                monthlyList.add(monthlyModel);
                                flag = 1;
                                break;
                            }
                        }
                        if(flag==0){
                            monthlyModel = new GenericModel(monthName, "0");
                            monthlyList.add(monthlyModel);
                        }
                        indexMonth++;
                    }
                    execSummaryModel.setGraphCoordinates(monthlyList); // added monthly model list to execute summary model
                    listOfModels.add(execSummaryModel);// added execute summary model to list.
                } //

            }
        } else {
            listOfModels.add(new GenericSummaryModel());
        }
        Collections.sort(listOfModels);
        logger.debug("List of Models Size " + listOfModels.size());
        //Collections.sort(listOfModels);
        if (xAxisFieldType.contains("Date Time Picker") ||
                xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED)) {
            logger.debug("START DATES LIST IS " + xCoordinatesList);
			if(xCoordinatesList!=null && xCoordinatesList.size()>0){
			calculateTotAndPercentage(listOfModels, xCoordinatesList.get(0),
                                                    xCoordinatesList.get(xCoordinatesList.size() - 1));
			}
            
        } else {
            calculateTotAndPercentageForNonDateXAxis(listOfModels,xAxisCoordinateValues);
        }

        return Response.ok(listOfModels).build();
    }

    /**
     * Helper method that populates the final JSON to be plotted from the xaxis point of view
     *
     * @param finalJson
     * @param issue
     * @param xAxisCustomFieldValuePerIssue
     */
    private void populateFinalJSONForXAxis(Map<String, List<Issue>> finalJson, Issue issue, String xAxisCustomFieldValuePerIssue) {
        List<Issue> xAxisFieldCountList;
        if(finalJson.containsKey(xAxisCustomFieldValuePerIssue)){
            List<Issue> sg_issue = (ArrayList)finalJson.get(xAxisCustomFieldValuePerIssue);
            sg_issue.add(issue);
            finalJson.put(xAxisCustomFieldValuePerIssue,sg_issue);
        } else {
            xAxisFieldCountList = new ArrayList<Issue>();
            xAxisFieldCountList.add(issue);
            finalJson.put(xAxisCustomFieldValuePerIssue,xAxisFieldCountList);
        }
    }

    /*****************************************************************************************************************/
    private void calculateTotAndPercentageForNonDateXAxis(ArrayList<GenericSummaryModel> listOfModels,
                                                          String xCoordinateValues) {
        String headers = xCoordinateValues;
        StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
        GenericSummaryModel execSummaryModel  = new GenericSummaryModel();
        List<GenericModel> fieldWiseTotalModel = new ArrayList<GenericModel>();
        while(headerTokenizer.hasMoreTokens()){
            String token = headerTokenizer.nextToken();
            long total = 0;
            if(listOfModels!=null && listOfModels.size()>0){
                for(int i=0;i<listOfModels.size(); i++){
                    GenericSummaryModel execModel =  listOfModels.get(i);
                    List<GenericModel> execMonthlyModels = execModel.getGraphCoordinates();
                    if(execMonthlyModels !=null ){
                        for(GenericModel execMonthlyModel : execMonthlyModels){
                            String oneXAxisField = execMonthlyModel.getXAxis();
                            if(oneXAxisField.equals(token)){
                                long incidentcount = parseNumberFormat(execMonthlyModel.getCountOrSum());
                                total =  total + incidentcount;
                                break;
                            }
                        }
                    }
                }
                fieldWiseTotalModel.add(new GenericModel(token, getNumberFormat(total)));
            }
        }
        execSummaryModel.setLegend("Total");
        execSummaryModel.setGraphCoordinates(fieldWiseTotalModel);
        listOfModels.add(execSummaryModel);
    }

    /**
     * Calculate the total and percentage for all the objects in the list which exist between start and end date.
     * @param list - ArrayList<GenericSummaryModel>
     */
    public void calculateTotAndPercentage(ArrayList<GenericSummaryModel> list,String startDate,String endDate){

        String headers = getListOfMonths(startDate, endDate);
        //System.out.println("headers: "+ headers);
        StringTokenizer headerTokenizer = new StringTokenizer(headers,",");
        GenericSummaryModel execSummaryModel  = new GenericSummaryModel();
        List<GenericModel> monthlyTotalModel = new ArrayList<GenericModel>();
        while(headerTokenizer.hasMoreTokens()){
            String token = headerTokenizer.nextToken();
            long total = 0;
            if(list!=null && list.size()>0){
                for(int i=0;i<list.size(); i++){
                    GenericSummaryModel execModel =  list.get(i);
                    List<GenericModel> execMonthlyModels = execModel.getGraphCoordinates();
                    if(execMonthlyModels !=null ){
                        for(GenericModel execMonthlyModel : execMonthlyModels){
                            String month = execMonthlyModel.getXAxis();
                            if(month.equals(token)){
                                long incidentcount = parseNumberFormat(execMonthlyModel.getCountOrSum());
                                total =  total + incidentcount;
                                break;
                            }
                        }
                    }
                }
                monthlyTotalModel.add(new GenericModel(token, getNumberFormat(total)));
            }
        }
        execSummaryModel.setLegend("Total");
        execSummaryModel.setGraphCoordinates(monthlyTotalModel);
        list.add(execSummaryModel);
    }

    /*****************************************************************************************************************/

    private String getListofValuesForCFAsCSV(CustomField xAxisOnBarChartCF, List<Issue> issues) {
        StringBuilder csvBuilderForCF = new StringBuilder();
        List<String> customFieldValList = new ArrayList<String>();
        for (Issue issue: issues) {
            if (issue.getCustomFieldValue(xAxisOnBarChartCF) != null) {
                String customFieldValue = issue.getCustomFieldValue(xAxisOnBarChartCF).toString();
                if (!customFieldValList.contains(customFieldValue)){
                    customFieldValList.add(customFieldValue);
                }
            }
        }

        for (String customFieldValue : customFieldValList) {
            csvBuilderForCF.append(customFieldValue).append(",");
        }

        String csvValues = csvBuilderForCF.deleteCharAt(csvBuilderForCF.length() - 1).toString();

        logger.debug("XAXIS VALUES TO BE PLOTTED " + csvValues);

        return csvValues;
    }


    @GET
    @AnonymousAllowed
    @Path("/getCustomFields")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomFields(@QueryParam("reporttype") String reporttype) throws Exception{

        
        List<CustomFieldsModel> customFieldList = new ArrayList<CustomFieldsModel>();

        List<Field> customFields = getAllDefaultFields();
        
        for(Field field:customFields){
        	System.out.println("Field Name" +field.getName());
        	customFieldList.add(new CustomFieldsModel(field.getName(),field.getName()));
        }
       
       return Response.ok(customFieldList).build();
       
    }

    public List<Field> getAllDefaultFields() {
        Set<Field> allFieldsSet = new TreeSet<>();
        allFieldsSet.addAll(ComponentAccessor.getFieldManager().getOrderableFields());
        try {
            allFieldsSet.addAll(ComponentAccessor.getFieldManager().getAllAvailableNavigableFields());
        } catch (FieldException e) {
        	logger.error("Unable to load navigable fields", e);
        }
        return new ArrayList<>(allFieldsSet);
    }

    /**
     * Helper method to create the Map based on the Legend. If the legend is a MS Cascade, then solution group as key
     * and list of issues as value other wise field name as key and list of issues as value
     *  @param legendCF CustomField Object of the Legend
     * @param issues List of issues
     * @param legendMap  The empty map to update
     * @param depthOfCascade depth of cascade field (only for multiselect)
     */
    private void updateHashMapBasedOnLegend(String legend, CustomField legendCF, List<Issue> issues, Map<String,
            List<Issue>> legendMap, int depthOfCascade) {
        ArrayList<Issue> newArry;
        String legendFieldType;
        if (legendCF!= null) {
            legendFieldType = legendCF.getCustomFieldType().getName();
        } else {
            //If its not a custom field, then its a system field
            legendFieldType = "System Field";
        }

        logger.debug("FIELD TYPE OF LEGEND IS " + legendFieldType);
        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        for(Issue issue: issues){
            if (legendFieldType.equalsIgnoreCase("Multi-Level Cascading Select")) {
                List solutionGroupList = (ArrayList)issue.getCustomFieldValue(legendCF);
                if (solutionGroupList != null && (solutionGroupList.size() > 0) ) {
                    //Option selectedSolGrp = (Option)solutionGroupList.get(depthOfCascade);
                    if (solutionGroupList.size() > depthOfCascade) {
                        Object solutionGrStringValueObj = solutionGroupList.get(depthOfCascade);
                        String solutionGrStringValue = solutionGrStringValueObj.toString() ;

                        if(legendMap.containsKey(solutionGrStringValue)){
                            List<Issue> existArry = legendMap.get(solutionGrStringValue);
                            existArry.add(issue);
                            legendMap.put(solutionGrStringValue,existArry);
                        } else {
                            newArry = new ArrayList<>();
                            newArry.add(issue);
                            legendMap.put(solutionGrStringValue,newArry);
                        }

                    }
                }
            } else if (legendFieldType.equalsIgnoreCase("Select List (single choice)")) {
                LazyLoadedOption singleChoiceListOptionVal = (LazyLoadedOption)issue.getCustomFieldValue(legendCF);
                if (singleChoiceListOptionVal != null) {
                    Option  singleChoiceListOption = optionsManager.findByOptionId(singleChoiceListOptionVal
                                                                   .getOptionId());
                    if (singleChoiceListOption != null && singleChoiceListOption.getValue() != null) {
                        String optionValue = singleChoiceListOption.getValue();
                        if(legendMap.containsKey(optionValue)){
                            ArrayList<Issue> existArry = (ArrayList<Issue>)legendMap.get(optionValue);
                            existArry.add(issue);
                            legendMap.put(optionValue,existArry);
                        } else {
                            newArry = new ArrayList<>();
                            newArry.add(issue);
                            legendMap.put(optionValue,newArry);
                        }
                    }
                }
            } else if (legendFieldType.equals("Select List (cascading)")) {
                //The older cascade field type for field  Solution Group - Product
                String depthValue = ""; //Keep it for getting first or second value as needed
                Map<LazyLoadedOption, LazyLoadedOption> selectListCascMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
                        issue.getCustomFieldValue(legendCF);
                if (selectListCascMap != null) {
                    for (Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : selectListCascMap.entrySet()) {
                        LazyLoadedOption llo = null;
                        if (opt.getKey() == null && depthOfCascade == 0) {
                            llo = opt.getValue();
                            depthValue = String.valueOf(llo.getValue());
                            logger.debug("Depth value 0 is " + depthValue);
                            logger.debug("Issue considerd is " + issue);
                        }
                        if(opt.getKey() !=  null && depthOfCascade == 1 ){
                            llo = opt.getValue();
                            depthValue = String.valueOf(llo.getValue());
                            logger.debug("Depth value 1 is " + depthValue);
                        }
                        if(legendMap.containsKey(depthValue)){
                            ArrayList<Issue> existArry = (ArrayList<Issue>)legendMap.get(depthValue);
                            if (!existArry.contains(issue)) {
                                existArry.add(issue);
                            }
                            logger.debug("Mapping Depth value " + depthValue + " --> " + existArry.toString());
                            legendMap.put(depthValue,existArry);
                        } else {
                            newArry = new ArrayList<Issue>();
                            newArry.add(issue);
                            legendMap.put(depthValue,newArry);
                        }
                    }
                }
            } else if (legendFieldType.equals("System Field")) {
                String sysField = "";

                switch(legend.toLowerCase()) {
                    case IssueFieldConstants.STATUS :
                        Status status = issue.getStatusObject();
                        if (status != null) {
                            sysField = status.getName();
                        } else {
                            sysField = "No Status";
                        }
                        break;
                    case "issue type" :
                        IssueType issueType = issue.getIssueTypeObject();
                        if (issueType != null) {
                            sysField = issueType.getName();
                        } else {
                            sysField = "No Issue Type";
                        }
                        break;
                    case IssueFieldConstants.RESOLUTION :
                        Resolution resolution = issue.getResolutionObject();
                        if(resolution != null) {
                            sysField = resolution.getName();
                        } else {
                            sysField = "Unresolved";
                        }
                        break;
                    case IssueFieldConstants.PRIORITY :
                        Priority priority = issue.getPriorityObject();
                        if (priority != null) {
                            sysField = priority.getName();
                        } else {
                            sysField = "Unprioritized";
                        }
                        break;
                    case IssueFieldConstants.ASSIGNEE :
                        ApplicationUser assignee = issue.getAssignee();
                        if (assignee != null) {
                            sysField = assignee.getName();
                        } else {
                            sysField = "Unassigned";
                        }
                        break;
                    case IssueFieldConstants.REPORTER:
                        ApplicationUser reporter = issue.getReporter();
                        if (reporter != null) {
                            sysField = reporter.getName();
                        } else {
                            sysField = "Unreported";
                        }
                        break;
                }

                if(legendMap.containsKey(sysField)){
                    List<Issue> existArry = legendMap.get(sysField);
                    existArry.add(issue);
                    legendMap.put(sysField,existArry);
                } else {
                    newArry = new ArrayList<>();
                    newArry.add(issue);
                    legendMap.put(sysField,newArry);
                }
            }
        }// end issues for loop
    }

    /**
     * Helper method that extracts the field value of 'Incident Start'
     * from the every incident and puts its value into a list. It then sorts the list
     * in ascending order of dates
     *
     * @param issues
     * @return List of dates in the format yyyy-MM-dd
     */
    private List<String> populateStartDatesList(List<Issue> issues, String xAxisOnBarChart) {
        CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
        CustomField  incidentStartCF = fieldManager.getCustomFieldObjectByName(xAxisOnBarChart);
        List<Date> incidentStartDates = new ArrayList<Date>();
        for (Issue issue : issues) {
            Date incidentStart = null;
            if (incidentStartCF != null) {
                incidentStart = (Date) issue.getCustomFieldValue(incidentStartCF);
            } else if (xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED)) {
                // If the Axis contains the  system field "Created"
                incidentStart = issue.getCreated();
            }

            if (incidentStart != null) {
                incidentStartDates.add(incidentStart);
            }
        }

        // sort the dates in ascending order as we need start and end dates used in the query
        Collections.sort(incidentStartDates);

        //Now add the sorted dates in a string array with the required format
        List<String> formattedDates = new ArrayList<String>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (Date date : incidentStartDates) {
            formattedDates.add(df.format(date));
            logger.debug("FORMATTED DATE IS " + df.format(date));
        }
        return formattedDates;
    }

    /**
     * calculate incident/problem duration programatically - note we cant use the custom field with the same name directly
     * as its a SIL script and has performance issues.
     *
     */
    private long calculateDuration(List<Issue>  monthLists, String yAxisOnBarChart){
        CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
        CustomField startDateCustomField = null;
        CustomField endDateCustomField = null;

        if (yAxisOnBarChart.contains("Incident")) {
            startDateCustomField = cfm.getCustomFieldObjectByName("Incident Start");
            endDateCustomField = cfm.getCustomFieldObjectByName("Incident End");
        } else if (yAxisOnBarChart.contains("Problem")) {
            startDateCustomField = cfm.getCustomFieldObjectByName("Problem Start");
            endDateCustomField = cfm.getCustomFieldObjectByName("Problem End");
        }

        long incidentDuration =0L;
        for(int i=0;i<monthLists.size();i++){
            Issue issue = monthLists.get(i);
            long incidentDurationMinsPerMonth = 0;
            Date startDate = (Date) issue.getCustomFieldValue(startDateCustomField);
            Date endDate = (Date) issue.getCustomFieldValue(endDateCustomField);
            if (startDate != null) {
                long millisecondsdiff;
                if (endDate != null) {
                    millisecondsdiff = endDate.getTime() - startDate.getTime();
                } else {
                    Date currentDate = new Date();
                    millisecondsdiff = currentDate.getTime() - startDate.getTime();
                }
                long seconds=(millisecondsdiff/1000)%60;
                incidentDurationMinsPerMonth=((millisecondsdiff-seconds)/1000)/60;
            }
            incidentDuration += incidentDurationMinsPerMonth;

        }
        return incidentDuration;
    }



    /**
     * Helper method to take in the search filter name and return the JQL of that filter
     * @param filter
     * @return Query object containing the JQL
     */
    private Query getQuery(String filter) {
        JqlClauseBuilder subjectBuilder = JqlQueryBuilder.newClauseBuilder().savedFilter(filter);
        return subjectBuilder.buildQuery();
    }

    /****************************************************************************************************************/
    @GET
    @AnonymousAllowed
    @Path("/getFilterName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilterName(@QueryParam("filterId") String filterId) throws Exception{

        return Response.ok(new FilterModel(filterId)).build();

    }
}