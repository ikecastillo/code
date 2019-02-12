package com.dt.jira.plugin.rest;

import java.util.List;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectInfoModel {

	@XmlElement(name = "label")
    private String label;
    @XmlElement(name = "options")
    private List<ProjectInfo> projectInfo;
   
    public ProjectInfoModel() {
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<ProjectInfo> getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(List<ProjectInfo> projectInfo) {
		this.projectInfo = projectInfo;
	}

	public ProjectInfoModel(String label, List<ProjectInfo> projectInfo) {
		super();
		this.label = label;
		this.projectInfo = projectInfo;
	}




   
}