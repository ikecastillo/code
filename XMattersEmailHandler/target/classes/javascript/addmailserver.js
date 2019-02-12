require([
    "jquery"
], function(
    jQuery
) {
    jQuery(function() {
        var knownServiceProviders = {
            "gmail-smtp" : { protocol: "smtps", serverName: "smtp.gmail.com", port: "465"},
            "gmail-pop3" : { protocol: "pop3s", serverName: "pop.gmail.com", port: "995" },
            "gmail-imap" : { protocol: "imaps", serverName: "imap.gmail.com", port: "993" },

            "yahooplus-smtp" : { protocol: "smtps", serverName: "plus.smtp.mail.yahoo.com", port: "465" },
            "yahooplus" : { protocol: "pop3s", serverName: "plus.pop.mail.yahoo.com", port: "995" }
        };

        jQuery("select[name=serviceProvider]").change(function() {
            var val = jQuery(this).val();
            var showFields = false;
            var protocol = jQuery("select[name=protocol]"), serverName = jQuery("input[name=serverName]"),
                    port = jQuery("input[name=port]"), tls = jQuery("input[name=tlsRequired]");
            var provider = knownServiceProviders[val];
            if (provider) {
                protocol.val(provider.protocol);
                serverName.val(provider.serverName);
                port.val(provider.port);
            } else {
                showFields = true;
            }

            protocol.parent(".field-group")
                    .add(protocol.parent("td.fieldValueArea").parent("tr")).toggle(showFields);
            serverName.parent(".field-group")
                    .add(serverName.parent("td.fieldValueArea").parent("tr")).toggle(showFields);
            port.parent(".field-group")
                    .add(port.parent("td.fieldValueArea").parent("tr")).toggle(showFields);
            tls && tls.parent(".field-group").
                    add(tls.parent("td.fieldValueArea").parent("tr")).toggle(showFields);
        }).change();
    });
});