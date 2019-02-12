package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "issuetypes")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetIssueTypesModel {
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
	public GetIssueTypesModel(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	
}
