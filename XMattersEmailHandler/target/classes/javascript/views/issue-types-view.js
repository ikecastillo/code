define('jira/mail/views/issue-types-view', [
    'jira/mail/views/options-view',
    'jquery'
], function(
    OptionsView,
    jQuery
) {
    return OptionsView.extend({
        setSelectedId: function(issueTypeId) {
            var selectedType = this.collection.find(function(type) { return type.get("id") == issueTypeId; });
            this.model.set({"issueTypeId": selectedType.get("id")});
        },
        render: function() {
            var val = this.model.get("issueTypeId");
            if (val) {
                jQuery(this.el).val(val);
            }
            return this;
        }
    });
});