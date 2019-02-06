package com.dt.jira.servicedesk.customize.service;

import java.util.List;

import com.dt.jira.servicedesk.customize.ao.AutomationPortal;
import com.atlassian.activeobjects.tx.Transactional;

/**
 * This class is used for portal configuration services.
 * 
 * @author Srinadh.garlapati
 */
@Transactional
public interface PortalService {

	AutomationPortal create(String project,String issueType,String subTask,String status,String assignee,String approval,String group);

	List<AutomationPortal> findAll();

	List<AutomationPortal> find(String description);

	AutomationPortal findById(int id);
	
	List<AutomationPortal> findByProjectIssueType(String project,String issueType);
	
	List<AutomationPortal> findByProjectIssueTypeSubTask(String project,String issueType,String subTask);	
	
	List<AutomationPortal> findAutomationPortal(String project,String issueType,String status,String approval,String assignee) ;
	
	void delete(String project,String issueType,String subTask); 
	

}