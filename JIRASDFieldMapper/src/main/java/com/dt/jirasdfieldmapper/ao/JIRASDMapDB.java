package com.dt.jirasdfieldmapper.ao;

import net.java.ao.Entity;

/**
 * The AO Entity that holds the mapping between JIRA and xMatters fields
 *
 * Created by yagnesh.bhat on 6/24/2015.
 */
public interface JIRASDMapDB extends Entity {
    String getJiraField();
    void setJiraField(String jiraField);
    String getJiraSDField();
    void setJiraSDField(String jiraSDField);
    String getJiraSDFieldId();
    void setJiraSDFieldId(String jiraSDFieldId);

}
