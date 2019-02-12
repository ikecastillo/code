package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Emergency Tickets per Release chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReleaseWiseDefectDistributionModel {
	@XmlElement(name = "release")
	private String message1;
	@XmlElement(name = "data")
	private String message3;	 
	
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public String getMessage3() {
		return message3;
	}
	public void setMessage3(String message3) {
		this.message3 = message3;
	}
	
	public ReleaseWiseDefectDistributionModel(String message1, String message3) {
		super();
		this.message1 = message1;
		this.message3 = message3;	
	}

    
}