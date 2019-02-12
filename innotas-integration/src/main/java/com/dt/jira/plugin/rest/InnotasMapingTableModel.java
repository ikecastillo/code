package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the
 *  Table view - % of Innotas\Jira Project mappings.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class InnotasMapingTableModel {

	@XmlElement(name = "key")
	private String key;
	@XmlElement(name = "projectName")
	private String  projectName;
	@XmlElement(name = "businessUnit")
	private String businessUnit;	
	@XmlElement(name = "innotasProjectName")
	private String innotasProjectName;
	@XmlElement(name = "innotasProjectId")
	private String innotasProjectId;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getInnotasProjectName() {
		return innotasProjectName;
	}
	public void setInnotasProjectName(String innotasProjectName) {
		this.innotasProjectName = innotasProjectName;
	}
	public String getInnotasProjectId() {
		return innotasProjectId;
	}
	public void setInnotasProjectId(String innotasProjectId) {
		this.innotasProjectId = innotasProjectId;
	}
	public InnotasMapingTableModel(String key, String projectName,
			String businessUnit, String innotasProjectName,
			String innotasProjectId) {
		super();
		this.key = key;
		this.projectName = projectName;
		this.businessUnit = businessUnit;
		this.innotasProjectName = innotasProjectName;
		this.innotasProjectId = innotasProjectId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessUnit == null) ? 0 : businessUnit.hashCode());
		result = prime
				* result
				+ ((innotasProjectId == null) ? 0 : innotasProjectId.hashCode());
		result = prime
				* result
				+ ((innotasProjectName == null) ? 0 : innotasProjectName
						.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		InnotasMapingTableModel other = (InnotasMapingTableModel) obj;
		if (businessUnit == null) {
			if (other.businessUnit != null)
				return false;
		} else if (!businessUnit.equals(other.businessUnit))
			return false;
		if (innotasProjectId == null) {
			if (other.innotasProjectId != null)
				return false;
		} else if (!innotasProjectId.equals(other.innotasProjectId))
			return false;
		if (innotasProjectName == null) {
			if (other.innotasProjectName != null)
				return false;
		} else if (!innotasProjectName.equals(other.innotasProjectName))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
	
	
		
}