package com.dt.jira.plugin.service;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.jira.issue.fields.Field;

import com.dt.jira.plugin.ao.Template;
import com.dt.jira.plugin.ao.Section;

import com.dt.jira.plugin.rest.TemplateModel;
import com.dt.jira.plugin.rest.SectionModel;

import java.util.List;
import java.util.Map;


/**
 * This class is used for portal configuration services.
 * 
 * @author Srinadh.Garlapati
 */
@Transactional
public interface TemplateService {

	TemplateModel create(String templateName,String mailTo,String mail_from,String subject,String mail_body,String schedule_time);
    
    SectionModel create(String sectionName,String jqlQuery,String columns,String headers,java.util.Date createDate,java.util.Date updateDate,String templateId);
    
     Map<TemplateModel,List<SectionModel>> findTemplateByName(String templateName); 
	 
	 List<TemplateModel> getAllTemplates();
    
   String findSceduleTimeByTemplateName(String templateName);
	
}