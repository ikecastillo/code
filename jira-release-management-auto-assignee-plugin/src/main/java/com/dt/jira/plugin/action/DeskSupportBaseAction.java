package com.dt.jira.plugin.action;


import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.dt.jira.plugin.ao.AutoAssigneePortal;
import com.dt.jira.plugin.service.AutoAssigneeService;
import static com.google.common.base.Preconditions.checkNotNull;


/**
 * This generic class is used for validating portal configuration.
 * 
 * @author Srinadh.G
 */
public class DeskSupportBaseAction extends JiraWebActionSupport {
	
	private static final long serialVersionUID = -2345865120367035004L;
	
	final static String VIEW_PAGE = "/plugins/servlet/jira-auto-assignee-mapping";
	
	
	protected final AutoAssigneeService autoAssigneeService;
	
	
	
	public DeskSupportBaseAction(AutoAssigneeService autoAssigneeService) {
		this.autoAssigneeService  =checkNotNull(autoAssigneeService);
	}
	
	protected void doKeyValidation() {
		
	}
	
	protected void doFieldValidation() {
		
	}
	
	protected AutoAssigneePortal autoAssigneePortal;
	
	public AutoAssigneePortal getAutomationPortal() {
		return this.autoAssigneePortal;
	}
	
	
}