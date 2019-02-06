package com.dt.jira.servicedesk.customize.service;

import java.util.List;


import com.dt.jira.servicedesk.customize.ao.AutomationPortal;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;

/**
 * @author Srinadh.garlapati
 *
 */
public class PortalServiceImpl implements PortalService {
	
	private final Logger logger = Logger.getLogger(PortalServiceImpl.class); 
	 
	private final ActiveObjects ao; 

	/**
	 * Constructor
	 * 
	 * @param ao 
	 *            the ActiveObjects to be injected
	 */
	public PortalServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}
	
		

	@Override
	public AutomationPortal create(String project,String issueType,String subTask,String userdn,String assignee,String password,String group) {
		// TODO Auto-generated method stub
		System.out.println("-------create-------subTask-----------"+subTask);
		AutomationPortal automationPortal = null;
		Query query = Query.select();
		query = query.where("project=? AND issue_type=? AND sub_task=?",project, issueType ,subTask);
		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		System.out.println("-------mapList------------------"+mapList);
		if (mapList == null || mapList.size() <= 0) {
			automationPortal = ao.create(AutomationPortal.class);
			logger.info("Successfully created");
			automationPortal.setProject(project);
			automationPortal.setIssueType(issueType);
			automationPortal.setSubTask(subTask);
			automationPortal.setUserdn(userdn);
			automationPortal.setAssignee(assignee);
			automationPortal.setPassword(password);
			automationPortal.setGroup(group);
			automationPortal.save();
		}else{
		//validation messages need to thrown	
		}
		logger.info("saveMapping-----------------------Successfully updated------------------------- ");
		return automationPortal;
	}

	@Override
	public List<AutomationPortal> findAll() {
		// TODO Auto-generated method stub
		 return newArrayList(ao.find(AutomationPortal.class));
	}

	@Override
	public List<AutomationPortal> find(String project) {
		// TODO Auto-generated method stub
		if (project == null) return null;
   		Query query = Query.select();
   		query = query.where("project=?", project);   		
   		logger.info("project-----------------------project------------------- "+ project ); 
		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
		logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
		return mapList;
	}
	
	@Override
	public List<AutomationPortal> findByProjectIssueType(String project,String issueType) {
		// TODO Auto-generated method stub
		if (project == null) return null;
   		Query query = Query.select();
   		query = query.where("project=? AND issue_type=?", project,issueType);   		
   		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		return mapList;
	}
	
	
	@Override
	public List<AutomationPortal> findByProjectIssueTypeSubTask(String project,String issueType,String subTask) {
		// TODO Auto-generated method stub
		if (project == null) return null;
   		Query query = Query.select();
   		query = query.where("project=? AND issue_type=? AND sub_task=?", project,issueType,subTask);   		
   		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		return mapList;
	}
	
	
	@Override
	public List<AutomationPortal> findAutomationPortal(String project,String issueType,String userdn,String approval,String assignee) {
		// TODO Auto-generated method stub
		if (project == null || issueType==null) return null;
   		Query query = Query.select();
   		query = query.where("project=? AND issue_type=?AND userdn=?AND approval=?AND assignee=?", project,issueType,userdn,approval,assignee);   		
   		logger.info("project-----------------------project------------------- "+ project ); 
		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
		logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
		return mapList;
	}
	
	@Override
	public void delete(String project,String issueType,String subTask) {
		AutomationPortal automationPortal = null;
		Query query = Query.select();
		query = query
				.where("project=? AND issue_type=? AND sub_task=?",project, issueType, subTask);
		List<AutomationPortal> mapList = newArrayList(ao.find(AutomationPortal.class,query));
		if (mapList != null && mapList.size() > 0) {
			AutomationPortal automationPortalobj=mapList.get(0)	;
			logger.info("-----------------------Successfully found element------------------------- "+automationPortalobj);
			ao.delete(automationPortalobj);
		}else{
			logger.info(" No element found to delete");
		}
	}

	@Override
	public AutomationPortal findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
