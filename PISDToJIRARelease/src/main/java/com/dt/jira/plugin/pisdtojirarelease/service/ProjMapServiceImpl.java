package com.dt.jira.plugin.pisdtojirarelease.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.jira.plugin.pisdtojirarelease.ao.ProjMapDB;
import com.dt.jira.plugin.pisdtojirarelease.rest.ProjMapBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 * This class contains methods to add, Delete and Get eth available mappings from database.
 */
public class ProjMapServiceImpl implements ProjMapService{

    private final Logger log = LoggerFactory.getLogger(ProjMapServiceImpl.class);
    private final ActiveObjects ao;

    public ProjMapServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }

    /*This Method adds a new mapping to database*/

    @Override
    public ProjMapBean addMapping(ProjMapBean projMapBean) {

        ProjMapBean alreadyExistBean = getMapping(projMapBean);
        if (alreadyExistBean == null) {
            ao.executeInTransaction(() -> addProjMapToAO(projMapBean));
        }
        return null;
    }

    /*This Method fetches an existing project mapping from database*/
    @Override
    public ProjMapBean getMapping(ProjMapBean projMapBean) {
        ProjMapBean alreadyExistBean = null;
        //jiraProjIssueType
        ProjMapDB[] projMapDBs = ao.find(ProjMapDB.class, " SD_PROJ_NAME = ? AND SD_PROJ_KEY = ? AND JIRA_PROJ_ISSUE_TYPE = ? ",
                projMapBean.getSdProjName(), projMapBean.getSdProjKey(), projMapBean.getJiraProjIssueType());

        log.info("Number of config found (should be 1 or 0) : " + projMapDBs.length);
        
        for (ProjMapDB projMapDB : projMapDBs) {
            alreadyExistBean = new ProjMapBean();
            alreadyExistBean.setSdProjName(projMapDB.getSdProjName());
            alreadyExistBean.setSdProjKey(projMapDB.getSdProjKey());
            alreadyExistBean.setJiraProjName(projMapDB.getJiraProjName());
            alreadyExistBean.setJiraProjKey(projMapDB.getJiraProjKey());
            alreadyExistBean.setJiraProjIssueType(projMapDB.getJiraProjIssueType());
        }
        
        return alreadyExistBean;
    }

    /*This Method deletes mapping to database*/
    @Override
    public ProjMapBean deleteMapping(ProjMapBean projMapBean) {
        ao.executeInTransaction(() -> deleteProjMapFromAO(projMapBean));
        return null;
    }
    
    @Override
    public List<ProjMapBean> getAllMappingsFromDB() {
        List<ProjMapBean> projMapBeanList = new ArrayList<>();
        ProjMapBean projMapBean;
        ProjMapDB[] projMapDBs = ao.find(ProjMapDB.class);
		log.info("-------------------------------------------------------------------Find all Project Name Mapped to this---------------------------------");
		log.info("--------------------------------Number of Projects :"+projMapDBs.length+"---------------------------------------------------------------");
		log.info("-------------------------------------------------------------------Find all Project Name Mapped to this---------------------------------");
        for(ProjMapDB projMapDB : projMapDBs) {
            projMapBean = new ProjMapBean();
            projMapBean.setSdProjName(projMapDB.getSdProjName());
            projMapBean.setSdProjKey(projMapDB.getSdProjKey());
            projMapBean.setJiraProjName(projMapDB.getJiraProjName());
            projMapBean.setJiraProjKey(projMapDB.getJiraProjKey());
            projMapBean.setJiraProjIssueType(projMapDB.getJiraProjIssueType());
            projMapBeanList.add(projMapBean);
			log.info("-------------------------------------------------------------------Name of the project---------------------------------");
			log.info("--------------------------------Name :"+projMapDB.getSdProjName()+"---------------------------------------------------------------");
			log.info("-------------------------------------------------------------------Name of the project---------------------------------");
        }
        return projMapBeanList;
    }

    @Override
    public ProjMapBean getMappingBasedOnSDProj(ProjMapBean projMapBean) {
        ProjMapBean alreadyExistBean = null;
        //jiraProjIssueType
        ProjMapDB[] projMapDBs = ao.find(ProjMapDB.class, " SD_PROJ_NAME = ? AND SD_PROJ_KEY = ? ",
                projMapBean.getSdProjName(), projMapBean.getSdProjKey());

        log.info("Number of config found (should be 1 or 0) : " + projMapDBs.length);

        for (ProjMapDB projMapDB : projMapDBs) {
            alreadyExistBean = new ProjMapBean();
            alreadyExistBean.setSdProjName(projMapDB.getSdProjName());
            alreadyExistBean.setSdProjKey(projMapDB.getSdProjKey());
            alreadyExistBean.setJiraProjName(projMapDB.getJiraProjName());
            alreadyExistBean.setJiraProjKey(projMapDB.getJiraProjKey());
            alreadyExistBean.setJiraProjIssueType(projMapDB.getJiraProjIssueType());
        }

        return alreadyExistBean;
    }

    private ProjMapBean addProjMapToAO(ProjMapBean projMapBean) {
        final ProjMapDB projMapDB = ao.create(ProjMapDB.class);
        projMapDB.setSdProjName(projMapBean.getSdProjName());
        projMapDB.setSdProjKey(projMapBean.getSdProjKey());
        projMapDB.setJiraProjName(projMapBean.getJiraProjName());
        projMapDB.setJiraProjKey(projMapBean.getJiraProjKey());
        projMapDB.setJiraProjIssueType(projMapBean.getJiraProjIssueType());
        projMapDB.save();
        return projMapBean;
    }

    private ProjMapBean deleteProjMapFromAO(ProjMapBean projMapBean) {
        ProjMapDB[] projMapDBs = ao.find(ProjMapDB.class," SD_PROJ_NAME = ? AND SD_PROJ_KEY = ? AND JIRA_PROJ_ISSUE_TYPE = ? ",
                projMapBean.getSdProjName(),
                projMapBean.getSdProjKey(),
                projMapBean.getJiraProjIssueType());
        log.info("Project Mapping found (1 or 0)? " + projMapDBs.length);
        if (projMapDBs.length > 0) {
            log.info("Project Mapping Found, going to delete it");
            ao.delete(projMapDBs);
            log.info("Project Mapping Deleted");
        }
        return projMapBean;
    }
}
