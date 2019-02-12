package com.dt.jira.problem.rcasubtask.action;

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
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.problem.rcasubtask.config.FieldConfigService;

public class ProblemRCAFieldMappingAction extends JiraWebActionSupport{
	
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
    
    public ProblemRCAFieldMappingAction(
        ApplicationProperties applicationProperties,
        FieldConfigService fieldConfigService,
        GroupManager groupManager)
    {
        this.applicationProperties = applicationProperties;
        this.fieldConfigService = fieldConfigService;
        this.groupManager = groupManager;

        // initialize params from saved properties
        userNames = fieldConfigService.getAdminUserStr();  
        project= fieldConfigService.getProject();
        
        selectedDefaultIssueFields = fieldConfigService.getDefaultIssueFields();     
        savedDefaultIssueFields = selectedDefaultIssueFields == null ? null : Arrays.asList(fieldConfigService.getDefaultIssueFields());
        
        selectedIssueCustomFields = fieldConfigService.getIssueCustomFields();
        savedIssueCustomFields = selectedIssueCustomFields == null ? null : Arrays.asList(fieldConfigService.getIssueCustomFields());
        
        selectedGroups = fieldConfigService.getWorkGroups();
        savedGroups = selectedGroups == null ? null : Arrays.asList(fieldConfigService.getWorkGroups());
       
        selectedProjects = fieldConfigService.getProjects();
        savedProjects = selectedProjects == null ? null : Arrays.asList(fieldConfigService.getProjects());
        
        selectedIssueTypes = fieldConfigService.getIssueTypes();
        savedIssueTypes = selectedIssueTypes == null ? null : Arrays.asList(fieldConfigService.getIssueTypes());
  
        
    }
    
    @Override
    protected String doExecute()
    throws Exception
    {
    	fieldConfigService.setProject(project);
    	//fieldConfigService.setIssueType(issueType);
        fieldConfigService.setAdminUserStr(userNames);
        
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
        
        fieldConfigService.setWorkGroups(selectedGroups);
        if (selectedGroups != null)
        {
            savedGroups = Arrays.asList(fieldConfigService.getWorkGroups());
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

        return getRedirect("ProblemRCAFieldMappingAction!default.jspa?saved=true");
    }

    @Override
    protected void doValidation()
    {
        super.doValidation();
        
//        if (project.isEmpty())
//        {
//            addErrorMessage("generic.events.admin.error.project");
//            return;
//        }
//        
//        if (issueTypes.isEmpty())
//        {
//            addErrorMessage("generic.events.admin.error.issueType");
//            return;
//        }
       

        if (userNames.isEmpty())
        {
            addErrorMessage("generic.events.admin.error.pgadmin");
            return;
        }
    }

    /**
     * Get all Jira groups.
     */
    public Collection<Group> getAllGroups()
    {
        return groupManager.getAllGroups();
    }
    
    
    /**
     * Get all Jira groups.
     */
    public String getProjectKey(){
    	
        return projectKey;
    }
    /**
     * Get all Jira groups.
     */
    public Collection<Project> getAllProjects()
    {
        return ComponentAccessor.getProjectManager().getProjectObjects();
    }
    /**
     * Get all Jira groups.
     */
    public Collection<IssueType> getAllProjectIssueTypes()
    {
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(request.getParameter("pkey"));
    	return project.getIssueTypes();
    }
    
    /**
     * Get all Jira groups.
     */
    public Collection<IssueType> getAllIssueTypes()
    {
    	return  ComponentAccessor.getConstantsManager().getAllIssueTypeObjects();
    }
    
    /**
     * Get all Jira Custom Fields.
     * @throws FieldException 
     */
   /* public Collection<NavigableField> getAllDefaultFields() throws FieldException
    {
         try {
 			    Set<NavigableField> navField=ComponentAccessor.getFieldManager().getAllAvailableNavigableFields();
 			   
 		        List<NavigableField> list = new ArrayList<NavigableField>(navField);
 		        for (int i = 0; i < list.size(); i++) {
 		        	NavigableField o = list.get(i);
 		            log.info("Custom Field Id = " + o.getId() +"  Custom Field Name = " + o.getName() );
 		        }

 		} catch (FieldException e) {
 			e.printStackTrace();
 		}    	
        return  ComponentAccessor.getFieldManager().getAllAvailableNavigableFields();
    }*/
    
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
     * Get all Jira groups.
     */
   /* public Collection<NavigableField> getAllIssueFields() throws FieldException
    {       
        return ComponentAccessor.getFieldManager().getAllAvailableNavigableFields();
    }*

    
    public List<Field> getAllIssueFields() {
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
     * Get context path.
     */
    public String getBaseUrl()
    {
        return applicationProperties.getBaseUrl();
    }

    public String getProject()
    {
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(request.getParameter("pkey"));
        return project.getKey();
    }

    public String getIssueType()
    {
        return issueType;
    }

    public List<String> getSavedGroups()
    {
        return savedGroups;
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
    
    public String[] getSelectedGroups()
    {
        return selectedGroups;
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

    public String getUserNames()
    {
        return userNames;
    }

    public boolean hasAdminPermission()
    {
        User user = getLoggedInUser();
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

    public void setUserNames(String userNames)
    {
        this.userNames = userNames;
    }
}
