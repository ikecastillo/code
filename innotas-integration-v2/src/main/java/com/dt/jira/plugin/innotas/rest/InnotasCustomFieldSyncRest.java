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
import com.dt.jira.plugin.innotas.util.InnotasCustomFieldSync;
@Path("/custom-field-sync")
public class InnotasCustomFieldSyncRest
{
	private final InnotasCacheService innotasCacheService;
	private final TransactionTemplate transactionTemplate;
	private final UserManager userManager;
	private final InnotasCustomFieldSync ifs;
	public static volatile long flag = 1; /* Written the code to handle multiple users click on the sync innotas data button on the UI*/
	public InnotasCustomFieldSyncRest(InnotasCacheService ics, TransactionTemplate tt, UserManager um, InnotasCustomFieldSync ifs) {
		this.innotasCacheService = ics;
		this.transactionTemplate = tt;
		this.userManager = um;
		this.ifs = ifs;
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
if(flag>1){
	  return Response.status(500).entity("Sync innotas data in progress").build();
  } else {
	  return Response.ok(transactionTemplate.execute(new TransactionCallback()
	  {
		public Object doInTransaction() {				
				flag++;
				innotasCacheService.syncCache();
				ifs.setAllFieldOptions(projectKey);
				System.out.println("flag: "+flag);
				flag = 1;
				return null;
		}		
	  })).build();
  }
}
}