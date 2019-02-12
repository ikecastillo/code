package com.dt.service.request.management.portal.plugin.service;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.service.request.management.portal.plugin.ao.Comment;
import com.dt.service.request.management.portal.plugin.ao.CustomerPortal;
import com.dt.service.request.management.portal.plugin.ao.CustomerPortalSubMenu;
/**
 * This class is used for portal configuration services implementation.
 * 
 * @author Firoz.Khan
 */
public final class CustomerPortalServiceImpl implements CustomerPortalService {

	private final ActiveObjects ao;

	public CustomerPortalServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

	@Override
	public CustomerPortal create(String name,String url,String icon,String description,int parentId) {
		final CustomerPortal customerPortal = ao.create(CustomerPortal.class);
		customerPortal.setName(name);
		customerPortal.setUrl(url);
		customerPortal.setIcon(icon);
		customerPortal.setDescription(description);
		customerPortal.setParentId(parentId);
		customerPortal.setComplete(false);
		customerPortal.save();
		return customerPortal;
	}

	@Override
	public List<CustomerPortal> findAll() {
		return newArrayList(ao.find(CustomerPortal.class));
	}

	@Override
	public List<CustomerPortal> find(String description) {
		return newArrayList(ao.find(CustomerPortal.class, Query.select().where("DESCRIPTION = ?", description)));
	}
	
	@Override
	public List<CustomerPortal> findAllParents() {
		return newArrayList(ao.find(CustomerPortal.class, Query.select().where("PARENT_ID = ?", 0)));
	}
	
	@Override
	public List<CustomerPortal> findAllChild(int parentId) {
		return newArrayList(ao.find(CustomerPortal.class, Query.select().where("PARENT_ID = ?", parentId)));
	}

	@Override
	public void addComment(CustomerPortal customerPortal, String text) {
		Comment comment = ao.create(Comment.class);
		comment.setCustomerPortal(customerPortal);
		comment.setText(text);
		comment.save();
	}
	
//	@Override
//	public void addCustomerPortalSubMenu(CustomerPortal customerPortal, String text) {
//		CustomerPortalSubMenu customerPortalSubMenu = ao.create(CustomerPortalSubMenu.class);
//		customerPortalSubMenu.setCustomerPortal(customerPortal);
//		customerPortalSubMenu.setText(text);
//		customerPortalSubMenu.save();
//	}

	@Override
	public CustomerPortal findById(int id) {
		//return ao.find(Todo.class, Query.select().where("id = ?", id);
		return ao.get(CustomerPortal.class, id);
	}

	@Override
	public void delete(int id) {
		CustomerPortal customerPortal = ao.get(CustomerPortal.class, id);
		ao.delete(customerPortal.getComments());
		//ao.delete(customerPortal.getCustomerPortalSubMenu());
		ao.delete(customerPortal);
	}


}