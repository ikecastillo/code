package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class dtrestModel {

    @XmlElement(name = "value")
    private String message;

    public dtrestModel() {
    }

    public dtrestModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}