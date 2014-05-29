package com.revimedia.tests.cds.DraftTests;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dstoianov on 5/23/2014, 4:24 PM.
 */
public class JustTest {


    @Test
    public void testName() throws Exception {
        //Expected: is "2013-05-23"
        //but: was "1"
        Map<String, Integer> map = new HashMap<>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("5+", 10);

        String key = "1";

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("-MM-dd");
        String end = dateFormat.format(date);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int begin = year - map.get(key);

        String result = Integer.toString(begin) + end;


    }


    @Test
    public void testIPAddress() throws Exception {

        InetAddress localHost = InetAddress.getLocalHost();


        //560-384-7995 is(123-456-7890)

        String s = "1234567890";
        String s1 = s.substring(0, 3);
        String s2 = s.substring(3, 6);
        String s3 = s.substring(6);


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
            portsList.add(getPort());
        }

        int newPort = getPort();

        if (portsList.indexOf(newPort) == -1) {
            synchronized (portsList) {
                portsList.add(newPort);
            }
        }
    }

    private synchronized int getPort() {
        Random rand = new Random();
        int min = 8085;
        int max = 8090;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    @Test
    public void testDateTest() throws Exception {

        String nonFormattedDate = "1977-11-15"; // to Nov 15, 1977

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(nonFormattedDate);
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String format1 = dateFormat.format(date);


        System.out.print(format1);
    }
}
