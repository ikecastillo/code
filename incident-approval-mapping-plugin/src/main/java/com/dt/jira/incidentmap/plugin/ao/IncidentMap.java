package com.dt.jira.incidentmap.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * The Entity Model for Incident Report Assignee Map Active Object on Incident Management project
 *
 * @author Firoz Khan
 */
@Preload
public interface IncidentMap extends Entity
{

    String getProjectKey();
	void setProjectKey(String v); 

	String getType();
	void setType(String v); 

    String getGrpOptionId();
	void setGrpOptionId(String v);
	
	String getGrpOptionName();
	void setGrpOptionName(String v);
	
	String getCldGrpOptionId();
	void setCldGrpOptionId(String v);
	
	String getCldGrpOptionName();
	void setCldGrpOptionName(String v);
	
	String getRoles();
	void setRoles(String v);
	
	String getUsers();
	void setUsers(String v);
	
	String getCreatedDate();
	void setCreatedDate(String v);
	
	String getModifiedDate();
	void setModifiedDate(String v);
	
	String getModifiedBy();
	void setModifiedBy(String v);
	
	String getFourthLevelOptionId();
	void setFourthLevelOptionId(String v);
	
	String getFourthLevelOptionName();
	void setFourthLevelOptionName(String v);
	

}
