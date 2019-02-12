package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Distribution Pie chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "defectdistribution")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectDistributionModel {

    @XmlElement(name = "severity")
    private String severityType;
    @XmlElement(name = "data")
    private int total;
	@XmlElement(name = "url")
	private String url;
    
	public DefectDistributionModel() {
    }
    

    public DefectDistributionModel(String severityType,int total) {
		this(severityType,total,"#");
    }

    public DefectDistributionModel(String severityType,int total, String url) {
        this.severityType = severityType;
        this.total = total;
		this.url = url;
    }

	public String getseverityType() {
		return severityType;
	}

	public void setseverityType(String severityType) {
		this.severityType = severityType;
	}

	public int gettotal() {
		return total;
	}

	public void settotal(int total) {
		this.total = total;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}
    
}