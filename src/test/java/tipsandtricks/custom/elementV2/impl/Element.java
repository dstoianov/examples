package tipsandtricks.custom.elementV2.impl;

import org.openqa.selenium.WebElement;

/**
 * Created by Funker on 07.01.2015.
 */

public class Element implements IElement {

    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    // универсальные методы для всех элементов

    @Override
    public String getHtml() {
        return webElement.getAttribute("innerHTML");
    }

    @Override
    public void click() {
        webElement.click();
    }

}
