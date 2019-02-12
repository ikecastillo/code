package com.dt.jira.trigger.incidents.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "message2")
@XmlAccessorType(XmlAccessType.FIELD)
public class  TriggerIncidentMonthlyModel {
	@XmlElement(name = "month")
	private String month;
	@XmlElement(name = "incidentcount")
	private String incidentcount;
	
	@XmlElement(name = "drillDown")
	private String drillDown;
	
	
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
	public TriggerIncidentMonthlyModel(String month, String incidentcount) {
		super();
		this.month = month;
		this.incidentcount = incidentcount;
	}
	public String getDrillDown() {
		return drillDown;
	}
	public void setDrillDown(String drillDown) {
		this.drillDown = drillDown;
	}
	
	
}
