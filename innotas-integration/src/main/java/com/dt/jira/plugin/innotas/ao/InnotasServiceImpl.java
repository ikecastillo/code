package com.dt.jira.plugin.innotas.ao;
 
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashSet;
import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.version.Version;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.dt.jira.plugin.innotas.service.InnotasLink;
import com.dt.jira.plugin.innotas.service.InnotasProjectModel;
import com.dt.jira.plugin.innotas.util.CustomFieldHelper;
import com.dt.jira.plugin.innotas.util.VersionsHelper;
 
public class InnotasServiceImpl implements InnotasService
{
    private final ActiveObjects ao;
		private final InnotasLink il;
		//private final Version version;
		//private final VersionsHelper vh;
    public InnotasServiceImpl(ActiveObjects ao, InnotasLink il) {
        this.ao = checkNotNull(ao);
				this.il = checkNotNull(il);
				//this .version = version;
				//this.vh = vh;
    }
		
    @Override
    public Innotas add(String jiraProjectKey, String jiraRelId, String innotasProjectId, boolean extRel)
    {
			//see if this jiraProject Key already exists
			Innotas innotas = find(jiraProjectKey,jiraRelId );
			//Innotas innotas = find(jiraProjectKey);
    	//if not, create a new entity row
			if (innotas == null) innotas = ao.create(Innotas.class);
			innotas.setInnotasProjectId(innotasProjectId);
			innotas.setJiraRelId(jiraRelId);
			innotas.setJiraProjectKey(jiraProjectKey);
			innotas.setExtRel(extRel);
      innotas.save();
      return innotas;
    }
	@Override	
    public Innotas find(String pKey, String relId){
			//return ao.find(Innotas.class, Query.select().where("JIRA_PROJECT_KEY=? AND JIRA_REL_ID=?", pKey, relId)); 
			Innotas[] i = ao.find(Innotas.class, Query.select().where("JIRA_PROJECT_KEY=? AND JIRA_REL_ID=?", pKey, relId)); 
			if (i!=null && i.length > 0) return i[0];
			else return null;
    }
	@Override
    public Innotas find(String pKey)
    {
        List<Innotas> lst = newArrayList(ao.find(Innotas.class));
		for (Innotas i : lst) {
			if(pKey.equalsIgnoreCase(i.getJiraProjectKey())) return i; 
        }
		return null;
    }

	@Override
	public List<Innotas> getInnotasMap() {
		return newArrayList(ao.find(Innotas.class));
	}
	@Override
	public List<Innotas> getInnotasMap(String pKey) {
		return newArrayList(ao.find(Innotas.class, Query.select().where("JIRA_PROJECT_KEY=?",pKey)));
	}
	@Override
	public Map<String, String> getInnotasBUs() {
		List<InnotasCache> buList = newArrayList(ao.find(InnotasCache.class,Query.select("BU_ID, BU_NAME").distinct()));
		Map<String, String> buMap = new TreeMap<String, String>();
		for(InnotasCache bu : buList) {
			buMap.put(bu.getBuId(), bu.getBuName());
		}
		return buMap;
	}
	
	@Override
	public Map<String, String> getInnotasSBUs() {
		return this.getInnotasSBUs(null);
	}	
	
	@Override
	public Map<String, String> getInnotasSBUs(String buId){
		Query q = null;
		if(buId == null)  q = Query.select("SBU_ID, SBU_NAME").distinct();
		else q = Query.select("SBU_ID, SBU_NAME").distinct().where("BU_ID=?",buId);
		List<InnotasCache> buList = newArrayList(ao.find(InnotasCache.class,q));
		Map<String, String> buMap = new TreeMap<String, String>();
		for(InnotasCache bu : buList) {
			buMap.put(bu.getSbuId(), bu.getSbuName());
		}
		return buMap;
	}

	@Override
	public List<String> getInnotasPrograms(){
		//TODO: 'Select' on single column throws exception. Troubleshoot this. 
		List<InnotasCache> progList = newArrayList(ao.find(InnotasCache.class,Query.select("'',PROGRAM_NAME")));
		List<String> programs = newArrayList();
		for(InnotasCache prog : progList) {
			System.out.println(prog.getProgramName());
			if (prog.getProgramName().equalsIgnoreCase("")) continue;
			programs.add(prog.getProgramName());
		}
		// Create a unique list of programs
		HashSet hs = new HashSet();
		hs.addAll(programs);
		programs.clear();
		programs.addAll(hs);
		return programs;
	}
	

	@Override
	public Map<String, Object> getInnotasProjects() {
		return getInnotasProjects(null, null);
	}

	@Override
	public Map<String, Object> getInnotasProjects(String buId) {
		return getInnotasProjects(buId, null);
	}
	
	@Override
	public Map<String, Object> getReleases(String projectKey) {
		System.out.println("project key: " + projectKey);
		Map<String, Object> relMap = new TreeMap<String, Object>();
		VersionsHelper vh = new VersionsHelper();
		List<Version> versions = vh.getVersionsList(projectKey);
		for(Version version : versions) {		
				System.out.println("version : " + version);
				relMap.put(version.getId().toString(), version);
		}
	return relMap;
	}
	@Override
	public Map<String, Object> getInnotasMapping(String pKey, String relId) {
			Innotas innotasMap = find(pKey, relId);
			Map<String, Object> map = new TreeMap<String, Object>();
			map.put(relId, innotasMap);
			return map;
	}
	@Override
	public String getInnotasProjectId(String pKey, String relId) {
			Innotas innotas = find(pKey, relId);
			if(innotas == null) return "";
			return innotas.getInnotasProjectId();
	}
	@Override
	public Map<String, Object> getInnotasProjects(String buId, String sbuId) {
		Query query = Query.select();
		if (buId != null) query = query.where("BU_ID=?", buId);
		if (sbuId != null) query = query.where("SBU_ID=?", sbuId);
		List<InnotasCache> prjList = newArrayList(ao.find(InnotasCache.class,query));
		Map<String, Object> prjMap = new TreeMap<String, Object>();
		for(InnotasCache prj : prjList) {		
				prjMap.put(prj.getProjectId(), prj);
		}
		return prjMap;	
	}
	@Override
	public boolean syncCache() {
		//InnotasLinkImpl il = new InnotasLinkImpl();
		Map<Long, Object> prjList = il.getInnotasProjects();
		InnotasCache[] ic = ao.find(InnotasCache.class);
		if (ic != null) ao.delete(ic);
		for(Map.Entry<Long, Object> prj : prjList.entrySet()) {
			InnotasCache icPrj = ao.create(InnotasCache.class);
			icPrj.setProjectId(prj.getKey().toString());
			InnotasProjectModel p = (InnotasProjectModel) prj.getValue();
			icPrj.setProjectName(p.getName());
			icPrj.setBuId(p.getBuId());
			icPrj.setBuName(p.getBu());
			icPrj.setSbuId(p.getSbuId());
			icPrj.setSbuName(p.getSbu());
			icPrj.setProgramName(p.getProgram());
			icPrj.save();
		}

		return true;
	}
	@Override
	public void updateOldIssues(String pKey) throws SearchException {
		List<Innotas> mapList = getInnotasMap(pKey);
		for(Innotas map : mapList) {				
			String innotasProjectId = map.getInnotasProjectId();
			InnotasProject[] prjList  = ao.find(InnotasProject.class, Query.select().where("INNOTAS_ID=?",innotasProjectId));
			if (prjList.length < 1 ) return;
			String buId = prjList[0].getBuId();
			String innotasProjectName = prjList[0].getInnotasName();
			String innotasSbu = prjList[0].getSbuName();
			String innotasProgram = prjList[0].getProgramName();
			InnotasBU[] buList = ao.find(InnotasBU.class, Query.select().where("BU_ID=?",buId));
			String innotasBU = "";
			if (buList.length > 0) innotasBU = buList[0].getBuName();
			
			SearchService searchServ = ComponentAccessor.getComponent(SearchService.class);		
			JiraAuthenticationContext authenticationContext = ComponentAccessor.getJiraAuthenticationContext();
			StringBuffer jql = new StringBuffer("project = "+ pKey);
			System.out.println("********* Query: "+jql.toString());

			final SearchService.ParseResult pResult= searchServ.parseQuery(authenticationContext.getLoggedInUser(), (jql.toString()));
			if (pResult.isValid()) {
				SearchResults issueList = searchServ.search(authenticationContext.getLoggedInUser(),pResult.getQuery(), PagerFilter.getUnlimitedFilter());
				List<Issue> issues = issueList.getIssues();
				CustomFieldHelper cfhPrjId =  new CustomFieldHelper("Innotas Project ID");
				CustomFieldHelper cfhPrjName =  new CustomFieldHelper("Innotas Project Name"); 
				CustomFieldHelper cfhBu =  new CustomFieldHelper("Innotas Business Unit"); 
				CustomFieldHelper cfhSbu =  new CustomFieldHelper("Innotas Sub Business Unit");
				CustomFieldHelper cfhProgram =  new CustomFieldHelper("Innotas Program");  
				for(Issue issue: issues){     
						System.out.println("Issue = " + issue.getKey() + "; innotasProjectId = " + innotasProjectId);
						cfhPrjId.updateFieldValue(issue, Double.valueOf(innotasProjectId));
						cfhPrjName.updateFieldValue(issue, innotasProjectName);
						cfhBu.updateFieldOption(issue, innotasBU);
						cfhSbu.updateFieldOption(issue, innotasSbu);
						cfhProgram.updateFieldOption(issue, innotasProgram);
				}
			}
		}
	}
	public void setAllFieldOptions(String pKey) {
		try {
			List<String> values = new ArrayList<String> (this.getInnotasBUs().values());
			this.setFieldOptions(pKey, "Innotas Business Unit", values);
			values = new ArrayList<String> (this.getInnotasSBUs().values());
			this.setFieldOptions(pKey, "Innotas Sub Business Unit", values);
			values = this.getInnotasPrograms();
			this.setFieldOptions(pKey, "Innotas Program", values);
			
			Collection<Object> prjList = this.getInnotasProjects().values();
			List <String> prjIds = newArrayList();
			List <String> prjNames = newArrayList();
			
			for(Object prj : prjList) {
				prjIds.add(((InnotasCache) prj).getProjectId());
				prjNames.add(((InnotasCache) prj).getProjectName());
			}
			this.setFieldOptions(pKey, "Innotas Project ID", prjIds);
			this.setFieldOptions(pKey, "Innotas Project Name", prjNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		@Override
		public void setFieldOptions(String pKey, String cfName, List<String> values) throws Exception{
			CustomFieldHelper cfh = new CustomFieldHelper(cfName);
			List<String> optionValues = newArrayList();
			for (String optionValue : values) {
				optionValues.add(optionValue);
			}
			cfh.setFieldOptions(pKey, optionValues);
		}

	@Override
	public List<Innotas> getMappedProjects() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Option> getFieldOptions(String pKey, String cfName) throws Exception{
		CustomFieldHelper cfh = new CustomFieldHelper(cfName);
		return cfh.getFieldOptions(pKey);
	}

}
