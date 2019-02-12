package com.dt.userfromemail.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 4/7/2016.
 */

@XmlRootElement(name = "issueBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class IssueBean {

    private String projectkey;
    private String issuetype;
    private String summary;
    private String description;
    private String solutiongroup;
    private String product;
    private String severity;
    private String reporteremail;
    private String customerimpact;

    //New fields added
    private String clientsimpacted;
    private String impacted;
    private String customertalkingpoints;
    private String incidentstart;
    private String incidentsource;

    public String getProjectkey() {
        return projectkey;
    }

    public void setProjectkey(String projectkey) {
        this.projectkey = projectkey;
    }

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolutiongroup() {
        return solutiongroup;
    }

    public void setSolutiongroup(String solutiongroup) {
        this.solutiongroup = solutiongroup;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getReporteremail() {
        return reporteremail;
    }

    public void setReporteremail(String reporteremail) {
        this.reporteremail = reporteremail;
    }

    public String getCustomerimpact() {
        return customerimpact;
    }

    public void setCustomerimpact(String customerimpact) {
        this.customerimpact = customerimpact;
    }

    public String getClientsimpacted() {
        return clientsimpacted;
    }

    public void setClientsimpacted(String clientsimpacted) {
        this.clientsimpacted = clientsimpacted;
    }

    public String getImpacted() {
        return impacted;
    }

    public void setImpacted(String impacted) {
        this.impacted = impacted;
    }

    public String getCustomertalkingpoints() {
        return customertalkingpoints;
    }

    public void setCustomertalkingpoints(String customertalkingpoints) {
        this.customertalkingpoints = customertalkingpoints;
    }

    public String getIncidentstart() {
        return incidentstart;
    }

    public void setIncidentstart(String incidentstart) {
        this.incidentstart = incidentstart;
    }

    public String getIncidentsource() {
        return incidentsource;
    }

    public void setIncidentsource(String incidentsource) {
        this.incidentsource = incidentsource;
    }


}
