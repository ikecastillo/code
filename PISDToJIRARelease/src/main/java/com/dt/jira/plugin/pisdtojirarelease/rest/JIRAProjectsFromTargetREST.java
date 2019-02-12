package com.dt.jira.plugin.pisdtojirarelease.rest;

import com.atlassian.jira.util.json.JSONArray;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.pisdtojirarelease.Constants.PISDToJiraConstants;
import com.dt.jira.plugin.pisdtojirarelease.handler.PluginSettingFactoryHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 * Rest call to fetch all the projects available in Jira Resource Managemnt.
e */
@Path("/getJIRAProjectsFromTarget")
public class JIRAProjectsFromTargetREST {


    private final Logger log = LoggerFactory.getLogger(JIRAProjectsFromTargetREST.class);
    private static PluginSettingFactoryHandler pluginSettingFactoryHandler;

    public JIRAProjectsFromTargetREST( PluginSettingFactoryHandler pluginSettingFactoryHandler) {
        this.pluginSettingFactoryHandler = pluginSettingFactoryHandler;
    }


    /*
    * This method fetches Use credentials and Project url from Intial Setup using Plugin Settings Factory.
    * */



    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() {
        List<JiraProject> projectList = new ArrayList<>();
        HttpResponse response = null;
        String jiraEnggUrl = pluginSettingFactoryHandler.getJiraEnggURL()+ PISDToJiraConstants.REST_API_LIST_OF_PROJECTS;
        HttpGet httpGet = new HttpGet(jiraEnggUrl);

        //set headers
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
        //create the client
        HttpClient defaultHttpClient = HttpClientBuilder.create().build();
        try {
            response = defaultHttpClient.execute(httpGet);
            if (response != null && response.getEntity().getContentLength() != 0) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder content = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    content.append(line);
                }
                log.info("**********Response *****" + content.toString());

                final JSONArray objArray = new JSONArray(content.toString());
                log.info("**********length *****" + objArray.length());
                /* Creating Java List Object from the JSON received from Rest Call.*/
                for (int i = 0; i < objArray.length(); i++) {
                    JiraProject jiraProject = new JiraProject();

                    JSONObject obj = objArray.getJSONObject(i);
                    log.info("**********obj *****" + obj.toString());
                    String projectKey = obj.getString("key");
                    jiraProject.setProjectKey(projectKey);
                    log.info("**********projectKey *****" + projectKey);
                    String projectName = obj.getString("name");
                    jiraProject.setProjectName(projectName);
                    log.info("Project name :  " + projectName);
                    projectList.add(jiraProject);
                }

            }
        } catch (IOException | JSONException e) {
                log.error("EXCEPTION CAUGHT in REST CALL to RETRIEVE JIRA RELEASE PROJECTS",e);
            httpGet.releaseConnection();
        }
        return Response.ok(projectList).build();
    }

}
