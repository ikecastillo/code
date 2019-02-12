package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message2")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentProductMonthlyModel {
	@XmlElement(name = "month")
	private String month;
	@XmlElement(name = "incidentcount")
	private String incidentcount;
	
	@XmlElement(name = "uptime")
	private String uptime;
	
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getIncidentcount() {
		return incidentcount;
	}
	public void setIncidentcount(String incidentcount) {
		this.incidentcount = incidentcount;
	}
	public IncidentProductMonthlyModel(String month, String incidentcount,String uptime) {
		super();
		this.month = month;
		this.incidentcount = incidentcount;
		this.uptime = uptime;
	}
}
