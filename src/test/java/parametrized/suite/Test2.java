package parametrized.suite;

import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Test2 extends Initial {



   @Test//(groups = "test", dependsOnGroups = "init")
    public void testTest2() throws Exception {
        System.out.println(URL);
    }


}
