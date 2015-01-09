package tipsandtricks.custom.elementV2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import tipsandtricks.custom.elementV2.impl.CheckBox;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 08.01.2015.
 */
public class ElementsTest {

    public WebDriver driver = new FirefoxDriver();

    @Test
    public void testCheckBox() throws Exception {
        ExamplePage page = new ExamplePage(driver);

//        driver.get("http://sislands.com/coin70/week4/chkBoxTest.htm");
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        List<CheckBox> checkBoxList = page.getCheckBoxList();

        assertThat(checkBoxList.size(), is(2));

        page.getCheckBox().setChecked(false);
        assertThat(page.getCheckBox().isChecked(), is(false));

        page.getCheckBox().setChecked(true);
        page.getCheckBox().setChecked(true);
        assertThat(page.getCheckBox().isChecked(), is(true));

        boolean checked = page.getCheckBoxAsWebElement().isSelected();
        assertThat(checked, is(true));
    }

    @Test
    public void testButton() throws Exception {
        ExamplePage page = new ExamplePage(driver);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        page.getButton().click();
        String html = page.getButton().getHtml();
    }


    @Test
    public void testSelect() throws Exception {
        ExamplePage page = new ExamplePage(driver);
        driver.get("http://sislands.com/coin70/week4/selectex.htm");

        String text = "lemur";
        page.getSelect().selectByVisibleText(text);
        assertThat(page.getSelect().getFirstSelectedOption().getText(), is(text));

        assertThat(page.getSelect().getOptions().size(), is(6));
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
