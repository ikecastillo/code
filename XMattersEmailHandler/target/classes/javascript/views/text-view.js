define('jira/mail/views/text-view', [
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
            "change": "changeSelected",
            "blur": "changeSelected"
        },
        initialize: function() {
            _.bindAll(this, "render");
        },
        changeSelected: function() {
            var ajs = jQuery(this.el);
            var obj = new Object();
            obj[ajs.attr("name")] = ajs.val();
            this.model.set(obj);
        },
        render: function() {
            var ajs = jQuery(this.el), val = this.model.get(ajs.attr("name"));
            if (val !== null) {
                ajs.val(val);
            }
            return this;
        }
    });
});