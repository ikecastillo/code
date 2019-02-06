package com.dt.jira.gadget.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssignedToSelectedGroupResourceModel {

	@XmlElement private String label;
	@XmlElement private String value;
	
	public AssignedToSelectedGroupResourceModel() {
    }

    public AssignedToSelectedGroupResourceModel(String label,String value) {
        this.label = label;
        this.value = value;
    }
    
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
	
	@Override
	public String toString() {
		return new StringBuffer(" label : ").append(this.label)
				.append(" value : ").append(this.value).toString();
	}
}