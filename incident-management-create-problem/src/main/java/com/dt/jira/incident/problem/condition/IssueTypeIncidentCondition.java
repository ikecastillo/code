package com.dt.jira.incident.problem.condition;
import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.jira.incident.problem.constants.CreateProblemConstants;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;

/**
 * IssueTypeIncidentCondition.java class for enabling Create Problem link within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IssueTypeIncidentCondition extends AbstractWebCondition {
	private boolean isRoleExist = false;
	/**
    * Checks the Web Item permission to enables the Create Problem link within Incident Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Create Problem link within Incident Management Project.
    */

	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
		{
			final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");
			ProjectRoleManager prm = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);

			String IssueTypeName = currentIssue.getIssueTypeObject().getName();

			PermissionManager permissionManager = ComponentAccessor.getPermissionManager();
			//hasPermission(int permissionsId, Issue issue, ApplicationUser user)
			boolean userCanEditIssueAndProjectIsITIM = permissionManager.hasPermission(Permissions.EDIT_ISSUE,currentIssue, user) && jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM");

			if(( IssueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) ||  IssueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE) ) &&
					(prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_NOC), currentIssue.getProjectObject()) ||
							prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_INCIDENT_MANAGER), currentIssue.getProjectObject())||
							prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_ADMINISTRATORS), currentIssue.getProjectObject())) ||
					userCanEditIssueAndProjectIsITIM){
				setRoleExist(true);
			}else{
				setRoleExist(false);
			}
			return isRoleExist();
		}
	}
	/*@Override
	public boolean shouldDisplay(User user, JiraHelper jiraHelper) {
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");	 
		 ProjectRoleManager prm = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);	
		 
		 String IssueTypeName = currentIssue.getIssueTypeObject().getName();

		PermissionManager permissionManager = ComponentAccessor.getPermissionManager();
		//hasPermission(int permissionsId, Issue issue, ApplicationUser user)
		boolean userCanEditIssueAndProjectIsITIM = permissionManager.hasPermission(Permissions.EDIT_ISSUE,currentIssue, user) && jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM");
		 		 
		 if(( IssueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_INCIDENT) ||  IssueTypeName.equalsIgnoreCase(CreateProblemConstants.ISSUE_TYPE_OUTAGE) ) &&
				 (prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_NOC), currentIssue.getProjectObject()) ||
		 prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_INCIDENT_MANAGER), currentIssue.getProjectObject())||
		 prm.isUserInProjectRole(user, prm.getProjectRole(CreateProblemConstants.ROLE_ADMINISTRATORS), currentIssue.getProjectObject())) ||
		 userCanEditIssueAndProjectIsITIM){
			  setRoleExist(true);		  
		 }else{
			  setRoleExist(false);
		 }		 
		 return isRoleExist();
	}*/
		
	public boolean isRoleExist() {
		return isRoleExist;
	}

	public void setRoleExist(boolean isRoleExist) {
		this.isRoleExist = isRoleExist;
	}


}
