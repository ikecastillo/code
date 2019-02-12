package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the release burn down chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReleaseBurnDownModel {

	@XmlElement(name = "name")
	private String sprintName;
	@XmlElement(name = "backlog")
	private String backlogStPoints;	
	@XmlElement(name = "completed")
	private String completedStPoints;
	@XmlElement(name = "key")
	private String sprintKey;
	@XmlElement(name = "optimistic")
	private String optimisticValue;
	@XmlElement(name = "pesimistic")
	private String pesimisticValue;	
	@XmlElement(name = "planned")
	private String plannedValue;
	
	@XmlElement(name = "startdate")
	private String startDate;
	@XmlElement(name = "enddate")
	private String endDate;
	public String getSprintName() {
		return sprintName;
	}
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}
	public String getBacklogStPoints() {
		return backlogStPoints;
	}
	public void setBacklogStPoints(String backlogStPoints) {
		this.backlogStPoints = backlogStPoints;
	}
	public String getCompletedStPoints() {
		return completedStPoints;
	}
	public void setCompletedStPoints(String completedStPoints) {
		this.completedStPoints = completedStPoints;
	}
	public String getSprintKey() {
		return sprintKey;
	}
	public void setSprintKey(String sprintKey) {
		this.sprintKey = sprintKey;
	}
	public String getOptimisticValue() {
		return optimisticValue;
	}
	public void setOptimisticValue(String optimisticValue) {
		this.optimisticValue = optimisticValue;
	}
	public String getPesimisticValue() {
		return pesimisticValue;
	}
	public void setPesimisticValue(String pesimisticValue) {
		this.pesimisticValue = pesimisticValue;
	}
	public String getPlannedValue() {
		return plannedValue;
	}
	public void setPlannedValue(String plannedValue) {
		this.plannedValue = plannedValue;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ReleaseBurnDownModel(String sprintName, String backlogStPoints,
			String completedStPoints, String sprintKey, String optimisticValue,
			String pesimisticValue, String plannedValue, String startDate,
			String endDate) {
		super();
		this.sprintName = sprintName;
		this.backlogStPoints = backlogStPoints;
		this.completedStPoints = completedStPoints;
		this.sprintKey = sprintKey;
		this.optimisticValue = optimisticValue;
		this.pesimisticValue = pesimisticValue;
		this.plannedValue = plannedValue;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backlogStPoints == null) ? 0 : backlogStPoints.hashCode());
		result = prime
				* result
				+ ((completedStPoints == null) ? 0 : completedStPoints
						.hashCode());
		result = prime * result
				+ ((sprintKey == null) ? 0 : sprintKey.hashCode());
		result = prime * result
				+ ((sprintName == null) ? 0 : sprintName.hashCode());
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
		ReleaseBurnDownModel other = (ReleaseBurnDownModel) obj;
		if (backlogStPoints == null) {
			if (other.backlogStPoints != null)
				return false;
		} else if (!backlogStPoints.equals(other.backlogStPoints))
			return false;
		if (completedStPoints == null) {
			if (other.completedStPoints != null)
				return false;
		} else if (!completedStPoints.equals(other.completedStPoints))
			return false;
		if (sprintKey == null) {
			if (other.sprintKey != null)
				return false;
		} else if (!sprintKey.equals(other.sprintKey))
			return false;
		if (sprintName == null) {
			if (other.sprintName != null)
				return false;
		} else if (!sprintName.equals(other.sprintName))
			return false;
		return true;
	}
	
	
	
}