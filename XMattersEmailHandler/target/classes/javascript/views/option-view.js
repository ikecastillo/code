define('jira/mail/views/option-view', [
    'jquery',
    'backbone',
    'underscore'
], function(
    jQuery,
    Backbone,
    _
) {
    return Backbone.View.extend({
        tagName: "option",
        initialize: function() {
            _.bindAll(this, "render");
        },
        render: function() {
            var val = this.model.get('key') || this.model.get('id');
            jQuery(this.el).attr('value', val).html(this.model.escape('name'));
            return this;
        }
    });
});