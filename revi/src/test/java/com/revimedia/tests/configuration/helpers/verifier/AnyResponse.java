package com.revimedia.tests.configuration.helpers.verifier;

import com.revimedia.testing.configuration.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by dstoianov on 6/5/2014, 1:09 PM.
 */

public class AnyResponse {

    public static void verify(Response response) {
        assertThat(response, allOf(
                hasProperty("_success", equalTo("BaeOK")),
                hasProperty("success", is(true)),
                hasProperty("isWarning", is(false))
        ));
        assertThat(response.getTransactionId().length(), is(36));
    }

    public static void verifyUpsell(Response response) {
        assertThat(response, allOf(
                hasProperty("_success", equalTo("BaeOK")),
                hasProperty("success", is(true)),
                hasProperty("isWarning", is(false))
        ));
        assertThat(response.getTransactionId().substring(response.getTransactionId().length() - 4), is("-up1"));
    }

}
