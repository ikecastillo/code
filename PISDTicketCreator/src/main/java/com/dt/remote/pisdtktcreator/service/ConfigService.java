package com.dt.remote.pisdtktcreator.service;

import com.dt.remote.pisdtktcreator.rest.ConfigBean;

import java.util.List;

/**
 * Created by yagnesh.bhat on 4/18/2016.
 */
public interface ConfigService {

    ConfigBean addConfig(final ConfigBean configBean);
    ConfigBean getConfig(final ConfigBean configBean);
    ConfigBean deleteConfig(final ConfigBean configBean);
    List<ConfigBean> getAllConfigsFromDB();
}
