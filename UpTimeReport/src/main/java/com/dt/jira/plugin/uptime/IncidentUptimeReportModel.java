package com.dt.jira.plugin.uptime;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "incidentuptimereport")
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentUptimeReportModel {
	

	@XmlElement(name = "monthly")
	private List<IncidentMonthSummaryModel> monthly;
	
	@XmlElement(name = "dataType")
	private String dataType;
	
	@XmlElement(name = "downtimeSumm")
	private List<IncidentDowntimeSummaryModel> downtimeSumm;
	
	
	@XmlElement(name = "minute")
	private String minute;
	
	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	@XmlElement(name = "uptime")
	private String uptime;
	
	@XmlElement(name = "tableHeader")
	private String tableHeader;
	
	
	
	public String getTableHeader() {
		return tableHeader;
	}


	public String getIncidentcount() {
		return incidentcount;
	}

	public void setIncidentcount(String incidentcount) {
		this.incidentcount = incidentcount;
	}
	@XmlElement(name = "incidentcount")
	private String incidentcount;
	
	@XmlElement(name = "product")
	private List<SoluttionGroupsModel> product;

	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public List<IncidentMonthSummaryModel> getMonthly() {
		return monthly;
	}

	public void setMonthly(List<IncidentMonthSummaryModel> monthly) {
		this.monthly = monthly;
	}

	public IncidentUptimeReportModel(List<IncidentMonthSummaryModel> monthly) {
		super();
		this.monthly = monthly;
	}


	public List<IncidentDowntimeSummaryModel> getDowntimeSumm() {
		return downtimeSumm;
	}

	public void setDowntimeSumm(List<IncidentDowntimeSummaryModel> downtimeSumm) {
		this.downtimeSumm = downtimeSumm;
	}
	
	public IncidentUptimeReportModel(List<IncidentMonthSummaryModel> monthly,String dataType, List<IncidentDowntimeSummaryModel> downtime) {
		super();
		this.monthly = monthly;
		this.dataType = dataType;
		this.downtimeSumm = downtime;
	}
	
	@XmlElement(name = "products")
	private String products;	
	
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}
	
	@XmlElement(name = "headers")
	private String headers;	
	
	

	public List<SoluttionGroupsModel> getProduct() {
		return product;
	}

	public void setProduct(List<SoluttionGroupsModel> product) {
		this.product = product;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	@XmlElement(name = "months")
	private List<IncidentProductMonthlyModel> months;
	

	public List<IncidentProductMonthlyModel> getMonths() {
		return months;
	}

	public void setMonths(List<IncidentProductMonthlyModel> months) {
		this.months = months;
	}

	public IncidentUptimeReportModel() {
		super();		
	}
	
	public IncidentUptimeReportModel(List<IncidentProductMonthlyModel> months,String headers) {
		super();
		this.months = months;
		this.headers = headers;
	}

	public void setTableHeader(String tableHeader) {
		this.tableHeader = tableHeader;
		
	}
	

	
}
