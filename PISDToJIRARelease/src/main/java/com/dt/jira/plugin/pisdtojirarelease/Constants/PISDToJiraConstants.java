package com.dt.jira.plugin.pisdtojirarelease.Constants;

/**
 * Created by Surya.Sriram on 8/9/2016.
 */
public class PISDToJiraConstants {
    public static final String JIRA_ENGG_REMOTE_LINK_URL_TRAILER = "/rest/remotelinktopisd/1.0/jiraeng/linktopisdticket?";
    public static final String JIRA_ENGG_CREATE_ISSUE_URL_TRAILER = "/rest/api/2/issue/";
    public static final String BASE_URL_TRAILER = "/browse/";
    public static final String REST_API_LIST_OF_PROJECTS = "/rest/api/2/project";
    public static final String SERVICE_DESK_GET_ISSUE_TYPES_URL = "/rest/remotelinktopisd/1.0/getIssueTypes?projectkey=";
    public static final String ISSUE_TYPE_STORY = "Story";
    public static final String SERVICE_DESK_APPLICATION_ID_LINK_URL_TRAILER = "/rest/applinks/1.0/applicationlink";
    public static final String JIRA_ENGG_PROJECTS_URL_TRAILER = "/rest/api/2/project";
    public static final String ATTACHMENTS_URL_TRAILER = "/attachments";
    public static final String ATTACHMENTS_FOLDER_PATH ="//opt//app//var//atlassian//application-data//jira//data//attachments//";
    public static final long ATTACHMENTS_FOLDER_NAME_TEN_THOUSAND = 10000 ;
    public static final String ATTACHMENTS_DOUBLE_SLASH = "//" ;
    public static final String REST_API_GET_USER = "/rest/api/2/user";
    public static final String REST_API_ADD_COMMENT = "/rest/api/2/issue";
}
