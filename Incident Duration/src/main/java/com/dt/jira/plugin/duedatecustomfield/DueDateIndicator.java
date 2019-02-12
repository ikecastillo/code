package com.dt.jira.plugin.duedatecustomfield;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.fields.CustomField;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

 
public class DueDateIndicator extends AbstractJiraContextProvider
{
	Logger log = LoggerFactory.getLogger(DueDateIndicator.class);

	private static final int MILLIS_IN_DAY = 24 * 60 * 60 * 1000;

	@Override
	public Map getContextMap(User user, JiraHelper jiraHelper) {
		Map contextMap = new HashMap();
		Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");
		
		CustomFieldManager customFieldManager = ComponentAccessor
				.getCustomFieldManager();
		
		//Name of the custom field --- Incident Duration
		CustomField mstrIncidentField = customFieldManager
				.getCustomFieldObjectByName("Incident Duration");

		log.info("!!!!!!!!!!!!!!!!1"
				+ currentIssue.getCustomFieldValue(mstrIncidentField));

		contextMap.put("daysAwayFromDueDate",
				currentIssue.getCustomFieldValue(mstrIncidentField));

		return contextMap;
    }
}