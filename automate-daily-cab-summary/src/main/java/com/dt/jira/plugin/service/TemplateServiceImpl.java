package com.dt.jira.plugin.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.issue.fields.Field;
import com.dt.jira.plugin.ao.Template;
import com.dt.jira.plugin.ao.Section;


import net.java.ao.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import com.dt.jira.plugin.rest.TemplateModel;
import com.dt.jira.plugin.rest.SectionModel;
import org.apache.log4j.Logger;
/**
 * This class is used for portal configuration services implementation.
 * 
 * @author Srinadh.Garlapati
 */
public final class TemplateServiceImpl implements TemplateService {

	private final ActiveObjects ao;
    private final Logger logger = Logger.getLogger(TemplateServiceImpl.class);

	public TemplateServiceImpl(ActiveObjects ao) {
		this.ao = checkNotNull(ao);
	}

    
    
    
		
    
    
	 @Override
	public TemplateModel create(String templateName,String mailTo,String mail_from,String subject,String mail_body,String schedule_time) {
		Template template = null;
        TemplateModel templateModel=null;
		Query query = Query.select();
		query = query.where("template_name=? AND mail_to=?",templateName, mailTo);
		List<Template> templateList = newArrayList(ao.find(Template.class,query));
		logger.info("-------templateList------------------"+templateList);
		if (templateList == null || templateList.size() <= 0) {
		template = ao.create(Template.class);
        template.setTemplateName(templateName);
        template.setMailTo(mailTo);
        template.setMailFrom(mail_from);
        template.setSubject(subject);
        template.setMailBody(mail_body);
        template.setScheduleTime(schedule_time);    
        template.save();
         if(template.getID()!=0){
        templateModel= new  TemplateModel(template.getID(),template.getTemplateName(),template.getMailTo(),template.getMailFrom(),template.getSubject(),template.getMailBody(),template.getScheduleTime());  
        }
        }
        return templateModel;     
      
	}
    
    
   
	
	 @Override
	public SectionModel create(String sectionName,String jqlQuery,String columns,String headers,java.util.Date createDate,java.util.Date updateDate,String templateId) {
		 Section section = null;
		Query query = Query.select();
		query = query.where("section_name=? AND jql_query=? AND columns=?  AND template_id=?",sectionName, jqlQuery,columns, templateId);
		List<Section> sectionList = newArrayList(ao.find(Section.class,query));
		logger.info("-------sectionList--------"+sectionList);
		if (sectionList == null || sectionList.size() <= 0) {
		section = ao.create(Section.class);
		section.setSectionName(sectionName);
		section.setJqlQuery(jqlQuery);
        section.setColumns(columns);
        section.setHeaders(headers);
		section.setCreateDate(createDate);
        section.setUpdateDate(updateDate);
        section.setTemplateId(templateId);
		section.save();
		//logger.info("------saving Fields---------");
		SectionModel sectionModel=null;
        if(section.getID()!=0){
        sectionModel= new  SectionModel(section.getID(),section.getSectionName(),section.getJqlQuery(),section.getColumns(),section.getHeaders(),section.getCreateDate() ,section.getUpdateDate(),section.getTemplateId());  
        }
        return sectionModel;
        }else{
        return null;     
       }
	} 
    
    
    @Override
	public Map<TemplateModel,List<SectionModel>> findTemplateByName(String templateName) {
        Map<TemplateModel,List<SectionModel>>  templateSectionModelList=new HashMap<TemplateModel,List<SectionModel>>();
		List<Template> templateList= newArrayList(ao.find(Template.class, Query.select().where("template_name=?",templateName)));
        logger.info("----------------------templateList-----------------"+templateList);
        if(templateList!=null){
         for(Template template:templateList){
             List<Section> SectionList= newArrayList(ao.find(Section.class, Query.select().where("template_id=?",template.getID())));
              logger.info("----------------------SectionList-----------------"+SectionList);
                    templateSectionModelList.put( (new  TemplateModel(template.getID(),template.getTemplateName(),template.getMailTo(),template.getMailFrom(),template.getSubject(),template.getMailBody(),template.getScheduleTime())),getSectionModelList(SectionList));
               
             
         }   
       }
    return templateSectionModelList;
	}
	
	
	
	@Override
	public	List<TemplateModel> getAllTemplates(){
	List<Template> templateList= newArrayList(ao.find(Template.class));
	List<TemplateModel> templateModelList=new ArrayList<TemplateModel>();
	if(templateList!=null){
         for(Template template:templateList){
		 templateModelList.add(new  TemplateModel(template.getID(),template.getTemplateName(),template.getMailTo(),template.getMailFrom(),template.getSubject(),template.getMailBody(),template.getScheduleTime()));
		 
		   }
		 }
	return templateModelList;
	}
    
     @Override
	public String findSceduleTimeByTemplateName(String templateName) {
        List<Template> templateList= newArrayList(ao.find(Template.class, Query.select().where("template_name=?",templateName)));
        System.out.println("templateList "+templateList);
        String scheduleTime=null;
        if(templateList!=null){
          scheduleTime=templateList.get(0).getScheduleTime();
               
             
         }
         System.out.println("scheduleTime  "+scheduleTime);
    return scheduleTime;
	}
	
	
private List<SectionModel>  getSectionModelList(List<Section>  sectionList){
    List<SectionModel> sectionModelList=new ArrayList<SectionModel>();
    if(sectionList!=null && (!sectionList.isEmpty())){
       for(Section secObj:sectionList){
           sectionModelList.add(new  SectionModel(secObj.getID(),secObj.getSectionName(),secObj.getJqlQuery(),secObj.getColumns(),secObj.getHeaders(),secObj.getCreateDate() ,secObj.getUpdateDate(),secObj.getTemplateId()) );
           
       } 
        
    }
    
    return sectionModelList;
 }
	
}