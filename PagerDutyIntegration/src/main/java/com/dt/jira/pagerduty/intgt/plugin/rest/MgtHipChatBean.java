package com.dt.jira.pagerduty.intgt.plugin.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 11/5/2015.
 */

@XmlRootElement(name = "mgtHipChatBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class MgtHipChatBean {
    private String solutionLine;
    private String hipChatUrl;

    public String getSolutionLine() {
        return solutionLine;
    }

    public void setSolutionLine(String solutionLine) {
        this.solutionLine = solutionLine;
    }

    public String getHipChatUrl() {
        return hipChatUrl;
    }

    public void setHipChatUrl(String hipChatUrl) {
        this.hipChatUrl = hipChatUrl;
    }


}
