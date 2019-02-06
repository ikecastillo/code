package com.dt.jira.plugin.changeRisk.calculator.rest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "RiskCalculation")
@XmlAccessorType(XmlAccessType.FIELD)
public class RiskCalculationModel {
	@XmlElement(name = "question1")
	private String question1;
	@XmlElement(name = "question2")
	private String question2;
	@XmlElement(name = "question3")
	private String question3;
	@XmlElement(name = "question4")
	private String question4;
	@XmlElement(name = "question5")
	private String question5;
	@XmlElement(name = "question6")
	private String question6;
	@XmlElement(name = "question7")
	private String question7;
	@XmlElement(name = "condition")
	private String condition;
	@XmlElement(name = "result")
	private String result;
	@XmlElement
	private String conditionID;
	
	
	
	
	
	
	public String getQuestion1() {
		return question1;
	}






	public void setQuestion1(String question1) {
		this.question1 = question1;
	}






	public String getQuestion2() {
		return question2;
	}






	public void setQuestion2(String question2) {
		this.question2 = question2;
	}






	public String getQuestion3() {
		return question3;
	}






	public void setQuestion3(String question3) {
		this.question3 = question3;
	}






	public String getQuestion4() {
		return question4;
	}






	public void setQuestion4(String question4) {
		this.question4 = question4;
	}






	public String getQuestion5() {
		return question5;
	}






	public void setQuestion5(String question5) {
		this.question5 = question5;
	}






	public String getQuestion6() {
		return question6;
	}






	public void setQuestion6(String question6) {
		this.question6 = question6;
	}






	public String getQuestion7() {
		return question7;
	}






	public void setQuestion7(String question7) {
		this.question7 = question7;
	}






	public String getCondition() {
		return condition;
	}






	public void setCondition(String condition) {
		this.condition = condition;
	}






	





	public String getResult() {
		return result;
	}






	public void setResult(String result) {
		this.result = result;
	}





	public String getConditionID() {
		return conditionID;
	}






	public void setConditionID(String conditionID) {
		this.conditionID = conditionID;
	}


	
	public RiskCalculationModel(String question1,String question2,String question3,String question4, String condition, String result) {
		super();
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
		this.question5 = "";
		this.question6 = "";
		this.question7 = "";
		this.condition = condition;
		this.result = result;
		
	}
	
}
