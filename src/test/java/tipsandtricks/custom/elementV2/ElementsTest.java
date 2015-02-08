package tipsandtricks.custom.elementV2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import tipsandtricks.custom.elementV2.impl.CheckBox;

import java.io.File;
import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 08.01.2015.
 */
public class ElementsTest {

    public WebDriver driver = new ChromeDriver();
    final static String testPage = "./src/test/resources/test-page/jquery-popup/index.html";
    final static String form = "forms.html";

    @Test
    public void testCheckBox() throws Exception {
        ExamplePage page = new ExamplePage(driver);

//        driver.get("http://sislands.com/coin70/week4/chkBoxTest.htm");
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        List<CheckBox> checkBoxList = page.getCheckBoxList();
        List<WebElement> webElementsList = page.getWebElementsList();

        assertThat(checkBoxList.size(), is(2));
        assertThat(checkBoxList.get(0).toString(), is("Name for CheckBox List 1 [0]"));
        assertThat(checkBoxList.get(1).toString(), is("Name for CheckBox List 1 [1]"));

        assertThat(webElementsList.size(), is(2));
        assertThat(webElementsList.toString(), is("Name for list WebElements"));


        page.getCheckBox().setChecked(false);
        assertThat(page.getCheckBox().isChecked(), is(false));

        page.getCheckBox().setChecked(true);
        page.getCheckBox().setChecked(true);
        assertThat(page.getCheckBox().isChecked(), is(true));

        boolean checked = page.getCheckBoxAsWebElement().isSelected();

        assertThat(page.getCheckBoxAsWebElement().toString(), is("Name for single WebElement"));
        assertThat(checked, is(true));
    }

    @Test
    public void testButton() throws Exception {
        ExamplePage page = new ExamplePage(driver);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        page.getButton().click();

        String name = page.getButton().getName();
        String html = page.getButton().getHtml();

        assertThat(name, is("Name for Button"));
        assertThat(html, is("Add"));
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


    @Test
    public void testName() throws Exception {
        ElementsTest.get(driver, testPage);

    }


    public static void get(WebDriver driver, String resource) {
        URI formsHtml = new File(resource).toURI();
//        URL formsHtml = ElementsTest.class.getClassLoader().getResource(resource);
        if (formsHtml == null) {
            throw new RuntimeException();
        }
        driver.get(formsHtml.toString());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
