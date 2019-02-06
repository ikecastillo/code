package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class EmailConfig
{
  @XmlElement private String userName;
  @XmlElement private String password;
  @XmlElement private String feilds;
  
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
    
    public String getFeilds() {
    	return feilds;
	}
	public void setFeilds(String feilds) {
		this.feilds = feilds;
	}
	
      
 
}
