package com.dt.jira.plugin.genericbarchart.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 8/14/2015.
 */
@XmlRootElement(name = "message3")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericModel {
    @XmlElement(name = "xAxis")
    private String xAxis;

    @XmlElement(name = "countOrSum")
    private String countOrSum;

    @XmlElement(name = "drillDown")
    private String drillDown;

    public String getXAxis() {
        return xAxis;
    }
    public void setXAxis(String xAxis) {
        this.xAxis = xAxis;
    }
    public String getCountOrSum() {
        return countOrSum;
    }
    public void setCountOrSum(String countOrSum) {
        this.countOrSum = countOrSum;
    }
    public GenericModel(String xAxis, String countOrSum) {
        super();
        this.xAxis = xAxis;
        this.countOrSum = countOrSum;
    }
    public String getDrillDown() {
        return drillDown;
    }
    public void setDrillDown(String drillDown) {
        this.drillDown = drillDown;
    }


}
