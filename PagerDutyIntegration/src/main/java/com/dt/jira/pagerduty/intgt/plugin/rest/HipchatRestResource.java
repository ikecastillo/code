package com.dt.jira.pagerduty.intgt.plugin.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;


import com.atlassian.jira.JiraException;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A resource of message.
 */
@Path("/getroomdetails")
public class HipchatRestResource {

	private static String environment = "https://api.hipchat.com/v2/room/";
	private static PluginSettingsFactory pluginSettingsFactory;
	private String hipChatToken = "";
	private static final String PLUGIN_STORAGE_KEY = ConfigResource.Config.class.getName();
	private final Logger log = LoggerFactory.getLogger(HipchatRestResource.class);
	
	public HipchatRestResource(PluginSettingsFactory psf) {
		this.pluginSettingsFactory = psf;
		setHipChatToken();
	}

	private void setHipChatToken() {
		PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
		hipChatToken = (String) pluginSettings.get(PLUGIN_STORAGE_KEY +".pdhipchattoken");
	}


    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("issuekey") String issuekey) throws JiraException
    {
	String response = "";
    	try{
			//String sid = "CK6FmS3iR5jkGJVs9EdWPOghlwJhB9EQJ8cqmSCG";
			 log.debug(" Passing the following HipChat Token ID " + hipChatToken);
			 response = Getauthenticate(hipChatToken,issuekey);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
    	
       return Response.ok(new HipchatRestResourceModel(response,"Success")).build();
    }
    
    public static String Getauthenticate(String sid,String issuekey) {
		   // Create an instance of HttpClient.
	    HttpClient httpclient = new HttpClient();
	    httpclient.setTimeout(6000000);
	   String url = environment+issuekey;
	   System.out.println(" URL "+ url);
	   GetMethod get = new GetMethod(url);
	    get.setRequestHeader("Content-Type", "application/json");	
	    get.setRequestHeader("Authorization", "Bearer " + sid);
	   
	    
	    
	   /* HttpMethodParams params = new HttpMethodParams();
	    params.setParameter("auth_token",sid);
	  
	    get.setParams(params);*/
	    
	   // String body = "{  'recipients':   [   {'targetName': 'JIRATest'}  ] }";
	    String responseBody = "";
	    try {
			httpclient.executeMethod(get);	      // Read the response body.
	        responseBody = get.getResponseBodyAsString();
	        System.out.println("Got room details successfully : " + new String(responseBody));
	    
	      
	    }catch (IOException e) {
	      System.err.println("Fatal transport error: " + e.getMessage());
	      e.printStackTrace();
	    }  finally {
	      // Release the connection.
			get.releaseConnection();
	    }
	return responseBody;		
	  }
}