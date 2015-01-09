package tipsandtricks.custom.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import tipsandtricks.custom.element.core.ElementFactory;
import tipsandtricks.custom.element.elements.CheckBox;

/**
 * A page that needs to be initialized the usual way (new)
 */
public class Part2ExamplePage {

    @FindBy(xpath = "(//input)[1]")
    public CheckBox checkBox;

    public Part2ExamplePage(WebDriver driver) {
        ElementFactory.initElements(driver, this);
    }
}
