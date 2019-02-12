package com.dt.jira.problem.rcasubtask.utils;

/**
 * @author Firoz Khan
 */
interface ValueConverter {
    /**
     * Get comparable value from object.
     */
    Comparable<?> getComparable(Object object);
}
