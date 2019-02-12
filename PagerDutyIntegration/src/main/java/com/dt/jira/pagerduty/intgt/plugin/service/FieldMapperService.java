package com.dt.jira.pagerduty.intgt.plugin.service;

import java.util.List;

import com.atlassian.activeobjects.tx.Transactional;
import com.dt.jira.pagerduty.intgt.plugin.rest.FieldBean;
/**
 * The Service for Assignee Map Active Object within Change Management project
 *
 * @author Firoz.Khan
 */ 
@Transactional
public interface FieldMapperService
{

	FieldBean addMapping(final FieldBean fieldBean);
    
    List<FieldBean> getMapping(final FieldBean fieldBean);
   	
    FieldBean deleteMapping(final FieldBean fieldBean);
	
	List<FieldBean> getAllMappingsFromDB();
	
}