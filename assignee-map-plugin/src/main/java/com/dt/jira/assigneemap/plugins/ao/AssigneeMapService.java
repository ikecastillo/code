package com.dt.jira.assigneemap.plugins.ao;

import com.atlassian.activeobjects.tx.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * The Service for Assignee Map Active Object within Change Management project
 *
 * @author Firoz.Khan
 */ 
@Transactional
public interface AssigneeMapService
{

    void saveMapping(String pjKey,String solutionGroup,String impact,String status,String users);
   	
	List<AssigneeMap> getAssigneeMappingByProjectKey(String pjKey);
	
	List<AssigneeMap> getAssigneeMappingDetails(String pjKey,String solutionGroup,String impact);
	
}