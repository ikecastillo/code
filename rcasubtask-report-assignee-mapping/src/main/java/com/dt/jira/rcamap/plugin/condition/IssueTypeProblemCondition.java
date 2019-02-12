package com.dt.jira.rcamap.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;

/**
 * IssueType Problem Condition for enabling RCA Report Subtask Assignee Mapping link within Problem Management Project
 * 
 * @author Firoz Khan
 * @version 1.0.0
 */
public class IssueTypeProblemCondition extends AbstractWebCondition {

	/**
    * Checks the Web Item permission to enables the Create RCA Report Subtask link within Problem Management Project.
    * @param user the User
    * @param jiraHelper the JiraHelper
    * @return true if the issue type Problem and enables Create RCA Report Subtask link within Problem Management Project.
    */
	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
			 if(jiraHelper.getProject().getKey().equalsIgnoreCase("PRB")){
				 return true;
			 }else{
				 return false;
			 }
		 
	}
}
