package com.dt.jira.plugin.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
import com.dt.jira.plugin.rest.TypeModel;

@Path("/getTypes")
public class Type {
	@GET
	@AnonymousAllowed
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
		ArrayList<TypeModel> listOfModels= new ArrayList<TypeModel>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);		
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Clients Impacted");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			List<Option> impacted = optionsManager.getOptions(fieldConfigSolution);
			CustomField typeField = cfManager
					.getCustomFieldObjectByName("Clients Impacted");
			long typeId = typeField.getIdAsLong();
			for(Option o: impacted){
				listOfModels.add(new TypeModel(o.getOptionId()+"",o.getValue(),"cf["+typeId+"]"));
			}
				
			
	
			
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfModels).build();		
		}
	
	//<Start Added dynamic project Key values - 08-05-2015>
	@GET
	@Path("/getLocationValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductList(@QueryParam("type") String type,@QueryParam("projectkey") String projectkey) throws Exception{
		ArrayList<TypeModel> listOfLocationModels= new ArrayList<TypeModel>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);		
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Clients Impacted");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			List<Option> typeList = optionsManager.getOptions(fieldConfigSolution);	  
			if(typeList!=null){
				for(Option o: typeList){
					if(o.getValue().equals(type)){
						for(Option childOption : o.getChildOptions()){
							if(!childOption.getDisabled()){
								listOfLocationModels.add(new TypeModel(childOption.getOptionId()+"",childOption.getValue()));
							}
						}
						break;
					}
				}
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfLocationModels).build();		
		}
	//<End Added dynamic project Key values - 11-05-2015>
	
	//<Start Added Third level Product drop down values - 08-05-2015>
	@GET
	@Path("/getProductValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSolutionProductList(@QueryParam("solutionGroup") String solutionGroup,@QueryParam("projectkey") String projectkey) throws Exception{		
		ArrayList<SoluttionGroupsModel> listOfProductModels= new ArrayList<SoluttionGroupsModel>();
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
	//<End Added Third level Product drop down values - 08-05-2015>
	
	
	//<Start Added Third level Impacted drop down values - 08-05-2015>
	@GET
	@Path("/getImpactedValues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImpactedList(@QueryParam("projectkey") String projectkey) throws Exception{
		ArrayList<SoluttionGroupsModel> listOfImpactedModels= new ArrayList<SoluttionGroupsModel>();
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
			CustomField customField = cfManager.getCustomFieldObjectByName("Impacted - Function");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			List<Option> impactOpts = optionsManager.getOptions(fieldConfigSolution);	  
			if(impactOpts!=null){
				for(Option impactOption: impactOpts){
					if(!impactOption.getDisabled()){
						listOfImpactedModels.add(new SoluttionGroupsModel(impactOption.getOptionId()+"",impactOption.getValue()));
					}					
				}
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfImpactedModels).build();		
		}
	//<End Added Third level Impacted drop down values - 08-05-2015>
}
