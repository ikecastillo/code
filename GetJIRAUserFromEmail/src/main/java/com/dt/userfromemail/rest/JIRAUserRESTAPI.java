package com.dt.userfromemail.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.dt.userfromemail.utils.EmailToUserID.getUserIDFromEmail;

/**
 * Created by Yagnesh.Bhat on 4/4/2016.
 */
@Path("/getJiraUserFromEmailId")
public class JIRAUserRESTAPI {

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@QueryParam("useremail") String useremail) throws Exception{
        return Response.ok(getUserIDFromEmail(useremail)).build();
    }

}
