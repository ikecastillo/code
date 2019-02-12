package com.dt.jira.plugin.scheduler;
import com.atlassian.sal.api.scheduling.PluginJob;
import com.dt.jira.plugin.rest.LoggerWrapper;

import java.util.Date;
import java.util.Map;

public class ScheduleJob implements PluginJob {
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());

	public void execute(Map<String, Object> jobDataMap) {
		logger.setInfoLogLevel();
		logger.info("Scheduler start ");
		final JobImpl jobImpl = (JobImpl)jobDataMap.get(JobImpl.KEY);
		String accessToken = jobImpl.authenticate();
		if(logger.isInfoEnabled())
		logger.info("get the token : " + accessToken);
		Map map = jobImpl.getAllSalesForceProjects(accessToken);
		
		if(logger.isInfoEnabled())
			logger.info(" ***Update versions which has parent key null " + jobImpl.updateVersionsHasParentKeyNull(map, accessToken));

		if(logger.isInfoEnabled())
			logger.info("****"+ jobImpl.updateOrdeleteProjectsOnSF(map,accessToken) +" the de-active/update versions on SF : ");
		jobImpl.setSleepInterval(2000);
		if(logger.isInfoEnabled())
			logger.info("****successfully sync the new proj/versions: "+ jobImpl.syncJiraProjectsAndReleases(map));
		
		jobImpl.setLastRun(new Date());
		logger.info("Scheduler ends ");
	}

}