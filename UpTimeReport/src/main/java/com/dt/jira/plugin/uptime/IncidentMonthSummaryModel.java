package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "monthlySummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentMonthSummaryModel {
	@XmlElement(name = "month")
	private String month;
	
	@XmlElement(name = "days")
	String days;
	@XmlElement(name = "hours")
	String hours;
	@XmlElement(name = "minutes")
	String minutes;
	@XmlElement(name = "incidentDurationMinutes")
	String incidentDurationMinutes;
	@XmlElement(name = "upTime")
	String upTime;
	
	public IncidentMonthSummaryModel(String month) {
		super();
		this.month = month;
	}
	
	public IncidentMonthSummaryModel(String days, String hours,
			String minutes, String incidentDurationMinutes, String upTime) {
		
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.incidentDurationMinutes = incidentDurationMinutes;
		this.upTime = upTime;
	}
	public String getDays() {
		return days;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getIncidentDurationMinutes() {
		return incidentDurationMinutes;
	}
	public void setIncidentDurationMinutes(String incidentDurationMinutes) {
		this.incidentDurationMinutes = incidentDurationMinutes;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	
	
}
