package com.dt.jira.create.incident.report.subtask.utils;

/**
 * @author Firoz Khan
 */
interface ValueConverter {
    /**
     * Get comparable value from object.
     */
    Comparable<?> getComparable(Object object);
}
