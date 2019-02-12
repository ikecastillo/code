package com.dt.jira.impm.rolebased.mapping.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.impm.rolebased.mapping.service.IMPMRoleBasedConfigService;

 /**
 * IMPMRoleBasedConfigAction.java class for Incident and Problem Management roles and group mapping - Add/Delete within Incident and Problem Management Project.
 * @author Firoz Khan
 * @version 1.0.0
 */
public class IMPMRoleBasedConfigAction extends JiraWebActionSupport{
	
    private static final long serialVersionUID = 1437688758307379730L;

    private final ApplicationProperties applicationProperties;
    private String projectKey;
    private String issueType;
    private final IMPMRoleBasedConfigService roleBasedConfigService;
    private final GroupManager groupManager;
    private boolean isSaved = false;
    
    private List<String> savedProjectRoles;
    private String[] selectedProjectRoles = new String[0];
        
    private List<String> savedProjects;    
    private String[] selectedProjects = new String[0];
    
    private String project;
    public IMPMRoleBasedConfigAction(
        ApplicationProperties applicationProperties,
        IMPMRoleBasedConfigService roleBasedConfigService,
        GroupManager groupManager)
    {
        this.applicationProperties = applicationProperties;
        this.roleBasedConfigService = roleBasedConfigService;
        this.groupManager = groupManager;

        //project= roleBasedConfigService.getProject();
        
        selectedProjects = roleBasedConfigService.getProjects();
        savedProjects = selectedProjects == null ? null : Arrays.asList(roleBasedConfigService.getProjects());
        
        selectedProjectRoles = roleBasedConfigService.getProjectRoles();     
        savedProjectRoles = selectedProjectRoles == null ? null : Arrays.asList(roleBasedConfigService.getProjectRoles());  
        
    }
    
    @Override
    protected String doExecute()
    throws Exception
    {
    	//roleBasedConfigService.setProject(project);
    	               
        roleBasedConfigService.setProjects(selectedProjects);
        if (selectedProjects != null)
        {
            savedProjects = Arrays.asList(roleBasedConfigService.getProjects());
        }
        
        roleBasedConfigService.setProjectRoles(selectedProjectRoles);
        if (selectedProjectRoles != null)
        {
        	savedProjectRoles = Arrays.asList(roleBasedConfigService.getProjectRoles());
        }
        
        setSaved(true);

        return getRedirect("RoleBasedConfigAction!default.jspa?saved=true");
    }

    @Override
    protected void doValidation()
    {
        super.doValidation();
   
    }

    
    /**
     * Get all Project Key.
     */
    public String getProjectKey(){
    	
        return projectKey;
    }
    /**
     * Get all Projects.
     */
    public Collection<Project> getAllProjects()
    {
        return ComponentAccessor.getProjectManager().getProjectObjects();
    }
  
 
	/**
     * Get all ProjectRoles.
     */
    public Collection<ProjectRole> getAllProjectRoles() {
    	ProjectRoleManager projectRoleManager =ComponentAccessor.getComponent(ProjectRoleManager.class);
		return projectRoleManager.getProjectRoles();
	}  
   
    /**
     * Get BaseUrl.
     */
    public String getBaseUrl()
    {
        return applicationProperties.getBaseUrl();
    }

//	/**
//     * Get Project.
//     */
//	 
//    public String getProject()
//    {
//    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(request.getParameter("pkey"));
//        return project.getKey();
//    }
//    
//    public void setProject(String project)
//    {
//        this.project = project;
//    }

    public String getIssueType()
    {
        return issueType;
    }

    
    public List<String> getSavedProjects()
    {
        return savedProjects;
    }

    
    public String[] getSelectedProjects()
    {
        return selectedProjects;
    }
    
    public List<String> getSavedProjectRoles()
    {
        return savedProjectRoles;
    }

    public String[] getSelectedProjectRoles()
    {
        return selectedProjectRoles;
    }
    
    public boolean hasAdminPermission()
    {
        ApplicationUser user = getLoggedInUser();
        if (user == null)
        {
            return false;
        }

        return getPermissionManager().hasPermission(Permissions.ADMINISTER, getLoggedInUser());
    }

    public boolean isSaved()
    {
        return isSaved;
    }

    
    public void setProjectKey(String projectKey)
    {
        this.projectKey = projectKey;
    }
   
    public void setIssueType(String issueType)
    {
        this.issueType = issueType;
    }

    public void setSaved(boolean isSaved)
    {
        this.isSaved = isSaved;
    }

    
    public void setSavedProjects(List<String> savedProjects)
    {
        this.savedProjects= savedProjects;
    }

    public void setSelectedProjects(String[] selectedProjects)
    {
        this.selectedProjects = selectedProjects;
    }
   
    
    public void setSavedProjectRoles(List<String> savedProjectRoles)
    {
        this.savedProjectRoles = savedProjectRoles;
    }

    public void setSelectedProjectRoles(String[] selectedProjectRoles)
    {
        this.selectedProjectRoles = selectedProjectRoles;
    }
    
  
}
