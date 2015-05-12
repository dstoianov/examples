package com.tipsandtricks.webelement.custom.elementV2.impl;

import org.openqa.selenium.WebElement;

/**
 * Created by Funker on 07.01.2015.
 */

public class Element implements IElement, Named {

    protected WebElement webElement;
    protected String name;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
