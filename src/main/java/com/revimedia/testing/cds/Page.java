package com.revimedia.testing.cds;

import com.revimedia.testing.configuration.utils.JsUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static com.revimedia.testing.cds.PageConstants.PAGE_CONTENT;
import static com.revimedia.testing.cds.PageConstants.PAGE_ERRORS;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class Page {
    protected static final Logger log = Logger.getLogger(Page.class);
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
//        waitForSelectFill(driver, webElement);
        waitForAjaxComplete();
        new Select(webElement).selectByVisibleText(text);
    }

    public void selectRandomByIndex(WebElement webElement) {
        waitForAjaxComplete();
        Select select = new Select(webElement);
        List<WebElement> elements = select.getOptions();
        select.selectByIndex(randInt(1, elements.size() - 1));
    }

    public void selectRandomByIndexFromSecond(WebElement webElement) {
        waitForAjaxComplete();
        Select select = new Select(webElement);
        List<WebElement> elements = select.getOptions();
        select.selectByIndex(randInt(2, elements.size() - 1));
    }

    public String getPageText() {
        return driver.findElement(By.xpath(PAGE_CONTENT)).getText();
    }

    public List<WebElement> getAllErrors() {
        List<WebElement> list = driver.findElements(By.xpath(PAGE_ERRORS));
        return list;
    }

    public void waitForAjaxComplete() {
        log.info("waiting for ajax completion");
        final JsUtils js = new JsUtils(driver);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        log.info("All ajax calls are complete");
    }

    public void waitForSelectFill(WebDriver driver, WebElement selectElement) {
        log.info("waiting for select fill in some data");
        final Select select = new Select(selectElement);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return select.getOptions().size() > 1;
            }
        });
        log.info("select is filled in");
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


    public void selectDate(WebElement ddMonth, WebElement ddDay, WebElement ddYear, String birthDate) {

        //Mar 12, 1987
        int blank = birthDate.indexOf(" ");
        int comma = birthDate.indexOf(",");
        String month = birthDate.substring(0, 3);
        String day = birthDate.substring(birthDate.indexOf(" ") + 1, comma);
        String year = birthDate.substring(comma + 2);

        selectByValue(ddMonth, month);
        selectByValue(ddDay, day);
        selectByValue(ddYear, year);

    }


}
