package com.dt.jira.plugin.innotas.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class SyncInnotasDetailsModel {

    @XmlElement(name = "value")
    private String message;

    public SyncInnotasDetailsModel() {
    }

    public SyncInnotasDetailsModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}