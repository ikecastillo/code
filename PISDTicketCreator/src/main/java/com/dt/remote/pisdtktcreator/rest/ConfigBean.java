package com.dt.remote.pisdtktcreator.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 4/18/2016.
 */
@XmlRootElement(name = "configBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ConfigBean {
    private String serviceDeskID;
    private String serviceDeskName;
    private String requestTypeID;
    private String requestTypeName;
    private String custFieldsWithIDs;


    public String getServiceDeskID() {
        return serviceDeskID;
    }

    public void setServiceDeskID(String serviceDeskID) {
        this.serviceDeskID = serviceDeskID;
    }

    public String getRequestTypeID() {
        return requestTypeID;
    }

    public String getServiceDeskName() {
        return serviceDeskName;
    }

    public void setServiceDeskName(String serviceDeskName) {
        this.serviceDeskName = serviceDeskName;
    }


    public void setRequestTypeID(String requestTypeID) {
        this.requestTypeID = requestTypeID;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getCustFieldsWithIDs() {
        return custFieldsWithIDs;
    }

    public void setCustFieldsWithIDs(String custFieldsWithIDs) {
        this.custFieldsWithIDs = custFieldsWithIDs;
    }




}
