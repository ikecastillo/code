package com.dt.jira.rcamap.plugin.ao;

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
 * The RCA Report Subtask Map Service Implementation for RCA Report Subtask Assignee Map on Problem Management project
 *
 * @author Firoz Khan
 */
public class RCASubtaskMapServiceImpl implements RCASubtaskMapService
{
	private final Logger logger = Logger.getLogger(RCASubtaskMapServiceImpl.class);
    private final ActiveObjects ao;
 
    public RCASubtaskMapServiceImpl(ActiveObjects ao)
    {
        this.ao = checkNotNull(ao);
    }

    
    @Override   
    public void saveRcaReportSubtaskMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby){
  		RCASubtaskMap rcaMap = null;
		logger.info("-----------saveRcaReportSubtaskMapping--------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " grpoptionname: "+ grpoptionname+ " cldgrpoptionid: "+ cldgrpoptionid + " cldgrpoptionname: "+ cldgrpoptionname+" role: "+ roles +" users: "+ users + " createdate: "+ createdate + " miodifieddate: "+ miodifieddate + " miodifiedby: "+ miodifiedby);

		Query query = Query.select();
		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_ID = ? AND CLD_GRP_OPTION_NAME = ? AND ROLES = ?", pjKey,type, grpoptionid, grpoptionname, cldgrpoptionid, cldgrpoptionname, roles);
		
		List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
		logger.info("-----------saveRcaReportSubtaskMapping-----------------------pjKey------------------------- "+ pjKey );
		if (rcaMapList!=null && rcaMapList.size()>0) {
			//If mapping exists get the row
			rcaMap = rcaMapList.get(0);
			logger.info("-----------saveRcaReportSubtaskMapping----------------------- "+rcaMap.getGrpOptionId());
		}
		else { 
			//else create a row
			rcaMap = ao.create(RCASubtaskMap.class);
			rcaMap.setCreatedDate(createdate);
			logger.info("Successfully created");
		}
		rcaMap.setProjectKey(pjKey);
		rcaMap.setType(type);
		rcaMap.setGrpOptionId(grpoptionid);
		rcaMap.setGrpOptionName(grpoptionname);
		rcaMap.setCldGrpOptionId(cldgrpoptionid);
		rcaMap.setCldGrpOptionName(cldgrpoptionname);
		rcaMap.setRoles(roles);
		rcaMap.setUsers(users);
		rcaMap.setModifiedDate(miodifieddate);
		rcaMap.setModifiedBy(miodifiedby);
		rcaMap.save();	
		logger.info("-----------saveRcaReportSubtaskMapping-----------------------Successfully updated------------------------- "); 
    }
    

    @Override
   	public List<RCASubtaskMap> getRcaReportSubtaskMappingByProjectKey(String pjKey){    		
	   		if (pjKey == null) return null;
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY=?", pjKey); 
   			return newArrayList(ao.find(RCASubtaskMap.class,query));
       }
    
    
    @Override    
    public List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetails(String pjKey, String type,String grpoptionname,String cldgrpoptionname){   
	   		logger.info("-----------getRcaReportSubtaskMappingParentChildDetails---------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname + " cldgrpoptionname: "+ cldgrpoptionname  );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_NAME = ?", pjKey,type, grpoptionname,cldgrpoptionname);
	   		List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
	   			logger.info("-----------getRcaReportSubtaskMappingParentChildDetails-----------------------rcaMapList size--------------------- "+ rcaMapList.size() );
			if (rcaMapList!=null && rcaMapList.size()>0) {					
				RCASubtaskMap rcaMap = (RCASubtaskMap) rcaMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname+ " cldgrpoptionname: "+ cldgrpoptionname);
			}	   		
   			return rcaMapList;
       }

    @Override
   	public List<RCASubtaskMap> getRcaReportSubtaskMappingParentDetails(String pjKey, String type,String grpoptionname){     
	   		logger.info("-----------getRcaReportSubtaskMappingParentDetails---------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_NAME = ?", pjKey,type, grpoptionname);
	   		List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
	   			logger.info("-----------getRcaReportSubtaskMappingParentDetails-----------------------rcaMapList size--------------------- "+ rcaMapList.size() );
			if (rcaMapList!=null && rcaMapList.size()>0) {					
				RCASubtaskMap rcaMap = (RCASubtaskMap) rcaMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionname: "+ grpoptionname);
			}	   		
   			return rcaMapList;
       }

	@Override    
    public List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid){   
	   		logger.info("-----------getRcaReportSubtaskMappingParentChildDetailsById---------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " cldgrpoptionid: "+ cldgrpoptionid  );
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND CLD_GRP_OPTION_ID = ?", pjKey,type, grpoptionid,cldgrpoptionid);
	   		List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
	   			logger.info("-----------getRcaReportSubtaskMappingParentChildDetailsById-----------------------rcaMapList size--------------------- "+ rcaMapList.size() );
			if (rcaMapList!=null && rcaMapList.size()>0) {					
				RCASubtaskMap rcaMap = (RCASubtaskMap) rcaMapList.get(0);	
			}else{
				logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid+ " cldgrpoptionid: "+ cldgrpoptionid);
			}	   		
   			return rcaMapList;
       }
	
	  @Override   
	    public void saveRcaReportSubtaskMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby,String fourthLevelOptId,String fourthLevelOptName){
	  		RCASubtaskMap rcaMap = null;
			logger.info("-----------saveRcaReportSubtaskMapping--------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " grpoptionname: "+ grpoptionname+ " cldgrpoptionid: "+ cldgrpoptionid + " cldgrpoptionname: "+ cldgrpoptionname+" role: "+ roles +" users: "+ users + " createdate: "+ createdate + " miodifieddate: "+ miodifieddate + " miodifiedby: "+ miodifiedby);

			Query query = Query.select();
			query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND GRP_OPTION_NAME = ? AND CLD_GRP_OPTION_ID = ? AND CLD_GRP_OPTION_NAME = ? AND FOURTH_LEVEL_OPTION_ID = ? AND FOURTH_LEVEL_OPTION_NAME = ? AND ROLES = ?", pjKey,type, grpoptionid, grpoptionname, cldgrpoptionid, cldgrpoptionname, fourthLevelOptId,fourthLevelOptName, roles);
			
			List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
			logger.info("-----------saveRcaReportSubtaskMapping-----------------------pjKey------------------------- "+ pjKey );
			if (rcaMapList!=null && rcaMapList.size()>0) {
				//If mapping exists get the row
				rcaMap = rcaMapList.get(0);
				logger.info("-----------saveRcaReportSubtaskMapping----------------------- "+rcaMap.getGrpOptionId());
			}
			else { 
				//else create a row
				rcaMap = ao.create(RCASubtaskMap.class);
				rcaMap.setCreatedDate(createdate);
				logger.info("Successfully created");
			}
			rcaMap.setProjectKey(pjKey);
			rcaMap.setType(type);
			rcaMap.setGrpOptionId(grpoptionid);
			rcaMap.setGrpOptionName(grpoptionname);
			rcaMap.setCldGrpOptionId(cldgrpoptionid);
			rcaMap.setCldGrpOptionName(cldgrpoptionname);
			rcaMap.setFourthLevelOptionId(fourthLevelOptId);
			if(fourthLevelOptId.equals("None"))
				rcaMap.setFourthLevelOptionName("None");
			else 
				rcaMap.setFourthLevelOptionName(fourthLevelOptName);
			
			rcaMap.setRoles(roles);
			rcaMap.setUsers(users);
			rcaMap.setModifiedDate(miodifieddate);
			rcaMap.setModifiedBy(miodifiedby);
			rcaMap.save();	
			logger.info("-----------saveRcaReportSubtaskMapping-----------------------Successfully updated------------------------- "); 
	    }
	   
	  @Override    
	    public List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid,String fourthLevelOptId){   
		   		logger.info("-----------getRcaReportSubtaskMappingParentChildDetailsById---------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid + " cldgrpoptionid: "+ cldgrpoptionid  + " fourthLevelOptId: "+ fourthLevelOptId);
		   		Query query = Query.select();
		   		query = query.where("PROJECT_KEY = ? AND TYPE = ? AND GRP_OPTION_ID = ? AND CLD_GRP_OPTION_ID = ? AND FOURTH_LEVEL_OPTION_ID = ?", pjKey,type, grpoptionid,cldgrpoptionid,fourthLevelOptId);
		   		List<RCASubtaskMap> rcaMapList = newArrayList(ao.find(RCASubtaskMap.class,query));
		   			logger.info("-----------getRcaReportSubtaskMappingParentChildDetailsById-----------------------rcaMapList size--------------------- "+ rcaMapList.size() );
				if (rcaMapList!=null && rcaMapList.size()>0) {					
					RCASubtaskMap rcaMap = (RCASubtaskMap) rcaMapList.get(0);	
				}else{
					logger.info("-----------No Mapping Available for------------------------pKey: "+ pjKey + " type: "+ type + " grpoptionid: "+ grpoptionid+ " cldgrpoptionid: "+ cldgrpoptionid);
				}	   		
	   			return rcaMapList;
	       }
		
}
