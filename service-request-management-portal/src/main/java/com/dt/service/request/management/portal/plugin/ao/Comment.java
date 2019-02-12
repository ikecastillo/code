package com.dt.service.request.management.portal.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * This Active Object Entity class is used for Service desk Portal configuration.
 * 
 * @author Firoz.Khan
 */
@Preload
public interface Comment extends Entity {

	CustomerPortal getCustomerPortal();

	void setCustomerPortal(CustomerPortal customerPortal);

	String getText();

	void setText(String text);

}
