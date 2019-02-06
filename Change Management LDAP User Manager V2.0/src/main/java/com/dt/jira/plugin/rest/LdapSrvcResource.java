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

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import com.atlassian.sal.api.user.UserManager;

@Path("/ldapsrvc")
public class LdapSrvcResource
{
  private final UserManager userManager;
  private final PluginSettingsFactory pluginSettingsFactory;
  private final TransactionTemplate transactionTemplate;

  public LdapSrvcResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory, TransactionTemplate transactionTemplate)
  {
    this.userManager = userManager;
    this.pluginSettingsFactory = pluginSettingsFactory;
    this.transactionTemplate = transactionTemplate;
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
      LdapSrvc ldapSrvc = new LdapSrvc();
      ldapSrvc.setLdapInitCtx((String) settings.get(LdapSrvc.class.getName() + ".ldapInitCtx"));
      ldapSrvc.setLdapSrvrName((String) settings.get(LdapSrvc.class.getName() + ".ldapSrvrName"));
      ldapSrvc.setLdapBaseDn((String) settings.get(LdapSrvc.class.getName() + ".ldapBaseDn"));
      ldapSrvc.setLdapUid((String) settings.get(LdapSrvc.class.getName() + ".ldapUid"));
      ldapSrvc.setLdapPwd((String) settings.get(LdapSrvc.class.getName() + ".ldapPwd"));
			System.out.println("************* " + ldapSrvc.getLdapUid());
      return ldapSrvc;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final LdapSrvc ldapSrvc, @Context HttpServletRequest request)
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
      /*System.out.println(LdapSrvc.class.getName() + ".ldapInitCtx"+ ldapSrvc.getLdapInitCtx());
      System.out.println(LdapSrvc.class.getName() + ".ldapSrvrName"+ ldapSrvc.getLdapSrvrName());
      System.out.println(LdapSrvc.class.getName() + ".ldapBaseDn"+ ldapSrvc.getLdapBaseDn());
      System.out.println(LdapSrvc.class.getName() + ".ldapUid"+ ldapSrvc.getLdapUid());
      System.out.println(LdapSrvc.class.getName() + ".ldapPwd"+ ldapSrvc.getLdapPwd());*/
      
      pluginSettings.put(LdapSrvc.class.getName() + ".ldapInitCtx", ldapSrvc.getLdapInitCtx());
      pluginSettings.put(LdapSrvc.class.getName() + ".ldapSrvrName", ldapSrvc.getLdapSrvrName());
      pluginSettings.put(LdapSrvc.class.getName() + ".ldapBaseDn", ldapSrvc.getLdapBaseDn());
      pluginSettings.put(LdapSrvc.class.getName() + ".ldapUid", ldapSrvc.getLdapUid());
	  pluginSettings.put(LdapSrvc.class.getName() + ".ldapPwd", ldapSrvc.getLdapPwd());
      return null;
    }
  });
  return Response.noContent().build();
}
}