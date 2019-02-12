package com.dt.jira.incidentmap.plugin.ao;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Map;

import net.java.ao.Query;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.google.common.collect.Maps;

/**
 * The Incident Map Service Implementation for Incident Report Assignee Map on Incident Management project
 *
 * @author Firoz Khan
 */
public class IncidentMapServiceImpl implements IncidentMapService
{
	private final Logger logger = Logger.getLogger(IncidentMapServiceImpl.class);
    private final ActiveObjects ao;
 
    public IncidentMapServiceImpl(ActiveObjects ao)
    {
        this.ao = checkNotNull(ao);
    }

    
    @Override   
    public void saveIncidentMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby){
  		IncidentMap incMap = null;
		logger.info("-----------saveIncidentMapping--------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " grpoptionname: "+ grpoptionname+ " cldgrpoptionid: "+ cldgrpoptionid + " cldgrpoptionname: "+ cldgrpoptionname+" role: "+ roles +" users: "+ users + " createdate: "+ createdate + " miodifieddate: "+ miodifieddate + " miodifiedby: "+ miodifiedby);

		Query query = Query.select();
		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_ID = ? AND CLD_GRP_OPTION_NAME = ? AND ROLES = ?", pjKey,type, grpoptionid, grpoptionname, cldgrpoptionid, cldgrpoptionname, roles);
		
		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
		logger.info("-----------saveIncidentMapping-----------------------pjKey------------------------- "+ pjKey );
		if (incMapList!=null && incMapList.size()>0) {
			//If mapping exists get the row
			incMap = incMapList.get(0);
			logger.info("-----------saveIncidentMapping----------------------- "+incMap.getGrpOptionId());
		}
		else { 
			//else create a row
			incMap = ao.create(IncidentMap.class);
			incMap.setCreatedDate(createdate);
			logger.info("Successfully created");
		}
		incMap.setProjectKey(pjKey);
		incMap.setType(type);
		incMap.setGrpOptionId(grpoptionid);
		incMap.setGrpOptionName(grpoptionname);
		incMap.setCldGrpOptionId(cldgrpoptionid);
		incMap.setCldGrpOptionName(cldgrpoptionname);
		incMap.setRoles(roles);
		incMap.setUsers(users);
		incMap.setModifiedDate(miodifieddate);
		incMap.setModifiedBy(miodifiedby);
		incMap.save();	
		logger.info("-----------saveIncidentMapping-----------------------Successfully updated------------------------- "); 
    }
    

    @Override
   	public List<IncidentMap> getIncidentMappingByProjectKey(String pjKey){    		
	   		if (pjKey == null) return null;
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY=?", pjKey); 
   			return newArrayList(ao.find(IncidentMap.class,query));
       }
    
    
    @Override   
    public List<IncidentMap> getIncidentMappingParentChildDetails(String pjKey, String type,String grpoptionname,String cldgrpoptionname){   
	   		logger.info("-----------getIncidentMappingDetails---------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname + " cldgrpoptionname: "+ cldgrpoptionname  );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_NAME = ?", pjKey,type, grpoptionname,cldgrpoptionname);
	   		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
	   			logger.info("-----------getIncidentMappingDetails-----------------------incMapList size--------------------- "+ incMapList.size() );
			if (incMapList!=null && incMapList.size()>0) {					
				IncidentMap incMap = (IncidentMap) incMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname+ " cldgrpoptionname: "+ cldgrpoptionname);
			}	   		
   			return incMapList;
       }

    @Override
   	public List<IncidentMap> getIncidentMappingParentDetails(String pjKey, String type,String grpoptionname){     
	   		logger.info("-----------getIncidentMappingDetails---------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_NAME = ?", pjKey,type, grpoptionname);
	   		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
	   			logger.info("-----------getIncidentMappingDetails-----------------------incMapList size--------------------- "+ incMapList.size() );
			if (incMapList!=null && incMapList.size()>0) {					
				IncidentMap incMap = (IncidentMap) incMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname);
			}	   		
   			return incMapList;
       }

	   
	    @Override   
    public List<IncidentMap> getIncidentMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid){   
	   		logger.info("-----------getIncidentMappingParentChildDetailsById---------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " cldgrpoptionid: "+ cldgrpoptionid  );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND CLD_GRP_OPTION_ID = ?", pjKey,type, grpoptionid,cldgrpoptionid);
	   		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
	   			logger.info("-----------getIncidentMappingParentChildDetailsById-----------------------incMapList size--------------------- "+ incMapList.size() );
			if (incMapList!=null && incMapList.size()>0) {					
				IncidentMap incMap = (IncidentMap) incMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid+ " cldgrpoptionid: "+ cldgrpoptionid);
			}	   		
   			return incMapList;
       }
	    @Override 
	public void saveIncidentMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby,String fourthLevelOptId,String fourthLevelOptName){
		IncidentMap incMap = null;
		logger.info("-----------saveIncidentMapping--------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " grpoptionname: "+ grpoptionname+ " cldgrpoptionid: "+ cldgrpoptionid + " cldgrpoptionname: "+ cldgrpoptionname+" role: "+ roles +" users: "+ users + " createdate: "+ createdate + " miodifieddate: "+ miodifieddate + " miodifiedby: "+ miodifiedby + " fourthLevelOptId: "+ fourthLevelOptId + " fourthLevelOptName: "+ fourthLevelOptName );

		Query query = Query.select();
		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_ID = ? AND CLD_GRP_OPTION_NAME = ? AND FOURTH_LEVEL_OPTION_ID = ? AND FOURTH_LEVEL_OPTION_NAME = ? AND ROLES = ?", pjKey,type, grpoptionid, grpoptionname, cldgrpoptionid, cldgrpoptionname, fourthLevelOptId,fourthLevelOptName, roles);
		
		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
		logger.info("-----------saveIncidentMapping-----------------------pjKey------------------------- "+ pjKey );
		if (incMapList!=null && incMapList.size()>0) {
			//If mapping exists get the row
			incMap = incMapList.get(0);
			logger.info("-----------saveIncidentMapping----------------------- "+incMap.getGrpOptionId());
		}
		else { 
			//else create a row
			incMap = ao.create(IncidentMap.class);
			incMap.setCreatedDate(createdate);
			logger.info("Successfully created");
		}
		incMap.setProjectKey(pjKey);
		incMap.setType(type);
		incMap.setGrpOptionId(grpoptionid);
		incMap.setGrpOptionName(grpoptionname);
		incMap.setCldGrpOptionId(cldgrpoptionid);
		incMap.setCldGrpOptionName(cldgrpoptionname);
		incMap.setRoles(roles);
		incMap.setUsers(users);
		incMap.setModifiedDate(miodifieddate);
		incMap.setModifiedBy(miodifiedby);
		incMap.setFourthLevelOptionId(fourthLevelOptId);
		if(fourthLevelOptId.equals("None"))
			incMap.setFourthLevelOptionName("None");
		else 
			incMap.setFourthLevelOptionName(fourthLevelOptName);
		
		incMap.save();	
		logger.info("-----------saveIncidentMapping-----------------------Successfully created"); 
	    }

	    @Override   
	    public List<IncidentMap> getIncidentMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid,String fourthLevelOptId){   
		   		logger.info("-----------getIncidentMappingParentChildDetailsById---------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " cldgrpoptionid: "+ cldgrpoptionid + " fourthLevelOptId: "+ fourthLevelOptId  );
		   		Query query = Query.select();
		   		
		   			query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND CLD_GRP_OPTION_ID = ? AND FOURTH_LEVEL_OPTION_ID = ? ", pjKey,type, grpoptionid,cldgrpoptionid,fourthLevelOptId);
		   		
		   		
		   		List<IncidentMap> incMapList = newArrayList(ao.find(IncidentMap.class,query));
		   			logger.info("-----------getIncidentMappingParentChildDetailsById-----------------------incMapList size--------------------- "+ incMapList.size() );
				if (incMapList!=null && incMapList.size()>0) {					
					IncidentMap incMap = (IncidentMap) incMapList.get(0);	
				}else{
					logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid+ " cldgrpoptionid: "+ cldgrpoptionid);
				}	   		
	   			return incMapList;
	       }
}
