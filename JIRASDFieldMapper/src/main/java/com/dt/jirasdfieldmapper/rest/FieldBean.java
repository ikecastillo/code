package com.dt.jirasdfieldmapper.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model POJO used in REST calls for making AO updates w.r.t mappings
 * Created by yagnesh.bhat on 6/24/2015.
 */

@XmlRootElement(name = "fieldBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class FieldBean {

    private String jiraFieldName;
    private String jiraSdFieldName;
    private String jiraSdFieldId;

    public String getJiraFieldName() {
        return jiraFieldName;
    }

    public void setJiraFieldName(String jiraFieldName) {
        this.jiraFieldName = jiraFieldName;
    }

    public String getJiraSdFieldName() {
        return jiraSdFieldName;
    }

    public void setJiraSdFieldName(String jiraSdFieldName) {
        this.jiraSdFieldName = jiraSdFieldName;
    }

    public String getJiraSdFieldId() {
        return jiraSdFieldId;
    }

    public void setJiraSdFieldId(String jiraSdFieldId) {
        this.jiraSdFieldId = jiraSdFieldId;
    }


}
