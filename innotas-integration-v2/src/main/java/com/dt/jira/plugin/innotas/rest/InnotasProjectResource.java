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

import com.dt.jira.plugin.innotas.ao.InnotasCache;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;
import static com.google.common.collect.Lists.newArrayList;

@Path("/innotas-project")
public class InnotasProjectResource
{
	private InnotasCacheService innotasCacheService;
	private final TransactionTemplate transactionTemplate;
	public InnotasProjectResource(InnotasCacheService ics, TransactionTemplate tt) {
		this.innotasCacheService = ics;
		this.transactionTemplate = tt;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class Project
	{
		@XmlElement private String id;
		@XmlElement private String name;
		@XmlElement private String sbuId;
		@XmlElement private String sbuName;
		@XmlElement private String program;
		@XmlElement private String agileProject;
		
		public String getId(){
			return id;
		}
		public void setId(String v){
			this.id = v;
		}
		public String getName(){
			return name;
		}
		public void setName(String v){
			this.name = v;
		}
		public String getSbuName(){
			return sbuName;
		}
		public void setSbuName(String v){
			this.sbuName = v;
		}		
		public String getProgram(){
			return program;
		}
		public void setProgram(String v){
			this.program = v;
		}	
		public String getAgileProject(){
			return agileProject;
		}
		public void setAgileProject(String v){
			this.agileProject = v;
		}		
	}

@GET
@Produces(MediaType.APPLICATION_JSON)
public Response get(@Context HttpServletRequest request, @QueryParam("buid") final String  buId)
{
  return Response.ok(transactionTemplate.execute(new TransactionCallback()
  {
    public Object doInTransaction()
    {
			String bu = null;
			if (!buId.equalsIgnoreCase("")) bu = buId;
			List<Project> prjList = newArrayList();
			Map<String, Object> ipMap = innotasCacheService.getInnotasProjects(bu);
			InnotasCache ic = null;
			for (Object ipo : ipMap.values()) {
				ic = (InnotasCache) ipo;
				Project prj = new Project();
				prj.setId(ic.getProjectId());
				prj.setName(ic.getProjectName());
				prj.setSbuName(ic.getSbuName());
				prj.setProgram(ic.getProgramName());
				prj.setAgileProject(ic.getAgileProject());
				prjList.add(prj);
			}
			return prjList;
    }
  })).build();
}
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final Project project, @Context HttpServletRequest request)
{
  return Response.noContent().build();
}
}