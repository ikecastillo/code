package com.dt.jira.incidentmap.plugin.constants;

public interface IRSubtaskAssigneeMapConstants {
	public static final String ISSUE_TYPE_INCIDENT = "Incident";
	public static final String ISSUE_TYPE_INCIDENT_REPORT_SUBTASK = "Incident Report Subtask";
	
	public static final String STATUS_PENDING_APPROVAL = "PENDING APPROVAL";
	public static final String STATUS_RESOLVED_PENDING_REPORT_REVIEW = "RESOLVED - PENDING REPORT REVIEW";
	public static final String STATUS_ASSIGNED = "Assigned";
	public static final String STATUS_PENDING_BOARD_REVIEW = "Pending Board Review";
	
	
	public static final String FIELD_TYPE = "Clients Impacted";
	//public static final String FIELD_SOLUTION_GROUP_PRODUCT = "Solution Group - Product";
	public static final String FIELD_SOLUTION_GROUP_PRODUCT= "Solution Groups - Products";
	public static final String FIELD_IMPACTED_FUNCTION = "Impacted - Function";
	//Impacted - Function
	
	/* Incident Report Assignee Map- Roles ID : Ids are not related system. It is used as unique number instead of role name. */
	public static final long ROLE_INCIDENT_REPORT_ASSIGNEE = 10800L; //Incident Report Assignee or First level approver
	public static final long ROLE_SOLUTIONTEAM_INCIDENT_MANAGER = 10801L; //Solution Team Incident Manager or Secondlevel approver	
	public static final long ROLE_INCIDENT_MANAGEMENT_BOARD_REVIEW_MEMEBER = 10802L; //Incident Management Board Review Member
	//additional feilds 
	public static final String ROLE_COMMUNICATIONS_LEAD = "10803";//Incident Management Communications Lead
	public static final String ROLE_TECH_LEAD = "10804";//Incident Management Tech Lead
	public static final String ROLE_CUST_IMPACT_LEAD = "10805";//Incident Management Cust. Impact Lead
	public static final String ROLE_CUST_SERVICE_CONTACT = "10806";//Incident Management Cust. Service Contact
	public static final String COMPONENT_COMMUNICATIONS_LEAD ="Communications Lead";//Custom component Communications Lead
	public static final String COMPONENT_TECH_LEAD = "Tech Lead";//Custom component Tech Lead
	public static final String COMPONENT_CUST_IMPACT_LEAD = "Cust. Impact Lead";//Custom component Cust. Impact Lead
	public static final String COMPONENT_CUST_SERVICE_CONTACT = "Cust. Service Contact";//Custom component Cust. Service Contact
	public static final String COMPONENT_ISSUE_STATUS = "status";//Custom component Cust. Service Contact
	
	
}
