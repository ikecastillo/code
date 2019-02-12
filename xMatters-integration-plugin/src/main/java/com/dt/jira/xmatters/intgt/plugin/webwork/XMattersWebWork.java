package com.dt.jira.xmatters.intgt.plugin.webwork;

import java.util.HashMap;

import java.util.Map;

import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.xsrf.RequiresXsrfCheck;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;

public class XMattersWebWork extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(XMattersWebWork.class);

    private final JiraAuthenticationContext authenticationContext;

    private Long id;
    private final WebResourceManager webResourceManager;
    public XMattersWebWork(JiraAuthenticationContext authenticationContext, WebResourceManager webResourceManager)
    {
        this.authenticationContext = authenticationContext;
        this.webResourceManager = webResourceManager;
    }

    protected void doValidation()
    {
        includeResources();      

    }

    @RequiresXsrfCheck
    protected String doExecute() throws Exception
    {
	System.out.println("Web work : doExecute():");
    	final Issue issue = getIssueObject();
        // We want to redirect back to the view issue page so
		
        return returnComplete("/browse/" + issue.getKey());
    }

    public String doDefault() throws Exception
    {
		log.info("Web work : doDefault():");
        final Issue issue = getIssueObject();
        
        if (issue == null)
        {
            return INPUT;
        }

        includeResources();

        // Initialization logic
        return INPUT;
    }


    private Map<String, Object> getDisplayParams()
    {
        // This will render the field in it's "aui" state.
        final Map<String, Object> displayParams = new HashMap<String, Object>();
        displayParams.put("theme", "aui");
        return displayParams;
    }


    private void includeResources() {
        webResourceManager.requireResource("jira.webresources:jira-fields");
    }

    public GenericValue getProject()
    {
        return getIssue().getProject();
    }

    public Issue getIssue()
    {
        return getIssueObject();
    }

    public Issue getIssueObject()
    {
    	
    	Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);
    	
        return  issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getIncidentType()
    {
    	CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();		
		CustomField typeCF = cfm.getCustomFieldObjectByName("Type");
    
    	log.info("Web work : Issue Id from context Parameter:"+id);
    	Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);
    	Map<LazyLoadedOption, LazyLoadedOption> typeMap = (HashMap<LazyLoadedOption, LazyLoadedOption>) issue.getCustomFieldValue(typeCF);
		int sizeOftypeMap = typeMap.size();
		log.info("Size of the sizeOfSolutionGpMap"+sizeOftypeMap);
		String typeVal = "";
		
		for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : typeMap.entrySet()) {
		LazyLoadedOption llo = null;
			if(opt.getKey() ==  null ){ // for Solution Group
				log.info("Type key: "+ opt.getKey());
				llo = (LazyLoadedOption) opt.getValue();
				log.info("Type id: "+ llo.getOptionId());
				String typeId = String.valueOf(llo.getOptionId());
				typeVal = String.valueOf(llo.getValue());
				log.info("Type Value: "+ typeVal);				
			}
		}
		log.info("Web work : End:"+id);
        return  typeVal;
    }


    
}
