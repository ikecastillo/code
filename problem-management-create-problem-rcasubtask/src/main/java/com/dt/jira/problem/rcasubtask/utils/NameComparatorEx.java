package com.dt.jira.problem.rcasubtask.utils;

import java.util.Comparator;

import com.atlassian.jira.issue.fields.Field;
import com.atlassian.jira.util.I18nHelper;

/**
 * @author firoz Khan
 *
 * This Comparator is used to compare two fields by its internationalized name.
 *
 */
public class NameComparatorEx implements Comparator<Field> {
    private final I18nHelper i18nHelper;

    public NameComparatorEx(I18nHelper i18nHelper) {
        this.i18nHelper = i18nHelper;
    }

    public int compare(Field o1, Field o2) {
        if (o1 == null)
            throw new IllegalArgumentException("The first parameter is null");
        if (o2 == null)
            throw new IllegalArgumentException("The second parameter is null");

        String name1 = i18nHelper.getText(o1.getName());
        String name2 = i18nHelper.getText(o2.getName());

        return name1.compareTo(name2);
    }
}
