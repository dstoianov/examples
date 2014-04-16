package grid.test;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


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
