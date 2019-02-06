// This is the entry point for your add-on, creating and configuring
// your add-on HTTP server

// [Express](http://expressjs.com/) is your friend -- it's the underlying
// web framework that `atlassian-connect-express` uses
var express = require('express');
var bodyParser = require('body-parser');
var compression = require('compression');
var cookieParser = require('cookie-parser');
var errorHandler = require('errorhandler');
var morgan = require('morgan');
// You need to load `atlassian-connect-express` to use her godly powers
var ac = require('atlassian-connect-express');
process.env.PWD = process.env.PWD || process.cwd(); // Fix expiry on Windows :(
// Static expiry middleware to help serve static resources efficiently
var expiry = require('static-expiry');
// We use [Handlebars](http://handlebarsjs.com/) as our view engine
// via [express-hbs](https://npmjs.org/package/express-hbs)
var hbs = require('express-hbs');
// We also need a few stock Node modules
var http = require('http');
var path = require('path');
var os = require('os');
var request = require('request');

/*var JiraClient = require('./node_modules/jira-connector/index.js');

JiraClient.oauth_util.getAuthorizeURL({
  host: 'jiraitsmdev.dealertrack.com',
  oauth: {
    consumer_key: 'dpf43f3p2l4k3l03',
    private_key: '-----BEGIN RSA PRIVATE KEY-----\n' +
    'MIIBOgIBAAJBALtcAuKw7bDEdQrPIxKt7IHk6YXq5AlgxLUnL9omOj8o//yPooPUSzJBuG5bcWBnlIAmbqvG+6qP/v902lzyhGUCAwEAAQJARLQY2sfGYWuo/621TsQ4Vel7OIBJ3qZ9e3BDO9uIwF/HiU+KOZRSQmuyql4E9LqvdbAJHzIA1A0RkCJJRfrqgQIhAOm74Ml/rX+IAZ8K21SUXr4xE8D440RPekteknlD9GtDAiEAzTUwLG8z9rbieUgNafoOlmPfN+7vpZDLq9FrJFVIkzcCIGgFvJoQ37dlYUtV+p0e1zeQAfmdCxBIyuWmNkwP6USfAiEAkOrA7IBTyCV7Zn71yiOnbZl2J//7CYdp7hrkeTIpIJ0CIDmvafw/Np7zLD8sw2EsAYV978OXYVsk9ah2kSdWt5ir\n' +
    '-----END RSA PRIVATE KEY-----'
  }
}, function (error, oauth) {
  console.log(oauth);
});*/


module.exports = http;

// Let's use Redis to store our data
ac.store.register('redis', require('atlassian-connect-express-redis'));

// Anything in ./public is served up as static content
var staticDir = path.join(__dirname, 'public');
// Anything in ./views are HBS templates
var viewsDir = __dirname + '/views';
// Your routes live here; this is the C in MVC
var routes = require('./routes');
// Bootstrap Express
var app = express();
// Bootstrap the `atlassian-connect-express` library
var addon = ac(app);
// You can set this in `config.js`
var port = addon.config.port();
// Declares the environment to use in `config.js`
var devEnv = app.get('env') == 'development';

// Load the HipChat AC compat layer
var hipchat = require('atlassian-connect-express-hipchat')(addon, app);

// The following settings applies to all environments
app.set('port', port);

// Configure the Handlebars view engine
/*app.engine('hbs', hbs.express3({partialsDir: viewsDir}));
app.set('view engine', 'hbs');
app.set('views', viewsDir);*/

app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// Declare any Express [middleware](http://expressjs.com/api.html#middleware) you'd like to use here
// Log requests, using an appropriate formatter by env
app.use(morgan(devEnv ? 'dev' : 'combined'));
// Include request parsers
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser());
// Gzip responses when appropriate
app.use(compression());
// Enable the ACE global middleware (populates res.locals with add-on related stuff)
app.use(addon.middleware());
// Enable static resource fingerprinting for far future expires caching in production
app.use(expiry(app, {dir: staticDir, debug: devEnv}));
// Add an hbs helper to fingerprint static resource urls
hbs.registerHelper('furl', function(url){ return app.locals.furl(url); });
// Mount the static resource dir
app.use(express.static(staticDir));

// Show nicer errors when in dev mode
if (devEnv) app.use(errorHandler());

// Wire up your routes using the express and `atlassian-connect-express` objects
routes(app, addon);

// Boot the damn thing
http.createServer(app).listen(port, function(){
  console.log('Add-on server running at '+ (addon.config.localBaseUrl()||('http://' + (os.hostname()) + ':' + port)));
  // Enables auto registration/de-registration of add-ons into a host in dev mode
  if (devEnv) addon.register();
});
