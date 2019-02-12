package com.dt.jira.xmatters.intgt.plugin.ao;

import net.java.ao.Entity;

/**
 * The AO Entity that holds the Event Configuration details
 *
 * Created by Yagnesh.Bhat on 10/19/2015.
 */
public interface EventDB extends Entity {
    String getFormAPIID();
    void setFormAPIID(String formAPIID);

    String getxMattersTemplateName();
    void setxMattersTemplateName(String xMattersTemplateName);

    String getxMattersFormWSURL();
    void setxMattersFormWSURL(String xMattersFormWSURL);

    String getxMattersResponseAvl();
    void setxMattersResponseAvl(String xMattersResponseAvl);

    String getxMattersResponseNotAvl();
    void setxMattersResponseNotAvl(String xMattersResponseNotAvl);
}
