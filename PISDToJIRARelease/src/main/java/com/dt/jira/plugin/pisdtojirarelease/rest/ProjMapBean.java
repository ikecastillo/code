package com.dt.jira.plugin.pisdtojirarelease.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 */
@XmlRootElement(name = "subprodvalues")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjMapBean {

    @XmlElement(name = "sdProjName")
    private String sdProjName;

    @XmlElement(name = "sdProjKey")
    private String sdProjKey;

    @XmlElement(name = "jiraProjName")
    private String jiraProjName;

    @XmlElement(name = "jiraProjKey")
    private String jiraProjKey;

    @XmlElement(name = "jiraProjIssueType")
    private String jiraProjIssueType;

    public String getSdProjName() {
        return sdProjName;
    }

    public void setSdProjName(String sdProjName) {
        this.sdProjName = sdProjName;
    }

    public String getSdProjKey() {
        return sdProjKey;
    }

    public void setSdProjKey(String sdProjKey) {
        this.sdProjKey = sdProjKey;
    }

    public String getJiraProjName() {
        return jiraProjName;
    }

    public void setJiraProjName(String jiraProjName) {
        this.jiraProjName = jiraProjName;
    }

    public String getJiraProjKey() {
        return jiraProjKey;
    }

    public void setJiraProjKey(String jiraProjKey) {
        this.jiraProjKey = jiraProjKey;
    }

    public String getJiraProjIssueType() {
        return jiraProjIssueType;
    }

    public void setJiraProjIssueType(String jiraProjIssueType) {
        this.jiraProjIssueType = jiraProjIssueType;
    }

}
