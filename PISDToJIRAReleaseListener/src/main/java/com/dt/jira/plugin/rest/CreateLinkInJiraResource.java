package com.dt.jira.plugin.rest;

import com.atlassian.jira.bc.issue.link.RemoteIssueLinkService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.link.RemoteIssueLink;
import com.atlassian.jira.issue.link.RemoteIssueLinkBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by Surya.Sriram on 7/25/2016.
 * This web service creates PISD reciprocal link in Jira Resource Management
 */

@Path("/jiraeng")
public class CreateLinkInJiraResource {
    private final Logger logger = LoggerFactory.getLogger(CreateLinkInJiraResource.class);


    private final JiraAuthenticationContext authenticationContext;

    public CreateLinkInJiraResource(JiraAuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }



    @POST
    @Path("/linktopisdticket")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createLink(@QueryParam("issueId") Long issueId,
                               @QueryParam("issueKey") String issueKey,
                               @QueryParam("remoteIssueId") String remoteIssueId,
                               @QueryParam("remoteIssueKey") String remoteIssueKey,
                               @QueryParam("PISDApplicationName") String pisdApplicationName,
                               @QueryParam("PISDApplicationLinkID") String pisdApplicationLinkID,
                               @QueryParam("PISDBaseURL") String pisdBaseURL) {


        String remoteLinkUrl = pisdBaseURL + "/browse/";
        try {
            if (StringUtils.isNotBlank(issueKey) && (remoteIssueId != null)) {
                RemoteIssueLinkBuilder remoteIssueLinkBuilder = new RemoteIssueLinkBuilder();
                remoteIssueLinkBuilder.url(remoteLinkUrl + remoteIssueKey);
                remoteIssueLinkBuilder.globalId("appId=" + pisdApplicationLinkID + "&issueId=" + remoteIssueId);
                remoteIssueLinkBuilder.title(remoteIssueKey);
                remoteIssueLinkBuilder.applicationType("com.atlassian.jira");
			    remoteIssueLinkBuilder.applicationName(pisdApplicationName);
                remoteIssueLinkBuilder.issueId(Long.valueOf(issueId));
                remoteIssueLinkBuilder.relationship("relates to");

                RemoteIssueLink remoteIssueLink = remoteIssueLinkBuilder.build();
                logger.debug(remoteIssueLink + "remoteIssueLinkBuilder---------" + remoteIssueLinkBuilder);
                RemoteIssueLinkService remoteIssueLinkService = ComponentAccessor.getComponent(RemoteIssueLinkService.class);
                RemoteIssueLinkService.CreateValidationResult createResult = remoteIssueLinkService.validateCreate(authenticationContext.getUser(), remoteIssueLink);

                logger.debug("createResult of issue link Error " + createResult.getErrorCollection().getErrors());
                logger.debug("createResult of issue link Error Message " + createResult.getErrorCollection().getErrorMessages());
                RemoteIssueLinkService.RemoteIssueLinkResult result = remoteIssueLinkService.create(authenticationContext.getUser(), createResult);
                logger.debug("result of issue link  " + result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return Response.ok("Success").build();
    }
}
