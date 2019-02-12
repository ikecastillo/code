package com.dt.jira.plugin.rest;


import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the velocity.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScopeVarianceModel {
	@XmlElement(name = "sprint")
	private String message1;
	@XmlElement(name = "comitted")
	private String message2;	
	@XmlElement(name = "completed")
	private String message3;
	@XmlElement(name = "key")
	private String message4;
	@XmlElement(name = "committedurl")
	private String message5;
	@XmlElement(name = "completedurl")
	private String message6;
	@XmlElement(name = "startdate")
	private String message7;
	@XmlElement(name = "enddate")
	private String message8;
	@XmlElement(name = "percentage")
	private String message9;
	@XmlElement(name = "vel_wk")
	private String message10;
	
	
	public String getMessage10() {
		return message10;
	}
	public void setMessage10(String message10) {
		this.message10 = message10;
	}
	public String getMessage9() {
		return message9;
	}
	public void setMessage9(String message9) {
		this.message9 = message9;
	}
	public String getMessage4() {
		return message4;
	}
	public void setMessage4(String message4) {
		this.message4 = message4;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public String getMessage2() {
		return message2;
	}
	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	public String getMessage3() {
		return message3;
	}
	public void setMessage3(String message3) {
		this.message3 = message3;
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
	public ScopeVarianceModel(String message1, String message2,
			String message3, String message4, String message5, String message6) {
		super();
		this.message1 = message1;
		this.message2 = message2;
		this.message3 = message3;
		this.message4 = message4;
		this.message5 = message5;
		this.message6 = message6;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((message1 == null) ? 0 : message1.hashCode());
		result = prime * result
				+ ((message2 == null) ? 0 : message2.hashCode());
		result = prime * result
				+ ((message3 == null) ? 0 : message3.hashCode());
		result = prime * result
				+ ((message4 == null) ? 0 : message4.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScopeVarianceModel other = (ScopeVarianceModel) obj;
		if (message1 == null) {
			if (other.message1 != null)
				return false;
		} else if (!message1.equals(other.message1))
			return false;
		if (message2 == null) {
			if (other.message2 != null)
				return false;
		} else if (!message2.equals(other.message2))
			return false;
		if (message3 == null) {
			if (other.message3 != null)
				return false;
		} else if (!message3.equals(other.message3))
			return false;
		if (message4 == null) {
			if (other.message4 != null)
				return false;
		} else if (!message4.equals(other.message4))
			return false;
		return true;
	}
	
	
	
    
}