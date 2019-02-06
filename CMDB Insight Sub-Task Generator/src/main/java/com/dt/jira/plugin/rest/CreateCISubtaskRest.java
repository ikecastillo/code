package com.dt.jira.plugin.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.event.type.EventDispatchOption;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.index.IndexException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.util.ImportUtils;
import com.atlassian.jira.issue.index.IssueIndexingService;

import org.apache.log4j.Logger;
/**
 * A resource is used to create the sub-task for each CI object which is selected on Select CI field.
 It creates on below use cases
 1. While create/edit change ticket and status is initiate
 2. On Select CI button 
 */
@Path("/subtask")
public class CreateCISubtaskRest {
	IssueManager issueManager = null;
	MutableIssue issue = null;
	ApplicationUser user = null;
	private final Logger logger = Logger.getLogger(CreateCISubtaskRest.class);
	private final IssueIndexingService issueIndexingService; 
	 
	public CreateCISubtaskRest(IssueIndexingService issueIndexingService){
	  this.issueIndexingService=issueIndexingService;
	}
	
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(final CreateCISubtaskModel[] insight, @Context HttpServletRequest request)
    {
      
      List<CreateCISubtaskModel> list=new ArrayList<CreateCISubtaskModel>();	 
	  String key =  "";
      issueManager = ComponentAccessor.getIssueManager();
	  if(insight!=null && insight.length>0){
        key = insight[0].getIssueKey();
		issue =issueManager.getIssueObject(key);
        user = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
    	for (int i=0;i<insight.length;i++) { 
    		String insightObj  = insight[i].getInsightObj();
    		String subject = insight[i].getSubject();
    		String owner = insight[i].getOwner();
    		String issuekey = insight[i].getIssueKey();
    		//createSubtask(subject+"( "+ insightObj +" )", owner,issuekey,insightObj);
			 logger.info("insightObj "+ insightObj);
    		 logger.info("subject "+ subject);
			 logger.info("owner "+ owner);
    		 logger.info("issue key "+ issuekey);
    		key = issuekey;
			//createSubtask(subject+"( "+ insightObj +" )", owner,issuekey,insightObj);
			CreateCISubtaskModel ciSubtaskModel = new CreateCISubtaskModel(insightObj,subject,owner,issuekey);
			list.add(ciSubtaskModel);
			createSubtask(subject+"( "+ insightObj +" )", owner,issuekey,insightObj);
    	} // end for loop
				
    	//createSubtaskTest("Testing ", "Firoz.Khan","CHG-689");
		//setReindex(issue);
		try{
    	issueManager.updateIssue(user,issue, EventDispatchOption.DO_NOT_DISPATCH, false);
		}catch(Exception e){
		   //System.out.println("issue update "+e.getMessage());
		  //  System.out.println();
		   e.printStackTrace();
		}
	}//end if
    	
    	return Response.ok(list).build();   	
    	
    }
     
      private void createSubtask(String subject, String owner,String key,String insightObj) {
      	SubTaskManager subTaskManager = ComponentAccessor.getSubTaskManager();
   		
   		Collection<Issue> subIssues= issue.getSubTaskObjects();
   		int flag = 0;
 		if(subIssues!=null){
		 for(Issue is : subIssues){	
			 if(is.getSummary().indexOf(insightObj)>0){
				 flag = 1;
				 break;
			 }
		 }
 		}
		 if(flag == 1){
			 return;
		 }
  		
      	 try{			
      		 			boolean b = ImportUtils.isIndexIssues();
  						ImportUtils.setIndexIssues(true);	
  						MutableIssue subTaskIssue = setIssueFields(issue, subject ,"" , owner);
  						Issue subTaskIssueObj = issueManager.createIssueObject(user, subTaskIssue);			
  						subTaskManager.createSubTaskIssueLink(issue, subTaskIssueObj, user);
  						//issue.store();
  						ImportUtils.setIndexIssues(b);				
						
  			
  			}catch(Exception e){
  				e.printStackTrace();
  			}
       }
   
     private MutableIssue  setIssueFields(Issue parentIssue,String name, String desc,String assignee){
 		ApplicationUser componentUser = ComponentAccessor.getUserManager().getUserByName(assignee);
 		IssueFactory  issueFactory = ComponentAccessor.getIssueFactory();
 		
 		MutableIssue issueObject = issueFactory.getIssue();
 		ApplicationUser user  = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();	
 				issueObject.setProjectObject(parentIssue.getProjectObject());
 				ProjectManager projectManager = ComponentAccessor.getProjectManager();
 				Project p = parentIssue.getProjectObject();
 				 Collection<IssueType> issueTypes = p.getIssueTypes();
 				 String stIssueTypeId = "";
 				for(IssueType issueType : issueTypes){
 					String typename = issueType.getName();
 					//logger.info("Type Name: "+ typename);
 					if(typename.equals("Configuration Item")){
 						stIssueTypeId = issueType.getId(); 
 						break;
 					}
 				}
 				issueObject.setIssueTypeId(stIssueTypeId); 
 				issueObject.setParentId(parentIssue.getId());
 				//issueObject.setAffectedVersions(parentIssue.getAffectedVersions());
 				issueObject.setSummary(name);
 				issueObject.setDescription(parentIssue.getDescription());
 				//issueObject.setLabels(parentIssue.getLabels());
 				issueObject.setReporter(user);			
 				issueObject.setAssignee(componentUser);
 		
 		
 		return issueObject;
 	}
     private void setReindex(MutableIssue mutableIssue){
			try {
				issueIndexingService.reIndex(mutableIssue);
			} catch (IndexException ie) {
				logger.debug("index issue" + ie.getMessage());
			}
    	}
}