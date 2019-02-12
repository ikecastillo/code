package com.dt.jira.incident.problem.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.incident.problem.ao.ProblemFieldMap;


import net.java.ao.Query;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
/**
 * This class is used for portal configuration services implementation.
 * 
 * @author Firoz.Khan
 */
public final class FieldMappingServiceImpl implements FieldMappingService {

	private final ActiveObjects ao;

	public FieldMappingServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

	 @Override
	public ProblemFieldMap create(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		
		final ProblemFieldMap fieldmapping = ao.create(ProblemFieldMap.class);
		fieldmapping.setFromIssueType(fromIssueType);
		fieldmapping.setJiraField(jiraField);
		fieldmapping.setToIssueType(toIssueType);
		fieldmapping.setMappingField(mappingField);
		fieldmapping.save();		
		return fieldmapping;
	} 
	
	
	@Override
	public List<ProblemFieldMap> findAll() {
		return newArrayList(ao.find(ProblemFieldMap.class));
	}
	
	@Override
	public List<ProblemFieldMap> findAllByParentAndChildIssue(String parentIssue,String childIssue){
		Query query = Query.select();
		query = query.where("FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",parentIssue,childIssue);
		return newArrayList(ao.find(ProblemFieldMap.class,query));
	}
	
	
	@Override
	public void delete(String jiraField,String mappingField,String fromIssueType,String toIssueType) {
		ProblemFieldMap fieldmapping = null;
		
		Query query = Query.select();
		query = query.where("JIRA_FIELD=? AND MAPPING_FIELD=? AND FROM_ISSUE_TYPE=? AND TO_ISSUE_TYPE=?",jiraField, mappingField,fromIssueType,toIssueType);
		List<ProblemFieldMap> fieldList = newArrayList(ao.find(ProblemFieldMap.class,query));
		if (fieldList != null && fieldList.size() > 0) {
			 fieldmapping=fieldList.get(0);			
			ao.delete(fieldmapping);
		}else{
			System.out.println("-----------------------not found element------------------------- ");	
		}
	}

	
}