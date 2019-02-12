package com.dt.jira.plugin.pisdtojirarelease.service;

import com.dt.jira.plugin.pisdtojirarelease.rest.ProjMapBean;

import java.util.List;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 */
public interface ProjMapService {

    ProjMapBean addMapping(final ProjMapBean projMapBean);
    ProjMapBean getMapping(final ProjMapBean projMapBean);
    ProjMapBean deleteMapping(final ProjMapBean projMapBean);
    List<ProjMapBean> getAllMappingsFromDB();
    //Additional method added to get the mapping based on only SD Project Key and SD Project Name
    ProjMapBean getMappingBasedOnSDProj(final ProjMapBean projMapBean);

}
