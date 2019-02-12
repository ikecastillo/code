package com.dt.jira.incident.problem.config;

import java.util.Set;

public interface FieldConfigService {

		String getProject();

	 	String getIssyeType();
	    
	    String[] getDefaultIssueFields();

	    String[] getIssueCustomFields();

	    Set<String> getAdminUsers();

	    String getAdminUserStr();
	    
	    String[] getWorkGroups();
	    
	    String[] getProjects();
	    
	    String[] getIssueTypes();

	    void setAdminUsers(Set<String> users);

	    void setAdminUserStr(String users);

	    void setWorkGroups(String[] workGroups);
	    
	    void setProjects(String[] projects);
	    
	    void setIssueTypes(String[] issueTypes);
	    
	    void setProject(String project);
	    
	    void setIssueType(String issueType);
	    
	    void setDefaultIssueFields(String[] defaultIssueFields);
	    
	    void setIssueCustomFields(String[] issueCustomFields);
}
