(function($) {
	JIRA.bind(JIRA.Events.NEW_CONTENT_ADDED, function(e, context, reason) {
		
		var createUser = AJS.$("#create-issue-submit").val();
		var createAdmin = AJS.$("#issue-create-submit").val();
		var edit = AJS.$("#edit-issue-submit").val();

		if (edit == "Update") {
				AJS.$('#incidentDummyTable').hide();
	    }
		

		

		function faBuildHtmlObj(faIssueType) {

			var faObj = faIssueType.split(":,");

			var faDataList = new Array();
			if (faObj.length > 1) {
				for ( var i = 0, l = faObj.length; i < l; i++) {
					var innerArr = faObj[i];
					var faMapData = new Array();
					var length = faObj.length - 1;

					var splitArr = innerArr.split(",");
					var splitLen = splitArr.length;
					var desc = "";
					var lastIndex = 0;
					if (splitLen > 4) {
						desc = "";

						for ( var j = 2, k = splitLen; j < k; j++) {

							if (j != (splitLen - 1)) {
								if (j == 2) {
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
						desc = splitArr[2];
						lastIndex = 3;
					}

					var splitArr = innerArr.split(",");

					var dateForm = splitArr[0] + "," + splitArr[1];
					faMapData.push(dateForm);
					
					faMapData.push(desc);
					if (i == length) {

						var index = splitArr[lastIndex].split(":");

						faMapData.push(index[0]);
					} else {
						faMapData.push(splitArr[lastIndex]);
					}
					faDataList.push(faMapData);
				}

			} else {
				var faMapData = new Array();
				var splitArr = faIssueType.split(",");
				var splitLen = splitArr.length;
				var desc = "";

				var lastIndex = 0;
				if (splitLen > 4) {
					desc = "";

					for ( var j = 2, k = splitLen; j < k; j++) {

						if (j != (splitLen - 1)) {
							if (j == 2) {
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
					desc = splitArr[2];
					lastIndex = 3;
				}

				var splitArr = faIssueType.split(",");
				var dateForm = splitArr[0] + "," + splitArr[1];
				faMapData.push(dateForm);
			
				faMapData.push(desc);
				var index = splitArr[lastIndex].split(":");
				faMapData.push(index[0]);
				faDataList.push(faMapData);
			}
			var dataStr = [];
			for ( var i = 0, l = faDataList.length; i < l; i++) {

				var faDate1 = faDataList[i][0];
				var faDesc1 = faDataList[i][1];
				var faIndex1 = faDataList[i][2];
				var resultDate = faDateValidation(faDate1);
				if (resultDate == false) {
					return false;
				}

				dataStr.push({
					fadate : faDate1,
					fadesc : faDesc1,
					faindex : faIndex1
				});
			}
			//var jsonStr = {
				//data : dataStr
			// };
			setValuetoDB(dataStr);
			//var stringVal = JSON.stringify(jsonStr);

			//AJS.$('#' + "actionItem").val(stringVal);

		}
		
		//convert json to html using library function
		function setValuetoDB(dataStr) {
			AJS.$('#' + "incidentDummyTable").html("<thead></thead><tbody></tbody>");
			AJS.$("#incidentDummyTable").jsonTable({
				head : [ 'FA Date', 'FA Description', 'FA Index' ],
				json : [ 'fadate', 'fadesc', 'faindex' ]
			});

			AJS.$("#incidentDummyTable").jsonTableUpdate({
				source : dataStr,
				callback : function() {

				}
			});
			//alert("setValuetoDB");
			var tableTag = "<table border=1>" + AJS.$("#incidentDummyTable").html()
					+ "</table>";
			AJS.$('#' + "actionItem").val(tableTag);
		}
		
		AJS.$("#edit-issue-submit").on('click', function() {
		     
			var faIssueType = AJS.$('#sampleItem').val();
			//alert("in fa js"+ faIssueType);
			if (faIssueType != "") {
				faBuildHtmlObj(faIssueType);
			} else {				
				var tableTag = "<table border=1><thead><tr><th bgcolor=#D0CECE>FA Date</th><th bgcolor=#D0CECE>FA Description</th><th bgcolor=#D0CECE><font color=#D0CECE>FA  Index</font></th></tr></thead><tbody></tbody></table>";
				//alert("no value in FurtherAction");
				AJS.$('#' + "actionItem").val(tableTag);
			}

		});

		function faDateValidation(tBoxData) {

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
