package com.dt.jira.pagerduty.intgt.plugin.ao;

import net.java.ao.Entity;

/**
 * The AO Entity that holds the mapping between JIRA and xMatters fields
 *
 * Created by yagnesh.bhat on 6/24/2015.
 */
public interface JiraFieldDB extends Entity {
    String getJiraField();
    void setJiraField(String jiraField);
}
