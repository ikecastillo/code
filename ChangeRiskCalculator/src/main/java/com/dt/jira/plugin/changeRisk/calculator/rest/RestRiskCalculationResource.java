package com.dt.jira.plugin.changeRisk.calculator.rest;




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.plugin.changeRisk.calculator.ao.RiskCalculation;
import com.dt.jira.plugin.changeRisk.calculator.rest.RiskCalculationModel;
import net.java.ao.Entity;
import com.dt.jira.plugin.changeRisk.calculator.service.RiskCalculationService;


/**
 * RestRiskCalculationResource web services for RiskCondition
 * @author srinadh.garlapati
 */
@Path("/riskCalculationResource")
public class RestRiskCalculationResource {

	
	
	
	private final Logger logger = Logger.getLogger(RestRiskCalculationResource.class);
	private final RiskCalculationService riskCalculationService;
/**
 * Constructor
 * @param riskCalculationService  to be injected
 */
	public RestRiskCalculationResource(RiskCalculationService riskCalculationService)
	 {
	   this.riskCalculationService = riskCalculationService;
	   
	 }
	

	@PUT
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addRiskCondition")
	public Response put(@QueryParam("question1") String question1 ,@QueryParam("question2") String question2 ,@QueryParam("question3") String question3 ,@QueryParam("question4") String question4 ,@QueryParam("condition") String condition,@QueryParam("result") String result)
	{
		logger.info("-------riskCalculationResource---------put-------------");
		RiskCalculation riskCalculationConfiguration=riskCalculationService.create(question1, question2, question3, question4, condition, result);
		logger.info("----------------riskCalculationConfiguration-------------"+riskCalculationConfiguration);
		if(riskCalculationConfiguration!=null){
			List<RiskCalculationModel> riskConditionModelList=getRiskConditions();
			return Response.ok(riskConditionModelList).build();
		}
		return Response.noContent().build();
	}
	
	
	
	
	@GET
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/riskConditions")
	public Response get()
	{
		List<RiskCalculationModel> riskConditionModelList=getRiskConditions();
		return Response.ok(riskConditionModelList).build();
	}
	
	private List<RiskCalculationModel> getRiskConditions(){
		List<RiskCalculationModel> riskCalculationModelList=new ArrayList<RiskCalculationModel>();
		List<RiskCalculation> conditionsList=riskCalculationService.findAll();
		for(RiskCalculation conditionObj: conditionsList){
			logger.info("-------------Question1------------------"+conditionObj.getQuestion1());
			RiskCalculationModel riskObj=new RiskCalculationModel(conditionObj.getQuestion1(),conditionObj.getQuestion2(),conditionObj.getQuestion3(),conditionObj.getQuestion4(),conditionObj.getCondition(),conditionObj.getResult());
			riskObj.setConditionID(""+conditionObj.getID());
			riskCalculationModelList.add(riskObj);
					}
		return riskCalculationModelList;
	}
	
	
	
	@PUT
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteRiskCondition")
	public Response delete(@QueryParam("conditionId") String conditionId)
	{
		logger.info("-------riskCalculationResource---------put-------------");
		riskCalculationService.deleteByConditionId(conditionId);
		List<RiskCalculationModel> riskConditionModelList=getRiskConditions();
		
		return Response.ok(riskConditionModelList).build();
		
		
	}
	
	
	
}
