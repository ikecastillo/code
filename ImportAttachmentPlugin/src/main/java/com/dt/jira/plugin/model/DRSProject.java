package com.dt.jira.plugin.model;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DRSProject {

    private int id;

    private String firstName;

    
    private List<FileAttachment> fileAttachment;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public List<FileAttachment> getFileAttachment() {
		return fileAttachment;
	}

	public void setFileAttachment(List<FileAttachment> fileAttachment) {
		this.fileAttachment = fileAttachment;
	}


    
}