package com.dt.remote.pisdtktcreator.rest;

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
@Path("/pisdadminconfig")
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
    public static final class Config
    {
        @XmlElement
        private String pisdTicketUid;

        @XmlElement
        private String pisdTicketPwd;

        @XmlElement
        private String pisdticketSDApplicationName;

        @XmlElement
        private String pisdticketITSMApplicationName;


        public String getPisdTicketUid(){
            return pisdTicketUid;
        }
        public void setPisdTicketUid(String v){
            this.pisdTicketUid = v;
        }
        public String getPisdTicketPwd(){
            return pisdTicketPwd;
        }
        public void setPisdTicketPwd(String v){
            this.pisdTicketPwd = v;
        }

        public String getPisdticketSDApplicationName() {
            return pisdticketSDApplicationName;
        }

        public void setPisdticketSDApplicationName(String pisdticketSDApplicationName) {
            this.pisdticketSDApplicationName = pisdticketSDApplicationName;
        }

        public String getPisdticketITSMApplicationName() {
            return pisdticketITSMApplicationName;
        }

        public void setPisdticketITSMApplicationName(String pisdticketITSMApplicationName) {
            this.pisdticketITSMApplicationName = pisdticketITSMApplicationName;
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

                config.setPisdTicketUid((String) settings.get(Config.class.getName() + ".pisdTicketUid"));
                config.setPisdTicketPwd((String) settings.get(Config.class.getName() + ".pisdTicketPwd"));


                config.setPisdticketSDApplicationName((String) settings.get(Config.class.getName()+ ".pisdticketSDApplicationName"));
                config.setPisdticketITSMApplicationName((String) settings.get(Config.class.getName()+ ".pisdticketITSMApplicationName"));

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

                pluginSettings.put(Config.class.getName() + ".pisdTicketUid", config.getPisdTicketUid());
                pluginSettings.put(Config.class.getName() + ".pisdTicketPwd", config.getPisdTicketPwd());


                pluginSettings.put(Config.class.getName() + ".pisdticketSDApplicationName", config.getPisdticketSDApplicationName());
                pluginSettings.put(Config.class.getName() + ".pisdticketITSMApplicationName", config.getPisdticketITSMApplicationName());

                return null;
            }
        });
        return Response.noContent().build();
    }
}
