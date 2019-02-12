package com.dt.jira.rcamap.plugin.rest;

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
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.dt.jira.rcamap.plugin.ao.RCASubtaskMap;
import com.dt.jira.rcamap.plugin.ao.RCASubtaskMapService;
import com.dt.jira.rcamap.plugin.constants.RCASubtaskAssigneeMapConstants;

/**
 * The RCA Report Subtask Map Rest Service for RCA Report Subtask Assignee Map on Problem Management project
 *
 * @author Firoz Khan
 */

@Path("/rcasubtask-mapping")
public class RCASubtaskMapResource
{
	private final Logger logger = Logger.getLogger(RCASubtaskMapResource.class);
	private final UserManager userManager;
	private final RCASubtaskMapService rcaSubtaskMapService;
	public static String CSV_PATH  = "//opt//app//assignee-map";
	
	/**
     * Constructor
     * @param userManager the UserManager to be injected
     * @param rcaSubtaskMapService the IncidentMapService to be injected
     */
	public RCASubtaskMapResource(UserManager userManager,RCASubtaskMapService rcaSubtaskMapService)
	 {		
	   this.userManager = userManager;
	   this.rcaSubtaskMapService= rcaSubtaskMapService;
	 }


@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectKey") String projectKey,@QueryParam("type") String type, @QueryParam("grpOptionName") String grpOptionName, @QueryParam("cldGrpOptionName") String cldGrpOptionName) {
	
	List<RCASubtaskMapModel> rcaMapList=new ArrayList<RCASubtaskMapModel>();
	   try {
	   
		   		List<RCASubtaskMap> rcaList=  rcaSubtaskMapService.getRcaReportSubtaskMappingParentChildDetailsById(projectKey,type,grpOptionName,cldGrpOptionName);
		   		for(RCASubtaskMap rcaMap: rcaList){
		   			logger.info("-----------Reading Value From Jira-Db projectKey------------  : "+rcaMap.getProjectKey());
					logger.info("-----------Reading Value From Jira-Db Type------------------  : "+rcaMap.getType());
					logger.info("-----------Reading Value From Jira-Db GrpOptionId-----------  : "+rcaMap.getGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db GrpOptionName---------  : "+rcaMap.getGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionId--------  : "+rcaMap.getCldGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionName------  : "+rcaMap.getCldGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CreatedDate-----------  : "+rcaMap.getCreatedDate());
					logger.info("-----------Reading Value From Jira-Db ModifiedDate----------  : "+rcaMap.getModifiedDate());	
					logger.info("-----------Reading Value From Jira-Db ModifiedBy------------  : "+rcaMap.getModifiedBy());
					RCASubtaskMapModel getRcaReportObj = new RCASubtaskMapModel( rcaMap.getProjectKey(),
							rcaMap.getType(),
							rcaMap.getGrpOptionId(),
							rcaMap.getGrpOptionName(),
							rcaMap.getCldGrpOptionId(),
							rcaMap.getCldGrpOptionName(),
							rcaMap.getRoles(),
							rcaMap.getUsers(),
							rcaMap.getCreatedDate(),
							rcaMap.getModifiedDate(),
							rcaMap.getModifiedBy());
					rcaMapList.add(getRcaReportObj);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
   return Response.ok(rcaMapList).build();
}

@GET
@AnonymousAllowed
@Path("/rcasubtask-mapping-thirdlevel")
@Produces(MediaType.APPLICATION_JSON)
public Response getMessage(@QueryParam("projectKey") String projectKey,@QueryParam("type") String type, @QueryParam("grpOptionName") String grpOptionName, @QueryParam("cldGrpOptionName") String cldGrpOptionName,@QueryParam("fourthLevelOptionName") String fourthLevelOptionName) {
	
	List<RCASubtaskMapModel> rcaMapList=new ArrayList<RCASubtaskMapModel>();
	   try {
		   		List<RCASubtaskMap> rcaList=  rcaSubtaskMapService.getRcaReportSubtaskMappingParentChildDetailsById(projectKey,type,grpOptionName,cldGrpOptionName,fourthLevelOptionName);
		   		for(RCASubtaskMap rcaMap: rcaList){
		   			logger.info("-----------Reading Value From Jira-Db projectKey------------  : "+rcaMap.getProjectKey());
					logger.info("-----------Reading Value From Jira-Db Type------------------  : "+rcaMap.getType());
					logger.info("-----------Reading Value From Jira-Db GrpOptionId-----------  : "+rcaMap.getGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db GrpOptionName---------  : "+rcaMap.getGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionId--------  : "+rcaMap.getCldGrpOptionId());
					logger.info("-----------Reading Value From Jira-Db CldGrpOptionName------  : "+rcaMap.getCldGrpOptionName());
					logger.info("-----------Reading Value From Jira-Db CreatedDate-----------  : "+rcaMap.getCreatedDate());
					logger.info("-----------Reading Value From Jira-Db ModifiedDate----------  : "+rcaMap.getModifiedDate());	
					logger.info("-----------Reading Value From Jira-Db ModifiedBy------------  : "+rcaMap.getModifiedBy());
					RCASubtaskMapModel getRcaReportObj = new RCASubtaskMapModel( rcaMap.getProjectKey(),
							rcaMap.getType(),
							rcaMap.getGrpOptionId(),
							rcaMap.getGrpOptionName(),
							rcaMap.getCldGrpOptionId(),
							rcaMap.getCldGrpOptionName(),
							rcaMap.getRoles(),
							rcaMap.getUsers(),
							rcaMap.getCreatedDate(),
							rcaMap.getModifiedDate(),
							rcaMap.getModifiedBy());
					getRcaReportObj.setFourthLevelOptionId(rcaMap.getFourthLevelOptionId());
					getRcaReportObj.setFourthLevelOptionName(rcaMap.getFourthLevelOptionName());
					
					rcaMapList.add(getRcaReportObj);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
   return Response.ok(rcaMapList).build();
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
    	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
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
                     optionGroupMap.put(String.valueOf(aChildOption.getOptionId()),aChildOption.getValue().toString());
                 }
	    	}
	    }		    
    }
    if(type.equalsIgnoreCase("Internal")){    	
    	CustomField customFieldImpact = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
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
    	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
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
    	CustomField customFieldImpact = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_IMPACTED_FUNCTION);
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
public Response put(final RCASubtaskMapModel[] rcaReportMapArray, @Context HttpServletRequest request)
{
  String username = userManager.getRemoteUsername(request);
  
  List<RCASubtaskMapModel> incMapList=new ArrayList<RCASubtaskMapModel>();	 
  
  Date today = new Date();
  SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy:HH:mm a");
  String date = DATE_FORMAT.format(today);
  
	for (int i=0;i<rcaReportMapArray.length;i++) {   
	
		final ApplicationUser selectedUser = ComponentAccessor.getUserManager().getUserByName(rcaReportMapArray[i].getUsers());  	
		 if (selectedUser == null)
		  {	
		    logger.info("-----------rcaReportMapArray["+i+"].Users UI Users----- "+rcaReportMapArray[i].getUsers()+" -----------Does Not Exist----- ");
			incMapList.add(rcaReportMapArray[i]);	
		  }
	}	
	if(incMapList.size()>0){
		return Response.ok(incMapList).build();
	}else{
	
	for (int i=0;i<rcaReportMapArray.length;i++) {
				
		String pjKey = rcaReportMapArray[i].getProjectKey();
		String type = rcaReportMapArray[i].getType();
		String grpOptionId = rcaReportMapArray[i].getGrpOptionId();
		String grpOptionName = rcaReportMapArray[i].getGrpOptionName();
		String cldGrpOptionId = rcaReportMapArray[i].getCldGrpOptionId();
		String cldGrpOptionName = rcaReportMapArray[i].getCldGrpOptionName();
		String fourthLevelOptionId = rcaReportMapArray[i].getFourthLevelOptionId();
		String fourthLevelOptionName = rcaReportMapArray[i].getFourthLevelOptionName();
		String roles = rcaReportMapArray[i].getRoles();
		String users = rcaReportMapArray[i].getUsers();
		
		String createdDate = date;
		String modifiedDate = date;
		String modifiedBy = username;
		
		rcaSubtaskMapService.saveRcaReportSubtaskMapping(pjKey,type, grpOptionId,grpOptionName,cldGrpOptionId,cldGrpOptionName,roles,users,createdDate, modifiedDate,modifiedBy,fourthLevelOptionId,fourthLevelOptionName);
		
	}
    	return Response.noContent().build();
	}
	
}

@GET
@Path("/exportrcasubtaskassigneecsv")
@Produces("application/vnd.ms-excel")
public Response getrcareportmapcsv(@QueryParam("projectKey") String projectKey) {
	logger.info("-----------csvexport Rest Produces projectKey------------- "+projectKey);
	
	ProjectManager projectManager = ComponentAccessor.getProjectManager();
	CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager(); 
	OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
	
    Project project1 = projectManager.getProjectObjByKey(projectKey);
	IssueType issueType = ComponentAccessor.getIssueTypeSchemeManager().getDefaultIssueType(project1);
	
	CustomField customFieldType = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_TYPE);   
	FieldConfig fieldConfigType = customFieldType.getRelevantConfig(new IssueContextImpl(project1, issueType));	
	Options types = optionsManager.getOptions(fieldConfigType);	
     		
	CustomField customFieldSolution = customFieldManager.getCustomFieldObjectByName(RCASubtaskAssigneeMapConstants.FIELD_SOLUTION_GROUP_PRODUCT);
	FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project1, issueType));
	Options solGrp = optionsManager.getOptions(fieldConfigSolution);
	
	   File CSV_FILE = new File(CSV_PATH,"rcareportmapexport.csv");
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
	   		List<RCASubtaskMap> rcaList=  rcaSubtaskMapService.getRcaReportSubtaskMappingByProjectKey(projectKey);
	   		for(RCASubtaskMap rcaMap: rcaList){
	   			logger.info("-----------csvexport Reading Value From Jira-Db projectKey------  : "+rcaMap.getProjectKey());
	   			logger.info("-----------csvexport Reading Value From Jira-Db Type------------  : "+rcaMap.getType());
	   			logger.info("-----------csvexport Reading Value From Jira-Db GrpOptionId-----  : "+rcaMap.getGrpOptionId());
	   			logger.info("-----------csvexport Reading Value From Jira-Db GrpOptionName---  : "+rcaMap.getGrpOptionName());
	   			logger.info("-----------csvexport Reading Value From Jira-Db Roles-----------  : "+rcaMap.getRoles());	
	   			logger.info("-----------csvexport Reading Value From Jira-Db User------------  : "+rcaMap.getUsers());	
	   			fstream.append(rcaMap.getProjectKey());
	            fstream.append(',');
				
				fstream.append(rcaMap.getType());
	            fstream.append(',');
	            
	            if(rcaMap.getType().equalsIgnoreCase("External")){
					try{
						String solGroupsName=rcaMap.getGrpOptionName();
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
			           // long locGroupsId=Long.parseLong(rcaMap.getGrpOptionId());
			            String locGroupsName=rcaMap.getGrpOptionName();
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
	            int status=Integer.parseInt(rcaMap.getRoles());
	            switch(status){
	                case  10800  :
	                	fstream.append("RCA Report Assignee");
	                    break;
	                case  10801  :
	                	fstream.append("RCA Approver");
	                    break;	                            
	                case  10802  :
	                	fstream.append("Enterprise Board Review Member");
	                    break;	                            
	                }
	            }catch(NumberFormatException e){
				       logger.info("not a number"); 
				}
	            fstream.append(',');
	            fstream.append(rcaMap.getUsers());
	            fstream.append(',');
	            fstream.append(rcaMap.getCreatedDate());
	            fstream.append(',');
	            fstream.append(rcaMap.getModifiedDate());
	            fstream.append(',');
	            fstream.append(rcaMap.getModifiedBy());
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