package com.dt.jira.plugin.rest;



import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;


import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


/**
 * A resource of message.
 */
@Path("/groupbyfields")
public class GroupByFieldResource {
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response getMessage() throws Exception{
	Response response=null;
	 ArrayList<GroupByFieldResourceModel> listOfModels= new ArrayList<GroupByFieldResourceModel>();
	 listOfModels.add(new GroupByFieldResourceModel("Severity"));
	 listOfModels.add(new GroupByFieldResourceModel("Cause"));
	 listOfModels.add(new GroupByFieldResourceModel("Resource"));
	 
	 response= Response.ok(listOfModels).build();	
	
	 return response;
  }
}