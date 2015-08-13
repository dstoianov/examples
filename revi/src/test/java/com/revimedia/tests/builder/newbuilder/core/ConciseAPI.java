package com.revimedia.tests.builder.newbuilder.core;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Funker on 12.08.2015.
 */
public abstract class ConciseAPI {

    private static final Logger LOG = LoggerFactory.getLogger(ConciseAPI.class);

    public abstract WebDriver getDriver();

    protected WebElement $(By locator) {
//        LOG.info("\tFind element '{}'", locator.toString());
        return getDriver().findElement(locator);
    }

    protected WebElement $(String cssSelector) {
        return $(By.cssSelector(cssSelector));
    }

    protected List<WebElement> $$(By locator) {
//        LOG.info("\tFind elements '{}'", locator.toString());
        return getDriver().findElements(locator);
    }

    protected List<WebElement> $$(String cssSelector) {
        return $$(By.cssSelector(cssSelector));
    }

    protected void refresh() {
        $("body").sendKeys(Keys.F5);
    }

    protected void open(String url) {
        LOG.info("Open url " + url);
        getDriver().get(url);
    }

    public void sleep(long i) {
        LOG.info("Waiting for a '{}' sec...", i / 1000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
