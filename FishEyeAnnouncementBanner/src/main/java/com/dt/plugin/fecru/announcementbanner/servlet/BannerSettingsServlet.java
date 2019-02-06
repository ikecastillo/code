package com.dt.plugin.fecru.announcementbanner.servlet;


import com.atlassian.templaterenderer.TemplateRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * This servlet renders the banner setup page under global settings
 *
 * Created by yagnesh.bhat on 7/25/2016.
 */


public class BannerSettingsServlet  extends HttpServlet {

    private final TemplateRenderer templateRenderer;

    public  BannerSettingsServlet(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map emptyMap = new HashMap();
        request.setAttribute("decorator", "atl.admin");
        response.setContentType("text/html");
        templateRenderer.render("/template/bannerSetup.vm", emptyMap, response.getWriter());
    }
}
