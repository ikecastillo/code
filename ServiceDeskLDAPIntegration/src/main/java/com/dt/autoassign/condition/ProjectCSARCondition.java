package com.dt.autoassign.condition;

import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

/**
 * Created by yagnesh.bhat on 4/28/2016.
 */
public class ProjectCSARCondition extends com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition {

    @Override
    public boolean shouldDisplay(ApplicationUser applicationUser, JiraHelper jiraHelper) {
        if(jiraHelper.getProjectObject().getKey().equalsIgnoreCase("CSAR")){
            return true;
        }else{
            return false;
        }
    }
}
