package com.dt.jira.servicedesk.customize.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.servicedesk.customize.ao.ServiceDesk;
import com.dt.jira.servicedesk.customize.ao.ServiceDeskMain;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskFields;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskMainFields;

import net.java.ao.Query;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
/**
 * This class is used for portal configuration services implementation.
 * 
 * @author Firoz.Khan
 */
public final class ServiceDeskServiceImpl implements ServiceDeskService {

	private final ActiveObjects ao;

	public ServiceDeskServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

	 @Override
	public ServiceDesk create(String icon,String item,String groups,String url,String servicedesks) {
		
		final ServiceDesk servicedesk = ao.create(ServiceDesk.class);
		servicedesk.setIcon(icon);
		servicedesk.setItem(item);
		servicedesk.setGroups(groups);
		servicedesk.setUrl(url);
		servicedesk.setServiceDesks(servicedesks);
		servicedesk.setComplete(false);
		servicedesk.save();
		//logger.info("------saving Fields---------");
		return servicedesk;
	} 
	
	 @Override
	public ServiceDeskMain create(String name,String description) {
		
		final ServiceDeskMain servicedeskmain = ao.create(ServiceDeskMain.class);
		servicedeskmain.setName(name);
		servicedeskmain.setDescription(description);
		servicedeskmain.save();
		//logger.info("------saving Fields---------");
		return servicedeskmain;
	} 
	
	 @Override
     public ServiceDesk update(String icon,String item,String groups,String url,String servicedesks,int id) {
                     ServiceDesk servicedesk = findById(id);
                     servicedesk.setIcon(icon);
                     servicedesk.setItem(item);
                     servicedesk.setGroups(groups);
                     servicedesk.setUrl(url);
                     servicedesk.setServiceDesks(servicedesks);
                     servicedesk.setComplete(false);
                     servicedesk.save();
                     //logger.info("------updating Fields---------");
                     return servicedesk;
                     
     }

	
	@Override
	public ServiceDeskMain update(String name,String description,int service_id) {
		
		ServiceDeskMain servicedeskmain = findByServiceId(service_id);
		servicedeskmain.setName(name);
		servicedeskmain.setDescription(description);
		servicedeskmain.save();
		//logger.info("------updating Fields---------");
		return servicedeskmain;
	} 
	
	
	@Override
	public List<ServiceDesk> findAll() {
		return newArrayList(ao.find(ServiceDesk.class));
	}
	
	@Override
	public List<ServiceDeskMain> findAllMainFields() {
		return newArrayList(ao.find(ServiceDeskMain.class));
	}
	
	/* @Override
	public List<FieldMapping> findAllByParentAndChildIssue(String parentIssue,String childIssue){
		Query query = Query.select();
		query = query.where("FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",parentIssue,childIssue);
		return newArrayList(ao.find(FieldMapping.class,query));
	} */

//	@Override
//	public List<FieldMapping> getAllCustomFieldsFromDb() {
//		return newArrayList(ao.find(FieldMapping.class));
//	}
//	 	
	
	
	
	@Override
	public void delete(String icon,String item,String groups,String url,String servicedesks) {
		//logger.info("icon "+icon +"item "+item+"groups "+groups+"url "+url+"servicedesks "+servicedesks);
		ServiceDesk servicedesk = null;
		Query query = Query.select();
		query = query
				.where("ITEM=? AND GROUPS=? AND URL=? AND SERVICE_DESKS=?",
						item,groups,url,servicedesks);
		List<ServiceDesk> fieldList = newArrayList(ao.find(ServiceDesk.class,
				query));
		if (fieldList != null && fieldList.size() > 0) {
			 servicedesk=fieldList.get(0)	;
			//logger.info("-----------------------Successfully found element------------------------- "+servicedesk);
			ao.delete(servicedesk);
		}else{
			//logger.info("-----------------------not found element------------------------- ");	
		}
	}
	
	@Override
	public void delete(String name,String description) {
		ServiceDeskMain servicedeskmain = null;
		Query query = Query.select();
		query = query
				.where("NAME=? AND DESCRIPTION=?",
						name,description);
		List<ServiceDeskMain> mainfieldList = newArrayList(ao.find(ServiceDeskMain.class,
				query));
		if (mainfieldList != null && mainfieldList.size() > 0) {
			 servicedeskmain=mainfieldList.get(0)	;
			//logger.info("-----------------------Successfully found element------------------------- "+servicedeskmain);
			ao.delete(servicedeskmain);
		}else{
			//logger.info("-----------------------not found element------------------------- ");	
		}
	}

	@Override
    public ServiceDesk findById(int id) {
                    //logger.info("-------------id----------------"+id);
                    return ao.get(ServiceDesk.class, id);
    }
	
	@Override
	public ServiceDeskMain findByServiceId(int service_id) {
		//return ao.find(Todo.class, Query.select().where("id = ?", id);
		return ao.get(ServiceDeskMain.class, service_id);
	}
	
	@Override
	public List<ServiceDesk> findGroups(String servicedesks) {
		return newArrayList(ao.find(ServiceDesk.class));
			
	}
	
	@Override
	public List<ServiceDesk> findGroupsByServiceDesk(String servicedesks) {
		return newArrayList(ao.find(ServiceDesk.class, Query.select().where("SERVICE_DESKS=?",servicedesks)));
			
	}
	
	@Override
	public List<ServiceDesk> findItems(String groups,String servicedesks) {
		return newArrayList(ao.find(ServiceDesk.class, Query.select().where("GROUPS=? AND SERVICE_DESKS=?",groups,servicedesks)));
			
	}
	
	
	@Override
	public List<ServiceDesk> findItemsByServiceDesk(String servicedesks) {
		return newArrayList(ao.find(ServiceDesk.class, Query.select().where("SERVICE_DESKS=?",servicedesks)));
			
	}

	
}