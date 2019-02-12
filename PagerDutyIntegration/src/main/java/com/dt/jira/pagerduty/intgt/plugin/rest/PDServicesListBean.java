package com.dt.jira.pagerduty.intgt.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 5/25/2016.
 */
@XmlRootElement(name = "PDServicesListBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PDServicesListBean {

    private String serviceName;
    private String serviceKey;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }
}
