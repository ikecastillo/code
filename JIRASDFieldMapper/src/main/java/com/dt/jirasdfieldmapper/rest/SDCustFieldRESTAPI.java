package com.dt.jirasdfieldmapper.rest;

import com.atlassian.jira.util.json.JSONArray;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jirasdfieldmapper.rest.AdminConfigResource.Config;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yagnesh.bhat on 5/16/2016.
 */

@Path("/getAllServiceDeskFields")
public class SDCustFieldRESTAPI {

    private static PluginSettingsFactory pluginSettingsFactory;
    private static final String PLUGIN_STORAGE_KEY = Config.class.getName();

    private final Logger log = LoggerFactory.getLogger(SDCustFieldRESTAPI.class);

    private String sdBaseUrl = "";
    private String userNamePwd = "";
    private List<SDCustFieldBean> custFieldBeans;


    public SDCustFieldRESTAPI(PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettingsFactory = pluginSettingsFactory;
        setConfiguration();
    }

    private void setConfiguration() {
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        sdBaseUrl = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskURL");

        String userid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskUserId");
        String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskPwd");
        userNamePwd = userid+":"+password;
    }

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() {
        makeHttpGetRequest(sdBaseUrl+"/rest/api/2/field");
        return Response.ok(custFieldBeans).build();
    }


    private void makeHttpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", "Basic " + getTokenId());
        HttpClient defaultHttpClient = null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpGet);
        } catch (IOException e) {
            log.error("Exception in getting application link details from jira itsm", e);
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.debug("Response from getting ITSM Application Links from ITSM Side " + response.getEntity());
            parseResponse(response);
        }
    }
    private void parseResponse(HttpResponse response) {
        String jsonString = getJSONStringFromResponse(response);
        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(jsonString);
            custFieldBeans = new ArrayList<>();
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                String id = jsonobject.getString("id");
                SDCustFieldBean sdCustFieldBean = new SDCustFieldBean();
                sdCustFieldBean.setFieldId(id);
                sdCustFieldBean.setFieldName(name);
                custFieldBeans.add(sdCustFieldBean);
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
        byte[] encoded = Base64.encodeBase64((userNamePwd).getBytes());
        String sid = new String(encoded);
        return sid;
    }

}
