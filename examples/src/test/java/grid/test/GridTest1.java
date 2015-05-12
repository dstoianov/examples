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
public class GridTest1 extends Base {

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
    public void test11() {
        doSomething();
    }

    @Test
    public void test12() {
        doSomething();
    }

    @Test
    public void test13() {
        doSomething();
    }

    @Test
    public void test14() {
        doSomething();
    }

}
