package com.dt.jira.xmatters.intgt.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class xMatterRestAPIModel {

	@XmlElement private String issueKey;
	@XmlElement private String eventId;
	@XmlElement private String status;
	@XmlElement private String message;
	

  
	public xMatterRestAPIModel() {
    }

   
    public xMatterRestAPIModel(String issueKey, String status, String message) {
		super();
		this.issueKey = issueKey;
		this.status = status;
		this.message = message;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }
    
    public String getEventId() {
  		return eventId;
  	}

  	public void setEventId(String eventId) {
  		this.eventId = eventId;
  	}

   
}