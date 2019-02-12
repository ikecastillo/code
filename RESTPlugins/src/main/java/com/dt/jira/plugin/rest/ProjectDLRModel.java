package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the project DLR chart.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDLRModel {

	@XmlElement(name = "field")
	private String field;
	@XmlElement(name = "qadefects")
	private String  qadefects;
	@XmlElement(name = "proddefects")
	private String proddefects;	
	@XmlElement(name = "TotalDef")
	private String total;
	
	@XmlElement(name = "URLForTable")
	private String urlForTable;
	@XmlElement(name = "URLForQA")
	private String urlForQA;
	@XmlElement(name = "URLForProd")
	private String urlForProd;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getQadefects() {
		return qadefects;
	}
	public void setQadefects(String qadefects) {
		this.qadefects = qadefects;
	}
	public String getProddefects() {
		return proddefects;
	}
	public void setProddefects(String proddefects) {
		this.proddefects = proddefects;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUrlForTable() {
		return urlForTable;
	}
	public void setUrlForTable(String urlForTable) {
		this.urlForTable = urlForTable;
	}

	public String getUrlForQA() {
		return urlForQA;
	}
	public void setUrlForQA(String urlForQA) {
		this.urlForQA = urlForQA;
	}
	public String getUrlForProd() {
		return urlForProd;
	}
	public void setUrlForProd(String urlForProd) {
		this.urlForProd = urlForProd;
	}
	public ProjectDLRModel(String field, String qadefects, String proddefects,
			String total, String urlForTable, String uRLForQA,
			String urlForProd) {
		super();
		this.field = field;
		this.qadefects = qadefects;
		this.proddefects = proddefects;
		this.total = total;
		this.urlForTable = urlForTable;
		urlForQA = uRLForQA;
		this.urlForProd = urlForProd;
	}
	
}