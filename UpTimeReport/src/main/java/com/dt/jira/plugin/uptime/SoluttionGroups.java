package com.dt.jira.plugin.uptime;



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
import com.dt.jira.plugin.uptime.SoluttionGroupsModel;


/**
 * Get Issue Types of a given Project
 */
@Path("/getSolutiionGroups")
public class SoluttionGroups {

@GET
@AnonymousAllowed
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
	ArrayList<SoluttionGroupsModel> listOfModels= new ArrayList<SoluttionGroupsModel>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);		
		//IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
		CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
		  IssueType issueType= null;         
          Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
          for(IssueType issueT: issueTypesProj){
                 if(!issueT.isSubTask()){ // exclude sub-task
                	 issueType = issueT;
                 }
          }
		CustomField customField = cfManager.getCustomFieldObjectByName("Solution Groups - Products");
		FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
		OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
		List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);	
		if(solOpts!=null){
			for(Option o: solOpts){
				if(!o.getDisabled()){
					listOfModels.add(new SoluttionGroupsModel(o.getOptionId()+"",o.getValue()));
				}
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
public Response getProductList(@QueryParam("solutionGroup") String solutionGroup) throws Exception{
	
	ArrayList<SoluttionGroupsModel> listOfProductModels= new ArrayList<SoluttionGroupsModel>();
	try{
		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("ITIM");	
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
					for(Option childOption : o.getChildOptions()){					
						if(!childOption.getDisabled()){
							listOfProductModels.add(new SoluttionGroupsModel(childOption.getOptionId()+"",childOption.getValue()));
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
}