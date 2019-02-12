package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.xmatters.intgt.plugin.service.EventConfigService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Contains all the REST calls pertaining to
 * - Add xMatters event config in AO
 * - Delete xMatters event config from AO
 * - Get all xMatters events configured in AO
 * - Get a particular xmatters event configured in AO , based on Form API ID
 *
 * Created by Yagnesh.Bhat on 10/19/2015.
 */
@Path("/eventConfig")
public class EventConfiguratorRESTAPI {

    private EventConfigService eventConfigService;

    public EventConfiguratorRESTAPI(EventConfigService eventConfigService) {
        this.eventConfigService = eventConfigService;
    }

    /**
     * Adds an xMatters event config to AO
     *
     * @param eventBean The Bean to add
     * @return JSON with all mappings that also includes the mapping currently added/deleted
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addXMattersEventConfig")
    public Response addXMattersEventConfig(final EventBean eventBean) {
        EventBean eventBean1 = eventConfigService.getEvent(eventBean);
        if(eventBean1 == null){
            eventConfigService.addEvent(eventBean);
        }
        return Response.ok(getAllEventsFromDB()).build();
    }

    /**
     * Deletes an xMatters event config to AO
     *
     * @param eventBean: event to be deleted.
     * @return After deletion, it returns the current JSON of all events in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteXMattersEventConfig")
    public Response deleteXMattersEventConfig(final EventBean eventBean){
        eventConfigService.deleteEvent(eventBean);
        return Response.ok(getAllEventsFromDB()).build();
    }

    /**
     * Gets all the xMatters Event configurations from AO
     *
     * @return JSON with all the events configured
     */
    @POST
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllXMattersEventConfigs")
    public Response getAllXMattersEventConfigs() {
        return Response.ok(getAllEventsFromDB()).build();
    }

    /**
     * Helper method that actually gets all the xmatters events configured at a given point of time from DB.
     *
     * @return List of EventBeans that contain all the xmatters events configured
     */
    private List<EventBean> getAllEventsFromDB() {
        return eventConfigService.getAllEventsFromDB();
    }
}
