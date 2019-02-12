package com.dt.jira.plugin.updatereporter.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.index.IssueIndexingService;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.jira.security.JiraAuthenticationContext;

/**
 * A resource of UpdateReporterResource.
 */
@Path("/updatepsid")
public class UpdateReporterResource {
    
	private final Logger logger = Logger
			.getLogger(UpdateReporterResource.class);
    private final IssueIndexingService issueIndexingService;  
			
	public		UpdateReporterResource(IssueIndexingService issueIndexingService){
			  this.issueIndexingService=issueIndexingService;
			}
        @PUT
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/reporter")
		public Response put(@QueryParam("reporter") String reporter ,@QueryParam("issueKey") String issueKey, @QueryParam("incidentId") String incidentId){
	JiraAuthenticationContext authenticationContext = ComponentAccessor.	getJiraAuthenticationContext();
        MutableIssue mutableIssue= ComponentAccessor.getIssueManager().getIssueObject(issueKey);
            mutableIssue.setReporter(ComponentAccessor.getUserManager().getUserByName(reporter));
           // Issue issue = ComponentAccessor.getIssueManager().updateIssue(authenticationContext.getLoggedInUser(),mutableIssue,eventDispatchOption.ISSUE_UPDATED,true);
		   
		CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
		CustomField customFeildObj = cfManager.getCustomFieldObjectByName("Incident ID");
		
		customFeildObj.updateValue(null, mutableIssue,  new ModifiedValue(
				customFeildObj.getValue(mutableIssue),incidentId),new DefaultIssueChangeHolder());
            mutableIssue.store();
            setReindex(mutableIssue);
            return Response.ok("Successfully updated PISD ticket " + issueKey + ",changed reporter to " + reporter
			+ " and updated incident ID custom field to " + incidentId).build();
        }
    
    private void setReindex(MutableIssue mutableIssue) {
		try {
			issueIndexingService.reIndex(mutableIssue);
		} catch (IndexException ie) {
						logger.debug("index issue" + ie.getMessage());
		}
	}

}
