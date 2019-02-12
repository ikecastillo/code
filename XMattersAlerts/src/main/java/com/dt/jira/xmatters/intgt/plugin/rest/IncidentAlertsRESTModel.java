package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.jira.issue.IssueManager;
import com.dt.jira.xmatters.intgt.plugin.business.xMattersLogFilterer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentAlertsRESTModel {

    @XmlElement private String jiraIssueKey; //key of the incident
    @XmlElement private String jiraIssueSummary; //jiraIssueSummary of the incident that are <=30 mins xMatters log message
                                        // from the current time

    public IncidentAlertsRESTModel() {}

    public IncidentAlertsRESTModel(String jiraIssueKey, String jiraIssueSummary) {
        super();
        this.jiraIssueKey = jiraIssueKey;
        this.jiraIssueSummary = jiraIssueSummary;
    }

    public String getJiraIssueKey() {
        return jiraIssueKey;
    }

    public void setJiraIssueKey(String jiraIssueKey) {
        this.jiraIssueKey = jiraIssueKey;
    }

    public String getJiraIssueSummary() {
        return jiraIssueSummary;
    }

    public void setJiraIssueSummary(String jiraIssueSummary) {
        this.jiraIssueSummary = jiraIssueSummary;
    }

    public List<IncidentAlertsRESTModel> getIncidentsToAlert(String unresolvedIncidentIDs, IssueManager issueManager) {
        List<IncidentAlertsRESTModel> incidentsToAlertList = new ArrayList<>();
        String[] incidentIDs = unresolvedIncidentIDs.split(",");
        xMattersLogFilterer xMattersLogFilterer = new xMattersLogFilterer();
        Map<String, String> incidentMappings = new HashMap<>(); //Contains mapping Incident ID -> Summary

        for(String incidentID : incidentIDs) {
            String summary = xMattersLogFilterer.getSummaryBasedOnXMattersLogs(incidentID, issueManager);

            //If there are no xMatters log entries with <=30 mins gap with current time, then summary wont be returned
            if (isNotBlank(summary)) {
                incidentMappings.put(incidentID, summary);
            }
        }

        //Put the values from the hashmap to the bean so the REST service can provide it in JSON
        for(Map.Entry<String, String> entry : incidentMappings.entrySet()){
            incidentsToAlertList.add(new IncidentAlertsRESTModel(entry.getKey(),entry.getValue()));
        }

        return incidentsToAlertList;
    }

}