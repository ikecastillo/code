package com.dt.jira.plugin.pisdtojirarelease.condition;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.jira.plugin.pisdtojirarelease.rest.ProjMapBean;
import com.dt.jira.plugin.pisdtojirarelease.service.ProjMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by yagnesh.bhat on 7/19/2016.
 * This Class contains the methods which executes automatically and checks teh conditions before user clicks on the link.
 */
public class ProjectPISDCondition extends AbstractJiraCondition {

    private final Logger log = LoggerFactory.getLogger(ProjectPISDCondition.class);
    private final ProjMapService projMapService;
    public ProjectPISDCondition(ProjMapService projMapService){
        this.projMapService = projMapService;
    }
    /*
     Need to get the ProjectMappingService
     get the object from the constructor
     * This method pre-executes and checks if the project has permissions to create the ticket. if it is true then the
     * Link to create project is activated else deactivated.
     */
    @Override
    public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
        final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");
        Project project = currentIssue.getProjectObject();
        log.info("Project Key found to enable the link for " + project.getKey());
        // define true or false based the projMapService
        List<ProjMapBean> projMapBeenList =  projMapService.getAllMappingsFromDB();
        for(ProjMapBean projMapBean: projMapBeenList){
		log.info("------------++++++++--------------");
		log.info(projMapBean.getSdProjKey());
		log.info("------------+++++++++--------------");
            if(project.getKey().equals(projMapBean.getSdProjKey())){
                return true;
            }
        }
        return false;
    }
}
