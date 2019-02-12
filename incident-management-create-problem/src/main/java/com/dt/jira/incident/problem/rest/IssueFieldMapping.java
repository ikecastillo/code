package com.dt.jira.incident.problem.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IssueFieldMapping")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * Rest Model for IssueFieldMappingRest
 * @author kiran.muthoju
 *
 */

public class IssueFieldMapping {
	
	
	@XmlElement private String jiraField;		
	@XmlElement private String mappingField;	
	@XmlElement private String fromIssueType;
	@XmlElement private String toIssueType;
	public String getFromIssueType() {
		return fromIssueType;
	}
	public void setFromIssueType(String fromIssueType) {
		this.fromIssueType = fromIssueType;
	}
	public String getToIssueType() {
		return toIssueType;
	}
	public void setToIssueType(String toIssueType) {
		this.toIssueType = toIssueType;
	}
	public String getJiraField() { 
		return jiraField; 
	}   
	public void setJiraField(String jiraField) { 
		this.jiraField = jiraField; 
	}   
	public String getMappingField() { 
		return mappingField; 
	}   
	public void setMappingField(String mappingField ) {
		this.mappingField = mappingField; 
	}

	public IssueFieldMapping(String fromIssueType,String jiraField,String toIssueType,String mappingField){
		this.fromIssueType = fromIssueType;	
		this.jiraField = jiraField;
		this.toIssueType = toIssueType;
		this.mappingField = mappingField;
	}
		

}
