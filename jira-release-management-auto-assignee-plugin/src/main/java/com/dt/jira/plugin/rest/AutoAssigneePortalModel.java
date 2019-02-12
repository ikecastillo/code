package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "AutoAssigneePortal")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutoAssigneePortalModel {
	@XmlElement(name = "project")
	private String project;
	@XmlElement(name = "issueType")
	private String issueType;
	@XmlElement(name = "status")
	private String status;
	@XmlElement(name = "assignee")
	private String assignee;
	
	
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	
	
	public AutoAssigneePortalModel(String project, String issueType,String status, String assignee) {
		super();
		this.project = project;
		this.issueType = issueType;
		this.status = status;
		this.assignee = assignee;
	}
	
}
