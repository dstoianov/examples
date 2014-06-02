package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

/**
 * Created by dstoianov on 08.05.14.
 */
public class DisclaimersTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"disclaimer"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldBePresentOnEachPageDisclaimerPrivacyPolicyAndTermsOfUseLinks(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        //ACT
        driverPage = new DriverPage(driver);
        // Assert
        driverPage.verifyPrivacyPolicyAndTermsOfUseLinks();

        // ACT
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        // ASSERT
        vehiclePage.verifyPrivacyPolicyAndTermsOfUseLinks();

        // ACT
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        // Assert
        compareAndSavePage.verifyPrivacyPolicyAndTermsOfUseLinks();
        compareAndSavePage.verifyTCPADisclaimerAndLinksLinks();
        compareAndSavePage.verifyDisclaimerAndLinksLinks();
    }
}
