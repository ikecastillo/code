package com.dt.autoassign.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 5/5/2016.
 */

@XmlRootElement(name = "csarConfigBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ConfigBean {
    private String solutionGroup;
    private String issueType;
    private String owner;
    private String implementor;

    public String getSolutionGroup() {
        return solutionGroup;
    }

    public void setSolutionGroup(String solutionGroup) {
        this.solutionGroup = solutionGroup;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImplementor() {
        return implementor;
    }

    public void setImplementor(String implementor) {
        this.implementor = implementor;
    }
}
