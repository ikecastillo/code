package com.dt.jira.multilevelcascadedepth.jql;

import com.atlassian.jira.JiraDataType;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.plugin.jql.function.AbstractJqlFunction;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yagnesh.Bhat on 11/4/2016.
 */

/**
 * Class is defined as customized jql function for the Incident Management
 */
@Scanned
public class MultilevelCascadeOptionForDepthJQLfunction extends AbstractJqlFunction {
    private static final Logger logger = LoggerFactory.getLogger(MultilevelCascadeOptionForDepthJQLfunction.class);

    @Nonnull
    public MessageSet validate(ApplicationUser applicationUser, @Nonnull FunctionOperand functionOperand,
                               @Nonnull TerminalClause terminalClause) {
        return validateNumberOfArgs(functionOperand, 5);
    }

    @Nonnull
    public List<QueryLiteral> getValues(QueryCreationContext queryCreationContext,
                                        FunctionOperand functionOperand, TerminalClause terminalClause) {

        final List<QueryLiteral> literals = new LinkedList<>();
        logger.debug(" Args for ML CASCADE FUNCTION " + functionOperand.getArgs().toString());
        String projectkey = functionOperand.getArgs().get(0);
        String issueType = functionOperand.getArgs().get(1);
        String customfieldname =  functionOperand.getArgs().get(2);
        String depth =  functionOperand.getArgs().get(3);
        String  cascadevalue=  functionOperand.getArgs().get(4);

        // Step 1. Get the list of issues from Incident type
        String jqlQuery =  buildJQLQuery(issueType,projectkey,null,null,null).toString();
        SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);
        JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();

        final SearchService.ParseResult pResult1= searchServ.parseQuery(authenticationContext.getLoggedInUser(), jqlQuery.toString());
        logger.debug(" MultilevelCascadeOptionForDepthJQLfunction JQL Query: "+jqlQuery.toString());
        List<Issue> results = null;
        SearchResults issueList = null;

        long totValidDef;

        if (pResult1.isValid())  {
            try {
                issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult1.getQuery(), PagerFilter.getUnlimitedFilter());
            } catch (SearchException e) {
                e.printStackTrace();
            }
            results = issueList.getIssues();
        }
        if(results!=null){
            totValidDef = results !=null? results.size(): 0;
            logger.debug(" total issues "+ totValidDef);
            //Step 2. Find the custom field value from the issue based on the depth  and is equal to the input parameter of the cascade value
            //Step 3. List of the issues from the above and return list of literals.
            for(Issue issue: results){
                int depthlong = Integer.valueOf(depth);
                CustomField customField =ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(customfieldname);
                List<Option> customList = (List<Option>)issue.getCustomFieldValue(customField);
                if(customList!=null && customList.size() > depthlong) {
                    Option option = customList.get(depthlong);
                    if(option.getValue().equals(cascadevalue)){
                        literals.add(new QueryLiteral(functionOperand, issue.getId()));
                    }
                }
            }

        }
        return literals;
    }

    public int getMinimumNumberOfExpectedArguments() {
        return 5;
    }

    @Nonnull
    public JiraDataType getDataType() {
        return JiraDataTypes.ISSUE;
    }

    private StringBuffer buildJQLQuery(String issuetypes,String projectkey,String version,String sprintId, String appendQuery){
        StringBuffer jql = new StringBuffer("issueType in (" + issuetypes + ") ");
        if(appendQuery!=null && !appendQuery.equals("")){
            jql.append(" and " + appendQuery);
        }

        if(projectkey!=null){
            jql.append(" and project = "+projectkey);
        }
        if(projectkey!=null  && version!=null && !version.equals("All")){
            jql.append(" and fixVersion in ("+ version +")");
        }
        if(projectkey!=null  && sprintId != null && !sprintId.equals("All")){
            jql.append(" and Sprint in ("+ sprintId +")");
        }
        return jql;
    }


}
