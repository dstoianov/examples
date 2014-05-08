package com.revimedia.tests.configuration.helpers;

import com.revimedia.testing.configuration.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 08.05.14.
 */
public class SubmitVerifier {

    public static void verifyResponse(Response response) {
        assertThat(response, allOf(
                hasProperty("_success", equalTo("BaeOK")),
                hasProperty("success", is(true)),
                hasProperty("isWarning", is(false))
        ));
        assertThat(response.getTransactionId().length(), is(36));
    }

    public static void verifyRequest(String request) {
        //assertThat(xml, hasXPath("//something[@id='b']/cheese", equalTo("Cheddar")));
    }
}
