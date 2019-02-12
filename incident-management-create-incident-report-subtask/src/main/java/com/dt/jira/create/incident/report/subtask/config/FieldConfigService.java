package com.dt.jira.create.incident.report.subtask.config;


public interface FieldConfigService {

		String getProject();

	 	String getIssyeType();
	    
	    String[] getDefaultIssueFields();

	    String[] getIssueCustomFields();
	    
	    String[] getProjects();
	    
	    String[] getIssueTypes();
	    
	    void setProjects(String[] projects);
	    
	    void setIssueTypes(String[] issueTypes);
	    
	    void setProject(String project);
	    
	    void setIssueType(String issueType);
	    
	    void setDefaultIssueFields(String[] defaultIssueFields);
	    
	    void setIssueCustomFields(String[] issueCustomFields);
}
