package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "restresource")
@XmlAccessorType(XmlAccessType.FIELD)
public class GithubIntegrationRestResourceModel {

    @XmlElement(name = "summary")
    private String summary;
    @XmlElement(name = "issuekey")
    private String issuekey;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIssuekey() {
		return issuekey;
	}
	public void setIssuekey(String issuekey) {
		this.issuekey = issuekey;
	}
	public GithubIntegrationRestResourceModel(String summary, String issuekey) {
		super();
		this.summary = summary;
		this.issuekey = issuekey;
	}
   
    
}