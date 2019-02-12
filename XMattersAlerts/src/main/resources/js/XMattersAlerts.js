/**
 * The starting point for JS to run for this plugin
 */
AJS.$(function() {
      setInterval(function() {
            AlertGenerator.checkForAlert();
      }, 60000);
});