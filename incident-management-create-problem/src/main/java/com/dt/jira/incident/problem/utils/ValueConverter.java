package com.dt.jira.incident.problem.utils;

/**
 * @author Firoz Khan
 */
interface ValueConverter {
    /**
     * Get comparable value from object.
     */
    Comparable<?> getComparable(Object object);
}
