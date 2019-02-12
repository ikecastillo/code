package com.dt.jira.pagerduty.intgt.plugin.rest;

import javax.xml.bind.annotation.*;

/**
 * Model POJO for the Project Fields REST Resource
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectFieldsModel {

    @XmlElement
    private String csvFields;// Project Issue fields

    public ProjectFieldsModel(String csvFields) {
        this.csvFields = csvFields;
    }

    public String getcsvFields() {
        return csvFields;
    }

    public void setcsvFields(String csvFields) {
        this.csvFields = csvFields;
    }

}