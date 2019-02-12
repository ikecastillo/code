define('jira/mail/views/default-reporter-view', [
    'jira/mail/views/text-view',
    'jquery',
    'underscore'
], function(
    TextView,
    jQuery,
    _
) {
    return TextView.extend({
        initialize: function() {
            _.bindAll(this, "render");
            this.model.bind('change', this.render, this);
            this.container = jQuery('.field-group.reporterusername');
        },
        render: function() {
            TextView.prototype.render.call(this); // super()
            var isDisabled = this.model.get("createusers");
            this.container.toggle(!isDisabled);
            return this;
        }
    });
});