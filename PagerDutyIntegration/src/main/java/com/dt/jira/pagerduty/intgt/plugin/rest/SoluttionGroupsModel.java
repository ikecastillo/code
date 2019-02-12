package com.dt.jira.pagerduty.intgt.plugin.rest;
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
	
}
