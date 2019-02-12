package com.dt.jira.plugin.utils;
/**
 * Class is defined to capture the sprint details.
 * @author kiran.muthoju
 *
 */
public class Sprint {
	
	private String id;
	private String name;
	private String startDate;
	private String endDate;
	private String state;
	private String completeDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public Sprint(String id, String name, String startDate, String endDate,
			String state, String completeDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.completeDate = completeDate;
	}
	
	

}
