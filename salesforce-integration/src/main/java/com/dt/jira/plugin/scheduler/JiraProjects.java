package com.dt.jira.plugin.scheduler;
/**
 *  This class is Model class which is persist the information about the project/version
 * @author kiran.muthoju
 *
 */
public class JiraProjects {
	
	private String projectId = null;
	private String projectName = null;
	private boolean active = false;
	private String version = null;
	private String parentKey = null;
	private String url = null;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public JiraProjects(String projectId, String projectName, boolean active,
			String url) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.active = active;
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JiraProjects other = (JiraProjects) obj;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
	

}
