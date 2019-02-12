package com.dt.jira.plugin.innotas.ao;
 
import net.java.ao.Entity;
import net.java.ao.Preload;
 
@Preload
public interface InnotasMap extends Entity
{
	String getInnotasProjectId();
	void setInnotasProjectId(String v); 

	String getInnotasReleaseId();
	void setInnotasReleaseId(String v); 

  String getJiraProjectKey();
	void setJiraProjectKey(String v);
	
	String getJiraReleaseId();
	void setJiraReleaseId(String v);
	}