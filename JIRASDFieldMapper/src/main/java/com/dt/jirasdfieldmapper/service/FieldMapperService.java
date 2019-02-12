package com.dt.jirasdfieldmapper.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.dt.jirasdfieldmapper.rest.FieldBean;

import java.util.List;

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