package com.dt.jira.plugin.innotas.rest;

import java.util.Collection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.project.version.VersionManager;

/**
 * A resource of message.
 */
@Path("/sync-jirarelease")
public class InnotasJiraReleasesSync {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey,@QueryParam("jiraReleaseId") String jiraReleaseId,@QueryParam("innotasReleaseName") String innotasReleaseName) throws Exception
    {
       String str  = " projectkey: "+projectkey +" jiraReleaseId: "+ jiraReleaseId +" innotasReleaseName "+innotasReleaseName;
       Long verId = Long.valueOf(jiraReleaseId);
       VersionManager versionManager = ComponentAccessor.getVersionManager();     
	  
       Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectkey);
       Collection<Version> vList = versionManager.getVersions(project);
       boolean isVersionExist = false;
       for(Version v: vList){
    	    if(v.getName().equals(innotasReleaseName)){
    	    	isVersionExist = true;
    	    	break;
    	    }
       }
       if(isVersionExist){
    	   return Response.status(500).entity(innotasReleaseName + " name already exist !").build();
       }
		
		Version v = versionManager.getVersion(verId);
		versionManager.editVersionDetails(v, innotasReleaseName, v.getDescription());
        
      
       return Response.ok(new InnotasJiraReleasesSyncModel("Successfuly done "+str)).build();
    }
}