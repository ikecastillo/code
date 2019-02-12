package com.dt.jira.plugin.scheduler;
import com.atlassian.sal.api.scheduling.PluginJob;


import java.util.Map;

import org.apache.log4j.Logger;

public class ScheduleJob implements PluginJob {
	/* Logger */
	public final Logger logger = Logger.getLogger(ScheduleJob.class);

	public void execute(Map<String, Object> jobDataMap) {
		
		logger.info("****Scheduler start ");
		System.out.println("Innotas Scheduler start ");
		final SyncInnotasSchedulerImpl jobImpl = (SyncInnotasSchedulerImpl)jobDataMap.get(SyncInnotasSchedulerImpl.KEY);
		boolean b = jobImpl.syncInnotasData();
		if(b){
			logger.info("****Scheduler ends ");
			System.out.println("Innotas Scheduler job run successfully ");
		} else {
			System.out.println("Innotas Scheduler job failed!");
		}
		
	}

}