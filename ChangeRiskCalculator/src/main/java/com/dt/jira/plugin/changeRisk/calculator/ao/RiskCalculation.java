package com.dt.jira.plugin.changeRisk.calculator.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * This entity class is used for Risk calculation.
 * 
 * @author Srinadh.G
 */
@Preload
public interface RiskCalculation extends Entity {

	String getQuestion1();

	void setQuestion1(String question1);
	
	
	String getQuestion2();

	void setQuestion2(String question2);
	
	
	String getQuestion3();

	void setQuestion3(String question3);
	
	
	String getQuestion4();

	void setQuestion4(String question4);
	
	
	String getQuestion5();

	void setQuestion5(String question5);
	
	
	String getQuestion6();

	void setQuestion6(String question6);
	
	
	String getQuestion7();

	void setQuestion7(String question7);
	
	
	String getCondition();

	void setCondition(String condition);
	
	String getResult();

	void setResult(String result);
	
	


}