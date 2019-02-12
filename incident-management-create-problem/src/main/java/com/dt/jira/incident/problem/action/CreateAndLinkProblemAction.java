package com.dt.jira.incident.problem.action;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueFactory;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.link.IssueLinkManager;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.issue.link.LinkCollection;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.incident.problem.ao.ProblemFieldMap;
import com.dt.jira.incident.problem.service.FieldMappingService;
import com.dt.jira.incident.problem.utils.IssueFieldUtils;
import com.dt.jira.incident.problem.utils.ProblemUtils;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.fields.config.FieldConfig;


/**
* Create the problem from incident manually from More dropdown 
*
*/
public class CreateAndLinkProblemAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(CreateAndLinkProblemAction.class);
    
    private Long id;
    private String key;
    private final WebResourceManager webResourceManager;
    private final IssueService issueService;
    private final JiraAuthenticationContext authenticationContext;
	private final IssueLinkManager issueLinkManager;
	private final ApplicationProperties applicationProperties;
	private final FieldMappingService fieldMapService;
	private final IssueFactory issueFactory;
	private final IssueManager issueManager;
	private final IssueFieldUtils issueFieldUtils;
	/**
     * Constructor
     * @param applicationProperties the ApplicationProperties to be injected
     * @param issueService the IssueService to be injected
     * @param authenticationContext the JiraAuthenticationContext to be injected
     * @param webResourceManager the WebResourceManager to be injected
     * @param issueLinkManager the IssueLinkManager to be injected
     */
    public CreateAndLinkProblemAction(ApplicationProperties applicationProperties,
    		IssueService issueService, 
    		JiraAuthenticationContext authenticationContext, 
    		WebResourceManager webResourceManager,
			IssueLinkManager issueLinkManager,
			FieldMappingService fieldMapService,
			IssueFactory issueFactory,
			IssueManager issueManager,
			IssueFieldUtils issueFieldUtils)
    {
    	this.applicationProperties = applicationProperties;
        this.issueService = issueService;
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
		this.issueLinkManager= issueLinkManager;
		this.fieldMapService = fieldMapService;
		this.issueFactory = issueFactory;
		this.issueManager = issueManager;
		this.issueFieldUtils = issueFieldUtils;
    }
    
    protected void doValidation()
    {
        includeResources();      

    }
 
    @Override
    public String execute() throws Exception {
        Issue issueParent = getIssueObject();
        
        includeResources();
        
		IssueType issueType		=	issueParent.getIssueTypeObject();
		CustomFieldManager cfm	=	ComponentAccessor.getCustomFieldManager();
		
		// for pre-populate incident details 		
		CustomField incidentKeyCustomField 			= 	cfm.getCustomFieldObjectByName("Incident Key");

		if( (issueType.getName().equalsIgnoreCase("Incident") || issueType.getName().equalsIgnoreCase("Outage") )
				&& getProblemTicketLinkedIssueCount(issueParent,incidentKeyCustomField)==0
				&& (String) issueParent.getCustomFieldValue(incidentKeyCustomField)==null){
	         	CustomField startTimeCustomField = cfm.getCustomFieldObjectByName("Problem Start");
				CustomField severiryCustomField = cfm.getCustomFieldObjectByName("Severity");
				@SuppressWarnings("unchecked")
	            Project prbProject = ComponentAccessor.getProjectManager().getProjectObjByKey("PRB");
				
				Date currentDate = new Date();
				long currentTime = currentDate.getTime();
				
				String pbrIssuTypeId = getProblemIssueTypeId(prbProject);
				 // Create the new issue object	
	         	 MutableIssue issueObject = issueFactory.getIssue();
				 issueObject.setProjectId(prbProject.getId());
				 issueObject.setIssueTypeId(pbrIssuTypeId);
				 issueObject.setParentId(issueParent.getId());
				 issueObject.setStatusId("1");
				 issueObject.setCustomFieldValue(startTimeCustomField, (Object)new Timestamp(currentTime));

				 // set subtask attributes
				 List<ProblemFieldMap> dbFields = fieldMapService.findAllByParentAndChildIssue(issueType.getName(),"Problem");
			     ApplicationUser user=getUserManager().getUserByName(authenticationContext.getLoggedInUser().getName());
				 if(dbFields!=null) {
			            for(ProblemFieldMap fieldMap:dbFields) {
							Object parentCustomFieldValue = issueFieldUtils.getCustomFieldValueFromIssue(issueParent, fieldMap.getJiraField(), true);
							issueFieldUtils.setCustomFieldValue(user, issueObject, fieldMap.getMappingField(), parentCustomFieldValue);
							/* In case Problem is created from incident manually from more link when the incident is pre resolve status -
							* Field 'Problem Start' will become null as it is copied from  incident end field which is defined in field mapping */
							if(issueObject.getCustomFieldValue(startTimeCustomField) == null){
								issueObject.setCustomFieldValue(startTimeCustomField, (Object)new Timestamp(currentTime));
							}
			            }
			        }
				 
				 issueObject.setCustomFieldValue(incidentKeyCustomField, issueParent.getKey());
				  /* Special use case for new cascade field Solution Groups - Products: Due to that incident and problem having two different contexts. Options can not  be copied from incident to problem*/
				 CustomField solutionProductsCustomField = cfm.getCustomFieldObjectByName("Solution Groups - Products");
				 
				 Object value=issueObject.getCustomFieldValue(solutionProductsCustomField);
				 List<Option> castObj = (List<Option>) value;
				 List<Option> replaceOptions=replaceCascadeSelectOption(castObj);
				 issueObject.setCustomFieldValue(solutionProductsCustomField,replaceOptions);				 	
				
				 /* Create Incident Report Subtask and link with Incident */
				 Issue createResult = issueManager.createIssueObject(authenticationContext.getLoggedInUser(), issueObject);
		    
					/* Validation before create the issue
		         	IssueService.CreateValidationResult createValidationResult = issueService.validateCreate(authenticationContext.getLoggedInUser(), issueInputParameters);
			         if (!createValidationResult.isValid())
			         {
			        	 log.info("**********Error while validate problem ticket**********"+createValidationResult.getErrorCollection());
			             this.addErrorCollection(createValidationResult.getErrorCollection());
			             messages= createValidationResult.getErrorCollection().toString();
			             addErrorMessage(messages);
			             return ERROR;
			         }
		            // This should validate whether the user is able to create the issue
			         IssueResult createResult = issueService.create(authenticationContext.getLoggedInUser(), createValidationResult);		
					
			         if (!createResult.isValid())
			         {
			        	 log.info("**********Error while creating problem ticket**********"+createValidationResult.getErrorCollection());
			        	 messages= createValidationResult.getErrorCollection().toString();
			             this.addErrorCollection(createResult.getErrorCollection());
			             addErrorMessage(messages);
			             return ERROR;
			         } */
		        
		        IssueLinkTypeManager issueLinkTypeManager = (IssueLinkTypeManager) ComponentAccessor.getComponentOfType(IssueLinkTypeManager.class);
		        IssueLinkType issueLinkType=issueLinkTypeManager.getIssueLinkType(10000L);  ///Issue is linked under "is blocked by" - 10000L  
		        issueLinkManager.createIssueLink(createResult.getId(), issueParent.getId(), issueLinkType.getId(), null, authenticationContext.getLoggedInUser());
		        return SUCCESS;
			
	        }else{
	 		        messages= "Problem Ticket is already created for "+issueParent.getKey();
					addErrorMessage(messages);		       
			        return ERROR;		       
	        }
		
    }
    
      /**
	   * Special use case for new cascade field Solution Groups - Products: Due to that incident and problem having two different * contexts. Options can not  be copied from incident to problem
	   */
    
    private List<Option> replaceCascadeSelectOption(List<Option> castObj){
    	List<Option> replaceOptionList=null;
    	if(castObj!=null){
    		Options solGroupProductsOptions=getSolutionGroupOptionsOfProblem();
    		int i=0;
    		Option replaceOption=null;
    		replaceOptionList=new ArrayList<Option>();
    		for(Option opt:castObj){
    			if(i==0){
    				replaceOption=getSolutionGroupOfProblem(opt.getValue(),solGroupProductsOptions.getRootOptions());
    				if(replaceOption!=null){
    					replaceOptionList.add(i,replaceOption);
    				}else{
    					replaceOptionList.add(i,opt);
    				}
    			}else{
    				if(replaceOption!=null){
    					replaceOption=getSolutionGroupOfProblem(opt.getValue(),replaceOption.getChildOptions());
    					if(replaceOption!=null){
        					replaceOptionList.add(i,replaceOption);
        				}else{
        					replaceOptionList.add(i,opt);        					
        				}
    			}
    				
    			}
    			i++;
    		}
    		
    	}
		return replaceOptionList;
    }
    
    /**
     * Gets number of Problem Ticket is linked with incident which have same incident key as parent key
     *
     * @param parent the Issue
     * @param incidentKeyCustomField the CustomField
     * @return the int
     */
    public int getProblemTicketLinkedIssueCount(Issue parent,CustomField incidentKeyCustomField){
	    int count = 0;   
	    log.info("**********problem Already Linked To Parent Ticket Count parent issue:" + parent.getKey());
	    LinkCollection linkCollection = issueLinkManager.getLinkCollection(parent, authenticationContext.getLoggedInUser());
		Collection<Issue> allLinkedIssues= linkCollection.getAllIssues();
		log.info("**********all Linked Issues size:" +allLinkedIssues.size());
		if(allLinkedIssues.size()==0){
			log.info("**********No problem Ticket linked with parent issue");
			return count;
		}else{
			for(Issue linkedissue : allLinkedIssues){
				log.info("**********problem Already Linked To Parent Ticket Count linked issue:" + linkedissue.getKey());
				log.info("**********problem Already Linked To Parent Ticket Count incidentKeyCustomField:" + linkedissue.getCustomFieldValue(incidentKeyCustomField));
				if(linkedissue.getIssueTypeObject().getName().equalsIgnoreCase("Problem") 
						&& linkedissue.getCustomFieldValue(incidentKeyCustomField)!=null 
						&& !linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase("") 
						&& linkedissue.getCustomFieldValue(incidentKeyCustomField).toString().equalsIgnoreCase(parent.getKey())){
					    log.info("**********linked issue:" + linkedissue.getKey() +" **********having same incident key:" +linkedissue.getCustomFieldValue(incidentKeyCustomField).toString() +" **********as its parent key:" +parent.getKey());
					    count++;
				}
			}
		  return count;
		}
    }
    
    private Options getSolutionGroupOptionsOfProblem(){    	
    	Project project = ComponentAccessor.getProjectManager().getProjectObjByKey("PRB");    	
    	CustomField customFieldSolution = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Solution Groups - Products" );
		//reverted back the context related change to previous state
		//FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project, changeIssueType));
    	FieldConfig fieldConfigSolution = customFieldSolution.getRelevantConfig(new IssueContextImpl(project.getId() , getProblemIssueTypeId(project)));
		
		OptionsManager optionsManager = ComponentAccessor.getComponentOfType(OptionsManager.class); 		
		Options solutionGroup = optionsManager.getOptions(fieldConfigSolution);	
		return 	solutionGroup;
    }
    
    
    private Option getSolutionGroupOfProblem(String solGroup,List<Option> solutionGroup){
    	if(solutionGroup != null && (!solutionGroup.isEmpty())){
			for(Option solopt : solutionGroup){ 				
				if(solopt.getValue().equals(solGroup)){
				return 	solopt;
				}
				
			}
		}
		 
		log.info("no option found and method end");	
		return 	null;
    }
        
   
    
	 /**
     * Gets the Problem Ticket Issue Type Id
     *
     * @param project the Project
     * @return the String
     */
    public String getProblemIssueTypeId(Project project){
    	Collection<IssueType> issuesTypes=project.getIssueTypes();
    	String issueTypeId="";
    	for(IssueType childIssue : issuesTypes){
    		if(childIssue.getName().equalsIgnoreCase("Problem")){
    			issueTypeId=childIssue.getId();
    			break;
    		}
    	}
    	return issueTypeId;
    }
    private String messages = "";
    public String getMessages(){return this.messages;}
    
    /**
     * Includes Jira Web Resources
     * @return void
     */
    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
    }

    /**
     * Gets Issue Object
     * @return the Issue
     */
    public Issue getIssueObject()
    {
    	IssueService.IssueResult issueResult =null;
    	if(id!=null){
	        issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), id);	        
    	}
    	else{
    		issueResult = issueService.getIssue(authenticationContext.getLoggedInUser(), key);            
    	}
    	
    	if (!issueResult.isValid())
        {
            this.addErrorCollection(issueResult.getErrorCollection());
            return null;
        }
    	
        return  issueResult.getIssue();
    }

    // Getter adn Setters for passing the form params

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
