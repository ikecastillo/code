package com.dt.jira.plugin.duedatecustomfield;

import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;


public class JiraCustomField extends GenericTextCFType {

	public JiraCustomField(CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {

		super(customFieldValuePersister, genericConfigManager);
	}


}

