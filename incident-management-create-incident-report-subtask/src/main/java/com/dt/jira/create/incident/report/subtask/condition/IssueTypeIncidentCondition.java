package com.dt.jira.create.incident.report.subtask.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;

/**
 * IssueTypeIncidentCondition.java class for enabling Create Incident Report Subtask link within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IssueTypeIncidentCondition extends AbstractWebCondition {
	private boolean isRoleExist = false;
	/**
    * Checks the Web Item permission to enables the Create Incident Report Subtask link within Incident Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Create Incident Report Subtask link within Incident Management Project.
    */
	
	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");	 
		 ProjectRoleManager prm = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);	
		
		 String IssueTypeName = currentIssue.getIssueTypeObject().getName();

		 PermissionManager permissionManager = ComponentAccessor.getPermissionManager();
		 //hasPermission(int permissionsId, Issue issue, ApplicationUser user)
		 boolean userCanEditIssueAndProjectIsITIM = permissionManager.hasPermission(Permissions.EDIT_ISSUE,currentIssue, user) && jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM");
		
		 if((IssueTypeName.equalsIgnoreCase("Incident") || IssueTypeName.equalsIgnoreCase("Outage") ) &&
				 (prm.isUserInProjectRole(user, prm.getProjectRole("NOC"), currentIssue.getProjectObject()) ||
		 prm.isUserInProjectRole(user, prm.getProjectRole("Incident Manager"), currentIssue.getProjectObject())||
		 prm.isUserInProjectRole(user, prm.getProjectRole("Administrators"), currentIssue.getProjectObject())) ||
		 userCanEditIssueAndProjectIsITIM){
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
