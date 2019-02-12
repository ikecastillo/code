define('jira/mail/edit-handler-details-backbone', [
    'jira/mail/entities/handler-settings',
    'jira/mail/views/settings-view',
    'jquery'
], function(
    HandlerSettings,
    SettingsView,
    jQuery
) {
    return function(details, availableProjects, availableBulkOptions) {
        var settings = new HandlerSettings(details);
        var settingsView = new SettingsView({
            el: jQuery("#mailHandlerForm"),
            model: settings,
            availableProjects: availableProjects,
            availableBulkOptions: availableBulkOptions
        }).render();

        if (settingsView.projectsView !== undefined) {
            settingsView.projectsView.$el.trigger("change");
        }
        if (settingsView.bulkOptionsView !== undefined) {
            settingsView.bulkOptionsView.$el.trigger("change");
        }
    }
});
