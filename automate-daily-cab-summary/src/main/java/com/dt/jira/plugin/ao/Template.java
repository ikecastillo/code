package com.dt.jira.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.StringLength;
import net.java.ao.Mutator;

/**
 * This entity class is used for automation configuration.
 * 
 * @author Srinadh.G
 */
@Preload
public interface Template extends Entity {
	
	String getTemplateName();

	void setTemplateName(String templateName);
    
    @StringLength(value=StringLength.UNLIMITED)
    String getMailTo();
    
    @StringLength(value=StringLength.UNLIMITED)
	void setMailTo(String mailTo);
    
    
    String getMailFrom();

	void setMailFrom(String mailFrom);
    
    @StringLength(value=StringLength.UNLIMITED)
    String getSubject();

	@StringLength(value=StringLength.UNLIMITED)
	void setSubject(String subject);
    
	@StringLength(value=StringLength.UNLIMITED)
    String getMailBody();

	@StringLength(value=StringLength.UNLIMITED)
	void setMailBody(String mailBody);
    
    String getScheduleTime();

	void setScheduleTime(String scheduleTime);
    
    
    
    
}
