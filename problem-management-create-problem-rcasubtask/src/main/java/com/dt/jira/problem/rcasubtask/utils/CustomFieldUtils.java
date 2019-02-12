package com.dt.jira.problem.rcasubtask.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.crowd.embedded.api.CrowdService;
import com.atlassian.jira.bc.project.component.ProjectComponentManager;
import com.atlassian.jira.config.PriorityManager;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.label.LabelManager;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.security.IssueSecurityLevelManager;
import com.atlassian.jira.issue.watchers.WatcherManager;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.workflow.WorkflowActionsBean;

public class CustomFieldUtils {

      
    private final Logger log = LoggerFactory.getLogger(CustomFieldUtils.class);

    private final FieldManager fieldManager;
   

    public CustomFieldUtils(FieldManager fieldManager) {
        this.fieldManager = fieldManager;
      
    }
    
    public Field getFieldFromKey(String key) {
        Field field;

        if (fieldManager.isCustomField(key)) {
            field = fieldManager.getCustomField(key);
        } else {
            field = fieldManager.getField(key);
        }

        if (field == null) {
            throw new IllegalArgumentException("Unable to find field '" + key + "'");
        }

        return field;
    }

}
