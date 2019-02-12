package com.dt.jira.plugin.innotas.ao;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.context.IssueContextImpl;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.project.Project;
import javax.servlet.http.HttpServletRequest;
import com.atlassian.jira.web.ExecutingHttpRequest;
import static com.google.common.base.Preconditions.*;
import com.atlassian.jira.user.UserProjectHistoryManager;

import com.dt.jira.plugin.innotas.service.InnotasLink;
import com.dt.jira.plugin.innotas.service.InnotasLinkImpl;  
public final class InnotasServlet extends HttpServlet
{
    private final InnotasService innotasService;
	private String _projectKey;
	private InnotasLink innotasLink;
	private String REQ_CACHE = "com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache";
    public InnotasServlet(InnotasService innotasService)
    {
        this.innotasService = checkNotNull(innotasService);
		init();
    }
	public void init() {
		// Project project = getProjectManager().getProjectObjByKey(_projectKey);
		// HttpServletRequest request = ExecutingHttpRequest.get();
		// request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":project").toString(), project);
		// innotasLink = new InnotasLinkImpl();
		// request.setAttribute((new StringBuilder()).append(REQ_CACHE).append(":innotaslink").toString(), innotasLink);
	}

	public void setInnotasLink(InnotasLink innotasLink) {
		this.innotasLink = innotasLink;
	}
	public InnotasLink getInnotasLink() {
		return this.innotasLink;
	}
	public void setProjectKey(String projectKey) {
		_projectKey = projectKey;
	}
	public String getProjectKey() {
		return _projectKey;
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final PrintWriter w = res.getWriter();
		w.write("<html>");
		w.write("<head>");
		w.write("<meta name=\"decorator\" content=\"atl.admin\" />");
		w.write("<meta content=\"admin_plugins_menu/source_control\" name=\"admin.active.section\">");
		w.write("<meta content=\"cvs_modules\" name=\"admin.active.tab\">");
		w.write("</head>");
		//w.write("<head><title>Innotas</title>  <meta name=\"decorator\" content=\"atl.admin\"/>  <meta name=\"projectKey\" content=\"WFNY\"/>  <meta name=\"projectId\" content=\"10900\"/>  <meta name=\"admin.active.tab\" content=\"Innotas\"/>  <meta name=\"admin.active.section\" content=\"atl.jira.proj.config\"/></head>");
        w.write("<body>");
		w.write("<h1>Innotas Link</h1>");
 
        // the form to post more TODOs
        w.write("<form method=\"post\">");
        w.write("<input type=\"text\" name=\"cf\" size=\"25\" value=\"Innotas Business Unit\"/>");
        // w.write("<input type=\"text\" name=\"projectId\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		// w.write("<input type=\"text\" name=\"projectName\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		// w.write("<input type=\"text\" name=\"bu\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		// w.write("<input type=\"text\" name=\"program\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		// w.write("<input type=\"text\" name=\"jpId\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		// w.write("<input type=\"text\" name=\"jpKey\" size=\"25\"/>");
        // w.write("&nbsp;&nbsp;");
		
        w.write("<input type=\"submit\" name=\"submit\" value=\"Save\"/>");
        w.write("</form>");
 
        try {
			List<Option> options = innotasService.getFieldOptions(this.getProjectKey(), "Innotas Business Unit");
			for(Option option : options) {
				w.printf("<li>%d - %d - %s </li>", option.getOptionId(), option.getSequence(), option.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        w.write("<ol>");
 
        for (Innotas innotas : innotasService.getInnotasMap()) // (2)
        {
            w.printf("<li>%s - %s - %s </li>", innotas.getInnotasProjectId(), innotas.getJiraProjectKey(), innotas.getJiraRelId());
        }
 
        w.write("</ol>");
        w.write("<script language='javascript'>document.forms[0].elements[0].focus();</script>");
		w.write("</body></html>");
        w.close();
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        final String projectId = req.getParameter("projectId");
		final String jpId = req.getParameter("jpId");
		final String jpKey = req.getParameter("jpKey");
        //innotasService.add(projectId, jpId, jpKey);
        res.sendRedirect(req.getContextPath() + "/plugins/servlet/todo/innotas");
    }
}