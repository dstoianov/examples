package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.prepop.PrePopExitTruePage;
import com.revimedia.tests.configuration.BaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by dstoianov on 5/6/2014, 7:48 PM.
 */
public class PrePopParametersTests extends BaseTest {

    @Test
    public void testExitTrue() throws Exception {
        PrePopExitTruePage exitTruePage = new PrePopExitTruePage(driver);
        exitTruePage.reloadPageWithPrePopTrue();
        exitTruePage.prePopShowUp();

        assertThat(exitTruePage.getHeader(), is("Wait, don't leave! We are here to help you!"));
        assertThat(exitTruePage.getPhoneTextLink(), is("(888) 759-1914"));

        exitTruePage.closePopUp();

        assertThat(exitTruePage.getHeader(), not(is("Wait, don't leave! We are here to help you!")));
        assertThat(exitTruePage.getPhoneTextLink(), not(is("(888) 759-1914")));
    }
}
