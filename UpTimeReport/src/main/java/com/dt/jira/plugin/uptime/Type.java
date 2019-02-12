package com.dt.jira.plugin.uptime;

import java.util.ArrayList;
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
import com.dt.jira.plugin.uptime.TypeModel;

@Path("/getType")
public class Type {
	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
			for(Option o: impacted){
				//System.out.println("Type: "+o.getValue());
				listOfModels.add(new TypeModel(o.getOptionId()+"",o.getValue()));
			}
				
				
			
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfModels).build();		
		}
		
	
	@GET
	@Path("/getLocationProductValues")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getProductList(@QueryParam("type") String type) throws Exception{
		ArrayList<TypeModel> listOfProductModels= new ArrayList<TypeModel>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("ITIM");		
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Clients Impacted");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			List<Option> typeList = optionsManager.getOptions(fieldConfigSolution);	  
			for(Option o: typeList){
				if(o.getValue().equals(type)){
					for(Option childOption : o.getChildOptions()){
						//System.out.println("Type Name :" + childOption.getValue());
						if(!childOption.getDisabled()){
							listOfProductModels.add(new TypeModel(childOption.getOptionId()+"",childOption.getValue()));
						}
					}
					break;
				}
			
			}
				
				
			
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfProductModels).build();		
		}
}
