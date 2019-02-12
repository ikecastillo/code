define('jira/mail/outgoing-mail-button', [
    'jquery',
    'underscore',
    'jira/lib/class'
], function(
    jQuery,
    _,
    Class
) {
    return Class.extend({
        /**
         * Creates a new
         *
         * @param {jQuery} $button
         */
        init: function($button) {
            this.$button = $button;
            this.$button.on('click', _.bind(this._onClicked, this));
        },

        /**
         * Event handler for when this button is clicked. When the button is clicked it will trigger an AJAX request
         * to either enable or disable outgoing mail. It will then reload the current page.
         *
         * @private
         */
        _onClicked: function() {
            var currentEnabled = this.$button.attr('data-enabled') === "true";

            this.$button.attr('disabled', 'disabled');
            jQuery.ajax(AJS.contextPath() + "/rest/jira-mail-plugin/1.0/outgoingMail/config", {
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify({
                            enabled: !currentEnabled
                        }
                    )
                }
            ).done(function() {
                // this is totally LAME but I don't want to rewrite this whole page just now
                window.location.reload();
            });
        }
    });
});

AJS.namespace('JIM.admin.OutgoingMailButton', null, require('jira/mail/outgoing-mail-button'));