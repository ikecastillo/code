require([
    'jquery',
    'jira/mail/outgoing-mail-button'
], function(
    jQuery,
    OutgoingMailButton
) {
    jQuery(function() {
        // bind to the outgoing mail button
        jQuery('#outgoing-mail-toggle').each(function() {
            new OutgoingMailButton(jQuery(this));
        });
    });
});
