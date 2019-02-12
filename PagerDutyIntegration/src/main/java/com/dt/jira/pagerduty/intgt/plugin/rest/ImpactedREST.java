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
 * Created by Yagnesh.Bhat on 5/25/2016.
 */

@Path("/getImpacted")
public class ImpactedREST {
    @GET
    @Path("/getImpactedValues")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImpactedList(@QueryParam("projectkey") String projectkey) throws Exception{
        List<ImpactedModel> listOfImpactedModels= new ArrayList<>();
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
                        listOfImpactedModels.add(new ImpactedModel(impactOption.getOptionId()+"",impactOption.getValue()));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return Response.ok(listOfImpactedModels).build();
    }
}
