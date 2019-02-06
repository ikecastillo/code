package com.dt.jira.plugin.automatedailycabsummary;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutomateDailyCabRestModel {

    @XmlElement(name = "value")
    private String message;

    public AutomateDailyCabRestModel() {
    }

    public AutomateDailyCabRestModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}