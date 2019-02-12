package com.dt.autoassign.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.autoassign.ao.ConfigDB;
import com.dt.autoassign.rest.ConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yagnesh.bhat on 5/5/2016.
 */
public class ConfigServiceImpl implements ConfigService {
    private final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private final ActiveObjects ao;

    public ConfigServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }

    @Override
    public ConfigBean addConfig(ConfigBean configBean) {
        ConfigBean alreadyExistConfigBean = getConfig(configBean);

        if (alreadyExistConfigBean == null) {
            ao.executeInTransaction(() -> {
                final ConfigDB configDB = ao.create(ConfigDB.class);
                configDB.setsolutionGroup(configBean.getSolutionGroup());
                configDB.setissueType(configBean.getIssueType());
                configDB.setowner(configBean.getOwner());
                configDB.setimplementor(configBean.getImplementor());
                configDB.save();
                return configBean;
            });
        }
        return null;
    }

    @Override
    public ConfigBean getConfig(ConfigBean configBean) {
        ConfigBean alreadyExistConfigBean = null;
        ConfigDB[] configDB = ao.find(ConfigDB.class, " SOLUTION_GROUP = ? AND ISSUE_TYPE = ? ",
                configBean.getSolutionGroup(),
                configBean.getIssueType());

        log.debug("Number of config found (should be 1) : " + configDB.length);

        for (ConfigDB configDB1 : configDB) {
            alreadyExistConfigBean = new ConfigBean();
            alreadyExistConfigBean.setSolutionGroup(configDB1.getsolutionGroup());
            alreadyExistConfigBean.setIssueType(configDB1.getissueType());
            alreadyExistConfigBean.setOwner(configDB1.getowner());
            alreadyExistConfigBean.setImplementor(configDB1.getimplementor());
        }
        return alreadyExistConfigBean;
    }

    @Override
    public ConfigBean deleteConfig(ConfigBean configBean) {
        ao.executeInTransaction(() -> {
            ConfigBean cb = new ConfigBean();
            ConfigDB[] configDB = ao.find(ConfigDB.class, " SOLUTION_GROUP = ? AND ISSUE_TYPE = ? ",
                    configBean.getSolutionGroup(),
                    configBean.getIssueType());
            log.debug("Config found (1 or 0)? " + configDB.length);
            if (configDB.length > 0) {
                log.debug("Config Found, going to delete it");
                ao.delete(configDB);
                log.debug("Config Deleted");
            }
            return cb;
        });
        return null;
    }

    @Override
    public List<ConfigBean> getAllConfigsFromDB() {
        ConfigDB[] configDB =ao.find(ConfigDB.class);
        log.debug("Total Number of config(s) found : " + configDB.length);
        List<ConfigBean> configBeanList = generateListOfConfigs(configDB);
        return configBeanList;
    }


    @Override
    public List<ConfigBean> getConfigsBasedOnSolutionGroup(ConfigBean configBean) {
        ConfigDB[] configDB = ao.find(ConfigDB.class, " SOLUTION_GROUP = ?",
                configBean.getSolutionGroup());

        log.debug("Number of config(s) found for solution group " + configBean.getSolutionGroup() + " : " + configDB.length);

        List<ConfigBean> configBeanList = generateListOfConfigs(configDB);
        return configBeanList;
    }

    private List<ConfigBean> generateListOfConfigs(ConfigDB[] configDB) {
        List<ConfigBean> configBeanList = new ArrayList<>();
        ConfigBean configBean;
        for(ConfigDB configDB1 : configDB) {
            configBean = new ConfigBean();
            configBean.setSolutionGroup(configDB1.getsolutionGroup());
            configBean.setIssueType(configDB1.getissueType());
            configBean.setOwner(configDB1.getowner());
            configBean.setImplementor(configDB1.getimplementor());
            configBeanList.add(configBean);
        }
        return configBeanList;
    }
}


