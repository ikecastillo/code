package com.dt.jira.incidentmap.plugin.ao;

import java.util.List;
import java.util.Map;

import com.atlassian.activeobjects.tx.Transactional;

/**
 * The Incident Map Service for Incident Report Assignee Map on Incident Management project
 *
 * @author Firoz Khan
 */
@Transactional
public interface IncidentMapService
{

   
    void saveIncidentMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby);
    
    void saveIncidentMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby,String fourthLevelOptId,String fourthLevelOptName);
   	
	List<IncidentMap> getIncidentMappingByProjectKey(String pjKey);
	
	List<IncidentMap> getIncidentMappingParentDetails(String pjKey,String type,String grpoptionname);
	
	List<IncidentMap> getIncidentMappingParentChildDetails(String pjKey,String type,String grpoptionname,String cldgrpoptionname);
	
	List<IncidentMap> getIncidentMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid);
	
	List<IncidentMap> getIncidentMappingParentChildDetailsById(String pjKey, String type,String grpoptionid,String cldgrpoptionid,String fourthLevelOptId);
	
}