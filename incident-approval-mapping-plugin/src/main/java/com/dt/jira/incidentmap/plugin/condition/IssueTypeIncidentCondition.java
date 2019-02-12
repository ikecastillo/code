package com.dt.jira.incidentmap.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.user.ApplicationUser;

/**
 * IssueType Incident Condition for enabling Incident Report Assignee Mapping link within Incident Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */
public class IssueTypeIncidentCondition extends AbstractWebCondition {

	/* *
    * Checks the Web Item permission to enables the Create Problem link within Incident Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Create Problem link within Incident Management Project.
    */
	/*@Override
	public boolean shouldDisplay(User user, JiraHelper jiraHelper) {
			 if(jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM")){
				 return true;
			 }else{
				 return false;
			 }
		 
	}*/

	@Override
	public boolean shouldDisplay(ApplicationUser applicationUser, JiraHelper jiraHelper) {
		if(jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM")){
			return true;
		}else{
			return false;
		}
	}
}
