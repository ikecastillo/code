package com.dt.jira.plugin.rest;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.innotas.ao.Innotas;
import com.dt.jira.plugin.innotas.ao.InnotasBU;
import com.dt.jira.plugin.innotas.ao.InnotasProject;
import com.dt.jira.plugin.innotas.ao.InnotasService;
import static com.google.common.base.Preconditions.*;


/**
 * A resource of message.
 */
@Path("/innotasmappingtable")
public class InnotasMapingTable {
	private String drilldownTableUrl;
	private String drilldownPieUrl;
	private final InnotasService innotasService;
	
	public InnotasMapingTable(InnotasService innotasService) {
		this.innotasService = checkNotNull(innotasService);
	}
	
@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
 public Response getMessage() throws JiraException{
	
	    
	ArrayList<InnotasMapingTableModel> listOfModels= new ArrayList<InnotasMapingTableModel>();
    ProjectManager pMana = ComponentAccessor.getProjectManager();
    List<Project> projects = pMana.getProjectObjects();
  
    List<Innotas> listOfIn = innotasService.getMappedProjects();
   
    
	Map<String, String> bumap = innotasService.getInnotasBUs();
	Map<String, Object> map = null;
	
	map = innotasService.getInnotasProjects();
	
	

		 if (projects!=null && projects.size()>0)
	      {
			 for(Project p: projects){
				 String projectKey = p.getKey();
				 String projectName = p.getName();
				 String jirProjectKey = "";
				 String inntasProjectName =  "";
				 String innotasProjectId = "";
				 String businessUnit = "";
				 InnotasProject innotasProject=null;
				 InnotasMapingTableModel innotasMapingTableModel=null;
				 boolean flag = false;
				  for(Innotas tas: listOfIn){
					  if(projectKey.equalsIgnoreCase(tas.getJiraProjectKey())){
						  jirProjectKey = tas.getJiraProjectKey();
						  //Get innotas project from innotas id
						  innotasProjectId = tas.getInnotasProjectId(); 
						  innotasProject = getInnotasProject(map, innotasProjectId); 
						  //Get Bu from buid 						  
						  if(innotasProject!=null){				  
								businessUnit = getBuName(innotasProject.getBuId());						  
								inntasProjectName = innotasProject.getInnotasId() +"-"+ innotasProject.getInnotasName();
						  }
						  //System.out.println(" buisenessUnit Mapp : "+ businessUnit + " Name: "+ inntasProjectName + " Jira project Name: "+ p.getName()+"Key: "+projectKey );
						  innotasMapingTableModel = new InnotasMapingTableModel(jirProjectKey, projectName, businessUnit, inntasProjectName,"Mapped");
						  listOfModels.add(innotasMapingTableModel);
						  flag = true;
						  break;
					  } 
				  }//end for
				  if(!flag){
					  //System.out.println(" buisenessUnit non mapp: "+ businessUnit + " Name: "+ inntasProjectName + " Jira project Name: "+ p.getName()+"Key: "+projectKey );
					  innotasMapingTableModel = new InnotasMapingTableModel(projectKey, projectName, businessUnit, "Not Mapped","NotMapped");
					  listOfModels.add(innotasMapingTableModel);
				  }//if
			 }// end for
			Collections.sort(listOfModels,new BusinessUnitSorter());
	      }// end if 
		 return  Response.ok(listOfModels).build();	   
	}
/**
 * Get the business unit name by passing business unit id
 * @param buid - business unit id
 * @return <String> - business unit name
 */
private String getBuName(String buid){
	Map<String, String> map = innotasService.getInnotasBUs();
	String buName =  map.get(buid);
	return buName;
}
/**
 * Get the InnotasProject from map
 * 
 * @param map Map<String,Object> - Map<innotasId, InnotasProject> 
 * @param innotasId - <String> - Innotas Id
 * @return Returns <InnotasProject> object
 */
private InnotasProject getInnotasProject(Map<String, Object> map,String innotasId){
	InnotasProject inProject = null;
	inProject =(InnotasProject) map.get(innotasId);
	
	return inProject;
}

}
