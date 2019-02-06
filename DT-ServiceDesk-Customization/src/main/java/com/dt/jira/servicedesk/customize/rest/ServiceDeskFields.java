package com.dt.jira.servicedesk.customize.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServiceDesk")
@XmlAccessorType(XmlAccessType.FIELD)


public class ServiceDeskFields {
	
	
	@XmlElement private String icon;
		
	@XmlElement private String item;
	
	@XmlElement private String groups;
	
	@XmlElement private String url;
	
	@XmlElement private String servicedesks;
	
	@XmlElement private int serviceID;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public String getGroups() { 
		return groups; 
	}   
	public void setGroups(String groups) { 
		this.groups = groups; 
	}   
	public String getUrl() { 
		return url; 
	}   
	public void setUrl(String url ) {
		this.url = url; 
	}
	
	public String getServiceDesks() { 
		return servicedesks; 
	}   
	public void setServiceDesks(String servicedesks ) {
		this.servicedesks = servicedesks; 
	}

	public ServiceDeskFields(String icon,String item,String groups,String url,String servicedesks){
			this.icon = icon;
			this.item = item;
			this.groups = groups; 
			this.url = url; 
			this.servicedesks = servicedesks; 
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	
	
		

}
