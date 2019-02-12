package com.dt.jira.pagerduty.intgt.plugin.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.pagerduty.intgt.plugin.service.FieldMapperService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Contains all the REST methods pertaining to
 * - Adding JIRA -> XMatters Mapping
 * - Deleting JIRA -> XMatters Mapping
 * - Getting all the persisted JIRA -> XMatters Mappings
 *
 * Created by yagnesh.bhat on 6/24/2015.
 */
@Path("/pdFieldMapper")
public class FieldMapperRESTAPI {
   
    private FieldMapperService fieldMapperService;
    
    public FieldMapperRESTAPI(FieldMapperService fieldMapperService) {
        this.fieldMapperService = fieldMapperService;
    }

    /**
     * Adds a Mapping for a JIRA field. Note that there is a one-to-many mapping between
     * JIRA field to XMatters field. In other words,one JIRA field can be mapped to many xmatters fields
     * but not the other way round.
     *
     * @param fieldBean The Bean to add
     * @return JSON with all mappings that also includes the mapping currently added/deleted
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addMapping")
    public Response addMapping(final FieldBean fieldBean) {       
    	List<FieldBean> fieldBeans = fieldMapperService.getMapping(fieldBean);
    	if(fieldBeans != null && fieldBeans.size() == 0 ){
    		fieldMapperService.addMapping(fieldBean);
    	}
        return Response.ok(getAllMappingsFromDB()).build();
    }

    /**
     * Deletes a mapping for a XMatters field.
     *
     * @param fieldBean: Mapping to be deleted.
     * @return After deletion, it returns the current JSON of all mappings in response.
     */
    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteMapping")
    public Response deleteMapping(final FieldBean fieldBean){       
    	fieldMapperService.deleteMapping(fieldBean);
        return Response.ok(getAllMappingsFromDB()).build();
    }

    /**
     * Gets all the mappings from AO
     *
     * @return JSON with all the mappings
     */
    @POST
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllMappings")
    public Response getAllMappings() {
        return Response.ok(getAllMappingsFromDB()).build();
    }

    /**
     * Helper method that actually gets all the mappings at a given point of time from DB.
     *
     * @return mappingsList List of FieldBeans that contain all the mappings
     */
    private List<FieldBean> getAllMappingsFromDB() {
        return fieldMapperService.getAllMappingsFromDB();
    }

}