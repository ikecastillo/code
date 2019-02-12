package com.dt.jira.plugin.pisdtojirarelease.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Surya.Sriram on 7/28/2016.
 * Project  Bean class with setters and Getters for Project Key, Project Naem and Selected value.
 *
 */
@XmlRootElement(name = "jiraProject")
@XmlAccessorType(XmlAccessType.FIELD)
public class JiraProject {

    @XmlElement(name = "projectName")
    public String projectName;

    @XmlElement(name = "projectKey")
    public String projectKey;

    @XmlElement(name = "selected")
    public String selected;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
