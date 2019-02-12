package com.dt.jira.rcamap.plugin.constants;

public  interface RCASubtaskAssigneeMapConstants {
	
	public static final String ISSUE_TYPE_INCIDENT = "Incident";
	public static final String ISSUE_TYPE_PROBLEM = "Problem";
	
	public static final String ISSUE_TYPE_INCIDENT_REPORT_SUBTASK = "Incident Report Subtask";
	public static final String ISSUE_TYPE_RCA_REPORT_SUBTASK = "RCA Report Subtask";
	
	public static final String STATUS_PENDING_APPROVAL = "PENDING APPROVAL";
	public static final String STATUS_RESOLVED_PENDING_REPORT_REVIEW = "RESOLVED - PENDING REPORT REVIEW"; ///Resolved - Pending Report Review
	public static final String STATUS_RESOLVED_CONFIRMED = "RESOLVED - CONFIRMED";
	public static final String STATUS_CLOSED = "CLOSED";
	public static final String STATUS_ASSIGNED = "Assigned";
	public static final String STATUS_PENDING_BOARD_REVIEW = "Pending Board Review";
	
	
	public static final String FIELD_PROBLEM_INVESTIGATION_REQUIRED = "Problem Investigation Required";
	public static final String FIELD_SEVERITY = "Severity";
	public static final String FIELD_TIMELINE_OF_EVENTS = "Timeline of Events";
	public static final String FIELD_XMATTERS_LOG = "XMatters Log";
	public static final String FIELD_INCIDENT_KEY = "Incident Key";
	public static final String FIELD_TYPE = "Clients Impacted";
	public static final String FIELD_SOLUTION_GROUP_PRODUCT= "Solution Groups - Products";
	public static final String FIELD_IMPACTED_FUNCTION = "Impacted - Function";
	
	
	public static final String ROLE_INCIDENT_MANAGER = "Incident Manager";
	public static final String ROLE_ADMINISTRATORS = "Administrators";
	public static final String ROLE_NOC = "NOC";
	
	
	/* RCA Report Assignee Map- Roles ID : Ids are not related system. It is used as unique number instead of role name. */
	public static final long ROLE_RCA_REPORT_ASSIGNEE = 10800L; //RCA Report Assignee or First level approver
	public static final long ROLE_RCA_APPROVER = 10801L; //RCA Approver or Secondlevel approver	
	public static final long ROLE_ENTERPRISE_BOARD_REVIEW_MEMEBER = 10802L; //Enterprise Board Review Member
	
	
	

}
