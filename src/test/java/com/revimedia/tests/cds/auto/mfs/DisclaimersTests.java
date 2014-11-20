package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

import static com.revimedia.testing.cds.constants.Disclaimers.DISCLAIMER_AUTO;
import static com.revimedia.testing.cds.constants.Disclaimers.TCPA_DISCLAIMER_AUTO;

/**
 * Created by dstoianov on 08.05.14.
 */
public class DisclaimersTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"disclaimer"}, dataProvider = "contactAndExtraDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldBePresentOnEachPageDisclaimerPrivacyPolicyAndTermsOfUseLinks(Contact contact, ExtraDataAutoMFS extraData) throws Exception {
        //ACT
        driverPage = new DriverPage(driver);
        // Assert
        driverPage.verifyPrivacyPolicyAndTermsOfUseLinks();

        // ACT
        vehiclePage = driverPage.fillInAllFields(contact, extraData).clickOnContinue();
        // Assert
        vehiclePage.verifyPrivacyPolicyAndTermsOfUseLinks();

        // ACT
        compareAndSavePage = vehiclePage.fillInAllFields(extraData).clickOnContinue();
        // Assert
        compareAndSavePage.verifyPrivacyPolicyAndTermsOfUseLinks();
        compareAndSavePage.verifyTCPADisclaimerAndLinksAuto(TCPA_DISCLAIMER_AUTO);
        compareAndSavePage.verifyDisclaimerAndLinks(DISCLAIMER_AUTO);
    }
}
