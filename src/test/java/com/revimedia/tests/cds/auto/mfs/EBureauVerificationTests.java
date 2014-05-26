package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.constants.Messages;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Funker on 23.05.14.
 */
public class EBureauVerificationTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"eBureau Verification"}, dataProvider = "contactAndStaticDataAutoMFSForeBureauChecking", dataProviderClass = AutoDataProvider.class)
    public void DRAFT_shouldBeShowneBureauVerificationMessage(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        //ACT
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);
        compareAndSavePage.clickSubmit();

        //Assert
        assertThat(compareAndSavePage.getPageText(), containsString(Messages.EBUREAU_VERIFICATION));
        assertThat(compareAndSavePage.getAllErrors().get(0).getText(), equalTo(Messages.EBUREAU_VERIFICATION));
    }
}
