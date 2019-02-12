package com.dt.jira.impm.rolebased.mapping.service;

/**
 * This interface is used for Incident and Problem Management roles and group mapping Configuration Service.
 * 
 * @author Firoz.Khan
 */
public interface IMPMRoleBasedConfigService {

		String getProject();
	
		String[] getProjects();
		
		String[] getIssueTypes();
	    
	    String[] getSolutionGroups();

	    String[] getProjectRoles();	
	    
	    String[] getWorkGroups();
	    
	    void setProject(String project);
	    
	    void setProjects(String[] projects);
	    
	    void setIssueTypes(String[] issueTypes);
	    
	    void setSolutionGroups(String[] SolutionGroups);
	    
	    void setProjectRoles(String[] projectRoles);
	    
	    void setWorkGroups(String[] workGroups);
}
