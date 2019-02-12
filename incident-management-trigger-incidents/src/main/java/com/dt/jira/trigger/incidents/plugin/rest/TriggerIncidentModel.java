package com.dt.jira.trigger.incidents.plugin.rest;


import java.util.List;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the trigger incidents.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "incidentexecsummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class TriggerIncidentModel implements Comparable<TriggerIncidentModel>{
	
	
	@XmlElement(name = "solutionGroup")
	public String solutionGroup;	 
	
	@XmlElement(name = "drillDown")
	private String drillDown;	 
	
	@XmlElement(name = "monthly")
	private List<TriggerIncidentMonthlyModel> monthly;
	
	
	public String getSolutionGroup() {
		return solutionGroup;
	}


	public void setSolutionGroup(String solutionGroup) {
		this.solutionGroup = solutionGroup;
	}


	public List<TriggerIncidentMonthlyModel> getMonthly() {
		return monthly;
	}


	public void setMonthly(List<TriggerIncidentMonthlyModel> monthly) {
		this.monthly = monthly;
	}


	public String getDrillDown() {
		return drillDown;
	}


	public void setDrillDown(String drillDown) {
		this.drillDown = drillDown;
	}


	public TriggerIncidentModel() {
		super();		
	}	
	
	
	@Override
	public int compareTo(TriggerIncidentModel o) {	
			if(o.solutionGroup.equals("Major Incidents")){
				return 1;
			}
			int solgroupValue = this.solutionGroup.compareTo(o.solutionGroup);			
		return solgroupValue;
	}	
	
	
	
}