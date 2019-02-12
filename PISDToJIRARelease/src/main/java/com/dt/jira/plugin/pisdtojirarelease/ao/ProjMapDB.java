package com.dt.jira.plugin.pisdtojirarelease.ao;

import net.java.ao.Entity;

/**
 * Created by yagnesh.bhat on 7/28/2016.
 * Thes is project Bean which  contains getters and setters for Project Name and Project Key.
 *
 */
public interface ProjMapDB extends Entity{
    String getSdProjName();
    void setSdProjName(String sdProjName);

    String getSdProjKey();
    void setSdProjKey(String sdProjKey);

    String getJiraProjName();
    void setJiraProjName(String jiraProjName);

    String getJiraProjKey();
    void setJiraProjKey(String jiraProjKey);

    String getJiraProjIssueType();
    void setJiraProjIssueType(String jiraProjIssueType);

}
