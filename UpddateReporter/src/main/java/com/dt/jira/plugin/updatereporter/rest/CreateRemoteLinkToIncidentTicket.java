package com.dt.jira.plugin.updatereporter.rest;

import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Yagnesh.Bhat on 4/21/2016.
 */
@Path("/createremotelinktoincident")
public class CreateRemoteLinkToIncidentTicket {

    private final JiraAuthenticationContext authenticationContext;
    private final Logger log = org.slf4j.LoggerFactory.getLogger(CreateRemoteLinkToIncidentTicket.class);

    public CreateRemoteLinkToIncidentTicket(JiraAuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    @PUT
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/linktoitsmticket")
    public Response put(@QueryParam("pisdTicketID") String pisdTicketID, @QueryParam("pisdTicketKey") String pisdTicketKey,
                        @QueryParam("incidentTicketId") String incidentTicketId, @QueryParam("incidentTicketKey") String incidentTicketKey,
                        @QueryParam("ITSMApplicationLinkID") String itsmApplicationLinkId,
                        @QueryParam("JIRAITSMBaseURL") String jiraitsmBaseURL,
                        @QueryParam("itsmApplicationName") String itsmApplicationName) {
        String response = createRemoteIssueLink(pisdTicketID, pisdTicketKey, Long.parseLong(incidentTicketId), incidentTicketKey,
                itsmApplicationLinkId, jiraitsmBaseURL, itsmApplicationName);
        return Response.ok(response).build();
    }

    private String createRemoteIssueLink(String issueId, String issueKey, Long remoteIssueId, String remoteIssueKey,
                                         String applicationLinkID, String jiraitsmBaseURL, String itsmApplicationName) {

        String remoteLinkUrl = jiraitsmBaseURL + "/browse/";
        if (StringUtils.isNotBlank(issueKey) && (remoteIssueId != null)) {
            RemoteIssueLinkBuilder remoteIssueLinkBuilder = new RemoteIssueLinkBuilder();
            remoteIssueLinkBuilder.url(remoteLinkUrl + remoteIssueKey);
            remoteIssueLinkBuilder.globalId("appId="+applicationLinkID+"&issueId="+remoteIssueId);
            remoteIssueLinkBuilder.title(remoteIssueKey);
            //remoteIssueLinkBuilder.statusName("Waiting for support");
            remoteIssueLinkBuilder.applicationType("com.atlassian.jira");
            remoteIssueLinkBuilder.applicationName(itsmApplicationName);
            remoteIssueLinkBuilder.issueId(Long.valueOf(issueId));
            remoteIssueLinkBuilder.relationship("relates to");
            //remoteIssueLinkBuilder.build();
            RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
            log.debug(remoteIssueLink + "remoteIssueLinkBuilder---------" + remoteIssueLinkBuilder);
            RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
            RemoteIssueLinkService.CreateValidationResult createResult = remoteIssueLinkService.validateCreate(authenticationContext.getUser(), remoteIssueLink);
            log.debug("createResult of issue link  " + createResult.getErrorCollection().getReasons());
            RemoteIssueLinkService.RemoteIssueLinkResult result = remoteIssueLinkService.create(authenticationContext.getUser(), createResult);
            log.debug("result of issue link  " + result);

        }
        return "Created links successfully from the Incident Ticket into the Service Desk Ticket";
    }
}
