package com.dt.jira.incident.problem.ao;

import net.java.ao.Entity;
/*
 * Create the Field Mapping table for Problem 
 */
public interface ProblemFieldMap extends Entity {

	String getJiraField();

	void setJiraField(String jiraField);
			
	String getMappingField();

	void setMappingField(String mappingField);
	
	String getFromIssueType();

	void setFromIssueType(String fromIssueType);
	
	String getToIssueType();

	void setToIssueType(String toIssueType);

	boolean isComplete();

	void setComplete(boolean complete);

	
}