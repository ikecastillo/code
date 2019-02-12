package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.jira.issue.IssueManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * A resource of incidents to be alerted.
 */
@Path("/getIncidentsToAlert")
public class IncidentAlertsREST {

    private final IssueManager issueManager;

    public IncidentAlertsREST(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIncidentAlerts(@QueryParam("unresolvedIncidents") String unresolvedIncidents) {
       return Response.ok(getIncidentsToAlert(unresolvedIncidents)).build();
    }

    private List<IncidentAlertsRESTModel> getIncidentsToAlert(String unresolvedIncidents) {
        return new IncidentAlertsRESTModel().getIncidentsToAlert(unresolvedIncidents, issueManager);
    }
}