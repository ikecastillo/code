package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "addlinkedissues")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddLinkedIssuesModel {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "name")
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public AddLinkedIssuesModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
}
