package com.dt.jira.plugin.pisdtojirarelease.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.pisdtojirarelease.service.ProjMapService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 */

@Path("/projmapconfig")
public class ProjMapRESTAPI {
    private ProjMapService projMapService;

    public ProjMapRESTAPI(ProjMapService projMapService) {
        this.projMapService = projMapService;
    }

    /**
     * Adds an SD config to AO
     *
     * @param projMapBean The Bean to add
     * @return JSON with all mappings that also includes the mapping currently added/deleted
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addProjectMapping")
    public Response addProjectMapping(final ProjMapBean projMapBean) {
        ProjMapBean projMapBean1 = projMapService.getMapping(projMapBean);
        if(projMapBean1 == null){
            projMapService.addMapping(projMapBean);
        }
        return Response.ok(getAllProjectMappingsFromDB()).build();
    }

    /**
     * Deletes an SD config from AO
     *
     * @param projMapBean: bean to be deleted.
     * @return After deletion, it returns the current JSON of all events in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteProjectMapping")
    public Response deleteProjectMapping(final ProjMapBean projMapBean){
        projMapService.deleteMapping(projMapBean);
        return Response.ok(getAllProjectMappingsFromDB()).build();
    }

    /**
     * Gets all the SD configurations from AO
     *
     * @return JSON with all the events configured
     */
    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllProjectMappings")
    public Response getAllProjectMappings() {
        return Response.ok(getAllProjectMappingsFromDB()).build();
    }

    /**
     * Helper method that actually gets all the SD onfigurations at a given point of time from DB.
     *
     * @return List of ProjMapBeans that contain all the SD configured
     */
    private List<ProjMapBean> getAllProjectMappingsFromDB() {
        return projMapService.getAllMappingsFromDB();
    }



}
