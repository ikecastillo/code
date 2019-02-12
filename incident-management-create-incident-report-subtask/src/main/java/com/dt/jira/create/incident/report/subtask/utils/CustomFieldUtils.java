package com.dt.jira.create.incident.report.subtask.utils;

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

    public static final String SPLITTER = "@@";

    private final WorkflowActionsBean workflowActionsBean = new WorkflowActionsBean();
    private final Logger log = LoggerFactory.getLogger(IssueFieldUtils.class);

    private final FieldManager fieldManager;
    private final IssueManager issueManager;
    private final ProjectComponentManager projectComponentManager;
    private final VersionManager versionManager;
    private final IssueSecurityLevelManager issueSecurityLevelManager;
    private final ApplicationProperties applicationProperties;
    private final FieldCollectionsUtils fieldCollectionsUtils;
    private final IssueLinkManager issueLinkManager;
    private final UserManager userManager;
    private final CrowdService crowdService;
    private final OptionsManager optionsManager;
    private final ProjectManager projectManager;
    private final PriorityManager priorityManager;
    private final LabelManager labelManager;
    private final ProjectRoleManager projectRoleManager;
    private final WatcherManager watcherManager;

    public CustomFieldUtils(
            FieldManager fieldManager, IssueManager issueManager,
            ProjectComponentManager projectComponentManager, VersionManager versionManager,
            IssueSecurityLevelManager issueSecurityLevelManager, ApplicationProperties applicationProperties,
            FieldCollectionsUtils fieldCollectionsUtils, IssueLinkManager issueLinkManager,
            UserManager userManager, CrowdService crowdService, OptionsManager optionsManager,
            ProjectManager projectManager, PriorityManager priorityManager, LabelManager labelManager,
            ProjectRoleManager projectRoleManager, WatcherManager watcherManager) {
        this.fieldManager = fieldManager;
        this.issueManager = issueManager;
        this.projectComponentManager = projectComponentManager;
        this.versionManager = versionManager;
        this.issueSecurityLevelManager = issueSecurityLevelManager;
        this.applicationProperties = applicationProperties;
        this.fieldCollectionsUtils = fieldCollectionsUtils;
        this.issueLinkManager = issueLinkManager;
        this.userManager = userManager;
        this.crowdService = crowdService;
        this.optionsManager = optionsManager;
        this.projectManager = projectManager;
        this.priorityManager = priorityManager;
        this.labelManager = labelManager;
        this.projectRoleManager = projectRoleManager;
        this.watcherManager = watcherManager;
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
