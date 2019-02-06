package com.dt.plugin.fecru.announcementbanner.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dt.plugin.fecru.announcementbanner.ao.BannerDB;
import com.dt.plugin.fecru.announcementbanner.rest.BannerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yagnesh.bhat on 7/26/2016.
 */
public class BannerServiceImpl implements BannerService {

    private final Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
    private final ActiveObjects ao;

    public BannerServiceImpl(ActiveObjects ao) {
        this.ao = ao;
    }

    public BannerBean addBanner(final BannerBean bannerBean) {
        final BannerDB[] oldBanner = ao.find(BannerDB.class);
        log.debug("Old banner found  (must be 0 or 1) " + oldBanner);
        //there will be only one or zero banner always - so just delete that and/or add the new banner :)
        if (oldBanner.length == 1) {
            log.debug("There is an old banner in the DB, I am updating with the new banner!");
            ao.executeInTransaction(() -> {
                ao.delete(oldBanner[0]);
                return createNewBanner(bannerBean);
            });
        } else if (oldBanner.length == 0)  {
            log.debug("There is no banner currently in the DB, so I am adding one!");
            ao.executeInTransaction(() ->
               createNewBanner(bannerBean)
            );
        }
        return null;
    }

    private BannerBean createNewBanner(BannerBean bannerBean) {
        BannerDB newBanner = ao.create(BannerDB.class);
        newBanner.setBannerText(bannerBean.getBannerText());
        newBanner.save();
        return bannerBean;
    }

    public BannerBean getBanner() {
        BannerDB[] oldBanner = ao.find(BannerDB.class);
        BannerBean bannerRetrieved = new BannerBean();
        if (oldBanner.length == 1) {
            bannerRetrieved.setBannerText(oldBanner[0].getBannerText());
        } else {
            bannerRetrieved.setBannerText("");
        }
        return bannerRetrieved;

    }

}
