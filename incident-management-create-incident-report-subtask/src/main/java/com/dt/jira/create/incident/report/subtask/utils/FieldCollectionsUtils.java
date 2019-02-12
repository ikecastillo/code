package com.dt.jira.create.incident.report.subtask.utils;

import static com.atlassian.jira.issue.IssueFieldConstants.AGGREGATE_PROGRESS;
import static com.atlassian.jira.issue.IssueFieldConstants.AGGREGATE_TIME_ESTIMATE;
import static com.atlassian.jira.issue.IssueFieldConstants.AGGREGATE_TIME_ORIGINAL_ESTIMATE;
import static com.atlassian.jira.issue.IssueFieldConstants.ATTACHMENT;
import static com.atlassian.jira.issue.IssueFieldConstants.COMMENT;
import static com.atlassian.jira.issue.IssueFieldConstants.CREATED;
import static com.atlassian.jira.issue.IssueFieldConstants.ISSUE_KEY;
import static com.atlassian.jira.issue.IssueFieldConstants.ISSUE_LINKS;
import static com.atlassian.jira.issue.IssueFieldConstants.ISSUE_TYPE;
import static com.atlassian.jira.issue.IssueFieldConstants.PROGRESS;
import static com.atlassian.jira.issue.IssueFieldConstants.PROJECT;
import static com.atlassian.jira.issue.IssueFieldConstants.STATUS;
import static com.atlassian.jira.issue.IssueFieldConstants.SUBTASKS;
import static com.atlassian.jira.issue.IssueFieldConstants.THUMBNAIL;
import static com.atlassian.jira.issue.IssueFieldConstants.TIMETRACKING;
import static com.atlassian.jira.issue.IssueFieldConstants.TIME_ESTIMATE;
import static com.atlassian.jira.issue.IssueFieldConstants.TIME_ORIGINAL_ESTIMATE;
import static com.atlassian.jira.issue.IssueFieldConstants.TIME_SPENT;
import static com.atlassian.jira.issue.IssueFieldConstants.UPDATED;
import static com.atlassian.jira.issue.IssueFieldConstants.VOTES;
import static com.atlassian.jira.issue.IssueFieldConstants.WORKLOG;
import static com.atlassian.jira.issue.IssueFieldConstants.WORKRATIO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.datetime.DateTimeFormatter;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFieldConstants;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.impl.DateCFType;
import com.atlassian.jira.issue.customfields.impl.DateTimeCFType;
import com.atlassian.jira.issue.customfields.impl.ImportIdLinkCFType;
import com.atlassian.jira.issue.customfields.impl.MultiSelectCFType;
import com.atlassian.jira.issue.customfields.impl.NumberCFType;
import com.atlassian.jira.issue.customfields.impl.ReadOnlyCFType;
import com.atlassian.jira.issue.customfields.impl.RenderableTextCFType;
import com.atlassian.jira.issue.customfields.impl.SelectCFType;
import com.atlassian.jira.issue.customfields.impl.TextAreaCFType;
import com.atlassian.jira.issue.customfields.impl.URLCFType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.issue.fields.FieldException;
import com.atlassian.jira.issue.fields.FieldManager;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayout;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutManager;
import com.atlassian.jira.issue.fields.screen.FieldScreen;
import com.atlassian.jira.issue.fields.screen.FieldScreenLayoutItem;
import com.atlassian.jira.issue.fields.screen.FieldScreenTab;
import com.atlassian.jira.util.I18nHelper;
import com.atlassian.jira.util.I18nHelper.BeanFactory;
import com.atlassian.jira.web.FieldVisibilityManager;

/**
 * This utils class exposes common methods to get field collections.
 *
 * @author Firoz Khan
 */

public class FieldCollectionsUtils {
    private static final Logger log = LoggerFactory.getLogger(FieldCollectionsUtils.class);

    private static final Collection<String> TIME_TRACKING_FIELDS = Arrays.asList(
            IssueFieldConstants.TIME_ESTIMATE,
            IssueFieldConstants.TIME_ORIGINAL_ESTIMATE,
            IssueFieldConstants.TIME_SPENT,
            IssueFieldConstants.TIMETRACKING,
            IssueFieldConstants.WORKLOG
    );

    private final I18nHelper.BeanFactory i18nHelper;
    private final ApplicationProperties applicationProperties;
    private final DateTimeFormatter dateTimeFormatter;
    private final FieldManager fieldManager;
    private final FieldLayoutManager fieldLayoutManager;
    private final CustomFieldManager customFieldManager;
    private final FieldVisibilityManager fieldVisibilityManager;

    public FieldCollectionsUtils(
            BeanFactory i18nHelper, ApplicationProperties applicationProperties,
            DateTimeFormatter dateTimeFormatter, FieldManager fieldManager,
            FieldLayoutManager fieldLayoutManager,
            CustomFieldManager customFieldManager,
            FieldVisibilityManager fieldVisibilityManager
    ) {
        this.i18nHelper = i18nHelper;
        this.applicationProperties = applicationProperties;
        this.dateTimeFormatter = dateTimeFormatter;
        this.fieldManager = fieldManager;
        this.fieldLayoutManager = fieldLayoutManager;
        this.customFieldManager = customFieldManager;
        this.fieldVisibilityManager = fieldVisibilityManager;
    }

    /**
     * @return a complete list of fields, including custom fields.
     */
    public List<Field> getAllFields() {
        Set<Field> allFieldsSet = new TreeSet<Field>(getComparator());

        allFieldsSet.addAll(fieldManager.getOrderableFields());

        try {
            allFieldsSet.addAll(fieldManager.getAllAvailableNavigableFields());
        } catch (FieldException e) {
            log.error("Unable to load navigable fields", e);
        }

        return new ArrayList<Field>(allFieldsSet);
    }

    /**
     * @return a complete list of fields for which theyr value may be cleared with API functionality,
     * actually all without {@link ReadOnlyCFType}.
     */
    public List<Field> getAllClearableFields() {
        List<Field> fields = getAllFields();
        Iterator<Field> it = fields.iterator();
        while(it.hasNext()) {
            Field f = it.next();
            if(f instanceof CustomField) {
                if(((CustomField)f).getCustomFieldType() instanceof ReadOnlyCFType) {
                    it.remove();
                }
            }
        }
        return fields;
    }

    /**
     * @param allFields list of fields to be sorted.
     * @return a list with fields sorted by name.
     */
    public List<Field> sortFields(List<Field> allFields) {
        Collections.sort(allFields, getComparator());

        return allFields;
    }

    /**
     * @return a list of all fields of type date and datetime.
     */
    public List<Field> getAllDateFields() {
        List<Field> allDateFields = new ArrayList<Field>();

        List<CustomField> fields = customFieldManager.getCustomFieldObjects();

        for (CustomField cfDate : fields) {
            CustomFieldType customFieldType = cfDate.getCustomFieldType();

            if ((customFieldType instanceof DateCFType) || (customFieldType instanceof DateTimeCFType)){
                allDateFields.add(cfDate);
            }
        }
        allDateFields.addAll(
                Arrays.asList(
                        fieldManager.getField(IssueFieldConstants.DUE_DATE),
                        fieldManager.getField(IssueFieldConstants.CREATED),
                        fieldManager.getField(IssueFieldConstants.UPDATED),
                        fieldManager.getField(IssueFieldConstants.RESOLUTION_DATE)));

        return sortFields(allDateFields);
    }

    public List<Field> getAllRegexpFields() {
        List<Field> allRegexpFields = new ArrayList<Field>();

        allRegexpFields.addAll(getAllTextFields());
        allRegexpFields.addAll(getAllSelectFields());

        return sortFields(allRegexpFields);
    }

    public List<Field> getAllSelectFields() {
        List<Field> allSelectFields = new ArrayList<Field>();

        List<CustomField> fields = customFieldManager.getCustomFieldObjects();

        for (CustomField cf : fields) {
            CustomFieldType customFieldType = cf.getCustomFieldType();

            if (customFieldType instanceof SelectCFType
                    || customFieldType instanceof MultiSelectCFType){
                allSelectFields.add(cf);
            }
        }

        return sortFields(allSelectFields);
    }

    public List<Field> getAllTextFields() {
        List<Field> allTextFields = new ArrayList<Field>();

        List<CustomField> fields = customFieldManager.getCustomFieldObjects();

        for (CustomField cf : fields) {
            CustomFieldType customFieldType = cf.getCustomFieldType();

            if (customFieldType instanceof TextAreaCFType
                    || customFieldType instanceof RenderableTextCFType
                    || customFieldType instanceof NumberCFType
                    || customFieldType instanceof URLCFType){
                allTextFields.add(cf);
            }
        }
        allTextFields.addAll(
                Arrays.asList(
                        fieldManager.getField(IssueFieldConstants.SUMMARY),
                        fieldManager.getField(IssueFieldConstants.DESCRIPTION)));

        return sortFields(allTextFields);
    }

    /**
     * @param issue: issue to which the field belongs
     * @param field wished field
     * @param fieldScreen wished screen
     * @return if a field is displayed in a screen.
     */
    public boolean isFieldOnScreen(Issue issue, Field field, FieldScreen fieldScreen){
        if (IssueFieldConstants.COMMENT.equals(field.getId())) { //Always present but cannot be detected.
            return true;
        }
        if (fieldManager.isCustomField(field)) {
            CustomFieldType type = ((CustomField) field).getCustomFieldType();

            if ((type instanceof ReadOnlyCFType) ||
                    (type instanceof ImportIdLinkCFType)) {
                return false;
            }
        }

        boolean retVal = false;
        Iterator<FieldScreenTab> itTabs = fieldScreen.getTabs().iterator();

        while(itTabs.hasNext() && !retVal){
            FieldScreenTab tab = itTabs.next();
            Iterator<FieldScreenLayoutItem> itFields = tab.getFieldScreenLayoutItems().iterator();

            while(itFields.hasNext() && !retVal){
                FieldScreenLayoutItem fieldScreenLayoutItem = itFields.next();

                if ( (field.getId().equals(fieldScreenLayoutItem.getFieldId()) && isIssueHasField(issue, field)) ||
                     (TIME_TRACKING_FIELDS.contains(field.getId()) && TIME_TRACKING_FIELDS.contains(fieldScreenLayoutItem.getFieldId()) ) //time tracking fields are not really clear...
                   ) {
                    retVal = true;
                }
            }
        }

        return retVal;
    }

    /*
    It's not possible to put a validation message on a timetracking field.
     */
    public boolean cannotSetValidationMessageToField(Field field) {
        return TIME_TRACKING_FIELDS.contains(field.getId());
    }

    /**
     * Check is the issue has the field.
     *
     * @param issue: issue to which the field belongs
     * @param field: wished field
     * @param ignoreContext, if true ignore context settings
     * @return if a field is available.
     */
    public boolean isIssueHasField(Issue issue, Field field, boolean ignoreContext) {
        final String fieldId = field.getId();

        boolean isHidden;

        if (TIME_TRACKING_FIELDS.contains(fieldId)) {
            isHidden = !fieldManager.isTimeTrackingOn();
        } else {
            isHidden = fieldVisibilityManager.isFieldHidden(field.getId(), issue);
        }

        if (isHidden) {
            // Looks like we found hidden field
            return false;
        }

        if (fieldManager.isCustomField(field)) {
            CustomField customField = (CustomField) field;
            FieldConfig config = customField.getRelevantConfig(issue);
            return (ignoreContext || config != null);
        }

        return true;
    }

    public boolean isIssueHasField(Issue issue, Field field) {
        return isIssueHasField(issue,field,false);
    }

    public FieldLayoutItem getFieldLayoutItem(Issue issue, Field field) {
                FieldLayout layout = fieldLayoutManager.getFieldLayout(
                issue.getProjectObject(),
                issue.getIssueTypeObject().getId()
        );

        if (layout.getId() == null) {
            layout = fieldLayoutManager.getEditableDefaultFieldLayout();
        }

        return layout.getFieldLayoutItem(field.getId());
    }
    
    public FieldLayoutItem getCustomFieldLayoutItem(Issue issue, CustomField field) {
        FieldLayout layout = fieldLayoutManager.getFieldLayout(
        issue.getProjectObject(),
        issue.getIssueTypeObject().getId()
        );

		if (layout.getId() == null) {
		    layout = fieldLayoutManager.getEditableDefaultFieldLayout();
		}
		
		return layout.getFieldLayoutItem(field.getId());
	}

    /**
     * @param issue: issue to which the field belongs
     * @param field: wished field
     * @return if a field is required.
     */
    public boolean isFieldRequired(Issue issue, Field field) {
        boolean retVal = false;
        FieldLayoutItem fieldLayoutItem = getFieldLayoutItem(issue, field);

        if (fieldLayoutItem != null) {
            retVal = fieldLayoutItem.isRequired();
        }
        return retVal;
    }

    /**
     * Transforms given list of fields into field containers.
     */
    public List<FieldContainer> getFieldContainers(List<Field> fields) {
        ArrayList<FieldContainer> containers = new ArrayList<FieldContainer>();
        if(fields!=null) {
            for(Field field:fields) {
                containers.add(new FieldContainer(field));
            }
        }
        return containers;
    }

    /**
     * @return a list of fields that could be chosen to copy their value.
     */
    public List<Field> getCopyFromFields(){
        List<Field> allFields = getAllFields();

        allFields.removeAll(getNonCopyFromFields());

        return allFields;
    }

    /**
     * @return a list of fields that will be eliminated from getCopyFromFields().
     */
    private List<Field> getNonCopyFromFields(){
        return asFields(
                ATTACHMENT,
                COMMENT,
                ISSUE_LINKS,
                SUBTASKS,
                THUMBNAIL,
                TIMETRACKING,
                PROGRESS,
                TIME_SPENT
        );
    }

    /**
     * @return a list of fields that could be chosen to copy their value.
     */
    public List<Field> getCopyToFields(){
        List<Field> allFields = getAllFields();
        allFields.removeAll(getNonCopyToFields());

        return allFields;
    }

    /**
     * @return a list of fields that will be eliminated from getCopyToFields().
     */
    private List<Field> getNonCopyToFields(){
        return asFields(
                ATTACHMENT,
                COMMENT,
                CREATED,
                TIMETRACKING,
                TIME_ORIGINAL_ESTIMATE,
                TIME_ESTIMATE,
                TIME_SPENT,
                AGGREGATE_TIME_ORIGINAL_ESTIMATE,
                AGGREGATE_TIME_ESTIMATE,
                AGGREGATE_PROGRESS,
                ISSUE_KEY,
                ISSUE_LINKS,
                ISSUE_TYPE,
                PROJECT,
                SUBTASKS,
                THUMBNAIL,
                UPDATED,
                VOTES,
                WORKRATIO
        );
    }

    /**
     * @return a list of fields that could be chosen like required.
     */
    public List<Field> getRequirableFields(){
        List<Field> allFields = getAllFields();

        allFields.removeAll(getNonRequirableFields());

        return allFields;
    }

    /**
     * @return a list of fields that will be eliminated from getRequirableFields().
     */
    private List<Field> getNonRequirableFields(){
        return asFields(
                CREATED,
                TIMETRACKING,
                PROGRESS,
                AGGREGATE_TIME_ORIGINAL_ESTIMATE,
                AGGREGATE_PROGRESS,
                ISSUE_KEY,
                ISSUE_LINKS,
                ISSUE_TYPE,
                PROJECT,
                STATUS,
                SUBTASKS,
                THUMBNAIL,
                UPDATED,
                VOTES,
                WORKRATIO,
                WORKLOG,
                AGGREGATE_TIME_ESTIMATE
        );
    }

    /**
     * @return a list of fields that could be chosen in Value-Field Condition.
     */
    public List<Field> getValueFieldConditionFields(){
        List<Field> allFields = getAllFields();

        allFields.removeAll(getNonValueFieldConditionFields());

        return allFields;
    }

    /**
     * @return a list of fields that will be eliminated from getValueFieldConditionFields().
     */
    private List<Field> getNonValueFieldConditionFields(){
        return asFields(
                ATTACHMENT,
                COMMENT,
                CREATED,
                ISSUE_KEY,
                ISSUE_LINKS,
                SUBTASKS,
                THUMBNAIL,
                TIMETRACKING,
                UPDATED,
                WORKRATIO
        );
    }

    /**
     * @param cal
     *
     * Clear the time part from a given Calendar.
     *
     */
    public void clearCalendarTimePart(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    /**
     * @return a String.
     *
     * It formats to a date nice.
     */
    public String getNiceDate(Timestamp tsDate){
        Date timePerformed = new Date(tsDate.getTime());
        return dateTimeFormatter.format(timePerformed);
    }

    /**
     * Get comparator for sorting fields.
     */
    private Comparator<Field> getComparator() {
        I18nHelper i18n = i18nHelper.getInstance(applicationProperties.getDefaultLocale());

        return new NameComparatorEx(i18n);
    }

    /**
     * Convert array of names into list of fields
     */
    private List<Field> asFields(String ... names) {
        List<Field> result = new ArrayList<Field>(names.length);

        for (String name : names) {
            result.add(fieldManager.getField(name));
        }

        return result;
    }
}
