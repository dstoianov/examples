package tipsandtricks.custom.elementV2.impl;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Funker on 09.01.2015.
 */
public class Select extends Element {

    private org.openqa.selenium.support.ui.Select innerSelect = null;

    public Select(WebElement webElement) {
        super(webElement);
    }

    public List<WebElement> getOptions() {
        return getSelect().getOptions();
    }

    public void selectByVisibleText(String text) {
        getSelect().selectByVisibleText(text);
    }

    public WebElement getFirstSelectedOption() {
        return getSelect().getFirstSelectedOption();
    }

    private org.openqa.selenium.support.ui.Select getSelect() {
        if (innerSelect == null) {
            innerSelect = new org.openqa.selenium.support.ui.Select(webElement);
        }
        return innerSelect;
    }
}
