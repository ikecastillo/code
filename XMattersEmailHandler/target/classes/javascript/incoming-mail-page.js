require([
    'jquery',
    'aui/inline-dialog',
    'jira/dialog/form-dialog'
], function(
    jQuery,
    InlineDialog,
    FormDialog
) {
    jQuery(function() {
        var fillToolTip = function (contents, trigger, showPopup) {
            contents.html(jQuery("#obsolete-settings-message").html());
            contents.css("background", "#FFFFDD");
            contents.parent().find("#arrow-obsolete-settings-popup path").attr("fill", "#FFFFDD");
            showPopup();
        };
        InlineDialog(jQuery(".obsolete-settings-hover"), "obsolete-settings-popup", fillToolTip, {
            width: 450,
            onHover: true,
            onTop: true,
            hideDelay: 0
        });

        var editInPopup = function() {
            var dialog = new FormDialog({
                trigger: this,
                id: this.id + "-dialog",
                ajaxOptions: {
                    url: this.href,
                    data: {
                        decorator: "dialog",
                        inline: "true"
                    }
                },
                widthClass: "large"
            });
        };

        jQuery("a#add-incoming-mail-handler").each(editInPopup);
        jQuery("#mail-handlers-table .edit").each(editInPopup);
        jQuery("#mail-handlers-advanced-config .aui-iconfont-help").tooltip({gravity:'w'});
    });
});