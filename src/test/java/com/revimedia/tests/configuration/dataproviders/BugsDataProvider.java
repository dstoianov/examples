package com.revimedia.tests.configuration.dataproviders;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import org.testng.annotations.DataProvider;

/**
 * Created by stde on 4/28/2014.
 */
public class BugsDataProvider extends DataProviderHelper {

    public static String xmlContactData = "./src/test/resources/data/leads_data_1000.xml";


    @DataProvider
    public static Object[][] contactAndStaticDataAutoMFSBugsTesting() {
        Contact contact1 = new Contact("Jackie", "Chan", "Male", "Oct 31, 1957", "3238550093", "6221 Monterey Rd 101", "miley.cyrus@hotmail.com", "90005", "LOS ANGELES", "CA");
        Contact contact2 = new Contact("Chuck", "Norris", "Female", "Feb 29, 1989", "3238550093", "123 Fake Street", "blah.blahblah@gmail.conm", "75201", "DALLAS", "TX");
        //birthdate=Jul 31, 1980
        return new Object[][]{
                {contact1, new StaticDataAutoMFS()},
                {contact2, new StaticDataAutoMFS()},
        };
    }

}
