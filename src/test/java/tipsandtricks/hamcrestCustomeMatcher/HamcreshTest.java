package tipsandtricks.hamcrestCustomeMatcher;


import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static tipsandtricks.hamcrestCustomeMatcher.AllFieldsMatcher.samePropertyValuesAs;


/**
 * Created by ozapolska on 09.11.2014.
 */
public class HamcreshTest {

    @Test
    public void testName() throws Exception {
        SomeObject someObject1 = new SomeObject("one", null, "three", "four", "rrr");
        SomeObject someObject2 = new SomeObject("zero", "two", "three", "four", null);


        assertThat(someObject1, samePropertyValuesAs(someObject2));
    }
}
