package com.dt.jira.plugin.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.atlassian.jira.bc.project.component.ProjectComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource is used to import the components data  to the specific project.
 * location of the file to import data is //opt//app//components//Components.csv
 * and projectkey = RHMQ
 */
@Path("/components")
public class ComponentsRestResource {
	
	public static String CSV_PATH  = "//opt//app//components//Components.csv";

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage( @QueryParam("projectkey") final String  projectKey)
    {
    	ProjectComponentManager componentManager  = ComponentAccessor.getProjectComponentManager();
    	String name = "";
    	String desc = "";
    	String lead = "Kiran.muthoju@dealertrack.com";
    	
    	ProjectManager projectManager = ComponentAccessor.getProjectManager();
    	
    	Project project = projectManager.getProjectByCurrentKeyIgnoreCase(projectKey);
    	Long projectid = project.getId();
    	long assingeetype = project.getAssigneeType();
    	
    	String csvFile = CSV_PATH ;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 		br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {	 
				// use comma as separator
				String[] component = line.split(cvsSplitBy);
				name = component[0];
				desc = component[1];
				lead = component[2];
				System.out.println("0="+ name + " 1= "+ desc + " 2= "+ lead);
				//componentManager.create(name, desc,  lead, assingeetype,projectid);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
    	
       return Response.ok(new ComponentsRestResourceModel("Hello World")).build();
    }
    
    
}