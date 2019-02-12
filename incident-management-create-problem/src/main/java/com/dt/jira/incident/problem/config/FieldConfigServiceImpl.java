package com.dt.jira.incident.problem.config;

import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

public class FieldConfigServiceImpl implements FieldConfigService {
    
    private final static String PROJECT_KEY 			= "PROJECT_KEY";
    private final static String PROJECTS_KEY 			= "PROJECTS_KEY";
    private final static String ISSUE_TYPES_KEY			= "ISSUE_TYPES_KEY";
    private final static String ISSUE_TYPE_KEY 			= "ISSUE_TYPE_KEY";
    private final static String DEFAULT_ISSUE_FIELD 	= "DEFAULT_ISSUE_FIELD";
    private final static String ISSUE_CUSTOME_FIELD 	= "ISSUE_CUSTOME_FIELD";    
    private final static String GROUPS_KEY 				= "GROUPS_KEY";
    private final static String USER_ADMIN_KEY 			= "USER_ADMIN_KEY";

    private final String PLUGIN_KEY = "rcaFieldConfig";
    private final PluginSettingsFactory pluginSettingsFactory;
    
    public FieldConfigServiceImpl(PluginSettingsFactory pluginSettingsFactory)
    {
        this.pluginSettingsFactory = pluginSettingsFactory;
    }

    @Override
    public Set<String> getAdminUsers()
    {
        String adminStr = getStringProperty(USER_ADMIN_KEY);

        Set<String> users = new TreeSet<String>();
        StringTokenizer st = new StringTokenizer(adminStr, ",");
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            users.add(token.trim());
        }

        return users;
    }

    @Override
    public String getAdminUserStr()
    {
        return getStringProperty(USER_ADMIN_KEY);
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
    public void setAdminUsers(Set<String> users)
    {
        StringBuilder sb = new StringBuilder();
        if (users != null)
        {
            for (String user : users)
            {
                sb.append(user).append(", ");
            }
        }

        setStringProperty(USER_ADMIN_KEY, sb.toString());
    }

    @Override
    public void setAdminUserStr(String users)
    {
        setStringProperty(USER_ADMIN_KEY, users);
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
