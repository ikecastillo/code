package com.dt.jira.impm.rolebased.mapping.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * This entity interface is used for Project Role Based Mapping configuration.
 * 
 * @author Firoz.Khan
 */
@Preload
public interface IMPMRoleBased extends Entity {
	
	String getProjectKey();

	void setProjectKey(String projectKey);
	
	String getIssueType();

	void setIssueType(String issueType);

	String getSolutionGroup();

	void setSolutionGroup(String solutionGroup);
			
	String getProjectRole();

	void setProjectRole(String projectRole);	

	boolean isComplete();

	void setComplete(boolean complete);

	
}