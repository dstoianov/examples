package rvmd.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static rvmd.auto.pages.PageConstants.*;

/**
 * User: stoianod
 * Date: 4/9/14
 */
public class Page {
    public WebDriver driver;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clearAndType(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void selectByValue(WebElement webElement, String text) {
        new Select(webElement).selectByVisibleText(text);
    }

    public String getPageText() {
        return driver.findElement(By.xpath(PAGE_CONTENT)).getText();
    }

    public List<WebElement> getAllErrors() {
        List<WebElement> list = driver.findElements(By.xpath(PAGE_ERRORS));
        return list;
    }
}
