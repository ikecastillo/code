package com.dt.jira.plugin.servicedesk.standardbauchange.rest;

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
public class ITSMCHGConfigResource {

	private final Logger logger = Logger.getLogger(ITSMCHGConfigResource.class);
	private static final String PLUGIN_STORAGE_KEY = ITSMCHGConfig.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	
  private String chgUrl;
  private String remoteLinkUrl;
  private String applinkId;
  private String templatelinkURL;
  private String itsmUserName;
  private String itsmPassword;
  
  private String ldapSrvrname;
  private String ldapBasedn;
  private String ldapUid;
  private String ldapPwd;
	
	
	public String getLdapSrvrname() {
		return ldapSrvrname;
	}
	public void setLdapSrvrname(String ldapSrvrname) {
		this.ldapSrvrname = ldapSrvrname;
	}
	public String getLdapBasedn() {
		return ldapBasedn;
	}
	public void setLdapBasedn(String ldapBasedn) {
		this.ldapBasedn = ldapBasedn;
	}
	public String getLdapUid() {
		return ldapUid;
	}
	public void setLdapUid(String ldapUid) {
		this.ldapUid = ldapUid;
	}
	public String getLdapPwd() {
		return ldapPwd;
	}
	public void setLdapPwd(String ldapPwd) {
		this.ldapPwd = ldapPwd;
	}	
    public String getChgUrl() {
    	return chgUrl;
	}
	public void setChgUrl(String chgUrl) {
		this.chgUrl = chgUrl;
	}
    
    public String getItsmUserName() {
    	return itsmUserName;
	}
	public void setItsmUserName(String itsmUserName) {
		this.itsmUserName = itsmUserName;
	} 
	
	public String getItsmPassword() {
    	return itsmPassword;
	}
	public void setItsmPassword(String itsmPassword) {
		this.itsmPassword = itsmPassword;
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
	  public String getTemplatelinkURL() {
    	return templatelinkURL;
	}
	public void setTemplatelinkURL(String templatelinkURL) {
		this.templatelinkURL = templatelinkURL;
	} 
 
	public ITSMCHGConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
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
		String templatelinkURL = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".templatelinkURL");
		String itsmUserName = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".itsmUserName");
		String itsmPassword = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".itsmPassword");
		
   		String ldapSrvrname = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapSrvrname");
		String ldapBasedn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBasedn");
		String ldapUid = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
		String ldapPwd = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
		
   		logger.info("chgUrl: "+ chgUrl);
   		logger.info("remoteLinkUrl: "+remoteLinkUrl);
   		logger.info("applinkId: "+applinkId);
		logger.info("templatelinkURL: "+templatelinkURL);
   		
   		
   		logger.info("Set plugin configuration");
   		
   		setChgUrl(chgUrl);
   		setRemoteLinkUrl(remoteLinkUrl);
   		setApplinkId(applinkId);
   		setTemplatelinkURL(templatelinkURL);
   		setItsmUserName(itsmUserName);
		setItsmPassword(itsmPassword);
   		setLdapSrvrname(ldapSrvrname);
   		setLdapBasedn(ldapBasedn);
		setLdapUid(ldapUid);
		setLdapPwd(ldapPwd);
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
		      ITSMCHGConfig apprConfig = new ITSMCHGConfig();
		      apprConfig.setChgUrl((String) settings.get(ITSMCHGConfig.class.getName() + ".chgUrl"));
		      apprConfig.setRemoteLinkUrl((String) settings.get(ITSMCHGConfig.class.getName() + ".remoteLinkUrl"));
		      apprConfig.setApplinkId((String) settings.get(ITSMCHGConfig.class.getName() + ".applinkId"));
			  apprConfig.setTemplatelinkURL((String) settings.get(ITSMCHGConfig.class.getName() + ".templatelinkURL"));
			  apprConfig.setItsmUserName((String) settings.get(ITSMCHGConfig.class.getName() + ".itsmUserName"));
			  apprConfig.setItsmPassword((String) settings.get(ITSMCHGConfig.class.getName() + ".itsmPassword"));
			  
			  apprConfig.setLdapSrvrname((String) settings.get(ITSMCHGConfig.class.getName() + ".ldapSrvrname"));
			  apprConfig.setLdapBasedn((String) settings.get(ITSMCHGConfig.class.getName() + ".ldapBasedn"));
			  apprConfig.setLdapUid((String) settings.get(ITSMCHGConfig.class.getName() + ".ldapUid"));
			  apprConfig.setLdapPwd((String) settings.get(ITSMCHGConfig.class.getName() + ".ldapPwd"));
		     
		      return apprConfig;
		    }
		  })).build();
		}
	
		
		
		
		
		@PUT
		@Path("/approverSave")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final ITSMCHGConfig approverConfig, @Context HttpServletRequest request)
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
		      System.out.println(ITSMCHGConfig.class.getName() + ".chgUrl"+ approverConfig.getChgUrl());
		      System.out.println(ITSMCHGConfig.class.getName() + ".remoteLinkUrl"+ approverConfig.getRemoteLinkUrl());
		      System.out.println(ITSMCHGConfig.class.getName() + ".applinkId"+ approverConfig.getApplinkId());
			  System.out.println(ITSMCHGConfig.class.getName() + ".templatelinkURL"+ approverConfig.getTemplatelinkURL());
		      
       
		      
		      pluginSettings.put(ITSMCHGConfig.class.getName() + ".chgUrl", approverConfig.getChgUrl());
		      pluginSettings.put(ITSMCHGConfig.class.getName() + ".remoteLinkUrl", approverConfig.getRemoteLinkUrl());
		      pluginSettings.put(ITSMCHGConfig.class.getName() + ".applinkId", approverConfig.getApplinkId());
			  pluginSettings.put(ITSMCHGConfig.class.getName() + ".templatelinkURL", approverConfig.getTemplatelinkURL());
			  pluginSettings.put(ITSMCHGConfig.class.getName() + ".itsmUserName", approverConfig.getItsmUserName());
			  pluginSettings.put(ITSMCHGConfig.class.getName() + ".itsmPassword", approverConfig.getItsmPassword());
			   pluginSettings.put(ITSMCHGConfig.class.getName() + ".ldapSrvrname", approverConfig.getLdapSrvrname());
			  pluginSettings.put(ITSMCHGConfig.class.getName() + ".ldapBasedn", approverConfig.getLdapBasedn());
			   pluginSettings.put(ITSMCHGConfig.class.getName() + ".ldapUid", approverConfig.getLdapUid());
			  pluginSettings.put(ITSMCHGConfig.class.getName() + ".ldapPwd", approverConfig.getLdapPwd());
		     return null;
		    }
		  });
		  return Response.noContent().build();
		}
	
}
