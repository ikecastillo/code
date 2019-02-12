package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Density Pie chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectDensityPieModel {

	@XmlElement(name = "Severity")
	private String severity;
	@XmlElement(name = "noofdefects")
	private String  noofdefects;
	
	
	@XmlElement(name = "URLForBar")
	private String urlForBar;


	public String getSeverity() {
		return severity;
	}


	public void setSeverity(String severity) {
		this.severity = severity;
	}


	public String getTurnAroundTime() {
		return noofdefects;
	}


	public void setTurnAroundTime(String turnAroundTime) {
		this.noofdefects = turnAroundTime;
	}


	public String getUrlForBar() {
		return urlForBar;
	}


	public void setUrlForBar(String urlForBar) {
		this.urlForBar = urlForBar;
	}


	public DefectDensityPieModel(String severity, String noofdefects,
			String urlForBar) {
		super();
		this.severity = severity;
		this.noofdefects = noofdefects;
		this.urlForBar = urlForBar;
	}
	
		
	
}