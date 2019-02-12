package com.dt.jira.plugin.service;

import java.util.List;


import com.dt.jira.plugin.ao.AutoAssigneePortal;
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
public class AutoAssigneeServiceImpl implements AutoAssigneeService {
	
	private final Logger logger = Logger.getLogger(AutoAssigneeServiceImpl.class); 
	 
	private final ActiveObjects ao; 

	/**
	 * Constructor
	 * 
	 * @param ao
	 *            the ActiveObjects to be injected
	 */
	public AutoAssigneeServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}
	
	
   /**
    * creating AutoAssigneePortal object
    * when project issueType and status  combination not exists.
    * 
    */
	@Override
	public AutoAssigneePortal create(String project,String issueType,String status,String assignee) {
		// TODO Auto-generated method stub
		AutoAssigneePortal automationPortal = null;
		Query query = Query.select();
		query = query
				.where("project=? AND issue_type=? AND status=?",
						project, issueType, status);
		List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,
				query));
		if (mapList == null || mapList.size() <= 0) {
			automationPortal = ao.create(AutoAssigneePortal.class);
			logger.info("Successfully created");
			automationPortal.setProject(project);
			automationPortal.setIssueType(issueType);
			automationPortal.setStatus(status);
			automationPortal.setAssignee(assignee);
			automationPortal.save();
		}else{
		//validation messages need to thrown	
		}
		logger.info("saveMapping-----------------------Successfully updated------------------------- ");
		return automationPortal;
	}

	@Override
	public List<AutoAssigneePortal> findAll() {
		// TODO Auto-generated method stub
		 return newArrayList(ao.find(AutoAssigneePortal.class));
	}
	
	
	
    /**
     * find by project
     */
	@Override
	public List<AutoAssigneePortal> find(String project) {
		// TODO Auto-generated method stub
		if (project == null) return null;
   		Query query = Query.select();
   		query = query.where("project=?", project);   		
   		logger.info("project-----------------------project------------------- "+ project ); 
		List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,query));
		logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
		logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
		return mapList;
	}
	
	
	/**
	 * find project and issue type
	 */
	@Override
	public List<AutoAssigneePortal> findByProjectIssueType(String project,String issueType) {
		// TODO Auto-generated method stub
		if (project == null) return null;
   		Query query = Query.select();
   		query = query.where("project=? AND issue_type=?", project,issueType);   		
   		logger.info("project-----------------------project------------------- "+ project ); 
		List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,query));
		logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
		logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
		return mapList;
	}
	
	/**
	 * fetch all on project , issue type ,status
	 */
	@Override
	public List<AutoAssigneePortal> findByProjectIssueTypeStatus(String project,String issueType,String status){
	// TODO Auto-generated method stub
			if (project == null) return null;
			if (issueType == null) return null;
			if (status == null) return null;
			Query query = Query.select();
			if(project != null && ("None").equals(issueType) && ("None").equals(status) ){
				query = query.where("project=?", project); 
				}
			else if(project != null && issueType!=null && ("None").equals(status) ){
				query = query.where("project=? AND issue_type=?", project,issueType); 
				}
			else if(project != null && issueType != null && status != null ){
			query = query.where("project=? AND issue_type=? AND status=?", project,issueType,status).order("ISSUE_TYPE  ASC"); 	
			}
	   		
	   		  		
	   		logger.info("project-----------------------project------------------- "+ project ); 
			List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,query));
			logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
			logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
			return mapList;
		}
	
	/**
	 *find AutomationPortal 
	 */
	@Override
	public List<AutoAssigneePortal> findAutomationPortal(String project,String issueType,String status,String assignee) {
		// TODO Auto-generated method stub
		if (project == null || issueType==null) return null;
   		Query query = Query.select();
   		query = query.where("project=? AND issue_type=?AND status=?AND approval=?AND assignee=?", project,issueType,status,assignee);   		
   		logger.info("project-----------------------project------------------- "+ project ); 
		List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,query));
		logger.info("AutomationPortal Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
		logger.info("mapList-----------------------Record Available in DB------- "+ mapList.size() );
		return mapList;
	}
	
	
	/**
	 *delete AutomationPortal 
	 */
	@Override
	public void delete(String project,String issueType,String status) {
		AutoAssigneePortal automationPortal = null;
		System.out.println("project--->"+project+"issueType--->"+issueType+"status--->"+status);
		Query query = Query.select();
		query = query
				.where("project=? AND issue_type=? AND status=?",
						project, issueType, status);
		List<AutoAssigneePortal> mapList = newArrayList(ao.find(AutoAssigneePortal.class,
				query));
		if (mapList != null && mapList.size() > 0) {
			AutoAssigneePortal automationPortalobj=mapList.get(0)	;
			System.out.println("-----------------------Successfully found element------------------------- "+automationPortalobj);
			ao.delete(automationPortalobj);
		}else{
			System.out.println("-----------------------not found element------------------------- ");	
		}
	}

	@Override
	public AutoAssigneePortal findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
