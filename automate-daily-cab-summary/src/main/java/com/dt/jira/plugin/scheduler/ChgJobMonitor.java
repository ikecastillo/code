package com.dt.jira.plugin.scheduler;
/**
 * Interface which defines the scheduler and its parameters.
 * @author kiran.muthoju
 *
 */
public interface ChgJobMonitor {
	/**
	 * Method to set the schedule parameters
	 * @param query - for future parameter 
	 * @param interval - <long> interval to be defined for the scheduler in milliseconds.
	 */
	public void reschedule(String query, long interval);
}