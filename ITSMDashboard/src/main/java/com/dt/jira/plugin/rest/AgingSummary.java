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
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.jql.builder.JqlClauseBuilder;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
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
import java.text.*;
import java.util.*;

import static com.dt.jira.plugin.utils.DateOperations.getListOfMonths;
import static com.dt.jira.plugin.utils.DateOperations.getMonthYear;
import static com.dt.jira.plugin.utils.DrillDown.*;
import static com.dt.jira.plugin.utils.NumberFormatter.getNumberFormat;
import static com.dt.jira.plugin.utils.NumberFormatter.parseNumberFormat;


/**
 * REST service that provides the data for Graphs pertaining to Aging.
 *
 */
@Path("/agingsummary")
public class AgingSummary {
    /* Logger */
    private final Logger logger = Logger.getLogger(AgingSummary.class);

    //public static String ISSUE_TYPES = "";

    /**
     * REST service that provides the data for incident count, incident duration, problem count and duration gadgets.
     * @param searchFilter
     * @param groupBy
     * @param depth
     * @param sigmavalues
     * @param xAxisOnBarChart
     * @param yAxisOnBarChart
     * @return JSON Response
     * @throws Exception
     */
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("searchFilter") String searchFilter,
                               @QueryParam("groupBy") String groupBy,
                               @QueryParam("legenddepth") String depth,
                               @QueryParam("sigmavalues") String sigmavalues,
                               @QueryParam("xaxis") String xAxisOnBarChart,
                               @QueryParam("yaxis") String yAxisOnBarChart) throws Exception{

        /* construct the jql query for drill down - WE DONT NEED DRILL DOWN FOR THE AGING GADGET*/
        //StringBuffer drilldownjql = new StringBuffer();
       /* int depthOfCascade = 0;

        if (!depth.equals("Does Not Apply")) {
            depthOfCascade = Integer.parseInt(depth);
        }*/

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
        String xAxisFieldType = xAxisOnBarChartCF.getCustomFieldType().getName();
        logger.debug("FIELD TYPE OF XAXIS IS " + xAxisFieldType);


        String drillDownForMonth = URL + jqlString;// Drill down jql query for each month
        logger.debug("Drill Down URL is " + drillDownForMonth);
	
	    /* search the issues based on jql query */
        final SearchService.ParseResult sevtMajor = searchServ.parseQuery(authenticationContext.getLoggedInUser(),
                jqlString);

	    /* Build the list for each solution group */
        ArrayList<GenericSummaryModel> listOfModels= new ArrayList<GenericSummaryModel>();
        List<String> xCoordinatesList = null; //to store a sorted list of dates in order to pick up start date and end date
        //String status = null;

        String xAxisCoordinateValues = "";
        logger.debug("CHECKING IF PARSE RESULTS ARE VALID ... ");
        if (sevtMajor.isValid()) {
            logger.debug("SEVTMAJOR IS VALID");
            SearchResults results1 = searchServ.search(authenticationContext.getLoggedInUser(), sevtMajor.getQuery(),
                    PagerFilter.getUnlimitedFilter());

            List<Issue> issues = results1.getIssues();

            //status = getStatusAsPipeSeparatedString(issues);

            if (xAxisFieldType.contains("Date Time Picker")) {
                xCoordinatesList = populateStartDatesList(issues, xAxisOnBarChart);
                xAxisCoordinateValues = getListOfMonths(xCoordinatesList.get(0),
                        xCoordinatesList.get(xCoordinatesList.size() - 1));
            }

            logger.debug("LIST OF XAXIS COORDINATE VALUES " + xAxisCoordinateValues);
            logger.debug("TOTAL ISSUES SIZE : " + issues.size());

            /* Start build the list for the header */
            GenericSummaryModel header = new GenericSummaryModel();
            //header.setLegend("Major " + ISSUE_TYPES + "s");
            header.setLegend("Major Issues");
            StringTokenizer  stringTokenizer = new StringTokenizer(xAxisCoordinateValues,",");
            ArrayList<GenericModel> headerModels= new ArrayList<GenericModel>();
            while(stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                headerModels.add(new GenericModel(token, "0"));
            }
            header.setGraphCoordinates(headerModels);
            listOfModels.add(header);

            if(issues!= null && issues.size() > 0)  {
                /* Create the Map for if type is external or internal. If it is external, then solution group as key
                and list of issues as value other wise location as key and list of issues as value */
                Map<String,List<Issue>> legendHashMap = new HashMap<String,List<Issue>>();

                // Iterated all the issues create the Map for each solution group.
                updateHashMapBasedOnLegend(legendCF, issues, legendHashMap);

                // Iterated the above created sgMap and re build the execute summary model  for each legend
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
                        String xAxisCustomFieldValuePerIssue = issue.getCustomFieldValue(xAxisOnBarChartCF)!= null ?
                                issue.getCustomFieldValue(xAxisOnBarChartCF).toString() : null ;

                        if (xAxisFieldType.contains("Date Time Picker") && xAxisCustomFieldValuePerIssue != null) {
                            xAxisCustomFieldValuePerIssue = getMonthYear(xAxisCustomFieldValuePerIssue);
                            populateFinalJSONForXAxis(finalJson, issue, xAxisCustomFieldValuePerIssue);
                        }

                    }//for

                    //System.out.println("SG: "+ key);
                    execSummaryModel.setLegend(key);
					/* Sets Drill Down for issue search */
                    logger.debug("SG :" + key);
                    String drillDownUrl = setDrillDown(URL, solutionGrOptId, key, type, typeId,
                            groupBy);
                    execSummaryModel.setDrillDown(drillDownUrl);

                    ArrayList<GenericModel> monthlyList= new ArrayList<GenericModel>();

                    String headers = null;
                    if (xAxisFieldType.contains("Date Time Picker")) {
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
                        GenericModel monthlyModel = null;
                        int flag = 0;
                        for(Map.Entry<String, List<Issue>>  finaljsonVar: finalJson.entrySet()){
                            String _key = finaljsonVar.getKey();
                            List<Issue>  xAxisValueLists = (List<Issue>) finalJson.get(_key);
                            if(monthName.equalsIgnoreCase(_key)){
                                monthlyModel = new GenericModel(_key, getNumberFormat(xAxisValueLists.size()));

                                /* Sets Monthly Drill Down for issue search */
                               /* String monthlyUrl = setMonthlyDrillDown(solutionGrOptId, key,
                                        _key, drillDownForMonth, indexMonth, totalMonths, xCoordinatesList.get(0),
                                        xCoordinatesList.get(xCoordinatesList.size() - 1),
                                        groupBy, xAxisOnBarChart);
                                monthlyModel.setDrillDown(monthlyUrl);*/
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
        if (xAxisFieldType.contains("Date Time Picker")) {
            logger.debug("START DATES LIST IS " + xCoordinatesList);
            calculateTotAndPercentage(listOfModels, xCoordinatesList.get(0),
                    xCoordinatesList.get(xCoordinatesList.size() - 1));
            //calculateAvarage(listOfModels, xCoordinatesList.get(0), xCoordinatesList.get(xCoordinatesList.size() - 1));
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

    /**
     * Helper method to create the Map based on the Legend. If the legend is a MS Cascade, then solution group as key
     * and list of issues as value other wise field name as key and list of issues as value
     *
     * @param issues List of issues
     * @param legendMap  The empty map to update
     */
    private void updateHashMapBasedOnLegend(CustomField legendCF, List<Issue> issues, Map<String,
            List<Issue>> legendMap) {

        List<Issue> oneDay = new ArrayList<Issue>();
        List<Issue> twoToFiveDays = new ArrayList<Issue>();
        List<Issue> sixToTwentyFiveDays = new ArrayList<Issue>();
        List<Issue> lessThanOneDay = new ArrayList<Issue>();
        List<Issue> moreThan25Days = new ArrayList<Issue>();

        legendMap.put("1 Day", oneDay);
        legendMap.put("2-5 Days" , twoToFiveDays);
        legendMap.put("6-25 Days", sixToTwentyFiveDays);
        legendMap.put("Less Than 1 Day", lessThanOneDay);
        legendMap.put("More Than 25 Days", moreThan25Days);

        for(Issue issue: issues){
            categorizeIssueByDurationInDays(issue, legendMap, legendCF);
        }// end issues for loop
    }

    /**
     * Helper method that populates our hashmap with the proper legends required for duration
     * custom field.
     *
     * @param issue
     * @param legendMap
     * @param durationCustomField
     */
    private void categorizeIssueByDurationInDays(Issue issue, Map<String, List<Issue>> legendMap,
                                                 CustomField durationCustomField) {

        /*long durationMinutes = Long.valueOf(issue
                .getCustomFieldValue(durationCustomField)
                .toString().replace(" Minutes", "").toString());*/

        /*if (incidentenddate is not null) {
            return incident end - incident start
        } else {
            return current time - incident start
        }*/

        CustomField incidentStartCustomField = ComponentAccessor.getCustomFieldManager()
                                                                .getCustomFieldObjectByName("Incident Start");
        CustomField incidentEndCustomField = ComponentAccessor.getCustomFieldManager()
                .getCustomFieldObjectByName("Incident End");

        Date incidentStartDate = (Date) issue.getCustomFieldValue(incidentStartCustomField);
        Date incidentEndDate = (Date) issue.getCustomFieldValue(incidentEndCustomField);


        double  durationInDays = 0;
        if (incidentStartDate != null) {
            if (incidentEndDate != null) {
                durationInDays = ((incidentEndDate.getTime() - incidentStartDate.getTime()) / (1000*60*60*24));
            } else {
                Date currentDate = new Date();
                durationInDays = ((currentDate.getTime() - incidentStartDate.getTime()) / (1000*60*60*24));
            }
        }



        /*long durationMinutes = 1440;*/

       /* double durationInDays = durationMinutes/24/60;*/

        if (durationInDays < 1) {
            legendMap.get("Less Than 1 Day").add(issue);
        } else if ( durationInDays >= 1 && durationInDays < 2) {
            legendMap.get("1 Day").add(issue);
        } else if (durationInDays >= 2 && durationInDays <= 5) {
           legendMap.get("2-5 Days").add(issue);
        } else if (durationInDays >= 6 && durationInDays <= 25) {
           legendMap.get("Less Than 1 Day").add(issue);
        } else if (durationInDays >= 2 && durationInDays <= 25) {
           legendMap.get("More Than 25 Days").add(issue);
        }

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
            Date incidentStart = (Date) issue.getCustomFieldValue(incidentStartCF);
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
     * Helper method to take in the search filter name and return the JQL of that filter
     * @param filter
     * @return Query object containing the JQL
     */
    private Query getQuery(String filter) {
        JqlClauseBuilder subjectBuilder = JqlQueryBuilder.newClauseBuilder().savedFilter(filter);
        return subjectBuilder.buildQuery();
    }
}