package com.dt.jira.plugin.innotas.config;

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

@Path("/innotasconfig")
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
  @XmlElement private String innotasUrl;
	@XmlElement private String innotasUid;
  @XmlElement private String innotasPwd;      
  public String getInnotasUrl(){
    return innotasUrl;
  }
  public void setInnotasUrl(String v){
    this.innotasUrl = v;
  }
  public String getInnotasUid(){
    return innotasUid;
  }
  public void setInnotasUid(String v){
    this.innotasUid = v;
  }
	public String getInnotasPwd(){
    return innotasPwd;
  }
  public void setInnotasPwd(String v){
    this.innotasPwd = v;
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
      config.setInnotasUrl((String) settings.get(Config.class.getName() + ".innotasUrl"));
      config.setInnotasUid((String) settings.get(Config.class.getName() + ".innotasUid"));
      config.setInnotasPwd((String) settings.get(Config.class.getName() + ".innotasPwd"));
			System.out.println("************* " + config.getInnotasUid());
      return config;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final Config config, @Context HttpServletRequest request)
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
      pluginSettings.put(Config.class.getName() + ".innotasUrl", config.getInnotasUrl());
      pluginSettings.put(Config.class.getName() + ".innotasUid", config.getInnotasUid());
			pluginSettings.put(Config.class.getName() + ".innotasPwd", config.getInnotasPwd());
      return null;
    }
  });
  return Response.noContent().build();
}
}