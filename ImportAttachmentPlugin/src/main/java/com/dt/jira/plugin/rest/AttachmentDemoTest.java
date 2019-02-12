package com.dt.jira.plugin.rest;

import java.io.File;import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.JiraException;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.AttachmentManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.attachment.Attachment;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.history.ChangeItemBean;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.util.AttachmentException;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.model.DRSProject;
import com.dt.jira.plugin.model.FileAttachment;
import com.dt.jira.plugin.utils.CommonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * A resource of message.
 */
@Path("/attachmentdemotest")
public class AttachmentDemoTest {

    @GET
    @AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey)
    {
       String jqlQuery = "project = '" + projectkey+"'";     
       String message = "sucess";
       long totDef = 0;
       SearchResults results =null;
		try {
			results = CommonService.getSerarchResults(jqlQuery);
			
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
		}
		System.out.println("jqlQuery: "+jqlQuery);
       if(results!=null){
    	   totDef = results.getIssues()!=null? results.getIssues().size(): 0;
       }
       List<Issue> issues = null;
       if(results!=null)
       		issues = results.getIssues();
    
       Issue issue1 = null;
       CustomField  externalIssueId= CommonService.getCustomFieldManager().getCustomFieldObjectByName("External issue ID");
      
       String filePath = "/opt/app/var/atlassian/application-data/jira/import/attachments/"+projectkey+"/";
       Map fileAttachmentMap = getFileAttachmentInfo();
       ArrayList<AttachmentDemoModel> output = new ArrayList<AttachmentDemoModel>();
        if(issues!= null && issues.size() > 0)
        {    
       	
   	   	  for(Issue issue: issues){ 
   	   		  String key = issue.getKey();
   	   		  //if(key.equals("DCID-1334"))
   	   		 // {
   	   			 // issue1 = issue;
   	   			  //String extIssueKey = (String) issue.getCustomFieldValue(externalIssueId);
   	   			  File folder = new File(filePath+key);
   	   			  File[] listOfFiles = folder.listFiles();
   	   			  if(listOfFiles!=null){
		   	   		for (int i = 0; i < listOfFiles.length; i++) {
		   	         if (listOfFiles[i].isFile()) {
		   	           
		   	           String referenceid =  listOfFiles[i].getName();
		   	           FileAttachment fileAttachment =(FileAttachment) fileAttachmentMap.get(referenceid);
		   	           if(fileAttachment!=null){
			   	           String fileName = fileAttachment.getFilename();
			   	           String mimeType = fileAttachment.getMimetype();
						   
			   	           //crateAttachment(listOfFiles[i],fileAttachment.getFilename(),fileAttachment.getMimetype(),issue);
						   message = " Issue Key: "+ key +" file name: "+ fileName;
						   output.add(new AttachmentDemoModel(message));
						   System.out.println(message);
		   	           } else {
		   	        	   System.out.println(" Issue Key: "+ key +" reference is missing for the given key "+referenceid);
		   	           }
		   	         } else if (listOfFiles[i].isDirectory()) {
		   	           //System.out.println("Directory " + listOfFiles[i].getName());
		   	         }
		   	       }
   	   			  } else {
   	   				  System.out.println("external Issue Key: "+ key+" folder is missing for the given key ");
   	   			  }
   	   			 
   	   			 // break;
   	   		  //}
   	   	  }
        }
        if(output.size()<1)
        	 output.add(new AttachmentDemoModel(message));
       return Response.ok(output).build();
    }
    private void crateAttachment(File file, String filename,String mimeType,Issue issue){
    	
        User auth = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
       
        
        AttachmentManager attachmentManager = ComponentAccessor.getAttachmentManager();
       List<Attachment> attachments  = attachmentManager.getAttachments(issue);
       if(attachments!=null){

        try {
     	   ChangeItemBean bean  = attachmentManager.createAttachment(file, filename,   mimeType,  auth,  issue);
 		
	 	} catch (AttachmentException e) {
	 		System.out.println("key: "+ issue.getKey() +" failed "+ e.getMessage());
	 	}
       }
    }
    private Map getFileAttachmentInfo(){
    	String imagedumpPath = "/opt/app/var/atlassian/application-data/jira/import/IMAGEDUMPMETADATA.xml";
    	Map<String, FileAttachment> map = new HashMap<String, FileAttachment>();
    	  JAXBContext jc=null;
          Unmarshaller unmarshaller = null;
          DRSProject drsProject = null;
  		try {
  			jc = JAXBContext.newInstance(DRSProject.class);    
  			unmarshaller = jc.createUnmarshaller();
  			File xml = new File(imagedumpPath);
  			drsProject = (DRSProject) unmarshaller.unmarshal(xml);
  		} catch (JAXBException e) {
  			e.printStackTrace();
  		}
        
         
      	List<FileAttachment> list2 = drsProject.getFileAttachment();
      	Iterator<FileAttachment> it2 = list2.iterator();
      	while(it2.hasNext()){
      		FileAttachment  fileAttachment =(FileAttachment) it2.next();
      		//System.out.println("id: "+fileAttachment.getId() +" fileName: "+ fileAttachment.getFilename() + " mime type: " + fileAttachment.getMimetype());
      		map.put(fileAttachment.getId(), fileAttachment);
      	}
      	return map;
    }
}