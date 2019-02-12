package com.dt.jira.plugin.innotas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import services.objects.xsd.EntityObj;
import services.objects.xsd.ValuePair;

import com.dt.jira.plugin.innotas.InnotasConstants;

public class InnotasLinkImpl implements InnotasLink, InnotasConstants
{
	private final EntityService entityService;
	public InnotasLinkImpl(EntityService es) {
		this.entityService = es;
	}
	@Override
	public Map <Long, String> getInnotasBUs() {
		Map<Long, String> mp = new HashMap<Long, String>();
		//EntityService entityService = new EntityService();
		long[] fieldsRequest = {207L};		
		EntityObj[] eob= entityService.findEntity(2L,null,fieldsRequest);
		for (int i=0; i<eob.length; i++) {
			ValuePair[] mv = eob[i].getMethodValues();
			mp.put(eob[i].getEntityId(),mv[0].getElementValue());
		}
		return mp;
	}
	@Override
	public Map <Long, Object> getInnotasProjects() {
		return getInnotasProjects(null,null);
	}
	@Override
	public Map <Long, Object> getInnotasProjects(String buId) {
		return getInnotasProjects(buId,null);
	}
	@Override
	public Map <Long, Object> getInnotasProjects(String buId, String sbuId) {
		Map<Long, Object> mp = new HashMap<Long, Object>();
		ArrayList<ValuePair> sv = new ArrayList<ValuePair>(); 
		//EntityService entityService = new EntityService();
		//long[] fieldsRequest = {FIELD_ID_PROJECT_ID, FIELD_ID_PROJECT_NAME, FIELD_ID_BU_ID, FIELD_ID_BU_NAME, FIELD_ID_SBU_ID, FIELD_ID_SBU_NAME, FIELD_ID_PROGRAM_NAME, FIELD_ID_STATUS_ID, FIELD_ID_STATUS_NAME, FIELD_ID_PROJECTID};
		long[] fieldsRequest = {FIELD_ID_PROJECT_NAME, FIELD_ID_BU_ID, FIELD_ID_BU_NAME, FIELD_ID_SBU_ID, FIELD_ID_SBU_NAME,FIELD_ID_PROGRAM_NAME };
		ValuePair s = new ValuePair();
		//s.setId(FIELD_ID_STATUS_ID);
		//s.setElementValue(FIELD_VALUE_STATUS_ID_ACTIVE);
		sv.add(s);
		if (buId != null && !buId.equalsIgnoreCase("") ) {
			s.setId(FIELD_ID_BU_ID);
			s.setElementValue(buId);
			sv.add(s);
		}
		if (sbuId != null && !sbuId.equalsIgnoreCase("") ) {
			s.setId(FIELD_ID_SBU_ID);
			s.setElementValue(sbuId);
			sv.add(s);
		}
		
		//System.out.println("BUId: " + buId);
		ValuePair vp[] = new ValuePair[sv.size()];
		vp = sv.toArray(vp);
		EntityObj[] eob= entityService.findEntity(ENTITY_TYPE_ID_PROJECT,vp,fieldsRequest);
		for (int i=0; i<eob.length; i++) {
			ValuePair[] mv = eob[i].getMethodValues();
			//String id, String name, String bu, String sbu,String program
			System.out.println(mv[1].getElementValue());
			InnotasProjectModel ipm = new InnotasProjectModel(eob[i].getEntityId().toString(), mv[0].getElementValue(),mv[1].getElementValue(), mv[2].getElementValue(), mv[3].getElementValue(), mv[4].getElementValue(), mv[5].getElementValue());
			mp.put(eob[i].getEntityId(),ipm);
		}
		return mp;
	}	
	
/*	public Hashtable <Long, Object> getInnotasProjects1(String buId) {
		Hashtable<Long, Object> mp = new Hashtable<Long, Object>();
		EntityService entityService = new EntityService();
		//long[] fieldsRequest = {FIELD_ID_PROJECT_ID, FIELD_ID_PROJECT_NAME, FIELD_ID_BU_ID, FIELD_ID_BU_NAME, FIELD_ID_SBU_ID, FIELD_ID_SBU_NAME, FIELD_ID_PROGRAM_NAME, FIELD_ID_STATUS_ID, FIELD_ID_STATUS_NAME, FIELD_ID_PROJECTID};
		long[] fieldsRequest = {FIELD_ID_PROJECT_NAME, FIELD_ID_SBU_NAME,FIELD_ID_PROGRAM_NAME };
		ValuePair s = new ValuePair();
		s.setId(FIELD_ID_STATUS_ID);
		s.setElementValue(FIELD_VALUE_STATUS_ID_ACTIVE);
		ValuePair s1 = new ValuePair();
		s1.setId(FIELD_ID_BU_ID);
		s1.setElementValue(buId);
		ValuePair[] sv = {s,s1}; 
		
		System.out.println("BUId: " + buId);
		EntityObj[] eob= entityService.findEntity(ENTITY_TYPE_ID_PROJECT,sv,fieldsRequest);
		for (int i=0; i<eob.length; i++) {
			//System.out.print("Project Id: " + eob[i].getEntityId());
			ValuePair[] mv = eob[i].getMethodValues();
			//System.out.println("Project Name: " + mv[0].getElementValue());
			//String id, String name, String bu, String sbu,String program
			System.out.println(mv[1].getElementValue());
			InnotasProjectModel ipm = new InnotasProjectModel(eob[i].getEntityId().toString(), mv[0].getElementValue(),buId, mv[1].getElementValue(), mv[2].getElementValue());
			mp.put(eob[i].getEntityId(),ipm);
		}
		return mp;
	}	*/
	public Map<Long, String> getInnotasData() { return null;}
	
}