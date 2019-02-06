package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
/**
* Model is defined for CreateCISubtaskRest resource
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateCISubtaskModel {

    //@XmlElement(name = "value")
	
    @XmlElement private String insightObj;
    @XmlElement private String subject;
	@XmlElement private String owner;
	@XmlElement private String issueKey;

    public CreateCISubtaskModel() {
    }
    public CreateCISubtaskModel(String insightObj, String subject,
			String owner, String issueKey) {
		super();
		this.insightObj = insightObj;
		this.subject = subject;
		this.owner = owner;
		this.issueKey = issueKey;
	}
	public String getInsightObj() {
		return insightObj;
	}
	public void setInsightObj(String insightObj) {
		this.insightObj = insightObj;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
}