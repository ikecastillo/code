package com.dt.jira.servicedesk.customize.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class ServiceDeskLdap
{
  @XmlElement private String ldapInitCtx;
  @XmlElement private String ldapSrvrName;
  @XmlElement private String ldapBaseDn;
  @XmlElement private String ldapUid;
  @XmlElement private String ldapPwd;
  @XmlElement private String dtincLdapPwd;

    public String getLdapInitCtx() {
    	return ldapInitCtx;
	}
	public void setLdapInitCtx(String ldapInitCtx) {
		this.ldapInitCtx = ldapInitCtx;
	}
	public String getLdapSrvrName() {
		return ldapSrvrName;
	}
	public void setLdapSrvrName(String ldapSrvrName) {
		this.ldapSrvrName = ldapSrvrName;
	}
	public String getLdapBaseDn() {
		return ldapBaseDn;
	}
	public void setLdapBaseDn(String ldapBaseDn) {
		this.ldapBaseDn = ldapBaseDn;
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

	public String getDtincLdapPwd() {
		return dtincLdapPwd;
	}

	public void setDtincLdapPwd(String dtincLdapPwd) {
		this.dtincLdapPwd = dtincLdapPwd;
	}
}
