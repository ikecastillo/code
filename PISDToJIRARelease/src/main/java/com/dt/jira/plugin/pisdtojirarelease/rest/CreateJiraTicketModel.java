package com.dt.jira.plugin.pisdtojirarelease.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateJiraTicketModel {

    @XmlElement(name = "value")
    private String message;

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    private String issueKey;

    public CreateJiraTicketModel() {
    }

    public CreateJiraTicketModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}