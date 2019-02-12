package com.dt.jira.create.incident.report.subtask.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FieldMapping")
@XmlAccessorType(XmlAccessType.FIELD)

/* A Model for rest resource  FieldMapingRestAPI */
public class FieldMappingFields {
	
	
	@XmlElement private String jiraField;
		
	@XmlElement private String mappingField;
	
	@XmlElement private String fromIssueType;
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

	@XmlElement private String toIssueType;

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

	public FieldMappingFields(String jiraField,String mappingField,String fromIssueType,String toIssueType){
			this.jiraField = jiraField; 
			this.mappingField = mappingField;
			this.fromIssueType = fromIssueType; 
			this.toIssueType = toIssueType;
	}
		

}
