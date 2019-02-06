package com.dt.jira.servicedesk.customize.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.servicedesk.customize.ao.ServiceDesk;
import com.dt.jira.servicedesk.customize.ao.ServiceDeskMain;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskFields;
import com.dt.jira.servicedesk.customize.rest.ServiceDeskMainFields;



import java.util.List;
/**
 * This class is used for portal configuration services.
 * 
 * @author Vijay.Badam
 */
@Transactional
public interface ServiceDeskService {

	ServiceDesk create(String icon,String item,String groups,String url,String servicedesks);
	
	ServiceDeskMain create(String name,String description);
	
	ServiceDesk update(String icon,String item,String groups,String url,String servicedesks,int id);
	
	ServiceDeskMain update(String name,String description,int service_id);
	
	List<ServiceDesk> findAll();
	
	List<ServiceDeskMain> findAllMainFields();
	
	List<ServiceDesk> findGroups(String servicedesks);
	
	List<ServiceDesk> findGroupsByServiceDesk(String servicedesks);
	
	List<ServiceDesk> findItems(String groups,String servicedesks);
	
	//List<ServiceDesk> findAllByParentAndChildIssue(String parentIssue,String childIssue); 
	
	//List<Field> getAllCustomFieldsFromDb();
	
	void delete(String icon,String item,String groups,String url,String servicedesks);
	
	void delete(String name,String description);
	
	ServiceDesk findById(int id);
	
	ServiceDeskMain findByServiceId(int id);
	
	List<ServiceDesk> findItemsByServiceDesk(String servicedesks);
	
}