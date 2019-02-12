package com.dt.jira.plugin.rest;
import javax.xml.bind.annotation.*;
/**
 * This class is Model class which is persist the information about the Defect Density Agile gadget.
 * @author kiran.muthoju
 *
 */
@XmlRootElement(name = "defectdensityagile")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefectDensityAgileModel {

  @XmlElement(name = "data")
  private String value;

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

public DefectDensityAgileModel(String value) {
	super();
	this.value = value;
}
  
  
}