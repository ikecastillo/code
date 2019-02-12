package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the First Time Fix gadget.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class FirstTimeFixMetricsModel {

	@XmlElement(name = "Severity")
	private String severity;
	@XmlElement(name = "FistTimeFix")
	private String  firstTimeFix;
	@XmlElement(name = "Rework")
	private String rework;	
	@XmlElement(name = "TotalDef")
	private String total;
	
	@XmlElement(name = "URLForTable")
	private String urlForTable;
	@XmlElement(name = "URLForFF")
	private String urlForFF;
	@XmlElement(name = "URLForRework")
	private String urlForRework;
	
	
	public String getUrlForTable() {
		return urlForTable;
	}
	public void setUrlForTable(String urlForTable) {
		this.urlForTable = urlForTable;
	}
	public String getUrlForFF() {
		return urlForFF;
	}
	public void setUrlForFF(String urlForFF) {
		this.urlForFF = urlForFF;
	}
	public String getUrlForRework() {
		return urlForRework;
	}
	public void setUrlForRework(String urlForRework) {
		this.urlForRework = urlForRework;
	}
	public String getFirstTimeFix() {
		return firstTimeFix;
	}
	public void setFirstTimeFix(String firstTimeFix) {
		this.firstTimeFix = firstTimeFix;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getRework() {
		return rework;
	}
	public void setRework(String rework) {
		this.rework = rework;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public FirstTimeFixMetricsModel(String severity, String firstTimeFix,
			String rework, String total, String urlForTable, String urlForFF,
			String urlForRework) {
		super();
		this.severity = severity;
		this.firstTimeFix = firstTimeFix;
		this.rework = rework;
		this.total = total;
		this.urlForTable = urlForTable;
		this.urlForFF = urlForFF;
		this.urlForRework = urlForRework;
	}
	
	
	
	
	
	
	
	
}