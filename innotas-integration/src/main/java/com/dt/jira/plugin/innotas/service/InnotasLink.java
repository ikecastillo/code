package com.dt.jira.plugin.innotas.service;
import java.util.Map;

public interface InnotasLink
{
    //String getName();
	Map<Long, String> getInnotasData();
	public Map <Long, String> getInnotasBUs();
  public Map <Long, Object> getInnotasProjects();
	public Map <Long, Object> getInnotasProjects(String buId);
	public Map <Long, Object> getInnotasProjects(String buId, String sbuId);
	
}