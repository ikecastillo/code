package com.dt.jira.plugin.automatedailycabsummary;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.mail.MailService;
import com.atlassian.jira.notification.NotificationRecipient;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.mail.queue.MailQueue;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import java.io.UnsupportedEncodingException;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.rest.EmailConfig;

import com.dt.jira.plugin.service.TemplateService;
import com.dt.jira.plugin.rest.TemplateModel;
import com.dt.jira.plugin.rest.SectionModel;
import java.lang.reflect.Field;

import java.util.*;
import org.apache.commons.collections.*;
//import org.apache.commons.collections4.TransformerUtils;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import com.atlassian.sal.api.ApplicationProperties;


@Path("/automatedailycab")
public class AutomateDailyCabRest {
	private static final String PLUGIN_STORAGE_KEY = EmailConfig.class.getName();
	private final MailService mailService;
    private final PluginSettingsFactory pluginSettingsFactory;
    private final TemplateService templateService;
    private final Logger logger = Logger.getLogger(AutomateDailyCabRest.class);
    private final ApplicationProperties applicationProperties;
    
	public AutomateDailyCabRest(MailService mailService,PluginSettingsFactory pluginSettingsFactory,TemplateService templateService, ApplicationProperties applicationProperties){
		this.mailService = mailService;
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.templateService=templateService;
        this.applicationProperties=applicationProperties;
	}
    
    
    private String curretnDateString(){
    String pattern = "MM/dd/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return (format.format(new Date()));
    }
    
  private String getCurretDateLongFormat(){   
      DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
      Date today = new Date();
  return dateFormatter.format(today);
  }
  
  private String getTemplatePathByTemplateName(String templateName){
  if(templateName!=null && (!templateName.isEmpty())){
  templateName=(templateName.trim()).toLowerCase();
   return templateName.replace(" ","_");
    }
	return null;
  }
  
  
  
  

    @GET
    @AnonymousAllowed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage()
    { 
        
        try{
        PluginSettings pluginSettings = this.pluginSettingsFactory.createGlobalSettings();
        String feilds = (String) pluginSettings.get(PLUGIN_STORAGE_KEY + ".feilds");
		
		List<TemplateModel>  templateList= templateService.getAllTemplates();
		System.out.println("templateList:   "+templateList);
		if(templateList!=null){
		
		for(TemplateModel templateModel:templateList){
		System.out.println(templateModel+"templateModel:   "+templateModel.getTemplate_name());
		//String vmName=getTemplatePathByTemplateName(templateModel.getTemplate_name());
		//System.out.println("vmName:    "+vmName);
		MailQueue mailQueue = ComponentAccessor.getMailQueue();		
    	String subjectTemplatePath = "/templates/subjecttemplate.vm";
    	String bodyTemplatePath = "/templates/bodytemplate.vm";
    	Map<String,Object> context = new HashMap<String,Object>();
            //need to remove template hard code later
        Map<TemplateModel,List<SectionModel>> templateDetails=getTemplateDetails(templateModel.getTemplate_name(),context);
        context.put("templateDetails",templateDetails);
        context.put("reportDate",curretnDateString());
        context.put("reportOnDate",getCurretDateLongFormat());
        context.put("chgUrl",applicationProperties.getBaseUrl()+"/browse/");    
            
            
         //List<ApplicationUser> usrList=getUserRecipients(templateDetails);
        //if(usrList!=null){
            List<String> usrList=getToUsers(templateDetails);
        if(usrList!=null){
            for(String user:usrList){
               String from=(String)context.get("mailFrom");
                ApplicationUser applicationUser =null;
                if(from!=null && (!from.isEmpty())){
                applicationUser = ComponentAccessor.getUserManager().getUserByName(from);
                }
        logger.info("---applicationUser---"+applicationUser);
    	
        NotificationRecipient recipient = new NotificationRecipient(user);
                if(applicationUser!=null){
        mailService.sendRenderedMail(applicationUser, recipient, subjectTemplatePath, bodyTemplatePath, context);
                }else{
             logger.info("---applicationUser-- is not configured -"+applicationUser);       
                }
            }
        }
		logger.info("Automate daily cab summary email sent");
        
		}
		}
		}catch(Exception e){
            
         }
		return Response.ok(new AutomateDailyCabRestModel("Automate daily cab summary email sent")).build();
    }
    
    //need mail_to from template
    
    //need SectionName as header and JQL to get Issues list and coloumns need to display.
    
    // Mailto list , Section Name , Section columns , issues List
    
    private  Map<TemplateModel,List<SectionModel>> getTemplateDetails(String templateName,Map<String,Object> context){
        Map<TemplateModel,List<SectionModel>> tempalteDetails= templateService.findTemplateByName(templateName);
        try{
        for (Map.Entry<TemplateModel,List<SectionModel>> entry : tempalteDetails.entrySet()){
            TemplateModel template=(TemplateModel)entry.getKey();
            context.put("mailFrom",template.getMail_from());
            context.put("subject",template.getSubject());
            context.put("mailBody",template.getMail_body());
           List<SectionModel> sectionModelList=(List<SectionModel>)entry.getValue();
            if(sectionModelList!=null){
                for(SectionModel section:sectionModelList){
                  List<Issue> issueList= getIssuesOnJQL(section.getJql_query());
                  List<Map<String,List<String>>> entireList=new ArrayList<Map<String,List<String>>>();
                    for(Issue issue:issueList){
                 Map<String,List<String>> issueMap= new IssueUtils().getJiraFieldValues(issue,section.getColumns().split(","));
                        context.put("colSize",section.getColumns().split(",").length);
                        entireList.add(issueMap);
                    }
                    
                    section.setJql_result(entireList);
                  }
                
                
                
                
            }
            
        }
        }catch(Exception e){
         e.printStackTrace();   
            
        }
        return tempalteDetails;
        
    }
    
    
    private List<ApplicationUser> getUserRecipients(Map<TemplateModel,List<SectionModel>> templateDetails){
        
         List<ApplicationUser> applicationUserList=new ArrayList<ApplicationUser>();
        try{
       for (Map.Entry<TemplateModel,List<SectionModel>> entry : templateDetails.entrySet()){
         TemplateModel template=(TemplateModel)entry.getKey(); 
         String users=  template.getMail_to();
           if(users!=null){
            String[] userArray= users.split(",");
               if(userArray!=null){
                 for(String user:userArray){
                     String appUser=user.trim();
                     if(appUser!=null && (!appUser.isEmpty())){
                   ApplicationUser applicationUser= ComponentAccessor.getUserManager().getUserByName(appUser); 
                         if(applicationUser!=null){
                           applicationUserList.add(applicationUser);  
                         }
                        }
                 }  
                   
               }
           }
           
       }
        }catch(Exception e){
         e.printStackTrace();   
            
        }
        return applicationUserList;
        
    }
    
    
    private List<String> getToUsers(Map<TemplateModel,List<SectionModel>> templateDetails){
        
         List<String> applicationUserList=new ArrayList<String>();
        try{
       for (Map.Entry<TemplateModel,List<SectionModel>> entry : templateDetails.entrySet()){
         TemplateModel template=(TemplateModel)entry.getKey(); 
         String users=  template.getMail_to();
           if(users!=null){
            String[] userArray= users.split(",");
               if(userArray!=null){
                applicationUserList.addAll(Arrays.asList(userArray));  
                   
               }
           }
           
       }
        }catch(Exception e){
         e.printStackTrace();   
            
        }
        return applicationUserList;
        
    }
    
    
    
    
    
   private List<Issue> getIssuesOnJQL(String jql) throws SearchException{
       List<Issue> issues =null;
       if(jql!=null){
	logger.info("*******Query: "+jql.toString());
    
    SearchService searchService=ComponentAccessor.getComponent(SearchService.class);
        
	final SearchService.ParseResult pResult= searchService.parseQuery(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),(jql.toString()));
	if (pResult.isValid())  {  
		SearchResults issueList = searchService.search( ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
	    issues = issueList.getIssues();
	    logger.info("total issues: " + issues.size());
	  }
    }
 return issues;

}

    
   
   
   
}