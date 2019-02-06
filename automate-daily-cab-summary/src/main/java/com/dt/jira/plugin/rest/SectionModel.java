package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.atlassian.jira.issue.Issue;
import java.util.List;
import java.util.Map;
 

@XmlRootElement(name="Section")
@XmlAccessorType(XmlAccessType.FIELD)

public class SectionModel {
	
    @XmlElement private int section_Id;
    
    @XmlElement private String section_name;
		
	@XmlElement private String jql_query;
	
	@XmlElement private String columns;
    
    @XmlElement private String headers;
    
    @XmlElement private java.util.Date create_date;
    
    @XmlElement private  java.util.Date update_date;
    
    @XmlElement private  String template_id;
    
    @XmlElement private  List<Map<String,List<String>>> jql_result;
	
	public int getSection_Id() {
		return section_Id;
	}
	public void setSection_Id(int section_Id) {
		this.section_Id = section_Id;
	}
    
    public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
    
    public String getJql_query() {
		return jql_query;
	}
	public void setJql_query(String jql_query) {
		this.jql_query = jql_query;
	}
    
    public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
    
    public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
    
    public java.util.Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(java.util.Date create_date) {
		this.create_date = create_date;
	}
    
    public java.util.Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(java.util.Date update_date) {
		this.update_date = update_date;
	}
    
     public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
    
     public List<Map<String,List<String>>> getJql_result() {
		return jql_result;
	}
	public void setJql_result(List<Map<String,List<String>>> jql_result) {
		this.jql_result = jql_result;
	}
    
    
   
	
	public SectionModel(String section_name,String jql_query,String columns,String headers,java.util.Date create_date ,java.util.Date update_date,String template_id){
			this.section_name = section_name;
			this.update_date = update_date;
            this.create_date=create_date;
            this.columns = columns;
            this.headers=headers;
			this.jql_query = jql_query;
            this.template_id=template_id;
            
	}
    
    
    public SectionModel(int section_Id,String section_name,String jql_query,String columns,String headers,java.util.Date create_date ,java.util.Date update_date,String template_id){
        this.section_Id=section_Id;
			this.section_name = section_name;
			this.update_date = update_date;
            this.create_date=create_date;
            this.columns = columns;
            this.headers=headers;
			this.jql_query = jql_query;
            this.template_id=template_id;
            
	}
		

}
