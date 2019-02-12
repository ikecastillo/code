package com.dt.jira.plugin.innotas.service;

import com.atlassian.activeobjects.tx.Transactional;
import java.util.Map;

@Transactional
public interface InnotasCacheService {
	Map<String, String> getInnotasBUs();
	Map<String, Object> getInnotasProjects();
	Map<String, Object> getInnotasProjectsByIDs(String ProjectIDs, String releseIds);
	Map<String, Object> getInnotasProjects(String buId);
	Map<String, Object> getInnotasProjects(String buId, String sbuId);
	Map<String, Object> getInnotasReleases(String projectId);
	Map<String, Object> getReleases(String projectKey);
	Map<String, Object> getMapping(String projectKey);
	void saveMapping(String jpKey, String jRel, String ipId, String ipRel);
	String getMappedBuId(String projectKey);
	boolean syncCache();
	String  getInnotasReleaseMapping(String projectKey, String releseIds);
}