package tipsandtricks.custom.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Funker on 08.01.2015.
 */
public class Part2ExampleTest {

    WebDriver driver;

    @Test
    public void simple() {
        driver = new FirefoxDriver();
        Part2ExamplePage page = new Part2ExamplePage(driver);

        driver.get("http://sislands.com/coin70/week4/chkBoxTest.htm");

        Assert.assertFalse(page.checkBox.isChecked());
        page.checkBox.check();
        page.checkBox.check();

        Assert.assertTrue(page.checkBox.isChecked());
        page.checkBox.uncheck();
        page.checkBox.uncheck();

        Assert.assertFalse(page.checkBox.isChecked());

        String innerHTML = page.checkBox.getWrappedElement().getAttribute("innerHTML");
        String html = page.checkBox.getHtml();

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
