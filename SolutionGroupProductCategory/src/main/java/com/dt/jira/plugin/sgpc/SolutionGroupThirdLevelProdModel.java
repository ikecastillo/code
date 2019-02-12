package com.dt.jira.plugin.sgpc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 7/28/2015.
 */
@XmlRootElement(name = "solgrpprodthirdlevel")
@XmlAccessorType(XmlAccessType.FIELD)
public class SolutionGroupThirdLevelProdModel {

    @XmlElement(name = "solutionGroup")
    private String solutionGroup;

    @XmlElement(name = "product")
    private String product;

    @XmlElement(name = "subproduct")
    private String subproduct;

    @XmlElement(name = "issueKey")
    private String issueKey;


    public SolutionGroupThirdLevelProdModel() {

    }

    public SolutionGroupThirdLevelProdModel(String solutionGroup, String product, String subproduct, String issueKey) {
        this.solutionGroup = solutionGroup;
        this.product = product;
        this.issueKey = issueKey;
        this.subproduct = subproduct;
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

    public String getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(String subproduct) {
        this.subproduct = subproduct;
    }

}
