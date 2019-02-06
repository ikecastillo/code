package com.dt.jira.gadget.plugin.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

/**
 * A resource of message.
 */
@Path("/message")
public class AssignedToSelectedGroupResource {

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage()
    {
IssueManager issueManager = ComponentAccessor.getIssueManager();
    	List<AssignedToSelectedGroupResourceModel> groupList = new ArrayList<AssignedToSelectedGroupResourceModel>();
        ApplicationUser user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
        System.out.println("user>>>>>>>>>>>>>> "+user.getName()+" belongs to the following>>>>>>>>>>>>>>"); 
        ComponentAccessor.getGroupManager().getGroupNamesForUser(user.getName()); 
        Collection<String> userGroups =  ComponentAccessor.getGroupManager().getGroupNamesForUser(user.getName());
        for (Iterator i = userGroups.iterator(); i.hasNext();) {
            String group =  (String) i.next();
            System.out.println("group name>>>>>>>>>>>>>>"+group);
            groupList.add(new AssignedToSelectedGroupResourceModel(group,group));
        }
       return Response.ok(groupList).build();
    }
}