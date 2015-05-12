package datafactory;

import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.annotations.Test;

/**
 * Created by Funker on 19.04.14.
 */
public class DataFactoryTest {


    @Test
    public void testName() throws Exception {
        DataFactory df = new DataFactory();
        for (int i = 0; i < 10; i++) {
//            String address = df.getAddress()+","+df.getCity()+","+df.getNumberText(5);
//            String business = df.getBusinessName();
//            System.out.println(business + " located at " + address);
            System.out.println(df.getName());
            System.out.println(df.getFirstName());
            System.out.println(df.getLastName());
            System.out.println(df.getEmailAddress());
            System.out.println(df.getAddress());
            System.out.println(df.getAddressLine2());
            System.out.println(df.getStreetName());
            System.out.println(df.getCity());
            System.out.println(df.getBirthDate());
            System.out.println(df.getStreetSuffix());
//        System.out.println(df.getRandomText(20, 25));
//            System.out.println(df.getRandomWord(4, 10));


            System.out.println(df.getRandomText(1, 25));
            System.out.println(df.getRandomChars(20));
            System.out.println(df.getRandomWord(4, 10));


            System.out.println("--------------------------------------");
        }

    }

    @Test
    public void testName2() throws Exception {

        DataFactory df = new DataFactory();

        //favorite animal
        String[] values = {"Cat", "Dog", "Goat", "Horse", "Sheep"};
        for (int i = 0; i < 10; i++) {
            System.out.println(df.getItem(values, 95, "None"));

        }
        System.out.println("------------- ");

        for (int i = 0; i < 10; i++) {
            System.out.println(df.getItem(values));
        }
    }
}

