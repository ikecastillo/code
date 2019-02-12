package com.dt.service.request.management.portal.plugin.action;

import static com.google.common.base.Preconditions.checkNotNull;

import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;

/**
 * This generic class is used for validating portal configuration.
 * 
 * @author Firoz.Khan
 */
public class CustomerPortalBaseAction extends JiraWebActionSupport {
	
	private static final long serialVersionUID = -2345865120367035004L;
	
	final static String VIEW_PAGE = "ViewCustomerPortalAction.jspa";
	
	protected final CustomerPortalService customerPortalService;
	
	public CustomerPortalBaseAction(CustomerPortalService customerPortalService) {
		this.customerPortalService = checkNotNull(customerPortalService);
	}
	
	protected void doKeyValidation() {
		this.log.debug("Entering doKeyValidation");
		final String id = this.request.getParameter("key");
		if (id == null || id.isEmpty()) {
			// ?key=
			addErrorMessage(getText("menu.config.form.id.missing"));
		} else {
			try {
				this.customerPortal = this.customerPortalService.findById(Integer.valueOf(id));
				this.customerPortal.getName();
				this.customerPortal.getUrl();
				this.customerPortal.getIcon();
				this.customerPortal.getDescription();
			} catch (NumberFormatException e) {
				// ?key=aaa
				addErrorMessage(getText("menu.config.form.id.wrong.format"));
			} catch (NullPointerException e) {
				// ?key=111
				addErrorMessage(getText("menu.config.form.not.found"));
			}
			
		}
	}
	
	protected void doFieldValidation() {
		final String name = this.request.getParameter("name");
		if (name == null || name.isEmpty()) {
			addErrorMessage(getText("menu.config.form.name.missing"));
		}
		final String url = this.request.getParameter("url");
		if (url == null || url.isEmpty()) {
			addErrorMessage(getText("menu.config.form.url.missing"));
		}
		final String icon = this.request.getParameter("icon");
		if (icon == null || icon.isEmpty()) {
			addErrorMessage(getText("menu.config.form.icon.missing"));
		}
		final String description = this.request.getParameter("description");
		if (description == null || description.isEmpty()) {
			addErrorMessage(getText("menu.config.form.description.missing"));
		}
		int maxLength = 50;
		if (description != null && description.length() > maxLength) {
			addErrorMessage(getText("menu.config.form.description.too.long"));
		}
	}
	
	protected CustomerPortal customerPortal;
	
	public CustomerPortal getCustomerPortal() {
		return this.customerPortal;
	}
	
}