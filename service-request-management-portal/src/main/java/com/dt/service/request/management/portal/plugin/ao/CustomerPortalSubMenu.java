package com.dt.service.request.management.portal.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

@Preload
public interface CustomerPortalSubMenu extends Entity {

	CustomerPortal getCustomerPortal();

	void setCustomerPortal(CustomerPortal customerPortal);

	String getText();

	void setText(String text);
}
