package com.dt.jira.plugin.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.innotas.ao.Innotas;
import com.dt.jira.plugin.innotas.ao.InnotasService;
import static com.google.common.base.Preconditions.*;


/**
 * REST service that provides the statistics of Jira projects mapped to Innotas and % which are not mapped.
 * The output is formatted for a jqplot pie chart 
 */
@Path("/innotasmapping")
public class InnotasMaping   {
	
	private String drilldownTableUrl;
	private String drilldownPieUrl;
	private final InnotasService innotasService;
	
	public InnotasMaping(InnotasService innotasService) {
		this.innotasService = checkNotNull(innotasService);
	}
	
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
/**
 * This method is rest service method  which get the  projects details from innotas service and jira api to find the mapping
 * between jira and innotas.
 * @return Returns the Object which contains InnotasMapingModel
 */
 public Response getMessage() throws Exception{	
	 ProjectManager pMana = ComponentAccessor.getProjectManager();
	 List<Project> projects = pMana.getProjectObjects();

    List<Innotas> listOfIn = innotasService.getMappedProjects();
 
    long totalMapped = 0;
    long totalNotMapped = 0;
    long totJiraProj = 0;
    totalMapped = getMappedInnotasProjects(listOfIn, projects);
    
		 if (projects!=null &&  projects.size()>0)  {
			 totJiraProj = projects.size();			 
	      } 
		 if (listOfIn!=null &&  listOfIn.size()>0)  {
			 totalNotMapped = totJiraProj - totalMapped;	
			 //totalMapped = listOfIn.size();
	      }
		 InnotasMapingModel innotasMapingModel = new InnotasMapingModel(totalMapped,totalNotMapped);
		 return  Response.ok(innotasMapingModel).build();	   
}
/**
 * Get the count of Jira projects mapped to Innotas 
 * @param listOfIn<List<Innotas>> - list of innotas project details 
 * @param projects<List<Project>> - list of jira project details
 * @return
 */
private long getMappedInnotasProjects(List<Innotas> listOfIn,  List<Project> projects){
	long count=0;
	for(Project p: projects){
		for(Innotas in: listOfIn){
			if(p.getKey().equalsIgnoreCase(in.getJiraProjectKey())){
				count++;
				break;
			}
		}
	}
	return count;
}
}