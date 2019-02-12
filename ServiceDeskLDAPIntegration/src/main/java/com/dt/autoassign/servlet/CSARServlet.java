package com.dt.autoassign.servlet;

import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yagnesh.bhat on 4/28/2016.
 */
public class CSARServlet extends HttpServlet{

    private final Logger logger = Logger.getLogger(CSARServlet.class);
    private final TemplateRenderer renderer;
    private final ProjectManager projectManager;
    private final ApplicationProperties applicationProperties;
    private final OptionsManager optionsManager;
    private final JiraBaseUrls jiraBaseUrls;

    public CSARServlet(TemplateRenderer renderer, ProjectManager pm,
                              ApplicationProperties applicationProperties,
                              OptionsManager optionsManager,
                              JiraBaseUrls jiraBaseUrls) {
        this.applicationProperties=applicationProperties;
        this.renderer = renderer;
        this.projectManager = pm;
        this.optionsManager = optionsManager;
        this.jiraBaseUrls = jiraBaseUrls;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> ctx = new HashMap<>();
        Project project = projectManager.getProjectObjByKey("CSAR");
        request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);
        response.setContentType("text/html;charset=utf-8");

        renderer.render("templates/projadmin/csaradmin.vm", ctx, response.getWriter());

    }


}
