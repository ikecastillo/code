package com.dt.jira.xmatters.intgt.plugin.webwork;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.xsrf.RequiresXsrfCheck;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.webresource.WebResourceManager;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //Type field is now changed to Clients Impacted per the new requirement
		CustomField typeCF = cfm.getCustomFieldObjectByName("Clients Impacted");
    
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

    /**
     * This method is added to facilitate the selection of management bridge by default,
     * if the product contains the word "partner" in it. This will be called from success.vm,
     * like this :
     *
     * @return product if available, empty string otherwise
     */
    public String getProductField() {
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField solnGrpsProds = cfm.getCustomFieldObjectByName("Solution Groups - Products");
        Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);

        List<String> values = new ArrayList<>();
        List valuesList = (ArrayList)issue.getCustomFieldValue(solnGrpsProds);

        //See if the values are set for the concerned custom field - if not, then just return an empty string
        if (valuesList != null) {
            for (Object value : valuesList) {
                values.add(value.toString());
            }

            if (values.size() >= 2) {
                String productValue = values.get(1);
                if (productValue != null) {
                    log.info("Product Value is selected in the issue and is seen as " + productValue  + " from the webwork");
                    return productValue;
                }
            }
        }

        return "";
    }

    public String getSolutionGroup() {
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField solnGrpsProds = cfm.getCustomFieldObjectByName("Solution Groups - Products");
        Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);

        List<String> values = new ArrayList<>();
        List valuesList = (ArrayList)issue.getCustomFieldValue(solnGrpsProds);

        //See if the values are set for the concerned custom field - if not, then just return an empty string
        if (valuesList != null) {
            for (Object value : valuesList) {
                values.add(value.toString());
            }

            if (values.size() >= 2) {
                String solnGrpValue = values.get(0);
                if (solnGrpValue != null) {
                    log.info("Solution Group is selected in the issue and is seen as " + solnGrpValue  + " from the webwork");
                    return solnGrpValue;
                }
            }
        }

        return "";
    }

    public String getSeverity() {
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField severityCF = cfm.getCustomFieldObjectByName("Severity");
        Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);
        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        LazyLoadedOption severityVal = (LazyLoadedOption)issue.getCustomFieldValue(severityCF);
        String severityName = "";
        if(severityVal!=null && severityVal.getOptionId()!=null ){
            Option severityOpt= optionsManager.findByOptionId(severityVal.getOptionId());
            if(severityOpt!=null && severityOpt.getValue()!=null){
                severityName = severityOpt.getValue();
                log.info("Severity : " + severityName);
            }
        }
        return severityName;
    }
}
