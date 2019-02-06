/**
 * JS pertaining to the Announcement Banner Setup page found under "Global Settings" -> "Announcement Banner"
 */
AJS.toInit(function() {
    function populateBannerTextArea() {
        AJS.$.ajax({
            url: "/rest/banner/1.0/bannerREST/getBanner",
            dataType: "json",
            success: function(banner) {
                AJS.log("Banner message is " + banner.bannerText);
                if(banner.bannerText) {
                    AJS.$('#bannerHTML').text(banner.bannerText);
                }
            }
        });

    }
    // AJS.$('#header').append('<b> Testing Banner </b>');
    populateBannerTextArea();

    AJS.$("#bannerSaveButton").click(function(e) {
        var bannerText = AJS.$('#bannerHTML').val();
        AJS.log("Banner Text found is " + bannerText);
        var postData = JSON.stringify({
            bannerText: bannerText
        });
        var url = "/rest/banner/1.0/bannerREST/addBanner"
        AJS.$.ajax({
            url: url,
            type: "POST",
            data: postData,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                AJS.$('#bannerHTML').val(data.bannerText);
                AJS.log("setting the text in form of banner to " + data.bannerText);
                AJS.messages.success({
                    title: "Success!",
                    body: "Your banner is now setup! Please refresh the page!"
                });
                /*alert("Your banner is now setup! Please refresh the page!");*/
            }
        });
    });
});