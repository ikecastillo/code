package com.dt.jira.plugin.servicedesk.standardbauchange.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class ITSMCHGConfig
{
	@XmlElement private String chgUrl;
	@XmlElement private String remoteLinkUrl;
	@XmlElement private String applinkId;
	@XmlElement private String templatelinkURL;
	@XmlElement private String itsmUserName;
	@XmlElement private String itsmPassword;
  
	@XmlElement private String ldapSrvrname;
	@XmlElement private String ldapBasedn;
	@XmlElement private String ldapUid;
	@XmlElement private String ldapPwd;
	
  
    public String getLdapSrvrname() {
		return ldapSrvrname;
	}
	public void setLdapSrvrname(String ldapSrvrname) {
		this.ldapSrvrname = ldapSrvrname;
	}
	public String getLdapBasedn() {
		return ldapBasedn;
	}
	public void setLdapBasedn(String ldapBasedn) {
		this.ldapBasedn = ldapBasedn;
	}
	public String getLdapUid() {
		return ldapUid;
	}
	public void setLdapUid(String ldapUid) {
		this.ldapUid = ldapUid;
	}
	public String getLdapPwd() {
		return ldapPwd;
	}
	public void setLdapPwd(String ldapPwd) {
		this.ldapPwd = ldapPwd;
	}
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
	
    public String getTemplatelinkURL() {
    	return templatelinkURL;
	}
	public void setTemplatelinkURL(String templatelinkURL) {
		this.templatelinkURL = templatelinkURL;
	} 
	
	public String getItsmUserName() {
    	return itsmUserName;
	}
	public void setItsmUserName(String itsmUserName) {
		this.itsmUserName = itsmUserName;
	} 
	
	public String getItsmPassword() {
    	return itsmPassword;
	}
	public void setItsmPassword(String itsmPassword) {
		this.itsmPassword = itsmPassword;
	} 
 
}
