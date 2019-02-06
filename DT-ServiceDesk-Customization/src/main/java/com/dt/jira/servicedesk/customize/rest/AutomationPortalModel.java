package com.dt.jira.servicedesk.customize.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "AutomationPortal")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutomationPortalModel {
	@XmlElement(name = "Project")
	private String project;
	@XmlElement(name = "issueType")
	private String issueType;
	@XmlElement(name = "subTask")
	private String subTask;
	@XmlElement(name = "userdn")
	private String userdn;
	@XmlElement(name = "password")
	private String password;
	@XmlElement(name = "assignee")
	private String assignee;
	@XmlElement(name = "group")
	private String group;
	
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 *            the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the issueType
	 */
	public String getIssueType() {
		return issueType;
	}

	/**
	 * @param issueType
	 *            the issueType to set
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	

	public String getUserdn() {
		return userdn;
	}

	public void setUserdn(String userdn) {
		this.userdn = userdn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee
	 *            the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	

	public String getSubTask() {
		return subTask;
	}

	public void setSubTask(String subTask) {
		this.subTask = subTask;
	}

	public AutomationPortalModel(String project, String issueType,String subTask,String userdn, String password,String assignee,String group) {
		super();
		this.project = project;
		this.issueType = issueType;
		this.subTask = subTask;
		this.userdn = userdn;
		this.assignee = assignee;
		this.password = password;
		this.group = group;
	}
	
}
