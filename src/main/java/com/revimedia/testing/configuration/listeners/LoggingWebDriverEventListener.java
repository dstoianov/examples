package com.revimedia.testing.configuration.listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;


public class LoggingWebDriverEventListener implements WebDriverEventListener {

    private Logger log = Logger.getLogger(this.getClass());
    private By lastFindBy;
    private String originalValue;

    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("WebDriver navigating to:'" + url + "'");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        //originalValue = element.getText();
        originalValue = element.getAttribute("value");
    }

    //TODO: Catch StaleElementException
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        //log.debug("!!!!!! WebDriver changing value in element found " + lastFindBy + " from '" + originalValue + "' to '" + element.getText() + "'");
        String newValue = element.getAttribute("value");
        if (!(originalValue.equalsIgnoreCase(newValue))) {
            log.info("WebDriver changing value from ('" + originalValue + "') to ('" + newValue + "')");
        }
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
    }

    public void onException(Throwable error, WebDriver driver) {
        if (error.getClass().equals(NoSuchElementException.class)) {
            log.error("WebDriver error: Element not found " + lastFindBy);
        } else {
            log.error("WebDriver error: ", error);
        }
    }

    public void beforeNavigateBack(WebDriver driver) {
    }

    public void beforeNavigateForward(WebDriver driver) {
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("WebDriver click on element - " + elementDescription(element));
    }

    public void beforeScript(String script, WebDriver driver) {
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
    }

    public void afterNavigateBack(WebDriver driver) {
    }

    public void afterNavigateForward(WebDriver driver) {
    }

    public void afterNavigateTo(String url, WebDriver driver) {
    }

    public void afterScript(String script, WebDriver driver) {
    }

    private String elementDescription(WebElement element) {
        String description = "tag:";//+ element.getTagName();
        if (element.getTagName().equalsIgnoreCase("option")) {
            description += "select ";
        } else if (element.getTagName().equalsIgnoreCase("input") && element.getAttribute("type").equalsIgnoreCase("radio")) {
            description += "radio button ";
            return description;
        } else if (element.getTagName().equalsIgnoreCase("button")) {
            description += "button ";
            return description;
        }

        if (!(element.getAttribute("id") != null && element.getAttribute("id") != "")) {
            description += " id: " + element.getAttribute("id");
        } else if (!(element.getAttribute("name") != null || element.getAttribute("name") != "")) {
            description += " name: " + element.getAttribute("name");
        }

        description += " ('" + element.getText() + "')";

        return description;
    }
}
