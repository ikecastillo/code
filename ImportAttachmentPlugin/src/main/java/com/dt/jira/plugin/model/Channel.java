package com.dt.jira.plugin.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {
	
	 private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	 
}
