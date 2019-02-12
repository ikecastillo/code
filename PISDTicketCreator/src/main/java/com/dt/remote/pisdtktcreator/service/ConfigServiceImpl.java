package com.dt.remote.pisdtktcreator.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.dt.remote.pisdtktcreator.ao.ConfigDB;
import com.dt.remote.pisdtktcreator.rest.ConfigBean;
import net.java.ao.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yagnesh.bhat on 4/18/2016.
 */
public class ConfigServiceImpl  implements  ConfigService{

    private final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);
    private final ActiveObjects ao;

    public ConfigServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }
    @Override
    public ConfigBean addConfig(final ConfigBean configBean) {
        ConfigBean alreadyExistConfigBean = getConfig(configBean);

        if (alreadyExistConfigBean == null) {
            ao.executeInTransaction(new TransactionCallback<ConfigBean>() {
                @Override
                public ConfigBean doInTransaction() {
                    final ConfigDB configDB = ao.create(ConfigDB.class);
                    configDB.setsrvcDskId(configBean.getServiceDeskID());
                    configDB.setsrvcDskName(configBean.getServiceDeskName());
                    configDB.setreqTypeId(configBean.getRequestTypeID());
                    configDB.setreqTypeName(configBean.getRequestTypeName());
                    configDB.setcustFields(configBean.getCustFieldsWithIDs());
                    configDB.save();
                    return configBean;
                }
            });
        }
        return null;
    }

    @Override
    public ConfigBean getConfig(ConfigBean configBean) {
        ConfigBean alreadyExistConfigBean = null;
        ConfigDB[] configDB = ao.find(ConfigDB.class, " SRVC_DSK_NAME = ? AND REQ_TYPE_NAME = ? ",
                configBean.getServiceDeskName(),
                configBean.getRequestTypeName());

        log.info("Number of config found (should be 1) : " + configDB.length);

        for (ConfigDB configDB1 : configDB) {
            alreadyExistConfigBean = new ConfigBean();
            alreadyExistConfigBean.setServiceDeskID(configDB1.getsrvcDskId());
            alreadyExistConfigBean.setServiceDeskName(configDB1.getsrvcDskName());
            alreadyExistConfigBean.setRequestTypeID(configDB1.getreqTypeId());
            alreadyExistConfigBean.setRequestTypeName(configDB1.getreqTypeName());
            alreadyExistConfigBean.setCustFieldsWithIDs(configDB1.getcustFields());
        }
        return alreadyExistConfigBean;
    }

    @Override
    public ConfigBean deleteConfig(final ConfigBean configBean) {
        ao.executeInTransaction(new TransactionCallback<ConfigBean>() {
            @Override
            public ConfigBean doInTransaction() {
                ConfigBean cb = new ConfigBean();
                ConfigDB[] configDB = ao.find(ConfigDB.class, " SRVC_DSK_NAME = ? AND REQ_TYPE_NAME = ? ",
                        configBean.getServiceDeskName(),
                        configBean.getRequestTypeName());
                log.info("Config found (1 or 0)? " + configDB.length);
                if (configDB.length > 0) {
                    log.info("Config Found, going to delete it");
                    ao.delete(configDB);
                    log.info("Config Deleted");
                }
                return cb;
            }
        });
        return null;
    }

    @Override
    public List<ConfigBean> getAllConfigsFromDB() {
        List<ConfigBean> configBeanList = new ArrayList<>();
        ConfigBean configBean;
        ConfigDB[] configDB =ao.find(ConfigDB.class, Query.select("SRVC_DSK_ID"));
        for(ConfigDB configDB1 : configDB) {
            configBean = new ConfigBean();
            configBean.setServiceDeskID(configDB1.getsrvcDskId());
            configBean.setServiceDeskName(configDB1.getsrvcDskName());
            configBean.setRequestTypeID(configDB1.getreqTypeId());
            configBean.setRequestTypeName(configDB1.getreqTypeName());
            configBean.setCustFieldsWithIDs(configDB1.getcustFields());
            configBeanList.add(configBean);
        }
        return configBeanList;
    }
}
