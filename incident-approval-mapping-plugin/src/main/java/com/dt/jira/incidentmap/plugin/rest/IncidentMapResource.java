package com.dt.jira.incidentmap.plugin.rest;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import org.apache.velocity.exception.VelocityException;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.context.JiraContextNode;
import com.atlassian.jira.issue.context.ProjectContext;
import com.atlassian.jira.issue.context.manager.JiraContextTreeManager;
import com.atlassian.jira.issue.customfields.CustomFieldType;
import com.atlassian.jira.issue.customfields.impl.CascadingSelectCFType;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.velocity.VelocityManager;
import com.dt.jira.incidentmap.plugin.ao.IncidentMap;
import com.dt.jira.incidentmap.plugin.ao.IncidentMapService;
import com.atlassian.jira.user.ApplicationUser;
import com.dt.jira.incidentmap.plugin.constants.IRSubtaskAssigneeMapConstants;

/**
 * The Incident Map Rest Service for Incident Report Assignee Map on Incident Management project
 *
 * @author Firoz Khan
 */

@Path("/incident-mapping")
public class IncidentMapResource
{
	private final Logger logger = Logger.getLogger(IncidentMapResource.class);
	private final UserManager userManager;
	private final IncidentMapService incidentMapService;
	public static String CSV_PATH  = "//opt//app//assignee-map";
	
	/**
     * Constructor
     * @param userManager the UserManager to be injected
     * @param incidentMapService the IncidentMapService to be injected
     */
	public IncidentMapResource(UserManager userManager,IncidentMapService incidentMapService)
	 {		
	   this.userManager = userManager;
	   this.incidentMapService= incidentMapService;
	 }


@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectKey") String projectKey,@QueryParam("type") String type, @QueryParam("grpOptionName") String grpOptionName, @QueryParam("cldGrpOptionName") String cldGrpOptionName) {
	
	List<IncidentMapModel> incMapList=new ArrayList<IncidentMapModel>();	
	
	   try {
		
		   		List<IncidentMap> incList=  incidentMapService.getIncidentMappingParentChildDetailsById(projectKey,type,grpOptionName,cldGrpOptionName);
		   		for(IncidentMap incMap: incList){
		   			logger.info("-----------Reading Value From Jira-Db projectKey------------  : "+incMap.getProjectKey());
					logger.info("-----------Reading Value From Jira-Db Type------------------  : "+incMap.getType());
					logger.info("-----------Reading Value From Jira-Db GrpOptionId-----------  : "+incMap.getGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db GrpOptionName---------  : "+incMap.getGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionId--------  : "+incMap.getCldGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionName------  : "+incMap.getCldGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CreatedDate-----------  : "+incMap.getCreatedDate());
					logger.info("-----------Reading Value From Jira-Db ModifiedDate----------  : "+incMap.getModifiedDate());	
					logger.info("-----------Reading Value From Jira-Db ModifiedBy------------  : "+incMap.getModifiedBy());
					IncidentMapModel getIncidentObj = new IncidentMapModel( incMap.getProjectKey(),
							incMap.getType(),
							incMap.getGrpOptionId(),
							incMap.getGrpOptionName(),
							incMap.getCldGrpOptionId(),
							incMap.getCldGrpOptionName(),
							incMap.getRoles(),
							incMap.getUsers(),
							incMap.getCreatedDate(),
							incMap.getModifiedDate(),
							incMap.getModifiedBy());
					incMapList.add(getIncidentObj);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
   return Response.ok(incMapList).build();
}
@GET
@AnonymousAllowed
@Path("/incident-mapping-thirdlevel")
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectKey") String projectKey,@QueryParam("type") String type, @QueryParam("grpOptionName") String grpOptionName, @QueryParam("cldGrpOptionName") String cldGrpOptionName,@QueryParam("fourthLevelOptionName") String fourthLevelOptionName) {
	
	List<IncidentMapModel> incMapList=new ArrayList<IncidentMapModel>();	
	
	   try {
		
		   		List<IncidentMap> incList=  incidentMapService.getIncidentMappingParentChildDetailsById(projectKey,type,grpOptionName,cldGrpOptionName,fourthLevelOptionName);
		   		for(IncidentMap incMap: incList){
					
		   			logger.info("projectKey------------  : "+incMap.getProjectKey() + " Type : "+ incMap.getType() +" GrpOptionId "+ incMap.getGrpOptionId() + " GrpOptionName " + incMap.getGrpOptionName() +" CldGrpOptionId " +incMap.getCldGrpOptionId() +" CldGrpOptionName "+ incMap.getCldGrpOptionName() +" getFourthLevelOptionId " + incMap.getFourthLevelOptionId() + " getFourthLevelOptionName " +  incMap.getFourthLevelOptionName());
					
					
					IncidentMapModel getIncidentObj = new IncidentMapModel( incMap.getProjectKey(),
							incMap.getType(),
							incMap.getGrpOptionId(),
							incMap.getGrpOptionName(),
							incMap.getCldGrpOptionId(),
							incMap.getCldGrpOptionName(),
							incMap.getRoles(),
							incMap.getUsers(),
							incMap.getCreatedDate(),
							incMap.getModifiedDate(),
							incMap.getModifiedBy());
							getIncidentObj.setFourthLevelOptionId(incMap.getFourthLevelOptionId());
							getIncidentObj.setFourthLevelOptionName(incMap.getFourthLevelOptionName());
					incMapList.add(getIncidentObj);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
   return Response.ok(incMapList).build();
}

@GET
@AnonymousAllowed
@Path("/cascade-mapping")
@Produces (MediaType.APPLICATION_JSON)
public Response getCascadeMapping(@Context HttpServletRequest request,@QueryParam ("projectKey") String projectKey,@QueryParam ("type") String type, @QueryParam ("grpOptionName") String grpOptionName)
{
	ProjectManager projectManager=ComponentAccessor.getProjectManager();
	Project project = projectManager.getProjectObjByKey(projectKey);
	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
	OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 
	
	IssueType changeIssueType= null;         
    Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
    for(IssueType issueTypes: issueTypesProj){
           if(!issueTypes.isSubTask()){ // exclude sub-task
                  changeIssueType = issueTypes;
           }
    }	
    Map<String,String> optionGroupMap = new HashMap<String,String>();
    if(type.equalsIgnoreCase("External")){
    	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
		FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
		 
		Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
		List<Option> solutionGroupEnable = new ArrayList<Option>();
		List<Option> productGroupEnable = new ArrayList<Option>();
		
	    for(Option solopt : solutionGroup){ 	
	    	if(!solopt.getDisabled() && solopt.getValue().equalsIgnoreCase(grpOptionName)){
	    		solutionGroupEnable.add(solopt);
	    		List childOpts = solopt.getChildOptions();			    		 
	    		 for (Iterator childIt = childOpts.iterator(); childIt.hasNext();) 
                 { 
                     Option aChildOption = (Option) childIt.next();
					 if(!aChildOption.getDisabled()){
						optionGroupMap.put(String.valueOf(aChildOption.getOptionId()),aChildOption.getValue().toString());
					 }
                 }
	    	}
	    }		    
    }
    if(type.equalsIgnoreCase("Internal")){    	
    	CustomField customFieldImpact = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
    	FieldConfig fieldConfigImpact = customFieldImpact.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
    	Options impactGroup = optionsManager.getOptions(fieldConfigImpact);	
		List<Option> impactGroupEnable = new ArrayList<Option>();
	    for(Option impopt : impactGroup){ 	
	    	if(!impopt.getDisabled()){
	    		impactGroupEnable.add(impopt);
	    		optionGroupMap.put(String.valueOf(impopt.getOptionId()),impopt.getValue().toString());
	    	}
	    }
		
    }
   
	return Response.ok(optionGroupMap).build();
}

@GET
@AnonymousAllowed
@Path("/cascade-mapping-thirdlevel")
@Produces (MediaType.APPLICATION_JSON)
public Response getCascadeMapping(@Context HttpServletRequest request,@QueryParam ("projectKey") String projectKey,@QueryParam ("type") String type, @QueryParam ("grpOptionName") String grpOptionName, @QueryParam ("cldGrpOptionName") String cldGrpOptionName)
{
	ProjectManager projectManager=ComponentAccessor.getProjectManager();
	Project project = projectManager.getProjectObjByKey(projectKey);
	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();
	OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 
	
	IssueType changeIssueType= null;         
    Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
    for(IssueType issueTypes: issueTypesProj){
           if(!issueTypes.isSubTask()){ // exclude sub-task
                  changeIssueType = issueTypes;
           }
    }	
    Map<String,String> optionGroupMap = new HashMap<String,String>();
    if(type.equalsIgnoreCase("External")){
    	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
		FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
		 
		Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
		List<Option> solutionGroupEnable = new ArrayList<Option>();
		List<Option> productGroupEnable = new ArrayList<Option>();
		
	    for(Option solopt : solutionGroup){ 		
	    	if(!solopt.getDisabled() && solopt.getValue().equalsIgnoreCase(grpOptionName)){
	    		solutionGroupEnable.add(solopt);
	    		List childOpts = solopt.getChildOptions();			    		 
	    		 for (Iterator childIt = childOpts.iterator(); childIt.hasNext();) 
                 { 
                     Option aChildOption = (Option) childIt.next();                      
                     if(aChildOption.getValue().equalsIgnoreCase(cldGrpOptionName)){
							List childOptsTrdLevel = aChildOption.getChildOptions();
							 
							 for (Iterator childThirdIt = childOptsTrdLevel.iterator(); childThirdIt.hasNext();) {
								 Option aChildTrdLevelOption = (Option) childThirdIt.next();
								
								  if(aChildTrdLevelOption!=null){
									  optionGroupMap.put(String.valueOf(aChildTrdLevelOption.getOptionId()),aChildTrdLevelOption.getValue().toString());
								  }
							 }
					   }
                 }
	    	}
	    }		    
    }
    if(type.equalsIgnoreCase("Internal")){    	
    	CustomField customFieldImpact = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
    	FieldConfig fieldConfigImpact = customFieldImpact.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
    	Options impactGroup = optionsManager.getOptions(fieldConfigImpact);	
		List<Option> impactGroupEnable = new ArrayList<Option>();
	    for(Option impopt : impactGroup){ 	
	    	if(!impopt.getDisabled()){
	    		impactGroupEnable.add(impopt);
	    		optionGroupMap.put(String.valueOf(impopt.getOptionId()),impopt.getValue().toString());
	    	}
	    }
		
    }
   
	return Response.ok(optionGroupMap).build();
}


@PUT
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response put(final IncidentMapModel[] incidentMapArray, @Context HttpServletRequest request)
{
  String username = userManager.getRemoteUsername(request);
  
  List<IncidentMapModel> incMapList=new ArrayList<IncidentMapModel>();	 
  
  Date today = new Date();
  SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy:HH:mm a");
  String date = DATE_FORMAT.format(today);
  
	for (int i=0;i<incidentMapArray.length;i++) {   
	
		final ApplicationUser selectedUser = ComponentAccessor.getUserManager().getUserByName(incidentMapArray[i].getUsers());  	
		 if (selectedUser == null)
		  {	
			Boolean isMandate=new Boolean(incidentMapArray[i].getMandate());
		    logger.info("-----------incidentMapArray["+i+"].Users UI Users----- "+incidentMapArray[i].getUsers()+" -----------Does Not Exist----- ");
		    if(incidentMapArray[i].getMandate()){
			incMapList.add(incidentMapArray[i]);
		    }
		  }
	}	
	if(incMapList.size()>0){
		return Response.ok(incMapList).build();
	}else{
	
	for (int i=0;i<incidentMapArray.length;i++) {
				
		String pjKey = incidentMapArray[i].getProjectKey();
		String type = incidentMapArray[i].getType();
		String grpOptionId = incidentMapArray[i].getGrpOptionId();
		String grpOptionName = incidentMapArray[i].getGrpOptionName();
		String cldGrpOptionId = incidentMapArray[i].getCldGrpOptionId();
		String cldGrpOptionName = incidentMapArray[i].getCldGrpOptionName();
		String fourthLevelOptionId = incidentMapArray[i].getFourthLevelOptionId();
		String fourthLevelOptionName = incidentMapArray[i].getFourthLevelOptionName();
		
		String roles = incidentMapArray[i].getRoles();
		String users = incidentMapArray[i].getUsers();
		
		String createdDate = date;
		String modifiedDate = date;
		String modifiedBy = username;
		
		incidentMapService.saveIncidentMapping(pjKey,type, grpOptionId,grpOptionName,cldGrpOptionId,cldGrpOptionName,roles,users,createdDate, modifiedDate,modifiedBy,fourthLevelOptionId,fourthLevelOptionName);
		
	}
    	return Response.noContent().build();
	}
	
}

@GET
@Path("/exportincidentassigneecsv")
@Produces("application/vnd.ms-excel")
public Response getincidentmapcsv(@QueryParam("projectKey") String projectKey) {
	logger.info("-----------csvexport Rest Produces projectKey------------- "+projectKey);
	
	ProjectManager projectManager = ComponentAccessor.getProjectManager();
	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager(); 
	OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
	
    Project project1 = projectManager.getProjectObjByKey(projectKey);
	IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project1);
	
	CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_TYPE );   
	FieldConfig fieldConfigType = customFieldType.getRelevantConfig(new IssueContextImpl(project1, issueType));	
	Options types = optionsManager.getOptions(fieldConfigType);	
     		
	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(IRSubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
	FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project1, issueType));
	Options solGrp = optionsManager.getOptions(fieldConfigSolution);
	
	   File CSV_FILE = new File(CSV_PATH,"incidentmapexport.csv");
	   logger.info("csvPath = " + CSV_FILE);
	          
	   try {
            FileWriter fstream = new FileWriter(CSV_FILE, false);
            fstream.append("PROJECT KEY");
            fstream.append(',');
            fstream.append("TYPE");
            fstream.append(',');
            fstream.append("SOLUTION-LOCATION-NAME");
            fstream.append(',');
            fstream.append("ROLES");
            fstream.append(',');
            fstream.append("USERS");
            fstream.append(',');
            fstream.append("CREATED DATE");
            fstream.append(',');
            fstream.append("MODIFIED DATE");
            fstream.append(',');
            fstream.append("MODIFIED BY");
            fstream.append('\n');
	   		List<IncidentMap> incList=  incidentMapService.getIncidentMappingByProjectKey(projectKey);
	   		for(IncidentMap incMap: incList){
	   			logger.info("-----------csvexport Reading Value From Jira-Db projectKey------  : "+incMap.getProjectKey());
	   			logger.info("-----------csvexport Reading Value From Jira-Db Type------------  : "+incMap.getType());
	   			logger.info("-----------csvexport Reading Value From Jira-Db GrpOptionId-----  : "+incMap.getGrpOptionId());
	   			logger.info("-----------csvexport Reading Value From Jira-Db GrpOptionName---  : "+incMap.getGrpOptionName());
	   			logger.info("-----------csvexport Reading Value From Jira-Db Roles-----------  : "+incMap.getRoles());	
	   			logger.info("-----------csvexport Reading Value From Jira-Db User------------  : "+incMap.getUsers());	
	   			fstream.append(incMap.getProjectKey());
	            fstream.append(',');
				
				fstream.append(incMap.getType());
	            fstream.append(',');
	            
	            if(incMap.getType().equalsIgnoreCase("External")){
					try{
						String solGroupsName=incMap.getGrpOptionName();
			            for(Option solGrpOpt : solGrp){ 		            	
		            	 if(!solGrpOpt.getDisabled() && solGrpOpt.getValue().equals(solGroupsName)){
		            		    logger.info("Solution Group Option Id--  : "+solGrpOpt.getValue()+" Solution Group Id--  : "+solGroupsName);
				           		fstream.append(solGrpOpt.getValue());
				           }
			           }
					}catch(NumberFormatException e){
					       logger.info("not a number"); 
					}
		            fstream.append(',');
	            }else{
	            	try{						
			           // long locGroupsId=Long.parseLong(incMap.getGrpOptionId());
			            String locGroupsName=incMap.getGrpOptionName();
			            for(Option typeopt : types){ 			            	
	        	    		List childOpts = typeopt.getChildOptions(); 
	        	    		for (Iterator childIt = childOpts.iterator(); childIt.hasNext();) 
	        	            { 
	        	                    Option aChildOption = (Option) childIt.next();
	        	                    if(!aChildOption.getDisabled() && aChildOption.getValue().equals(locGroupsName)){
	    		            		    logger.info("Location Group Option Id--  : "+aChildOption.getValue()+" Location Group Id--  : "+locGroupsName);
	    				           		fstream.append(aChildOption.getValue());
	    				           }
	        	            } 
			            }
					}catch(NumberFormatException e){
					       logger.info("not a number"); 
					}
		            fstream.append(',');
	            }

				try{
	            int status=Integer.parseInt(incMap.getRoles());
	            switch(status){
	                case  10800  :
	                	fstream.append("Solution Team Incident Manager");
	                    break;
	                case  10801  :
	                	fstream.append("Incident Management Board Review Member");
	                    break;	                            
	                }
	            }catch(NumberFormatException e){
				       logger.info("not a number"); 
				}
	            fstream.append(',');
	            fstream.append(incMap.getUsers());
	            fstream.append(',');
	            fstream.append(incMap.getCreatedDate());
	            fstream.append(',');
	            fstream.append(incMap.getModifiedDate());
	            fstream.append(',');
	            fstream.append(incMap.getModifiedBy());
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