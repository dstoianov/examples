package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.BrowserMobProxy;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import net.lightbody.bmp.core.har.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 23.04.14.
 */
public class AutoTests extends BaseTest {

    @Test(dataProvider = "contactData", dataProviderClass = AutoDataProvider.class)
    public void testPositiveSubmit(Contact contact) throws Exception {
        StaticDataAutoMFS staticData = new StaticDataAutoMFS();

        DriverPage driverPage = new DriverPage(driver);
        driverPage.fillInAllFields(contact, staticData);
        assertThat(driverPage.getPageText(), containsString(contact.getCity()));

        VehiclePage vehiclePage = driverPage.clickOnContinue();

        CompareAndSavePage compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);

        assertThat(compareAndSavePage.getZipStateAndCity(), containsString(contact.getCity() + ", " + contact.getState()));

        compareAndSavePage.submitForm();
        Thread.sleep(3000);
        Har haar = BrowserMobProxy.getHaar();
        List<HarEntry> entries = haar.getLog().getEntries();


        for (HarEntry entry : haar.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            if (request.getUrl().contains("submit")) {
                System.out.println("Has submit");

                System.out.println("------------------request----------response----------------");
                System.out.println(request.getUrl());
                System.out.println(request.getPostData().getParams());
                request.getPostData().getParams();
                for (HarPostDataParam list : request.getPostData().getParams()) {
                    System.out.println(list.getName());
                    System.out.println(list.getValue());
                }
                System.out.println("----------");
                System.out.println(response.getContent().getText());
                System.out.println("------------------request----------response----------------");
            }

            // assertThat(response.getStatus(), is(200));
        }



        //compareAndSavePage.
    }

    @Test
    public void testCheckRequiredFields() throws Exception {
        DriverPage driverPage = new DriverPage(driver);

        driverPage.clickOnContinue();

        assertThat(driverPage.getPageText(), containsString("Please enter zip code."));
        assertThat(driverPage.getAllErrors().size(), is(7));

        driverPage.fillInZipCode("20002").clickOnContinue();
        assertThat(driverPage.getAllErrors().size(), is(6));


        assertThat(driverPage.getPageText(), containsString("Please correct the errors above."));
        //assertThat();
    }

    @Test(dataProvider = "contactData", dataProviderClass = AutoDataProvider.class)
    public void testPrePop() throws Exception {


    }
}
