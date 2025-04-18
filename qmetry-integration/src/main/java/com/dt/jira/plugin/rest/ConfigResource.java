

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

@Path("/qmetryconfig")
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
  @XmlElement private String qmetryUrl;
	@XmlElement private String qmetryUid;
  @XmlElement private String qmetryPwd;      
  public String getQmetryUrl(){
    return qmetryUrl;
  }
  public void setQmetryUrl(String v){
    this.qmetryUrl = v;
  }
  public String getQmetryUid(){
    return qmetryUid;
  }
  public void setQmetryUid(String v){
    this.qmetryUid = v;
  }
	public String getQmetryPwd(){
    return qmetryPwd;
  }
  public void setQmetryPwd(String v){
    this.qmetryPwd = v;
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
      config.setQmetryUrl((String) settings.get(Config.class.getName() + ".qmetryUrl"));
      config.setQmetryUid((String) settings.get(Config.class.getName() + ".qmetryUid"));
      config.setQmetryPwd((String) settings.get(Config.class.getName() + ".qmetryPwd"));
			System.out.println("************* " + config.getQmetryUid());
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
      pluginSettings.put(Config.class.getName() + ".qmetryUrl", config.getQmetryUrl());
      pluginSettings.put(Config.class.getName() + ".qmetryUid", config.getQmetryUid());
			pluginSettings.put(Config.class.getName() + ".qmetryPwd", config.getQmetryPwd());
      return null;
    }
  });
  return Response.noContent().build();
}
}



