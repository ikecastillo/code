define('jira/mail/views/projects-view', [
    'jira/mail/views/options-view',
    'jquery'
], function(
    OptionsView,
    jQuery
) {
    return OptionsView.extend({
        setSelectedId: function(projectKey) {
            var selectedProject = this.collection.find(function(project) { return project.get("key") == projectKey; });
            this.model.set({"projectKey": selectedProject.get("key")});
            this.issueTypesView.collection.reset(selectedProject.get("issueTypes"));
            this.issueTypesView.$el.trigger("change");
        },
        render: function() {
            var val = this.model.get("projectKey");
            if (val) {
                jQuery(this.el).val(val);
            }
            return this;
        }
    });
});