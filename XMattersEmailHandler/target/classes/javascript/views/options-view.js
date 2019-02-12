define('jira/mail/views/options-view', [
    'jira/mail/views/option-view',
    'jquery',
    'backbone',
    'underscore'
], function(
    OptionView,
    jQuery,
    Backbone,
    _
) {
    return Backbone.View.extend({
        events: {
            "change": "changeSelected"
        },
        initialize: function() {
            _.bindAll(this, 'addOne', 'addAll');
            this.collection.bind('reset', this.addAll);
            this.addAll();
        },
        addOne: function(project) {
            var optionView = new OptionView({model: project});
            this.optionViews.push(optionView);
            jQuery(this.el).append(optionView.render().el);
        },
        addAll: function() {
            _.each(this.optionViews, function(optionView) { optionView.remove(); });
            this.optionViews = [];
            this.collection.each(this.addOne);
            this.render();
        },
        changeSelected: function() {
            this.setSelectedId(jQuery(this.el).val());
        }
    });
});