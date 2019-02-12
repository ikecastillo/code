package com.dt.jira.xmatters.intgt.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model POJO used in REST calls for making AO updates w.r.t event configurations
 *
 * Created by Yagnesh.Bhat on 10/19/2015.
 */

@XmlRootElement(name = "eventBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class EventBean {

    private String formAPIID;
    private String xMattersTemplateName;
    private String xMattersFormWSURL;
    private String xMattersFormResponseCodeAvailableUUID;
    private String xMattersFormResponseCodeNotAvailableUUID;

    public String getFormAPIID() {
        return formAPIID;
    }

    public void setFormAPIID(String formAPIID) {
        this.formAPIID = formAPIID;
    }

    public String getxMattersTemplateName() {
        return xMattersTemplateName;
    }

    public void setxMattersTemplateName(String xMattersTemplateName) {
        this.xMattersTemplateName = xMattersTemplateName;
    }

    public String getxMattersFormWSURL() {
        return xMattersFormWSURL;
    }

    public void setxMattersFormWSURL(String xMattersFormWSURL) {
        this.xMattersFormWSURL = xMattersFormWSURL;
    }


    public String getxMattersFormResponseCodeAvailableUUID() {
        return xMattersFormResponseCodeAvailableUUID;
    }

    public void setxMattersFormResponseCodeAvailableUUID(String xMattersFormResponseCodeAvailableUUID) {
        this.xMattersFormResponseCodeAvailableUUID = xMattersFormResponseCodeAvailableUUID;
    }

    public String getxMattersFormResponseCodeNotAvailableUUID() {
        return xMattersFormResponseCodeNotAvailableUUID;
    }

    public void setxMattersFormResponseCodeNotAvailableUUID(String xMattersFormResponseCodeNotAvailableUUID) {
        this.xMattersFormResponseCodeNotAvailableUUID = xMattersFormResponseCodeNotAvailableUUID;
    }

}
