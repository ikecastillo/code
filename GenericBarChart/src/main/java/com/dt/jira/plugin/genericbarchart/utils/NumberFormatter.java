package com.dt.jira.plugin.genericbarchart.utils;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Utility class to do number formatting operations
 *
 * Created by yagnesh.bhat on 9/2/2015.
 */
public class NumberFormatter {

    /**
     * Apply the default number format
     * @param total total value
     * @return
     */
    public static String getNumberFormat(long total){
        String totalStr = "0";
        NumberFormat defaultFormat = NumberFormat.getInstance();
        totalStr = defaultFormat.format(total);
        return totalStr;
    }

    /**
     * Convert the Decimal format to long
     * @param incidentcount - incidentcount value
     * @return
     */
    public static long parseNumberFormat(String incidentcount){

        NumberFormat defaultFormat = NumberFormat.getInstance();
        long l = 0;
        try {
            Number num = defaultFormat.parse(incidentcount);
            l = num.longValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }
}
