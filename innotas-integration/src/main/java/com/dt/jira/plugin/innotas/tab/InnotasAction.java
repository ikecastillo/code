package com.dt.jira.plugin.innotas.tab;
import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.project.Project;
import javax.servlet.http.HttpServletRequest;

import net.java.ao.Query;
import java.util.List;

import com.atlassian.jira.web.ExecutingHttpRequest;
import static com.google.common.base.Preconditions.checkNotNull;

import com.dt.jira.plugin.innotas.service.InnotasLink;
import com.dt.jira.plugin.innotas.ao.InnotasProject;
import com.dt.jira.plugin.innotas.ao.InnotasService;
import com.dt.jira.plugin.innotas.ao.Innotas;
import com.dt.jira.plugin.innotas.ao.InnotasCache;

public class InnotasAction extends JiraWebActionSupport {
	private InnotasService innotasService;
	private String REQ_CACHE = "com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache";
	private InnotasLink innotasLink;
	private String innotasProjectId;
	private String innotasProjectName;
	private String businessUnit;
	private String subBusinessUnit;
	private String program;
	private String jiraProjectKey;
	private String jiraRelId;
	private String allMapping;
	//private Project project;
	private HttpServletRequest request;
	private final ActiveObjects ao;
	public InnotasAction(InnotasService innotasService, ActiveObjects ao) {
	this.innotasService = checkNotNull(innotasService);
	this.ao = checkNotNull(ao);
	}
	private void init(String _jiraProjectKey) throws Exception{
		Project project = getProjectManager().getProjectObjByKey(_jiraProjectKey);
		this.request = ExecutingHttpRequest.get();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		//innotasLink = new InnotasLinkImpl();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":innotasservice").toString(), innotasService);
				//System.out.println("********** SRI1: " + (String)request.getParameterNames().nextElement());
				//System.out.println("********** SRI2: " + request.getParameterNames().hasMoreElements() + " xxxx  "  + (String)request.getParameterNames().nextElement());
		Innotas i = innotasService.find(_jiraProjectKey);
		if (i != null) {
			setInnotasProjectId(i.getInnotasProjectId());
			setJiraProjectKey(i.getJiraProjectKey());
			setJiraRelId(i.getJiraRelId());
			InnotasCache[] ic = ao.find(InnotasCache.class, Query.select().where("PROJECT_ID=?",i.getInnotasProjectId()));
			if (ic !=null && ic.length > 0) this.setBusinessUnit(ic[0].getBuId());
		}
		
	}
	@Override
	public String doDefault() throws Exception {
		init(this.jiraProjectKey);
		System.out.println("***************** Inside doDefault ***************");
		System.out.println("*********** " + getInnotasProjectId());
		return "input";
	}

	public void setInnotasservice(InnotasService innotasService) {
		this.innotasService = innotasService;
	}
	public InnotasService getInnotasservice() {
		return this.innotasService;
	}

	public void setInnotasLink(InnotasLink innotasLink) {
		this.innotasLink = innotasLink;
	}
	public InnotasLink getInnotasLink() {
		return this.innotasLink;
	}

	/* (non-Javadoc) * @see webwork.action.ActionSupport#doValidation() */
	@Override
	protected void doValidation(){
		super.doValidation();
	}

	/* (non-Javadoc) * @see webwork.action.ActionSupport#doExecute() 
	* 
	* This method is always called when this Action's .jspa URL is 
	* invoked if there were no errors in doValidation(). 
	*/
	@Override
	protected String doExecute() throws Exception {
		System.out.println("***************** Inside doExecute ***************");
		System.out.println("allmapping " + this.getAllMapping());
		String[] temp1 = this.getAllMapping().split(",");
		String[] temp2; 
		for(int i=0;i<temp1.length;i++) {
			System.out.println(temp1[i]);
			temp2 = temp1[i].split("\\|");
			if (temp2.length < 2) continue;
			//System.out.println("pKey: " + this.jiraProjectKey + " RelId: " + temp2[0] + " InnotasProjectId: " + temp2[1]);
			this.innotasService.add(this.jiraProjectKey,temp2[0],temp2[1],temp2[2].equalsIgnoreCase("1"));
		}
		//this.innotasService.add(jiraProjectKey, jiraProjectId, innotasProjectId);
		//this.innotasService.updateOldIssues(this.jiraProjectKey);
		init(this.jiraProjectKey);
		return "success";
	}
	public String doUpdateOldIssues() throws SearchException {
		this.innotasService.updateOldIssues(this.jiraProjectKey);
		Project project = getProjectManager().getProjectObjByKey(this.jiraProjectKey);
		this.request = ExecutingHttpRequest.get();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":InnotasAOService").toString(), innotasService);
		return "input";
	}	
	public String doSyncCache() throws Exception {
		this.innotasService.syncCache();
		Project project = getProjectManager().getProjectObjByKey(this.jiraProjectKey);
		this.request = ExecutingHttpRequest.get();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":InnotasAOService").toString(), innotasService);
		return "input";
	}

	public String doRefreshProject() throws Exception {
		//System.out.println("*********** " + this.jiraProjectKey);	
		Project project = getProjectManager().getProjectObjByKey(this.jiraProjectKey);
		this.request = ExecutingHttpRequest.get();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":InnotasAOService").toString(), innotasService);
		return "input";
	}
    public void setInnotasProjectId(String innotasProjectId) {
        log.debug("Setting innotasProjectId to: " + innotasProjectId);
        this.innotasProjectId = innotasProjectId;
    }
    public String getInnotasProjectId() {
        log.debug("Getting InnotasProjectId");
        return innotasProjectId;
    }
	public void setJiraProjectKey(String value) {
		jiraProjectKey = value;
	}
	public String getJiraProjectKey() {
		return jiraProjectKey;
	}
	public void setJiraRelId(String value) {
		jiraRelId = value;
	}
	public String getJiraRelId() {
		return jiraRelId;
	}
	public void setBusinessUnit(String value) {
		businessUnit = value;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setAllMapping(String value) {
		allMapping = value;
	}
	public String getAllMapping() {
		return allMapping;
	}
		public String doSetFieldOptions() throws Exception {
		this.innotasService.setAllFieldOptions(this.jiraProjectKey);
		Project project = getProjectManager().getProjectObjByKey(this.jiraProjectKey);
		this.request = ExecutingHttpRequest.get();
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		this.request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":InnotasAOService").toString(), innotasService);
		return "input";
	}
	
}