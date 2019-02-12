define('jira/mail/views/bulk-options-view', [
    'jira/mail/views/options-view',
    'jquery'
], function(
    OptionsView,
    jQuery
) {
    return OptionsView.extend({
        setSelectedId: function(optionId) {
            var selectedOption = this.collection.find(function(type) { return type.get("id") == optionId; });
            this.model.set({"bulk": selectedOption.get("id")});
        },
        render: function() {
            var val = this.model.get("bulk");
            if (val) {
                jQuery(this.el).val(val);
            }
            return this;
        }
    });
});