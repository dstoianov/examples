package com.tipsandtricks.webelement.custom.element;

import com.tipsandtricks.webelement.custom.element.core.ElementFactory;
import com.tipsandtricks.webelement.custom.element.elements.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

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
