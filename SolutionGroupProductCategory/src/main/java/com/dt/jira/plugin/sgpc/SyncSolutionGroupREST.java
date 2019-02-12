package com.dt.jira.plugin.sgpc;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

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
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
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
import com.atlassian.jira.issue.index.IssueIndexingService;

/**
 * Created by yagnesh.bhat on 7/28/2015.
 */

@Path("/newssolutiongroup")
public class SyncSolutionGroupREST {
    private final IssueIndexingService issueIndexingService;
    public SyncSolutionGroupREST(IssueIndexingService issueIndexingService) {
        this.issueIndexingService = issueIndexingService;
    }

	@GET
    @Path("/syncsolutiongroupvalues")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSolutionGroup(@QueryParam("jqlQuery") String jqlQuery) throws Exception {
		/**
		 * 1. Create jql query using project key, list of issues
		 * 2. Read all the issues
		 * 3. Get the custom field both old and new
		 * 4. Update the custom field value with the old field value
		 * 5. Model to populate successful message
		 */

		return Response.ok(updateSolutionGroupForIssues(jqlQuery)).build();
	}

    public List<SolutionGroupModel> updateSolutionGroupForIssues(String jqlQuery) {
        Logger log = LoggerFactory.getLogger(SyncSolutionGroupREST.class);
        List<SolutionGroupModel> solGroupIssueUpdateList = new ArrayList<>();

        List<Issue> issues = getIssuesForProject(jqlQuery); //Use this finally
        log.info("TOTAL NUMBER OF ISSUES CONSIDERED FOR UPDATING NEW FIELD IS " + issues.size());

        //Using this for testing, when working fine use the original list issues above
        //List<Issue> issuesSub = getIssuesForProject(projectKey).subList(0, 25); //Using this for testing

        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        int issuesUpdatedCount = 0;

        for (Issue currentissue : issues) {
            log.debug("Issue UNDER CONSIDERATION " + currentissue.getKey());
            //if (currentissue.getKey().equals("ITIM-4323")) { //Uncomment this and line 158 to test for a single issue
            Project project = currentissue.getProjectObject();
            CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();

            //IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project);

            IssueType issueType = null;
            Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
            for (IssueType issueTypes : issueTypesProj) {
                if(!issueTypes.isSubTask()){ // exclude sub-task
                    issueType = issueTypes;
                }
            }
            log.debug(" IssueType: " + issueType.getName());

            CustomField cascadeSelect = customFieldManager.getCustomFieldObjectByName("Solution Groups - Products");
            FieldConfig fieldConfigSolution = cascadeSelect.getRelevantConfig(new IssueContextImpl(project, issueType));


            CustomField oldCustomField = customFieldManager.getCustomFieldObjectByName("Solution Group - Product");

            List<String> oldCustomFieldValues = getCascadeSelectValue(currentissue, oldCustomField);

            List<Option> valuesListNew = new ArrayList<>();

            Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);

            if (solutionGroup != null) {
                Option solutionGroupEnable = null;
                solutionGroupEnable = getEnabledSolutionGroup(oldCustomFieldValues, valuesListNew, solutionGroup, solutionGroupEnable);

                //If no solution group is enabled, no point in proceeding further with this iteration
                if (solutionGroupEnable == null) {
                    log.info("THIS ISSUE IS NOT UPDATED BECAUSE SOLUTION GROUP IS NOT ENABLED " + currentissue.getKey());
                    continue;
                }

                Option productEnable = getEnabledProduct(oldCustomFieldValues, valuesListNew, solutionGroupEnable);

                MutableIssue issue = ComponentAccessor.getIssueManager().getIssueByCurrentKey(currentissue.getKey());
                List<Option> cascadeList = new ArrayList<Option>();
                cascadeList.add(solutionGroupEnable);
                if (productEnable != null) cascadeList.add(productEnable);

                log.debug("$$$$$$$$$$$$$$$$$$ issueMap $$$$$$$$$$$$$$"
                        + cascadeList);
                issue.setCustomFieldValue(cascadeSelect, cascadeList);

                try {
                   // ComponentAccessor.getIssueIndexManager().reIndex(issue);
                    issueIndexingService.reIndex(issue);
                } catch (Exception ie) {
                    log.error("THIS ISSUE COULD NOT BE UPDATED - PLEASE CHECK " + issue.getKey());
                    ie.printStackTrace();
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
                    log.error("THIS ISSUE COULD NOT BE UPDATED - PLEASE CHECK " + currentissue.getKey());
                    ie.printStackTrace();
                }
            } else {
                continue;
            }


            String solutionGroupForJSON = valuesListNew.get(0).toString();
            String productForJSON = valuesListNew.size() == 2 ? valuesListNew.get(1).toString() : "";
            SolutionGroupModel bean = new SolutionGroupModel(solutionGroupForJSON, productForJSON,
                    currentissue.getKey());
            solGroupIssueUpdateList.add(bean);
        }

        log.info("NUMBER OF ISSUES UPDATED WITH THE NEW FIELD SUCCESSFULLY " + issuesUpdatedCount);
        //}

        return solGroupIssueUpdateList;
    }

    private Option getEnabledProduct(List<String> oldCustomFieldValues, List<Option> valuesListNew, Option solutionGroupEnable) {
        Option productEnable = null;
        for(Option childopt : solutionGroupEnable.getChildOptions()){
            if(!childopt.getDisabled()){
                if(childopt.getValue().equals(oldCustomFieldValues.get(1))){
                    productEnable = childopt;
                    valuesListNew.add(productEnable);
                }
            }
        }
        return productEnable;
    }

    private Option getEnabledSolutionGroup(List<String> oldCustomFieldValues, List<Option> valuesListNew, Options solutionGroup, Option solutionGroupEnable) {
        for(Option solopt : solutionGroup){
            if(!solopt.getDisabled()){
                if(solopt.getValue().equals(oldCustomFieldValues.get(0))){
                    solutionGroupEnable = solopt;
                    valuesListNew.add(solutionGroupEnable);
                }
            }
        }
        return solutionGroupEnable;
    }

    /**
     * To make sure the value exists in the new field
     * @param value
     * @param issue
     * @param newCustomField
     * @return
     */
    private Option getMasterValueFromNewList(Option value, Issue issue, CustomField newCustomField) {
        //List valuesListNew = (ArrayList)issue.getCustomFieldValue(newCustomField);
        List valuesListNew = (ArrayList)newCustomField.getValue(issue);
        Logger log = LoggerFactory.getLogger(SyncSolutionGroupREST.class);
        log.debug("ISSUE TO EXTRACT NEW FIELD VALUES IS " + issue.getKey());
        if (valuesListNew == null) {
            log.debug("NULL VALUESLISTNEW");
            return null;
        } else {
            log.debug("SIZE OF NEW VALUE LIST IS " + valuesListNew.size());
            for (Object valueNew : valuesListNew) {
                log.debug("Value of valueNew is " + valueNew.toString());

                log.debug("Type of valueNew is " + valueNew.getClass());
                Option mValue = (LazyLoadedOption)valueNew;
                log.debug("ID of VALUENEW IS " + mValue.getOptionId());
                if (mValue.getValue().equals(value.getValue())) {
                    mValue = value;
                    return mValue;
                }
            }
        }

        return null;
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

    /**
     * Get the values for custom field type is cascade select
     * @param issue
     * @param customField
     * @return list of value(s) for cascade field - its size would always be > 1
     */
    private List<String> getCascadeSelectValue(Issue issue, CustomField customField){
        String solutionGrValue = "";
        String productValue = "";
        List<String> values = new ArrayList<>();
        Map<LazyLoadedOption, LazyLoadedOption> solutionGroupMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
                issue.getCustomFieldValue(customField);
        if(solutionGroupMap!=null){
            for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : solutionGroupMap.entrySet()) {
                LazyLoadedOption llo = null;
                if(opt.getKey() ==  null ){ // for Solution Group
                    llo = opt.getValue();
                    solutionGrValue = String.valueOf(llo.getValue());
                }
                if(opt.getKey() !=  null ){
                    llo = opt.getValue();
                    productValue = String.valueOf(llo.getValue());
                }
            }
        }
        values.add(solutionGrValue);
        values.add(productValue);
        return values;
    }

/********************************************** Another REST CALL to sync master values in the new copy********************/
@GET
@Path("/syncSGPC")
@Produces(MediaType.APPLICATION_JSON)
public Response syncSolutionGroup(@QueryParam("projectKey") String projectKey) {

    Logger log = LoggerFactory.getLogger(SyncSolutionGroupREST.class);
	
	 Map<String,Option> optionGroupMap = new HashMap<String,Option>();
	 	List<Option> sgpEnable = new ArrayList<Option>();
		String message = "Begin";
	  try{
		  
		  Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
	    	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
	    	CustomField customFieldProduct = customFieldManager.getCustomFieldObjectByName("Solution Group - Product" );
	    	CustomField customFieldProductCat = customFieldManager.getCustomFieldObjectByName("Solution Groups - Products");
		        
			
	    	IssueType changeIssueType= null;    	
	    	Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
	    	for(IssueType issueT: issueTypesProj){
	    		if(!issueT.isSubTask()){ // exclude sub-task
	    			//issueT = changeIssueType;
					changeIssueType = issueT;
	    		}
	    	}

          log.debug("Issue type is " + changeIssueType.getName());

	    FieldConfig fieldConfigSGP = customFieldProduct.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
 		FieldConfig fieldConfigSGPCat = customFieldProductCat.getRelevantConfig(new IssueContextImpl(project, changeIssueType));


          Options sgp = ComponentAccessor.getOptionsManager().getOptions(fieldConfigSGP);
        log.info("NUMBER OF OPTIONS IN FIELD SOLUTION GROUP - PRODUCT " + sgp.size());

 		OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
 		// Remove custom field options
 		//optionsManager.removeCustomFieldOptions(customFieldProductCat);
          optionsManager.removeCustomFieldConfigOptions(fieldConfigSGPCat);
 		log.debug("Removed existing options from Solution Group : " + customFieldProductCat.getName());
        int optionCount = 0;
 		if(sgp != null) {				
 			for(Option sgpEnableopt : sgp) {
                if (!sgpEnableopt.getDisabled()) {
                    System.out.println("Solution Group : "+ sgpEnableopt.getValue());
 					Option newoption =  optionsManager.createOption(fieldConfigSGPCat, null, null, sgpEnableopt.getValue());
 					setOptions(newoption,sgpEnableopt,fieldConfigSGPCat);
                    optionCount++;
 					System.out.println("Success fully created new option "+ sgpEnableopt.getValue());					
 			}
 		}
 		}
        log.info("NEW OPTIONS COUNT UPDATED IN MASTER VALUES FOR SOLUTION GROUPS - PRODUCTS FIELD " + optionCount);
 		
 	
 		message = "Success fully updated  new options";
 		
		
       } catch (Exception e) {
	        message = e.getMessage();
           e.printStackTrace();
       }
	ResponseBuilder response = Response.ok(message);	
	return response.build();

}

     private void setOptions(Option newOption, Option existingoption,FieldConfig fieldConfigSGPCat){
    	Options sgpOptions = ComponentAccessor.getOptionsManager().getOptions(fieldConfigSGPCat);
    	List<Option> childOptions = existingoption.getChildOptions();
		if(childOptions!=null){
    	for(Option child: childOptions){
    		sgpOptions.addOption(newOption,child.getValue());	
    	}
		}
    	
    }
}
