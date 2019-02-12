package com.dt.userfromemail.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 4/5/2016.
 */

@XmlRootElement(name = "incidentoutagemodel")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentOutageModel {

    @XmlElement(name = "issueid")
    private String issueid;

    public String getIssueid() {
        return issueid;
    }
    public void setIssueid(String issueid) {
        this.issueid = issueid;
    }

    public IncidentOutageModel(String issueid) {
        super();
        this.issueid = issueid;
    }
}
