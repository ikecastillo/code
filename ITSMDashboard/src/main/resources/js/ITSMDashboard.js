var ReportTypeGenerator = {
    generateYAxisColumnName: function(reportType) {
        var yAxis;
        switch (reportType) {
            case "IncidentCount":
                yAxis = "Incident Count";
                break;
            case "IncidentDuration":
                yAxis = "Incident Duration";
                break;
            case "ProblemCount":
                yAxis = "Problem Count";
                break;
            case "ProblemDuration":
                yAxis = "Problem Duration";
                break;
        }
        return yAxis;
    }
}


var YAxisLegend = {
	    generateYAxisLegend: function(legend, sigma) {	       	        
	        return legend+" "+sigma;
	    }
	}