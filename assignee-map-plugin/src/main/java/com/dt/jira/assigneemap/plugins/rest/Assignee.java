package com.dt.jira.assigneemap.plugins.rest;

import javax.xml.bind.annotation.*;

/**
 * The REST model for for Assignee Map within Change Management Project
 *
 * @author Firoz.Khan
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Assignee {
	@XmlElement private String projectKey;		
	@XmlElement private String solutionGroup;
	@XmlElement private String impact;
	@XmlElement private String status;
	@XmlElement private String users;
	
	public Assignee() {	}
	
	public Assignee(String projectKey, String solutionGroup, String impact,
			String status, String users) {
		super();
		this.projectKey = projectKey;
		this.solutionGroup = solutionGroup;
		this.impact = impact;
		this.status = status;
		this.users = users;
	}
		
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	public String getSolutionGroup() {
		return solutionGroup;
	}
	public void setSolutionGroup(String solutionGroup) {
		this.solutionGroup = solutionGroup;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return new StringBuffer(" projectKey : ").append(this.projectKey)
				.append(" solutionGroup : ").append(this.solutionGroup)
				.append(" impact : ").append(this.impact)
				.append(" status : ").append(this.status) 
				.append(" users : ").append(this.users).toString();
	}
}