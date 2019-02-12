package com.dt.jira.pagerduty.intgt.plugin.ao;

import net.java.ao.Entity;

/**
 * The AO Entity that holds the PD Service Configuration details
 *
 * Created by Yagnesh.Bhat on 5/24/2016.
 */
public interface ServiceDB extends Entity {
    String getServiceKey();
    void setServiceKey(String serviceKey);

    String getServiceName();
    void setServiceName(String serviceName);

    String getClientsImpacted();
    void setClientsImpacted(String clientsImpacted);

    String getImpacted();
    void setImpacted(String impacted);

    String getSeverities();
    void setSeverities(String severities);

    String getDdcProduct();
    void setDdcProduct(String ddcProduct);

    String getDdcSubProduct0();
    void setDdcSubProduct0(String ddcSubProduct0);

    String getDdcSubProduct1();
    void setDdcSubProduct1(String ddcSubProduct1);


}
