define("jira/mail/edit-server-details", [
    "jquery"
], function(
    jQuery
) {
    return function() {
        jQuery("select#mailServer").bind("change keyup", function () {
            var optgroup = jQuery(this).find("option:selected").parent("optgroup");
            var group = optgroup != undefined ? optgroup.attr("label") : "";

            var folderGroup = jQuery("#folder").parents(".field-group").first();

            jQuery("div.description", folderGroup).text(
                "IMAP" == group ? AJS.I18n.getText('admin.service.imap.folder.desc')
                    : AJS.I18n.getText('admin.services.edit.file.service.directory', "[jira.home]/import/mail"));
            folderGroup.toggle("POP3" != group);
        }).change();
    };
});

AJS.namespace('Mail.EditServerDetails', null, require("jira/mail/edit-server-details"));