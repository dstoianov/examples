package tipsandtricks.custom.elementV2.impl;

import org.openqa.selenium.WebElement;

/**
 * Created by Funker on 08.01.2015.
 */
public class Button extends Element {

    public Button(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void click() {
        webElement.click();
    }
}
