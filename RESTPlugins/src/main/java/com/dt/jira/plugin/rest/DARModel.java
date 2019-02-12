package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information from the web service.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "DAR")
@XmlAccessorType(XmlAccessType.FIELD)
public class DARModel {
	/* total no.of valid defects*/
    @XmlElement(name = "AcceptedDefects")
    private String acceptedDefects;
    /* total no.of defects*/
    @XmlElement(name = "TotalDefects")
    private String totalDefects;

    public DARModel() {
    }

    public DARModel(String acceptedDefects, String totalDefects) {
        this.acceptedDefects = acceptedDefects;
		this.totalDefects = totalDefects;
    }

    public String getacceptedDefects() {
        return acceptedDefects;
    }
    public void setacceptedDefects(String acceptedDefects) {
        this.acceptedDefects = acceptedDefects;
    }
    
	public String gettotalDefects() {
        return totalDefects;
    }
    public void settotalDefects(String totalDefects) {
        this.totalDefects = totalDefects;
    }
}