var request = require('request');
var cors = require('cors');
var uuid = require('uuid');
var url = require('url');
var _ = require('lodash');
var linkify = require("html-linkify");
var moment = require('moment');
var debug = require('debug')('HipConnectTester:routes');
var prettyjson = require('prettyjson');
var urlRoot = "https://jiraitsmdev.dealertrack.com";
var credentials = {
    'user':'dtjira.admin',
    'pass' : '<enter password here>'
};

function getTheme(req) {
  return req.query.theme || 'light';
}

// This is the heart of your HipChat Connect add-on. For more information,
// take a look at https://developer.atlassian.com/hipchat/tutorials/getting-started-with-atlassian-connect-express-node-js
module.exports = function (app, addon) {
  var hipchat = require('../lib/hipchat')(addon);

  // simple healthcheck
  app.get('/healthcheck', function (req, res) {
    res.send('OK');
  });

  // Root route. This route will serve the `addon.json` unless a homepage URL is
  // specified in `addon.json`.
  app.get('/',
    function (req, res) {
      // Use content-type negotiation to choose the best way to respond
      res.format({
        // If the request content-type is text-html, it will decide which to serve up
        'text/html': function () {
          var homepage = url.parse(addon.descriptor.links.homepage);
          if (homepage.hostname === req.hostname && homepage.path === req.path) {
            res.render('homepage', addon.descriptor);
          } else {
            res.redirect(addon.descriptor.links.homepage);
          }
        },
        // This logic is here to make sure that the `addon.json` is always
        // served up when requested by the host
        'application/json': function () {
          res.redirect('/atlassian-connect.json');
        }
      });
    }
    );

  // This is an example route that's used by the default for the configuration page
  // https://developer.atlassian.com/hipchat/guide/configuration-page
  app.get('/config',
    // Authenticates the request using the JWT token in the request
    addon.authenticate(),
    function (req, res) {
      // The `addon.authenticate()` middleware populates the following:
      // * req.clientInfo: useful information about the add-on client such as the
      //   clientKey, oauth info, and HipChat account info
      // * req.context: contains the context data accompanying the request like
      //   the roomId
      res.render('config', req.context);
    }
    );

  // This is an example glance that shows in the sidebar
  // https://developer.atlassian.com/hipchat/guide/glances
  app.get('/glance',
    cors(),
    addon.authenticate(),
    function (req, res) {
      res.json({
        "label": {
          "type": "html",
          "value": "Hello World!"
        },
        "status": {
          "type": "lozenge",
          "value": {
            "label": "NEW",
            "type": "error"
          }
        }
      });
    }
    );

  // This is an example end-point that you can POST to to update the glance info
  // Room update API: https://www.hipchat.com/docs/apiv2/method/room_addon_ui_update
  // Group update API: https://www.hipchat.com/docs/apiv2/method/addon_ui_update
  // User update API: https://www.hipchat.com/docs/apiv2/method/user_addon_ui_update
  app.post('/update_glance',
    cors(),
    addon.authenticate(),
    function (req, res) {
      res.json({
        "label": {
          "type": "html",
          "value": "Hello World!"
        },
        "status": {
          "type": "lozenge",
          "value": {
            "label": "All good",
            "type": "success"
          }
        }
      });
    }
    );

  // This is an example sidebar controller that can be launched when clicking on the glance.
  // https://developer.atlassian.com/hipchat/guide/dialog-and-sidebar-views/sidebar
  app.get('/sidebar',
    addon.authenticate(),
    function (req, res) {
      res.render('sidebar', {
        identity: req.identity
      });
    }
    );

  // This is an example dialog controller that can be launched when clicking on the glance.
  // https://developer.atlassian.com/hipchat/guide/dialog-and-sidebar-views/dialog
  app.get('/dialog',
    addon.authenticate(),
    function (req, res) {
      res.render('dialog', {
        identity: req.identity
      });
    }
    );

  // Sample endpoint to send a card notification back into the chat room
  // See https://developer.atlassian.com/hipchat/guide/sending-messages
  app.post('/send_notification',
    addon.authenticate(),
    function (req, res) {
      var card = {
        "style": "link",
        "url": "https://www.hipchat.com",
        "id": uuid.v4(),
        "title": req.body.messageTitle,
        "description": "Great teams use HipChat: Group and private chat, file sharing, and integrations",
        "icon": {
          "url": "https://hipchat-public-m5.atlassian.com/assets/img/hipchat/bookmark-icons/favicon-192x192.png"
        }
      };
      var msg = '<b>' + card.title + '</b>: ' + card.description;
      var opts = { 'options': { 'color': 'yellow' } };
      hipchat.sendMessage(req.clientInfo, req.identity.roomId, msg, opts, card);
      res.json({ status: "ok" });
    }
    );

  // This is an example route to handle an incoming webhook
  // https://developer.atlassian.com/hipchat/guide/webhooks
  app.post('/webhook',
    addon.authenticate(),
    function (req, res) {
      hipchat.sendMessage(req.clientInfo, req.identity.roomId, 'incident ticket created!')
        .then(function (data) {
          res.sendStatus(200);
        });
    }
    );

  // Notify the room that the add-on was installed. To learn more about
  // Connect's install flow, check out:
  // https://developer.atlassian.com/hipchat/guide/installation-flow
  addon.on('installed', function (clientKey, clientInfo, req) {
    //hipchat.sendMessage(clientInfo, req.body.roomId, 'The ' + addon.descriptor.name + ' add-on has been installed in this room');
    var options = {
      options: {
        color: "green"
      }
    };
    var msg = "The Create Incident From Room add-on has been installed into this room.";
    hipchat.sendMessage(clientInfo, req.body.roomId, msg, options);
  });

  // Clean up clients when uninstalled
  addon.on('uninstalled', function (id) {
    addon.settings.client.keys(id + ':*', function (err, rep) {
      rep.forEach(function (k) {
        addon.logger.info('Removing key:', k);
        addon.settings.client.del(k);
      });
    });
  });


  app.get('/dialog/complex',
      addon.authenticate(),
      function(req, res) {
        res.render('complex-dialog', {
          theme: getTheme(req)
        });
      });

  app.get('/dialog/complex2',
      addon.authenticate(),
      function(req, res) {
        res.render('dialog-outage', {
          theme: getTheme(req)
        });
      });


    app.post('/createticket',addon.authenticate(),
            function(req, res) {
                //Make multiple calls using access token
                /* var OAuth = require('oauth').OAuth;
                 var privateKeyData = fs.readFileSync("/root/mykey.pem", "utf8");

                 var consumer =
                 new OAuth("https://jiraitsmdev.dealertrack.com/plugins/servlet/oauth/request-token",
                 "https://jiraitsmdev.dealertrack.com/plugins/servlet/oauth/access-token",
                 config["consumerKey"],
                 "",
                 "1.0",
                 "https://jiraitsmdev.dealertrack.com:3000/sessions/callback",
                 "RSA-SHA1",
                 null,
                 privateKeyData);*/
                var url= urlRoot +"/rest/get-jira-user-from-email/1.0/createincidentoroutage";
                //Get all the data from the route
                var projectkey = req.body.projectkey;
                var summary = req.body.summary;
                var description = req.body.description;
                var solutionGroup = req.body.solutiongroup;
                var product = req.body.product;
                var severity = req.body.severity;
                var reporteremail = req.body.reporteremail;
                var issuetype = req.body.issuetype;
                var customerimpact = req.body.customerimpact;
                var clientsimpacted = req.body.clientsimpacted;
                var impacted = req.body.impacted;
                var customertalkingpoints = req.body.customertalkingpoints;
                var incidentstart = req.body.incidentstart;
                var incidentsource = req.body.incidentsource;

                console.log("CREATING INCIDENT OR OUTAGE AS ASKED FOR");
                //Make a simple REST call using request
                var headers = {
                    'Access-Control-Allow-Origin' : urlRoot +':3000',
                    'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
                };

                var json = {
                    projectkey : projectkey,
                    issuetype : issuetype,
                    summary : summary,
                    description : description,
                    solutiongroup : solutionGroup,
                    product : product,
                    severity : severity,
                    reporteremail : reporteremail,
                    customerimpact : customerimpact,
                    clientsimpacted: clientsimpacted,
                    impacted: impacted,
                    customertalkingpoints: customertalkingpoints,
                    incidentstart: incidentstart,
                    incidentsource: incidentsource
                };

                var requestOptions = {
                    url : url,
                    method : "POST",
                    json: json,
                    headers: headers,
                    auth : credentials
                }; //Note : json attribute will add  'Content-Type': 'application/json', in header

                request(requestOptions, function(err, response, body) {
                    var data = body;
                    if (err) {
                        console.log(err);
                        res.writeHead(err.statusCode, { 'Content-Type': 'application/json' });
                        res.end(err.message);
                    } else if (response.statusCode === 200) {
                        console.log(body);
                        console.log("BEFORE RESPONSE");
                        res.writeHead(200, { 'Content-Type': 'application/json' });
                        res.end(JSON.stringify(data));
                    } else if (response.statusCode === 500) {
                        console.log("500 ERROR " + response.statusCode);
                        res.writeHead(500, { 'Content-Type': 'application/json' });
                        var errorMessage = "Cannot create " + issuetype +
                            " - No valid JIRA user found or JIRA server is down";
                        res.end(errorMessage);
                    } else {
                        console.log(response.statusCode);
                    }
                });

            }
    );

    app.post('/getProducts', addon.authenticate(),
        function(req,res) {
            var url = urlRoot + "/rest/incident/1.0/getTypes/getProductValues";
            //Get all the data from the route
            var projectkey = req.body.projectkey;
            var solutionGroup = req.body.solutionGroup;

            var headers = {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : urlRoot +':3000',
                'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
            };

            var requestOptions = {
                url : url,
                method : "GET",
                json: {},
                qs: {
                    projectkey : projectkey,
                    solutionGroup : solutionGroup
                },
                headers: headers,
                auth : credentials
            };

            request(requestOptions, function(err, response, body) {
                var data = body;
                if (err) {
                    console.log(err);
                } else if (response.statusCode === 200) {
                    console.log("BEFORE RESPONSE");
                    res.writeHead(200, { 'Content-Type': 'application/json' });
                    res.end(JSON.stringify(data));

                } else {
                    console.log(response.statusCode);
                    console.log("SERVER DOWN!");
                }
            });

            console.log("REQUEST END");

        });


    app.post('/getImpactedValues', addon.authenticate(),
        function(req,res) {
            var url = urlRoot + "/rest/incident/1.0/getTypes/getImpactedValues";
            //Get all the data from the route
            var projectkey = req.body.projectkey;

            var headers = {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : urlRoot +':3000',
                'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
            };

            var requestOptions = {
                url : url,
                method : "GET",
                json: {},
                qs: {
                    projectkey : projectkey
                },
                headers: headers,
                auth : credentials
            };

            request(requestOptions, function(err, response, body) {
                var data = body;
                if (err) {
                    console.log(err);
                } else if (response.statusCode === 200) {
                    console.log("BEFORE RESPONSE");
                    res.writeHead(200, { 'Content-Type': 'application/json' });
                    res.end(JSON.stringify(data));

                } else {
                    console.log(response.statusCode);
                    console.log("SERVER DOWN!");
                }
            });

            console.log("REQUEST END");

        });


};
