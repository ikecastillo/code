package com.dt.jira.assigneemap.plugins.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * The Entity Model for Assignee Map Active Object within Change Management project
 *
 * @author Firoz.Khan
 */
@Preload
public interface AssigneeMap extends Entity
{

    String getProjectKey();
	void setProjectKey(String v); 

	String getSolutionGroup();
	void setSolutionGroup(String v); 

    String getImpact();
	void setImpact(String v);
	
	String getStatus();
	void setStatus(String v);
	
	String getUsers();
	void setUsers(String v);

}
