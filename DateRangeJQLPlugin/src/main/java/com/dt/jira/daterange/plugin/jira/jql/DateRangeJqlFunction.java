package com.dt.jira.daterange.plugin.jira.jql;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.JiraDataType;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.plugin.jql.function.AbstractJqlFunction;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;
import com.atlassian.sal.api.message.I18nResolver;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Code for the function getIssuesForTimeRange.
 */
public class DateRangeJqlFunction extends AbstractJqlFunction
{
    private static final Logger log = LoggerFactory.getLogger(DateRangeJqlFunction.class);

    private final I18nResolver i18n;

    public DateRangeJqlFunction(I18nResolver i18n) {
        this.i18n = i18n;
    }

    public MessageSet validate(ApplicationUser searcher, FunctionOperand operand, TerminalClause terminalClause)
    {
        return validateNumberOfArgs(operand, 2);
    }

    public List<QueryLiteral> getValues(QueryCreationContext queryCreationContext, FunctionOperand operand, TerminalClause terminalClause)
    {
        final List<QueryLiteral> literals = new LinkedList<QueryLiteral>();
        try
        {
            String projectKey = i18n.getText("date-range-jql-function.input.projectkey");
            log.debug("Project Key used by the function is " + projectKey);
            Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
            String issueType = i18n.getText("date-range-jql-function.input.issueType");
            log.debug("Issue Type set for the function is " + issueType);
            Integer startTime = Integer.parseInt(operand.getArgs().get(0));
            Integer endTime = Integer.parseInt(operand.getArgs().get(1));
            if(startTime > endTime)
            {
                throw new Exception("Start time cannot be greater than end time");
            }
            if(startTime < 1 || startTime > 24 || endTime < 1 || endTime > 24)
            {
                throw new Exception("Times should be between 1 and 24.");
            }
            Collection<Long> issueIds = ComponentAccessor.getIssueManager().getIssueIdsForProject(project.getId());

            //Filter the ids by issue type - we need to do this with projectkey as dependency as JQL functions cant process
            //search results a.k.a. Filter results
            for(Long issueId:issueIds)
            {
                Issue issue = ComponentAccessor.getIssueManager().getIssueObject(issueId);
                DateTime issueCreated = new DateTime(issue.getCreated());

                if(issueCreated.getHourOfDay() >= startTime &&
                    (issueCreated.getHourOfDay() < endTime || (issueCreated.getHourOfDay() == endTime
                    && issueCreated.getMinuteOfHour() == 0 && issueCreated.getSecondOfMinute() == 0)) &&
                        issue.getIssueTypeObject().getName().equals(issueType)) {
                    log.debug("ISSUE KEY IS " + issue.getKey());
                    log.debug("Hour of the day is " + issueCreated.getHourOfDay());
                    log.debug("Minute of the day is " + issueCreated.getMinuteOfHour());
                    log.debug("Second of the day is " + issueCreated.getSecondOfMinute());
                    log.debug("Year is " + issueCreated.getYear());
                    literals.add(new QueryLiteral(operand, issueId));
                }

            }
        }
        catch(Exception exc)
        {
            log.error(exc.toString());
        }
        return literals;
        //return Collections.singletonList(new QueryLiteral(operand, Iterables.get(operand.getArgs(), 0)));
    }

    public int getMinimumNumberOfExpectedArguments()
    {
        return 2;
    }

    public JiraDataType getDataType()
    {
        return JiraDataTypes.ISSUE;
    }
}