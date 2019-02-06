package com.dt.jira.assigneemap.plugins.condition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;

/**
 * IssueTypeChangeCondition.java class for enabling Assignee/Status Mapping link within Change Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IssueTypeChangeCondition extends AbstractWebCondition {

	/**
    * Checks the Web Item permission to enables the Assignee/Status Mapping link within Change Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Assignee/Status Mapping link within Change Management Project.
    */
	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
		 if(jiraHelper.getProject().getKey().equalsIgnoreCase("CHG")){
				 return true;
			 }else{
				 return false;
			 }
		 
	}
}
