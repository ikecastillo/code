package com.dt.jira.problem.rcasubtask.service;

import com.atlassian.activeobjects.external.ActiveObjects;


import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.problem.rcasubtask.ao.RCASubtaskFieldMap;
import com.dt.jira.problem.rcasubtask.rest.FieldMappingFields;
import com.dt.jira.problem.rcasubtask.rest.FieldMappingRestAPI;

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
	public RCASubtaskFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		
		final RCASubtaskFieldMap RCASubtaskFieldMap = ao.create(RCASubtaskFieldMap.class);
		RCASubtaskFieldMap.setJiraField(jiraField);
		RCASubtaskFieldMap.setMappingField(mappingField);
		RCASubtaskFieldMap.setFromIssueType(fromIssueType);
		RCASubtaskFieldMap.setToIssueType(toIssueType);
		RCASubtaskFieldMap.setComplete(false);
		RCASubtaskFieldMap.save();
		logger.info("------saving Fields---------");
		return RCASubtaskFieldMap;
	} 
	 /**
	  * Get all entities from the database.
	  */
	@Override
	public List<RCASubtaskFieldMap> findAll() {
		return newArrayList(ao.find(RCASubtaskFieldMap.class));
	}
	 /**
	  * Find all entities from the database by passing source issue type and target issue type.
	  */
	@Override
	public List<RCASubtaskFieldMap> findAllByParentAndChildIssue(String parentIssueType,String childIssueType){
		Query query = Query.select();
		query = query.where("FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",parentIssueType,childIssueType);
		return newArrayList(ao.find(RCASubtaskFieldMap.class,query));
	}

	 /**
	  * Delete entity from the database.
	  */
	@Override
	public void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		RCASubtaskFieldMap RCASubtaskFieldMap = null;	
		Query query = Query.select();
		query = query.where("JIRA_FIELD=? AND MAPPING_FIELD=? AND FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",jiraField, mappingField,fromIssueType,toIssueType);
		List<RCASubtaskFieldMap> fieldList = newArrayList(ao.find(RCASubtaskFieldMap.class,query));
		if (fieldList != null && fieldList.size() > 0) {
			 RCASubtaskFieldMap=fieldList.get(0)	;		
			ao.delete(RCASubtaskFieldMap);
		}else{
			logger.info("-----------------------not found element------------------------- ");	
		}
	}

	
}