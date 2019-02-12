package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Turn Around gadget.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectTurnAroundTimeModel {

	@XmlElement(name = "Severity")
	private String severity;
	@XmlElement(name = "turnaroundtime")
	private String  turnAroundTime;
	
	
	@XmlElement(name = "URLForBar")
	private String urlForBar;


	public String getSeverity() {
		return severity;
	}


	public void setSeverity(String severity) {
		this.severity = severity;
	}


	public String getTurnAroundTime() {
		return turnAroundTime;
	}


	public void setTurnAroundTime(String turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}


	public String getUrlForBar() {
		return urlForBar;
	}


	public void setUrlForBar(String urlForBar) {
		this.urlForBar = urlForBar;
	}


	public DefectTurnAroundTimeModel(String severity, String turnAroundTime,
			String urlForBar) {
		super();
		this.severity = severity;
		this.turnAroundTime = turnAroundTime;
		this.urlForBar = urlForBar;
	}
	
		
	
}