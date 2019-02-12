package com.dt.jira.incident.problem.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.ofbiz.core.entity.GenericEntityException;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.crowd.embedded.api.CrowdService;
import com.atlassian.jira.bc.project.component.ProjectComponentManager;
import com.atlassian.jira.config.PriorityManager;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.IssueRelationConstants;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.label.LabelManager;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.security.IssueSecurityLevelManager;
import com.atlassian.jira.issue.watchers.WatcherManager;
import com.atlassian.jira.issue.worklog.WorkRatio;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.util.ObjectUtils;
import com.atlassian.jira.workflow.WorkflowActionsBean;

public class ProblemUtils {
	public static final String SPLITTER = "@@";

    private final WorkflowActionsBean workflowActionsBean = new WorkflowActionsBean();
    private final Logger log = LoggerFactory.getLogger(IssueFieldUtils.class);

    private final FieldManager fieldManager;
    private final IssueManager issueManager;
    private final ProjectComponentManager projectComponentManager;
    private final VersionManager versionManager;
    private final IssueSecurityLevelManager issueSecurityLevelManager;
    private final ApplicationProperties applicationProperties;
    private final FieldUtils fieldCollectionsUtils;
    private final IssueLinkManager issueLinkManager;
    private final UserManager userManager;
    private final CrowdService crowdService;
    private final OptionsManager optionsManager;
    private final ProjectManager projectManager;
    private final PriorityManager priorityManager;
    private final LabelManager labelManager;
    private final ProjectRoleManager projectRoleManager;
    private final WatcherManager watcherManager;

    public ProblemUtils(
            FieldManager fieldManager, IssueManager issueManager,
            ProjectComponentManager projectComponentManager, VersionManager versionManager,
            IssueSecurityLevelManager issueSecurityLevelManager, ApplicationProperties applicationProperties,
            FieldUtils fieldCollectionsUtils, IssueLinkManager issueLinkManager,
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

    public Object getFieldValueFromIssue(Issue issue, CustomField field) {
        return getFieldValueFromIssue(issue,field,false);
    }
    public Object getFieldValueFromIssue(Issue issue, CustomField customField, boolean asOption) {
        Object retVal = null;

        try {
        	
            if (fieldManager.isCustomField(customField)) {
                // Return the CustomField value. It could be any object.
                //CustomField customField = (CustomField) field;
                Object value = issue.getCustomFieldValue(customField);
               
                if (customField.getCustomFieldType() instanceof CascadingSelectCFType) {
                    HashMap<String, Option> hashMapEntries = (HashMap<String, Option>) value;

                    if (hashMapEntries != null) {
                        Option parent =  hashMapEntries.get(CascadingSelectCFType.PARENT_KEY);
                        Option child =  hashMapEntries.get(CascadingSelectCFType.CHILD_KEY);

                        if (parent != null) {
                            if (ObjectUtils.isValueSelected(child)) {
                                retVal = asOption?child:child.toString();
                            } else {
                                final List<Option> childOptions = parent.getChildOptions();

                                if ((childOptions == null) || (childOptions.isEmpty())) {
                                    retVal = asOption?parent:parent.toString();
                                }
                            }
                        }
                    }
                } else {
                    retVal = value; 
                }

                if (log.isDebugEnabled()) {
                    log.debug(
                            String.format(
                                    "Got field value [object=%s;class=%s]",
                                    retVal, ((retVal != null) ? retVal.getClass() : "")
                            )
                    );
                }
            } else {
                String fieldId = customField.getId();
                Collection<?> retCollection;

                // Special treatment of fields.
                if (fieldId.equals(IssueFieldConstants.ATTACHMENT)) {
                    // return a collection with the attachments associated to given issue.
                    retCollection = issue.getAttachments();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.AFFECTED_VERSIONS)) {
                    retCollection = issue.getAffectedVersions();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.COMMENT)) {
                    // return a list with the comments of a given issue.
                    try {
                        retCollection = issueManager.getEntitiesByIssueObject(
                                IssueRelationConstants.COMMENTS, issue
                        );

                        if (retCollection != null && !retCollection.isEmpty()) {
                            retVal = retCollection;
                        }
                    } catch (GenericEntityException e) {
                        retVal = null;
                    }
                } else if (fieldId.equals(IssueFieldConstants.COMPONENTS)) {
                    retCollection = issue.getComponentObjects();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.FIX_FOR_VERSIONS)) {
                    retCollection = issue.getFixVersions();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.THUMBNAIL)) {
                    // Not implemented, yet.
                } else if (fieldId.equals(IssueFieldConstants.ISSUE_TYPE)) {
                    retVal = issue.getIssueTypeObject();
                } else if (fieldId.equals(IssueFieldConstants.TIMETRACKING)) {
                    // Not implemented, yet.
                } else if (fieldId.equals(IssueFieldConstants.ISSUE_LINKS)) {
                    retVal = issueLinkManager.getIssueLinks(issue.getId());
                } else if (fieldId.equals(IssueFieldConstants.WORKRATIO)) {
                    retVal = String.valueOf(WorkRatio.getWorkRatio(issue));
                } else if (fieldId.equals(IssueFieldConstants.ISSUE_KEY)) {
                    retVal = issue.getKey();
                } else if (fieldId.equals(IssueFieldConstants.SUBTASKS)) {
                    retCollection = issue.getSubTaskObjects();

                    if (retCollection != null && !retCollection.isEmpty()) {
                        retVal = retCollection;
                    }
                } else if (fieldId.equals(IssueFieldConstants.PRIORITY)) {
                    retVal = issue.getPriorityObject();
                } else if (fieldId.equals(IssueFieldConstants.RESOLUTION)) {
                    retVal = issue.getResolutionObject();
                } else if (fieldId.equals(IssueFieldConstants.STATUS)) {
                    retVal = issue.getStatusObject();
                } else if (fieldId.equals(IssueFieldConstants.PROJECT)) {
                    retVal = issue.getProjectObject();
                } else if (fieldId.equals(IssueFieldConstants.SECURITY)) {
                    retVal = issue.getSecurityLevel();
                } else if (fieldId.equals(IssueFieldConstants.TIME_ESTIMATE)) {
                    retVal = issue.getEstimate();
                } else if (fieldId.equals(IssueFieldConstants.TIME_SPENT)) {
                    retVal = issue.getTimeSpent();
                } else if (fieldId.equals(IssueFieldConstants.AGGREGATE_TIME_SPENT)) {
                    retVal = issue.getTimeSpent();
                } else if (fieldId.equals(IssueFieldConstants.ASSIGNEE)) {
                    retVal = issue.getAssigneeUser();
                } else if (fieldId.equals(IssueFieldConstants.REPORTER)) {
                    retVal = issue.getReporterUser();
                } else if (fieldId.equals(IssueFieldConstants.DESCRIPTION)) {
                    retVal = issue.getDescription();
                } else if (fieldId.equals(IssueFieldConstants.ENVIRONMENT)) {
                    retVal = issue.getEnvironment();
                } else if (fieldId.equals(IssueFieldConstants.SUMMARY)) {
                    retVal = issue.getSummary();
                } else if (fieldId.equals(IssueFieldConstants.DUE_DATE)) {
                    retVal = issue.getDueDate();
                } else if (fieldId.equals(IssueFieldConstants.UPDATED)) {
                    retVal = issue.getUpdated();
                } else if (fieldId.equals(IssueFieldConstants.CREATED)) {
                    retVal = issue.getCreated();
                } else if (fieldId.equals(IssueFieldConstants.RESOLUTION_DATE)) {
                    retVal = issue.getResolutionDate();
                } else if (fieldId.equals(IssueFieldConstants.LABELS)) {
                    retVal = issue.getLabels();
                } else if (fieldId.equals(IssueFieldConstants.WATCHES)) {
                    retVal = watcherManager.getWatchers(issue, Locale.getDefault());
                } else {
                    log.warn("Issue field \"" + fieldId + "\" is not supported.");

                    GenericValue gvIssue = issue.getGenericValue();

                    if (gvIssue != null) {
                        retVal = gvIssue.get(fieldId);
                    }
                }
            }
        } catch (NullPointerException e) {
            retVal = null;

            log.error("Unable to get customField \"" + customField.getId() + "\" value", e);
        }
        
        return retVal;
    }
}
