package com.dt.remote.pisdtktcreator.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.remote.pisdtktcreator.service.ConfigService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by yagnesh.bhat on 4/18/2016.
 */

@Path("/config")
public class ConfigRestAPI {

    private ConfigService configService;

    public ConfigRestAPI(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * Adds an SD config to AO
     *
     * @param configBean The Bean to add
     * @return JSON with all mappings that also includes the mapping currently added/deleted
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addConfig")
    public Response addConfig(final ConfigBean configBean) {
        ConfigBean configBean1 = configService.getConfig(configBean);
        if(configBean1 == null){
            configService.addConfig(configBean);
        }
        return Response.ok(getAllConfigsFromDB()).build();
    }

    /**
     * Deletes an SD config from AO
     *
     * @param configBean: config to be deleted.
     * @return After deletion, it returns the current JSON of all events in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteConfig")
    public Response deleteConfig(final ConfigBean configBean){
       configService.deleteConfig(configBean);
        return Response.ok(getAllConfigsFromDB()).build();
    }

    /**
     * Gets all the SD configurations from AO
     *
     * @return JSON with all the events configured
     */
    @POST
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllConfigs")
    public Response getAllXMattersEventConfigs() {
        return Response.ok(getAllConfigsFromDB()).build();
    }

    /**
     * Helper method that actually gets all the SD onfigurations at a given point of time from DB.
     *
     * @return List of ConfigBeans that contain all the SD configured
     */
    private List<ConfigBean> getAllConfigsFromDB() {
        return configService.getAllConfigsFromDB();
    }
}
