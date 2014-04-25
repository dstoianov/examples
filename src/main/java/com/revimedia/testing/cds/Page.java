package com.revimedia.testing.cds;

import com.revimedia.testing.configuration.utils.JsUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.revimedia.testing.cds.PageConstants.*;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class Page {
    public WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 60;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void clearAndType(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void selectByValue(WebElement webElement, String text) {
        waitForSelectFill(driver, webElement);
        new Select(webElement).selectByVisibleText(text);
    }

    public String getPageText() {
        return driver.findElement(By.xpath(PAGE_CONTENT)).getText();
    }

    public List<WebElement> getAllErrors() {
        List<WebElement> list = driver.findElements(By.xpath(PAGE_ERRORS));
        return list;
    }

    public void waitForAjaxComplete() {
        //log.verbose("waiting for ajax completion");
        final JsUtils js = new JsUtils(driver);
        wait.until(new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        // log.verbose("All ajax calls are complete");
    }

    public void waitForSelectFill(WebDriver driver, WebElement selectElement) {
        final Select select = new Select(selectElement);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return select.getOptions().size() > 1;
            }
        });
    }


}
