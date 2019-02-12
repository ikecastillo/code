package com.dt.jira.problem.rcasubtask.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.problem.rcasubtask.ao.RCASubtaskFieldMap;
import com.dt.jira.problem.rcasubtask.rest.FieldMappingFields;


import java.util.List;
/**
 * This class is used for Filed Mapping services.
 * 
 * @author Vijay.Badam
 */
@Transactional
public interface FieldMappingService {

	RCASubtaskFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
	List<RCASubtaskFieldMap> findAll();
	
	List<RCASubtaskFieldMap> findAllByParentAndChildIssue(String parentIssue,String childIssue);
	
	//List<Field> getAllCustomFieldsFromDb();
	
	void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
}