package com.dt.jira.plugin.pisdtojirarelease.handler;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.pisdtojirarelease.rest.AdminConfigResource;
import com.dt.jira.plugin.pisdtojirarelease.utils.ApplicationIDFinder;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * A HandlerImpl  holds the information of the plugin settings factory details.
 * Created by kiran.muthoju on 8/9/2016.
 */
public class PluginSettingFactoryHandlerServiceImpl implements PluginSettingFactoryHandler{
    /* ServiceDesk application name */
    private  String servicedeskPisdApplicationName = "";
    /* Jira Engg. Application Name */
    private  String jiraEnggApplicationName = "";
    /* user name and password. */
    private  String usernamePwd ="";
    /* BaseUrl of Jira Engg. System. */
    private  String jiraEnggBaseurl ="";
    // Jira Engg. Application Link ID
    private  String jiraEnggApplicationlinkId ="";
    //Service Desk Application link ID
    private  String servicedeskPisdApplicationlinkId ="";
    //Service Desk Application URL
    private  String servicedeskPisdApplicationUrl ="";
    //Admin account
    private  String userName="";


    public static PluginSettingsFactory pluginSettingsFactory = null;

    public static final String PLUGIN_STORAGE_KEY = AdminConfigResource.Config.class.getName();
    public final Logger logger = LoggerFactory.getLogger(PluginSettingFactoryHandler.class);

    public PluginSettingFactoryHandlerServiceImpl(PluginSettingsFactory psf){
        pluginSettingsFactory = psf;
        setConfiguration();
    }


    private void setConfiguration() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();

        String userid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdtorelticketuid");
        userName = userid;
        String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdtorelticketpwd");
        usernamePwd =  userid+":"+password;
        jiraEnggApplicationName = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdToRelticketApplicationName");
        servicedeskPisdApplicationUrl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pisdToRelticketApplicationUrl");
        servicedeskPisdApplicationName = ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_TITLE);
        logger.info("Current APPLICATION NAME IS " + servicedeskPisdApplicationName);

        ApplicationIDFinder applicationIDFinder = new ApplicationIDFinder(jiraEnggApplicationName, servicedeskPisdApplicationName,
                ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL), getTokenId());
        Map<String, String> applicationIDMap = applicationIDFinder.createApplicationIDMap();
        jiraEnggBaseurl = applicationIDMap.get(jiraEnggApplicationName).split("\\|")[0];
        jiraEnggApplicationlinkId = applicationIDMap.get(jiraEnggApplicationName).split("\\|")[1];
        servicedeskPisdApplicationlinkId = applicationIDMap.get(servicedeskPisdApplicationName).split("\\|")[1];

        logger.info("Set createRemoteIssueLinkInJira baseURL = " + jiraEnggBaseurl);
        logger.info("Set Application Link ID of Jira Engg as " + jiraEnggApplicationlinkId);
        logger.info("Set Application Link ID of SD as " + servicedeskPisdApplicationlinkId);
    }
    /*This method applies Base 64 encoding to user credentials and forms a secure id.*/

    @Override
    public String getTokenId() {
        // encoding byte array into base 64
        byte[] encoded = Base64.encodeBase64((usernamePwd).getBytes());
        String sid = new String(encoded);
        return sid;
    }

    @Override
    public String getServiceDeskAppName() {
        return servicedeskPisdApplicationName;
    }


    @Override
    public String getServiceDeskAppUrl() {
        return servicedeskPisdApplicationUrl;
    }

    @Override
    public String getServiceDeskAppLinkID() {
        return servicedeskPisdApplicationlinkId;
    }

    @Override
    public String getJiraEnggAppName() {
        return jiraEnggApplicationName;
    }

    @Override
    public String getJiraEnggURL() {
        return jiraEnggBaseurl;
    }

    @Override
    public String getJiraEnggAppLinkID() {
        return jiraEnggApplicationlinkId;
    }

    public String getUserName() {
        return userName;
    }


}
