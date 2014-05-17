package com.revimedia.tests.configuration.dataproviders;

import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contacts;
import org.testng.annotations.DataProvider;

/**
 * Created by stde on 4/28/2014.
 */
public class AutoDataProvider extends DataProviderHelper {

    @DataProvider
    public static Object[][] contactAndStaticDataAutoMFS() {
        Object contact = unMarshalToObject("./src/test/resources/data/leads_data_1000.xml", Contacts.class);
        Object[][] result = {
                {contact, new StaticDataAutoMFS()},
        };
        return result;
    }

    @DataProvider
    public static Object[][] contactData() {
        return new Object[][]{
                {unMarshalToObject("./src/test/resources/data/leads_data_1000.xml", Contacts.class)},
        };
    }
}
