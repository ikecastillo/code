package com.dt.jira.plugin.innotas.util;
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
import com.dt.jira.plugin.innotas.util.CustomFieldHelper;
import com.dt.jira.plugin.innotas.service.InnotasCacheService;
import com.dt.jira.plugin.innotas.ao.InnotasCache;

public class InnotasCustomFieldSyncImpl implements InnotasCustomFieldSync{
private final InnotasCacheService innotasCacheService;
private final ActiveObjects ao;
public InnotasCustomFieldSyncImpl(InnotasCacheService ics, ActiveObjects ao) {
this.innotasCacheService = ics;
this.ao = ao;
}

	public Map<String, String> getInnotasSBUs() {
		return this.getInnotasSBUs(null);
	}	
	
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

	public List<String> getInnotasPrograms(){
		//TODO: 'Select' on single column throws exception. Troubleshoot this. 
		List<InnotasCache> progList = newArrayList(ao.find(InnotasCache.class,Query.select("'',PROGRAM_NAME")));
		List<String> programs = newArrayList();
		for(InnotasCache prog : progList) {
			//System.out.println(prog.getProgramName());
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
	public void setAllFieldOptions(String pKey) {
		try {
			List<String> values = new ArrayList<String> (innotasCacheService.getInnotasBUs().values());
			this.setFieldOptions(pKey, "Innotas Business Unit", values);
			values = new ArrayList<String> (this.getInnotasSBUs().values());
			this.setFieldOptions(pKey, "Innotas Sub Business Unit", values);
			values = this.getInnotasPrograms();
			this.setFieldOptions(pKey, "Innotas Program", values);
			
			Collection<Object> prjList = innotasCacheService.getInnotasProjects().values();
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
	public void setFieldOptions(String pKey, String cfName, List<String> values) throws Exception{
		CustomFieldHelper cfh = new CustomFieldHelper(cfName);
		List<Option> existingOptions = cfh.getFieldOptions(pKey);
		List<String> current_optionValues = new   ArrayList();
		List<String> new_optionValues = new   ArrayList();
		if( (existingOptions!=null && existingOptions.size()>0) && (values!=null && values.size()>0) ){
			for(Option o: existingOptions){
				String existingName = o.getValue();					
				current_optionValues.add(existingName);
			}
		
			for(String newValue: values){
				if(!current_optionValues.contains(newValue)){				
					new_optionValues.add(newValue);
				}
			}
			
		} else {
			if(values!=null && values.size()>0){
				for(String newValue: values){							
					new_optionValues.add(newValue);
				}
			}		
		}
		
		if(new_optionValues!=null && new_optionValues.size() > 0){			
			cfh.setFieldOptions(pKey, new_optionValues);
		}
	}	
}