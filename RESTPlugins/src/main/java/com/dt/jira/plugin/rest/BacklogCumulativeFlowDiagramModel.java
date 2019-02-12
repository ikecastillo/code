package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the backlog cumulative flow chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class BacklogCumulativeFlowDiagramModel {

	@XmlElement(name = "date")
	private String date;
	@XmlElement(name = "indefinition")
	private long indefinition;	
	@XmlElement(name = "readyForDevelopment")
	private long readyForDevelopment;
	@XmlElement(name = "inprogress")
	private long inprogress;
	@XmlElement(name = "devComplete")
	private long devComplete;
	@XmlElement(name = "qaTesting")
	private long qaTesting;	
	@XmlElement(name = "qaComplete")
	private long qaComplete;	
	@XmlElement(name = "closed")
	private long closed;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getIndefinition() {
		return indefinition;
	}

	public void setIndefinition(long indefinition) {
		this.indefinition = indefinition;
	}

	public long getReadyForDevelopment() {
		return readyForDevelopment;
	}

	public void setReadyForDevelopment(long readyForDevelopment) {
		this.readyForDevelopment = readyForDevelopment;
	}

	public long getInprogress() {
		return inprogress;
	}

	public void setInprogress(long inprogress) {
		this.inprogress = inprogress;
	}

	public long getDevComplete() {
		return devComplete;
	}

	public void setDevComplete(long devComplete) {
		this.devComplete = devComplete;
	}

	public long getQaTesting() {
		return qaTesting;
	}

	public void setQaTesting(long qaTesting) {
		this.qaTesting = qaTesting;
	}

	public long getQaComplete() {
		return qaComplete;
	}

	public void setQaComplete(long qaComplete) {
		this.qaComplete = qaComplete;
	}

	public long getClosed() {
		return closed;
	}

	public void setClosed(long closed) {
		this.closed = closed;
	}

	public BacklogCumulativeFlowDiagramModel(String date, long indefinition,
			long readyForDevelopment, long inprogress, long devComplete,
			long qaTesting, long qaComplete, long closed) {
		super();
		this.date = date;
		this.indefinition = indefinition;
		this.readyForDevelopment = readyForDevelopment;
		this.inprogress = inprogress;
		this.devComplete = devComplete;
		this.qaTesting = qaTesting;
		this.qaComplete = qaComplete;
		this.closed = closed;
	}
	
}