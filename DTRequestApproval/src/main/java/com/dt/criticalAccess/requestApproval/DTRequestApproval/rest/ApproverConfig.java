package com.dt.criticalAccess.requestApproval.DTRequestApproval.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class ApproverConfig
{
  @XmlElement private String chgUrl;
  @XmlElement private String remoteLinkUrl;
  @XmlElement private String applinkId;
  
    public String getChgUrl() {
    	return chgUrl;
	}
	public void setChgUrl(String chgUrl) {
		this.chgUrl = chgUrl;
	}
    
    
    
    public String getRemoteLinkUrl() {
    	return remoteLinkUrl;
	}
	public void setRemoteLinkUrl(String remoteLinkUrl) {
		this.remoteLinkUrl = remoteLinkUrl;
	}
    
    
    
    public String getApplinkId() {
    	return applinkId;
	}
	public void setApplinkId(String applinkId) {
		this.applinkId = applinkId;
	}
	
      
 
}
