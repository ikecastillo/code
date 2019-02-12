package com.dt.jira.xmatters.intgt.plugin.rest;

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
  @XmlElement private String xmattersOutUrl; // Outage URL
  @XmlElement private String xmattersNonOutUrl; // Non-Outage URL
  @XmlElement private String xmattersinternalOutUrl; // Internal Outage URL
  @XmlElement private String xmattersinternalNonOutUrl; // Internal Non-Outage URL
  @XmlElement private String xmattersUid;
  @XmlElement private String xmattersPwd;   
  
  @XmlElement private String xmattersInternalServiceUrl; // Internal Service UUID
  @XmlElement private String xmattersExternalServiceUrl; // External Service UUID  
  @XmlElement private String xmattersInterval;
  
  @XmlElement private String xmattersExternalResponse1;
  @XmlElement private String xmattersExternalResponse2;
  @XmlElement private String xmattersInternalResponse1;
  @XmlElement private String xmattersInternalResponse2;
  
  @XmlElement private String jiraAdminEmail;
  
  public String getXmattersOutUrl(){
    return xmattersOutUrl;
  }
  public void setXmattersOutUrl(String v){
    this.xmattersOutUrl = v;
  }
  
  
  
public String getXmattersInterval() {
	return xmattersInterval;
}
public void setXmattersInterval(String xmattersInterval) {
	this.xmattersInterval = xmattersInterval;
}
public String getxmattersinternalOutUrl(){
    return xmattersinternalOutUrl;
  }
  public void setxmattersinternalOutUrl(String v){
    this.xmattersinternalOutUrl = v;
  }
  
  
  public String getxmattersinternalNonOutUrl(){
    return xmattersinternalNonOutUrl;
  }
  public void setxmattersinternalNonOutUrl(String v){
    this.xmattersinternalNonOutUrl = v;
  }
  
  
  public String getXmattersNonOutUrl(){
    return xmattersNonOutUrl;
  }
  public void setXmattersNonOutUrl(String v){
    this.xmattersNonOutUrl = v;
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
	public String getXmattersInternalServiceUrl() {
		return xmattersInternalServiceUrl;
	}
	public void setXmattersInternalServiceUrl(String xmattersInternalServiceUrl) {
		this.xmattersInternalServiceUrl = xmattersInternalServiceUrl;
	}
	public String getXmattersExternalServiceUrl() {
		return xmattersExternalServiceUrl;
	}
	public void setXmattersExternalServiceUrl(String xmattersExternalServiceUrl) {
		this.xmattersExternalServiceUrl = xmattersExternalServiceUrl;
	}
	public String getXmattersExternalResponse1() {
		return xmattersExternalResponse1;
	}
	public void setXmattersExternalResponse1(String xmattersExternalResponse1) {
		this.xmattersExternalResponse1 = xmattersExternalResponse1;
	}
	public String getXmattersExternalResponse2() {
		return xmattersExternalResponse2;
	}
	public void setXmattersExternalResponse2(String xmattersExternalResponse2) {
		this.xmattersExternalResponse2 = xmattersExternalResponse2;
	}
	public String getXmattersInternalResponse1() {
		return xmattersInternalResponse1;
	}
	public void setXmattersInternalResponse1(String xmattersInternalResponse1) {
		this.xmattersInternalResponse1 = xmattersInternalResponse1;
	}
	public String getXmattersInternalResponse2() {
		return xmattersInternalResponse2;
	}
	public void setXmattersInternalResponse2(String xmattersInternalResponse2) {
		this.xmattersInternalResponse2 = xmattersInternalResponse2;
	}
	public String getJiraAdminEmail() {
		return jiraAdminEmail;
	}
	public void setJiraAdminEmail(String jiraAdminEmail) {
		this.jiraAdminEmail = jiraAdminEmail;
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
      config.setXmattersOutUrl((String) settings.get(Config.class.getName() + ".xmattersOutUrl"));
	  config.setXmattersNonOutUrl((String) settings.get(Config.class.getName() + ".xmattersNonOutUrl"));
	  
	  config.setXmattersExternalResponse1((String) settings.get(Config.class.getName() + ".xmattersExternalResponse1"));
	  config.setXmattersExternalResponse2((String) settings.get(Config.class.getName() + ".xmattersExternalResponse2"));
	  
	  config.setxmattersinternalOutUrl((String) settings.get(Config.class.getName() + ".xmattersinternalOutUrl"));
	  config.setxmattersinternalNonOutUrl((String) settings.get(Config.class.getName() + ".xmattersinternalNonOutUrl"));
	  
	  config.setXmattersInternalResponse1((String) settings.get(Config.class.getName() + ".xmattersInternalResponse1"));
	  config.setXmattersInternalResponse2((String) settings.get(Config.class.getName() + ".xmattersInternalResponse2"));
	  
	  config.setXmattersUid((String) settings.get(Config.class.getName() + ".xmattersUid"));
      config.setXmattersPwd((String) settings.get(Config.class.getName() + ".xmattersPwd"));
		
      config.setXmattersExternalServiceUrl((String) settings.get(Config.class.getName() + ".xmattersExternalServiceUrl"));
	  config.setXmattersInternalServiceUrl((String) settings.get(Config.class.getName() + ".xmattersInternalServiceUrl"));
	  
	  config.setXmattersInterval((String) settings.get(Config.class.getName() + ".xmattersInterval"));
	  config.setJiraAdminEmail((String) settings.get(Config.class.getName() + ".jiraAdminEmail"));
     	 
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
      pluginSettings.put(Config.class.getName() + ".xmattersOutUrl", config.getXmattersOutUrl());
	  pluginSettings.put(Config.class.getName() + ".xmattersNonOutUrl", config.getXmattersNonOutUrl());
	  
	  pluginSettings.put(Config.class.getName() + ".xmattersExternalResponse1", config.getXmattersExternalResponse1());
	  pluginSettings.put(Config.class.getName() + ".xmattersExternalResponse2", config.getXmattersExternalResponse2());
	  
	  pluginSettings.put(Config.class.getName() + ".xmattersinternalOutUrl", config.getxmattersinternalOutUrl());
	  pluginSettings.put(Config.class.getName() + ".xmattersinternalNonOutUrl", config.getxmattersinternalNonOutUrl());
	  
	  pluginSettings.put(Config.class.getName() + ".xmattersInternalResponse1", config.getXmattersInternalResponse1());
	  pluginSettings.put(Config.class.getName() + ".xmattersInternalResponse2", config.getXmattersInternalResponse2());
	  
      pluginSettings.put(Config.class.getName() + ".xmattersUid", config.getXmattersUid());
	  pluginSettings.put(Config.class.getName() + ".xmattersPwd", config.getXmattersPwd());
	  
	  pluginSettings.put(Config.class.getName() + ".xmattersInternalServiceUrl", config.getXmattersInternalServiceUrl());
	  pluginSettings.put(Config.class.getName() + ".xmattersExternalServiceUrl", config.getXmattersExternalServiceUrl());
	  
	  pluginSettings.put(Config.class.getName() + ".xmattersInterval", config.getXmattersInterval());	
	  
	  pluginSettings.put(Config.class.getName() + ".jiraAdminEmail", config.getJiraAdminEmail());
	  
      return null;
    }
  });
  return Response.noContent().build();
}
}