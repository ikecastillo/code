define('jira/mail/entities/issue-types', [
    'backbone'
], function(
    Backbone
) {
    return Backbone.Collection.extend({model:Backbone.Model.extend()});
});