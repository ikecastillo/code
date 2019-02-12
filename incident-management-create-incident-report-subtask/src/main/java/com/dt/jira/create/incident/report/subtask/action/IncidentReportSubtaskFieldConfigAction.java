package com.dt.jira.create.incident.report.subtask.action;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.atlassian.crowd.embedded.api.Group;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldException;
import com.atlassian.jira.issue.fields.NavigableField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.create.incident.report.subtask.config.FieldConfigService;
import com.atlassian.jira.user.ApplicationUser;

public class IncidentReportSubtaskFieldConfigAction extends JiraWebActionSupport{
	
    private static final long serialVersionUID = 1437688758307379730L;

    private final ApplicationProperties applicationProperties;
    private String project;
    
    private String projectKey;
    private String issueType;
    private final FieldConfigService fieldConfigService;
    private final GroupManager groupManager;
    private boolean isSaved = false;
    private String userNames;
    private List<String> savedDefaultIssueFields;
    private String[] selectedDefaultIssueFields = new String[0];
    private List<String> savedIssueCustomFields;
    private String[] selectedIssueCustomFields = new String[0];
    private List<String> savedGroups;    
    private String[] selectedGroups = new String[0];
    
    private List<String> savedProjects;    
    private String[] selectedProjects = new String[0];
    
    private List<String> savedIssueTypes;    
    private String[] selectedIssueTypes = new String[0];
    
    public IncidentReportSubtaskFieldConfigAction(
        ApplicationProperties applicationProperties,
        FieldConfigService fieldConfigService,
        GroupManager groupManager)
    {
        this.applicationProperties = applicationProperties;
        this.fieldConfigService = fieldConfigService;
        this.groupManager = groupManager;

        project= fieldConfigService.getProject();
        
        selectedDefaultIssueFields = fieldConfigService.getDefaultIssueFields();     
        savedDefaultIssueFields = selectedDefaultIssueFields == null ? null : Arrays.asList(fieldConfigService.getDefaultIssueFields());
        
        selectedIssueCustomFields = fieldConfigService.getIssueCustomFields();
        savedIssueCustomFields = selectedIssueCustomFields == null ? null : Arrays.asList(fieldConfigService.getIssueCustomFields());
  
        selectedProjects = fieldConfigService.getProjects();
        savedProjects = selectedProjects == null ? null : Arrays.asList(fieldConfigService.getProjects());
        
        selectedIssueTypes = fieldConfigService.getIssueTypes();
        savedIssueTypes = selectedIssueTypes == null ? null : Arrays.asList(fieldConfigService.getIssueTypes());
  
        
    }
    
    @Override
    protected String doExecute()
    throws Exception
    {
    	fieldConfigService.setProject(project);;
        
        fieldConfigService.setDefaultIssueFields(selectedDefaultIssueFields);
        if (selectedDefaultIssueFields != null)
        {
        	savedDefaultIssueFields = Arrays.asList(fieldConfigService.getDefaultIssueFields());
        }
        
        fieldConfigService.setIssueCustomFields(selectedIssueCustomFields);
        if (selectedIssueCustomFields != null)
        {
        	savedIssueCustomFields = Arrays.asList(fieldConfigService.getIssueCustomFields());
        }

        
        fieldConfigService.setProjects(selectedProjects);
        if (selectedProjects != null)
        {
            savedProjects = Arrays.asList(fieldConfigService.getProjects());
        }
        
        fieldConfigService.setIssueTypes(selectedIssueTypes);
        if (selectedIssueTypes != null)
        {
            savedIssueTypes = Arrays.asList(fieldConfigService.getIssueTypes());
        }
        
        
        setSaved(true);

        return getRedirect("FieldConfigAction!default.jspa?saved=true");
    }

    @Override
    protected void doValidation()
    {
        super.doValidation();
   
    }

    
    /**
     * Get project key.
     */
    public String getProjectKey(){
    	
        return projectKey;
    }
    /**
     * Get all projects.
     */
    public Collection<Project> getAllProjects()
    {
        return ComponentAccessor.getProjectManager().getProjectObjects();
    }
    /**
     * Get all issue types of the project.
     */
    public Collection<IssueType> getAllProjectIssueTypes()
    {
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(request.getParameter("pkey"));
    	return project.getIssueTypes();
    }
    
    /**
     * Get all issue types.
     */
    public Collection<IssueType> getAllIssueTypes()
    {
    	return  ComponentAccessor.getConstantsManager().getAllIssueTypeObjects();
    }
    
    /**
     * Get all the fields respective of the project.
     */  
    public List<Field> getAllDefaultFields() {
        Set<Field> allFieldsSet = new TreeSet<Field>();
        allFieldsSet.addAll(ComponentAccessor.getFieldManager().getOrderableFields());
        try {
            allFieldsSet.addAll(ComponentAccessor.getFieldManager().getAllAvailableNavigableFields());
        } catch (FieldException e) {
            log.error("Unable to load navigable fields", e);
        }
        return new ArrayList<Field>(allFieldsSet);
    }
    
   
    /**
     * Get base URL path.
     */
    public String getBaseUrl()
    {
        return applicationProperties.getBaseUrl();
    }
    /**
     * Get project key
     * @return
     */
    public String getProject()
    {
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(request.getParameter("pkey"));
        return project.getKey();
    }

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
    
    public List<String> getSavedIssueTypes()
    {
        return savedIssueTypes;
    }

    
    public String[] getSelectedIssueTypes()
    {
        return selectedIssueTypes;
    }
  
    
    public List<String> getSavedIssueCustomFields()
    {
        return savedIssueCustomFields;
    }

    public String[] getSelectedIssueCustomFields()
    {
        return selectedIssueCustomFields;
    }
    
    public List<String> getSavedDefaultIssueFields()
    {
        return savedDefaultIssueFields;
    }

    public String[] getSelectedDefaultIssueFields()
    {
        return selectedDefaultIssueFields;
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
    
    public void setProject(String project)
    {
        this.project = project;
    }

    public void setIssueType(String issueType)
    {
        this.issueType = issueType;
    }

    public void setSaved(boolean isSaved)
    {
        this.isSaved = isSaved;
    }

    public void setSavedGroups(List<String> savedGroups)
    {
        this.savedGroups = savedGroups;
    }
    public void setSelectedGroups(String[] selectedGroups)
    {
        this.selectedGroups = selectedGroups;
    }
    
    public void setSavedProjects(List<String> savedProjects)
    {
        this.savedProjects= savedProjects;
    }

    public void setSelectedProjects(String[] selectedProjects)
    {
        this.selectedProjects = selectedProjects;
    }
    
    public void setSavedIssueTypes(List<String> savedIssueTypes)
    {
        this.savedIssueTypes= savedIssueTypes;
    }

    public void setSelectedIssueTypes(String[] selectedIssueTypes)
    {
        this.selectedIssueTypes = selectedIssueTypes;
    }
    
    
    public void setSavedDefaultIssueFields(List<String> savedDefaultIssueFields)
    {
        this.savedDefaultIssueFields = savedDefaultIssueFields;
    }

    public void setSelectedDefaultIssueFields(String[] selectedDefaultIssueFields)
    {
        this.selectedDefaultIssueFields = selectedDefaultIssueFields;
    }
    
    
    public void setSavedIssueCustomeFields(List<String> savedIssueCustomFields)
    {
        this.savedIssueCustomFields = savedIssueCustomFields;
    }

    public void setSelectedIssueCustomFields(String[] selectedIssueCustomFields)
    {
        this.selectedIssueCustomFields = selectedIssueCustomFields;
    }

}
