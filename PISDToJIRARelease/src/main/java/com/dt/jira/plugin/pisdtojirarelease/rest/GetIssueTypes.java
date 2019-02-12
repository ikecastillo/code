package com.dt.jira.plugin.pisdtojirarelease.rest;


import com.atlassian.jira.util.json.JSONArray;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Get Issue Types of a given Project
 */
@Path("/getIssueTypes")
public class GetIssueTypes {



	private final Logger log = LoggerFactory.getLogger(GetIssueTypes.class);
	private static PluginSettingFactoryHandler pluginSettingFactoryHandler;

	public GetIssueTypes(PluginSettingFactoryHandler pluginSettingFactoryHandler) {
		this.pluginSettingFactoryHandler = pluginSettingFactoryHandler;
	}




	@GET
	@AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON})
	public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception{

		String url = pluginSettingFactoryHandler.getServiceDeskAppUrl() + PISDToJiraConstants.SERVICE_DESK_GET_ISSUE_TYPES_URL+projectkey;

		HttpResponse response = null;
		BufferedReader br;
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Content-Type", "application/json");
		httpGet.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
		HttpClient defaultHttpClient = HttpClientBuilder.create().build();
		try {
			response = defaultHttpClient.execute(httpGet);
		}
		catch (IOException e) {
			log.error("Exception in executing the REST call IN RETREIVING JIRA RELEASE ISSUE TYPES",e);
		}

		List<GetIssueTypesModel> issueTypesModelList = new ArrayList<>();
		if (response != null && response.getEntity().getContentLength() != 0) {
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuilder content = new StringBuilder();
			String line;
			while (null != (line = br.readLine())) {
				content.append(line);
			}
			log.info("**********Response *****"+ content.toString());
			final JSONArray objArray = new JSONArray(content.toString());
			log.info("**********length *****" + objArray.length());
			/* Creating Java List Object from the JSON received from Rest Call.*/

			for (int i = 0; i < objArray.length(); i++) {
				JSONObject obj = objArray.getJSONObject(i);
				log.info("**********obj *****" + obj.toString());
				String label = obj.getString("label");
				log.info("**********Issue Type label  *****" + label);
				String value = obj.getString("value");
				log.info("**********Issue Type value  *****" + value);
				GetIssueTypesModel issueTypesModel = new GetIssueTypesModel(label, value);
				issueTypesModelList.add(issueTypesModel);
			}

		}
		return Response.ok(issueTypesModelList).build();
	}


}
