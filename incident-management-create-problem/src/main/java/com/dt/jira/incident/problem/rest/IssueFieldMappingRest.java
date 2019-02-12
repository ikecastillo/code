package com.dt.jira.incident.problem.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.incident.problem.ao.ProblemFieldMap;
import com.dt.jira.incident.problem.service.FieldMappingService;

/**
 * The REST Service for add/delete field mapping for problem  
 *
 * @author Srinadh.Garlapati
 */
@Path("/IssueFieldMap")
public class IssueFieldMappingRest
{
		private final Logger logger = Logger.getLogger(IssueFieldMappingRest.class);
		private final FieldMappingService fieldmappingservice;
		private final UserManager userManager;
	/**
	 * Constructor
	 * @param portalService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public IssueFieldMappingRest(FieldMappingService fieldmappingservice, UserManager userManager)
		 {
		   this.fieldmappingservice = fieldmappingservice;
		   this.userManager = userManager;
		 }
		/**
		 * Add the field mapping for incident to incident report sub-task
		 * @param jiraField - jira field name
		 * @param mappingField - jira field name 
		 * @param fromIssueType - Incident issue type 
		 * @param toIssueType  -  Incident Report Sub-task issue type
		 * @param request
		 * @return
		 */

		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addIssueFieldMapping")
		public Response put(@QueryParam("jiraField") String jiraField ,@QueryParam("mappingField") String mappingField,@QueryParam("fromIssueType") String fromIssueType ,@QueryParam("toIssueType") String toIssueType, @Context HttpServletRequest request)
		{
			logger.info("------IssueFieldMappingRest---------addIssueFieldMapping----------------"+jiraField+">>>>>>>>>>>>>>>>>>>>"+mappingField);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			ProblemFieldMap fieldmapping=fieldmappingservice.create(jiraField, mappingField,fromIssueType,toIssueType);		
			if(fieldmapping!=null){				
				List<IssueFieldMapping> FieldMappingFieldsList=getAllFieldMappingFields(jiraField,mappingField,fromIssueType,toIssueType);
				return Response.ok(FieldMappingFieldsList).build();
			}
			return Response.noContent().build();
		}
		/**
		 * Delete the field mapping for incident to incident report sub-task
		 * @param jiraField - jira field name
		 * @param mappingField - jira field name 
		 * @param fromIssueType - Incident issue type 
		 * @param toIssueType  -  Incident Report Sub-task issue type
		 * @param request
		 * @return
		 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deleteIssueFieldMapping")
		public Response delete(@QueryParam("jiraField") String jiraField ,@QueryParam("mappingField") String mappingField ,@QueryParam("fromIssueType") String fromIssueType ,@QueryParam("toIssueType") String toIssueType, @Context HttpServletRequest request)
		{
			fieldmappingservice.delete(jiraField, mappingField,fromIssueType,toIssueType);
			
			List<IssueFieldMapping> FieldMappingFieldsList=getAllFieldMappingFields(jiraField,mappingField,fromIssueType,toIssueType);		
			return Response.ok(FieldMappingFieldsList).build();			
		}
		
		/**
		 * Get all the records from DB
		 * @return Response - json object
		 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getAllIssueFieldMappings")
		public Response getAllFieldMappings() {		
			return Response.ok(getAllFieldMappingsFromDB()).build();
		}
		
		private List<IssueFieldMapping> getAllFieldMappingsFromDB() {
			List<IssueFieldMapping> FieldMappingFieldsList=new ArrayList<IssueFieldMapping>();			
			List<ProblemFieldMap> xList=fieldmappingservice.findAll();
		
			for(ProblemFieldMap fieldmapping: xList){				
				FieldMappingFieldsList.add(new IssueFieldMapping(fieldmapping.getFromIssueType(),fieldmapping.getJiraField(),fieldmapping.getToIssueType(),fieldmapping.getMappingField()));
			} 			
			return FieldMappingFieldsList;
		}
		
        private List<IssueFieldMapping> getAllFieldMappingFields(String jiraField,String mappingField,String fromIssueType,String toIssueType){
			
			List<IssueFieldMapping> FieldMappingFieldsList=new ArrayList<IssueFieldMapping>();			
			List<ProblemFieldMap> xList=fieldmappingservice.findAll();			
			
			for(ProblemFieldMap fieldmapping: xList){				
				FieldMappingFieldsList.add(new IssueFieldMapping(fieldmapping.getFromIssueType(),fieldmapping.getJiraField(),fieldmapping.getToIssueType(),fieldmapping.getMappingField()));
			}
			
			return FieldMappingFieldsList;			
		}
}