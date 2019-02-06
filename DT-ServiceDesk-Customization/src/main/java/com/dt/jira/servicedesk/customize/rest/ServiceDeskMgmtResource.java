package com.dt.jira.servicedesk.customize.rest;

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
 * A resource of ServiceDeskMgmtResource.
 */
@Path("/ServiceDeskMgmtResource")
public class ServiceDeskMgmtResource {

	private final Logger logger = Logger.getLogger(ServiceDeskMgmtResource.class);
	private static final String PLUGIN_STORAGE_KEY = ServiceDeskLdap.class.getName();
	private final UserManager userManager;
	private final PluginSettingsFactory pluginSettingsFactory;
	private final TransactionTemplate transactionTemplate;

	private String userName = "";
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getInitCtx() {
		return initCtx;
	}

	public void setInitCtx(String initCtx) {
		this.initCtx = initCtx;
	}

	public String getSrvrName() {
		return srvrName;
	}

	public void setSrvrName(String srvrName) {
		this.srvrName = srvrName;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public String getDtincLdapPwd() {
		return dtincLdapPwd;
	}

	public void setDtincLdapPwd(String dtincLdapPwd) {
		this.dtincLdapPwd = dtincLdapPwd;
	}

	private String passWord = "";
	private String initCtx = "";
	private String srvrName = "";	
	private String baseDn = "" ;
	private String dtincLdapPwd = "";

	public ServiceDeskMgmtResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
	  {
	    this.userManager = userManager;
	    this.pluginSettingsFactory = pluginSettingsFactory;
	    this.transactionTemplate = transactionTemplate;
	    setConfiguration();
	  }
	  
    
    
    private void setConfiguration(){
   	 PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
   	 	String initCtx  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapInitCtx");
		String srvrName  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapSrvrName");
		String baseDn = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapBaseDn");
   		String username  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapUid");
   		String password  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".ldapPwd");
		String dtincldappwd  = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".dtincLdapPwd");
   		logger.info("initCtx: "+ initCtx);
   		logger.info("srvrName: "+srvrName);
   		logger.info("baseDn: "+baseDn);
   		logger.info("username: "+username);
   		//logger.info("password: "+password);
   		
   		logger.info("Set plugin configuration");
   		
   		setInitCtx(initCtx);
   		setSrvrName(srvrName);
   		setBaseDn(baseDn);
   		setUserName(username);
   		setPassWord(password);
   		setDtincLdapPwd(dtincldappwd);
   		
   		
    }
	public DirContext getDirectoryContext()
	  {
		 Properties properties = new Properties();
		 InitialDirContext initialdircontext = null;
				 try
				 {
					  properties.put("java.naming.factory.initial",getInitCtx());
					  properties.put("java.naming.provider.url", getSrvrName());
					  properties.put("java.naming.security.principal",getUserName());
					  properties.put("java.naming.security.credentials",getPassWord());
					  initialdircontext = new InitialDirContext(properties);
					  logger.info("LDAP connection object from  LDAP Server[DirectoryContextFactory][getDirectoryContext]::"+initialdircontext);
					 

					  return initialdircontext;
				}catch (Exception e)
					{
						e.printStackTrace();
						return null;
					}
				
		}
		
		
		
		
		@GET
		@Path("/ldapServiceDesk")
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
		      ServiceDeskLdap ldapSrvc = new ServiceDeskLdap();
		      ldapSrvc.setLdapInitCtx((String) settings.get(ServiceDeskLdap.class.getName() + ".ldapInitCtx"));
		      ldapSrvc.setLdapSrvrName((String) settings.get(ServiceDeskLdap.class.getName() + ".ldapSrvrName"));
		      ldapSrvc.setLdapBaseDn((String) settings.get(ServiceDeskLdap.class.getName() + ".ldapBaseDn"));
		      //System.out.println("===========basedn============="+ServiceDeskLdap.class.getName() + ".ldapBaseDn");
		      ldapSrvc.setLdapUid((String) settings.get(ServiceDeskLdap.class.getName() + ".ldapUid"));
		      ldapSrvc.setLdapPwd((String) settings.get(ServiceDeskLdap.class.getName() + ".ldapPwd"));
			  ldapSrvc.setDtincLdapPwd((String) settings.get(ServiceDeskLdap.class.getName() + ".dtincLdapPwd"));

		      logger.info("************* " + ldapSrvc.getLdapUid());
		      return ldapSrvc;
		    }
		  })).build();
		}
		
		@PUT
		@Path("/ldapsave")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final ServiceDeskLdap ldapSrvc, @Context HttpServletRequest request)
		{
			
			logger.info("calling ldapsave");
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
		      logger.info(ServiceDeskLdap.class.getName() + ".ldapInitCtx"+ ldapSrvc.getLdapInitCtx());
		      logger.info(ServiceDeskLdap.class.getName() + ".ldapSrvrName"+ ldapSrvc.getLdapSrvrName());
		      logger.info(ServiceDeskLdap.class.getName() + ".ldapBaseDn"+ ldapSrvc.getLdapBaseDn());
		      logger.info(ServiceDeskLdap.class.getName() + ".ldapUid"+ ldapSrvc.getLdapUid());
		      //logger.info(ServiceDeskLdap.class.getName() + ".ldapPwd"+ ldapSrvc.getLdapPwd());
		      
		      pluginSettings.put(ServiceDeskLdap.class.getName() + ".ldapInitCtx", ldapSrvc.getLdapInitCtx());
		      pluginSettings.put(ServiceDeskLdap.class.getName() + ".ldapSrvrName", ldapSrvc.getLdapSrvrName());
		      pluginSettings.put(ServiceDeskLdap.class.getName() + ".ldapBaseDn", ldapSrvc.getLdapBaseDn());
		      pluginSettings.put(ServiceDeskLdap.class.getName() + ".ldapUid", ldapSrvc.getLdapUid());
			  pluginSettings.put(ServiceDeskLdap.class.getName() + ".ldapPwd", ldapSrvc.getLdapPwd());
				pluginSettings.put(ServiceDeskLdap.class.getName() + ".dtincLdapPwd", ldapSrvc.getDtincLdapPwd());

		      return null;
		    }
		  });
		  return Response.noContent().build();
		}
	
}
