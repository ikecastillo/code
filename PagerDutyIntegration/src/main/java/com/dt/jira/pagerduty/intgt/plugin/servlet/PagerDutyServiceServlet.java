package com.dt.jira.pagerduty.intgt.plugin.servlet;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yagnesh.Bhat on 5/23/2016.
 */
public class PagerDutyServiceServlet  extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(PagerDutyServiceServlet.class);
    private final TemplateRenderer renderer;
    private final ProjectManager projectManager;
    private final ApplicationProperties applicationProperties;
    private final OptionsManager optionsManager;
    private final JiraBaseUrls jiraBaseUrls;
    private final JiraAuthenticationContext authenticationContext;

    public PagerDutyServiceServlet(TemplateRenderer renderer, ProjectManager pm,
                              ApplicationProperties applicationProperties,
                              OptionsManager optionsManager,
                              JiraBaseUrls jiraBaseUrls,JiraAuthenticationContext authenticationContext) {
        this.applicationProperties=applicationProperties;
        this.renderer = renderer;
        this.projectManager = pm;
        this.optionsManager = optionsManager;
        this.jiraBaseUrls = jiraBaseUrls;
        this.authenticationContext = authenticationContext;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> ctx = new HashMap<>();
        Project project = projectManager.getProjectObjByKey("ITIM");
        ctx.put("projectKey",  "ITIM");

        request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);
        response.setContentType("text/html;charset=utf-8");

        ApplicationUser loggedInUser = authenticationContext.getLoggedInUser();
        /*PermissionManager permissionManager = ComponentAccessor.getPermissionManager();
*/


        ProjectRoleManager projectRoleManager = ComponentManager.getComponentInstanceOfType(ProjectRoleManager.class);
        ProjectRole administratorProjectRole = projectRoleManager.getProjectRole("Administrators");

        if (loggedInUser == null || !projectRoleManager.isUserInProjectRole(loggedInUser, administratorProjectRole, project) ) {
            renderer.render("templates/projadmin/pd-service-setup-error.vm", ctx, response.getWriter());
        } else {
            renderer.render("templates/projadmin/pd-service-setup.vm", ctx, response.getWriter());
        }


    }

}
