package com.dt.remote.pisdtktcreator.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.remote.pisdtktcreator.constants.CreatePISDConstants;
/**
 * IssueTypeIncidentCondition.java class for enabling Create Problem link within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */

public class IssueTypeIncidentCondition extends AbstractJiraCondition {
	private boolean isRoleExist = false;
	/**
    * Checks the Web Item permission to enables the Create Problem link within Incident Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Create Problem link within Incident Management Project.
    */
	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");	 
			
		 
		 String IssueTypeName = currentIssue.getIssueTypeObject().getName();
		 		 
		 if( IssueTypeName.equalsIgnoreCase(CreatePISDConstants.ISSUE_TYPE_INCIDENT) ||  IssueTypeName.equalsIgnoreCase(CreatePISDConstants.ISSUE_TYPE_OUTAGE) ){
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
