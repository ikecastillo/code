package com.dt.remote.dtchgcreator.rest;


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
import static com.dt.remote.dtchgcreator.utils.ChanageConstants.*;
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


import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;


 import com.atlassian.jira.security.roles.ProjectRoleManager;
 import com.atlassian.jira.security.roles.ProjectRole;
 import com.atlassian.jira.security.roles.ProjectRoleActors;
 import com.atlassian.jira.security.roles.RoleActor;
 import com.atlassian.jira.security.roles.RoleActorDoesNotExistException;
 import com.atlassian.jira.security.roles.RoleActorFactory;
 import com.atlassian.jira.security.roles.actor.UserRoleActorFactory;


import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;




///remoteapi/changecreator/dealertrack/change
/**
 * The REST Service for Automation Portal 
 *
 * @author Srinadh.Garlapati
 */
@Path("/remoteapi")
public class ChangeResource
{
		private static final Logger logger = Logger.getLogger(ChangeResource.class);
		private final UserManager userManager;
        private static final String DEPARTMENT="department"; 
        private static final String MAIL="mail";  
        private static final String TELEPHONENO="telephonenumber";  
        private static final String MANAGER="manager"; 
        private static final String SN="sn"; 
        private static final String GIVENNAME="givenname";
        private static final String TITLE = "title";
        private final PluginSettingsFactory pluginSettingsFactory;
   
    
     private static final String DISTINGUIESHEDNAME="distinguishedName"; 
    private static final String SAMACCOUNTNAME="sAMAccountName";

    /**
     * Constructor
     * @param userManager
     * @param pluginSettingsFactory
     */
		public ChangeResource(UserManager userManager, PluginSettingsFactory pluginSettingsFactory)
		 {
		   this.userManager = userManager;
            this.pluginSettingsFactory = pluginSettingsFactory;
		 }
    
    /**
     *get parameters from request body
     */
    public static String getBody(HttpServletRequest request) throws IOException {

    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
	logger.info("-------------Error occurs in ChangeResource.decodeString method"+ex.getMessage());
        //throw ex;
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
			logger.info("------------------Error occurs in ChangeResource.decodeString method"+ex.getMessage());
                //throw ex;
            }
        }
    }

    body = stringBuilder.toString();
    return decodeString(body);
}
       
    	
    
   
    
    /**
     *URL decode for parameter string.
     */
    private static String decodeString(String rawValue){
    try {
   return  URLDecoder.decode(rawValue, "UTF-8");
    } catch (UnsupportedEncodingException uee) {
	logger.info("----------------Error occurs in ChangeResource.decodeString method"+uee.getMessage());
         //   uee.printStackTrace(); 
    }
        return "";
       
    }
    
    
    /**
     *Rest call for creating ticket on Critcal Access request approval ticket
     *moved to pending owner approval.
     */
        @POST
		@AnonymousAllowed
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/changecreator")
		public Response put(@QueryParam("projectKey") String projectKey ,@QueryParam("issueType") String issueType, @QueryParam("managerName") String managerName, @Context HttpServletRequest request)
		{
          
          Map<String,String> reqMap=new HashMap<String,String>();
           //getting URL request parameters from critical access request approval ticket.
            String mName=managerName; 
            try{
           String body=getBody(request);
           logger.info("-----------------decoded body :"+body);
          String[] keyValuesList=body.split("&");
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
           }
                }
            
            } catch (IOException ex) {
			logger.info("---------------------Error occurs in ChangeResource.put method"+ex.getMessage());
            // ex.printStackTrace();
            }
    
            
        if(PROJECT_KEY.equals(projectKey) && ISSUE_TYPE.equals(issueType)){ 
          String responses="";  
        String environments=reqMap.get("TypeofEnvironment");
           String[] envList=environments.split(",");
            if(envList!=null){
                //creates change ticket for each enviroment.
            for(String env:envList){
          //n  
          if(env!=null && (!env.isEmpty())){  
            
        IssueService issueService = ComponentAccessor.getIssueService();
        IssueInputParameters issueIP = issueService.newIssueInputParameters(); 
            
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField sgpCF = cfm.getCustomFieldObjectByName(SOLUTION_GROUP_PRODUCT);
        CustomField impactedFnCF = cfm.getCustomFieldObjectByName(IMPACTED_FUNCTION);
        CustomField environmentCF = cfm.getCustomFieldObjectByName(ENVIRONMENT);
		CustomField ibuCF = cfm.getCustomFieldObjectByName(IMPACTED_BUSINESS_UNIT);
        CustomField changeTypeCF = cfm.getCustomFieldObjectByName(CHANGE_TYPE);
        CustomField riskTypeCF = cfm.getCustomFieldObjectByName(RISK);
        CustomField reporter = cfm.getCustomFieldObjectByName("Reporter");
        CustomField reporterTitle = cfm.getCustomFieldObjectByName(REPORTERS_TITLE);
        CustomField reporterEmailCF = cfm.getCustomFieldObjectByName(REPORTERS_EMAIL);
        CustomField reporterDepartmentCF = cfm.getCustomFieldObjectByName(REPORTERS_DEPARTMENT);
        CustomField reporterPhoneCF = cfm.getCustomFieldObjectByName("Reporter's Contact Phone");
        CustomField managersNameCF = cfm.getCustomFieldObjectByName(MANAGERS_NAME);
        CustomField rollbackDescCF = cfm.getCustomFieldObjectByName(ROLLBACK_DESC);
       // CustomField rollbackDescCF = cfm.getCustomFieldObjectByName(ROLLBACK_DESC);
        CustomField reqStartDateCF = cfm.getCustomFieldObjectByName(REQ_ST_DATE);
        CustomField reqEndDateCF = cfm.getCustomFieldObjectByName(REQ_END_DATE);
        /* Risk Questions*/
        CustomField question1 = cfm.getCustomFieldObjectByName(RISK_QUESTION1);
        CustomField question2 = cfm.getCustomFieldObjectByName(RISK_QUESTION2);
        CustomField question3 = cfm.getCustomFieldObjectByName(RISK_QUESTION3);
        CustomField question4 = cfm.getCustomFieldObjectByName(RISK_QUESTION4);

        CustomField rollbackQuestion1 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION1);
        CustomField rollbackQuestion2 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION2);
        CustomField rollbackQuestion3 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION3);
        CustomField rollbackQuestion4 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION4);
        CustomField rollbackQuestion5 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION5);
        CustomField rollbackQuestion6 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION6);
        CustomField rollbackQuestion7 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION7);
        CustomField rollbackQuestion8 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION8);
        CustomField rollbackQuestion9 = cfm.getCustomFieldObjectByName(ROLLBACKPLAN_QUESTION9);
            
            

            //Set the required System fields
          Project proj = ComponentAccessor.getProjectManager().getProjectObjByKey(projectKey);
         JiraAuthenticationContext jAC = ComponentAccessor.getJiraAuthenticationContext(); 
           String id= getIssueTypeIdbyName(issueType);
        issueIP.setProjectId(proj.getId());     // SET PROJECT ID
        issueIP.setSummary(reqMap.get("Summary"));// SETS SUMMARY
        issueIP.setIssueTypeId(id);// SETS ISSUE TYPE
        issueIP.setDescription(ISSUE_DESC_VALUE);// SETS ISSUE DESCRIPTION
        issueIP.setReporterId(reqMap.get("Reporter")); //Sets reporter to the sender of the email
        issueIP.setPriorityId("5"); //Sets PriorityId
        issueIP.setAssigneeId(jAC.getLoggedInUser().getName());
            
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMM dd, YYYY hh:mm aa");
        String[] solArray=new String[2] ;   
          String solGrp= reqMap.get("SolutionGroupProduct");
           if(solGrp.contains(",")){
             solArray= solGrp.split(",");  
            }else{
              solArray= solGrp.split("%2C");  
            }
               Map<String,String> repDetails= null;
        try{   
           try{   
       repDetails= getDistinguishedNamebyUserName(reqMap.get("Reporter"));
	   logger.info("-----------ReportersDeatils:-------------"+repDetails);
				for (Object o : repDetails.keySet()) {
					logger.info("-------------key:-------------"+o);
				}
              } catch (NamingException ex) {
			  logger.info("------------------------Error occurs in ChangeResource.put method"+ex.getMessage());
			//ex.printStackTrace();
			//throw ex;
		}
              } catch (Exception e) {
			  logger.info("------------------------Error occurs in ChangeResource.put method"+e.getMessage());
			//e.printStackTrace();
			//throw ex;
		}
              
         logger.info(solArray+"---------------solArray.length ---------------------- :"+solArray.length);
        String sol_grp_product=getDefaultOption(sgpCF.getName(),proj.getId(),id,solArray[0]);
        logger.info("-----------------------sol_grp_product  :"+sol_grp_product);
        String impact=getDefaultOption(impactedFnCF.getName(),proj.getId(),id,IMPACTED_VALUE);
              logger.info("----------impact----------"+impact);
        String function=getDefaultfunctionOption(impactedFnCF.getName(),proj.getId(),id,IMPACTED_VALUE,IMPACTED_FUNCTION_VALUE); 
              logger.info("----------function------------------"+function);
			  logger.info("---------reporterTitle-----"+reporterTitle);
			  logger.info("---------reporterTitle - ID-----"+reporterTitle.getId());
          if(solArray.length==2){    
   issueIP.addCustomFieldValue(reqStartDateCF.getId(), getFormattedRequestStartDate(date,fmt))
           .addCustomFieldValue(reqEndDateCF.getId(), getFormattedRequestEndDate(date,fmt))
           .addCustomFieldValue(sgpCF.getId(), getDefaultOption(sgpCF.getName(),proj.getId(),id,solArray[0]))
           .addCustomFieldValue(sgpCF.getId()+ ":1", getDefaultfunctionOption(sgpCF.getName(),proj.getId(),id,solArray[0],solArray[1]))
           .addCustomFieldValue(impactedFnCF.getId(),impact)
           .addCustomFieldValue(impactedFnCF.getId() + ":1",function)
           .addCustomFieldValue(changeTypeCF.getId(), getDefaultOption(changeTypeCF.getName(),proj.getId(),id,CHANGE_TYPE_VALUE))
                .addCustomFieldValue(environmentCF.getId(), getDefaultOption(environmentCF.getName(),proj.getId(),id,env))
                .addCustomFieldValue(riskTypeCF.getId(), getDefaultOption(riskTypeCF.getName(),proj.getId(),id,RISK_VALUE))
                .addCustomFieldValue(reporterTitle.getId(), (repDetails.get(TITLE)!=null)?repDetails.get(TITLE):"")
                .addCustomFieldValue(reporterEmailCF.getId(),(repDetails.get(MAIL)!=null)?repDetails.get(MAIL):"NA")
                .addCustomFieldValue(reporterDepartmentCF.getId(),(repDetails.get(DEPARTMENT)!=null)?repDetails.get(DEPARTMENT):"NA")
                .addCustomFieldValue(reporterPhoneCF.getId(),(repDetails.get(TELEPHONENO)!=null)?repDetails.get(TELEPHONENO):"")
                .addCustomFieldValue(managersNameCF.getId(),(repDetails.get(MANAGER)!=null)?getUserNameByDistinguishedName(repDetails.get(MANAGER)):"")
                .addCustomFieldValue(question1.getId(),getDefaultOption(question1.getName(),proj.getId(),id,RISK_QUESTION1_VALUE))
                .addCustomFieldValue(question2.getId(),getDefaultOption(question2.getName(),proj.getId(),id,RISK_QUESTION2_VALUE))
                .addCustomFieldValue(question3.getId(),getDefaultOption(question3.getName(),proj.getId(),id,RISK_QUESTION3_VALUE))
                .addCustomFieldValue(question4.getId(),getDefaultOption(question4.getName(),proj.getId(),id,RISK_QUESTION4_VALUE))
                .addCustomFieldValue(rollbackQuestion1.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion2.getId(), ROLLBACK_USER)
                .addCustomFieldValue(rollbackQuestion3.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion4.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion5.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion6.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion7.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion8.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion9.getId(), ROLLBACK_DEFAULT_VALUE)
				.addCustomFieldValue(ibuCF.getId(), IMPACTED_BUSINESS_UNIT_VALUE );
          }else{
           logger.info("else block :"+solArray.length);
           issueIP.addCustomFieldValue(reqStartDateCF.getId(), getFormattedRequestStartDate(date,fmt))
           .addCustomFieldValue(reqEndDateCF.getId(), getFormattedRequestEndDate(date,fmt))
           .addCustomFieldValue(sgpCF.getId(), sol_grp_product)
           .addCustomFieldValue(impactedFnCF.getId(),impact)
           .addCustomFieldValue(impactedFnCF.getId() + ":1",function)
           .addCustomFieldValue(changeTypeCF.getId(), getDefaultOption(changeTypeCF.getName(),proj.getId(),id,CHANGE_TYPE_VALUE))
                .addCustomFieldValue(environmentCF.getId(), getDefaultOption(environmentCF.getName(),proj.getId(),id,env))
                .addCustomFieldValue(riskTypeCF.getId(), getDefaultOption(riskTypeCF.getName(),proj.getId(),id,RISK_VALUE))
                   .addCustomFieldValue(reporterTitle.getId(), (repDetails.get(TITLE)!=null)?repDetails.get(TITLE):"")
                .addCustomFieldValue(reporterEmailCF.getId(),(repDetails.get(MAIL)!=null)?repDetails.get(MAIL):"NA")
                .addCustomFieldValue(reporterDepartmentCF.getId(),(repDetails.get(DEPARTMENT)!=null)?repDetails.get(DEPARTMENT):"NA")
                .addCustomFieldValue(reporterPhoneCF.getId(),(repDetails.get(TELEPHONENO)!=null)?repDetails.get(TELEPHONENO):"")
                .addCustomFieldValue(managersNameCF.getId(),(repDetails.get(MANAGER)!=null)?getUserNameByDistinguishedName(repDetails.get(MANAGER)):"")
                .addCustomFieldValue(question1.getId(),getDefaultOption(question1.getName(),proj.getId(),id,RISK_QUESTION1_VALUE))
                .addCustomFieldValue(question2.getId(),getDefaultOption(question2.getName(),proj.getId(),id,RISK_QUESTION2_VALUE))
                .addCustomFieldValue(question3.getId(),getDefaultOption(question3.getName(),proj.getId(),id,RISK_QUESTION3_VALUE))
                .addCustomFieldValue(question4.getId(),getDefaultOption(question4.getName(),proj.getId(),id,RISK_QUESTION4_VALUE))
                .addCustomFieldValue(rollbackQuestion1.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion2.getId(), ROLLBACK_USER)
                .addCustomFieldValue(rollbackQuestion3.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion4.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion5.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion6.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion7.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion8.getId(), ROLLBACK_DEFAULT_VALUE)
                .addCustomFieldValue(rollbackQuestion9.getId(), ROLLBACK_DEFAULT_VALUE)
				.addCustomFieldValue(ibuCF.getId(), IMPACTED_BUSINESS_UNIT_VALUE );
              
          }
        


        IssueService.CreateValidationResult issue;
        
        issue = issueService.validateCreate(jAC.getLoggedInUser(), issueIP);// GETS THE CreateValidationResult OBJECT
         
        
        if (issue.isValid())
        {
            IssueResult createResult = issueService.create(jAC.getLoggedInUser(), issue);
            if (!createResult.isValid())
            {
                logger.info("issue not created!!!"+createResult.getErrorCollection());
                for(String error : createResult.getErrorCollection().getErrorMessages())
                {
                       logger.info("1:   "+error);
                }
            } else  {
                logger.info("Issue created  : "+createResult.getIssue().getKey());
                responses = responses+(createResult.getIssue().getId()+"="+createResult.getIssue().getKey())+",";
             }
        } else {
                logger.info("Issue validation failed  : "+issue.getErrorCollection().getErrors());
                for(String error : issue.getErrorCollection().getErrorMessages())
                {
                        logger.info("2:    "+error);
                }
            }
    
      // PASSES THE CreateValidationResult OBJECT AND THE user INTO TO CREATE ISSUE STATEMENT, WHICH SHOULD CREATE THE ISSUE.
                    }
                 }
            
          return Response.ok(responses).build(); 
        }
              }
      return Response.noContent().build();
		}
    
                                     
    /**
     *get optionId by optionName.
     */  
    private String getDefaultOption(String feildName,Long projectId,String issueTypeId,String optName){
        logger.info("-----------Field Name:----"+feildName+"---------------projectId:--------"+projectId+"-------------issueTypeId:--------"+issueTypeId+"------------------optName:-"+optName);
    CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();    
    CustomField feildObj = cfManager.getCustomFieldObjectByName(feildName);
	String value="";
        if(feildObj!=null){
        IssueContextImpl issueContext = new IssueContextImpl(projectId, issueTypeId);
        FieldConfig fieldConfig = feildObj.getRelevantConfig(issueContext);
        OptionsManager optionsManager =  ComponentAccessor.getOptionsManager();
        Options op=optionsManager.getOptions(fieldConfig);
            if(op!=null && op.getRootOptions()!=null){
          List<Option> optList=  op.getRootOptions();
                for(Option opt:optList){
                    if((!(opt.getDisabled())) && (opt.getValue().equalsIgnoreCase(optName))){
					value = ""+opt.getOptionId();
					break;
                     //logger.info(feildName+"optName"+optName+"feild name and its option Id"+opt.getOptionId());
                       // return ""+opt.getOptionId();
                    }
                }
              }
            }
       return value;         
    }
    
    /**
     *get child optionId by  child optionName.
     */
    private String getDefaultfunctionOption(String feildName,Long projectId,String issueTypeId,String parentOptName,String childOptName){
	//logger.info("-------feildName----:"+feildName);
	//logger.info("-------ProjectId----:"+projectId);
	//logger.info("-------issueTypeId----:"+issueTypeId);
	//logger.info("-------parentOptName----:"+parentOptName);
	//logger.info("-------childOptName----:"+childOptName);
	String value="";
    CustomFieldManager cfManager = ComponentAccessor.getCustomFieldManager();    
    CustomField feildObj = cfManager.getCustomFieldObjectByName(feildName);
        if(feildObj!=null){
		logger.info("-------Field object is not null----:");
        IssueContextImpl issueContext = new IssueContextImpl(projectId, issueTypeId);
        FieldConfig fieldConfig = feildObj.getRelevantConfig(issueContext);
        OptionsManager optionsManager =  ComponentAccessor.getOptionsManager();
        Options op=optionsManager.getOptions(fieldConfig);
            if(op!=null && op.getRootOptions()!=null){
			//logger.info("-------Options List is not null----:");
           List<Option> optList=  op.getRootOptions();
                for(Option opt:optList){
				//logger.info("-------Iterating the list----:");
                    if((!opt.getDisabled()) && (opt.getValue().equals(parentOptName))){
                        List<Option> coptList=opt.getChildOptions();
                         for(Option copt:coptList){
						// logger.info("-----------------childOptName-----------------"+childOptName);
						//logger.info("--------------------is option disabled--"+copt.getDisabled()+"-----");
						//logger.info("-----------------copt.getValue()-----------------"+copt.getValue());
						//logger.info("-----------------copt.getValue().equals(childOptName)-----------------"+copt.getValue().equals(childOptName));
                        if((!copt.getDisabled()) && (copt.getValue().equals(childOptName))){
                        value =  ""+copt.getOptionId();
						break;
                         }
                       }
                    }
                }
              }
            }
			logger.info("-------The value is:----:"+value);
       return value;         
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
    
    
    //get lDAP context
    /**
	 * get the LDAP context
	 * 
	 * @return
	 */
	private DirContext getLDAPContext() throws NamingException {
		DirContext context = null;
		PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
		LdapConfiguration ldapSrvc = new LdapConfiguration();
		 // Get LDAP Initial Context Class Name
		String ldapInitCtx = (String) settings.get(LdapConfiguration.class.getName() + ".ldapInitCtx");
       // logger.info("-------------ldapInitCtx--------"+ldapInitCtx);
	     // Get LDAP URL 	
		String ldapSrvrName = (String) settings.get(LdapConfiguration.class.getName() + ".ldapSrvrName");
        //logger.info("-------------ldapSrvrName--------"+ldapSrvrName);
		// Get LDAP BaseDN  Name
		String ldapBaseDn = (String) settings.get(LdapConfiguration.class.getName() + ".ldapBaseDn");
       // logger.info("-------------ldapBaseDn--------"+ldapBaseDn);
		// Get LDAP user login Name
		String ldapUid = (String) settings.get(LdapConfiguration.class.getName()+ ".ldapUid");
        //logger.info("-------------ldapUid--------"+ldapUid);
		String ldapPwd = (String) settings.get(LdapConfiguration.class.getName()+ ".ldapPwd");
       // logger.info("-------------ldapPwd--------"+ldapPwd);
		Properties properties = new Properties();
		/*properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(javax.naming.Context.PROVIDER_URL, "ldap://"+"10.134.38.10:389");
		properties.put(javax.naming.Context.SECURITY_PRINCIPAL, "cn=DTjira Admin,ou=Service Accounts,ou=Lake Success,dc=dt,dc=inc");
		properties.put(javax.naming.Context.SECURITY_CREDENTIALS, "DTpass11");*/
        
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,ldapInitCtx);
		properties.put(javax.naming.Context.PROVIDER_URL, ldapSrvrName);
		properties.put(javax.naming.Context.SECURITY_PRINCIPAL, ldapUid);
		properties.put(javax.naming.Context.SECURITY_CREDENTIALS, ldapPwd);

		try {

			context = new InitialDirContext(properties);

		} catch (NamingException e) {
			logger.info("Error occurs in ChangeResource.getLDAPContext method"+e.getMessage());
			//e.printStackTrace();
			//throw e;

		}

		return context;
	}
    
    
    
    
    
    //get reporter details by name
    
    /**
	 * gets distinguishedName by user name from LDAP BaseDN.
	 * 
	 * @param username
	 * @return
	 */
	public Map<String,String> getDistinguishedNamebyUserName(String username) throws NamingException,Exception{
        DirContext initialdircontext = null;
		try {
			initialdircontext = getLDAPContext();
            PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
            String ldapBaseDn = (String) settings.get(LdapConfiguration.class.getName() + ".ldapBaseDn");
			// String ldapBaseDn ="dc=dt,dc=inc";
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { DISTINGUIESHEDNAME,SN,GIVENNAME,MAIL,TELEPHONENO,MANAGER,DEPARTMENT,TITLE  };
			constraints.setReturningAttributes(attrIDs);
			logger.info("----------------username "+ username);
			 Map<String,String> reporterDetails=new HashMap<String,String>();
			NamingEnumeration answer = initialdircontext.search(ldapBaseDn,SAMACCOUNTNAME+"=" + username, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next())
						.getAttributes();
				logger.info("---------------------distinguishedName "
						+ attrs.get(DISTINGUIESHEDNAME).get(0));
                
               
               
                if(attrs.get(DISTINGUIESHEDNAME)!=null){
                reporterDetails.put(DISTINGUIESHEDNAME,attrs.get(DISTINGUIESHEDNAME).get(0).toString());
                }
                if(attrs.get(MAIL)!=null){
                reporterDetails.put(MAIL,attrs.get(MAIL).get(0).toString());
                }
                
                if(attrs.get(TELEPHONENO)!=null){
                reporterDetails.put(TELEPHONENO,attrs.get(TELEPHONENO).get(0).toString());
                }
                if(attrs.get(MANAGER)!=null){
                reporterDetails.put(MANAGER,attrs.get(MANAGER).get(0).toString());
                }
                
                if(attrs.get(DEPARTMENT)!=null){
                reporterDetails.put(DEPARTMENT,attrs.get(DEPARTMENT).get(0).toString());
                }
                // Reporter's Title
                if(attrs.get(TITLE)!=null){
                    reporterDetails.put(TITLE,attrs.get(TITLE).get(0).toString());
                }
                logger.info("---------------------reporterDetails : "+reporterDetails);
				return reporterDetails;
			} else {
			logger.info("Error occurs in ChangeResource.getDistinguishedNamebyUserName method");
				//throw new Exception("Invalid User");
			}

		} catch (NamingException ex) {
		logger.info("Error occurs in ChangeResource.getDistinguishedNamebyUserName method"+ex.getMessage());
			//ex.printStackTrace();
			//throw ex;
		}
        return null;
    }
        
        
    /**
	 * gets distinguishedName by user name from LDAP BaseDN.
	 * 
	 * @param distinguishedName
	 * @return
	 */
	public String getUserNameByDistinguishedName(String distinguishedName) {
        DirContext initialdircontext = null;
		try {
			initialdircontext = getLDAPContext();
             PluginSettings settings = pluginSettingsFactory.createGlobalSettings();
            String ldapBaseDn = (String) settings.get(LdapConfiguration.class.getName() + ".ldapBaseDn");
			//String ldapBaseDn ="dc=dt,dc=inc";
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { SAMACCOUNTNAME };
			constraints.setReturningAttributes(attrIDs);
			logger.info("distinguishedName "+ distinguishedName);
			NamingEnumeration answer = initialdircontext.search(ldapBaseDn,DISTINGUIESHEDNAME+"=" + distinguishedName, constraints);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next())
						.getAttributes();
				logger.info("sAMAccountName "
						+ attrs.get(SAMACCOUNTNAME).get(0));
                
                return ""+attrs.get(SAMACCOUNTNAME).get(0);
			} else {
				logger.info("Invalid distinguishedName : "+distinguishedName);
			}

		} catch (NamingException ex) {
			logger.info("Error occurs in ChangeResource.getUserNameByDistinguishedName method"+ex.getMessage());
			//ex.printStackTrace();
			//throw ex;
		}

		return null;

	}
    
    
}