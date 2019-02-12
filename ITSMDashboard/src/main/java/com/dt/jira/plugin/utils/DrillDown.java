package com.dt.jira.plugin.utils;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atlassian.jira.issue.IssueFieldConstants.*;
import static com.dt.jira.plugin.utils.DateOperations.*;

/**
 * Utility class for the drilldown method used by the all-in-one gadget
 *
 * Created by Yagnesh.Bhat on 8/17/2015.
 */
public class DrillDown {

    public static Logger log = LoggerFactory.getLogger(DrillDown.class);

    /**
     * monthly drill down url format for searching issues
     *
     */
    public static String setMonthlyDrillDown(String solutionGrpOptionId, String legendStringValue,
                                       String month, String url, int indexMonth, int totalMonths, String startDate,
                                       String endDate, String legend,
                                       String xAxisOnBarChart, int legendDepth,String projectKey,String issueType){
        StringBuffer drillUrl = new StringBuffer(url);
        String keyString = "";
        // add below condition for ampersand since it is query string which used for drill down
        if(legendStringValue!=null && legendStringValue.length()>0 && legendStringValue.indexOf('&')>0){
            keyString = legendStringValue.replaceAll("&", "%26");
            legendStringValue = keyString;
        }

        CustomFieldManager fieldManager =  ComponentAccessor.getCustomFieldManager();
        CustomField legendCF = fieldManager.getCustomFieldObjectByName(legend);
        String legendFieldType = legendCF != null ? legendCF.getCustomFieldType().getName() : "System Field";


        /*if(type.equals("External")){*/
            /*drillUrl.append(" and  \""+fieldToCountOn+"\" in  MultiLevelCascadeOption(\""+  solutionGrpOptionId +"\") " +
                    "and  cf["+typeId+"] in  cascadeOption('"+ type + "')");*/
        if (legendFieldType.equalsIgnoreCase("Multi-Level Cascading Select")) {
            String cascadeValue =  getSolutionGrpCascadeValue(solutionGrpOptionId,legendDepth);
            String customjqlparams =  projectKey+","+issueType+","+"\""+legend+"\"" +","+legendDepth+","+"\""+cascadeValue+"\"";
            // WCDICC-1107 - customized jql function to get the issues on multilevel cascade field value
            drillUrl.append(" and  issue in  MultiLevelCascadeOptionForDepth("+customjqlparams+")");
        } else if (legendFieldType.equalsIgnoreCase("Select List (single choice)")) {
            drillUrl.append(" and \""+legend+"\" in (\""+ legendStringValue+ "\")");
        } else if (legendFieldType.equalsIgnoreCase("Select List (cascading)")) {
            if (legendDepth == 0 && legendStringValue.equals("Other")) {
                drillUrl.append(" and \""+legend+"\" in cascadeOption (\""+ legendStringValue+ "\")");
            } else {
                drillUrl.append(" and \""+legend+"\" in (\""+ legendStringValue+ "\")");
            }
        } else if (legendFieldType.equals("System Field")) {
            String legendInLowerCase =  legend.toLowerCase();
            switch (legendInLowerCase) {
                case STATUS : appendSystemFieldToDrillURL(legendInLowerCase, legendStringValue, "No Status", drillUrl); break;
                case "issue type" : appendSystemFieldToDrillURL(ISSUE_TYPE, legendStringValue, "No Issue Type", drillUrl); break;
                case RESOLUTION : appendSystemFieldToDrillURL(legendInLowerCase, legendStringValue, "Unresolved", drillUrl); break;
                case PRIORITY : appendSystemFieldToDrillURL(legendInLowerCase, legendStringValue, "Unprioritized", drillUrl); break;
                case ASSIGNEE : appendSystemFieldToDrillURL(legendInLowerCase, legendStringValue, "Unassigned", drillUrl); break;
                case REPORTER : appendSystemFieldToDrillURL(legendInLowerCase, legendStringValue, "Unreported", drillUrl); break;
            }
            //Can add more system fields here later, depending on the requirement

        }

      /*  } else {
            drillUrl.append(" and  cf["+typeId+"] in  cascadeOption('"+ type + "','" + solutionGrStringValue + "')");
        }*/

        CustomField xAxisOnBarChartCF = fieldManager.getCustomFieldObjectByName(xAxisOnBarChart);
        String xAxisFieldType;
        if (xAxisOnBarChartCF != null) {
            xAxisFieldType = xAxisOnBarChartCF.getCustomFieldType().getName();
        } else {
            xAxisFieldType = "System Field";
        }
        log.debug("XAXIS Field Type in set monthly drill down is " + xAxisFieldType);

	/* Modified by Kiran: Issue with Incident Start <= <endDate> does not considered last date. To resolve the issue changed the jql query to < getNextDate(endDate of Month) */
        if ((xAxisFieldType.contains("Date Time Picker")) ||
            (xAxisFieldType.equals("System Field") && xAxisOnBarChart.equalsIgnoreCase(IssueFieldConstants.CREATED)) ) {
            if(indexMonth==0){
                drillUrl.append(" and '"+xAxisOnBarChart+"'  >= '" + startDate + "' and '"+xAxisOnBarChart+"' < '" +
                        getNextDate(getLastDate(month)) + "'");
            }

            if(indexMonth==(totalMonths-1)){
                drillUrl.append(" and '"+xAxisOnBarChart+"'  >= '" + getStartDate(month) + "' and '"+
                        xAxisOnBarChart+"' < '" + getNextDate(endDate) + "'");
            }

            if(indexMonth>0 && indexMonth<(totalMonths-1)) {
                drillUrl.append(" and '" + xAxisOnBarChart + "'  >= '" + getStartDate(month) +
                        "' and '" + xAxisOnBarChart + "' < '" + getNextDate(getLastDate(month)) + "'");
            }
        } /*else if (xAxisFieldType.contains(""))*/

        return drillUrl.toString();
    }

    /**
     * Helper method that adds the system field related part to the drill down URL
     * @param systemFieldName
     * @param systemFieldValue
     * @param systemFieldValueWhenNull
     * @param drillUrl
     */
    private static void appendSystemFieldToDrillURL(String systemFieldName, String systemFieldValue, String systemFieldValueWhenNull, StringBuffer drillUrl) {
        if (systemFieldValue.equalsIgnoreCase(systemFieldValueWhenNull)) {
            drillUrl.append(" and " + systemFieldName + " is EMPTY");
        } else {
            drillUrl.append(" and \""+ systemFieldName+"\" in (\""+ systemFieldValue+"\")");
        }
    }

    /**
     * drill down url format for searching issues
     *
     */
    public static String setDrillDown(String url, String solutionGroupOptionId, String solutionGrStringValue, String type,
                                long typeId, String legend){
        String drillUrl = "";
        String keyString ="";
        // add below condition for ampersand since it is query string which used for drill down
        if(solutionGrStringValue!=null && solutionGrStringValue.length()>0 && solutionGrStringValue.indexOf('&')>0){
            keyString = solutionGrStringValue.replaceAll("&", "%26");
            solutionGrStringValue = keyString;
        }
        /*if(type.equals("External")){
            drillUrl = url + " and  \""+fieldToCountOn+"\" in  MultiLevelCascadeOption(\""+ solutionGroupOptionId+"\") and  cf["+typeId+"] in  cascadeOption('"+ type + "')";
        }
        else{
            drillUrl = url + " and  cf["+typeId+"] in  cascadeOption('"+ type + "','" + solutionGrStringValue + "')";
        }*/

        //drillUrl = url + " and  \""+legend+"\" in  MultiLevelCascadeOption(\""+ solutionGroupOptionId+"\")";

        drillUrl = url + " and  \""+legend+"\" in  MultiLevelCascadeOption("+ solutionGroupOptionId+")";

        return drillUrl;
    }

    /**
     * Get the option name of the multilevel cascade field
     * @param solutionGroupOptionId - optionIds
     * @param legenddepth - lengend
     * @return - option name
     */
    private static  String getSolutionGrpCascadeValue(String solutionGroupOptionId,int legenddepth){
        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        String keyString = "";
        String solutiongroupids[] = solutionGroupOptionId.split(",");
        if(solutiongroupids!=null && solutiongroupids.length>0) {
            Long numberAsSring = new Long(solutiongroupids[legenddepth].substring(1,solutiongroupids[legenddepth].length()-1));
            Option option = optionsManager.findByOptionId(numberAsSring);
            String name = option.getValue();
            if(name!=null && name.length()>0 && name.indexOf('&')>0){
                keyString = name.replaceAll("&", "%26");
            } else {
                keyString = name;
            }
            return keyString;
        }
            return null;
    }
}
