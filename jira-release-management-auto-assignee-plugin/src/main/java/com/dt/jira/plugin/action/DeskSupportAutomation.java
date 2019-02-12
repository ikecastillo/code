package com.dt.jira.plugin.action;

import org.apache.log4j.Logger;

import com.atlassian.jira.project.ProjectManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.dt.jira.plugin.rest.IssueResource;
import com.dt.jira.plugin.service.AutoAssigneeService;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class is used for view portal configuration.
 * 
 * @author Srinadh.G
 */
public class DeskSupportAutomation extends DeskSupportBaseAction {

	private static final long serialVersionUID = -2785052435600102817L;
	private final Logger logger = Logger.getLogger(IssueResource.class);

	
	
	public DeskSupportAutomation(AutoAssigneeService autoAssigneeService) {
		super(autoAssigneeService);
	}
	
	

	@Override
	protected String doExecute() throws Exception {
		logger.info("<-------doExecute-------->");
		/*Client c = Client.create();
        AsyncWebResource asyncResource = c.resource("/projectIssues/getAllProjects");
        logger.info("asyncResource----->"+asyncResource);*/
		return SUCCESS;
	}

	public String doSelect() {
		logger.info("<-------doSelect-------->");
		return SUCCESS;
	}
	
	public String doEdit() {
		logger.info("<-------doEdit-------->");
		return INPUT;
	}

}