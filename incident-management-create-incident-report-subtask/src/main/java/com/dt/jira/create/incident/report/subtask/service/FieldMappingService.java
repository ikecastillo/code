package com.dt.jira.create.incident.report.subtask.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.create.incident.report.subtask.ao.IRSubtaskFieldMap;
import com.dt.jira.create.incident.report.subtask.rest.FieldMappingFields;


import java.util.List;
/**
 * This class is used for Filed Mapping services.
 * 
 * @author Vijay.Badam
 */
@Transactional
public interface FieldMappingService {

	IRSubtaskFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
	List<IRSubtaskFieldMap> findAll();
	
	List<IRSubtaskFieldMap> findAllByParentAndChildIssue(String parentIssue,String childIssue);
	
	//List<Field> getAllCustomFieldsFromDb();
	
	void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType);
	
}