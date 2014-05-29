package com.revimedia.tests.cds.DraftTests;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        int min = 8085;
        int max = 8090;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }



}
