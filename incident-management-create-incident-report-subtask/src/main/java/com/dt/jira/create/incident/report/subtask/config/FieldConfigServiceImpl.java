package com.dt.jira.create.incident.report.subtask.config;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class FieldConfigServiceImpl implements FieldConfigService {
    
    private final static String PROJECT_KEY 			= "PROJECT_KEY";
    private final static String PROJECTS_KEY 			= "PROJECTS_KEY";
    private final static String ISSUE_TYPES_KEY			= "ISSUE_TYPES_KEY";
    private final static String ISSUE_TYPE_KEY 			= "ISSUE_TYPE_KEY";
    private final static String DEFAULT_ISSUE_FIELD 	= "DEFAULT_ISSUE_FIELD";
    private final static String ISSUE_CUSTOME_FIELD 	= "ISSUE_CUSTOME_FIELD"; 

    private final String PLUGIN_KEY = "fieldConfig";
    private final PluginSettingsFactory pluginSettingsFactory;
    
    public FieldConfigServiceImpl(PluginSettingsFactory pluginSettingsFactory)
    {
        this.pluginSettingsFactory = pluginSettingsFactory;
    }

    @Override
    public String getProject()
    {
        return getStringProperty(PROJECT_KEY);
    }

    @Override
    public String getIssyeType()
    {
        return getStringProperty(ISSUE_TYPE_KEY);
    }

   

    private String getStringProperty(String key)
    {
        return (String) pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY).get(key);
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
    public String[] getIssueTypes()
    {
        String isssueTypes = getStringProperty(ISSUE_TYPES_KEY);
        if (isssueTypes != null)
        {
            return isssueTypes.split("&");
        }

        return null;
    }
    
    @Override
    public String[] getDefaultIssueFields()
    {
        String defaultIssueFields = getStringProperty(DEFAULT_ISSUE_FIELD);
        if (defaultIssueFields != null)
        {
            return defaultIssueFields.split("&");
        }

        return null;
    }
    
    @Override
    public String[] getIssueCustomFields()
    {
        String issueCustomFields = getStringProperty(ISSUE_CUSTOME_FIELD);
        if (issueCustomFields != null)
        {
            return issueCustomFields.split("&");
        }

        return null;
    }

 

    @Override
    public void setProject(String project)
    {
        setStringProperty(PROJECT_KEY, project);
    }

    @Override
    public void setIssueType(String issueType)
    {
        setStringProperty(ISSUE_TYPE_KEY, issueType);
    }


    private void setStringProperty(String key, String value)
    {
        pluginSettingsFactory.createSettingsForKey(PLUGIN_KEY).put(key, value);
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
    public void setIssueTypes(String[] issueTypes)
    {
        if (issueTypes != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String issueType : issueTypes)
            {
                sb.append(issueType).append("&");
            }
            setStringProperty(ISSUE_TYPES_KEY, sb.toString());
        }
    }
    
    @Override
    public void setDefaultIssueFields(String[] defaultCustomFileds)
    {
        if (defaultCustomFileds != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String defaultCustomFiled : defaultCustomFileds)
            {
                sb.append(defaultCustomFiled).append("&");
            }
            setStringProperty(DEFAULT_ISSUE_FIELD, sb.toString());
        }
    }
    
    
    @Override
    public void setIssueCustomFields(String[] issueCustomFields)
    {
        if (issueCustomFields != null)
        {
            StringBuilder sb = new StringBuilder(50);
            for (String issueCustomField : issueCustomFields)
            {
                sb.append(issueCustomField).append("&");
            }
            setStringProperty(ISSUE_CUSTOME_FIELD, sb.toString());
        }
    }
}
