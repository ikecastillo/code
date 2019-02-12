package com.dt.jira.plugin.innotas.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import net.java.ao.Query;

import com.atlassian.jira.project.version.Version;
import com.atlassian.activeobjects.external.ActiveObjects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import com.dt.jira.plugin.innotas.util.InnotasConstants;
import com.dt.jira.plugin.innotas.util.VersionsHelper;
import com.dt.jira.plugin.innotas.ao.InnotasCache;
import com.dt.jira.plugin.innotas.ao.InnotasRelease;
import com.dt.jira.plugin.innotas.ao.InnotasMap;

import services.objects.xsd.EntityObj;
import services.objects.xsd.ValuePair;
import com.google.common.collect.Iterables;
import com.google.common.base.Joiner;
import com.google.common.base.Functions;
import com.google.common.base.Splitter;

public class InnotasCacheServiceImpl implements InnotasCacheService, InnotasConstants {
	private final ActiveObjects ao;
	private final EntityService entityService;
	public InnotasCacheServiceImpl(ActiveObjects ao, EntityService es) {
			this.ao = checkNotNull(ao);
			this.entityService = es;
	}
	@Override
	public Map<String, String> getInnotasBUs() {
		List<InnotasCache> buList = newArrayList(ao.find(InnotasCache.class,Query.select("BU_ID, BU_NAME").distinct()));
		Map<String, String> buMap = new TreeMap<String, String>();
		if(buList!=null && buList.size()>0){
			for(InnotasCache bu : buList) {
				buMap.put(bu.getBuId(), bu.getBuName());
			}
		}
		return buMap;
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
	public Map<String, Object> getInnotasReleases(String projectId) {
		if (projectId == null) return null;
		Query query = Query.select();
		query = query.where("PROJECT_ID=?", projectId);
		List<InnotasRelease> relList = newArrayList(ao.find(InnotasRelease.class,query));
		Map<String, Object> map = new TreeMap<String, Object>();
		for(InnotasRelease rel : relList) {		
				map.put(rel.getReleaseId(), rel);
		}	
	return map;
	}
	
	@Override
	public Map<String, Object> getInnotasProjectsByIDs(String ProjectIDs, String releseIds ) {
		
		
		String strMappedProjectID = getInnotasReleaseMapping(ProjectIDs,releseIds);		
		//System.out.println("Final Mapped Release ID's" + strMappedProjectID);		

		Iterable<String> matchValues=Splitter.on(',').split(strMappedProjectID);
		
		String placeholderCommaList = Joiner.on(", ").join(
        Iterables.transform(matchValues, Functions.constant("?")));
		
		Object[] matchValuesArray = Iterables.toArray(matchValues, Object.class);
		//System.out.println("Array Value 0 > " + matchValuesArray[1]);
		//System.out.println("Array Value 1 > " + matchValuesArray[0]);

		Query query = Query.select();
		if (ProjectIDs != null) query = query.where("PROJECT_ID IN (" +  placeholderCommaList + ")", matchValuesArray);
		List<InnotasCache> prjList = newArrayList(ao.find(InnotasCache.class,query));
		System.out.println("Number of records found " + prjList.size());		
		Map<String, Object> prjMap = new TreeMap<String, Object>();
		for(InnotasCache prj : prjList) {		
				prjMap.put(prj.getProjectId(), prj);
		}
		return prjMap;	
	}	
	
	@Override
	public String  getInnotasReleaseMapping(String projectKey, String releseIds){
	
		Query query = Query.select();
		query = query.where("JIRA_PROJECT_KEY=?", projectKey);
		//query = query.where("JIRA_RELEASE_ID IN (?) ", releseIds);	
		System.out.println("releseIds data found: "+releseIds);		
		List<InnotasMap> prjList = newArrayList(ao.find(InnotasMap.class,query));
		//System.out.println("Size of the mapping data found: "+prjList.size());
		Map<String, Object> prjMap = new TreeMap<String, Object>();
		
		StringBuffer mappedReleaseID = new StringBuffer();
		if(prjList!=null && prjList.size()>0){
			for(InnotasMap prj : prjList) {		
				
				if (prj.getJiraReleaseId() != null  && releseIds.contains(prj.getJiraReleaseId()))
				{
						mappedReleaseID.append(prj.getInnotasProjectId());
						mappedReleaseID.append(",");
						//System.out.println("Release ID Found  "+prj.getInnotasProjectId());			
				}
				else
				{
						//System.out.println("Release ID Not found" + prj.getInnotasProjectId());			
				}			
				//prjMap.put(prj.getJiraReleaseId(), prj);
				//prjMap.put(prj.getJiraProjectKey(), prj);
				//prjMap.put(prj.getJiraProjectKey(), prj);
				//System.out.println("Innotas Project Name insie looop: "+prj.getJiraReleaseId());			
			}
		}
		String mappedProjectIDS = "";
		if (mappedReleaseID.length()!=0)
		    {mappedProjectIDS= mappedReleaseID.substring(0, mappedReleaseID.length()-1);}
		//System.out.println("Final Mapped Release ID's" + mappedProjectIDS);		
		
		
		
		
	return mappedProjectIDS;	
	}
	
	@Override
	public Map<String, Object> getReleases(String projectKey) {
		//System.out.println("project key: " + projectKey);
		Map<String, Object> relMap = new TreeMap<String, Object>();
		VersionsHelper vh = new VersionsHelper();
		List<Version> versions = vh.getVersionsList(projectKey);
		for(Version version : versions) {		
				//System.out.println("version : " + version);
				relMap.put(version.getId().toString(), version);
		}
		return relMap;
	}
	
	@Override
	public boolean syncCache() {
		System.out.println("**************** starting syncCache");
		long[] fieldsRequest = {FIELD_ID_PROJECT_NAME, FIELD_ID_BU_ID, FIELD_ID_BU_NAME, FIELD_ID_SBU_ID, FIELD_ID_SBU_NAME,FIELD_ID_PROGRAM_NAME,FIELD_ID_PROJECT_AGILEPROJECT,FIELD_ID_STATUS_ID,FIELD_ID_PROJECT_ID };		
		EntityObj[] eob= entityService.findEntity(ENTITY_TYPE_ID_PROJECT,null,fieldsRequest);				
		if (eob ==null || eob.length < 1) return false; 
		
		InnotasCache[] icCurr = ao.find(InnotasCache.class);
		//Clear Innotas Cache
		if (icCurr != null) ao.delete(icCurr);

		//Populate with the fresh data retrieved from Innotas
		for (int i=0; i<eob.length; i++) {
			ValuePair[] mv = eob[i].getMethodValues();
			//String id, String name, String bu, String sbu,String program
			//Check the project is active
			if( (mv[7].getElementValue().equals(FIELD_VALUE_STATUS_ID_ACTIVE)) || (mv[7].getElementValue().equals(FIELD_VALUE_STATUS_ID_PROPOSED)) ){
				InnotasCache ic = ao.create(InnotasCache.class);
				//ic.setProjectId(eob[i].getEntityId().toString());
				ic.setProjectId(mv[8].getElementValue());
				ic.setProjectName(mv[0].getElementValue());
				ic.setBuId(mv[1].getElementValue());
				ic.setBuName(mv[2].getElementValue());
				ic.setSbuId(mv[3].getElementValue());
				ic.setSbuName(mv[4].getElementValue());
				ic.setProgramName(mv[5].getElementValue());
				ic.setAgileProject(mv[6].getElementValue());			
				ic.save();
			} else {
				System.out.println("Project : " + mv[0].getElementValue() +" Status: "+ mv[7].getElementValue());
			}
		}
		//syncInnotasReleases(); // Sync Innnotas Release table when activity code = Testing
		syncInnotasReleasesOnRelease(); // Sync Innnotas Release table when activity code = Release			
		return true;
	}	
	/*private boolean syncInnotasReleases() {
		long[] fieldsRequest = {FIELD_ID_PROJECT_ID, FIELD_ID_RELEASE_NAME};		
		ArrayList<ValuePair> sv = new ArrayList<ValuePair>();
		sv.add(new ValuePair(FIELD_VALUE_TASK_ACTIVITY_CODE_TESTING, FIELD_ID_TASK_ACTIVITY_CODE));		
		ValuePair vp[] = new ValuePair[sv.size()];
		vp = sv.toArray(vp);
		EntityObj[] eob= entityService.findEntity(ENTITY_TYPE_ID_TASK,vp,fieldsRequest);				
		if (eob ==null || eob.length < 1) return false;
		//Populate with the fresh data retrieved from Innotas
		System.out.println("# Sync Innnotas Release table when activity code = Testing: " + eob.length);
		InnotasRelease[] irCurr = ao.find(InnotasRelease.class);
		//Clear Innotas Releases
		if (irCurr != null) ao.delete(irCurr);
		
		for (int i=0; i<eob.length; i++) {
			ValuePair[] mv = eob[i].getMethodValues();
			InnotasRelease ir = ao.create(InnotasRelease.class);
			ir.setProjectId(mv[0].getElementValue());
			ir.setReleaseId(eob[i].getEntityId().toString());
			ir.setReleaseName(mv[1].getElementValue());
			ir.save();
		}
		syncInnotasReleasesOnRelease(); // Sync Innnotas Release table when activity code = Release		
		return true;		
	}*/
	private boolean syncInnotasReleasesOnRelease() {
		long[] fieldsRequest = {FIELD_ID_PROJECT_ID, FIELD_ID_RELEASE_NAME};		
		ArrayList<ValuePair> sv = new ArrayList<ValuePair>();
		sv.add(new ValuePair(FIELD_VALUE_TASK_ACTIVITY_CODE_RELEASE, FIELD_ID_TASK_ACTIVITY_CODE));	
		ValuePair vp[] = new ValuePair[sv.size()];
		vp = sv.toArray(vp);
		EntityObj[] eob= entityService.findEntity(ENTITY_TYPE_ID_TASK,vp,fieldsRequest);				
		if (eob ==null || eob.length < 1) return false;
		InnotasRelease[] irCurr = ao.find(InnotasRelease.class);
		//Clear Innotas Releases
		if (irCurr != null) ao.delete(irCurr);
		//Populate with the fresh data retrieved from Innotas
		System.out.println("# Sync Innnotas Release table when activity code = Release: " + eob.length);	
		
		for (int i=0; i<eob.length; i++) {
			ValuePair[] mv = eob[i].getMethodValues();
			InnotasRelease ir = ao.create(InnotasRelease.class);
			ir.setProjectId(mv[0].getElementValue());
			ir.setReleaseId(eob[i].getEntityId().toString());
			ir.setReleaseName(mv[1].getElementValue());
			ir.save();
		}
		
		return true;		
	}
	
	
	@Override
	public Map<String, Object> getMapping(String projectKey) {
		Map<String, Object> map = new TreeMap<String, Object>();
		if (projectKey != null) {
			Query query = Query.select();
			query = query.where("JIRA_PROJECT_KEY=?", projectKey);
			List<InnotasMap> mapList = newArrayList(ao.find(InnotasMap.class,query));
			
			for(InnotasMap m: mapList) {		
					map.put(m.getJiraReleaseId(), m);
			}	
		}
		return map;
	}
	@Override
	public String getMappedBuId(String projectKey) {
		//Get the mapping using projectKey
		Map<String, Object> map = getMapping(projectKey);
		if (map.entrySet().iterator().hasNext()) {
			InnotasMap im = (InnotasMap) (map.entrySet().iterator().next().getValue());
			//get the InnotasProjectId from the mapping
			String innotasProjectId = im.getInnotasProjectId();
			//Get the business Unit Id from InnotasCache using the InnotasProjectId
			String buId = null;
			Query query = Query.select();
			query = query.where("PROJECT_ID=?", innotasProjectId);
			List<InnotasCache> prjList = newArrayList(ao.find(InnotasCache.class,query));
			for(InnotasCache prj : prjList) {		
				return prj.getBuId();
			}
		}
		return "";	
	}
	@Override
	public void saveMapping(String jpKey, String jRel, String ipId, String ipRel){
		InnotasMap im = null;
		Query query = Query.select();
		query = query.where("JIRA_RELEASE_ID=?", jRel);
		List<InnotasMap> mapList = newArrayList(ao.find(InnotasMap.class,query));
		if (mapList.size()>0) {
			//If mapping exists get the row
			im = mapList.get(0);
			//If no Innotas Project is mapped, then delete the existing mapping
			if (ipId == null || ipId.equalsIgnoreCase("")) {
				ao.delete(im);
				return;
			}
		}
		else { //This is a new mapping
			//If no Innotas Project is mapped, then do nothing
			if (ipId == null || ipId.equalsIgnoreCase("")) return;
			//else create a row
			im = ao.create(InnotasMap.class);
		}
		im.setJiraProjectKey(jpKey);
		im.setJiraReleaseId(jRel);
		im.setInnotasProjectId(ipId);
		im.setInnotasReleaseId(ipRel);
		im.save();		
	}
}