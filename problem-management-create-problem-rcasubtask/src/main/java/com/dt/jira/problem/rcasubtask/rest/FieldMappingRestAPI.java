package com.dt.jira.problem.rcasubtask.rest;

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
import com.dt.jira.problem.rcasubtask.ao.RCASubtaskFieldMap;
import com.dt.jira.problem.rcasubtask.service.FieldMappingService;

/**
 * The REST Service for Add/Delete row for field mapping table  
 *
 * @author Srinadh.Garlapati
 */
@Path("/RCASubtaskFieldMap")
public class FieldMappingRestAPI
{
		private final Logger logger = Logger.getLogger(FieldMappingRestAPI.class);
		private final FieldMappingService fieldmappingservice;
		private final UserManager userManager;
	/**
	 * Constructor
	 * @param portalService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public FieldMappingRestAPI(FieldMappingService fieldmappingservice, UserManager userManager)
		 {
		   this.fieldmappingservice = fieldmappingservice;
		   this.userManager = userManager;
		 }
		
		/**
		 * Add record to the database
		 * 
		 * @param jiraField - from jira field
		 * @param mappingField - to jira field 
		 * @param fromIssueType - from issue type
		 * @param toIssueType - to issue type
		 * @param request - HttpServletRequest
		 * @return
		 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addFieldMappingField")
		public Response put(@QueryParam("jiraField") String jiraField ,@QueryParam("mappingField") String mappingField,@QueryParam("fromIssueType") String fromIssueType ,@QueryParam("toIssueType") String toIssueType, @Context HttpServletRequest request)
		{
			 logger.info("------FieldMappingRestAPI---------"+jiraField+">>>>>>>>>>>>>>>>>>>>"+mappingField);
			/* logger.info("--------IssueResource---project--------------- "+project);  */
			RCASubtaskFieldMap RCASubtaskFieldMap=fieldmappingservice.create(jiraField, mappingField,fromIssueType,toIssueType);
			logger.info("------RCASubtaskFieldMap created---------");
			if(RCASubtaskFieldMap!=null){
				logger.info("------RCASubtaskFieldMap is not null---------");
				List<FieldMappingFields> FieldMappingFieldsList=getAllFieldMappingFields(jiraField,mappingField,fromIssueType,toIssueType);
				logger.info(FieldMappingFieldsList);
				logger.info("------RCASubtaskFieldMap added to response---------");
				return Response.ok(FieldMappingFieldsList).build();
			}
			return Response.noContent().build();
		}

		/**
		 * Delete record to the database
		 * 
		 * @param jiraField - from jira field
		 * @param mappingField - to jira field 
		 * @param fromIssueType - from issue type
		 * @param toIssueType - to issue type
		 * @param request - HttpServletRequest
		 * @return
		 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deleteFieldMappingField")
		public Response delete(@QueryParam("jiraField") String jiraField ,@QueryParam("mappingField") String mappingField ,@QueryParam("fromIssueType") String fromIssueType ,@QueryParam("toIssueType") String toIssueType, @Context HttpServletRequest request)
		{
			logger.info("------FieldMappingRestAPIdelete---------");
			/* logger.info("--------IssueResource---project--------------- "+project); */
			fieldmappingservice.delete(jiraField, mappingField,fromIssueType,toIssueType);
			logger.info("------Mapping Deleted---------");
			List<FieldMappingFields> FieldMappingFieldsList=getAllFieldMappingFields(jiraField,mappingField,fromIssueType,toIssueType);
			logger.info(FieldMappingFieldsList);
				return Response.ok(FieldMappingFieldsList).build();
			
		}
		

		/**
		 * Get all records from the database
		 * 		
		 * @return
		 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getAllFieldMappings")
		public Response getAllFieldMappings() {
			logger.info("------FieldMappingRestAPIFetchingFields---------");
        return Response.ok(getAllFieldMappingsFromDB()).build();
		}
		
		private List<FieldMappingFields> getAllFieldMappingsFromDB() {
			List<FieldMappingFields> FieldMappingFieldsList=new ArrayList<FieldMappingFields>();
			logger.info("------Pre Finding Fields---------");
			List<RCASubtaskFieldMap> xList=fieldmappingservice.findAll();
			logger.info(xList);
			logger.info("------Pre Getting Fields---------");
			for(RCASubtaskFieldMap RCASubtaskFieldMap: xList){
				
				FieldMappingFieldsList.add(new FieldMappingFields(RCASubtaskFieldMap.getJiraField(),RCASubtaskFieldMap.getMappingField(),RCASubtaskFieldMap.getFromIssueType(),RCASubtaskFieldMap.getToIssueType()));
			} 
			logger.info(FieldMappingFieldsList);			
			return FieldMappingFieldsList;
		}
		
        private List<FieldMappingFields> getAllFieldMappingFields(String jiraField,String mappingField,String fromIssueType,String toIssueType){
			
			List<FieldMappingFields> FieldMappingFieldsList=new ArrayList<FieldMappingFields>();
			logger.info("------Finding Fields---------");
			List<RCASubtaskFieldMap> xList=fieldmappingservice.findAll();
			logger.info(xList);
			logger.info("------Getting Fields---------");
			for(RCASubtaskFieldMap RCASubtaskFieldMap: xList){
				
				FieldMappingFieldsList.add(new FieldMappingFields(RCASubtaskFieldMap.getJiraField(),RCASubtaskFieldMap.getMappingField(),RCASubtaskFieldMap.getFromIssueType(),RCASubtaskFieldMap.getToIssueType()));
			}
			logger.info(FieldMappingFieldsList);
			return FieldMappingFieldsList;
			
		}

	


}