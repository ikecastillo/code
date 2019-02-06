package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "addDeploymentInst")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddDeploymentInstructionsModel {
	@XmlElement(name = "cfid")
	private String cfid;
	@XmlElement(name = "cfvalue")
	private String cfvalue;
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getCfvalue() {
		return cfvalue;
	}
	public void setCfvalue(String cfvalue) {
		this.cfvalue = cfvalue;
	}
	public AddDeploymentInstructionsModel(String cfid, String cfvalue) {
		super();
		this.cfid = cfid;
		this.cfvalue = cfvalue;
	}
	
	
	
	
	
}
