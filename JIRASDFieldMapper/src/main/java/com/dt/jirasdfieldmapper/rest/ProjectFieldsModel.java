package com.dt.jirasdfieldmapper.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model POJO for the Project Fields REST Resource
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectFieldsModel {

    @XmlElement
    private String projectKey;

    @XmlElement
    private String issueType; // Project Issue Type

    @XmlElement
    private String csvFields;// Project Issue fields

    public ProjectFieldsModel(String projectKey, String issueType, String csvFields) {
        this.projectKey = projectKey;
        this.issueType = issueType;
        this.csvFields = csvFields;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }
    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getcsvFields() {
        return csvFields;
    }

    public void setcsvFields(String csvFields) {
        this.csvFields = csvFields;
    }

}