package com.dt.jira.plugin.sgpc;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.customfields.option.Options;
import com.atlassian.jira.issue.customfields.view.CustomFieldParams;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchProvider;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugins.rest.common.security.AuthenticationContext;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yagnesh.bhat on 7/28/2015.
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class SolutionGroupModel {

    @XmlElement(name = "solutionGroup")
    private String solutionGroup;

    @XmlElement(name = "product")
    private String product;

    @XmlElement(name = "issueKey")
    private String issueKey;


    public SolutionGroupModel() {

    }

    public SolutionGroupModel(String solutionGroup, String product, String issueKey) {
        this.solutionGroup = solutionGroup;
        this.product = product;
        this.issueKey = issueKey;
    }

    public String getSolutionGroup() {
        return solutionGroup;
    }

    public void setSolutionGroup(String solutionGroup) {
        this.solutionGroup = solutionGroup;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

}
