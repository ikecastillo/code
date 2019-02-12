package com.dt.jira.plugin.rest;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;

@Path("/sfconfig")
public class ConfigResource
{
  private final UserManager userManager;
  private final PluginSettingsFactory pluginSettingsFactory;
  private final TransactionTemplate transactionTemplate;

  public ConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, 
                  TransactionTemplate transactionTemplate)
  {
    this.userManager = userManager;
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.transactionTemplate = transactionTemplate;
  }

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public static final class Config
{
  @XmlElement private String sfintegrationUrl;
	@XmlElement private String sfintegrationUid;
  @XmlElement private String sfintegrationPwd;   
   @XmlElement private String sfClientId;
 @XmlElement private String  sfClientSecretKey;
  @XmlElement private String  sfInterval;
  
  public String getSfintegrationUrl(){
    return sfintegrationUrl;
  }
  public void setSfintegrationUrl(String v){
    this.sfintegrationUrl = v;
  }
  public String getSfintegrationUid(){
    return sfintegrationUid;
  }
  public void setSfintegrationUid(String v){
    this.sfintegrationUid = v;
  }
	public String getSfintegrationPwd(){
    return sfintegrationPwd;
  }
  public void setSfintegrationPwd(String v){
    this.sfintegrationPwd = v;
  }
  public String getSfClientId() {
	return sfClientId;
  }

  public void setSfClientId(String v) {
	this.sfClientId = v;
  }

  public String getSfClientSecretKey() {
	return sfClientSecretKey;
  }

  public void setSfClientSecretKey(String v) {
	this.sfClientSecretKey = v;
  }
public String getSfInterval() {
	return sfInterval;
}
public void setSfInterval(String sfInterval) {
	this.sfInterval = sfInterval;
}
 
  
	
	
}

@GET
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
      Config config = new Config();
      config.setSfintegrationUrl((String) settings.get(Config.class.getName() + ".sfintegrationUrl"));
      config.setSfintegrationUid((String) settings.get(Config.class.getName() + ".sfintegrationUid"));
      config.setSfintegrationPwd((String) settings.get(Config.class.getName() + ".sfintegrationPwd"));
		config.setSfClientId((String) settings.get(Config.class.getName() + ".sfClientId"));
		config.setSfClientSecretKey((String) settings.get(Config.class.getName() + ".sfClientSecretKey"));
		config.setSfInterval((String) settings.get(Config.class.getName() + ".sfInterval"));
		
	  System.out.println("************* " + config.getSfInterval());
      return config;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final Config config, @Context HttpServletRequest request)
{
  String username = userManager.getRemoteUsername(request);
  System.out.println("username: "+username);
  if (username == null || !userManager.isSystemAdmin(username))
  {
    return Response.status(Status.UNAUTHORIZED).build();
  }

  transactionTemplate.execute(new TransactionCallback()
  {
    public Object doInTransaction()
    {
      PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
      pluginSettings.put(Config.class.getName() + ".sfintegrationUrl", config.getSfintegrationUrl());
      pluginSettings.put(Config.class.getName() + ".sfintegrationUid", config.getSfintegrationUid());
			pluginSettings.put(Config.class.getName() + ".sfintegrationPwd", config.getSfintegrationPwd());
			pluginSettings.put(Config.class.getName() + ".sfClientId", config.getSfClientId());
			pluginSettings.put(Config.class.getName() + ".sfClientSecretKey", config.getSfClientSecretKey());
			pluginSettings.put(Config.class.getName() + ".sfInterval", config.getSfInterval());
      return null;
    }
  });
  return Response.noContent().build();
}
}