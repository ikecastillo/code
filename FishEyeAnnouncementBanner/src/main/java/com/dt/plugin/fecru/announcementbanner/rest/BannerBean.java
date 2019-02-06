package com.dt.plugin.fecru.announcementbanner.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yagnesh.bhat on 7/26/2016.
 */

@XmlRootElement(name = "bannerBean")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BannerBean {

    private String bannerText;

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

}
