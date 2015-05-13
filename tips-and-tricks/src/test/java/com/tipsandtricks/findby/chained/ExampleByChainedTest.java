package com.tipsandtricks.findby.chained;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Funker on 13.05.2015.
 */
public class ExampleByChainedTest {

    public static final By MESSAGE_SUBJECT = By.cssSelector("div#layout_3pane div.wm_message_pane_border > div.wm_message_headers > div > span");

    public static final By CHAINED = new ByChained(
            By.id("layout_3pane"),
            By.className("wm_message_pane_border"),
            By.className("wm_message_headers"),
            By.tagName("div"),
            By.tagName("span")
    );

    public WebDriver driver = new FirefoxDriver();

    @FindBys({@FindBy(tagName = "a"), @FindBy(tagName = "img")})
    List<WebElement> images;

    @Test(description = "How to use ByChained mechanism, it used to locate elements within a document using a series of other lookups")
    public void testName() {
        ByChained byChained = new ByChained(
                By.cssSelector("cssSelector"),
                By.tagName("div"),
                By.xpath("dfdh")
        );

        driver.findElement(byChained);

        driver.findElement(new ByChained(By.className("form-search"), By.tagName("button"))).click();
    }
}
