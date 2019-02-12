package com.dt.service.request.management.portal.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;
import net.java.ao.Preload;

/**
 * This Active Object Entity class is used for Service desk Portal configuration.
 * 
 * @author Firoz.Khan
 */
@Preload
public interface CustomerPortal extends Entity {

	String getName();

	void setName(String name);
	
	String getUrl();

	void setUrl(String url);
	
	String getIcon();

	void setIcon(String icon);
	
	String getDescription();

	void setDescription(String description);
	
	int getParentId();

	void setParentId(int parentId);

	boolean isComplete();

	void setComplete(boolean complete);

	@OneToMany
	public Comment[] getComments();


}
