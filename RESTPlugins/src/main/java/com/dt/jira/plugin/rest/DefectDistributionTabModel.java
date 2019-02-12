package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Distribution table chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "defectdist-table")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectDistributionTabModel {


	@XmlElement(name = "key")
	private String key;
	@XmlElement(name = "url")
	private String url;	
	@XmlElement(name = "percentofdefects")
	private String percentOfDefects;	 
	@XmlElement(name = "noofdefects")
	private String noOfDefects;
	@XmlElement(name = "low")
	private String low;    
	@XmlElement(name = "medium")
	private String medium;
	@XmlElement(name = "high")
	private String high;
	@XmlElement(name = "critical")
	private String critical;
	
	@XmlElement(name = "none")
	private String none;
	
	public String getkey() {
		return key;
	}
	public void setkey(String key) {
		this.key = key;
	}
	public String getpercentOfDefects() {
		return percentOfDefects;
	}
	public void setpercentOfDefects(String percentOfDefects) {
		this.percentOfDefects = percentOfDefects;
	}
	public String getnoOfDefects() {
		return noOfDefects;
	}
	public void setnoOfDefects(String noOfDefects) {
		this.noOfDefects = noOfDefects;
	}
	public String getlow() {
		return low;
	}
	public void setlow(String low) {
		this.low = low;
	}
	public String getmedium() {
		return medium;
	}
	public void setmedium(String medium) {
		this.medium = medium;
	}
	public String gethigh() {
		return high;
	}
	public void sethigh(String high) {
		this.high = high;
	}
	public String getcritical() {
		return critical;
	}
	public void setcritical(String critical) {
		this.critical = critical;
	}
	
	public String getnone() {
		return none;
	}
	public void setnone(String none) {
		this.none = none;
	}

	public DefectDistributionTabModel(String key, String url, String percentOfDefects,
			String noOfDefects, String low, String medium, String high,
			String critical, String none) {
		super();
		this.key = key;
		this.url = url;
		this.percentOfDefects = percentOfDefects;
		this.noOfDefects = noOfDefects;
		this.low = low;
		this.medium = medium;
		this.high = high;
		this.critical = critical;
		this.none = none;
	}
	 
	
}