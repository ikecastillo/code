package com.dt.plugin.fecru.announcementbanner.service;

import com.dt.plugin.fecru.announcementbanner.rest.BannerBean;

/**
 * Created by yagnesh.bhat on 7/26/2016.
 */
public interface BannerService {

    BannerBean addBanner(final BannerBean bannerBean);
    BannerBean getBanner();

}
