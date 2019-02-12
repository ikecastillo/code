package com.dt.jira.plugin.rest;


import java.util.List;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Emergency Tickets per Release chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "incidentexecsummary")
@XmlAccessorType(XmlAccessType.FIELD)
 public class IncidentExecSummaryModel implements Comparable<IncidentExecSummaryModel>{
	
	
	@XmlElement(name = "solutionGroup")
	public String solutionGroup;	 
	
	@XmlElement(name = "drillDown")
	private String drillDown;	 
	
	@XmlElement(name = "monthly")
	private List<IncidentExecMonthlyModel> monthly;
	
	
	public String getSolutionGroup() {
		return solutionGroup;
	}


	public void setSolutionGroup(String solutionGroup) {
		this.solutionGroup = solutionGroup;
	}


	public List<IncidentExecMonthlyModel> getMonthly() {
		return monthly;
	}


	public void setMonthly(List<IncidentExecMonthlyModel> monthly) {
		this.monthly = monthly;
	}


	public String getDrillDown() {
		return drillDown;
	}


	public void setDrillDown(String drillDown) {
		this.drillDown = drillDown;
	}


	public IncidentExecSummaryModel() {
		super();		
	}


	@Override
	public int compareTo(IncidentExecSummaryModel o) {	
			if(o.solutionGroup.equals("Major Incidents") || o.solutionGroup.equals("Major Problems")){
				return 1;
			}
			int solgroupValue = this.solutionGroup.compareTo(o.solutionGroup);			
		return solgroupValue;
	}	
	
	
	
	
}