package com.dt.jira.plugin.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.model.Channel;
import com.dt.jira.plugin.model.Item;
import com.dt.jira.plugin.model.Rss;
import com.dt.jira.plugin.utils.CommonService;

/**
 * A resource of message.
 */
@Path("/deletecomments")
public class DeleteCommentsDemo {
	
	 
    @GET
    @AnonymousAllowed
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey) throws Exception
    {
       String jqlQuery = "project = '" + projectkey+"'";     
       String message = "sucess";
       //long totDef = 0;
       SearchResults results =null;
		try {
			results = CommonService.getSerarchResults(jqlQuery);
		} catch (JiraException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		System.out.println("jqlQuery: "+jqlQuery);
		 String filePath = "/opt/app/var/atlassian/application-data/jira/import/RSS XML.xml";
		 JAXBContext jc = JAXBContext.newInstance(Rss.class);
	     Unmarshaller unmarshaller = jc.createUnmarshaller();
	     File xml = new File(filePath);
	     Rss cust = (Rss) unmarshaller.unmarshal(xml);
	    
	    
	     Channel  channel = cust.getChannel();
	       List<Item> items = channel.getItem();
	       System.out.println("issues : "+items.size());
	       ArrayList<SyncCommentsModel> outputModel = new ArrayList<SyncCommentsModel>();
	     try{ 
	      List<Issue> issues = null;
	      if(results!=null)
      		issues = results.getIssues();

	   		 CommentManager  commentManager = ComponentAccessor.getCommentManager();
			IssueManager issueManager = ComponentAccessor.getIssueManager();
			
	      if(issues!= null && issues.size() > 0)
	        {  
	    	  
	    	  for(Issue issue: issues){
	    		  try{
						
						List<Comment> commentList = commentManager.getComments(issue);
						if(commentList!=null && commentList.size()>0)
						for(Comment c: commentList){
							commentManager.delete(c);
							message = "delete exception  on  the comments "+ issue.getKey();
							System.out.println(message);
							outputModel.add(new SyncCommentsModel(message));
						}
						
						}catch(Exception e){
							message = "delete exception  on  the comments "+ issue.getKey();
							System.out.println(message);
						}
	    	  }
	    	  
				
				
	   	   	 
	        }
		
		
		        
		        
		} catch (Exception e) {		
			e.printStackTrace();
			message = e.getMessage();
		}
		//System.out.println("jqlQuery: "+jqlQuery);
	     if(outputModel!=null && outputModel.size()<1){
	    	 outputModel.add(new SyncCommentsModel(message));
	     }
    
       return Response.ok(outputModel).build();
    }
}