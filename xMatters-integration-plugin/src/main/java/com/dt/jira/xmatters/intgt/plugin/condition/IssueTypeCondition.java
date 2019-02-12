package com.dt.jira.xmatters.intgt.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;

public class IssueTypeCondition extends AbstractJiraCondition {

	@Override
	public boolean shouldDisplay(User arg0, JiraHelper jiraHelper) {
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");		
		 String IssueTypeName = currentIssue.getIssueTypeObject().getName();		 
		  String statusName = currentIssue.getStatusObject().getName(); 
		 if(IssueTypeName.equalsIgnoreCase("Incident") && !statusName.equalsIgnoreCase("Assigned") ){
		     return true;
		 }else{
			 return false;
		 }
	}
}
