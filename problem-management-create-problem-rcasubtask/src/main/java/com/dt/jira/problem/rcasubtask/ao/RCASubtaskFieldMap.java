package com.dt.jira.problem.rcasubtask.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;

/**
 * This entity class is used for field mapping for incident to incident report sub-task.
 * 
 * @author Vijay.Badam
 */
@Preload
public interface RCASubtaskFieldMap extends Entity {

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
