package com.dt.grouprolessync.rest;

import com.atlassian.jira.bc.projectroles.ProjectRoleService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.util.SimpleErrorCollection;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yagnesh.Bhat on 8/15/2016.
 */

@Path("/syncRoles")
public class GroupRolesREST {

    private final Logger log = LoggerFactory.getLogger(GroupRolesREST.class);

    /*private final ProjectRoleService projectRoleService = ComponentAccessor.getComponent(ProjectRoleService.class);*/

    private final ProjectRoleService projectRoleService;

    public GroupRolesREST(ProjectRoleService projectRoleService) {
        this.projectRoleService = projectRoleService;
        /*this.projectRoleService = ComponentAccessor.getComponent(ProjectRoleService.class);*/
    }
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addGroupToRole")
    public Response addGroupToRole(final GroupRolesBean groupRolesBean) {
        String projectsCSV = groupRolesBean.getProjectsCSV();
        //String groupToMimic = groupRolesBean.getGroupToMimic();
        String groupThatMimicks = groupRolesBean.getGroupThatMimicks();
        Long roleId = groupRolesBean.getRoleId();
        if (projectsCSV.equalsIgnoreCase("All")) {
            //Iterate through all the projects and add same roles to groupThatMimicks that are common to groupToMimic
            log.debug("Looks like this needs to work for ALL JIRA Projects");
            String[] allProjectsIndicator = {"All"};
            syncRolesForSelectedProjects(allProjectsIndicator, groupThatMimicks, roleId);
        } else {
            //Iterate through projects specified as CSV and add same roles to groupThatMimicks that are common
            // to groupToMimic
            String[] projectsToIterate = projectsCSV.split(",");
            log.debug("Projects to iterate are " + projectsToIterate);
            syncRolesForSelectedProjects(projectsToIterate, groupThatMimicks, roleId);
        }

        return Response.ok("Group " + groupRolesBean.getGroupThatMimicks() + " is provided all the roles in common to the group " +
                groupRolesBean.getGroupToMimic() + " from following projects in this JIRA Instance : " +
                groupRolesBean.getProjectsCSV())
                .build();
    }

    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removeGroupFromRole")
    public Response removeGroupFromRole(final GroupRolesBean groupRolesBean) {
        String projectsCSV = groupRolesBean.getProjectsCSV();
        //String groupToMimic = groupRolesBean.getGroupToMimic();
        String groupThatMimicks = groupRolesBean.getGroupThatMimicks();
        Long roleId = groupRolesBean.getRoleId();
        if (projectsCSV.equalsIgnoreCase("All")) {
            //Iterate through all the projects and add same roles to groupThatMimicks that are common to groupToMimic
            log.debug("Looks like this needs to work for ALL JIRA Projects");
            String[] allProjectsIndicator = {"All"};
            unsyncRolesForSelectedProjects(allProjectsIndicator, groupThatMimicks, roleId);
        } else {
            //Iterate through projects specified as CSV and add same roles to groupThatMimicks that are common
            // to groupToMimic
            String[] projectsToIterate = projectsCSV.split(",");
            log.debug("Projects to iterate are " + projectsToIterate);
            unsyncRolesForSelectedProjects(projectsToIterate, groupThatMimicks, roleId);
        }

        return Response.ok("Group " + groupRolesBean.getGroupThatMimicks() + " is removed from all roles in common to the group " +
                            groupRolesBean.getGroupToMimic() + " from following projects in this JIRA Instance : " +
                            groupRolesBean.getProjectsCSV())
                        .build();
    }

    private void unsyncRolesForSelectedProjects(String[] projectsToIterate, String groupThatMimicks, Long roleId) {
        ProjectManager pm = ComponentAccessor.getProjectManager();
        List<Project> projectList = new ArrayList<>();
        projectList = getProjects(projectsToIterate, pm, projectList);

        for (Project project : projectList) {
            //Call removeActorGroup here
            removeActorGroup(project, roleId, groupThatMimicks);
        }

    }

    private void syncRolesForSelectedProjects(String[] projectsToIterate, String groupThatMimicks,
                                              Long roleId) {
        ProjectManager pm = ComponentAccessor.getProjectManager();
        List<Project> projectList = new ArrayList<>();
        projectList = getProjects(projectsToIterate, pm, projectList);

        for (Project project : projectList) {
            //Call addActorGroup here
            addActorGroup(project, roleId, groupThatMimicks);
        }

    }

    private List<Project> getProjects(String[] projectsToIterate, ProjectManager pm, List<Project> projectList) {
        if (projectsToIterate[0].equalsIgnoreCase("All")) {
            projectList = pm.getProjectObjects();
        } else {
            for (String projectKey : projectsToIterate) {
                Project project1 = pm.getProjectObjByKey(projectKey);
                if (project1 != null) {
                    projectList.add(project1);
                }
            }
        }
        return projectList;
    }

    private void addActorGroup(Project project, final Long id,
                                  String groupThatMimicks)
    {

        final SimpleErrorCollection errorCollection = new SimpleErrorCollection();
        final ProjectRole projectRole = projectRoleService.getProjectRole(id, errorCollection);
        //checkForErrors(errorCollection);

        if (StringUtils.isBlank(groupThatMimicks))
        {
            log.error("GROUP NAME CANNOT BE BLANK WHILE SYNCING ROLES");
            return;
        }

        if (groupThatMimicks != null)
        {
            final SimpleErrorCollection addActorErrorCollection = new SimpleErrorCollection();
            projectRoleService.addActorsToProjectRole(Arrays.asList(groupThatMimicks), projectRole, project,
                    "atlassian-group-role-actor", addActorErrorCollection);
            log.info("Group "+ groupThatMimicks + "  added under project role " + projectRole.getName() + " for the project with key " +
            project.getKey());
            //checkForErrors(addActorErrorCollection);
        }

    }

    private void removeActorGroup(Project project, Long id, String groupThatMimicks) {
        final SimpleErrorCollection errorCollection = new SimpleErrorCollection();
        final ProjectRole projectRole = projectRoleService.getProjectRole(id, errorCollection);
        //checkForErrors(errorCollection);

        if (StringUtils.isBlank(groupThatMimicks))
        {
            log.error("GROUP NAME CANNOT BE BLANK WHILE SYNCING ROLES");
            return;
        }

        if (groupThatMimicks != null)
        {
            final SimpleErrorCollection addActorErrorCollection = new SimpleErrorCollection();
            projectRoleService.removeActorsFromProjectRole(Arrays.asList(groupThatMimicks), projectRole, project,
                    "atlassian-group-role-actor", addActorErrorCollection);
            log.info("Group "+ groupThatMimicks + "  removed under project role " + projectRole.getName() + " for the project with key " +
                    project.getKey());
            //checkForErrors(addActorErrorCollection);
        }
    }

   /* private void checkForErrors(SimpleErrorCollection errorCollection)  {
        if (errorCollection.hasAnyErrors())
        {
            throw new Exception("Error collection has errors!");
        }
    }*/
}
