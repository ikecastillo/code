package com.dt.jira.plugin.changeRisk.calculator.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.dt.jira.plugin.changeRisk.calculator.ao.RiskCalculation;


/**
 * RiskCalculationService  Interface 
 * contains methods for RiskCalculation.
 * @author srinadh.garlapati
 *
 */

@Transactional
public interface RiskCalculationService {
	
	RiskCalculation create(String question1,String question2,String question3,String question4, String condition, String result);

	List<RiskCalculation> findAll();

	void deleteByConditionId(String conditionId); 

}
