package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Density chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "defectdensity")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectDensityModel {

  @XmlElement(name = "defectdensity")
  private String density;
  @XmlElement(name = "totQA")
  private String totQA;
  @XmlElement(name = "totStories")
  private String totStories;
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getTotQA() {
		return totQA;
	}
	public void setTotQA(String totQA) {
		this.totQA = totQA;
	}
	public String getTotStories() {
		return totStories;
	}
	public void setTotStories(String totStories) {
		this.totStories = totStories;
	}
public DefectDensityModel(String density, String totQA, String totStories) {
	super();
	this.density = density;
	this.totQA = totQA;
	this.totStories = totStories;
}
  
  

    
}