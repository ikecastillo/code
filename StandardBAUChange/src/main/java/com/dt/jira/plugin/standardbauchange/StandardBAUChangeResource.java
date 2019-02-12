package com.dt.jira.plugin.standardbauchange;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.atlassian.jira.security.JiraAuthenticationContext;
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
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.fields.CustomField;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.atlassian.jira.bc.issue.IssueService.IssueResult;
import javax.ws.rs.core.Context;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;






/**
 * The REST Service for Automation Portal 
 *
 * @author Kiran.muthoju
 */
@Path("/remoteapi")
public class StandardBAUChangeResource
{
		private final Logger logger = Logger.getLogger(StandardBAUChangeResource.class);
		private final UserManager userManager;
        private static final String PROJECT_KEY="CHG"; 
        private static final String ISSUE_TYPE="Change";  
     
		
    
	/**
	 * Constructor
	 * @param portalService  to be injected
	 * @param userManager the UserManager to be injected
	 */
		public StandardBAUChangeResource(UserManager userManager)
		 {
		   this.userManager = userManager;          
		 }
    
   
       
    	
    
   
    
    /**
     *URL decode for parameter string.
     */
    private static String decodeString(String rawValue){
    try {
			return  URLDecoder.decode(rawValue, "UTF-8");
    } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace(); 
    }
        return "";
       
    }
    
    
    /**
     *Rest call for WCDICC-693	Copy Template and Value from Service Desk and add to Change Management System
     *
     */
        @POST
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/copystandardCascadeValue")
		public Response copystandardCascadeValue(@Context HttpServletRequest request)
		{
			CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager(); 
			Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("CHG");			
			logger.info("****************** StandardBAUChangeResource  ******************"+project.getName());
			CustomField impactedCF = customFieldManager.getCustomFieldObjectByName("Impacted - Function");
		 try{
			String  params= getBody(request);
			Map<String,String> reqMap=new HashMap<String,String>();
			logger.info("decoded body :"+params);
			String[] keyValuesList=params.split("&");
				if(keyValuesList!=null && keyValuesList.length>0){
				   for(String keyValue:keyValuesList){
					   String[]  key= keyValue.split("=");
					   
					   if(key!=null && key.length>0){
						   int len=key.length;
						   if(len>=2){
					   reqMap.put(key[0],key[1]);
						   }else if(len==1){
							reqMap.put(key[0],"");    
						   }
						}
				   } // end for loop
				}//end if
			String summary=reqMap.get("Summary");
			String impactedValue=reqMap.get("Impacted");
			IssueType changeIssueType= null;         
			Collection<IssueType> issueTypesProj = ComponentAccessor.getIssueTypeSchemeManager().getIssueTypesForProject(project);
			for(IssueType issueT: issueTypesProj){
				 if(!issueT.isSubTask() && issueT.getName().equals("Change") ){ // exclude sub-task					
						changeIssueType = issueT;
				 }
			}
			FieldConfig fieldConfigSolution = impactedCF.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
			OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class);  
			Options options = optionsManager.getOptions(fieldConfigSolution);
			String resMsg = "";			
			//System.out.println("options : " + options);
			List<Option> solutionGroupEnable = new ArrayList<Option>();
			if(options != null){
				for(Option solopt : options){ 	
					if(!solopt.getDisabled() && solopt.getValue().equals(impactedValue) ){
						//solutionGroupEnable.add(solopt);
						System.out.println("Standard name : "+solopt.getValue());
						Option option = getOptionBasedonHiddenFeildText(impactedCF,solopt.getChildOptions(),summary,solopt.getOptionId(),project,changeIssueType);
						if(option!=null){
							//System.out.println("Added new cascade value under Standard change type");
							logger.info("Added new cascade value");
							resMsg = "Added new cascade value";
						} else {
							//System.out.println("Cascade value is already exist");
							logger.info("Cascade value is already exist");
							resMsg = "Cascade value is already exist";
						}
					}
				}
			}
				
			 
			  
			  return Response.ok(resMsg).build();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.noContent().build();
	}
    /***
	 * Method checks option exists for hidden field or ,if it does not exists in JIRA it will create in JIRA.
	 * if option does not exists in sales force it ignores.
	 * @param childOpt
	 * @param textFieldValue
	 * @param parentOptionId
	 * @param issueParent
	 * @return
	 */
	private Option getOptionBasedonHiddenFeildText(CustomField changeTypeCF, List<Option> childOpt,String textFieldValue,Long parentOptionId,Project project,IssueType issueType){
		String compare = null;
		compare = textFieldValue;
		boolean isOptionExist = false;
		//  Find existing child option
			for (Option childOptionValue : childOpt) {
				String childStr = childOptionValue.toString();
				if (childStr.equalsIgnoreCase(compare)) {
					isOptionExist = true;
					logger.info("existing child option found: "+compare);
					return null;					
				}
			}
		

		// add child option in JIRA
		if (!isOptionExist) {
			// add child option			
			FieldConfig fieldConfigSolution = changeTypeCF.getRelevantConfig(new IssueContextImpl(project.getId() , issueType.getId()));
			 OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 
			Option newOption= optionsManager.createOption(fieldConfigSolution,parentOptionId, new Long(childOpt.size()), textFieldValue) ;			
			
			logger.info("  added new Option in "+ newOption);
			return 	newOption;
			
		}
		
		return 	null;
	}
                                     
    
    
    /**
     * Helper method to get the requested start date as a string
     * @param date
     * @param fmt
     * @return Requested Start Date Formatted as String
     */
    private String getFormattedRequestStartDate(LocalDateTime date, DateTimeFormatter fmt) {
        date = date.plusHours(2); //Add two hours to current date time
        return date.toString(fmt);
    }

    /**
     * Helper method to get the requested end date as a string
     *
     * @param date
     * @param fmt
     * @return Requested End Date formatted as String
     */
    private String getFormattedRequestEndDate(LocalDateTime date, DateTimeFormatter fmt){
        date = date.plusHours(3); //add three hours to current date time
        return date.toString(fmt);
    }
    
    /**
     *getIssueTypeIdbyName
     */
    private String getIssueTypeIdbyName(String issuetypeName){
        Collection<IssueType> issueTypeList= ComponentAccessor.getConstantsManager().getAllIssueTypeObjects();
        for(IssueType issueType:issueTypeList){
            if(issueType.getName().equals(issuetypeName)){
                return issueType.getId();
            }
        }
     return null;        
    }
                
                                     
    /**
     *getOptionIdByName
     */                                 
    private String getOptionIdByName(String optionName){
     OptionsManager optionsManager =  ComponentAccessor.getOptionsManager();
     List<Option> optList=optionsManager.findByOptionValue(optionName);
        if(optList!=null && (!optList.isEmpty())){
            for(Option option:optList){
                if(!option.getDisabled()){
                 return ""+(optList.get(0)).getOptionId();
                }
            }
        }
     return null;
    }
    
    /**
     *getCustomFeildIdByName
     */
    private String getCustomFeildIdByName(String mandatoryFeild){
        CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();
        CustomField customFeildObj = cfManager.getCustomFieldObjectByName(mandatoryFeild);
        return customFeildObj.getId();
        
    }
    /**
     *get parameters from request body
     */
    public String getBody(HttpServletRequest request) throws IOException {

    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String bytesRead = null;
            while ((bytesRead = bufferedReader.readLine()) != null ) {
                stringBuilder.append(bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
        throw ex;
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
    }

    body = stringBuilder.toString();  
	
    return decodeString(body);
}
    
    
}