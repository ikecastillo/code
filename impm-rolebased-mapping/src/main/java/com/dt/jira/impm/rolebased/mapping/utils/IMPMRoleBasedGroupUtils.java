package com.dt.jira.impm.rolebased.mapping.utils;

import java.util.HashMap;
/**
 * This class is used for Incident and Problem Management Constants Utility.
 * 
 * @author Srinadh.Garlapati & Firoz.Khan
 */
public class IMPMRoleBasedGroupUtils {
	public static final HashMap<String, String> solutionGroupMap = new HashMap<String, String>();
	public static final String SOLUTION_GROUPS_PRODUCTS_FIELD = "Solution Groups - Products";
	public static final String CUSTOM_COMPONENT_MULTI_USER ="SolutionLineNocUsers";
	public static final String CUSTOM_COMPONENT_MULTI_GROUP ="SolutionLineNocGroups";		
	//Problem Constants
	public static final String ISSUE_TYPE_PROBLEM = "Problem";
	public static final String ISSUE_TYPE_RCA_REPORT_SUBTASK = "RCA Report Subtask";
	//Incident Constants
	public static final String ISSUE_TYPE_INCIDENT = "Incident";
	public static final String ISSUE_TYPE_INCIDENT_REPORT_SUBTASK = "Incident Report Subtask";
	public static final String SUMMARY_FIELD = "summary";
	public static final String DESCRIPTION_FIELD = "description";
	public static final String XMATTERS_DESCRIPTION_FIELD = "xMatters Description";
}
