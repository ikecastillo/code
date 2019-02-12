package com.dt.jira.problem.rcasubtask.constants;

public  interface CreateRCASubtaskConstants {
	
	public static final String ISSUE_TYPE_INCIDENT = "Incident";
	public static final String ISSUE_TYPE_PROBLEM = "Problem";
	public static final String ISSUE_TYPE_OUTAGE = "Outage";
	
	public static final String ISSUE_TYPE_INCIDENT_REPORT_SUBTASK = "Incident Report Subtask";
	public static final String ISSUE_TYPE_RCA_REPORT_SUBTASK = "RCA Report Subtask";

	public static final String STATUS_IN_PROGRESS = "IN PROGRESS";
	public static final String STATUS_PENDING_APPROVAL = "PENDING APPROVAL";
	public static final String STATUS_RESOLVED_PENDING_REPORT_REVIEW = "RESOLVED - PENDING REPORT REVIEW"; ///Resolved - Pending Report Review
	public static final String STATUS_RESOLVED_CONFIRMED = "RESOLVED - CONFIRMED";
	public static final String STATUS_CLOSED = "CLOSED";
	
	
	
	public static final String FIELD_PROBLEM_INVESTIGATION_REQUIRED = "Problem Investigation Required";
	public static final String FIELD_SEVERITY = "Severity";
	public static final String FIELD_TIMELINE_OF_EVENTS = "Timeline of Events";
	public static final String FIELD_XMATTERS_LOG = "XMatters Log";
	public static final String FIELD_INCIDENT_KEY = "Incident Key";
	
	public static final String ROLE_INCIDENT_MANAGER = "Incident Manager";
	public static final String ROLE_ADMINISTRATORS = "Administrators";
	public static final String ROLE_NOC = "NOC";
	
	
	
	
	

}
