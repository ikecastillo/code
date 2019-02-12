package com.dt.jira.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * This entity class is used for automation configuration.
 * 
 * @author Srinadh.G
 */
@Preload
public interface AutoAssigneePortal extends Entity {

	String getProject();

	void setProject(String project);
	
	String getIssueType();

	void setIssueType(String issueType);
	
	String getStatus();

	void setStatus(String status);
	
	String getAssignee();

	void setAssignee(String assignee);
	
}
