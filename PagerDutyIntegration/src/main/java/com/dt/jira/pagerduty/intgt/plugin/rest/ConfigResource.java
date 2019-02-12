package com.dt.jira.pagerduty.intgt.plugin.rest;

import com.atlassian.gzipfilter.org.tuckey.web.filters.urlrewrite.Conf;
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

@Path("/pdconfig")
public class ConfigResource
{
  private final UserManager userManager;
  private final PluginSettingsFactory pluginSettingsFactory;
  private final TransactionTemplate transactionTemplate;

  public ConfigResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, 
                  TransactionTemplate transactionTemplate) {
    this.userManager = userManager;
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.transactionTemplate = transactionTemplate;	
  }

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public static final class Config
{
  @XmlElement private String pduid;
  @XmlElement private String pdpwd;
  @XmlElement private String pdinterval;
  @XmlElement private String pdjiraemailid;
  @XmlElement private String pdhipchattoken;
  @XmlElement private String pdrestapibaseurl;
  @XmlElement private String pddturl;
  @XmlElement private String pdserviceslimit;
  @XmlElement private String pdapitoken;


  public String getPdinterval() {
      return pdinterval;
  }

  public void setPdinterval(String pdinterval) {
      this.pdinterval = pdinterval;
  }

  public String getPduid(){
    return pduid;
  }

  public void setPduid(String v){
    this.pduid = v;
  }

  public String getPdpwd(){
    return pdpwd;
  }

  public void setPdpwd(String v){
    this.pdpwd = v;
  }

  public String getPdjiraemailid() {
		return pdjiraemailid;
	}

  public void setPdjiraemailid(String pdjiraemailid) {
		this.pdjiraemailid = pdjiraemailid;
	}

  public String getPdhipchattoken() {
    return pdhipchattoken;
  }

  public void setPdhipchattoken(String pdhipchattoken) {
    this.pdhipchattoken = pdhipchattoken;
  }

  public String getPdrestapibaseurl() {
        return pdrestapibaseurl;
    }

  public void setPdrestapibaseurl(String pdrestapibaseurl) {
        this.pdrestapibaseurl = pdrestapibaseurl;
    }

  public String getPddturl() {
        return pddturl;
    }

  public void setPddturl(String pddturl) {
        this.pddturl = pddturl;
    }

  public String getPdserviceslimit() {
        return pdserviceslimit;
    }

  public void setPdserviceslimit(String pdserviceslimit) {
        this.pdserviceslimit = pdserviceslimit;
    }

  public String getPdapitoken() {
    return pdapitoken;
  }

   public void setPdapitoken(String pdapitoken) {
    this.pdapitoken = pdapitoken;
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

	  config.setPduid((String) settings.get(Config.class.getName() + ".pduid"));
      config.setPdpwd((String) settings.get(Config.class.getName() + ".pdpwd"));
	  config.setPdinterval((String) settings.get(Config.class.getName() + ".pdinterval"));
	  config.setPdjiraemailid((String) settings.get(Config.class.getName() + ".pdjiraemailid"));
      config.setPdhipchattoken((String) settings.get(Config.class.getName() + ".pdhipchattoken"));
      config.setPdrestapibaseurl((String) settings.get(Config.class.getName()+ ".pdrestapibaseurl"));
      config.setPddturl((String) settings.get(Config.class.getName() + ".pddturl"));
      config.setPdserviceslimit((String) settings.get(Config.class.getName() + ".pdserviceslimit"));
      config.setPdapitoken((String) settings.get(Config.class.getName() + ".pdapitoken"));

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
	  
      pluginSettings.put(Config.class.getName() + ".pduid", config.getPduid());
	  pluginSettings.put(Config.class.getName() + ".pdpwd", config.getPdpwd());
	  pluginSettings.put(Config.class.getName() + ".pdinterval", config.getPdinterval());
	  pluginSettings.put(Config.class.getName() + ".pdjiraemailid", config.getPdjiraemailid());
      pluginSettings.put(Config.class.getName() + ".pdhipchattoken", config.getPdhipchattoken());
      pluginSettings.put(Config.class.getName() + ".pdrestapibaseurl",config.getPdrestapibaseurl());
      pluginSettings.put(Config.class.getName() + ".pddturl", config.getPddturl());
      pluginSettings.put(Config.class.getName() + ".pdserviceslimit", config.getPdserviceslimit());
      pluginSettings.put(Config.class.getName() + ".pdapitoken", config.getPdapitoken());
	  
      return null;
    }
  });
  return Response.noContent().build();
}
}