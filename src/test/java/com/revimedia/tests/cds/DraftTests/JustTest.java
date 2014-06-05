package com.revimedia.tests.cds.DraftTests;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dstoianov on 5/23/2014, 4:24 PM.
 */
public class JustTest {

    public static Map<String, Integer> map = new HashMap<String, Integer>();


    static {
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("5+", 10);
    }


    @Test
    public void testName() throws Exception {
        /// Jul 3, 1980  to 1980-07-03
        String nonFormattedDate = "Jan 12, 1980";
        //SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
        //DateFormat format = DateFormat.getDateInstance();
        DateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(nonFormattedDate);
        format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        System.out.println(format1);

    }


    public boolean equals(Object that, String[] fields) {

        try {
            for (String fieldName : fields) {
                if (!this.getClass().getField(fieldName).equals(that.getClass().getField(fieldName)))
                    return false;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Test
    public void testGson() throws Exception {

        StaticDataAutoMFS staticDataAutoMFS = new StaticDataAutoMFS();

        StaticDataAutoMFS copy = staticDataAutoMFS.clone();
        copy.setMake("qqqqq");
        //assertThat(staticDataAutoMFS, is(equalTo(copy)));
        //assertThat((Object) staticDataAutoMFS, equalTo((Object) copy));
        Assert.assertTrue(staticDataAutoMFS.equals(copy), "not Same");


    }

    @Test
    public void testCheckPort() throws Exception {

        List<Integer> portsList = Collections.synchronizedList(new ArrayList<Integer>());
        synchronized (portsList) {
            portsList.add(getPort());
            portsList.add(getPort());
            portsList.add(5245);
        }

        int newPort = getPort();

        if (portsList.indexOf(newPort) == -1) {
            synchronized (portsList) {
                portsList.add(newPort);
            }
        }
        int i = portsList.indexOf(5245);
        portsList.remove(i);


    }

    private int getPort() {
        Random rand = new Random();
        int min = 100;
        int max = 250;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Test
    public void testDateTests() throws Exception {
        String dec = dateTransformExpirationDate("Dec");
        String jun = dateTransformExpirationDate("Jun");
        String jan = dateTransformExpirationDate("Jan");


        // String s1 = dateTransformInsuredSince("3", "0");
        //String s2 = dateTransformInsuredSince("4", "10");
        // String s = dateTransformInsuredSince("4", "11");

    }

    public static String dateTransformExpirationDate(String insuredSinceMonths) {
        try { // Dec -> 2014-12-01
            Calendar c = Calendar.getInstance(Locale.ENGLISH);
            int yearNow = c.get(Calendar.YEAR);
            int monthNow = c.get(Calendar.MONTH) + 1;
            Date mmm = (new SimpleDateFormat("MMM", Locale.ENGLISH)).parse(insuredSinceMonths);
            String mm = DateFormatUtils.format(mmm, "MM", Locale.ENGLISH);
            if (monthNow >= Integer.parseInt(mm)) {
                return Integer.toString(yearNow + 1) + "-" + mm + "-01";
            } else {
                return Integer.toString(yearNow) + "-" + mm + "-01";
            }
        } catch (ParseException e) {
            System.out.println("Can't transform '" + insuredSinceMonths + "' date to valid for compare!!!");
            e.printStackTrace();
        }
        return insuredSinceMonths;
    }


    public static String dateTransformInsuredSince(String insuredSinceYear, String insuredSinceMonths) {
        Calendar now = Calendar.getInstance(Locale.ENGLISH);
        now.add(Calendar.YEAR, -map.get(insuredSinceYear));
        now.add(Calendar.MONTH, -Integer.parseInt(insuredSinceMonths));
        String end = DateFormatUtils.format(now, "yyyy-MM-dd", Locale.ENGLISH);
        return end;
    }


}
