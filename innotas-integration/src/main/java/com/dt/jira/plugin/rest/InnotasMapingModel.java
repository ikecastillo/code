package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the
 *  Pie Chart - % of Innotas\Jira Project mappings.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class InnotasMapingModel {

	@XmlElement(name = "mapped")
	private long mapped;
	@XmlElement(name = "notMapped")
	private long  notMapped;
	public long getMapped() {
		return mapped;
	}
	public void setMapped(long mapped) {
		this.mapped = mapped;
	}
	public long getNotMapped() {
		return notMapped;
	}
	public void setNotMapped(long notMapped) {
		this.notMapped = notMapped;
	}
	public InnotasMapingModel(long mapped, long notMapped) {
		super();
		this.mapped = mapped;
		this.notMapped = notMapped;
	}
	
	
}