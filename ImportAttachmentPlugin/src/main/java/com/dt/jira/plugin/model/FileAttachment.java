package com.dt.jira.plugin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileAttachment {
	
	@XmlAttribute
	private String id;
	
	@XmlAttribute
	private String issue;
	
	@XmlAttribute
	private String mimetype;
	
	@XmlAttribute
	private String filename;
		  
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getMimetype() {
		return mimetype;
	}
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
		
	
}
