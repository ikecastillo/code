package com.dt.jira.pagerduty.intgt.plugin.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.dt.jira.pagerduty.intgt.plugin.ao.ServiceDB;
import com.dt.jira.pagerduty.intgt.plugin.rest.PDServiceBean;
import net.java.ao.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of the Service to actually add/delete/retrieve entries from the AO
 *
 * Created by Yagnesh.Bhat on 5/24/2016.
 */
public class PDServicesConfigServiceImpl implements PDServicesConfigService {

    private final Logger log = LoggerFactory.getLogger(PDServicesConfigServiceImpl.class);
    private final ActiveObjects ao;

    /**
     * Constructor
     * @param ao the ActiveObjects to be injected
     */
    public PDServicesConfigServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }


    /**
     * Save Event config to DB
     * @param pdServiceBean
     * @return event bean that is saved
     */
    @Override
    public PDServiceBean addService(final PDServiceBean pdServiceBean) {

        PDServiceBean alreadyExistPDServiceBean = getService(pdServiceBean);

        if (alreadyExistPDServiceBean == null) {
            ao.executeInTransaction(new TransactionCallback<PDServiceBean>() {
                @Override
                public PDServiceBean doInTransaction() {
                    final ServiceDB xMServiceDB1 = ao.create(ServiceDB.class);
                    xMServiceDB1.setServiceKey(pdServiceBean.getServiceKey());
                    xMServiceDB1.setServiceName(pdServiceBean.getServiceName());
                    xMServiceDB1.setClientsImpacted(pdServiceBean.getClientsImpacted());
                    xMServiceDB1.setImpacted(pdServiceBean.getImpacted());
                    xMServiceDB1.setSeverities(pdServiceBean.getSeverities());
                    xMServiceDB1.setDdcProduct(pdServiceBean.getDdcProduct());
                    xMServiceDB1.setDdcSubProduct0(pdServiceBean.getDdcSubProduct0());
                    xMServiceDB1.setDdcSubProduct1(pdServiceBean.getDdcSubProduct1());
                    xMServiceDB1.save();
                    return pdServiceBean;
                }
            });
        }
        return null;
    }

    /**
     * Gets the unique service row in the table
     * @param pdServiceBean
     * @return pdServiceBean if exists, null otherwise
     */
    @Override
    public PDServiceBean getService(PDServiceBean pdServiceBean) {

        PDServiceBean alreadyExistPDServiceBean = null;
        String aoQuery = " SERVICE_KEY = ? AND SERVICE_NAME = ? AND CLIENTS_IMPACTED = ? AND IMPACTED = ?" +
                " AND SEVERITIES = ? AND DDC_PRODUCT = ? AND DDC_SUB_PRODUCT0 = ? AND DDC_SUB_PRODUCT1 = ? ";
        ServiceDB[] serviceDB = ao.find(ServiceDB.class,aoQuery,
                pdServiceBean.getServiceKey(), pdServiceBean.getServiceName(), pdServiceBean.getClientsImpacted(),
                pdServiceBean.getImpacted(),pdServiceBean.getSeverities(),pdServiceBean.getDdcProduct(),
                pdServiceBean.getDdcSubProduct0(), pdServiceBean.getDdcSubProduct1());

        log.debug("Number of services found (should be 1) : " + serviceDB.length);

        /* There will only be one event returned at the max, if any, since every row in the table would be unique */
        for (ServiceDB serviceDB1 : serviceDB) {
            alreadyExistPDServiceBean = new PDServiceBean();
            alreadyExistPDServiceBean.setServiceKey(serviceDB1.getServiceKey());
            alreadyExistPDServiceBean.setServiceName(serviceDB1.getServiceName());
            alreadyExistPDServiceBean.setClientsImpacted(serviceDB1.getClientsImpacted());
            alreadyExistPDServiceBean.setImpacted(serviceDB1.getImpacted());
            alreadyExistPDServiceBean.setSeverities(serviceDB1.getSeverities());
            alreadyExistPDServiceBean.setDdcProduct(serviceDB1.getDdcProduct());
            alreadyExistPDServiceBean.setDdcSubProduct0(serviceDB1.getDdcSubProduct0());
            alreadyExistPDServiceBean.setDdcSubProduct1(serviceDB1.getDdcSubProduct1());
        }
        return alreadyExistPDServiceBean;
    }

    /**
     * Deletes an event having a form api Id
     * @param pdServiceBean
     * @return the event bean if deleted successfully, null otherwise
     */
    @Override
    public PDServiceBean deleteService(final PDServiceBean pdServiceBean) {
        ao.executeInTransaction(new TransactionCallback<PDServiceBean>() {
            @Override
            public PDServiceBean doInTransaction() {
                PDServiceBean eb = new PDServiceBean();
                String aoQuery = " SERVICE_KEY = ? AND SERVICE_NAME = ? AND CLIENTS_IMPACTED = ? AND IMPACTED = ?" +
                        " AND SEVERITIES = ? AND DDC_PRODUCT = ? AND DDC_SUB_PRODUCT0 = ? AND DDC_SUB_PRODUCT1 = ? ";
                ServiceDB[] serviceDB = ao.find(ServiceDB.class,aoQuery,
                        pdServiceBean.getServiceKey(), pdServiceBean.getServiceName(), pdServiceBean.getClientsImpacted(),
                        pdServiceBean.getImpacted(),pdServiceBean.getSeverities(),pdServiceBean.getDdcProduct(),
                        pdServiceBean.getDdcSubProduct0(), pdServiceBean.getDdcSubProduct1());
                if (serviceDB.length > 0 ) {
                    ao.delete(serviceDB);
                }
                return eb;
            }
        });
        return null;
    }

    /**
     * Get all the PagerDuty Services configured in DB
     *
     * @return list of all events
     */
    @Override
    public List<PDServiceBean> getAllServicesFromDB() {

        List<PDServiceBean> servicesList = new ArrayList<>();
        PDServiceBean pdServiceBean;
        ServiceDB[] serviceDB = ao.find(ServiceDB.class, Query.select());
        for (ServiceDB serviceDB1 : serviceDB) {
            pdServiceBean = new PDServiceBean();
            pdServiceBean.setServiceKey(serviceDB1.getServiceKey());
            pdServiceBean.setServiceName(serviceDB1.getServiceName());
            pdServiceBean.setClientsImpacted(serviceDB1.getClientsImpacted());
            pdServiceBean.setImpacted(serviceDB1.getImpacted());
            pdServiceBean.setSeverities(serviceDB1.getSeverities());
            pdServiceBean.setDdcProduct(serviceDB1.getDdcProduct());
            pdServiceBean.setDdcSubProduct0(serviceDB1.getDdcSubProduct0());
            pdServiceBean.setDdcSubProduct1(serviceDB1.getDdcSubProduct1());
            servicesList.add(pdServiceBean);
        }
        return servicesList;
    }


    @Override
    public List<PDServiceBean> searchService(PDServiceBean pdServiceBean) {
        List<PDServiceBean> servicesList = new ArrayList<>();
        ServiceDB[] serviceDB = constructAOQueryForSearch(pdServiceBean);
        for (ServiceDB serviceDB1 : serviceDB) {
            PDServiceBean pdServiceBeanTemp = new PDServiceBean();
            pdServiceBeanTemp.setServiceKey(serviceDB1.getServiceKey());
            pdServiceBeanTemp.setServiceName(serviceDB1.getServiceName());
            pdServiceBeanTemp.setClientsImpacted(serviceDB1.getClientsImpacted());
            pdServiceBeanTemp.setImpacted(serviceDB1.getImpacted());
            pdServiceBeanTemp.setSeverities(serviceDB1.getSeverities());
            pdServiceBeanTemp.setDdcProduct(serviceDB1.getDdcProduct());
            pdServiceBeanTemp.setDdcSubProduct0(serviceDB1.getDdcSubProduct0());
            pdServiceBeanTemp.setDdcSubProduct1(serviceDB1.getDdcSubProduct1());
            servicesList.add(pdServiceBeanTemp);
        }

        return servicesList;
    }

    private ServiceDB[] constructAOQueryForSearch(PDServiceBean pdServiceBean) {
        String serviceName = pdServiceBean.getServiceName();
        String ddcProduct = pdServiceBean.getDdcProduct();
        ServiceDB[] serviceDBs;

        //Either Service name or ddc product will be "All" (not both)
        if (StringUtils.isNotBlank(serviceName) && serviceName.equals("All")) {
            serviceDBs = ao.find(ServiceDB.class," DDC_PRODUCT = ? ", ddcProduct);
        } else if (StringUtils.isNotBlank(ddcProduct) && ddcProduct.equals("All")) {
            serviceDBs = ao.find(ServiceDB.class," SERVICE_NAME = ? ", serviceName);
        } else {
            serviceDBs = ao.find(ServiceDB.class," SERVICE_NAME = ? AND DDC_PRODUCT = ? ",
                    serviceName,ddcProduct);
        }


        return serviceDBs;
    }
}
