package com.dt.jira.xmatters.intgt.plugin.rest;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Path("/xconfig")
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
  @XmlElement private String xmattersUid;
  @XmlElement private String xmattersPwd;   
  

  @XmlElement private String xmattersInterval;

  @XmlElement private String jiraAdminEmail;

  @XmlElement private String xmattersHipchattoken;

  
  
public String getXmattersInterval() {
	return xmattersInterval;
}
public void setXmattersInterval(String xmattersInterval) {
	this.xmattersInterval = xmattersInterval;
}

  public String getXmattersUid(){
    return xmattersUid;
  }
  public void setXmattersUid(String v){
    this.xmattersUid = v;
  }
	public String getXmattersPwd(){
    return xmattersPwd;
  }
  public void setXmattersPwd(String v){
    this.xmattersPwd = v;
  }

	public String getJiraAdminEmail() {
		return jiraAdminEmail;
	}
	public void setJiraAdminEmail(String jiraAdminEmail) {
		this.jiraAdminEmail = jiraAdminEmail;
	}

  public String getXmattershipchattoken() {
    return xmattersHipchattoken;
  }

  public void setXmattershipchattoken(String xmattersHipchattoken) {
    this.xmattersHipchattoken = xmattersHipchattoken;
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

	  config.setXmattersUid((String) settings.get(Config.class.getName() + ".xmattersUid"));
      config.setXmattersPwd((String) settings.get(Config.class.getName() + ".xmattersPwd"));

	  config.setXmattersInterval((String) settings.get(Config.class.getName() + ".xmattersInterval"));
	  config.setJiraAdminEmail((String) settings.get(Config.class.getName() + ".jiraAdminEmail"));

      config.setXmattershipchattoken((String) settings.get(Config.class.getName() + ".xmattershipchattoken"));

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
	  
      pluginSettings.put(Config.class.getName() + ".xmattersUid", config.getXmattersUid());
	  pluginSettings.put(Config.class.getName() + ".xmattersPwd", config.getXmattersPwd());

	  pluginSettings.put(Config.class.getName() + ".xmattersInterval", config.getXmattersInterval());	
	  
	  pluginSettings.put(Config.class.getName() + ".jiraAdminEmail", config.getJiraAdminEmail());

      pluginSettings.put(Config.class.getName() + ".xmattershipchattoken", config.getXmattershipchattoken());
	  
      return null;
    }
  });
  return Response.noContent().build();
}
}