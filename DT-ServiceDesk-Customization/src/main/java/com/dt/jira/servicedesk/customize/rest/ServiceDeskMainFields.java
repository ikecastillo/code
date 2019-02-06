package com.dt.jira.servicedesk.customize.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ServiceDeskMain")
@XmlAccessorType(XmlAccessType.FIELD)


public class ServiceDeskMainFields {
	
				
	@XmlElement private String name;
		
	@XmlElement private String description;
	
	@XmlElement private int service_id;
	
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() { 
		return description; 
	}   
	public void setDescription(String description ) {
		this.description = description; 
	}
	
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	
	
	public ServiceDeskMainFields(String name,String description){
			this.name = name;
			this.description = description;
	}
		

}
