package com.dt.jira.problem.rcasubtask.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.dt.jira.problem.rcasubtask.constants.CreateRCASubtaskConstants;

public class IssueTypeProblemCondition extends AbstractJiraCondition {
private boolean isRoleExist = false;

		/**
	    * Checks the Web Item permission to enables the Create Problem RCA Subtask link within Incident Management Project.
	    * @param user the User
	    * @param jiraHelper the JiraHelper
	    * @return true if the issue type Incident and enables Create Problem RCA Subtask link within Incident Management Project.
	    */
		@Override
		public boolean shouldDisplay(User user, JiraHelper jiraHelper) {
			 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");
			 String IssueTypeName = currentIssue.getIssueTypeObject().getName();
			 ProjectRoleManager prm = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);
			 if(IssueTypeName.equalsIgnoreCase(CreateRCASubtaskConstants.ISSUE_TYPE_PROBLEM) && (prm.isUserInProjectRole(user, prm.getProjectRole(CreateRCASubtaskConstants.ROLE_NOC), currentIssue.getProjectObject()) ||
					 prm.isUserInProjectRole(user, prm.getProjectRole(CreateRCASubtaskConstants.ROLE_INCIDENT_MANAGER), currentIssue.getProjectObject())||
					 prm.isUserInProjectRole(user, prm.getProjectRole(CreateRCASubtaskConstants.ROLE_ADMINISTRATORS), currentIssue.getProjectObject()))){
			  setRoleExist(true);			  
		 }else{
			  setRoleExist(false);
		 }		 
		 return isRoleExist();
		}
		
		public boolean isRoleExist() {
		return isRoleExist;
	}

	public void setRoleExist(boolean isRoleExist) {
		this.isRoleExist = isRoleExist;
	}
}
