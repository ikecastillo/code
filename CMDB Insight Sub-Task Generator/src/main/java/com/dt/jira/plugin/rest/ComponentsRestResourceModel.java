package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
/**
* Model is defined for ComponentsRestResource resource
*/
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComponentsRestResourceModel {

    @XmlElement(name = "value")
    private String message;

    public ComponentsRestResourceModel() {
    }

    public ComponentsRestResourceModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}