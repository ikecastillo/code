package com.dt.jira.plugin.pisdtojirarelease.rest;


import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
 * A Restful resource to save or update the plugin setting details
 */
@Path("/pisdtoreladminconfig")
public class AdminConfigResource {


    private final UserManager userManager;


    private final PluginSettingsFactory pluginSettingsFactory;


    private final TransactionTemplate transactionTemplate;

    private Logger log = LoggerFactory.getLogger(AdminConfigResource.class);

    @Autowired
    public AdminConfigResource(UserManager userManager,
                               PluginSettingsFactory pluginSettingsFactory,
                               TransactionTemplate transactionTemplate) {
        this.userManager = userManager;
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.transactionTemplate = transactionTemplate;
    }




    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class Config
    {
        @XmlElement
        private String pisdtorelticketuid;

        @XmlElement
        private String pisdtorelticketpwd;

        @XmlElement
        private String pisdToRelticketApplicationName;

        @XmlElement
        private String pisdToRelticketApplicationUrl;



        public String getPisdtorelticketuid(){
            return pisdtorelticketuid;
        }
        public void setPisdtorelticketuid(String v){
            this.pisdtorelticketuid = v;
        }
        public String getPisdtorelticketpwd(){
            return pisdtorelticketpwd;
        }
        public void setPisdtorelticketpwd(String v){
            this.pisdtorelticketpwd = v;
        }

        public String getPisdToRelticketApplicationName() {
            return pisdToRelticketApplicationName;
        }

        public void setPisdToRelticketApplicationName(String pisdToRelticketApplicationName) {
            this.pisdToRelticketApplicationName = pisdToRelticketApplicationName;
        }

        public String getPisdToRelticketApplicationUrl() {
            return pisdToRelticketApplicationUrl;
        }

        public void setPisdToRelticketApplicationUrl(String pisdToRelticketApplicationUrl) {
            this.pisdToRelticketApplicationUrl = pisdToRelticketApplicationUrl;
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

                config.setPisdtorelticketuid((String) settings.get(Config.class.getName() + ".pisdtorelticketuid"));
                config.setPisdtorelticketpwd((String) settings.get(Config.class.getName() + ".pisdtorelticketpwd"));
                config.setPisdToRelticketApplicationName((String) settings.get(Config.class.getName()+ ".pisdToRelticketApplicationName"));
                config.setPisdToRelticketApplicationUrl((String) settings.get(Config.class.getName()+ ".pisdToRelticketApplicationUrl"));


                return config;
            }
        })).build();
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(final Config config, @Context HttpServletRequest request)
    {
        log.debug("PUTTING VALUES");
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

                pluginSettings.put(Config.class.getName() + ".pisdtorelticketuid", config.getPisdtorelticketuid());
                pluginSettings.put(Config.class.getName() + ".pisdtorelticketpwd", config.getPisdtorelticketpwd());

                pluginSettings.put(Config.class.getName() + ".pisdToRelticketApplicationName", config.getPisdToRelticketApplicationName());
                pluginSettings.put(Config.class.getName() + ".pisdToRelticketApplicationUrl", config.getPisdToRelticketApplicationUrl());

                return null;
            }
        });
        return Response.noContent().build();
    }
}
