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

import com.dt.jira.plugin.innotas.ao.InnotasRelease;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;
import static com.google.common.collect.Lists.newArrayList;

@Path("/innotas-release")
public class InnotasReleaseResource
{
	private InnotasCacheService innotasCacheService;
	private final TransactionTemplate transactionTemplate;
	public InnotasReleaseResource(InnotasCacheService ics, TransactionTemplate tt) {
		this.innotasCacheService = ics;
		this.transactionTemplate = tt;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class Release
	{
		@XmlElement private String releaseId;
		@XmlElement private String releaseName;
		// @XmlElement private String projectId;      
		public String getReleaseId(){
			return releaseId;
		}
		public void setReleaseId(String v){
			this.releaseId = v;
		}
		public String getReleaseName(){
			return releaseName;
		}
		public void setReleaseName(String v){
			this.releaseName = v;
		}
		// public String getProjectId(){
			// return projectId;
		// }
		// public void setProjectId(String v){
			// this.projectId = v;
		// }
	}

@GET
@Produces(MediaType.APPLICATION_JSON)
public Response get(@Context HttpServletRequest request, @QueryParam("innotasProjectId") final String  pId)
{
  return Response.ok(transactionTemplate.execute(new TransactionCallback()
  {
    public Object doInTransaction()
    {
			List<Release> relList = newArrayList();
			Map<String, Object> irMap = innotasCacheService.getInnotasReleases(pId);
			InnotasRelease ir = null;
			for (Object iro : irMap.values()) {
				ir = (InnotasRelease) iro;
				Release rel = new Release();
				//rel.setProjectId(ir.getProjectId());
				rel.setReleaseId(ir.getReleaseId());
				rel.setReleaseName(ir.getReleaseName());
				relList.add(rel);
			}
			return relList;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final Release release, @Context HttpServletRequest request)
{
  return Response.noContent().build();
}
}