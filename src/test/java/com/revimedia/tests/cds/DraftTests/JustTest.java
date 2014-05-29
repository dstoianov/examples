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
        int min = 8085;
        int max = 8090;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }



}
