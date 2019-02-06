package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ITILChngMgmtResourceModel { 
	
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
	@XmlElement(name = "mngrDetails")
	private String mngrDetails;
	@XmlElement(name = "deptName")
	private String deptName;
	@XmlElement(name = "mngrName")
	private String mngrName;
	@XmlElement(name = "mngrMailId")
	private String mngrMailId;
	@XmlElement(name = "mngrAccountName")
    private String mngrAccountName;
	@XmlElement(name = "reporterTitle")
	private String reporterTitle;
	
	public ITILChngMgmtResourceModel() {}
		
	public ITILChngMgmtResourceModel(String userName,
		String userMailId,
		String phoneNo,
		String deptName,
		String mngrName,
		String mngrAccountName,
		String mngrMailId, String reporterTitle) {
		this.userName= userName;   
		this.userMailId= userMailId;
		this.phoneNo=phoneNo;
		this.deptName= deptName;
		this.mngrName= mngrName;
		this.mngrAccountName=mngrAccountName;
		this.mngrMailId= mngrMailId;
		this.reporterTitle = reporterTitle;
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

	public String getMngrDetails() {
		return mngrDetails;
	}

	public void setMngrDetails(String mngrDetails) {
		this.mngrDetails = mngrDetails;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMngrName() {
		return mngrName;
	}

	public void setMngrName(String mngrName) {
		this.mngrName = mngrName;
	}

	public String getMngrMailId() {
		return mngrMailId;
	}

	public void setMngrMailId(String mngrMailId) {
		this.mngrMailId = mngrMailId;
	}

	public String getMngrAccountName() {
		return mngrAccountName;
	}

	public void setMngrAccountName(String mngrAccountName) {
		this.mngrAccountName = mngrAccountName;
	}

	public String getReporterTitle() {
		return reporterTitle;
	}

	public void setReporterTitle(String reporterTitle) {
		this.reporterTitle = reporterTitle;
	}

	@Override
	public String toString() {
		return new StringBuffer(" firstName : ").append(this.firstName)
				.append(" lastName : ").append(this.lastName)
				.append(" homePhone : ").append(this.homePhone)
				.append(" givenName : ").append(this.givenName) 
				.append(" mobileNo : ").append(this.mobileNo)
				.append(" phoneNo : ").append(this.phoneNo)
				.append(" userName : ").append(this.userName)
				.append(" userMailId : ").append(this.userMailId)				
				.append(" mngrDetails : ").append(this.mngrDetails)
				.append(" deptName : ").append(this.deptName)
				.append(" mngrName : ").append(this.mngrName)
				.append(" mngrAccountName : ").append(this.mngrAccountName)
				.append(" mngrMailId : ").append(this.mngrMailId)
				.append(" reporterTitle : ").append(this.reporterTitle).toString();
	}
}