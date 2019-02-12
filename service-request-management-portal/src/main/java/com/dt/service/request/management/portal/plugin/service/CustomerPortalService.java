package com.dt.service.request.management.portal.plugin.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
/**
 * This class is used for portal configuration services.
 * 
 * @author Firoz.Khan
 */
@Transactional
public interface CustomerPortalService {

	CustomerPortal create(String name,String url,String icon,String description,int parentId);

	List<CustomerPortal> findAll();

	List<CustomerPortal> findAllParents();
	
	List<CustomerPortal> findAllChild(int parentId);
	
	List<CustomerPortal> find(String description);

	CustomerPortal findById(int id);

	void delete(int id);

	//void addCustomerPortalSubMenu(CustomerPortal customerPortal, String text);
	
	void addComment(CustomerPortal customerPortal, String text);

}