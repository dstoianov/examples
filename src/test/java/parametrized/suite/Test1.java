package parametrized.suite;

import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class Test1 extends Initial {



    @Test//(groups = "test", dependsOnGroups = "init")
    public void testTest1() throws Exception {
        System.out.println(URL);
    }



}
