package com.dt.jira.pagerduty.intgt.plugin.service;

import com.dt.jira.pagerduty.intgt.plugin.rest.PDServiceBean;

import java.util.List;

/**
 * Created by Yagnesh.Bhat on 5/24/2016.
 */
public interface PDServicesConfigService {

    PDServiceBean addService(final PDServiceBean pdServiceBean);

    PDServiceBean getService(final PDServiceBean pdServiceBean);

    PDServiceBean deleteService(final PDServiceBean pdServiceBean);

    List<PDServiceBean> searchService(final PDServiceBean pdServiceBean);

    List<PDServiceBean> getAllServicesFromDB();
}
