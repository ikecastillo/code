package com.dt.jira.plugin.genericbarchart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class that does all the date and time related operations needed by the gadgets.
 * For now, only the new all-in-one gadget uses this.
 *
 * Created by Yagnesh.Bhat on 8/17/2015.
 */
public class DateOperations {

    /**
     * reads start date
     */
    public static String getStartDate(String MMM_YYYY){
        SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat currentFormat = new SimpleDateFormat("MMM-yyyy");
        String currntMonth = MMM_YYYY;
        Date d=null;
        String startd ="";
        try {
            d=currentFormat.parse(currntMonth);
            startd = startdateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startd;

    }

    /**
     * reads end date
     */
    public static String getLastDate(String MMM_YYYY){
        SimpleDateFormat lastdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat currentFormat = new SimpleDateFormat("MMM-yyyy");
        String currntMonth = MMM_YYYY;
        Date d=null;
        try {
            d=currentFormat.parse(currntMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String endd = "";
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = c.getTime();
        endd = lastdateFormat.format(date);
        return endd;
    }

    /**
     * Returns the next date in the form yyyy-MM-dd
     * @param endDate of form yyyy-MM-dd
     * @return next date after endDate in the form yyyy-MM-dd
     */
    public static String getNextDate(String endDate) {
        SimpleDateFormat nextdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(nextdateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);  // number of days to add
        String nextDate = nextdateFormat.format(c.getTime());  // dt is now the new date
        return nextDate;
    }

    /**
     * reads month and year
     */
    public static String getMonthYear(String date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date d=null;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String month = new SimpleDateFormat("MMM").format(d.getTime());
        String year = new SimpleDateFormat("yyyy").format(d.getTime());
        return month+"-"+year;
    }

    /**
     * Calculate list of months exist between start and end date
     */
    public static String getListOfMonths(String startdate,String enddate){
        StringBuffer months = new StringBuffer();
        String startDate = startdate;
        String endDate = enddate;
        SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedStartDate=null;
        Date convertedEndDate=null;
        try {
            convertedStartDate = startdateFormat.parse(startDate);
            convertedEndDate = startdateFormat.parse(endDate);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        while(convertedStartDate.compareTo(convertedEndDate)<=0){
            String res = getNextMonth(startDate);
            months.append(new SimpleDateFormat("MMM-yyyy").format(convertedStartDate.getTime()));
            months.append(",");
            try {
                convertedStartDate = startdateFormat.parse(res);
                startDate = startdateFormat.format(convertedStartDate);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        String bufferStr  = "";
        if(months!=null && months.length()>0)
            bufferStr = months.toString().substring(0, months.length()-1);
        return bufferStr;
    }


    /**
     * reads next month
     */
    public static  String getNextMonth(String dateString){
        SimpleDateFormat startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate=null;
        try {
            convertedDate = startdateFormat.parse(dateString);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(convertedDate);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date = c.getTime();

        Date d = addDays(date, 1);
        String returnDate=null;
        returnDate = startdateFormat.format(d);

        return returnDate;
    }

    public static  Date addDays(Date d, int days)
    {
        Date date = d;
        date.setTime(d.getTime() + (days * 1000 * 60 * 60 * 24));

        return date;
    }
}


