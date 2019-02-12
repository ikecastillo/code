package com.dt.jira.table.furtherAction;

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

/**
 * 
 * This customField type is used to configure FurtherAction incident
 *
 */
public class FurtherActionTable extends GenericTextCFType {
	public FurtherActionTable(
			CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister, genericConfigManager);
	}

	@Nonnull
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
	}

	public String getDefaultValue(FieldConfig fieldConfig) {
		if (super.getDefaultValue(fieldConfig) == null) {
			setDefaultValue(fieldConfig, "<html><table border=1><thead><tr><th>Date</th><th>Description</th><th>Index</th></tr></thead><tbody></tbody></table></html>");
		}
		return (String) super.getDefaultValue(fieldConfig);
	}

	public void createValue(CustomField field, Issue issue,
			@Nonnull String value) {
		 value = "<Html>"+value+"</Html>";
		this.customFieldValuePersister
				.createValues(
						field,
						issue.getId(),
						getDatabaseType(),
						Lists.newArrayList(new Object[] { getDbValueFromObject(value) }));

	}

	public void updateValue(CustomField customField, Issue issue, String value) {

		
		this.customFieldValuePersister
				.updateValues(
						customField,
						issue.getId(),
						getDatabaseType(),
						Lists.newArrayList(new Object[] { getDbValueFromObject(value) }));

	}

	public static class FurtherActionTableConfig {

		private List<FurtherAction> data = null;

		public List<FurtherAction> getData() {
			return data;
		}

		public void setData(List<FurtherAction> data) {
			this.data = data;
		}
	}

	public static class FurtherAction {
		private String date = "";
		private String desc = "";
		
		private String index = "";

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		

	}

}
