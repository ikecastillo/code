package com.dt.jira.plugin.service;

import java.util.List;

import com.dt.jira.plugin.ao.AutoAssigneePortal;
import com.atlassian.activeobjects.tx.Transactional;

/**
 * This class is used for portal configuration services.
 * 
 * @author Srinadh.garlapati
 */
@Transactional
public interface AutoAssigneeService {

	AutoAssigneePortal create(String project,String issueType,String status,String assignee);

	List<AutoAssigneePortal> findAll();

	List<AutoAssigneePortal> find(String description);

	AutoAssigneePortal findById(int id);
	
	List<AutoAssigneePortal> findByProjectIssueType(String project,String issueType);
	
	List<AutoAssigneePortal> findByProjectIssueTypeStatus(String project,String issueType,String status);
	
	
	List<AutoAssigneePortal> findAutomationPortal(String project,String issueType,String status,String assignee) ;
	
	void delete(String project,String issueType,String status); 
	

}