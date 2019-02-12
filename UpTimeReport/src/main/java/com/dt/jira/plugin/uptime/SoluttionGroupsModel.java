package com.dt.jira.plugin.uptime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "sgvalues")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoluttionGroupsModel {
	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "value")
	private String value;
	public String getLabel() {
		return label;
	}
	
	@XmlElement(name = "month")
	private String month;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	@XmlElement(name = "issueKey")
	private String issueKey;
	
	@XmlElement(name = "minutes")
	private String minutes;
	@XmlElement(name = "uptime")
	private String uptime;
	
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public SoluttionGroupsModel(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	
	public SoluttionGroupsModel(String label, String value,String month,String issueKey,String minutes,String uptime) {
		super();
		this.label = label;
		this.value = value;
		this.month = month;
		this.issueKey = issueKey;
		this.minutes = minutes;
		this.uptime = uptime;
	}
	public SoluttionGroupsModel() {
		// TODO Auto-generated constructor stub
	}
	
}
