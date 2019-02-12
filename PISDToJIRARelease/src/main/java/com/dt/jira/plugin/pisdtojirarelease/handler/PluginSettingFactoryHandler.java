package com.dt.jira.plugin.pisdtojirarelease.handler;

/**
 * Created by kiran.muthoju on 8/4/2016.
 *
 * A Handler holds the information for plugin settings details.
 */
public interface PluginSettingFactoryHandler {
    public  String getTokenId();
    public  String getServiceDeskAppName();
    public  String getServiceDeskAppUrl();
    public  String getServiceDeskAppLinkID();
    public  String getJiraEnggAppName();
    public  String getJiraEnggURL();
    public  String getJiraEnggAppLinkID();
    public  String getUserName();
}
