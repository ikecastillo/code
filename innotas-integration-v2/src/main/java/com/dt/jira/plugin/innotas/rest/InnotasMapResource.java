package com.dt.jira.plugin.innotas.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import java.util.Map;
import java.util.List;

import com.dt.jira.plugin.innotas.ao.InnotasMap;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;
import static com.google.common.collect.Lists.newArrayList;

@Path("/innotas-mapping")
public class InnotasMapResource
{
	private final InnotasCacheService innotasCacheService;
	private final TransactionTemplate transactionTemplate;
	private final UserManager userManager;
	public InnotasMapResource(InnotasCacheService ics, TransactionTemplate tt, UserManager um) {
		this.innotasCacheService = ics;
		this.transactionTemplate = tt;
		this.userManager = um;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class Mapping
	{
		@XmlElement private String jiraProjectKey;
		@XmlElement private String jiraReleaseId;
		@XmlElement private String innotasProjectId;
		@XmlElement private String innotasReleaseId;
		
		public String getJiraProjectKey(){
			return jiraProjectKey;
		}
		public void setJiraProjectKey(String v){
			this.jiraProjectKey = v;
		}
		public String getJiraReleaseId(){
			return jiraReleaseId;
		}
		public void setJiraReleaseId(String v){
			this.jiraReleaseId = v;
		}
		public String getInnotasProjectId(){
			return innotasProjectId;
		}
		public void setInnotasProjectId(String v){
			this.innotasProjectId = v;
		}
		public String getInnotasReleaseId(){
			return innotasReleaseId;
		}
		public void setInnotasReleaseId(String v){
			this.innotasReleaseId = v;
		}		
	}

@GET
@Produces(MediaType.APPLICATION_JSON)
public Response get(@Context HttpServletRequest request, @QueryParam("projectkey") final String  projectKey)
{
  return Response.ok(transactionTemplate.execute(new TransactionCallback()
  {
    public Object doInTransaction()
    {
			List<Mapping> mapList = newArrayList();
			if (!projectKey.equalsIgnoreCase("")) {
				Map<String, Object> imap = innotasCacheService.getMapping(projectKey);
				InnotasMap im = null;
				for (Object imo : imap.values()) {
					im = (InnotasMap) imo;
					Mapping m = new Mapping();
					m.setJiraProjectKey(im.getJiraProjectKey());
					m.setJiraReleaseId(im.getJiraReleaseId());
					m.setInnotasProjectId(im.getInnotasProjectId());
					m.setInnotasReleaseId(im.getInnotasReleaseId());
					mapList.add(m);
				}
			}
			return mapList;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final Mapping[] m, @Context HttpServletRequest request)
{
  String username = userManager.getRemoteUsername(request);
  if (username == null || !userManager.isSystemAdmin(username)) {
    return Response.status(Status.UNAUTHORIZED).build();
  }

  transactionTemplate.execute(new TransactionCallback()
  {
    public Object doInTransaction() {
			/*PluginSettings pluginSettings = pluginSettingsFactory.createGlobalSettings();
      pluginSettings.put(Config.class.getName() + ".innotasUrl", config.getInnotasUrl());
      pluginSettings.put(Config.class.getName() + ".innotasUid", config.getInnotasUid());
			pluginSettings.put(Config.class.getName() + ".innotasPwd", config.getInnotasPwd()); */
			System.out.println("--------------------- " + m.length);
			for (int i=0;i<m.length;i++) {
				String jpKey = m[i].getJiraProjectKey();
				String jRel = m[i].getJiraReleaseId();
				String ipId = m[i].getInnotasProjectId();
				String ipRel = m[i].getInnotasReleaseId();
				innotasCacheService.saveMapping(jpKey,jRel,ipId,ipRel);
      }
			return null;
    }
  });
  return Response.noContent().build();

}
}