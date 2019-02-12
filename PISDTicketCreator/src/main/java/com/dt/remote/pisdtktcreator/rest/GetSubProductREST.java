package com.dt.remote.pisdtktcreator.rest;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.customfields.option.Option;
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
 * Created by yagnesh.bhat on 4/19/2016.
 */
@Path("/subProducts")
public class GetSubProductREST {

    @GET
    @Path("/getSubProductValues")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolutionSubProductList(@QueryParam("solutionGroup") String solutionGroup, @QueryParam("projectkey") String projectkey,
                                           @QueryParam("product") String product) throws Exception{
        ArrayList<SubProductBean> listOfSubProductModels= new ArrayList<>();
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
                                        listOfSubProductModels.add(new SubProductBean(subProdOption.getOptionId()+"",subProdOption.getValue()));
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
