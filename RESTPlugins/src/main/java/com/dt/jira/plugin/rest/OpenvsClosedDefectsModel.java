package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Open vs Closed defects bar chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpenvsClosedDefectsModel {

	@XmlElement(name = "date")
	private String date;
	@XmlElement(name = "open")
	private String open;	
	@XmlElement(name = "closed")
	private String closed;
	@XmlElement(name = "openDrillDownUrl")
	private String openDrillDownUrl;
	@XmlElement(name = "closeDrillDownUrl")
	private String closeDrillDownUrl;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getClosed() {
		return closed;
	}
	public void setClosed(String closed) {
		this.closed = closed;
	}
	
	public String getOpenDrillDownUrl() {
		return openDrillDownUrl;
	}
	public void setOpenDrillDownUrl(String openDrillDownUrl) {
		this.openDrillDownUrl = openDrillDownUrl;
	}
	public String getCloseDrillDownUrl() {
		return closeDrillDownUrl;
	}
	public void setCloseDrillDownUrl(String closeDrillDownUrl) {
		this.closeDrillDownUrl = closeDrillDownUrl;
	}
	public OpenvsClosedDefectsModel(String date, String open, String closed) {
		super();
		this.date = date;
		this.open = open;
		this.closed = closed;
	}
}