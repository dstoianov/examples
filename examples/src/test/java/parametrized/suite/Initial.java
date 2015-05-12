package parametrized.suite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Initial {

    protected String URL;

    @Parameters({"url"})
    @Test
    public void setURL(String URL) {
        this.URL = URL;
    }

}
