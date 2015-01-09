package parametrized.suite;

import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Test3 extends Initial {


    @Test//(groups = "test", dependsOnGroups = "init")
    public void testTest3() throws Exception {
        System.out.println(URL);
    }


}
