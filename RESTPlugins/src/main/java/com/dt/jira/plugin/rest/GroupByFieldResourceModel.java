package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupByFieldResourceModel {

    @XmlElement(name = "groupbyfiled")
    private String field;
  

    public GroupByFieldResourceModel(String field){
        this.field = field;
    }

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	
    
}