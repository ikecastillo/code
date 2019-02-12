package com.dt.jira.pagerduty.intgt.plugin.utils;

/**
 * Created by Yagnesh.Bhat on 4/12/2016.
 */
public class IncidentDurationConverter {

    public static String convertToDaysHrsMins(String incidentDuration) {


        int incidentDurationMins = Integer.parseInt(incidentDuration.split(" ")[0]);
        if (incidentDurationMins == 0) {
            return "0 Minutes";
        }
        int incidentDurationDays = incidentDurationMins/1440;
        int incidentDurationDaysRemainder = incidentDurationMins % 1440;
        int incidentDurationHours = incidentDurationDaysRemainder/60;
        int incidentDurationMinutesRemainder = incidentDurationDaysRemainder % 60;

        StringBuffer convertedOutput = new StringBuffer();
        if (incidentDurationDays > 0)  {
            if (incidentDurationDays == 1) {
                convertedOutput.append(incidentDurationDays).append(" Day, ");
            } else {
                convertedOutput.append(incidentDurationDays).append(" Days, ");
            }
        }

        if (incidentDurationHours > 0) {
            if (incidentDurationHours == 1) {
                convertedOutput.append(incidentDurationHours).append(" Hour, ");
            } else {
                convertedOutput.append(incidentDurationHours).append(" Hours, ");
            }
        }

        if (incidentDurationMinutesRemainder > 0) {
            if (incidentDurationMinutesRemainder == 1) {
                convertedOutput.append(incidentDurationMinutesRemainder).append(" Minute");
            } else {
                convertedOutput.append(incidentDurationMinutesRemainder).append(" Minutes");
            }
        }

        String convertedOutputStr = convertedOutput.toString();

        if (convertedOutputStr.substring(0, convertedOutputStr.length() - 1).endsWith(",")) {
            convertedOutputStr = convertedOutputStr.substring(0, convertedOutputStr.length() - 2);
        }

        return convertedOutputStr;
    }

    /**
     * Just some unit tests to make sure its doing the conversion right
     * @param args
     */
    public static void main(String[] args) {


        String convertedOutput = convertToDaysHrsMins("0 Minutes");
        System.out.println("0 minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("20 Minutes");
        System.out.println("20 minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("60 Minutes");
        System.out.println("60 minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("1 Minute");
        System.out.println("1 Minute is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("61 Minutes");
        System.out.println("61 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("120 Minutes");
        System.out.println("120 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("200 Minutes");
        System.out.println("200 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("1200 Minutes");
        System.out.println("1200 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("2000 Minutes");
        System.out.println("2000 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("6567 Minutes");
        System.out.println("6567 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("1440 Minutes");
        System.out.println("1440 Minutes is " + convertedOutput);

        convertedOutput = convertToDaysHrsMins("2880 Minutes");
        System.out.println("2880 Minutes is " + convertedOutput);

    }
}
