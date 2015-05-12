package grid.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


/**
 * Created with IntelliJ IDEA.
 * User: stoianod
 * Date: 12/26/13
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridTest2 extends Base {

    @BeforeMethod
    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void test21() {
        doSomething();
    }

    @Test
    public void test22() {
        doSomething();
    }

    @Test
    public void test23() {
        doSomething();
    }

    @Test
    public void test24() {
        doSomething();
    }

}
