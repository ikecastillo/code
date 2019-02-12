package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentDemoModel {

    @XmlElement(name = "value")
    private String message;

    public AttachmentDemoModel() {
    }

    public AttachmentDemoModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}