package com.dt.jira.incident.problem.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.incident.problem.ao.ProblemFieldMap;



import java.util.List;
/**
 * This class is used for portal configuration services.
 * 
 * @author Vijay.Badam
 */
@Transactional
public interface FieldMappingService {

	ProblemFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
	List<ProblemFieldMap> findAll();
	
	List<ProblemFieldMap> findAllByParentAndChildIssue(String parentIssue,String childIssue);
	
	void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
}