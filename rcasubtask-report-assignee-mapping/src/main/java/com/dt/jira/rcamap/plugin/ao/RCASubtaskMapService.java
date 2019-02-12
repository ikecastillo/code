package com.dt.jira.rcamap.plugin.ao;

import java.util.List;
import java.util.Map;

import com.atlassian.activeobjects.tx.Transactional;

/**
 * The RCA Report Subtask Map Service for RCA Report Subtask Assignee Map on Problem Management project
 *
 * @author Firoz Khan
 */
@Transactional
public interface RCASubtaskMapService
{

   
    void saveRcaReportSubtaskMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby);
    
    void saveRcaReportSubtaskMapping(String pjKey,String type,String grpoptionid,String grpoptionname,String cldgrpoptionid,String cldgrpoptionname,String roles,String users,String createdate,String miodifieddate,String miodifiedby,String fourthLevelOptId,String fourthLevelOptName);
   	
	List<RCASubtaskMap> getRcaReportSubtaskMappingByProjectKey(String pjKey);
	
	List<RCASubtaskMap> getRcaReportSubtaskMappingParentDetails(String pjKey,String type,String grpoptionname);
	
	List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetails(String pjKey,String type,String grpoptionname,String cldgrpoptionname);
	
	List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetailsById(String pjKey,String type,String grpoptionid,String cldgrpoptionid);
			
	List<RCASubtaskMap> getRcaReportSubtaskMappingParentChildDetailsById(String pjKey,String type,String grpoptionid,String cldgrpoptionid,String fourthLevelOptId);
	
}