package com.dt.jira.rcamap.plugin.rest;

import javax.xml.bind.annotation.*;

/**
 * The RCA Report Subtask Map Rest Model for RCA Report Subtask Assignee Map on Problem Management project
 *
 * @author Firoz Khan
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RCASubtaskMapModel {
	@XmlElement private String projectKey;
	@XmlElement private String type;
	@XmlElement private String grpOptionId;
	@XmlElement private String grpOptionName;
	@XmlElement private String cldGrpOptionId;
	@XmlElement private String cldGrpOptionName;
	@XmlElement private String fourthLevelOptionId;
	@XmlElement private String fourthLevelOptionName;
	
	
	
	public String getFourthLevelOptionId() {
		return fourthLevelOptionId;
	}

	public void setFourthLevelOptionId(String fourthLevelOptionId) {
		this.fourthLevelOptionId = fourthLevelOptionId;
	}

	public String getFourthLevelOptionName() {
		return fourthLevelOptionName;
	}

	public void setFourthLevelOptionName(String fourthLevelOptionName) {
		this.fourthLevelOptionName = fourthLevelOptionName;
	}
	public String getCldGrpOptionId() {
		return cldGrpOptionId;
	}

	public void setCldGrpOptionId(String cldGrpOptionId) {
		this.cldGrpOptionId = cldGrpOptionId;
	}

	public String getCldGrpOptionName() {
		return cldGrpOptionName;
	}

	public void setCldGrpOptionName(String cldGrpOptionName) {
		this.cldGrpOptionName = cldGrpOptionName;
	}


	
	public String getGrpOptionName() {
		return grpOptionName;
	}

	public void setGrpOptionName(String grpOptionName) {
		this.grpOptionName = grpOptionName;
	}


	@XmlElement private String roles;
	@XmlElement private String users;
	@XmlElement private String createdDate;
	@XmlElement private String modifiedDate;
	@XmlElement private String modifiedBy;
	
	public RCASubtaskMapModel() {	}
	
	public RCASubtaskMapModel(String projectKey, String type, 
			String grpOptionId, String grpOptionName, 
			String cldGrpOptionId, String cldGrpOptionName,
			String roles, String users, String createdDate,
			String modifiedDate, String modifiedBy) {
		super();
		this.projectKey = projectKey;
		this.type = type;
		this.grpOptionId = grpOptionId;
		this.grpOptionName = grpOptionName;
		this.cldGrpOptionId = cldGrpOptionId;
		this.cldGrpOptionName = cldGrpOptionName;
		this.roles = roles;
		this.users = users;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}
	
	public String getGrpOptionId() {
		return grpOptionId;
	}

	public void setGrpOptionId(String grpOptionId) {
		this.grpOptionId = grpOptionId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	
	@Override
	public String toString() {
		return new StringBuffer(" projectKey : ").append(this.projectKey)
				.append(" type : ").append(this.type)
				.append(" grpOptionId : ").append(this.grpOptionId)
				.append(" grpOptionName : ").append(this.grpOptionName)
				.append(" cldGrpOptionId : ").append(this.cldGrpOptionId)
				.append(" cldGrpOptionName : ").append(this.cldGrpOptionName)
				.append(" roles : ").append(this.roles) 
				.append(" users : ").append(this.users)
				.append(" createdDate : ").append(this.createdDate)
				.append(" modifiedDate : ").append(this.modifiedDate) 
				.append(" modifiedBy : ").append(this.modifiedBy).toString();
	}
}