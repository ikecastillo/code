package com.dt.criticalAccess.requestApproval.DTRequestApproval.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;
/**
 * A resource of RequestApproverResource.
 */
@Path("/requestApproverResource")
public class RequestApproverResource {

	private final Logger logger = Logger.getLogger(RequestApproverResource.class);
	private static final String PLUGIN_STORAGE_KEY = ApproverConfig.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	
  private String chgUrl;
  private String remoteLinkUrl;
  private String applinkId;
  
    public String getChgUrl() {
    	return chgUrl;
	}
	public void setChgUrl(String chgUrl) {
		this.chgUrl = chgUrl;
	}
    
    
    
    public String getRemoteLinkUrl() {
    	return remoteLinkUrl;
	}
	public void setRemoteLinkUrl(String remoteLinkUrl) {
		this.remoteLinkUrl = remoteLinkUrl;
	}
    
    
    
    public String getApplinkId() {
    	return applinkId;
	}
	public void setApplinkId(String applinkId) {
		this.applinkId = applinkId;
	}
	
	public RequestApproverResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
	  
    
    
    private void setConfiguration(){
   	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String chgUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".chgUrl");
		String remoteLinkUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".remoteLinkUrl");
		String applinkId = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".applinkId");
   		logger.info("chgUrl: "+ chgUrl);
   		logger.info("remoteLinkUrl: "+remoteLinkUrl);
   		logger.info("applinkId: "+applinkId);
   		  		
   		logger.info("Set plugin configuration");
   		
   		setChgUrl(chgUrl);
   		setRemoteLinkUrl(remoteLinkUrl);
   		setApplinkId(applinkId);
   		
    }
    
    
    
    
        @GET
		@Path("/approval")
		@Produces(MediaType.APPLICATION_JSON)
		public Response get(@Context HttpServletRequest request)
		{
		  String username = userManager.getRemoteUsername(request);
		  if (username == null || !userManager.isSystemAdmin(username))
		  {
		    return Response.status(Status.UNAUTHORIZED).build();
		  }

		  return Response.ok(transactionTemplate.execute(new TransactionCallback()
		  {
		    public Object doInTransaction()
		    {
		      PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		      ApproverConfig apprConfig = new ApproverConfig();
		      apprConfig.setChgUrl((String) settings.get(ApproverConfig.class.getName() + ".chgUrl"));
		      apprConfig.setRemoteLinkUrl((String) settings.get(ApproverConfig.class.getName() + ".remoteLinkUrl"));
		      apprConfig.setApplinkId((String) settings.get(ApproverConfig.class.getName() + ".applinkId"));
		     
		      return apprConfig;
		    }
		  })).build();
		}
	
		
		
		
		
		@PUT
		@Path("/approverSave")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final ApproverConfig approverConfig, @Context HttpServletRequest request)
		{
			
			System.out.println("calling approverSave");
		  String username = userManager.getRemoteUsername(request);
		  if (username == null || !userManager.isSystemAdmin(username))
		  {
		    return Response.status(Status.UNAUTHORIZED).build();
		  }

		  transactionTemplate.execute(new TransactionCallback()
		  {
		    public Object doInTransaction()
		    {
		      PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
		      logger.info(ApproverConfig.class.getName() + ".chgUrl"+ approverConfig.getChgUrl());
		      logger.info(ApproverConfig.class.getName() + ".remoteLinkUrl"+ approverConfig.getRemoteLinkUrl());
		      logger.info(ApproverConfig.class.getName() + ".applinkId"+ approverConfig.getApplinkId());
		      
       
		      
		      pluginSettings.put(ApproverConfig.class.getName() + ".chgUrl", approverConfig.getChgUrl());
		      pluginSettings.put(ApproverConfig.class.getName() + ".remoteLinkUrl", approverConfig.getRemoteLinkUrl());
		      pluginSettings.put(ApproverConfig.class.getName() + ".applinkId", approverConfig.getApplinkId());
		     return null;
		    }
		  });
		  return Response.noContent().build();
		}
	
}
