package com.dt.jirasdfieldmapper.rest;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.fields.FieldException;
import com.atlassian.jira.issue.fields.NavigableField;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * REST Resource to return the project issue fields based on the issue type.
 */
@Path("/jirafields")
public class ProjectFields {

    private final ProjectManager projectManager;

    public ProjectFields(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{projectkey}/{issuetype}")
    public Response getProjectFieldsFromPath(@PathParam("projectkey")String projectKey,
                                             @PathParam("issuetype")String issueType) {
        return Response.ok(new ProjectFieldsModel(projectKey, issueType, getProjectFields(projectKey, issueType))).build();
    }

    /**
     * Helper method that actually returns all the fields of a given project and Issue Type,
     * including system fields as a comma separated string.
     *
     * @param projectKey
     * @param issueType
     * @return Project issue fields separated by commas
     */
    private String getProjectFields(String projectKey, String issueType) {
	StringBuilder fields = new StringBuilder();
	String prefix = "";

    try {
        Set<NavigableField> navField=ComponentAccessor.getFieldManager().getAllAvailableNavigableFields();
        List<NavigableField> list = new ArrayList<NavigableField>(navField);
        Collections.sort(list,new Comparator<NavigableField>() {

			@Override
			public int compare(NavigableField paramT1, NavigableField paramT2) {
				return paramT1.getName().compareTo(paramT2.getName());
			}
        		
		});
		
            for (int i = 0; i < list.size(); i++) {
                NavigableField navigableField = list.get(i);
                fields.append(prefix);
                prefix = ",";
                fields.append(navigableField.getName());

           }
        } catch (FieldException e) {
            e.printStackTrace();
        }


    return fields.toString();
    }
}