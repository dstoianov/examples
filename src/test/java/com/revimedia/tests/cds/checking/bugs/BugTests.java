package com.revimedia.tests.cds.checking.bugs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.BugsDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by dstoianov on 6/2/2014, 6:43 PM.
 */
public class BugTests extends BaseTest {

    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"prepop"}, dataProvider = "contactAndStaticDataAutoMFSBugsTesting", dataProviderClass = BugsDataProvider.class)
    public void testRVMD_3395(Contact contact, StaticDataAutoMFS staticData) {
        driverPage = new DriverPage(driver);
        driverPage.open(PrePopParameters.generateURLForAutoMFSWithContactAndStatic(this.url, contact, staticData));
        vehiclePage = driverPage.fillInTheRestFields(staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();

        assertThat(compareAndSavePage.getDateOfBirthValue(), equalTo(contact.getBirthDate()));
    }
}
