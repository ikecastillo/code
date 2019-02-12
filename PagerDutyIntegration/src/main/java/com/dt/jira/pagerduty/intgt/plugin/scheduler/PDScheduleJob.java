package com.dt.jira.pagerduty.intgt.plugin.scheduler;

import com.atlassian.sal.api.scheduling.PluginJob;
import com.dt.jira.pagerduty.intgt.plugin.rest.LoggerWrapper;

import java.util.Date;
import java.util.Map;

public class PDScheduleJob implements PluginJob {
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());

	public void execute(Map<String, Object> jobDataMap) {
		logger.setInfoLogLevel();
		
		logger.info("xMatters****Scheduler start ");		
		final PDJobImpl jobImpl = (PDJobImpl)jobDataMap.get(PDJobImpl.KEY);
		jobImpl.setLastRun(new Date());
		logger.info("xMatters****Scheduler start "+jobImpl.getLastRun());	
		String results = jobImpl.syncSGandProducts();
		jobImpl.setLastRun(new Date());
		logger.info("xMatters****Solution Group and Products data sync "+ results + "  "+jobImpl.getLastRun());
		results =	jobImpl.syncLocationsandImpacted();
		jobImpl.setLastRun(new Date());
		logger.info("xMatters****Impacted data sync "+ results + "  "+jobImpl.getLastRun());
		
		
		logger.info("xMatters****Scheduler ends ");
	}

}