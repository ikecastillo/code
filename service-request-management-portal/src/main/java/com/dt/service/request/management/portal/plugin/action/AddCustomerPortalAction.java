package com.dt.service.request.management.portal.plugin.action;

import java.util.Date;

import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;
/**
 * This class is used for adding portal configuration.
 * 
 * @author Firoz.Khan
 */
public class AddCustomerPortalAction extends CustomerPortalBaseAction {

	private static final long serialVersionUID = -2345865120367035004L;

	public AddCustomerPortalAction(CustomerPortalService customerPortalService) {
		super(customerPortalService);
	}

	@Override
	public String doExecute() throws Exception {
		final String name = request.getParameter("name");
		final String url = request.getParameter("url");
		final String icon = request.getParameter("icon");
		final String description = request.getParameter("description");
		final int parentId = Integer.valueOf(request.getParameter("parentId"));
		try{
		CustomerPortal customerPortal = this.customerPortalService.create(name,url,icon,description,parentId);
		this.customerPortalService.addComment(customerPortal, "Created at " + new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return getRedirect(VIEW_PAGE);
	}

	@Override
	public void doValidation() {
		doFieldValidation();
	}

	public String doAdd() throws Exception {
		return INPUT;
	}

}