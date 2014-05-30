package com.revimedia.testing.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by dstoianov on 5/30/2014, 7:12 PM.
 * http://internetka.in.ua/webdrivereventlistener/
 * https://github.com/dstoianov/WebdriverPlatform/blob/master/src/main/java/org/freespace/testingplatform/webdriver/listneres/LoggingWebDriverEventListener.java
 */
public class WebDriverLogger implements WebDriverEventListener {

    @Override
    public void beforeNavigateTo(String s, WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String s, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeScript(String s, WebDriver driver) {

    }

    @Override
    public void afterScript(String s, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }
}
