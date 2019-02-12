package com.dt.jira.impm.rolebased.mapping.rest;

import java.util.ArrayList;
import java.util.List;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import org.apache.log4j.Logger;

import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.impm.rolebased.mapping.ao.IMPMRoleBased;
import com.dt.jira.impm.rolebased.mapping.service.IMPMRoleBasedMappingService;

import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
/**
 * This rest service is used for Add/Delete and View Incident and Problem Management roles and group mapping from DB. 
 * @author Firoz.Khan
 */
@Path("/ProjectRoles")
public class IMPMRoleBasedRest
{
		private final Logger logger = Logger.getLogger(IMPMRoleBasedRest.class);
		private final IMPMRoleBasedMappingService roleBasedService;
		private final UserManager userManager;
	/**
	 * Constructor
	 * @param userManager the UserManager to be injected
	 */
		public IMPMRoleBasedRest(IMPMRoleBasedMappingService roleBasedService, UserManager userManager)
		 {
		   this.roleBasedService = roleBasedService;
		   this.userManager = userManager;
		 }
		
		
			
/**
 * This method is rest service method and takes input parameters project key, issueType, solutionGroup and projectRole.
 * It saves the Incident and Problem Management roles and group mapping into DB.

 * @param issueType - <String> issueType
 * @param solutionGroup - <String> - solutionGroup.
 * @param projectRole - <String> - projectRole.
 * @return Returns the Response which roleBasedMapList.
 */
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/addRoleBasedMap")
		public Response put(@QueryParam("projectKey") String projectKey,
				@QueryParam("issueType") String issueType,
				@QueryParam("solutionGroup") String solutionGroup,
				@QueryParam("projectRole") String projectRole, 
				@Context HttpServletRequest request)
		{
				logger.info("------IMPMRoleBasedRest addRoleBasedMap---------"+projectKey+">>>>>>>>>>>>>>>>>>>>"+issueType);
				if(solutionGroup.indexOf("%26")>0){
					solutionGroup = solutionGroup.replaceAll("%26", "&");
				}
				
				if(projectRole.indexOf("%26")>0){
					projectRole = projectRole.replaceAll("%26", "&");
				}
				
				logger.info("------IMPMRoleBasedRest addRoleBasedMap---------solutionGroup : "+solutionGroup);
				
				boolean recordExist = roleBasedService.isProjectRoleExist(projectKey, issueType,solutionGroup,projectRole);
				System.out.println("recordExist"+recordExist);
				
				IMPMRoleBased roleBasedObj=roleBasedService.create(projectKey, issueType,solutionGroup,projectRole);
				logger.info("------IMPMRoleBasedRest created---------");			
				if(roleBasedObj!=null){
					logger.info("------roleBasedObj is not null---------");
					List<IMPMRoleBasedModel> roleBasedMapList=getAllRoleBasedMaps(projectKey,issueType,solutionGroup,projectRole);
					logger.info(roleBasedMapList);
					logger.info("------roleBasedMapList added to response---------");
					return Response.ok(roleBasedMapList).build();
				}
			
			return Response.noContent().build();
		}
	
/**
 * This method is rest service method and takes input parameters project key, issueType, solutionGroup and projectRole.
 * It delete the Incident and Problem Management roles and group mapping from DB.
 * @param issueType - <String> issueType
 * @param solutionGroup - <String> - solutionGroup.
 * @param projectRole - <String> - projectRole.
 * @return Returns the Response which roleBasedMapList.
 */	
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/deleteRoleBasedMap")
		public Response delete(@QueryParam("projectKey") String projectKey,
				@QueryParam("issueType") String issueType,
				@QueryParam("solutionGroup") String solutionGroup,
				@QueryParam("projectRole") String projectRole, 
				@Context HttpServletRequest request)
		{
			
			if(solutionGroup.indexOf("%26")>0){
				solutionGroup = solutionGroup.replaceAll("%26", "&");
			}
			
			if(projectRole.indexOf("%26")>0){
				projectRole = projectRole.replaceAll("%26", "&");
			}
				
			logger.info("------IMPMRoleBasedRest delete---------");
			/* logger.info("--------IssueResource---project--------------- "+project); */
			roleBasedService.delete(projectKey, issueType,solutionGroup,projectRole);
			logger.info("------Role Based Mapping Deleted---------");
			List<IMPMRoleBasedModel> roleBasedMapList=getAllRoleBasedMaps(projectKey,issueType,solutionGroup,projectRole);
			logger.info(roleBasedMapList);
				return Response.ok(roleBasedMapList).build();
			
		}

		/**
		 * Gets the Incident and Problem Management roles and group mapping from DB.
		 * @return Returns the Response which roleBasedMapList.
		 */				
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getAllRoleBasedMap")
		public Response getAllRoleBasedMap() {
			logger.info("------IMPMRoleBasedRest getAllRoleBasedMap---------");
        return Response.ok(getAllRoleBasedMapFromDB()).build();
		}
		
		/**
		 * Gets the Incident and Problem Management roles and group mapping from DB.
		 *
		 * @return Returns the List<IMPMRoleBasedModel>.
		 */		
		 
		private List<IMPMRoleBasedModel> getAllRoleBasedMapFromDB() {
			List<IMPMRoleBasedModel> roleBasedMapList=new ArrayList<IMPMRoleBasedModel>();
			logger.info("------Pre Finding Role Based Mapping---------");
			List<IMPMRoleBased> roleBasedList=roleBasedService.findAll();
			logger.info(roleBasedList);
			logger.info("------Pre Getting Role Based Mapping---------");
			for(IMPMRoleBased roleBasedMap: roleBasedList){
				
				roleBasedMapList.add(new IMPMRoleBasedModel(roleBasedMap.getProjectKey(),roleBasedMap.getIssueType(),roleBasedMap.getSolutionGroup(),roleBasedMap.getProjectRole()));
			} 
			logger.info(roleBasedMapList);			
			return roleBasedMapList;
		}
		
		/**
		 * This method takes input parameters project key, issueType, solutionGroup and projectRole.
		 * It gets the Incident and Problem Management roles and group mapping from DB.
		 *
		 * @return Returns the List<IMPMRoleBasedModel>.
		 */		
		 
        private List<IMPMRoleBasedModel> getAllRoleBasedMaps(String projectKey,String issuType,String solutionGroup,String projectRole){
			
			List<IMPMRoleBasedModel> roleBasedMapList=new ArrayList<IMPMRoleBasedModel>();
			logger.info("------Finding Role Based Mapping---------");
			List<IMPMRoleBased> roleBasedList=roleBasedService.findAll();
			logger.info(roleBasedList);
			logger.info("------Getting Role Based Mapping---------");
			for(IMPMRoleBased roleBasedMap: roleBasedList){
				
				roleBasedMapList.add(new IMPMRoleBasedModel(roleBasedMap.getProjectKey(),roleBasedMap.getIssueType(),roleBasedMap.getSolutionGroup(),roleBasedMap.getProjectRole()));
			}
			logger.info(roleBasedMapList);
			return roleBasedMapList;
			
		}
		
		@GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getIssueTypes")
        public Response getIssueTypes(@QueryParam("projectKey") String projectKey) throws Exception{	
		
        	ArrayList<IMPMFieldModel> listOfModels= new ArrayList<IMPMFieldModel>();
        	try{
        		Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
        		Collection<IssueType> issueTypesCollection = project.getIssueTypes();
        		
        		for(IssueType issueType:  issueTypesCollection){
        			listOfModels.add(new IMPMFieldModel(issueType.getName(),issueType.getName()));
        		}
        		
        	}catch(Exception e){
        			e.printStackTrace();
        	}
          		return Response.ok(listOfModels).build();		
        }
				
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getAllSolGrpMap")
		public Response getAllSolGrpMap(@QueryParam("projectKey") String projectKey,@QueryParam("issueType") String issueType) {
		List<IMPMFieldModel> solutionGroup = new ArrayList<IMPMFieldModel>();
    	try{
    		CustomFieldManager cfManager =ComponentAccessor.getCustomFieldManager();
    		 IssueType issueTypeA= null;			 
    		 if(projectKey.equalsIgnoreCase("CHG") && issueType.equalsIgnoreCase("Change")){
    			 issueTypeA = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey));
    		 }else{    			 
	    		 Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey));
	              for(IssueType issueTypeB: issueTypesProj){
	                     if(issueTypeB.getName().equalsIgnoreCase(issueType)){
	                    	 issueTypeA = issueTypeB;
	                     }
	              }
    		 }
    		CustomField customField = cfManager.getCustomFieldObjectByName("Solution Groups - Products");
    		FieldConfig fieldConfigSolution = customField.getRelevantConfig(new IssueContextImpl(ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey), issueTypeA));
    		OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
    		List<Option> solOpts = optionsManager.getOptions(fieldConfigSolution);	
    		if(solOpts!=null){
    			for(Option solopt: solOpts){
    				if(!solopt.getDisabled()){
						solutionGroup.add(new IMPMFieldModel(solopt.getValue(),solopt.getValue().toString()));
    				}
    			}
    		}	
    	}catch(Exception e){
    			e.printStackTrace();
    	}
		   return Response.ok(solutionGroup).build();
		}
		
		
}
	