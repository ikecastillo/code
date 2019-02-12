package com.dt.jira.plugin.innotas.rest;

import java.util.ArrayList;import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.JiraException;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.dt.jira.plugin.innotas.ao.InnotasCache;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;


/**
 * A resource of message.
 */
@Path("/syncallissues")
public class SyncInnotasDetails {
	private final InnotasCacheService cacheService;
	CustomField cf_inbu = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Business Unit");
	CustomField cf_inProgram = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Program");
	CustomField cf_inProjId = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Project ID");
	CustomField cf_inProjName = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Project Name");
	CustomField cf_insubbu = ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Innotas Sub Business Unit");
	public SyncInnotasDetails(InnotasCacheService cacheService) {
		this.cacheService = cacheService;
	}

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage(@QueryParam("projectkey") String projectkey)
    {
    	try{
    	long totValidDef = 0;
    	SearchResults results = null;
    	StringBuffer jql = new StringBuffer("project in (" + projectkey + ") ");
    	SearchService searchService  = ComponentAccessor.getComponent(SearchService.class);
    	 JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
		
		 final SearchService.ParseResult parseResult= searchService.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
		  if (parseResult.isValid())
		    {         
		          results = searchService.search(authenticationContext.getLoggedInUser(),parseResult.getQuery(), PagerFilter.getUnlimitedFilter());
		          totValidDef = results.getIssues().size();
		          System.out.println("total defects : "+totValidDef);
		    }
    	
    		 if(results!=null)
    				totValidDef = results.getIssues()!=null? results.getIssues().size(): 0;    				
    				 if (results!=null &&  results.getIssues()!=null && results.getIssues().size()>0)
    			      {
    			         List<Issue> issues = results.getIssues();
    			         for(Issue issue: issues){
    			        	 MutableIssue mutableIssue = ComponentAccessor.getIssueManager()
    			 					.getIssueObject(issue.getId());
    			 			String releseIds = getReleaseId(issue);
    			 			Project p = issue.getProjectObject();
    			 			Map<String, Object> imapList = cacheService
    			 					.getInnotasProjectsByIDs(p.getKey(), releseIds);
    			 			if(imapList!=null)
    			 				System.out.println("Sync up all issues- project list size:  " + imapList.size());
    			 			InnotasCache ic = null;
    			 			List<Option> inproopt = new ArrayList<Option>();
    			 			List<Option> inbuopt = new ArrayList<Option>();
    			 			List<Option> inProjIdopt = new ArrayList<Option>();
    			 			List<Option> inProjNameopt = new ArrayList<Option>();
    			 			List<Option> insubbuopt = new ArrayList<Option>();
    			 			
    			 			if(imapList!=null){
    			 				for (Object icache : imapList.values()) {
    			 					ic = (InnotasCache) icache;
    			 	
    			 					System.out.println("Sync up all issues-  Business Unit ID:  "
    			 							+ ic.getBuId());
    			 					System.out.println("Sync up all issues -  Business Unit Name:  "
    			 							+ ic.getBuName());
    			 					System.out
    			 							.println("Sync up all issues-  Innotas Project Projectid:  "
    			 									+ ic.getProjectId());
    			 					System.out.println("Sync up all issues -  Innotas Project Name:  "
    			 							+ ic.getProjectName());
    			 					System.out.println("Sync up all issues -  Innotas Prog Name:  "
    			 							+ ic.getProgramName());
    			 					System.out.println("Sync up all issues -  Innotas Sub BU Name:  "
    			 							+ ic.getSbuName());
    			 	
    			 					String innbuName = ic.getBuName();
    			 					String innProjId = ic.getProjectId();
    			 					String innProjectName = ic.getProjectName();
    			 					String innProgName = ic.getProgramName();
    			 					String innsubBuName = ic.getSbuName();
    			 	
    			 					
    			 					// build  options for innotas program name				
    			 					Option opt_innProgName = getOption(cf_inProgram, innProgName,issue);
    			 					inproopt.add(opt_innProgName);
    			 	
    			 					//  build  options for innotas business unit
    			 					Option opt_innbuName = getOption(cf_inbu, innbuName,issue);
    			 					inbuopt.add(opt_innbuName);
    			 	
    			 					//  build  options for innotas project id
    			 					Option opt_innProjId = getOption(cf_inProjId, innProjId,issue);
    			 					inProjIdopt.add(opt_innProjId);
    			 					
    			 					// build  options for innotas project name
    			 					Option opt_innProjectName = getOption(cf_inProjName, innProjectName,issue);
    			 					inProjNameopt.add(opt_innProjectName);
    			 					
    			 					// build  options for innotas sub bu name
    			 					Option opt_innsubBuName = getOption(cf_insubbu, innsubBuName,issue);
    			 					insubbuopt.add(opt_innsubBuName);
    			 				}// for end
    			 			}
    			 			cf_inProgram.createValue(mutableIssue, inproopt);
    			 			cf_inbu.createValue(mutableIssue, inbuopt);
    			 			cf_inProjId.createValue(mutableIssue, inProjIdopt);
    			 			cf_inProjName.createValue(mutableIssue, inProjNameopt);
    			 			cf_insubbu.createValue(mutableIssue, insubbuopt);

    			 			System.out.println("insubbuopt : " + insubbuopt);
    			 			System.out.println("inuopt : " + inbuopt);
    			 			System.out.println("inproopt : " + inproopt);
    			 			System.out.println("inProjNameopt : " + inProjNameopt);
    			 			System.out.println("inProjIdopt : " + inProjIdopt);

    			 			setReindex(mutableIssue);
    			         }
    			      }
    				
    	}catch(JiraException je){
    		je.printStackTrace();
    	}
       return Response.ok(new InnotasJiraReleasesSyncModel("Sync up all issues button")).build();
    }
    private Option getOption(CustomField customField, String value,Issue issue){
   	 Option o = null;
   	 OptionsManager optionManager = ComponentAccessor
   				.getOptionsManager();
   	 FieldConfig fieldConfig = customField.getRelevantConfig(issue);
   		Options opts = optionManager.getOptions(fieldConfig);
   		Iterator<Option> it = opts.iterator();
   		while (it.hasNext()) {
   			Option option = it.next();
   			if (option.getValue().equals(value)) {
   				 o = optionManager
   						.findByOptionId(option.getOptionId());				
   				break;
   			}			
   		}
   		return o;
    }
    private String getReleaseId(Issue issue) {
		Collection<Version> versions = issue.getFixVersions();
		String releseIds = "";
		int intCounter = 1;
		StringBuffer sb = new StringBuffer();
		if (versions!=null && versions.size() > 0){
			for (Version v : versions) {
				// strVersion[intCounter] = v.getId().toString();
				intCounter = intCounter + 1;
				sb.append(v.getId());
				sb.append(",");
			}
		}		
		
		if (sb != null && sb.length() > 0) {
			releseIds = sb.substring(0, sb.length() - 1);
		}

		return releseIds;
	}
    private void setReindex(MutableIssue mutableIssue) throws JiraException{
   	   			ComponentAccessor.getIssueIndexManager().reIndex(mutableIssue);
    }
}