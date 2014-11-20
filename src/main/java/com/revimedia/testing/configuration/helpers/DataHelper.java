package com.revimedia.testing.configuration.helpers;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Funker on 08.05.14.
 */
public class DataHelper {

    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "hh-mm-ss";
    public static final String DATE_TIME = DATE + "_" + TIME;
    public static final String NAMED_DATE = "MMM d, yyyy";
    public static final Locale LOCALE = Locale.US;


    public static Map<String, Integer> mapYears = new HashMap<String, Integer>();
    public static Map<String, String> mapDailyMileage = new HashMap<String, String>();
    public static Map<String, String> squareFootage = new HashMap<String, String>() {{
        put("< 1000 sq.ft", "900");
        put("1000 – 2000 sq.ft", "1500");
        put("2001 – 3000 sq.ft", "2500");
        put("3001 – 4000 sq.ft", "3500");
        put("4001 – 5000 sq.ft", "4500");
        put("5000+ sq.ft", "5500");
    }};

    public static Map<String, String> coverageAmount = new HashMap<String, String>() {{
        put("< $50,000", "40000");
        put("$50,000", "50000");
        put("$100,000", "100000");
        put("$150,000", "150000");
        put("$200,000", "200000");
        put("$250,000", "250000");
        put("$300,000", "300000");
        put("$350,000", "350000");
        put("$400,000", "400000");
        put("$450,000", "450000");
        put("$500,000", "500000");
        put("$500,000+", "550000");
    }};

    static {
        mapYears.put("0", 0);
        mapYears.put("1", 1);
        mapYears.put("2", 2);
        mapYears.put("3", 3);
        mapYears.put("4", 4);
        mapYears.put("5", 5);
        mapYears.put("5+", 10);

        mapDailyMileage.put("0", "0");
        mapDailyMileage.put("1-6", "1-3");
        mapDailyMileage.put("7-10", "4-5");
        mapDailyMileage.put("11-19", "6-9");
        mapDailyMileage.put("20-39", "10-19");
        mapDailyMileage.put("40-99", "20-49");
        mapDailyMileage.put("100+", "50+");
    }

    public static String phoneTransformation(String phone) {
        return phone.replaceAll("[^\\d]", "");
    }

    public static String phoneTransformationAddHyphens(String phone) {
        String s1 = phone.substring(0, 3);
        String s2 = phone.substring(3, 6);
        String s3 = phone.substring(6);
        return s1 + "-" + s2 + "-" + s3;
    }

    public static String generateInvalidAddress() {
        return "Invalid Street Address " + (new Random()).nextInt(999999);
    }

    public static String getCurrentDateAndTime() {
        return DateFormatUtils.format(new Date(), DATE_TIME, LOCALE);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static String dateTransformationAsXMLData(String nonFormattedDate) {
        // Jul 3, 1980  to 1980-07-03
        try {
            Date result = new SimpleDateFormat(NAMED_DATE, LOCALE).parse(nonFormattedDate);
            return DateFormatUtils.format(result, DATE, LOCALE);
        } catch (ParseException e) {
            System.out.println("Can't transform '" + nonFormattedDate + "' date to valid for compare");
            e.printStackTrace();
        }
        return nonFormattedDate;
    }

    public static String dateTransformationAsContactData(String nonFormattedDate) {
        // 1980-07-03  to Jul 3, 1980
        try {
            Date parse = (new SimpleDateFormat(DATE, LOCALE)).parse(nonFormattedDate);
            return DateFormatUtils.format(parse, NAMED_DATE, LOCALE);
        } catch (ParseException e) {
            System.out.println("Can't transform '" + nonFormattedDate + "' date to valid for compare");
            e.printStackTrace();
        }
        return nonFormattedDate;
    }

    public static String dateTransformInsuredSince(String key) {
        String end = DateFormatUtils.format(new Date(), "-MM-dd", LOCALE);
        Calendar now = Calendar.getInstance(LOCALE);
        int year = now.get(Calendar.YEAR);
        int begin = year - mapYears.get(key);
        return Integer.toString(begin) + end;
    }

    public static String dateTransformInsuredSince(String insuredSinceYear, String insuredSinceMonths) {
        Calendar now = Calendar.getInstance(LOCALE);
        now.add(Calendar.YEAR, -mapYears.get(insuredSinceYear));
        now.add(Calendar.MONTH, -Integer.parseInt(insuredSinceMonths));
        return DateFormatUtils.format(now, DATE, LOCALE);
    }

    public static String addMonthToNow(int i, String format) {
        Calendar now = Calendar.getInstance(LOCALE);
        now.add(Calendar.MONTH, i);
        return DateFormatUtils.format(now, format, LOCALE);
    }


/*    public static String dateTransformExpirationDate(String insuredSinceMonths) {
        try { // Dec -> 2014-12-"DAY_OF_MONTH"
            Calendar c = Calendar.getInstance(LOCALE);
            int yearNow = c.get(Calendar.YEAR);
            int monthNow = c.get(Calendar.MONTH) + 1;
            int dayNow = c.get(Calendar.DAY_OF_MONTH);
            Date mmm = (new SimpleDateFormat("MMM", LOCALE)).parse(insuredSinceMonths);
            String mm = DateFormatUtils.format(mmm, "MM", LOCALE);
            if (monthNow > Integer.parseInt(mm)) {
                return Integer.toString(yearNow + 1) + "-" + mm + "-" + String.format("%02d%n", dayNow).trim();
            } else {
                return Integer.toString(yearNow) + "-" + mm + "-" + String.format("%02d%n", dayNow).trim();
            }
        } catch (ParseException e) {
            System.out.println("Can't transform '" + insuredSinceMonths + "' date to valid for compare!!!");
            e.printStackTrace();
        }
        return insuredSinceMonths;
    }*/

    public static String dateTransformExpirationDate(String insuredSinceMonths) {
        DateTime instance = DateTimeFormat.forPattern("MMM").withLocale(LOCALE).parseDateTime(insuredSinceMonths);
        int month = instance.getMonthOfYear();
        Calendar cal = Calendar.getInstance(LOCALE);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int i = (currentMonth < month) ? month - currentMonth : 12 + month - currentMonth;
        Date result = DateUtils.addMonths(new Date(), i);
        return DateFormatUtils.format(result, DATE, LOCALE);
    }

    public static String transformDailyMileage(String dailyMileage) {
        return mapDailyMileage.get(dailyMileage);
    }

    public static String transformYears(String dailyMileage) {
        return Integer.toString(mapYears.get(dailyMileage));
    }

    public static String getCurrentDatePlus1Month() {
        Date newDate = DateUtils.addDays(new Date(), 1);
        Date result = DateUtils.addMonths(newDate, 1);
        return DateFormatUtils.format(result, DATE, LOCALE);
    }

    private static String addMonth(String mmm) {
        DateTime instance = DateTimeFormat.forPattern("MMM").withLocale(LOCALE).parseDateTime(mmm);
        int month = instance.getMonthOfYear();
        Calendar cal = Calendar.getInstance(LOCALE);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int i = (currentMonth < month) ? month - currentMonth : 12 + month - currentMonth;
        Date result = DateUtils.addMonths(new Date(), i);
        return DateFormatUtils.format(result, DATE, LOCALE);
    }

    public static void main(String[] args) {
        System.out.println(addMonth("Dec"));
        System.out.println(addMonth("Oct"));
        System.out.println(addMonth("Jul"));
        System.out.println(addMonth("Jan"));
        System.out.println(addMonth("Feb"));
        // 1980-07-03  to Jul 3, 1980
        System.out.println(dateTransformationAsXMLData("Jul 3, 1980"));
    }

}
