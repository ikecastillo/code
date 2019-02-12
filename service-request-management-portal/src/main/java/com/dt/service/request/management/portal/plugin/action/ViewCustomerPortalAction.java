package com.dt.service.request.management.portal.plugin.action;

import java.util.Collections;
import java.util.List;

import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;

/**
 * This class is used for view portal configuration.
 * 
 * @author Firoz.Khan
 */
public class ViewCustomerPortalAction extends CustomerPortalBaseAction {

	private static final long serialVersionUID = -2785052435600102817L;

	public ViewCustomerPortalAction(CustomerPortalService customerPortalService) {
		super(customerPortalService);
	}

	@Override
	protected String doExecute() throws Exception {
		try{
			customerPortals = this.customerPortalService.findAll();
		}catch(Exception e){
			e.getStackTrace();
		}
		return SUCCESS;
	}

	public String doSelect() {
		final String name = request.getParameter("name");
		customerPortals = this.customerPortalService.find(name);
		return SUCCESS;
	}

	List<CustomerPortal> customerPortals = Collections.emptyList();
	public List<CustomerPortal> getCustomerPortals() {
		return customerPortals;
	}

}