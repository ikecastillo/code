package com.dt.grouprolessync.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 8/15/2016.
 *
 *
 Sample JSON inputs for this REST Call:
 {
 "projectsCSV" : "DPDMO",
 "groupToMimic" : "jira-users",
 "groupThatMimicks" : "JIRA-USERS-VIEW",
 "roleId" : 10000
 }

 {
 "projectsCSV" : "DPDMO,DT",
 "groupToMimic" : "jira-users",
 "groupThatMimicks" : "JIRA-USERS-VIEW",
 "roleId" : 10000
 }

 {
 "projectsCSV" : "All",
 "groupToMimic" : "jira-users",
 "groupThatMimicks" : "JIRA-USERS-VIEW",
 "roleId" : 10000
 }
 */

@XmlRootElement(name = "grouprolesbean")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupRolesBean {

    @XmlElement(name = "projectsCSV")
    private String projectsCSV;

    @XmlElement(name = "groupToMimic")
    private String groupToMimic;

    @XmlElement(name = "groupThatMimicks")
    private String groupThatMimicks;

    @XmlElement(name = "roleId")
    private Long roleId;

    public String getProjectsCSV() {
        return projectsCSV;
    }

    public void setProjectsCSV(String projectsCSV) {
        this.projectsCSV = projectsCSV;
    }

    public String getGroupToMimic() {
        return groupToMimic;
    }

    public void setGroupToMimic(String groupToMimic) {
        this.groupToMimic = groupToMimic;
    }

    public String getGroupThatMimicks() {
        return groupThatMimicks;
    }

    public void setGroupThatMimicks(String groupThatMimicks) {
        this.groupThatMimicks = groupThatMimicks;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
