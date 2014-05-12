package com.revimedia.tests.cds.DraftTests;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.HasXPath.hasXPath;

/**
 * Created by dstoianov on 5/12/2014, 5:38 PM.
 */
public class HasXPath2Test {

    private static final String EXAMPLE_XML = "<stuff>\n"
            + "     <thing>hat</thing>\n" + "       <thing>cat</thing>\n"
            + "     <thing>car</thing>\n" + "</stuff>";

    // some trivial tests to see if XPath 2.0 is available

    @Test
    public void theEndsWithFunctionIsAvailable() {
        assertThat(the(EXAMPLE_XML),
                hasXPath("count(//thing[ends-with(., 'at')])", equalTo("2")));
    }

}
