package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "locationvalues")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationsModel {
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
	public LocationsModel(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
			
}
