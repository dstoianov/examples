package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.prepop.PrePopExitPage;
import com.revimedia.tests.configuration.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by dstoianov on 6/6/2014, 5:24 PM.
 */
public class PrePopExitTests extends BaseTest {
    public PrePopExitPage exitTruePage;

    @Test(groups = {"prepop", "not ready yet"}, enabled = false, description = "Is not implemented yet on CDS 2.0")
    public void shouldBeShownExitPopUpWindow() throws Exception {
        exitTruePage = new PrePopExitPage(driver);
        exitTruePage.reloadPageWithPrePopTrue();
        exitTruePage.prePopShowUp();

        assertThat(exitTruePage.getHeader(), is("Wait, don't leave! We are here to help you!"));
        assertThat(exitTruePage.getPhoneTextLink(), is("(888) 759-1914"));

        exitTruePage.closePopUp();

        assertThat(exitTruePage.getHeader(), not(is("Wait, don't leave! We are here to help you!")));
        assertThat(exitTruePage.getPhoneTextLink(), not(is("(888) 759-1914")));
    }
}
