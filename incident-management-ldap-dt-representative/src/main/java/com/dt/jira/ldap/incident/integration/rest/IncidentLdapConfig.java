package com.dt.jira.ldap.incident.integration.rest;

import javax.xml.bind.annotation.*;

/**
 * The REST model for Incident Management LDAP Configuration
 *
 * @author Firoz Khan
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class IncidentLdapConfig {

	  @XmlElement private String ldapInitCtx;
	  @XmlElement private String ldapServerName;
	  @XmlElement private String ldapBaseDn;
	  @XmlElement private String ldapUid;
	  @XmlElement private String ldapPwd;
	  
	    public String getLdapInitCtx() {
	    	return ldapInitCtx;
		}
		public void setLdapInitCtx(String ldapInitCtx) {
			this.ldapInitCtx = ldapInitCtx;
		}
		public String getLdapServerName() {
			return ldapServerName;
		}
		public void setLdapServerName(String ldapServerName) {
			this.ldapServerName = ldapServerName;
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
}