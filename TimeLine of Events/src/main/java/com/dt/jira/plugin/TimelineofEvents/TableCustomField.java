package com.dt.jira.plugin.TimelineofEvents;

import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import javax.annotation.Nonnull;

import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.config.FieldConfigItemType;
import com.atlassian.jira.util.ErrorCollection;
import com.atlassian.jira.util.ErrorCollection.Reason;
import com.atlassian.jira.issue.customfields.view.CustomFieldParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutManager;
import com.atlassian.jira.issue.fields.layout.field.FieldLayout;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.issue.MutableIssue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/**
 * 
 * This customField type is used to configure Timeline of Events for incident
 *
 */
public class TableCustomField extends GenericTextCFType {
	String defaultValue = "<table border=1><thead><tr><th bgcolor=#D0CECE>Date</th><th bgcolor=#D0CECE>Time</th><th bgcolor=#D0CECE>Description</th><th bgcolor=#D0CECE style='display:none;width:0px'>Index</th></tr></thead><tbody></tbody></table>";


	private final Logger log = LoggerFactory.getLogger(TableCustomField.class);

	public TableCustomField(
			CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister, genericConfigManager);
	}

	@Nonnull
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
	}

	//insert value in the database
	public void createValue(CustomField field, Issue issue,
			@Nonnull String value) {

		if (value.equals("{\"data\": []}")) {
			value = defaultValue;
		}

		log.debug("Value is " + value);
		/*value = "<Html>" + value + "</Html>";*/
		System.out.println("************************* create Timeline of events****************");
		this.customFieldValuePersister
				.createValues(
						field,
						issue.getId(),
						getDatabaseType(),
						Lists.newArrayList(new Object[] { getDbValueFromObject(value) }));

	}

	//Update value in the database
	public void updateValue(CustomField customField, Issue issue, String value) {
		if(value.equals("{\"data\": []}")){
			value = defaultValue;
		}
		System.out.println("******************** update Timeline of events****************");
		this.customFieldValuePersister
				.updateValues(
						customField,
						issue.getId(),
						getDatabaseType(),
						Lists.newArrayList(new Object[] { getDbValueFromObject(value) }));

	}
}
