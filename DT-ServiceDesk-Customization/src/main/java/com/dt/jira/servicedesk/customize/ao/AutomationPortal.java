package com.dt.jira.servicedesk.customize.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * This entity class is used for automation configuration.
 * 
 * @author Srinadh.G
 */
@Preload
public interface AutomationPortal extends Entity {

	String getProject();

	void setProject(String project);
	
	String getIssueType();

	void setIssueType(String issueType);
	
	String getSubTask();

	void setSubTask(String subTask);
	
	String getUserdn();

	void setUserdn(String userdn);
	
	String getAssignee();

	void setAssignee(String assignee);
	
	String getPassword();

	void setPassword(String password);
	
	String getGroup();

	void setGroup(String group);

	


}
