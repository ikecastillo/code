package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class SyncCommentsModel {

    @XmlElement(name = "value")
    private String message;

    public SyncCommentsModel() {
    }

    public SyncCommentsModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}