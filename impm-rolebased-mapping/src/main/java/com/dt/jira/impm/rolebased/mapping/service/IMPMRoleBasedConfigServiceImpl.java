package com.dt.jira.impm.rolebased.mapping.service;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
/**
 * This class is used for Project Role Based Configuration Service Implementation.
 * 
 * @author Firoz.Khan
 */
public class IMPMRoleBasedConfigServiceImpl implements IMPMRoleBasedConfigService {
	 private final static String PROJECT_KEY 			= "PROJECT_KEY";
    private final static String PROJECTS_KEY 			= "PROJECTS_KEY";
    private final static String ISSUE_TYPE_KEY 			= "ISSUE_TYPE_KEY";
    private final static String SOLUTION_GROUP_KEY		= "SOLUTION_GROUP_KEY";
    private final static String PROJECT_ROLE_KEY 		= "PROJECT_ROLE_KEY";
    private final static String GROUPS_KEY 				= "GROUPS_KEY";
    private final String PLUGIN_KEY = "ProjectRoleBasedConfig";
    private final PluginSettingsFactory pluginSettingsFactory;
    
    public IMPMRoleBasedConfigServiceImpl(PluginSettingsFactory pluginSettingsFactory)
    {
        this.pluginSettingsFactory = pluginSettingsFactory;
    }

    private String getStringProperty(String key)
    {
        return (String) pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY).get(key);
    }

    private void setStringProperty(String key, String value)
    {
        pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY).put(key, value);
    }
    
    @Override
    public String getProject()
    {
        return getStringProperty(PROJECT_KEY);
    }
    
    @Override
    public void setProject(String project)
    {
        setStringProperty(PROJECT_KEY, project);
    }
    
    @Override
    public String[] getProjects()
    {
        String projects = getStringProperty(PROJECTS_KEY);
        if (projects != null)
        {
            return projects.split("&");
        }

        return null;
    }
    
    @Override
    public void setProjects(String[] projects)
    {
        if (projects != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String project : projects)
            {
                sb.append(project).append("&");
            }
            setStringProperty(PROJECTS_KEY, sb.toString());
        }
    }
    
    @Override
    public String[] getIssueTypes()
    {
        String issueTypes = getStringProperty(ISSUE_TYPE_KEY);
        if (issueTypes != null)
        {
            return issueTypes.split("&");
        }

        return null;
    }
    
    @Override
    public void setIssueTypes(String[] issueTypes)
    {
        if (issueTypes != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String issueType : issueTypes)
            {
                sb.append(issueType).append("&");
            }
            setStringProperty(ISSUE_TYPE_KEY, sb.toString());
        }
    }
    
    
    @Override
    public String[] getSolutionGroups()
    {
        String solutionGroups = getStringProperty(SOLUTION_GROUP_KEY);
        if (solutionGroups != null)
        {
            return solutionGroups.split("&");
        }

        return null;
    }
    
    @Override
    public void setSolutionGroups(String[] solutionGroups)
    {
        if (solutionGroups != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String issueType : solutionGroups)
            {
                sb.append(issueType).append("&");
            }
            setStringProperty(SOLUTION_GROUP_KEY, sb.toString());
        }
    }
    
    @Override
    public String[] getProjectRoles()
    {
        String defaultIssueFields = getStringProperty(PROJECT_ROLE_KEY);
        if (defaultIssueFields != null)
        {
            return defaultIssueFields.split("&");
        }

        return null;
    }
    
    @Override
    public void setProjectRoles(String[] projectRoles)
    {
        if (projectRoles != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String defaultCustomFiled : projectRoles)
            {
                sb.append(defaultCustomFiled).append("&");
            }
            setStringProperty(PROJECT_ROLE_KEY, sb.toString());
        }
    }
    
    @Override
    public String[] getWorkGroups()
    {
        String groups = getStringProperty(GROUPS_KEY);
        if (groups != null)
        {
            return groups.split("&");
        }

        return null;
    }
    
    @Override
    public void setWorkGroups(String[] workGroups)
    {
        if (workGroups != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String workGroup : workGroups)
            {
                sb.append(workGroup).append("&");
            }
            setStringProperty(GROUPS_KEY, sb.toString());
        }
    }
}
