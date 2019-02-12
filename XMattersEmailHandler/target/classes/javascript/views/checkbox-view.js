define('jira/mail/views/checkbox-view', [
    'jquery',
    'backbone',
    'underscore'
], function(
    jQuery,
    Backbone,
    _
) {
    return Backbone.View.extend({
        events: {
            "change": "changeSelected"
        },
        initialize: function() {
            _.bindAll(this, "render");
        },
        changeSelected: function() {
            var ajs = jQuery(this.el);
            var obj = new Object();
            obj[ajs.attr("name")] = ajs.prop("checked");
            this.model.set(obj);
        },
        render: function() {
            var ajs = jQuery(this.el), val = this.model.get(ajs.attr("name"));
            if (val === true) {
                ajs.attr("checked", "checked");
            }
            return this;
        }
    });
});