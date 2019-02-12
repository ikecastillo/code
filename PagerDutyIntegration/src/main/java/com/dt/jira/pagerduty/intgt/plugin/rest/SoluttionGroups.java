package com.dt.jira.pagerduty.intgt.plugin.rest;


import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Get Solution Groups, Products, Sub Products and Sub sub products for a given project
 */
@Path("/getSolutionGroups")
public class SoluttionGroups {

@GET
@AnonymousAllowed
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
	List<SoluttionGroupsModel> listOfModels= new ArrayList<>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
		//IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		  IssueType issueType= null;
          Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
          for(IssueType issueT: issueTypesProj){
                 if(!issueT.isSubTask()){ // exclude sub-task
                	 issueType = issueT;
                 }
          }
		CustomField customField = cfManager
				.getCustomFieldObjectByName("Solution Groups - Products");
		FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
		OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
		List<Option> impacted = optionsManager.getOptions(fieldConfigSolution);
		for(Option o: impacted){
			//System.out.println("Name: "+o.getValue());
			if(!o.getDisabled()){
				listOfModels.add(new SoluttionGroupsModel(o.getOptionId()+"",o.getValue()));
			}
		}
			
			
			
		
	}catch(Exception e){
			e.printStackTrace();
	}
  		return Response.ok(listOfModels).build();		
	}

	@GET
	@Path("/getProductValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSolutionProductList(@QueryParam("solutionGroup") String solutionGroup,@QueryParam("projectkey") String projectkey) throws Exception{
		List<SoluttionGroupsModel> listOfProductModels= new ArrayList<>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
			IssueType issueType= null;
			Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
			for(IssueType issueT: issueTypesProj){
				if(!issueT.isSubTask()){ // exclude sub-task
					issueType = issueT;
				}
			}
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager.getCustomFieldObjectByName("Solution Groups - Products");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
			List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);
			if(solOpts!=null){
				for(Option o: solOpts){
					if(o.getValue().equals(solutionGroup)){
						for(Option productOption : o.getChildOptions()){

							if(!productOption.getDisabled()){
								listOfProductModels.add(new SoluttionGroupsModel(productOption.getOptionId()+"",productOption.getValue()));
							}
						}
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.ok(listOfProductModels).build();
	}

	@GET
	@Path("/getSubProductValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSolutionSubProductList(@QueryParam("solutionGroup") String solutionGroup, @QueryParam("projectkey") String projectkey,
											  @QueryParam("product") String product) throws Exception{
		List<ProductBean> listOfSubProductModels= new ArrayList<>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
			IssueType issueType= null;
			Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
			for(IssueType issueT: issueTypesProj){
				if(!issueT.isSubTask()){ // exclude sub-task
					issueType = issueT;
				}
			}
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager.getCustomFieldObjectByName("Solution Groups - Products");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
			List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);
			if(solOpts!=null){
				for(Option o: solOpts){
					if(o.getValue().equals(solutionGroup)){
						for(Option productOption : o.getChildOptions()){
							if(productOption.getValue().equals(product)) {
								List<Option> subProdOptions = productOption.getChildOptions();
								for (Option subProdOption : subProdOptions) {
									if(!subProdOption.getDisabled()){
										listOfSubProductModels.add(new ProductBean(subProdOption.getOptionId()+"",subProdOption.getValue()));
									}
								}

							}
						}
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.ok(listOfSubProductModels).build();
	}

	@GET
	@Path("/getSubSubProductValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSolutionSubSubProductList(@QueryParam("solutionGroup") String solutionGroup, @QueryParam("projectkey") String projectkey,
											  @QueryParam("product") String product, @QueryParam("subproduct") String subproduct) throws Exception{
		List<ProductBean> listOfSubProductModels= new ArrayList<>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
			IssueType issueType= null;
			Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
			for(IssueType issueT: issueTypesProj){
				if(!issueT.isSubTask()){ // exclude sub-task
					issueType = issueT;
				}
			}
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager.getCustomFieldObjectByName("Solution Groups - Products");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
			List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);
			if(solOpts!=null){
				for(Option o: solOpts){
					if(o.getValue().equals(solutionGroup)){
						for(Option productOption : o.getChildOptions()){
							if(productOption.getValue().equals(product)) {
								List<Option> subProdOptions = productOption.getChildOptions();
								for (Option subProdOption : subProdOptions) {
									if(!subProdOption.getDisabled() && subProdOption.getValue().equals(subproduct)){
										List<Option> subProdOptions2 = subProdOption.getChildOptions();
										for (Option subProdOption2 : subProdOptions2) {
											if(!subProdOption2.getDisabled()) {
												listOfSubProductModels.add(new ProductBean(subProdOption2.getOptionId()+"",subProdOption2.getValue()));
											}

										}

									}
								}

							}
						}
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.ok(listOfSubProductModels).build();
	}
}
