
package com.atlassian.plugin.tutorial;

//import com.atlassian.greenhopper.service.ServiceOutcome;
//import com.atlassian.greenhopper.service.configuration.ConfigurationService;
//import com.atlassian.greenhopper.service.configuration.ConfigurationServiceImpl;
//import com.atlassian.greenhopper.service.sprint.Sprint;
//import com.atlassian.greenhopper.service.sprint.SprintManager;
//import com.atlassian.greenhopper.service.sprint.SprintManagerImpl;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.jira.util.velocity.VelocityRequestContextFactory;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.jira.web.util.OutlookDate;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.query.Query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.ofbiz.core.entity.GenericValue;
import org.ofbiz.core.entity.GenericEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * REST endpoint for days left in iteration gadget.
 *
 * @since v4.0
 */
@Path ("/days-left-in-iteration")
@AnonymousAllowed
@Produces ({ MediaType.APPLICATION_JSON })
public class DaysLeftInVersionResource
{

    static final int MILLISECONDS_IN_SEC = 1000;
    static final int SECONDS_IN_MIN = 60;
    static final int MINUTES_IN_DAY = 60;
    static final int HOURS_IN_DAY = 24;
    private static final ToStringStyle TO_STRING_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    private final VersionManager versionManager;
    private final JiraAuthenticationContext authenticationContext;
    private final SearchService searchService;
    private VelocityRequestContextFactory velocityRequestContextFactory;
//    private final ConfigurationServiceImpl configurationServiceImpl;

//    public DaysLeftInVersionResource(final SearchService searchService, final JiraAuthenticationContext authenticationContext, final VelocityRequestContextFactory velocityRequestContextFactory, final VersionManager versionManager, final ConfigurationServiceImpl configurationServiceImpl)
    public DaysLeftInVersionResource(final SearchService searchService, final JiraAuthenticationContext authenticationContext, final VelocityRequestContextFactory velocityRequestContextFactory, final VersionManager versionManager)
    {
        this.searchService = searchService;
        this.authenticationContext = authenticationContext;
        this.velocityRequestContextFactory = velocityRequestContextFactory;
        this.versionManager = versionManager;
//        this.configurationServiceImpl = configurationServiceImpl;
    }

    @GET
    @Path ("/getSampleJSON")
    public Response getSampleJSON1()
	{
    	String str = "{ \"cols\": [ { \"id\": \"\", \"label\": \"Sprint\", \"type\": \"string\" }, { \"id\": \"\", \"label\": \"Issue Burndown\", \"type\": \"number\" } ], \"rows\": [ { \"c\": [ { \"v\": \"Sprint 1\" }, { \"v\": 4 } ] }, { \"c\": [ { \"v\": \"Sprint 2\" }, { \"v\": 3 } ] }, { \"c\": [ { \"v\": \"Sprint 3\" }, { \"v\": 2 } ] }, { \"c\": [ { \"v\": \"Sprint 4\" }, { \"v\": 2 } ] }, { \"c\": [ { \"v\": \"Sprint 5\" }, { \"v\": 1 } ] } ] }";
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
		return Response.ok(str).cacheControl(cacheControl).build();
	}

    @GET
    @Path ("/getVersions")

    public Response getVersionsForProject(@QueryParam ("projectId") String projectIdString)
    {
        Long projectId = Long.valueOf(projectIdString.substring("project-".length()));
        List<Version> versions = getVersionList(projectId);

        

        final OutlookDate outlookDate = authenticationContext.getOutlookDate();
        long daysRemaining;
        List<VersionInfo> versionList = new ArrayList<VersionInfo>();

        String releaseDate;
        for (Version v : versions){
            releaseDate = formatDate(v.getReleaseDate());
            Project srcProj = v.getProjectObject();
            ProjectInfo targetProj = new ProjectInfo(srcProj.getId(), srcProj.getKey(), srcProj.getName());
            if("".equals(releaseDate)){
                daysRemaining = 0;
            }
            else {
                daysRemaining = calculateDaysLeftInVersion(v.getReleaseDate());
            }
            List<Issue> issuesList = getIssuesList(v);
            versionList.add(new VersionInfo(v.getId(),v.getName(), v.getDescription(),releaseDate,targetProj, daysRemaining, issuesList));
            getSprints(v);
			
        }

        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        return Response.ok(new VersionList(versionList)).cacheControl(cacheControl).build();

    }
   
    public static long calculateDaysLeftInVersion(Date targetDate){
        Date currentDate = new Date(System.currentTimeMillis());
        Date releaseDate = targetDate; //TO DO need to write convert string to date FUNCTION
        long currentTime = currentDate.getTime();
        long targetTime = releaseDate.getTime();

        long remainingTime = targetTime - currentTime;  //remaining time in milliseconds
        long hoursRemaining = remainingTime/(MILLISECONDS_IN_SEC* SECONDS_IN_MIN * MINUTES_IN_DAY);
        long daysRemaining = remainingTime/(MILLISECONDS_IN_SEC* SECONDS_IN_MIN * MINUTES_IN_DAY * HOURS_IN_DAY); //
        if(hoursRemaining % HOURS_IN_DAY > 0 ) {
            daysRemaining++; //the days remaining includes today should be updated for different time z
        }
        return daysRemaining;
    }

    public  String formatDate(Date date){
        if(date == null) {
            return "";
        } else {
            OutlookDate outlookDate = authenticationContext.getOutlookDate();
            return outlookDate.formatDMY(date);
        }
    }
    public List<Version> getVersionList(Long projectId)
    {
    	List<Version> versions = versionManager.getVersions(projectId);
//    	List<Version> versions = new ArrayList<Version>();
//        versions.addAll(versionManager.getVersionsUnreleased(projectId, false));

        Collections.sort(versions, new Comparator<Version>()
        {
            public int compare(Version v1, Version v2)
            {
                if(v1.getReleaseDate()== null)
                {
                    return 1;
                }
                else if (v2.getReleaseDate() == null)
                {
                    return 0;
                }
                else {
                    return v1.getReleaseDate().compareTo(v2.getReleaseDate());

                }
            }
        });
        return versions;
    }
    ///CLOVER:OFF


    /**
     * The data structure of the days left in iteration
     * <p/>
     * It contains the a collection of versionData about all the versions of a particular project
     */
    @XmlRootElement
    public static class VersionList
    {
        @XmlElement
        Collection<VersionInfo> versionsForProject;

        @SuppressWarnings ({ "UnusedDeclaration", "unused" })
        private VersionList()
        { }

        VersionList(final Collection<VersionInfo> versionsForProject)
        {
            this.versionsForProject = versionsForProject;
        }

        public Collection<VersionInfo> getVersionsForProject()
        {
            return versionsForProject;

        }

    }
    @XmlRootElement
    public static class ProjectInfo
    {

        @XmlElement
        private long id;

        @XmlElement
        private String key;

        @XmlElement
        private String name;

        @SuppressWarnings ({ "UnusedDeclaration", "unused" })
        private ProjectInfo()
        {}

        ProjectInfo(final long id, String key, String name)
        {
            this.id = id;
            this.key = key;
            this.name = name;
        }

        public long getId()
        {
            return id;
        }

        public String getKey()
        {
            return key;
        }

        public String getName()
        {
            return name;
        }

        @Override
        public int hashCode()
        {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public boolean equals(final Object o)
        {
            return EqualsBuilder.reflectionEquals(this, o);
        }

        @Override
        public String toString()
        {
            return ToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
        }
    }
    
    /* New Class Added*/
    @XmlRootElement
    public static class Issue {

    	@XmlElement
    	private String issueName;
    	
    	@XmlElement
    	private String status;
    	
    	@XmlElement
    	private String priority;
    	
    	  @SuppressWarnings ({ "UnusedDeclaration", "unused" })
          private Issue()
          {}

    	Issue(final String issueName1,final String status1, final String priority1){
    		this.issueName = issueName1;
    		this.status = status1;
    		this.priority = priority1;
    	}

    	public String getIssueName() {
    		return issueName;
    	}

    	
    	public String getStatus() {
    		return status;
    	}

    	
    	public String getPriority() {
    		return priority;
    	}

    	
    	 @Override
         public int hashCode()
         {
             return HashCodeBuilder.reflectionHashCode(this);
         }

         @Override
         public boolean equals(final Object o)
         {
             return EqualsBuilder.reflectionEquals(this, o);
         }

         @Override
         public String toString()
         {
             return ToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
         }


    }

    
    @XmlRootElement
    public static class VersionInfo
    {
        @XmlElement
        private long id;

        @XmlElement
        private String name;

        @XmlElement
        private String description;

        @XmlElement
        private String releaseDate;

        @XmlElement
        private long daysRemaining;

        @XmlElement
        private boolean isOverdue;

        @XmlElement
        private ProjectInfo owningProject;

        @XmlElement
        private List<Issue> issuesList;
        
        @SuppressWarnings ({ "UnusedDeclaration", "unused" })
        private VersionInfo()
        { }

        VersionInfo(final long id, final String name, final String description, final String releaseDate,  final ProjectInfo owningProject, final long daysRemaining, final List<Issue> issuesList)
        {
            this.id = id;
            this.name = name;
            this.description = description;
            this.releaseDate = releaseDate;
            this.isOverdue = isOverdue();
            this.owningProject = owningProject;
            this.daysRemaining = daysRemaining;
            this.issuesList = issuesList;

        }

        public long getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String getDescription()
        {
            return description;
        }

        public String getReleaseDate()
        {
            return releaseDate;
        }

        public long getDaysRemaining()
        {
            return daysRemaining;
        }

        public boolean isOverdue ()
        {
            if (daysRemaining < 0 )
            {
                isOverdue = true;
            }
            else
            {
                isOverdue = false;
            }
            return isOverdue;
        }

        public ProjectInfo getOwningProject()
        {
            return owningProject;
        }


        public List<Issue> getIssuesList(){
        	return issuesList;
        }

    }
/* New Method Added*/
/**
     * Retrieve a list of all the issues in the current project. Note that several of these objects are passed via
     * dependency injection as constructor parameters.
     *
     * @return list of Issue objects
     */
    public List<com.atlassian.jira.issue.Issue> getAllIssuesInCurrentProject(String projId)
    {
        final  JqlQueryBuilder builder = JqlQueryBuilder.newBuilder();
        builder.where().project(projId);
        Query query = builder.buildQuery();
        try
        {
            final SearchResults results = searchService.search(authenticationContext.getUser(),
                    query, PagerFilter.getUnlimitedFilter());
            return results.getIssues();
        }
        catch (SearchException e)
        {
            e.printStackTrace();
        }
 
        return Collections.emptyList();
    }
	
public List<Issue> getIssuesList(Version version){
    	List<Issue> issuesList = new ArrayList<Issue>();
    	try{	
    		Collection<com.atlassian.jira.issue.Issue> issueList1 = versionManager.getIssuesWithAffectsVersion(version);
    		for(com.atlassian.jira.issue.Issue issue : issueList1){
    			com.atlassian.jira.project.Project proj = issue.getProjectObject();	
    			ApplicationUser user1 = issue.getAssigneeUser();
//    			System.out.println("configurationServiceImpl----------------->" + configurationServiceImpl);
//    			com.pyxis.greenhopper.jira.configurations.ProjectConfiguration pc = configurationServiceImpl.getConfiguration(user1, proj);
//    			System.out.println("Version Start Date ---" + pc.getVersionStartDate(version));
//    			System.out.println("Version End date--------------------->" + pc.getVersionEndDate(version));
    		}
    	    Collection<Long> issueIds = versionManager.getIssueIdsWithAffectsVersion(version);
    		
    		for(Long issueId : issueIds){

    			/*com.atlassian.jira.issue.Issue issueObj = (com.atlassian.jira.issue.Issue) genericValue;
    			com.atlassian.jira.issue.status.Status st = issueObj.getStatusObject();
    			System.out.println("-----------Status Desc===> "+st.getDescription());*/
                Issue mutableIssue = (Issue)ComponentAccessor.getIssueManager().getIssueObject(issueId);
    			//String summaryInfo = genericValue.getString("summary");
    			//String statusInfo = genericValue.getString("status");
    			//String priorityInfo = genericValue.getString("priority");
    			//System.out.println("--------summary :" + summaryInfo + "  & Status=" + statusInfo);
    			//Issue issue = new Issue(summaryInfo, statusInfo, priorityInfo);
    			issuesList.add(mutableIssue);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    		return issuesList;
    }	
public void getSprints(Version v) {

	HttpClient client = new HttpClient();
	
	GetMethod method = new GetMethod("http://localhost:2990/jira/rest/greenhopper/1.0/sprints/1?os_username=admin&os_password=admin");
	
	try {
	  // Execute the method.
	  int statusCode = client.executeMethod(method);
	  System.out.println(method.getResponseBodyAsString());
	  JSONObject json = new JSONObject(method.getResponseBodyAsString());
	
	  if (statusCode != HttpStatus.SC_OK) {
		  System.out.println(method.getStatusText());
	    //log.error("REST call failed: " + method.getStatusLine());
	    return;
	  }
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {}
}

/*Using GreenHopper API ---Sprint Info*/

public void display(){
	System.out.println("----Sprint info--------------");
//	SprintManagerImpl manager = new SprintManagerImpl();
//	ServiceOutcome sprint = (ServiceOutcome)manager.getSprint(1);
	System.out.println("123==>");	
}

}
