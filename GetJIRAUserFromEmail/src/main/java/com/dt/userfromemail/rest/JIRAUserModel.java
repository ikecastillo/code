package com.dt.userfromemail.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Yagnesh.Bhat on 4/4/2016.
 */

@XmlRootElement(name = "jirauserfromemail")
@XmlAccessorType(XmlAccessType.FIELD)
public class JIRAUserModel {
    @XmlElement(name = "userid")
    private String userid;
    @XmlElement(name = "useremail")
    private String useremail;
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
    public JIRAUserModel(String userid, String useremail) {
        super();
        this.userid = userid;
        this.useremail = useremail;
    }
}
