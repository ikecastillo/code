package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "typevalue")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeModel {
	@XmlElement(name = "label")
	private String label;
	@XmlElement(name = "value")
	private String value;
	@XmlElement(name = "typeId")
	private String typeId;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
	public TypeModel(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	public TypeModel(String label, String value, String typeId) {
		super();
		this.label = label;
		this.value = value;
		this.typeId = typeId;
	}
	
	
	
}
