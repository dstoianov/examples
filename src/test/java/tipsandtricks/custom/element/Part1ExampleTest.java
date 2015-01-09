package tipsandtricks.custom.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import tipsandtricks.custom.element.elements.CheckBox;
import tipsandtricks.custom.element.elements.impl.CheckBoxImpl;

/**
 * Created by Funker on 07.01.2015.
 */
public class Part1ExampleTest {

    @FindBy(xpath = "(//input)[1]")
    private WebElement checkBox;

    @FindBy(xpath = "//form")
    private WebElement form;

    @Test
    public void simple() {

        WebDriver driver = new FirefoxDriver();
        PageFactory.initElements(driver, this);

//        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.get("http://sislands.com/coin70/week4/chkBoxTest.htm");
        CheckBox wrappedCheckBox = new CheckBoxImpl(checkBox);

        Assert.assertFalse(wrappedCheckBox.isChecked());
        wrappedCheckBox.check();
        wrappedCheckBox.check();
        Assert.assertTrue(wrappedCheckBox.isChecked());
        wrappedCheckBox.uncheck();
        wrappedCheckBox.uncheck();
        Assert.assertFalse(wrappedCheckBox.isChecked());

        String innerHTML = checkBox.getAttribute("innerHTML");
        String html = wrappedCheckBox.getHtml();


        CheckBox wrapedForm = new CheckBoxImpl(form);
        String innerHTML1 = form.getAttribute("innerHTML");
        String html1 = wrapedForm.getHtml();

        driver.quit();

    }

}
