package com.dt.jira.impm.rolebased.mapping.service;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import net.java.ao.Query;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.jira.impm.rolebased.mapping.ao.IMPMRoleBased;
import com.dt.jira.impm.rolebased.mapping.rest.IMPMRoleBasedRest;

/**
 * This services implementation classs is used for Add/Delete and View Incident and Problem Management roles and group mapping from DB. 
 * @author Firoz.Khan
 */

public final class IMPMRoleBasedMappingServiceImpl implements IMPMRoleBasedMappingService {
	private final Logger logger = Logger.getLogger(IMPMRoleBasedRest.class);
	private final ActiveObjects ao;

	public IMPMRoleBasedMappingServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

/**
 * Save the entity to the database.
 */
	 @Override
	public IMPMRoleBased create(String projectKey,String issueType,String solutionGroup,String projectRole) {
	
		IMPMRoleBased[] roles = ao.find(IMPMRoleBased.class, Query.select().where("PROJECT_KEY=? AND ISSUE_TYPE=? AND SOLUTION_GROUP=? AND PROJECT_ROLE=?",projectKey,issueType,solutionGroup,projectRole));
       
		if (roles.length == 0)
        {
        	final IMPMRoleBased roleBasedObj = ao.create(IMPMRoleBased.class);
    		roleBasedObj.setProjectKey(projectKey);
    		roleBasedObj.setIssueType(issueType);
    		roleBasedObj.setSolutionGroup(solutionGroup);
    		roleBasedObj.setProjectRole(projectRole);		
    		roleBasedObj.setComplete(false);
    		roleBasedObj.save();
    		
    		return roleBasedObj;
    		
        } else {
        	  throw new IllegalStateException("Duplicate Entry Exist for Solution Group! " + solutionGroup);
	    }		
	} 
	
	/**
	  * Get all entities from the database.
	  */
	@Override
	public List<IMPMRoleBased> findAll() {
		return newArrayList(ao.find(IMPMRoleBased.class));
	}
	
	
	 /**
	  * Is project roles exists into database by passing search parameters.
	  */
	 @Override
		public boolean isProjectRoleExist(String projectKey,String issueType, String solGroup,String projectRole) {
			boolean isExist=false;
			IMPMRoleBased[] roles = ao.find(IMPMRoleBased.class, Query.select().where("PROJECT_KEY=? AND ISSUE_TYPE=? AND SOLUTION_GROUP=? AND PROJECT_ROLE=?",projectKey,issueType,solGroup,projectRole));
		
			if (roles.length > 0) {
				isExist= true;
			}else{
				isExist= false;
			}
			return isExist;		
		}
	 
	 /**
	  * Get all existing project roles from the database by passing search parameters.
	  */
	 @Override
		public List<IMPMRoleBased> findProjectRoleExist(String projectKey,String issueType, String solGroup,String projectRole) {
		 return newArrayList( ao.find(IMPMRoleBased.class, Query.select().where("PROJECT_KEY=? AND ISSUE_TYPE=? AND SOLUTION_GROUP=? AND PROJECT_ROLE=?",projectKey,issueType,solGroup,projectRole)));
	}
	 
	 /**
	  * Delete entity from the database.
	  */	  
	@Override
	public void delete(String projectKey,String issueType,String solutionGroup,String projectRole) {
		IMPMRoleBased roleBasedObj = null;
		logger.info("projectKey---> "+projectKey+" issueType---> "+issueType+" solutionGroup--->"+solutionGroup+" projectRole---> "+projectRole);
		Query query = Query.select();
		query = query.where("PROJECT_KEY=? AND ISSUE_TYPE=? AND SOLUTION_GROUP=? AND PROJECT_ROLE=?",projectKey,issueType,solutionGroup, projectRole);
		List<IMPMRoleBased> roleBasedList = newArrayList(ao.find(IMPMRoleBased.class,query));
		if (roleBasedList != null && roleBasedList.size() > 0) {
			roleBasedObj=roleBasedList.get(0)	;
			logger.info("-----------------------Successfully found element------------------------- "+roleBasedObj);
			ao.delete(roleBasedObj);
		}else{
			logger.info("-----------------------not found element------------------------- ");	
		}
	}

	/**
	  * Get all project roles from the database by passing search parameters.
	  */
	@Override
	public List<IMPMRoleBased> findProjectRoleBySolutionGroup(String projectKey, String issueType, String solGroup) {
		return newArrayList( ao.find(IMPMRoleBased.class, Query.select().where("PROJECT_KEY=? AND ISSUE_TYPE=? AND SOLUTION_GROUP=?",projectKey,issueType,solGroup)));
		
	}
}