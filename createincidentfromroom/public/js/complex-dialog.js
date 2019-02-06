/**
 * All the JS related to "Create Incident" Dialog.
 */
$(function () {
    var loggedInUserEmailId = "";
    var solutionGroup = "DDC";
    HipChat.user.getCurrentUser(function(err, data) {
        if (!err) {
            console.log("USER RETREIVED");
            console.log(JSON.stringify(data, null, 2));
            var userJSON = JSON.stringify(data, null, 2);
            loggedInUserEmailId = JSON.parse(userJSON).email;
            console.log("FOUND LOGGED IN USERS EMAIL ID AS " + loggedInUserEmailId);
        }
    });

    HipChat.auth.withToken(function (err, token) {
        if (!err) {
            //Then, make an AJAX call to the add-on backend, including the JWT token
            //Server-side, the JWT token is validated using the middleware function addon.authenticate()
            $.ajax(
                {
                    type: 'POST',
                    url: '/getProducts',
                    headers: {'Authorization': 'JWT ' + token},
                    dataType: 'json',
                    data: {
                        projectkey : "ITIM",
                        solutionGroup : solutionGroup
                    },
                    success: function (data) {
                        console.log("Got PRODUCTS");
                        /*console.log(data);*/
                        var productSelectField = $("#proddmkt");
                        for(var i=0;i<data.length;i++){
                            var value = data[i].value;
                            var productOption = AJS.$("<option/>").attr("value", value).text(value);
                            productSelectField.append(productOption);
                        }
                        console.log("Successfully populated products for the dropdown!");
                    },
                    error: function () {
                        //callback(true);
                        console.log("Got products thru route!");
                    }
                });
        }
    });

    HipChat.auth.withToken(function (err, token) {
        if (!err) {
            //Then, make an AJAX call to the add-on backend, including the JWT token
            //Server-side, the JWT token is validated using the middleware function addon.authenticate()
            $.ajax(
                {
                    type: 'POST',
                    url: '/getImpactedValues',
                    headers: {'Authorization': 'JWT ' + token},
                    dataType: 'json',
                    data: {
                        projectkey : "ITIM"
                    },
                    success: function (data) {
                        console.log("Got Impacted");
                        /*console.log(data);*/
                        var impactedSelectField = $("#impacted");
                        for(var i=0;i<data.length;i++){
                            var value = data[i].value;
                            var impactedOption = AJS.$("<option/>").attr("value", value).text(value);
                            impactedSelectField.append(impactedOption);
                        }
                        console.log("Successfully populated impacted dropdown!");
                    },
                    error: function () {
                        //callback(true);
                        console.log("Got impacted thru route!");
                    }
                });
        }
    });

    populateIncidentStartDate();


    //Incident Start Date will be of the format : May 10, 2016 03:05 PM
    function populateIncidentStartDate() {
        addNewFormatsToDateProto();
        var today = new Date();
        var dateString = today.format("mmm d, yyyy hh:MM TT");
        $('#incidentstart').val(dateString);
    }

    /* Handle the primary button click */
    function buttonClicked(event, closeDialog) {
        if (event.action === "dialog.yes") {
            AJS.$('.error').html('') //Clear all error messages
            $("#errorcreatingticket").html('');
            // handle the action here
            console.log("TIME TO CREATE AN INCIDENT");
            console.log("Parameters :");

            var summary = $('#incidentsummary').val();
            var description = $('#incidentdescription').val();
            var product = $( "#proddmkt option:selected" ).val();
            var severity = $("#incidentsev option:selected").val();
            var customerimpact = $('#customerimpact').val();

            var clientsimpacted = $('#clientsimpacted').val();
            var impacted = $('#impacted').val();
            var customertalkingpoints = $("#customertalkingpoints").val();
            var incidentstart = $('#incidentstart').val();

            console.log("Summary : " + summary + " ,Description " + description + " ,Solution Group " + solutionGroup);
            console.log("Product : " + product + " ,Severity " + severity+ " ,Customer Impact " + customerimpact);
            console.log("Clients Impacted : " + clientsimpacted + " ,Impacted: " + impacted+
                " ,Customer Talking Points :  " + customertalkingpoints + " ,Incident Start: " + incidentstart);

            var validationPassed = true;

            if (!clientsimpacted) {
                AJS.$("#clientsimpacted").focus().siblings(".error").html("Please select the clients impacted");
                closeDialog(false);
                validationPassed = false;
            }


            if (!summary) {
                AJS.$("#incidentsummary").focus().siblings(".error").html("You must specify a summary of the issue.");
                closeDialog(false);
                validationPassed = false;
            }

            if (!description) {
                AJS.$("#incidentdescription").focus().siblings(".error").html("Description is required.");
                closeDialog(false);
                validationPassed = false;
            }

            if (clientsimpacted === "External" && !product) {
                AJS.$("#proddmkt").focus().siblings(".error").html("Product is required for clients impacted External.");
                closeDialog(false);
                validationPassed = false;
            }

            if (!severity) {
                AJS.$("#incidentsev").focus().siblings(".error").html("Severity is required.");
                closeDialog(false);
                validationPassed = false;
            }

            if (!customerimpact) {
                AJS.$("#customerimpact").focus().siblings(".error").html("Customer Impact is required.");
                closeDialog(false);
                validationPassed = false;
            }

            if (clientsimpacted === "Internal" && !impacted) {
                AJS.$("#impacted").focus().siblings(".error").html("Please select Impacted since Clients Impacted is Internal");
                closeDialog(false);
                validationPassed = false;
            }
            
            if (!incidentstart) {
                AJS.$("#incidentstart").focus().siblings(".error").html("Incident Start cannot be left blank");
                closeDialog(false);
                validationPassed = false;
            }

            try {
                var incidentStartDate = new Date(incidentstart);
                var now = new Date();
                if (incidentStartDate > now) {
                    AJS.$("#incidentstart").focus().siblings(".error").html("Start date cannot be greater than current date");
                    closeDialog(false);
                    validationPassed = false;
                }  else {
                    incidentstart = incidentStartDate.format("mmm d, yyyy hh:MM TT");
                }
            } catch (e) {
                AJS.$("#incidentstart").focus().siblings(".error").html(e.message + ", please enter date in the exact format mmm d, yyyy hh:MM TT ");
                closeDialog(false);
                validationPassed = false;
            }

            if (validationPassed){
                //Go ahead and create the incident ticket
                console.log("CREATING INCIDENT");
                HipChat.auth.withToken(function (err, token) {
                    if (!err) {
                        //Then, make an AJAX call to the add-on backend, including the JWT token
                        //Server-side, the JWT token is validated using the middleware function addon.authenticate()
                        $.ajax(
                            {
                                type: 'POST',
                                url: '/createticket',
                                headers: {'Authorization': 'JWT ' + token},
                                dataType: 'json',
                                data: {
                                    projectkey : "ITIM",
                                    issuetype : "Incident",
                                    summary : summary,
                                    description : description,
                                    solutiongroup : solutionGroup,
                                    product : product,
                                    severity : severity,
                                    reporteremail : loggedInUserEmailId,
                                    customerimpact : customerimpact,
                                    clientsimpacted: clientsimpacted,
                                    impacted: impacted,
                                    customertalkingpoints: customertalkingpoints,
                                    incidentstart: incidentstart,
                                    incidentsource: "HipChat"
                                },
                                success: function (data) {
                                    console.log("Incident created!");
                                    console.log(data.issueid);
                                    closeDialog(true);
                                },
                                error: function (error) {
                                    //callback(true);
                                    console.log("Cannot create incident!");
                                    console.log(error);
                                    var errorMessage = error.responseText;
                                    $("#errorcreatingticket").html(errorMessage);
                                    closeDialog(false);
                                }
                            });
                    }
                });
            }
        } else {
            closeDialog(true); // you can also pass false if you want to prevent the dialog from closing (missing required fields, for instance)
        }

    }
    AP.register({
        "dialog-button-click": buttonClicked
    });

});

function addNewFormatsToDateProto() {
    /*
     * Date Format 1.2.3
     * (c) 2007-2009 Steven Levithan <stevenlevithan.com>
     * MIT license
     *
     * Includes enhancements by Scott Trenda <scott.trenda.net>
     * and Kris Kowal <cixar.com/~kris.kowal/>
     *
     * Accepts a date, a mask, or a date and a mask.
     * Returns a formatted version of the given date.
     * The date defaults to the current date/time.
     * The mask defaults to dateFormat.masks.default.
     */

    var dateFormat = function () {
        var    token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
            timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
            timezoneClip = /[^-+\dA-Z]/g,
            pad = function (val, len) {
                val = String(val);
                len = len || 2;
                while (val.length < len) val = "0" + val;
                return val;
            };

        // Regexes and supporting functions are cached through closure
        return function (date, mask, utc) {
            var dF = dateFormat;

            // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
            if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
                mask = date;
                date = undefined;
            }

            // Passing date through Date applies Date.parse, if necessary
            date = date ? new Date(date) : new Date;
            if (isNaN(date)) throw SyntaxError("invalid date");

            mask = String(dF.masks[mask] || mask || dF.masks["default"]);

            // Allow setting the utc argument via the mask
            if (mask.slice(0, 4) == "UTC:") {
                mask = mask.slice(4);
                utc = true;
            }

            var    _ = utc ? "getUTC" : "get",
                d = date[_ + "Date"](),
                D = date[_ + "Day"](),
                m = date[_ + "Month"](),
                y = date[_ + "FullYear"](),
                H = date[_ + "Hours"](),
                M = date[_ + "Minutes"](),
                s = date[_ + "Seconds"](),
                L = date[_ + "Milliseconds"](),
                o = utc ? 0 : date.getTimezoneOffset(),
                flags = {
                    d:    d,
                    dd:   pad(d),
                    ddd:  dF.i18n.dayNames[D],
                    dddd: dF.i18n.dayNames[D + 7],
                    m:    m + 1,
                    mm:   pad(m + 1),
                    mmm:  dF.i18n.monthNames[m],
                    mmmm: dF.i18n.monthNames[m + 12],
                    yy:   String(y).slice(2),
                    yyyy: y,
                    h:    H % 12 || 12,
                    hh:   pad(H % 12 || 12),
                    H:    H,
                    HH:   pad(H),
                    M:    M,
                    MM:   pad(M),
                    s:    s,
                    ss:   pad(s),
                    l:    pad(L, 3),
                    L:    pad(L > 99 ? Math.round(L / 10) : L),
                    t:    H < 12 ? "a"  : "p",
                    tt:   H < 12 ? "am" : "pm",
                    T:    H < 12 ? "A"  : "P",
                    TT:   H < 12 ? "AM" : "PM",
                    Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                    o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                    S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
                };

            return mask.replace(token, function ($0) {
                return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
            });
        };
    }();

    // Some common format strings
    dateFormat.masks = {
        "default":      "ddd mmm dd yyyy HH:MM:ss",
        shortDate:      "m/d/yy",
        mediumDate:     "mmm d, yyyy",
        longDate:       "mmmm d, yyyy",
        fullDate:       "dddd, mmmm d, yyyy",
        shortTime:      "h:MM TT",
        mediumTime:     "h:MM:ss TT",
        longTime:       "h:MM:ss TT Z",
        isoDate:        "yyyy-mm-dd",
        isoTime:        "HH:MM:ss",
        isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
        isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
    };

    // Internationalization strings
    dateFormat.i18n = {
        dayNames: [
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        ],
        monthNames: [
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        ]
    };

    // For convenience...
    Date.prototype.format = function (mask, utc) {
        return dateFormat(this, mask, utc);
    };

}
