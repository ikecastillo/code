package com.dt.jira.pagerduty.intgt.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

/**
 * Condition class to check that the project key is ITIM
 */
public class ProjectITIMCondition extends AbstractJiraCondition {

	/**
    * Checks the Web Item permission to enables the Create Problem link within Incident Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Incident and enables Create Problem link within Incident Management Project.
    */
	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
			 if(jiraHelper.getProjectObject().getKey().equalsIgnoreCase("ITIM")){
				 return true;
			 }else{
				 return false;
			 }
		 
	}
}
