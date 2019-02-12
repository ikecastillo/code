package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "downtimeSummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentDowntimeSummaryModel {
	
	@XmlElement(name = "products")
	String products;
	
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	@XmlElement(name = "dateOfOcuurence")
	String dateOfOcuurence;
	
	@XmlElement(name = "downtime")
	String downtime;
	
	@XmlElement(name = "reason")
	String reason;

	public String getDateOfOcuurence() {
		return dateOfOcuurence;
	}

	public void setDateOfOcuurence(String dateOfOcuurence) {
		this.dateOfOcuurence = dateOfOcuurence;
	}

	public String getDowntime() {
		return downtime;
	}

	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String getReason() {
		return reason;
	}

	public IncidentDowntimeSummaryModel(String dateOfOcuurence,
			String downtime, String reason) {
		super();
		this.dateOfOcuurence = dateOfOcuurence;
		this.downtime = downtime;
		this.reason = reason;
	}
	
	public IncidentDowntimeSummaryModel(String products,String dateOfOcuurence,
			String downtime, String reason) {
		super();
		this.products = products;
		this.dateOfOcuurence = dateOfOcuurence;
		this.downtime = downtime;
		this.reason = reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
