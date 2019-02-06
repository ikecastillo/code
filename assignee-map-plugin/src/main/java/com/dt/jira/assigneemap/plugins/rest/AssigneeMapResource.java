package com.dt.jira.assigneemap.plugins.rest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMap;
import com.dt.jira.assigneemap.plugins.ao.AssigneeMapService;
import com.atlassian.jira.user.ApplicationUser;

/**
 * The REST Service for Assignee Map within Change Management Project
 *
 * @author Firoz.Khan
 */
@Path("/assignee-mapping")
public class AssigneeMapResource
{
		private final Logger logger = Logger.getLogger(AssigneeMapResource.class);
		private final AssigneeMapService assigneeMapService;
		private final UserManager userManager;
		public static String CSV_PATH  = "//opt//app//assignee-map";
	/**
	 * Constructor
	 * @param assigneeMapService the AssigneeMapService to be injected
	 * @param userManager the UserManager to be injected
	 */
		public AssigneeMapResource(AssigneeMapService assigneeMapService, UserManager userManager)
		 {
		   this.assigneeMapService = assigneeMapService;
		   this.userManager = userManager;
		 }


	 /**
	 * Consume/Save the assignee mapping details into JIRA DB based on the mapping detail used by the user from GUI
	 * Produce/Validate the assignee mapping details if already exist or not from JIRA DB based on the mapping detail used by the user from GUI
	 * @param assigneeArray the String array of Assignee[]
	 * @param request the HttpServletRequest
	 * @return the Response (Failure or Success) in GUI
	 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(final Assignee[] assigneeArray, @Context HttpServletRequest request)
		{
		  String username = userManager.getRemoteUsername(request);
		  List<Assignee> assigneeList=new ArrayList<Assignee>();
			for (int i=0;i<assigneeArray.length;i++) {				
				final ApplicationUser selectedUser = ComponentAccessor.getUserManager().getUserByName(assigneeArray[i].getUsers());  	
				 if (selectedUser == null)
				  {	
					System.out.println("-----------assigneeArray["+i+"].Users UI Users----- "+assigneeArray[i].getUsers()+" -----------Does Not Exist----- ");
					assigneeList.add(assigneeArray[i]);	
				  }
			}	
			if(assigneeList.size()>0){
				return Response.ok(assigneeList).build();
			}else{
			
			for (int i=0;i<assigneeArray.length;i++) {
						
				String pjKey = assigneeArray[i].getProjectKey();
				String solution = assigneeArray[i].getSolutionGroup();
				String impact = assigneeArray[i].getImpact();
				String status = assigneeArray[i].getStatus();
				String users = assigneeArray[i].getUsers();
				
				logger.info("-----------SAVE Value Into Jira-Db pjKey--------------- "+pjKey);
				logger.info("-----------SAVE Value Into Jira-Db solution------------ "+solution);
				logger.info("-----------SAVE Value Into Jira-Db impact-------------- "+impact);
				logger.info("-----------SAVE Value Into Jira-Db status-------------- "+status);
				logger.info("-----------SAVE Value Into Jira-Db users--------------- "+users);
				
				assigneeMapService.saveMapping(pjKey,solution,impact,status,users);
			}
				return Response.noContent().build();
			}
			
		}

	/**
	 * Consume/Save the assignee mapping details into JIRA DB based on the mapping detail used by the user from GUI
	 * Produce/Validate the assignee mapping details if already exist or not from JIRA DB based on the mapping detail used by the user from GUI
	 * @param assigneeArray the String array of Assignee[]
	 * @param request the HttpServletRequest
	 * @return the Response (Failure or Success) in GUI
	 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getMessage(@QueryParam("projectKey") String projectKey,@QueryParam("solutionGroup") String solutionGroup,@QueryParam("impact") String impact) {
			logger.info("-----------Rest Produces projectKey------------- "+projectKey);
			logger.info("-----------Rest Produces solutionGroup---------- "+solutionGroup);
			logger.info("-----------Rest Produces impact----------------- "+impact);
			List<Assignee> assigneeList=new ArrayList<Assignee>();		   
			   try {
						List<AssigneeMap> assigneList=  assigneeMapService.getAssigneeMappingDetails(projectKey,solutionGroup,impact);
						for(AssigneeMap assignee: assigneList){
							logger.info("-----------Reading Value From Jira-Db projectKey------  : "+assignee.getProjectKey());
							logger.info("-----------Reading Value From Jira-Db Solution Group--  : "+assignee.getSolutionGroup());
							logger.info("-----------Reading Value From Jira-Db Impact----------  : "+assignee.getImpact());
							logger.info("-----------Reading Value From Jira-Db Status----------  : "+assignee.getStatus());	
							logger.info("-----------Reading Value From Jira-Db User------------  : "+assignee.getUsers());	
							Assignee getAssigne = new Assignee( assignee.getProjectKey(),
									   assignee.getSolutionGroup(),
									   assignee.getImpact(),
									   assignee.getStatus(),
									   assignee.getUsers());
							assigneeList.add(getAssigne);
					} 

				} catch (Exception e) {
					e.printStackTrace();
				}
		   return Response.ok(assigneeList).build();
		}

	/**
	 * Produce/exporting the assignee mapping details from JIRA DB as an Excel/CSV Report
	 * @param projectKey the String 
	 * @return the Response
	 */
		@GET
		@Path("/exportassigneecsv")
		@Produces("application/vnd.ms-excel")
		public Response getassigneemapcsv(@QueryParam("projectKey") String projectKey) {
			logger.info("-----------csvexport Rest Produces projectKey------------- "+projectKey);
			
			CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();             
			Project project1 = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
			IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project1);
			//reverted back the context related change to previous state
			/*IssueType changeIssueType= null;         
            Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project1);
            for(IssueType issueT: issueTypesProj){
				 if(!issueT.isSubTask()){ // exclude sub-task
						changeIssueType = issueT;
				 }
            }*/
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			
			CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName("Solution Group - Product" );  
			//reverted back the context related change to previous state			
			//FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project1, changeIssueType));	
			FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project1, issueType));	
			Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
			
			CustomField customFieldImpcated = customFieldManager.getCustomFieldObjectByName("Impacted - Function" );	        
			//reverted back the context related change to previous state
			//FieldConfig fieldConfigImpacted = customFieldImpcated.getRelevantConfig(new IssueContextImpl(project1, changeIssueType));
			FieldConfig fieldConfigImpacted = customFieldImpcated.getRelevantConfig(new IssueContextImpl(project1, issueType));
			Options impacted = optionsManager.getOptions(fieldConfigImpacted);
			   File CSV_FILE = new File(CSV_PATH,"asigneemapexport.csv");
			   logger.info("csvPath = " + CSV_FILE);
					  
			   try {
					FileWriter fstream = new FileWriter(CSV_FILE, false);
					fstream.append("PROJECT KEY");
					fstream.append(',');
					fstream.append("SOLUTION GROUP");
					fstream.append(',');
					fstream.append("IMPACTED GROUP");
					fstream.append(',');
					fstream.append("STATUS");
					fstream.append(',');
					fstream.append("USERS");
					fstream.append('\n');
					List<AssigneeMap> assigneList=  assigneeMapService.getAssigneeMappingByProjectKey(projectKey);
					for(AssigneeMap assignee: assigneList){
						logger.info("-----------csvexport Reading Value From Jira-Db projectKey------  : "+assignee.getProjectKey());
						
						fstream.append(assignee.getProjectKey());
						fstream.append(',');
						
						try{
						logger.info("-----------csvexport Reading Value From Jira-Db Solution Group--  : "+assignee.getSolutionGroup());
						long solutionGroupId=Long.parseLong(assignee.getSolutionGroup());
						for(Option solopt : solutionGroup){ 			   			
							 if(solopt.getOptionId().equals(solutionGroupId)){
								logger.info("Solution Group Option Id--  : "+solopt.getOptionId()+" Solution Group Id--  : "+solutionGroupId+" Solution Group Value--  : "+solopt.getValue());
								fstream.append(solopt.getValue());
							  }
							  
						  }
						}catch(NumberFormatException e){
							   System.out.println("not a number"); 
						}
						fstream.append(',');
						try{
							logger.info("-----------csvexport Reading Value From Jira-Db Impact----------  : "+assignee.getImpact());
							long imactedGroupsId=Long.parseLong(assignee.getImpact());
							for(Option impactopt : impacted){ 		            	
							 if(impactopt.getOptionId().equals(imactedGroupsId)){
									logger.info("Impacted Group Option Id--  : "+impactopt.getOptionId()+" Impacted Function Id--  : "+imactedGroupsId+" Impacted Function Value--  : "+impactopt.getValue());
									fstream.append(impactopt.getValue());
							  }
						   }
						}catch(NumberFormatException e){
							   System.out.println("not a number"); 
						}
						fstream.append(',');
						try{
						logger.info("-----------csvexport Reading Value From Jira-Db Status----------  : "+assignee.getStatus());
						int status=Integer.parseInt(assignee.getStatus());
						switch(status){
						    
							case  10800  :
								fstream.append("PENDING APPROVAL - SME");
								break;
							case  10801  :
								fstream.append("PENDING APPROVAL - SECURITY");
								break;
							case  10802  :
								fstream.append("PENDING APPROVAL - CAB");
								break;
							case  10799  :
								fstream.append("PENDING APPROVAL ENT-CAB");
								break;
							case  10803  :
								fstream.append("SCHEDULED");
								break;
							case  10804  :
								fstream.append("PENDING APPROVAL - ECAB1");
								break;
							case  10805  :
								fstream.append("PENDING APPROVAL - ECAB2");
								break;              
							}
						}catch(NumberFormatException e){
							   System.out.println("not a number"); 
						}
						fstream.append(',');
						logger.info("-----------csvexport Reading Value From Jira-Db User------------  : "+assignee.getUsers());
						fstream.append(assignee.getUsers());
						fstream.append('\n');
						
				   } 
				   BufferedWriter out = new BufferedWriter(fstream);
				   out.close();          
				   logger.info("*** Writting this information to file: " + CSV_FILE);
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
			ResponseBuilder response = Response.ok((Object) CSV_FILE);
			response.header("Content-Disposition","attachment; filename=\""+CSV_FILE.getName()+"\"");
			return response.build();

		}


}