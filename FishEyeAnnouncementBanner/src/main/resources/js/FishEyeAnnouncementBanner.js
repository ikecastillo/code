/**
 * Created by yagnesh.bhat on 7/26/2016.
 * JS that actually renders the banner on every fisheye page.
 */
AJS.$(function() {
    function populateBanner() {
        AJS.$.ajax({
            url: "/rest/banner/1.0/bannerREST/getBanner",
            dataType: "json",
            success: function(banner) {
                AJS.log("Banner message is " + banner.bannerText);
                if(banner.bannerText) {
                    AJS.$('#header').append(banner.bannerText);
                }
            }
        });
    }
    populateBanner();
});