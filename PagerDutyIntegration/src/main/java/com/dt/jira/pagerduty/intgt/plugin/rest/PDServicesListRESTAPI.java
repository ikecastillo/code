package com.dt.jira.pagerduty.intgt.plugin.rest;

import com.atlassian.jira.util.json.JSONArray;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import org.apache.commons.codec.binary.Base64;
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
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yagnesh.Bhat on 5/25/2016.
 */

@Path("/getAllPDServices")
public class PDServicesListRESTAPI {

    private static PluginSettingsFactory pluginSettingsFactory;
    private static final String PLUGIN_STORAGE_KEY = ConfigResource.Config.class.getName();

    private final Logger log = LoggerFactory.getLogger(PDServicesListRESTAPI.class);

    private String pdBaseUrl = "";
    private String servicesLimit = "";
    private String pdAPIToken = "";
    private List<PDServicesListBean> pdServicesListBeans;


    public PDServicesListRESTAPI (PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettingsFactory = pluginSettingsFactory;
        setConfiguration();
    }

    private void setConfiguration() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        pdBaseUrl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pddturl");
        servicesLimit = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdserviceslimit");
        pdAPIToken = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".pdapitoken");
    }

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() {
        makeHttpGetRequest(pdBaseUrl +"/api/v1/services?limit=" + servicesLimit);
        return Response.ok(pdServicesListBeans).build();
    }


    private void makeHttpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", "Token token=" + pdAPIToken);
        HttpClient defaultHttpClient = null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpGet);
        } catch (IOException e) {
            log.error("Exception in getting services from PagerDuty", e);
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.debug("Response in getting services from PagerDuty from ITSM Side " + response.getEntity());
            parseResponse(response);
        }
    }
    private void parseResponse(HttpResponse response) {
        String jsonString = getJSONStringFromResponse(response);
        JSONArray jsonarray = null;
        try {
            JSONObject responseJSON = new JSONObject(jsonString);
            jsonarray = responseJSON.getJSONArray("services");
            pdServicesListBeans = new ArrayList<>();
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                String service_key = jsonobject.getString("service_key");
                PDServicesListBean sdCustFieldBean = new PDServicesListBean();
                sdCustFieldBean.setServiceKey(service_key);
                sdCustFieldBean.setServiceName(name);
                pdServicesListBeans.add(sdCustFieldBean);
            }
        } catch (JSONException e) {
            log.error("Exception in constructing the JSON response " ,e);
        }
    }

    private String getJSONStringFromResponse(HttpResponse response) {
        StringBuilder sb = new StringBuilder();
        try (DataInputStream in = new DataInputStream(response.getEntity().getContent())){
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("IO Exception in constructing JSON String from response ",e);
        }

        String jsonString = sb.toString();
        return jsonString;
    }
    public String getTokenId() {
        // encoding byte array into base 64
        byte[] encoded = Base64.encodeBase64((servicesLimit).getBytes());
        String sid = new String(encoded);
        return sid;
    }
}
