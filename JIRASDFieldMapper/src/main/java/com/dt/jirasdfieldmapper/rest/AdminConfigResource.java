package com.dt.jirasdfieldmapper.rest;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 4/20/2016.
 */
@Path("/jirasdfmadminconfig")
public class AdminConfigResource {

    private final UserManager userManager;
    private final PluginSettingsFactory pluginSettingsFactory;
    private final TransactionTemplate transactionTemplate;

    public AdminConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory,
                               TransactionTemplate transactionTemplate) {
        this.userManager = userManager;
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.transactionTemplate = transactionTemplate;
    }


    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class Config {
        @XmlElement
        private String serviceDeskUserId;

        @XmlElement
        private String serviceDeskPwd;

        @XmlElement
        private String serviceDeskURL;

        public String getServiceDeskUserId(){
            return serviceDeskUserId;
        }
        public void setServiceDeskUserId(String v){
            this.serviceDeskUserId = v;
        }
        public String getServiceDeskPwd(){
            return serviceDeskPwd;
        }
        public void setServiceDeskPwd(String v){
            this.serviceDeskPwd = v;
        }

        public String getServiceDeskURL() {
            return serviceDeskURL;
        }

        public void setServiceDeskURL(String serviceDeskURL) {
            this.serviceDeskURL = serviceDeskURL;
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context HttpServletRequest request)
    {
        String username = userManager.getRemoteUsername(request);
        if (username == null || !userManager.isSystemAdmin(username))
        {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok(transactionTemplate.execute(new TransactionCallback()
        {
            public Object doInTransaction()
            {
                PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
                Config config = new Config();

                config.setServiceDeskUserId((String) settings.get(Config.class.getName() + ".serviceDeskUserId"));
                config.setServiceDeskPwd((String) settings.get(Config.class.getName() + ".serviceDeskPwd"));


                config.setServiceDeskURL((String) settings.get(Config.class.getName()+ ".serviceDeskURL"));

                return config;
            }
        })).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(final Config config, @Context HttpServletRequest request)
    {
        String username = userManager.getRemoteUsername(request);
        if (username == null || !userManager.isSystemAdmin(username))
        {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        transactionTemplate.execute(new TransactionCallback()
        {
            public Object doInTransaction()
            {
                PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();

                pluginSettings.put(Config.class.getName() + ".serviceDeskUserId", config.getServiceDeskUserId());
                pluginSettings.put(Config.class.getName() + ".serviceDeskPwd", config.getServiceDeskPwd());


                pluginSettings.put(Config.class.getName() + ".serviceDeskURL", config.getServiceDeskURL());

                return null;
            }
        });
        return Response.noContent().build();
    }
}
