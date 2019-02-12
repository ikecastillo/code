define('jira/mail/entities/bulk-options', [
    'backbone'
], function(
    Backbone
) {
    return Backbone.Collection.extend({model:Backbone.Model.extend()});
});