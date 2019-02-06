package com.dt.jira.servicedesk.customize.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;

/**
 * This entity class is used for service desk configuration.
 * 
 * @author Vijay.Badam
 */
@Preload
public interface ServiceDeskMain extends Entity {

	
	String getName();

	void setName(String name);
		
	String getDescription();

	void setDescription(String description);
	
		
}
