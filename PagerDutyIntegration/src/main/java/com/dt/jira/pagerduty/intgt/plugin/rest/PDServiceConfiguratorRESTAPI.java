package com.dt.jira.pagerduty.intgt.plugin.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.pagerduty.intgt.plugin.service.PDServicesConfigService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Contains all the REST calls pertaining to add/delete/retrieve PD services setup for the ITIM Project
 *
 * Created by Yagnesh.Bhat on 5/24/2016.
 */
@Path("/pdServiceConfig")
public class PDServiceConfiguratorRESTAPI {

    private PDServicesConfigService pdServicesConfigService;

    public PDServiceConfiguratorRESTAPI(PDServicesConfigService pdServicesConfigService) {
        this.pdServicesConfigService = pdServicesConfigService;
    }

    /**
     * Adds a PD Service config to AO
     *
     * @param pdServiceBean The Bean to add
     * @return JSON with all PD Service configs that also includes the config currently added/deleted
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addPDServiceConfig")
    public Response addPDServiceConfig(final PDServiceBean pdServiceBean) {
        PDServiceBean pdServiceBean1 = pdServicesConfigService.getService(pdServiceBean);
        if(pdServiceBean1 == null){
            pdServicesConfigService.addService(pdServiceBean);
        }
        return Response.ok(getAllPDServiceConfigsFromDB()).build();
    }

    /**
     * Deletes a PD Service config from AO
     *
     * @param pdServiceBean: event to be deleted.
     * @return After deletion, it returns the current JSON of all PD Service configs in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deletePDServiceConfig")
    public Response deletePDServiceConfig(final PDServiceBean pdServiceBean){
        pdServicesConfigService.deleteService(pdServiceBean);
        return Response.ok(getAllPDServiceConfigsFromDB()).build();
    }

    /*
     * Update a PD Service config from AO
     *
     * @param pdServiceBean: bean to be updated
     * @return After updation, it returns the current JSON of all PD Service configs in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updatePDServiceConfig")
    public Response updatePDServiceConfig(final PDServiceBean[] pdServiceBean){
        PDServiceBean pdServiceBeanToEdit = pdServicesConfigService.getService(pdServiceBean[0]);
        PDServiceBean alreadyExistBeanToAdd = pdServicesConfigService.getService(pdServiceBean[1]);

        //If the bean to be edited is there and the edited bean is not there then we edit
        if (pdServiceBeanToEdit != null && alreadyExistBeanToAdd == null) {
            pdServicesConfigService.deleteService(pdServiceBean[0]);
            pdServicesConfigService.addService(pdServiceBean[1]);
        }

        return Response.ok(getAllPDServiceConfigsFromDB()).build();
    }

    /**
     * Gets all the a PD Service configs from AO
     *
     * @return JSON with all the PD Service configs
     */
    @POST
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllPDServiceConfigs")
    public Response getAllPDServiceConfigs() {
        return Response.ok(getAllPDServiceConfigsFromDB()).build();
    }

    /**
     * Helper method that actually gets all the a PD Services configured at a given point of time from DB.
     *
     * @return List of EventBeans that contain all the PD Service configs
     */
    private List<PDServiceBean> getAllPDServiceConfigsFromDB() {
        return pdServicesConfigService.getAllServicesFromDB();
    }

    /**
     * Searches PD Service configs from AO
     *
     * @param pdServiceBean The Bean to search
     * @return JSON with all PD Service configs that has the bean properties
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchPDServiceConfig")
    public Response searchPDServiceConfig(final PDServiceBean pdServiceBean) {
        List<PDServiceBean> pdServiceBeanSearchResults = pdServicesConfigService.searchService(pdServiceBean);
        return Response.ok(pdServiceBeanSearchResults).build();
    }
}
