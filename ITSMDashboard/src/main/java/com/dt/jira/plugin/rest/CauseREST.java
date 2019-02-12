package com.dt.jira.plugin.rest;

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
 * Created by yagnesh.bhat on 9/14/2015.
 */

@Path("/getCauses")
public class CauseREST {
    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{
        ArrayList<CauseModel> listOfModels= new ArrayList<>();
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
            CustomField customField = cfManager
                    .getCustomFieldObjectByName("Cause");
            FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(project, issueType));
            OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);
            List<Option> impacted = optionsManager.getOptions(fieldConfigSolution);
            for(Option o: impacted){
                //System.out.println("Name: "+o.getValue());
                if(!o.getDisabled()){
                    listOfModels.add(new CauseModel(o.getValue()+"",o.getValue()));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return Response.ok(listOfModels).build();
    }
}
