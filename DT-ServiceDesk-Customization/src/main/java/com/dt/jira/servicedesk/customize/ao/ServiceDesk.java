package com.dt.jira.servicedesk.customize.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;

/**
 * This entity class is used for portal configuration.
 * 
 * @author Vijay.Badam
 */
@Preload
public interface ServiceDesk extends Entity {

	String getIcon();

	void setIcon(String icon);
			
	String getItem();

	void setItem(String item);
	
	String getGroups();

	void setGroups(String groups);
	
	String getUrl();

	void setUrl(String url);
	
	String getServiceDesks();

	void setServiceDesks(String servicedesks);

	boolean isComplete();

	void setComplete(boolean complete);
	
	
}
