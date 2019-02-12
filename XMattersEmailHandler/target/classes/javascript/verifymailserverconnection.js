define('jira/mail/email', [
    'exports',
    'jquery'
], function(
    email,
    jQuery
) {

    email.dialogInitializer = function() {
        jQuery("#verifyServer").hide();

        var changePassword = jQuery('#changePassword');
        if (changePassword.length > 0) {
            var passwordSection = jQuery('input[name=password]').parents('tr').first();
            var passwordChangeDescription = jQuery('<div class="fieldDescription" />')
                .html(AJS.I18n.getText("admin.mailservers.password.usernamechanged.description"))
                .hide()
                .insertAfter(changePassword);

            changePassword.change(function() {
                passwordSection.toggle(jQuery(this).prop("checked"));
            }).change();

            var originalUsername = jQuery("#originalUsername").val();
            jQuery('input[name=username]').bind("change input", function () {
                if (originalUsername == jQuery(this).val()) {
                    changePassword.removeAttr('disabled');
                    passwordChangeDescription.hide();
                } else {
                    changePassword.attr('disabled', 'disabled').attr('checked', 'checked').change();
                    passwordChangeDescription.show();
                }
            });
        }
    };

    email.verifyServerConnection = function(e, url) {
        jQuery("#verifyServer").show();
        jQuery("#verifyMessages").hide();
        document.forms.jiraform.action = url;
        jQuery(document.forms.jiraform).submit();

    };

    jQuery(email.dialogInitializer);
});

AJS.namespace('JIRA.app.admin.email', null, require('jira/mail/email'));
