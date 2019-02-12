package com.dt.jira.plugin.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
@Path("/addcomments")
public class AddCommentsDemo {
	
	 
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
			
	      if(issues!= null && issues.size() > 0)
	        {    	   	 
	   	   		 CommentManager  commentManager = ComponentAccessor.getCommentManager();
				IssueManager issueManager = ComponentAccessor.getIssueManager();
				
				
				String author = CommonService.getJiraAuthenticationContext().getLoggedInUser().getDisplayName();
				String body = "";
			   
				
				Iterator it2 = items.iterator();
				String recentKey ="";
				while(it2.hasNext()){
					Item  item =(Item) it2.next();
					//System.out.println("number"+ item.getTitle());
					String issueKey = getIssueKey(item.getTitle()); 	
					Issue issue = issueManager.getIssueObject(issueKey);
					if(issue!=null) {
					
					
						//System.out.println("issueKey: "+ issueKey);
						//System.out.println("Author: "+ item.getAuthor());
						String migrationCom = " Comments added during migration \n "  ;
						String lineItem = " -------------------------------------\n " ;
						String authorStr = " Author: "+ item.getAuthor() +"\n"; 
						String posted = " Posted On: "+ item.getPubDate() +"\n "; 
						//System.out.println("posted : "+ item.getPubDate());
						//System.out.println("desc: "+ formatDisc(item.getDescription()));
						body = migrationCom + lineItem+  authorStr + posted + formatDisc(item.getDescription());
						try{
						commentManager.create(issue, author, body, false);
						}catch(Exception e){
							message = "SQL exception or Data exception on  the comments on the "+ issueKey;
							System.out.println(e.getMessage());
							outputModel.add(new SyncCommentsModel(message));
						}
						message = "Successfully created the comments for the "+ issueKey;
						System.out.println(message);
						outputModel.add(new SyncCommentsModel(message));
						recentKey =  issueKey;
						
					}	
					
				}
	   	   	 
	        }
		
		
		        
		        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = e.getMessage();
		}
		//System.out.println("jqlQuery: "+jqlQuery);
    
       return Response.ok(outputModel).build();
    }

private  String getIssueKey(String itemKey){	
	
	int start = itemKey.indexOf('[');
	int end = itemKey.indexOf(']');
	String key = itemKey.substring(start+1,end);
	
	return key;
}
private  String formatDisc(String desc){
	desc = desc.replaceAll("<br/>", "");
	
	int startIndex = desc.indexOf("<table>");
	int endIndex = desc.indexOf("</table>");
	String subDesc = desc.substring(0, startIndex);
	//System.out.println(" after format desc: "+ subDesc);
	return subDesc;
}

}