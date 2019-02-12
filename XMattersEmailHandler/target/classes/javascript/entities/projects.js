define('jira/mail/entities/projects', [
    'backbone'
], function(
    Backbone
) {
    return Backbone.Collection.extend({model:Backbone.Model.extend()});
});
