package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Template")
@XmlAccessorType(XmlAccessType.FIELD)


public class TemplateModel {
	
    
    @XmlElement private int template_Id;
    
	@XmlElement private String template_name;
		
	@XmlElement private String mail_to;
    
    @XmlElement private String mail_from;
    
    @XmlElement private String subject;
    
    @XmlElement private String mail_body;
    
    @XmlElement private String schedule_time;
    
    
    public int getTemplate_Id() {
		return template_Id;
	}
	public void setTemplate_name(int template_Id) {
		this.template_Id = template_Id;
	}
	
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
    
   public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
    
    
     public String getMail_from() {
		return mail_from;
	}
	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}
    
    
     public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    
     public String getMail_body() {
		return mail_body;
	}
	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}
    
    public String getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(String schedule_time) {
		this.schedule_time = schedule_time;
	}
    
        

    
    
	
	/*public TemplateModel(String template_name,String mail_to){
			this.template_name = template_name;
			this.mail_to = mail_to;
            
	}*/
    
    
    public TemplateModel(String template_name,String mail_to,String mail_from,String subject,String mail_body){
			this.template_name = template_name;
			this.mail_to = mail_to;
        this.mail_from = mail_from;
        this.subject = subject;
        this.mail_body = mail_body;
            
	}
    
    
    public TemplateModel(int template_Id,String template_name,String mail_to,String mail_from,String subject,String mail_body,String schedule_time){
            this.template_Id=template_Id;
			this.template_name = template_name;
			this.mail_to = mail_to;
            this.mail_from = mail_from;
            this.subject = subject;
            this.mail_body = mail_body;
            this.schedule_time= schedule_time;
            
	}
		

}
