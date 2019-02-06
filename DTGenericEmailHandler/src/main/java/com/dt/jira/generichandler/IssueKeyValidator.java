package com.dt.jira.generichandler;


import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yagnesh.bhat on 2/12/2016.
 */
public class IssueKeyValidator {

    private final IssueManager issueManager;

    public IssueKeyValidator(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    public Issue validateIssue(String issueKey, MessageHandlerErrorCollector collector) {
        if (StringUtils.isBlank(issueKey)) {
            collector.error("Issue key cannot be undefined.");
            return null;
        }

        final Issue issue = issueManager.getIssueObject(issueKey);
        if (issue == null) {
            collector.error(issueKey + " - This issue does not exist.");
            return null;
        }
        if (!issueManager.isEditable(issue)) {
            collector.error(issueKey + " - This issue is not editable.");
            return null;
        }
        return issue;
    }
}
