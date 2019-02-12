package com.dt.jira.pagerduty.intgt.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model POJO used in REST calls for making AO updates w.r.t PagerDuty Service Configurations
 *
 * Created by Yagnesh.Bhat on 5/24/2016
 */

@XmlRootElement(name = "pdServiceBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PDServiceBean {

    private String serviceKey;
    private String serviceName;
    private String clientsImpacted;
    private String impacted;
    private String severities;
    private String ddcProduct;
    private String ddcSubProduct0;
    private String ddcSubProduct1;

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientsImpacted() {
        return clientsImpacted;
    }

    public void setClientsImpacted(String clientsImpacted) {
        this.clientsImpacted = clientsImpacted;
    }


    public String getImpacted() {
        return impacted;
    }

    public void setImpacted(String impacted) {
        this.impacted = impacted;
    }

    public String getSeverities() {
        return severities;
    }

    public void setSeverities(String severities) {
        this.severities = severities;
    }

    public String getDdcProduct() {
        return ddcProduct;
    }

    public void setDdcProduct(String ddcProduct) {
        this.ddcProduct = ddcProduct;
    }

    public String getDdcSubProduct0() {
        return ddcSubProduct0;
    }

    public void setDdcSubProduct0(String ddcSubProduct0) {
        this.ddcSubProduct0 = ddcSubProduct0;
    }

    public String getDdcSubProduct1() {
        return ddcSubProduct1;
    }

    public void setDdcSubProduct1(String ddcSubProduct1) {
        this.ddcSubProduct1 = ddcSubProduct1;
    }



}
