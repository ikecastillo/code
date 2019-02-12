package com.dt.jira.ldap.incident.integration.rest;

import javax.xml.bind.annotation.*;

/**
 * The REST model for Incident Management DT Representative Details
 *
 * @author Firoz Khan
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentLdap {

	@XmlElement(name = "userName")
	private String userName;
	@XmlElement(name = "firstName")
	private String firstName;
	@XmlElement(name = "lastName")
	private String lastName;
	@XmlElement(name = "homePhone")
	private String homePhone;
	@XmlElement(name = "givenName")
	private String givenName;
	@XmlElement(name = "mobileNo")
	private String mobileNo;
	@XmlElement(name = "phoneNo")
	private String phoneNo;
	@XmlElement(name = "userMailId")
	private String userMailId;
	
	/**
     * Constructor
     */
	public IncidentLdap() {}
	
	/**
     * Constructor
     * @param userName the String text
     * @param userMailId the String text
     * @param phoneNo the String text
     */

	public IncidentLdap(String userName,
		String userMailId,
		String phoneNo) {
		this.userName= userName;   
		this.userMailId= userMailId;
		this.phoneNo=phoneNo;
	}	
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}


	@Override
	public String toString() {				
				return new StringBuffer(" userName : ").append(this.userName)
				.append(" userMailId : ").append(this.userMailId)
				.append(" phoneNo : ").append(this.phoneNo).toString();
	}
}