package com.dt.jira.assigneemap.plugins.ao;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.jira.plugin.event.ComponentChangeEventListener;
/**
 * The Service Implementation for Assignee Map Active Object within Change Management project
 *
 * @author Firoz.Khan
 */ 
public class AssigneeMapServiceImpl implements AssigneeMapService
{
	private final Logger logger = Logger.getLogger(AssigneeMapServiceImpl.class);
    private final ActiveObjects ao;
 /**
     * Constructor
     * @param ao the ActiveObjects to be injected
     */
    public AssigneeMapServiceImpl(ActiveObjects ao)
    {
        this.ao = checkNotNull(ao);
    }

    
	/**
     * Save the assignee mapping details into JIRA DB based on the solution group, Impacted function within selected project
     * @param pjKey the String
     * @param solutionGroup the String
	 * @param impact the String
	 * @param status the String
	 * @param users the String
     */
    @Override
	public void saveMapping(String pjKey, String solutionGroup,String impact,String status,String users){
		AssigneeMap assigneeMap = null;
		logger.info("saveMapping--------pKey: "+ pjKey + " solutionGroup: "+ solutionGroup + " impact: "+ impact + " status: "+ status );

		Query query = Query.select();
		query = query.where("project_key = ? AND solution_group = ? AND impact = ? AND status = ?", pjKey,solutionGroup, impact, status);
		//System.out.println("query>>>>>>>>>>>>>>>>>>> "+query);
		List<AssigneeMap> mapList = newArrayList(ao.find(AssigneeMap.class,query));
		logger.info("saveMapping-----------------------pjKey------------------------- "+ pjKey );
   		logger.info("saveMapping-----------------------query------------------------- "+ query.getWhereClause() );
		logger.info("saveMapping-----------------------mapList size------------------ "+ mapList.size() );
		
		if (mapList!=null && mapList.size()>0) {
			//If mapping exists get the row
			assigneeMap = mapList.get(0);
			logger.info("$$$$$$ "+assigneeMap.getImpact());
		}
		else { 
			//else create a row
			assigneeMap = ao.create(AssigneeMap.class);
			logger.info("Successfully created");
		}
		assigneeMap.setProjectKey(pjKey);
		assigneeMap.setSolutionGroup(solutionGroup);
		assigneeMap.setImpact(impact);
		assigneeMap.setStatus(status);
		assigneeMap.setUsers(users);
		assigneeMap.save();	
		logger.info("saveMapping-----------------------Successfully updated------------------------- "); 
    }
    

	/**
     * Gets the assignee mapping details from JIRA DB based on the selected project
     * @param pjKey the String
	 * @return the List<AssigneeMap>
     */
    @Override
   	public List<AssigneeMap> getAssigneeMappingByProjectKey(String pjKey){    		
	   		if (pjKey == null) return null;
	   		Query query = Query.select();
	   		query = query.where("PROJECT_KEY=?", pjKey);   		
	   		logger.info("getAssigneeMappingByProjectKey-----------------------pjKey------------------- "+ pjKey ); 
			List<AssigneeMap> mapList = newArrayList(ao.find(AssigneeMap.class,query));
			logger.info("getAssigneeMappingByProjectKey Assignee Mapping Table Name>>>>>>>>>>>>>>>>>>> "+query.getTable());
			logger.info("getAssigneeMappingDetails-----------------------Record Available in DB------- "+ mapList.size() );
   			//return newArrayList(ao.find(AssigneeMap.class,query));
			return mapList;
       }
    
    /**
     * Gets the assignee mapping details from JIRA DB based on the solution group, Impacted function within selected project
     * @param pjKey the String
	 * @param solutionGroup the String
	 * @param impact the String
	 * @return the List<AssigneeMap>
     */
    @Override
   	public List<AssigneeMap> getAssigneeMappingDetails(String pjKey, String solutionGroup,String impact){ 
    
	   		logger.info("getAssigneeMappingDetails---------pKey: "+ pjKey + " solutionGroup: "+ solutionGroup + " impact: "+ impact );
	   		Query query = Query.select();
	   		query = query.where("project_key = ? AND solution_group = ? AND impact = ?", pjKey,solutionGroup, impact);
	   		List<AssigneeMap> mapList = newArrayList(ao.find(AssigneeMap.class,query));
	   		logger.info("getAssigneeMappingDetails-----------------------pjKey------------------------- "+ pjKey );
	   		logger.info("getAssigneeMappingDetails-----------------------query where------------------- "+ query.getWhereClause() );
			logger.info("getAssigneeMappingDetails-----------------------mapList size------------------ "+ mapList.size() );
			if (mapList!=null && mapList.size()>0) {
				
				logger.info("getAssigneeMappingDetails-----------------------mapList value------------- "+ (AssigneeMap) mapList.get(0) );
				AssigneeMap _asignMap = (AssigneeMap) mapList.get(0);
				
				logger.info("getAssigneeMappingDetails-----------------------mapList getProjectKey----- "+ _asignMap.getProjectKey() );
				logger.info("getAssigneeMappingDetails-----------------------mapList getSolutionGroup-- "+ _asignMap.getSolutionGroup());
				logger.info("getAssigneeMappingDetails-----------------------mapList getImpact--------- "+ _asignMap.getImpact() );
				logger.info("getAssigneeMappingDetails-----------------------mapList getStatus--------- "+ _asignMap.getStatus() );
				logger.info("getAssigneeMappingDetails-----------------------mapList getStatus--------- "+ _asignMap.getUsers());
				
				
			}
	   		
   			return mapList;
       }

}
