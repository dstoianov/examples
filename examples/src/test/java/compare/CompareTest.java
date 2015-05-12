package compare;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by dstoianov on 6/6/2014, 3:29 PM.
 */
public class CompareTest {

    @Test
    public void testName() throws Exception {

        Attribute attribute1 = new Attribute("type1", "value1");
        Attribute attribute2 = new Attribute("type1", "value1");
        Attribute attribute3 = new Attribute("type1", "value1");
        attribute3.setType("type2");


        int i = attribute1.compareTo(attribute2);
        int i1 = attribute1.compareTo(attribute3);

        System.out.println(i);
        System.out.println(i1);
    }


    @Test
    public void testName2() throws Exception {
        Attribute attribute1 = new Attribute("type1", "value1");
        Attribute attribute2 = new Attribute("type1", "value1");
        Attribute attribute3 = new Attribute("type1", "value1");
        attribute3.setType("type2");

        boolean equals = attribute1.equals(attribute2);
        boolean equals2 = attribute1.equals(attribute3);

        System.out.println(equals);
        System.out.println(equals2);
    }

    @Test
    public void testName3() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "10-08-1982 10:20:56";
        Date d = sdf.parse(dateInString);

//        Date d = new Date();
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, 10);
        Date time = cal.getTime();
    }
}
