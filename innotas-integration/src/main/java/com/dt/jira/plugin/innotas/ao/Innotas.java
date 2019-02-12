package com.dt.jira.plugin.innotas.ao;
 
import net.java.ao.Entity;
import net.java.ao.Preload;
//import net.java.ao.schema.PrimaryKey;
 
@Preload
public interface Innotas extends Entity
{
	String getInnotasProjectId();
	void setInnotasProjectId(String projectId); 
	//@PrimaryKey("jiraProjectKey")
  String getJiraProjectKey();
	void setJiraProjectKey(String jiraProjectKey);
	String getJiraRelId();
	void setJiraRelId(String jiraRelId);
	boolean getExtRel();
	void setExtRel(boolean value);

	}