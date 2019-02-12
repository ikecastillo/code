package com.dt.jira.plugin.uptime;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

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
import com.dt.jira.plugin.uptime.LocationsModel;

@Path("/getLocation")
public class Locations {
	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
		ArrayList<LocationsModel> listOfModels= new ArrayList<LocationsModel>();
		try{
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);		
			//IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);
			  CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();      
              IssueType incidentIssueType= null;         
              Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
              for(IssueType issueT: issueTypesProj){
                     if(!issueT.isSubTask()){ // exclude sub-task
                            incidentIssueType = issueT;
                     }
              }
			CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
			CustomField customField = cfManager
					.getCustomFieldObjectByName("Impacted - Function");
			FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, incidentIssueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			List<Option> impacted = optionsManager.getOptions(fieldConfigSolution);	
			for(Option o: impacted){
				//System.out.println("Name: "+o.getValue());
				if(!o.getDisabled()){
				listOfModels.add(new LocationsModel(o.getOptionId()+"",o.getValue()));
				}
			}
				
				
			
		}catch(Exception e){
				e.printStackTrace();
		}
	  		return Response.ok(listOfModels).build();		
		}

}
