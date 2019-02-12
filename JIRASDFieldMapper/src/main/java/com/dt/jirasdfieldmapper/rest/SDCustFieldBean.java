package com.dt.jirasdfieldmapper.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 5/16/2016.
 */
@XmlRootElement(name = "SDCustfieldBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class SDCustFieldBean {

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    private String fieldName;
    private String fieldId;



}
