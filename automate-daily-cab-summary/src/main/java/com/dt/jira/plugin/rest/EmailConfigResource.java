package com.dt.jira.plugin.rest;

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
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
/**
 * A resource of EmailConfigResource.
 */
@Path("/emailConfigResource")
public class EmailConfigResource {

	private final Logger logger = Logger.getLogger(EmailConfigResource.class);
	private static final String PLUGIN_STORAGE_KEY = EmailConfig.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;


  private String userName;
  private String password;
  private String feilds;
  
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
    
    public String getFeilds() {
    	return feilds;
	}
	public void setFeilds(String feilds) {
		this.feilds = feilds;
	}
	
	
	public EmailConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
	  
    
    
    private void setConfiguration(){
   	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String userName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".userName");
		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".password");
		String feilds = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".feilds");
   		
   		setUserName(userName);
   		setPassword(password);
   		setFeilds(feilds);
   		
   		
   		
   		
    }
    
    
    
    
        @GET
		@Path("/emailConfig")
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
		      EmailConfig emailConfig = new EmailConfig();
		      emailConfig.setUserName((String) settings.get(EmailConfig.class.getName() + ".userName"));
		      emailConfig.setPassword((String) settings.get(EmailConfig.class.getName() + ".password"));
		      emailConfig.setFeilds((String) settings.get(EmailConfig.class.getName() + ".feilds"));
		     
		      return emailConfig;
		    }
		  })).build();
		}
	
		
		
		
		
		@PUT
		@Path("/emailConfigSave")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final EmailConfig emailConfig, @Context HttpServletRequest request)
		{
			
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
		      pluginSettings.put(EmailConfig.class.getName() + ".userName", emailConfig.getUserName());
		      pluginSettings.put(EmailConfig.class.getName() + ".password", emailConfig.getPassword());
		      pluginSettings.put(EmailConfig.class.getName() + ".feilds", emailConfig.getFeilds());
		     return null;
		    }
		  });
		  return Response.noContent().build();
		}
    
    
    
     /**
     *URL decode for parameter string.
     */
   /* private static String decodeString(String rawValue){
    try {
   return  URLDecoder.decode(rawValue, "UTF-8");
    } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace(); 
    }
        return "";
       
    }
    
    
  private static String encodeString(String decodeString){
    try {
   return  URLEncoder.encode(decodeString, "UTF-8");
    } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace(); 
    }
        return "";
       
    }*/
    
    
	
}




