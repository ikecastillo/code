package com.dt.jira.xmatters.intgt.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

/*
   This class defined to apply the security on "Create xMatters Event" button on issue page
**/
public class IssueTypeCondition extends AbstractJiraCondition {

	@Override
	public boolean shouldDisplay(ApplicationUser arg0, JiraHelper jiraHelper) {
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");	
		 boolean display = false;
			if(currentIssue!=null){
				String IssueTypeName = currentIssue.getIssueTypeObject().getName();		 
				String statusName = currentIssue.getStatusObject().getName(); 
				if((IssueTypeName.equalsIgnoreCase("Incident") || IssueTypeName.equalsIgnoreCase("Outage")) && !statusName.equalsIgnoreCase("Assigned") ){
					display = true;
				}else{
					display = false;
				}
			}
		 return display;
	}
}
