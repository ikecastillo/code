package com.dt.service.request.management.portal.plugin.action;

import com.dt.service.request.management.portal.plugin.service.CustomerPortalService;

/**
 * This class is used for editing portal configuration.
 * 
 * @author Firoz.Khan
 */
public class EditCustomerPortalAction extends CustomerPortalBaseAction {

	private static final long serialVersionUID = -2345865120367035004L;

	public EditCustomerPortalAction(CustomerPortalService customerPortalService) {
		super(customerPortalService);
	}

	@Override
	protected String doExecute() throws Exception {
		final String name = request.getParameter("name");
		final String url = request.getParameter("url");
		final String icon = request.getParameter("icon");
		final String description = request.getParameter("description");		
		final int parentId = Integer.valueOf(request.getParameter("parentId"));
		try{
			System.out.println("parentId" + parentId);
			customerPortal.setName(name);
			customerPortal.setUrl(url);
			customerPortal.setIcon(icon);
			customerPortal.setDescription(description);
			customerPortal.setParentId(parentId);
			customerPortal.save();
		}catch(Exception e){
			e.printStackTrace();
		}
		return getRedirect(VIEW_PAGE);
	}

	public String doEdit() throws Exception {
		doKeyValidation();
		return INPUT;
	}

	@Override
	public void doValidation() {
		doKeyValidation();
		doFieldValidation();
	}

}