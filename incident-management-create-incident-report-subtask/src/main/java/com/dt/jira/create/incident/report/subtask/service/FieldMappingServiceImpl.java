package com.dt.jira.create.incident.report.subtask.service;

import com.atlassian.activeobjects.external.ActiveObjects;

import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.create.incident.report.subtask.ao.IRSubtaskFieldMap;
import com.dt.jira.create.incident.report.subtask.rest.FieldMappingFields;
import com.dt.jira.create.incident.report.subtask.rest.FieldMappingRestAPI;

import net.java.ao.Query;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
/**
 * This class is used for Filed Mapping service implementation.
 * 
 * @author Firoz.Khan
 */
public final class FieldMappingServiceImpl implements FieldMappingService {

	private final ActiveObjects ao;
	private final Logger logger = Logger.getLogger(FieldMappingServiceImpl.class);
	
	public FieldMappingServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}
/**
 * Save the entity to the database.
 */
	 @Override
	public IRSubtaskFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		
		final IRSubtaskFieldMap IRSubtaskFieldMap = ao.create(IRSubtaskFieldMap.class);
		IRSubtaskFieldMap.setJiraField(jiraField);
		IRSubtaskFieldMap.setMappingField(mappingField);
		IRSubtaskFieldMap.setFromIssueType(fromIssueType);
		IRSubtaskFieldMap.setToIssueType(toIssueType);
		IRSubtaskFieldMap.setComplete(false);
		IRSubtaskFieldMap.save();
		logger.info("------saving Fields---------");
		return IRSubtaskFieldMap;
	} 
	 /**
	  * Get all entities from the database.
	  */
	@Override
	public List<IRSubtaskFieldMap> findAll() {
		return newArrayList(ao.find(IRSubtaskFieldMap.class));
	}
	 /**
	  * Find all entities from the database by passing source issue type and target issue type.
	  */
	@Override
	public List<IRSubtaskFieldMap> findAllByParentAndChildIssue(String parentIssueType,String childIssueType){
		Query query = Query.select();
		query = query.where("FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",parentIssueType,childIssueType);
		return newArrayList(ao.find(IRSubtaskFieldMap.class,query));
	}

	 /**
	  * Delete entity from the database.
	  */
	@Override
	public void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		IRSubtaskFieldMap IRSubtaskFieldMap = null;	
		Query query = Query.select();
		query = query.where("JIRA_FIELD=? AND MAPPING_FIELD=? AND FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",jiraField, mappingField,fromIssueType,toIssueType);
		List<IRSubtaskFieldMap> fieldList = newArrayList(ao.find(IRSubtaskFieldMap.class,query));
		if (fieldList != null && fieldList.size() > 0) {
			 IRSubtaskFieldMap=fieldList.get(0)	;		
			ao.delete(IRSubtaskFieldMap);
		}else{
			logger.info("-----------------------not found element------------------------- ");	
		}
	}

	
}