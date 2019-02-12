package com.dt.jira.plugin.standardbauchange.rest;

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
@Path("/smConfig")
public class ServiceDeskConfigResource {

	private final Logger logger = Logger.getLogger(ServiceDeskConfigResource.class);
	private static final String PLUGIN_STORAGE_KEY = ServiceDeskConfig.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	
	private String serviceDeskUrl;
	private String userName;
	private String password;
	
	
	
	public String getServiceDeskUrl() {
		return serviceDeskUrl;
	}
	public void setServiceDeskUrl(String serviceDeskUrl) {
		this.serviceDeskUrl = serviceDeskUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 
	public ServiceDeskConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
	  
    
    
    private void setConfiguration(){
   	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String serviceDeskUrl  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".serviceDeskUrl");
		String userName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".userName");
		String password = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".password");
		
		
   		logger.info("serviceDeskUrl: "+ serviceDeskUrl);
   		logger.info("userName: "+userName);
   		
   		
   		
   		logger.info("Set plugin configuration ServiceDeskConfig");
   		
   		setServiceDeskUrl(serviceDeskUrl);
   		setUserName(userName);
   		setPassword(password);
   	
   		
    }
    
    
    
    
        @GET
		@Path("/smConfigGet")
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
		      ServiceDeskConfig apprConfig = new ServiceDeskConfig();
		      apprConfig.setServiceDeskUrl((String) settings.get(ServiceDeskConfig.class.getName() + ".serviceDeskUrl"));
		      apprConfig.setUserName((String) settings.get(ServiceDeskConfig.class.getName() + ".userName"));
		      apprConfig.setPassword((String) settings.get(ServiceDeskConfig.class.getName() + ".password"));
			 
		     
		      return apprConfig;
		    }
		  })).build();
		}
	
		
		
		
		
		@PUT
		@Path("/smConfigPut")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final ServiceDeskConfig approverConfig, @Context HttpServletRequest request)
		{
			
			System.out.println("calling Save");
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
		      System.out.println(ServiceDeskConfig.class.getName() + ".serviceDeskUrl"+ approverConfig.getServiceDeskUrl());
		      System.out.println(ServiceDeskConfig.class.getName() + ".userName"+ approverConfig.getUserName());
		    
		      
       
		      
		      pluginSettings.put(ServiceDeskConfig.class.getName() + ".serviceDeskUrl", approverConfig.getServiceDeskUrl());
		      pluginSettings.put(ServiceDeskConfig.class.getName() + ".userName", approverConfig.getUserName());
		      pluginSettings.put(ServiceDeskConfig.class.getName() + ".password", approverConfig.getPassword());
			 
		     return null;
		    }
		  });
		  return Response.noContent().build();
		}
	
}
