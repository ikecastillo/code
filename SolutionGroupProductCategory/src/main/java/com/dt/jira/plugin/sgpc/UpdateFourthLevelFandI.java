package com.dt.jira.plugin.sgpc;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchProvider;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Created by Yagnesh.Bhat on 9/23/2016.
 */
@Path("/FandI4thUpdate")
public class UpdateFourthLevelFandI {

    private final IssueIndexingService issueIndexingService;
    public UpdateFourthLevelFandI(IssueIndexingService issueIndexingService) {
        this.issueIndexingService = issueIndexingService;
    }

    /**
     * REST method that updates product at fourth level to F&I 2.0 if first level is F&I 2.0
     * and updates product at fourth level to F&I 1.0 if first level is F&I Solutions (F&I).
     *
     */
    @GET
    @Path("/updateFandIFourthLevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateThirdLevelProductForFandI(@QueryParam("jqlQuery") String jqlQuery) throws Exception {
        return Response.ok(updateFourthLevelProductForFandIForIssues(jqlQuery)).build();
    }


    public List<SolutionGroupFourthLevelProdModel> updateFourthLevelProductForFandIForIssues(String jqlQuery) {
        Logger log = LoggerFactory.getLogger(UpdateFourthLevelFandI.class);
        List<SolutionGroupFourthLevelProdModel> solGroupIssueUpdateList = new ArrayList<>();

        List<Issue> issues = getIssuesForProject(jqlQuery); //Use this finally
        log.info("TOTAL NUMBER OF ISSUES CONSIDERED FOR UPDATING FOURTH LEVEL IS " + issues.size());

        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        int issuesUpdatedCount = 0;

        for (Issue currentissue : issues) {
            log.debug("Issue UNDER CONSIDERATION " + currentissue.getKey());

            Project project = currentissue.getProjectObject();
            CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();


            IssueType issueType = null;
            Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
            for (IssueType issueTypes : issueTypesProj) {
                if (!issueTypes.isSubTask()) { // exclude sub-task
                    issueType = issueTypes;
                }
            }
            log.debug(" IssueType: " + issueType.getName());

            CustomField cascadeSelect = customFieldManager.getCustomFieldObjectByName("Solution Groups - Products");
            List<String> solutionGroupProdValuesList = getMultiLevelCascadingSelectValue(currentissue, cascadeSelect);
            FieldConfig fieldConfigSolution = cascadeSelect.getRelevantConfig(new IssueContextImpl(project, issueType));


            //The Solution Group should be either of those F&I related as shown below and there must be a product.
            //i.e. size of the cascade must be 2, to even think of adding at third level
            if (solutionGroupProdValuesList.size() == 3 &&
                    solutionGroupProdValuesList.get(0).equals("F&I Solutions (F&I)") &&
                    solutionGroupProdValuesList.get(2).equals("F&I 1.0")) {
                solutionGroupProdValuesList.add(3, "F&I 1.0");
            } else if (solutionGroupProdValuesList.size() == 3 &&
                    solutionGroupProdValuesList.get(0).equals("F&I 2.0") &&
                    solutionGroupProdValuesList.get(2).equals("F&I 2.0")) {
                solutionGroupProdValuesList.add(3, "F&I 2.0");
            } else {
                log.info("Solution group has to be either F&I Solutions (F&I) or F&I 2.0, " +
                        "  and there must be a product and an \"F&I\" sub product" +
                        " for this script to run for issue (also it can be that this issue is already updated at fourth level?) :"
                        + currentissue.getKey());
                continue;
            }

            List<Option> valuesListNew = new ArrayList<>();
            Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);

            Option solutionGroupEnable = null;
            if (solutionGroup != null) {
                solutionGroupEnable = getEnabledSolutionGroup(solutionGroup, solutionGroupProdValuesList, valuesListNew);

                //If no solution group is enabled, no point in proceeding further with this iteration
                if (solutionGroupEnable == null) {
                    log.info("THIS ISSUE IS NOT UPDATED BECAUSE SOLUTION GROUP IS NOT ENABLED " + currentissue.getKey());
                    continue;
                }


                List<Option> cascadeList = new ArrayList<>();
                cascadeList.add(solutionGroupEnable);

                Option productEnable = getEnabledProduct(solutionGroupProdValuesList, solutionGroupEnable, valuesListNew);
                if (productEnable != null) cascadeList.add(productEnable);
                //If no product is enabled, no point in proceeding further with this iteration
                if (productEnable == null) {
                    log.info("THIS ISSUE IS NOT UPDATED AT FOURTH LEVEL BECAUSE PRODUCT IS NOT ENABLED "
                            + currentissue.getKey());
                    continue;
                }

                Option subProductEnable = getEnabledSubProduct(solutionGroupProdValuesList, solutionGroupEnable, valuesListNew);
                if (subProductEnable != null) cascadeList.add(subProductEnable);
                //If the required sub product is also not enabled, there is not point in proceeding with this iteration
                if (subProductEnable == null) {
                    log.info("THIS ISSUE IS NOT UPDATED AT FOURTH LEVEL BECAUSE THIRD LEVEL F&I SUB-PROD IS NOT ENABLED "
                            + currentissue.getKey());
                    continue;
                }

                Option subSubProductEnable = getEnabledSubSubProduct(solutionGroupProdValuesList, solutionGroupEnable, valuesListNew);
                if (subSubProductEnable != null) cascadeList.add(subSubProductEnable);
                //If the required sub product is also not enabled, there is not point in proceeding with this iteration
                if (subSubProductEnable == null) {
                    log.info("THIS ISSUE IS NOT UPDATED AT FOURTH LEVEL BECAUSE FOURTH LEVEL F&I SUB-PROD IS NOT ENABLED "
                            + currentissue.getKey());
                    continue;
                }

                log.debug("$$$$$$$$$$$$$$$$$$ issueMap FOURTH level f and i $$$$$$$$$$$$$$"
                        + cascadeList);
                MutableIssue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(currentissue.getKey());
                issue.setCustomFieldValue(cascadeSelect, cascadeList);

                try {
                    // ComponentAccessor.getIssueIndexManager().reIndex(issue);
                    issueIndexingService.reIndex(issue);
                } catch (Exception ie) {
                    log.error("THIS ISSUE COULD NOT BE UPDATED FOR FOURTH LEVEL F & I - PLEASE CHECK " + issue.getKey(), ie);
                }

                Object selectValue = cascadeSelect.getValue(issue);
                cascadeSelect.updateValue(null, issue, new ModifiedValue(
                        selectValue, cascadeList), new DefaultIssueChangeHolder());
                issue.store();

                try {
                    // ComponentAccessor.getIssueIndexManager().reIndex(currentissue);
                    issueIndexingService.reIndex(currentissue);
                    issuesUpdatedCount++;
                } catch (Exception ie) {
                    log.error("THIS ISSUE COULD NOT BE UPDATED FOR FOURTH LEVEL F & I - PLEASE CHECK " + currentissue.getKey(),ie);
                }

            } else {
                continue;
            }
            String solutionGroupForJSON = valuesListNew.get(0).toString();
            String productForJSON = valuesListNew.size() > 3 ? valuesListNew.get(1).toString() : "";
            String subProductForJSON = valuesListNew.size() > 3 ? valuesListNew.get(2).toString() : "";
            String subSubProductForJSON = valuesListNew.size() > 3 ? valuesListNew.get(3).toString() : "";
            SolutionGroupFourthLevelProdModel bean = new SolutionGroupFourthLevelProdModel(solutionGroupForJSON,
                    productForJSON,subProductForJSON,subSubProductForJSON, currentissue.getKey());
            solGroupIssueUpdateList.add(bean);
        }

        log.info("NUMBER OF ISSUES UPDATED WITH THE FOURTH LEVEL F & I value SUCCESSFULLY " + issuesUpdatedCount);

        return  solGroupIssueUpdateList;
    }


    /**
     * Helper method to deal with multilevel cascade field. Note, as of now, only the Solution Groups - Products
     * is a multilevel cascade field
     *
     * @param issue
     * @param customField
     * @return list of values for multiselect cascade field
     */
    private List getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
        List<String> values = new ArrayList<>();
        List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

        if (valuesList != null) {
            for (Object value : valuesList) {
                values.add(value.toString());
            }

        }
        return values;
    }

    private Option getEnabledSolutionGroup(Options solutionGroup, List<String> solutionGroupProdValuesList,
                                           List<Option> valuesListNew) {
        Option solutionGroupEnable = null;
        for(Option solopt : solutionGroup){
            if(!solopt.getDisabled()){
                if(solopt.getValue().equals(solutionGroupProdValuesList.get(0))){
                    solutionGroupEnable = solopt;
                    valuesListNew.add(solutionGroupEnable);
                }
            }
        }
        return solutionGroupEnable;
    }

    private Option getEnabledProduct(List<String> solutionGroupProdValuesList, Option solutionGroupEnable,
                                     List<Option> valuesListNew) {
        Option productEnable = null;
        for(Option childopt : solutionGroupEnable.getChildOptions()){
            if(!childopt.getDisabled()){
                if(childopt.getValue().equals(solutionGroupProdValuesList.get(1))){
                    productEnable = childopt;
                    valuesListNew.add(productEnable);
                }
            }
        }
        return productEnable;
    }

    private Option getEnabledSubProduct(List<String> solutionGroupProdValuesList,
                                        Option solutionGroupEnable, List<Option> valuesListNew) {
        Option subProductEnable = null;
        for(Option childopt : solutionGroupEnable.getChildOptions()){
            if(!childopt.getDisabled()){
                if(childopt.getValue().equals(solutionGroupProdValuesList.get(1))){
                    for(Option subChildOption : childopt.getChildOptions()) {
                        if (!subChildOption.getDisabled()) {
                            if(subChildOption.getValue().equals(solutionGroupProdValuesList.get(2))) {
                                subProductEnable = subChildOption;
                                valuesListNew.add(subProductEnable);
                            }
                        }
                    }
                }
            }
        }
        return subProductEnable;
    }


    private Option getEnabledSubSubProduct(List<String> solutionGroupProdValuesList,
                                           Option solutionGroupEnable, List<Option> valuesListNew) {
        Option subSubProductEnable = null;
        for(Option childopt : solutionGroupEnable.getChildOptions()){
            if(!childopt.getDisabled()){
                if(childopt.getValue().equals(solutionGroupProdValuesList.get(1))){
                    for(Option subChildOption : childopt.getChildOptions()) {
                        if (!subChildOption.getDisabled()) {
                            if(subChildOption.getValue().equals(solutionGroupProdValuesList.get(2))) {
                                for(Option subSubChildOption : subChildOption.getChildOptions()) {
                                    if (!subSubChildOption.getDisabled()) {
                                        if (subSubChildOption.getValue().equals(solutionGroupProdValuesList.get(3))) {
                                            subSubProductEnable = subSubChildOption;
                                            valuesListNew.add(subSubProductEnable);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return subSubProductEnable;
    }
    private List<Issue> getIssuesForProject(String jqlQuery) {
        // String jqlQuery = "project = " + projectKey + " and issuetype IN (Incident, \"Incident Report Subtask\")";
        List<Issue> projectIssues = null;
        SearchService searchService = ComponentAccessor.getComponent(SearchService.class);
        SearchProvider searchProvider = ComponentAccessor.getComponent(SearchProvider.class);
        JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

        final SearchService.ParseResult result = searchService.parseQuery(authenticationContext.getLoggedInUser(),
                jqlQuery);

        if (result != null) {
            try {
                final SearchResults results = searchProvider.search(result.getQuery(),authenticationContext.getLoggedInUser(),
                        PagerFilter.getUnlimitedFilter());
                projectIssues = results.getIssues();
            } catch (SearchException e) {
                e.printStackTrace();
            }
        }

        return projectIssues;
    }

}
