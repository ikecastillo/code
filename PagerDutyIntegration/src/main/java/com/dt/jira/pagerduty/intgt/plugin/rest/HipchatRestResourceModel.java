package com.dt.jira.pagerduty.intgt.plugin.rest;


import javax.xml.bind.annotation.*;
@XmlRootElement(name = "hipchatrestresource")
@XmlAccessorType(XmlAccessType.FIELD)
public class HipchatRestResourceModel {
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
	
	public HipchatRestResourceModel(String message1, String message3) {
		super();
		this.message1 = message1;
		this.message3 = message3;	
	}

    
}