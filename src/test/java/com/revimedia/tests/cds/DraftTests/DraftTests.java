package com.revimedia.tests.cds.DraftTests;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.dto.Contacts;
import com.revimedia.testing.configuration.utils.JsUtils;
import com.revimedia.tests.configuration.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 08.05.14.
 */
public class DraftTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;


    @Test
    public void testCheckRequiredFields() throws Exception {
        driverPage = new DriverPage(driver);
        driverPage.clickOnContinue();

        assertThat(driverPage.getPageText(), containsString("Please enter zip code."));
        assertThat(driverPage.getAllErrors().size(), is(7));

        driverPage.fillInZipCode("20002").clickOnContinue();
        assertThat(driverPage.getAllErrors().size(), is(6));

        assertThat(driverPage.getPageText(), containsString("Please correct the errors above."));
        //assertThat();
    }


    @Test(groups = {"submit"}, enabled = true, dataProvider = "contactAndStaticDataAutoMFS")
    public void testCheckZipCode(Contacts contacts, StaticDataAutoMFS staticData) throws Exception {
        driverPage = new DriverPage(driver);
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        for (Contact contact : contacts.getContacts()) {
            driverPage.open(this.url);
            driverPage.fillInAllFields(contact, staticData);
            vehiclePage = driverPage.clickOnContinue();
            compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();

            String zipStateAndCity = compareAndSavePage.getZipStateAndCity();

            if (!zipStateAndCity.contains(contact.getCity() + ", " + contact.getState())) {
                //System.out.println(i + ")XXX Zip Code and City mismatched,  expected " + contact.getZipCode() + " " + contact.getCity() + " " + contact.getState());
                //System.out.println(i + ")XXX But  was " + zipStateAndCity.toString());
                buffer.append(i + ")XXX Zip Code and City mismatched,  expected " + contact.getZipCode() + " " + contact.getCity() + " " + contact.getState() + "\n");
                buffer.append(i + ")XXX But  was " + zipStateAndCity.toString() + "\n");
                i++;
            }
            //System.out.println(buffer.toString());

        }
        System.out.println("incorrect address is: " + i);
        System.out.println(buffer.toString());

    }


    @DataProvider
    public static Object[][] contactAndStaticDataAutoMFS() {
        return new Object[][]{
                {unMarshalToContact(xmlContactData, Contacts.class), new StaticDataAutoMFS()},
        };
    }


    public static String xmlContactData = "./src/test/resources/data/leads_data_1000.xml";

    static <T> Contacts unMarshalToContact(String xml_file_name, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            T data = (T) jaxbUnmarshaller.unmarshal(new File(xml_file_name));

            return (Contacts) data;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void waitForAjaxComplete() {
        log.info("waiting for ajax completion");
        final JsUtils js = new JsUtils(driver);
        new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        log.info("All ajax calls are complete");
    }

}
