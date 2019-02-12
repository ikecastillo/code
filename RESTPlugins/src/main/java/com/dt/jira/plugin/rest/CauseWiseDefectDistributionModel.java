package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "defectdistribution")
@XmlAccessorType(XmlAccessType.FIELD)
public class CauseWiseDefectDistributionModel {


	@XmlElement(name = "cause")
	private String message1;
	@XmlElement(name = "percentofdefects")
	private String message3;	 
	@XmlElement(name = "noofdefects")
	private String message4;
	@XmlElement(name = "low")
	private String message5;    
	@XmlElement(name = "medium")
	private String message6;
	@XmlElement(name = "high")
	private String message7;
	@XmlElement(name = "critical")
	private String message8;
	
	@XmlElement(name = "none")
	private String message9;
	
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public String getMessage3() {
		return message3;
	}
	public void setMessage3(String message3) {
		this.message3 = message3;
	}
	public String getMessage4() {
		return message4;
	}
	public void setMessage4(String message4) {
		this.message4 = message4;
	}
	public String getMessage5() {
		return message5;
	}
	public void setMessage5(String message5) {
		this.message5 = message5;
	}
	public String getMessage6() {
		return message6;
	}
	public void setMessage6(String message6) {
		this.message6 = message6;
	}
	public String getMessage7() {
		return message7;
	}
	public void setMessage7(String message7) {
		this.message7 = message7;
	}
	public String getMessage8() {
		return message8;
	}
	public void setMessage8(String message8) {
		this.message8 = message8;
	}
	
	public String getMessage9() {
		return message9;
	}
	public void setMessage9(String message9) {
		this.message9 = message9;
	}

	public CauseWiseDefectDistributionModel(String message1, String message3,
			String message4, String message5, String message6, String message7,
			String message8, String message9) {
		super();
		this.message1 = message1;
		this.message3 = message3;
		this.message4 = message4;
		this.message5 = message5;
		this.message6 = message6;
		this.message7 = message7;
		this.message8 = message8;
		this.message9 = message9;
	}
	 
	
}