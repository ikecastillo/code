package com.dt.jira.plugin.standardbauchange.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class ServiceDeskConfig
{
	@XmlElement private String serviceDeskUrl;
	@XmlElement private String userName;
	@XmlElement private String password;
	
	
  
    public String getServiceDeskUrl() {
		return serviceDeskUrl;
	}
	public void setServiceDeskUrl(String serviceDeskUrl) {
		this.serviceDeskUrl = serviceDeskUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
 
}
