package com.dt.plugin.fecru.announcementbanner.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.plugin.fecru.announcementbanner.service.BannerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by yagnesh.bhat on 7/26/2016.
 */

@Path("/bannerREST")
public class BannerRESTAPI {

    private BannerService bannerService;

    public BannerRESTAPI(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @POST
    @AnonymousAllowed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addBanner")
    public Response addBanner(final BannerBean bannerBean) {
        bannerService.addBanner(bannerBean);
        return getBanner();
    }

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBanner")
    public Response getBanner() {
        return Response.ok(bannerService.getBanner()).build();
    }
}
