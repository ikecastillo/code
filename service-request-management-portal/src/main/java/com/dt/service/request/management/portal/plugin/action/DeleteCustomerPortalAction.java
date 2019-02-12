package com.dt.service.request.management.portal.plugin.action;

import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;

/**
 * This class is used for deleting portal configuration.
 * 
 * @author Firoz.Khan
 */
public class DeleteCustomerPortalAction extends CustomerPortalBaseAction {

	private static final long serialVersionUID = -5096207440673295940L;

	public DeleteCustomerPortalAction(CustomerPortalService customerPortalService) {
		super(customerPortalService);
	}

	@Override
	protected String doExecute() throws Exception {
		final String id = request.getParameter("key");
		this.customerPortalService.delete(Integer.valueOf(id));

		return getRedirect(VIEW_PAGE);
	}

	@Override
	public void doValidation() {
		doKeyValidation();
	}

}