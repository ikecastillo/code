package com.dt.jira.plugin.changeRisk.calculator.service;

import java.util.List;

import com.dt.jira.plugin.changeRisk.calculator.ao.RiskCalculation;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.jira.plugin.changeRisk.calculator.service.RiskCalculationService;


/**
 * RiskCalculationService  Interface 
 * contains methods for RiskCalculation.
 * @author srinadh.garlapati
 *
 */
public class RiskCalculationServiceImpl implements RiskCalculationService{
	
	
	
	private final Logger logger = Logger.getLogger(RiskCalculationServiceImpl.class); 
	 
	private final ActiveObjects ao; 

	/**
	 * Constructor
	 * 
	 * @param ao
	 *            the ActiveObjects to be injected
	 */
	public RiskCalculationServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}
	
	/**
	 * creating RiskCalculation condition.
	 */
	@Override
	public RiskCalculation create(String question1,String question2,String question3,String question4,String condition, String result){

		// TODO Auto-generated method stub
		RiskCalculation riskCalculationConfiguration = null;
			if (isEmptyCheck(result) ) {
			riskCalculationConfiguration = ao.create(RiskCalculation.class);
			logger.info("Successfully created");
			riskCalculationConfiguration.setQuestion1(question1);
			riskCalculationConfiguration.setQuestion2(question2);
			riskCalculationConfiguration.setQuestion3(question3);
			riskCalculationConfiguration.setQuestion4(question4);
			riskCalculationConfiguration.setQuestion5("");
			riskCalculationConfiguration.setQuestion6("");
			riskCalculationConfiguration.setQuestion7("");
			
			riskCalculationConfiguration.setCondition(condition);
			riskCalculationConfiguration.setResult(result);
			riskCalculationConfiguration.save();
		}else{
		//validation messages need to thrown	
		}
		logger.info("saveMapping-----------------------Successfully updated------------------------- ");
		return riskCalculationConfiguration;	
		}

	/**
	 * find all risk calculation conditions
	 */
	@Override
	public List<RiskCalculation> findAll() {
		// TODO Auto-generated method stub
		
		 return newArrayList(ao.find(RiskCalculation.class));
	}

	/**
	 * delete Risk calculation condition By ConditionId
	 */
	@Override
	public void deleteByConditionId(String conditionId) {
		// TODO Auto-generated method stub
		
		Query query = Query.select();
		query = query
				.where("ID=? ",conditionId);
		List<RiskCalculation> riskList = newArrayList(ao.find(RiskCalculation.class,query));
		if (riskList != null && riskList.size() > 0) {
			RiskCalculation riskObj=riskList.get(0)	;
			logger.info("-----------------------Successfully found element------------------------- "+riskObj);
			logger.info("-----------------------Successfully found element------------------------- "+riskObj);
			ao.delete(riskObj);
		}else{
			logger.info("-----------------------no element found ------------------------- ");	
		}
		
	}

	/**
	 * Verify the given text is empty or null
	 * @param text
	 * @return
	 */
	private boolean isEmptyCheck(String text){
		if(text==null || text.isEmpty()){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	

}
