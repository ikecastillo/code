package com.dt.jira.plugin.scheduler;
import com.atlassian.sal.api.scheduling.PluginJob;
import com.dt.jira.plugin.rest.LoggerWrapper;

import java.util.Date;
import java.util.Map;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScheduleJob implements PluginJob {
	/* Logger */
	public final LoggerWrapper logger = LoggerWrapper.with(getClass());

	public void execute(Map<String, Object> jobDataMap) {
        if(isWeekDays()){
		logger.setInfoLogLevel();
		System.out.println("****Scheduler start ");
		final ChgJobImpl chgJobImpl = (ChgJobImpl)jobDataMap.get(ChgJobImpl.KEY);
        chgJobImpl.sendAutomatecabEmail();
		chgJobImpl.setSleepInterval(180000);
		if(logger.isInfoEnabled())
			logger.info("****successfully change tickets email has sent ");
		
		chgJobImpl.setLastRun(new Date());
		logger.info("****Scheduler ends ");
             }else{
           logger.info("  no need scedule AUTO CAB SUMMARY in weekends");  
            
        }
	}
    
    
     private Boolean isWeekDays(){
      Calendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());  
        //SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, and SATURDAY.
		//exclude for saturday and sunday.
		logger.info("Sending email on day :   "+calEnd.get(Calendar.DAY_OF_WEEK));
         if((Calendar.SUNDAY==calEnd.get(Calendar.DAY_OF_WEEK)) || (Calendar.SATURDAY==calEnd.get(Calendar.DAY_OF_WEEK))){
           logger.info("IS Weekends"); 
          return Boolean.FALSE;  
        }
         logger.info("IS Weekdays");
        return Boolean.TRUE; 
    }
    

}