package com.dt.jira.impm.rolebased.mapping.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.dt.jira.impm.rolebased.mapping.ao.IMPMRoleBased;
/**
 * This interface is used for Incident and Problem Management roles and group mapping services.
 * 
 * @author Firoz.Khan
 */
@Transactional
public interface IMPMRoleBasedMappingService {

	IMPMRoleBased create(String projectKey,String issueType,String solutionGroup,String projectRoles);
	
	List<IMPMRoleBased> findAll();
	
	boolean isProjectRoleExist(String projectKey,String issueType, String solGroup,String projectRole);
	
	List<IMPMRoleBased> findProjectRoleBySolutionGroup(String projectKey,String issueType,String solGroup);
	
	void delete(String projectKey,String issueType,String solutionGroup,String projectRoles);
	
	List<IMPMRoleBased> findProjectRoleExist(String projectKey,String issueType, String solGroup,String projectRole);
	
}