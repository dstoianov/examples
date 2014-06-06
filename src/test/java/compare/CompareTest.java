package compare;

import org.testng.annotations.Test;

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
}
