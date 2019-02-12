
package com.dt.jira.plugin.innotas.ao;
 
import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.search.SearchException;
 
import java.util.List;
import java.util.Map;
 
@Transactional
public interface InnotasService
{
	Innotas add(String innotasProjectId, String jiraProjectKey, String jiraProjectId,boolean extRel); 
  List<Innotas> getMappedProjects();
	Innotas find(String pKey);
	Innotas find(String pKey, String relId);
	Map<String, String> getInnotasBUs();
	Map<String, String> getInnotasSBUs(String buId);
	Map<String, Object> getInnotasProjects();
	Map<String, Object> getInnotasProjects(String buId);
	Map<String, Object> getInnotasProjects(String buId, String sbuId);
	boolean syncCache();
	void updateOldIssues(String pKey) throws SearchException;
	Map<String, Object> getReleases(String projectKey);
	String getInnotasProjectId(String pKey, String relId);
	Map<String, Object> getInnotasMapping(String pKey, String relId);
	List<Innotas> getInnotasMap();
	List<Innotas> getInnotasMap(String pKey);
	List<Option> getFieldOptions(String pKey, String cfName) throws Exception;
	void setFieldOptions(String pKey, String cfName, List<String> values)
			throws Exception;
	void setAllFieldOptions(String jiraProjectKey);
	Map<String, String> getInnotasSBUs();
	List<String> getInnotasPrograms();
}