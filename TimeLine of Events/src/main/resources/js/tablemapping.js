(function($) {
	JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function(e, context, reason) {
		var createUser = AJS.$("#create-issue-submit").val();
		var createAdmin = AJS.$("#issue-create-submit").val();
		var edit = AJS.$("#edit-issue-submit").val();

		if (createUser == "Create" || createAdmin == "Create"
				|| edit == "Update") {
			AJS.$('#dummytable').hide();
		}
		
		//TimeLineEvent for user create page
		AJS.$("#create-issue-submit").click(
				function() {
					AJS.log("CREATE ISSUE CLICK DETECTED");
					var issueType = AJS.$("#issuetype-field").val();
					var projectType = AJS.$("#project-field").val();
					//AJS.log("Issue Type DETECTED IS " + issueType);
					//AJS.log("project Type detected is " + projectType);
					if (projectType == "IT Incident Management (ITIM)"
							&& issueType == "Incident") {
						var issueType = AJS.$('#sample').val();
						//alert(issueType);
						if (issueType != "") {
							//AJS.log("Issue Type is NOT Blank, calling to build html object : " + issueType );
							buildHtmlObj(issueType);
						} else {
							//alert("no value");
							//AJS.log("Blank ROW!!!");
							var tableTag = "<table style=border-style:none;width:100%>" +
								"<thead>" +
								"<tr bgcolor=#D0CECE>" +
								"<th style='border:1px solid Black;width:20%'>Date</th>" +
								"<th style='border:1px solid Black;width:20%'>Time</th>" +
								"<th style='border:1px solid Black;width:60%'>Description</th>" +
								"<th style='display:none;width:0%;background-color:#FFFFFF;border-left:1px;border-right:0;border-top:0;border-bottom:0'>" +
								"<font color=#FFFFFF>Index</font></th>" +
								"</tr>" +
								"</thead>" +
								"<tbody></tbody></table>";
							AJS.$('#' + "custome").val(tableTag);
						}
					}

				});

		//TimeLineEvent for admin create page
		AJS.$("#issue-create-submit").on(
				'click',
				function() {

					var projectType = AJS.$("#issue-create-project-name")
							.text();

					var issueType = AJS.$("#issue-create-issue-type").text();

					if (projectType == " IT Incident Management"
							|| projectType == "IT Incident Management"
							&& issueType == "Incident") {

						var issueType = AJS.$('#sample').val();
						//alert(issueType);
						if (issueType != "") {
							buildHtmlObj(issueType);
						} else {
							//alert("no value");
							var tableTag = "<table style=border-style:none;width:100%>" +
								"<thead>" +
								"<tr bgcolor=#D0CECE>" +
								"<th style='border:1px solid Black;width:20%'>Date</th>" +
								"<th style='border:1px solid Black;width:20%'>Time</th>" +
								"<th style='border:1px solid Black;width:60%'>Description</th>" +
								"<th style='display:none;width:0%;background-color:#FFFFFF;border-left:1px;border-right:0;border-top:0;border-bottom:0'>" +
								"<font color=#FFFFFF>Index</font></th>" +
								"</tr>" +
								"</thead>" +
								"<tbody></tbody></table>";
							AJS.$('#' + "custome").val(tableTag);
						}
					}

				});

		//parse Json object and convert to Html object
		function buildHtmlObj(issueType) {
			var obj = issueType.split(":,");
			var dataList = new Array();
			if (obj.length > 1) {
				for ( var i = 0, l = obj.length; i < l; i++) {
					var innerArr = obj[i];
					var mapData = new Array();
					var length = obj.length - 1;

					var splitArr = innerArr.split(",");
					var splitLen = splitArr.length;
					var desc = "";
					var lastIndex = 0;
					if (splitLen > 5) {
						for ( var j = 3, k = splitLen; j < k; j++) {

							if (j != (splitLen - 1)) {
								if (j == 3) {
									desc = splitArr[j];
								} else {
									
									desc += splitArr[j];
									
								}
								if (j != (splitLen - 2)) {
									desc += ",";
								}
							}
							lastIndex = j;
						}
					} 
					else {
						desc = splitArr[3];
						lastIndex = 4;
					}

					var splitArr = innerArr.split(",");

					var dateForm = splitArr[0] + "," + splitArr[1];
					mapData.push(dateForm);
					mapData.push(splitArr[2]);
					mapData.push(desc);
					if (i == length) {

						var index = splitArr[lastIndex].split(":");

						mapData.push(index[0]);
					} else {
						mapData.push(splitArr[lastIndex]);
					}
					dataList.push(mapData);
				}

			} else {
				var mapData = new Array();
				var splitArr = issueType.split(",");
				var splitLen = splitArr.length;
				var desc = "";
                var lastIndex = 0;
				if (splitLen > 5) {
					for ( var j = 3, k = splitLen; j < k; j++) {

						if (j != (splitLen - 1)) {
							if (j == 3) {
								desc = splitArr[j];
							} else {
								desc += splitArr[j];
							}
							if (j != (splitLen - 2)) {
								desc += ",";
							}
						}
						lastIndex = j;
					}
				} else {
					desc = splitArr[3];
					lastIndex = 4;
				}

				var splitArr = issueType.split(",");
				var dateForm = splitArr[0] + "," + splitArr[1];
				mapData.push(dateForm);
				mapData.push(splitArr[2]);
				mapData.push(desc);
				
				var index = splitArr[lastIndex].split(":");
				mapData.push(index[0]);
				dataList.push(mapData);
			}
			var dataStr = [];
			var htmldata = " ";
			var resultDate = true;
			for ( var i = 0, l = dataList.length; i < l; i++) {

				var date1 = dataList[i][0];
				var time1 = dataList[i][1];
				var desc1 = dataList[i][2];
				var index1 = dataList[i][3];
				resultDate = dateValidation(date1);
				
				if (resultDate == false) {
					return false;
				}

				dataStr.push({
					date : date1,
					time : time1,
					desc : desc1,
					index : index1
				});
			}
			setValuetoDB(dataStr);
			

		}
		
		//convert json to html using library function
		function setValuetoDB(dataStr) {
			AJS.$('#' + "dummytable").html("<thead></thead><tbody></tbody>");
			AJS.$("#dummytable").jsonTable({
				head : [ 'Date', 'Time', 'Description', 'Index' ],
				json : [ 'date', 'time', 'desc', 'index' ]
			});

			AJS.$("#dummytable").jsonTableUpdate({
				source : dataStr,
				callback : function() {

				}
			});
			var tableTag = "<table style=border-style:none;width:100%>" + AJS.$("#dummytable").html()
				+ "</table>";
			//alert(tableTag);
			AJS.$('#' + "custome").val(tableTag);
		}
		
		//TimeLineEvent for update page
		AJS.$("#edit-issue-submit").on('click', function() {

			AJS.log("Checking if timeline of events is edited");
			var issueType = AJS.$('#sample').val();
			//alert("in timeline js"+ issueType);
			if (issueType != "") {
				buildHtmlObj(issueType);
			} else {

				var tableTag = "<table style=border-style:none;width:100%>" +
					"<thead>" +
					"<tr bgcolor=#D0CECE>" +
					"<th style='border:1px solid Black;width:20%'>Date</th>" +
					"<th style='border:1px solid Black;width:20%'>Time</th>" +
					"<th style='border:1px solid Black;width:60%'>Description</th>" +
					"<th style='display:none;width:0%;background-color:#FFFFFF;border-left:1px;border-right:0;border-top:0;border-bottom:0'>" +
					"<font color=#FFFFFF>Index</font></th>" +
					"</tr>" +
					"</thead>" +
					"<tbody></tbody></table>";
				AJS.$('#' + "custome").val(tableTag);
			}

		});

		function dateValidation(tBoxData) {

			var months = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun",
					"Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
			var split = tBoxData.split(" ");

			if (split.length != 3) {
				alert("DateFormat  is not proper");
				return false;
			}
			if (split[0].length == 3) {
				var monthExist = false;
				for ( var i = 0, l = months.length; i < l; i++) {
					if (split[0] == months[i]) {
						monthExist = true;
						break;
					}
				}
				if (monthExist == false) {
					alert("Month is not proper");
					return false;
				}
			} else {
				alert("Month is not proper");
				return;
			}

			if (split[1].length == 3 || split[1].length == 2) {
				var split1 = split[1].split(",");

				var iNum = parseInt(split1[0]);
				var convLength = iNum.toString().length;
				var stringLen = split1[0].length;

				if (stringLen != convLength) {
					alert("Date is not proper");
					return false;
				}
				if (iNum == NaN) {
					alert("Date is not proper");
					return false;
				}
				if (split1[0] < 1 || split1[0] > 31) {
					alert("Date is not proper");
					return false;
				}
			} else {
				alert("Date is not proper");
				return false;
			}
			if (split[2].length != 4) {
				alert("Year is not proper");
				return false;
			}

		}
	});

})(AJS.$);
