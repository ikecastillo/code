package com.dt.plugin.fecru.announcementbanner.ao;

import net.java.ao.Entity;
import net.java.ao.schema.StringLength;

/**
 * Created by yagnesh.bhat on 7/26/2016.
 */
public interface BannerDB extends Entity {
    @StringLength(value= StringLength.UNLIMITED)
    String getBannerText();
    @StringLength(value=StringLength.UNLIMITED)
    void setBannerText(String bannerText);
}
