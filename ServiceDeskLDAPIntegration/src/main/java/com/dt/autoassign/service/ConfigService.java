package com.dt.autoassign.service;

import com.dt.autoassign.rest.ConfigBean;

import java.util.List;

/**
 * Created by yagnesh.bhat on 5/5/2016.
 */
public interface ConfigService {

    ConfigBean addConfig(final ConfigBean configBean);
    ConfigBean getConfig(final ConfigBean configBean);
    ConfigBean deleteConfig(final ConfigBean configBean);
    List<ConfigBean> getAllConfigsFromDB();
    List<ConfigBean> getConfigsBasedOnSolutionGroup(final ConfigBean configBean);
}
