package com.dt.jira.impm.rolebased.mapping.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity class is used for Project Role Based Rest Model.
 * 
 * @author Firoz.Khan
 */
@XmlRootElement(name="ProjectRoleBasedMapping")
@XmlAccessorType(XmlAccessType.FIELD)
public class IMPMRoleBasedModel {
		
	@XmlElement private String projectKey;	
	@XmlElement private String issueType;	
	@XmlElement private String solutionGroup;	
	@XmlElement private String projectRole;
	@XmlElement private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getSolutionGroup() {
		return solutionGroup;
	}

	public void setSolutionGroup(String solutionGroup) {
		this.solutionGroup = solutionGroup;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public IMPMRoleBasedModel(String projectKey,String issueType,String solutionGroup,String projectRole){
			this.projectKey = projectKey; 
			this.issueType = issueType;
			this.solutionGroup = solutionGroup; 
			this.projectRole = projectRole;
	}
	
	public IMPMRoleBasedModel(String result,String projectKey,String issueType,String solutionGroup,String projectRole){
		this.result = result; 
		this.projectKey = projectKey; 
		this.issueType = issueType;
		this.solutionGroup = solutionGroup; 
		this.projectRole = projectRole;
}

	public IMPMRoleBasedModel() {
		// TODO Auto-generated constructor stub
	}
		

}
